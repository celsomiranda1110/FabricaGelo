/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Entrega;
import Bean.ProdutoEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import DAO.ProdutoEntregaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoExcluirProduto extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        String pagRetorno = "FabricaGelo.Entrega.AcaoEncerrarRota";
        
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        ProdutoEntrega produtoEntrega = (ProdutoEntrega)sessao.getAttribute("produtoEntrega"); 
        
        if (entrega == null)
        {
            sessao.setAttribute("avisoErro", "Rota não selecionada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else if (produtoEntrega == null)
        {
            sessao.setAttribute("avisoErro", "Produto não selecionado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }        
//        ProdutoEntregaDAO produtoEntregaDAO = new ProdutoEntregaDAO(conexao);
//        ProdutoEntrega produtoEntrega = new ProdutoEntrega();
//        String idProdutoEntrega = req.getParameter("idProdutoEntrega");
//        produtoEntrega.setIdProdutoEntrega(Integer.parseInt(idProdutoEntrega));
//        produtoEntregaDAO.delete(produtoEntrega);
//        
//        EntregaDAO entregaDAO = new EntregaDAO(conexao);
//        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
//        entrega = entregaDAO.listaUm(entrega);        
        
        sessao.setAttribute("entrega",entrega);
        sessao.setAttribute("lstProdutoEntrega",entrega.getLstProdutoEntrega());
        sessao.setAttribute("lstCustoEntrega",entrega.getLstCustoEntrega());
        sessao.setAttribute("lstAbastecimento",entrega.getLstAbastecimento());
        sessao.setAttribute("custoEntrega",null);
        sessao.setAttribute("avariaEntrega",null);
        sessao.setAttribute("produtoEntrega",null);
        sessao.setAttribute("abastecimento",null);

        return "visao/entrega.jsp";        
    }
}
