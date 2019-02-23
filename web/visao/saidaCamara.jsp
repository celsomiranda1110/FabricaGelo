<%-- 
    Document   : saidaCamara
    Created on : 30/12/2018, 11:57:48
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Informações sobre Carregamento</title>
        
    </head>
    <body>
        <form name="formSaidaCamara" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Dados de Carregamentos</b></h3></div>
                    <div class="panel-body">
                                
                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="atualizar()">Atualizar</button></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="fechar()">Fechar carregamento</button></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="devolver()">Devolver carregamento</button></li>
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
                                    <div class="col-sm-3">
                                        <label class="control-label">Data</label>
                                    </div>
                                    <div class="col-sm-9">
                                        <label class="control-label">Veículo</label>
                                    </div> 
                                </div>
                                <div class="row">
                                   
                                    <div class="col-sm-3">
                                        <input type="date" class="form-control" name="txtDataCarregamento" id="txtDataCarregamento" value="${saidaCamara.dataFormatada}">
                                    </div>
                                    
                                    <div class="col-sm-9">
                                        <select name="cmbVeiculo" class="form-control">
                                            <option value="0">... Selecione um Veículo ...</option>
                                            <c:forEach var="veic" items="${lstVeiculo}" varStatus="s">
                                              <c:if test="${veic.idEquipamento == saidaCamara.idEquipamento}">
                                                <option value="${veic.idEquipamento}" selected="selected">${veic.descricao}</option>
                                              </c:if>
                                              <c:if test="${veic.idEquipamento != saidaCamara.idEquipamento}">
                                                <option value="${veic.idEquipamento}">${veic.descricao}</option>
                                              </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    
                                </div>
                                
                            </div>
                            

                        </div> <!-- div primeira linha -->
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel-heading"><h4 class="panel-title text-left"><b>Estoque</b></h4></div>
                                <div class="jumbotron">
                                    
                                    <div class="row">

                                        <div class="col-sm-12">
                                            <label class="control-label">Produto</label>
                                        </div>

                                    </div>  
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <select name="cmbProduto" class="form-control" onchange="buscaEstoque()">
                                                <option value="0">... Selecione um Produto ...</option>
                                                <c:forEach var="prod" items="${lstProduto}" varStatus="s">
                                                  <c:if test="${prod.idProduto == pIdProduto}">
                                                    <option value="${prod.idProduto}" selected="selected">${prod.descricao}</option>
                                                  </c:if>
                                                  <c:if test="${prod.idProduto != pIdProduto}">
                                                    <option value="${prod.idProduto}">${prod.descricao}</option>
                                                  </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="table table-hover table-striped" overflow="scroll">
                                                <tbody>
                                                    <tr>
                                                        <th><div align="left"></div></th>
                                                        <th><div align="left">Câmara</div></th>
                                                        <th><div align="left">Saldo</div></th>
                                                        
                                                    </tr> 
                                                    <c:forEach var="lstProdCamara" items="${lstProdutoCamara}" varStatus="s">
                                                        <tr>
                                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onclick="selecionaProdutoCamara('FabricaGelo.Carregamento.AcaoSelecionaProdutoCamara?idProdutoCamara=${lstProdCamara.idProdutoCamara}')"> </td>
                                                            <td> ${lstProdCamara.equipamento} </td>
                                                            <td> ${lstProdCamara.saldo} </td>
                                                            
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>                                            
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-sm-8">
                                            <label class="control-label">Produto e Estoque selecionado</label>
                                        </div>
                                        <div class="col-sm-2">
                                            <label class="control-label">Qt de carregamento</label>
                                        </div>
                                        <div class="col-sm-2">
                                            <label class="control-label">Sobra</label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="txtEstoqueSelecionado" id="txtEstoqueSelecionado" value="${produtoCamara}" readonly="true">
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" name="txtQtSaida" id="txtQtSaida" value="${saidaCamara.saida}">
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" name="txtQtSobra" id="txtQtSobra" value="${saidaCamara.devolucao}">
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

            function atualizar()
            {
                document.forms[0].action="FabricaGelo.Carregamento.AcaoGravaCarregamento";
                document.forms[0].submit();
            }
            function selecionaProdutoCamara(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();                
            }
            function buscaEstoque()
            {
                document.forms[0].action="FabricaGelo.Carregamento.AcaoBuscaProdutoCamara";
                document.forms[0].submit();                
            }
            function retornar()
            {
                document.forms[0].action="FabricaGelo.Carregamento.AcaoListarCarregamento";
                document.forms[0].submit();                
            }
            function fechar()
            {
                document.forms[0].action="FabricaGelo.Carregamento.AcaoFecharCarregamento";
                document.forms[0].submit();                
            }
            function devolver()
            {
                document.forms[0].action="FabricaGelo.Carregamento.AcaoDevolverCarregamento";
                document.forms[0].submit();                
            }
        </script> 
    </body>
</html>
