/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Entrega;
import Bean.Movimento;
import Controle.FabricaGelo.Gerais.Acao;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoAbreEntrega extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        Movimento movimento = (Movimento)sessao.getAttribute("movimento");
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
//        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
//        if (entrega == null)
//            entrega = movimento.getEntrega();
        
        sessao.setAttribute("entrega", entrega);
        
        return "visao/entrega.jsp";
    }
}
