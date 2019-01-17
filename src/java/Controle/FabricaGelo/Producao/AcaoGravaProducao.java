/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Avaria;
import Bean.AvariaProducao;
import Bean.Equipamento;
import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.TransferenciaProducao;
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
        
        
//        MaquinaProducao maquinaProducao = (MaquinaProducao)sessao.getAttribute("maquinaProducao");
//        AvariaProducao avariaProducao = (AvariaProducao)sessao.getAttribute("avariaProducao");
        List<MaquinaProducao> lstMaquinaProducao = new ArrayList<MaquinaProducao>();
        List<AvariaProducao> lstAvariaProducao = new ArrayList<AvariaProducao>();
        List<TransferenciaProducao> lstTransferencia = new ArrayList<TransferenciaProducao>();
        
        
        // campos de producao
        String dataProducao = (req.getParameter("txtData").equals("") || req.getParameter("txtData") == null) ? dataAtual() : req.getParameter("txtData");
        String turno = (req.getParameter("cmbTurno").equals("") || req.getParameter("cmbTurno") == null) ? "0" : req.getParameter("cmbTurno");
        String idProduto = (req.getParameter("cmbProduto").equals("") || req.getParameter("cmbProduto") == null) ? "0" : req.getParameter("cmbProduto");
        String quantidade = (req.getParameter("txtQuantidade").equals("") || req.getParameter("txtQuantidade") == null) ? "0" : req.getParameter("txtQuantidade");

        // campos de maquina da producao
        String idMaquina = (req.getParameter("cmbMaquina").equals("") || req.getParameter("cmbMaquina") == null) ? "0" : req.getParameter("cmbMaquina");
        String idEmbalagem = (req.getParameter("cmbEmbalagem").equals("") || req.getParameter("cmbEmbalagem") == null) ? "0" : req.getParameter("cmbEmbalagem");
        String hrInicial = (req.getParameter("txtHrInicial").equals("") || req.getParameter("txtHrInicial") == null) ? "00:00" : req.getParameter("txtHrInicial");
        String hrFinal = (req.getParameter("txtHrFinal").equals("") || req.getParameter("txtHrFinal") == null) ? "00:00" : req.getParameter("txtHrFinal");            
        String quantidadeAnterior = (req.getParameter("txtSlAnterior").equals("") || req.getParameter("txtSlAnterior") == null) ? "0" : req.getParameter("txtSlAnterior");
        String qtProducao = (req.getParameter("txtProducao").equals("") || req.getParameter("txtProducao") == null) ? "0" : req.getParameter("txtProducao");

        // objeto avaria produção
        String idAvaria = (req.getParameter("cmbAvaria").equals("") || req.getParameter("cmbAvaria") == null) ? "0" : req.getParameter("cmbAvaria");
        String qtAvariada = (req.getParameter("txtQuantidadeAvaria").equals("") || req.getParameter("txtQuantidadeAvaria") == null) ? "0" : req.getParameter("txtQuantidadeAvaria");

        // objeto transferência da produção
        String idProdutoCamara = (req.getParameter("cmbProdutoCamara").equals("") || req.getParameter("cmbProdutoCamara") == null) ? "0" : req.getParameter("cmbProdutoCamara");
        String qtTransferencia = (req.getParameter("txtQuantidadeTransferencia").equals("") || req.getParameter("txtQuantidadeTransferencia") == null) ? "0" : req.getParameter("txtQuantidadeTransferencia");
        

        if (idProduto.equals("0"))
        {
            sessao.setAttribute("avisoErro", "Produto não selecionado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";            
        }
        else if ((producao != null) && (producao.getSituacao().equals("PF")))
        {
            sessao.setAttribute("avisoErro", "Produção já finalizada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";            
        }           
        else
        {
        
            producao.setDataFormatada(dataProducao);
            producao.setTurno(turno);
            producao.setQuantidade(Double.parseDouble(quantidade));
            producao.setIdProduto(Integer.parseInt(idProduto));

           if (!idMaquina.equals("0") && !idEmbalagem.equals("0"))
            {
                MaquinaProducao maquinaProducao = new MaquinaProducao();
                maquinaProducao.setIdMaquinaProducao(Integer.parseInt(idMaquina));
                maquinaProducao.setHrInicial(hrInicial);
                maquinaProducao.setHrFinal(hrFinal);
                maquinaProducao.setQtSaldoAnterior(Double.parseDouble(quantidadeAnterior));
                maquinaProducao.setQtReposicao(0);
                maquinaProducao.setQtProducao(Double.parseDouble(qtProducao));


                if (!idAvaria.equals("0"))
                {
                    AvariaProducao avariaProducao = new AvariaProducao();
                    avariaProducao.setIdAvaria(Integer.parseInt(idAvaria));
                    avariaProducao.setQuantidade(Double.parseDouble(qtAvariada));
                    lstAvariaProducao.add(avariaProducao);
                    maquinaProducao.setLstAvariaProducao(lstAvariaProducao);
                }

                lstMaquinaProducao.add(maquinaProducao);
                producao.setLstMaquinaProducao(lstMaquinaProducao);
            }
            
            if (!idProdutoCamara.equals("0"))
            {
                TransferenciaProducao transferencia = new TransferenciaProducao();
                transferencia.setIdProdutoCamara(Integer.parseInt(idProdutoCamara));
                transferencia.setQuantidade(Double.parseDouble(qtTransferencia));
                lstTransferencia.add(transferencia);
                producao.setLstTransferenciaProducao(lstTransferencia);
            }

            if (producaoDAO.atualizar(producao))
            {
                sessao.setAttribute("maquinaProducao", null); 
                sessao.setAttribute("lstAvariaProducao", null);
                sessao.setAttribute("avariaProducao", null); 
                sessao.setAttribute("transf", null);                 
                sessao.setAttribute("avisoErro", "Produção registrada");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
                pagRetorno = "visao/erro.jsp";               
            }
            else
            {

                pagRetorno = "FabricaGelo.Producao.AcaoListarProducao";
            }
        }
        
        return pagRetorno;
    }
}


