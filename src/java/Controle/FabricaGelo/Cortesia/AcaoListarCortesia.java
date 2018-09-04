/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Cortesia;

import Bean.Movimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MovimentoDAO;
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
public class AcaoListarCortesia extends Acao{
public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        
        
        List<Movimento> lstCortesia = new ArrayList();
        MovimentoDAO cortesiaDAO = new MovimentoDAO(conexao);
        Movimento cortesia = new Movimento();
        cortesia.setTipo("CO"); // limitando pesquisa para movimentos de cortesias
        
        lstCortesia = cortesiaDAO.listaTodos(cortesia);
        sessao.setAttribute("lstCortesia",lstCortesia);
        

        return "visao/listarCortesia.jsp";
    }       
}
