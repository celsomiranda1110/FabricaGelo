/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Camara;

import Bean.Camara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.CamaraDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoGravaCamara extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        CamaraDAO camaraDAO = new CamaraDAO(conexao);
        Camara camara = (Camara)sessao.getAttribute("camara");
        if (camara == null)
            camara = new Camara();
        
        String descricao = (req.getParameter("txtDescricao") == "" || req.getParameter("txtDescricao") == null) ? "" : req.getParameter("txtDescricao");
        String capacidade = (req.getParameter("txtCapacidade") == "" || req.getParameter("txtCapacidade") == null) ? "A" : req.getParameter("txtCapacidade");
        String situacao = (req.getParameter("cmbSituacao") == "" || req.getParameter("cmbSituacao") == null) ? "A" : req.getParameter("cmbSituacao");
        
        camara.setDescricao(descricao);
        camara.setCapacidade(Double.parseDouble(capacidade));
        camara.setSituacao(situacao);
        
        
        if (camaraDAO.atualizar(camara))
            sessao.setAttribute("camara", camara);

        
        //return "visao/camara.jsp";
        return "FabricaGelo.Camara.AcaoListarCamara";
    }
}
