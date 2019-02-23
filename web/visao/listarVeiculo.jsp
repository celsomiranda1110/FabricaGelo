<%-- 
    Document   : listarVeiculo
    Created on : 22/07/2018, 00:32:24
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <c:import url="CabRod/cabecalho.jsp"/>
    <head>
        <title>Veiculo</title>
    </head>
    <body>
        <form name="formVeiculo" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Lista de Veiculos</b></h3></div>
                    <div class="panel-body">
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn bottom-right"><button type="button" class="btn btn-default" onClick="novo()">Novo Veículo</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><input type="text" class="form-control" name="txtPesquisa" id="txtPesquisa" placeholder="Descrição do veículo"></li>
                                                    <li class="btn">
                                                        <select name="cmbSituacaoPesquisa" class="form-control" >
                                                            <option value=""> Situação </option>
                                                            <option value="A">Ativo</option>
                                                            <option value="I">Inativo</option>
                                                        </select> 
                                                    </li>
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
                                        <th><div align="left">Descrição</div></th>
                                        <th><div align="left">Placa</div></th>
                                        <th><div align="left">Marca</div></th>
                                        <th><div align="left">Modelo</div></th>
                                        <th><div align="left">Ano</div></th>
                                        <th><div align="left">Quilometragem</div></th>
                                        <th><div align="left">Capacidade (Kg)</div></th>
                                    </tr> 
                                    <c:forEach var="lstVeic" items="${lstVeiculo}" varStatus="s">
                                        <tr>
                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onclick="seleciona('FabricaGelo.Veiculo.AcaoSelecionaVeiculo?idEquipamento=${lstVeic.idEquipamento}')"> </td>
                                            <td> ${lstVeic.descricao} </td>
                                            <td> ${lstVeic.placa} </td>
                                            <td> ${lstVeic.marca} </td>
                                            <td> ${lstVeic.modelo} </td>
                                            <td> ${lstVeic.ano} </td>
                                            <td> ${lstVeic.quilometragem} </td>
                                            <td> <fmt:formatNumber value="${lstVeic.capacidade}" pattern="#,#00.00#"/> </td>
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
 
                document.forms[0].action="FabricaGelo.Veiculo.AcaoNovoVeiculo";
                document.forms[0].submit();
            }
            function pesquisar()
            {
 
                document.forms[0].action="FabricaGelo.Veiculo.AcaoPesquisaVeiculo";
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
