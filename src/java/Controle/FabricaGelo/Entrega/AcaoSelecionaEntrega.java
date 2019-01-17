/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Entrega;
import Bean.Equipamento;
import Bean.Profissional;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import DAO.EquipamentoDAO;
import DAO.ProfissionalDAO;
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
public class AcaoSelecionaEntrega extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "";

        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = new Entrega();
        String idEntrega = req.getParameter("idEntrega");
        entrega.setIdEntrega(Integer.parseInt(idEntrega));
        entrega = entregaDAO.listaUm(entrega);
        
        
        
        if (entrega.getSituacao().equals("CD"))
            pagRetorno = "FabricaGelo.Entrega.AcaoNovaRota";  
        
        sessao.setAttribute("entrega", entrega);
        
        return pagRetorno;
        
//        EntregaDAO entregaDAO = new EntregaDAO(conexao);
//        Entrega entrega = new Entrega();
//        String idEntrega = req.getParameter("idEntrega");
//        entrega.setIdEntrega(Integer.parseInt(idEntrega));
//        entrega = entregaDAO.listaUm(entrega);
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
//        
//        sessao.setAttribute("entrega",entrega);
//        sessao.setAttribute("lstProdutoEntrega",entrega.getLstProdutoEntrega());
//        sessao.setAttribute("lstClienteVisita",entrega.getLstColaboradorEntrega());
//        sessao.setAttribute("lstCustoEntrega",entrega.getLstCustoEntrega());
//        sessao.setAttribute("lstAbastecimento",entrega.getLstAbastecimento());
//        sessao.setAttribute("lstProfissional",lstProfissional);
//        sessao.setAttribute("lstVeiculo",lstVeiculo);
//        sessao.setAttribute("custoEntrega",null);
//        sessao.setAttribute("avariaEntrega",null);
//        sessao.setAttribute("produtoEntrega",null);
//        sessao.setAttribute("lstAvariaEntrega",null);
//        sessao.setAttribute("abastecimento",null);
//        
//        return "visao/entrega.jsp";
    }
}
