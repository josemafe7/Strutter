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
        <s:if test="%{#session.usuario.tipo.equals('CLIENTE')}">  
            <s:include value="../header.jsp"/>
            <button class="w3-button w3-hide-large w3-amber w3-xlarge" onclick="w3_open()">☰</button>
            <!-- Sidebar/menu -->
            <s:include value="../sidebar.jsp"/>



            <!-- !PAGE CONTENT! -->
            <div class="w3-main" style="margin-left:300px;margin-top:43px;">
                <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white">
                    <h4 class="w3-center"><s:text name="- Buscador -"/></h4>
                    <hr>
                    <s:form namespace="/ofertas" action="loadOfertas">
                        <s:select  cssClass="w3-input" label="Categorias" list="listCategorias" listKey="id" listValue="%{categoria}" name="idCategoria"/>
                        <s:submit name="searchByCategoria" value="Buscar por categoria"/>
                    </s:form>
                    <hr>
                    <s:form namespace="/ofertas" action="loadOfertas">
                        <s:select  cssClass="w3-input" label="Empresas" list="listEmpresas" listKey="nif" listValue="%{nombre}" name="nifEmpresa"/>
                        <s:submit name="searchByEmpresa" value="Buscar por empresa"/>
                    </s:form>
                    <hr>
                    <s:form namespace="/ofertas" action="loadOfertas">
                        <s:textfield cssClass="w3-input" name="palabraBuscar" label="Palabra"></s:textfield>
                        <s:submit name="searchByPalabra" value="Buscar por palabra"/>
                    </s:form>

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
