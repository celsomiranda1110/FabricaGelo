/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Funcao;

import Bean.Colaborador;
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
public class AcaoSelecionaFuncao extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        FuncaoDAO funcaoDAO = new FuncaoDAO(conexao);
        Funcao funcao = new Funcao();
        
        String idFuncao = req.getParameter("idFuncao");
        funcao.setIdFuncao(Integer.parseInt(idFuncao));
        funcao = funcaoDAO.listaUm(funcao);
        
        String pagRetorno = (String)sessao.getAttribute("pagRetorno");
        Colaborador colaborador = (Colaborador)sessao.getAttribute("colaborador");
        
        
        sessao.setAttribute("colaborador",colaborador);
        sessao.setAttribute("funcao",funcao);
        sessao.setAttribute("pagRetorno",pagRetorno);
        
        return "visao/funcao.jsp";
    }
    
}
