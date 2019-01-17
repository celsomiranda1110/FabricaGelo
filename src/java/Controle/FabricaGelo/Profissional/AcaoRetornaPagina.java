/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Profissional;

import Bean.Colaborador;
import Bean.Entrega;
import Bean.Movimento;
import Bean.Profissional;
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
        
        Movimento movimento = (Movimento)sessao.getAttribute("movimento");
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        
        Profissional profissional = (Profissional)sessao.getAttribute("profissional");
        
        
        if (pagRetorno != null)
        {
            if (pagRetorno.equals("FabricaGelo.Entrega.AcaoAbreEntrega"))
            {
                
                
                sessao.setAttribute("entrega", entrega);
            }            
        }
        else
        {
            pagRetorno =  "FabricaGelo.Profissional.AcaoListarProfissional";
        }
        
        return pagRetorno;
    }
}
