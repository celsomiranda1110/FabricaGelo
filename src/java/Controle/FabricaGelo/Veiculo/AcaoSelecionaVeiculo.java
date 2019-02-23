/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Veiculo;

import Bean.Colaborador;
import Bean.Equipamento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import DAO.EquipamentoDAO;
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
public class AcaoSelecionaVeiculo extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        String pagRetorno = "FabricaGelo.Veiculo.AcaoAbreVeiculo";
        
        EquipamentoDAO veiculoDAO = new EquipamentoDAO(conexao);
        Equipamento veiculo = new Equipamento();
        
        String idEquipamento = req.getParameter("idEquipamento");
        veiculo.setIdEquipamento(Integer.parseInt(idEquipamento));
        veiculo = veiculoDAO.listaUm(veiculo);
        
        
        sessao.setAttribute("veiculo",veiculo);
        
        
        return pagRetorno;
    }
}
