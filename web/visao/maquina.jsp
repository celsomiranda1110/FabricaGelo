<%-- 
    Document   : maquina
    Created on : 22/07/2018, 01:26:12
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

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
                                        <input type="text" class="form-control" name="txtDescricao" id="txtDescricao" value="${maquina.descricao}">
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtMarca" id="txtMarca" value="${maquina.marca}">
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="txtModelo" id="txtModelo" value="${maquina.modelo}">
                                    </div>
                                    <div class="col-sm-1">
                                        <input type="checkbox" name="ck_Ativo" ${maquina.descAtivo}> Inativo
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
            function excluir()
            {
                document.forms[0].action="FabricaGelo.Maquina.AcaoDeleteMaquina";
                document.forms[0].submit();
            }            
            function retornar()
            {
                document.forms[0].action="FabricaGelo.Maquina.AcaoListarMaquina";
                document.forms[0].submit();                
            }
              
        </script> 
    </body>
</html>