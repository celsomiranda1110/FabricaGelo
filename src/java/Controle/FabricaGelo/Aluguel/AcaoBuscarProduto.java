/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Aluguel;

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
        
        // Dados do aluguel
        String numero = (req.getParameter("txtNumero").equals("") || req.getParameter("txtNumero") == null) ? "" : req.getParameter("txtNumero");
        String dtDevolucao = (req.getParameter("txtDevolucao").equals("") || req.getParameter("txtDevolucao") == null) ? "" : req.getParameter("txtDevolucao");
        String dtLancamento = (req.getParameter("txtLancamento").equals("") || req.getParameter("txtLancamento") == null) ? "" : req.getParameter("txtLancamento");        
        String situacaoAluguel = (req.getParameter("cmbSituacao").equals("") || req.getParameter("cmbSituacao") == null) ? "" : req.getParameter("cmbSituacao").toUpperCase();              
      
        Movimento aluguel = (Movimento)sessao.getAttribute("aluguel");
        if (aluguel == null)
            aluguel = new Movimento();
        
        aluguel.setNumero(numero);
        aluguel.setNotaFiscal(numero);
        aluguel.setDataLancamento(dtLancamento);
        aluguel.setDataEntrega(dtDevolucao);
        aluguel.setSituacao(situacaoAluguel);
        aluguel.setTipo("AL");        


        lstProduto = produtoDAO.listaTodos();

        sessao.setAttribute("aluguel",aluguel);
        sessao.setAttribute("lstProduto",lstProduto);
        sessao.setAttribute("pagRetorno","FabricaGelo.Aluguel.AcaoAbreAluguel");
        
        return "visao/listarProduto.jsp"; 
    }
    
}
