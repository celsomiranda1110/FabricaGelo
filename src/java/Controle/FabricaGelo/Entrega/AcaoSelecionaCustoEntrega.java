/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.CustoEntrega;
import Bean.Entrega;
import Bean.ProdutoEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.CustoEntregaDAO;
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
public class AcaoSelecionaCustoEntrega extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        CustoEntregaDAO custoEntregaDAO = new CustoEntregaDAO(conexao);
        CustoEntrega custoEntrega = new CustoEntrega();
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        entrega = entregaDAO.listaUm(entrega);
        
        String idCustoEntrega = req.getParameter("idCustoEntrega");
        custoEntrega.setIdCustoEntrega(Integer.parseInt(idCustoEntrega));
        custoEntrega = custoEntregaDAO.listaUm(custoEntrega);
        
        sessao.setAttribute("entrega",entrega);
        sessao.setAttribute("lstProdutoEntrega",entrega.getLstProdutoEntrega());
        sessao.setAttribute("lstCustoEntrega",entrega.getLstCustoEntrega());
        sessao.setAttribute("avariaEntrega",null);
        sessao.setAttribute("produtoEntrega",null);
        sessao.setAttribute("custoEntrega",custoEntrega);
        
        return "visao/entrega.jsp";
    }
}
