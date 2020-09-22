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

                <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white" style="overflow-x:auto;">
                    <h4 class="w3-center"><s:text name="- Empleados -"/></h4>
                    <hr>
                    <s:if test="empleados.size() == 0">
                        <h4>No hay empleados</h4>
                    </s:if>
                    <s:else>
                        <table border="0" style="border-collapse: collapse; font-size: 0.5em; margin:auto; ">
                            <tr>
                                <th>Usuario</th>
                                <th>Dni</th>
                                <th>Nombre</th>
                                <th>1er Apellido</th>
                                <th>2do Apellido</th>
                                <th>Sexo</th>
                                <th>Fecha Nacimiento</th>
                                <th>Direccion</th>
                                <th>Localidad</th>
                                <th>Codigo Postal</th>
                                <th>Telefono</th>
                                <th>Correo</th>
                                <th>Salario</th>
                                <th>Departamento</th>

                                <th colspan="2">

                                    <s:form namespace="/empleados" action="goAddEmpleado">
                                        <s:submit name="Nuevo Empleado" value="Nuevo Empleado"/>
                                    </s:form>

                                </th>
                            </tr>
                            <s:iterator value="empleados" var="empleado">
                                <tr>

                                    <td><s:property value="usuarioAsociado.usuario"/></td>
                                    <td><s:property value="dni"/></td>
                                    <td><s:property value="nombre"/></td>
                                    <td><s:property value="apellido1"/></td>
                                    <td> <s:property value="apellido2"/></td>
                                    <td> <s:property value="sexo"/></td>
                                    <td> <s:property value="fechaNac"/></td>
                                    <td> <s:property value="direccion"/></td>
                                    <td> <s:property value="localidad"/></td>                               
                                    <td> <s:property value="codpostal"/></td>
                                    <td> <s:property value="telefono"/></td>
                                    <td> <s:property value="correo"/></td>
                                    <td> <s:property value="salario"/></td>
                                    <td> <s:property value="departamento.deptNombre"/></td>

                                    <td>
                                        <s:if test="departamento.deptNo != 1 && !#session.datosUser.dni.equals(dni) || #session.datosUser.departamento.deptNo == 1">
                                            <s:form namespace="/empleados" action="goModifyEmpleado">
                                                <s:hidden name="dni"/>
                                                <s:hidden name="usuarioid" value="%{usuarioAsociado.id}"/>
                                                <s:submit name="modificar" value="Modificar"/>
                                            </s:form>
                                        </s:if>
                                    </td>
                                    <td>
                                        <s:if test="departamento.deptNo != 1 && !#session.datosUser.dni.equals(dni) || #session.datosUser.departamento.deptNo == 1">
                                            <s:form namespace="/empleados" action="borrarEmpleado">
                                                <s:hidden name="nif"/>
                                                <s:hidden name="usuarioid" value="%{usuarioAsociado.id}"/>                                 
                                                <s:submit name="borrar" value="Borrar"/>
                                            </s:form>
                                        </s:if>
                                    </td>

                                </tr>


                            </s:iterator>
                        </table>
                    </s:else>
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
