<%-- 
    Document   : listarEntregas
    Created on : 23/09/2018, 20:41:46
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <c:import url="CabRod/cabecalho.jsp"/>
    <head>
        <title>Rotas das entregas</title>
    </head>
    <body>
        <form name="formEntrega" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Rotas diárias das entregas</b></h3></div>
                    <div class="panel-body">
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn bottom-right"><button type="button" class="btn btn-default" onClick="novo()">Nova Rota</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    
                                                    <li class="btn">
                                                        <select name="cmbSituacaoPesquisa" class="form-control">

                                                            <option value="">  </option>
                                                            <option value="AB"> ABERTA </option>
                                                            <option value="FE"> FECHADA </option>
                                                            <option value="PE"> PENDENTE </option>

                                                        </select>
                                                    </li>                                                       
                                                    <li class="btn"><input type="date" class="form-control" name="txtPesquisa" id="txtPesquisa"></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="pesquisar()">Pesquisa</button></li>
                                                    
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
            
                        <div class="row">
                            <table class="table table-hover table-striped" overflow="scroll">
                                <tbody>
                                    <tr>
                                        <th><div align="left"></div></th>
                                        <th><div align="left">Data</div></th>
                                        <th><div align="left">Veículo</div></th>
                                        <th><div align="left">Situação</div></th>
                                        
                                    </tr> 
                                    <c:forEach var="lstEntreg" items="${lstEntrega}" varStatus="s">
                                        <tr>
                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onclick="seleciona('FabricaGelo.Entrega.AcaoSelecionaEntrega?idEntrega=${lstEntreg.idEntrega}')"> </td>
                                            <td> ${lstEntreg.dataApresentacao} </td>
                                            <td> ${lstEntreg.veiculo} </td>
                                            <td> ${lstEntreg.descSituacao} </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>            
                        
                        
                        
                    </div>
                </div>
            </div>
        </form>
        <script type="text/javascript">

            // chamada a eventos

            function novo()
            {
 
                document.forms[0].action="FabricaGelo.Entrega.AcaoNovaRota";
                document.forms[0].submit();
            }
            function pesquisar()
            {
 
                document.forms[0].action="FabricaGelo.Entrega.AcaoPesquisaEntrega";
                document.forms[0].submit();
            } 
            function seleciona(caminho)
            {
 
                document.forms[0].action=caminho;
                document.forms[0].submit();
            } 

        </script>          
    </body>
</html>
