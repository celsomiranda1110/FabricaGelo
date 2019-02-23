/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Avaria;
import Bean.AvariaProducao;
import Bean.Equipamento;
import Bean.MaquinaProducao;
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
public class AcaoAbreProducao extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        List<ProdutoCamara> lstProdutoCamara = new ArrayList<ProdutoCamara>();
        
        MaquinaProducao maquinaProducao = (MaquinaProducao)sessao.getAttribute("maquinaProducao");
        
        ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
        Producao producao = (Producao)sessao.getAttribute("producao");
        if (producao != null)
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


        
      
        
        sessao.setAttribute("producao",producao);
        sessao.setAttribute("lstMaquinaProducao", (producao == null ? null : producao.getLstMaquinaProducao()) );
        sessao.setAttribute("lstProduto", lstProduto);
        sessao.setAttribute("lstEmbalagem", lstEmbalagem);
        sessao.setAttribute("lstMaquina", lstMaquina);
        sessao.setAttribute("maquinaProducao",maquinaProducao);

            

        
        return "visao/producao.jsp";

    }
    
}
