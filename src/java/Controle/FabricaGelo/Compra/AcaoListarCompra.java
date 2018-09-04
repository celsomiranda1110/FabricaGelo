/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Compra;

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
public class AcaoListarCompra extends Acao{
public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        
        
        List<Movimento> lstCompra = new ArrayList();
        MovimentoDAO compraDAO = new MovimentoDAO(conexao);
        Movimento compra = new Movimento();
        compra.setTipo("CP"); // limitando pesquisa para movimentos de compras
        
        lstCompra = compraDAO.listaTodos(compra);
        sessao.setAttribute("lstCompra",lstCompra);
        

        return "visao/listarCompra.jsp";
    }       
}
