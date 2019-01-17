/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Bonificacao;

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
public class AcaoGravaBonificacao extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String mensagemErro = "";
        String pagRetorno = "";
        
        Profissional usuario = (Profissional)sessao.getAttribute("usuario");
        
        MovimentoDAO bonificacaoDAO = new MovimentoDAO(conexao);
        Movimento bonificacao = (Movimento)sessao.getAttribute("bonificacao");
        if (bonificacao == null)
            bonificacao = new Movimento();
            
        if (!bonificacao.getSituacao().equals("FE"))
        {
            
            ProdutoMovimento produtoBonificacao = (ProdutoMovimento)sessao.getAttribute("bonificacaoProduto");
            Pagamento pagamento = (Pagamento)sessao.getAttribute("pagamento");

            List<ProdutoMovimento> lstProdutos = new ArrayList<ProdutoMovimento>();
            if (bonificacao.getLstProdutoMovimento() != null)
                lstProdutos = bonificacao.getLstProdutoMovimento();


            // Dados do bonificacao
            String numero = (req.getParameter("txtNumero").equals("") || req.getParameter("txtNumero") == null) ? "" : req.getParameter("txtNumero");
            String notaFiscal = (req.getParameter("txtNotaFiscal").equals("") || req.getParameter("txtNotaFiscal") == null) ? "" : req.getParameter("txtNotaFiscal");
            String dtLancamento = (req.getParameter("txtLancamento").equals("") || req.getParameter("txtLancamento") == null) ? "" : req.getParameter("txtLancamento");        
            String situacaoBonificacao = (req.getParameter("cmbSituacao").equals("") || req.getParameter("cmbSituacao") == null) ? "" : req.getParameter("cmbSituacao").toUpperCase();        

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
            
            if (produtoBonificacao != null)
            {
                produtoBonificacao.setQuantidade(Double.parseDouble(quantidade));
                produtoBonificacao.setValor(Double.parseDouble(vlUnitario));
                produtoBonificacao.setIcms(Double.parseDouble(vlIcms));
                produtoBonificacao.setDesconto(Double.parseDouble(vlDesconto));
                produtoBonificacao.setValorTotal(dbVlTotal);
                produtoBonificacao.setQtAvaria(dbQtAvaria); 
                produtoBonificacao.setOperacao("");
                Iterator itProduto = lstProdutos.iterator();
                while (itProduto.hasNext())
                {
                    ProdutoMovimento _produtoBonificacao = (ProdutoMovimento)itProduto.next();
                    if (_produtoBonificacao.getIdProduto() == produtoBonificacao.getIdProduto())
                    {
                        lstProdutos.remove(_produtoBonificacao);
                        break;
                    }
                } 
                lstProdutos.add(produtoBonificacao);
            }
            
            // criticando quantidade e setando operação de estoque
            List<ProdutoMovimento> lstProdutosAux = new ArrayList<ProdutoMovimento>();
            Iterator itProduto = lstProdutos.iterator();
            while (itProduto.hasNext())
            {
                
                ProdutoMovimento _produtoBonificacao = (ProdutoMovimento)itProduto.next();
                if (situacaoBonificacao.equals("FE"))
                    _produtoBonificacao.setOperacao("AD");
                else
                    _produtoBonificacao.setOperacao("");
                
//                // verificando saldo dos produtos envolvidos
//                Produto produto = (Produto)_produtoBonificacao.getProduto();
//                if (produto.getSaldo() < _produtoBonificacao.getQuantidade())
//                    mensagemErro += " " + produto + " com saldo insuficiente \n";
                
                // preenchendo list com produtoBonificacao atualizados
                lstProdutosAux.add(_produtoBonificacao);
            }    
            
            if (!mensagemErro.equals(""))
            {
                sessao.setAttribute("avisoErro", mensagemErro);
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Bonificacao.AcaoAbreBonificacao");
                pagRetorno = "visao/erro.jsp";                 
            }
            else
            {
                // montagem do movimento de bonificacao

                bonificacao.setNumero(numero);
                bonificacao.setNotaFiscal(notaFiscal);
                bonificacao.setDataLancamento(dtLancamento);
                bonificacao.setDataEntrega(dtLancamento);
                bonificacao.setSituacao(situacaoBonificacao);
                bonificacao.setTipo("BO"); // indica bonificacao
                bonificacao.setLstProdutoMovimento(lstProdutosAux);

                if (bonificacaoDAO.atualizar(bonificacao))
                {
                    bonificacao = bonificacaoDAO.listaUm(bonificacao);
                    sessao.setAttribute("bonificacao", bonificacao);
                    sessao.setAttribute("lstProdutoBonificacao",bonificacao.getLstProdutoMovimento());
                    sessao.setAttribute("bonificacaoProduto", null);
                    pagRetorno = "FabricaGelo.Bonificacao.AcaoAbreBonificacao";
                } 
            }
        }
        else
        {
            sessao.setAttribute("avisoErro", "Movimento de bonificacao Fechado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Bonificacao.AcaoAbreBonificacao");
            pagRetorno = "visao/erro.jsp";            
        }
        
        return pagRetorno;
        
    }
    

}

