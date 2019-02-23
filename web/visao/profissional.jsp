<%-- 
    Document   : profissional
    Created on : 22/07/2018, 06:57:42
    Author     : celso
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Informações sobre a Profissional</title>
        
    </head>
    <body>
        <form name="formProfissional" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Dados do Profissional</b></h3></div>
                    <div class="panel-body">
                        
                        
                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <ul class="nav navbar-nav">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="atualizar()">Atualizar</button></li>
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="excluir()">Excluir</button></li>
                                                </ul>
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li class="btn"><button type="button" class="btn btn-default" onClick="retornar()">Retornar</button></li>
                                                </ul>
                                            </div>
                                        </div>                                        
                                    </div>
                                </div>
                            </div>

                        </div> <!-- div dos botoes --> 
                                
                        <div class="row"> <!-- div primeira linha -->
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <label class="control-label">CPF(*)</label>
                                    </div>
                                    <div class="col-sm-9">
                                        <label class="control-label">Nome(*)</label>
                                    </div>                                    
                                    
                                </div>
                                <div class="row">
                                   
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtCpf" id="txtCpf" value="${profissional.cpf}">
                                        
                                    </div>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="txtProfissional" id="txtProfissional" value="${profissional.nome}">
                                    </div>
                                  
                                </div>
                            </div>
                            

                        </div> <!-- div primeira linha -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <label class="control-label">CTPS</label>
                                    </div>
                                    <div class="col-sm-9">
                                        <label class="control-label">Função</label>
                                    </div>
                                    
                                </div>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtCtps" id="txtCtps" value="${profissional.ctps}">
                                    </div>                                        
                                    <div class="col-sm-9">
                                        
                                        <select name="cmbFuncao" class="form-control">
                                            <option value="0">... Selecione uma função profissional ...</option>
                                            <c:forEach var="varFuncao" items="${lstFuncao}" varStatus="s">
                                              <c:if test="${varFuncao.idFuncao == profissional.idFuncao}">
                                                <option value="${varFuncao.idFuncao}" selected="selected">${varFuncao.descricao}</option>
                                              </c:if>
                                              <c:if test="${varFuncao.idFuncao != profissional.idFuncao}">
                                                <option value="${varFuncao.idFuncao}">${varFuncao.descricao}</option>
                                              </c:if>
                                            </c:forEach>
                                        </select>                                             
                                            
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row"> <!-- div terceira linha -->
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <label class="control-label">Celular</label>
                                    </div>
                                                                       
                                    
                                </div>
                                <div class="row">
                                   
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="txtCelular" id="txtCpf" value="${profissional.celular}">
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="checkbox" name="ck_Ativo" ${profissional.descAtivo}> Inativo
                                    </div> 
                                  
                                </div>
                            </div>
                            

                        </div> <!-- div terceira linha -->                                            
                        <div class="row"> <!-- div terceira linha -->
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <label class="control-label">Usuário</label>
                                    </div>
                                    <div class="col-sm-6">
                                        <label class="control-label">Senha</label>
                                    </div>                                    
                                    
                                </div>
                                <div class="row">
                                   
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="txtUsuario" id="txtUsuario" value="${profissional.usuario}">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control" name="txtSenha" id="txtSenha" value="${profissional.senha}">
                                    </div>
                                  
                                </div>
                            </div>
                            

                        </div> <!-- div terceira linha -->                        
                    </div>
                
                </div>
            </div> 
        </form>
        <script type="text/javascript">

            // chamada a eventos

            function atualizar()
            {
                var descricao = formProfissional.txtProfissional.value;
                if (descricao == "")
                {
                    alert('Necessário descrição do profissional!');
                    return false;
                }
                else
                {
                    document.forms[0].action="FabricaGelo.Profissional.AcaoGravaProfissional";
                    document.forms[0].submit();
                }
            }
            function excluir()
            {
                document.forms[0].action="FabricaGelo.Profissional.AcaoDeletaProfissional";
                document.forms[0].submit();
            }
            function retornar()
            {
                document.forms[0].action="FabricaGelo.Profissional.AcaoListarProfissional";
                document.forms[0].submit();                
            }
            function buscaFuncao()
            {
                document.forms[0].action="FabricaGelo.Profissional.AcaoBuscarFuncao";
                document.forms[0].submit();                
            }
        </script> 
    </body>
</html>
