<%-- 
    Document   : rotaIniciarTransito
    Created on : 18/01/2019, 04:51:55
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Iniciar Rota</title>
    </head>
    <body>
        <form name="formRotaIniciar" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><div class="panel-title text-center"><h3><b>Iniciar rota</b></h3></div></div>
                    <div class="panel-body">
                        

                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="confirmar()"> Confirme início de rota </button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="cancelar()">Cancelar</button></li>
                                                </ul>
                                            </div>
                                        </div>                                        
                                    </div>
                                </div>
                            </div>
                            
                            

                        </div> <!-- div dos botoes --> 

                        <div class="row">
                            <div class="col-lg-5">
                                <div class="row">
                                    <div class="col-sm-5">
                                        <label class="control-label">Horário</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="txtHrInicial" maxlength="2" id="txtHrInicial"> 
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="txtMnInicial" maxlength="2" id="txtMnInicial">
                                    </div>
                                </div>
                            </div>
                        </div>

                        
                    </div>
                </div>
            </div>
        </form>
        <script type="text/javascript">

            // chamada a eventos
            function confirmar()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoIniciarRota";
                document.forms[0].submit();
            }
            function cancelar()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoIniciarRota";
                document.forms[0].submit();
            }
            
            
            
        </script>            
    </body>
</html>
