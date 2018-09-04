/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.AvariaProducao;
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
public class AcaoAbreProducao extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        
        Producao producao = (Producao)sessao.getAttribute("producao");
        AvariaProducao avariaProducao = (AvariaProducao)sessao.getAttribute("avariaProducao");
        
        
        if (producao != null)
        {
            sessao.setAttribute("producao",producao);
            
            if (avariaProducao != null)
                sessao.setAttribute("avariaProducao", avariaProducao);
            else
                sessao.setAttribute("avariaProducao", null); 
     
        }
        else
        {
            sessao.setAttribute("producao",null);
            sessao.setAttribute("avariaProducao", null);
        }
        
        
        
        return "visao/producao.jsp";
    }
    
}
