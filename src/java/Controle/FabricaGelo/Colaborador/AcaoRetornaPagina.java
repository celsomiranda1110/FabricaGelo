/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;

import Bean.Bairro;
import Bean.Colaborador;
import Bean.ColaboradorProduto;
import Bean.Movimento;
import Bean.ProdutoMovimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
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
public class AcaoRetornaPagina extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        Movimento movimento = (Movimento)sessao.getAttribute("movimento");
        Colaborador cliente = (Colaborador)sessao.getAttribute("colaborador");
        if ((movimento != null) && (cliente != null))
        {
            // Buscando produtos vinculados ao cliente
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
            cliente = colaboradorDAO.listaUm(cliente);
            List<ProdutoMovimento> lstProdutoMovimento = new ArrayList<ProdutoMovimento>();
            List<ColaboradorProduto> lstColaboradorProduto = cliente.getLstColaboradorProduto();
            Iterator it = lstColaboradorProduto.iterator();
            while (it.hasNext())
            {
                ColaboradorProduto _colaboradorProduto = (ColaboradorProduto)it.next();
                ProdutoMovimento _produtoMovimento = new ProdutoMovimento();
                _produtoMovimento.setProduto(_colaboradorProduto.getProduto());
                _produtoMovimento.setIcms(_colaboradorProduto.getIcms());
                _produtoMovimento.setValor(_colaboradorProduto.getValor());
                _produtoMovimento.setQuantidade(0);
                _produtoMovimento.setValorTotal(0);
                _produtoMovimento.setDesconto(0);
                lstProdutoMovimento.add(_produtoMovimento);
                
                
            }
            movimento.setColaborador(cliente);
            movimento.setLstProdutoMovimento(lstProdutoMovimento);
        }
        if (pagRetorno != null)
        {
            if (pagRetorno.equals("FabricaGelo.Venda.AcaoAbreVenda"))
            {
                sessao.setAttribute("venda", movimento);
            }
            else if (pagRetorno.equals("FabricaGelo.Compra.AcaoAbreCompra"))
            {
                sessao.setAttribute("compra", movimento);
            }
            else if (pagRetorno.equals("FabricaGelo.Cortesia.AcaoAbreCortesia"))
            {
                sessao.setAttribute("cortesia", movimento);
            }
            else if (pagRetorno.equals("FabricaGelo.Aluguel.AcaoAbreAluguel"))
            {
                sessao.setAttribute("aluguel", movimento);
            } 
            else if (pagRetorno.equals("FabricaGelo.Bonificacao.AcaoAbreBonificacao"))
            {
                sessao.setAttribute("bonificacao", movimento);
            }             
        }
        else
        {
            sessao.setAttribute("movimento", null);
            pagRetorno =  "FabricaGelo.Colaborador.AcaoListarColaborador";
        } 
//        if (pagRetorno != null)
//        {
//            sessao.setAttribute("venda", movimento);
//            sessao.setAttribute("lstProdutoVenda", movimento.getLstProdutoMovimento());
//        }
//        else
//        {        
//            sessao.setAttribute("venda", null);
//            sessao.setAttribute("lstProdutoVenda", null);
//            pagRetorno = "FabricaGelo.Colaborador.AcaoListarColaborador";
//        }
        return pagRetorno;
    }
}
