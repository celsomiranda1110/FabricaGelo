/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Veiculo;

import Bean.Colaborador;
import Bean.Equipamento;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoBuscarColaborador extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        // dados do veiculo
        String descricao = (req.getParameter("txtDescricao").equals("") || req.getParameter("txtDescricao") == null) ? "" : req.getParameter("txtDescricao");
        String placa = (req.getParameter("txtPlaca").equals("") || req.getParameter("txtPlaca") == null) ? "" : req.getParameter("txtPlaca");
        String marca = (req.getParameter("txtMarca").equals("") || req.getParameter("txtMarca") == null) ? "" : req.getParameter("txtMarca");
        String modelo = (req.getParameter("txtModelo").equals("") || req.getParameter("txtModelo") == null) ? "" : req.getParameter("txtModelo");
        String ano = (req.getParameter("txtAno") == "" || req.getParameter("txtAno") == null) ? "" : req.getParameter("txtAno");
        String capacidade = (req.getParameter("txtCapacidade").equals("") || req.getParameter("txtCapacidade") == null) ? "" : req.getParameter("txtCapacidade");
        
        // dados de manutenção
        String dtInicio = (req.getParameter("txtDtInicio").equals("") || req.getParameter("txtDtInicio") == null) ? "" : req.getParameter("txtDtInicio");
        String dtFim = (req.getParameter("txtDtFinal").equals("") || req.getParameter("txtDtFinal") == null) ? "" : req.getParameter("txtDtFinal");
        String dtGarantia = (req.getParameter("txtDtGarantia").equals("") || req.getParameter("txtDtGarantia") == null) ? "" : req.getParameter("txtDtGarantia");
        String valor = (req.getParameter("txtValor").equals("") || req.getParameter("txtValor") == null) ? "" : req.getParameter("txtValor");
        
        Equipamento veiculo = (Equipamento)sessao.getAttribute("veiculo");
        veiculo.setTipo("VE");
        veiculo.setDescricao(descricao);
        veiculo.setSituacao("A");
        veiculo.setPlaca(placa);
        veiculo.setMarca(marca);
        veiculo.setModelo(modelo);
        veiculo.setAno(ano);
        veiculo.setCapacidade(Double.parseDouble(capacidade));

        List<Colaborador> lstColaborador = new ArrayList<Colaborador>();
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        lstColaborador = colaboradorDAO.listaTodos();
        
        sessao.setAttribute("veiculo",veiculo);
        sessao.setAttribute("lstColaborador",lstColaborador);
        sessao.setAttribute("pagRetorno","FabricaGelo.Veiculo.AcaoAbreVeiculo");
        
        return "visao/listarColaborador.jsp";        
    }
}
