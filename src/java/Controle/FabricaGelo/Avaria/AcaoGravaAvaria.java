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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoGravaAvaria extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        AvariaDAO avariaDAO = new AvariaDAO(conexao);
        Avaria avaria = (Avaria)sessao.getAttribute("avaria");
        if (avaria == null)
            avaria = new Avaria();
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        
        String descricao = (req.getParameter("txtAvaria") == "" || req.getParameter("txtAvaria") == null) ? "" : req.getParameter("txtAvaria");
        
        avaria.setDescricao(descricao);
        
        
        if (avariaDAO.atualizar(avaria))
        
            sessao.setAttribute("avaria", avaria);
        
        sessao.setAttribute("pagRetorno",pagRetorno);
        
        return "FabricaGelo.Avaria.AcaoListarAvaria";
    }
}

