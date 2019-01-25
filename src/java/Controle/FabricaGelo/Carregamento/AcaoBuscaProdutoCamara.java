/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Carregamento;

import Bean.ProdutoCamara;
import Bean.SaidaCamara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProdutoCamaraDAO;
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
public class AcaoBuscaProdutoCamara extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Carregamento.AcaoAbreCarregamento";
        
        String dtSaidaCamara = (req.getParameter("txtDataCarregamento").equals("") || req.getParameter("txtDataCarregamento") == null) ? "1900-01-01" : req.getParameter("txtDataCarregamento");
        String idVeiculo = (req.getParameter("cmbVeiculo").equals("") || req.getParameter("cmbVeiculo") == null) ? "0" : req.getParameter("cmbVeiculo");
        String idProduto = (req.getParameter("cmbProduto").equals("") || req.getParameter("cmbProduto") == null) ? "0" : req.getParameter("cmbProduto");
        
        SaidaCamara saidaCamara = (SaidaCamara)sessao.getAttribute("saidaCamara");
        if (saidaCamara == null)
            saidaCamara = new SaidaCamara();
        saidaCamara.setDataFormatada(dtSaidaCamara);
        saidaCamara.setIdEquipamento(Integer.parseInt(idVeiculo));
        saidaCamara.setSituacao("CC"); // setando saída como cadastrada
        
        List<ProdutoCamara> lstProdutoCamara = new ArrayList<ProdutoCamara>();
        ProdutoCamaraDAO produtoCamaraDAO = new ProdutoCamaraDAO(conexao);
        ProdutoCamara produtoCamara = new ProdutoCamara();
        produtoCamara.setIdEquipamento(0);
        produtoCamara.setIdProduto(Integer.parseInt(idProduto));
        
        lstProdutoCamara = produtoCamaraDAO.listaTodos(produtoCamara);
        
        sessao.setAttribute("saidaCamara", saidaCamara);
        sessao.setAttribute("pIdProduto", idProduto);
        sessao.setAttribute("lstProdutoCamara", lstProdutoCamara);
        
        return pagRetorno;
        
    }
}
