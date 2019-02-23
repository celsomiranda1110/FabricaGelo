<%-- 
    Document   : listarSaidaCamara
    Created on : 30/12/2018, 11:58:02
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Carregamentos em veículos</title>
    </head>
    <body>
        <form name="formBairro" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Carregamento</b></h3></div>
                    
                    <div class="panel-body">
                        
                        <!-- Barra de ferramentas da tela -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn bottom-right"><button type="button" class="btn btn-default" onClick="adicionar()">Novo Carregamento</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><input type="date" class="form-control" name="txtPesquisaIn" id="txtPesquisa"></li>
                                                    <li class="btn"><input type="date" class="form-control" name="txtPesquisaFi" id="txtPesquisa"></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="pesquisar()">Listar Carregamentos</button></li>
                                                    
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        
                        <!-- Area de tabela -->
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table table-hover table-striped">
                                    <tbody>
                                        <tr>
                                            <th><div align="left">Ação</div></th>
                                            <th><div align="left">Data</div></th>
                                            <th><div align="left">Carga</div></th>
                                            <th><div align="left">Quantidade</div></th>
                                            <th><div align="left">Situação</div></th>
                                        </tr>
                                        <c:forEach var="lstCarr" items="${lstSaidaCamara}" varStatus="s">
                                        <tr>
                                          <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="SelecionaSaidaCamara('FabricaGelo.Carregamento.AcaoSelecionaCarregamento?idSaidaCamara=${lstCarr.idSaidaCamara}')">  </td>
                                          <td> ${lstCarr.dataFormatada} </td>
                                          <td> ${lstCarr.produtoCamara} </td>
                                          <td> ${lstCarr.saida} </td>
                                          <td> ${lstCarr.descSituacao} </td>
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
            function adicionar()
            {
                document.forms[0].action="FabricaGelo.Carregamento.AcaoNovoCarregamento";
                document.forms[0].submit();
            }                            
            function pesquisar()
            {
                document.forms[0].action="FabricaGelo.Carregamento.AcaoPesquisaCarregamento";
                document.forms[0].submit();
            }   
   
            function SelecionaSaidaCamara(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
                               
            } 
                       
        </script>  
    </body>
</html>
