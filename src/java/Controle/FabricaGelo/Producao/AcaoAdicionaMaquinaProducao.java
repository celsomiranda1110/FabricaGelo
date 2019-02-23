/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.MaquinaProducao;
import Bean.Producao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MaquinaProducaoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoAdicionaMaquinaProducao extends Acao
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
        else if (maquinaProducao == null)
        {
            sessao.setAttribute("avisoErro", "Produção por máquina não identificada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            
            String hrInicial = (req.getParameter("txtHrInicial").equals("") || req.getParameter("txtHrInicial") == null) ? "00:00" : req.getParameter("txtHrInicial");
            String hrFinal = (req.getParameter("txtHrFinal").equals("") || req.getParameter("txtHrFinal") == null) ? "00:00" : req.getParameter("txtHrFinal");            
            
            
            MaquinaProducaoDAO maquinaProducaoDAO = new MaquinaProducaoDAO(conexao);
            maquinaProducao.setIdProducao(producao.getIdProducao());
            maquinaProducao.setHrInicial(hrInicial);
            maquinaProducao.setHrFinal(hrFinal);
            if(maquinaProducaoDAO.atualizar(maquinaProducao))
            {
                
                sessao.setAttribute("maquinaProducao",null);
                sessao.setAttribute("lstAvaria", null);
                sessao.setAttribute("lstProdutoCamara", null);
                sessao.setAttribute("avisoErro", "Produção por máquina atualizada");
                sessao.setAttribute("tipoAviso","alert alert-success");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
                pagRetorno = "visao/erro.jsp";                
            }
        }
        
        return pagRetorno;
        
    }
}
