<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" omit-xml-declaration="yes"
                doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
                doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
                indent="yes" />
    <xsl:template match="/">
        <xsl:param name="prm" select="/params/param"/>
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
                <link type="text/css" rel="stylesheet" media="all" href="css/tableview.css"  />
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
                <script type="text/javascript" src="js/viewer.table.js"></script>
                <script type="text/javascript" src="js/viewer.popups.js"></script>
                <script type="text/javascript">
                    $('form').submit(function(){
                        this.post(Params);
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
                                        <a href="Reqm" title="Principal">
                                            <img src="imgs/SDLogo2.png" alt="Soluciones Digitales" class="logo" />
                                        </a>
                                    </div>
                                    <div class="col2">
                                        <div class="mail">
                                            <xsl:if test="/params/messages/text() &gt; 0">
                                                <xsl:attribute name="id">new</xsl:attribute>
                                            </xsl:if>
                                            <a rel="prettyPhoto[obs]" href="Observaciones?iframe=true&#38;width=70%&#38;height=80%">
                                                <xsl:value-of select="/params/messages"/>
                                            </a>
                                        </div>
                                        <a href="Reqm?drop=true" class="bg-user">Cerrar Sesi&#243;n</a>
                                    </div>
                                </div>
                                <div class="head-row2">
                                    <div class="col1">
                                        <!-- YUI Menu div-->
                                        <div>
                                            <div>
                                                <ul class="sf-menu sf-navbar">
                                                    <xsl:for-each select="/params/permisos/sitio">
                                                        <xsl:if test="@id=100">
                                                            <li>
                                                                <a href="PFisicas" class="sf-with-ul">Personas</a>
                                                                <ul>
                                                                    <xsl:for-each select="/params/permisos/sitio[@id&lt;200]">
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
                                                                    <xsl:for-each select="/params/permisos/sitio[@id&gt;200 and @id&lt;300]">
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
                                                                    <xsl:for-each select="/params/permisos/sitio[@id&gt;400 and @id&lt;500]">
                                                                        <xsl:if test="@id=401">
                                                                            <li>
                                                                                <a href="Params">Adm. Parametros</a>
                                                                            </li>
                                                                        </xsl:if>
                                                                        <xsl:if test="@id=408">
                                                                            <li>
                                                                                <a href="Equipos">Adm. Equipos</a>
                                                                            </li>
                                                                        </xsl:if>
                                                                    </xsl:for-each>
                                                                </ul>
                                                            </li>
                                                        </xsl:if>
                                                        <xsl:if test="@id=600">                                                             <li>                                                                 <a href="Taller" class="sf-with-ul">Taller</a>                                                                 <ul>                                                                     <xsl:for-each select="/root/permisos/sitio[@id&gt;600]">                                                                         <xsl:if test="@id=601">                                                                             <li>                                                                                 <a href="Dispositivos">Adm. Dispositivos</a>                                                                             </li>                                                                         </xsl:if>                                                                         <xsl:if test="@id=602">                                                                             <li>                                                                                 <a href="Taller">Adm. Trabajos</a>                                                                             </li>                                                                         </xsl:if>                                                                         <xsl:if test="@id=603">                                                                             <li>                                                                                 <a href="Soluciones">Adm. Soluciones</a></li></xsl:if><xsl:if test="@id=605"><li><a href="Cotizaciones">Adm. Cotizaciones</a></li></xsl:if>                                                                     </xsl:for-each>                                                                 </ul>                                                             </li>                                                         </xsl:if>                                                         <xsl:if test="@id=500">
                                                            <li>
                                                                <a href="Usuarios" class="sf-with-ul">Seguridad</a>
                                                                <ul>
                                                                    <xsl:for-each select="/params/permisos/sitio[@id&gt;500]">
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
                                        <form method="POST" action="Params" accept-charset="UTF-8" id="search-theme-form">
                                            <div>
                                                <input maxlength="128" name="inSearch" id="edit-search-theme-form-1"
                                                       size="15" title="Ingrese las palabras a buscar." class="form-text"
                                                       type="text" />
                                                <input name="srch" class="form-submit" value="" type="submit" />
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
                                                                    <h3>Parametros</h3>
                                                                </div>
                                                                <div class="content">
                                                                    <ul class="menu">
                                                                        <xsl:for-each select="/params/permisos/sitio[@id&gt;401 and @id&lt;500]">
                                                                            <xsl:if test="@id=402">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="$prm='pcargos'">
                                                                                        <li class="leaf first" ><div id="selected">Cargos</div></li>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                        <li class="leaf first">
                                                                                            <a href="Params?prm=pcargos">Cargos</a>
                                                                                        </li>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                            </xsl:if>
                                                                            <xsl:if test="@id=403">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="$prm='pcategorias'">
                                                                                        <li class="leaf"><div id="selected">Categorias</div></li>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                        <li class="leaf">
                                                                                            <a href="Params?prm=pcategorias">Categorias</a>
                                                                                        </li>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                            </xsl:if>
                                                                            <xsl:if test="@id=404">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="$prm='pcontactos'">
                                                                                        <li class="leaf first"><div id="selected">Contactos</div></li>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                        <li class="leaf">
                                                                                            <a href="Params?prm=pcontactos">Contactos</a>
                                                                                        </li>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                            </xsl:if>
                                                                            <xsl:if test="@id=405">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="$prm='pdocumentos'">
                                                                                        <li class="leaf first"><div id="selected">Documentos</div></li>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                    <li class="leaf">
                                                                                        <a href="Params?prm=pdocumentos">Documentos</a>
                                                                                    </li>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                            </xsl:if>
                                                                            <xsl:if test="@id=406">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="$prm='prubros'">
                                                                                        <li class="leaf first"><div id="selected">Rubros</div></li>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                    <li class="leaf">
                                                                                        <a href="Params?prm=prubros">Rubros</a>
                                                                                    </li>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                            </xsl:if>
                                                                            <xsl:if test="@id=407">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="$prm='ptipos'">
                                                                                        <li class="leaf first"><div id="selected">Tipos de Proyecto</div></li>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                    <li class="leaf last">
                                                                                        <a href="Params?prm=ptipos">Tipos de Proyecto</a>
                                                                                    </li>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                            </xsl:if>
                                                                            <xsl:if test="@id=410">
                                                                                <li>---------------------</li>
                                                                                <xsl:choose>
                                                                                    <xsl:when test="$prm='ppiezas'">
                                                                                        <li class="leaf first"><div id="selected">Piezas</div></li>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                    <li class="leaf last">
                                                                                        <a href="Params?prm=ppiezas">Piezas</a>
                                                                                    </li>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                            </xsl:if>
                                                                            <xsl:if test="@id=411">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="$prm='pmarcas'">
                                                                                        <li class="leaf first"><div id="selected">Marcas</div></li>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                    <li class="leaf last">
                                                                                        <a href="Params?prm=pmarcas">Marcas</a>
                                                                                    </li>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                            </xsl:if>
                                                                            <xsl:if test="@id=412">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="$prm='pmotivos'">
                                                                                        <li class="leaf first"><div id="selected">Motivos Trabajo</div></li>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                    <li class="leaf last">
                                                                                        <a href="Params?prm=pmotivos">Motivos de Trabajo</a>
                                                                                    </li>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                            </xsl:if>
                                                                        </xsl:for-each>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="cont-col">
                                                <div class="ind">
                                                    <h2>Administrador de Parametros</h2>
                                                    <!-- start main content -->
                                                    <div class="node">
                                                        <div class="taxonomy"></div>
                                                        <div class="content">
                                                            <div class="services">Esta interfaz permite la administraci&#243;n de par&#225;metros del sistema
                                                                <br />
                                                                <a href="#" class="cont-more">m&#225;s ayuda</a>
                                                                <br class="clear" />
                                                                <br />
                                                                <div class="columns">
                                                                    <div class="column-left">
                                                                        <form method="post" action="Params">
                                                                            <input type="hidden" id="keycode" name="keycode" value="" />
                                                                            <input type="hidden" name="prm">
                                                                                <xsl:attribute name="value">
                                                                                    <xsl:value-of select="$prm" />
                                                                                </xsl:attribute>
                                                                            </input>
                                                                            <table id="report" width="500px">
                                                                                <tr>
                                                                                    <th>Codigo</th>
                                                                                    <th>Nombre</th>
                                                                                    <th>
                                                                                    <xsl:for-each select="/params/permisos/sitio[@id=402]">
                                                                                        <xsl:if test="./ins='true'">
                                                                                            <a id="formNew" rel="prettyPhoto[new]">
                                                                                                <xsl:attribute name="href">Params?ins=true&#38;prm=<xsl:value-of select="$prm"/>&#38;iframe=true&#38;width=60%&#38;height=100%</xsl:attribute>
                                                                                                <img src="imgs/buttons/add.png" alt="Nuevo Registro" />
                                                                                            </a>
                                                                                            <div>Nuevo</div>
                                                                                        </xsl:if>
                                                                                    </xsl:for-each>
                                                                                    </th>
                                                                                </tr>
                                                                                <xsl:for-each select="/params/row">
                                                                                    <xsl:param name="key" select="@key"/>
                                                                                    <tr>
                                                                                        <td>
                                                                                            <xsl:value-of select="$key" />
                                                                                        </td>
                                                                                        <td>
                                                                                            <xsl:value-of select="name" />
                                                                                        </td>
                                                                                        <td>
                                                                                            <div class="arrow"></div>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="5">
                                                                                            <h1 class="xsldtitulos">Opciones</h1>
                                                                                            <ul class="gallery clearfix">
                                                                                                <xsl:for-each select="/params/permisos/sitio[@id=402]">
                                                                                                    <li>
                                                                                                        <xsl:if test="./mod='true'">
                                                                                                            <span class="formButtons">
                                                                                                                <a id="formLink" rel="prettyPhoto[mod]" >
                                                                                                                    <xsl:attribute name="href">Params?mod=<xsl:value-of select="$key" />&#38;prm=<xsl:value-of select="$prm"/>&#38;iframe=true&#38;width=60%&#38;height=100%</xsl:attribute>
                                                                                                                    <img src="imgs/buttons/edit.png" alt="Modificar Registro" />
                                                                                                                </a>Modificar
                                                                                                            </span>
                                                                                                        </xsl:if>
                                                                                                        <xsl:if test="./del='true'">
                                                                                                            <span style="padding-left: 70px;">
                                                                                                                <input class="formButtons" type="image" src="imgs/buttons/trash.png" alt="Eliminar" name="del" >
                                                                                                                    <xsl:attribute name="value">
                                                                                                                        <xsl:value-of select="$key" />
                                                                                                                    </xsl:attribute>
                                                                                                                </input>
                                                                                                                <span class="formButtons">Eliminar</span>
                                                                                                            </span>
                                                                                                        </xsl:if>
                                                                                                    </li>
                                                                                                </xsl:for-each>
                                                                                            </ul>
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