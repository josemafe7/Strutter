<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strutter - Categorias</title>
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
                    <h4 class="w3-center"><s:text name="- Categorias -"/></h4>
                    <hr>
                    <s:if test="categorias.size() == 0">
                        <h4>No hay categorias</h4>
                    </s:if>
                    <s:else>
                        <table border="0" style="border-collapse: collapse; margin:auto;">
                            <tr>
                                <th>Id</th>
                                <th>Categoria</th>

                                <th colspan="2">

                                    <s:form namespace="/categorias" action="goAddCategoria">
                                        <s:submit name="Nueva Categoria" value="Nueva Categoria"/>
                                    </s:form>

                                </th>
                            </tr>
                            <s:iterator value="categorias" var="categoria">
                                <tr>

                                    <td><s:property value="id"/></td>
                                    <td><s:property value="categoria"/></td>

                                    <td>
                                        <s:form namespace="/categorias" action="goModifyCategoria">
                                            <s:hidden name="id"/>
                                            <s:submit name="modificar" value="Modificar"/>
                                        </s:form>
                                    </td>
                                    <td>
                                        <s:form namespace="/categorias" action="borrarCategoria">
                                            <s:hidden name="id"/>                               
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
