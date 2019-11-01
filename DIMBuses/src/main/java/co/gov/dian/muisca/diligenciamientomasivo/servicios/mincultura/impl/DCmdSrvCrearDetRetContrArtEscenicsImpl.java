/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.impl;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDAdminPersistencia;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAODetRetContrArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvCrearDetRetContrArtEscenics;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de servicio utilizado para crear un objeto
 * DetRetContrArtEscenics.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: DIAN
 * </p>
 * 
 * @author dmahechav
 * @version $Revision:$
 * 
 *          <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvCrearDetRetContrArtEscenicsImpl extends
		DCmdSrvCrearDetRetContrArtEscenics {

	private static final long serialVersionUID = 1278321813L;

	/**
	 * Ejecuta el comando de servicio.
	 * 
	 * @throws DExcepcion
	 *             Si ocurre algn error al realizar la creación de
	 *             DetRetContrArtEscenics
	 */
	@Override
	protected void ejecutarComando() throws DExcepcion {
		final IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			final IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			final IDDAODetRetContrArtEscenics dao = fabrica
					.getDaoDetRetContrArtEscenics();
			switch (tipoOperacion) {
			case CREAR:
				dao.inicializarCrear(toDetRetContrArtEscenics);
				break;
			case CREAR_EN_BATCH:
				dao.inicializarCrearEnBatch(objetosDetRetContrArtEscenics);
				break;
			default:
				throw new DValidarExcepcion(getMensajeGeneral("la eliminacion",
						"de objetos DetRetContrArtEscenics"),
						getMensajeOperInvalida());
			}
			admin.guardar(dao);

		} finally {
			admin.cerrarSesion();
		}
	}
}