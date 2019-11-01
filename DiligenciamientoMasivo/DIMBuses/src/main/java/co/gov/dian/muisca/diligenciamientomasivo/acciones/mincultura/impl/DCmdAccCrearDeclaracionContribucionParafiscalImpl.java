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
 * <p>Descripcion: Comando de acción utilizado para crear un objeto DeclaracionContribucionParafiscal.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccCrearDeclaracionContribucionParafiscalImpl extends DCmdAccCrearDeclaracionContribucionParafiscal {
	private static final long serialVersionUID = -1423389353L; 

	/**
	 * Ejecuta el comando de acción.
	 */
	protected void ejecutarComando() {
		try {
			DCmdSrvCrearDeclaracionContribucionParafiscal servicio = (DCmdSrvCrearDeclaracionContribucionParafiscal) getServicio("diligenciamientomasivo.DCmdSrvCrearDeclaracionContribucionParafiscal");
				servicio.inicializar(toDeclaracionContribucionParafiscal);
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
