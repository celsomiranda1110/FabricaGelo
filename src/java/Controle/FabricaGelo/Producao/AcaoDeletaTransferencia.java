/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.TransferenciaProducao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MaquinaProducaoDAO;
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
        
        Producao producao = (Producao)sessao.getAttribute("producao");
        MaquinaProducao maquinaProducao = (MaquinaProducao)sessao.getAttribute("maquinaProducao");
     
        if ((producao != null) && (producao.getSituacao().equals("PF")))
        {
            sessao.setAttribute("avisoErro", "Produção já finalizada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";            
        }            
        else
        {
            
            String idTransferenciaProducao = req.getParameter("idTransferenciaProducao");
            TransferenciaProducao transf = new TransferenciaProducao();  
            transf.setIdTransferenciaProducao(Integer.parseInt(idTransferenciaProducao));
            TransferenciaProducaoDAO transfDAO = new TransferenciaProducaoDAO(conexao);
           
            if (transfDAO.deleta(transf))
            {
                MaquinaProducaoDAO maquinaProducaoDAO = new MaquinaProducaoDAO(conexao);
                if (maquinaProducaoDAO.atualizar(maquinaProducao))
                    maquinaProducao = maquinaProducaoDAO.listaUm(maquinaProducao);
                
                sessao.setAttribute("transf", null);
                sessao.setAttribute("maquinaProducao",maquinaProducao);
                sessao.setAttribute("lstAvariaProducao",maquinaProducao.getLstAvariaProducao());
                sessao.setAttribute("lstTransferencia",maquinaProducao.getLstTransferenciaProducao());
                sessao.setAttribute("avisoErro", "Transferência apagada");
                sessao.setAttribute("tipoAviso","alert alert-success");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
                pagRetorno = "visao/erro.jsp";              
            }
           
        }

        
        return pagRetorno;
        
    }
}
