/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.MaquinaProducao;
import Bean.Producao;
import Controle.FabricaGelo.Gerais.Acao;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoCalculaProducao extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/producao.jsp";
        
        Producao producao = (Producao)sessao.getAttribute("producao");
        MaquinaProducao maquinaProducao = (MaquinaProducao)sessao.getAttribute("maquinaProducao");
        String reposicao = (req.getParameter("txtReposicao").equals("") || req.getParameter("txtReposicao") == null) ? "0" : req.getParameter("txtReposicao");            
        
        if (producao == null || maquinaProducao == null)
        {
            sessao.setAttribute("avisoErro", "Produção ou Equipamento não selecionados");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";          
        }
        else
        {
            if (maquinaProducao != null)
            {
                
                double saldoAnterior = maquinaProducao.getQtSaldoAnterior();
                double qtReposicao = Double.parseDouble(reposicao);
                double qtProducao = saldoAnterior + qtReposicao;
                double qtSaldo = qtProducao;
                
                maquinaProducao.setQtSaldoAnterior(saldoAnterior);
                maquinaProducao.setQtReposicao(qtReposicao);
                maquinaProducao.setQtProducao(qtProducao);
                maquinaProducao.setQtSaldo(qtSaldo);
                
                sessao.setAttribute("maquinaProducao", maquinaProducao);
            }
            
        }
        
        return pagRetorno;
        
    }
}
