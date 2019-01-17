/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.AvariaEntrega;
import Bean.Entrega;
import Bean.ProdutoEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaEntregaDAO;
import DAO.EntregaDAO;
import DAO.ProdutoEntregaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoExcluirAvaria extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        AvariaEntregaDAO avariaEntregaDAO = new AvariaEntregaDAO(conexao);
        AvariaEntrega avariaEntrega = new AvariaEntrega();
        String idAvariaEntrega = req.getParameter("idAvariaEntrega");
        avariaEntrega.setIdAvariaEntrega(Integer.parseInt(idAvariaEntrega));
        avariaEntregaDAO.delete(avariaEntrega);
        
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        entrega = entregaDAO.listaUm(entrega);  
        
        ProdutoEntregaDAO produtoEntregaDAO = new ProdutoEntregaDAO(conexao);
        ProdutoEntrega produtoEntrega = (ProdutoEntrega)sessao.getAttribute("produtoEntrega");
        produtoEntrega = produtoEntregaDAO.listaUm(produtoEntrega);        
        
        sessao.setAttribute("entrega",entrega);
        sessao.setAttribute("lstProdutoEntrega",entrega.getLstProdutoEntrega());
        sessao.setAttribute("lstCustoEntrega",entrega.getLstCustoEntrega());
        sessao.setAttribute("lstAbastecimento",entrega.getLstAbastecimento());
        sessao.setAttribute("custoEntrega",null);
        sessao.setAttribute("avariaEntrega",null);
        sessao.setAttribute("produtoEntrega",produtoEntrega);
        sessao.setAttribute("lstAvariaEntrega",produtoEntrega.getLstAvariaEntrega());
        sessao.setAttribute("abastecimento",null);

        return "visao/entrega.jsp";        
    }
}
