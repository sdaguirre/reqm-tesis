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
                                                        <xsl:when test="/root/row/name">
                                                            <h2>Modificar Requerimiento</h2>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <h2>Nuevo Requerimiento</h2>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                    <!-- start main content -->
                                                    <div class="node-form">
                                                        <div class="taxonomy"></div>
                                                        <div class="content">
                                                            <div class="services">
                                                                <div class="columns">
                                                                    <div class="column-left">
                                                                        <form method="post" action="Requerimientos">
                                                                            <table width="500px" class="form-wider">
                                                                                <colgroup>
                                                                                    <col width="20%" />
                                                                                    <col width="60%" />
                                                                                </colgroup>
                                                                                <xsl:choose>
                                                                                    <xsl:when test="/root/row/name">
                                                                                        <xsl:for-each select="/root/row">
                                                                                            <xsl:param name="type" select="type"/>
                                                                                            <xsl:param name="estado" select="estado"/>
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
                                                                                                    <label for="inFKey">Proyecto:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <select name="inFKey" size="1">
                                                                                                        <xsl:for-each select="/root/row">
                                                                                                            <option>
                                                                                                                <xsl:attribute name="value">
                                                                                                                    <xsl:value-of select="fkey" />
                                                                                                                </xsl:attribute>
                                                                                                                <xsl:value-of select="fkeynm" />
                                                                                                            </option>
                                                                                                        </xsl:for-each>
                                                                                                    </select>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inRPadre">Req. Padre:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <select name="inRPadre" size="1">
                                                                                                        <xsl:for-each select="/root/row">
                                                                                                            <xsl:choose>
                                                                                                                <xsl:when test="padrenm">
                                                                                                                    <option>
                                                                                                                        <xsl:attribute name="value">
                                                                                                                            <xsl:value-of select="padrekey" />
                                                                                                                        </xsl:attribute>
                                                                                                                        <xsl:value-of select="padrenm" />
                                                                                                                    </option>
                                                                                                                </xsl:when>
                                                                                                                <xsl:otherwise>
                                                                                                                    <option value="0">- - - Ninguno - - -</option>
                                                                                                                </xsl:otherwise>
                                                                                                            </xsl:choose>
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
                                                                                                    <label for="inType">Tipo:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <select name="inType" size="1">
                                                                                                        <option value="1">
                                                                                                            <xsl:if test="$type=1">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                        Pendiente
                                                                                                        </option>
                                                                                                        <option value="2">
                                                                                                            <xsl:if test="$type=2">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                        Aprob. Cliente
                                                                                                        </option>
                                                                                                        <option value="3">
                                                                                                            <xsl:if test="$type=3">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                        Aprob. Empresa
                                                                                                        </option>
                                                                                                        <option value="4">
                                                                                                            <xsl:if test="$type=4">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                        Final
                                                                                                        </option>
                                                                                                    </select>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inEstado">Estado:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <select name="inEstado" size="1">
                                                                                                        <option value="1">
                                                                                                            <xsl:if test="$estado=1">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                        Inicial
                                                                                                        </option>
                                                                                                        <option value="2">
                                                                                                            <xsl:if test="$estado=2">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                        Analisis
                                                                                                        </option>
                                                                                                        <option value="3">
                                                                                                            <xsl:if test="$estado=3">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                        Asignado
                                                                                                        </option>
                                                                                                        <option value="4">
                                                                                                            <xsl:if test="$estado=4">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                        Desarrollo
                                                                                                        </option>
                                                                                                        <option value="5">
                                                                                                            <xsl:if test="$estado=5">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                        Optimizado
                                                                                                        </option>
                                                                                                        <option value="6">
                                                                                                            <xsl:if test="$estado=6">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                        Terminado
                                                                                                        </option>
                                                                                                    </select>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inProgress">Progreso:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inProgress" type="text" maxlength="255" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="avance" />
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
                                                                                                    <label for="inDtIn">Fecha:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inDtIn" id="dateFieldA" class="embed" type="text" readonly="true" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="dtini" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                    <div class="hiddenDiv">
                                                                                                        <img class="trigger" id="calImg" src="imgs/buttons/calendar.png" alt="Fecha" />
                                                                                                    </div>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inDesc">Descripcion:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <textarea name="inDesc" cols="30" rows="10">
                                                                                                        <xsl:value-of select="desc" />
                                                                                                    </textarea>
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
                                                                                                <label for="inFKey">Proyecto:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <select name="inFKey" size="1">
                                                                                                    <xsl:for-each select="/root/row">
                                                                                                        <option>
                                                                                                            <xsl:attribute name="value">
                                                                                                                <xsl:value-of select="fkey" />
                                                                                                            </xsl:attribute>
                                                                                                            <xsl:value-of select="fkeynm" />
                                                                                                        </option>
                                                                                                    </xsl:for-each>
                                                                                                </select>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inRPadre">Req. Padre:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <select name="inRPadre" size="1">
                                                                                                    <xsl:for-each select="/root/row">
                                                                                                        <xsl:choose>
                                                                                                            <xsl:when test="padrenm">
                                                                                                                <option>
                                                                                                                    <xsl:attribute name="value">
                                                                                                                        <xsl:value-of select="padrekey" />
                                                                                                                    </xsl:attribute>
                                                                                                                    <xsl:value-of select="padrenm" />
                                                                                                                </option>
                                                                                                            </xsl:when>
                                                                                                            <xsl:otherwise>
                                                                                                                <option value="0">- - - Ninguno - - -</option>
                                                                                                            </xsl:otherwise>
                                                                                                        </xsl:choose>
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
                                                                                                <label for="inType">Tipo:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <select name="inType" size="1">
                                                                                                    <option value="1">Pendiente</option>
                                                                                                    <option value="2">Aprob. Cliente</option>
                                                                                                    <option value="3">Aprob. Empresa</option>
                                                                                                    <option value="4">Final</option>
                                                                                                </select>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inEstado">Estado:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <select name="inEstado" size="1">
                                                                                                    <option value="1">Inicial</option>
                                                                                                    <option value="2">Analisis</option>
                                                                                                    <option value="3">Asignado</option>
                                                                                                    <option value="4">Desarrollo</option>
                                                                                                    <option value="5">Optimizado</option>
                                                                                                    <option value="6">Terminado</option>
                                                                                                </select>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inProgress">Progreso:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inProgress" type="text" maxlength="255" value="1" readonly="true"/>
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
                                                                                                <label for="inDtIn">Fecha:</label>
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
                                                                                                <label for="inDesc">Descripcion:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <textarea name="inDesc" cols="30" rows="10" />
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td id="tdButton">
                                                                                                <input type="hidden" name="inFKey">
                                                                                                    <xsl:attribute name="value">
                                                                                                        <xsl:value-of select="/root/row/@key" />
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