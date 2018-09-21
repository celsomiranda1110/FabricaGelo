/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Insumo;

import Bean.Produto;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProdutoDAO;
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
public class AcaoListarInsumo extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Produto> lstInsumo = new ArrayList<Produto>();
        ProdutoDAO insumoDAO = new ProdutoDAO(conexao);
        Produto insumo = new Produto();
        insumo.setTipo("IN");
        
        lstInsumo = insumoDAO.listaTodos(insumo);
        sessao.setAttribute("lstInsumo",lstInsumo);
        
        return "visao/listarInsumo.jsp"; 
    }
}

