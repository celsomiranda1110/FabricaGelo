/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Compra;

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
public class AcaoAbrePagamento extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        Movimento compra = (Movimento)sessao.getAttribute("compra");
        
        
        
        if (compra.getTipo().equals("CP"))
        {
            sessao.setAttribute("movimento",compra);
            sessao.setAttribute("pagamento",compra.getPagamento());
            sessao.setAttribute("lstParcela",compra.getPagamento().getLstParcela());
            sessao.setAttribute("parcela",null);
            sessao.setAttribute("pagRetorno","FabricaGelo.Compra.AcaoAbreCompra");
        }
        return "visao/pagamento.jsp";
    }
}
