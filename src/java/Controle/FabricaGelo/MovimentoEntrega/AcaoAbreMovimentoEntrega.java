/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.MovimentoEntrega;

import Bean.ColaboradorEntrega;
import Bean.Entrega;
import Bean.Movimento;
import Bean.MovimentoEntrega;
import Bean.ProdutoMovimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoAbreMovimentoEntrega extends Acao{ 
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/movimentoEntrega.jsp";
        
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        ColaboradorEntrega colaboradorEntrega = (ColaboradorEntrega)sessao.getAttribute("colaboradorEntrega");        
        MovimentoEntrega movimentoEntrega = (MovimentoEntrega)sessao.getAttribute("movimentoEntrega");
        Movimento movimento = (Movimento)movimentoEntrega.getMovimento();
        ProdutoMovimento produtoMovimento = (ProdutoMovimento)sessao.getAttribute("produtoMovimento");
        
        if (entrega == null)
        {
            sessao.setAttribute("avisoErro", "Entrega não selecionada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";              
        }
        else if (colaboradorEntrega == null)
        {
            sessao.setAttribute("avisoErro", "Cliente não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else if (movimento == null)
        {
            sessao.setAttribute("avisoErro", "Movimento não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            sessao.setAttribute("entrega", entrega);
            sessao.setAttribute("colaboradorEntrega", colaboradorEntrega);
            sessao.setAttribute("movimentoEntrega", movimentoEntrega);
            sessao.setAttribute("movimento", movimento);
            sessao.setAttribute("lstProdutoMovimento", movimento.getLstProdutoMovimento()); 
                       
        }
        
        return pagRetorno;
        
    }
}
