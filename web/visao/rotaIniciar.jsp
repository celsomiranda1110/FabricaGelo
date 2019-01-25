<%-- 
    Document   : rota
    Created on : 11/01/2019, 04:54:51
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
        <form name="formRotaIniciar" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><div class="panel-title text-center"><h3><b>Dados de rota</b></h3></div></div>
                    <div class="panel-body">
                        
                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="atualizar()">Atualizar</button></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="iniciarTransito()">Iniciar rota</button></li>
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
                                                        <select name="cmbVeiculo" class="form-control" onchange="buscarVeiculo()">
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
                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" name="txtKmInicial" id="txtKmInicial" readonly="true" placeholder="Quilometragem" value="${entrega.kmInicial}">
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
                                                                    <th></th>
                                                                    <th><div align="left">Produto</div></th>
                                                                    <th><div align="left">Quantidade</div></th>
                                                                    
                                                                </tr> 
                                                                <c:forEach var="lstProduEntr" items="${lstProdutoEntrega}" varStatus="s">
                                                                    <tr>
                                                                        <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionarProduto('FabricaGelo.Entrega.AcaoSelecionaProduto?idProdutoEntrega=${lstProduEntr.idProdutoEntrega}')"> </td>
                                                                        <td> ${lstProduEntr.produto} </td>
                                                                        <td> ${lstProduEntr.dblQuantidade} </td>
                                                                        
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
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading"><h4><b>Profissionais</b></h4></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="navbar navbar-default">
                                                <div class="container-fluid">
                                                    <div class="col-sm-10 btn">
                                                        <select name="cmbProfissional" class="form-control">
                                                            <option value="0">... Selecione um Profissional ...</option>
                                                            <c:forEach var="profis" items="${lstProfissional}" varStatus="s">
                                                              <option value="${profis.idProfissional}">${profis.nome}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm-2 btn">
                                                        <button type="button" class="btn btn-default" onClick="addProfissional()">Adicionar</button>
                                                    </div>
                                                </div>
                                            </div>
                                               
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <table class="table table-hover table-striped" overflow="scroll">
                                                    <tbody>
                                                        <c:forEach var="lstProfisEntr" items="${lstProfissionalEntrega}" varStatus="s">
                                                            <tr>
                                                                <td> <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="excluirProfissional('FabricaGelo.Entrega.AcaoExcluirProfissional?idProfissionalEntrega=${lstProfisEntr.idProfissionalEntrega}')">  </td>
                                                                <td> ${lstProfisEntr.profissional} </td>
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
                                    <div class="panel-heading"><h4><b>Roteiro de visitas</b></h4></div>
                                    <div class="panel-body">
                                        
                                        <div class="row">
                                            <div class="navbar navbar-default">
                                                <div class="container-fluid">                                            
                                                    <div class="col-sm-3">
                                                        <select name="cmbMotivo" class="form-control" onchange="buscarColaboradorPendente()">
                                                            <c:if test="${'CH' == paramPesquisa}">
                                                                <option value="">...Selecione um motivo...</option>
                                                                <option value="CH" selected="selected"> Listar Clientes com visitas para hoje </option>
                                                                <option value="CP"> Listar Clientes com pagamentos para hoje </option>
                                                                <option value="CI"> Listar Clientes inadimplentes </option>
                                                            </c:if>
                                                            <c:if test="${'CP' == paramPesquisa}">
                                                                <option value="">...Selecione um motivo...</option>
                                                                <option value="CH"> Listar Clientes com visitas para hoje </option>
                                                                <option value="CP"  selected="selected"> Listar Clientes com pagamentos para hoje </option>
                                                                <option value="CI"> Listar Clientes inadimplentes </option>
                                                            </c:if>                                                                
                                                            <c:if test="${'CI' == paramPesquisa}">
                                                                <option value="">...Selecione um motivo...</option>
                                                                <option value="CH"> Listar Clientes com visitas para hoje </option>
                                                                <option value="CP"> Listar Clientes com pagamentos para hoje </option>
                                                                <option value="CI"  selected="selected"> Listar Clientes inadimplentes </option>
                                                            </c:if> 
                                                            <c:if test="${'CI' != paramPesquisa && 'CP' != paramPesquisa && 'CH' != paramPesquisa}">
                                                                <option value=""  selected="selected">...Selecione um motivo...</option>
                                                                <option value="CH"> Listar Clientes com visitas para hoje </option>
                                                                <option value="CP"> Listar Clientes com pagamentos para hoje </option>
                                                                <option value="CI"> Listar Clientes inadimplentes </option>
                                                            </c:if>                                                                 
                                                        </select>                                                
                                                    </div>
                                                    <div class="col-sm-7">
                                                        <select name="cmbColaborador" class="form-control">
                                                            <option value="0">... Selecione uma empresa ...</option>
                                                            <c:forEach var="colab" items="${lstColaborador}" varStatus="s">
                                                              <option value="${colab.idColaborador}">${colab.razaoSocial}</option>
                                                            </c:forEach>
                                                        </select> 
                                                    </div> 
                                                    <div class="col-sm-2 btn">
                                                        <button type="button" class="btn btn-default" onClick="addColaborador()">Adicionar</button>
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
                                                        <c:forEach var="lstClienteVisit" items="${lstColaboradorEntrega}" varStatus="s">
                                                            <tr>
                                                                <td> <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="selecionaColaborador('FabricaGelo.Entrega.AcaoSelecionaColaborador?idColaboradorEntrega=${lstClienteVisit.idColaboradorEntrega}')">  </td>
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
            function iniciarTransito()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoAbreInicioRota";
                document.forms[0].submit();
            }
            function buscarVeiculo()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoBuscarVeiculo";
                document.forms[0].submit();
            }
            
            function addProfissional()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoAdicionaProfissional";
                document.forms[0].submit();
            }
            function addColaborador()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoAdicionaColaborador";
                document.forms[0].submit();
            }
            function excluirColaborador(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
            }
            function excluirProfissional(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
            }
            function buscarColaboradorPendente()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoPesquisaColaboradorPendente";
                document.forms[0].submit();
            }
            function retornar()
            {
                document.forms[0].action="FabricaGelo.Entrega.AcaoListarEntrega";
                document.forms[0].submit();
            }
        </script>                                             
    </body>
</html>
