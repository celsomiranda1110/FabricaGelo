/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Entrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
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
public class AcaoListarEntrega extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        List<Entrega> lstEntrega = new ArrayList<Entrega>();
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        lstEntrega = entregaDAO.listaTodos(null);
        
        sessao.setAttribute("entrega",null);
        sessao.setAttribute("lstEntrega",lstEntrega);
        
        return "visao/listarEntregas.jsp"; 
        
    }
}
