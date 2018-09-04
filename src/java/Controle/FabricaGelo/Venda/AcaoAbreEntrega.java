/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Venda;

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
        
        Movimento venda = (Movimento)sessao.getAttribute("venda");
        Entrega entrega = venda.getEntrega();

        if (venda.getTipo().equals("VE"))
        {
            sessao.setAttribute("movimento",venda);
            sessao.setAttribute("entrega",entrega);
            if (entrega != null)
                sessao.setAttribute("lstCustoEntrega",entrega.getLstCustoEntrega());
            else
                sessao.setAttribute("lstCustoEntrega",null);
            sessao.setAttribute("pagRetorno","FabricaGelo.Venda.AcaoAbreVenda");
        }
        return "visao/entrega.jsp";
    }
}
