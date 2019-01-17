/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Veiculo;

import Bean.Colaborador;
import Bean.Equipamento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EquipamentoDAO;
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

        EquipamentoDAO veiculoDAO = new EquipamentoDAO(conexao);
        Equipamento veiculo = new Equipamento();
        
        String idEquipamento = req.getParameter("idEquipamento");
        veiculo.setIdEquipamento(Integer.parseInt(idEquipamento));
        veiculo = veiculoDAO.listaUm(veiculo);
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");

        sessao.setAttribute("veiculo",veiculo);
        sessao.setAttribute("lstManutencao", veiculo.getLstManutencao());
        sessao.setAttribute("pagRetorno",pagRetorno);
        
        return "visao/veiculo.jsp";
    }
}
