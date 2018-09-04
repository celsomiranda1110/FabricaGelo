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
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoPesquisaProfissional extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Profissional> lstProfissional = new ArrayList<Profissional>();
        List<Profissional> lstRetorno = new ArrayList<Profissional>();
        
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
        lstProfissional = profissionalDAO.listaTodos();
        
        String pesquisa = req.getParameter("txtPesquisa");
        if (pesquisa != "")
        {
            Iterator it = lstProfissional.iterator();
            while (it.hasNext())
            {
                Profissional _profissional = (Profissional)it.next();

                if (_profissional.getNome().contains(pesquisa.toUpperCase()))
                    lstRetorno.add(_profissional);
            }
        }
        else
            lstRetorno = lstProfissional; 
        
        sessao.setAttribute("lstProfissional",lstRetorno); 
        
        return "visao/listarProfissional.jsp";        
    }
}
