<%-- 
    Document   : producao
    Created on : 28/08/2018, 05:48:53
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="atualizar()">Atualizar</button></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="fechar()">Fechar</button></li>
                                                    <li class="btn"><label class="control-label">${producao.descSituacao}</label></li>
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="listarProducao()">Lista produções</button></li>
                                                    
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
                                        <input type="date" class="form-control" name="txtData" id="txtData" value="${producao.data}">
                                    </div>
                                    <div class="col-sm-3">
                                        <select name="cmbTurno" class="form-control">

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
                                        <select name="cmbProduto" class="form-control">
                                            <option value="0">... Selecione um Produto ...</option>
                                            <c:forEach var="prod" items="${lstProduto}" varStatus="s">
                                              <c:if test="${prod.idProduto == producao.idProduto}">
                                                <option value="${prod.idProduto}" selected="selected">${prod.descricao}</option>
                                              </c:if>
                                              <c:if test="${prod.idProduto != producao.idProduto}">
                                                <option value="${prod.idProduto}">${prod.descricao}</option>
                                              </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="txtQuantidade" id="txtQuantidade" value="${producao.quantidade}">
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
                                            <div class="col-sm-4">
                                                <label class="control-label">Máquina</label>
                                            </div>
                                            <div class="col-sm-4">
                                                <label class="control-label">Embalagem</label>
                                            </div>
                                            <div class="col-sm-1">
                                                <label class="control-label">Hr Inicial</label>
                                            </div> 
                                            <div class="col-sm-1">
                                                <label class="control-label">Hr Final</label>
                                            </div>
                                            <div class="col-sm-1">
                                                <label class="control-label">Sl ant.</label>
                                            </div>
                                            <div class="col-sm-1">
                                                <label class="control-label">Prod.</label>
                                            </div>

                                        </div>  
                                        <div class="row">
                                            <div class="col-sm-4">
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
                                            <div class="col-sm-4">
                                                <select name="cmbEmbalagem" class="form-control">
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
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" name="txtHrInicial" id="txtHrInicial" value="${maquinaProducao.hrInicial}">
                                            </div> 
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" name="txtHrFinal" id="txtHrFinal" value="${maquinaProducao.hrFinal}">
                                            </div>
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" name="txtSlAnterior" id="txtSlAnterior" value="${maquinaProducao.qtSaldoAnterior}">
                                            </div>
                                            <div class="col-sm-1">
                                                <input type="text" class="form-control" name="txtProducao" id="txtProducao" value="${maquinaProducao.qtProducao}">
                                            </div>
                                        </div> 
                                        <div class="row">
                                            <div class="col-sm-12">

                                                <table class="table table-hover table-striped">
                                                    <tbody>
                                                        <tr>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>                                                           
                                                            <th><div align="left"></div></th>
                                                        </tr>
                                                        <c:forEach var="lstMaquinaProd" items="${lstMaquinaProducao}" varStatus="s">
                                                        <tr>
                                                            <c:set var="tQuantidade" value="${tQuantidade + lstMaquinaProd.qtProducao}" />
                                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionaMaquinaProducao('FabricaGelo.Producao.AcaoSelecionaMaquinaProducao?idMaquinaProducao=${lstMaquinaProd.idMaquinaProducao}')">  </td>
                                                            <td> ${lstMaquinaProd.embalagem} </td>
                                                            <td> ${lstMaquinaProd.maquina} </td>
                                                            <td> ${lstMaquinaProd.hrInicial} </td>
                                                            <td> ${lstMaquinaProd.hrFinal} </td>
                                                            <td> ${lstMaquinaProd.qtSaldoAnterior} </td>
                                                            <td> ${lstMaquinaProd.qtProducao} </td>

                                                        </tr>
                                                        </c:forEach>
                                                        <tr>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left">${tQuantidade} </div></th>
                                                        </tr>
                                                    </tbody>
                                                </table>                                         

                                            </div>
                                        </div>
                                                        
                                        <div class="panel panel-default">
                                            <div class="panel-heading">Avarias</div>
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-sm-10">
                                                        <label class="control-label">Motivo da avaria</label>
                                                    </div> 
                                                    <div class="col-sm-2">
                                                        <label class="control-label">Quantidade</label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-10">
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
                                                                  <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="SelecionaAvariaProducao('FabricaGelo.Producao.AcaoSelecionaAvariaProducao?idAvariaProducao=${lstAvariaProd.idAvariaProducao}')">  </td>
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
                                    </div>
                                </div>
                                    
                            </div>
                        </div>
                                            
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">Estocagem</div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <label class="control-label">Câmara</label>
                                            </div>
                                            <div class="col-sm-2">
                                                <label class="control-label">Qt</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-10">
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
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <table class="table table-hover table-striped">
                                                    <tbody>
                                                        <tr>
                                                            <th><div align="left"></div></th>
                                                            <th><div align="left">Câmara</div></th>
                                                            <th><div align="left">Qt</div></th>

                                                        </tr>
                                                        <c:forEach var="transfer" items="${lstTransferencia}" varStatus="s">
                                                        <tr>
                                                          <td> 
                                                              <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="SelecionaTransferencia('FabricaGelo.Producao.AcaoSelecionaTransferencia?idTransferenciaProducao=${transfer.idTransferenciaProducao}')"> 
                                                              <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="DeletaTransferencia('FabricaGelo.Producao.AcaoDeletaTransferencia?idTransferenciaProducao=${transfer.idTransferenciaProducao}')">  
                                                          </td>
                                                          <td> ${transfer.produtoCamara.equipamento} </td>
                                                          <td> ${transfer.quantidade} </td>
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
                
                document.forms[0].action="FabricaGelo.Producao.AcaoGravaProducao";
                document.forms[0].submit();
            }
            function fechar()
            {
                document.forms[0].action="FabricaGelo.Producao.AcaoFechaProducao";
                document.forms[0].submit();
            }            
            function pesquisaEmbalagem()
            {
                document.forms[0].action="FabricaGelo.Producao.AcaoBuscarEmbalagem";
                document.forms[0].submit();
            }
            function pesquisaProduto()
            {
                document.forms[0].action="FabricaGelo.Producao.AcaoBuscarProduto";
                document.forms[0].submit();
            }
            function pesquisaMaquina()
            {
                document.forms[0].action="FabricaGelo.Producao.AcaoBuscarMaquina";
                document.forms[0].submit();
            }
            function pesquisaAvaria()
            {
                document.forms[0].action="FabricaGelo.Producao.AcaoBuscarAvaria";
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
        </script> 
    </body>
</html>
