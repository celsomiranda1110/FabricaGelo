/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Bairro;

import Bean.Bairro;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.BairroDAO;
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
public class AcaoListarBairro extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Bairro> lstBairro = new ArrayList<Bairro>();
        BairroDAO bairroDAO = new BairroDAO(conexao);
        
        lstBairro = bairroDAO.listaTodos();
        sessao.setAttribute("lstBairro",lstBairro);
        
        return "visao/listarBairro.jsp"; 
    }
}
