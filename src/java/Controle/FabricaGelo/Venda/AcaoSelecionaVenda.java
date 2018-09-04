/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Venda;

import Bean.Movimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MovimentoDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaVenda extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        MovimentoDAO vendaDAO = new MovimentoDAO(conexao);
        Movimento venda = new Movimento();
        
        String idMovimento = req.getParameter("idMovimento");
        venda.setIdMovimento(Integer.parseInt(idMovimento));
        
        venda = vendaDAO.listaUm(venda);
        
        sessao.setAttribute("venda",venda);
        sessao.setAttribute("lstProdutoVenda",venda.getLstProdutoMovimento());
        sessao.setAttribute("pagamento", venda.getPagamento());
        
        return "visao/venda.jsp";
    }
}
