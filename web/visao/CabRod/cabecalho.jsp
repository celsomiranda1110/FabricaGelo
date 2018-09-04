<%-- 
    Document   : cabecalho
    Created on : 09/08/2011, 16:32:52
    Author     : silva
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
	
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    
    <head>
        
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />-->

        <!-- bootstrap -->
        <link rel="stylesheet" href="visao/css/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="visao/css/bootstrap/css/bootstrap-theme.css" />
        <!--<link rel="stylesheet" href="visao/css/bootstrap/css/bootstrap.min.css" />-->
        <script src="visao/css/bootstrap/js/jquery-2.1.4.min.js"></script>
        <!--<script src="visao/css/bootstrap/js/jquery-2.1.4.js"></script>-->
        <script src="visao/css/bootstrap/js/bootstrap.js"></script>
        <!--<script src="visao/css/bootstrap/js/bootstrap.min.js"></script> -->
    
    </head>
    <body>
    
    <!-- implementação com bootstrap -->
    
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"> Fábrica de Gelo </a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
<%--
                    <c:forEach var="varMenu" items="${lstMenuGeral}" varStatus="s">
                        <c:if test="${varMenu.idSubMenu == 0}">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">${varMenu.descricao}</a>
                                <ul class="dropdown-menu">
                                    <c:forEach var="varSubMenu" items="${lstMenuGeral}" varStatus="s">
                                        <c:if test="${varSubMenu.idSubMenu == varMenu.idMenu}">
                                            <c:forEach var="varAcesso" items="${lstMenuAcesso}" varStatus="s">
                                                <c:if test="${varAcesso.idMenu == varSubMenu.idMenu}">
                                                    <c:if test="${varAcesso.ativo == 1}">-
                                                        <li> <a href=${varSubMenu.caminhoWeb}>${varSubMenu.descricao}</a> </li>
                                                    </c:if>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:if>
                    </c:forEach>
--%>
                    <c:forEach var="varMenu" items="${lstMenuGeral}" varStatus="s">
                        <c:if test="${varMenu.idSubMenu == 0}">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">${varMenu.descricao}</a>
                                <ul class="dropdown-menu">
                                    <c:forEach var="varSubMenu" items="${lstMenuGeral}" varStatus="s">
                                        <c:if test="${varSubMenu.idSubMenu == varMenu.idMenu}">
                                            
                                            <li> <a href=${varSubMenu.caminhoWeb}>${varSubMenu.descricao}</a> </li>
                                            
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:if>
                    </c:forEach>                    
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li> <a href="#"><span class="glyphicon glyphicon-user"></span>${usuario.nome}</a> </li>
                </ul>
            </div>
        </div>
    </nav>
    
    <!-- fim da implementação com bootstrap -->
    
</body>
</html>

