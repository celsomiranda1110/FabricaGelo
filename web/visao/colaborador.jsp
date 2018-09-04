<%-- 
    Document   : colaborador
    Created on : 28/01/2016, 12:34:10
    Author     : Miranda
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    
    <head>
        <c:import url="CabRod/cabecalho.jsp"/>
        <title>Informações sobre a Empresa</title>
        
    </head>
    <body>
        <form name="formColaborador" method="post">
            
            <div class="container">
                
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title text-center"><b>Dados da Empresa</b></h3></div>
                    <div class="panel-body">
                        
                        <div class="row">
                            <div class="col-lg-12">
                                
                                <span class="label label-danger">${avisoColaborador}</span>
                                
                            </div>
                        </div>
                   
                                
                        <div class="row"> <!-- div dos botoes -->

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <button type="button" class="btn btn-primary" onClick="atualizar()">Atualizar</button>
                                        <button type="button" class="btn btn-default" onClick="selecionar()">Selecionar</button>
                                    </div>
                                </div>
                            </div>

                        </div> <!-- div dos botoes -->
                                
                        <div class="row"> <!-- div primeira linha -->
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <label class="control-label">CNPJ</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">Inscrição Estadual</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">Inscrição Municipal</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">MEI</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-3">
                                      <input type="text" class="form-control" name="txtCnpj"  id="txtCnpj" value="${colaborador.cnpj}">  
                                    </div>
                                    
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtInscricaoEstadual"  id="txtInscricaoEstadual" value="${colaborador.inscricaoEstadual}">
                                    </div>
                                    
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtInscricaoMunicipal" id="txtInscricaoMunicipal" value="${colaborador.inscricaoMunicipal}">
                                    </div>  
                                    
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" name="txtNumMei" id="txtNumMei" value="${colaborador.numMei}">
                                    </div>                                  
                                    
                                </div>
                            </div>
                            

                        </div> <!-- div primeira linha -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <label class="control-label">Razão Social</label>
                                    </div>
                                    <div class="col-sm-6">
                                        <label class="control-label">Nome Fantasia</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="txtRazaoSocial" id="txtRazaoSocial" value="${colaborador.razaoSocial}">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="txtNomeFantasia" id="txtNomeFantasia" value="${colaborador.nome}">
                                    </div>                                    
                                </div>
                            </div>
                        </div>
                        <div class="row"> <!-- div segunda linha -->
                            <div class="col-lg-12">
                                <div class="row">

                                    <div class="col-sm-6">
                                        <label class="control-label">Endereço</label>
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        <label class="control-label">Bairro</label>
                                    </div>                                    
                                    
                                </div>
                                <div class="row">
                                    
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="txtEndereco" id="txtEndereco" value="${colaborador.endereco}">
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="txtBairro" value="${colaborador.bairro}">
                                            <div class="input-group-btn">
                                                <button type="button" class="btn btn-default" onClick="pesquisaBairro()">...</button>
                                            </div>                                            
                                        </div>                                         
     
                                    </div>                                   
                                </div>
                            </div>
                            

                        </div> <!-- div segunda linha -->
                        
                        <div class="row"> <!-- div terceira linha -->
                            <div class="col-lg-12">
                                <div class="row">
                                    
                                    <div class="col-sm-2">
                                       <label class="control-label">CPF contato</label>
                                    </div>                                    
                                    <div class="col-sm-4">
                                       <label class="control-label">Nome contato</label>
                                    </div>
                                    
                                    <div class="col-lg-2">
                                        <label class="control-label">Fone</label>
                                    </div>
                                    
                                    <div class="col-sm-4">
                                        <label class="control-label">e-mail</label>
                                    </div>
                                    
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="txtCpf" name="txtCpf" value="${colaborador.cpf}">
                                    </div>
                                    
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="txtContato" name="txtContato" value="${colaborador.contato}">
                                    </div>
                                    
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="txtFone" name="txtFone" value="${colaborador.fone}">
                                    </div>

                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="txtEmail" name="txtEmail" value="${colaborador.email}">
                                    </div>
                                    
                                </div>
                            </div>
                                                                   

                        </div> <!-- div terceira linha --> 
                        
                        <div class="row">
                            <div class="col-lg-12">
                                
                                <div class="panel panel-default">
                                  <!-- Default panel contents -->
                                  <div class="panel-heading">Produtos associados</div>
                                  <div class="panel-body">
                                      
                                   
                                    
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label class="control-label">Produto/Serviço</label>
                                        </div>
                                        <div class="col-sm-2">
                                            <label class="control-label">ICMS</label>
                                        </div> 
                                        <div class="col-sm-2">
                                            <label class="control-label">Desconto</label>
                                        </div>                                         
                                        <div class="col-sm-2">
                                            <label class="control-label">Valor contratado</label>
                                        </div>                                        
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="txtProduto"  value="${colaboradorProduto.produto}">
                                                <div class="input-group-btn">
                                                    <button type="button" class="btn btn-default" onClick="pesquisaProduto()">...</button>
                                                </div>                                            
                                            </div>                                            
                                            
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" name="txtIcms" id="txtIcms" value="${colaboradorProduto.icms}"> 
                                            
                                        </div> 
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" name="txtDesconto" id="txtDesconto" value="${colaboradorProduto.desconto}">
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" name="txtValor" id="txtValor" value="${colaboradorProduto.valor}">
                                        </div>                                         
                                    </div>
                                    
                                    
                                  
                                  
                                  
                                    
                                    <!-- Table -->
                                    <div class="row">
                                        <table class="table table-hover table-striped" overflow="scroll">
                                            <tbody>
                                                <tr>
                                                    <th><div align="left"></div></th>
                                                    <th><div align="left">Produto</div></th>
                                                    <th><div align="left">Icms</div></th>
                                                    <th><div align="left">Desconto</div></th>
                                                    <th><div align="left">Valor</div></th>
                                                </tr> 
                                                <c:forEach var="lstColabProd" items="${lstColaboradorProduto}" varStatus="s">
                                                    <tr>
                                                        <td> <input type="image" src="visao/css/bootstrap/img/Text.png" onClick="selecionarProduto('FabricaGelo.Colaborador.AcaoSelecionaColaboradorProduto?idColaboradorProduto=${lstColabProd.idColaboradorProduto}')">  
                                                             <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="deletarProduto('FabricaGelo.Colaborador.AcaoDeletaColaboradorProduto?idColaboradorProduto=${lstColabProd.idColaboradorProduto}')">  </td>
                                                        <td> ${lstColabProd.produto} </td>
                                                        <td> ${lstColabProd.icms} </td>
                                                        <td> ${lstColabProd.desconto} </td>
                                                        <td> ${lstColabProd.valor} </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    
                                  </div>
                                </div>                                
                                
                            </div>
 
                            
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">Dias para visita</div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <table class="table table-hover table-striped" overflow="scroll">
                                                <tbody>
                                                    <tr>
                                                        <th><div align="left">Domingo</div></th>
                                                        <th><div align="left">Segunda-feira</div></th>
                                                        <th><div align="left">Terça-feira</div></th>
                                                        <th><div align="left">Quarta-feira</div></th>
                                                        <th><div align="left">Quinta-feira</div></th>
                                                        <th><div align="left">Sexta-feira</div></th>
                                                        <th><div align="left">Sábado</div></th>
                                                    </tr> 
                                                    <tr>
                                                        
                                                        <c:forEach var="lstVisiCola" items="${lstVisitaColaborador}" varStatus="s">
                                                            <td><input type="image" src="${lstVisiCola.imagemAtiva}" onClick="alterarDia('FabricaGelo.Colaborador.AcaoAlteraDia?idVisitaColaborador=${lstVisiCola.idVisitaColaborador}')"></td>
                                                        </c:forEach>

                                                    </tr>
                                                </tbody>
                                            </table>
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
                var numDocumento = formColaborador.txtDocumento.value;
                if (numDocumento == "")
                {
                    alert('Necessário documento do colaborador!');
                    return false;
                }
                else
                {
                    document.forms[0].action="FabricaGelo.Colaborador.AcaoGravaColaborador";
                    document.forms[0].submit();
                }
            }
            function pesquisaProduto()
            {
                    document.forms[0].action="FabricaGelo.Colaborador.AcaoBuscarProduto";
                    document.forms[0].submit();                
            }
            function pesquisaTaxa()
            {
                    document.forms[0].action="FabricaGelo.Colaborador.AcaoBuscarTaxa";
                    document.forms[0].submit();                
            } 
            function pesquisaBairro()
            {
                    document.forms[0].action="FabricaGelo.Colaborador.AcaoBuscarBairro";
                    document.forms[0].submit();                
            }            
            function selecionarProduto(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }
            function deletarProduto(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }     
            function alterarDia(caminho)
            {
                    document.forms[0].action=caminho;
                    document.forms[0].submit();                
            }
            function selecionar()
            {
                document.forms[0].action="FabricaGelo.Colaborador.AcaoRetornaPagina";
                document.forms[0].submit();                
            }            

        </script>        
    </body>
</html>
