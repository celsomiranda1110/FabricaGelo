<%-- 
    Document   : listarAluguel
    Created on : 23/08/2018, 17:08:48
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Movimento</title>
    </head>
    <body>
        <form name="formAluguel" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Aluguels</b></h3></div>
                    
                    <div class="panel-body">
                        
                        <!-- Barra de ferramentas da tela -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn bottom-right"><button type="button" class="btn btn-default" onClick="novaAluguel()">Nova aluguel</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><input type="date" class="form-control" name="txtPesquisa" id="txtPesquisa" value="${dtPesquisa}"></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="pesquisar()">Listar aluguels</button></li>
                                                    
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
                                            <th><div align="left">Ordem</div></th>
                                            <th><div align="left">Dt Aluguel</div></th>
                                            <th><div align="left">Profissional</div></th>
                                            <th><div align="left">Empresa cliente</div></th>
                                        </tr>
                                        <c:forEach var="lstVend" items="${lstAluguel}" varStatus="s">
                                        <tr>
                                          <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="SelecionaAluguel('FabricaGelo.Aluguel.AcaoSelecionaAluguel?idMovimento=${lstVend.idMovimento}')">  </td>
                                          <td> ${lstVend.numero} </td>
                                          <td> ${lstVend.dataLancamento} </td>
                                          <td> ${lstVend.profissional} </td>
                                          <td> ${lstVend.colaborador} </td>
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
            function novaAluguel()
            {
                document.forms[0].action="FabricaGelo.Aluguel.AcaoNovoAluguel";
                document.forms[0].submit();
            }                            
            function pesquisar()
            {
                document.forms[0].action="FabricaGelo.Aluguel.AcaoPesquisaAluguel";
                document.forms[0].submit();
            }   
   
            function SelecionaAluguel(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
                               
            } 
                       
        </script>  
    </body>
</html>


