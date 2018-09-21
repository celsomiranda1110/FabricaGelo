/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Movimento;
import Bean.Producao;
import Bean.Produto;
import Bean.Profissional;
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
public class AcaoBuscarProduto extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        // objeto produção
        String dataProducao = (req.getParameter("txtData").equals("") || req.getParameter("txtData") == null) ? dataAtual() : req.getParameter("txtData");
        String turno = (req.getParameter("cmbTurno").equals("") || req.getParameter("cmbTurno") == null) ? "" : req.getParameter("cmbTurno");
        String quantidade = (req.getParameter("txtQuantidade").equals("") || req.getParameter("txtQuantidade") == null) ? "0" : req.getParameter("txtQuantidade");                
        Producao producao = (Producao)sessao.getAttribute("producao");
        if (producao == null)
            producao = new Producao();
        producao.setDataFormatada(dataProducao);
        producao.setTurno(turno);
        producao.setQuantidade(Double.parseDouble(quantidade));

        List<Produto> lstProduto = new ArrayList<Produto>();
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        Produto produto = new Produto();
        produto.setTipo("PR");
        lstProduto = produtoDAO.listaTodos(produto);

        sessao.setAttribute("producao",producao);
        sessao.setAttribute("lstProduto",lstProduto);
        sessao.setAttribute("pagRetorno","FabricaGelo.Producao.AcaoAbreProducao");
        
        return "visao/listarProduto.jsp"; 
    }
    
}
