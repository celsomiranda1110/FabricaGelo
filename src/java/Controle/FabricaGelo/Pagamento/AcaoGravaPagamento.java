/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Pagamento;

import Bean.Movimento;
import Bean.Pagamento;
import Bean.Parcela;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.PagamentoDAO;
import DAO.ParcelaDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoGravaPagamento extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "";

        PagamentoDAO pagamentoDAO = new PagamentoDAO(conexao);
        List<Parcela> lstParcela = new ArrayList<Parcela>();
        
        // dados do pagamento

        Pagamento pagamento = (Pagamento)sessao.getAttribute("pagamento");
        if ((pagamento.getSituacao().equals("PG")))
        {
            sessao.setAttribute("avisoErro", "Pagamento liquidado");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Pagamento.AcaoAbrePagamento");
            pagRetorno = "visao/erro.jsp";             
        }
//        else if (pagamento.getNumParcela() != numParcela && pagamento.getSituacao().equals("PP"))
//        {
//            sessao.setAttribute("avisoErro", "Pagamento com parcelas definidas");
//            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Pagamento.AcaoAbrePagamento");
//            pagRetorno = "visao/erro.jsp";                 
//        }
        else if (!pagamento.isProcessar())
        {
            sessao.setAttribute("avisoErro", "Pagamento não processável.");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Pagamento.AcaoAbrePagamento");
            pagRetorno = "visao/erro.jsp";            
        }
        else 
        {  
            // salva guarda de parcela, se existir
            Parcela parcela = (Parcela)sessao.getAttribute("parcela");
            if (parcela != null)
            {
                if (parcela.getSituacao().equals("G"))
                {
                    sessao.setAttribute("avisoErro", "Parcela paga");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Pagamento.AcaoAbrePagamento");
                    pagRetorno = "visao/erro.jsp";                 
                }
                else
                {
                    // dados da parcela
                    String dtPagamento = (req.getParameter("txtDtPagamento").equals("") || req.getParameter("txtDtPagamento") == null) ? "" : req.getParameter("txtDtPagamento");
                    String formaPagamento = (req.getParameter("cmbFormaPagamento").equals("") || req.getParameter("cmbFormaPagamento") == null) ? "" : req.getParameter("cmbFormaPagamento");        
                    String vlPago = (req.getParameter("txtValorPago").equals("") || req.getParameter("txtValorPago") == null) ? "" : req.getParameter("txtValorPago");                            

                    // Atualizando parcela
                    ParcelaDAO parcelaDAO = new ParcelaDAO(conexao);
                    parcela.setDtPagamentoFormatado(dtPagamento);
                    parcela.setValorPago(Double.parseDouble(vlPago));
                    parcela.setFormaPagamento(formaPagamento);
                    if (!formaPagamento.equals(""))
                        parcela.setSituacao("G");
                    
                    if (parcelaDAO.atualizar(parcela))
                    {
                        boolean encerraPagamento = false;
                        
                        // verificando se todas as parcelas foram pagas para alteração
                        // de situação do pagamento
                        pagamento = pagamentoDAO.listaUm(pagamento);
                        lstParcela = pagamento.getLstParcela();

                        Iterator itParcela = lstParcela.iterator();
                        while (itParcela.hasNext())
                        {
                            Parcela _parcela = (Parcela)itParcela.next();
                            if (_parcela.getSituacao().equals("G"))
                                encerraPagamento = true;
                            else
                                encerraPagamento = false;
                        }
                        if (encerraPagamento)
                        {
                            pagamento.setSituacao("PG");
                            pagamentoDAO.atualizar(pagamento);
                        }                        
                    }
                    
                    
                    
                }
            }
            else // não existindo parcela, atualiza dados do pagamento
            {
                boolean alteraPagamento = true;
                

                
                // verificando se pagamento pode ser atualizada
                pagamento = pagamentoDAO.listaUm(pagamento);
                lstParcela = pagamento.getLstParcela();                
                Iterator itParcela = lstParcela.iterator();
                while (itParcela.hasNext())
                {
                    // havendo parcela paga, não altera pagamento
                    Parcela _parcela = (Parcela)itParcela.next();
                    if (_parcela.getSituacao().equals("G"))
                        alteraPagamento = false;
                    
                } 
                
                if (alteraPagamento)
                {
                    int diasVencimento = 0;
                    double vlParcela = 0;
                    int nParc = 0;
                    
                    String cobranca = (req.getParameter("cmbCobranca").equals("") || req.getParameter("cmbCobranca") == null) ? "" : req.getParameter("cmbCobranca");
                    int numParcela = (req.getParameter("txtParcelas").equals("") || req.getParameter("txtParcelas") == null) ? 1 : Integer.parseInt(req.getParameter("txtParcelas"));                                               
                    String formaPagamento = (req.getParameter("cmbFormaPagamento").equals("") || req.getParameter("cmbFormaPagamento") == null) ? "" : req.getParameter("cmbFormaPagamento");        
                    
                    
                    // apaga todas as parcelas geradas
                    // para posterior alteração de parcelas
                    pagamentoDAO.deletaParcelas(pagamento);

                    // Configuração de parcelas, se houver
                    if (cobranca.equals("0")) // à vista, possui apenas uma parcela
                        numParcela = 1;
                    else
                    {
                        diasVencimento = Integer.parseInt(cobranca);
                        vlParcela = pagamento.getValorTotal() / numParcela;
                    }

                    // carrega objeto pagamento
                    pagamento.setCobranca(cobranca);
                    pagamento.setNumParcela(numParcela);

                    // montando parcelas para pagamento
                    Calendar dataVen = Calendar.getInstance();
                    dataVen.setTime(pagamento.getData());
                    List<Parcela> lstParcelaAux = new ArrayList<Parcela>();
                    while ((++nParc) <= numParcela)
                    {

                        // montando objeto parcela
                        Parcela _parc = new Parcela();
                        _parc.setDtPagamento(dataVen.getTime());
                        _parc.setDtVencimento(dataVen.getTime());
                        _parc.setFormaPagamento(formaPagamento);
                        _parc.setNumParcela(nParc);
                        _parc.setSituacao("P");
                        _parc.setValor(vlParcela);
                        lstParcelaAux.add(_parc);
                        
                        // acrescentando próxima data de vencimento e pagamento pendente
                        dataVen.add(Calendar.DAY_OF_MONTH, (diasVencimento * nParc));
                    }
                    pagamento.setLstParcela(lstParcelaAux);
                    if (pagamentoDAO.atualizar(pagamento))
                    {
                        pagamento = pagamentoDAO.listaUm(pagamento);
                        sessao.setAttribute("pagamento", pagamento);                            
                    }

                }
                else
                {
                    sessao.setAttribute("avisoErro", "Pagamento com parcela paga. Não é possível alterar pagamento.");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Pagamento.AcaoAbrePagamento");
                    pagRetorno = "visao/erro.jsp";             
                }  
            }
            
            if (pagRetorno.equals(""))
                pagRetorno = "FabricaGelo.Pagamento.AcaoAbrePagamento";             
            
        }     
        
        return pagRetorno;
        
