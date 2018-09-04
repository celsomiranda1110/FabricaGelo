/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Maquina;

import Bean.Maquina;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MaquinaDAO;
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
public class AcaoGravaMaquina extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        MaquinaDAO maquinaDAO = new MaquinaDAO(conexao);
        Maquina maquina = (Maquina)sessao.getAttribute("maquina");
        if (maquina == null)
            maquina = new Maquina();
        
        String descricao = (req.getParameter("txtDescricao") == "" || req.getParameter("txtDescricao") == null) ? "" : req.getParameter("txtDescricao");
        String situacao = (req.getParameter("cmbSituacao") == "" || req.getParameter("cmbSituacao") == null) ? "A" : req.getParameter("cmbSituacao");
        
        maquina.setDescricao(descricao);
        maquina.setSituacao(situacao);
        
        
        if (maquinaDAO.atualizar(maquina))
            sessao.setAttribute("maquina", maquina);

        
        //return "visao/maquina.jsp";
        return "FabricaGelo.Maquina.AcaoListarMaquina";
    }
}
