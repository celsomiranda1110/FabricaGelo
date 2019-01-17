/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Movimento;

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
        
        MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
        Movimento movimento = (Movimento)sessao.getAttribute("movimento");

        if ((movimento != null) && (movimento.getIdMovimento() == 0))
        {
            sessao.setAttribute("avisoErro", "Movimento deve ser atualizada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Movimento.AcaoAbreMovimento");
            pagRetorno = "visao/erro.jsp";            
        }
        else if (!movimento.getSituacao().equals("FE"))
        {        

            ProdutoMovimentoDAO movimentoProdutoDAO = new ProdutoMovimentoDAO(conexao);

            ProdutoMovimento movimentoProduto = movimentoProduto = new ProdutoMovimento();
            String idProdutoMovimento = req.getParameter("idProdutoMovimento");
            movimentoProduto.setIdProdutoMovimento(Integer.parseInt(idProdutoMovimento));
            if (movimentoProdutoDAO.delete(movimentoProduto))
                movimento = movimentoDAO.listaUm(movimento);

            sessao.setAttribute("movimento",movimento);
            sessao.setAttribute("lstProdutoMovimento",movimento.getLstProdutoMovimento());
            sessao.setAttribute("movimentoProduto",null);
            pagRetorno = "FabricaGelo.Movimento.AcaoAbreMovimento";
            
        }  
        else
        {
            sessao.setAttribute("avisoErro", "Movimento Fechado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Movimento.AcaoAbreMovimento");
            pagRetorno = "visao/erro.jsp";            
        }
        
        return pagRetorno;        
    }
}
