/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Avaria;
import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.ProdutoCamara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaDAO;
import DAO.MaquinaProducaoDAO;
import DAO.ProducaoDAO;
import DAO.ProdutoCamaraDAO;
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
public class AcaoSelecionaMaquinaProducao extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Producao.AcaoAbreProducao";
        
        List<ProdutoCamara> lstProdutoCamara = new ArrayList<ProdutoCamara>();
        List<Avaria> lstAvaria = new ArrayList<Avaria>();
        Producao producao = (Producao)sessao.getAttribute("producao");
        
        if (producao == null)
        {
            sessao.setAttribute("avisoErro", "Produção não selecionada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoListarProducao");
            pagRetorno = "visao/erro.jsp";                
        }
        else
        {

            MaquinaProducaoDAO maquinaProducaoDAO = new MaquinaProducaoDAO(conexao);
            MaquinaProducao maquinaProducao = new MaquinaProducao();
            String idMaquinaProducao = req.getParameter("idMaquinaProducao");
            maquinaProducao.setIdMaquinaProducao(Integer.parseInt(idMaquinaProducao));
            maquinaProducao = maquinaProducaoDAO.listaUm(maquinaProducao);
            
            if (maquinaProducao != null)
            {

                AvariaDAO avariaDAO = new AvariaDAO(conexao);
                lstAvaria = avariaDAO.listaTodos(null); 
                
                ProdutoCamaraDAO produtoCamaraDAO = new ProdutoCamaraDAO(conexao);
                ProdutoCamara produtoCamara = new ProdutoCamara();
                produtoCamara.setIdProduto(producao.getIdProduto());
                produtoCamara.setIdEquipamento(0);
                lstProdutoCamara = produtoCamaraDAO.listaTodos(produtoCamara);                
            }

            sessao.setAttribute("producao",producao);
            sessao.setAttribute("maquinaProducao",maquinaProducao);
            sessao.setAttribute("lstAvariaProducao",maquinaProducao.getLstAvariaProducao());
            sessao.setAttribute("lstTransferencia",maquinaProducao.getLstTransferenciaProducao());
            sessao.setAttribute("lstAvaria", lstAvaria);
            sessao.setAttribute("lstProdutoCamara", lstProdutoCamara);
            
        }
             
        return pagRetorno;
        
    }
}
