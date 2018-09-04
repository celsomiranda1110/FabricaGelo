/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle.FabricaGelo.Relatorios;

import Bean.Colaborador;
import Bean.Pagamento;
import DAO.PagamentoDAO;
import Controle.FabricaGelo.Gerais.Acao;
import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miranda
 */
public class AcaoPesquisaRelFinanceiro extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        
//        Connection conexao = (Connection)req.getAttribute("connection");
//        HttpSession sessao = req.getSession(false);
//        Empresa empresa = (Empresa)sessao.getAttribute("empresa");        
//        
//        PagamentoDAO pagamentoDAO = new PagamentoDAO(conexao);
//        pagamentoDAO.setDataInicial(req.getParameter("txtPer1"));
//        pagamentoDAO.setDataFinal(req.getParameter("txtPer2"));
//        // lista de entradas
//        pagamentoDAO.setTipoPagamento(1);
//        List<Pagamento> lstPagamentoEnt = pagamentoDAO.listaTodos(null);
//        // lista de saídas
//        pagamentoDAO.setTipoPagamento(2);
//        List<Pagamento> lstPagamentoSai = pagamentoDAO.listaTodos(null);
//        
//        sessao.setAttribute("lstPagamentoEnt", lstPagamentoEnt);
//        sessao.setAttribute("lstPagamentoSai", lstPagamentoSai);
//        
//        sessao.setAttribute("dtPer1", req.getParameter("txtPer1"));
//        sessao.setAttribute("dtPer2", req.getParameter("txtPer2"));
//        
//        return "visao/gerFinanceiro.jsp";
        return "";
    }
}
