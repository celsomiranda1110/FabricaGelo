/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Aluguel;

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
public class AcaoGravaAluguel extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String mensagemErro = "";
        String pagRetorno = "";
        
        Profissional usuario = (Profissional)sessao.getAttribute("usuario");
        
        MovimentoDAO aluguelDAO = new MovimentoDAO(conexao);
        Movimento aluguel = (Movimento)sessao.getAttribute("aluguel");
        if (aluguel == null)
            aluguel = new Movimento();
            
        if (!aluguel.getSituacao().equals("FE"))
        {
            
            ProdutoMovimento produtoAluguel = (ProdutoMovimento)sessao.getAttribute("aluguelProduto");
            Pagamento pagamento = (Pagamento)sessao.getAttribute("pagamento");

            List<ProdutoMovimento> lstProdutos = new ArrayList<ProdutoMovimento>();
            if (aluguel.getLstProdutoMovimento() != null)
                lstProdutos = aluguel.getLstProdutoMovimento();


            // Dados do aluguel
            String numero = (req.getParameter("txtNumero").equals("") || req.getParameter("txtNumero") == null) ? "" : req.getParameter("txtNumero");
            String dtDevolucao = (req.getParameter("txtDevolucao").equals("") || req.getParameter("txtDevolucao") == null) ? "" : req.getParameter("txtDevolucao");
            String dtLancamento = (req.getParameter("txtLancamento").equals("") || req.getParameter("txtLancamento") == null) ? "" : req.getParameter("txtLancamento");        
            String situacaoAluguel = (req.getParameter("cmbSituacao").equals("") || req.getParameter("cmbSituacao") == null) ? "" : req.getParameter("cmbSituacao").toUpperCase();        

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
            
            if (produtoAluguel != null)
            {
                produtoAluguel.setQuantidade(Double.parseDouble(quantidade));
                produtoAluguel.setValor(Double.parseDouble(vlUnitario));
                produtoAluguel.setIcms(Double.parseDouble(vlIcms));
                produtoAluguel.setDesconto(Double.parseDouble(vlDesconto));
                produtoAluguel.setValorTotal(dbVlTotal);
                produtoAluguel.setOperacao("");
                Iterator itProduto = lstProdutos.iterator();
                while (itProduto.hasNext())
                {
                    ProdutoMovimento _produtoAluguel = (ProdutoMovimento)itProduto.next();
                    if (_produtoAluguel.getIdProduto() == produtoAluguel.getIdProduto())
                    {
                        lstProdutos.remove(_produtoAluguel);
                        break;
                    }
                } 
                lstProdutos.add(produtoAluguel);
            }
            
            // criticando quantidade e setando operação de estoque
            List<ProdutoMovimento> lstProdutosAux = new ArrayList<ProdutoMovimento>();
            Iterator itProduto = lstProdutos.iterator();
            while (itProduto.hasNext())
            {
                
                ProdutoMovimento _produtoAluguel = (ProdutoMovimento)itProduto.next();
                if (situacaoAluguel.equals("FE"))
                    _produtoAluguel.setOperacao("AD");
                else
                    _produtoAluguel.setOperacao("");
                
//                // verificando saldo dos produtos envolvidos
//                Produto produto = (Produto)_produtoAluguel.getProduto();
//                if (produto.getSaldo() < _produtoAluguel.getQuantidade())
//                    mensagemErro += " " + produto + " com saldo insuficiente \n";
                
                // preenchendo list com produtoAluguel atualizados
                lstProdutosAux.add(_produtoAluguel);
            }    
            
            if (!mensagemErro.equals(""))
            {
                sessao.setAttribute("avisoErro", mensagemErro);
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Aluguel.AcaoAbreAluguel");
                pagRetorno = "visao/erro.jsp";                 
            }
            else
            {
                // montagem do movimento de aluguel
                aluguel.setNumero(numero);
                aluguel.setNotaFiscal(numero);
                aluguel.setDataLancamento(dtLancamento);
                aluguel.setDataEntrega(dtDevolucao);
                aluguel.setSituacao(situacaoAluguel);
                aluguel.setTipo("AL"); // indica aluguel
                aluguel.setLstProdutoMovimento(lstProdutosAux);

                if (aluguelDAO.atualizar(aluguel))
                {
                    aluguel = aluguelDAO.listaUm(aluguel);
                    sessao.setAttribute("aluguel", aluguel);
                    sessao.setAttribute("lstProdutoAluguel",aluguel.getLstProdutoMovimento());
                    sessao.setAttribute("aluguelProduto", null);
                    pagRetorno = "FabricaGelo.Aluguel.AcaoAbreAluguel";
                } 
            }
        }
        else
        {
            sessao.setAttribute("avisoErro", "Movimento de aluguel Fechado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Aluguel.AcaoAbreAluguel");
            pagRetorno = "visao/erro.jsp";            
        }
        
        return pagRetorno;
        
    }
    

}

