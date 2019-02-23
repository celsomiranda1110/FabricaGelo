/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Movimento;

import Bean.Movimento;
import Bean.ProdutoMovimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MovimentoDAO;
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
public class AcaoGravaMovimento extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        boolean atualiza = true;
        String mensagemErro = "";
        String pagRetorno = "";      
        List<ProdutoMovimento> lstProdutos = new ArrayList<ProdutoMovimento>();
        MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
        Movimento movimento = (Movimento)sessao.getAttribute("movimento");
        
        
        if(movimento != null)
        {
        
            if (movimento.getSituacao().equals("FE"))
            {
                atualiza = false;
                sessao.setAttribute("avisoErro", "Movimento fechado");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Movimento.AcaoAbreMovimento");
                pagRetorno = "visao/erro.jsp";                 

            }
            if (movimento.getSituacao().equals("CN"))
            {
                atualiza = false;
                sessao.setAttribute("avisoErro", "Movimento cancelado");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Movimento.AcaoAbreMovimento");
                pagRetorno = "visao/erro.jsp";                 

            }  
        }
        
        if (atualiza)
        {

            // Dados de produto
            String idProduto = (req.getParameter("cmbProduto").equals("") || req.getParameter("cmbProduto") == null) ? "0" : req.getParameter("cmbProduto"); 
            String quantidade = (req.getParameter("txtQuantidade").equals("") || req.getParameter("txtQuantidade") == null) ? "0" : req.getParameter("txtQuantidade"); 
            String vlUnitario = (req.getParameter("txtVlUnitario").equals("") || req.getParameter("txtVlUnitario") == null) ? "0" : req.getParameter("txtVlUnitario");        
            String vlIcms = (req.getParameter("txtIcms").equals("") || req.getParameter("txtIcms") == null) ? "0" : req.getParameter("txtIcms");        
            String vlDesconto = (req.getParameter("txtVlDesconto").equals("") || req.getParameter("txtVlDesconto") == null) ? "0" : req.getParameter("txtVlDesconto");        

            if (!idProduto.equals("0"))
            {
                ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                

                double dbQuantidade = Double.parseDouble(quantidade);
                double dbVlUnitario = Double.parseDouble(vlUnitario);
                double dbIcms = Double.parseDouble(vlIcms);
                double dbDesconto = Double.parseDouble(vlDesconto);
                double dbVlTotal = 0;
                dbVlTotal = ((dbQuantidade * dbVlUnitario)  + ((dbQuantidade * dbVlUnitario) * (dbIcms/100)) - dbDesconto);                

                produtoMovimento.setIdProduto(Integer.parseInt(idProduto));
                produtoMovimento.setQuantidade(dbQuantidade);
                produtoMovimento.setValor(dbVlUnitario);
                produtoMovimento.setIcms(dbIcms);
                produtoMovimento.setDesconto(dbDesconto);
                produtoMovimento.setValorTotal(dbVlTotal);
                produtoMovimento.setOperacao("");   

                lstProdutos.add(produtoMovimento);
            }

            // dados do movimento
            String idColaborador = (req.getParameter("cmbColaborador").equals("") || req.getParameter("cmbColaborador") == null) ? "0" : req.getParameter("cmbColaborador");
            String tipoMovimento = (req.getParameter("cmbTipo").equals("") || req.getParameter("cmbTipo") == null) ? "" : req.getParameter("cmbTipo");
            String numero = (req.getParameter("txtNumero").equals("") || req.getParameter("txtNumero") == null) ? "" : req.getParameter("txtNumero");
            String dtLancamento = (req.getParameter("txtLancamento").equals("") || req.getParameter("txtLancamento") == null) ? "1900-01-01" : req.getParameter("txtLancamento");        


            movimento.setIdColaborador(Integer.parseInt(idColaborador));
            movimento.setTipo(tipoMovimento);
            movimento.setNumero(numero);
            movimento.setDataLancamento(dtLancamento);
            movimento.setDataEntrega(dtLancamento);
            movimento.setNotaFiscal("000");
            movimento.setSituacao("AB");
            movimento.setLstProdutoMovimento(lstProdutos);

            if (movimentoDAO.atualizar(movimento))
            {
                movimento.setIdMovimento(movimentoDAO.getIdentity());
                movimento = movimentoDAO.listaUm(movimento);
                sessao.setAttribute("movimento", movimento);
                sessao.setAttribute("lstProdutoMovimento",movimento.getLstProdutoMovimento());
                sessao.setAttribute("produtoMovimento", null);                
            }

            pagRetorno = "visao/movimento.jsp";
        }   
        
        
        
        return pagRetorno;
    }
}
