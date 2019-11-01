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
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvConsLstDetRetContrArtEscenics;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de servicio utilizado para consultar objetos
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
public class DCmdSrvConsLstDetRetContrArtEscenicsImpl extends
		DCmdSrvConsLstDetRetContrArtEscenics {

	private static final long serialVersionUID = -694888278L;

	/**
	 * Ejecuta el comando de servicio.
	 * 
	 * @throws DExcepcion
	 *             Si ocurre algn error al realizar la consulta de objetos
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
			case CONSULTAR_POR_RETENCONTRIBARTESCENICS:
				dao
						.inicializarConsultarPorRetenContribArtEscenics(pkRetenContribArtEscenics);
				break;

			case CONSULTA_GENERICA:
				dao.inicializarConsultaGenerica(toDetRetContrArtEscenics);
				break;

			case CONSULTAR_POR_DOCUMENTO_CARGA:
				dao.inicializarConsultarPorDocumentoCarga(ideDocumentoCarga,
						numRepeticionCarga);
				break;
			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta",
						"de objetos DetRetContrArtEscenics"),
						getMensajeOperInvalida());
			}

			// Consultar
			admin.buscar(dao);
			objetosDetRetContrArtEscenics = dao
					.getColeccionDetRetContrArtEscenics();
			objetosPkDetRetContrArtEscenics = dao
					.getColeccionPkDetRetContrArtEscenics();
		} finally {
			admin.cerrarSesion();
		}
	}
}
