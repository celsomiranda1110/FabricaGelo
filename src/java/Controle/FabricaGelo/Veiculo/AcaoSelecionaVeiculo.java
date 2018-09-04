/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Veiculo;

import Bean.Colaborador;
import Bean.Veiculo;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.VeiculoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaVeiculo extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);
        Veiculo veiculo = new Veiculo();
        
        String idVeiculo = req.getParameter("idVeiculo");
        veiculo.setIdVeiculo(Integer.parseInt(idVeiculo));
        veiculo = veiculoDAO.listaUm(veiculo);
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        
        
        sessao.setAttribute("colaborador",colaborador);
        sessao.setAttribute("veiculo",veiculo);
        sessao.setAttribute("pagRetorno",pagRetorno);
        
        return "visao/veiculo.jsp";
    }
}
