package Controle.FabricaGelo.Gerais;

import Bean.Colaborador;
import DAO.ColaboradorDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;

// Referenced classes of package Controle.Estoque.Gerais:
//            Acao

public class AcaoMenu extends Acao
{

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
//        Connection conexao = (Connection)req.getAttribute("connection");
//        HttpSession sessao = req.getSession(false);
//        sessao.setAttribute("aviso", "");
//        return "visao/principal.jsp";
        Connection conexao = (Connection)req.getAttribute("connection");
        HttpSession sessao = req.getSession(false);
        
        List<Colaborador> lstColaborador = new ArrayList();
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO(conexao);
        
        lstColaborador = colaboradorDAO.listaTodos();
        sessao.setAttribute("lstColaborador",lstColaborador);

        return "visao/listarEmpresa.jsp";
    }
}
