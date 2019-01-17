/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Veiculo;

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
public class AcaoAbreVeiculo extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        Equipamento veiculo = (Equipamento)sessao.getAttribute("veiculo");
        Manutencao manutencao = (Manutencao)sessao.getAttribute("manutencao");
        
        
        sessao.setAttribute("veiculo",veiculo);
        sessao.setAttribute("manutencao",manutencao);
        
        return "visao/veiculo.jsp";
    }
}
