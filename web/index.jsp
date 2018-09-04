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
        <form name="formAcesso" method="post">
            
            <div class="container">
                
                <div class="panel panel-default panel">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Acesso ao sistema</b></h3></div>
                    <div class="row"> <!-- div segunda linha -->

                        
                        <div class="col-lg-6">
                            <div class="row">
                                <div class="col-lg-12">
                                    <label class="col-sm-4 control-label">Usuario</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" name="txtLogin">
                                    </div>
                                </div>
                            </div>
                            
                        </div>

                        <div class="col-lg-6">
                            <div class="row">
                                <div class="col-lg-12">
                                    <label class="col-sm-4 control-label">Senha</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="col-sm-12">
                                        <input type="password" class="form-control" name="txtSenha">
                                    </div>
                                </div>
                            </div>
                            
                        </div>

                        
                    </div>
                    
                    <div class="row"> <!-- div dos botoes -->
                        
                        <div class="col-lg-12 text-right">
                            <div class="col-sm-12">
                                <button type="button" class="btn btn-primary" onClick="validaLogin()">Acessar</button>
                            </div>
                        </div>
                        
                    </div> <!-- div dos botoes -->                    
                    
                </div>
                
            </div>
            
        </form>
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
