/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Maquina;

import Bean.Colaborador;
import Bean.Maquina;
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
public class AcaoRetornaPagina extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        Maquina maquina = (Maquina)sessao.getAttribute("maquina");
        Producao producao = (Producao)sessao.getAttribute("producao");
        
        if (pagRetorno != null)
        {
            if (pagRetorno.equals("FabricaGelo.Producao.AcaoAbreProducao"))
            {
                
                producao.setMaquina(maquina);
                sessao.setAttribute("producao", producao);
                
            }
           
        }
        else
        {
            sessao.setAttribute("maquina", null);
            pagRetorno = "FabricaGelo.Maquina.AcaoListarMaquina";
        }
        return pagRetorno;
    }
}