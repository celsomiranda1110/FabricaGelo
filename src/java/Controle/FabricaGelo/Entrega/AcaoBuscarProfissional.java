/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;


import Bean.Entrega;
import Bean.Movimento;
import Bean.Profissional;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProfissionalDAO;
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
public class AcaoBuscarProfissional extends Acao{ 
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        Movimento movimento = (Movimento)sessao.getAttribute("movimento");
        
        List<Profissional> lstProfissional = new ArrayList<Profissional>();
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
        
        // campos da entrega
        String dtEntrega = (req.getParameter("txtDtEntrega").equals("") || req.getParameter("txtDtEntrega") == null) ? dataAtual() : req.getParameter("txtDtEntrega");
        String kmInicial = (req.getParameter("txtKmInicial").equals("") || req.getParameter("txtKmInicial") == null) ? "0" : req.getParameter("txtKmInicial");
        String kmFinal = (req.getParameter("txtKmFinal").equals("") || req.getParameter("txtKmFinal") == null) ? "0" : req.getParameter("txtKmFinal");
        String hrSaida = (req.getParameter("txtHrSaida").equals("") || req.getParameter("txtHrSaida") == null) ? "00:00" : req.getParameter("txtHrSaida");
        String hrChegada = (req.getParameter("txtHrChegada").equals("") || req.getParameter("txtHrChegada") == null) ? "00:00" : req.getParameter("txtHrChegada");
        String qtLitros = (req.getParameter("txtLitros").equals("") || req.getParameter("txtLitros") == null) ? "0" : req.getParameter("txtLitros");
        String situacao = (req.getParameter("cmbSituacao").equals("") || req.getParameter("cmbSituacao") == null) ? "EN" : req.getParameter("cmbSituacao");
      
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        if (entrega == null)
            entrega = new Entrega();
        
        //montagem do objeto Entrega
        entrega.setDataFormatada(dtEntrega);
        entrega.setKmInicial(Double.parseDouble(kmInicial));
        entrega.setKmFinal((Double.parseDouble(kmFinal)));
        entrega.setHrSaida(hrSaida);
        entrega.setHrChegada(hrChegada);
        entrega.setLitros(Double.parseDouble(qtLitros));
        entrega.setSituacao(situacao);
        //entrega.setLstCustoEntrega(lstCustoEntrega);        
        
        lstProfissional = profissionalDAO.listaTodos();
        
        sessao.setAttribute("movimento", movimento);
        sessao.setAttribute("entrega",entrega);
        sessao.setAttribute("lstProfissional",lstProfissional);
        sessao.setAttribute("pagRetorno","FabricaGelo.Entrega.AcaoAbreEntrega");
        
        return "visao/listarProfissional.jsp";
    }
}
