/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.TipoColaborador;

import Bean.TipoColaborador;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.TipoColaboradorDAO;
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
public class AcaoPesquisaTipoColaborador extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<TipoColaborador> lstTipoColaborador = new ArrayList<TipoColaborador>();
        List<TipoColaborador> lstRetorno = new ArrayList<TipoColaborador>();
        
        String pesquisa = req.getParameter("txtPesquisa");
        String situacao = req.getParameter("cmbSituacaoPesquisa").equals("") ? "" : req.getParameter("cmbSituacaoPesquisa");
        
        TipoColaboradorDAO bairroDAO = new TipoColaboradorDAO(conexao);
        TipoColaborador tipoColaborador = new TipoColaborador();
        tipoColaborador.setAtivo(situacao);
        
        lstTipoColaborador = bairroDAO.listaTodos(tipoColaborador);
        
        
        if (!pesquisa.equals(""))
        {
            Iterator it = lstTipoColaborador.iterator();
            while (it.hasNext())
            {
                TipoColaborador _tipoColaborador = (TipoColaborador)it.next();

                if (_tipoColaborador.getDescricao().contains(pesquisa.toUpperCase()))
                    lstRetorno.add(_tipoColaborador);
            }
        }
        else
            lstRetorno = lstTipoColaborador; 
        
        sessao.setAttribute("lstTipoColaborador",lstRetorno); 
        
        return "visao/listarTipoColaborador.jsp";        
    }
}
