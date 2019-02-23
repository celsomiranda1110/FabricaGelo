/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.AvariaProducao;
import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.Produto;
import Bean.ProdutoCamara;
import Bean.TransferenciaProducao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MaquinaProducaoDAO;
import DAO.ProducaoDAO;
import DAO.ProdutoCamaraDAO;
import DAO.ProdutoDAO;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoFechaProducao extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/producaoFinalizar.jsp";
        
        ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
        Producao producao = (Producao)sessao.getAttribute("producao");

        if (producao == null)
        {
            sessao.setAttribute("avisoErro", "Produção não selecionada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoListarProducao");
            pagRetorno = "visao/erro.jsp";            
        }
        if (producao.getSituacao().equals("PF"))
        {
            sessao.setAttribute("avisoErro", "Produção já finalizada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";            
        }   
        else
        {
            // verificando se há transferência de produção para as câmeras
            // obrigatório
            double qtTransferencia = 0;
            boolean naoFecha = false;
            
            MaquinaProducaoDAO maquinaProducaoDAO = new MaquinaProducaoDAO(conexao);
            producao = producaoDAO.listaUm(producao);
            Iterator itMaquinaProducao = producao.getLstMaquinaProducao().iterator();
            while (itMaquinaProducao.hasNext())
            {
                qtTransferencia = 0;
                MaquinaProducao maquinaProducao = (MaquinaProducao)itMaquinaProducao.next();
                maquinaProducao = maquinaProducaoDAO.listaUm(maquinaProducao);
                Iterator itTransferenciaProducao = maquinaProducao.getLstTransferenciaProducao().iterator();
                while (itTransferenciaProducao.hasNext())
                {
                    TransferenciaProducao transferenciaProducao = (TransferenciaProducao)itTransferenciaProducao.next();
                    qtTransferencia += transferenciaProducao.getQuantidade();
                }
                if (qtTransferencia == 0)
                    naoFecha = true;
            }
            
            if (naoFecha)
            {
                sessao.setAttribute("avisoErro", "Há máquinas sem transferência para câmaras");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
                pagRetorno = "visao/erro.jsp" ;              
            }
            
        }
        
        sessao.setAttribute("producao", producao);
        
        return pagRetorno;
    }
}
