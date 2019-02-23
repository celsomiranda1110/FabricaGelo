/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Profissional;

import Bean.Colaborador;
import Bean.Movimento;
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
public class AcaoSelecionaProfissional extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Profissional.AcaoAbreProfissional";


        ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
        Profissional profissional = new Profissional();
        
        String idProfissional = req.getParameter("idProfissional");
        profissional.setIdProfissional(Integer.parseInt(idProfissional));
        profissional = profissionalDAO.listaUm(profissional);


        sessao.setAttribute("profissional",profissional);
        
        
        return pagRetorno;
    }

}
