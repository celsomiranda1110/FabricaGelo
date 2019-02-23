/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.AvariaProducao;
import Bean.MaquinaProducao;
import Bean.Producao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaProducaoDAO;
import DAO.MaquinaProducaoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoDeletaAvariaProducao extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Producao.AcaoAbreProducao";
        
        Producao producao = (Producao)sessao.getAttribute("producao");
        MaquinaProducao maquinaProducao = (MaquinaProducao)sessao.getAttribute("maquinaProducao");

        if (producao == null)
        {
            sessao.setAttribute("avisoErro", "Produção não selecionada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoListarProducao");
            pagRetorno = "visao/erro.jsp";            
        }
        if (producao.getSituacao().equals("PF"))
        {
            sessao.setAttribute("avisoErro", "Produção já finalizada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";            
        }
        else if (maquinaProducao == null)
        {
            sessao.setAttribute("avisoErro", "Produção por máquina não identificada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
                   
            String idAvariaProducao = req.getParameter("idAvariaProducao");
            AvariaProducao avariaProducao = new AvariaProducao();
            avariaProducao.setIdAvariaProducao(Integer.parseInt(idAvariaProducao));
            AvariaProducaoDAO avariaProducaoDAO = new AvariaProducaoDAO(conexao);

            if (avariaProducaoDAO.delete(avariaProducao))
            {
                MaquinaProducaoDAO maquinaProducaoDAO = new MaquinaProducaoDAO(conexao);
                if (maquinaProducaoDAO.atualizar(maquinaProducao))
                    maquinaProducao = maquinaProducaoDAO.listaUm(maquinaProducao);
                
                sessao.setAttribute("avariaProducao", null);
                sessao.setAttribute("maquinaProducao",maquinaProducao);
                sessao.setAttribute("lstAvariaProducao",maquinaProducao.getLstAvariaProducao());
                sessao.setAttribute("lstTransferencia",maquinaProducao.getLstTransferenciaProducao());
                sessao.setAttribute("avisoErro", "Avaria apagada");
                sessao.setAttribute("tipoAviso","alert alert-success");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
                pagRetorno = "visao/erro.jsp";             
            }
        }
        
        return pagRetorno;
    }
    
}
