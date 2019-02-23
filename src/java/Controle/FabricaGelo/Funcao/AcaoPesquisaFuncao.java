/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Funcao;

import Bean.Funcao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.FuncaoDAO;
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
public class AcaoPesquisaFuncao extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Funcao> lstFuncao = new ArrayList<Funcao>();
        List<Funcao> lstRetorno = new ArrayList<Funcao>();

        String pesquisa = req.getParameter("txtPesquisa");
        String situacao = req.getParameter("cmbSituacaoPesquisa").equals("") ? "" : req.getParameter("cmbSituacaoPesquisa");
        
        FuncaoDAO funcaoDAO = new FuncaoDAO(conexao);
        Funcao funcao = new Funcao();
        funcao.setAtivo(situacao);
        
        lstFuncao = funcaoDAO.listaTodos(funcao);
        
        
        if (!pesquisa.equals(""))
        {
            Iterator it = lstFuncao.iterator();
            while (it.hasNext())
            {
                Funcao _funcao = (Funcao)it.next();

                if (_funcao.getDescricao().contains(pesquisa.toUpperCase()))
                    lstRetorno.add(_funcao);
            }
        }
        else
            lstRetorno = lstFuncao; 
        
        sessao.setAttribute("lstFuncao",lstRetorno); 
        
        return "visao/listarFuncao.jsp";        
    }
}
