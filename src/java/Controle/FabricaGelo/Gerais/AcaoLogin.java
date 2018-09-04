package Controle.FabricaGelo.Gerais;


import Bean.Colaborador;
import Bean.Profissional;
import Bean.Menu;
import DAO.ColaboradorDAO;
import DAO.ProfissionalDAO;
import DAO.MenuDAO;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import javax.servlet.http.*;
import java.util.*;

// Referenced classes of package Controle.Estoque.Gerais:
//            Acao

public class AcaoLogin extends Acao
{

    

    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        String retorno = "";
        
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//
//        // capta dados da página de login
//        String login = req.getParameter("txtLogin");
//        String senha = req.getParameter("txtSenha"); 
//        
//        // Busca funcionário de login na empresa
//        ProfissionalDAO funcionarioDAO = new ProfissionalDAO(conexao);
//        Profissional funcionario = new Profissional();
//        funcionario.setUsuario(login);
//        funcionario.setSenha(senha);
//        funcionario = funcionarioDAO.listaUm(funcionario);
//        if (funcionario != null)
//        {
//
//            // Lista do menu para definição de acesso
//            List lstMenu = new ArrayList();
//            //MenuDAO menuDAO = new MenuDAO(conexao);
//            //lstMenu = menuDAO.listaTodos(); 
//
//            
//            sessao.setAttribute("usuario", funcionario);
//            //sessao.setAttribute("lstMenuAcesso", funcionario.getLstFuncionarioMenu());
//            //sessao.setAttribute("lstMenuGeral", lstMenu);
//            retorno = "visao/principal.jsp";                
//                
//
//        }
//        else
//        {
//            sessao.setAttribute("mensagemUsuario", "Usu\341rio inv\341lido");
//            retorno = "index.jsp";
//        }
//        
//        return retorno;

        String login = req.getParameter("txtLogin");
        String senha = req.getParameter("txtSenha");
        
        // Busca funcionário de login na empresa
        ProfissionalDAO funcionarioDAO = new ProfissionalDAO(conexao);
        Profissional funcionario = new Profissional();
        List<Profissional> lstFuncionario = funcionarioDAO.listaTodos();
        for(Profissional _profissional : lstFuncionario)
        {
            if ((_profissional.getUsuario().equals(login)) && (_profissional.getSenha().equals(senha)))
            {
                funcionario = _profissional;
                break;
            }
        }
        
        List<Menu> lstMenu = new ArrayList();
        MenuDAO menuDAO = new MenuDAO(conexao);
        lstMenu = menuDAO.listaTodos(); 
        
        sessao.setAttribute("usuario", funcionario);
        sessao.setAttribute("lstMenuGeral", lstMenu);

        
        return "visao/principal.jsp";  

    }
}