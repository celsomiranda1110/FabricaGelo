/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.FabricaGelo.Camara;

import Bean.Entrega;
import Bean.Equipamento;
import Bean.Manutencao;
import Bean.MaquinaProducao;
import Bean.Producao;
import Bean.ProdutoCamara;
import Controle.FabricaGelo.Gerais.Acao;
import DAO.EntregaDAO;
import DAO.EquipamentoDAO;
import DAO.ManutencaoDAO;
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
public class AcaoDeletaCamara extends Acao
{
    
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        String pagRetorno = "visao/camara.jsp";
        
        Equipamento camara = (Equipamento)sessao.getAttribute("camara");
        if (camara == null)
        {
            sessao.setAttribute("avisoErro", "Câmara não selecionado");
            sessao.setAttribute("tipoAviso","alert alert-danger");
            sessao.setAttribute("pagOrigemErro", "FabricaGelo.Camara.AcaoAbreCamara");
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
                if (manutencao.getIdEquipamento() == camara.getIdEquipamento())
                {
                    bolApaga = false;
                    break;
                }
            }
            
            if (bolApaga)
            {
                ProdutoCamaraDAO produtoCamaraDAO = new ProdutoCamaraDAO(conexao);
                ProdutoCamara produtoCamara = new ProdutoCamara();
                produtoCamara.setIdEquipamento(camara.getIdEquipamento());
                Iterator itProdutoCamara = produtoCamaraDAO.listaTodos(produtoCamara).iterator();
                while (itProdutoCamara.hasNext())
                {
                    ProdutoCamara _produtoCamara = (ProdutoCamara)itProdutoCamara.next();
                    if (_produtoCamara.getIdEquipamento() == camara.getIdEquipamento())
                    {
                        bolApaga = false;
                        break;
                    }
                    
                }
            }
            
            if (bolApaga)
            {
                SaidaCamaraDAO saidaCamaraDAO = new SaidaCamaraDAO(conexao);
                if (saidaCamaraDAO.listaTodos(camara).size() > 0)
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
                        if (maquinaProducao.getIdEquipamento() == camara.getIdEquipamento())
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
                entrega.setIdEquipamento(camara.getIdEquipamento());
                if (entregaDAO.listaTodos(entrega).size() > 0)
                    bolApaga = false;
            }
            
            if (!bolApaga)
            {
                sessao.setAttribute("avisoErro", "Câmara está relacionado a movimentos. Inative para preservação dos dados");
                sessao.setAttribute("tipoAviso","alert alert-danger");
                sessao.setAttribute("pagOrigemErro", "FabricaGelo.Camara.AcaoAbreCamara");
                pagRetorno = "visao/erro.jsp";              
                
            }
            else
            {
                EquipamentoDAO equipamentoDAO = new EquipamentoDAO(conexao);
                if (equipamentoDAO.delete(camara))
                {
                    sessao.setAttribute("avisoErro", "Câmara deletado com sucesso");
                    sessao.setAttribute("tipoAviso","alert alert-successr");
                    sessao.setAttribute("pagOrigemErro", "FabricaGelo.Camara.AcaoListarCamara");
                    pagRetorno = "visao/erro.jsp";                     
                }
                
            }
                
        }            
        
        return pagRetorno;
        
    }
}
