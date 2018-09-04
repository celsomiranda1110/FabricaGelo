/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Bonificacao;

import Bean.Movimento;
import Bean.ProdutoMovimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MovimentoDAO;
import DAO.ProdutoMovimentoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoDeletaProduto extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        String pagRetorno = "";
        
        MovimentoDAO bonificacaoDAO = new MovimentoDAO(conexao);
        Movimento bonificacao = (Movimento)sessao.getAttribute("bonificacao");

        if ((bonificacao != null) && (bonificacao.getIdMovimento() == 0))
        {
            sessao.setAttribute("avisoErro", "Bonificacao deve ser atualizada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Bonificacao.AcaoAbreBonificacao");
            pagRetorno = "visao/erro.jsp";            
        }
        else if (!bonificacao.getSituacao().equals("FE"))
        {        
        
        
            ProdutoMovimentoDAO bonificacaoProdutoDAO = new ProdutoMovimentoDAO(conexao);

            ProdutoMovimento bonificacaoProduto = (ProdutoMovimento)sessao.getAttribute("bonificacaoProduto");
            if (bonificacaoProduto == null)
                bonificacaoProduto = new ProdutoMovimento();

            String idProdutoMovimento = req.getParameter("idProdutoMovimento");
            bonificacaoProduto.setIdProdutoMovimento(Integer.parseInt(idProdutoMovimento));
            if (bonificacaoProdutoDAO.delete(bonificacaoProduto))
                bonificacao = bonificacaoDAO.listaUm(bonificacao);

            sessao.setAttribute("bonificacao",bonificacao);
            sessao.setAttribute("lstProdutoMovimento",bonificacao.getLstProdutoMovimento());
            sessao.setAttribute("bonificacaoProduto",null);
            pagRetorno = "FabricaGelo.Bonificacao.AcaoAbreBonificacao";
            
        }  
        else
        {
            sessao.setAttribute("avisoErro", "Movimento de bonificacao Fechado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Bonificacao.AcaoAbreBonificacao");
            pagRetorno = "visao/erro.jsp";            
        }
        
        return pagRetorno;        
    }
}


