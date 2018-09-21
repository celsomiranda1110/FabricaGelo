/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.AvariaProducao;
import Bean.MaquinaProducao;
import Bean.Producao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.AvariaProducaoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoSelecionaAvariaProducao extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        Producao producao = (Producao)sessao.getAttribute("producao");
        if (producao != null)
        {
            MaquinaProducao maquinaProducao = (MaquinaProducao)sessao.getAttribute("maquinaProducao");
            if (maquinaProducao != null)
            {
                AvariaProducaoDAO avariaProducaoDAO = new AvariaProducaoDAO(conexao);
                AvariaProducao avariaProducao = new AvariaProducao();
                
                String idAvariaProducao = req.getParameter("idAvariaProducao");
                avariaProducao.setIdAvariaProducao(Integer.parseInt(idAvariaProducao));
                
                avariaProducao = avariaProducaoDAO.listaUm(avariaProducao);
                
                sessao.setAttribute("avariaProducao", avariaProducao);
                
                
            }
        }
        
        return "visao/producao.jsp";
    }
}
