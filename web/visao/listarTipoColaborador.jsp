<%-- 
    Document   : listarTipoColaborador
    Created on : 26/01/2019, 07:48:57
    Author     : celso


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Tipos de estabelecimentos</title>
    </head>
    <body>
        <form name="formTipoColaborador" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Tipos de estabelecimentos</b></h3></div>
                    
                    <div class="panel-body">
                        
                        <!-- Barra de ferramentas da tela -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn bottom-right"><button type="button" class="btn btn-default" onClick="adicionar()">Novo </button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><input type="text" class="form-control" name="txtPesquisa" id="txtPesquisa" placeholder="Descrição"></li>
                                                    <li class="btn">
                                                        <select name="cmbSituacaoPesquisa" class="form-control" >
                                                            <option value=""> Situação </option>
                                                            <option value="A">Ativo</option>
                                                            <option value="I">Inativo</option>
                                                        </select> 
                                                    </li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="pesquisar()">Listar </button></li>
                                                    
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
                                            <th><div align="left">Descrição</div></th>
                                            
                                        </tr>
                                        <c:forEach var="lstTipoColaborador" items="${lstTipoColaborador}" varStatus="s">
                                        <tr>
                                          <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="SelecionaTipoColaborador('FabricaGelo.TipoColaborador.AcaoSelecionaTipoColaborador?idTipoColaborador=${lstTipoColaborador.idTipoColaborador}')">  </td>
                                          <td> ${lstTipoColaborador.descricao} </td>
                                         
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
                document.forms[0].action="FabricaGelo.TipoColaborador.AcaoNovoTipoColaborador";
                document.forms[0].submit();
            }                            
            function pesquisar()
            {
                document.forms[0].action="FabricaGelo.TipoColaborador.AcaoPesquisaTipoColaborador";
                document.forms[0].submit();
            }   
   
            function SelecionaTipoColaborador(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
                               
            } 
                       
        </script>  
    </body>
</html>

