/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.ColaboradorEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorEntregaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaColaboradorEntrega extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "";
        
        // selecionando colaboradorEntrega
        String idColaboradorEntrega = req.getParameter("idColaboradorEntrega");
        if (idColaboradorEntrega.equals(""))
        {
            sessao.setAttribute("avisoErro", "Cliente não selecionado!");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            ColaboradorEntregaDAO colaboradorEntregaDAO = new ColaboradorEntregaDAO(conexao);
            ColaboradorEntrega colaboradorEntrega = new ColaboradorEntrega();
            colaboradorEntrega.setIdColaboradorEntrega(Integer.parseInt(idColaboradorEntrega));
            colaboradorEntrega = colaboradorEntregaDAO.listaUm(colaboradorEntrega);

            if (colaboradorEntrega != null)
            {
                sessao.setAttribute("colaboradorEntrega", colaboradorEntrega);
                sessao.setAttribute("lstMovimentoEntrega", (colaboradorEntrega != null ? colaboradorEntrega.getLstMovimentoEntrega() : null));
            }            
        }

        
        return "FabricaGelo.Entrega.AcaoEncerrarRota";
    }
}
