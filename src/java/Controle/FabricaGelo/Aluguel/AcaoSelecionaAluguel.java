/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Aluguel;

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
public class AcaoSelecionaAluguel extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        MovimentoDAO aluguelDAO = new MovimentoDAO(conexao);
        Movimento aluguel = new Movimento();
        
        String idMovimento = req.getParameter("idMovimento");
        aluguel.setIdMovimento(Integer.parseInt(idMovimento));
        
        aluguel = aluguelDAO.listaUm(aluguel);
        
        sessao.setAttribute("aluguel",aluguel);
        sessao.setAttribute("lstProdutoAluguel",aluguel.getLstProdutoMovimento());
        sessao.setAttribute("pagamento", aluguel.getPagamento());
        
        return "visao/aluguel.jsp";
    }
}