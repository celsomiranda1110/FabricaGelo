/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Maquina;

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
public class AcaoGravaMaquina extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        String pagRetorno = "";
        
        EquipamentoDAO maquinaDAO = new EquipamentoDAO(conexao);
        Equipamento maquina = (Equipamento)sessao.getAttribute("maquina");
        Manutencao manutencao = (Manutencao)sessao.getAttribute("manutencao");
        
        if (maquina == null)
            maquina = new Equipamento();
        

        List<Manutencao> lstManutencao = new ArrayList<Manutencao>();

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
        
        maquina.setTipo("MA");
        maquina.setDescricao(descricao);
        maquina.setSituacao(situacao);
        maquina.setPlaca(placa);
        maquina.setMarca(marca);
        maquina.setModelo(modelo);
        maquina.setAno(ano);
        maquina.setCapacidade(Double.parseDouble(capacidade));
        maquina.setLstManutencao(lstManutencao);        
        
        if (maquinaDAO.atualizar(maquina))
        {
            maquina.setIdEquipamento(maquinaDAO.getIdentity());
            maquina = maquinaDAO.listaUm(maquina);
            sessao.setAttribute("maquina", maquina);
            
            sessao.setAttribute("avisoErro", "Máquina de gelo atualizada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Maquina.AcaoAbreMaquina");
            pagRetorno = "visao/erro.jsp";              
        }
        
        return pagRetorno;
    }
}
