/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Produto;

import Bean.Produto;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProdutoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoGravaProduto extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        String pagRetorno = "FabricaGelo.Produto.AcaoAbreProduto";
        
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        Produto produto = (Produto)sessao.getAttribute("produto");
        if (produto == null)
            produto = new Produto();
        
        String descricao = (req.getParameter("txtProduto").equals("") || req.getParameter("txtProduto") == null) ? "" : req.getParameter("txtProduto").toUpperCase();
        String vlUnitario = (req.getParameter("txtVlUnitario").equals("") || req.getParameter("txtVlUnitario") == null) ? "0" : req.getParameter("txtVlUnitario");
        String inativo = (req.getParameter("ck_Ativo") == null ? "A" : "I");
        
        produto.setDescricao(descricao);
        produto.setVlUnitario(Double.parseDouble(vlUnitario));
        produto.setTipo("PR");
        produto.setAtivo(inativo);
                
        
        if (produtoDAO.atualizar(produto))
        {
            produto.setIdProduto(produtoDAO.getIdentity());
            produto = produtoDAO.listaUm(produto);
            sessao.setAttribute("produto", produto);
            
            sessao.setAttribute("avisoErro", "Produto atualizado");
            sessao.setAttribute("tipoAviso","alert alert-success");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Produto.AcaoAbreProduto");
            pagRetorno = "visao/erro.jsp";            
        }
        
        return pagRetorno;
        
    }
    
}
