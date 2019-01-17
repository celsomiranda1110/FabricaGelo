/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Entrega;

import Bean.Colaborador;
import Bean.ColaboradorEntrega;
import Bean.Entrega;
import Bean.Movimento;
import Bean.MovimentoEntrega;
import Bean.Pagamento;
import Bean.Parcela;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.ColaboradorDAO;
import DAO.ColaboradorEntregaDAO;
import DAO.MovimentoDAO;
import DAO.MovimentoEntregaDAO;
import DAO.PagamentoDAO;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class AcaoMarcaColaboradorVisita extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        Entrega entrega = (Entrega)sessao.getAttribute("entrega");
        
        String idColaborador = req.getParameter("idColaborador");
        String paramPesquisa = req.getParameter("cmbSituacao");        
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        Colaborador colaborador = new Colaborador();
        colaborador.setIdColaborador(Integer.parseInt(idColaborador));
        colaborador = colaboradorDAO.listaUm(colaborador);
        
        if ((colaborador != null) && (entrega != null))
        {
            ColaboradorEntregaDAO colaboradorEntregaDAO = new ColaboradorEntregaDAO(conexao);
            ColaboradorEntrega colaboradorEntrega = new ColaboradorEntrega();
            colaboradorEntrega.setIdEntrega(entrega.getIdEntrega());
            colaboradorEntrega.setCliente(colaborador);
            colaboradorEntrega = colaboradorEntregaDAO.listaUm(colaboradorEntrega);
            
            if (colaboradorEntrega == null)
            {
                colaboradorEntrega = new ColaboradorEntrega();
                colaboradorEntrega.setIdEntrega(entrega.getIdEntrega());
                colaboradorEntrega.setCliente(colaborador); 
                colaboradorEntrega.setMotivo(paramPesquisa); 
                colaboradorEntrega.setSituacao("PM");
            }
            else
            {
                colaboradorEntrega.setIdEntrega(entrega.getIdEntrega());
                colaboradorEntrega.setCliente(colaborador); 
                colaboradorEntrega.setMotivo(paramPesquisa);
                if (colaboradorEntrega.getSituacao().equals("PD"))
                    colaboradorEntrega.setSituacao("PM");
                else
                    colaboradorEntrega.setSituacao("PD");                
            }
            
            if ((colaboradorEntrega.getSituacao().equals("PM")) && (colaboradorEntrega.getMotivo().equals("CI") || colaboradorEntrega.getMotivo().equals("CP")))
            {
                boolean seInadimplente = false;
                List<MovimentoEntrega> lstMovimentoEntrega = new ArrayList<MovimentoEntrega>();
                List<Pagamento> lstPagamento = new ArrayList<Pagamento>();
                List<Parcela> lstParcela = new ArrayList<Parcela>();
                
                GregorianCalendar gc = new GregorianCalendar();
                Date dt = gc.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dtFormatada = formatter.format(dt);                
                
                
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
                        seInadimplente = false;
                        Parcela parcela = (Parcela)itParcela.next();
                        
                        if (colaboradorEntrega.getMotivo().equals("CI")) // colaborador inadimplente
                        {
                            if (parcela.getSituacao().equals("P") && (parcela.getDtVencimento().before(dt)))
                                seInadimplente = true;
                        }
                        else // senão, considera colaborador com pagamento no dia
                        {
                            if (parcela.getSituacao().equals("P") && (parcela.getDtVencimentoFormatado().equals(dtFormatada)))
                                seInadimplente = true;
                        }
                        
                        //if (parcela.getSituacao().equals("P") && (parcela.getDtVencimentoFormatado().equals(dtFormatada)))
                        if (seInadimplente)
                        {
                            // Identificando colaborador com parcela em atraso
                            MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);
                            Movimento movimento = new Movimento();
                            movimento.setIdMovimento(pagamento.getIdMovimento());
                            movimento = movimentoDAO.listaUm(movimento);

                            if (movimento.getTipo().equals("VE"))
                            {
                                Colaborador _colaboradorE = colaboradorEntrega.getCliente();
                                Colaborador _colaboradorM = movimento.getColaborador();
                                
                                if (_colaboradorE.getRazaoSocial().equals(_colaboradorM.getRazaoSocial()))
                                {
                                    MovimentoEntrega movimentoEntrega = new MovimentoEntrega();
                                    movimentoEntrega.setMovimento(movimento);
                                    lstMovimentoEntrega.add(movimentoEntrega);
                                }


                            }
                        }    
                    }
                }
                // vinculando movimentosEntrega
                colaboradorEntrega.setLstMovimentoEntrega(lstMovimentoEntrega);
                
            }
            else if ((colaboradorEntrega.getSituacao().equals("PD")) && (colaboradorEntrega.getMotivo().equals("CI") || colaboradorEntrega.getMotivo().equals("CP")))
            {
                MovimentoEntregaDAO movimentoEntregaDAO = new MovimentoEntregaDAO(conexao);
                List<MovimentoEntrega> lstMovimentoEntrega = colaboradorEntrega.getLstMovimentoEntrega();
                if (lstMovimentoEntrega != null)
                {    
                    Iterator itMovEnt = lstMovimentoEntrega.iterator();
                    while (itMovEnt.hasNext())
                    {
                        MovimentoEntrega _movEntrega = (MovimentoEntrega)itMovEnt.next();
                        movimentoEntregaDAO.deletar(_movEntrega);
                    }
                    colaboradorEntrega.setLstMovimentoEntrega(null);
                }
            }
            
            colaboradorEntregaDAO.atualizar(colaboradorEntrega);
            

        }
        

        return "FabricaGelo.Colaborador.AcaoPesquisaColaboradorPendente";        
        
    }
    
}
