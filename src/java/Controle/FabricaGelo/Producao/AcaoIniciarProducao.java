/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Producao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProducaoDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoIniciarProducao extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Producao.AcaoListarProducao";
        
        // campos de producao
        String dataProducao = (req.getParameter("txtData").equals("") || req.getParameter("txtData") == null) ? dataAtual() : req.getParameter("txtData");
        String turno = (req.getParameter("cmbTurno").equals("") || req.getParameter("cmbTurno") == null) ? "0" : req.getParameter("cmbTurno");
        String idProduto = (req.getParameter("cmbProduto").equals("") || req.getParameter("cmbProduto") == null) ? "0" : req.getParameter("cmbProduto");
        
        
        Producao producao = new Producao();
        producao.setDataFormatada(dataProducao);
        producao.setTurno(turno);
        producao.setIdProduto(Integer.parseInt(idProduto));
        producao.setSituacao("PI"); 
        
        
        if (producao.getIdProduto() == 0)
        {
            sessao.setAttribute("avisoErro", "Produto não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoNovaProducao");
            pagRetorno = "visao/erro.jsp";            
            
        }
        else if (producao.getTurno().equals("0"))
        {
            sessao.setAttribute("avisoErro", "Turno não definido");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoNovaProducao");
            pagRetorno = "visao/erro.jsp";            
            
        }
        else
        {
        
            // procurando produções abertas para o produto específico
            boolean bolEmProducao = false;
            List<Producao> lstProducao = new ArrayList<Producao>();
            ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
            
            
            Iterator itProducao = producaoDAO.listaTodos(producao).iterator();
            while (itProducao.hasNext())
            {
                Producao _producao = (Producao)itProducao.next();
                if (_producao.getIdProduto() == Integer.parseInt(idProduto)) 
                    bolEmProducao = true;
            }
            if (bolEmProducao)
            {
                sessao.setAttribute("avisoErro", "Produção iniciada e não concluída");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoListarProducao");
                pagRetorno = "visao/erro.jsp";            
                
            }
            else
            {
                if (producaoDAO.atualizar(producao))
                {
                    sessao.setAttribute("producao", null);
                    
                    sessao.setAttribute("avisoErro", "Produção iniciada.");
                    sessao.setAttribute("tipoAviso","alert alert-success");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoListarProducao");
                    pagRetorno = "visao/erro.jsp";            
                    
                }
            }
        }
        
        return pagRetorno;
        
    }
}
