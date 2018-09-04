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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoGravaVeiculo extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);
        Veiculo veiculo = (Veiculo)sessao.getAttribute("veiculo");
        if (veiculo == null)
            veiculo = new Veiculo();
        
        String placa = (req.getParameter("txtPlaca") == "" || req.getParameter("txtPlaca") == null) ? "" : req.getParameter("txtPlaca");
        String marca = (req.getParameter("txtMarca") == "" || req.getParameter("txtMarca") == null) ? "" : req.getParameter("txtMarca");
        String modelo = (req.getParameter("txtModelo") == "" || req.getParameter("txtModelo") == null) ? "" : req.getParameter("txtModelo");
        String ano = (req.getParameter("txtAno") == "" || req.getParameter("txtAno") == null) ? "" : req.getParameter("txtAno");
        
        veiculo.setPlaca(placa);
        veiculo.setMarca(marca);
        veiculo.setModelo(modelo);
        veiculo.setAno(ano);
                
        
        if (veiculoDAO.atualizar(veiculo))
            sessao.setAttribute("veiculo", veiculo);

        
        //return "visao/veiculo.jsp";
        return "FabricaGelo.Veiculo.AcaoListarVeiculo";
    }
}
