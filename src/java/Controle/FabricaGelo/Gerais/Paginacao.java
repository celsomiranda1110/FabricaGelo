package Controle.FabricaGelo.Gerais;

import java.util.ArrayList;
import java.util.List;

public class Paginacao
{

    private List listaObjetos;
    private List arrayLista[];
    private int LIMITEPAGINA;
    private int numPaginas;
    private static int paginaAtual = 0;

    public Paginacao(List lista, int numPaginas)
    {
        LIMITEPAGINA = 0;
        this.numPaginas = 0;
        if(lista != null)
        {
            listaObjetos = (ArrayList)lista;
        }
        if(numPaginas == 0)
        {
            LIMITEPAGINA = 5;
        } else
        {
            LIMITEPAGINA = numPaginas;
        }
        criaPaginas();
    }

    private void criaPaginas()
    {
        int indiceMinimo = 0;
        if(listaObjetos.size() > LIMITEPAGINA)
        {
            numPaginas = Math.round(listaObjetos.size() / LIMITEPAGINA);
        }
        arrayLista = new List[++numPaginas];
        for(int i = 0; i < numPaginas; i++)
        {
            if(indiceMinimo + LIMITEPAGINA < listaObjetos.size())
            {
                arrayLista[i] = listaObjetos.subList(indiceMinimo, indiceMinimo + LIMITEPAGINA);
            } else
            {
                arrayLista[i] = listaObjetos.subList(indiceMinimo, listaObjetos.size());
            }
            indiceMinimo += LIMITEPAGINA;
        }

    }

    public List getPrimeiraPagina()
    {
        paginaAtual = 0;
        if(arrayLista != null)
        {
            return arrayLista[paginaAtual];
        } else
        {
            return null;
        }
    }

    public List getProximaPagina()
    {
        paginaAtual++;
        if(paginaAtual > numPaginas - 1)
        {
            paginaAtual--;
        }
        if(arrayLista != null)
        {
            return arrayLista[paginaAtual];
        } else
        {
            return null;
        }
    }

    public List getAnteriorPagina()
    {
        paginaAtual--;
        if(paginaAtual < 0)
        {
            paginaAtual++;
        }
        if(arrayLista != null)
        {
            return arrayLista[paginaAtual];
        } else
        {
            return null;
        }
    }

    public List getUltimaPagina()
    {
        paginaAtual = numPaginas - 1;
        if(arrayLista != null)
        {
            return arrayLista[paginaAtual];
        } else
        {
            return null;
        }
    }

    public List getPaginaAtual()
    {
        if(arrayLista != null)
        {
            return arrayLista[paginaAtual];
        } else
        {
            return null;
        }
    }

    public int getNumeroPaginas()
    {
        return numPaginas;
    }

}
