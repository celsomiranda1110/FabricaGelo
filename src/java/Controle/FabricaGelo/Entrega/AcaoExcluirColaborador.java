/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.ColaboradorEntrega;
import Bean.Entrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorEntregaDAO;
import DAO.EntregaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoExcluirColaborador extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Entrega.AcaoNovaRota";
        
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        
        // deletando colaboradorEntrega
        String idColaboradorEntrega = req.getParameter("idColaboradorEntrega");
        ColaboradorEntregaDAO colaboradorEntregaDAO = new ColaboradorEntregaDAO(conexao);
        ColaboradorEntrega colaboradorEntrega = new ColaboradorEntrega();
        colaboradorEntrega.setIdColaboradorEntrega(Integer.parseInt(idColaboradorEntrega));
        
        if (colaboradorEntregaDAO.delete(colaboradorEntrega))
            entrega = entregaDAO.listaUm(entrega);

        sessao.setAttribute("entrega",entrega);
         
        
        return pagRetorno;
        
    }
}
