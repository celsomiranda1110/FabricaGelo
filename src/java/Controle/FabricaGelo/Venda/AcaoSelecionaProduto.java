/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Venda;

import Bean.Movimento;
import Bean.ProdutoMovimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProdutoMovimentoDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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

        Movimento venda = (Movimento)sessao.getAttribute("venda");
        ProdutoMovimentoDAO vendaProdutoDAO = new ProdutoMovimentoDAO(conexao);
        
        ProdutoMovimento vendaProduto = (ProdutoMovimento)sessao.getAttribute("vendaProduto");
        if (vendaProduto == null)
            vendaProduto = new ProdutoMovimento();
        
        String idProdutoMovimento = req.getParameter("idProdutoMovimento");
        vendaProduto.setIdProdutoMovimento(Integer.parseInt(idProdutoMovimento));
        vendaProduto = vendaProdutoDAO.listaUm(vendaProduto);
        
        // Lista de funcionários da empresa
        sessao.setAttribute("venda",venda);
        sessao.setAttribute("lstProdutoMovimento",venda.getLstProdutoMovimento());
        sessao.setAttribute("vendaProduto",vendaProduto);
            
        
        return "visao/venda.jsp";        
    }
    
}