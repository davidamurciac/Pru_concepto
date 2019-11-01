/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion.impl;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion.*;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de acción utilizado para eliminar un objeto FormalizaBorradorDef.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccElimFormalizaBorradorDefImpl extends DCmdAccElimFormalizaBorradorDef {
	private static final long serialVersionUID = 28649031L; 

	/**
	 * Ejecuta el comando de acción.
	 */
	protected void ejecutarComando() {
		try {
			DCmdSrvElimFormalizaBorradorDef servicio = (DCmdSrvElimFormalizaBorradorDef) getServicio("diligenciamientomasivo.DCmdSrvElimFormalizaBorradorDef");
			servicio.inicializar(pkFormalizaBorradorDef);
			servicio.ejecutar();
			isOk = true;
		}
		catch (DExcepcion ex) {
			mensajeError = ex.getMessage();
			mensajeErrorDetallado = ex.getMensajeDetallado();
			isOk = false;
		}
	}
}
