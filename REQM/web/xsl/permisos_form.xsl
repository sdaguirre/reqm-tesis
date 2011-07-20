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
                                                        <xsl:when test="/root/row">
                                                            <h2>Nuevo Permiso</h2>
                                                        <!-- start main content -->
                                                            <div class="node-form">
                                                                <div class="taxonomy"></div>
                                                                <div class="content">
                                                                    <div class="services">
                                                                        <div class="columns">
                                                                            <div class="column-left">
                                                                                <form method="post" action="Permisos">
                                                                                    <table width="500px">
                                                                                        <colgroup>
                                                                                            <col width="20%" />
                                                                                            <col width="60%" />
                                                                                        </colgroup>
                                                                                        <xsl:for-each select="/root/row">
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inCode">Codigo:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inCode" type="text" maxlength="255" readonly="true" value ="00000"/>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inSite">Sitio:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <select name="inSite" >
                                                                                                        <xsl:for-each select="/root/row">
                                                                                                            <xsl:sort select="sitenm" />
                                                                                                            <option>
                                                                                                                <xsl:attribute name="value">
                                                                                                                    <xsl:value-of select="@key" />
                                                                                                                </xsl:attribute>
                                                                                                                <xsl:value-of select="sitenm" />
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
                                                                                                    <label for="inSel">Visible:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inSel" type="radio" value="true" checked="checked" />Activo
                                                                                                    <input name="inSel" type="radio" value="false" />Inactivo
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inIns">Insertar:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inIns" type="radio" value="true" checked="checked" />Activo
                                                                                                    <input name="inIns" type="radio" value="false" />Inactivo
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inMod">Modificar:</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inMod" type="radio" value="true" checked="checked" />Activo
                                                                                                    <input name="inMod" type="radio" value="false" />Inactivo
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td class="td-label">
                                                                                                    <label for="inDel">Eliminar</label>
                                                                                                </td>
                                                                                                <td class="td-input">
                                                                                                    <input name="inDel" type="radio" value="true" checked="checked" />Activo
                                                                                                    <input name="inDel" type="radio" value="false" />Inactivo
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
                                                                                    </table>
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <h2>No existen mas sitios para adicionar
                                                            </h2>
                                                            <div>Por favor cierre esta ventana para continuar</div>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
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