<%-- 
    Document   : movimento
    Created on : 14/11/2018, 01:58:45
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Movimento</title>
        
    </head>
    <body>
        <form name="formMovimento" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Dados do movimento</b></h3></div>
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
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="fechar()">Fechar</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav text-center">
                                                    <li class="btn">
                                                        
                                                        <span class="badge badge-info">${movimento.descSituacao}  ${movimento.pagamento.descSituacao}</span>
                                                                                                           
                                                    </li>
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="gerarPagamento()">Pagamento</button></li>
                                                    
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
                                        <select name="cmbColaborador" class="form-control">
                                            <option value="0">... Selecione uma Empresa ...</option>
                                            <c:forEach var="colab" items="${lstColaborador}" varStatus="s">
                                              <c:if test="${colab.idColaborador == movimento.idColaborador}">
                                                <option value="${colab.idColaborador}" selected="selected">${colab.nome}</option>
                                              </c:if>
                                              <c:if test="${colab.idColaborador != movimento.idColaborador}">
                                                <option value="${colab.idColaborador}">${colab.nome}</option>
                                              </c:if>
                                            </c:forEach>
                                        </select>                                            
                                    </div>                                    
                                    <div class="col-sm-3">
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
                                    <div class="col-sm-2">
                                        <input type="date" class="form-control" name="txtLancamento" id="txtLancamento" value="${movimento.dataLancamento}">
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
                                            <select name="cmbProduto" class="form-control" onchange="buscaPreco()">
                                                <option value="0">... Selecione Produto/Serviço ...</option>
                                                <c:forEach var="prod" items="${lstProduto}" varStatus="s">
                                                  <c:if test="${prod.idProduto == produtoMovimento.idProduto}">
                                                    <option value="${prod.idProduto}" selected="selected">${prod.descricao}</option>
                                                  </c:if>
                                                  <c:if test="${prod.idProduto != produtoMovimento.idProduto}">
                                                    <option value="${prod.idProduto}">${prod.descricao}</option>
                                                  </c:if>
                                                </c:forEach>
                                            </select>                                            
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
                                            <input type="text" class="form-control" name="txtVlTotal" id="txtVlTotal" value="${produtoMovimento.valorTotal}">
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
                                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionarProduto('FabricaGelo.Movimento.AcaoSelecionaProduto?idProdutoMovimento=${lstProdMov.idProdutoMovimento}')">  
                                                                 <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="deletarProduto('FabricaGelo.Movimento.AcaoDeletaProduto?idProdutoMovimento=${lstProdMov.idProdutoMovimento}')">  </td>
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
                document.forms[0].action="FabricaGelo.Movimento.AcaoGravaMovimento";
                document.forms[0].submit();
            }
            function cancelar()
            {
                document.forms[0].action="FabricaGelo.Movimento.AcaoCancelarMovimento";
                document.forms[0].submit();
            }  
            function fechar()
            {
                document.forms[0].action="FabricaGelo.Movimento.AcaoFecharMovimento";
                document.forms[0].submit();
            }
            function pesquisaColaborador()
            {
                    document.forms[0].action="FabricaGelo.Movimento.AcaoBuscarColaborador";
                    document.forms[0].submit();                
            }
            function pesquisaProduto()
            {
                    document.forms[0].action="FabricaGelo.Movimento.AcaoBuscarProduto";
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
                    document.forms[0].action="FabricaGelo.Movimento.AcaoAbrePagamento";
                    document.forms[0].submit();                
            }               
            function buscaPreco()
            {
                    document.forms[0].action="FabricaGelo.Movimento.AcaoBuscaPreco";
                    document.forms[0].submit();                
            }             

        </script>                                                         
    </body>
</html>
