<%-- 
    Document   : insumo
    Created on : 09/09/2018, 21:25:19
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Informações sobre a Insumo</title>
        
    </head>
    <body>
        <form name="formInsumo" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Dados do Insumo</b></h3></div>
                    <div class="panel-body">
                        

                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn"><button type="button" class="btn btn-success" onClick="atualizar()">Atualizar</button></li>
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="selecionar()">Selecionar</button></li>
                                                </ul>
                                            </div>
                                        </div>                                        
                                    </div>
                                </div>
                            </div>

                        </div> <!-- div dos botoes --> 
                                
                        <div class="row"> <!-- div primeira linha -->
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-10">
                                        <label class="control-label">Descrição</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <label class="control-label">Estoque</label>
                                    </div>                                    
                                </div>
                                <div class="row">
                                   
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="txtInsumo" id="txtInsumo" value="${insumo.descricao}">
                                    </div>
                                    
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="txtSaldo" id="txtSaldo" value="${insumo.saldo}">
                                    </div>                                    
                                </div>
                            </div>
                            

                        </div> <!-- div primeira linha -->
                       
                    </div>
                
                </div>
            </div> 
        </form>
        <script type="text/javascript">

            // chamada a eventos

            function atualizar()
            {
                var descricao = formInsumo.txtInsumo.value;
                if (descricao == "")
                {
                    alert('Necessário descrição do insumo!');
                    return false;
                }
                else
                {
                    document.forms[0].action="FabricaGelo.Insumo.AcaoGravaInsumo";
                    document.forms[0].submit();
                }
            }
            function selecionar()
            {
                document.forms[0].action="FabricaGelo.Insumo.AcaoRetornaPagina";
                document.forms[0].submit();                
            }

        </script> 
    </body>
</html>

