<%-- 
    Document   : entrega
    Created on : 23/08/2018, 21:32:21
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Entrega</title>
    </head>
    <body>
                <form name="formEntrega" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Entrega do Movimento</b></h3></div>
                    <div class="panel-body">
              
                      
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
                                                    <li class="btn"><button type="button" class="btn btn-success" onClick="retornar()">Retornar</button></li>
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
                                        <label class="control-label">Ordem</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">Nota Fiscal</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">Lançamento</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">Tipo</label>
                                    </div>                                    
                                </div>
                                <div class="row">
                                    
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtNumero"  id="txtNumero" value="${movimento.numero}">
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtNotaFiscal" id="txtNotaFiscal" value="${movimento.notaFiscal}">
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="date" class="form-control" name="txtLancamento" id="txtLancamento" value="${movimento.dataLancamento}">
                                    </div>                                     
                                    <div class="col-sm-3">

                                        <select name="cmbTipo" class="form-control">

                                            <c:if test="${'AB' == movimento.situacao}">
                                                <option value="">  </option>
                                                <option value="AB" selected="selected"> ABERTA </option>
                                                <option value="FE"> FECHADO </option>
                                            </c:if>
                                            <c:if test="${'FE' == movimento.situacao}">
                                                <option value="">  </option>
                                                <option value="AB"> ABERTA </option>
                                                <option value="FE" selected="selected"> FECHADO </option>
                                            </c:if>  
                                            <c:if test="${'AB' != movimento.situacao && 'FE' != movimento.situacao}">
                                                <option value="" selected="selected">  </option>
                                                <option value="AB" selected="selected"> ABERTA </option>
                                                <option value="FE"> FECHADO </option>
                                            </c:if>

                                        </select>

                                    </div>
                                    
                                 
                                    
                                </div>
                            </div>
                            

                        </div> <!-- div primeira linha -->
                                
                        <div class="row"> <!-- div segunda linha -->
                            <div class="col-lg-12">
                                <div class="row">

                                    <div class="col-sm-12">
                                        <label class="control-label">Cliente</label>
                                    </div>
                                </div>
                                <div class="row">

                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" id="txtCliente" value="${movimento.colaborador}">
                                    </div>
                                    
                                </div>
                            </div>
                            

                        </div> <!-- div segunda linha -->
                        
                      
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">Registro de entrega</div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <label class="control-label">Profissional</label>
                                            </div>
                                            <div class="col-sm-5">
                                                <label class="control-label">Veículo</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Situação</label>
                                            </div>
                                            
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <div class="input-group">
                                                    <input type="text" class="form-control" id="txtProfissional" value="${entrega.profissional}">
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn btn-default" onClick="pesquisaProfissional()">...</button>
                                                    </div>                                            
                                                </div>
                                            </div>                                            
                                            <div class="col-sm-5">
                                                <div class="input-group">
                                                    <input type="text" class="form-control" id="txtVeiculo" value="${entrega.veiculo}">
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn btn-default" onClick="pesquisaVeiculo()">...</button>
                                                    </div>                                            
                                                </div>
                                            </div>
                                            <div class="col-sm-2">

                                                <select name="cmbSituacao" class="form-control">

                                                    <c:if test="${'EN' == entrega.situacao}">
                                                        <option value="">  </option>
                                                        <option value="EN" selected="selected"> ENTREGUE </option>
                                                        <option value="PE"> PENDENTE </option>
                                                    </c:if>
                                                    <c:if test="${'PE' == entrega.situacao}">
                                                        <option value="">  </option>
                                                        <option value="EN"> ENTREGUE </option>
                                                        <option value="PE" selected="selected"> PENDENTE </option>
                                                    </c:if>  
                                                    <c:if test="${'EN' != entrega.situacao && 'PE' != entrega.situacao}">
                                                        <option value="" selected="selected">  </option>
                                                        <option value="EN"> ENTREGUE </option>
                                                        <option value="PE"> PENDENTE </option>
                                                    </c:if>

                                                </select>

                                            </div>                                                    
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <label class="control-label">Data</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Km Inicial</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Km Final</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Saída</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Chegada</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Qt combustível</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <input type="date" class="form-control" name="txtDtEntrega" id="txtDtEntrega" value="${entrega.dataFormatada}">
                                            </div>                                     
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtKmInicial" id="txtKmInicial" value="${entrega.kmInicial}">
                                            </div>                                     
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtKmFinal" id="txtKmFinal" value="${entrega.kmFinal}">
                                            </div>                                     
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtHrSaida" id="txtHrSaida" value="${entrega.hrSaida}">
                                            </div>                                     
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtHrChegada" id="txtHrChegada" value="${entrega.hrChegada}">
                                            </div>                                     
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtLitros" id="txtLitros" value="${entrega.litros}">
                                            </div>                                     
                                            
                                        </div>
                                            
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">Custos da entrega</div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-8">
                                                <label class="control-label">Descrição</label>
                                            </div>
                                            <div class="col-sm-4">
                                                <label class="control-label">Valor</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" name="txtDescCusto" id="txtDescCusto" value="${custoEntrega.descricao}">
                                            </div>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" name="txtValor" id="txtValor" value="${custoEntrega.valor}">
                                            </div>
                                            
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <table class="table table-hover table-striped" overflow="scroll">
                                                    <tbody>
                                                        <tr>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left">Custo</div></th>
                                                            <th><div align="left">Valor</div></th>
                                                            
                                                        </tr> 
                                                        <c:forEach var="lstCustoEntr" items="${lstCustoEntrega}" varStatus="s">
                                                            <tr>
                                                                <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionarCusto('FabricaGelo.Entrega.AcaoSelecionaCustoEntrega?idCustoEntrega=${lstCustoEntr.idCustoEntrega}')">  
                                                                     <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="cancelarCusto('FabricaGelo.Entrega.AcaoCancelaCustoEntrega?idCustoEntrega=${lstCustoEntr.idCustoEntrega}')">  </td>
                                                                <td> ${lstCustoEntr.descricao} </td>
                                                                <td> ${lstCustoEntr.valor} </td>
                                                           
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
                document.forms[0].action="FabricaGelo.Entrega.AcaoGravaEntrega";
                document.forms[0].submit();
            }
            function retornar(caminho)
            {
                    document.forms[0].action="${pagRetorno}";
                    document.forms[0].submit();
            }            
            function pesquisaProfissional()
            {
                    document.forms[0].action="FabricaGelo.Entrega.AcaoBuscarProfissional";
                    document.forms[0].submit();                
            }selecionarCusto
            function pesquisaVeiculo()
            {
                    document.forms[0].action="FabricaGelo.Entrega.AcaoBuscarVeiculo";
                    document.forms[0].submit();                
            }
            function selecionarCusto(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            } 
            function cancelarCusto(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }            
        </script>        
    </body>
</html>
