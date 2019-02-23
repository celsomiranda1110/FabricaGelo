<%-- 
    Document   : producaoIniciar
    Created on : 08/02/2019, 11:01:14
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Iniciar Produção</title>
    </head>
    <body>
        <form name="formProducao" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Dados de Produção</b></h3></div>
                    <div class="panel-body">
                       
                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="atualizar()">Atualizar</button></li>
                                                    <li class="btn"><label class="control-label">${producao.descSituacao}</label></li>
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
                                <div class="row">
                                    <div class="col-sm-2">
                                        <label class="control-label">Data</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">Turno</label>
                                    </div>
                                    <div class="col-sm-7">
                                        <label class="control-label">Produto</label>
                                    </div>
                                    
                                                                       
                                </div>
                                <div class="row">
                                   
                                    <div class="col-sm-2">
                                        <input type="date" class="form-control" name="txtData" id="txtData" value="${producao.data}">
                                    </div>
                                    <div class="col-sm-3">
                                        <select name="cmbTurno" class="form-control">

                                            <c:if test="${'MA' == producao.turno}">
                                                <option value="">  </option>
                                                <option value="MA" selected="selected"> MATUTINO </option>
                                                <option value="VE"> VESPERTINO </option>
                                                <option value="NO"> NOTURNO </option>
                                            </c:if>
                                            <c:if test="${'VE' == producao.turno}">
                                                <option value="">  </option>
                                                <option value="MA"> MATUTINO </option>
                                                <option value="VE" selected="selected"> VESPERTINO </option>
                                                <option value="NO"> NOTURNO </option>
                                            </c:if>
                                            <c:if test="${'NO' == producao.turno}">
                                                <option value="">  </option>
                                                <option value="MA"> MATUTINO </option>
                                                <option value="VE"> VESPERTINO </option>
                                                <option value="NO" selected="selected"> NOTURNO </option>
                                            </c:if>
                                            <c:if test="${'MA' != producao.turno && 'VE' != producao.turno && 'NO' != producao.turno}">
                                                <option value=""  selected="selected">  </option>
                                                <option value="MA"> MATUTINO </option>
                                                <option value="VE"> VESPERTINO </option>
                                                <option value="NO"> NOTURNO </option>
                                            </c:if>

                                        </select>
                                    </div>
                                    <div class="col-sm-7">
                                        <select name="cmbProduto" class="form-control">
                                            <option value="0">... Selecione um Produto ...</option>
                                            <c:forEach var="prod" items="${lstProduto}" varStatus="s">
                                              <c:if test="${prod.idProduto == producao.idProduto}">
                                                <option value="${prod.idProduto}" selected="selected">${prod.descricao}</option>
                                              </c:if>
                                              <c:if test="${prod.idProduto != producao.idProduto}">
                                                <option value="${prod.idProduto}">${prod.descricao}</option>
                                              </c:if>
                                            </c:forEach>
                                        </select>
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
                
                document.forms[0].action="FabricaGelo.Producao.AcaoIniciarProducao";
                document.forms[0].submit();
            }

            function retornar()
            {
 
                document.forms[0].action="FabricaGelo.Producao.AcaoListarProducao";
                document.forms[0].submit();
            } 
            
        </script> 
    </body>
</html>
