<%-- 
    Document   : listarColaboradorPendencia
    Created on : 05/11/2018, 06:00:03
    Author     : celso
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
                                                    <li class="btn">
                                                        <select name="cmbSituacao" class="form-control">
                                                            <c:if test="${'CH' == paramPesquisa}">
                                                                <option value="">  </option>
                                                                <option value="CH" selected="selected"> Listar Clientes com visitas para hoje </option>
                                                                <option value="CP"> Listar Clientes com pagamentos para hoje </option>
                                                                <option value="CI"> Listar Clientes inadimplentes </option>
                                                            </c:if>
                                                            <c:if test="${'CP' == paramPesquisa}">
                                                                <option value="">  </option>
                                                                <option value="CH"> Listar Clientes com visitas para hoje </option>
                                                                <option value="CP"  selected="selected"> Listar Clientes com pagamentos para hoje </option>
                                                                <option value="CI"> Listar Clientes inadimplentes </option>
                                                            </c:if>                                                                
                                                            <c:if test="${'CI' == paramPesquisa}">
                                                                <option value="">  </option>
                                                                <option value="CH"> Listar Clientes com visitas para hoje </option>
                                                                <option value="CP"> Listar Clientes com pagamentos para hoje </option>
                                                                <option value="CI"  selected="selected"> Listar Clientes inadimplentes </option>
                                                            </c:if> 
                                                            <c:if test="${'CI' != paramPesquisa && 'CP' != paramPesquisa && 'CH' != paramPesquisa}">
                                                                <option value=""  selected="selected">  </option>
                                                                <option value="CH"> Listar Clientes com visitas para hoje </option>
                                                                <option value="CP"> Listar Clientes com pagamentos para hoje </option>
                                                                <option value="CI"> Listar Clientes inadimplentes </option>
                                                            </c:if>                                                                 
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
                                        <th><div align="left">Razão social</div></th>
                                        <th><div align="left">Bairro</div></th>
                                        
                                    </tr> 
                                    <c:forEach var="lstColabPendente" items="${lstColaboradorEntrega}" varStatus="s">
                                        <tr>
                                            <td> <input type="image" src="${lstColabPendente.imagemAtiva}" onclick="marcarVisita('FabricaGelo.Entrega.AcaoMarcaColaboradorVisita?idColaborador=${lstColabPendente.cliente.idColaborador}')"></td>
                                            <td> ${lstColabPendente.cliente.cnpj} </td>
                                            <td> ${lstColabPendente.cliente.razaoSocial} </td>
                                            <td> ${lstColabPendente.cliente.bairro} </td>
                                            
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
 
                document.forms[0].action="FabricaGelo.Colaborador.AcaoPesquisaColaboradorPendente";
                document.forms[0].submit();
            }            
            function selecionar(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
                             
            }
            function marcarVisita(caminho)
            {
                document.forms[0].action=caminho;
                document.forms[0].submit();
                             
            }            
        </script>           
    </body>
</html>

