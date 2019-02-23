<%-- 
    Document   : movimentoEntrega
    Created on : 21/01/2019, 13:30:17
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Movimento</title>
        
    </head>
    <body>
        <form name="formMovimentoEntrega" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Movimento de rotas</b></h3></div>
                    <div class="panel-body">
                        
                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="atualizar()">Atualizar</button></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="cancelar()">Cancelar</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav text-center">
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="retornar()">Rota</button></li>
                                                    
                                                </ul>
                                            </div>
                                        </div>                                        
                                    </div>
                                </div>
                            </div>

                        </div> <!-- div dos botoes -->     
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">Movimento</div>
                                    <div class="panel-body">
                                        
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <label class="control-label">Empresa</label>
                                            </div>
                                            <div class="col-sm-3">
                                                <label class="control-label">Tipo</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Número</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Data</label>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <input type="text" class="form-control" name="txtColaborador" id="txtColaborador" readonly="true" value="${movimento.colaborador}"> 
                                            </div>                                    
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control" name="txtTipoMovimento" id="txtTipoMovimento" readonly="true" value="${movimento.descTipo}">                                         
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtNumero" id="txtNumero" value="${movimento.numero}">
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="date" class="form-control" name="txtLancamento" id="txtLancamento" value="${movimento.dataLancamento}">
                                            </div>

                                        </div>
                                    
                                    </div>
                                </div>
                            </div>
                        </div>
                       
                        <div class="row">
                            <div class="col-lg-12">
                                
                                <div class="panel panel-default">
                                  <!-- Default panel contents -->
                                  <div class="panel-heading">Produtos</div>
                                  <div class="panel-body">

                                    <div class="row">
                                        <div class="col-sm-7">
                                            <label class="control-label">Produto/Serviço</label>
                                        </div>
                                        <div class="col-sm-1">
                                            <label class="control-label">Vl Unit</label>
                                        </div>
                                        <div class="col-sm-1">
                                            <label class="control-label">Qt</label>
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
                                        
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-7">
                                            <input type="text" class="form-control" name="txtProdutoMovimentoEntrega" id="txtProdutoMovimentoEntrega" readonly="true" value="${produtoMovimento.produto}">                                            
                                        </div>
                                        <div class="col-sm-1">
                                            <input type="text" class="form-control" name="txtVlUnitario" id="txtVlUnitario" value="${produtoMovimento.valor}">
                                        </div>
                                        <div class="col-sm-1">
                                            <input type="text" class="form-control" name="txtQuantidade" id="txtQuantidade" value="${produtoMovimento.quantidade}">
                                        </div> 
                                        <div class="col-sm-1">
                                            <input type="text" class="form-control" name="txtIcms" id="txtIcms" value="${produtoMovimento.icms}">
                                        </div>
                                        <div class="col-sm-1">
                                            <input type="text" class="form-control" name="txtVlDesconto" id="txtVlDesconto" value="${produtoMovimento.desconto}">
                                        </div>                                          
                                        <div class="col-sm-1">
                                            <input type="text" class="form-control" name="txtVlTotal" id="txtVlTotal" readonly="true" value="${produtoMovimento.valorTotal}">
                                        </div>  
                                        
                                    </div>

                                    <!-- Table -->
                                    <div class="row">
                                        <div class="col-sm-12">
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

                                                    </tr> 
                                                    <c:forEach var="lstProdMov" items="${lstProdutoMovimento}" varStatus="s">
                                                        <tr>
                                                            <c:set var="tQuantidade" value="${tQuantidade + lstProdMov.quantidade}" />
                                                            <c:set var="tVlItens" value="${tVlItens + lstProdMov.valorTotal}" />
                                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionarProduto('FabricaGelo.MovimentoEntrega.AcaoSelecionaProduto?idProdutoMovimento=${lstProdMov.idProdutoMovimento}')"></td>
                                                            <td> ${lstProdMov.produto} </td>
                                                            <td> ${lstProdMov.quantidade} </td>
                                                            <td> ${lstProdMov.valor} </td>
                                                            <td> ${lstProdMov.icms} </td>
                                                            <td> ${lstProdMov.desconto} </td>
                                                            <td> ${lstProdMov.valorTotal} </td>

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
            </div>
        </form>
        <script type="text/javascript">

            // chamada a eventos

            function atualizar()
            {
                document.forms[0].action="FabricaGelo.MovimentoEntrega.AcaoGravaMovimentoEntrega";
                document.forms[0].submit();
            }
            function cancelar()
            {
                document.forms[0].action="FabricaGelo.MovimentoEntrega.AcaoCancelarMovimentoEntrega";
                document.forms[0].submit();
            }  
            function retornar()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoEncerrarRota";
                document.forms[0].submit();
            }
            function selecionarProduto(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
            }  

        </script>                                                         
    </body>
</html>

