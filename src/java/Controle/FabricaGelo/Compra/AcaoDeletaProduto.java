/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Compra;

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
        
        MovimentoDAO compraDAO = new MovimentoDAO(conexao);
        Movimento compra = (Movimento)sessao.getAttribute("compra");

        if ((compra != null) && (compra.getIdMovimento() == 0))
        {
            sessao.setAttribute("avisoErro", "Compra deve ser atualizada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Compra.AcaoAbreCompra");
            pagRetorno = "visao/erro.jsp";            
        }
        else if (!compra.getSituacao().equals("FE"))
        {        
        
        
            ProdutoMovimentoDAO compraProdutoDAO = new ProdutoMovimentoDAO(conexao);

            ProdutoMovimento compraProduto = (ProdutoMovimento)sessao.getAttribute("compraProduto");
            if (compraProduto == null)
                compraProduto = new ProdutoMovimento();

            String idProdutoMovimento = req.getParameter("idProdutoMovimento");
            compraProduto.setIdProdutoMovimento(Integer.parseInt(idProdutoMovimento));
            if (compraProdutoDAO.delete(compraProduto))
                compra = compraDAO.listaUm(compra);

            sessao.setAttribute("compra",compra);
            sessao.setAttribute("lstProdutoMovimento",compra.getLstProdutoMovimento());
            sessao.setAttribute("compraProduto",null);
            pagRetorno = "FabricaGelo.Compra.AcaoAbreCompra";
            
        }  
        else
        {
            sessao.setAttribute("avisoErro", "Movimento de compra Fechado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Compra.AcaoAbreCompra");
            pagRetorno = "visao/erro.jsp";            
        }
        
        return pagRetorno;        
    }
}

