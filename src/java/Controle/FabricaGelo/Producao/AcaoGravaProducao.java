/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Avaria;
import Bean.AvariaProducao;
import Bean.Maquina;
import Bean.MaquinaProducao;
import Bean.Producao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaDAO;
import DAO.ProducaoDAO;
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
public class AcaoGravaProducao extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "";
        
        ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
        Producao producao = (Producao)sessao.getAttribute("producao");
        MaquinaProducao maquinaProducao = (MaquinaProducao)sessao.getAttribute("maquinaProducao");
        AvariaProducao avariaProducao = (AvariaProducao)sessao.getAttribute("avariaProducao");
        List<MaquinaProducao> lstMaquinaProducao = new ArrayList<MaquinaProducao>();
        List<AvariaProducao> lstAvariaProducao = new ArrayList<AvariaProducao>();
        
        
        // campos de producao
        String dataProducao = (req.getParameter("txtData").equals("") || req.getParameter("txtData") == null) ? dataAtual() : req.getParameter("txtData");
        String turno = (req.getParameter("cmbTurno").equals("") || req.getParameter("cmbTurno") == null) ? "0" : req.getParameter("cmbTurno");
        String quantidade = (req.getParameter("txtQuantidade").equals("") || req.getParameter("txtQuantidade") == null) ? "0" : req.getParameter("txtQuantidade");
        producao.setDataFormatada(dataProducao);
        producao.setTurno(turno);
        producao.setQuantidade(Double.parseDouble(quantidade));
        
        // campos de maquina da producao
        if (maquinaProducao != null)
        {
            String hrInicial = (req.getParameter("txtHrInicial").equals("") || req.getParameter("txtHrInicial") == null) ? "00:00" : req.getParameter("txtHrInicial");
            String hrFinal = (req.getParameter("txtHrFinal").equals("") || req.getParameter("txtHrFinal") == null) ? "00:00" : req.getParameter("txtHrFinal");            
            String quantidadeAnterior = (req.getParameter("txtSlAnterior").equals("") || req.getParameter("txtSlAnterior") == null) ? "0" : req.getParameter("txtSlAnterior");
            String qtReposicao = (req.getParameter("txtReposicao").equals("") || req.getParameter("txtReposicao") == null) ? "0" : req.getParameter("txtReposicao");
            String qtProducao = (req.getParameter("txtProducao").equals("") || req.getParameter("txtProducao") == null) ? "0" : req.getParameter("txtProducao");
            maquinaProducao.setHrInicial(hrInicial);
            maquinaProducao.setHrFinal(hrFinal);
            maquinaProducao.setQtSaldoAnterior(Double.parseDouble(quantidadeAnterior));
            maquinaProducao.setQtReposicao(Double.parseDouble(qtReposicao));
            maquinaProducao.setQtProducao(Double.parseDouble(qtProducao));
            
            // objeto avaria produção
            if (avariaProducao != null)
            {
                String qtAvariada = (req.getParameter("txtQuantidadeAvaria").equals("") || req.getParameter("txtQuantidadeAvaria") == null) ? "0" : req.getParameter("txtQuantidadeAvaria");
                avariaProducao.setQuantidade(Double.parseDouble(qtAvariada));
                lstAvariaProducao.add(avariaProducao);
                maquinaProducao.setLstAvariaProducao(lstAvariaProducao);
            }
            
            lstMaquinaProducao.add(maquinaProducao);
            producao.setLstMaquinaProducao(lstMaquinaProducao);
        }
        
    
        
        if (producaoDAO.atualizar(producao))
        {
            producao = producaoDAO.listaUm(producao);
            sessao.setAttribute("producao", producao);
            pagRetorno = "FabricaGelo.Producao.AcaoAbreProducao";
        }
        else
            pagRetorno = "FabricaGelo.Producao.AcaoListarProducao";
    
        
        return pagRetorno;
    }
}


