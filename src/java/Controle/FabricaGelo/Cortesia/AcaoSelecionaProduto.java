/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Cortesia;

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

        Movimento cortesia = (Movimento)sessao.getAttribute("cortesia");
        ProdutoMovimentoDAO cortesiaProdutoDAO = new ProdutoMovimentoDAO(conexao);
        
        ProdutoMovimento cortesiaProduto = (ProdutoMovimento)sessao.getAttribute("cortesiaProduto");
        if (cortesiaProduto == null)
            cortesiaProduto = new ProdutoMovimento();
        
        String idProdutoMovimento = req.getParameter("idProdutoMovimento");
        cortesiaProduto.setIdProdutoMovimento(Integer.parseInt(idProdutoMovimento));
        cortesiaProduto = cortesiaProdutoDAO.listaUm(cortesiaProduto);
        
        // Lista de funcionários da empresa
        sessao.setAttribute("cortesia",cortesia);
        sessao.setAttribute("lstProdutoMovimento",cortesia.getLstProdutoMovimento());
        sessao.setAttribute("cortesiaProduto",cortesiaProduto);
            
        
        return "visao/cortesia.jsp";        
    }
    
}
