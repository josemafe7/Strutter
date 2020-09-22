<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<nav class="w3-sidebar w3-bar-block w3-white w3-animate-left w3-card-4" style="z-index:3;width:300px;" id="mySidebar"><br>
   
    <div class="w3-container w3-row">
        <a href="#" onclick="w3_close()" class=" w3-right w3-jumbo w3-hide-large w3-padding w3-hover-grey" title="close menu">
            <i class="fa fa-remove"></i>
        </a>
        <div class="w3-col s4">
        </div>
        <div class="w3-col s8 w3-bar">                 
            <img src='../files/images/logo.png' alt="Logo Strutter" style=" width:46px"><br>
            <span>Bienvenido, <strong><s:property value="%{#session.usuario.usuario}"/></strong></span><br>
            <s:form action="logout" namespace="/login">
                <s:submit cssClass="w3-button w3-bar-item w3-hover-grey" name="logout" value="Cerrar sesión" cssStyle="width:100%;"/>
            </s:form>
        </div>
    </div>
    <hr>
    <s:form namespace="/index" action="goIndex">
        <div class="w3-bar-block">
            <h5><s:submit cssClass="w3-bar-item w3-button" value="Página Principal"/></h5>
        </div>   
    </s:form>

    <s:if test="#session.usuario.tipo=='CLIENTE'">
        <s:form namespace="/ofertas" action="loadOfertas">
            <div class="w3-container">                     
                <s:submit cssClass="w3-bar-item w3-button" value="Ofertas" cssStyle="width:100%;"></s:submit>

                </div>
        </s:form>
        <s:form namespace="/ofertas" action="loadBuscador">
            <div class="w3-container">                     
                <s:submit cssClass="w3-bar-item w3-button" name="btnBuscador" value="Buscador" cssStyle="width:100%;"></s:submit>

                </div>
        </s:form>
        <s:form namespace="/compras" action="loadCompras">
            <div class="w3-container">
                <s:submit cssClass="w3-bar-item w3-button" value="Mis compras" cssStyle="width:100%;"></s:submit>
                </div>
        </s:form>
        <s:form namespace="/miperfil" action="loadMiPerfil">
            <div class="w3-container">
                <s:submit cssClass="w3-bar-item w3-button" value="Mi perfil" cssStyle="width:100%;"></s:submit>
                </div>
        </s:form>
    </s:if> 
    <s:elseif test="#session.usuario.tipo=='EMPLEADO'">
        <s:if test="#session.datosUser.departamento.deptNo==1">
            <s:form namespace="/ofertas" action="loadOfertas">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Gestionar Ofertas" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
            <s:form namespace="/clientes" action="loadClientes">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Gestionar Clientes" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
            <s:form namespace="/empleados" action="loadEmpleados">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Gestionar Empleados" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
            <s:form namespace="/empresas" action="loadEmpresas">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Gestionar Empresas" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
            <s:form namespace="/categorias" action="loadCategorias">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Gestionar Categorias" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
            <s:form namespace="/compras" action="loadCompras">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Gestionar Compras" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
            <s:form namespace="/departamentos" action="loadDepartamentos">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Gestionar Departamentos" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
            <s:form namespace="/miperfil" action="loadMiPerfil">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Mi perfil" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
        </s:if>
        <s:elseif test="#session.datosUser.departamento.deptNo==2">
            <s:form namespace="/ofertas" action="loadOfertas">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Gestionar Ofertas" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
            <s:form namespace="/clientes" action="loadClientes">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Gestionar Clientes" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
            <s:form namespace="/miperfil" action="loadMiPerfil">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Mi perfil" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
        </s:elseif>
        <s:elseif test="#session.datosUser.departamento.deptNo==3">
            <s:form namespace="/empleados" action="loadEmpleados">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Gestionar Empleados" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
            <s:form namespace="/empresas" action="loadEmpresas">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Gestionar Empresa" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
            <s:form namespace="/categorias" action="loadCategorias">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Gestionar Categorias" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
            <s:form namespace="/compras" action="loadCompras">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Gestionar Compras" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
            <s:form namespace="/miperfil" action="loadMiPerfil">
                <div class="w3-container">
                    <s:submit cssClass="w3-bar-item w3-button" value="Mi perfil" cssStyle="width:100%;"></s:submit>
                    </div>
            </s:form>
        </s:elseif>
    </s:elseif>
    <s:elseif test="#session.usuario.tipo=='EMPRESA'">
        <s:form namespace="/ofertas" action="addOferta">
            <div class="w3-container">
                <s:submit cssClass="w3-bar-item w3-button" value="Subir nueva oferta" cssStyle="width:100%;"></s:submit>
                </div>
        </s:form>
        <s:form namespace="/ofertas" action="loadOfertas">
            <div class="w3-container">
                <s:submit cssClass="w3-bar-item w3-button" value="Mis ofertas" cssStyle="width:100%;"></s:submit>
                </div>
        </s:form>
        <s:form namespace="/compras" action="loadCompras">
            <div class="w3-container">
                <s:submit cssClass="w3-bar-item w3-button" value="Mis ventas" cssStyle="width:100%;"></s:submit>
                </div>
        </s:form>
        <s:form namespace="/miperfil" action="loadMiPerfil">
            <div class="w3-container">
                <s:submit cssClass="w3-bar-item w3-button" value="Mi perfil" cssStyle="width:100%;"></s:submit>
                </div>
        </s:form>
    </s:elseif> 
    <div class="w3-bar-block">
        <!--<a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>-->
        <div class="w3-container w3-margin-bottom">

        </div>
    </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>