/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;

import Bean.Bairro;
import Bean.Colaborador;
import Bean.ColaboradorProduto;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.BairroDAO;
import DAO.ColaboradorProdutoDAO;
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
public class AcaoDeletaColaboradorProduto extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        ColaboradorProdutoDAO colaboradorProdutoDAO = new ColaboradorProdutoDAO(conexao);
        
        ColaboradorProduto colaboradorProduto = (ColaboradorProduto)sessao.getAttribute("colaboradorProduto");
        if (colaboradorProduto == null)
            colaboradorProduto = new ColaboradorProduto();

        String idColaboradorProduto = req.getParameter("idColaboradorProduto");
        colaboradorProduto.setIdColaboradorProduto(Integer.parseInt(idColaboradorProduto));
        colaboradorProdutoDAO.delete(colaboradorProduto);
        
        // Lista de funcionários da empresa
        sessao.setAttribute("colaborador",colaborador);
        sessao.setAttribute("lstColaboradorProduto",null);
        sessao.setAttribute("colaboradorProduto",null);
           
        
        return "FabricaGelo.Colaborador.AcaoAbreColaborador";          
    }
}
