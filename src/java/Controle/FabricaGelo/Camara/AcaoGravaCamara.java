/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Camara;

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
public class AcaoGravaCamara extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);

        String pagRetorno = "";
        
        EquipamentoDAO camaraDAO = new EquipamentoDAO(conexao);
        Equipamento camara = (Equipamento)sessao.getAttribute("camara");
        Manutencao manutencao = (Manutencao)sessao.getAttribute("manutencao");
        if (camara == null)
            camara = new Equipamento();
        
        List<Manutencao> lstManutencao = new ArrayList<Manutencao>();
        String descricao = (req.getParameter("txtDescricao").equals("") || req.getParameter("txtDescricao") == null) ? "" : req.getParameter("txtDescricao");
        String situacao = (req.getParameter("cmbSituacao").equals("") || req.getParameter("cmbSituacao") == null) ? "A" : req.getParameter("cmbSituacao");
        String placa = "";
        String marca = "";
        String modelo = "";
        String ano = "";
        String capacidade = (req.getParameter("txtCapacidade").equals("") || req.getParameter("txtCapacidade") == null) ? "0" : req.getParameter("txtCapacidade");
        
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
     
        camara.setTipo("CA");
        camara.setDescricao(descricao);
        camara.setSituacao(situacao);
        camara.setPlaca(placa);
        camara.setMarca(marca);
        camara.setModelo(modelo);
        camara.setAno(ano);
        camara.setCapacidade(Double.parseDouble(capacidade));
        camara.setLstManutencao(lstManutencao);
        
        
        if (camaraDAO.atualizar(camara))
        {
            camara.setIdEquipamento(camaraDAO.getIdentity());
            camara = camaraDAO.listaUm(camara);
            sessao.setAttribute("camara", camara);
            
            sessao.setAttribute("avisoErro", "Câmara atualizada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Camara.AcaoAbreCamara");
            pagRetorno = "visao/erro.jsp";             
        }
        
       
        return pagRetorno;
    }
}
