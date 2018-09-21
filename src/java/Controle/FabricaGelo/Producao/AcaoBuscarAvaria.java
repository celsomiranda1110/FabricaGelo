/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Avaria;
import Bean.AvariaProducao;
import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.Profissional;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaDAO;
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
public class AcaoBuscarAvaria extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        // objeto produção
        String dataProducao = (req.getParameter("txtData").equals("") || req.getParameter("txtData") == null) ? dataAtual() : req.getParameter("txtData");
        String turno = (req.getParameter("cmbTurno").equals("") || req.getParameter("cmbTurno") == null) ? "" : req.getParameter("cmbTurno");
        String quantidade = (req.getParameter("txtQuantidade").equals("") || req.getParameter("txtQuantidade") == null) ? "0" : req.getParameter("txtQuantidade");                
        Producao producao = (Producao)sessao.getAttribute("producao");
        if (producao == null)
            producao = new Producao();
        producao.setDataFormatada(dataProducao);
        producao.setTurno(turno);
        producao.setQuantidade(Double.parseDouble(quantidade));
        
        // maquina produção
        String hrInicial = (req.getParameter("txtHrInicial").equals("") || req.getParameter("txtHrInicial") == null) ? "00:00" : req.getParameter("txtHrInicial");
        String hrFinal = (req.getParameter("txtHrFinal").equals("") || req.getParameter("txtHrFinal") == null) ? "00:00" : req.getParameter("txtHrFinal");            
        String quantidadeAnterior = (req.getParameter("txtSlAnterior").equals("") || req.getParameter("txtSlAnterior") == null) ? "0" : req.getParameter("txtSlAnterior");
        String qtReposicao = (req.getParameter("txtReposicao").equals("") || req.getParameter("txtReposicao") == null) ? "0" : req.getParameter("txtReposicao");
        String qtProducao = (req.getParameter("txtProducao").equals("") || req.getParameter("txtProducao") == null) ? "0" : req.getParameter("txtProducao");
        MaquinaProducao maquinaProducao = (MaquinaProducao)sessao.getAttribute("maquinaProducao");
        if (maquinaProducao == null)
            maquinaProducao = new MaquinaProducao();
        maquinaProducao.setHrInicial(hrInicial);
        maquinaProducao.setHrFinal(hrFinal);
        maquinaProducao.setQtSaldoAnterior(Double.parseDouble(quantidadeAnterior));
        maquinaProducao.setQtReposicao(Double.parseDouble(qtReposicao));
        maquinaProducao.setQtProducao(Double.parseDouble(qtProducao));
        
        // avaria produção
        String qtAvaria = (req.getParameter("txtQuantidadeAvaria").equals("") || req.getParameter("txtQuantidadeAvaria") == null) ? "0" : req.getParameter("txtQuantidadeAvaria");
        AvariaProducao avariaProducao = (AvariaProducao)sessao.getAttribute("avariaProducao");
        if (avariaProducao == null)
            avariaProducao = new AvariaProducao();
        avariaProducao.setQuantidade(Double.parseDouble(qtAvaria));
        
        List<Avaria> lstAvaria = new ArrayList<Avaria>();
        AvariaDAO avariaDAO = new AvariaDAO(conexao);
        lstAvaria = avariaDAO.listaTodos();

        sessao.setAttribute("producao",producao);
        sessao.setAttribute("maquinaProducao",maquinaProducao);
        sessao.setAttribute("avariaProducao",avariaProducao);
        sessao.setAttribute("lstAvaria",lstAvaria);
        sessao.setAttribute("pagRetorno","FabricaGelo.Producao.AcaoAbreProducao");
        
        return "visao/listarAvaria.jsp"; 
    }
    
}

