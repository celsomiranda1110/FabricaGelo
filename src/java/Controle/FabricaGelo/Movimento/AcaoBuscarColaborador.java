/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Movimento;

import Bean.Colaborador;
import Bean.Movimento;
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
public class AcaoBuscarColaborador extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        

        
        List<Colaborador> lstColaborador = new ArrayList<Colaborador>();
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        lstColaborador = colaboradorDAO.listaTodos();
        
        // Dados do venda
        String numero = (req.getParameter("txtNumero") == "" || req.getParameter("txtNumero") == null) ? "" : req.getParameter("txtNumero");
        String tipo = (req.getParameter("cmbTipo").equals("") || req.getParameter("cmbTipo") == null) ? "" : req.getParameter("cmbTipo");
        String dtLancamento = (req.getParameter("txtLancamento") == "" || req.getParameter("txtLancamento") == null) ? "" : req.getParameter("txtLancamento");        
        String situacao = (req.getParameter("cmbSituacao") == "" || req.getParameter("cmbSituacao") == null) ? "" : req.getParameter("cmbSituacao").toUpperCase();        
      
        Movimento movimento = (Movimento)sessao.getAttribute("venda");
        if (movimento == null)
            movimento = new Movimento();
        
        movimento.setNumero(numero);
        movimento.setDataLancamento(dtLancamento);
        movimento.setSituacao(situacao);
        movimento.setTipo(tipo);        
        
        
        
        sessao.setAttribute("movimento",movimento);
        sessao.setAttribute("lstColaborador",lstColaborador);
        sessao.setAttribute("pagRetorno","FabricaGelo.Movimento.AcaoAbreMovimento");
        
        return "visao/listarColaborador.jsp";
    }
}
