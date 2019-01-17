/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Entrega;
import Bean.Profissional;
import Bean.ProfissionalEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import DAO.ProfissionalDAO;
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
public class AcaoAdicionaProfissional extends Acao{ 
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Entrega.AcaoNovaRota";
        
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        
                
        
        List<ProfissionalEntrega> lstProfissionalEntrega = new ArrayList<ProfissionalEntrega>();
        
        String idProfissional = (req.getParameter("cmbProfissional").equals("") || req.getParameter("cmbProfissional") == null) ? "0" : req.getParameter("cmbProfissional");
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
        Profissional profissional = new Profissional();
        profissional.setIdProfissional(Integer.parseInt(idProfissional));
        profissional = profissionalDAO.listaUm(profissional);
        
        ProfissionalEntrega profissionalEntrega = new ProfissionalEntrega();
        profissionalEntrega.setProfissional(profissional);
        profissionalEntrega.setResponsavel(("N"));
        
        lstProfissionalEntrega.add(profissionalEntrega);
        entrega.setLstProfissionalEntrega(lstProfissionalEntrega);

        
        if (entregaDAO.atualizar(entrega))
        {
           entrega = entregaDAO.listaUm(entrega);
           
           sessao.setAttribute("entrega",entrega);
//            sessao.setAttribute("lstProfissional",lstProfissional);
        }
        
           
        
        return pagRetorno;
    }
}
