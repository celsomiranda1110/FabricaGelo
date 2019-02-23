/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Bairro;

import Bean.Bairro;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.BairroDAO;
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
public class AcaoPesquisaBairro extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Bairro> lstBairro = new ArrayList<Bairro>();
        List<Bairro> lstRetorno = new ArrayList<Bairro>();
        
        BairroDAO bairroDAO = new BairroDAO(conexao);
        Bairro bairro = new Bairro();
        String pesquisa = req.getParameter("txtPesquisa");
        String situacao = req.getParameter("cmbSituacaoPesquisa").equals("") ? "" : req.getParameter("cmbSituacaoPesquisa");
        bairro.setAtivo(situacao);
        
        lstBairro = bairroDAO.listaTodos(bairro);
        
        
        if (!pesquisa.equals(""))
        {
            Iterator it = lstBairro.iterator();
            while (it.hasNext())
            {
                Bairro _bairro = (Bairro)it.next();

                if (_bairro.getDescricao().contains(pesquisa.toUpperCase()))
                    lstRetorno.add(_bairro);
            }
        }
        else
            lstRetorno = lstBairro; 
        
        sessao.setAttribute("lstBairro",lstRetorno); 
        
        return "visao/listarBairro.jsp";        
    }
}
