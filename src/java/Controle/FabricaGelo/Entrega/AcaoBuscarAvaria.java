/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Avaria;
import Bean.Entrega;
import Bean.ProdutoEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaDAO;
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
public class AcaoBuscarAvaria extends Acao{ 
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "";
        
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        ProdutoEntrega produtoEntrega = (ProdutoEntrega)sessao.getAttribute("produtoEntrega");
        if (produtoEntrega != null)
        {
            List<Avaria> lstAvaria = new ArrayList<Avaria>();
            AvariaDAO avariaDAO = new AvariaDAO(conexao);
            lstAvaria = avariaDAO.listaTodos();

            sessao.setAttribute("entrega", entrega);
            sessao.setAttribute("produtoEntrega", produtoEntrega);
            sessao.setAttribute("lstAvaria",lstAvaria);
            sessao.setAttribute("pagRetorno","FabricaGelo.Entrega.AcaoAbreEntrega");  
            pagRetorno = "visao/listarAvaria.jsp"; 
        }
        else
        {
            sessao.setAttribute("avisoErro", "Produto não selecionado!");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoAbreEntrega");
            pagRetorno = "visao/erro.jsp";              
        }
  
        
        return pagRetorno;
        
    }
}
