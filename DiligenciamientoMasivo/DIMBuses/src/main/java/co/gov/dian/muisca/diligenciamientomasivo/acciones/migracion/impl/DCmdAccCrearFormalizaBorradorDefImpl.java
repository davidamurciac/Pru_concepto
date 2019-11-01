/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion.impl;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion.DCmdAccCrearFormalizaBorradorDef;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion.DCmdSrvCrearFormalizaBorradorDef;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de acción utilizado para crear un objeto
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
public class DCmdAccCrearFormalizaBorradorDefImpl extends DCmdAccCrearFormalizaBorradorDef {
	private static final long serialVersionUID = -1510660787L;

	/**
	 * Ejecuta el comando de acción.
	 */
	@Override
	protected void ejecutarComando() {
		try {
			final DCmdSrvCrearFormalizaBorradorDef servicio = (DCmdSrvCrearFormalizaBorradorDef) getServicio(
					"diligenciamientomasivo.migracion.DCmdSrvCrearFormalizaBorradorDef");
			servicio.inicializar(toFormalizaBorradorDef);
			servicio.ejecutar();
			isOk = true;
			pkFormalizaBorradorDef = servicio.getPkFormalizaBorradorDef();

		} catch (final DExcepcion ex) {
			mensajeError = ex.getMessage();
			mensajeErrorDetallado = ex.getMensajeDetallado();
			isOk = false;
		}
	}
}
