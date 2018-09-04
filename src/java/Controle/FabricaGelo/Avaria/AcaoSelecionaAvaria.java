/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Avaria;

import Bean.Avaria;
import Bean.Colaborador;
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
public class AcaoSelecionaAvaria extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        AvariaDAO avariaDAO = new AvariaDAO(conexao);
        Avaria avaria = new Avaria();
        
        String idAvaria = req.getParameter("idAvaria");
        avaria.setIdAvaria(Integer.parseInt(idAvaria));
        avaria = avariaDAO.listaUm(avaria);
        
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        
        sessao.setAttribute("colaborador",colaborador);
        sessao.setAttribute("pagRetorno",pagRetorno);
        sessao.setAttribute("avaria",avaria);
        
        
        return "visao/avaria.jsp";
    }
}