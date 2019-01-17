/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Camara;

import Bean.Colaborador;
import Bean.Equipamento;
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
        Equipamento camara = (Equipamento)sessao.getAttribute("camara");
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        
        if (pagRetorno != null)
        {
            sessao.setAttribute("camara", camara);
            sessao.setAttribute("colaborador", colaborador);
        }
        else
        {
            sessao.setAttribute("camara", null);
            pagRetorno = "FabricaGelo.Camara.AcaoListarCamara";
        }
        
        return pagRetorno;
    }
}
