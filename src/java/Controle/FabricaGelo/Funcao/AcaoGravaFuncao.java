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
        
        String pagRetorno = "FabricaGelo.Funcao.AcaoAbreFuncao";
        

        FuncaoDAO funcaoDAO = new FuncaoDAO(conexao);
        Funcao funcao = (Funcao)sessao.getAttribute("funcao");
        if (funcao == null)
            funcao = new Funcao();
        
        String descricao = (req.getParameter("txtFuncao") == "" || req.getParameter("txtFuncao") == null) ? "" : req.getParameter("txtFuncao").toUpperCase();
        String inativo = (req.getParameter("ck_Ativo") == null ? "A" : "I");
        
        funcao.setDescricao(descricao);
        funcao.setAtivo(inativo);
        
        if (funcaoDAO.atualizar(funcao))
        {
            funcao.setIdFuncao(funcaoDAO.getIdentity());
            funcao = funcaoDAO.listaUm(funcao);
            sessao.setAttribute("funcao", funcao);
            
            sessao.setAttribute("avisoErro", "Função atualizada");
            sessao.setAttribute("tipoAviso","alert alert-success");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Funcao.AcaoAbreFuncao");
            pagRetorno = "visao/erro.jsp";             
        }
        
        
        return pagRetorno;
    }
}
