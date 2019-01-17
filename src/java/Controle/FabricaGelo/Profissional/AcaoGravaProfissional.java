/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Profissional;

import Bean.Profissional;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ProfissionalDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoGravaProfissional extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        String pagRetorno = "";
        
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
        Profissional profissional = (Profissional)sessao.getAttribute("profissional");
        if (profissional == null)
            profissional = new Profissional();
        
        String cpf = (req.getParameter("txtCpf") == "" || req.getParameter("txtCpf") == null) ? "" : req.getParameter("txtCpf");
        String nome = (req.getParameter("txtProfissional") == "" || req.getParameter("txtProfissional") == null) ? "" : req.getParameter("txtProfissional");
        String ctps = (req.getParameter("txtCtps") == "" || req.getParameter("txtCtps") == null) ? "" : req.getParameter("txtCtps");
        String celular = (req.getParameter("txtCelular") == "" || req.getParameter("txtCelular") == null) ? "" : req.getParameter("txtCelular");
        String usuario = (req.getParameter("txtUsuario") == "" || req.getParameter("txtUsuario") == null) ? "" : req.getParameter("txtUsuario");
        String senha = (req.getParameter("txtSenha") == "" || req.getParameter("txtSenha") == null) ? "" : req.getParameter("txtSenha");
 
        profissional.setCpf(cpf);
        profissional.setNome(nome);
        profissional.setCtps(ctps);
        profissional.setCelular(celular);
        profissional.setUsuario(usuario);
        profissional.setSenha(senha);
       
        if (profissionalDAO.atualizar(profissional))
        {
            profissional.setIdProfissional(profissionalDAO.getIdentity());
            profissional = profissionalDAO.listaUm(profissional);
            sessao.setAttribute("profissional", profissional);
            
            sessao.setAttribute("avisoErro", "Dados do Profissional atualizados");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Profissional.AcaoAbreProfissional");
            pagRetorno = "visao/erro.jsp";                
        }
        
        return pagRetorno;
        
        
    }
}
