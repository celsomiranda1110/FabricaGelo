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

        if (camara == null)
            camara = new Equipamento();
        
        String descricao = (req.getParameter("txtDescricao").equals("") || req.getParameter("txtDescricao") == null) ? "" : req.getParameter("txtDescricao").toUpperCase();
        String placa = "";
        String marca = "";
        String modelo = "";
        String ano = "";
        String capacidade = (req.getParameter("txtCapacidade").equals("") || req.getParameter("txtCapacidade") == null) ? "0" : req.getParameter("txtCapacidade");
        String inativo = (req.getParameter("ck_Ativo") == null ? "A" : "I");
     
        camara.setTipo("CA");
        camara.setDescricao(descricao);
        camara.setSituacao("A");
        camara.setPlaca(placa);
        camara.setMarca(marca);
        camara.setModelo(modelo);
        camara.setAno(ano);
        camara.setCapacidade(Double.parseDouble(capacidade));
        camara.setAtivo(inativo);
        camara.setLstManutencao(null);
        
        
        if (camaraDAO.atualizar(camara))
        {
            camara.setIdEquipamento(camaraDAO.getIdentity());
            camara = camaraDAO.listaUm(camara);
            sessao.setAttribute("camara", camara);
            
            sessao.setAttribute("avisoErro", "Câmara atualizada");
            sessao.setAttribute("tipoAviso","alert alert-success");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Camara.AcaoAbreCamara");
            pagRetorno = "visao/erro.jsp";             
        }
        
       
        return pagRetorno;
    }
}
