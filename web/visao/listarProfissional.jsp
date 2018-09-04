<%-- 
    Document   : listarProfissionais
    Created on : 22/07/2018, 06:57:30
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <c:import url="CabRod/cabecalho.jsp"/>
    <head>
        <title>Profissional</title>
    </head>
    <body>
        <form name="formProfissional" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Lista de Profissionals</b></h3></div>
                    <div class="panel-body">
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-primary right" onClick="novo()">Novo Profissional</button>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="input-group">
                                            <input class="form-control" type="text" placeholder="Pesquisar" />
                                            <div class="input-group-btn">
                                                <button type="button" class="btn btn-default" onClick="pesquisa()" >Pesquisa</button>
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
                                        <th><div align="left">Nome</div></th>
                                        <th><div align="left">Cpf</div></th>
                                    </tr> 
                                    <c:forEach var="lstProd" items="${lstProfissional}" varStatus="s">
                                        <tr>
                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onclick="seleciona('FabricaGelo.Profissional.AcaoSelecionaProfissional?idProfissional=${lstProd.idProfissional}')"> </td>
                                            <td> ${lstProd.nome} </td>
                                            <td> ${lstProd.cpf} </td>
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
 
                document.forms[0].action="FabricaGelo.Profissional.AcaoAbreProfissional";
                document.forms[0].submit();
            }
            function pesquisa()
            {
 
                document.forms[0].action="FabricaGelo.Profissional.AcaoPesquisaProfissional";
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
