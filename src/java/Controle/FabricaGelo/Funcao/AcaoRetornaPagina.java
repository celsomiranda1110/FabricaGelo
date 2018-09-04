/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Funcao;


import Bean.Funcao;
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
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        Funcao funcao = (Funcao)sessao.getAttribute("funcao");
        
        
        if (pagRetorno != null)
        {
            Profissional profissional = (Profissional)sessao.getAttribute("profissional");
            profissional.setFuncao(funcao);
            sessao.setAttribute("profissional", profissional);
        }
        else
        {
            sessao.setAttribute("profissional", null);
            pagRetorno = "FabricaGelo.Funcao.AcaoListarFuncao";
        }
        
        return pagRetorno;
    }
}
