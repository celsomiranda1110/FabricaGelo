/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Venda;

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
        
        MovimentoDAO vendaDAO = new MovimentoDAO(conexao);
        Movimento venda = (Movimento)sessao.getAttribute("venda");

        if ((venda != null) && (venda.getIdMovimento() == 0))
        {
            sessao.setAttribute("avisoErro", "Venda deve ser atualizada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Venda.AcaoAbreVenda");
            pagRetorno = "visao/erro.jsp";            
        }
        else if (!venda.getSituacao().equals("FE"))
        {        

            ProdutoMovimentoDAO vendaProdutoDAO = new ProdutoMovimentoDAO(conexao);

            ProdutoMovimento vendaProduto = (ProdutoMovimento)sessao.getAttribute("vendaProduto");
            if (vendaProduto == null)
                vendaProduto = new ProdutoMovimento();

            String idProdutoMovimento = req.getParameter("idProdutoMovimento");
            vendaProduto.setIdProdutoMovimento(Integer.parseInt(idProdutoMovimento));
            if (vendaProdutoDAO.delete(vendaProduto))
                venda = vendaDAO.listaUm(venda);

            sessao.setAttribute("venda",venda);
            sessao.setAttribute("lstProdutoMovimento",venda.getLstProdutoMovimento());
            sessao.setAttribute("vendaProduto",null);
            pagRetorno = "FabricaGelo.Venda.AcaoAbreVenda";
            
        }  
        else
        {
            sessao.setAttribute("avisoErro", "Movimento de venda Fechado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Venda.AcaoAbreVenda");
            pagRetorno = "visao/erro.jsp";            
        }
        
        return pagRetorno;        
    }
}
