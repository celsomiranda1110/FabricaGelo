/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;

import Bean.Colaborador;

import Bean.ColaboradorProduto;
import Bean.Produto;


import DAO.FuncaoDAO;
import Controle.FabricaGelo.Gerais.Acao;
import java.sql.Connection;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author MIRANDA
 */
public class AcaoAbreColaborador extends Acao
{
    
public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        if (pagRetorno != null)
        {
            if (pagRetorno.equals("FabricaGelo.Colaborador.AcaoAbreColaborador"))
            {
                ColaboradorProduto colaboradorProduto = (ColaboradorProduto)sessao.getAttribute("colaboradorProduto");
                if (colaboradorProduto == null)
                    colaboradorProduto = new ColaboradorProduto();
                Produto produto = (Produto)sessao.getAttribute("produto");
                
                if (produto != null)
                    colaboradorProduto.setProduto(produto);
                
                sessao.setAttribute("colaboradorProduto", colaboradorProduto);
                
            }
        }
        
        // Lista de funcionários da empresa
        sessao.setAttribute("colaborador",colaborador);
        if (colaborador != null)
        {
            sessao.setAttribute("lstColaboradorProduto",colaborador.getLstColaboradorProduto());
            sessao.setAttribute("lstVisitaColaborador",colaborador.getLstVisitaColaborador());
        }
        else
            sessao.setAttribute("lstColaboradorProduto",null);
            

        return "visao/colaborador.jsp";
        
    }    
}
