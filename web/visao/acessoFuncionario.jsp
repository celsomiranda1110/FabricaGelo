<%-- 
    Document   : acessoFuncionario
    Created on : 24/01/2019, 00:48:27
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <c:import url="CabRod/cabecalho.jsp"/>
    <head>
        <title>Definição de acesso</title>
    </head>
    <body>
        <form name="formAcesso" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Acesso</b></h3></div>
                    <div class="panel-body">
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn bottom-right"><button type="button" class="btn btn-default" onClick="atualizar()">Atualizar</button></li>
                                                    
                                                </ul>
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> 
                        
                        <div class="row">
                            <div class="panel panel-default">
                                <div class="panel-heading"><h5>Profissionais</h5></div>
                                <div class="panel-body">    
                                    <div class="col-sm-12">
                                        <select name="cmbProfissional" class="form-control" onchange="buscarAcesso()">
                                            <option value="0">... Selecione um Profissional ...</option>
                                            <c:forEach var="varProfissional" items="${lstProfissional}" varStatus="s">
                                              <c:if test="${varProfissional.idProfissional == profissional.idProfissional}">
                                                <option value="${varProfissional.idProfissional}" selected="selected">${varProfissional.nome}</option>
                                              </c:if>
                                              <c:if test="${varProfissional.idProfissional != profissional.idProfissional}">
                                                <option value="${varProfissional.idProfissional}">${varProfissional.nome}</option>
                                              </c:if>
                                            </c:forEach>
                                        </select>                                                 
                                    </div>
                                                                                       
                                </div>
                                <div class="panel-footer">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="panel-title text-center"><h5>Menu de acesso</h5></div>
                                        </div>
                                    </div> 
                                    <div class="row">
                                        
                                        <div class="col-sm-12">
                                        
                                            <div class="navbar navbar-default">
                                                <div class="container-fluid">
                                                    <ul class="list-group">
                                                        
                                                        <c:forEach var="varMenu" items="${lstMenu}" varStatus="s">
                                                            <c:if test="${varMenu.idSubMenu == 0}">
                                                                
                                                                <li class="list-group-item active">${varMenu.descricao}</li>
                                                                
                                                                <ul class="list-group">
                                                                    <c:forEach var="varSubMenu" items="${lstMenu}" varStatus="s">
                                                                        
                                                                        <c:if test="${varSubMenu.idSubMenu == varMenu.idMenu}">
                                                                            
                                                                            <c:if test="${lstMenuProfissional != null}">
                                                                                <c:forEach var="varAcesso" items="${lstMenuProfissional}" varStatus="s">

                                                                                    <c:if test="${varAcesso.idMenu == varSubMenu.idMenu}">

                                                                                        <c:if test="${varAcesso.ativo == 'S'}">
                                                                                            <li class="list-group-item">
                                                                                                ${varSubMenu.descricao}
                                                                                               <span class="badge badge-primary badge-pill"><input type="checkbox" name="ck_${varSubMenu.idMenu}" checked></span>
                                                                                            </li>
                                                                                        </c:if>
                                                                                        <c:if test="${varAcesso.ativo == 'N'}">
                                                                                            <li class="list-group-item">
                                                                                                ${varSubMenu.descricao}
                                                                                               <span class="badge badge-primary badge-pill"><input type="checkbox" name="ck_${varSubMenu.idMenu}"></span>
                                                                                            </li>
                                                                                        </c:if>

                                                                                    </c:if>

                                                                                </c:forEach>
                                                                            </c:if>
                                                                                            
                                                                            <c:if test="${lstMenuProfissional == null}"> 
                                                                                <li class="list-group-item">
                                                                                    ${varSubMenu.descricao}
                                                                                   <span class="badge badge-primary badge-pill"><input type="checkbox" name="ck_${varSubMenu.idMenu}"></span>
                                                                                </li>
                                                                            </c:if>
                                                                           
                                                                        </c:if>
                                                                                        
                                                                    </c:forEach>
                                                                </ul>
                                                                
                                                                
                                                            </c:if>
                                                        </c:forEach>
                                                        

                                                    </ul>

                                                </div>
                                            </div>  
                                            
                                       </div> 
                                        
                                    </div>
                                </div>
                            </div>

                        </div> 
                                                    
                    </div>
                </div>
            </div>
        </form>
        <script type="text/javascript">

            // chamada a eventos

            function atualizar()
            {
                document.forms[0].action="FabricaGelo.Gerais.AcaoAtualizaAcesso";
                document.forms[0].submit();
            }
            function buscarAcesso()
            {
                document.forms[0].action="FabricaGelo.Gerais.AcaoDefinicaoAcesso";
                document.forms[0].submit();
            }

        </script>           
    </body>
</html>
