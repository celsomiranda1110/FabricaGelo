/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Camara;

import Bean.Colaborador;
import Bean.Camara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.CamaraDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaCamara extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        CamaraDAO camaraDAO = new CamaraDAO(conexao);
        Camara camara = new Camara();
        
        String idCamara = req.getParameter("idCamara");
        camara.setIdCamara(Integer.parseInt(idCamara));
        camara = camaraDAO.listaUm(camara);
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        
        
        sessao.setAttribute("colaborador",colaborador);
        sessao.setAttribute("camara",camara);
        sessao.setAttribute("pagRetorno",pagRetorno);
        
        return "visao/camara.jsp";
    }
}

