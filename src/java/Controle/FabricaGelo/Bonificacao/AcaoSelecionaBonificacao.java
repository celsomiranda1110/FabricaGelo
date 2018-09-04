/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Bonificacao;

import Bean.Movimento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MovimentoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaBonificacao extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        MovimentoDAO bonificacaoDAO = new MovimentoDAO(conexao);
        Movimento bonificacao = new Movimento();
        
        String idMovimento = req.getParameter("idMovimento");
        bonificacao.setIdMovimento(Integer.parseInt(idMovimento));
        
        bonificacao = bonificacaoDAO.listaUm(bonificacao);
        
        sessao.setAttribute("bonificacao",bonificacao);
        sessao.setAttribute("lstProdutoBonificacao",bonificacao.getLstProdutoMovimento());
        sessao.setAttribute("pagamento", bonificacao.getPagamento());
        
        return "visao/bonificacao.jsp";
    }
}
