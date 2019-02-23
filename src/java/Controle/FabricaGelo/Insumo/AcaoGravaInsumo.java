/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Insumo;

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
public class AcaoGravaInsumo extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "";
        
        ProdutoDAO insumoDAO = new ProdutoDAO(conexao);
        Produto insumo = (Produto)sessao.getAttribute("insumo");
        
        // dados do produto
        String descricao = (req.getParameter("txtInsumo").equals("") || req.getParameter("txtInsumo") == null) ? "" : req.getParameter("txtInsumo").toUpperCase();
        String inativo = (req.getParameter("ck_Ativo") == null ? "A" : "I");
        
        if (insumo == null)
            insumo = new Produto();
        
        insumo.setDescricao(descricao);
        insumo.setVlUnitario(0);
        insumo.setTipo("IN");
        insumo.setAtivo(inativo);
        
        
        if (insumoDAO.atualizar(insumo))
        {
            insumo.setIdProduto(insumoDAO.getIdentity());
            insumo = insumoDAO.listaUm(insumo);
            sessao.setAttribute("insumo", insumo);
            
            
            sessao.setAttribute("avisoErro", "Insumo atualizado");
            sessao.setAttribute("tipoAviso","alert alert-success");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Insumo.AcaoAbreInsumo");
            pagRetorno = "visao/erro.jsp";                    
        }
        
        return pagRetorno;
        
    }
    
}
