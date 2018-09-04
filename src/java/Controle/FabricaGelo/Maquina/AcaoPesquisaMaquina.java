/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Maquina;

import Bean.Maquina;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MaquinaDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoPesquisaMaquina extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Maquina> lstMaquina = new ArrayList<Maquina>();
        List<Maquina> lstRetorno = new ArrayList<Maquina>();
        
        MaquinaDAO maquinaDAO = new MaquinaDAO(conexao);
        lstMaquina = maquinaDAO.listaTodos();
        
        String pesquisa = req.getParameter("txtPesquisa");
        if (pesquisa != "")
        {
            Iterator it = lstMaquina.iterator();
            while (it.hasNext())
            {
                Maquina _maquina = (Maquina)it.next();

                if (_maquina.getDescricao().contains(pesquisa.toUpperCase()))
                    lstRetorno.add(_maquina);
            }
        }
        else
            lstRetorno = lstMaquina; 
        
        sessao.setAttribute("lstMaquina",lstRetorno); 
        
        return "visao/listarMaquina.jsp";        
    }
}
