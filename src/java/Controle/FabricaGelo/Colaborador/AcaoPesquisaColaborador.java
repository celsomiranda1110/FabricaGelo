/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;

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
public class AcaoPesquisaColaborador extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        List<Colaborador> lstColaborador = new ArrayList();
        List<Colaborador> lstRetorno = new ArrayList();
        
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        Colaborador colaborador = new Colaborador();
        String pesquisa = req.getParameter("txtPesquisa");
        String situacao = req.getParameter("cmbSituacaoPesquisa").equals("") ? "" : req.getParameter("cmbSituacaoPesquisa");
        colaborador.setAtivo(situacao);
        
        lstColaborador = colaboradorDAO.listaTodos(colaborador);
        
        if (!pesquisa.equals(""))
        {
            Iterator it = lstColaborador.iterator();
            while (it.hasNext())
            {
                Colaborador _colaborador = (Colaborador)it.next();

                if (_colaborador.getRazaoSocial().contains(pesquisa.toUpperCase()))
                    lstRetorno.add(_colaborador);
            }
        }
        else
            lstRetorno = lstColaborador;
        
        sessao.setAttribute("lstColaborador",lstRetorno); 
        
        return "visao/listarColaborador.jsp";
    }
    
}
