/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Bonificacao;

import Bean.Colaborador;
import Bean.Movimento;
import Bean.Profissional;
import Controle.FabricaGelo.Gerais.Acao;
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

        Profissional usuario = (Profissional)sessao.getAttribute("usuario");
        
        List<Colaborador> lstColaborador = new ArrayList<Colaborador>();
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        
        // Dados do bonificacao
        String numero = (req.getParameter("txtNumero") == "" || req.getParameter("txtNumero") == null) ? "" : req.getParameter("txtNumero");
        String notaFiscal = (req.getParameter("txtNotaFiscal").equals("") || req.getParameter("txtNotaFiscal") == null) ? "" : req.getParameter("txtNotaFiscal");
        String dtLancamento = (req.getParameter("txtLancamento") == "" || req.getParameter("txtLancamento") == null) ? "" : req.getParameter("txtLancamento");        
        String situacao = (req.getParameter("cmbSituacao") == "" || req.getParameter("cmbSituacao") == null) ? "" : req.getParameter("cmbSituacao").toUpperCase();        
      
        Movimento bonificacao = (Movimento)sessao.getAttribute("bonificacao");
        if (bonificacao == null)
            bonificacao = new Movimento();
        
        bonificacao.setProfissional(usuario);
        bonificacao.setNumero(numero);
        bonificacao.setNotaFiscal(notaFiscal);
        bonificacao.setDataLancamento(dtLancamento);
        bonificacao.setSituacao(situacao);
        bonificacao.setTipo("BO");        
        
        lstColaborador = colaboradorDAO.listaTodos();
        
        sessao.setAttribute("movimento",bonificacao);
        sessao.setAttribute("lstColaborador",lstColaborador);
        sessao.setAttribute("pagRetorno","FabricaGelo.Bonificacao.AcaoAbreBonificacao");
        
        return "visao/listarColaborador.jsp";
    }
}

