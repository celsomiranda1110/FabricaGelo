/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;

import Bean.Bairro;
import Bean.Colaborador;
import Bean.ColaboradorProduto;
import Bean.Produto;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.BairroDAO;
import DAO.ColaboradorDAO;
import DAO.ColaboradorProdutoDAO;
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
public class AcaoSelecionaColaboradorProduto extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        ColaboradorProdutoDAO colaboradorProdutoDAO = new ColaboradorProdutoDAO(conexao);
        
        ColaboradorProduto colaboradorProduto = (ColaboradorProduto)sessao.getAttribute("colaboradorProduto");
        if (colaboradorProduto == null)
            colaboradorProduto = new ColaboradorProduto();
        
        BairroDAO bairroDAO = new BairroDAO(conexao);
        List<Bairro> lstBairro = new ArrayList<Bairro>();
        lstBairro = bairroDAO.listaTodos();
        
        String idColaboradorProduto = req.getParameter("idColaboradorProduto");
        colaboradorProduto.setIdColaboradorProduto(Integer.parseInt(idColaboradorProduto));
        colaboradorProduto = colaboradorProdutoDAO.listaUm(colaboradorProduto);
        
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        Produto produto = new Produto();
        List<Produto> lstProduto = new ArrayList<Produto>();
        produto.setTipo("PR");
        lstProduto = produtoDAO.listaTodos(produto);        
        
        // Lista de funcionários da empresa
        
        sessao.setAttribute("colaborador",colaborador);
        sessao.setAttribute("lstProduto", lstProduto);
        sessao.setAttribute("lstColaboradorProduto",colaborador.getLstColaboradorProduto());
        sessao.setAttribute("colaboradorProduto",colaboradorProduto);
        sessao.setAttribute("lstBairro", lstBairro);        
        
        return "visao/colaborador.jsp";        
    }
    
}
