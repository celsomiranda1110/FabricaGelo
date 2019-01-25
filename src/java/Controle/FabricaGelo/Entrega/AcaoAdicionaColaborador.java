/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.ColaboradorEntrega;
import Bean.Entrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
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
public class AcaoAdicionaColaborador extends Acao{ 
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Entrega.AcaoNovaRota";
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        
        List<ColaboradorEntrega> lstColaboradorEntrega = new ArrayList<ColaboradorEntrega>();
        ColaboradorEntrega colaboradorEntrega = new ColaboradorEntrega();
        
        String motivo = (req.getParameter("cmbMotivo").equals("") || req.getParameter("cmbMotivo") == null) ? "" : req.getParameter("cmbMotivo");
        String idColaborador = (req.getParameter("cmbColaborador").equals("") || req.getParameter("cmbColaborador") == null) ? "0" : req.getParameter("cmbColaborador");
        String situacao = "AT";
        
        colaboradorEntrega.setMotivo(motivo);
        colaboradorEntrega.setIdColaborador(Integer.parseInt(idColaborador));
        colaboradorEntrega.setSituacao(situacao);
        
        lstColaboradorEntrega.add(colaboradorEntrega);
        entrega.setLstColaboradorEntrega(lstColaboradorEntrega);
        
        if (entregaDAO.atualizar(entrega))
        {
            entrega = entregaDAO.listaUm(entrega);

            sessao.setAttribute("entrega",entrega);
            
            sessao.setAttribute("paramPesquisa",""); 
            sessao.setAttribute("lstColaborador",null); 

        }        
        
        return pagRetorno;
        
    }
}
