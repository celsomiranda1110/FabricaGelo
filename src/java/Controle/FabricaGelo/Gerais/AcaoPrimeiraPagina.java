package Controle.FabricaGelo.Gerais;

import java.util.List;
import javax.servlet.http.*;

// Referenced classes of package Controle.Estoque.Gerais:
//            Acao, Paginacao

public class AcaoPrimeiraPagina extends Acao
{

    public AcaoPrimeiraPagina()
    {
    }

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        HttpSession sessao = req.getSession(false);
        String nomePagina = (String)sessao.getAttribute("nomePagina");
        String nomeLista = (String)sessao.getAttribute("nomeLista");
        Paginacao pagina = new Paginacao((List)sessao.getAttribute(nomeLista), 10);
        req.setAttribute("listagem", pagina.getPrimeiraPagina());
        return nomePagina;
    }
}
