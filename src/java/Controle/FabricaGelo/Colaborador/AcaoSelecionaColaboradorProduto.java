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
import Bean.TipoColaborador;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.BairroDAO;
import DAO.ColaboradorDAO;
import DAO.ColaboradorProdutoDAO;
import DAO.ProdutoDAO;
import DAO.TipoColaboradorDAO;
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
        
        String pagRetorno = "FabricaGelo.Colaborador.AcaoAbreColaborador";

//        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        ColaboradorProdutoDAO colaboradorProdutoDAO = new ColaboradorProdutoDAO(conexao);
//        
        ColaboradorProduto colaboradorProduto = colaboradorProduto = new ColaboradorProduto();
        String idColaboradorProduto = req.getParameter("idColaboradorProduto");
        colaboradorProduto.setIdColaboradorProduto(Integer.parseInt(idColaboradorProduto));
        colaboradorProduto = colaboradorProdutoDAO.listaUm(colaboradorProduto);
        
//        // Tipo colaborador
//        TipoColaboradorDAO tipoColaboradorDAO = new TipoColaboradorDAO(conexao);
//        List<TipoColaborador> lstTipoColaborador = new ArrayList<TipoColaborador>();
//        lstTipoColaborador = tipoColaboradorDAO.listaTodos();
//        
//        // lstando bairros
//        BairroDAO bairroDAO = new BairroDAO(conexao);
//        List<Bairro> lstBairro = new ArrayList<Bairro>();
//        lstBairro = bairroDAO.listaTodos();
//        
//        
//        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
//        Produto produto = new Produto();
//        List<Produto> lstProduto = new ArrayList<Produto>();
//        produto.setTipo("PR");
//        lstProduto = produtoDAO.listaTodos(produto); 
//        
//        // Lista de funcionários da empresa
//        sessao.setAttribute("colaborador",colaborador);
        sessao.setAttribute("colaboradorProduto",colaboradorProduto);
//        sessao.setAttribute("lstProduto", lstProduto);
//        sessao.setAttribute("lstTipoColaborador", lstTipoColaborador);
//        sessao.setAttribute("lstBairro",lstBairro);        
//        sessao.setAttribute("lstColaboradorProduto",(colaborador == null ? null : colaborador.getLstColaboradorProduto()));
//        sessao.setAttribute("lstVisitaColaborador",(colaborador == null ? null : colaborador.getLstVisitaColaborador()));

        
        return pagRetorno;        
    }
    
}
