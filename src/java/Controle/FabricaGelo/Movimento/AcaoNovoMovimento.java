/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Movimento;

import Bean.Colaborador;
import Bean.Movimento;
import Bean.ColaboradorEntrega;
import Bean.Produto;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import DAO.ColaboradorEntregaDAO;
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
public class AcaoNovoMovimento extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
//        Movimento movimento = new Movimento();
//        ColaboradorEntregaDAO colaboradorEntregaDAO = new ColaboradorEntregaDAO(conexao);
//        ColaboradorEntrega colaboradorEntrega = (ColaboradorEntrega)sessao.getAttribute("colaboradorEntrega");
//        if (colaboradorEntrega != null)
//        {
//            colaboradorEntrega = colaboradorEntregaDAO.listaUm(colaboradorEntrega);
//            movimento.setColaborador(colaboradorEntrega.getCliente());
//        }
//        else
//            movimento = null;

        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        List<Colaborador> lstColaborador = new ArrayList<Colaborador>();
        lstColaborador = colaboradorDAO.listaTodos();
        
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        Produto produto = new Produto();
        List<Produto> lstProduto = new ArrayList<Produto>();
        produto.setTipo("PR");
        lstProduto = produtoDAO.listaTodos(produto);
        
        sessao.setAttribute("movimento", null);
        sessao.setAttribute("lstColaborador", lstColaborador);
        sessao.setAttribute("lstProduto", lstProduto);
        
        
        return "visao/movimento.jsp";
    }
}
