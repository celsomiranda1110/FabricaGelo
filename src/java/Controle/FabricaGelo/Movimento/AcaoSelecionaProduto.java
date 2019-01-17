/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Movimento;

import Bean.Movimento;
import Bean.ProdutoMovimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProdutoMovimentoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaProduto extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        Movimento movimento = (Movimento)sessao.getAttribute("movimento");
        if (movimento != null)
        {
            ProdutoMovimentoDAO movimentoProdutoDAO = new ProdutoMovimentoDAO(conexao);
            ProdutoMovimento movimentoProduto = movimentoProduto = new ProdutoMovimento();


            String idProdutoMovimento = req.getParameter("idProdutoMovimento");
            movimentoProduto.setIdProdutoMovimento(Integer.parseInt(idProdutoMovimento));
            movimentoProduto = movimentoProdutoDAO.listaUm(movimentoProduto);

            // Lista de funcionários da empresa
            sessao.setAttribute("movimento",movimento);
            sessao.setAttribute("lstProdutoMovimento",movimento.getLstProdutoMovimento());
            sessao.setAttribute("produtoMovimento",movimentoProduto);
        }   
        
        return "visao/movimento.jsp";        
    }
    
}