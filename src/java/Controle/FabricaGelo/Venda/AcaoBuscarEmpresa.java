/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Venda;

import Bean.Bairro;
import Bean.Colaborador;
import Bean.Movimento;
import Bean.Profissional;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.BairroDAO;
import DAO.ColaboradorDAO;
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
public class AcaoBuscarEmpresa extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        

        
        List<Colaborador> lstColaborador = new ArrayList<Colaborador>();
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        
        // Dados do venda
        String numero = (req.getParameter("txtNumero") == "" || req.getParameter("txtNumero") == null) ? "" : req.getParameter("txtNumero");
        String notaFiscal = (req.getParameter("txtNotaFiscal").equals("") || req.getParameter("txtNotaFiscal") == null) ? "" : req.getParameter("txtNotaFiscal");
        String dtLancamento = (req.getParameter("txtLancamento") == "" || req.getParameter("txtLancamento") == null) ? "" : req.getParameter("txtLancamento");        
        String situacao = (req.getParameter("cmbSituacao") == "" || req.getParameter("cmbSituacao") == null) ? "" : req.getParameter("cmbSituacao").toUpperCase();        
      
        Movimento venda = (Movimento)sessao.getAttribute("venda");
        if (venda == null)
            venda = new Movimento();
        
        venda.setNumero(numero);
        venda.setNotaFiscal(notaFiscal);
        venda.setDataLancamento(dtLancamento);
        venda.setSituacao(situacao);
        venda.setTipo("VE");        
        
        lstColaborador = colaboradorDAO.listaTodos();
        
        sessao.setAttribute("movimento",venda);
        sessao.setAttribute("lstColaborador",lstColaborador);
        sessao.setAttribute("pagRetorno","FabricaGelo.Venda.AcaoAbreVenda");
        
        return "visao/listarColaborador.jsp";
    }
}
