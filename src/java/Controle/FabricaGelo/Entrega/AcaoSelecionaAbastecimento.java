/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Abastecimento;
import Bean.Entrega;
import Bean.ProdutoEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AbastecimentoDAO;
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
public class AcaoSelecionaAbastecimento extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO(conexao);
        Abastecimento abastecimento = new Abastecimento();
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        entrega = entregaDAO.listaUm(entrega);
        
        String idAbastecimento = req.getParameter("idAbastecimento");
        abastecimento.setIdAbastecimento(Integer.parseInt(idAbastecimento));
        abastecimento = abastecimentoDAO.listaUm(abastecimento);
        
        sessao.setAttribute("entrega",entrega);
        sessao.setAttribute("lstProdutoEntrega",entrega.getLstProdutoEntrega());
        sessao.setAttribute("lstCustoEntrega",entrega.getLstCustoEntrega());
        sessao.setAttribute("lstAbastecimento",entrega.getLstAbastecimento());
        sessao.setAttribute("custoEntrega",null);
        sessao.setAttribute("avariaEntrega",null);
        sessao.setAttribute("produtoEntrega",null);
        sessao.setAttribute("abastecimento",abastecimento);
        
        return "visao/entrega.jsp";
    }
}
