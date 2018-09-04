<%-- 
    Document   : maquina
    Created on : 22/07/2018, 01:26:12
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Informações sobre a Maquina</title>
        
    </head>
    <body>
        <form name="formMaquina" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Dados do Maquina</b></h3></div>
                    <div class="panel-body">
                        
                        <div class="row">
                            <div class="col-lg-12">
                                
                                <span class="label label-danger">${avisoMaquina}</span>
                                
                            </div>
                        </div>
                   
                                
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
                                    <div class="col-sm-10">
                                        <label class="control-label">Descrição</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <label class="control-label">Situação</label>
                                    </div>                                    
                                        
                                </div>
                                <div class="row">
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="txtDescricao" id="txtDescricao" value="${maquina.descricao}">
                                    </div>
                                    <div class="col-sm-2">
                                        <select name="cmbSituacao" class="form-control">

                                            <c:if test="${'A' == maquina.situacao}">
                                                <option value="">  </option>
                                                <option value="A" selected="selected"> Ativo </option>
                                                <option value="I"> Inativo </option>
                                            </c:if>

                                            <c:if test="${'I' == maquina.situacao}">
                                                <option value="">  </option>
                                                <option value="A" > Ativo </option>
                                                <option value="I" selected="selected"> Inativo </option>
                                            </c:if>  
                                            <c:if test="${'A' != maquina.situacao && 'I' != maquina.situacao}">
                                                <option value="" selected="selected">  </option>
                                                <option value="A"> Ativo </option>
                                                <option value="I"> Inativo </option>
                                            </c:if>

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
                var descricao = formMaquina.txtDescricao.value;
                if (descricao == "")
                {
                    alert('Necessário identificação do maquina!');
                    return false;
                }
                else
                {
                    document.forms[0].action="FabricaGelo.Maquina.AcaoGravaMaquina";
                    document.forms[0].submit();
                }
            }
            function selecionar()
            {
                document.forms[0].action="FabricaGelo.Maquina.AcaoRetornaPagina";
                document.forms[0].submit();                
            }

        </script> 
    </body>
</html>