//        Connection conexao = (Connection)req.getAttribute("connection");
//        HttpSession sessao = req.getSession(false);
//        
//        String pagRetorno = "";
//        
//        // dados do pagamento
//        String cobranca = (req.getParameter("cmbCobranca").equals("") || req.getParameter("cmbCobranca") == null) ? "" : req.getParameter("cmbCobranca");
//        int numParcela = (req.getParameter("txtParcelas").equals("") || req.getParameter("txtParcelas") == null) ? 1 : Integer.parseInt(req.getParameter("txtParcelas"));           
//
//        // dados da parcela
//        String dtPagamento = (req.getParameter("txtDtPagamento").equals("") || req.getParameter("txtDtPagamento") == null) ? "" : req.getParameter("txtDtPagamento");
//        String formaPagamento = (req.getParameter("cmbFormaPagamento").equals("") || req.getParameter("cmbFormaPagamento") == null) ? "" : req.getParameter("cmbFormaPagamento");        
//        String vlPago = (req.getParameter("txtValorPago").equals("") || req.getParameter("txtValorPago") == null) ? "" : req.getParameter("txtValorPago");        
//        
//        Pagamento pagamento = (Pagamento)sessao.getAttribute("pagamento");
//        Parcela parcela = (Parcela)sessao.getAttribute("parcela");
//        
//        if ((pagamento.getSituacao().equals("PG")))
//        {
//            sessao.setAttribute("avisoErro", "Pagamento liquidado");
//            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Pagamento.AcaoAbrePagamento");
//            pagRetorno = "visao/erro.jsp";             
//        }
//        else if ((parcela) != null && (parcela.getSituacao().equals("G")))
//        {
//            sessao.setAttribute("avisoErro", "Parcela paga");
//            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Pagamento.AcaoAbrePagamento");
//            pagRetorno = "visao/erro.jsp";                 
//        }
//        else if (pagamento.getNumParcela() != numParcela && pagamento.getSituacao().equals("PP"))
//        {
//            sessao.setAttribute("avisoErro", "Pagamento com parcelas definidas");
//            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Pagamento.AcaoAbrePagamento");
//            pagRetorno = "visao/erro.jsp";                 
//        }
//        else if (!pagamento.isProcessar())
//        {
//            sessao.setAttribute("avisoErro", "Pagamento não processável.");
//            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Pagamento.AcaoAbrePagamento");
//            pagRetorno = "visao/erro.jsp";            
//        }
//        else 
//        {
//            boolean alteraPagamento = true;
//            boolean encerraPagamento = false;
//            int diasVencimento = 0;
//            double vlParcela = 0;
//            int nParc = 0;
//            
//            PagamentoDAO pagamentoDAO = new PagamentoDAO(conexao);
//            List<Parcela> lstParcela = pagamento.getLstParcela();
//            
//            // caso parcela for selecionada, atualiza
//            if (parcela != null)
//            {
//                // Atualizanado parcela
//                ParcelaDAO parcelaDAO = new ParcelaDAO(conexao);
//                parcela.setDtPagamentoFormatado(dtPagamento);
//                parcela.setValorPago(Double.parseDouble(vlPago));
//                parcela.setFormaPagamento(formaPagamento);
//                if (!formaPagamento.equals(""))
//                    parcela.setSituacao("G");
//                parcelaDAO.atualizar(parcela);
//                
//                // verificando se todas as parcelas foram pagas para alteração
//                // de situação do pagamento
//                pagamento = pagamentoDAO.listaUm(pagamento);
//                lstParcela = pagamento.getLstParcela();
//                
//                Iterator itParcela = lstParcela.iterator();
//                while (itParcela.hasNext())
//                {
//                    Parcela _parcela = (Parcela)itParcela.next();
//                    if (_parcela.getSituacao().equals("G"))
//                        encerraPagamento = true;
//                    else
//                        encerraPagamento = false;
//                }
//                if (encerraPagamento)
//                {
//                    pagamento.setSituacao("PG");
//                    pagamentoDAO.atualizar(pagamento);
//                }
//            }
//            else
//            {
//               
//                // verificando se pagamento pode ser atualizada
//                Iterator itParcela = lstParcela.iterator();
//                while (itParcela.hasNext())
//                {
//                    // havendo parcela paga, não altera pagamento
//                    Parcela _parcela = (Parcela)itParcela.next();
//                    if (_parcela.getSituacao().equals("G"))
//                        alteraPagamento = false;
//                }                
//            
//                if (alteraPagamento)
//                {
//                    // apaga todas as parcelas geradas
//                    // para posterior alteração de parcelas
//                    pagamentoDAO.deletaParcelas(pagamento);
//                    
//                    // Configuração de parcelas, se houver
//                    if (cobranca.equals("0")) // à vista, possui apenas uma parcela
//                        numParcela = 1;
//                    else
//                    {
//                        diasVencimento = Integer.parseInt(cobranca);
//                        vlParcela = pagamento.getValorTotal() / numParcela;
//                    }
//                    
//                    // carrega objeto pagamento
//                    pagamento.setCobranca(cobranca);
//                    pagamento.setNumParcela(numParcela);
//                    
//                    // montando parcelas para pagamento
//                    List<Parcela> lstParcelaAux = new ArrayList<Parcela>();
//                    while ((++nParc) <= numParcela)
//                    {
//                        // calculando datas de vencimentos
//                        Calendar dataVen = Calendar.getInstance();
//                        dataVen.setTime(pagamento.getData());
//                        dataVen.add(Calendar.DAY_OF_MONTH, (diasVencimento * nParc));
//                        
//                        // montando objeto parcela
//                        Parcela _parc = new Parcela();
//                        _parc.setDtPagamento(dataVen.getTime());
//                        _parc.setDtVencimento(dataVen.getTime());
//                        _parc.setFormaPagamento(formaPagamento);
//                        _parc.setNumParcela(nParc);
//                        _parc.setSituacao("P");
//                        _parc.setValor(vlParcela);
//                        lstParcelaAux.add(_parc);
//                    }
//                    pagamento.setLstParcela(lstParcelaAux);
//                    pagamentoDAO.atualizar(pagamento);
//                    
//                    pagamento = pagamentoDAO.listaUm(pagamento);
//                    sessao.setAttribute("pagamento", pagamento);
//                           
//                }
//                else
//                {
//                    sessao.setAttribute("avisoErro", "Pagamento com parcela paga. Não é possível alterar pagamento.");
//                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Pagamento.AcaoAbrePagamento");
//                    pagRetorno = "visao/erro.jsp";             
//                }                
//                
//            }
//            
//            if (pagRetorno.equals(""))
//                pagRetorno = "FabricaGelo.Pagamento.AcaoAbrePagamento";  
//        }
//        
//        return pagRetorno;
//        
    }
}




