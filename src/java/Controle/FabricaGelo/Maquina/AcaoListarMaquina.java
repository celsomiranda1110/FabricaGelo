/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Maquina;

import Bean.Equipamento;
import Controle.FabricaGelo.Gerais.Acao;
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
public class AcaoListarMaquina extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Equipamento> lstMaquina = new ArrayList<Equipamento>();
        EquipamentoDAO maquinaDAO = new EquipamentoDAO(conexao);
        Equipamento equipamento = new Equipamento();
        equipamento.setTipo("MA");
        
        lstMaquina = maquinaDAO.listaTodos(equipamento);
        sessao.setAttribute("lstMaquina",lstMaquina);
        
        return "visao/listarMaquina.jsp"; 
    }
}
