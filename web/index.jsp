<%-- 
    Document   : index
    Created on : 28/01/2016, 11:06:38
    Author     : Miranda
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <!-- bootstrap -->
        
        
        <link rel="stylesheet" href="visao/css/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="visao/css/bootstrap/css/bootstrap-theme.css" />
        <!--<link rel="stylesheet" href="visao/css/bootstrap/css/bootstrap.min.css" />-->
        <script src="visao/css/bootstrap/js/jquery-2.1.4.min.js"></script> 
        <!--<script src="visao/css/bootstrap/js/jquery-2.1.4.js"></script>-->
        <script src="visao/css/bootstrap/js/bootstrap.js"></script>
        <!--<script src="visao/css/bootstrap/js/bootstrap.min.js"></script> -->
        
        
        
        <title>Acesso</title>
        
    </head>
    <body>
        
           
        <div class="container" >
            <form name="formAcesso" method="post">
                <div class="panel panel-default panel">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Acesso ao sistema</b></h3></div>
                    <div class="row"> <!-- div segunda linha -->

<%-- 
                        <div class="col-lg-4">
                            <div class="row">
                                <div class="col-sm-12">
                                    <label class="col-sm-4 control-label">Usuario</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="txtLogin">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <label class="col-sm-4 control-label">Senha</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <input type="password" class="form-control" name="txtSenha">
                                </div>
                            </div>
                            <div class="row" >
                                <div class="col-sm-12 text-center">
                                    <button type="button" class="btn btn-primary" onClick="validaLogin()">Acessar</button>

                                </div>
                            </div>                            
                            
                        </div>
--%>
                        <div class="col-lg-4">
                            <div class="jumbotron">
                                <div class="container-fluid">
                                    <ul class="panel-body">
                                        <li class="btn"><label class="control-label">Usuario</label></li>
                                        <li class="btn"><input type="text" class="form-control" name="txtLogin"></li>
                                        <li class="btn"><label class="control-label">Senha</label></li>
                                        <li class="btn"><input type="password" class="form-control" name="txtSenha"></li>
                                    </ul>
                                    <ul class="panel-footer text-center">
                                        <button type="button" class="btn btn-primary" onClick="validaLogin()">Acessar</button>    
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-8">
                            <div class="container-fluid">
                                <ul class="panel-body text-center">
                                    <li class="btn">
                                        <input type="image" width="340" height="300" src="visao/utils/logoIce.jpg">
                                    </li>
                                </ul>
                            </div>
                        </div>
<%--                         
                        <div class="col-lg-8">
                            <div class="row">
                                <div class="col-sm-12 text-center">
                                    <input type="image" width="320" height="250" src="visao/utils/logoIce.jpg">
                                </div>
                            </div>
                            

                        </div>
--%>                        
                    </div> 

                </div>
            </form>

        </div>
            
   
       
        <script type="text/javascript">

            // chamada a eventos

            function validaLogin()
            {

                document.forms[0].action="FabricaGelo.Gerais.AcaoLogin";
                document.forms[0].submit();


            }

        </script>        
    </body>
</html>
