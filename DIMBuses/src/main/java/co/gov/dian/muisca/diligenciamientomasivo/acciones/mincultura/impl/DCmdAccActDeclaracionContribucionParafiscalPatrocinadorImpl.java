/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
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
 * <p>Descripcion: Comando de acci�n utilizado para actualizar un objeto DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccActDeclaracionContribucionParafiscalPatrocinadorImpl extends DCmdAccActDeclaracionContribucionParafiscalPatrocinador {
	private static final long serialVersionUID = 938835386L; 

	/**
	 * Ejecuta el comando de acci�n.
	 */
	protected void ejecutarComando() {
		try {
			DCmdSrvActDeclaracionContribucionParafiscalPatrocinador servicio = (DCmdSrvActDeclaracionContribucionParafiscalPatrocinador) getServicio("diligenciamientomasivo.DCmdSrvActDeclaracionContribucionParafiscalPatrocinador");
			servicio.inicializar(toDeclaracionContribucionParafiscalPatrocinador);
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