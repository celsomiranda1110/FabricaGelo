/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Maquina;
import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.Produto;
import Bean.Profissional;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MaquinaDAO;
import DAO.ProdutoDAO;
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
public class AcaoBuscarMaquina extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        Profissional usuario = (Profissional)sessao.getAttribute("usuario");
        
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
        
        
        List<Maquina> lstMaquina = new ArrayList<Maquina>();
        MaquinaDAO maquinaDAO = new MaquinaDAO(conexao);        
        lstMaquina = maquinaDAO.listaTodos();

        sessao.setAttribute("producao",producao);
        sessao.setAttribute("maquinaProducao", maquinaProducao);
        sessao.setAttribute("lstMaquina",lstMaquina);
        sessao.setAttribute("pagRetorno","FabricaGelo.Producao.AcaoAbreProducao");
        
        return "visao/listarMaquina.jsp"; 
    }
    
}
