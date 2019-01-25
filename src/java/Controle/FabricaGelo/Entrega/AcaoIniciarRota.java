/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Entrega;
import Bean.Equipamento;
import Bean.ProdutoCamara;
import Bean.ProdutoEntrega;
import Bean.SaidaCamara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import DAO.SaidaCamaraDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoIniciarRota extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/rotaIniciar.jsp";
        
        // buscando entrega
        EntregaDAO entregaDAO = new EntregaDAO(conexao);
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        entrega = entregaDAO.listaUm(entrega);
        
        String hrInicial = (req.getParameter("txtHrInicial").equals("") || req.getParameter("txtHrInicial") == null) ? "0" : req.getParameter("txtHrInicial");
        String mnInicial = (req.getParameter("txtMnInicial").equals("") || req.getParameter("txtMnInicial") == null) ? "0" : req.getParameter("txtMnInicial");
        int hInicial = Integer.parseInt(hrInicial);
        int mInicial = Integer.parseInt(mnInicial);
        
        if (entrega == null)
        {
            sessao.setAttribute("avisoErro", "Rota não cadastrada");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoListarEntrega");
            pagRetorno = "visao/erro.jsp";              
        }
        else if (entrega.getLstColaboradorEntrega() == null)
        {
            sessao.setAttribute("avisoErro", "Rota deve possuir clientes.");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoNovaRota");
            pagRetorno = "visao/erro.jsp";              
        }
        else if (entrega.getLstProfissionalEntrega() == null)
        {
            sessao.setAttribute("avisoErro", "Rota deve possuir profissionais.");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoNovaRota");
            pagRetorno = "visao/erro.jsp";              
        }
        else if( ( (hInicial <= 0) || (hInicial > 24) ) || ( (mInicial <= 0) || (mInicial > 60) ) )
        {
            sessao.setAttribute("avisoErro", "Horário indefinido");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoNovaRota");
            pagRetorno = "visao/erro.jsp";              
        }        
        else
        {
            // Necessário haver produtos carregados nos veículos
            
            Equipamento veiculo = (Equipamento)entrega.getVeiculo();
            
            List<SaidaCamara> lstSaidaCamara = null;
            List<SaidaCamara> lstTabela = new ArrayList<SaidaCamara>();
            SaidaCamaraDAO saidaCamaraDAO = new SaidaCamaraDAO(conexao);
            
            lstTabela = saidaCamaraDAO.listaTodos(veiculo);
            Iterator itSaidaCamara = lstTabela.iterator();
            while (itSaidaCamara.hasNext())
            {
                SaidaCamara saidaCamara = (SaidaCamara)itSaidaCamara.next();
                if (saidaCamara.getSituacao().equals("CA")) // veículo carregado
                {
                    if (lstSaidaCamara == null)
                        lstSaidaCamara = new ArrayList<SaidaCamara>();
                    lstSaidaCamara.add(saidaCamara);
                }
            }
            if (lstSaidaCamara == null)
            {
                sessao.setAttribute("avisoErro", "Não há carga no veículo");
                sessao.setAttribute("pagOrigemErro", "Fabricaelo.Entrega.AcaoNovaRota");
                pagRetorno = "visao/erro.jsp";                   
            }
            else
            {
                // realizando transferência de carregamento para 
                // produtos de rota
                
                List<ProdutoEntrega> lstProdutoEntrega = new ArrayList<ProdutoEntrega>();
                itSaidaCamara = lstTabela.iterator();
                while (itSaidaCamara.hasNext())
                {
                    SaidaCamara saidaCamara = (SaidaCamara)itSaidaCamara.next();
                    if (saidaCamara.getSituacao().equals("CA")) // veículo carregado
                    {
                        saidaCamara = saidaCamaraDAO.listaUm(saidaCamara);
                        ProdutoCamara produtoCamara = (ProdutoCamara)saidaCamara.getProdutoCamara();
                        
                        ProdutoEntrega produtoEntrega = new ProdutoEntrega();
                        produtoEntrega.setIdProduto(produtoCamara.getIdProduto());
                        produtoEntrega.setDblQuantidade(saidaCamara.getSaida());
                        produtoEntrega.setSituacao("VI");
                        lstProdutoEntrega.add(produtoEntrega);
                    }
                }
                
                // definindo valores de início de rota
                entrega.setHrSaida(hrInicial+":"+mnInicial);
                entrega.setSituacao("ET");
                entrega.setLstProdutoEntrega(lstProdutoEntrega);
                

                if (!entregaDAO.atualizar(entrega))
                {
                    sessao.setAttribute("avisoErro", "Ocorreu um erro ao iniciar rota.");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoNovaRota");
                    pagRetorno = "visao/erro.jsp";                   
                }
                else
                {
                    sessao.setAttribute("avisoErro", "Rota iniciada com sucesso.");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Entrega.AcaoNovaRota");
                    pagRetorno = "visao/erro.jsp";                   
                    
                }
                    
            }
        }
        return pagRetorno;
        
    }
}
