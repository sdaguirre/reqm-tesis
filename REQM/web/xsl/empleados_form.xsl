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
                <link type="text/css" rel="stylesheet" media="all" href="css/jquery.datepick.css" />
                <link type="text/css" rel="stylesheet" media="all" href="css/ui-blitzer.datepick.css" />
                <!--script-->
                <script type="text/javascript" src="js/cufon-yui.js"></script>
                <script type="text/javascript" src="js/cufon-replace.js"></script>
                <script type="text/javascript" src="js/Myriad_Pro_400.js"></script>
                <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
                <script type="text/javascript" src="js/jquery-ui-1.8.1.min.js"></script>
                <script type="text/javascript" src="js/jquery.datepick.js"></script>
                <script type="text/javascript" src="js/jquery.datepick-es.js"></script>
                <script type="text/javascript" src="js/DateField.js"></script>
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
                                                        <xsl:when test="/root/row">
                                                            <h2>Modificar Empleado</h2>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <h2>Nuevo Empleado</h2>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                    <!-- start main content -->
                                                    <div class="node-form">
                                                        <div class="taxonomy"></div>
                                                        <div class="content">
                                                            <div class="services">
                                                                <div class="columns">
                                                                    <div class="column-left">
                                                                        <form method="post" action="Empleados">
                                                                            <table width="500px">
                                                                                <colgroup>
                                                                                    <col width="20%" />
                                                                                    <col width="60%" />
                                                                                </colgroup>
                                                                                <xsl:choose>
                                                                                    <xsl:when test="/root/row">
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
                                                                                                    <label for="inName">Nombre:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inName" type="text" maxlength="255" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="name" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inType">Cargo:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <select name="inType" >
                                                                                                        <xsl:for-each select="/root/params/row">
                                                                                                        <xsl:sort select="name" />
                                                                                                        <option>
                                                                                                            <xsl:attribute name="value">
                                                                                                                <xsl:value-of select="@key" />
                                                                                                            </xsl:attribute>
                                                                                                            <xsl:if test="$type=@key">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                            <xsl:value-of select="name" />
                                                                                                        </option>
                                                                                                        </xsl:for-each>
                                                                                                    </select>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inPay">Salario:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inPay" type="text" maxlength="255" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="pay" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inDtIn">Fecha Ingreso:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inDtIn" id="dateFieldA" class="embed" type="text" readonly="true" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="dtin" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                    <div class="hiddenDiv">
                                                                                                        <img class="trigger" id="calImg" src="imgs/buttons/calendar.png" alt="Fecha" />
                                                                                                    </div>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inDtOut">Fecha Egreso:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inDtOut" id="dateFieldB" class="embed" type="text" readonly="true" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="dtout" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inActive">Estado:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inActive" type="radio" value="1">
                                                                                                        <xsl:if test="active=1">
                                                                                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                                                                                        </xsl:if>Activo
                                                                                                    </input>
                                                                                                    <input name="inActive" type="radio" value="0">
                                                                                                        <xsl:if test="active=0">
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
                                                                                                <label for="inType">Cargo:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <select name="inType" size="1" >
                                                                                                    <xsl:for-each select="/root/params/row">
                                                                                                    <xsl:sort select="name" />
                                                                                                    <option><xsl:attribute name="value">
                                                                                                        <xsl:value-of select="@key" />
                                                                                                        </xsl:attribute><xsl:value-of select="name" />
                                                                                                    </option>
                                                                                                    </xsl:for-each>
                                                                                                </select>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inName">Nombre:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inName" type="text" maxlength="255" value="" />
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inPay">Salario:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inPay" type="text" maxlength="255" />
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inDtIn">Fecha Ingreso:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inDtIn" id="dateField" class="embed" type="text" readonly="true" />
                                                                                                <div class="hiddenDiv">
                                                                                                    <img class="trigger" id="calImg" src="imgs/buttons/calendar.png" alt="Fecha" />
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inActive">Estado:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inActive" type="radio" value="1" checked="checked" />Activo
                                                                                                <input name="inActive" type="radio" value="0" />Inactivo
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