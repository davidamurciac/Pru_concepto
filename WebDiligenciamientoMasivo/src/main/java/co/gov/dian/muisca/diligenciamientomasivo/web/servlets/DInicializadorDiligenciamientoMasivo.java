package co.gov.dian.muisca.diligenciamientomasivo.web.servlets;

import javax.servlet.ServletContextEvent;import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

//import co.gov.dian.muisca.arquitectura.general.cache.DAdministradorCache;
//import co.gov.dian.muisca.arquitectura.general.excepcion.DCacheExcepcion;

public class DInicializadorDiligenciamientoMasivo implements ServletContextListener{
	
	private static Logger LOGGER = Logger.getLogger(DInicializadorDiligenciamientoMasivo.class);
    
	public DInicializadorDiligenciamientoMasivo() {
    }

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

}