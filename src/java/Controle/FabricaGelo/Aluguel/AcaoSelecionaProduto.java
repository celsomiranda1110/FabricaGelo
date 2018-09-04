/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Aluguel;

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

        Movimento aluguel = (Movimento)sessao.getAttribute("aluguel");
        ProdutoMovimentoDAO aluguelProdutoDAO = new ProdutoMovimentoDAO(conexao);
        
        ProdutoMovimento aluguelProduto = (ProdutoMovimento)sessao.getAttribute("aluguelProduto");
        if (aluguelProduto == null)
            aluguelProduto = new ProdutoMovimento();
        
        String idProdutoMovimento = req.getParameter("idProdutoMovimento");
        aluguelProduto.setIdProdutoMovimento(Integer.parseInt(idProdutoMovimento));
        aluguelProduto = aluguelProdutoDAO.listaUm(aluguelProduto);
        
        // Lista de funcionários da empresa
        sessao.setAttribute("aluguel",aluguel);
        sessao.setAttribute("lstProdutoMovimento",aluguel.getLstProdutoMovimento());
        sessao.setAttribute("aluguelProduto",aluguelProduto);
            
        
        return "visao/aluguel.jsp";        
    }
    
}

