<%-- 
    Document   : rotaFinalizar
    Created on : 18/01/2019, 13:00:56
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                                                                        <td> ${lstProduEntr.dblQuantidadeReposicao} </td>
                                                                        <td> ${lstProduEntr.dblSaldo} </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>                                                            
                                                    </div>
                                                    
                                                    <div class="panel-footer">
                                                        <div class="row">
                                                            <div class="col-sm-12">
                                                                <div class="panel-title text-center"><h5>Avarias ${produtoEntrega.produto}</h5></div>
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
                                                           
                        <div class="row">
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
                                                                <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionaColaboradorEntrega('FabricaGelo.Entrega.AcaoSelecionaColaboradorEntrega?idColaboradorEntrega=${varColaboradorEntrega.idColaboradorEntrega}')">  </td>
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
                                                <div class="panel-title text-center"><h5>Movimentos ${colaboradorEntrega.cliente}</h5></div>
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
                                                                    <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionaMovimentoEntrega('FabricaGelo.MovimentoEntrega.AcaoSelecionaMovimentoEntrega?idMovimentoEntrega=${varMovimentoEntrega.idMovimentoEntrega}')">  </td>
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
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading"><h4><b>Abastecimentos</b></h4></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <label class="control-label">Posto</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">NF</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Valor do litro</label>
                                            </div> 
                                            <div class="col-sm-2">
                                                <label class="control-label">Litros</label>
                                            </div> 
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <select name="cmbAvaria" class="form-control">
                                                    <option value="0">... Selecione um posto ...</option>
                                                    <c:forEach var="colab" items="${lstColaborador}" varStatus="s">
                                                      <option value="${colab.idColaborador}">${colab.razaoSocial}</option>
                                                    </c:forEach>
                                                </select> 
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtNota" id="txtNota" value="${abastecimento.nota}">    
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtVlLitro" id="txtVlLitro" value="${abastecimento.vlUnitario}">    
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtQtLitro" id="txtQtLitro" value="${abastecimento.quantidade}">    
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <table class="table table-hover table-striped" overflow="scroll">
                                                    <tbody>
                                                        <tr>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left">Posto</div></th>
                                                            <th><div align="left">Valor</div></th>
                                                            <th><div align="left">Litros</div></th>
                                                        </tr> 
                                                        <c:forEach var="lstAbastec" items="${lstAbastecimento}" varStatus="s">
                                                            <tr>
                                                                <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionarAbastecimento('FabricaGelo.Entrega.AcaoSelecionaAbastecimento?idAbastecimento=${lstAbastec.idAbastecimento}')">  
                                                                     <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="excluirAbasteimento('FabricaGelo.Entrega.AcaoCancelaAbastecimento?idAbastecimento=${lstAbastec.idAbastecimento}')">  </td>
                                                                <td> ${lstAbastec.colaborador} </td>
                                                                <td> ${lstAbastec.vlUnitario} </td>
                                                                <td> ${lstAbastec.quantidade} </td>
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
                                           
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading"><h4><b>Outros custos de entrega</b></h4></div>
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
            function selecionaMovimentoEntrega(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
            }

        </script>                                             
    </body>
</html>
