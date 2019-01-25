/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.MovimentoEntrega;

import Bean.ColaboradorEntrega;
import Bean.Entrega;
import Bean.MovimentoEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MovimentoEntregaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaMovimentoEntrega extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.MovimentoEntrega.AcaoAbreMovimentoEntrega";

        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        ColaboradorEntrega colaboradorEntrega = (ColaboradorEntrega)sessao.getAttribute("colaboradorEntrega");        
        
        String idMovimentoEntrega = (req.getParameter("idMovimentoEntrega").equals("") || req.getParameter("idMovimentoEntrega") == null) ? "0" : req.getParameter("idMovimentoEntrega");
        MovimentoEntregaDAO movimentoEntregaDAO = new MovimentoEntregaDAO(conexao);
        MovimentoEntrega movimentoEntrega = new MovimentoEntrega();
        movimentoEntrega.setIdMovimentoEntrega(Integer.parseInt(idMovimentoEntrega));
        movimentoEntrega = movimentoEntregaDAO.listaUm(movimentoEntrega);
        
        sessao.setAttribute("entrega",entrega);
        sessao.setAttribute("colaboradorEntrega", colaboradorEntrega);
        sessao.setAttribute("movimentoEntrega", movimentoEntrega);
        
        return pagRetorno;
        
    }
}
