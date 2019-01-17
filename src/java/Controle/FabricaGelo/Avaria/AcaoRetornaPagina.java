/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Avaria;

import Bean.Avaria;
import Bean.AvariaProducao;
import Bean.AvariaEntrega;
import Controle.FabricaGelo.Gerais.Acao;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoRetornaPagina extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        
        Avaria avaria = (Avaria)sessao.getAttribute("avaria");
        
        
        if (pagRetorno != null)
        {
            if (pagRetorno.equals("FabricaGelo.Producao.AcaoAbreProducao"))
            {
                AvariaProducao avariaProducao = new AvariaProducao();
                avariaProducao.setAvaria(avaria);
                sessao.setAttribute("avariaProducao", avariaProducao);
            }
            else if (pagRetorno.equals("FabricaGelo.Entrega.AcaoAbreEntrega"))
            {
                AvariaEntrega avariaEntrega = new AvariaEntrega();
                avariaEntrega.setAvaria(avaria);
                sessao.setAttribute("avariaEntrega", avariaEntrega);
            }
        }
        else
        {
            pagRetorno = "FabricaGelo.Avaria.AcaoListarAvaria";
        }
        
        return pagRetorno;
    }
}
