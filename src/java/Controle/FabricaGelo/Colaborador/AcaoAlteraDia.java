/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;

import Bean.Colaborador;
import Bean.VisitaColaborador;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import DAO.VisitaColaboradorDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoAlteraDia extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");

        String idVisitaColaborador = req.getParameter("idVisitaColaborador");
        VisitaColaboradorDAO visitaColaboradorDAO = new VisitaColaboradorDAO(conexao);
        VisitaColaborador visitaColaborador = new VisitaColaborador();
        visitaColaborador.setIdVisitaColaborador(Integer.parseInt(idVisitaColaborador)); 
        visitaColaborador = visitaColaboradorDAO.listaUm(visitaColaborador); 
        
        if (visitaColaborador.getAtivo().equals("A"))
            visitaColaborador.setAtivo("I");
        else
            visitaColaborador.setAtivo("A");
        
        visitaColaboradorDAO.atualizar(visitaColaborador);
        
        colaborador = colaboradorDAO.listaUm(colaborador);
        
        sessao.setAttribute("colaborador",colaborador);
        sessao.setAttribute("lstColaboradorProduto",colaborador.getLstColaboradorProduto());
        sessao.setAttribute("lstVisitaColaborador",colaborador.getLstVisitaColaborador());        
        
        return "visao/colaborador.jsp";
    }
}
