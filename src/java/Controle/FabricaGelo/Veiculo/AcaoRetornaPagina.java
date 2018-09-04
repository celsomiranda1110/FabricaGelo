/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Veiculo;

import Bean.Colaborador;
import Bean.Entrega;
import Bean.Veiculo;
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
        Veiculo veiculo = (Veiculo)sessao.getAttribute("veiculo");
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        
        if (pagRetorno != null)
        {
            if (pagRetorno.equals("FabricaGelo.Entrega.AcaoAbreEntrega"))
            {
                entrega.setVeiculo(veiculo);
                sessao.setAttribute("entrega", entrega);
            }            
        }
        else
        {
            pagRetorno =  "FabricaGelo.Veiculo.AcaoListarVeiculo";
        }       
//        if (pagRetorno != null)
//        {
//            sessao.setAttribute("veiculo", veiculo);
//           //sessao.setAttribute("colaborador", colaborador);
//        }
//        else
//        {
//            sessao.setAttribute("veiculo", null);
//            pagRetorno = "FabricaGelo.Veiculo.AcaoListarVeiculo";
//        }
        
        return pagRetorno;
    }
}
