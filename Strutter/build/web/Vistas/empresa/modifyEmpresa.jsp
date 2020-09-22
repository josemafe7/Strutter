<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strutter - Empresas</title>
        <link rel="shortcut icon" href="../files/images/logo.png" />
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script>
            function w3_open() {
                document.getElementById("mySidebar").style.display = "block";
            }

        </script>
        <s:head/>
    </head>
    <body class="w3-cyan">
<s:if test="%{#session.usuario.tipo.equals('EMPLEADO') && !#session.datosUser.departamento.deptNo.equals(2)}"> 
        <s:include value="../header.jsp"/>
         <button class="w3-button w3-hide-large w3-amber w3-xlarge" onclick="w3_open()">☰</button>
        <!-- Sidebar/menu -->
        <s:include value="../sidebar.jsp"/>

        <!-- !PAGE CONTENT! -->
        <div class="w3-main" style="margin-left:300px;margin-top:43px;">

            <div class="w3-row-padding w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white">
                <s:form namespace="/empresas" action="modifyEmpresa">
                    <h4 class="w3-center"><s:text name="Modificar Empresa"/></h4>
                    
                    <s:text name="ID Usuario: "/><s:text name="%{usuarioid}"/><br>
                    <s:hidden name="usuarioid" value="%{usuarioid}" />
                    <s:text name="Nif: "/><s:text name="%{nif}"/>
                    <s:hidden name="nif" value="%{nif}" />
                    <hr>
                    <s:textfield cssClass="w3-input" name="usuario" label="Usuario" value="%{usuarioObject.usuario}"/>
                    <s:password cssClass="w3-input" name="clave" label="Clave"></s:password>                   
                    <s:textfield cssClass="w3-input" name="nombre" label="Nombre" value="%{empresa.nombre}"></s:textfield>
                    <s:textarea cssClass="w3-input" name="descripcion" label="Descripcion" value="%{empresa.descripcion}"></s:textarea>
                    <s:textfield cssClass="w3-input" name="direccion" label="Direccion" value="%{empresa.direccion}"></s:textfield>
                    <s:textfield cssClass="w3-input" name="localidad" label="Localidad" value="%{empresa.localidad}"></s:textfield>
                    <s:textfield cssClass="w3-input" name="codpostal" label="Codigo Postal" value="%{empresa.codpostal}"></s:textfield>
                    <s:textfield cssClass="w3-input" name="telefono" label="Telefono" value="%{empresa.telefono}"></s:textfield>
                    <s:textfield cssClass="w3-input" name="correo" label="Correo electronico" value="%{empresa.correo}"></s:textfield>


                    <s:submit cssClass="w3-button" name="modifyEmpresa" value="Modificar empresa"></s:submit>
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
