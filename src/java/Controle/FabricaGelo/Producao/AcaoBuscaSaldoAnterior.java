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
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoBuscaSaldoAnterior extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/producao.jsp";
        
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
            double saldoAnterior = 0;
            String idMaquina = (req.getParameter("cmbMaquina").equals("") || req.getParameter("cmbMaquina") == null) ? "0" : req.getParameter("cmbMaquina");
            String idEmbalagem = (req.getParameter("cmbEmbalagem").equals("") || req.getParameter("cmbEmbalagem") == null) ? "0" : req.getParameter("cmbEmbalagem");            
            
            //  saldo anterior
            MaquinaProducao maquinaProducao = new MaquinaProducao();
            maquinaProducao.setIdEquipamento(Integer.parseInt(idMaquina));
            maquinaProducao.setIdProduto(Integer.parseInt(idEmbalagem));
            if (maquinaProducao.getIdEquipamento() == 0 || maquinaProducao.getIdProduto() == 0)
            {
                sessao.setAttribute("avisoErro", "Embalagem ou Máquina não selecionado");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
                pagRetorno = "visao/erro.jsp";            
            }            
            ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
            if (producao == null)
                producao = new Producao();
            producao.setSituacao("PF");
            
            Iterator itProducao = producaoDAO.listaTodos(producao).iterator();
            while (itProducao.hasNext())
            {
                Iterator itMaquinaProducao = producao.getLstMaquinaProducao().iterator();
                while (itMaquinaProducao.hasNext())
                {
                    MaquinaProducao _maquinaProducao = (MaquinaProducao)itMaquinaProducao.next();
                    if ((maquinaProducao.getIdProduto() == _maquinaProducao.getIdProduto()) && (maquinaProducao.getIdEquipamento() == _maquinaProducao.getIdEquipamento()))
                        saldoAnterior = maquinaProducao.getQtSaldo();

                }

            }
            
            maquinaProducao.setQtSaldoAnterior(saldoAnterior);
            
            sessao.setAttribute("maquinaProducao", maquinaProducao);
            
        }
        
        return pagRetorno;
        
    }
}
