<%-- 
    Document   : listarProduto
    Created on : 14/07/2018, 15:55:39
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <c:import url="CabRod/cabecalho.jsp"/>
    <head>
        <title>Produto</title>
    </head>
    <body>
        <form name="formProduto" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Lista de Produtos</b></h3></div>
                    <div class="panel-body">
                        
                        
                        <!-- Barra de ferramentas da tela -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn bottom-right"><button type="button" class="btn btn-default" onClick="novo()">Novo Produto</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><input type="text" class="form-control" name="txtPesquisa" id="txtPesquisa" placeholder="Descri��o de produto"></li>
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
                                        <th><div align="left">Vl Unit. (R$)</div></th>
                                    </tr> 
                                    <c:forEach var="lstProd" items="${lstProduto}" varStatus="s">
                                        <tr>
                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onclick="seleciona('FabricaGelo.Produto.AcaoSelecionaProduto?idProduto=${lstProd.idProduto}')"> </td>
                                            <td> ${lstProd.descricao} </td>
                                            <td> <fmt:formatNumber value="${lstProd.vlUnitario}" pattern="#,#00.00#"/> </td>
                                            
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
                document.forms[0].action="FabricaGelo.Produto.AcaoNovoProduto";
                document.forms[0].submit();
            }
            function pesquisar()
            {
 
                document.forms[0].action="FabricaGelo.Produto.AcaoPesquisaProduto";
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
