<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" omit-xml-declaration="yes"
                doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
                doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
                indent="yes" />
    <xsl:template match="/">
        <html dir="ltr" xml:lang="es" xmlns="http://www.w3.org/1999/xhtml" lang="es">
            <head>
                <title>Administrador de Requerimientos REQM</title>
                <meta http-equiv="Content-Style-Type" content="text/css" />
                <meta http-equiv="content-language" content="es" />
                <meta name="keywords" content="sd,soldig,soluciones,digitales,soluciones digitales,Desarrollo Web,bolivia,Hosting Bolivia,Venta de Dominios,Software Bolivia,Web Flash,Soporte,Mantenimiento,Consultoria,Asesoria,Servidores,Base de Datos, Redes,Cisco,lan,wan" />
                <meta name="description" content="Sea usted bienvenido al mundo de las SOLUCIONES DIGITALES" />
                <meta name="geo.placename" CONTENT="Bolivia, Santa Cruz" />
                <meta name="robots" content="ALL" />
                <meta name="revisit-after" content="1 Days" />
                <meta name="reply-to" content="soldig@sd-bo.com" />
                <link rel="shortcut icon" href="imgs/SDLOGO.ico" type="image/x-icon" />
                <!-- CSS -->
                <link type="text/css" rel="stylesheet" media="all" href="css/form.css" />
                <link type="text/css" rel="stylesheet" media="all" href="css/style.css" />
                <link type="text/css" rel="stylesheet" media="all" href="css/tableview.css"  />
                <!--script-->
                <script type="text/javascript" src="js/cufon-yui.js"></script>
                <script type="text/javascript" src="js/cufon-replace.js"></script>
                <script type="text/javascript" src="js/Myriad_Pro_400.js"></script>
                <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
                <script type="text/javascript" src="js/jquery-ui-1.8.1.min.js"></script>
            </head>
            <body class="yui-skin-sam" id="body">
                <div class="min-width">
                    <div class="bg-line">
                        <div id="main-form">
                            <div id="header">
                                <div class="head-row1">
                                    <div class="logo-div-form">
                                        <img src="imgs/SDLogo2.png" alt="Soluciones Digitales" class="logo-form"/>
                                    </div>
                                </div>
                            </div>
                            <div id="cont">
                                <div class="cont-inner">
                                    <div >
                                        <div>
                                            <div id="cont-col">
                                                <div class="ind">
                                                    <xsl:choose>
                                                        <xsl:when test="/root/row/fkey">
                                                            <h2>Modificar Usuario</h2>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <h2>Nuevo Usuario</h2>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                    <!-- start main content -->
                                                    <div class="node-form">
                                                        <div class="taxonomy"></div>
                                                        <div class="content">
                                                            <div class="services">
                                                                <div class="columns">
                                                                    <div class="column-left">
                                                                        <form method="post" action="Usuarios">
                                                                            <table width="500px">
                                                                                <colgroup>
                                                                                    <col width="20%" />
                                                                                    <col width="60%" />
                                                                                </colgroup>
                                                                                <xsl:choose>
                                                                                    <xsl:when test="/root/row/fkey">
                                                                                        <xsl:for-each select="/root/row">
                                                                                            <xsl:param name="type" select="type"/>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inCode">Codigo:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inCode" type="text" maxlength="255" readonly="true" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="@key" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inRol">Rol:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <select name="inRol" size="1" >
                                                                                                        <option>
                                                                                                            <xsl:attribute name="value">
                                                                                                                <xsl:value-of select="role" />
                                                                                                            </xsl:attribute>
                                                                                                            <xsl:value-of select="rolenm" />
                                                                                                        </option>
                                                                                                    </select>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inPFisica">Persona:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <select name="inPFisica" size="1" >
                                                                                                        <option>
                                                                                                            <xsl:attribute name="value">
                                                                                                                <xsl:value-of select="fkey" />
                                                                                                            </xsl:attribute>
                                                                                                            <xsl:value-of select="fkeynm" />
                                                                                                        </option>
                                                                                                    </select>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td colspan="2">
                                                                                                    <div style="padding:0px 0px 10px 0px;">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</div>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inName">Cuenta:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inName" type="text" maxlength="255" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="acc" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inKind">Modalidad:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inKind" type="radio" value="1">
                                                                                                        <xsl:if test="kind=1">
                                                                                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                                                                                        </xsl:if>Empresa
                                                                                                    </input>
                                                                                                    <input name="inKind" type="radio" value="2">
                                                                                                        <xsl:if test="kind=2">
                                                                                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                                                                                        </xsl:if>Cliente
                                                                                                    </input>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inActive">Estado:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inActive" type="radio" value="1">
                                                                                                        <xsl:if test="state=1">
                                                                                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                                                                                        </xsl:if>Activo
                                                                                                    </input>
                                                                                                    <input name="inActive" type="radio" value="2">
                                                                                                        <xsl:if test="state=2">
                                                                                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                                                                                        </xsl:if>Inactivo
                                                                                                    </input>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td id="tdButton">
                                                                                                    <input type="hidden" name="inFKey">
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="fkey" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input class="formButtons" type="image" src="imgs/buttons/save.png" alt="Guardar" name="ok" value="true"/>Guardar
                                                                                                </td>
                                                                                            </tr>
                                                                                        </xsl:for-each>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inCode">Codigo:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inCode" type="text" maxlength="25" readonly="true" value="00000" />
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inRol">Rol:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <select name="inRol" size="1" >
                                                                                                    <xsl:for-each select="/root/params[@kind='roles']/row">
                                                                                                        <xsl:sort select="name" />
                                                                                                        <option>
                                                                                                            <xsl:attribute name="value">
                                                                                                                <xsl:value-of select="@key" />
                                                                                                            </xsl:attribute>
                                                                                                            <xsl:value-of select="name" />
                                                                                                        </option>
                                                                                                    </xsl:for-each>
                                                                                                </select>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inPFisica">Persona:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <select name="inPFisica" size="1" >
                                                                                                    <xsl:for-each select="/root/row">
                                                                                                        <xsl:sort select="name" />
                                                                                                        <option>
                                                                                                            <xsl:attribute name="value">
                                                                                                                <xsl:value-of select="@key" />
                                                                                                            </xsl:attribute>
                                                                                                            <xsl:value-of select="name" />
                                                                                                        </option>
                                                                                                    </xsl:for-each>
                                                                                                </select>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="2">
                                                                                                <div style="padding:0px 0px 10px 0px;">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inName">Cuenta:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inName" type="text" maxlength="25" value="" />
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inPass">Contrase&#241;a</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inPass" type="password" maxlength="25" value="" />
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inKind">Modalidad:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inKind" type="radio" value="1" checked="checked" />Empresa
                                                                                                <input name="inKind" type="radio" value="2" />Cliente
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inActive">Estado:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inActive" type="radio" value="1" checked="checked" />Activo
                                                                                                <input name="inActive" type="radio" value="2" />Inactivo
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td id="tdButton">
                                                                                                <input type="hidden" name="icon">
                                                                                                    <xsl:attribute name="value">
                                                                                                        <xsl:value-of select="@key" />
                                                                                                    </xsl:attribute>
                                                                                                </input>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input class="formButtons" type="image" src="imgs/buttons/save.png" alt="Guardar" name="ok" value="true"/>Guardar
                                                                                            </td>
                                                                                        </tr>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                            </table>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="footer">
                        <div class="foot-form">
	            	Soluciones Digitales &#169; 2010
                        </div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>