/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Producao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProducaoDAO;
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
public class AcaoPesquisaProducao extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
       
        String dtPesquisa = (req.getParameter("txtPesquisa").equals("") || req.getParameter("txtPesquisa") == null ? "1900-01-01" : req.getParameter("txtPesquisa"));
        String situacao = req.getParameter("cmbSituacaoPesquisa").equals("") ? "" : req.getParameter("cmbSituacaoPesquisa");
        
        ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
        Producao producao = new Producao();
        producao.setSituacao(situacao);
        
        List<Producao> lstRetorno = new ArrayList<Producao>();
        List<Producao> lstProducao = producaoDAO.listaTodos(producao);
        
        if (!dtPesquisa.equals("1900-01-01"))
        {
            Iterator itProducao = lstProducao.iterator();
            while (itProducao.hasNext())
            {
                Producao _producao = (Producao)itProducao.next();
                if (_producao.getDataFormatada().equals(dtPesquisa))
                    lstRetorno.add(_producao);
            }
        }
        else
            lstRetorno = lstProducao;
        
        sessao.setAttribute("lstProducao",lstRetorno); 
        
        return "visao/listarProducao.jsp";
    }
}
