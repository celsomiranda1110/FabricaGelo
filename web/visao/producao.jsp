<%-- 
    Document   : producao
    Created on : 28/08/2018, 05:48:53
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Informações sobre Produção</title>
    </head>
    <body>
        <form name="formProducao" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Dados de Produção</b></h3></div>
                    <div class="panel-body">
                       
                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="fechar()">Finalizar produção</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="listarProducao()">Retornar</button></li>
                                                    
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
                                    <div class="col-sm-2">
                                        <label class="control-label">Data</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">Turno</label>
                                    </div>
                                    <div class="col-sm-5">
                                        <label class="control-label">Produto</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <label class="control-label">Quantidade</label>
                                    </div>
                                                                       
                                </div>
                                <div class="row">
                                   
                                    <div class="col-sm-2">
                                        <input type="date" class="form-control" name="txtData" id="txtData" readonly="true" value="${producao.data}">
                                    </div>
                                    <div class="col-sm-3">
                                        <select name="cmbTurno" class="form-control" readonly="true">

                                            <c:if test="${'MA' == producao.turno}">
                                                <option value="">  </option>
                                                <option value="MA" selected="selected"> MATUTINO </option>
                                                <option value="VE"> VESPERTINO </option>
                                                <option value="NO"> NOTURNO </option>
                                            </c:if>
                                            <c:if test="${'VE' == producao.turno}">
                                                <option value="">  </option>
                                                <option value="MA"> MATUTINO </option>
                                                <option value="VE" selected="selected"> VESPERTINO </option>
                                                <option value="NO"> NOTURNO </option>
                                            </c:if>
                                            <c:if test="${'NO' == producao.turno}">
                                                <option value="">  </option>
                                                <option value="MA"> MATUTINO </option>
                                                <option value="VE"> VESPERTINO </option>
                                                <option value="NO" selected="selected"> NOTURNO </option>
                                            </c:if>
                                            <c:if test="${'MA' != producao.turno && 'VE' != producao.turno && 'NO' != producao.turno}">
                                                <option value=""  selected="selected">  </option>
                                                <option value="MA"> MATUTINO </option>
                                                <option value="VE"> VESPERTINO </option>
                                                <option value="NO"> NOTURNO </option>
                                            </c:if>

                                        </select>
                                    </div>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" readonly="true" name="txtProduto" id="txtProduto" value="${producao.produto}">
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" readonly="true" name="txtQuantidade" id="txtQuantidade" value="${producao.quantidade}">
                                    </div>
                                    
                                </div>
                            </div>
                            

                        </div> <!-- div primeira linha -->
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">Produção por máquina</div>
                                    <div class="panel-body">
                                        
                                        <div class="row">
                                            
                                            <div class="col-sm-6">
                                                <label class="control-label">Equipamento</label>
                                            </div>
                                            <div class="col-sm-6">
                                                <label class="control-label">Embalagem</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <select name="cmbMaquina" class="form-control">
                                                    <option value="0">... Selecione uma máquina ...</option>
                                                    <c:forEach var="maq" items="${lstMaquina}" varStatus="s">
                                                      <c:if test="${maq.idEquipamento == maquinaProducao.idEquipamento}">
                                                        <option value="${maq.idEquipamento}" selected="selected">${maq.descricao}</option>
                                                      </c:if>
                                                      <c:if test="${maq.idEquipamento != maquinaProducao.idEquipamento}">
                                                        <option value="${maq.idEquipamento}">${maq.descricao}</option>
                                                      </c:if>
                                                    </c:forEach>
                                                </select> 
                                            </div>
                                            <div class="col-sm-6">
                                                <select name="cmbEmbalagem" class="form-control" onchange="buscaSaldo()">
                                                    <option value="0">... Selecione uma Embalagem ...</option>
                                                    <c:forEach var="embal" items="${lstEmbalagem}" varStatus="s">
                                                      <c:if test="${embal.idProduto == maquinaProducao.idProduto}">
                                                        <option value="${embal.idProduto}" selected="selected">${embal.descricao}</option>
                                                      </c:if>
                                                      <c:if test="${embal.idProduto != maquinaProducao.idProduto}">
                                                        <option value="${embal.idProduto}">${embal.descricao}</option>
                                                      </c:if>
                                                    </c:forEach>
                                                </select> 
                                            </div>
                                        </div>                                        
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <label class="control-label">Sd anterior</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Reposição</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Produção</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Hr inicial</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Hr final</label>
                                            </div>
                                            
                                            <div class="col-sm-2">
                                                <label class="control-label"></label>
                                            </div>
                                        </div>
                                        
                                        
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtSlAnterior" id="txtSlAnterior" value="${maquinaProducao.qtSaldoAnterior}" readonly="true">
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtReposicao" id="txtReposicao" value="${maquinaProducao.qtReposicao}" onchange="calculaProducao()">
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control" name="txtProducao" id="txtProducao" value="${maquinaProducao.qtProducao}" readonly="true">
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="time" class="form-control" name="txtHrInicial" id="txtHrInicial" value="${maquinaProducao.hrInicial}"> 
                                            </div> 
                                            <div class="col-sm-2">
                                                <input type="time" class="form-control" name="txtHrFinal" id="txtHrFinal" value="${maquinaProducao.hrFinal}">
                                            </div>
                                            
                                            <div class="col-sm-2">
                                                <button type="button" class="btn btn-default" onClick="addMaquinaProducao()">Adicionar</button>
                                            </div>
                                        </div> 
                                        <div class="row">
                                            <div class="col-sm-12">

                                                <table class="table table-hover table-striped">
                                                    <tbody>
                                                        <tr>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left">Máquina</div></th>
                                                            <th><div align="left">Embalagem</div></th>
                                                            <th><div align="left">Sd Anterior</div></th>
                                                            <th><div align="left">Reposição</div></th>
                                                            <th><div align="left">Produção</div></th>
                                                            <th><div align="left">Avarias</div></th>
                                                            <th><div align="left">Saldo</div></th>
                                                            <th><div align="left">Hr Inicial</div></th>                                                           
                                                            <th><div align="left">Hr Final</div></th>
                                                        </tr>
                                                        <c:forEach var="lstMaquinaProd" items="${lstMaquinaProducao}" varStatus="s">
                                                        <tr>
                                                            <c:set var="tQuantidade" value="${tQuantidade + lstMaquinaProd.qtProducao}" />
                                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionaMaquinaProducao('FabricaGelo.Producao.AcaoSelecionaMaquinaProducao?idMaquinaProducao=${lstMaquinaProd.idMaquinaProducao}')">  </td>
                                                            <td> ${lstMaquinaProd.maquina} </td>
                                                            <td> ${lstMaquinaProd.embalagem} </td>
                                                            <td> ${lstMaquinaProd.qtSaldoAnterior} </td>
                                                            <td> ${lstMaquinaProd.qtReposicao} </td>
                                                            <td> ${lstMaquinaProd.qtProducao} </td>
                                                            <td> ${lstMaquinaProd.qtAvaria} </td>
                                                            <td> ${lstMaquinaProd.qtSaldo} </td>
                                                            <td> ${lstMaquinaProd.hrInicial} </td>
                                                            <td> ${lstMaquinaProd.hrFinal} </td>
                                                        </tr>
                                                        </c:forEach>
                                                        <tr>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left">${tQuantidade} </div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                        </tr>
                                                    </tbody>
                                                </table>                                         

                                            </div>
                                        </div>
                                                        
                                        <div class="panel panel-default">
                                            <div class="panel-heading">Avarias</div>
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-sm-8">
                                                        <label class="control-label">Motivo da avaria</label>
                                                    </div> 
                                                    <div class="col-sm-2">
                                                        <label class="control-label">Quantidade</label>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <label class="control-label"></label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-8">
                                                        <select name="cmbAvaria" class="form-control">
                                                            <option value="0">... Selecione uma avaria ...</option>
                                                            <c:forEach var="avar" items="${lstAvaria}" varStatus="s">
                                                              <c:if test="${avar.idAvaria == avariaProducao.idAvaria}">
                                                                <option value="${avar.idAvaria}" selected="selected">${avar.descricao}</option>
                                                              </c:if>
                                                              <c:if test="${avar.idAvaria != avariaProducao.idAvaria}">
                                                                <option value="${avar.idAvaria}">${avar.descricao}</option>
                                                              </c:if>
                                                            </c:forEach>
                                                        </select>                                                     
                                                    </div> 
                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" name="txtQuantidadeAvaria" id="txtQuantidadeAvaria" value="${avariaProducao.quantidade}">
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <button type="button" class="btn btn-default" onClick="addAvaria()">Adicionar</button>
                                                    </div>                                                    
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <table class="table table-hover table-striped">
                                                            <tbody>
                                                                <tr>
                                                                    <th><div align="left"></div></th>
                                                                    <th><div align="left">Avaria</div></th>
                                                                    <th><div align="left">Quantidade</div></th>

                                                                </tr>
                                                                <c:forEach var="lstAvariaProd" items="${lstAvariaProducao}" varStatus="s">
                                                                <tr>
                                                                    <c:set var="tQuantidadeAvaria" value="${tQuantidadeAvaria + lstAvariaProd.quantidade}" />
                                                                  <td> 
                                                                      <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="SelecionaAvariaProducao('FabricaGelo.Producao.AcaoSelecionaAvariaProducao?idAvariaProducao=${lstAvariaProd.idAvariaProducao}')">  
                                                                      <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="DeletaAvariaProducao('FabricaGelo.Producao.AcaoDeletaAvariaProducao?idAvariaProducao=${lstAvariaProd.idAvariaProducao}')">  
                                                                  </td>
                                                                  <td> ${lstAvariaProd.avaria} </td>
                                                                  <td> ${lstAvariaProd.quantidade} </td>
                                                                </tr>
                                                                </c:forEach>
                                                                <tr>
                                                                    <th><div align="left"></div></th>
                                                                    <th><div align="left"></div></th>
                                                                    <th><div align="left">${tQuantidadeAvaria}</div></th>

                                                                </tr>
                                                            </tbody>
                                                        </table>                                                 
                                                    </div>
                                                </div>
                                            </div>
                                        </div> 

                                        <div class="panel panel-default">
                                            <div class="panel-heading">Estocagem</div>
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-sm-8">
                                                        <label class="control-label">Câmara</label>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <label class="control-label">Quantidade</label>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <label class="control-label"></label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-8">
                                                        <select name="cmbProdutoCamara" class="form-control">
                                                            <option value="0">... Selecione uma Câmara ...</option>
                                                            <c:forEach var="produtoCamar" items="${lstProdutoCamara}" varStatus="s">
                                                                <c:if test="${produtoCamar.idProdutoCamara == transf.idProdutoCamara}">
                                                                    <option value="${produtoCamar.idProdutoCamara}" selected="selected">${produtoCamar.equipamento}</option>    
                                                                </c:if>
                                                                <c:if test="${produtoCamar.idProdutoCamara != transf.idProdutoCamara}">
                                                                    <option value="${produtoCamar.idProdutoCamara}">${produtoCamar.equipamento}</option>    
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>                                                
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control" name="txtQuantidadeTransferencia" id="txtQuantidadeTransferencia" value="${transf.quantidade}">
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <button type="button" class="btn btn-default" onClick="addTransferencia()">Adicionar</button>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <table class="table table-hover table-striped">
                                                            <tbody>
                                                                <tr>
                                                                    <th><div align="left"></div></th>
                                                                    <th><div align="left">Câmara</div></th>
                                                                    <th><div align="left">Quantidade</div></th>

                                                                </tr>
                                                                <c:forEach var="transfer" items="${lstTransferencia}" varStatus="s">
                                                                <tr>
                                                                    <c:set var="tQuantidadeTransf" value="${tQuantidadeTransf + transfer.quantidade}" />
                                                                  <td> 
                                                                      <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="SelecionaTransferencia('FabricaGelo.Producao.AcaoSelecionaTransferencia?idTransferenciaProducao=${transfer.idTransferenciaProducao}')"> 
                                                                      <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="DeletaTransferencia('FabricaGelo.Producao.AcaoDeletaTransferencia?idTransferenciaProducao=${transfer.idTransferenciaProducao}')">  
                                                                  </td>
                                                                  <td> ${transfer.produtoCamara.equipamento} </td>
                                                                  <td> ${transfer.quantidade} </td>
                                                                </tr>
                                                                </c:forEach>
                                                                <tr>
                                                                    <th><div align="left"></div></th>
                                                                    <th><div align="left"></div></th>
                                                                    <th><div align="left">${tQuantidadeTransf}</div></th>

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
                
                </div>
            </div> 
        </form>
        <script type="text/javascript">

            // chamada a eventos

            function atualizar()
            {
                
                document.forms[0].action="FabricaGelo.Producao.AcaoGravaProducao";
                document.forms[0].submit();
            }
            function addAvaria()
            {
                
                document.forms[0].action="FabricaGelo.Producao.AcaoAdicionaAvaria";
                document.forms[0].submit();
            }
            function addMaquinaProducao()
            {
                
                document.forms[0].action="FabricaGelo.Producao.AcaoAdicionaMaquinaProducao";
                document.forms[0].submit();
            }
            function addTransferencia()
            {
                
                document.forms[0].action="FabricaGelo.Producao.AcaoAdicionaTransferencia";
                document.forms[0].submit();
            }
            function fechar()
            {
                document.forms[0].action="FabricaGelo.Producao.AcaoFechaProducao";
                document.forms[0].submit();
            }            
            function buscaSaldo()
            {
                document.forms[0].action="FabricaGelo.Producao.AcaoBuscaSaldoAnterior";
                document.forms[0].submit();
            }
            function calculaProducao()
            {
                document.forms[0].action="FabricaGelo.Producao.AcaoCalculaProducao";
                document.forms[0].submit();
            }            
            function selecionaMaquinaProducao(caminho)
            {
 
                document.forms[0].action=caminho;
                document.forms[0].submit();
            } 
            function listarProducao()
            {
 
                document.forms[0].action="FabricaGelo.Producao.AcaoListarProducao";
                document.forms[0].submit();
            } 
            function SelecionaTransferencia(caminho)
            {
 
                document.forms[0].action=caminho;
                document.forms[0].submit();
            } 
            function DeletaTransferencia(caminho)
            {
 
                document.forms[0].action=caminho;
                document.forms[0].submit();
            }
            function DeletaAvariaProducao(caminho)
            {
 
                document.forms[0].action=caminho;
                document.forms[0].submit();
            }
        </script> 
    </body>
</html>
