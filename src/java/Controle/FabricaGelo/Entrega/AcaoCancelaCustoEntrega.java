/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.CustoEntrega;
import Bean.Entrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.CustoEntregaDAO;
import DAO.EntregaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoCancelaCustoEntrega extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "";
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        
        if (!entrega.getSituacao().equals("EN"))
        {
            String idCustoEntrega = req.getParameter("idCustoEntrega");

            CustoEntregaDAO custoEntregaDAO = new CustoEntregaDAO(conexao);
            CustoEntrega custoEntrega = new CustoEntrega();
            custoEntrega.setIdCustoEntrega(Integer.parseInt(idCustoEntrega));
            if (custoEntregaDAO.deleta(custoEntrega))
               entrega = entregaDAO.listaUm(entrega);
            
            sessao.setAttribute("entrega",entrega);
            sessao.setAttribute("lstCustoEntrega",entrega.getLstCustoEntrega());
            sessao.setAttribute("custoEntrega",null);            
            pagRetorno = "FabricaGelo.Entrega.AcaoAbreEntrega";
        }
        else
        {
            sessao.setAttribute("avisoErro", "Entrega realizada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoAbreEntrega");
            pagRetorno = "visao/erro.jsp"; 
        }  
        
        return pagRetorno;
            
    }
}
