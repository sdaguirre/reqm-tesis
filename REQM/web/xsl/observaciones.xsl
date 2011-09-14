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
                                                                            <xsl:choose>
                                                                                <xsl:when test="/root/inbox/text()='true'">
                                                                                    <div id="selected">Entrada</div>
                                                                                </xsl:when>
                                                                                <xsl:otherwise>
                                                                                    <a href="Observaciones?rcv=true">Entrada</a>
                                                                                </xsl:otherwise>
                                                                            </xsl:choose>
                                                                        </li>
                                                                        <li class="leaf">
                                                                            <xsl:choose>
                                                                                <xsl:when test="/root/inbox/text()='true'">
                                                                                    <a href="Observaciones?sent=true">Salida</a>
                                                                                </xsl:when>
                                                                                <xsl:otherwise>
                                                                                    <div id="selected">Salida</div>
                                                                                </xsl:otherwise>
                                                                            </xsl:choose>
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
                                                    <h2>Bandeja de Entrada</h2>
                                                    <!-- start main content -->
                                                    <div class="node-form">
                                                        <div class="taxonomy"></div>
                                                        <div class="content">
                                                            <div class="services">
                                                                <div class="columns">
                                                                    <div class="column-left">
                                                                        <table width="480px">
                                                                            <colgroup>
                                                                                <col width="60%" />
                                                                                <col width="40%" />
                                                                            </colgroup>
                                                                            <thead>
                                                                                <th>Asunto</th>
                                                                                <th>Fecha</th>
                                                                            </thead>
                                                                            <xsl:for-each select="/root/row">
                                                                                <tr>
                                                                                    <td>
                                                                                        <xsl:if test="read='true'">
                                                                                            <xsl:attribute name="id">new</xsl:attribute>
                                                                                        </xsl:if>
                                                                                        <a><xsl:attribute name="href">Observaciones?mail=<xsl:value-of select="@key"/></xsl:attribute>
                                                                                            <xsl:value-of select="name"/>
                                                                                        </a>
                                                                                    </td>
                                                                                    <td>
                                                                                        <xsl:if test="read='true'">
                                                                                            <xsl:attribute name="id">new</xsl:attribute>
                                                                                        </xsl:if>
                                                                                        <xsl:value-of select="date"/>
                                                                                    </td>
                                                                                </tr>
                                                                            </xsl:for-each>
                                                                        </table>
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