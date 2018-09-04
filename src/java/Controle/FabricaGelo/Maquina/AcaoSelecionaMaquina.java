/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Maquina;

import Bean.Colaborador;
import Bean.Maquina;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MaquinaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaMaquina extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        MaquinaDAO maquinaDAO = new MaquinaDAO(conexao);
        Maquina maquina = new Maquina();
        
        String idMaquina = req.getParameter("idMaquina");
        maquina.setIdMaquina(Integer.parseInt(idMaquina));
        maquina = maquinaDAO.listaUm(maquina);
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        
        
        sessao.setAttribute("colaborador",colaborador);
        sessao.setAttribute("maquina",maquina);
        sessao.setAttribute("pagRetorno",pagRetorno);
        
        return "visao/maquina.jsp";
    }
}
