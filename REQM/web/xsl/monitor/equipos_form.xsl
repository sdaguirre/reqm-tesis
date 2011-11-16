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
                <script type="text/javascript" src="js/jquery.extrafields.js"></script>
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
                                                        <xsl:when test="/root/row/type">
                                                            <h2>Modificar Equipo</h2>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <h2>Nuevo Equipo</h2>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                    <!-- start main content -->
                                                    <div class="node-form">
                                                        <div class="taxonomy"></div>
                                                        <div class="content">
                                                            <div class="services">
                                                                <div class="columns">
                                                                    <div class="column-left">
                                                                        <form method="post" action="Dispositivos">
                                                                            <table width="500px">
                                                                                <colgroup>
                                                                                    <col width="20%" />
                                                                                    <col width="60%" />
                                                                                </colgroup>
                                                                                <xsl:choose>
                                                                                    <xsl:when test="/root/row/type">
                                                                                        <xsl:for-each select="/root/row">
                                                                                            <xsl:param name="type" select="type"/>
                                                                                            <xsl:param name="net" select="net"/>
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
                                                                                                    <label for="inClientNm">Cliente:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inClientNm" type="text" maxlength="255" readonly="true">
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="clientnm" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
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
                                                                                                    <select name="inType" >
                                                                                                        <option value="1">
                                                                                                            <xsl:if test="$type=1">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>CPU
                                                                                                        </option>
                                                                                                        <option value="2">
                                                                                                            <xsl:if test="$type=2">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>Portatil
                                                                                                        </option>
                                                                                                        <option value="3">
                                                                                                            <xsl:if test="$type=3">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>Servidor
                                                                                                        </option>
                                                                                                        <option value="4">
                                                                                                            <xsl:if test="$type=4">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>Disp. Red
                                                                                                        </option>
                                                                                                        <option value="5">
                                                                                                            <xsl:if test="$type=5">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>Disp. Externos
                                                                                                        </option>
                                                                                                        <option value="6">
                                                                                                            <xsl:if test="$type=6">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>Otros
                                                                                                        </option>
                                                                                                    </select>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inRef">Referencia:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inRef" type="text" maxlength="255" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="ref" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inPerson">Responsable:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inPerson" type="text" maxlength="255" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="person" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inUser">Usuario:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inUser" type="text" maxlength="255" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="user" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inPass">Contrase&#241;a:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inPass" type="text" maxlength="255" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="pass" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inIP">IP Equipo:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inIP" type="text" maxlength="255" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="sip" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inNet">Red:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <select name="inNet" >
                                                                                                        <option value="1">
                                                                                                            <xsl:if test="$net=1">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>Dominio
                                                                                                        </option>
                                                                                                        <option value="2">
                                                                                                            <xsl:if test="$net=2">
                                                                                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                                                <xsl:attribute name="class">marked</xsl:attribute>
                                                                                                            </xsl:if>Grupo de Trabajo
                                                                                                        </option>
                                                                                                    </select>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inNetNm">Nombre de Red:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inNetNm" type="text" maxlength="255" >
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="netnm" />
                                                                                                        </xsl:attribute>
                                                                                                    </input>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td id="tdButton">
                                                                                                    <input type="hidden" name="inFKey">
                                                                                                        <xsl:attribute name="value">
                                                                                                            <xsl:value-of select="clientkey" />
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
                                                                                                <label for="inFKeyNm">Cliente:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inFKeyNm" type="text" maxlength="255" value="" readonly="true">
                                                                                                    <xsl:attribute name="value">
                                                                                                        <xsl:value-of select="/root/row/clientnm" />
                                                                                                    </xsl:attribute>
                                                                                                </input>
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
                                                                                                <select name="inType" >
                                                                                                    <option value="1">CPU
                                                                                                    </option>
                                                                                                    <option value="2">Portatil
                                                                                                    </option>
                                                                                                    <option value="3">Servidor
                                                                                                    </option>
                                                                                                    <option value="4">Disp. Red
                                                                                                    </option>
                                                                                                    <option value="5">Disp. Externos
                                                                                                    </option>
                                                                                                    <option value="6">Otros
                                                                                                    </option>
                                                                                                </select>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inRef">Referencia:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inRef" type="text" maxlength="255" />
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inPerson">Responsable:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inPerson" type="text" maxlength="255" />
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="2" style="padding-bottom:10px;
                                                                                            border-bottom-width: 2px;border-bottom-style: dashed;">
                                                                                                <a id="executer">+ Mas detalles</a>
                                                                                        <div id="extras" style="padding-top:10px;">
                                                                                            <table width="500px">
                                                                                                <colgroup>
                                                                                                    <col width="20%" />
                                                                                                    <col width="60%" />
                                                                                                </colgroup>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inUser">Usuario:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inUser" type="text" maxlength="255" />
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inPass">Contrase&#241;a:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inPass" type="text" maxlength="255" />
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inIP">IP Equipo:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inIP" type="text" maxlength="255" />
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inNet">Red:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <select name="inNet" >
                                                                                                    <option value="1">Dominio
                                                                                                    </option>
                                                                                                    <option value="2">Grupo de Trabajo
                                                                                                    </option>
                                                                                                </select>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="td-label">
                                                                                                <label for="inNetNm">Nombre de Red:</label>
                                                                                            </td>
                                                                                            <td class="td-input">
                                                                                                <input name="inNetNm" type="text" maxlength="255" />
                                                                                            </td>
                                                                                        </tr>
                                                                                        </table>
                                                                                        </div>
                                                                                        </td></tr>
                                                                                        <tr>
                                                                                            <td id="tdButton">
                                                                                                <input type="hidden" name="inFKey">
                                                                                                    <xsl:attribute name="value">
                                                                                                        <xsl:value-of select="/root/row/clientkey" />
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