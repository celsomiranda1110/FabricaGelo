/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Movimento;

import Bean.Colaborador;
import Bean.ColaboradorProduto;
import Bean.Movimento;
import Bean.Produto;
import Bean.ProdutoMovimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import DAO.ProdutoDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoBuscaPreco extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        String mensagemErro = "";
        String pagRetorno = "";
        List<ProdutoMovimento> lstProdutos = new ArrayList<ProdutoMovimento>();
        Movimento movimento = (Movimento)sessao.getAttribute("movimento");
        ProdutoMovimento produtoMovimento = new ProdutoMovimento();
        
        // dados do movimento
        String idColaborador = (req.getParameter("cmbColaborador").equals("") || req.getParameter("cmbColaborador") == null) ? "0" : req.getParameter("cmbColaborador");
        String tipoMovimento = (req.getParameter("cmbTipo").equals("") || req.getParameter("cmbTipo") == null) ? "" : req.getParameter("cmbTipo");
        String numero = (req.getParameter("txtNumero").equals("") || req.getParameter("txtNumero") == null) ? "" : req.getParameter("txtNumero");
        String dtLancamento = (req.getParameter("txtLancamento").equals("") || req.getParameter("txtLancamento") == null) ? "1900-01-01" : req.getParameter("txtLancamento");        
        
        
//        // Dados de produto
        String idProduto = (req.getParameter("cmbProduto").equals("") || req.getParameter("cmbProduto") == null) ? "0" : req.getParameter("cmbProduto"); 
        double dbQuantidade = 0;
        double dbVlUnitario = 0;
        double dbIcms = 0;
        double dbDesconto = 0;
        double dbVlTotal = 0;
        
            
        if ((!idColaborador.equals("0")) && (!idProduto.equals("0")))
        {
            // verificando se o produto pertence ao rol de produtos do colaborador
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
            Colaborador colaborador = new Colaborador();
            colaborador.setIdColaborador(Integer.parseInt(idColaborador));
            colaborador = colaboradorDAO.listaUm(colaborador);
            if (colaborador != null)
            {
                Iterator itColaboradorProduto = colaborador.getLstColaboradorProduto().iterator();
                while (itColaboradorProduto.hasNext())
                {
                    ColaboradorProduto colaboradorProduto = (ColaboradorProduto)itColaboradorProduto.next();
                    if (colaboradorProduto.getIdProduto() == Integer.parseInt(idProduto))
                    {
                        dbVlUnitario = colaboradorProduto.getValor();
                        dbIcms = colaboradorProduto.getIcms();
                        dbDesconto = colaboradorProduto.getDesconto();
                    }
                }
            }
            
            // caso não encontre o produto no rol dos produtos do colaborador
            // adotar o valor do próprio produto
            if (dbVlUnitario == 0)
            {
                ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
                Produto produto = new Produto();
                produto.setIdProduto(Integer.parseInt(idProduto));
                produto = produtoDAO.listaUm(produto);
                if (produto != null)
                    dbVlUnitario = produto.getVlUnitario();
            }
            
            dbVlTotal = ((dbQuantidade * dbVlUnitario)  + ((dbQuantidade * dbVlUnitario) * (dbIcms/100)) - dbDesconto);                            

            produtoMovimento.setIdProduto(Integer.parseInt(idProduto));
            produtoMovimento.setQuantidade(dbQuantidade);
            produtoMovimento.setValor(dbVlUnitario);
            produtoMovimento.setIcms(dbIcms);
            produtoMovimento.setDesconto(dbDesconto);
            produtoMovimento.setValorTotal(dbVlTotal);
            produtoMovimento.setOperacao("");        
            
            pagRetorno = "FabricaGelo.Movimento.AcaoAbreMovimento";

        }

        if (movimento == null)
        {
            movimento = new Movimento();
            movimento.setSituacao("AB");
        }
        movimento.setIdColaborador(Integer.parseInt(idColaborador));
        movimento.setTipo(tipoMovimento);
        movimento.setNumero(numero);
        movimento.setDataLancamento(dtLancamento);
        movimento.setDataEntrega(dtLancamento);
        
        
        sessao.setAttribute("movimento", movimento);
        sessao.setAttribute("produtoMovimento", produtoMovimento); 
        
            
        return pagRetorno;
    }
}
