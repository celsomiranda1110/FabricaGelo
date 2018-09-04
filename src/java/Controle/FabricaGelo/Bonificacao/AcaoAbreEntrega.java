/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Bonificacao;

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
        
        Movimento bonificacao = (Movimento)sessao.getAttribute("bonificacao");

        if (bonificacao.getTipo().equals("BO"))
        {
            sessao.setAttribute("movimento",bonificacao);
            sessao.setAttribute("entrega",bonificacao.getEntrega());
            sessao.setAttribute("pagRetorno","FabricaGelo.Bonificacao.AcaoAbreBonificacao");
        }
        return "visao/entrega.jsp";
    }
}
