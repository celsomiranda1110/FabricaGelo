package Controle.FabricaGelo.Gerais;

import javax.servlet.http.*;

// Referenced classes of package Controle.Estoque.Gerais:
//            Acao

public class AcaoSairSistema extends Acao
{

    public AcaoSairSistema()
    {
    }

    public String executa(HttpServletRequest req, HttpServletResponse res)
        throws Exception
    {
        HttpSession sessao = req.getSession(false);
        sessao.setAttribute("usuario", null);
        sessao.invalidate();
        return "index.jsp";
    }
}
