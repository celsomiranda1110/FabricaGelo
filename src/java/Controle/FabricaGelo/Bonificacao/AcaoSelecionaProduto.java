/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Bonificacao;

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

        Movimento bonificacao = (Movimento)sessao.getAttribute("bonificacao");
        ProdutoMovimentoDAO bonificacaoProdutoDAO = new ProdutoMovimentoDAO(conexao);
        
        ProdutoMovimento bonificacaoProduto = (ProdutoMovimento)sessao.getAttribute("bonificacaoProduto");
        if (bonificacaoProduto == null)
            bonificacaoProduto = new ProdutoMovimento();
        
        String idProdutoMovimento = req.getParameter("idProdutoMovimento");
        bonificacaoProduto.setIdProdutoMovimento(Integer.parseInt(idProdutoMovimento));
        bonificacaoProduto = bonificacaoProdutoDAO.listaUm(bonificacaoProduto);
        
        // Lista de funcionários da empresa
        sessao.setAttribute("bonificacao",bonificacao);
        sessao.setAttribute("lstProdutoMovimento",bonificacao.getLstProdutoMovimento());
        sessao.setAttribute("bonificacaoProduto",bonificacaoProduto);
            
        
        return "visao/bonificacao.jsp";        
    }
    
}

