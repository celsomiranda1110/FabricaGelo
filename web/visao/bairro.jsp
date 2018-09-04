<%-- 
    Document   : bairro
    Created on : 19/07/2018, 10:26:23
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Informações sobre bairros</title>
        
    </head>
    <body>
        <form name="formBairro" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Dados de Bairros</b></h3></div>
                    <div class="panel-body">
                        
                        <div class="row">
                            <div class="col-lg-12">
                                
                                <span class="label label-danger">${avisoBairro}</span>
                                
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
                                    <div class="col-sm-12">
                                        <label class="control-label">Descrição</label>
                                    </div>
                                                                       
                                </div>
                                <div class="row">
                                   
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" name="txtBairro" id="txtBairro" value="${bairro.descricao}">
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
                var descricao = formBairro.txtBairro.value;
                if (descricao == "")
                {
                    alert('Necessário descrição de bairro!');
                    return false;
                }
                else
                {
                    document.forms[0].action="FabricaGelo.Bairro.AcaoGravaBairro";
                    document.forms[0].submit();
                }
            }
            function selecionar()
            {
                document.forms[0].action="FabricaGelo.Bairro.AcaoRetornaPagina";
                document.forms[0].submit();                
            }

        </script> 
    </body>
</html>