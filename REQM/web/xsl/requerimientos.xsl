<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" omit-xml-declaration="yes"
                doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
                doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
                indent="yes" />
    <xsl:template match="/">
        <html dir="ltr" xml:lang="es" xmlns="http://www.w3.org/1999/xhtml" lang="es">
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
                <link type="text/css" rel="stylesheet" media="all" href="css/menu.css" />
                <link type="text/css" rel="stylesheet" media="all" href="css/superfish.css" />
                <!--<link type="text/css" rel="stylesheet" media="all" href="css/tableview.css"  />-->
                <link type="text/css" rel="stylesheet" media="all" href="css/jquery.acts_as_tree_table.css" />
                <link type="text/css" rel="stylesheet" media="all" href="css/superfish-navbar.css"  />
                <link type="text/css" rel="stylesheet" media="all" href="css/prettyPhoto.css"/>

                <!--script-->
                <script type="text/javascript" src="js/yahoo-dom-event.js"></script>
                <script type="text/javascript" src="js/animation-min.js"></script>
                <script type="text/javascript" src="js/container_core-min.js"></script>
                <script type="text/javascript" src="js/menu-min.js"></script>
                <script type="text/javascript" src="js/drupal.js"></script>
                <script type="text/javascript" src="js/cufon-yui.js"></script>
                <script type="text/javascript" src="js/cufon-replace.js"></script>
                <script type="text/javascript" src="js/Myriad_Pro_400.js"></script>
                <script type="text/javascript" src="js/jquery.js"></script>
                <script type="text/javascript" src="js/jquery_002.js"></script>
                <script type="text/javascript" src="js/jquery_003.js"></script>
                <script type="text/javascript" src="js/slider.js"></script>

                <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
                <script type="text/javascript" src="js/jquery.prettyPhoto.js"></script>
                <script type="text/javascript" src="js/jquery.acts_as_tree_table.js"></script>
                <script type="text/javascript" src="js/viewer.popups.js"></script>
                <script type="text/javascript">
                    $(document).ready(function() {
                        $(".reqtree").acts_as_tree_table();
                    });
                </script>
                <script type="text/javascript">
                    $('form').submit(function(){
                        this.post(Requerimientos);
                    });
                </script>
                
                <script type="text/javascript" src="js/hoverIntent.js"></script>
                <script type="text/javascript" src="js/superfish.js"></script>
                <script type="text/javascript">
                // initialise plugins
                jQuery(function(){
                    jQuery('ul.sf-menu').superfish();
                });
                </script>
                <script type="text/javascript" src="js/preloadImages.js"></script>
            </head>
            <body class="yui-skin-sam" id="body">
                <iframe style="position: absolute; visibility: visible; width: 10em; height: 10em; top: -114px; left: -114px; border-width: 0pt;" id="_yuiResizeMonitor"></iframe>
                <div class="min-width">
                    <div class="bg-line">
                        <div id="main">
                            <div id="header">
                                <div class="head-row1">
                                    <div class="col1">
                                        <a href="http://www.sd-bo.com" title="Home">
                                            <img src="imgs/SDLogo2.png" alt="Soluciones Digitales" class="logo" />
                                        </a>
                                    </div>
                                    <div class="col2">
                                        <a href="Reqm?drop=true" class="bg-user">Cerrar Sesi&#243;n</a>
                                    </div>
                                </div>
                                <div class="head-row2">
                                    <div class="col1">
                                        <!-- YUI Menu div-->
                                        <div>
                                            <div>
                                                <ul class="sf-menu sf-navbar">
                                                    <xsl:for-each select="/root/permisos/sitio">
                                                        <xsl:if test="@id=100">
                                                            <li>
                                                                <a href="PFisicas" class="sf-with-ul">Personas</a>
                                                                <ul>
                                                                    <xsl:for-each select="/root/permisos/sitio[@id&lt;200]">
                                                                        <xsl:if test="@id=101">
                                                                            <li>
                                                                                <a href="PFisicas">Adm. Clientes</a>
                                                                            </li>
                                                                        </xsl:if>
                                                                        <xsl:if test="@id=102">
                                                                            <li>
                                                                                <a href="Empleados">Adm. Empleados</a>
                                                                            </li>
                                                                        </xsl:if>
                                                                    </xsl:for-each>
                                                                </ul>
                                                            </li>
                                                        </xsl:if>
                                                        <xsl:if test="@id=200">
                                                            <li>
                                                                <a href="Proyectos" class="sf-with-ul">Proyectos</a>
                                                                <ul>
                                                                    <xsl:for-each select="/root/permisos/sitio[@id&lt;300]">
                                                                        <xsl:if test="@id=201">
                                                                            <li>
                                                                                <a href="Anteproyectos">Adm. Anteproyectos</a>
                                                                            </li>
                                                                        </xsl:if>
                                                                        <xsl:if test="@id=202">
                                                                            <li>
                                                                                <a href="Proyectos">Adm. Proyectos</a>
                                                                            </li>
                                                                        </xsl:if>
                                                                    </xsl:for-each>
                                                                </ul>
                                                            </li>
                                                        </xsl:if>
                                                        <xsl:if test="@id=300">
                                                            <li>
                                                                <a href="Informes" class="sf-with-ul">Informes</a>
                                                            </li>
                                                        </xsl:if>
                                                        <xsl:if test="@id=400">
                                                            <li>
                                                                <a href="Params" class="sf-with-ul">Administraci&#243;n</a>
                                                                <ul>
                                                                    <xsl:for-each select="/root/permisos/sitio[@id&gt;400]">
                                                                        <xsl:if test="@id=401">
                                                                            <li>
                                                                                <a href="Params">Adm. Parametros</a>
                                                                            </li>
                                                                        </xsl:if>
                                                                    </xsl:for-each>
                                                                </ul>
                                                            </li>
                                                        </xsl:if>
                                                        <xsl:if test="@id=500">
                                                            <li>
                                                                <a href="Seguridad" class="sf-with-ul">Seguridad</a>
                                                                <ul>
                                                                    <xsl:for-each select="/root/permisos/sitio[@id&gt;500]">
                                                                        <xsl:if test="@id=501">
                                                                            <li>
                                                                                <a href="Usuarios">Adm. Usuarios</a>
                                                                            </li>
                                                                        </xsl:if>
                                                                        <xsl:if test="@id=502">
                                                                            <li>
                                                                                <a href="Roles">Adm. Roles</a>
                                                                            </li>
                                                                        </xsl:if>
                                                                    </xsl:for-each>
                                                                </ul>
                                                            </li>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col2">
                                        <form method="GET" action="http://www.google.com/search" accept-charset="UTF-8" id="search-theme-form">
                                            <div>
                                                <input type="hidden" name="ie" value="UTF-8" />
                                                <input type="hidden" name="oe" value="UTF-8" />
                                                <input type="hidden" name="domains" value="www.sd-bo.com" />
                                                <input maxlength="128" name="q" id="edit-search-theme-form-1"
                                                       size="15" title="Ingrese las palabras a buscar." class="form-text"
                                                       type="text" />
                                                <input id="hide" type="radio" name="sitesearch" value="www.sd-bo.com"
                                                       checked="checked" />
                                                <input name="op" class="form-submit" value="" type="submit" />
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div id="cont">
                                <div class="cont-inner">
                                    <div class="bg-line-left">
                                        <div>
                                            <div id="left-col">
                                                <div class="ind">
                                                    <div class="width">
                                                        <div class="block block-user" id="block-user-1">
                                                            <div class="block-top">
                                                                <div class="title">
                                                                    <h3>Secci&#243;n</h3>
                                                                </div>
                                                                <div class="content">
                                                                    <ul class="menu">
                                                                        <li class="leaf first">
                                                                            <a href="Proyectos">Clientes</a>
                                                                        </li>
                                                                        <li class="leaf">
                                                                            <a href="Proyectos">Proyectos</a>
                                                                        </li>
                                                                        <li class="leaf last"><div id="selected">Requerimientos</div></li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="cont-col">
                                                <div class="ind">
                                                    <h2>Administrador de Requerimientos</h2>
                                                    <!-- start main content -->
                                                    <div class="node">
                                                        <div class="taxonomy"></div>
                                                        <div class="content">
                                                            <div class="services">Esta interfaz permite la administracion de requerimientos existentes en un proyecto del sistema
                                                                <br />
                                                                <a href="#" class="cont-more">mas ayuda</a>
                                                                <br class="clear" />
                                                                <br />
                                                                <div class="columns">
                                                                    <div class="column-left">
                                                                        <form method="post" action="Requerimientos">
                                                                            <input type="hidden" id="keycode" name="keycode" value="" />
                                                                            <table class="reqtree" width="710px">
                                                                                <colgroup>
                                                                                    <col width="*"/>
                                                                                    <col width="10px"/>
                                                                                    <col width="60px"/>
                                                                                    <col width="70px"/>
                                                                                    <col width="140px"/>
                                                                                </colgroup>
                                                                                <thead>
                                                                                    <th class="col-one">Requerimiento</th>
                                                                                    <th>Tipo</th>
                                                                                    <th>Estado</th>
                                                                                    <th>Avance</th>
                                                                                    <th class="fix">Operaciones
                                                                                        <xsl:for-each select="/root/permisos/sitio[@id=202]">
                                                                                            <xsl:if test="//ins='true'">
                                                                                                <a id="formNew" rel="prettyPhoto[new]" href="Requerimientos?ins=00000&#38;iframe=true&#38;width=60%&#38;height=100%" >
                                                                                                <img src="imgs/buttons/add.png" alt="Nuevo Registro" /></a>
                                                                                            </xsl:if>
                                                                                        </xsl:for-each>
                                                                                    </th>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <xsl:for-each select="/root/row">
                                                                                        <xsl:param name="key" select="@key"/>
                                                                                        <tr>
                                                                                            <xsl:attribute name="id">node-<xsl:value-of select="$key" /></xsl:attribute>
                                                                                            <xsl:choose>
                                                                                            <xsl:when test="reqp!=''">
                                                                                                <xsl:attribute name="class">child-of-node-<xsl:value-of select="reqp" /> collapsed</xsl:attribute>
                                                                                            </xsl:when>
                                                                                            <xsl:otherwise>
                                                                                                <xsl:attribute name="class">collapsed</xsl:attribute>
                                                                                            </xsl:otherwise>
                                                                                            </xsl:choose>
                                                                                                <td >
                                                                                                    <xsl:value-of select="$key" /> - <xsl:value-of select="name" />
                                                                                                </td>
                                                                                                <td>
                                                                                                    <xsl:value-of select="type" />
                                                                                                </td>
                                                                                                <td>
                                                                                                    <xsl:value-of select="estado" />
                                                                                                </td>
                                                                                                <td>
                                                                                                    <xsl:value-of select="avance" />
                                                                                                </td>
                                                                                                <td><div class="formButtons">
                                                                                                    <xsl:for-each select="/root/permisos/sitio[@id=203]">
                                                                                                        <xsl:if test="//ins='true'">
                                                                                                            <span>
                                                                                                                <a id="formLink" rel="prettyPhoto[mod]" >
                                                                                                                    <xsl:attribute name="href">Requerimientos?ins=<xsl:value-of select="$key" />&#38;iframe=true&#38;width=60%&#38;height=100%</xsl:attribute>
                                                                                                                    <xsl:attribute name="rel">prettyPhoto[<xsl:value-of select="$key" />]</xsl:attribute>
                                                                                                                    <img src="imgs/buttons/add.png" alt="Nuevo Registro Hijo" />
                                                                                                                </a>
                                                                                                            </span>
                                                                                                        </xsl:if>
                                                                                                    </xsl:for-each>
                                                                                                    <xsl:for-each select="/root/permisos/sitio[@id=206]">
                                                                                                        <xsl:if test="//sel='true'">
                                                                                                            <span class="spacer">
                                                                                                                <a id="formLink" >
                                                                                                                    <xsl:attribute name="href">ReqDocs?fkey=</xsl:attribute><img src="imgs/buttons/document.png" alt="Ver Documentos Registrados" />
                                                                                                                </a>
                                                                                                            </span>
                                                                                                        </xsl:if>
                                                                                                    </xsl:for-each>
                                                                                                    <xsl:for-each select="/root/permisos/sitio[@id=203]">
                                                                                                        <xsl:if test="//mod='true'">
                                                                                                            <span>
                                                                                                                <a id="formLink" rel="prettyPhoto[mod]" >
                                                                                                                    <xsl:attribute name="href">Requerimientos?mod=<xsl:value-of select="$key" />&#38;iframe=true&#38;width=60%&#38;height=100%</xsl:attribute>
                                                                                                                    <img src="imgs/buttons/edit.png" alt="Modificar Registro" />
                                                                                                                </a>
                                                                                                            </span>
                                                                                                        </xsl:if>
                                                                                                        <xsl:if test="//del='true'">
                                                                                                            <span>
                                                                                                                <input type="image" src="imgs/buttons/trash.png" alt="Eliminar Registro" name="del" >
                                                                                                                    <xsl:attribute name="value">
                                                                                                                        <xsl:value-of select="$key" />
                                                                                                                    </xsl:attribute>
                                                                                                                </input>
                                                                                                            </span>
                                                                                                        </xsl:if>
                                                                                                    </xsl:for-each>
                                                                                                    </div>
                                                                                                </td>
                                                                                            </tr>
                                                                                        </xsl:for-each>
                                                                                    </tbody>
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
                                <div id="banners">
                                    <div class="ind">
                                        <div class="block block-block" id="block-block-14">
                                            <div class="block-top">
                                                <div class="content">
                                                    <a href="http://www.ubuntu.com">
                                                        <img alt="Ubuntu" src="imgs/banner.jpg" />
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="block block-block" id="block-block-15">
                                            <div class="block-top">
                                                <div class="content">
                                                    <a href="http://www.dell.com">
                                                        <img alt="Dell" src="imgs/banner2.jpg" />
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="block block-block" id="block-block-16">
                                            <div class="block-top">
                                                <div class="content">
                                                    <a href="http://www.windows.com">
                                                        <img alt="MS Windows" src="imgs/banner3.jpg" />
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="block block-block" id="block-block-20">
                                            <div class="block-top">
                                                <div class="content">
                                                    <a href="http://www.hp.com">
                                                        <img alt="HP" src="imgs/banner4.jpg" />
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="pr-menu">
                                    <ul class="links primary-links">
                                        <li class="menu-242 active-trail first active">
                                            <a href="index.xml" class="active">Principal</a>
                                        </li>
                                        <li class="menu-228">
                                            <a href="empresa.html">Acerca de</a>
                                        </li>
                                        <li class="menu-215">
                                            <a href="soluciones.html">Soluciones</a>
                                        </li>
                                        <li class="menu-237">
                                            <a href="socios.html">Socios</a>
                                        </li>
                                        <li class="menu-243 last">
                                            <a href="contactenos.html">Contactenos</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div id="footer">
                            <div class="foot">
	            	Soluciones Digitales &#169; 2010
                            </div>
                        </div>
                    </div>
                </body>
            </html>
        </xsl:template>
    </xsl:stylesheet>