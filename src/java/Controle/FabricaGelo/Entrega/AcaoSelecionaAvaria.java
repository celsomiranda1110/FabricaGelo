/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Entrega;
import Bean.Avaria;
import Bean.AvariaEntrega;
import Bean.ProdutoEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import DAO.AvariaDAO;
import DAO.AvariaEntregaDAO;
import DAO.ProdutoEntregaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaAvaria extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        

        
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        entrega = entregaDAO.listaUm(entrega);
        
        ProdutoEntregaDAO produtoEntregaDAO = new ProdutoEntregaDAO(conexao);
        ProdutoEntrega produtoEntrega = (ProdutoEntrega)sessao.getAttribute("produtoEntrega");
        produtoEntrega = produtoEntregaDAO.listaUm(produtoEntrega);
        
        AvariaEntregaDAO avariaEntregaDAO = new AvariaEntregaDAO(conexao);
        AvariaEntrega avariaEntrega = new AvariaEntrega();        
        String idAvariaEntrega = req.getParameter("idAvariaEntrega");
        avariaEntrega.setIdAvariaEntrega(Integer.parseInt(idAvariaEntrega));
        avariaEntrega = avariaEntregaDAO.listaUm(avariaEntrega);
        
        sessao.setAttribute("entrega",entrega);
        sessao.setAttribute("lstAvaria",produtoEntrega.getLstAvariaEntrega());
        sessao.setAttribute("lstCustoEntrega",entrega.getLstCustoEntrega());
        sessao.setAttribute("avariaEntrega",avariaEntrega);
        sessao.setAttribute("produtoEntrega",produtoEntrega);
        sessao.setAttribute("custoEntrega",null);
        
        return "visao/entrega.jsp";
    }
}
