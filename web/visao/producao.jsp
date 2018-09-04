<%-- 
    Document   : producao
    Created on : 28/08/2018, 05:48:53
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Informações sobre Produção</title>
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
                                        <button type="button" class="btn btn-primary" onClick="atualizar()">Atualizar</button>
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
                                    <div class="col-sm-2">
                                        <label class="control-label">Turno</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="control-label">Embalagem</label>
                                    </div>
                                    <div class="col-sm-1">
                                        <label class="control-label">Quantidade</label>
                                    </div>
                                    <div class="col-sm-1">
                                        <label class="control-label">Qt ant.</label>
                                    </div>
                                    <div class="col-sm-1">
                                        <label class="control-label">Avaria</label>
                                    </div>                                    
                                    <div class="col-sm-1">
                                        <label class="control-label">Sobra</label>
                                    </div>                                    
                                </div>
                                <div class="row">
                                   
                                    <div class="col-sm-2">
                                        <input type="date" class="form-control" name="txtData" id="txtData" value="${producao.data}">
                                    </div>
                                    <div class="col-sm-2">
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
                                    <div class="col-sm-4">
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="txtEmbalagem"  value="${producao.produto}">
                                            <div class="input-group-btn">
                                                <button type="button" class="btn btn-default" onClick="pesquisaEmbalagem()">...</button>
                                            </div>                                            
                                        </div> 
                                    </div>
                                    <div class="col-sm-1">
                                        <input type="text" class="form-control" name="txtQuantidade" id="txtQuantidade" value="${producao.quantidade}">
                                    </div>
                                    <div class="col-sm-1">
                                        <input type="text" class="form-control" name="txtQuantidadeAnterior" id="txtQuantidadeAnterior" value="${producao.quantidadeAnterior}">
                                    </div>
                                    <div class="col-sm-1">
                                        <input type="text" class="form-control" name="txtAvaria" id="txtAvaria" value="${producao.qtAvaria}">
                                    </div>
                                    <div class="col-sm-1">
                                        <input type="text" class="form-control" name="txtSobra" id="txtSobra" value="${producao.sobra}">
                                    </div>
                                </div>
                            </div>
                            

                        </div> <!-- div primeira linha -->
                        
                        <div class="row">
                            <div class="col-lg-12">
                                
                                <div class="row">
                                    <div class="col-sm-6">
                                        <label class="control-label">Máquina</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <label class="control-label">Hr Inicial</label>
                                    </div> 
                                    <div class="col-sm-2">
                                        <label class="control-label">Hr Final</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <label class="control-label">Rend.</label>
                                    </div>
                                </div>  
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="txtEmbalagem"  value="${producao.maquina}">
                                            <div class="input-group-btn">
                                                <button type="button" class="btn btn-default" onClick="pesquisaMaquina()">...</button>
                                            </div>                                            
                                        </div> 
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="txtHrInicial" id="txtHrInicial" value="${producao.horaInicial}">
                                    </div> 
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="txtHrFinal" id="txtHrFinal" value="${producao.horaFinal}">
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="txtRendimento" id="txtRendimento" value="${producao.rendimento}">
                                    </div>
                                </div>                                         
                                 
                            </div>
                        </div>
                                            
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">Avarias</div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <label class="control-label">Motivo da avaria</label>
                                            </div> 
                                            <div class="col-sm-2">
                                                <label class="control-label">Quantidade</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <div class="input-group">
                                                    <input type="text" class="form-control" id="txtEmbalagem"  value="${avariaProducao.avaria}">
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn btn-default" onClick="pesquisaAvaria()">...</button>
                                                    </div>                                            
                                                </div>
                                            </div> 
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtQuantidadeAvaria" id="txtQuantidadeAvaria" value="${avariaProducao.quantidade}">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <table class="table table-hover table-striped">
                                                    <tbody>
                                                        <tr>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left">Avaria</div></th>
                                                            <th><div align="left">Quantidade</div></th>

                                                        </tr>
                                                        <c:forEach var="lstAvariaProd" items="${lstAvariaProducao}" varStatus="s">
                                                        <tr>
                                                          <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="SelecionaAvariaProducao('FabricaGelo.Producao.AcaoSelecionaAvariaProducao?idAvariaProducao=${lstAvariaProd.idAvariaProducao}')">  </td>
                                                          <td> ${lstAvariaProd.avaria} </td>
                                                          <td> ${lstAvariaProd.quantidade} </td>
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
        </form>
        <script type="text/javascript">

            // chamada a eventos

            function atualizar()
            {
                document.forms[0].action="FabricaGelo.Producao.AcaoGravaProducao";
                document.forms[0].submit();
            }
            function pesquisaEmbalagem()
            {
                document.forms[0].action="FabricaGelo.Producao.AcaoBuscarProduto";
                document.forms[0].submit();
            }            
            function pesquisaMaquina()
            {
                document.forms[0].action="FabricaGelo.Producao.AcaoBuscarMaquina";
                document.forms[0].submit();
            }
            function pesquisaAvaria()
            {
                document.forms[0].action="FabricaGelo.Producao.AcaoBuscarAvaria";
                document.forms[0].submit();
            }            
        </script> 
    </body>
</html>