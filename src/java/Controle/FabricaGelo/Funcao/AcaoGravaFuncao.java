/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Funcao;

import Bean.Funcao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.FuncaoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoGravaFuncao extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        FuncaoDAO funcaoDAO = new FuncaoDAO(conexao);
        Funcao funcao = (Funcao)sessao.getAttribute("funcao");
        if (funcao == null)
            funcao = new Funcao();
        
        String descricao = (req.getParameter("txtFuncao") == "" || req.getParameter("txtFuncao") == null) ? "" : req.getParameter("txtFuncao");
        
        funcao.setDescricao(descricao);
        
        if (funcaoDAO.atualizar(funcao))
            sessao.setAttribute("funcao", funcao);

        
        //return "visao/funcao.jsp";
        return "FabricaGelo.Funcao.AcaoListarFuncao";
    }
}
