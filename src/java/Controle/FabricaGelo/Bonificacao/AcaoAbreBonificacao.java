/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Bonificacao;

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
public class AcaoAbreBonificacao extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String formato = "yyyy-mm-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        Date date = new Date();
        String dtPesquisa = formatter.format(date);
        
        ProdutoMovimento bonificacaoProduto = (ProdutoMovimento)sessao.getAttribute("bonificacaoProduto");
        Movimento bonificacao = (Movimento)sessao.getAttribute("bonificacao");

        
        sessao.setAttribute("bonificacao",bonificacao);
        if (bonificacao == null)
        {
            sessao.setAttribute("lstProdutoBonificacao",null);
            
        }
        else
        {
            sessao.setAttribute("bonificacaoProduto",bonificacaoProduto);
            sessao.setAttribute("lstProdutoBonificacao",bonificacao.getLstProdutoMovimento());
            
        }
        
        return "visao/bonificacao.jsp";
    }
}

