/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Equipamento;
import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.Produto;
import Bean.Profissional;
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
public class AcaoBuscarMaquina extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "";
        
        Producao producao = (Producao)sessao.getAttribute("producao");
        if (producao == null)
        {
            sessao.setAttribute("avisoErro", "Produção não selecionada.");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";             
        } 
        else
        {
            List<Equipamento> lstMaquina = new ArrayList<Equipamento>();
            EquipamentoDAO maquinaDAO = new EquipamentoDAO(conexao);   
            Equipamento equipamento = new Equipamento();
            equipamento.setTipo("MA");
            lstMaquina = maquinaDAO.listaTodos(equipamento);

            sessao.setAttribute("producao",producao);
            sessao.setAttribute("maquinaProducao", null);
            sessao.setAttribute("lstMaquina",lstMaquina);
            pagRetorno = "visao/listarMaquina.jsp";            
        }
        
        return pagRetorno; 
    }
    
}
