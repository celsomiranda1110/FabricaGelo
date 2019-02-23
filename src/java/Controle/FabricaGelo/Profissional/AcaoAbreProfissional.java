/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Profissional;

import Bean.Funcao;
import Bean.Profissional;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.FuncaoDAO;
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
public class AcaoAbreProfissional extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        Profissional profissional = (Profissional)sessao.getAttribute("profissional");
       
        FuncaoDAO funcaoDAO = new FuncaoDAO(conexao);
        List<Funcao> lstFuncao = new ArrayList<Funcao>();
        lstFuncao = funcaoDAO.listaTodos(new Funcao());

        sessao.setAttribute("profissional",profissional);
        sessao.setAttribute("lstFuncao",lstFuncao);
        
        return "visao/profissional.jsp";
    }
    
}
