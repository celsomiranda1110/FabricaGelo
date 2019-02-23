/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Camara;

import Bean.Equipamento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EquipamentoDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoPesquisaCamara extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Equipamento> lstCamara = new ArrayList<Equipamento>();
        List<Equipamento> lstRetorno = new ArrayList<Equipamento>();
        
        String pesquisa = req.getParameter("txtPesquisa");
        String situacao = req.getParameter("cmbSituacaoPesquisa").equals("") ? "" : req.getParameter("cmbSituacaoPesquisa");
         
        
        EquipamentoDAO camaraDAO = new EquipamentoDAO(conexao);
        Equipamento camara = new Equipamento();
        camara.setTipo("CA");
        camara.setAtivo(situacao);
        lstCamara = camaraDAO.listaTodos(camara);
        
        
        if (!pesquisa.equals(""))
        {
            Iterator it = lstCamara.iterator();
            while (it.hasNext())
            {
                Equipamento _camara = (Equipamento)it.next();

                if (_camara.getDescricao().contains(pesquisa.toUpperCase()))
                    lstRetorno.add(_camara);
            }
        }
        else
            lstRetorno = lstCamara; 
        
        sessao.setAttribute("lstCamara",lstRetorno); 
        
        return "visao/listarCamara.jsp";        
    }
}

