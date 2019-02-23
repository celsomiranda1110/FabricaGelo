<%-- 
    Document   : pagamento
    Created on : 09/08/2018, 20:26:23
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Pagamento</title>
    </head>
    <body>
        <form name="formPagamento" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Pagamento do Movimento</b></h3></div>
                    <div class="panel-body">
              
                      
                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="atualizar()">Atualizar</button></li>
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="retornar()">Voltar ao movimento</button></li>
                                                </ul>
                                            </div>
                                        </div>                                        
                                    </div>
                                </div>
                            </div>

                        </div> <!-- div dos botoes -->                        
                                
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <label class="control-label">Tipo</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <label class="control-label">Número</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">Data</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">Situação</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <select name="cmbTipo" class="form-control">
                                            <c:if test="${'BO' != movimento.tipo && 'CP' != movimento.tipo && 'CO' != movimento.tipo && 'VE' != movimento.tipo && 'RE' != movimento.tipo}">
                                                <option value="" selected="selected">  </option>
                                                <option value="BO"> BONIFICAÇÃO </option>
                                                <option value="CP"> COMPRA </option>
                                                <option value="CO"> CORTESIA </option>
                                                <option value="VE"> VENDA </option>
                                                <option value="RE"> REPOSIÇÃO </option>
                                            </c:if>
                                            <c:if test="${'BO' == movimento.tipo}">
                                                <option value="">  </option>
                                                <option value="BO" selected="selected"> BONIFICAÇÃO </option>
                                                <option value="CP"> COMPRA </option>
                                                <option value="CO"> CORTESIA </option>
                                                <option value="VE"> VENDA </option>
                                                <option value="RE"> REPOSIÇÃO </option>
                                            </c:if>
                                            <c:if test="${'CP' == movimento.tipo}">
                                                <option value="">  </option>
                                                <option value="BO"> BONIFICAÇÃO </option>
                                                <option value="CP" selected="selected"> COMPRA </option>
                                                <option value="CO"> CORTESIA </option>
                                                <option value="VE"> VENDA </option>
                                                <option value="RE"> REPOSIÇÃO </option>
                                            </c:if>
                                            <c:if test="${'CO' == movimento.tipo}">
                                                <option value="">  </option>
                                                <option value="BO"> BONIFICAÇÃO </option>
                                                <option value="CP"> COMPRA </option>
                                                <option value="CO" selected="selected"> CORTESIA </option>
                                                <option value="VE"> VENDA </option>
                                                <option value="RE"> REPOSIÇÃO </option>
                                            </c:if>
                                            <c:if test="${'VE' == movimento.tipo}">
                                                <option value="">  </option>
                                                <option value="BO"> BONIFICAÇÃO </option>
                                                <option value="CP"> COMPRA </option>
                                                <option value="CO"> CORTESIA </option>
                                                <option value="VE" selected="selected"> VENDA </option>
                                                <option value="RE"> REPOSIÇÃO </option>
                                            </c:if>
                                            <c:if test="${'RE' == movimento.tipo}">
                                                <option value="">  </option>
                                                <option value="BO"> BONIFICAÇÃO </option>
                                                <option value="CP"> COMPRA </option>
                                                <option value="CO"> CORTESIA </option>
                                                <option value="VE"> VENDA </option>
                                                <option value="RE" selected="selected"> REPOSIÇÃO </option>
                                            </c:if>
                                        </select>                                        
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="txtNumero" id="txtNumero" value="${movimento.numero}">
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="date" class="form-control" name="txtLancamento" id="txtLancamento" value="${movimento.dataLancamento}">
                                    </div>
                                    <div class="col-sm-3">
                                        <select name="cmbSituacao" class="form-control">
                                            <c:if test="${'AB' != movimento.situacao && 'FE' != movimento.situacao}">
                                                <option value="" selected="selected">  </option>
                                                <option value="AB"> ABERTO </option>
                                                <option value="FE"> FECHADO </option>
                                            </c:if>
                                            <c:if test="${'AB' == movimento.situacao}">
                                                <option value="">  </option>
                                                <option value="AB" selected="selected"> ABERTO </option>
                                                <option value="FE"> FECHADO </option>
                                            </c:if>
                                            <c:if test="${'FE' == movimento.situacao}">
                                                <option value="">  </option>
                                                <option value="AB"> ABERTO </option>
                                                <option value="FE" selected="selected"> FECHADO </option>
                                            </c:if>
                                        </select>                                        

                                    </div>
                                </div>
                            </div>
                        </div>
                                    
                        <div class="row"> <!-- div segunda linha -->
                            <div class="col-lg-12">
                                <div class="row">
                                    
                                    <div class="col-sm-12">
                                        <label class="control-label">Empresa</label>
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
                                    <div class="panel-heading">Registro Pagamento - ${pagamento.descSituacao}</div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <label class="control-label">Valor da Nota</label>
                                            </div>
                                            <div class="col-sm-6">
                                                <label class="control-label">Cobrança</label>
                                            </div> 
                                            <div class="col-sm-3">
                                                <label class="control-label">Parcelas</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control" name="txtVlNota" id="txtVlNota" value="${pagamento.valor}">
                                            </div>
                                            <div class="col-sm-6">
                                                <select name="cmbCobranca" class="form-control">
                                                    <c:if test="${'0' == pagamento.cobranca}">
                                                        <option value="">  </option>
                                                        <option value="0" selected="selected"> À VISTA </option>
                                                        <option value="7"> SEMANAL </option>
                                                        <option value="15"> QUINZENAL </option>
                                                        <option value="30"> MENSAL </option>
                                                    </c:if>
                                                    <c:if test="${'7' == pagamento.cobranca}">
                                                        <option value="">  </option>
                                                        <option value="0"> À VISTA </option>
                                                        <option value="7" selected="selected"> SEMANAL </option>
                                                        <option value="15"> QUINZENAL </option>
                                                        <option value="30"> MENSAL </option>
                                                    </c:if>
                                                    <c:if test="${'15' == pagamento.cobranca}">
                                                        <option value="">  </option>
                                                        <option value="0"> À VISTA </option>
                                                        <option value="7"> SEMANAL </option>
                                                        <option value="15" selected="selected"> QUINZENAL </option>
                                                        <option value="30"> MENSAL </option>
                                                    </c:if>  
                                                    <c:if test="${'30' == pagamento.cobranca}">
                                                        <option value="">  </option>
                                                        <option value="0"> À VISTA </option>
                                                        <option value="7"> SEMANAL </option>
                                                        <option value="15"> QUINZENAL </option>
                                                        <option value="30" selected="selected"> MENSAL </option>
                                                    </c:if>
                                                    <c:if test="${'0' != pagamento.cobranca && '7' != pagamento.cobranca && '15' != pagamento.cobranca && '30' != pagamento.cobranca}">
                                                        <option value="" selected="selected">  </option>
                                                        <option value="0"> À VISTA </option>
                                                        <option value="7"> SEMANAL </option>
                                                        <option value="15"> QUINZENAL </option>
                                                        <option value="30"> MENSAL </option>
                                                    </c:if>                                                        

                                                </select>
                                            </div> 
                                            <div class="col-sm-3">
                                                 <input type="text" class="form-control" name="txtParcelas" id="txtParcelas" value="${pagamento.numParcela}">
                                            </div>
                                        </div>
                                            
                                       
                                    </div>
                                </div>
                            </div>
                        </div>
                                            
                        <div class="row">
                            <div class="col-lg-12">
                                
                                <div class="panel panel-default">
                                    <div class="panel-heading">Registro de pagamento das parcelas</div>
                                    <div class="panel-body">
                                        
                                        <div class="row">
                                            <div class="col-sm-1">
                                                <label class="control-label">Parcela</label>
                                            </div>
                                            <div class="col-sm-3">
                                                <label class="control-label">Vencimento</label>
                                            </div> 
                                            <div class="col-sm-3">
                                                <label class="control-label">Dt Pagamento</label>
                                            </div> 
                                            <div class="col-sm-1">
                                                <label class="control-label">Valor</label>
                                            </div> 
                                            <div class="col-sm-4">
                                                <label class="control-label">Forma pagamento</label>
                                            </div> 
                                        </div>
                                        
                                        <div class="row">
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" name="txtNuParcela" id="txtNuParcela" value="${parcela.numParcela}">
                                            </div>
                                            <div class="col-sm-3">
                                                <input type="date" class="form-control" name="txtDtVencimento" id="txtDtVencimento" value="${parcela.dtVencimento}">
                                            </div> 
                                            <div class="col-sm-3">
                                                <input type="date" class="form-control" name="txtDtPagamento" id="txtDtPagamento" value="${parcela.dtPagamento}">
                                            </div>
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" name="txtValorPago" id="txtValorPago" value="${parcela.valor}">
                                            </div>
                                            <div class="col-sm-4">
                                                <select name="cmbFormaPagamento" class="form-control">

                                                    <c:if test="${'DI' == parcela.formaPagamento}">
                                                        <option value="">  </option>
                                                        <option value="DI" selected="selected"> DINHEIRO </option>
                                                        <option value="DB"> DÉBITO </option>
                                                        <option value="CR"> CRÉDITO </option>
                                                        <option value="CT"> CARTEIRA </option>
                                                        <option value="TR"> TRANSFERÊNCIA </option>
                                                    </c:if>
                                                    <c:if test="${'DB' == parcela.formaPagamento}">
                                                        <option value="">  </option>
                                                        <option value="DI"> DINHEIRO </option>
                                                        <option value="DB" selected="selected"> DÉBITO </option>
                                                        <option value="CR"> CRÉDITO </option>
                                                        <option value="CT"> CARTEIRA </option>
                                                        <option value="TR"> TRANSFERÊNCIA </option>
                                                    </c:if>  
                                                    <c:if test="${'CR' == parcela.formaPagamento}">
                                                        <option value="">  </option>
                                                        <option value="DI"> DINHEIRO </option>
                                                        <option value="DB"> DÉBITO </option>
                                                        <option value="CR" selected="selected"> CRÉDITO </option>
                                                        <option value="CT"> CARTEIRA </option>
                                                        <option value="TR"> TRANSFERÊNCIA </option>
                                                    </c:if>
                                                    <c:if test="${'CT' == parcela.formaPagamento}">
                                                        <option value="">  </option>
                                                        <option value="DI"> DINHEIRO </option>
                                                        <option value="DB"> DÉBITO </option>
                                                        <option value="CR"> CRÉDITO </option>
                                                        <option value="CT" selected="selected"> CARTEIRA </option>
                                                        <option value="TR"> TRANSFERÊNCIA </option>
                                                    </c:if>
                                                    <c:if test="${'TR' == parcela.formaPagamento}">
                                                        <option value="">  </option>
                                                        <option value="DI"> DINHEIRO </option>
                                                        <option value="DB"> DÉBITO </option>
                                                        <option value="CR"> CRÉDITO </option>
                                                        <option value="CT"> CARTEIRA </option>
                                                        <option value="TR" selected="selected"> TRANSFERÊNCIA </option>
                                                    </c:if>                                                        
                                                    <c:if test="${'DI' != parcela.formaPagamento && 'DB' != parcela.formaPagamento && 'CR' != parcela.formaPagamento && 'CT' != parcela.formaPagamento && 'TR' != parcela.formaPagamento}">
                                                        <option value="" selected="selected">  </option>
                                                        <option value="DI"> DINHEIRO </option>
                                                        <option value="DB"> DÉBITO </option>
                                                        <option value="CR"> CRÉDITO </option>
                                                        <option value="CT"> CARTEIRA </option>
                                                        <option value="TR"> TRANSFERÊNCIA </option>
                                                    </c:if> 
                                                </select>
                                            </div>                                                                                  
                                        </div> 
                                                    
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <table class="table table-hover table-striped" overflow="scroll">
                                                    <tbody>
                                                        <tr>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left">Num Parcela</div></th>
                                                            <th><div align="left">Vencimento</div></th>
                                                            <th><div align="left">Pagamento</div></th>
                                                            <th><div align="left">Valor</div></th>
                                                            <th><div align="left">Forma de pagamento</div></th>
                                                        </tr> 
                                                        <c:forEach var="lstParc" items="${lstParcela}" varStatus="s">
                                                            <tr>
                                                                <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionarParcela('FabricaGelo.Pagamento.AcaoSelecionaParcela?idParcela=${lstParc.idParcela}')">  
                                                                     <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="cancelarParcela('FabricaGelo.Pagamento.AcaoCancelaParcela?idParcela=${lstParc.idParcela}')">  </td>
                                                                <td> ${lstParc.numParcela} </td>
                                                                <td> ${lstParc.dtVencimentoFormatado} </td>
                                                                <td> ${lstParc.dtPagamentoFormatado} </td>
                                                                <td> ${lstParc.valor} </td>
                                                                <td> ${lstParc.descFormaPagamento} </td>
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
                document.forms[0].action="FabricaGelo.Pagamento.AcaoGravaPagamento";
                document.forms[0].submit();
            }
            function pesquisaFormaPagamento()
            {
                    document.forms[0].action="FabricaGelo.Pagamento.AcaoBuscarFormaPagamento";
                    document.forms[0].submit();                
            }
            function retornar(caminho)
            {
                    document.forms[0].action="${pagRetorno}";
                    document.forms[0].submit();
            }            
            function selecionarParcela(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();
            } 
        </script>        
    </body>
</html>
