<%-- 
    Document   : rotaProfissionais
    Created on : 09/01/2019, 02:03:56
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Cadastro de rota</title>
    </head>
    <body>
        <form name="formRotaProfissionais" method="post">
            
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><div class="panel-title text-center"><h3><b>Definição de profissionais</b></h3></div></div>
                    <div class="panel-body">
                        
                        <div class="row"> 
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <label class="control-label">Profissional</label>
                                            </div>
                                                                                       
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <select name="cmbProfissional" class="form-control">
                                                    <option value="0">... Selecione um profissional ...</option>
                                                    <c:forEach var="prof" items="${lstProfissional}" varStatus="s">
                                                        <option value="${prof.idProfissional}">${prof}</option>
                                                    </c:forEach>
                                                </select>                                                 
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="navbar navbar-default">
                                                    <div class="container-fluid">
                                                        <ul class="nav navbar-nav">
                                                            <li class="btn"><button type="button" class="btn btn-default" onClick="adicionaProfissional()">Adicionar</button></li>
                                                        </ul>
                                                        
                                                    </div>
                                                </div>                                                 
                                            </div>
                                        </div>
                                                      
                                        

                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading"><h4><b>Profissionais da rota</b></h4></div>
                                                    <div class="panel-body">
                                                        
                                                        <table class="table table-hover table-striped" overflow="scroll">
                                                            <tbody>
                                                                <tr>
                                                                    <th><div align="left">Profissional</div></th>
                                                                    
                                                                </tr> 
                                                                <c:forEach var="lstProfisEntr" items="${lstProfissionalEntrega}" varStatus="s">
                                                                    <tr>
                                                                        <td> ${lstProfisEntr.profissional} </td>
                                                                        

                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>                                                            
                                                        
                                                                                                                
                                                    </div>
                                                </div>                                            
                                            </div>
                                        </div>
                                                      
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

            function buscarVeiculo()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoBuscarVeiculo";
                document.forms[0].submit();
            }
            function adicionaProfissional()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoAdicionaProfissional";
                document.forms[0].submit();
            }
        </script>                                                                            
    </body>
</html>
