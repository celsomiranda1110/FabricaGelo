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
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoPesquisaColaboradorCusto extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Entrega.AcaoEncerrarRota";
        
        String pesquisa = ( req.getParameter("cmbEmpreendimento").equals("") || req.getParameter("cmbEmpreendimento") == null ? "0" : req.getParameter("cmbEmpreendimento"));
        int idTipoEmpreendimento = Integer.parseInt(pesquisa);
        
        List<Colaborador> lstColaborador = new ArrayList<Colaborador>();
        
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        
        Iterator itColaborador = colaboradorDAO.listaTodos().iterator();
        while (itColaborador.hasNext())
        {
            Colaborador colaborador = (Colaborador)itColaborador.next();
            if (colaborador.getIdTipoColaborador() == idTipoEmpreendimento)
                lstColaborador.add(colaborador);
                 
        }
        
        sessao.setAttribute("lstColaborador",lstColaborador); 
        sessao.setAttribute("paramPesquisa",pesquisa); 
        sessao.setAttribute("link", "<a href='#divCusto'>");
        
        return pagRetorno;
        
    }
}
