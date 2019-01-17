/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Entrega;
import Bean.Produto;
import Controle.FabricaGelo.Gerais.Acao;
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
public class AcaoBuscarProduto extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        // campos da entrega
        String dtEntrega = (req.getParameter("txtDtEntrega").equals("") || req.getParameter("txtDtEntrega") == null) ? dataAtual() : req.getParameter("txtDtEntrega");
        String kmInicial = (req.getParameter("txtKmInicial").equals("") || req.getParameter("txtKmInicial") == null) ? "0" : req.getParameter("txtKmInicial");
        String kmFinal = (req.getParameter("txtKmFinal").equals("") || req.getParameter("txtKmFinal") == null) ? "0" : req.getParameter("txtKmFinal");
        String hrSaida = (req.getParameter("txtHrSaida").equals("") || req.getParameter("txtHrSaida") == null) ? "00:00" : req.getParameter("txtHrSaida");
        String hrChegada = (req.getParameter("txtHrChegada").equals("") || req.getParameter("txtHrChegada") == null) ? "00:00" : req.getParameter("txtHrChegada");
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
        entrega.setSituacao(situacao);

        List<Produto> lstProduto = new ArrayList<Produto>();
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        Produto produto = new Produto();
        produto.setTipo("PR");
        lstProduto = produtoDAO.listaTodos(produto);

        sessao.setAttribute("entrega",entrega);
        sessao.setAttribute("lstProduto",lstProduto);
        sessao.setAttribute("pagRetorno","FabricaGelo.Entrega.AcaoAbreEntrega");        
        
        return "visao/listarProduto.jsp"; 
    }
}
