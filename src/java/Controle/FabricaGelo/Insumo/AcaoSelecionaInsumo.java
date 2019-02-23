/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Insumo;

import Bean.Colaborador;
import Bean.Produto;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProdutoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaInsumo extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        ProdutoDAO insumoDAO = new ProdutoDAO(conexao);
        Produto insumo = new Produto();
        
        String idInsumo = req.getParameter("idInsumo");
        insumo.setIdProduto(Integer.parseInt(idInsumo));
        insumo = insumoDAO.listaUm(insumo);
        
        
        
        sessao.setAttribute("insumo",insumo);
        
        
        return "visao/insumo.jsp";
    }
    
}