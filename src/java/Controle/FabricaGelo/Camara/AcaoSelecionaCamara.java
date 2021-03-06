/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Camara;

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
public class AcaoSelecionaCamara extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        String pagRetorno = "FabricaGelo.Camara.AcaoAbreCamara";
        
        EquipamentoDAO camaraDAO = new EquipamentoDAO(conexao);
        Equipamento camara = new Equipamento();
        
        String idEquipamento = req.getParameter("idEquipamento");
        camara.setIdEquipamento(Integer.parseInt(idEquipamento));
        camara = camaraDAO.listaUm(camara);
        
        

        sessao.setAttribute("camara",camara);

        
        return pagRetorno;
    }
}

