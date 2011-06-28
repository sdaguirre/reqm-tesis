var x;
x=$(document);
x.ready(inicializarEventos);
function inicializarEventos()
{
	$("#hide").hide();
}

$(function(){
   $("#faded").faded();
});
  YAHOO.util.Event.onContentReady("productsandservices", function () {
		            var oMenuBar = new YAHOO.widget.MenuBar("productsandservices", {
		                                                            autosubmenudisplay: true,
		                                                            hidedelay: 750,
		                                                            lazyload: true });
		          var oSubmenuData = [{
										id: "Principal",
										itemdata: [
										]},
										{
										id: "Empresa",
										itemdata: []},
										{
											id: "Soluciones",
											itemdata: [
												{ text: "Redes", url: "redes.html" },
												{ text: "Equipos", url: "equipos.html" },
												{ text: "Software", url: "software.html" },
												{ text: "Diseño Grafico", url: "disenio.html" },
												{ text: "Soporte", url: "soporte.html" },
												{ text: "Portafolio", url: "portafolio.html" }
											]
										},
										{
										id: "Agenda",
										itemdata: [
										]},
										{
										id: "Contactenos",
										itemdata: [
										]},
										{
										id: "Adminitraci&oacute;n",
										itemdata: [
										]},
		          ];var ua = YAHOO.env.ua,
		                    oAnim;  // Animation instance
		                function onSubmenuBeforeShow(p_sType, p_sArgs) {
		                    var oBody,
		                        oElement,
		                        oShadow,
		                        oUL;
		                    if (this.parent) {
		                        oElement = this.element;
		                        oShadow = oElement.lastChild;
		                        oShadow.style.height = "0px";
		                        if (oAnim && oAnim.isAnimated()) {
		                            oAnim.stop();
		                            oAnim = null;
		                        }
		                        oBody = this.body;
		                        //  Check if the menu is a submenu of a submenu.
		                        if (this.parent &&
		                            !(this.parent instanceof YAHOO.widget.MenuBarItem)) {
		                            if (ua.gecko) {
		                                oBody.style.width = oBody.clientWidth + "px";
		                            }
		                            if (ua.ie == 7) {
		                                oElement.style.width = oElement.clientWidth + "px";
		                            }
		                        }
		                        oBody.style.overflow = "hidden";
		                        oUL = oBody.getElementsByTagName("ul")[0];
		                        oUL.style.marginTop = ("-" + oUL.offsetHeight + "px");
		                    }
		                }

		               function onTween(p_sType, p_aArgs, p_oShadow) {
		                    if (this.cfg.getProperty("iframe")) {
		                        this.syncIframe();
		                    }
		                    if (p_oShadow) {
		                        p_oShadow.style.height = this.element.offsetHeight + "px";
		                    }
		                }
		                function onAnimationComplete(p_sType, p_aArgs, p_oShadow) {
		                    var oBody = this.body,
		                        oUL = oBody.getElementsByTagName("ul")[0];
		                    if (p_oShadow) {
		                        p_oShadow.style.height = this.element.offsetHeight + "px";
		                    }
		                    oUL.style.marginTop = "";
		                    oBody.style.overflow = "";
		                    //  Check if the menu is a submenu of a submenu.
		                    if (this.parent &&
		                        !(this.parent instanceof YAHOO.widget.MenuBarItem)) {
		                        // Clear widths set by the "beforeshow" event handler
		                        if (ua.gecko) {
		                            oBody.style.width = "";
		                        }
		                        if (ua.ie == 7) {
		                            this.element.style.width = "";
		                        }
		                    }
		                }
		                function onSubmenuShow(p_sType, p_sArgs) {
		                    var oElement,
		                        oShadow,
		                        oUL;
		                    if (this.parent) {
		                        oElement = this.element;
		                        oShadow = oElement.lastChild;
		                        oUL = this.body.getElementsByTagName("ul")[0];
		                        oAnim = new YAHOO.util.Anim(oUL,
		                            { marginTop: { to: 0 } },
		                            .5, YAHOO.util.Easing.easeOut);
		                        oAnim.onStart.subscribe(function () {
		                            oShadow.style.height = "100%";
		                        });
		                        oAnim.animate();
		                        if (YAHOO.env.ua.ie) {
		                            oShadow.style.height = oElement.offsetHeight + "px";
		                            oAnim.onTween.subscribe(onTween, oShadow, this);
		                        }
		                        oAnim.onComplete.subscribe(onAnimationComplete, oShadow, this);
		                    }
		                }
		                oMenuBar.subscribe("beforeRender", function () {
		                    if (this.getRoot() == this) {
			                    this.getItem(2).cfg.setProperty("submenu", oSubmenuData[2]);
							}
						});
						oMenuBar.subscribe("beforeShow", onSubmenuBeforeShow);
						oMenuBar.subscribe("show", onSubmenuShow);
						oMenuBar.render();
		           		});
