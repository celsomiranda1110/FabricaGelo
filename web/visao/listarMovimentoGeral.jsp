<%-- 
    Document   : listarMovimentoGeral
    Created on : 18/11/2018, 08:16:31
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Lista de Movimentos</title>
    </head>
    <body>
        <form name="formListaMovimentoGeral" method="post">
            
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Movimentos</b></h3></div>
                    
                    <div class="panel-body">
                        
                        <!-- Barra de ferramentas da tela -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn bottom-right"><button type="button" class="btn btn-default" onClick="novoMovimento()">Novo movimento</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn">
                                                        <select name="cmbTipoPesquisa" class="form-control">
                                                            
                                                            <option value="">  </option>
                                                            <option value="BO"> BONIFICAÇÃO </option>
                                                            <option value="CP"> COMPRA </option>
                                                            <option value="CO"> CORTESIA </option>
                                                            <option value="VE"> VENDA </option>
                                                            <option value="RE"> REPOSIÇÃO </option>
                                                            
                                                        </select>
                                                    </li>
                                                    <li class="btn">
                                                        <input type="date" class="form-control" name="txtPesquisa" id="txtPesquisa" value="${dtPesquisa}">
                                                    </li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="pesquisar()">Listar vendas</button></li>
                                                    
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> 

                        <div class="row">
                            <div class="col-lg-12">
                                <table class="table table-hover table-striped">
                                    <tbody>
                                        <tr>
                                            <th><div align="left">Ação</div></th>
                                            <th><div align="left">Empresa</div></th>
                                            <th><div align="left">Movimento</div></th>
                                            <th><div align="left">Número</div></th>
                                            <th><div align="left">Lançamento</div></th>
                                            <th><div align="left">Situação</div></th>
                                        </tr>
                                        <c:forEach var="lstMov" items="${lstMovimento}" varStatus="s">
                                        <tr>
                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="SelecionaMovimento('FabricaGelo.Movimento.AcaoSelecionaMovimento?idMovimento=${lstMov.idMovimento}')">  </td>
                                            <td>${lstMov.colaborador}</td>
                                            <td>${lstMov.descTipo}</td>
                                            <td>${lstMov.numero}</td>
                                            <td>${lstMov.dataLancamento}</td>
                                            <td>${lstMov.descSituacao}</td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    
                                                    
                                                    
                </div>
            </div>
            
        </form>
        <script>
            function novoMovimento()
            {
                document.forms[0].action="FabricaGelo.Movimento.AcaoNovoMovimento";
                document.forms[0].submit();
            }                            
            function pesquisar()
            {
                document.forms[0].action="FabricaGelo.Movimento.AcaoPesquisaMovimentoGeral";
                document.forms[0].submit();
            }   
   
            function SelecionaMovimento(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
                               
            } 
                       
        </script>                                                     
    </body>
</html>
