<%-- 
    Document   : listarMaquina
    Created on : 22/07/2018, 01:26:20
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <c:import url="CabRod/cabecalho.jsp"/>
    <head>
        <title>Equipamentos</title>
    </head>
    <body>
        <form name="formMaquina" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Lista de Equipamentos</b></h3></div>
                    <div class="panel-body">
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn bottom-right"><button type="button" class="btn btn-default" onClick="novo()">Novo equipamento</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><input type="text" class="form-control" name="txtPesquisa" id="txtPesquisa"></li>
                                                    <li class="btn">
                                                        <select name="cmbSituacaoPesquisa" class="form-control" >
                                                            <option value=""> Situa��o </option>
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
                                        <th><div align="left">Descri��o</div></th>
                                        <th><div align="left">Situa��o</div></th>
                                        <th><div align="left">Marca</div></th>
                                        <th><div align="left">Modelo</div></th>
                                    </tr> 
                                    <c:forEach var="lstMaq" items="${lstMaquina}" varStatus="s">
                                        <tr>
                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onclick="seleciona('FabricaGelo.Maquina.AcaoSelecionaMaquina?idEquipamento=${lstMaq.idEquipamento}')"> </td>
                                            <td> ${lstMaq.descricao} </td>
                                            <td> ${lstMaq.descSituacao} </td>
                                            <td> ${lstMaq.marca} </td>
                                            <td> ${lstMaq.modelo} </td>
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
 
                document.forms[0].action="FabricaGelo.Maquina.AcaoNovoMaquina";
                document.forms[0].submit();
            }
            function pesquisar()
            {
 
                document.forms[0].action="FabricaGelo.Maquina.AcaoPesquisaMaquina";
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

