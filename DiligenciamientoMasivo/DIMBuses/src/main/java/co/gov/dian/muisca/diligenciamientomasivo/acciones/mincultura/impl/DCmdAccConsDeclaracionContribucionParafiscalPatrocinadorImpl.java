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
 * <p>Descripcion: Comando de acción utilizado para consultar un objeto DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsDeclaracionContribucionParafiscalPatrocinadorImpl extends DCmdAccConsDeclaracionContribucionParafiscalPatrocinador {
	private static final long serialVersionUID = 1298607699L; 

	/**
	 * Ejecuta el comando de acción.
	 */
	protected void ejecutarComando() {
		try {
			DCmdSrvConsDeclaracionContribucionParafiscalPatrocinador servicio = (DCmdSrvConsDeclaracionContribucionParafiscalPatrocinador) getServicio("diligenciamientomasivo.DCmdSrvConsDeclaracionContribucionParafiscalPatrocinador");
			servicio.inicializar(pkDeclaracionContribucionParafiscalPatrocinador);
			servicio.ejecutar();
			toDeclaracionContribucionParafiscalPatrocinador = servicio.getDeclaracionContribucionParafiscalPatrocinador();
			isOk = true;
		}
		catch (DExcepcion ex) {
			mensajeError = ex.getMessage();
			mensajeErrorDetallado = ex.getMensajeDetallado();
			isOk = false;
		}
	}
}
