/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Movimento;

import Bean.Movimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MovimentoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoFechaMovimento extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        boolean fechar = true;
        String mensagemErro = "";
        String pagRetorno = "";   
        MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
        Movimento movimento = (Movimento)sessao.getAttribute("movimento");
        movimento = movimentoDAO.listaUm(movimento);
        
        if (movimento == null)
        {
            fechar = false;
            sessao.setAttribute("avisoErro", "Movimento inexistente");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Movimento.AcaoAbreMovimento");
            pagRetorno = "visao/erro.jsp";                 
            
        }        
        else if (movimento.getSituacao().equals("FE"))
        {
            fechar = false;
            sessao.setAttribute("avisoErro", "Movimento fechado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Movimento.AcaoAbreMovimento");
            pagRetorno = "visao/erro.jsp";                 
        }        
        else if (movimento.getSituacao().equals("CN"))
        {
            fechar = false;
            sessao.setAttribute("avisoErro", "Movimento cancelado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Movimento.AcaoAbreMovimento");
            pagRetorno = "visao/erro.jsp";                 
            
        } 
        
        if (fechar)
        {
            // FECHAMENTO DO MOVIMENTO
            movimento.setSituacao("FE");

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
