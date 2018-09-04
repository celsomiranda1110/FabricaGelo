<%-- 
    Document   : veiculo
    Created on : 22/07/2018, 00:32:48
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Informações sobre a Veiculo</title>
        
    </head>
    <body>
        <form name="formVeiculo" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Dados do Veiculo</b></h3></div>
                    <div class="panel-body">
                        
                        
                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <button type="button" class="btn btn-primary" onClick="atualizar()">Atualizar</button>
                                        <button type="button" class="btn btn-default" onClick="selecionar()">Selecionar</button>
                                    </div>
                                </div>
                            </div>

                        </div> <!-- div dos botoes -->
                                
                        <div class="row"> <!-- div primeira linha -->
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <label class="control-label">Placa</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">Marca</label>
                                    </div>                                    
                                    <div class="col-sm-3">
                                        <label class="control-label">Modelo</label>
                                    </div> 
                                    <div class="col-sm-3">
                                        <label class="control-label">Ano</label>
                                    </div>                                     
                                </div>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtPlaca" id="txtPlaca" value="${veiculo.placa}">
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtMarca" id="txtMarca" value="${veiculo.marca}">
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtModelo" id="txtModelo" value="${veiculo.modelo}">
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtAno" id="txtAno" value="${veiculo.ano}">
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
                var descricao = formVeiculo.txtPlaca.value;
                if (descricao == "")
                {
                    alert('Necessário identificação do veiculo!');
                    return false;
                }
                else
                {
                    document.forms[0].action="FabricaGelo.Veiculo.AcaoGravaVeiculo";
                    document.forms[0].submit();
                }
            }
            function selecionar()
            {
                document.forms[0].action="FabricaGelo.Veiculo.AcaoRetornaPagina";
                document.forms[0].submit();                
            }

        </script> 
    </body>
</html>

