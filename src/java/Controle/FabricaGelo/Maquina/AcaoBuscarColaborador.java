/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Maquina;

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
        String situacao = (req.getParameter("cmbSituacao").equals("") || req.getParameter("cmbSituacao") == null) ? "A" : req.getParameter("cmbSituacao");
        String placa = "";
        String marca = "";
        String modelo = "";
        String ano = "";
        String capacidade = "0";
        
        // dados de manutenção
        String dtInicio = (req.getParameter("txtDtInicio").equals("") || req.getParameter("txtDtInicio") == null) ? "" : req.getParameter("txtDtInicio");
        String dtFim = (req.getParameter("txtDtFinal").equals("") || req.getParameter("txtDtFinal") == null) ? "" : req.getParameter("txtDtFinal");
        String dtGarantia = (req.getParameter("txtDtGarantia").equals("") || req.getParameter("txtDtGarantia") == null) ? "" : req.getParameter("txtDtGarantia");
        String valor = (req.getParameter("txtValor").equals("") || req.getParameter("txtValor") == null) ? "" : req.getParameter("txtValor");
        
        Equipamento maquina = (Equipamento)sessao.getAttribute("maquina");
        maquina.setTipo("VE");
        maquina.setDescricao(descricao);
        maquina.setSituacao("A");
        maquina.setPlaca(placa);
        maquina.setMarca(marca);
        maquina.setModelo(modelo);
        maquina.setAno(ano);
        maquina.setCapacidade(Double.parseDouble(capacidade));

        List<Colaborador> lstColaborador = new ArrayList<Colaborador>();
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        lstColaborador = colaboradorDAO.listaTodos();
        
        sessao.setAttribute("maquina",maquina);
        sessao.setAttribute("lstColaborador",lstColaborador);
        sessao.setAttribute("pagRetorno","FabricaGelo.Maquina.AcaoAbreMaquina");
        
        return "visao/listarColaborador.jsp";        
    }
}
