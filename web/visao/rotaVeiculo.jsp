<%-- 
    Document   : rotaVeiculo
    Created on : 08/01/2019, 22:29:18
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
        <form name="formRotaVeiculo" method="post">
            
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><div class="panel-title text-center"><h3><b>Definição de veículo</b></h3></div></div>
                    <div class="panel-body">
                        
                        <div class="row"> 
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <label class="control-label">Veículo</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Km Inicial</label>
                                            </div>                                             
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <select name="cmbVeiculo" class="form-control" onchange="buscarVeiculo()">
                                                    <option value="0">... Selecione um Veículo ...</option>
                                                    <c:forEach var="veic" items="${lstVeiculo}" varStatus="s">
                                                      <c:if test="${veic.idEquipamento == entrega.idEquipamento}">
                                                        <option value="${veic.idEquipamento}" selected="selected">${veic.descricao}</option>
                                                      </c:if>
                                                      <c:if test="${veic.idEquipamento != entrega.idEquipamento}">
                                                        <option value="${veic.idEquipamento}">${veic.descricao}</option>
                                                      </c:if>
                                                    </c:forEach>
                                                </select>                                                 
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtKmInicial" id="txtKmInicial" readonly="true" value="${entrega.kmInicial}">
                                            </div>
                                        </div>
                                                      
                                        

                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading"><h4><b>Carregamento</b></h4></div>
                                                    <div class="panel-body">
                                                        
                                                        <table class="table table-hover table-striped" overflow="scroll">
                                                            <tbody>
                                                                <tr>
                                                                    <th><div align="left">Produto</div></th>
                                                                    <th><div align="left">Quantidade</div></th>

                                                                </tr> 
                                                                <c:forEach var="lstProduEntr" items="${lstProdutoEntrega}" varStatus="s">
                                                                    <tr>
                                                                        <td> ${lstProduEntr.produto} </td>
                                                                        <td> ${lstProduEntr.dblQuantidade} </td>

                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>                                                            
                                                        
                                                                                                                
                                                    </div>
                                                </div>                                            
                                            </div>
                                        </div>
                                              
                                            
                                        <div class="row">
                                            <div class="navbar navbar-default">
                                                <div class="container-fluid">
                                                    <ul class="nav navbar-nav navbar-right">
                                                        <li class="btn"><button type="button" class="btn btn-default" onClick="adicionaProfissional()">Profissionais</button></li>
                                                    </ul>

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
                document.forms[0].action="FabricaGelo.Entrega.AcaoBuscarProfissional";
                document.forms[0].submit();
            }
        </script>                                                                            
    </body>
</html>
