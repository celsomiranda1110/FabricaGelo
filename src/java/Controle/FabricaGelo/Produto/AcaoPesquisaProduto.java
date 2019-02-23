/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Produto;

import Bean.Produto;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProdutoDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoPesquisaProduto extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Produto> lstProduto = new ArrayList<Produto>();
        List<Produto> lstRetorno = new ArrayList<Produto>();

        String pesquisa = req.getParameter("txtPesquisa");
        String situacao = req.getParameter("cmbSituacaoPesquisa").equals("") ? "" : req.getParameter("cmbSituacaoPesquisa");

        
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        Produto produto = new Produto();
        produto.setTipo("PR");
        produto.setAtivo(situacao);
        
        lstProduto = produtoDAO.listaTodos(produto);
        
        if (!pesquisa.equals(""))
        {
            Iterator it = lstProduto.iterator();
            while (it.hasNext())
            {
                Produto _produto = (Produto)it.next();

                if (_produto.getDescricao().contains(pesquisa.toUpperCase()))
                    lstRetorno.add(_produto);
            }
        }
        else
            lstRetorno = lstProduto; 
        
        sessao.setAttribute("lstProduto",lstRetorno); 
        
        return "visao/listarProduto.jsp";        
    }
    
}
