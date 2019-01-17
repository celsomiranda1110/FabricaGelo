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
        
        String pagRetorno = "";
        
        ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
        Producao producao = (Producao)sessao.getAttribute("producao");

        if (producao.getSituacao().equals("PF"))
        {
            sessao.setAttribute("avisoErro", "Produção já finalizada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
            pagRetorno = "visao/erro.jsp";            
        }   
        else
        {
            double qtProducao = 0;
            double qtMaquinas = 0;
            double qtAvarias = 0;
            double qtTransf = 0;
            MaquinaProducaoDAO maqProdDAO = new MaquinaProducaoDAO(conexao);
            
            
            // verificando se quantidade produzida condiz com as diferenças
            // da quantidade de avarias
            producao = producaoDAO.listaUm(producao);
            qtProducao = producao.getQuantidade();
            
            // capturando quantidade das máquinas e quantidade de avarias
            Iterator itMaqProd = producao.getLstMaquinaProducao().iterator();
            while (itMaqProd.hasNext())
            {
                MaquinaProducao maqProd = (MaquinaProducao)itMaqProd.next();
                maqProd = maqProdDAO.listaUm(maqProd);
                qtMaquinas += maqProd.getQtProducao();
                
                Iterator itAvaria = maqProd.getLstAvariaProducao().iterator();
                while (itAvaria.hasNext())
                {
                    AvariaProducao avaProd = (AvariaProducao)itAvaria.next();
                    qtAvarias += avaProd.getQuantidade();
                }
            }
            
            //capturando a quantidade de transferências da produção para câmaras
            Iterator itTransf = producao.getLstTransferenciaProducao().iterator();
            while (itTransf.hasNext())
            {
                TransferenciaProducao transf = (TransferenciaProducao)itTransf.next();
                qtTransf += transf.getQuantidade();
            }
            
            // verificando quantidades
            if (qtProducao != (qtMaquinas - qtAvarias))
            {
                sessao.setAttribute("avisoErro", "Quantidade produzida diferente do total das máquinas");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
                pagRetorno = "visao/erro.jsp";                 
            }
            else if (qtProducao != qtTransf)
            {
                sessao.setAttribute("avisoErro", "Necessário transferir toda a produção para câmaras");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
                pagRetorno = "visao/erro.jsp";                 
            }
            else
            {
                String mensagem = "";
                
                // ajuste de saldos em câmaras e no produto
                ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
                Produto produto = new Produto();
                produto = producao.getProduto();
                produto.setSaldo(produto.getSaldo() + qtProducao);
                if (!produtoDAO.atualizar(produto))
                    mensagem = "Problemas ao atualizar saldo de produto";
                
                if (mensagem.equals(""))
                {
                    // atualizando saldo por câmara
                    ProdutoCamaraDAO produtoCamaraDAO = new ProdutoCamaraDAO(conexao);
                    Iterator itTransfSaldo = producao.getLstTransferenciaProducao().iterator();
                    while (itTransfSaldo.hasNext())
                    {
                        if (mensagem.equals(""))
                        {
                            TransferenciaProducao transf = (TransferenciaProducao)itTransfSaldo.next();
                            ProdutoCamara prodCamara = (ProdutoCamara)transf.getProdutoCamara();
                            prodCamara.setSaldo(prodCamara.getSaldo() + qtTransf);
                            prodCamara.setSaldoAnterior(prodCamara.getSaldo());
                            if (!produtoCamaraDAO.atualizar(prodCamara))
                                mensagem = "Problemas ao atualizar saldo de câmaras";
                        }
                    } 
                    

                }
                
                if (mensagem.equals(""))
                {
                    producao.setSituacao("PF");
                    if (!producaoDAO.atualizar(producao))
                        mensagem = "Problemas ao atualizar produção";
                }
                
                if (mensagem.equals(""))
                    mensagem = "Produção fechada e estoques atualizados";                
                
                sessao.setAttribute("maquinaProducao", null); 
                sessao.setAttribute("lstAvariaProducao", null);
                sessao.setAttribute("avariaProducao", null); 
                sessao.setAttribute("transf", null);
                
                sessao.setAttribute("avisoErro", mensagem);
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Producao.AcaoAbreProducao");
                pagRetorno = "visao/erro.jsp";                   
            }
            
        }
        
        return pagRetorno;
    }
}
