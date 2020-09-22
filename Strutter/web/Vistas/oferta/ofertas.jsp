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
        <s:if test="%{#session.usuario!=null}">  
            <s:include value="../header.jsp"/>
            <button class="w3-button w3-hide-large w3-amber w3-xlarge" onclick="w3_open()">☰</button>
            <!-- Sidebar/menu -->
            <s:include value="../sidebar.jsp"/>



            <!-- !PAGE CONTENT! -->
            <div class="w3-main" style="margin-left:300px;margin-top:43px;">

                <s:if test="%{#session.usuario.tipo.equals('EMPLEADO') && !#session.datosUser.departamento.deptNo.equals(3)}">
                    <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white" style="overflow-x:auto;">
                        <h4 class="w3-center"><s:text name="- Ofertas -"/></h4>
                        <hr>
                        <s:if test="ofertas.size() == 0">
                            <h4>No hay ofertas</h4>
                        </s:if>
                        <s:else>
                            <table border="0" style="margin:auto;">
                                <tr>
                                    <th>Id</th>
                                    <th>Titulo</th>
                                    <th>Descripcion</th>
                                    <th>Fecha</th>
                                    <th>Imagen</th>                               
                                    <th>Precio (€)</th>
                                    <th>Empresa</th>
                                    <th>Categoria</th>
                                    <th>Estado</th>
                                    <th colspan="3">

                                        <s:form namespace="/ofertas" action="goAddOferta">
                                            <s:submit name="Nueva Oferta" value="Nueva Oferta"/>
                                        </s:form>

                                    </th>
                                </tr>
                                <s:iterator value="ofertas" var="oferta">
                                    <tr>


                                        <td><s:property value="idOferta"/></td>
                                        <td><s:property value="titulo"/></td>
                                        <td><s:property value="descripcion"/></td>
                                        <td><s:property value="fechaOferta"/></td>
                                        <s:if test="imagen != null">
                                            <td><img src="../files/ofertasImages/<s:property value="imagen"/>" alt="<s:property value="titulo"/>" style="width:2em"></td>
                                            </s:if>
                                            <s:else>
                                            <td>-</td>                                
                                        </s:else>
                                        <td> <s:property value="precio"/> €</td>
                                        <td><s:property value="empresaAsociada.nombre"/></td>
                                        <td><s:property value="categoriaAsociada.categoria"/></td>
                                        <td><s:property value="estado"/></td>
                                        <s:if test="!estado.equals('COMPRADA')">
                                            <td>
                                                <s:form namespace="/ofertas" action="goModifyOferta">
                                                    <s:hidden name="idOferta"/>
                                                    <s:submit name="modificar" value="Modificar"/>
                                                </s:form>
                                            </td>

                                            <s:if test="estado.equals('ACTIVA')">
                                                <td>
                                                    <s:form namespace="/ofertas" action="desactivarOferta">
                                                        <s:hidden name="idOferta"/>
                                                        <s:submit name="desactivar" value="Desactivar"/>
                                                    </s:form>
                                                </td>
                                            </s:if>    
                                            <s:elseif test="estado.equals('BORRADA')">
                                                <td>
                                                    <s:form namespace="/ofertas" action="activarOferta">
                                                        <s:hidden name="idOferta"/>
                                                        <s:submit name="activar" value="Activar"/>
                                                    </s:form>
                                                </td>

                                            </s:elseif>
                                            <s:else>
                                                <td>
                                                    <s:form namespace="/ofertas" action="activarOferta">
                                                        <s:hidden name="idOferta"/>
                                                        <s:submit name="activar" value="Activar"/>
                                                    </s:form>
                                                </td>
                                                <td>
                                                    <s:form namespace="/ofertas" action="desecharOferta">
                                                        <s:hidden name="idOferta"/>
                                                        <s:submit name="desechar" value="Desechar"/>
                                                    </s:form>
                                                </td>
                                            </s:else>



                                        </s:if>

                                    </tr>


                                </s:iterator>
                            </table>
                        </s:else>

                    </div>
                </s:if>
                <s:elseif test="%{#session.usuario.tipo.equals('EMPRESA')}">
                    <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white">
                        <h4 class="w3-center"><s:text name="- Mis Ofertas -"/></h4>
                        <hr>
                        <s:if test="ofertas.size() == 0">
                            <h4>No hay ofertas</h4>
                        </s:if>
                        <s:else>
                            <table border="0" style="margin:auto;">
                                <tr>
                                    <th>Titulo</th>
                                    <th>Descripcion</th>
                                    <th>Fecha</th>
                                    <th>Imagen</th>
                                    <th>Precio (€)</th>
                                    <th>Categoria</th>
                                    <th colspan="2">

                                    </th>
                                </tr>
                                <s:iterator value="ofertas" var="oferta">
                                    <tr>


                                        <td><s:property value="titulo"/></td>
                                        <td><s:property value="descripcion"/></td>
                                        <td><s:property value="fechaOferta"/></td>
                                        <s:if test="imagen != null">
                                            <td><img src="../files/ofertasImages/<s:property value="imagen"/>" alt="<s:property value="titulo"/>" style="width:2em"></td>
                                            </s:if>
                                            <s:else>
                                            <td>-</td>                                
                                        </s:else>
                                        <td> <s:property value="precio"/> €</td>
                                        <td><s:property value="categoriaAsociada.categoria"/></td>
                                        <s:if test="!estado.equals('COMPRADA')">
                                            <td>
                                                <s:form namespace="/ofertas" action="goModifyOferta">
                                                    <s:hidden name="idOferta"/>
                                                    <s:submit name="modificar" value="Modificar"/>
                                                </s:form>
                                            </td>

                                            <s:if test="estado.equals('ACTIVA')">
                                                <td>
                                                    <s:form namespace="/ofertas" action="desactivarOferta">
                                                        <s:hidden name="idOferta"/>
                                                        <s:submit name="desactivar" value="Desactivar"/>
                                                    </s:form>
                                                </td>

                                            </s:if>
                                            <s:else>
                                                <td>
                                                    <s:form namespace="/ofertas" action="activarOferta">
                                                        <s:hidden name="idOferta"/>
                                                        <s:submit name="activar" value="Activar"/>
                                                    </s:form>
                                                </td>
                                                <td>
                                                    <s:form namespace="/ofertas" action="desecharOferta">
                                                        <s:hidden name="idOferta"/>
                                                        <s:submit name="desechar" value="Desechar"/>
                                                    </s:form>
                                                </td>
                                            </s:else>



                                        </s:if>
                                        <s:else>
                                            <td>
                                                VENDIDA
                                            </td>
                                        </s:else>

                                    </tr>


                                </s:iterator>
                            </table>


                        </s:else>
                    </div>
                </s:elseif>
                <s:elseif test="%{#session.usuario.tipo.equals('CLIENTE')}">
                    <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white">
                        <h4 class="w3-center"><s:text name="- Ofertas -"/></h4>
                        <hr>
                        <s:if test="ofertas.size() == 0">
                            <h4>No hay ofertas</h4>
                        </s:if>
                        <s:else>
                            <s:iterator value="ofertas" var="oferta">
                                <table border="1" style="width: 50em; background-color: #FDFD96; margin:auto">

                                    <tr>


                                        <th colspan="4"><s:property value="titulo"/></th>
                                    </tr>
                                    <tr>

                                        <td colspan="2"><s:property value="descripcion"/></td>
                                        <td colspan="2"><img src="../files/ofertasImages/<s:property value="imagen"/>" alt="<s:property value="titulo"/>" style="width:10em"></td>
                                    </tr>
                                    <tr>
                                        <td> <s:property value="precio"/> €</td>
                                        <td><s:property value="empresaAsociada.localidad"/> - <s:property value="empresaAsociada.direccion"/></td>

                                        <td colspan="2">
                                            <s:property value="categoriaAsociada.categoria"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3"> <s:property value="empresaAsociada.nombre"/></td>
                                        <td>
                                            <s:form namespace="/ofertas" action="goComprar">
                                                <s:hidden name="idOferta"/>
                                                <s:submit name="comprar" value="Comprar"/>
                                            </s:form>
                                        </td>
                                    </tr>



                                </table>
                                <hr>
                            </s:iterator>
                        </s:else>
                    </div>
                </s:elseif>
                <s:else>
                    <% response.sendRedirect("http://localhost:8080/Strutter/");%>
                </s:else>
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
