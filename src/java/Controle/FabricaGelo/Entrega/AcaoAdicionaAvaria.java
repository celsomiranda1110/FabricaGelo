/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;


import Bean.AvariaEntrega;
import Bean.Entrega;
import Bean.ProdutoEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaEntregaDAO;
import DAO.EntregaDAO;
import DAO.ProdutoEntregaDAO;
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
public class AcaoAdicionaAvaria extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Entrega.AcaoEncerrarRota";
        
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        ProdutoEntrega produtoEntrega = (ProdutoEntrega)sessao.getAttribute("produtoEntrega");
        String idAvaria = (req.getParameter("cmbAvaria").equals("") || req.getParameter("cmbAvaria") == null) ? "0" : req.getParameter("cmbAvaria");
        String qtAvaria = (req.getParameter("txtQtAvariaPE").equals("") || req.getParameter("txtQtAvariaPE") == null) ? "0" : req.getParameter("txtQtAvariaPE");

        if (entrega == null)
        {
            sessao.setAttribute("avisoErro", "Rota não selecionada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else if (produtoEntrega == null)
        {
            sessao.setAttribute("avisoErro", "Produto não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";             
        }
        else if (idAvaria.equals("0"))
        {
            sessao.setAttribute("avisoErro", "Avaria não selecionada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";              
        }
        else if (qtAvaria.equals("0"))
        {
            sessao.setAttribute("avisoErro", "Não há avarias para registrar");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoEncerrarRota");
            pagRetorno = "visao/erro.jsp";              
        }
        else
        {
            AvariaEntregaDAO avariaEntregaDAO = new AvariaEntregaDAO(conexao);
            AvariaEntrega avariaEntrega = new AvariaEntrega();
            avariaEntrega.setIdProdutoEntrega(produtoEntrega.getIdProdutoEntrega());
            avariaEntrega.setIdAvaria(Integer.parseInt(idAvaria));
            avariaEntrega.setQuantidade(Double.parseDouble(qtAvaria));
            if (avariaEntregaDAO.atualizar(avariaEntrega))
            {
                ProdutoEntregaDAO produtoEntregaDAO = new ProdutoEntregaDAO(conexao);
                produtoEntrega = produtoEntregaDAO.listaUm(produtoEntrega);
                produtoEntregaDAO.atualizar(produtoEntrega);                
            }
            
//            List<AvariaEntrega> lstAvariaEntrega = new ArrayList<AvariaEntrega>();
//            AvariaEntregaDAO avariaEntregaDAO = new AvariaEntregaDAO(conexao);
//            AvariaEntrega avariaEntrega = new AvariaEntrega();
//            avariaEntrega.setIdAvaria(Integer.parseInt(idAvaria));
//            avariaEntrega.setIdProdutoEntrega(produtoEntrega.getIdProdutoEntrega());
//            avariaEntrega.setQuantidade(Double.parseDouble(qtAvaria));
//            if(avariaEntregaDAO.atualizar(avariaEntrega))
//            {
//                ProdutoEntregaDAO produtoEntregaDAO = new ProdutoEntregaDAO(conexao);
//                produtoEntrega.setDblQuantidadeAvaria(avariaEntrega.getQuantidade());
//                produtoEntrega.setDblSaldo(produtoEntrega.getDblQuantidade() - avariaEntrega.getQuantidade());
//                produtoEntregaDAO.atualizar(produtoEntrega);                        
//            }
            
        }

        
        return pagRetorno;
        
    }
}
