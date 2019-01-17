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
public class AcaoSelecionaClienteVisita extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        // deletando colaboradorEntrega
        String idColaboradorEntrega = req.getParameter("idColaboradorEntrega");
        ColaboradorEntregaDAO colaboradorEntregaDAO = new ColaboradorEntregaDAO(conexao);
        ColaboradorEntrega colaboradorEntrega = new ColaboradorEntrega();
        colaboradorEntrega.setIdColaboradorEntrega(Integer.parseInt(idColaboradorEntrega));
        colaboradorEntrega = colaboradorEntregaDAO.listaUm(colaboradorEntrega);
        
        if (colaboradorEntrega != null)
        {
            sessao.setAttribute("colaboradorEntrega", colaboradorEntrega);
            sessao.setAttribute("lstMovimentoEntrega", colaboradorEntrega.getLstMovimentoEntrega());
        }
        
        return "visao/listarMovimento.jsp";
    }
}
