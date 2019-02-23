/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;


import Bean.Entrega;
import Bean.Equipamento;
import Bean.Produto;
import Bean.ProdutoCamara;
import Bean.ProdutoEntrega;
import Bean.SaidaCamara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import DAO.EquipamentoDAO;
import DAO.ProdutoDAO;
import DAO.SaidaCamaraDAO;
import java.sql.Connection;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoBuscarVeiculo extends Acao{ 
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Entrega.AcaoNovaRota";  
        boolean temRotaAtiva = false;
        
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");

        List<ProdutoEntrega> lstProdutoEntrega = null;
        
        String idVeiculo = (req.getParameter("cmbVeiculo").equals("") || req.getParameter("cmbVeiculo") == null) ? "0" : req.getParameter("cmbVeiculo");
        Equipamento equipamento = new Equipamento();
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(conexao);
        equipamento.setIdEquipamento(Integer.parseInt(idVeiculo));
        equipamento = equipamentoDAO.listaUm(equipamento);
        
        // verificando se há rota iniciada ou cadastrada para este veículo
        List<Entrega> lstEntrega = new ArrayList<Entrega>();
        lstEntrega = entregaDAO.listaTodos(null);
        Iterator itEntrega = lstEntrega.iterator();
        while (itEntrega.hasNext())
        {
            Entrega rota = (Entrega)itEntrega.next();
            if ((rota.getSituacao().equals("CD")) || (rota.getSituacao().equals("ET")))
                if (rota.getIdEquipamento() == equipamento.getIdEquipamento())
                    temRotaAtiva = true;
        }
        
        if (temRotaAtiva)
        {
            sessao.setAttribute("avisoErro", "Há rota ativa para o veículo selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoListarEntrega");
            pagRetorno = "visao/erro.jsp";               
        }
        else
        {
            // completando rota
            entrega.setVeiculo(equipamento);
            entrega.setKmInicial(equipamento.getQuilometragem());

            SaidaCamaraDAO saidaCamaraDAO = new SaidaCamaraDAO(conexao);
            List<SaidaCamara> lstSaidaCamara = new ArrayList<SaidaCamara>();
            lstSaidaCamara = saidaCamaraDAO.listaTodos(equipamento);
            lstProdutoEntrega = new ArrayList<ProdutoEntrega>();

             Iterator itSaidaCamara = lstSaidaCamara.iterator();
             while (itSaidaCamara.hasNext())
             {

                 SaidaCamara saidaCamara = (SaidaCamara)itSaidaCamara.next();

                 if (saidaCamara.getSituacao().equals("CA"))
                 {
                     ProdutoCamara produtoCamara = (ProdutoCamara)saidaCamara.getProdutoCamara();
                     ProdutoEntrega produtoEntrega = new ProdutoEntrega();
                     produtoEntrega.setIdProduto(produtoCamara.getIdProduto());
                     produtoEntrega.setDblQuantidade(saidaCamara.getSaida());

                     ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
                     Produto produto = new Produto();
                     produto.setIdProduto(produtoEntrega.getIdProduto());
                     produto = produtoDAO.listaUm(produto);
                     produtoEntrega.setProduto(produto);

                     lstProdutoEntrega.add(produtoEntrega);
                 }
             }
          
             if(entregaDAO.atualizar(entrega))
             {
                 entrega.setIdEntrega(entregaDAO.getIdentity());
                 entrega = entregaDAO.listaUm(entrega);
             }
             
             // produtos para entrega ainda não vinculada no banco
             // necessário confirmar início da entrega
             entrega.setLstProdutoEntrega(lstProdutoEntrega);
             
             sessao.setAttribute("entrega", entrega);
             //sessao.setAttribute("lstProdutoEntrega", lstProdutoEntrega);  
             
           
        }
        

        
        return pagRetorno;
    }
}

