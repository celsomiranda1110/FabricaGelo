<%-- 
    Document   : aluguel
    Created on : 23/08/2018, 17:08:00
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Informações sobre a Empresa</title>
        
    </head>
    <body>
        <form name="formAluguel" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Dados da Aluguel</b></h3></div>
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
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="gerarPagamento()">Pagamento</button></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="gerarEntrega()">Entrega</button></li>
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
                                        <label class="control-label">Situação</label>
                                    </div>                                    
                                </div>
                                <div class="row">
                                    
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtNumero"  id="txtNumero" value="${aluguel.numero}">
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtNotaFiscal" id="txtNotaFiscal" value="${aluguel.notaFiscal}">
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="date" class="form-control" name="txtLancamento" id="txtLancamento" value="${aluguel.dataLancamento}">
                                    </div>                                     
                                    <div class="col-sm-3">

                                        <select name="cmbSituacao" class="form-control">

                                            <c:if test="${'AB' == aluguel.situacao}">
                                                <option value="">  </option>
                                                <option value="AB" selected="selected"> ABERTA </option>
                                                <option value="FE"> FECHADO </option>
                                            </c:if>
                                            <c:if test="${'FE' == aluguel.situacao}">
                                                <option value="">  </option>
                                                <option value="AB"> ABERTA </option>
                                                <option value="FE" selected="selected"> FECHADO </option>
                                            </c:if>  
                                            <c:if test="${'AB' != aluguel.situacao && 'FE' != aluguel.situacao}">
                                                <option value="" selected="selected">  </option>
                                                <option value="AB"> ABERTA </option>
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
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="txtCliente" value="${aluguel.colaborador}">
                                            <div class="input-group-btn">
                                                <button type="button" class="btn btn-default" onClick="pesquisaColaborador()">...</button>
                                            </div>                                            
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                            

                        </div> <!-- div segunda linha -->
                        
 
                        <div class="row">
                            <div class="col-lg-12">
                                
                                <div class="panel panel-default">
                                  <!-- Default panel contents -->
                                  <div class="panel-heading">Produtos</div>
                                  <div class="panel-body">
                                      
                                   
                                    
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label class="control-label">Produto/Serviço</label>
                                        </div>
                                        <div class="col-sm-1">
                                            <label class="control-label">Qt</label>
                                        </div>                                        
                                        <div class="col-sm-1">
                                            <label class="control-label">Vl Unit</label>
                                        </div>                                        
                                        <div class="col-sm-1">
                                            <label class="control-label">Icms</label>
                                        </div> 
                                        <div class="col-sm-1">
                                            <label class="control-label">Desc</label>
                                        </div>
                                        <div class="col-sm-1">
                                            <label class="control-label">Vl Total</label>
                                        </div> 
                                        <div class="col-sm-1">
                                            <label class="control-label">Avarias</label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="txtProduto"  value="${aluguelProduto.produto}">
                                                <div class="input-group-btn">
                                                    <button type="button" class="btn btn-default" onClick="pesquisaProduto()">...</button>
                                                </div>                                            
                                            </div>                                            
                                            
                                        </div>
                                        <div class="col-sm-1">
                                            <input type="text" class="form-control" name="txtQuantidade" id="txtQuantidade" value="${aluguelProduto.quantidade}">
                                        </div> 
                                        <div class="col-sm-1">
                                            <input type="text" class="form-control" name="txtVlUnitario" id="txtVlUnitario" value="${aluguelProduto.valor}">
                                        </div>                                        
                                        <div class="col-sm-1">
                                            <input type="text" class="form-control" name="txtIcms" id="txtIcms" value="${aluguelProduto.icms}">
                                        </div>
                                        <div class="col-sm-1">
                                            <input type="text" class="form-control" name="txtVlDesconto" id="txtVlDesconto" value="${aluguelProduto.desconto}">
                                        </div>                                          
                                        <div class="col-sm-1">
                                            <input type="text" class="form-control" name="txtVlTotal" id="txtVlTotal" value="${aluguelProduto.valorTotal}">
                                        </div>  
                                        <div class="col-sm-1">
                                            <input type="text" class="form-control" name="txtQtAvaria" id="txtQtAvaria" value="${aluguelProduto.qtAvaria}">
                                        </div>
                                    </div>

                                    <!-- Table -->
                                    <div class="row">
                                        <table class="table table-hover table-striped" overflow="scroll">
                                            <tbody>
                                                <tr>
                                                    <th><div align="left"></div></th>
                                                    <th><div align="left">Produto</div></th>
                                                    <th><div align="left">Quantidade</div></th>
                                                    <th><div align="left">Vl Unitário</div></th>
                                                    <th><div align="left">Icms</div></th>
                                                    <th><div align="left">Desconto</div></th>
                                                    <th><div align="left">Vl Total</div></th>
                                                    <th><div align="left">Avaria</div></th>
                                                </tr> 
                                                <c:forEach var="lstProdVend" items="${lstProdutoAluguel}" varStatus="s">
                                                    <tr>
                                                        <c:set var="tQuantidade" value="${tQuantidade + lstProdVend.quantidade}" />
                                                        <c:set var="tVlItens" value="${tVlItens + lstProdVend.valorTotal}" />
                                                        <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionarProduto('FabricaGelo.Aluguel.AcaoSelecionaProduto?idProdutoMovimento=${lstProdVend.idProdutoMovimento}')">  
                                                             <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="deletarProduto('FabricaGelo.Aluguel.AcaoDeletaProduto?idProdutoMovimento=${lstProdVend.idProdutoMovimento}')">  </td>
                                                        <td> ${lstProdVend.produto} </td>
                                                        <td> ${lstProdVend.quantidade} </td>
                                                        <td> ${lstProdVend.valor} </td>
                                                        <td> ${lstProdVend.icms} </td>
                                                        <td> ${lstProdVend.desconto} </td>
                                                        <td> ${lstProdVend.valorTotal} </td>
                                                        <td> ${lstProdVend.qtAvaria} </td>
                                                    </tr>
                                                </c:forEach>
                                                <tr>
                                                    <th><div align="left"></div></th>
                                                    <td></td>
                                                    <td> ${tQuantidade} </td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><fmt:formatNumber value="${tVlItens}" pattern="#,#00.00#"/></td>
                                                    <td></td>
                                                </tr>
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
                document.forms[0].action="FabricaGelo.Aluguel.AcaoGravaAluguel";
                document.forms[0].submit();
            }
            function pesquisaProduto()
            {
                    document.forms[0].action="FabricaGelo.Aluguel.AcaoBuscarProduto";
                    document.forms[0].submit();                
            }
            function pesquisaColaborador()
            {
                    document.forms[0].action="FabricaGelo.Aluguel.AcaoBuscarEmpresa";
                    document.forms[0].submit();                
            } 
            function selecionarProduto(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }
            function deletarProduto(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }     
            function gerarPagamento()
            {
                    document.forms[0].action="FabricaGelo.Aluguel.AcaoAbrePagamento";
                    document.forms[0].submit();                
            }               
            function gerarEntrega()
            {
                    document.forms[0].action="FabricaGelo.Aluguel.AcaoAbreEntrega";
                    document.forms[0].submit();                
            }

        </script>        
    </body>
</html>
