/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Carregamento;

import Bean.SaidaCamara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.SaidaCamaraDAO;
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
public class AcaoListarCarregamento extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<SaidaCamara> lstSaidaCamara = new ArrayList<SaidaCamara>();
        SaidaCamaraDAO saidaCamaraDAO = new SaidaCamaraDAO(conexao);
        
        lstSaidaCamara = saidaCamaraDAO.listaTodos(null);
        sessao.setAttribute("lstSaidaCamara",lstSaidaCamara);
        
        return "visao/listarSaidaCamara.jsp"; 
    }
}
