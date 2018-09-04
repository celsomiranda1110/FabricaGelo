/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Maquina;

import Bean.Maquina;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MaquinaDAO;
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
public class AcaoListarMaquina extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Maquina> lstMaquina = new ArrayList<Maquina>();
        MaquinaDAO maquinaDAO = new MaquinaDAO(conexao);
        
        lstMaquina = maquinaDAO.listaTodos();
        sessao.setAttribute("lstMaquina",lstMaquina);
        
        return "visao/listarMaquina.jsp"; 
    }
}
