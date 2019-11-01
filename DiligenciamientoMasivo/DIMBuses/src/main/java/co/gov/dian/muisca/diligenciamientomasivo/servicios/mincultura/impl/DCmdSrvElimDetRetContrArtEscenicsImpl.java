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
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvElimDetRetContrArtEscenics;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de servicio utilizado para eliminar un objeto
 * DetRetContrArtEscenics.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: DIAN
 * </p>
 * 
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * 
 *          <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvElimDetRetContrArtEscenicsImpl extends
		DCmdSrvElimDetRetContrArtEscenics {

	private static final long serialVersionUID = -1822667883L;

	/**
	 * Ejecuta el comando de servicio.
	 * 
	 * @throws DExcepcion
	 *             Si ocurre algn error al realizar la consulta de
	 *             DetRetContrArtEscenics o si el objeto no existe
	 */
	@Override
	protected void ejecutarComando() throws DExcepcion {
		final IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			final IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			final IDDAODetRetContrArtEscenics dao = fabrica
					.getDaoDetRetContrArtEscenics();

			// Eliminar
			switch (tipoOperacion) {

			case ELIMINAR:
				dao.inicializarEliminar(pkDetRetContrArtEscenics);
				break;
			case ELIMINAR_EN_BATCH:
				dao.inicializarEliminarEnBatch(objetosPkDetRetContrArtEscenics);
				break;
			default:
				throw new DValidarExcepcion(getMensajeGeneral("la eliminacion",
						"de objetos DetRetContrArtEscenics"),
						getMensajeOperInvalida());
			}
			registrosEliminados = admin.eliminar(dao);
		} finally {
			admin.cerrarSesion();
		}
	}
}
