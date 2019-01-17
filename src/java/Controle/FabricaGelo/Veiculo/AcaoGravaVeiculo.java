/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Veiculo;

import Bean.Equipamento;
import Bean.Manutencao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EquipamentoDAO;
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
public class AcaoGravaVeiculo extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        String pagRetorno = "";
        
        EquipamentoDAO veiculoDAO = new EquipamentoDAO(conexao);
        Equipamento veiculo = (Equipamento)sessao.getAttribute("veiculo");
        Manutencao manutencao = (Manutencao)sessao.getAttribute("manutencao");
        
        if (veiculo == null)
            veiculo = new Equipamento();
        
        List<Manutencao> lstManutencao = new ArrayList<Manutencao>();
        
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
        String motivo = (req.getParameter("txtMotivo").equals("") || req.getParameter("txtMotivo") == null) ? "" : req.getParameter("txtMotivo");
        
        if (manutencao != null)
        {
            manutencao.setDataInicioFormatada(dtInicio);
            manutencao.setDataFimFormatada(dtFim);
            manutencao.setDataGarantiaFormatada(dtGarantia);
            manutencao.setValor(Double.parseDouble(valor));
            manutencao.setMotivo(motivo);
            lstManutencao.add(manutencao);
        }
        
        veiculo.setTipo("VE");
        veiculo.setDescricao(descricao);
        veiculo.setSituacao("A");
        veiculo.setPlaca(placa);
        veiculo.setMarca(marca);
        veiculo.setModelo(modelo);
        veiculo.setAno(ano);
        veiculo.setCapacidade(Double.parseDouble(capacidade));
        veiculo.setLstManutencao(lstManutencao);
                
        
        if (veiculoDAO.atualizar(veiculo))
        {
            veiculo.setIdEquipamento(veiculoDAO.getIdentity());
            veiculo = veiculoDAO.listaUm(veiculo);
            sessao.setAttribute("veiculo", veiculo);
            
            sessao.setAttribute("avisoErro", "Dados do Veículo atualizados");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Veiculo.AcaoAbreVeiculo");
            pagRetorno = "visao/erro.jsp"; 
        }
        
        return pagRetorno;
    }
}
