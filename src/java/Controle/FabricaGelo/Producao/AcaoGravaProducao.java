/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Avaria;
import Bean.AvariaProducao;
import Bean.Maquina;
import Bean.Producao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaDAO;
import DAO.ProducaoDAO;
import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoGravaProducao extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
        Producao producao = (Producao)sessao.getAttribute("producao");
        List<AvariaProducao> lstAvariaProducao = producao.getLstAvariaProducao();
        AvariaProducao avariaProducao = (AvariaProducao)sessao.getAttribute("avariaProducao");

        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        
        // campos de producao
        String dataProducao = (req.getParameter("txtData").equals("") || req.getParameter("txtData") == null) ? dataAtual() : req.getParameter("txtData");
        String turno = (req.getParameter("cmbTurno").equals("") || req.getParameter("cmbTurno") == null) ? "0" : req.getParameter("cmbTurno");
        String quantidade = (req.getParameter("txtQuantidade").equals("") || req.getParameter("txtQuantidade") == null) ? "0" : req.getParameter("txtQuantidade");
        String quantidadeAnterior = (req.getParameter("txtQuantidadeAnterior").equals("") || req.getParameter("txtQuantidadeAnterior") == null) ? "0" : req.getParameter("txtQuantidadeAnterior");
        String qtAvariaP = (req.getParameter("txtAvaria").equals("") || req.getParameter("txtAvaria") == null) ? "0" : req.getParameter("txtAvaria");
        String sobra = (req.getParameter("txtSobra").equals("") || req.getParameter("txtSobra") == null) ? "0" : req.getParameter("txtSobra");
        String hrInicial = (req.getParameter("txtHrInicial").equals("") || req.getParameter("txtHrInicial") == null) ? "00:00" : req.getParameter("txtHrInicial");
        String hrFinal = (req.getParameter("txtHrFinal").equals("") || req.getParameter("txtHrFinal") == null) ? "00:00" : req.getParameter("txtHrFinal");
        String rendimento = (req.getParameter("txtRendimento").equals("") || req.getParameter("txtRendimento") == null) ? "0" : req.getParameter("txtRendimento");
        
        // campos de avaria
        String qtAvariada = (req.getParameter("txtQuantidadeAvaria").equals("") || req.getParameter("txtQuantidadeAvaria") == null) ? "0" : req.getParameter("txtQuantidadeAvaria");
        
        
        // objeto produção
        if (producao == null)
            producao = new Producao();
        producao.setDataFormatada(dataProducao);
        producao.setQtAvaria(Double.parseDouble(qtAvariaP));
        producao.setQuantidade(Double.parseDouble(quantidade));
        producao.setQuantidadeAnterior(Double.parseDouble(quantidadeAnterior));
        producao.setSobra(Double.parseDouble(sobra));
        producao.setTurno(turno);
        producao.setHoraInicial(hrInicial);
        producao.setHoraFinal(hrFinal);
        producao.setRendimento(Double.parseDouble(rendimento)); 
        
        // objeto avaria producao
        if (avariaProducao != null)
        {
            avariaProducao.setQuantidade(Double.parseDouble(qtAvariada));
            lstAvariaProducao.add(avariaProducao);
            producao.setLstAvariaProducao(lstAvariaProducao);
        }

        
        if (producaoDAO.atualizar(producao))
        
            sessao.setAttribute("producao", producao);
        
        sessao.setAttribute("pagRetorno",pagRetorno);
        
        return "FabricaGelo.Producao.AcaoListarProducao";
    }
}


