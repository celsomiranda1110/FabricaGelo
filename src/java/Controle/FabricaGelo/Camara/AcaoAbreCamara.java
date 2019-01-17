/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Camara;

import Bean.Equipamento;
import Bean.Manutencao;
import Controle.FabricaGelo.Gerais.Acao;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoAbreCamara extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        Equipamento camara = (Equipamento)sessao.getAttribute("camara");
        Manutencao manutencao = (Manutencao)sessao.getAttribute("manutencao");

        sessao.setAttribute("camara",camara);
        sessao.setAttribute("manutencao",manutencao);
        
        return "visao/camara.jsp";
    }
}
