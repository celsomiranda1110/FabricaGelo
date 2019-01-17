/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author celso
 */
import Bean.Colaborador;
import Bean.Entrega;
import Bean.Movimento;
import Bean.Pagamento;
import Bean.ProdutoMovimento;
import Bean.Profissional;
import java.sql.Connection;
import java.util.*;

public class MovimentoDAO extends DAO{

    public MovimentoDAO(Connection conexao) {
        super(conexao);
    }
    
    public List<Movimento> listaTodos(Movimento _movimento)
    {
        List<Movimento> lstTabela = new ArrayList();
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblMovimento`.`intMovimentoId`,";
        comSql += "     `tblMovimento`.`intColaboradorId`,";
        comSql += "     `tblMovimento`.`strNotaFiscal`,";
        comSql += "     `tblMovimento`.`datDataEntrega`,";
        comSql += "     `tblMovimento`.`strNumero`,";
        comSql += "     `tblMovimento`.`datDataLancamento`,";
        comSql += "     `tblMovimento`.`chrTipo`,";
        comSql += "     `tblMovimento`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblMovimento`";
//        comSql += " WHERE ";
//        comSql += "     `tblMovimento`.`chrTipo` = '" + _movimento.getTipo() + "'";
//        if (!_movimento.getDataLancamento().equals(""))
//            comSql += "      and `tblMovimento`.`datDataLancamento` = '" + _movimento.getDataLancamento() + "'";
        comSql += ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Movimento movimento = new Movimento();
            List bkp = (ArrayList)tabela.get(i);
            
            movimento.setIdMovimento(((Integer)bkp.get(0)).intValue());
            movimento.setIdColaborador(((Integer)bkp.get(1)).intValue());
            movimento.setNotaFiscal((String)bkp.get(2));
            movimento.setDtEntrega((Date)bkp.get(3));
            movimento.setNumero((String)bkp.get(4));
            movimento.setDtLancamento((Date)bkp.get(5));
            movimento.setTipo((String)bkp.get(6));
            movimento.setSituacao((String)bkp.get(7));
            
            
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
            Colaborador colaborador = new Colaborador();
            colaborador.setIdColaborador(movimento.getIdColaborador());
            colaborador = colaboradorDAO.listaUm(colaborador);
            movimento.setColaborador(colaborador);
            
            lstTabela.add(movimento);
        }
        
