/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Compra;

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

        Movimento compra = (Movimento)sessao.getAttribute("compra");
        ProdutoMovimentoDAO compraProdutoDAO = new ProdutoMovimentoDAO(conexao);
        
        ProdutoMovimento compraProduto = (ProdutoMovimento)sessao.getAttribute("compraProduto");
        if (compraProduto == null)
            compraProduto = new ProdutoMovimento();
        
        String idProdutoMovimento = req.getParameter("idProdutoMovimento");
        compraProduto.setIdProdutoMovimento(Integer.parseInt(idProdutoMovimento));
        compraProduto = compraProdutoDAO.listaUm(compraProduto);
        
        // Lista de funcionários da empresa
        sessao.setAttribute("compra",compra);
        sessao.setAttribute("lstProdutoMovimento",compra.getLstProdutoMovimento());
        sessao.setAttribute("compraProduto",compraProduto);
            
        
        return "visao/compra.jsp";        
    }
    
}
