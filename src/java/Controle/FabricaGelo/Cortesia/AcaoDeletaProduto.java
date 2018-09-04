/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Cortesia;

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
        
        MovimentoDAO cortesiaDAO = new MovimentoDAO(conexao);
        Movimento cortesia = (Movimento)sessao.getAttribute("cortesia");

        if ((cortesia != null) && (cortesia.getIdMovimento() == 0))
        {
            sessao.setAttribute("avisoErro", "Cortesia deve ser atualizada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Cortesia.AcaoAbreCortesia");
            pagRetorno = "visao/erro.jsp";            
        }
        else if (!cortesia.getSituacao().equals("FE"))
        {        
        
        
            ProdutoMovimentoDAO cortesiaProdutoDAO = new ProdutoMovimentoDAO(conexao);

            ProdutoMovimento cortesiaProduto = (ProdutoMovimento)sessao.getAttribute("cortesiaProduto");
            if (cortesiaProduto == null)
                cortesiaProduto = new ProdutoMovimento();

            String idProdutoMovimento = req.getParameter("idProdutoMovimento");
            cortesiaProduto.setIdProdutoMovimento(Integer.parseInt(idProdutoMovimento));
            if (cortesiaProdutoDAO.delete(cortesiaProduto))
                cortesia = cortesiaDAO.listaUm(cortesia);

            sessao.setAttribute("cortesia",cortesia);
            sessao.setAttribute("lstProdutoMovimento",cortesia.getLstProdutoMovimento());
            sessao.setAttribute("cortesiaProduto",null);
            pagRetorno = "FabricaGelo.Cortesia.AcaoAbreCortesia";
            
        }  
        else
        {
            sessao.setAttribute("avisoErro", "Movimento de cortesia Fechado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Cortesia.AcaoAbreCortesia");
            pagRetorno = "visao/erro.jsp";            
        }
        
        return pagRetorno;        
    }
}


