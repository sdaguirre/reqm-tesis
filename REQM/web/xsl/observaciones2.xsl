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
                <link type="text/css" rel="stylesheet" media="all" href="css/style.css" />
                <link type="text/css" rel="stylesheet" media="all" href="css/mailtable.css" />
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
                        <div id="main-mail">
                            <div id="header" style="padding-bottom:45px;">
                                
                            </div>
                            <div id="cont">
                                <div class="cont-inner">
                                    <div class="bg-line-left-mail">
                                        <div>
                                            <div id="mail-col">
                                                <div class="ind">
                                                    <div class="width">
                                                        <div class="block block-user" id="block-user-1">
                                                            <div class="block-top">
                                                                <div class="title">
                                                                    <h2 id="mailer">Accesos</h2>
                                                                </div>
                                                                <div class="content">
                                                                    <ul class="menu">
                                                                        <li class="leaf first">
                                                                            <a href="Observaciones?rcv=true">Entrada</a>
                                                                        </li>
                                                                        <li class="leaf">
                                                                            <a href="Observaciones?sent=true">Salida</a>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="cont-col">
                                                <div class="ind">
                                                    <xsl:for-each select="/root/row">
                                                        <h2>
                                                            <xsl:value-of select="name"/>
                                                        </h2>
                                                        <h1 style="font-size:14px;">Enviado el
                                                            <xsl:value-of select="date"/>
                                                        </h1>
                                                    <!-- start main content -->
                                                        <table width="480px">
                                                            <tr>
                                                                <td colspan="3" class="message">
                                                                    <div>
                                                                        <textarea name="messager" cols="60" rows="10" readonly="true">
                                                                        <xsl:value-of select="desc"/>
                                                                        </textarea>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <tr class="optioner">
                                                                <td><a href="#" onclick="history.back()"><img src="imgs/buttons/back.png" alt="Volver"/>Volver</a></td>
                                                                <td><xsl:if test="/root/inbox/text()='true'">
                                                                    <a><xsl:attribute name="href">Observaciones?reply=<xsl:value-of select="@key"/></xsl:attribute><img src="imgs/buttons/comment.png" alt="Responder"/>Responder</a>
                                                                </xsl:if></td>
                                                                <td><xsl:if test="/root/inbox='true'">
                                                                    <a><xsl:attribute name="href">Observaciones?del=<xsl:value-of select="@key"/></xsl:attribute><img src="imgs/buttons/trash.png" alt="Eliminar"/>Eliminar</a>
                                                                </xsl:if></td>
                                                            </tr>
                                                        </table>
                                                    </xsl:for-each>
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