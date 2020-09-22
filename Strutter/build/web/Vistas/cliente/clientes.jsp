<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strutter - Clientes</title>
        <link rel="shortcut icon" href="../files/images/logo.png" />
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <s:head/>
    </head>
    <body class="w3-cyan">
        <s:if test="%{#session.usuario.tipo.equals('EMPLEADO') && !#session.datosUser.departamento.deptNo.equals(3)}">  
            <s:include value="../header.jsp"/>
            <button class="w3-button w3-hide-large w3-amber w3-xlarge" onclick="w3_open()">☰</button>
            <!-- Sidebar/menu -->
            <s:include value="../sidebar.jsp"/>

            <!-- !PAGE CONTENT! -->
            <div class="w3-main" style="margin-left:300px;margin-top:43px;">

                <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white" style="overflow-x:auto;">
                    <h4 class="w3-center"><s:text name="- Clientes -"/></h4>
                    <hr>
                    <s:if test="clientes.size() == 0">
                        <h4>No hay clientes</h4>
                    </s:if>
                    <s:else>
                        <table border="0" style="border-collapse: collapse; font-size: 0.6em; margin:auto;">
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
                                <th colspan="2">

                                    <s:form namespace="/clientes" action="goAddCliente">
                                        <s:submit name="Nuevo Cliente" value="Nuevo Cliente"/>
                                    </s:form>

                                </th>
                            </tr>
                            <s:iterator value="clientes" var="cliente">
                                <tr>

                                    <td><s:property value="usuarioAsociado.usuario"/></td>
                                    <td><s:property value="dni"/></td>
                                    <td><s:property value="nombre"/></td>
                                    <td><s:property value="apellido1"/></td>
                                    <s:if test="apellido2 != null">
                                        <td> <s:property value="apellido2"/></td>
                                    </s:if>
                                    <s:else>
                                        <td>-</td>                                
                                    </s:else>
                                    <s:if test="sexo != null">
                                        <td> <s:property value="sexo"/></td>
                                    </s:if>
                                    <s:else>
                                        <td>-</td>                                
                                    </s:else>
                                    <td> <s:property value="fechaNac"/></td>
                                    <s:if test="direccion != null">
                                        <td> <s:property value="direccion"/></td>
                                    </s:if>
                                    <s:else>
                                        <td>-</td>                                
                                    </s:else>
                                    <s:if test="localidad != null">
                                        <td> <s:property value="localidad"/></td>
                                    </s:if>
                                    <s:else>
                                        <td>-</td>                                
                                    </s:else>
                                    <s:if test="codpostal != null">
                                        <td> <s:property value="codpostal"/></td>
                                    </s:if>
                                    <s:else>
                                        <td>-</td>                                
                                    </s:else>
                                    <s:if test="telefono != null">
                                        <td> <s:property value="telefono"/></td>
                                    </s:if>
                                    <s:else>
                                        <td>-</td>                                
                                    </s:else>
                                    <td> <s:property value="correo"/></td>

                                    <td>
                                        <s:form namespace="/clientes" action="goModifyCliente">
                                            <s:hidden name="dni"/>
                                            <s:hidden name="usuarioid" value="%{usuarioAsociado.id}"/>
                                            <s:submit name="modificar" value="Modificar"/>
                                        </s:form>
                                    </td>
                                    <td>
                                        <s:form namespace="/clientes" action="borrarCliente">
                                            <s:hidden name="dni"/>
                                            <s:hidden name="usuarioid" value="%{usuarioAsociado.id}"/>                                 
                                            <s:submit name="borrar" value="Borrar"/>
                                        </s:form>
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
