/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Aluguel;

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
public class AcaoListarAluguel extends Acao{
public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        
        
        List<Movimento> lstAluguel = new ArrayList();
        MovimentoDAO aluguelDAO = new MovimentoDAO(conexao);
        Movimento aluguel = new Movimento();
        aluguel.setTipo("AL"); // limitando pesquisa para movimentos de aluguels
        
        lstAluguel = aluguelDAO.listaTodos(aluguel);
        sessao.setAttribute("lstAluguel",lstAluguel);
        

        return "visao/listarAluguel.jsp";
    }       
}
