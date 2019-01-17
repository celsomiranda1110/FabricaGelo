/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.MaquinaProducao;
import Bean.Producao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MaquinaProducaoDAO;
import DAO.ProducaoDAO;
import java.sql.Connection;
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
        
        Producao producao = (Producao)sessao.getAttribute("producao");
        if (producao != null)
        {
            
            ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
            producao = producaoDAO.listaUm(producao);
            
            MaquinaProducaoDAO maquinaProducaoDAO = new MaquinaProducaoDAO(conexao);
            MaquinaProducao maquinaProducao = new MaquinaProducao();
            
            String idMaquinaProducao = req.getParameter("idMaquinaProducao");
            
            maquinaProducao.setIdMaquinaProducao(Integer.parseInt(idMaquinaProducao));
            maquinaProducao = maquinaProducaoDAO.listaUm(maquinaProducao);
            
            sessao.setAttribute("producao",producao);
            sessao.setAttribute("lstMaquinaProducao",producao.getLstMaquinaProducao());
            sessao.setAttribute("maquinaProducao",maquinaProducao);
            sessao.setAttribute("lstAvariaProducao",maquinaProducao.getLstAvariaProducao());
            sessao.setAttribute("avariaProducao", null);
        }
             
        return "visao/producao.jsp";
        
    }
}
