/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Veiculo;

import Bean.Veiculo;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.VeiculoDAO;
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
public class AcaoPesquisaVeiculo extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        List<Veiculo> lstVeiculo = new ArrayList<Veiculo>();
        List<Veiculo> lstRetorno = new ArrayList<Veiculo>();
        
        VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);
        lstVeiculo = veiculoDAO.listaTodos();
        
        String pesquisa = req.getParameter("txtPesquisa");
        if (pesquisa != "")
        {
            Iterator it = lstVeiculo.iterator();
            while (it.hasNext())
            {
                Veiculo _veiculo = (Veiculo)it.next();

                if (_veiculo.getPlaca().contains(pesquisa.toUpperCase()))
                    lstRetorno.add(_veiculo);
            }
        }
        else
            lstRetorno = lstVeiculo; 
        
        sessao.setAttribute("lstVeiculo",lstRetorno); 
        
        return "visao/listarVeiculo.jsp";        
    }
}
