/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Aluguel;

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
        
        MovimentoDAO aluguelDAO = new MovimentoDAO(conexao);
        Movimento aluguel = (Movimento)sessao.getAttribute("aluguel");

        if ((aluguel != null) && (aluguel.getIdMovimento() == 0))
        {
            sessao.setAttribute("avisoErro", "Aluguel deve ser atualizada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Aluguel.AcaoAbreAluguel");
            pagRetorno = "visao/erro.jsp";            
        }
        else if (!aluguel.getSituacao().equals("FE"))
        {        
        
        
            ProdutoMovimentoDAO aluguelProdutoDAO = new ProdutoMovimentoDAO(conexao);

            ProdutoMovimento aluguelProduto = (ProdutoMovimento)sessao.getAttribute("aluguelProduto");
            if (aluguelProduto == null)
                aluguelProduto = new ProdutoMovimento();

            String idProdutoMovimento = req.getParameter("idProdutoMovimento");
            aluguelProduto.setIdProdutoMovimento(Integer.parseInt(idProdutoMovimento));
            if (aluguelProdutoDAO.delete(aluguelProduto))
                aluguel = aluguelDAO.listaUm(aluguel);

            sessao.setAttribute("aluguel",aluguel);
            sessao.setAttribute("lstProdutoMovimento",aluguel.getLstProdutoMovimento());
            sessao.setAttribute("aluguelProduto",null);
            pagRetorno = "FabricaGelo.Aluguel.AcaoAbreAluguel";
            
        }  
        else
        {
            sessao.setAttribute("avisoErro", "Movimento de aluguel Fechado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Aluguel.AcaoAbreAluguel");
            pagRetorno = "visao/erro.jsp";            
        }
        
        return pagRetorno;        
    }
}


