/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Cortesia;

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
public class AcaoAbreCortesia extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String formato = "yyyy-mm-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        Date date = new Date();
        String dtPesquisa = formatter.format(date);
        
        ProdutoMovimento cortesiaProduto = (ProdutoMovimento)sessao.getAttribute("cortesiaProduto");
        Movimento cortesia = (Movimento)sessao.getAttribute("cortesia");

        
        sessao.setAttribute("cortesia",cortesia);
        if (cortesia == null)
        {
            sessao.setAttribute("lstProdutoCortesia",null);
            
        }
        else
        {
            sessao.setAttribute("cortesiaProduto",cortesiaProduto);
            sessao.setAttribute("lstProdutoCortesia",cortesia.getLstProdutoMovimento());
            
        }
        
        return "visao/cortesia.jsp";
    }
}
