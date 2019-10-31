/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
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
 * <p>Descripcion: Comando de acci�n utilizado para actualizar un objeto FormalizaBorradorDef.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccActFormalizaBorradorDefImpl extends DCmdAccActFormalizaBorradorDef {
	private static final long serialVersionUID = -352718056L; 

	/**
	 * Ejecuta el comando de acci�n.
	 */
	protected void ejecutarComando() {
		try {
			DCmdSrvActFormalizaBorradorDef servicio = (DCmdSrvActFormalizaBorradorDef) getServicio("diligenciamientomasivo.DCmdSrvActFormalizaBorradorDef");
			servicio.inicializar(toFormalizaBorradorDef);
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
