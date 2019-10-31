/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
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
 * <p>Descripcion: Comando de servicio utilizado para crear un objeto DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvCrearDeclaracionContribucionParafiscalPatrocinadorImpl extends DCmdSrvCrearDeclaracionContribucionParafiscalPatrocinador {

	private static final long serialVersionUID = 1294136879L; 

	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion Si ocurre algn error al realizar la
	 * creaci�n de DeclaracionContribucionParafiscalPatrocinador
	 */
	protected void ejecutarComando() throws DExcepcion {
		IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			IDDAODeclaracionContribucionParafiscalPatrocinador dao = fabrica.getDaoDeclaracionContribucionParafiscalPatrocinador();

			// Crear
			if(tipoOperacion == CREAR) {
				dao.inicializarCrear(toDeclaracionContribucionParafiscalPatrocinador);
				admin.guardar(dao);
			} else if(tipoOperacion == CREAR_CON_COLECCION) {
				if(! objetosDeclaracionContribucionParafiscalPatrocinador.isEmpty()) {
					dao.inicializarCrear(objetosDeclaracionContribucionParafiscalPatrocinador);
					admin.guardar(dao);
				}
			}
			
		}
		finally {
			admin.cerrarSesion();
		}
	}
}
