/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Producao;
import Bean.TransferenciaProducao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.TransferenciaProducaoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoDeletaTransferencia extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Producao.AcaoAbreProducao";
        
        TransferenciaProducaoDAO transfDAO = new TransferenciaProducaoDAO(conexao);
        TransferenciaProducao transf = new TransferenciaProducao();
        
        Producao producao = (Producao)sessao.getAttribute("producao");
        String idTransferenciaProducao = req.getParameter("idTransferenciaProducao");
        
        if ((producao != null) && (producao.getSituacao().equals("PF")))
        {
            sessao.setAttribute("avisoErro", "Produção já finalizada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";            
        }            
        else
        {
            transf.setIdTransferenciaProducao(Integer.parseInt(idTransferenciaProducao));
            if (transfDAO.deleta(transf))
                sessao.setAttribute("transf", null);
            else
                sessao.setAttribute("transf",transf);

            sessao.setAttribute("producao", producao);            
        }

        
        return pagRetorno;
        
    }
}
