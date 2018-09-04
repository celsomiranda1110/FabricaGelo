/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Venda;

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
public class AcaoListarVenda extends Acao{
public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        
        
        List<Movimento> lstVenda = new ArrayList();
        MovimentoDAO vendaDAO = new MovimentoDAO(conexao);
        Movimento venda = new Movimento();
        venda.setTipo("VE"); // limitando pesquisa para movimentos de vendas
        
        lstVenda = vendaDAO.listaTodos(venda);
        sessao.setAttribute("lstVenda",lstVenda);
        

        return "visao/listarVenda.jsp";
    }       
}
