/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Cortesia;

import Bean.Movimento;
import Bean.Produto;
import Bean.Profissional;
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
        
        Profissional usuario = (Profissional)sessao.getAttribute("usuario");
        
        List<Produto> lstProduto = new ArrayList<Produto>();
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        
        // Dados do cortesia
        String numero = (req.getParameter("txtNumero") == "" || req.getParameter("txtNumero") == null) ? "" : req.getParameter("txtNumero");
        String notaFiscal = (req.getParameter("txtNotaFiscal").equals("") || req.getParameter("txtNotaFiscal") == null) ? "" : req.getParameter("txtNotaFiscal");        
        String dtLancamento = (req.getParameter("txtLancamento") == "" || req.getParameter("txtLancamento") == null) ? "" : req.getParameter("txtLancamento");        
        String situacao = (req.getParameter("cmbSituacao") == "" || req.getParameter("cmbSituacao") == null) ? "" : req.getParameter("cmbSituacao").toUpperCase();        
      
        Movimento cortesia = (Movimento)sessao.getAttribute("cortesia");
        if (cortesia == null)
            cortesia = new Movimento();
        
        
        cortesia.setNumero(numero);
        cortesia.setNotaFiscal(notaFiscal);
        cortesia.setDataLancamento(dtLancamento);
        cortesia.setSituacao(situacao);
        cortesia.setTipo("CO");

        lstProduto = produtoDAO.listaTodos();

        sessao.setAttribute("cortesia",cortesia);
        sessao.setAttribute("lstProduto",lstProduto);
        sessao.setAttribute("pagRetorno","FabricaGelo.Cortesia.AcaoAbreCortesia");
        
        return "visao/listarProduto.jsp"; 
    }
    
}
