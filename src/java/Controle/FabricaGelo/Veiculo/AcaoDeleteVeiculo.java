/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Veiculo;

import Bean.Entrega;
import Bean.Equipamento;
import Bean.Manutencao;
import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.ProdutoCamara;
import Bean.SaidaCamara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import DAO.EquipamentoDAO;
import DAO.ManutencaoDAO;
import DAO.MaquinaProducaoDAO;
import DAO.ProducaoDAO;
import DAO.ProdutoCamaraDAO;
import DAO.SaidaCamaraDAO;
import java.sql.Connection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author celso
 */
public class AcaoDeleteVeiculo extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/veiculo.jsp";
        
        Equipamento veiculo = (Equipamento)sessao.getAttribute("veiculo");
        if (veiculo == null)
        {
            sessao.setAttribute("avisoErro", "Veículo não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Veiculo.AcaoAbreVeiculo");
            pagRetorno = "visao/erro.jsp";             
        }
        else
        {
            boolean bolApaga = true;
            
            ManutencaoDAO manutencaoDAO = new ManutencaoDAO(conexao);
            Iterator itManutencao = manutencaoDAO.listaTodos(null).iterator();
            while (itManutencao.hasNext())
            {
                Manutencao manutencao = (Manutencao)itManutencao.next();
                if (manutencao.getIdEquipamento() == veiculo.getIdEquipamento())
                {
                    bolApaga = false;
                    break;
                }
            }
            
            if (bolApaga)
            {
                ProdutoCamaraDAO produtoCamaraDAO = new ProdutoCamaraDAO(conexao);
                ProdutoCamara produtoCamara = new ProdutoCamara();
                produtoCamara.setIdEquipamento(veiculo.getIdEquipamento());
                Iterator itProdutoCamara = produtoCamaraDAO.listaTodos(produtoCamara).iterator();
                while (itProdutoCamara.hasNext())
                {
                    ProdutoCamara _produtoCamara = (ProdutoCamara)itProdutoCamara.next();
                    if (_produtoCamara.getIdEquipamento() == veiculo.getIdEquipamento())
                    {
                        bolApaga = false;
                        break;
                    }
                    
                }
            }
            
            if (bolApaga)
            {
                SaidaCamaraDAO saidaCamaraDAO = new SaidaCamaraDAO(conexao);
                if (saidaCamaraDAO.listaTodos(veiculo).size() > 0)
                    bolApaga = false;
            }
            
            if (bolApaga)
            {
                ProducaoDAO producaoDAO = new ProducaoDAO(conexao);
                Iterator itProducao = producaoDAO.listaTodos().iterator();
                while (itProducao.hasNext())
                {
                    Producao producao = (Producao)itProducao.next();
                    Iterator itMaquinaProducao = producao.getLstMaquinaProducao().iterator();
                    while (itMaquinaProducao.hasNext())
                    {
                        MaquinaProducao maquinaProducao = (MaquinaProducao)itMaquinaProducao.next();
                        if (maquinaProducao.getIdEquipamento() == veiculo.getIdEquipamento())
                        {
                            bolApaga = false;
                            break;
                        }
                    }
                    if (!bolApaga)
                        break;
                    
                }
                
            }
            
            if (bolApaga)
            {
                EntregaDAO entregaDAO = new EntregaDAO(conexao);
                Entrega entrega = new Entrega();
                entrega.setDataFormatada("1900-01-01");
                entrega.setSituacao("");
                entrega.setIdEquipamento(veiculo.getIdEquipamento());
                if (entregaDAO.listaTodos(entrega).size() > 0)
                    bolApaga = false;
            }
            
            if (!bolApaga)
            {
                sessao.setAttribute("avisoErro", "Veículo está relacionado a movimentos. Inative para preservação dos dados");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Veiculo.AcaoAbreVeiculo");
                pagRetorno = "visao/erro.jsp";              
                
            }
            else
            {
                EquipamentoDAO equipamentoDAO = new EquipamentoDAO(conexao);
                if (equipamentoDAO.delete(veiculo))
                {
                    sessao.setAttribute("avisoErro", "Veículo deletado com sucesso");
                    sessao.setAttribute("tipoAviso","alert alert-success");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Veiculo.AcaoListarVeiculo");
                    pagRetorno = "visao/erro.jsp";                     
                }
                
            }
                
        }            
        
        return pagRetorno;
        
    }
}
