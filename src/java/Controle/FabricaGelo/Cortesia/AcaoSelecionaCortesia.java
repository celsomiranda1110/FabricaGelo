/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Cortesia;

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
public class AcaoSelecionaCortesia extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        MovimentoDAO cortesiaDAO = new MovimentoDAO(conexao);
        Movimento cortesia = new Movimento();
        
        String idMovimento = req.getParameter("idMovimento");
        cortesia.setIdMovimento(Integer.parseInt(idMovimento));
        
        cortesia = cortesiaDAO.listaUm(cortesia);
        
        sessao.setAttribute("cortesia",cortesia);
        sessao.setAttribute("lstProdutoCortesia",cortesia.getLstProdutoMovimento());
        sessao.setAttribute("pagamento", cortesia.getPagamento());
        
        return "visao/cortesia.jsp";
    }
}

