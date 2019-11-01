/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.impl;


import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para crear un objeto DeclaracionContribucionParafiscal.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvCrearDeclaracionContribucionParafiscalImpl extends DCmdSrvCrearDeclaracionContribucionParafiscal {

	private static final long serialVersionUID = 461789089L; 

	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion Si ocurre algn error al realizar la
	 * creación de DeclaracionContribucionParafiscal
	 */
	protected void ejecutarComando() throws DExcepcion {
		IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			IDDAODeclaracionContribucionParafiscal dao = fabrica.getDaoDeclaracionContribucionParafiscal();

			// Crear
			dao.inicializarCrear(toDeclaracionContribucionParafiscal);
			admin.guardar(dao);
			
			persistirEventos();
			persistirPatrocinadores();
		}
		finally {
			admin.cerrarSesion();
		}
	}
	
	private void persistirEventos() throws DExcepcion {		
		if(toDeclaracionContribucionParafiscal.getColEventos() != null && !toDeclaracionContribucionParafiscal.getColEventos().isEmpty()) {
			DCmdSrvCrearDeclaracionContribucionParafiscalEvento srv = (DCmdSrvCrearDeclaracionContribucionParafiscalEvento)getServicio("diligenciamientomasivo.mincultura.DCmdSrvCrearDeclaracionContribucionParafiscalEvento");
			srv.inicializar(toDeclaracionContribucionParafiscal.getColEventos());
			srv.ejecutar();
		}		
	}
	
	private void persistirPatrocinadores() throws DExcepcion {		
		if(toDeclaracionContribucionParafiscal.getColPatrocinadores() != null && !toDeclaracionContribucionParafiscal.getColPatrocinadores().isEmpty()) {
			DCmdSrvCrearDeclaracionContribucionParafiscalPatrocinador srv = (DCmdSrvCrearDeclaracionContribucionParafiscalPatrocinador)getServicio("diligenciamientomasivo.mincultura.DCmdSrvCrearDeclaracionContribucionParafiscalPatrocinador");
			srv.inicializar(toDeclaracionContribucionParafiscal.getColPatrocinadores());
			srv.ejecutar();
		}		
	}
}
