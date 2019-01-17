/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;


import Bean.Bairro;
import Bean.Colaborador;
import Bean.Movimento;
import Bean.Produto;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.BairroDAO;
import DAO.ColaboradorDAO;
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
public class AcaoSelecionaColaborador extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        // Movimento chama colaborador para pesquisa
        Movimento venda = (Movimento)sessao.getAttribute("venda");
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        if (colaborador == null)
            colaborador = new Colaborador();
        

        String idColaborador = req.getParameter("idColaborador");
        colaborador.setIdColaborador(Integer.parseInt(idColaborador));
        colaborador = colaboradorDAO.listaUm(colaborador);
        
        // lstando bairros
        BairroDAO bairroDAO = new BairroDAO(conexao);
        List<Bairro> lstBairro = new ArrayList<Bairro>();
        lstBairro = bairroDAO.listaTodos();
        sessao.setAttribute("lstBairro",lstBairro);
        
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        Produto produto = new Produto();
        List<Produto> lstProduto = new ArrayList<Produto>();
        produto.setTipo("PR");
        lstProduto = produtoDAO.listaTodos(produto);        
        
        // Lista de funcionários da empresa
        sessao.setAttribute("venda",venda);
        sessao.setAttribute("pagRetorno",pagRetorno);
        
        sessao.setAttribute("colaborador",colaborador);
        sessao.setAttribute("lstProduto", lstProduto);
        sessao.setAttribute("lstColaboradorProduto",colaborador.getLstColaboradorProduto());
        sessao.setAttribute("lstVisitaColaborador",colaborador.getLstVisitaColaborador());
              
        
        return "visao/colaborador.jsp";
    }
    
}
