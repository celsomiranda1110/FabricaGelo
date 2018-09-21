/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.AvariaProducao;
import Bean.Producao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProducaoDAO;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaProducao extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        
        ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
        Producao producao = new Producao();
        
        String idProducao = req.getParameter("idProducao");
        producao.setIdProducao(Integer.parseInt(idProducao));
        producao = producaoDAO.listaUm(producao);
        
        sessao.setAttribute("producao",producao);
        if (producao != null)
            sessao.setAttribute("lstMaquinaProducao", producao.getLstMaquinaProducao());
        else
            sessao.setAttribute("lstAvariaProducao", null);
        sessao.setAttribute("maquinaProducao", null);
        
        return "visao/producao.jsp";
    }
}

