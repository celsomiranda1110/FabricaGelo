/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Venda;

import Bean.ColaboradorProduto;
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
import org.exolab.castor.types.Date;

/**
 *
 * @author celso
 */
public class AcaoGravaVenda extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String mensagemErro = "";
        String pagRetorno = "";
        
        Profissional usuario = (Profissional)sessao.getAttribute("usuario");
        
        MovimentoDAO vendaDAO = new MovimentoDAO(conexao);
        Movimento venda = (Movimento)sessao.getAttribute("venda");
        if (venda == null)
            venda = new Movimento();
            
        if (!venda.getSituacao().equals("FE"))
        {
            
            ProdutoMovimento produtoVenda = (ProdutoMovimento)sessao.getAttribute("vendaProduto");
            Pagamento pagamento = (Pagamento)sessao.getAttribute("pagamento");

            List<ProdutoMovimento> lstProdutos = new ArrayList<ProdutoMovimento>();
            if (venda.getLstProdutoMovimento() != null)
                lstProdutos = venda.getLstProdutoMovimento();


            // Dados do venda
            String numero = (req.getParameter("txtNumero").equals("") || req.getParameter("txtNumero") == null) ? "" : req.getParameter("txtNumero");
            String notaFiscal = (req.getParameter("txtNotaFiscal").equals("") || req.getParameter("txtNotaFiscal") == null) ? "" : req.getParameter("txtNotaFiscal");
            String dtLancamento = (req.getParameter("txtLancamento").equals("") || req.getParameter("txtLancamento") == null) ? "" : req.getParameter("txtLancamento");        
            String situacaoVenda = (req.getParameter("cmbSituacao").equals("") || req.getParameter("cmbSituacao") == null) ? "" : req.getParameter("cmbSituacao").toUpperCase();        

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
            
            if (produtoVenda != null)
            {
                produtoVenda.setQuantidade(Double.parseDouble(quantidade));
                produtoVenda.setValor(Double.parseDouble(vlUnitario));
                produtoVenda.setIcms(Double.parseDouble(vlIcms));
                produtoVenda.setDesconto(Double.parseDouble(vlDesconto));
                produtoVenda.setValorTotal(dbVlTotal);
                produtoVenda.setQtAvaria(dbQtAvaria); 
                produtoVenda.setOperacao("");
                Iterator itProduto = lstProdutos.iterator();
                while (itProduto.hasNext())
                {
                    ProdutoMovimento _produtoVenda = (ProdutoMovimento)itProduto.next();
                    if (_produtoVenda.getIdProduto() == produtoVenda.getIdProduto())
                    {
                        lstProdutos.remove(_produtoVenda);
                        break;
                    }
                } 
                lstProdutos.add(produtoVenda);
            }
            
            // criticando quantidade e setando operação de estoque
            List<ProdutoMovimento> lstProdutosAux = new ArrayList<ProdutoMovimento>();
            Iterator itProduto = lstProdutos.iterator();
            while (itProduto.hasNext())
            {
                
                ProdutoMovimento _produtoVenda = (ProdutoMovimento)itProduto.next();
                if (situacaoVenda.equals("FE"))
                    _produtoVenda.setOperacao("SU");
                else
                    _produtoVenda.setOperacao("");
                
                // verificando saldo dos produtos envolvidos
                Produto produto = (Produto)_produtoVenda.getProduto();
                if (produto.getSaldo() < _produtoVenda.getQuantidade())
                    mensagemErro += " " + produto + " com saldo insuficiente \n";
                
                // preenchendo list com produtoVenda atualizados
                lstProdutosAux.add(_produtoVenda);
            }    
            
            if (!mensagemErro.equals(""))
            {
                sessao.setAttribute("avisoErro", mensagemErro);
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Venda.AcaoAbreVenda");
                pagRetorno = "visao/erro.jsp";                 
            }
            else
            {
                // montagem do movimento de venda
                venda.setProfissional(usuario);
                venda.setNumero(numero);
                venda.setNotaFiscal(notaFiscal);
                venda.setDataLancamento(dtLancamento);
                venda.setDataEntrega(dtLancamento);
                venda.setSituacao(situacaoVenda);
                venda.setTipo("VE"); // indica venda
                venda.setLstProdutoMovimento(lstProdutosAux);

                if (vendaDAO.atualizar(venda))
                {
                    venda = vendaDAO.listaUm(venda);
                    sessao.setAttribute("venda", venda);
                    sessao.setAttribute("lstProdutoVenda",venda.getLstProdutoMovimento());
                    sessao.setAttribute("vendaProduto", null);
                    pagRetorno = "FabricaGelo.Venda.AcaoAbreVenda";
                } 
            }
        }
        else
        {
            sessao.setAttribute("avisoErro", "Movimento de venda Fechado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Venda.AcaoAbreVenda");
            pagRetorno = "visao/erro.jsp";            
        }
        
        return pagRetorno;
        
    }
    

}

