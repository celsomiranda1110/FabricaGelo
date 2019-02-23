/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.TipoColaborador;

import Bean.TipoColaborador;
import Bean.Colaborador;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.TipoColaboradorDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaTipoColaborador extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        TipoColaboradorDAO tipoColaboradorDAO = new TipoColaboradorDAO(conexao);
        TipoColaborador tipoColaborador = new TipoColaborador();
        
        String idTipoColaborador = req.getParameter("idTipoColaborador");
        tipoColaborador.setIdTipoColaborador(Integer.parseInt(idTipoColaborador));
        tipoColaborador = tipoColaboradorDAO.listaUm(tipoColaborador);
        
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        
        sessao.setAttribute("colaborador",colaborador);
        sessao.setAttribute("pagRetorno",pagRetorno);
        sessao.setAttribute("tipoColaborador",tipoColaborador);
        
        
        return "visao/tipoColaborador.jsp";
    }
}
