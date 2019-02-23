<%-- 
    Document   : colaborador
    Created on : 28/01/2016, 12:34:10
    Author     : Miranda
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

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
                                        <label class="control-label">CNPJ(*)</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">Inscrição Estadual(*)</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <label class="control-label">Inscrição Municipal(*)</label>
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
                                        <label class="control-label">Razão Social(*)</label>
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
                                        <label class="control-label">Bairro(*)</label>
                                    </div>                                    
                                    
                                </div>
                                <div class="row">
                                    
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="txtEndereco" id="txtEndereco" value="${colaborador.endereco}">
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        
                                        <select name="cmbBairro" class="form-control">
                                            <option value="0">... Selecione um Bairro ...</option>
                                            <c:forEach var="bairr" items="${lstBairro}" varStatus="s">
                                              <c:if test="${bairr.idBairro == colaborador.idBairro}">
                                                <option value="${bairr.idBairro}" selected="selected">${bairr.descricao}</option>
                                              </c:if>
                                              <c:if test="${bairr.idBairro != colaborador.idBairro}">
                                                <option value="${bairr.idBairro}">${bairr.descricao}</option>
                                              </c:if>
                                            </c:forEach>
                                        </select>                                         

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
                                <div class="row">
                                    <div class="col-sm-8">
                                        <label class="control-label">Tipo de estabelecimentos</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="control-label">Inativo</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-8">
                                        <select name="cmbTipoColaborador" class="form-control">
                                            <option value="0">... Selecione Tipo Colaborador ...</option>
                                            <c:forEach var="varTipoColaborador" items="${lstTipoColaborador}" varStatus="s">
                                              <c:if test="${varTipoColaborador.idTipoColaborador == colaborador.idTipoColaborador}">
                                                <option value="${varTipoColaborador.idTipoColaborador}" selected="selected">${varTipoColaborador.descricao}</option>
                                              </c:if>
                                              <c:if test="${varTipoColaborador.idTipoColaborador != colaborador.idTipoColaborador}">
                                                <option value="${varTipoColaborador.idTipoColaborador}">${varTipoColaborador.descricao}</option>
                                              </c:if>
                                            </c:forEach>
                                        </select>                                       
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="checkbox" name="ck_Ativo" ${colaborador.descAtivo}>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <label class="control-label">Observação</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" name="txtObservacao" id="txtObservacao" value="${colaboradorProduto.observacao}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-lg-12">
                                
                                <div class="panel panel-default">
                                  <!-- Default panel contents -->
                                  <div class="panel-heading">Produtos associados</div>
                                  <div class="panel-body">

                                    <div class="row">
                                        <div class="col-sm-4">
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
                                        <div class="col-sm-2">
                                            
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <select name="cmbProduto" class="form-control" onchange="buscaPreco()">
                                                <option value="0">... Selecione Produto/Serviço ...</option>
                                                <c:forEach var="prod" items="${lstProduto}" varStatus="s">
                                                  <c:if test="${prod.idProduto == colaboradorProduto.idProduto}">
                                                    <option value="${prod.idProduto}" selected="selected">${prod.descricao}</option>
                                                  </c:if>
                                                  <c:if test="${prod.idProduto != colaboradorProduto.idProduto}">
                                                    <option value="${prod.idProduto}">${prod.descricao}</option>
                                                  </c:if>
                                                </c:forEach>
                                            </select>                                           
                                            
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
                                        <div class="col-sm-2">
                                            <button type="button" class="btn btn-default" onClick="addProduto()">Adicionar</button>
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
                                                        <td> <input type="image" src="visao/css/bootstrap/img/Delete.png" onClick="deletarProduto('FabricaGelo.Colaborador.AcaoDeletaColaboradorProduto?idColaboradorProduto=${lstColabProd.idColaboradorProduto}')">  </td>
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
                document.forms[0].action="FabricaGelo.Colaborador.AcaoGravaColaborador";
                document.forms[0].submit();
            }
            function excluir()
            {
                document.forms[0].action="FabricaGelo.Colaborador.AcaoDeletaColaborador";
                document.forms[0].submit();
            }
            function addProduto()
            {
                document.forms[0].action="FabricaGelo.Colaborador.AcaoAdicionaProduto";
                document.forms[0].submit();
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
            function retornar()
            {
                document.forms[0].action="FabricaGelo.Colaborador.AcaoListarColaborador";
                document.forms[0].submit();                
            }   
            
            
            // JavaScript Document
            //adiciona mascara de cnpj
            function MascaraCNPJ(cnpj){
                    if(mascaraInteiro(cnpj) == false){
                       event.returnValue = false;
                    }       
                    return formataCampo(cnpj, '00.000.000/0000-00', event);
            }

            //adiciona mascara de cep
            function MascaraCep(cep){
                            if(mascaraInteiro(cep)==false){
                            event.returnValue = false;
                    }       
                    return formataCampo(cep, '00.000-000', event);
            }

            //adiciona mascara de data
            function MascaraData(data){
                    if(mascaraInteiro(data)==false){
                            event.returnValue = false;
                    }       
                    return formataCampo(data, '00/00/0000', event);
            }

            //adiciona mascara ao telefone
            function MascaraTelefone(tel){  
                    if(mascaraInteiro(tel)==false){
                            event.returnValue = false;
                    }       
                    return formataCampo(tel, '(00) 0000-0000', event);
            }

            //adiciona mascara ao CPF
            function MascaraCPF(cpf){
                    if(mascaraInteiro(cpf)==false){
                            event.returnValue = false;
                    }       
                    return formataCampo(cpf, '000.000.000-00', event);
            }

            //valida telefone
            function ValidaTelefone(tel){
                    exp = /\(\d{2}\)\ \d{4}\-\d{4}/
                    if(!exp.test(tel.value))
                            alert('Numero de Telefone Invalido!');
            }

            //valida CEP
            function ValidaCep(cep){
                    exp = /\d{2}\.\d{3}\-\d{3}/
                    if(!exp.test(cep.value))
                            alert('Numero de Cep Invalido!');               
            }

            //valida data
            function ValidaData(data){
                    exp = /\d{2}\/\d{2}\/\d{4}/
                    if(!exp.test(data.value))
                            alert('Data Invalida!');                        
            }

            //valida o CPF digitado
            function ValidarCPF(Objcpf){
                    var cpf = Objcpf.value;
                    exp = /\.|\-/g
                    cpf = cpf.toString().replace( exp, "" ); 
                    var digitoDigitado = eval(cpf.charAt(9)+cpf.charAt(10));
                    var soma1=0, soma2=0;
                    var vlr =11;

                    for(i=0;i<9;i++){
                            soma1+=eval(cpf.charAt(i)*(vlr-1));
                            soma2+=eval(cpf.charAt(i)*vlr);
                            vlr--;
                    }       
                    soma1 = (((soma1*10)%11)==10 ? 0:((soma1*10)%11));
                    soma2=(((soma2+(2*soma1))*10)%11);

                    var digitoGerado=(soma1*10)+soma2;
                    if(digitoGerado!=digitoDigitado)        
                            alert('CPF Invalido!');         
            }

            //valida numero inteiro com mascara
            function mascaraInteiro(){
                    if (event.keyCode < 48 || event.keyCode > 57){
                            event.returnValue = false;
                            return false;
                    }
                    return true;
            }

            //valida o CNPJ digitado
            function ValidarCNPJ(ObjCnpj){
                    var cnpj = ObjCnpj.value;
                    var valida = new Array(6,5,4,3,2,9,8,7,6,5,4,3,2);
                    var dig1= new Number;
                    var dig2= new Number;

                    exp = /\.|\-|\//g
                    cnpj = cnpj.toString().replace( exp, "" ); 
                    var digito = new Number(eval(cnpj.charAt(12)+cnpj.charAt(13)));

                    for(i = 0; i<valida.length; i++){
                            dig1 += (i>0? (cnpj.charAt(i-1)*valida[i]):0);  
                            dig2 += cnpj.charAt(i)*valida[i];       
                    }
                    dig1 = (((dig1%11)<2)? 0:(11-(dig1%11)));
                    dig2 = (((dig2%11)<2)? 0:(11-(dig2%11)));

                    if(((dig1*10)+dig2) != digito)  
                            alert('CNPJ Invalido!');

            }

            //formata de forma generica os campos
            function formataCampo(campo, Mascara, evento) { 
                    var boleanoMascara; 

                    var Digitato = evento.keyCode;
                    exp = /\-|\.|\/|\(|\)| /g
                    campoSoNumeros = campo.value.toString().replace( exp, "" ); 

                    var posicaoCampo = 0;    
                    var NovoValorCampo="";
                    var TamanhoMascara = campoSoNumeros.length;; 

                    if (Digitato != 8) { // backspace 
                            for(i=0; i<= TamanhoMascara; i++) { 
                                    boleanoMascara  = ((Mascara.charAt(i) == "-") || (Mascara.charAt(i) == ".")
                                                                            || (Mascara.charAt(i) == "/")) 
                                    boleanoMascara  = boleanoMascara || ((Mascara.charAt(i) == "(") 
                                                                            || (Mascara.charAt(i) == ")") || (Mascara.charAt(i) == " ")) 
                                    if (boleanoMascara) { 
                                            NovoValorCampo += Mascara.charAt(i); 
                                              TamanhoMascara++;
                                    }else { 
                                            NovoValorCampo += campoSoNumeros.charAt(posicaoCampo); 
                                            posicaoCampo++; 
                                      }              
                              }      
                            campo.value = NovoValorCampo;
                              return true; 
                    }else { 
                            return true; 
                    }
            }            

        </script>        
    </body>
</html>
