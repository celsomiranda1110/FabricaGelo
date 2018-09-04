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
        comSql += "     `tblMovimento`.`intProfissionalId`,";
        comSql += "     `tblMovimento`.`strNotaFiscal`,";
        comSql += "     `tblMovimento`.`datDataEntrega`,";
        comSql += "     `tblMovimento`.`strNumero`,";
        comSql += "     `tblMovimento`.`datDataLancamento`,";
        comSql += "     `tblMovimento`.`chrTipo`,";
        comSql += "     `tblMovimento`.`chrSituacao`";
        comSql += " FROM `bdGelo`.`tblMovimento`";
        comSql += " WHERE ";
        comSql += "     `tblMovimento`.`chrTipo` = '" + _movimento.getTipo() + "'";
        if (_movimento.getDataLancamento() != null)
            comSql += "      and `tblMovimento`.`datDataLancamento` = '" + _movimento.getDataLancamento() + "'";
        comSql += ";";
        
        List tabela = super.listaTodos();
        
        for(int i = 0; i < tabela.size(); i++)
        {
            Movimento movimento = new Movimento();
            List bkp = (ArrayList)tabela.get(i);
            
            movimento.setIdMovimento(((Integer)bkp.get(0)).intValue());
            movimento.setIdColaborador(((Integer)bkp.get(1)).intValue());
            movimento.setIdProfissionalId(((Integer)bkp.get(2)).intValue());
            movimento.setNotaFiscal((String)bkp.get(3));
            movimento.setDtEntrega((Date)bkp.get(4));
            movimento.setNumero((String)bkp.get(5));
            movimento.setDtLancamento((Date)bkp.get(6));
            movimento.setTipo((String)bkp.get(7));
            movimento.setSituacao((String)bkp.get(8));
            
            
            ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
            Colaborador colaborador = new Colaborador();
            colaborador.setIdColaborador(movimento.getIdColaborador());
            colaborador = colaboradorDAO.listaUm(colaborador);
            movimento.setColaborador(colaborador);
            
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
            Profissional profissional = new Profissional();
            profissional.setIdProfissional(movimento.getIdProfissional());
            profissional = profissionalDAO.listaUm(profissional);
            movimento.setProfissional(profissional);
            


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
        comSql += "     `tblMovimento`.`intProfissionalId`,";
        comSql += "     `tblMovimento`.`strNotaFiscal`,";
        comSql += "     `tblMovimento`.`datDataEntrega`,";
        comSql += "     `tblMovimento`.`strNumero`,";
        comSql += "     `tblMovimento`.`datDataLancamento`,";
        comSql += "     `tblMovimento`.`chrTipo`,";
        comSql += "     `tblMovimento`.`chrSituacao`";
        comSql += " FROM `bdGelo`.`tblMovimento`";
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
                movimento.setIdProfissionalId(((Integer)bkp.get(2)).intValue());
                movimento.setNotaFiscal((String)bkp.get(3));
                movimento.setDtEntrega((Date)bkp.get(4));
                movimento.setNumero((String)bkp.get(5));
                movimento.setDtLancamento((Date)bkp.get(6));
                movimento.setTipo((String)bkp.get(7));
                movimento.setSituacao((String)bkp.get(8));
                

                ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
                Colaborador colaborador = new Colaborador();
                colaborador.setIdColaborador(movimento.getIdColaborador());
                colaborador = colaboradorDAO.listaUm(colaborador);
                movimento.setColaborador(colaborador);

                ProfissionalDAO profissionalDAO = new ProfissionalDAO(conexao);
                Profissional profissional = new Profissional();
                profissional.setIdProfissional(movimento.getIdProfissional());
                profissional = profissionalDAO.listaUm(profissional);
                movimento.setProfissional(profissional);

                PagamentoDAO pagamentoDAO = new PagamentoDAO(conexao);
                Pagamento pagamento = new Pagamento();
                pagamento.setIdMovimento(movimento.getIdMovimento());
                pagamento = pagamentoDAO.listaUm(pagamento);
                movimento.setPagamento(pagamento);  
                
                EntregaDAO entregaDAO = new EntregaDAO(conexao);
                Entrega entrega = new Entrega();
                entrega.setIdMovimento(movimento.getIdMovimento());
                entrega = entregaDAO.listaUm(entrega);
                movimento.setEntrega(entrega);
              
                
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
            comSql += " INSERT INTO `bdGelo`.`tblMovimento`";
            comSql += " 	(`intColaboradorId`,";
            comSql += " 	`intProfissionalId`,";
            comSql += " 	`strNotaFiscal`,";
            comSql += " 	`datDataEntrega`,";
            comSql += " 	`strNumero`,";
            comSql += " 	`datDataLancamento`,";
            comSql += " 	`chrTipo`,";
            comSql += " 	`chrSituacao`)";
            comSql += " VALUES";
            comSql += " 	(" + _movimento.getIdColaborador();
            comSql += " 	," + _movimento.getIdProfissional();
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
                comSql += " Select max(intMovimentoId) as idMovimento from `tblMovimento`;";
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
            comSql = "";
            comSql += " UPDATE `bdGelo`.`tblMovimento` SET ";
            comSql += " 	`intColaboradorId` = " + _movimento.getIdColaborador();
            comSql += " 	,`intProfissionalId` = " + _movimento.getIdProfissional();
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
            
            if(_movimento.getLstProdutoMovimento() != null)
            {
                ProdutoMovimentoDAO _produtoMovimentoDAO = new ProdutoMovimentoDAO(conexao);
                Iterator it = _movimento.getLstProdutoMovimento().iterator();
                while (it.hasNext())
                {
                    ProdutoMovimento _produtoMovimento = (ProdutoMovimento)it.next();
                    _produtoMovimento.setIdMovimento(_movimento.getIdMovimento());
                    if(_produtoMovimentoDAO.atualizar(_produtoMovimento))
                    {
                        // reseta os novos valores do produto movimento
                        _produtoMovimento = _produtoMovimentoDAO.listaUm(_produtoMovimento);
                        
                        vlProduto += _produtoMovimento.getValorTotal();
                        vlDesconto += _produtoMovimento.getDesconto();
                    }
                }
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
                pagamento.setSituacao("PP");

                
                pagamentoDAO.atualizar(pagamento);
            }
            else
            {
                pagamento.setValor(vlProduto);
                pagamento.setValorTotal(vlProduto - vlDesconto);
                pagamentoDAO.atualizar(pagamento);
            }
            
            // observar a possibilidade de existência de entrega
        }

        
        return retorno;
        
    }      
    
}
