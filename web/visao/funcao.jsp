<%-- 
    Document   : funcao
    Created on : 19/07/2018, 11:06:18
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Informações sobre a Funcao</title>
        
    </head>
    <body>
        <form name="formFuncao" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Dados do Funcao</b></h3></div>
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
                                    <div class="col-sm-12">
                                        <label class="control-label">Descrição</label>
                                    </div>
                                    
                                    
                                </div>
                                <div class="row">
                                   
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="txtFuncao" id="txtFuncao" value="${funcao.descricao}">
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="checkbox" name="ck_Ativo" ${funcao.descAtivo}> Inativo
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
                var descricao = formFuncao.txtFuncao.value;
                if (descricao == "")
                {
                    alert('Necessário descrição do funcao!');
                    return false;
                }
                else
                {
                    document.forms[0].action="FabricaGelo.Funcao.AcaoGravaFuncao";
                    document.forms[0].submit();
                }
            }
           function excluir()
            {
                document.forms[0].action="FabricaGelo.Funcao.AcaoDeleteFuncao";
                document.forms[0].submit();
            }            
            function retornar()
            {
                document.forms[0].action="FabricaGelo.Funcao.AcaoListarFuncao";
                document.forms[0].submit();                
            }

        </script> 
    </body>
</html>
