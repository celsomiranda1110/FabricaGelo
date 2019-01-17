/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Colaborador;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoBuscarColaboradorPendente extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        List<Colaborador> lstColaborador = new ArrayList<Colaborador>();
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        lstColaborador = colaboradorDAO.listaTodos();
        
        

        sessao.setAttribute("lstColaboradorPendente",lstColaborador);
        sessao.setAttribute("pagRetorno","FabricaGelo.Entrega.AcaoAbreEntrega");
        
        return "visao/listarColaboradorPendencia.jsp";           
    }
}
