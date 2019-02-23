<%-- 
    Document   : veiculo
    Created on : 22/07/2018, 00:32:48
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

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
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="atualizar()">Atualizar</button></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="excluir()">Excluir</button></li>
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="retornar()">Retornar</button></li>
                                                </ul>
                                            </div>
                                        </div>                                        
                                    </div>
                                </div>
                            </div>

                        </div> <!-- div dos botoes --> 
                                
                        <div class="row"> <!-- div primeira linha -->
                            <div class="col-lg-12">
                                <div class="navbar navbar-default">
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <label class="control-label">Descrição</label>
                                            </div>
                                            <div class="col-sm-3">
                                                <label class="control-label">Marca</label>
                                            </div>                                    
                                            <div class="col-sm-3">
                                                <label class="control-label">Modelo</label>
                                            </div> 
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" name="txtDescricao" id="txtDescricao" value="${veiculo.descricao}">
                                            </div>
                                            
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control" name="txtMarca" id="txtMarca" value="${veiculo.marca}">
                                            </div>
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control" name="txtModelo" id="txtModelo" value="${veiculo.modelo}">
                                            </div>
                                            
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <label class="control-label">Placa</label>
                                            </div>
                                            <div class="col-sm-3">
                                                <label class="control-label">Ano</label>
                                            </div> 
                                            <div class="col-sm-3">
                                                <label class="control-label">Quilometragem</label>
                                            </div> 
                                            <div class="col-sm-3">
                                                <label class="control-label">Capacidade (Kg)</label>
                                            </div>                                            
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control" name="txtPlaca" id="txtPlaca" value="${veiculo.placa}">
                                            </div>
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control" name="txtAno" id="txtAno" value="${veiculo.ano}">
                                            </div>   
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control" name="txtQuilometragem" id="txtQuilometragem" value="${veiculo.quilometragem}" readonly="true">
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtCapacidade" id="txtCapacidade" value="${veiculo.capacidade}" onkeypress="return SomenteNumero(event)">
                                            </div>
                                            <div class="col-sm-1">
                                                <input type="checkbox" name="ck_Ativo" ${veiculo.descAtivo}> Inativo
                                            </div> 
                                        </div>
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
                
                document.forms[0].action="FabricaGelo.Veiculo.AcaoGravaVeiculo";
                document.forms[0].submit();
               
            }
            function excluir()
            {
                document.forms[0].action="FabricaGelo.Veiculo.AcaoDeleteVeiculo";
                document.forms[0].submit();
            }
            function retornar()
            {
                document.forms[0].action="FabricaGelo.Veiculo.AcaoListarVeiculo";
                document.forms[0].submit();                
            }
            function SomenteNumero(e){
                var tecla=(window.event)?event.keyCode:e.which;   
                if((tecla>47 && tecla<58) || (tecla == 44)) return true;
                else{
                    if (tecla==8 || tecla==0) return true;
                    else  return false;
                }
            }
                     
                    
        </script> 
    </body>
</html>

