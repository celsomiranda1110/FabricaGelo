/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Bonificacao;

import Controle.FabricaGelo.Gerais.Acao;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoNovaBonificacao extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        sessao.setAttribute("bonificacao",null);
        sessao.setAttribute("bonificacaoProduto",null); 
        sessao.setAttribute("lstProdutoBonificacao",null);
        sessao.setAttribute("pagamento",null);
        sessao.setAttribute("parcela",null);
        
        return "visao/bonificacao.jsp";
    }
    
}
