/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Avaria;
import Bean.Entrega;
import Bean.AvariaEntrega;
import Bean.Equipamento;
import Bean.Movimento;
import Bean.ProdutoEntrega;
import Bean.Profissional;
import Bean.SaidaCamara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaDAO;
import DAO.EntregaDAO;
import DAO.EquipamentoDAO;
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
public class AcaoAbreEntrega extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
//        EntregaDAO entregaDAO = new EntregaDAO(conexao);
//        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
//        entrega = entregaDAO.listaUm(entrega);
//        
//        if (entrega != null)
//        {
//            SaidaCamaraDAO saidaCamaraDAO = new SaidaCamaraDAO(conexao);
//            entrega = entregaDAO.listaUm(entrega);
//            
//            if (entrega.getSituacao().equals("CD"))
//            {
//                List<SaidaCamara> lstSaidaCamara = new ArrayList<SaidaCamara>();
//                lstSaidaCamara = saidaCamaraDAO.listaTodos(entrega.getVeiculo());
//                Iterator itCarregamento = lstSaidaCamara.iterator();
//                while (itCarregamento.hasNext())
//                {
//                    SaidaCamara saidaCamara = (SaidaCamara)itCarregamento.next();
//                    saidaCamara = saidaCamaraDAO.listaUm(saidaCamara);
//                    ProdutoEntrega produtoEntrega = new ProdutoEntrega();
//                    produtoEntrega.setIdProduto((saidaCamara.getProdutoCamara()).getIdProduto());
//                    produtoEntrega.setDblQuantidade(saidaCamara.getSaida());
//                }
//            }
//        }

//        EntregaDAO entregaDAO = new EntregaDAO(conexao);
//        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
//        if (entrega != null)
//            entrega = entregaDAO.listaUm(entrega);
//        
//        ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
//        List<Profissional> lstProfissional = new ArrayList<Profissional>();
//        lstProfissional = profissionalDAO.listaTodos();
//        
//        EquipamentoDAO veiculoDAO = new EquipamentoDAO(conexao);
//        List<Equipamento> lstVeiculo = new ArrayList<Equipamento>();
//        Equipamento veiculo = new Equipamento();
//        veiculo.setTipo("VE");
//        lstVeiculo = veiculoDAO.listaTodos(veiculo);        
//        
//        List<Avaria> lstAvaria = new ArrayList<Avaria>();
//        AvariaDAO avariaDAO = new AvariaDAO(conexao);
//        lstAvaria = avariaDAO.listaTodos();
//        
//        sessao.setAttribute("entrega",entrega);
//        sessao.setAttribute("lstProdutoEntrega",(entrega == null ? null : entrega.getLstProdutoEntrega()));
//        sessao.setAttribute("lstClienteVisita",(entrega == null ? null : entrega.getLstColaboradorEntrega()));
//        sessao.setAttribute("lstCustoEntrega",(entrega == null ? null : entrega.getLstCustoEntrega()));
//        sessao.setAttribute("lstAbastecimento",(entrega == null ? null : entrega.getLstAbastecimento()));
//        sessao.setAttribute("lstProfissional",lstProfissional);
//        sessao.setAttribute("lstVeiculo",lstVeiculo);
//        sessao.setAttribute("lstAvaria",lstAvaria);
//        sessao.setAttribute("custoEntrega",null);
//        sessao.setAttribute("avariaEntrega",null);
//        sessao.setAttribute("produtoEntrega",null);
//        sessao.setAttribute("lstAvariaEntrega",null);
//        sessao.setAttribute("abastecimento",null);
//return "visao/entrega.jsp";        

        String pagRetorno = "";

        
        
        return pagRetorno;
    }
}
