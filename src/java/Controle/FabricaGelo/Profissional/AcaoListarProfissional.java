/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Profissional;

import Bean.Profissional;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProfissionalDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoListarProfissional extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Profissional> lstProfissional = new ArrayList<Profissional>();
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
        
        lstProfissional = profissionalDAO.listaTodos();
        
        
        sessao.setAttribute("lstProfissional",lstProfissional);
        sessao.setAttribute("profissional",null);
        
        return "visao/listarProfissional.jsp"; 
    }
}
