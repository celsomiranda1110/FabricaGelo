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
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn"><button type="button" class="btn btn-success" onClick="atualizar()">Atualizar</button></li>
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="selecionar()">Selecionar</button></li>
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

                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">Manutenções</div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <label class="control-label">Prestador do serviço</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Início</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Fim</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Garantia</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Valor</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="text" class="form-control" id="txtColaborador"  value="${manutencao.colaborador}">
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn btn-default" onClick="pesquisaColaborador()">...</button>
                                                    </div>                                            
                                                </div>     
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="date" class="form-control" name="txtDtInicio" id="txtDtInicio" value="${manutencao.dataInicio}">
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="date" class="form-control" name="txtDtFinal" id="txtDtFinal" value="${manutencao.dataFim}">
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="date" class="form-control" name="txtDtGarantia" id="txtDtGarantia" value="${manutencao.dataGarantia}">
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtValor" id="txtValor" value="${manutencao.valor}">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <label class="control-label">Motivo</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <input type="text" class="form-control" name="txtMotivo" id="txtMotivo" value="${manutencao.motivo}">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <table class="table table-hover table-striped" overflow="scroll">
                                                <tbody>
                                                    <tr>
                                                        <th><div align="left"></div></th>
                                                        <th><div align="left">Prestador do serviço</div></th>
                                                        <th><div align="left">Inicio</div></th>
                                                        <th><div align="left">Fim</div></th>
                                                        <th><div align="left">Garantia</div></th>
                                                        <th><div align="left">Valor</div></th>
                                                    </tr> 
                                                    <c:forEach var="lstManut" items="${lstManutencao}" varStatus="s">
                                                        <tr>
                                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onclick="selecionaManutencao('FabricaGelo.Veiculo.AcaoSelecionaManutencao?idManutencao=${lstManut.idManutencao}')"> </td>
                                                            <td> ${lstManut.colaborador} </td>
                                                            <td> ${lstManut.dataInicio} </td>
                                                            <td> ${lstManut.dataFim} </td>
                                                            <td> ${lstManut.dataGarantia} </td>
                                                            <td> ${lstManut.valor} </td>
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
            function pesquisaColaborador()
            {
                document.forms[0].action="FabricaGelo.Maquina.AcaoBuscarColaborador";
                document.forms[0].submit();                
            }   
        </script> 
    </body>
</html>