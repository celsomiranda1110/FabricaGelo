/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Avaria;

import Bean.Avaria;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoGravaAvaria extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Avaria.AcaoAbreAvaria";
        
        AvariaDAO avariaDAO = new AvariaDAO(conexao);
        Avaria avaria = (Avaria)sessao.getAttribute("avaria");
        if (avaria == null)
            avaria = new Avaria();
        
        
        
        String descricao = (req.getParameter("txtAvaria").equals("") || req.getParameter("txtAvaria") == null) ? "" : req.getParameter("txtAvaria").toUpperCase();
        String inativo = (req.getParameter("ck_Ativo") == null ? "A" : "I");
        
        avaria.setDescricao(descricao);
        avaria.setAtivo(inativo);
        
        if (avaria.getDescricao().equals(""))
        {
            sessao.setAttribute("avisoErro", "Descrição vazia");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Avaria.AcaoAbreAvaria");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            if (avariaDAO.atualizar(avaria))
            {
                avaria.setIdAvaria(avariaDAO.getIdentity());
                avaria = avariaDAO.listaUm(avaria);

                sessao.setAttribute("avaria", avaria);

                sessao.setAttribute("avisoErro", "Avaria atualizada");
                sessao.setAttribute("tipoAviso","alert alert-success");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Avaria.AcaoAbreAvaria");
                pagRetorno = "visao/erro.jsp";                          

            }
        }
        
        return pagRetorno;
    }
}

