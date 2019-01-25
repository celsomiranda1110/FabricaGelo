/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Gerais;

import Bean.Menu;
import Bean.MenuProfissional;
import Bean.Profissional;
import DAO.MenuDAO;
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
public class AcaoAtualizaAcesso extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabrigaGelo.Gerais.AcaoDefinicaoAcesso";
        
        String idProfissional = (req.getParameter("cmbProfissional") == "" || req.getParameter("cmbProfissional") == null) ? "" : req.getParameter("cmbProfissional");
        if (idProfissional.equals(""))
        {
            sessao.setAttribute("avisoErro", "Profissional não selecionado");
            sessao.setAttribute("pagOrigemErro", "FabrigaGelo.Gerais.AcaoDefinicaoAcesso");
            pagRetorno = "visao/erro.jsp";              
        }
        else
        {
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
            Profissional profissional = new Profissional();
            profissional.setIdProfissional(Integer.parseInt(idProfissional));
            profissional = profissionalDAO.listaUm(profissional);
            
            List<MenuProfissional> lstAcesso = new ArrayList<MenuProfissional>();
            
            if (profissional.getLstMenuProfissional().size() == 0)
            {
                MenuDAO menuDAO = new MenuDAO(conexao);
                List<Menu> lstMenu = menuDAO.listaTodos();
                Iterator itMenu = lstMenu.iterator();
                while (itMenu.hasNext())
                {
                    MenuProfissional menuProfissional = new MenuProfissional();
                    String nomeCk = "ck_";
                    
                    Menu mn = (Menu)itMenu.next();
                    nomeCk += mn.getIdMenu();
                    if (req.getParameter(nomeCk) != null)
                        menuProfissional.setAtivo("S");
                    else
                        menuProfissional.setAtivo("N");
                    
                    menuProfissional.setIdMenu(mn.getIdMenu());
                    lstAcesso.add(menuProfissional);
                }
            }
            else
            {
                Iterator itMenuProfissional = profissional.getLstMenuProfissional().iterator();
                while (itMenuProfissional.hasNext())
                {
                    MenuProfissional menuProfissional = (MenuProfissional)itMenuProfissional.next();
                    String nomeCk = "ck_";
                    nomeCk += menuProfissional.getIdMenu();
                    
                    if (req.getParameter(nomeCk) != null)
                        menuProfissional.setAtivo("S");
                    else
                        menuProfissional.setAtivo("N");
                    
                    lstAcesso.add(menuProfissional);
                }
            }
            
            profissional.setLstMenuProfissional(lstAcesso);
            if (profissionalDAO.atualizar(profissional))
            {
                sessao.setAttribute("avisoErro", "Profissional com acesso definido");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Gerais.AcaoDefinicaoAcesso");
                pagRetorno = "visao/erro.jsp";                  
            }
        }
        
        return pagRetorno;
    }
    
}
