/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;

import Bean.Colaborador;
import Bean.ColaboradorProduto;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
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
public class AcaoAdicionaProduto extends Acao
{
    
public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        
        String pagRetorno = "FabricaGelo.Colaborador.AcaoAbreColaborador";
        
        List<ColaboradorProduto> lstColaboradorProduto = new ArrayList<ColaboradorProduto>();
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        
        // Dados do produto
        String idProduto = (req.getParameter("cmbProduto").equals("") || req.getParameter("cmbProduto") == null) ? "0" : req.getParameter("cmbProduto"); 
        String icms = (req.getParameter("txtIcms").equals("") || req.getParameter("txtIcms") == null) ? "0" : req.getParameter("txtIcms"); 
        String desconto = (req.getParameter("txtDesconto").equals("") || req.getParameter("txtDesconto") == null) ? "0" : req.getParameter("txtDesconto");        
        String valor = (req.getParameter("txtValor").equals("") || req.getParameter("txtValor") == null) ? "0" : req.getParameter("txtValor");        
        
        
        if (colaborador == null)
        {
            sessao.setAttribute("avisoErro", "Empresa não identificada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Colaborador.AcaoAbreColaborador");
            pagRetorno = "visao/erro.jsp";                 
        }
        else if (idProduto.equals("0"))
        {
            sessao.setAttribute("avisoErro", "Produto não identificado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Colaborador.AcaoAbreColaborador");
            pagRetorno = "visao/erro.jsp";                 
        }
        else
        {
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
            
            ColaboradorProduto colaboradorProduto = (ColaboradorProduto)sessao.getAttribute("colaboradorProduto");
            if (colaboradorProduto == null)
               colaboradorProduto = new ColaboradorProduto();

            colaboradorProduto.setIdProduto(Integer.parseInt(idProduto));
            colaboradorProduto.setIcms(Double.parseDouble(icms));
            colaboradorProduto.setDesconto(Double.parseDouble(desconto));
            colaboradorProduto.setValor(Double.parseDouble(valor));
            lstColaboradorProduto.add(colaboradorProduto);
            
            colaborador.setLstColaboradorProduto(lstColaboradorProduto);
            
            if (colaboradorDAO.atualizar(colaborador))
            {
                colaborador = colaboradorDAO.listaUm(colaborador);

                sessao.setAttribute("colaborador", colaborador);  
                sessao.setAttribute("colaboradorProduto", null);

                sessao.setAttribute("avisoErro", "Empresa atualizada");
                sessao.setAttribute("tipoAviso","alert alert-success");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Colaborador.AcaoAbreColaborador");
                pagRetorno = "visao/erro.jsp";                             
            }

        }
        
        return pagRetorno;
        
    }
}
