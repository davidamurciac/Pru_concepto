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
 * <p>Descripcion: Comando de servicio utilizado para actualizar un objeto DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvActDeclaracionContribucionParafiscalPatrocinadorImpl extends DCmdSrvActDeclaracionContribucionParafiscalPatrocinador {

	private static final long serialVersionUID = -360041596L; 


	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion Si ocurre algn error al realizar la
	 * actualización de DeclaracionContribucionParafiscalPatrocinador
	 */
	protected void ejecutarComando() throws DExcepcion {
		IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			IDDAODeclaracionContribucionParafiscalPatrocinador dao = fabrica.getDaoDeclaracionContribucionParafiscalPatrocinador();

			// Actualizar
			dao.inicializarActualizar(toDeclaracionContribucionParafiscalPatrocinador);
			admin.guardar(dao);
		}
		finally {
			admin.cerrarSesion();
		}
	}
}
