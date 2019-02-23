/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;

import Bean.Colaborador;

import Bean.ColaboradorProduto;
import Bean.Produto;


import DAO.FuncaoDAO;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.BairroDAO;
import Bean.Bairro;
import Bean.TipoColaborador;
import Bean.VisitaColaborador;
import DAO.ColaboradorDAO;
import DAO.ProdutoDAO;
import DAO.TipoColaboradorDAO;
import java.sql.Connection;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author MIRANDA
 */
public class AcaoAbreColaborador extends Acao
{
    
public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        
        String pagRetorno = "visao/colaborador.jsp";
        
        List<VisitaColaborador> lstVisitaColaborador = new ArrayList<VisitaColaborador>();
        
//        ColaboradorProduto colaboradorProduto = (ColaboradorProduto)sessao.getAttribute("colaboradorProduto");
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        if (colaborador != null)
        {
            colaborador = colaboradorDAO.listaUm(colaborador);
            lstVisitaColaborador = colaborador.getLstVisitaColaborador();
        }
        
        
        // Tipo colaborador
        TipoColaboradorDAO tipoColaboradorDAO = new TipoColaboradorDAO(conexao);
        List<TipoColaborador> lstTipoColaborador = new ArrayList<TipoColaborador>();
        lstTipoColaborador = tipoColaboradorDAO.listaTodos(new TipoColaborador());
        
        // lstando bairros
        BairroDAO bairroDAO = new BairroDAO(conexao);
        List<Bairro> lstBairro = new ArrayList<Bairro>();
        lstBairro = bairroDAO.listaTodos(new Bairro());
        
        
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        Produto produto = new Produto();
        List<Produto> lstProduto = new ArrayList<Produto>();
        produto.setTipo("PR");
        lstProduto = produtoDAO.listaTodos(produto);        
        
        sessao.setAttribute("colaborador",colaborador);
//        sessao.setAttribute("colaboradorProduto",colaboradorProduto);
        sessao.setAttribute("lstProduto", lstProduto);
        sessao.setAttribute("lstTipoColaborador", lstTipoColaborador);
        sessao.setAttribute("lstBairro",lstBairro);        
        sessao.setAttribute("lstColaboradorProduto",(colaborador == null ? null : colaborador.getLstColaboradorProduto()));
        sessao.setAttribute("lstVisitaColaborador",lstVisitaColaborador);

        return pagRetorno;
        
    }    
}
