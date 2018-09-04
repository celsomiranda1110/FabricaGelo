/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Aluguel;

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
public class AcaoAbreAluguel extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String formato = "yyyy-mm-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        Date date = new Date();
        String dtPesquisa = formatter.format(date);
        
        ProdutoMovimento aluguelProduto = (ProdutoMovimento)sessao.getAttribute("aluguelProduto");
        Movimento aluguel = (Movimento)sessao.getAttribute("aluguel");

        
        sessao.setAttribute("aluguel",aluguel);
        if (aluguel == null)
        {
            sessao.setAttribute("lstProdutoAluguel",null);
            
        }
        else
        {
            sessao.setAttribute("aluguelProduto",aluguelProduto);
            sessao.setAttribute("lstProdutoAluguel",aluguel.getLstProdutoMovimento());
            
        }
        
        return "visao/aluguel.jsp";
    }
}
