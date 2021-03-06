<%-- 
    Document   : listarEmprresa
    Created on : 11/04/2017, 02:12:40
    Author     : Miranda


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <c:import url="CabRod/cabecalho.jsp"/>
    <head>
        <title>Empresas</title>
    </head>
    <body>
        <form name="formEmpresas" method="post">
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Lista de Empresas</b></h3></div>
                    <div class="panel-body">
                        
                       
                        <!-- Barra de ferramentas da tela -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn bottom-right"><button type="button" class="btn btn-default" onClick="novo()">Nova Empresa</button></li>
                                                    
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><input type="text" class="form-control" name="txtPesquisa" placeholder="Raz�o social" id="txtPesquisa"></li>
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
                                        <th><div align="left">CNPJ</div></th>
                                        <th><div align="left">Raz�o social</div></th>
                                        <th><div align="left">Tipo empreendimento</div></th>
                                    </tr> 
                                    <c:forEach var="lstColab" items="${lstColaborador}" varStatus="s">
                                        <tr>
                                            <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionar('FabricaGelo.Colaborador.AcaoSelecionaColaborador?idColaborador=${lstColab.idColaborador}')">  </td>
                                            <td> ${lstColab.cnpj} </td>
                                            <td> ${lstColab.razaoSocial} </td>
                                            <td> ${lstColab.tipoColaborador} </td>
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
 
                document.forms[0].action="FabricaGelo.Colaborador.AcaoNovoColaborador";
                document.forms[0].submit();
            }
            function pesquisar()
            {
 
                document.forms[0].action="FabricaGelo.Colaborador.AcaoPesquisaColaborador";
                document.forms[0].submit();
            }            
            function selecionar(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
                             
            } 
        </script>           
    </body>
</html>
