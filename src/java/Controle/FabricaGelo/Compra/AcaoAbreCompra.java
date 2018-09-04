/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Compra;

import Bean.Movimento;
import Bean.ProdutoMovimento;
import Controle.FabricaGelo.Gerais.Acao;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoAbreCompra extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String formato = "yyyy-mm-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        Date date = new Date();
        String dtPesquisa = formatter.format(date);
        
        ProdutoMovimento compraProduto = (ProdutoMovimento)sessao.getAttribute("compraProduto");
        Movimento compra = (Movimento)sessao.getAttribute("compra");

        
        sessao.setAttribute("compra",compra);
        if (compra == null)
        {
            sessao.setAttribute("lstProdutoCompra",null);
            
        }
        else
        {
            sessao.setAttribute("compraProduto",compraProduto);
            sessao.setAttribute("lstProdutoCompra",compra.getLstProdutoMovimento());
            
        }
        
        return "visao/compra.jsp";
    }
}
