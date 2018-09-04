/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Bairro;

import Bean.Bairro;
import Bean.Colaborador;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.BairroDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaBairro extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        BairroDAO bairroDAO = new BairroDAO(conexao);
        Bairro bairro = new Bairro();
        
        String idBairro = req.getParameter("idBairro");
        bairro.setIdBairro(Integer.parseInt(idBairro));
        bairro = bairroDAO.listaUm(bairro);
        
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        
        sessao.setAttribute("colaborador",colaborador);
        sessao.setAttribute("pagRetorno",pagRetorno);
        sessao.setAttribute("bairro",bairro);
        
        
        return "visao/bairro.jsp";
    }
}
