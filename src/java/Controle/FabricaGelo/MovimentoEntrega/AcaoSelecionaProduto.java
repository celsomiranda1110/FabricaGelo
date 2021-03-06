/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.MovimentoEntrega;

import Bean.ColaboradorEntrega;
import Bean.Movimento;
import Bean.MovimentoEntrega;
import Bean.ProdutoMovimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProdutoMovimentoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaProduto extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.MovimentoEntrega.AcaoAbreMovimentoEntrega";

        ColaboradorEntrega colaboradorEntrega = (ColaboradorEntrega)sessao.getAttribute("colaboradorEntrega");        
        MovimentoEntrega movimentoEntrega = (MovimentoEntrega)sessao.getAttribute("movimentoEntrega");
        Movimento movimento = (Movimento)sessao.getAttribute("movimento");
        
        ProdutoMovimentoDAO produtoMovimentoDAO = new ProdutoMovimentoDAO(conexao);
        ProdutoMovimento produtoMovimento = new ProdutoMovimento();
        
        String idProdutoEntrega = req.getParameter("idProdutoMovimento");
        produtoMovimento.setIdProdutoMovimento(Integer.parseInt(idProdutoEntrega));
        produtoMovimento = produtoMovimentoDAO.listaUm(produtoMovimento);
        
        sessao.setAttribute("produtoMovimento", produtoMovimento);
        
        
        return pagRetorno;
    }
}
