<%-- 
    Document   : listarInsumo
    Created on : 09/09/2018, 21:25:36
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <c:import url="CabRod/cabecalho.jsp"/>
    <head>
        <title>Produto</title>
    </head>
    <body>
        <form name="formProduto" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Lista de Insumos</b></h3></div>
                    <div class="panel-body">
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn bottom-right"><button type="button" class="btn btn-default" onClick="novo()">Novo Insumo</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><input type="text" class="form-control" name="txtPesquisa" id="txtPesquisa"></li>
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
                                        <th><div align="left">Saldo</div></th>
                                    </tr> 
                                    <c:forEach var="lstInsum" items="${lstInsumo}" varStatus="s">
                                        <tr>
                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onclick="seleciona('FabricaGelo.Insumo.AcaoSelecionaInsumo?idInsumo=${lstInsum.idProduto}')"> </td>
                                            <td> ${lstInsum.descricao} </td>
                                            <td> ${lstInsum.saldo} </td>
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
                document.forms[0].action="FabricaGelo.Insumo.AcaoAbreInsumo";
                document.forms[0].submit();
            }
            function pesquisar()
            {
 
                document.forms[0].action="FabricaGelo.Insumo.AcaoPesquisaInsumo";
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
