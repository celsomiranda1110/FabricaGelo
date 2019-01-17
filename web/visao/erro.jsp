<%-- 
    Document   : erro
    Created on : 08/08/2018, 16:51:57
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Aviso de Erros</title>
        
    </head>
    <body>
        <form name="formErro" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="alert alert-danger">
                              <strong>Mensagem: </strong> ${avisoErro}
                            </div> 
                        </div>
                    </div>

                    <div class="row"> <!-- div dos botoes -->

                        <div class="col-lg-12">
                            <div class="row">
                                <div class="col-sm-12">
                                    <button type="button" class="btn btn-primary" onClick="retornar()">Retornar</button>
                                </div>
                            </div>
                        </div>

                    </div> <!-- div dos botoes --> 
                </div>
            </div>
        </form>
        <script type="text/javascript">

            // chamada a eventos

            function retornar()
            {
                document.forms[0].action="${pagOrigemErro}";
                document.forms[0].submit();                
            }

        </script>                 
    </body>
</html>
