<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strutter - Ofertas</title>
        <link rel="shortcut icon" href="../files/images/logo.png" />
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <s:head/>
    </head>
    <body class="w3-cyan">
        <s:if test="%{#session.usuario.tipo.equals('EMPRESA') || !#session.usuario.tipo.equals('CLIENTE') && !#session.datosUser.departamento.deptNo.equals(3)}">  
            <s:include value="../header.jsp"/>
            <button class="w3-button w3-hide-large w3-amber w3-xlarge" onclick="w3_open()">☰</button>
            <!-- Sidebar/menu -->
            <s:include value="../sidebar.jsp"/>

            <!-- !PAGE CONTENT! -->
            <div class="w3-main" style="margin-left:300px;margin-top:43px;">

                <div class="w3-row-padding w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white">
                    <s:form namespace="/ofertas" action="addOferta" enctype="multipart/form-data">
                        <h4 class="w3-center"><s:text name="Nueva Oferta"/></h4>
                        <s:textfield cssClass="w3-input" name="titulo" label="Titulo"></s:textfield>
                        <s:textarea cssClass="w3-input" name="descripcion" label="Descripcion"></s:textarea>
                        <s:textfield cssClass="w3-input" name="precioString" label="Precio"></s:textfield>
                        <s:text name="Imagen: "/><s:file name="uploadFichero" accept="image/jpeg"/>
                        <s:select  cssClass="w3-input" label="Categoria asociada" list="listCategorias" listKey="id" listValue="%{id+'; ' +categoria}" name="idCategoria"/>
                        <s:if test="%{#session.usuario.tipo=='EMPLEADO'}">
                            <s:select   cssClass="w3-input" label="Empresa asociada" list="listEmpresas" listKey="nif" listValue="%{nif+'; ' +nombre+'; ' +direccion}" name="nifEmpresa"/>
                        </s:if>
                        <s:elseif test="%{#session.usuario.tipo=='EMPRESA'}">
                            <s:text name="Empresa asociada: "/><s:text name="#session.datosUser.nif"/>-<s:text name="#session.datosUser.nombre"/>-<s:text name="#session.datosUser.direccion"/>
                            <s:hidden name="nifEmpresa" value="%{#session.datosUser.nif}" />
                        </s:elseif>


                        <s:submit cssClass="w3-button" name="addOferta" value="Añadir oferta"></s:submit>
                    </s:form>


                </div>
            </div>
        </div>

        <s:include value="../scripts.jsp"/>
    </s:if>
    <s:else>
        <s:form name="formulario1" namespace="/first" action="first">
            <s:submit value="Acceso denegado"></s:submit>
        </s:form>  
        <script>
            document.formulario1.submit();
        </script>
    </s:else>
</body>
</html>
