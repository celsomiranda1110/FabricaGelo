<%-- 
    Document   : entrega
    Created on : 23/08/2018, 21:32:21
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Entrega</title>
    </head>
    <body>
        <form name="formEntrega" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><div class="panel-title text-center"><b>Dados de rota</b></div></div>
                    <div class="panel-body">
              
                      
                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="atualizar()">Atualizar</button></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="fechar()">Fechar</button></li>
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
                                    <div class="panel-heading"><h3><b>Registro de entrega</b></h3></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <label class="control-label">Profissional</label>
                                            </div>
                                            <div class="col-sm-6">
                                                <label class="control-label">Veículo</label>
                                            </div>
                                            
                                            
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <select name="cmbProfissional" class="form-control">
                                                    <option value="0">... Selecione um Profissional ...</option>
                                                    <c:forEach var="profis" items="${lstProfissional}" varStatus="s">
                                                      <c:if test="${profis.idProfissional == entrega.idProfissional}">
                                                        <option value="${profis.idProfissional}" selected="selected">${profis.nome}</option>
                                                      </c:if>
                                                      <c:if test="${profis.idProfissional != entrega.idProfissional}">
                                                        <option value="${profis.idProfissional}">${profis.nome}</option>
                                                      </c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>                                            
                                            <div class="col-sm-6">
                                                <select name="cmbVeiculo" class="form-control">
                                                    <option value="0">... Selecione um Veículo ...</option>
                                                    <c:forEach var="veic" items="${lstVeiculo}" varStatus="s">
                                                      <c:if test="${veic.idEquipamento == entrega.idEquipamento}">
                                                        <option value="${veic.idEquipamento}" selected="selected">${veic.descricao}</option>
                                                      </c:if>
                                                      <c:if test="${veic.idEquipamento != entrega.idEquipamento}">
                                                        <option value="${veic.idEquipamento}">${veic.descricao}</option>
                                                      </c:if>
                                                    </c:forEach>
                                                </select>                                           
                                                
                                            </div>
                                                                                          
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <label class="control-label">Data</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Saída</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Chegada</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Km Inicial</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Km Final</label>
                                            </div>                                            
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <input type="date" class="form-control" name="txtDtEntrega" id="txtDtEntrega" value="${entrega.dataFormatada}">
                                            </div>   
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtHrSaida" id="txtHrSaida" value="${entrega.hrSaida}">
                                            </div>                                     
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtHrChegada" id="txtHrChegada" value="${entrega.hrChegada}">
                                            </div> 
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtKmInicial" id="txtKmInicial" readonly="true" value="${entrega.kmInicial}">
                                            </div>                                     
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtKmFinal" id="txtKmFinal" value="${entrega.kmFinal}">
                                            </div>                                     
                                                                                
                                            
                                        </div>
                                            
                                        
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading"><h3>Produtos da entrega e possíveis avarias</h3></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <label class="control-label">Produto</label>
                                            </div>  
                                            <div class="col-sm-1">
                                                <label class="control-label">Qt</label>
                                            </div>
                                            <div class="col-sm-1">
                                                <label class="control-label">Bônus</label>
                                            </div> 
                                            <div class="col-sm-1">
                                                <label class="control-label">Cortesia</label>
                                            </div>  
                                            <div class="col-sm-1">
                                                <label class="control-label">Avaria</label>
                                            </div> 
                                            <div class="col-sm-1">
                                                <label class="control-label">Reposição</label>
                                            </div>
                                            <div class="col-sm-1">
                                                <label class="control-label">Saldo</label>
                                            </div>
                                        </div>
                                    
                                        <div class="row">
                                             
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" name="txtProduto" id="txtProduto" value="${produtoEntrega.produto}">
                                            </div>
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" name="txtQuantidade" id="txtQuantidade" value="${produtoEntrega.dblQuantidade}">
                                            </div>
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" name="txtQtBonus" id="txtQtBonus" value="${produtoEntrega.dblQuantidadeBonus}">
                                            </div> 
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" name="txtQtCortesia" id="txtQtCortesia" value="${produtoEntrega.dblQuantidadeCortesia}">
                                            </div>  
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" name="txtQtAvaria" id="txtQtAvaria" value="${produtoEntrega.dblQuantidadeAvaria}">
                                            </div> 
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" name="txtQtReposicao" id="txtQtReposicao" value="${produtoEntrega.dblQuantidadeReposicao}">
                                            </div> 
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" name="txtQtSaldo" id="txtQtSaldo" value="${produtoEntrega.dblSaldo}">
                                            </div>
                                        </div>
                                            
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <table class="table table-hover table-striped" overflow="scroll">
                                                    <tbody>
                                                        <tr>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                        </tr> 
                                                        <c:forEach var="lstProduEntr" items="${lstProdutoEntrega}" varStatus="s">
                                                            <tr>
                                                                <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionarProduto('FabricaGelo.Entrega.AcaoSelecionaProduto?idProdutoEntrega=${lstProduEntr.idProdutoEntrega}')">  
                                                                     <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="excluirProduto('FabricaGelo.Entrega.AcaoExcluirProduto?idProdutoEntrega=${lstProduEntr.idProdutoEntrega}')">  </td>
                                                                <td> ${lstProduEntr.produto} </td>
                                                                <td> ${lstProduEntr.dblQuantidade} </td>
                                                                <td> ${lstProduEntr.dblQuantidadeBonus} </td>
                                                                <td> ${lstProduEntr.dblQuantidadeCortesia} </td>
                                                                <td> ${lstProduEntr.dblQuantidadeAvaria} </td>
                                                                <td> ${lstProduEntr.dblQuantidadeReposicao} </td>
                                                                <td> ${lstProduEntr.dblSaldo} </td>
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
                                                <div class="panel-title text-center"><h5>Avarias</h5></div>
                                            </div>
                                        </div>
                                        
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <div class="input-group">
                                                    <input type="text" class="form-control" id="txtAvaria" placeholder="Avaria" value="${avariaEntrega.avaria}">
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn btn-default" onClick="pesquisaAvaria()">...</button>
                                                    </div>                                            
                                                </div> 
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtQtAvariaPE" id="txtQtAvariaPE" placeholder="quantidade" value="${avariaEntrega.quantidade}">    
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
                                                                <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionarAvaria('FabricaGelo.Entrega.AcaoSelecionaAvaria?idAvariaEntrega=${lstAvariaEntr.idAvariaEntrega}')">  
                                                                     <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="excluirAvaria('FabricaGelo.Entrega.AcaoExcluirAvaria?idAvariaEntrega=${lstAvariaEntr.idAvariaEntrega}')">  </td>
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
                                            
                                            
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading"><h3>Roteiro de visitas</h3><h6>* Editando um cliente, você definirá movimentos gerados pela visita.</h6></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <label class="control-label">Cliente</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="input-group">
                                                    <input type="text" class="form-control" id="txtCliente"  value="${colaboradorEntrega.cliente}">
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn btn-default" onClick="pesquisaCliente()">...</button>
                                                    </div>                                            
                                                </div> 
                                            </div>                                              
                                        </div>
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
                                                        <c:forEach var="lstClienteVisit" items="${lstClienteVisita}" varStatus="s">
                                                            <tr>
                                                                <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionarClienteVisita('FabricaGelo.Entrega.AcaoSelecionaClienteVisita?idColaboradorEntrega=${lstClienteVisit.idColaboradorEntrega}')">  
                                                                     <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="excluirClienteVisita('FabricaGelo.Entrega.AcaoExcluirClienteVisita?idColaboradorEntrega=${lstClienteVisit.idColaboradorEntrega}')">  </td>
                                                                <td> ${lstClienteVisit.cliente.cnpj} </td>
                                                                <td> ${lstClienteVisit.cliente.razaoSocial} </td>
                                                                <td> ${lstClienteVisit.cliente.bairro} </td>
                                                                <td> ${lstClienteVisit.descMotivo} </td>
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
                                    <div class="panel-heading"><h3>Abastecimentos</h3></div>
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
                                            <div class="col-sm-8">
                                                <div class="input-group">
                                                    <input type="text" class="form-control" id="txtPosto" placeholder="Posto" value="${abastecimento.colaborador}">
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn btn-default" onClick="pesquisaPosto()">...</button>
                                                    </div> 
                                                </div>
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
            function fechar()
            {
                    document.forms[0].action="FabricaGelo.Entrega.AcaoFechaEntrega";
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
            }
            function pesquisaVeiculo()
            {
                    document.forms[0].action="FabricaGelo.Entrega.AcaoBuscarVeiculo";
                    document.forms[0].submit();                
            }
            function pesquisaProduto()
            {
                    document.forms[0].action="FabricaGelo.Entrega.AcaoBuscarProduto";
                    document.forms[0].submit();                
            } 
            function pesquisaPosto()
            {
                    document.forms[0].action="FabricaGelo.Entrega.AcaoBuscarColaborador";
                    document.forms[0].submit();                
            }     
            function pesquisaAvaria()
            {
                    document.forms[0].action="FabricaGelo.Entrega.AcaoBuscarAvaria";
                    document.forms[0].submit();  
   
            } 
            function pesquisaCliente()
            {
                    document.forms[0].action="FabricaGelo.Entrega.AcaoBuscarColaboradorPendente";
                    document.forms[0].submit();  
   
            }             
            function selecionarAvaria(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }  
            function excluirAvaria(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }  
            function selecionarAbastecimento(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }  
            function excluirAbastecimento(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }            
            function selecionarCusto(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }
            function selecionarProduto(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }
            function selecionarClienteVisita(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }            
            function cancelarCusto(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }
            function excluirProduto(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }
            function excluirClienteVisita(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }     
            function buscaCarregamento()
            {
                    document.forms[0].action="FabricaGelo.Entrega.AcaoBuscaCarregamento";
                    document.forms[0].submit();                
            }
        </script>        
    </body>
</html>
