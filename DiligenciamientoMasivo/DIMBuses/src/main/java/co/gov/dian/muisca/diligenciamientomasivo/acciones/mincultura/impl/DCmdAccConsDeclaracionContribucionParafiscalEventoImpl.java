/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.mincultura.impl;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.*;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.mincultura.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de acción utilizado para consultar un objeto DeclaracionContribucionParafiscalEvento.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsDeclaracionContribucionParafiscalEventoImpl extends DCmdAccConsDeclaracionContribucionParafiscalEvento {
	private static final long serialVersionUID = -1817490630L; 

	/**
	 * Ejecuta el comando de acción.
	 */
	protected void ejecutarComando() {
		try {
			DCmdSrvConsDeclaracionContribucionParafiscalEvento servicio = (DCmdSrvConsDeclaracionContribucionParafiscalEvento) getServicio("diligenciamientomasivo.DCmdSrvConsDeclaracionContribucionParafiscalEvento");
			servicio.inicializar(pkDeclaracionContribucionParafiscalEvento);
			servicio.ejecutar();
			toDeclaracionContribucionParafiscalEvento = servicio.getDeclaracionContribucionParafiscalEvento();
			isOk = true;
		}
		catch (DExcepcion ex) {
			mensajeError = ex.getMessage();
			mensajeErrorDetallado = ex.getMensajeDetallado();
			isOk = false;
		}
	}
}
