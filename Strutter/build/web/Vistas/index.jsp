<%-- 
Trabajo Aplicación Strutter
--%>

<%-- 
    Author     : José_Manuel_Fernández_Labrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strutter - Principal</title>
        <link rel="shortcut icon" href="../files/images/logo.png" />
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <s:head/>
    </head>
    <body class="w3-cyan">
        <s:if test="%{#session.usuario!=null}">       
            <s:include value="header.jsp"/>
            <button class="w3-button w3-hide-large w3-amber w3-xlarge" onclick="w3_open()">☰</button>
            <!-- Sidebar/menu -->
            <s:include value="sidebar.jsp"/>

            <!-- !PAGE CONTENT! -->
            <div class="w3-main" style="margin-left:300px;margin-top:43px;">

                <s:if test="%{#session.usuario.tipo.equals('CLIENTE')}">  

                    <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white">
                        <p>Como cliente, usted puede:</p>
                        <ul class="w3-ul">
                            <li><u>Buscar y comprar una oferta</u></li>
                            <li><u>Ver sus compras</u></li>
                            <li><u>Gestionar su cuenta</u></li>
                        </ul>
                        <p>Para comenzar, haga <u>click</u> en una de las opciones que tiene asignadas en la lista de la izquierda</p>
                    </div>

                </s:if>
                <s:elseif test="%{#session.usuario.tipo.equals('EMPLEADO')}">
                    <s:if test="%{#session.datosUser.departamento.deptNo==1}"> 
                        <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white">
                            <p>Como empleado ADMINISTRADOR, usted puede:</p>
                            <ul class="w3-ul">
                                <li><u>Gestionar las ofertas actuales</u></li>
                                <li><u>Gestionar clientes</u></li>
                                <li><u>Gestionar los empleados</u></li>
                                <li><u>Gestionar las empresas</u></li> 
                                <li><u>Gestionar las categorias</u></li>
                                <li><u>Gestionar las compras</u></li>
                                <li><u>Gestionar su cuenta</u></li>
                            </ul>
                            <p>Para comenzar, haga <u>click</u> en una de las opciones que tiene asignadas en la lista de la izquierda</p>
                        </div>
                    </s:if>
                    <s:elseif test="%{#session.datosUser.departamento.deptNo==2}"> 
                        <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white">
                            <p>Como empleado MODERADOR, usted puede:</p>
                            <ul class="w3-ul">
                                <li><u>Gestionar las ofertas actuales</u></li>                        
                                <li><u>Gestionar clientes</u></li>
                                <li><u>Gestionar su cuenta</u></li>
                            </ul>
                            <p>Para comenzar, haga <u>click</u> en una de las opciones que tiene asignadas en la lista de la izquierda</p>
                        </div>
                    </s:elseif>
                    <s:elseif test="%{#session.datosUser.departamento.deptNo==3}"> 
                        <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white">
                            <p>Como empleado ADMINISTRATIVO, usted puede:</p>
                            <ul class="w3-ul">
                                <li><u>Gestionar los empleados</u></li> 
                                <li><u>Gestionar las empresas</u></li> 
                                <li><u>Gestionar las categorias</u></li>
                                <li><u>Gestionar las compras</u></li>
                                <li><u>Gestionar su cuenta</u></li>
                            </ul>
                            <p>Para comenzar, haga <u>click</u> en una de las opciones que tiene asignadas en la lista de la izquierda</p>
                        </div>
                    </s:elseif>
                </s:elseif>
                <s:elseif test="%{#session.usuario.tipo.equals('EMPRESA')}">
                    <div class="w3-row-padding w3-center w3-margin-bottom w3-card-4 w3-padding-16 w3-margin w3-white">
                        <p>Como empresa, usted puede:</p>
                        <ul class="w3-ul">
                            <li><u>A&ntilde;adir una oferta</u></li>
                            <li><u>Ver mis ofertas</u></li>
                            <li><u>Ver las ofertas compradas</u></li>
                            <li><u>Gestionar su cuenta</u></li>
                        </ul>
                        <p>Para comenzar, haga <u>click</u> en una de las opciones que tiene asignadas en la lista de la izquierda</p>
                    </div>
                </s:elseif>
            </div>
        </div>

        <s:include value="scripts.jsp"/>
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
