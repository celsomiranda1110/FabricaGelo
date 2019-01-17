/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Compra;

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
        
        String mensagemErro = "";
        String pagRetorno = "";        
        
        Profissional usuario = (Profissional)sessao.getAttribute("usuario");
     
        // Dados do compra
        String numero = (req.getParameter("txtNumero") == "" || req.getParameter("txtNumero") == null) ? "" : req.getParameter("txtNumero");
        String notaFiscal = (req.getParameter("txtNotaFiscal").equals("") || req.getParameter("txtNotaFiscal") == null) ? "" : req.getParameter("txtNotaFiscal");        
        String dtLancamento = (req.getParameter("txtLancamento") == "" || req.getParameter("txtLancamento") == null) ? "" : req.getParameter("txtLancamento");        
        String situacao = (req.getParameter("cmbSituacao") == "" || req.getParameter("cmbSituacao") == null) ? "" : req.getParameter("cmbSituacao").toUpperCase();        
      
        Movimento compra = (Movimento)sessao.getAttribute("compra");
        if (compra == null)
            compra = new Movimento();
        
        compra.setNumero(numero);
        compra.setNotaFiscal(notaFiscal);
        compra.setDataLancamento(dtLancamento);
        compra.setSituacao(situacao);
        compra.setTipo("CO");
        
        // Produto ou Embalagem
        String tipoProduto = (req.getParameter("cmbTipoProduto") == "" || req.getParameter("cmbTipoProduto") == null) ? "" : req.getParameter("cmbTipoProduto");
//        if (tipoProduto.equals(""))
//        {
//            sessao.setAttribute("avisoErro", "Necessário identificar tipo!");
//            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Compra.AcaoAbreCompra");
//            pagRetorno = "visao/erro.jsp";             
//        }
        List<Produto> lstProduto = new ArrayList<Produto>();
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        Produto produto = new Produto();
        produto.setTipo(tipoProduto);
        lstProduto = produtoDAO.listaTodos(produto);

        sessao.setAttribute("compra",compra);
        sessao.setAttribute("lstProduto",lstProduto);
        sessao.setAttribute("pagRetorno","FabricaGelo.Compra.AcaoAbreCompra");
        
        return "visao/listarProduto.jsp"; 
    }
    
}
