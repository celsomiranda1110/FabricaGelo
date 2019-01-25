/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Gerais;

import Bean.Menu;
import Bean.Profissional;
import DAO.MenuDAO;
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
public class AcaoDefinicaoAcesso extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/acessoFuncionario.jsp";
        
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
        Profissional profissional = null;
            
        String idProfissional = (req.getParameter("cmbProfissional") == "" || req.getParameter("cmbProfissional") == null) ? "" : req.getParameter("cmbProfissional");
        if (!idProfissional.equals(""))
        {
            profissional = new Profissional();
            profissional.setIdProfissional(Integer.parseInt(idProfissional));
            profissional = profissionalDAO.listaUm(profissional); 
        }
        
        List<Profissional> lstProfissional = new ArrayList<Profissional>();
        lstProfissional = profissionalDAO.listaTodos();
        
        List<Menu> lstMenu = new ArrayList();
        MenuDAO menuDAO = new MenuDAO(conexao);
        lstMenu = menuDAO.listaTodos();
        
        sessao.setAttribute("profissional", profissional);
        if (profissional  != null)
            sessao.setAttribute("lstMenuProfissional", (profissional.getLstMenuProfissional().size() == 0 ? null : profissional.getLstMenuProfissional()));
        else
            sessao.setAttribute("lstMenuProfissional", null);
            
        sessao.setAttribute("lstProfissional", lstProfissional);
        sessao.setAttribute("lstMenu", lstMenu);        

        return pagRetorno;
        
    }
}
