/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion.impl;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDAdminPersistencia;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.migracion.IDDAOFormalizaBorradorDef;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion.DCmdSrvConsLstFormalizaBorradorDef;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de servicio utilizado para consultar objetos
 * FormalizaBorradorDef.
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
 *          </pre>
 */
public class DCmdSrvConsLstFormalizaBorradorDefImpl extends DCmdSrvConsLstFormalizaBorradorDef {

	private static final long serialVersionUID = 1749229056L;

	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion
	 *             Si ocurre algn error al realizar la consulta de objetos
	 *             FormalizaBorradorDef
	 */
	@Override
	protected void ejecutarComando() throws DExcepcion {
		final IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			final IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			final IDDAOFormalizaBorradorDef dao = fabrica.getDaoFormalizaBorradorDef();

			switch (tipoOperacion) {

			case CONSULTA_GENERICA:
				dao.inicializarConsultaGenerica(toFormalizaBorradorDef);
				break;
			case CONSULTA_TODOS:
				dao.inicializarConsultaTodos();
				break;

			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos FormalizaBorradorDef"),
						getMensajeOperInvalida());
			}

			// Consultar
			admin.buscar(dao);
			objetosFormalizaBorradorDef = dao.getColeccionFormalizaBorradorDef();
		} finally {
			admin.cerrarSesion();
		}
	}
}
