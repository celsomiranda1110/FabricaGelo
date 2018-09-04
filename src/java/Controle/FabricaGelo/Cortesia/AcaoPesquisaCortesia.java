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
public class AcaoPesquisaCortesia extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Movimento> lstMovimento = new ArrayList<Movimento>();

        
        String pesquisa = req.getParameter("txtPesquisa");
        
        MovimentoDAO cortesiaDAO = new MovimentoDAO(conexao);
        Movimento cortesia = new Movimento();
        cortesia.setTipo("CO");
        cortesia.setDataLancamento(pesquisa);
        lstMovimento = cortesiaDAO.listaTodos(cortesia);

        
        sessao.setAttribute("lstCortesia",lstMovimento); 
        
        return "visao/listarCortesia.jsp";              
        
    }
}
