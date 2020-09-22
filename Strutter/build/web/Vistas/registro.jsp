<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strutter - Registro</title>
        <link rel="shortcut icon" href="../files/images/logo.png" />
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-blue-grey.css">
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <s:head/>
    </head>

    <body class="w3-cyan">
        <header class="w3-red w3-large w3-center w3-card-4 w3-margin-bottom">
            <h1 class="w3-jumbo" style="font-weight: bold;">Strutter</h1>
        </header>

        <div class="w3-card-4 w3-round-medium w3-white w3-margin-bottom w3-half w3-medium" style="margin-left:300px;margin-top:43px;">

            <div class="w3-container">

                <s:form namespace="/registro" action="registro">
                    <h4 class="w3-center"><s:text name="registroTitle"/></h4>
                    <s:textfield cssClass="w3-input" key="user"></s:textfield>
                    <s:password cssClass="w3-input" key="password"></s:password>
                    <s:textfield cssClass="w3-input" key="dni"></s:textfield>
                    <s:textfield cssClass="w3-input" key="nombre"></s:textfield>
                    <s:textfield cssClass="w3-input" key="apellido1"></s:textfield>
                    <s:textfield cssClass="w3-input" key="apellido2"></s:textfield>
                    <s:radio key="sexo" list="genders"/>
                    <s:textfield cssClass="w3-input" key="fecha_nac"></s:textfield>
                    <s:textfield cssClass="w3-input" key="direccion"></s:textfield>
                    <s:textfield cssClass="w3-input" key="localidad"></s:textfield>
                    <s:textfield cssClass="w3-input" key="codpostal"></s:textfield>
                    <s:textfield cssClass="w3-input" key="telefono"></s:textfield>
                    <s:textfield cssClass="w3-input" key="correo"></s:textfield>


                    <s:submit cssClass="w3-button" name="registro" key="registrosubmit"></s:submit>
                </s:form>

                <s:form namespace="/first" action="first">
                    <s:submit cssClass="w3-button" name="tologin" key="tologinsubmit"></s:submit>
                </s:form>
            </div>
        </div>
    </div>

    <footer class="w3-margin-top w3-container w3-yellow w3-center" style='clear: both; height: 5em;'>                 
        <ul class="w3-bar">
            <li class="w3-bar-item">
                <em class="w3-large">&copy;Strutter</em>
            </li>
            <li class="w3-bar-item">
                Grupo 01:
            </li>
            <li class="w3-bar-item">
                Daniel Barciela Rueda
            </li>
            <li class="w3-bar-item">
                Jos&eacute; Manuel Fern&aacute;ndez Labrador
            </li>
            <li class="w3-bar-item">
                Manuel Herrera Pulido
            </li>
        </ul>
    </footer>
</body>
</html>
