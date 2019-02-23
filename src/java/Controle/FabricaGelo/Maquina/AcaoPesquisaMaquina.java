/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Maquina;

import Bean.Equipamento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EquipamentoDAO;
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

        List<Equipamento> lstMaquina = new ArrayList<Equipamento>();
        List<Equipamento> lstRetorno = new ArrayList<Equipamento>();
        
        String pesquisa = req.getParameter("txtPesquisa");
        String situacao = req.getParameter("cmbSituacaoPesquisa").equals("") ? "" : req.getParameter("cmbSituacaoPesquisa");
         
        
        EquipamentoDAO maquinaDAO = new EquipamentoDAO(conexao);
        Equipamento maquina = new Equipamento();
        maquina.setTipo("MA");
        maquina.setAtivo(situacao);
        
        lstMaquina = maquinaDAO.listaTodos(maquina);
        
        
        if (!pesquisa.equals(""))
        {
            Iterator it = lstMaquina.iterator();
            while (it.hasNext())
            {
                Equipamento _maquina = (Equipamento)it.next();

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
