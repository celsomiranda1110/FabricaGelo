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
        
        String pagRetorno = "FabricaGelo.Bairro.AcaoAbreBairro";
        
        BairroDAO bairroDAO = new BairroDAO(conexao);
        Bairro bairro = (Bairro)sessao.getAttribute("bairro");
        if (bairro == null)
            bairro = new Bairro();
      
        String descricao = (req.getParameter("txtBairro") == "" || req.getParameter("txtBairro") == null) ? "" : req.getParameter("txtBairro");
        String inativo = (req.getParameter("ck_Ativo") == null ? "A" : "I");
        bairro.setDescricao(descricao);
        bairro.setAtivo(inativo);
        
        
        if (bairroDAO.atualizar(bairro))
        {
            bairro.setIdBairro(bairroDAO.getIdentity());
            bairro = bairroDAO.listaUm(bairro);
            sessao.setAttribute("bairro", bairro);
            
            sessao.setAttribute("avisoErro", "Bairro atualizado");
            sessao.setAttribute("tipoAviso","alert alert-success");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Bairro.AcaoAbreBairro");
            pagRetorno = "visao/erro.jsp";              
        }
        
        
        return pagRetorno;
    }
}
