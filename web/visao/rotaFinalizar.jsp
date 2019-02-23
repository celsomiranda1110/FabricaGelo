<%-- 
    Document   : rotaFinalizar
    Created on : 18/01/2019, 13:00:56
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Rota</title>
    </head>
    <body>
        <form name="formRotaFinalizar" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><div class="panel-title text-center"><h3><b>Finalização de rota</b></h3></div></div>
                    <div class="panel-body">
                        
                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="atualizar()">Atualizar</button></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="finalizarTransito()">Finalizar rota</button></li>
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
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <a href="#divVeiculo" class="badge badge-info">Veículo e carga</a>
                                        <a href="#divMovimento" class="badge badge-info">Movimentos com clientes</a>
                                        <a href="#divCusto" class="badge badge-info">Custos de entrega</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row" id="divVeiculo">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading"><h4><b>Veículo e carga</b></h4></div>
                                    <div class="panel-body">
                                        
                                        
                                        <div class="row">
                                            <div class="navbar navbar-default">
                                                <div class="container-fluid">
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" name="txtVeiculo" id="txtVeiculo" readonly="true" value="${entrega.veiculo}">                                                
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" name="txtKmInicial" id="txtKmInicial" readonly="true" value="${entrega.kmInicial}">
                                                    </div>                                                    
                                                </div>
                                            </div>

                                        </div> 
                                            
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading"><h5>Carregamento</h5></div>
                                                    <div class="panel-body">
                                                        
                                                        <table class="table table-hover table-striped" overflow="scroll">
                                                            <tbody>
                                                                <tr>
                                                                    <th><div align="left"></div></th>
                                                                    <th><div align="left">Produto</div></th>
                                                                    <th><div align="left">Qt</div></th>
                                                                    <th><div align="left">Qt Bônus</div></th>
                                                                    <th><div align="left">Qt Cortesia</div></th>
                                                                    <th><div align="left">Qt Avaria</div></th>
                                                                    <th><div align="left">Qt Reposição</div></th>
                                                                    <th><div align="left">Qt Venda</div></th>
                                                                    <th><div align="left">Saldo</div></th>
                                                                </tr> 
                                                                <c:forEach var="lstProduEntr" items="${lstProdutoEntrega}" varStatus="s">
                                                                    <tr>
                                                                        <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionarProduto('FabricaGelo.Entrega.AcaoSelecionaProduto?idProdutoEntrega=${lstProduEntr.idProdutoEntrega}')"> </td>
                                                                        <td> ${lstProduEntr.produto} </td>
                                                                        <td> ${lstProduEntr.dblQuantidade} </td>
                                                                        <td> ${lstProduEntr.dblQuantidadeBonus} </td>
                                                                        <td> ${lstProduEntr.dblQuantidadeCortesia} </td>
                                                                        <td> ${lstProduEntr.dblQuantidadeAvaria} </td>
                                                                        <td> ${lstProduEntr.dblQuantidadeReposicao} </td>
                                                                        <td> ${lstProduEntr.dblQuantidadeVenda} </td>
                                                                        <td> ${lstProduEntr.dblSaldo} </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>                                                            
                                                    </div>
                                                    
                                                    <div class="panel-footer">
                                                        <div class="row">
                                                            <div class="col-sm-12">
                                                                <div class="alert alert-info">${produtoEntrega.produto}</div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-8">
                                                                <select name="cmbAvaria" class="form-control">
                                                                    <option value="0">... Selecione uma avaria ...</option>
                                                                    <c:forEach var="avari" items="${lstAvaria}" varStatus="s">
                                                                      <option value="${avari.idAvaria}">${avari.descricao}</option>
                                                                    </c:forEach>
                                                                </select> 
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <input type="text" class="form-control" name="txtQtAvariaPE" id="txtQtAvariaPE" placeholder="quantidade" value="${avariaEntrega.quantidade}">    
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <button type="button" class="btn btn-default" onClick="addAvaria()">Adicionar</button>
                                                            </div>
                                                        </div>  
                                                        <div class="row">
                                                            <div class="col-sm-12">
                                                                <table class="table table-hover table-striped" overflow="scroll">
                                                                    <tbody>
                                                                        <tr>
                                                                            <th><div align="left"></div></th>
                                                                            <th><div align="left">Avaria</div></th>
                                                                            <th><div align="left">Quantidade</div></th>
                                                                        </tr> 
                                                                        <c:forEach var="lstAvariaEntr" items="${lstAvariaEntrega}" varStatus="s">
                                                                            <tr>
                                                                                <td> <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="excluirAvaria('FabricaGelo.Entrega.AcaoExcluirAvaria?idAvariaEntrega=${lstAvariaEntr.idAvariaEntrega}')">  </td>
                                                                                <td> ${lstAvariaEntr.avaria} </td>
                                                                                <td> ${lstAvariaEntr.quantidade} </td>
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
                                                           
                        <div class="row" id="divMovimento">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading"><h4><b>Movimentos com clientes</b></h4></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <table class="table table-hover table-striped" overflow="scroll">
                                                    <tbody>
                                                        <tr>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left">CNPJ</div></th>
                                                            <th><div align="left">RAZÃO SOCIAL</div></th>
                                                            <th><div align="left">BAIRRO</div></th>
                                                            <th><div align="left">MOTIVO</div></th>
                                                        </tr> 
                                                        <c:forEach var="varColaboradorEntrega" items="${lstColaboradorEntrega}" varStatus="s">
                                                            <tr>
                                                                <td><input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionaColaboradorEntrega('FabricaGelo.Entrega.AcaoSelecionaColaboradorEntrega?idColaboradorEntrega=${varColaboradorEntrega.idColaboradorEntrega}')"></td>
                                                                <td> ${varColaboradorEntrega.cliente.cnpj} </td>
                                                                <td> ${varColaboradorEntrega.cliente.razaoSocial} </td>
                                                                <td> ${varColaboradorEntrega.cliente.bairro} </td>
                                                                <td> ${varColaboradorEntrega.descMotivo} </td>
                                                            </tr>
                                                        </c:forEach>                                                        
                                                    </tbody>
                                                </table>
                                            </div>  
                                        </div>
                                    </div>
                                    <div class="panel-footer">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="alert alert-info">${colaboradorEntrega.cliente}</div>
                                            </div>
                                        </div>   
                                            <div class="row">
                                                <div class="col-sm-10">
                                                    <select name="cmbTipoMovimento" class="form-control">
                                                        <option value="">... Selecione um tipo de movimento ...</option>
                                                        <option value="BO"> BONIFICAÇÃO </option>
                                                        <option value="CP"> COMPRA </option>
                                                        <option value="CO"> CORTESIA </option>
                                                        <option value="VE"> VENDA </option>
                                                        <option value="RE"> REPOSIÇÃO </option>
                                                    </select> 
                                                </div>
                                                
                                                <div class="col-sm-2">
                                                    <button type="button" class="btn btn-default" onClick="addMovimento()">Adicionar</button>
                                                </div>
                                            </div> 
                                            
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <table class="table table-hover table-striped" overflow="scroll">
                                                        <tbody>
                                                            <tr>
                                                                <th><div align="left"></div></th>
                                                                <th><div align="left">TIPO</div></th>
                                                                <th><div align="left">NÚMERO</div></th>
                                                                <th><div align="left">SITUAÇÃO</div></th>
                                                            </tr> 
                                                            <c:forEach var="varMovimentoEntrega" items="${lstMovimentoEntrega}" varStatus="s">
                                                                <tr>
                                                                    <td><input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionaMovimentoEntrega('FabricaGelo.MovimentoEntrega.AcaoSelecionaMovimentoEntrega?idMovimentoEntrega=${varMovimentoEntrega.idMovimentoEntrega}')"></td>
                                                                    <td> ${varMovimentoEntrega.movimento.descTipo} </td>
                                                                    <td> ${varMovimentoEntrega.movimento.numero} </td>
                                                                    <td> ${varMovimentoEntrega.movimento.descSituacao} </td>
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
                        
                        <div class="row" id="divCusto">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading"><h4><b>Custos de entrega</b></h4></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <label class="control-label">Tipo empreendimento</label>
                                            </div>
                                            <div class="col-sm-8">
                                                <label class="control-label">Fornecedor</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <select name="cmbEmpreendimento" class="form-control" onchange="buscarColaborador()">
                                                    <option value="0">... Selecione um tipo de empreendimento ...</option>
                                                    <c:forEach var="varTipoEmp" items="${lstTipoColaborador}" varStatus="s">
                                                        <c:if test="${varTipoEmp.idTipoColaborador == paramPesquisa}">
                                                            <option value="${varTipoEmp.idTipoColaborador}" selected="selected">${varTipoEmp.descricao}</option>
                                                        </c:if>
                                                        <c:if test="${varTipoEmp.idTipoColaborador != paramPesquisa}">
                                                            <option value="${varTipoEmp.idTipoColaborador}">${varTipoEmp.descricao}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select> 
                                            </div>
                                            <div class="col-sm-8">
                                                <select name="cmbColaborador" class="form-control">
                                                    <option value="0">... Selecione um fornecedor ...</option>
                                                    <c:forEach var="colab" items="${lstColaborador}" varStatus="s">
                                                        <c:if test="${colab.idColaborador == custoEntrega.getIdColaborador}">
                                                            <option value="${colab.idColaborador}" selected="selected">${colab.razaoSocial}</option>
                                                        </c:if>
                                                        <c:if test="${colab.idColaborador != custoEntrega.getIdColaborador}">
                                                            <option value="${colab.idColaborador}">${colab.razaoSocial}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select> 
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <label class="control-label">NF</label>
                                            </div>
                                            <div class="col-sm-4">
                                                <label class="control-label">Serviço/Produto</label>
                                            </div> 
                                            <div class="col-sm-1">
                                                <label class="control-label">Qt</label>
                                            </div> 
                                            <div class="col-sm-2">
                                                <label class="control-label">Vl Unit.</label>
                                            </div>
                                            <div class="col-sm-3">
                                                <label class="control-label">Vl Tot.</label>
                                            </div>
                                            
                                        </div>
                                        <div class="row">
                                            
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtNota" id="txtNota" value="${custoEntrega.numero}">    
                                            </div>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" name="txtDescricao" id="txtDescricao" value="${custoEntrega.descricao}">    
                                            </div>
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" name="txtQtCusto" id="txtQtCusto" value="${custoEntrega.quantidade}">    
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtVlUnitCusto" id="txtVlUnitCusto" value="${custoEntrega.vlUnitario}">    
                                            </div>
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control" name="txtTotalCusto" id="txtTotalCusto" readonly="true" value="${custoEntrega.vlTotal}">    
                                                <button type="button" class="btn btn-default" onClick="addCusto()">Adicionar</button>
                                            </div>
                                            
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <table class="table table-hover table-striped" overflow="scroll">
                                                    <tbody>
                                                        <tr>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left">Fornecedor</div></th>
                                                            <th><div align="left">Serviço/Produto</div></th>
                                                            <th><div align="left">Vl Unit.</div></th>
                                                            <th><div align="left">Qt</div></th>
                                                            <th><div align="left">Vl Total</div></th>
                                                        </tr> 
                                                        <c:forEach var="varCustoEntrega" items="${lstCustoEntrega}" varStatus="s">
                                                            <tr>
                                                                <td> <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="excluirCustoEntrega('FabricaGelo.Entrega.AcaoExcluirCustoEntrega?idCustoEntrega=${varCustoEntrega.idCustoEntrega}')">  </td>
                                                                <td> ${varCustoEntrega.colaborador} </td>
                                                                <td> ${varCustoEntrega.descricao} </td>
                                                                <td> ${varCustoEntrega.vlUnitario} </td>
                                                                <td> ${varCustoEntrega.quantidade} </td>
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
            function retornar()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoListarEntrega";
                document.forms[0].submit();
            }
            function addCusto()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoAdicionaCusto";
                document.forms[0].submit();
            }
            function addAvaria()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoAdicionaAvaria";
                document.forms[0].submit();
            }
            function excluirAvaria(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
            }
            function addMovimento()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoAdicionaMovimento";
                document.forms[0].submit();
            }
            function selecionarProduto(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
            }
            function selecionaColaboradorEntrega(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
            }
            function cancelaColaboradorEntrega(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
            }
            function selecionaMovimentoEntrega(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
            }
            function selecionarCustoEntrega(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
            }
            function excluirCustoEntrega(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
            }
            function buscarColaborador()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoPesquisaColaboradorCusto";
                document.forms[0].submit();
            }

        </script>                                             
    </body>
</html>
