<%-- 
    Document   : producaoFinalizar
    Created on : 21/02/2019, 03:35:39
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Informações sobre a Produção</title>
        
    </head>
    <body>
        <form name="formProducaoFinalizar" method="post">
            
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Para finalizar defina uma situação da produção</b></h3></div>
                    <div class="panel-body">
                        
                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                                                               
                                        <ul class="nav flex-column">
                                            
                                          <li class="nav-item">
                                            <button type="button" class="btn btn-primary btn-lg btn-block">Concluir produção</button>
                                          </li>
                                          <li class="nav-item">
                                            <button type="button" class="btn btn-primary btn-lg btn-block">Cancelar produção</button>
                                          </li>
                                          <li class="nav-item">
                                            <button type="button" class="btn btn-primary btn-lg btn-block" onClick="retornar()">Retornar para a tela de produção para revisão</button>
                                          </li>
                                          
                                        </ul>                                        
                                        
                                        
                                    </div>
                                </div>
                            </div>

                        </div> <!-- div dos botoes -->                           
                        
                    </div>
                </div>
            </div>
            
        </form>
        <script type="text/javascript">

            // chamada a eventos


            function retornar()
            {
                document.forms[0].action="FabricaGelo.Producao.AcaoAbreProducao";
                document.forms[0].submit();
            } 
            
        </script>         
    </body>
</html>
