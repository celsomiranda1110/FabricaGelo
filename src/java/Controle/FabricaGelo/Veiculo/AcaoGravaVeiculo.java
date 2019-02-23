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

        String pagRetorno = "FabricaGelo.Veiculo.AcaoAbreVeiculo";
        
        EquipamentoDAO veiculoDAO = new EquipamentoDAO(conexao);
        Equipamento veiculo = (Equipamento)sessao.getAttribute("veiculo");
       
        
        // dados do veiculo
        String descricao = (req.getParameter("txtDescricao").equals("") || req.getParameter("txtDescricao") == null) ? "" : req.getParameter("txtDescricao").toUpperCase();
        String placa = (req.getParameter("txtPlaca").equals("") || req.getParameter("txtPlaca") == null) ? "" : req.getParameter("txtPlaca").toUpperCase();
        String marca = (req.getParameter("txtMarca").equals("") || req.getParameter("txtMarca") == null) ? "" : req.getParameter("txtMarca").toUpperCase();
        String modelo = (req.getParameter("txtModelo").equals("") || req.getParameter("txtModelo") == null) ? "" : req.getParameter("txtModelo").toUpperCase();
        String ano = (req.getParameter("txtAno") == "" || req.getParameter("txtAno") == null) ? "" : req.getParameter("txtAno");
        String quilometragem = (req.getParameter("txtQuilometragem").equals("") || req.getParameter("txtQuilometragem") == null) ? "0" : req.getParameter("txtQuilometragem");
        String capacidade = (req.getParameter("txtCapacidade").equals("") || req.getParameter("txtCapacidade") == null) ? "0" : req.getParameter("txtCapacidade");
        String inativo = (req.getParameter("ck_Ativo") == null ? "A" : "I");
        
        // trocando víurgula por ponto
        capacidade = capacidade.replace(",", ".");
        
        if (veiculo == null)
            veiculo = new Equipamento();
        
        veiculo.setTipo("VE");
        veiculo.setDescricao(descricao);
        veiculo.setSituacao("A");
        veiculo.setPlaca(placa);
        veiculo.setMarca(marca);
        veiculo.setModelo(modelo);
        veiculo.setAno(ano);
        veiculo.setQuilometragem(Double.parseDouble(quilometragem));
        veiculo.setCapacidade(Double.parseDouble(capacidade));
        veiculo.setAtivo(inativo);
        veiculo.setLstManutencao(null);   
        
        
        if (veiculo.getDescricao().equals(""))
        {
            sessao.setAttribute("avisoErro", "Necessário informar descrição do veícullo");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Veiculo.AcaoAbreVeiculo");
            pagRetorno = "visao/erro.jsp";              
        }        
        else
        {
            if (veiculoDAO.atualizar(veiculo))
            {
                veiculo.setIdEquipamento(veiculoDAO.getIdentity());
                veiculo = veiculoDAO.listaUm(veiculo);
                sessao.setAttribute("veiculo", veiculo);

                sessao.setAttribute("avisoErro", "Dados do Veículo atualizados");
                sessao.setAttribute("tipoAviso","alert alert-success");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Veiculo.AcaoAbreVeiculo");
                pagRetorno = "visao/erro.jsp"; 
            }
        }
        
        return pagRetorno;
    }
}
