/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Veiculo;

import Bean.Veiculo;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.VeiculoDAO;
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
public class AcaoListarVeiculo extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Veiculo> lstVeiculo = new ArrayList<Veiculo>();
        VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);
        
        lstVeiculo = veiculoDAO.listaTodos();
        sessao.setAttribute("lstVeiculo",lstVeiculo);
        
        return "visao/listarVeiculo.jsp"; 
    }
}