        return lstTabela;
    }
    
    public Movimento listaUm(Movimento movimento)
    {
        
        
        comSql = "";      
        comSql += " SELECT ";
        comSql += " 	`tblMovimento`.`intMovimentoId`,";
        comSql += "     `tblMovimento`.`intColaboradorId`,";
        comSql += "     `tblMovimento`.`strNotaFiscal`,";
        comSql += "     `tblMovimento`.`datDataEntrega`,";
        comSql += "     `tblMovimento`.`strNumero`,";
        comSql += "     `tblMovimento`.`datDataLancamento`,";
        comSql += "     `tblMovimento`.`chrTipo`,";
        comSql += "     `tblMovimento`.`chrSituacao`";
        comSql += " FROM `smmdaa_bdGelo`.`tblMovimento`";
        comSql += " WHERE ";
        comSql += "     `tblMovimento`.`intMovimentoId` = " + movimento.getIdMovimento() + ";";
        List tabela = super.listaUm();
        
        if(!tabela.isEmpty())
        {
            for(int i = 0; i < tabela.size(); i++)
            {
                
                List bkp = (ArrayList)tabela.get(i);

                movimento.setIdMovimento(((Integer)bkp.get(0)).intValue());
                movimento.setIdColaborador(((Integer)bkp.get(1)).intValue());
                movimento.setNotaFiscal((String)bkp.get(2));
                movimento.setDtEntrega((Date)bkp.get(3));
                movimento.setNumero((String)bkp.get(4));
                movimento.setDtLancamento((Date)bkp.get(5));
                movimento.setTipo((String)bkp.get(6));
                movimento.setSituacao((String)bkp.get(7));
                

                ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
                Colaborador colaborador = new Colaborador();
                colaborador.setIdColaborador(movimento.getIdColaborador());
                colaborador = colaboradorDAO.listaUm(colaborador);
                movimento.setColaborador(colaborador);

                PagamentoDAO pagamentoDAO = new PagamentoDAO(conexao);
                Pagamento pagamento = new Pagamento();
                pagamento.setIdMovimento(movimento.getIdMovimento());
                pagamento = pagamentoDAO.listaUm(pagamento);
                movimento.setPagamento(pagamento);  
                
                ProdutoMovimentoDAO produtoMovimentoDAO = new ProdutoMovimentoDAO(conexao);
                List<ProdutoMovimento> lstProdutoMovimento = new ArrayList<ProdutoMovimento>();
                lstProdutoMovimento = produtoMovimentoDAO.listaTodos(movimento);
                movimento.setLstProdutoMovimento(lstProdutoMovimento);
                
            }  
            return movimento;
        }
        else
            return null;

    }
    
    public boolean atualizar(Movimento movimento) 
    {
        boolean retorno;
        double vlProduto = 0;
        double vlDesconto = 0;
        
        
        Movimento _movimento = new Movimento();
        movimento.replicar(_movimento);
        
        
        //List<ProdutoMovimento> lstProdutoMovimento = movimento.getLstProdutoMovimento();
       
        if (listaUm(movimento) == null)
        {
            comSql = "";
            comSql += " INSERT INTO `smmdaa_bdGelo`.`tblMovimento`";
            comSql += " 	(`intColaboradorId`,";
            comSql += " 	`strNotaFiscal`,";
            comSql += " 	`datDataEntrega`,";
            comSql += " 	`strNumero`,";
            comSql += " 	`datDataLancamento`,";
            comSql += " 	`chrTipo`,";
            comSql += " 	`chrSituacao`)";
            comSql += " VALUES";
            comSql += " 	(" + _movimento.getIdColaborador();
            comSql += " 	,'" + _movimento.getNotaFiscal() + "'";
            comSql += " 	,'" + _movimento.getDataEntrega() + "'";
            comSql += " 	,'" + _movimento.getNumero() + "'";
            comSql += " 	,'" + _movimento.getDataLancamento() + "'";
            comSql += " 	,'" + _movimento.getTipo() + "'";
            comSql += " 	,'" + _movimento.getSituacao() + "');";

            
            retorno = atualizar();
            if(retorno)
            {
                comSql = "";
                comSql += " Select max(intMovimentoId) as idMovimento from `smmdaa_bdGelo`.`tblMovimento`;";
                List lstConsulta = listaUm();
                for(int i = 0; i < lstConsulta.size(); i++)
                {
                    List reg = ((ArrayList)lstConsulta.get(0));
                    identity = ((Integer)reg.get(0)).intValue();
                    _movimento.setIdMovimento(((Integer)reg.get(0)).intValue() );
                }
                
            }            
        }
        else
        {
            
            identity = _movimento.getIdMovimento();
            
            comSql = "";
            comSql += " UPDATE `smmdaa_bdGelo`.`tblMovimento` SET ";
            comSql += " 	`intColaboradorId` = " + _movimento.getIdColaborador();
            comSql += " 	,`strNotaFiscal` = '" + _movimento.getNotaFiscal() + "'";
            comSql += " 	,`datDataEntrega` = '" + _movimento.getDataEntrega() + "'";
            comSql += " 	,`strNumero` = '" + _movimento.getNumero() + "'";
            comSql += " 	,`datDataLancamento` = '" + _movimento.getDataLancamento() + "'";
            comSql += " 	,`chrTipo` = '" + _movimento.getTipo() + "'";
            comSql += " 	,`chrSituacao` = '" + _movimento.getSituacao() + "'";
            comSql += " WHERE ";
            comSql += " 	`intMovimentoId` = " + _movimento.getIdMovimento() + ";";
            
            
            
            retorno = atualizar();
        }
        
        if (retorno)
        {
            // atualização de produtos do movimento
            ProdutoMovimentoDAO _produtoMovimentoDAO = new ProdutoMovimentoDAO(conexao);
            if(_movimento.getLstProdutoMovimento() != null)
            {
                Iterator it = _movimento.getLstProdutoMovimento().iterator();
                while (it.hasNext())
                {
                    ProdutoMovimento _produtoMovimento = (ProdutoMovimento)it.next();
                    _produtoMovimento.setIdMovimento(_movimento.getIdMovimento());
                    
                    // EM CASO DE MOVIMENTO FECHAR
                    // ATUALIZA O SALDO EM ESTOQUE DO PRODUTO
                    if (_movimento.getSituacao().equals("FE"))
                    {
                        if (_movimento.getTipo().equals("CP"))
                            _produtoMovimento.setOperacao("AD"); // ADICIONA AO ESTOQUE
                        else
                            _produtoMovimento.setOperacao("SU"); // SUBTRAI DO ESTOQUE
                    }
                    
                    
                    
                    _produtoMovimentoDAO.atualizar(_produtoMovimento);
                 }
             }
            
            // atualizando valores do movimento
            List<ProdutoMovimento> lstProdutoMovimento = new ArrayList<ProdutoMovimento>();
            lstProdutoMovimento = _produtoMovimentoDAO.listaTodos(_movimento);
            Iterator itProdutoMovimento = lstProdutoMovimento.iterator();
            while (itProdutoMovimento.hasNext())
            {
                ProdutoMovimento _produtoMovimento = (ProdutoMovimento)itProdutoMovimento.next();
                vlProduto += _produtoMovimento.getValorTotal();
                vlDesconto += _produtoMovimento.getDesconto();
            }              
            
            // atualização sobre pagamento baseado nos valores dos ítens
            Pagamento pagamento = movimento.getPagamento();
            PagamentoDAO pagamentoDAO = new PagamentoDAO(conexao);
            if (pagamento == null)
            {
                pagamento = new Pagamento();
            
                pagamento.setIdMovimento(_movimento.getIdMovimento());
                pagamento.setData(_movimento.getDtLancamento());
                pagamento.setDesconto(vlDesconto);
                pagamento.setValor(vlProduto);
                pagamento.setValorTotal(vlProduto - vlDesconto);
                pagamento.setNumParcela(1);
                pagamento.setCobranca("0");
                pagamento.setSituacao("PC"); // PERMITE ALTERAR NÚMERO DE PARCELAS
                // PAGAMENTO PROCESSÁVEL FINANCEIRAMENTE SENDO VENDA OU COMPRA
                // OUTROS MOVIMENTOS, SOMENTE A TÍTULO DE INFORMAÇÃO
                pagamento.setProcessar( _movimento.getTipo().equals("VE") || _movimento.getTipo().equals("CP"));

                
                pagamentoDAO.atualizar(pagamento);
            }
            else
            {
                pagamento.setValor(vlProduto);
                pagamento.setValorTotal(vlProduto - vlDesconto);
                pagamentoDAO.atualizar(pagamento);
            }
            
            
        }

        
        return retorno;
        
    }      
    
}
