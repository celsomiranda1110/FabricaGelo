/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;


import Bean.Entrega;
import Bean.Veiculo;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.VeiculoDAO;
import java.sql.Connection;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoBuscarVeiculo extends Acao{ 
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        
        
        List<Veiculo> lstVeiculo = new ArrayList<Veiculo>();
        VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);
        
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
        
        lstVeiculo = veiculoDAO.listaTodos();
        
        sessao.setAttribute("entrega",entrega);
        sessao.setAttribute("lstVeiculo",lstVeiculo);
        sessao.setAttribute("pagRetorno","FabricaGelo.Entrega.AcaoAbreEntrega");
        
        return "visao/listarVeiculo.jsp";
    }
}

