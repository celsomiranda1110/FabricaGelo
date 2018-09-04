<%-- 
    Document   : listarBonificacao
    Created on : 23/08/2018, 17:09:01
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Movimento</title>
    </head>
    <body>
        <form name="formBonificacao" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Bonificacaos</b></h3></div>
                    
                    <div class="panel-body">
                        
                        <!-- Barra de ferramentas da tela -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn bottom-right"><button type="button" class="btn btn-default" onClick="novaBonificacao()">Nova bonificacao</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><input type="date" class="form-control" name="txtPesquisa" id="txtPesquisa" value="${dtPesquisa}"></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="pesquisar()">Listar compras</button></li>
                                                    
                                                </ul>
                                            </div>
                                        </div>compra
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
                                            <th><div align="left">Dt Bonificacao</div></th>
                                            <th><div align="left">Profissional</div></th>
                                            <th><div align="left">Empresa cliente</div></th>
                                        </tr>
                                        <c:forEach var="lstVend" items="${lstBonificacao}" varStatus="s">
                                        <tr>
                                          <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="SelecionaBonificacao('FabricaGelo.Bonificacao.AcaoSelecionaBonificacao?idMovimento=${lstVend.idMovimento}')">  </td>
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
            function novaBonificacao()
            {
                document.forms[0].action="FabricaGelo.Bonificacao.AcaoNovaBonificacao";
                document.forms[0].submit();
            }                            
            function pesquisar()
            {
                document.forms[0].action="FabricaGelo.Bonificacao.AcaoPesquisaBonificacao";
                document.forms[0].submit();
            }   
   
            function SelecionaBonificacao(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
                               
            } 
                       
        </script>  
    </body>
</html>


