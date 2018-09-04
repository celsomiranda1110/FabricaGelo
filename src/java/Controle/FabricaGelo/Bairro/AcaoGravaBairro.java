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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoGravaBairro extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        BairroDAO bairroDAO = new BairroDAO(conexao);
        Bairro bairro = (Bairro)sessao.getAttribute("bairro");
        if (bairro == null)
            bairro = new Bairro();
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        
        String descricao = (req.getParameter("txtBairro") == "" || req.getParameter("txtBairro") == null) ? "" : req.getParameter("txtBairro");
        
        bairro.setDescricao(descricao);
        
        
        if (bairroDAO.atualizar(bairro))
        
            sessao.setAttribute("bairro", bairro);
        
        sessao.setAttribute("pagRetorno",pagRetorno);
        
        return "FabricaGelo.Bairro.AcaoListarBairro";
    }
}
