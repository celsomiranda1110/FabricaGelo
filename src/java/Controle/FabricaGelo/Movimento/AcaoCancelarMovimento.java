/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Movimento;

import Bean.Movimento;
import Bean.Pagamento;
import Bean.Parcela;
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
public class AcaoCancelarMovimento extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        boolean cancelar = true;
        String mensagemErro = "";
        String pagRetorno = "";   
        MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
        Movimento movimento = (Movimento)sessao.getAttribute("movimento");
        movimento = movimentoDAO.listaUm(movimento);
        
        if (movimento == null)
        {
            cancelar = false;
            sessao.setAttribute("avisoErro", "Movimento inexistente");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Movimento.AcaoAbreMovimento");
            pagRetorno = "visao/erro.jsp";                 
        }   
        else if (movimento.getSituacao().equals("CN"))
        {
            cancelar = false;
            sessao.setAttribute("avisoErro", "Movimento cancelado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Movimento.AcaoAbreMovimento");
            pagRetorno = "visao/erro.jsp";                 
        } 
        
        if (cancelar)
        {
            // preparando para desfazer movimento fechado
            List<ProdutoMovimento> lstProdutoMovimentoN = new ArrayList<ProdutoMovimento>();
            List<ProdutoMovimento> lstProdutoMovimento = movimento.getLstProdutoMovimento();
            Iterator itProdutoMovimento = lstProdutoMovimento.iterator();
            while (itProdutoMovimento.hasNext())
            {
                ProdutoMovimento produtoMovimento = (ProdutoMovimento)itProdutoMovimento.next();
                produtoMovimento.setQuantidade((-1) * produtoMovimento.getQuantidade());
                lstProdutoMovimentoN.add(produtoMovimento);
            }
            movimento.setLstProdutoMovimento(lstProdutoMovimentoN);
            
            // preparando cancelamento de pagamentos
            Pagamento pagamento = (Pagamento)movimento.getPagamento();
            List<Parcela> lstParcelaN = pagamento.getLstParcela();
            List<Parcela> lstParcela = pagamento.getLstParcela();
            Iterator itParcela = lstParcela.iterator();
            while (itParcela.hasNext())
            {
                Parcela parcela = (Parcela)itParcela.next();
                if (!parcela.getSituacao().equals("G"))
                    parcela.setSituacao("C");
                lstParcelaN.add(parcela);
            }
            pagamento.setLstParcela(lstParcelaN);
            pagamento.setSituacao("PC");
            movimento.setPagamento(pagamento);        
        
            // CANCELAMENTO DE MOVIMENTO
            movimento.setSituacao("CN");

            if (movimentoDAO.atualizar(movimento))
            {
                movimento.setIdMovimento(movimentoDAO.getIdentity());
                movimento = movimentoDAO.listaUm(movimento);
                sessao.setAttribute("movimento", movimento);
                sessao.setAttribute("lstProdutoMovimento",movimento.getLstProdutoMovimento());
                sessao.setAttribute("produtoMovimento", null);                
            }
        }
        
        return pagRetorno;
        
    }
}
