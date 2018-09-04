/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Pagamento;

import Bean.Pagamento;
import Bean.Parcela;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ParcelaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoCancelaParcela extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "";
        
        ParcelaDAO parcelaDAO = new ParcelaDAO(conexao);
        
        Pagamento pagamento = (Pagamento)sessao.getAttribute("pagamento");
        Parcela parcela = (Parcela)sessao.getAttribute("parcela");        
        
        if ((pagamento.getSituacao().equals("PG")))
        {
            sessao.setAttribute("avisoErro", "Pagamento concluído");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Pagamento.AcaoAbrePagamento");
            pagRetorno = "visao/erro.jsp";             
        }
        else if ((parcela) != null && (parcela.getSituacao().equals("G")))
        {
            sessao.setAttribute("avisoErro", "Parcela paga");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Pagamento.AcaoAbrePagamento");
            pagRetorno = "visao/erro.jsp";                 
        }
        else
        {
            String idParcela = req.getParameter("idParcela");
            parcela.setIdParcela(Integer.parseInt(idParcela));
            parcela = parcelaDAO.listaUm(parcela);
            if (parcela != null)
            {
                parcela.setSituacao("P");
                parcelaDAO.atualizar(parcela);
            }
            pagRetorno = "FabricaGelo.Pagamento.AcaoAbrePagamento"; 
        }
        
        return pagRetorno;
        
    }
}
