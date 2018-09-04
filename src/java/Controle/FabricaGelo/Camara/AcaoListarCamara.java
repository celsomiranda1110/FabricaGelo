/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Camara;

import Bean.Camara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.CamaraDAO;
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
public class AcaoListarCamara extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Camara> lstCamara = new ArrayList<Camara>();
        CamaraDAO camaraDAO = new CamaraDAO(conexao);
        
        lstCamara = camaraDAO.listaTodos();
        sessao.setAttribute("lstCamara",lstCamara);
        
        return "visao/listarCamara.jsp"; 
    }
}
