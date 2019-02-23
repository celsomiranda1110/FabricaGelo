/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Insumo;

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
public class AcaoPesquisaInsumo extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Produto> lstInsumo = new ArrayList<Produto>();
        List<Produto> lstRetorno = new ArrayList<Produto>();
        
        String pesquisa = req.getParameter("txtPesquisa");
        String situacao = req.getParameter("cmbSituacaoPesquisa").equals("") ? "" : req.getParameter("cmbSituacaoPesquisa");
        
        ProdutoDAO insumoDAO = new ProdutoDAO(conexao); 
        Produto insumo = new Produto();
        insumo.setTipo("IN");
        insumo.setAtivo(situacao);
        
        lstInsumo = insumoDAO.listaTodos(insumo);
        

        if (!pesquisa.equals(""))
        {
            Iterator it = lstInsumo.iterator();
            while (it.hasNext())
            {
                Produto _insumo = (Produto)it.next();

                if (_insumo.getDescricao().contains(pesquisa.toUpperCase()))
                    lstRetorno.add(_insumo);
            }
        }
        else
            lstRetorno = lstInsumo; 
        
        sessao.setAttribute("lstInsumo",lstInsumo); 
        
        return "visao/listarInsumo.jsp";        
    }
    
}
