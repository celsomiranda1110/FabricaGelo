package DAO;


public class Parametro
{

    String valor;
    String tipo;
    String retorno;
    String nomeParam;

    public Parametro(String valor, String tipo, String retorno)
    {
        this.valor = "";
        this.valor = valor;
        this.tipo = tipo;
        this.retorno = retorno;
    }

    public Parametro(String valor, String tipo)
    {
        this.valor = "";
        this.valor = valor;
        this.tipo = tipo;
        retorno = "in";
    }

    public Parametro()
    {
        valor = "";
        retorno = "in";
    }

    public String getTipo()
    {
        return tipo;
    }

    public String getValor()
    {
        return valor;
    }

    public String getRetorno()
    {
        return retorno;
    }

    public String getNomeParam()
    {
        return nomeParam;
    }
}
