/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Colaborador;

import Bean.Colaborador;
import Bean.Movimento;
import Bean.MovimentoEntrega;
import Bean.Pagamento;
import Bean.Parcela;
import Bean.VisitaColaborador;
import Bean.ColaboradorEntrega;
import Bean.Entrega;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import DAO.ColaboradorEntregaDAO;
import DAO.MovimentoDAO;
import DAO.PagamentoDAO;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoPesquisaColaboradorPendente extends Acao{
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        // identificando dia da semana atual
        //Calendar dt = Calendar.getInstance();
        GregorianCalendar gc = new GregorianCalendar();
        Date dt = gc.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dtFormatada = formatter.format(dt);
        int dia = gc.get(gc.DAY_OF_WEEK);
        
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        
        List<Colaborador> lstColaborador = new ArrayList<Colaborador>();
        List<ColaboradorEntrega> lstRetorno = new ArrayList<ColaboradorEntrega>();
        List<ColaboradorEntrega> lstRetornoAux = new ArrayList<ColaboradorEntrega>();
        List<Pagamento> lstPagamento = new ArrayList<Pagamento>();
        List<Parcela> lstParcela = new ArrayList<Parcela>();
        
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        
        lstColaborador = colaboradorDAO.listaTodos();
        String pesquisa = req.getParameter("cmbSituacao");
        
        if (pesquisa.equals("CH"))
        {
            Iterator it = lstColaborador.iterator();
            while (it.hasNext())
            {
                Colaborador _colaborador = (Colaborador)it.next();
                
                Iterator itDia = _colaborador.getLstVisitaColaborador().iterator();
                while (itDia.hasNext())
                {
                    VisitaColaborador _visitaColaborador = (VisitaColaborador)itDia.next();
                    if ((_visitaColaborador.getDia() == dia) && (_visitaColaborador.getAtivo().equals("A")))
                    {
                        ColaboradorEntrega colaboradorEntrega = new ColaboradorEntrega();
                        colaboradorEntrega.setCliente(_colaborador);
                        colaboradorEntrega.setIdEntrega(entrega.getIdEntrega());
                        lstRetorno.add(colaboradorEntrega);
                    }
                }

                
            }
        }
        else if (pesquisa.equals("CP"))
        {
            boolean inclui = true;
            
            // procurando pagamentos pendentes
            PagamentoDAO pagamentoDAO = new PagamentoDAO(conexao);
            Pagamento pagamento = new Pagamento();
            pagamento.setSituacao("PP");
            lstPagamento = pagamentoDAO.listaTodos(pagamento);
            Iterator itPagamento = lstPagamento.iterator();
            while (itPagamento.hasNext())
            {
                // procurando parcela pendente com vencimentos em atraso ou até o dia
                pagamento = (Pagamento)itPagamento.next();
                lstParcela = pagamento.getLstParcela();
                
                Iterator itParcela = lstParcela.iterator();
                while (itParcela.hasNext())
                {
                    inclui = true;
                    Parcela parcela = (Parcela)itParcela.next();
                    if (parcela.getSituacao().equals("P") && (parcela.getDtVencimentoFormatado().equals(dtFormatada)))
                    {
                        // Identificando colaborador com parcela em atraso
                        MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
                        Movimento movimento = new Movimento();
                        movimento.setIdMovimento(pagamento.getIdMovimento());
                        movimento = movimentoDAO.listaUm(movimento);
                        
                        if (movimento.getTipo().equals("VE"))
                        {
                        
                            Colaborador colaborador = (Colaborador)movimento.getColaborador();
                            Iterator itColaborador = lstRetorno.iterator();
                            while (itColaborador.hasNext())
                            {
                                ColaboradorEntrega _colaboradorEntrega = (ColaboradorEntrega)itColaborador.next();
                                Colaborador _colaborador = _colaboradorEntrega.getCliente();
                                if (_colaborador.getIdColaborador() == colaborador.getIdColaborador())
                                    inclui = false;
                            }

                            if (inclui)
                            {
                                
                                ColaboradorEntrega colaboradorEntrega = new ColaboradorEntrega();
                                colaboradorEntrega.setCliente(colaborador);
                                colaboradorEntrega.setIdEntrega(entrega.getIdEntrega());
                                
                                lstRetorno.add(colaboradorEntrega);
                                
                            }
                        }
                    }    
                }
            }
            
        }
        else if (pesquisa.equals("CI"))
        {
            boolean inclui = true;
            
            // procurando pagamentos pendentes
            PagamentoDAO pagamentoDAO = new PagamentoDAO(conexao);
            Pagamento pagamento = new Pagamento();
            pagamento.setSituacao("PP");
            lstPagamento = pagamentoDAO.listaTodos(pagamento);
            Iterator itPagamento = lstPagamento.iterator();
            while (itPagamento.hasNext())
            {
                // procurando parcela pendente com vencimentos em atraso ou até o dia
                pagamento = (Pagamento)itPagamento.next();
                lstParcela = pagamento.getLstParcela();
                
                Iterator itParcela = lstParcela.iterator();
                while (itParcela.hasNext())
                {
                    inclui = true;
                    Parcela parcela = (Parcela)itParcela.next();
                    if (parcela.getSituacao().equals("P") && (parcela.getDtVencimento().before(dt)))
                    {
                        // Identificando colaborador com parcela em atraso
                        MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
                        Movimento movimento = new Movimento();
                        movimento.setIdMovimento(pagamento.getIdMovimento());
                        movimento = movimentoDAO.listaUm(movimento);
                        
                        if(movimento.getTipo().equals("VE"))
                        {
                        
                            Colaborador colaborador = (Colaborador)movimento.getColaborador();
                            Iterator itColaborador = lstRetorno.iterator();
                            while (itColaborador.hasNext())
                            {
                                ColaboradorEntrega _colaboradorEntrega = (ColaboradorEntrega)itColaborador.next();
                                Colaborador _colaborador = _colaboradorEntrega.getCliente();
                                if (_colaborador.getIdColaborador() == colaborador.getIdColaborador())
                                    inclui = false;
                            }

                            if (inclui)
                            {
                                                                
                                ColaboradorEntrega colaboradorEntrega = new ColaboradorEntrega();
                                colaboradorEntrega.setCliente(colaborador);
                                colaboradorEntrega.setIdEntrega(entrega.getIdEntrega());
                                
                                lstRetorno.add(colaboradorEntrega);
                            }
                            
                        }
                    }    
                }
            }            
        }
        else
        {
            Iterator itColaborador = lstColaborador.iterator();
            while (itColaborador.hasNext())
            {
                Colaborador colaborador = (Colaborador)itColaborador.next();
                ColaboradorEntrega colaboradorEntrega = new ColaboradorEntrega();
                colaboradorEntrega.setCliente(colaborador);
                colaboradorEntrega.setIdEntrega(entrega.getIdEntrega());
                lstRetorno.add(colaboradorEntrega);
            }
            
        }
        
        // atualizando lista de colaboradores para o usuário
        ColaboradorEntregaDAO colaboradorEntregaDAO = new ColaboradorEntregaDAO(conexao);
        Iterator itColaboradorEntrega = lstRetorno.iterator();
        while (itColaboradorEntrega.hasNext())
        {
            ColaboradorEntrega colaboradorEntrega = new ColaboradorEntrega();
            ColaboradorEntrega _colaboradorEntrega = (ColaboradorEntrega)itColaboradorEntrega.next();
            
            colaboradorEntrega = colaboradorEntregaDAO.listaUm(_colaboradorEntrega);
            
            if (colaboradorEntrega != null)
                lstRetornoAux.add(colaboradorEntrega);
            else
                lstRetornoAux.add(_colaboradorEntrega);
 
        }
        
        sessao.setAttribute("entrega",entrega);
        sessao.setAttribute("paramPesquisa",pesquisa); 
        sessao.setAttribute("lstColaboradorEntrega",lstRetornoAux); 
        
        return "FabricaGelo.Entrega.AcaoNovaRota";
    }
    
}
