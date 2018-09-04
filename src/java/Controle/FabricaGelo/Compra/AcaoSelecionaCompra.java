/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Compra;

import Bean.Movimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MovimentoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaCompra extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        MovimentoDAO compraDAO = new MovimentoDAO(conexao);
        Movimento compra = new Movimento();
        
        String idMovimento = req.getParameter("idMovimento");
        compra.setIdMovimento(Integer.parseInt(idMovimento));
        
        compra = compraDAO.listaUm(compra);
        
        sessao.setAttribute("compra",compra);
        sessao.setAttribute("lstProdutoCompra",compra.getLstProdutoMovimento());
        sessao.setAttribute("pagamento", compra.getPagamento());
        
        return "visao/compra.jsp";
    }
}
