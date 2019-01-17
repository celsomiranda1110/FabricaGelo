/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Avaria;
import Bean.AvariaProducao;
import Bean.Equipamento;
import Bean.Producao;
import Bean.Produto;
import Bean.ProdutoCamara;
import Bean.TransferenciaProducao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaDAO;
import DAO.EquipamentoDAO;
import DAO.ProducaoDAO;
import DAO.ProdutoCamaraDAO;
import DAO.ProdutoDAO;
import DAO.TransferenciaProducaoDAO;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaProducao extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        
        ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
        Producao producao = new Producao();
        
        String idProducao = req.getParameter("idProducao");
        producao.setIdProducao(Integer.parseInt(idProducao));
        producao = producaoDAO.listaUm(producao);

        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        Produto produto = new Produto();
        List<Produto> lstProduto = new ArrayList<Produto>();
        produto.setTipo("PR");
        lstProduto = produtoDAO.listaTodos(produto);  
        
        ProdutoDAO embalagemDAO = new ProdutoDAO(conexao);
        Produto embalagem = new Produto();
        List<Produto> lstEmbalagem = new ArrayList<Produto>();
        embalagem.setTipo("IN");
        lstEmbalagem = embalagemDAO.listaTodos(embalagem);           
        
        EquipamentoDAO maquinaDAO = new EquipamentoDAO(conexao);
        Equipamento maquina = new Equipamento();
        List<Equipamento> lstMaquina = new ArrayList<Equipamento>();
        maquina.setTipo("MA");
        lstMaquina = maquinaDAO.listaTodos(maquina);
        
        AvariaDAO avariaDAO = new AvariaDAO(conexao);
        List<Avaria> lstAvaria = new ArrayList<Avaria>();
        lstAvaria = avariaDAO.listaTodos();
        
        ProdutoCamaraDAO produtoCamaraDAO = new ProdutoCamaraDAO(conexao);
        List<ProdutoCamara> lstProdutoCamara = new ArrayList<ProdutoCamara>();
        ProdutoCamara produtoCamara = new ProdutoCamara();
        produtoCamara.setIdProduto(producao.getIdProduto());
        produtoCamara.setIdEquipamento(0);
        lstProdutoCamara = produtoCamaraDAO.listaTodos(produtoCamara);
        
//        TransferenciaProducaoDAO transfDAO = new TransferenciaProducaoDAO(conexao);
//        List<TransferenciaProducao> lstTransf = new ArrayList<TransferenciaProducao>();
//        lstTransf = transfDAO.listaTodos(producao);
        
        if (producao != null)
        {
            sessao.setAttribute("producao",producao);
            sessao.setAttribute("lstMaquinaProducao", producao.getLstMaquinaProducao());
            sessao.setAttribute("lstTransferencia", producao.getLstTransferenciaProducao());
            sessao.setAttribute("lstProduto", lstProduto);
            sessao.setAttribute("lstEmbalagem", lstEmbalagem);
            sessao.setAttribute("lstMaquina", lstMaquina);
            sessao.setAttribute("lstAvaria", lstAvaria);
            sessao.setAttribute("lstProdutoCamara", lstProdutoCamara);
            sessao.setAttribute("maquinaProducao", null); 
            sessao.setAttribute("lstAvariaProducao", null);
            sessao.setAttribute("avariaProducao", null);            
            
        }
 
        return "visao/producao.jsp";
    }
}

