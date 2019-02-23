/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.TipoColaborador;

import Bean.Colaborador;
import Bean.TipoColaborador;
import Controle.FabricaGelo.Gerais.Acao;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoAbreTipoColaborador extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        TipoColaborador tipoColaborador = (TipoColaborador)sessao.getAttribute("tipoColaborador");
        
        
        sessao.setAttribute("tipoColaborador",tipoColaborador);
        return "visao/tipoColaborador.jsp";
    }
}