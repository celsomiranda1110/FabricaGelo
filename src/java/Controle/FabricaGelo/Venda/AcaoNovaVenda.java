/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Venda;

import Controle.FabricaGelo.Gerais.Acao;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoNovaVenda extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        sessao.setAttribute("venda",null);
        sessao.setAttribute("vendaProduto",null); 
        sessao.setAttribute("lstProdutoVenda",null);
        sessao.setAttribute("pagamento",null);
        sessao.setAttribute("parcela",null);
        
        return "visao/venda.jsp";
    }
    
}
