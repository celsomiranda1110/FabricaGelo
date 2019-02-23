/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;

import Bean.Colaborador;
import DAO.ColaboradorDAO;
import Controle.FabricaGelo.Gerais.Acao;
import java.sql.Connection;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Miranda
 */
public class AcaoListarColaborador extends Acao{
public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        List<Colaborador> lstColaborador = new ArrayList<Colaborador>();
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        Colaborador colaborador = new Colaborador();
        

        
        lstColaborador = colaboradorDAO.listaTodos(colaborador);
        sessao.setAttribute("lstColaborador",lstColaborador);

        return "visao/listarColaborador.jsp";
    }       
}
