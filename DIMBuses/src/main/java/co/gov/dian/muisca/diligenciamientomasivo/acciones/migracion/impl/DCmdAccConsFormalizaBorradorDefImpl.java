/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion.impl;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion.DCmdAccConsFormalizaBorradorDef;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion.DCmdSrvConsFormalizaBorradorDef;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de acción utilizado para consultar un objeto
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
public class DCmdAccConsFormalizaBorradorDefImpl extends DCmdAccConsFormalizaBorradorDef {
	private static final long serialVersionUID = -730252691L;

	/**
	 * Ejecuta el comando de acción.
	 */
	@Override
	protected void ejecutarComando() {
		try {
			final DCmdSrvConsFormalizaBorradorDef servicio = (DCmdSrvConsFormalizaBorradorDef) getServicio(
					"diligenciamientomasivo.migracion.DCmdSrvConsFormalizaBorradorDef");
			servicio.inicializar(pkFormalizaBorradorDef);
			servicio.ejecutar();
			toFormalizaBorradorDef = servicio.getFormalizaBorradorDef();
			isOk = true;
		} catch (final DExcepcion ex) {
			mensajeError = ex.getMessage();
			mensajeErrorDetallado = ex.getMensajeDetallado();
			isOk = false;
		}
	}
}
