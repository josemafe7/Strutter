<%-- 
    Document   : login
    Created on : 30-abr-2020, 19:00:58
    Author     : Dani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strutter - Login</title>
        <link rel="shortcut icon" href="../files/images/logo.png" />
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <s:head/>
    </head>
    <body class="w3-cyan">
        <header class="w3-red w3-large w3-center w3-card-4 w3-margin-bottom">
            <h1 class="w3-jumbo" style="font-weight: bold;">Strutter</h1>
        </header>

        <div class="w3-card-4 w3-round-medium w3-white w3-margin w3-half w3-display-middle">
            <div class="w3-container">
                <s:form namespace="/login" action="login">
                    <h4 class="w3-center"><s:text name="loginTitle"/></h4>
                    <s:textfield cssClass="w3-input" key="user"></s:textfield>
                    <s:password cssClass="w3-input" key="password"></s:password>
                    <s:submit cssClass="w3-button" key="loginsubmit"></s:submit>
                </s:form>
                <s:form namespace="/registro" action="goRegistro">
                    <s:submit cssClass="w3-button" key="registrosubmit"></s:submit>
                </s:form>  
            </div>
        </div>

        <footer class="w3-margin-top w3-container w3-yellow w3-center w3-bottom" style='clear: both; height: 60px'>                 
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

