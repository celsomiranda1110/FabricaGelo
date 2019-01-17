/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Cortesia;

import Bean.Movimento;
import Bean.Pagamento;
import Bean.ProdutoMovimento;
import Bean.Profissional;
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
public class AcaoGravaCortesia extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String mensagemErro = "";
        String pagRetorno = "";
        
        Profissional usuario = (Profissional)sessao.getAttribute("usuario");
        
        MovimentoDAO cortesiaDAO = new MovimentoDAO(conexao);
        Movimento cortesia = (Movimento)sessao.getAttribute("cortesia");
        if (cortesia == null)
            cortesia = new Movimento();
            
        if (!cortesia.getSituacao().equals("FE"))
        {
            
            ProdutoMovimento produtoCortesia = (ProdutoMovimento)sessao.getAttribute("cortesiaProduto");
            Pagamento pagamento = (Pagamento)sessao.getAttribute("pagamento");

            List<ProdutoMovimento> lstProdutos = new ArrayList<ProdutoMovimento>();
            if (cortesia.getLstProdutoMovimento() != null)
                lstProdutos = cortesia.getLstProdutoMovimento();


            // Dados do cortesia
            String numero = (req.getParameter("txtNumero").equals("") || req.getParameter("txtNumero") == null) ? "" : req.getParameter("txtNumero");
            String notaFiscal = (req.getParameter("txtNotaFiscal").equals("") || req.getParameter("txtNotaFiscal") == null) ? "" : req.getParameter("txtNotaFiscal");
            String dtLancamento = (req.getParameter("txtLancamento").equals("") || req.getParameter("txtLancamento") == null) ? "" : req.getParameter("txtLancamento");        
            String situacaoCortesia = (req.getParameter("cmbSituacao").equals("") || req.getParameter("cmbSituacao") == null) ? "" : req.getParameter("cmbSituacao").toUpperCase();        

            // Dados de produto
            String quantidade = (req.getParameter("txtQuantidade").equals("") || req.getParameter("txtQuantidade") == null) ? "0" : req.getParameter("txtQuantidade"); 
            String vlUnitario = (req.getParameter("txtVlUnitario").equals("") || req.getParameter("txtVlUnitario") == null) ? "0" : req.getParameter("txtVlUnitario");        
            String vlIcms = (req.getParameter("txtIcms").equals("") || req.getParameter("txtIcms") == null) ? "0" : req.getParameter("txtIcms");        
            String vlDesconto = (req.getParameter("txtVlDesconto").equals("") || req.getParameter("txtVlDesconto") == null) ? "0" : req.getParameter("txtVlDesconto");        
            String qtAvaria = (req.getParameter("txtQtAvaria").equals("") || req.getParameter("txtQtAvaria") == null) ? "0" : req.getParameter("txtQtAvaria");        
            double dbQuantidade = Double.parseDouble(quantidade);
            double dbVlUnitario = Double.parseDouble(vlUnitario);
            double dbIcms = Double.parseDouble(vlIcms);
            double dbDesconto = Double.parseDouble(vlDesconto);
            double dbVlTotal = 0;
            double dbQtAvaria = Double.parseDouble(qtAvaria);
            dbVlTotal = ((dbQuantidade * dbVlUnitario)  + ((dbQuantidade * dbVlUnitario) * (dbIcms/100)) - dbDesconto);
            
            if (produtoCortesia != null)
            {
                produtoCortesia.setQuantidade(Double.parseDouble(quantidade));
                produtoCortesia.setValor(Double.parseDouble(vlUnitario));
                produtoCortesia.setIcms(Double.parseDouble(vlIcms));
                produtoCortesia.setDesconto(Double.parseDouble(vlDesconto));
                produtoCortesia.setValorTotal(dbVlTotal);
                produtoCortesia.setQtAvaria(dbQtAvaria); 
                produtoCortesia.setOperacao("");
                Iterator itProduto = lstProdutos.iterator();
                while (itProduto.hasNext())
                {
                    ProdutoMovimento _produtoCortesia = (ProdutoMovimento)itProduto.next();
                    if (_produtoCortesia.getIdProduto() == produtoCortesia.getIdProduto())
                    {
                        lstProdutos.remove(_produtoCortesia);
                        break;
                    }
                } 
                lstProdutos.add(produtoCortesia);
            }
            
            // criticando quantidade e setando operação de estoque
            List<ProdutoMovimento> lstProdutosAux = new ArrayList<ProdutoMovimento>();
            Iterator itProduto = lstProdutos.iterator();
            while (itProduto.hasNext())
            {
                
                ProdutoMovimento _produtoCortesia = (ProdutoMovimento)itProduto.next();
                if (situacaoCortesia.equals("FE"))
                    _produtoCortesia.setOperacao("AD");
                else
                    _produtoCortesia.setOperacao("");
                
//                // verificando saldo dos produtos envolvidos
//                Produto produto = (Produto)_produtoCortesia.getProduto();
//                if (produto.getSaldo() < _produtoCortesia.getQuantidade())
//                    mensagemErro += " " + produto + " com saldo insuficiente \n";
                
                // preenchendo list com produtoCortesia atualizados
                lstProdutosAux.add(_produtoCortesia);
            }    
            
            if (!mensagemErro.equals(""))
            {
                sessao.setAttribute("avisoErro", mensagemErro);
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Cortesia.AcaoAbreCortesia");
                pagRetorno = "visao/erro.jsp";                 
            }
            else
            {
                // montagem do movimento de cortesia
                
                cortesia.setNumero(numero);
                cortesia.setNotaFiscal(notaFiscal);
                cortesia.setDataLancamento(dtLancamento);
                cortesia.setDataEntrega(dtLancamento);
                cortesia.setSituacao(situacaoCortesia);
                cortesia.setTipo("CO"); // indica cortesia
                cortesia.setLstProdutoMovimento(lstProdutosAux);

                if (cortesiaDAO.atualizar(cortesia))
                {
                    cortesia = cortesiaDAO.listaUm(cortesia);
                    sessao.setAttribute("cortesia", cortesia);
                    sessao.setAttribute("lstProdutoCortesia",cortesia.getLstProdutoMovimento());
                    sessao.setAttribute("cortesiaProduto", null);
                    pagRetorno = "FabricaGelo.Cortesia.AcaoAbreCortesia";
                } 
            }
        }
        else
        {
            sessao.setAttribute("avisoErro", "Movimento de cortesia Fechado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Cortesia.AcaoAbreCortesia");
            pagRetorno = "visao/erro.jsp";            
        }
        
        return pagRetorno;
        
    }
    

}

