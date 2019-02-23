/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Producao;

import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.TransferenciaProducao;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.MaquinaProducaoDAO;
import DAO.TransferenciaProducaoDAO;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoAdicionaTransferencia extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "FabricaGelo.Producao.AcaoAbreProducao";
        
        Producao producao = (Producao)sessao.getAttribute("producao");
        MaquinaProducao maquinaProducao = (MaquinaProducao)sessao.getAttribute("maquinaProducao");

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
        else if (maquinaProducao == null)
        {
            sessao.setAttribute("avisoErro", "Produção por máquina não identificada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";             
        }
        else if (maquinaProducao.getQtReposicao() == 0)
        {
            sessao.setAttribute("avisoErro", "Reposição não atualizada");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";             
        } 
        else if (maquinaProducao.getQtSaldo() == 0)
        {
            sessao.setAttribute("avisoErro", "Saldo zerado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";             
        }      
        else
        {
            
            // objeto transferência da produção
            String idProdutoCamara = (req.getParameter("cmbProdutoCamara").equals("") || req.getParameter("cmbProdutoCamara") == null) ? "0" : req.getParameter("cmbProdutoCamara");
            String qtTransferencia = (req.getParameter("txtQuantidadeTransferencia").equals("") || req.getParameter("txtQuantidadeTransferencia") == null) ? "0" : req.getParameter("txtQuantidadeTransferencia");
            
            TransferenciaProducao transferencia = new TransferenciaProducao();
            transferencia.setIdMaquinaProducao(maquinaProducao.getIdMaquinaProducao());
            transferencia.setIdProdutoCamara(Integer.parseInt(idProdutoCamara));
            transferencia.setQuantidade(Double.parseDouble(qtTransferencia));
            transferencia.setDataFormatada("1900-01-01");
            
            if (transferencia.getIdProdutoCamara() == 0)
            {
                sessao.setAttribute("avisoErro", "Câmara não selecionada");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
                pagRetorno = "visao/erro.jsp";                 
            }
            if (maquinaProducao.getQtSaldo() < transferencia.getQuantidade())
            {
                sessao.setAttribute("avisoErro", "Quantidade de transferência supera ao saldo da máquina");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
                pagRetorno = "visao/erro.jsp";                 
            }                
            else
            {
                TransferenciaProducaoDAO transferenciaProducaoDAO = new TransferenciaProducaoDAO(conexao);
                if (transferenciaProducaoDAO.atualizar(transferencia))
                {
                    MaquinaProducaoDAO maquinaProducaoDAO = new MaquinaProducaoDAO(conexao);
                    if (maquinaProducaoDAO.atualizar(maquinaProducao))
                        maquinaProducao = maquinaProducaoDAO.listaUm(maquinaProducao);
                    
                    sessao.setAttribute("transf", null);
                    sessao.setAttribute("maquinaProducao",maquinaProducao);
                    sessao.setAttribute("lstAvariaProducao",maquinaProducao.getLstAvariaProducao());
                    sessao.setAttribute("lstTransferencia",maquinaProducao.getLstTransferenciaProducao());
                    sessao.setAttribute("avisoErro", "Transferência atualizada");
                    sessao.setAttribute("tipoAviso","alert alert-success");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
                    pagRetorno = "visao/erro.jsp";                     
                }
                        
            }
            
        }
        return pagRetorno;
    }
}
