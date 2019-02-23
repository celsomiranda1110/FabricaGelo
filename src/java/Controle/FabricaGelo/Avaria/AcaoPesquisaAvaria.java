/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Avaria;

import Bean.Avaria;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaDAO;
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
public class AcaoPesquisaAvaria extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Avaria> lstAvaria = new ArrayList<Avaria>();
        List<Avaria> lstRetorno = new ArrayList<Avaria>();
        
        String pesquisa = req.getParameter("txtPesquisa");
        String situacao = req.getParameter("cmbSituacaoPesquisa").equals("") ? "" : req.getParameter("cmbSituacaoPesquisa");
        
        
        AvariaDAO avariaDAO = new AvariaDAO(conexao);
        Avaria avaria = new Avaria();
        avaria.setAtivo(situacao);
        lstAvaria = avariaDAO.listaTodos(avaria);
        
        if (!pesquisa.equals(""))
        {
            Iterator it = lstAvaria.iterator();
            while (it.hasNext())
            {
                Avaria _avaria = (Avaria)it.next();

                if (_avaria.getDescricao().contains(pesquisa.toUpperCase()))
                    lstRetorno.add(_avaria);
            }
        }
        else
            lstRetorno = lstAvaria; 
        
        sessao.setAttribute("lstAvaria",lstRetorno); 
        
        return "visao/listarAvaria.jsp";        
    }
}
