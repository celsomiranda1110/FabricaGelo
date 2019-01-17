/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Carregamento;

import Bean.Equipamento;
import Bean.Produto;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EquipamentoDAO;
import DAO.ProdutoDAO;
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
public class AcaoAbreCarregamento extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/saidaCamara.jsp";
        
        EquipamentoDAO veiculoDAO = new EquipamentoDAO(conexao);
        List<Equipamento> lstVeiculo = new ArrayList<Equipamento>();
        Equipamento veiculo = new Equipamento();
        veiculo.setTipo("VE");
        lstVeiculo = veiculoDAO.listaTodos(veiculo); 
        
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        Produto produto = new Produto();
        List<Produto> lstProduto = new ArrayList<Produto>();
        produto.setTipo("PR");
        lstProduto = produtoDAO.listaTodos(produto);        

        sessao.setAttribute("lstVeiculo",lstVeiculo);  
        sessao.setAttribute("lstProduto", lstProduto);
        
        return pagRetorno;
    }
    
}
