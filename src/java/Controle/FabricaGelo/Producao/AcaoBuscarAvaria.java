/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.Avaria;
import Bean.AvariaProducao;
import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.Profissional;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaDAO;
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
public class AcaoBuscarAvaria extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "";
        
        // objeto produção
        Producao producao = (Producao)sessao.getAttribute("producao");
        MaquinaProducao maquinaProducao = (MaquinaProducao)sessao.getAttribute("maquinaProducao");
        
        if (producao == null)
        {
            sessao.setAttribute("avisoErro", "Produção não selecionada.");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";             
        }       
        else if (maquinaProducao == null)
        {
            sessao.setAttribute("avisoErro", "Máquina de produção não selecionada.");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            List<Avaria> lstAvaria = new ArrayList<Avaria>();
            AvariaDAO avariaDAO = new AvariaDAO(conexao);
            lstAvaria = avariaDAO.listaTodos();

            sessao.setAttribute("producao",producao);
            sessao.setAttribute("maquinaProducao",maquinaProducao);
            sessao.setAttribute("avariaProducao",null);
            sessao.setAttribute("lstAvaria",lstAvaria);
            sessao.setAttribute("pagRetorno", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/listarAvaria.jsp";             
        }

        return pagRetorno; 
    }
    
}

