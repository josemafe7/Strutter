<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strutter - Empleados</title>
        <link rel="shortcut icon" href="../files/images/logo.png" />
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                    <s:form namespace="/empleados" action="addEmpleado">
                        <h4 class="w3-center"><s:text name="Nuevo Empleado"/></h4>
                        <s:textfield cssClass="w3-input" name="usuario" label="Usuario"></s:textfield>
                        <s:password cssClass="w3-input" name="clave" label="Contraseña"></s:password>
                        <s:textfield cssClass="w3-input" name="dni" label="DNI"></s:textfield>
                        <s:textfield cssClass="w3-input" name="nombre" label="Nombre"></s:textfield>
                        <s:textfield cssClass="w3-input" name="apellido1" label="Primer Apellido"></s:textfield>
                        <s:textfield cssClass="w3-input" name="apellido2" label="Segundo Apellido"></s:textfield>
                        <s:radio name="sexo" label="Sexo" list="genders"/>
                        <s:textfield cssClass="w3-input" name="fechaNac" label="Fecha nacimiento"></s:textfield>
                        <s:textfield cssClass="w3-input" name="direccion" label="Direccion"></s:textfield>
                        <s:textfield cssClass="w3-input" name="localidad" label="Localidad"></s:textfield>                   
                        <s:textfield cssClass="w3-input" name="codpostal" label="Codigo Postal"></s:textfield>
                        <s:textfield cssClass="w3-input" name="telefono" label="Telefono"></s:textfield>
                        <s:textfield cssClass="w3-input" name="correo" label="Correo electronico"></s:textfield>
                        <s:textfield cssClass="w3-input" name="salario" label="Salario"></s:textfield>
                        <s:select cssClass="w3-input" label="Departamento asociado" list="listDepartamentos" listKey="deptNo" listValue="%{deptNo+'; ' +deptNombre}" name="deptnoDepartamento"/>



                        <s:submit cssClass="w3-button" name="addEmpleado" value="Añadir empleado"></s:submit>
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
