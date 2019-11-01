/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion.impl;

import java.sql.Timestamp;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDAdminPersistencia;
import co.gov.dian.muisca.diligenciamientomasivo.dao.IDDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.DDAOFactoryDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.dao.migracion.IDDAOFormalizaBorradorDef;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion.DCmdSrvActFormalizaBorradorDef;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de servicio utilizado para actualizar un objeto
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
public class DCmdSrvActFormalizaBorradorDefImpl extends DCmdSrvActFormalizaBorradorDef {

	private static final long serialVersionUID = 197664310L;

	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion
	 *             Si ocurre algn error al realizar la actualización de
	 *             FormalizaBorradorDef
	 */
	@Override
	protected void ejecutarComando() throws DExcepcion {
		final IDAdminPersistencia admin = getAdministradorPersistencia();
		try {
			// Iniciar los DAO's
			final IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
			final IDDAOFormalizaBorradorDef dao = fabrica.getDaoFormalizaBorradorDef();

			toFormalizaBorradorDef.getAtt().setFecCambio(new Timestamp(System.currentTimeMillis()));
			toFormalizaBorradorDef.getAtt().setIdeUsuarioCambio(getContexto().getContextoSeguridad().getIdeUsuario());

			// Actualizar
			switch (tipoOperacion) {
			case ACTUALIZAR:
				dao.inicializarActualizar(toFormalizaBorradorDef);
				break;
			case ACTUALIZAR_ESTADO:
				dao.inicializarActualizarEstado(toFormalizaBorradorDef);
				break;
			default:
				throw new DValidarExcepcion(getMensajeGeneral("la actualización", "de objetos FormalizaBorradorDef"),
						getMensajeOperInvalida());
			}
			admin.guardar(dao);
		} finally {
			admin.cerrarSesion();
		}
	}
}
