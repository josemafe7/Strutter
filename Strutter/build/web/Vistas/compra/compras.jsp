<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strutter - Compras</title>
        <link rel="shortcut icon" href="../files/images/logo.png" />
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <s:head/>
    </head>
    <body class="w3-cyan">
        <s:if test="#session.usuario.tipo.equals('CLIENTE') || #session.usuario.tipo.equals('EMPRESA') || #session.usuario.tipo.equals('EMPLEADO') && !#session.datosUser.departamento.deptNo.equals(2)"> 
            <s:include value="../header.jsp"/>
            <button class="w3-button w3-hide-large w3-amber w3-xlarge" onclick="w3_open()">☰</button>
            <!-- Sidebar/menu -->
            <!-- Sidebar/menu -->
            <s:include value="../sidebar.jsp"/>

            <!-- !PAGE CONTENT! -->
            <div class="w3-main" style="margin-left:300px;margin-top:43px;">

                <s:if test="#session.usuario.tipo.equals('EMPLEADO')">
                    <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white" style="overflow-x:auto;">
                        <h4 class="w3-center"><s:text name="- Compras -"/></h4>
                        <hr>

                        <s:if test="compras.size() == 0">
                            <h4>No hay compras</h4>
                        </s:if>
                        <s:else>
                            <table border="0" style="border-collapse: collapse; margin:auto;">
                                <tr>
                                    <th>Id</th>
                                    <th>Fecha de compra</th>
                                    <th>Oferta</th>
                                    <th>Usuario</th>

                                    <th colspan="2">

                                        <s:form namespace="/compras" action="goAddCompra">
                                            <s:submit name="Nueva Compra" value="Nueva Compra"/>
                                        </s:form>

                                    </th>
                                </tr>
                                <s:iterator value="compras" var="compra">
                                    <tr>

                                        <td><s:property value="idCompra"/></td>
                                        <td><s:property value="fechaCompra"/></td>
                                        <td><s:property value="ofertaAsociada.titulo"/></td>
                                        <td><s:property value="clienteAsociado.nombre"/></td>

                                        <td>
                                            <s:form namespace="/compras" action="goModifyCompra">
                                                <s:hidden name="idCompra"/>
                                                <s:submit name="modificar" value="Modificar"/>
                                            </s:form>
                                        </td>
                                    </tr>


                                </s:iterator>
                            </table>
                        </s:else>
                    </div>
                </s:if>
                <s:elseif test="#session.usuario.tipo.equals('CLIENTE')">
                    <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white">
                        <h4 class="w3-center"><s:text name="- Mis Compras -"/></h4>
                        <hr>
                        <s:if test="compras.size() == 0">
                            <h4>No hay compras</h4>
                        </s:if>
                        <s:else>
                            <table border="0" style="border-collapse: collapse; margin:auto;">
                                <tr>
                                    <th>Fecha de compra</th>
                                    <th>Oferta</th>
                                    <th>Descripcion</th>
                                    <th>Precio (€)</th>
                                    <th>Empresa</th>


                                </tr>
                                <s:iterator value="compras" var="compra">
                                    <tr>

                                        <td><s:property value="fechaCompra"/></td>
                                        <td><s:property value="ofertaAsociada.titulo"/></td>
                                        <td><s:property value="ofertaAsociada.descripcion"/></td>
                                        <td><s:property value="ofertaAsociada.precio"/> €</td>
                                        <td><s:property value="ofertaAsociada.empresaAsociada.nombre"/></td>

                                    </tr>


                                </s:iterator>
                            </table>
                        </s:else>
                    </div>
                </s:elseif>
                <s:elseif test="#session.usuario.tipo.equals('EMPRESA')">
                    <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white">
                        <h4 class="w3-center"><s:text name="- Mis Ventas -"/></h4>
                        <hr>
                        <s:if test="compras.size() == 0">
                            <h4>No hay compras</h4>
                        </s:if>
                        <s:else>
                            <table border="0" style="border-collapse: collapse; margin:auto;">
                                <tr>
                                    <th>Fecha de compra</th>
                                    <th>Oferta</th>
                                    <th>Precio (€)</th>
                                    <th>Cliente</th>
                                    <th>Correo</th>



                                </tr>
                                <s:iterator value="compras" var="compra">
                                    <tr>

                                        <td><s:property value="fechaCompra"/></td>
                                        <td><s:property value="ofertaAsociada.titulo"/></td>
                                        <td><s:property value="ofertaAsociada.precio"/> €</td>
                                        <td><s:property value="clienteAsociado.nombre"/></td>
                                        <td><s:property value="clienteAsociado.correo"/></td>


                                    </tr>


                                </s:iterator>
                            </table>
                        </s:else>
                    </div>
                </s:elseif>
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
