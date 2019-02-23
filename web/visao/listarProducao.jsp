<%-- 
    Document   : listarProducao
    Created on : 28/08/2018, 05:13:55
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <c:import url="CabRod/cabecalho.jsp"/>
    <head>
        <title>Lista de Produ��es</title>
    </head>
    <body>
        <form name="formProducao" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Lista de Produ��o</b></h3></div>
                    <div class="panel-body">

 
                        <!-- Barra de ferramentas da tela -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn bottom-right"><button type="button" class="btn btn-default right" onClick="novo()">Nova Produ��o</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><input type="date" class="form-control" name="txtPesquisa" id="txtPesquisa" placeholder="Data de produ��o"></li>
                                                    <li class="btn">
                                                        <select name="cmbSituacaoPesquisa" class="form-control" >
                                                            <option value=""> Situa��o </option>
                                                            <option value="PI">Produ��o iniciada</option>
                                                            <option value="PF">Produ��o finalizada</option>
                                                        </select> 
                                                    </li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="pesquisar()">Listar Produ��es</button></li>
                                                    
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
                                        <th><div align="left">Turno</div></th>
                                        <th><div align="left">Produto</div></th>
                                        <th><div align="left">Qt Produ��o</div></th>
                                        <th><div align="left">Situa��o</div></th>
                                    </tr> 
                                    <c:forEach var="lstProd" items="${lstProducao}" varStatus="s">
                                        <tr>
                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onclick="seleciona('FabricaGelo.Producao.AcaoSelecionaProducao?idProducao=${lstProd.idProducao}')"> </td>
                                            <td> ${lstProd.data} </td>
                                            <td> ${lstProd.descTurno} </td>
                                            <td> ${lstProd.produto} </td>
                                            <td> ${lstProd.quantidade} </td>
                                            <td> ${lstProd.descSituacao} </td>
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
 
                document.forms[0].action="FabricaGelo.Producao.AcaoNovaProducao";
                document.forms[0].submit();
            }
            function pesquisar()
            {
 
                document.forms[0].action="FabricaGelo.Producao.AcaoPesquisaProducao";
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
