/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Colaborador;
import Bean.Entrega;
import Bean.Equipamento;
import Bean.Produto;
import Bean.ProdutoCamara;
import Bean.ProdutoEntrega;
import Bean.Profissional;
import Bean.SaidaCamara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import DAO.EntregaDAO;
import DAO.EquipamentoDAO;
import DAO.ProdutoDAO;
import DAO.ProfissionalDAO;
import DAO.SaidaCamaraDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoNovaRota extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/rotaIniciar.jsp";
        
        List<ProdutoEntrega> lstProdutoEntrega = null;
        
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        if (entrega == null)
            entrega = new Entrega();
        else
            entrega = entregaDAO.listaUm(entrega);
        
        EquipamentoDAO veiculoDAO = new EquipamentoDAO(conexao);
        List<Equipamento> lstVeiculo = new ArrayList<Equipamento>();
        Equipamento veiculo = new Equipamento();
        veiculo.setTipo("VE");
        lstVeiculo = veiculoDAO.listaTodos(veiculo);     
        
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
        List<Profissional> lstProfissional = new ArrayList<Profissional>();
        lstProfissional = profissionalDAO.listaTodos();   
        
        // carrega o carregamento do veículo
        // ainda não vinculada à entrega
        if (entrega.getVeiculo() != null)
        {
            Equipamento _veiculo = (Equipamento)entrega.getVeiculo();
            SaidaCamaraDAO saidaCamaraDAO = new SaidaCamaraDAO(conexao);
            List<SaidaCamara> lstSaidaCamara = new ArrayList<SaidaCamara>();
            lstSaidaCamara = saidaCamaraDAO.listaTodos(_veiculo);
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
          
             
             // produtos para entrega ainda não vinculada no banco
             // necessário confirmar início da entrega
             entrega.setLstProdutoEntrega(lstProdutoEntrega);
            
        }
        
        sessao.setAttribute("entrega", entrega);
        sessao.setAttribute("lstProfissionalEntrega", entrega.getLstProfissionalEntrega());
        sessao.setAttribute("lstColaboradorEntrega", entrega.getLstColaboradorEntrega());
        sessao.setAttribute("lstProdutoEntrega", entrega.getLstProdutoEntrega());
        sessao.setAttribute("lstVeiculo",lstVeiculo);
        sessao.setAttribute("lstProfissional",lstProfissional);
        
        
        return pagRetorno;
        
    }
}
