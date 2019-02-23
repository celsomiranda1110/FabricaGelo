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

        
        if (maquina == null)
            maquina = new Equipamento();
        



        String descricao = (req.getParameter("txtDescricao").equals("") || req.getParameter("txtDescricao") == null) ? "" : req.getParameter("txtDescricao").toUpperCase();
        String inativo = (req.getParameter("ck_Ativo") == null ? "A" : "I");
        String placa = "";
        String marca = (req.getParameter("txtMarca").equals("") || req.getParameter("txtMarca") == null) ? "A" : req.getParameter("txtMarca").toUpperCase();
        String modelo = (req.getParameter("txtModelo").equals("") || req.getParameter("txtModelo") == null) ? "A" : req.getParameter("txtModelo").toUpperCase();
        String ano = "";
        String capacidade = "0";
        
        
        maquina.setTipo("MA");
        maquina.setDescricao(descricao);
        maquina.setSituacao("A");
        maquina.setPlaca(placa);
        maquina.setMarca(marca);
        maquina.setModelo(modelo);
        maquina.setAno(ano);
        maquina.setCapacidade(Double.parseDouble(capacidade));
        maquina.setAtivo(inativo);
        maquina.setLstManutencao(null);        
        
        if (maquinaDAO.atualizar(maquina))
        {
            maquina.setIdEquipamento(maquinaDAO.getIdentity());
            maquina = maquinaDAO.listaUm(maquina);
            sessao.setAttribute("maquina", maquina);
            
            sessao.setAttribute("avisoErro", "Equipamento atualizado");
            sessao.setAttribute("tipoAviso","alert alert-success");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Maquina.AcaoAbreMaquina");
            pagRetorno = "visao/erro.jsp";              
        }
        
        return pagRetorno;
    }
}
