<%-- 
    Document   : insumo
    Created on : 09/09/2018, 21:25:19
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

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
                                        <input type="text" class="form-control" name="txtInsumo" id="txtInsumo" value="${insumo.descricao}">
                                    </div>
                                    
                                    <div class="col-sm-2">
                                        <input type="checkbox" name="ck_Ativo" ${insumo.descAtivo}> Inativo
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
            function excluir()
            {
                document.forms[0].action="FabricaGelo.Insumo.AcaoDeleteInsumo";
                document.forms[0].submit();
            }             
            function retornar()
            {
                document.forms[0].action="FabricaGelo.Insumo.AcaoListarInsumo";
                document.forms[0].submit();                
            }

        </script> 
    </body>
</html>

