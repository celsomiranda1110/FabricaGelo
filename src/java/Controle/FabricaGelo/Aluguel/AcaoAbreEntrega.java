/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Aluguel;

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
        
        Movimento aluguel = (Movimento)sessao.getAttribute("aluguel");

        if (aluguel.getTipo().equals("AL"))
        {
            sessao.setAttribute("movimento",aluguel);
            sessao.setAttribute("entrega",aluguel.getEntrega());
            sessao.setAttribute("pagRetorno","FabricaGelo.Aluguel.AcaoAbreAluguel");
        }
        return "visao/entrega.jsp";
    }
}