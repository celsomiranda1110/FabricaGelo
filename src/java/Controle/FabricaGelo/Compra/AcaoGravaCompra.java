/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Compra;

import Bean.Movimento;
import Bean.Pagamento;
import Bean.Produto;
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
public class AcaoGravaCompra extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        Profissional usuario = (Profissional)sessao.getAttribute("usuario");    
        
        String mensagemErro = "";
        String pagRetorno = "";
        
        MovimentoDAO compraDAO = new MovimentoDAO(conexao);
        Movimento compra = (Movimento)sessao.getAttribute("compra");
        if (compra == null)
            compra = new Movimento();
            
        if (!compra.getSituacao().equals("FE"))
        {
            
            ProdutoMovimento produtoCompra = (ProdutoMovimento)sessao.getAttribute("compraProduto");
            Pagamento pagamento = (Pagamento)sessao.getAttribute("pagamento");

            List<ProdutoMovimento> lstProdutos = new ArrayList<ProdutoMovimento>();
            if (compra.getLstProdutoMovimento() != null)
                lstProdutos = compra.getLstProdutoMovimento();
            
            

            // Dados do compra
            String numero = (req.getParameter("txtNumero").equals("") || req.getParameter("txtNumero") == null) ? "" : req.getParameter("txtNumero");
            String notaFiscal = (req.getParameter("txtNotaFiscal").equals("") || req.getParameter("txtNotaFiscal") == null) ? "" : req.getParameter("txtNotaFiscal");
            String dtLancamento = (req.getParameter("txtLancamento").equals("") || req.getParameter("txtLancamento") == null) ? "" : req.getParameter("txtLancamento");        
            String situacaoCompra = (req.getParameter("cmbSituacao").equals("") || req.getParameter("cmbSituacao") == null) ? "" : req.getParameter("cmbSituacao").toUpperCase();        

            // Dados de produto
            String quantidade = (req.getParameter("txtQuantidade").equals("") || req.getParameter("txtQuantidade") == null) ? "0" : req.getParameter("txtQuantidade"); 
            String vlUnitario = (req.getParameter("txtVlUnitario").equals("") || req.getParameter("txtVlUnitario") == null) ? "0" : req.getParameter("txtVlUnitario");        
            String vlIcms = (req.getParameter("txtIcms").equals("") || req.getParameter("txtIcms") == null) ? "0" : req.getParameter("txtIcms");        
            String vlDesconto = (req.getParameter("txtVlDesconto").equals("") || req.getParameter("txtVlDesconto") == null) ? "0" : req.getParameter("txtVlDesconto");        
            
            double dbQuantidade = Double.parseDouble(quantidade);
            double dbVlUnitario = Double.parseDouble(vlUnitario);
            double dbIcms = Double.parseDouble(vlIcms);
            double dbDesconto = Double.parseDouble(vlDesconto);
            double dbVlTotal = 0;
            dbVlTotal = ((dbQuantidade * dbVlUnitario)  + ((dbQuantidade * dbVlUnitario) * (dbIcms/100)) - dbDesconto);
            
            if (produtoCompra != null)
            {
                produtoCompra.setQuantidade(Double.parseDouble(quantidade));
                produtoCompra.setValor(Double.parseDouble(vlUnitario));
                produtoCompra.setIcms(Double.parseDouble(vlIcms));
                produtoCompra.setDesconto(Double.parseDouble(vlDesconto));
                produtoCompra.setValorTotal(dbVlTotal);
                produtoCompra.setOperacao("");
                Iterator itProduto = lstProdutos.iterator();
                while (itProduto.hasNext())
                {
                    ProdutoMovimento _produtoCompra = (ProdutoMovimento)itProduto.next();
                    if (_produtoCompra.getIdProduto() == produtoCompra.getIdProduto())
                    {
                        lstProdutos.remove(_produtoCompra);
                        break;
                    }
                } 
                lstProdutos.add(produtoCompra);
            }
            
            // criticando quantidade e setando operação de estoque
            List<ProdutoMovimento> lstProdutosAux = new ArrayList<ProdutoMovimento>();
            Iterator itProduto = lstProdutos.iterator();
            while (itProduto.hasNext())
            {
                
                ProdutoMovimento _produtoCompra = (ProdutoMovimento)itProduto.next();
                if (situacaoCompra.equals("FE"))
                    _produtoCompra.setOperacao("AD");
                else
                    _produtoCompra.setOperacao("");
                
//                // verificando saldo dos produtos envolvidos
//                Produto produto = (Produto)_produtoCompra.getProduto();
//                if (produto.getSaldo() < _produtoCompra.getQuantidade())
//                    mensagemErro += " " + produto + " com saldo insuficiente \n";
                
                // preenchendo list com produtoCompra atualizados
                lstProdutosAux.add(_produtoCompra);
            }    
            
            if (!mensagemErro.equals(""))
            {
                sessao.setAttribute("avisoErro", mensagemErro);
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Compra.AcaoAbreCompra");
                pagRetorno = "visao/erro.jsp";                 
            }
            else
            {
                // montagem do movimento de compra
                compra.setNumero(numero);
                compra.setNotaFiscal(notaFiscal);
                compra.setDataLancamento(dtLancamento);
                compra.setDataEntrega(dtLancamento);
                compra.setSituacao(situacaoCompra);
                compra.setTipo("CP"); // indica compra
                compra.setLstProdutoMovimento(lstProdutosAux);

                if (compraDAO.atualizar(compra))
                {
                    compra = compraDAO.listaUm(compra);
                    sessao.setAttribute("compra", compra);
                    sessao.setAttribute("lstProdutoCompra",compra.getLstProdutoMovimento());
                    sessao.setAttribute("compraProduto", null);
                    pagRetorno = "FabricaGelo.Compra.AcaoAbreCompra";
                } 
            }
        }
        else
        {
            sessao.setAttribute("avisoErro", "Movimento de compra Fechado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Compra.AcaoAbreCompra");
            pagRetorno = "visao/erro.jsp";            
        }
        
        return pagRetorno;
        
    }
    

}

