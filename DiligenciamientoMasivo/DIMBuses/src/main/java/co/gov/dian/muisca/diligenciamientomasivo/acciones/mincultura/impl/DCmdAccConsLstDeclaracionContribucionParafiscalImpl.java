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
 * <p>Descripcion: Comando de acción utilizado para consultar objetos DeclaracionContribucionParafiscal.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstDeclaracionContribucionParafiscalImpl extends DCmdAccConsLstDeclaracionContribucionParafiscal {
	private static final long serialVersionUID = 759764130L; 

	/**
	 * Ejecuta el comando de acción.
	 */
	protected void ejecutarComando() {
		try {
			DCmdSrvConsLstDeclaracionContribucionParafiscal servicio = (DCmdSrvConsLstDeclaracionContribucionParafiscal) getServicio("diligenciamientomasivo.DCmdSrvConsLstDeclaracionContribucionParafiscal");
			servicio.setPaginable(true);
			switch (tipoOperacion) {

			case CONSULTA_GENERICA:
				servicio.inicializarConsultaGenerica(toDeclaracionContribucionParafiscal);
				break;


			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos DeclaracionContribucionParafiscal"), getMensajeOperInvalida());
			}
			servicio.ejecutar();
			objetosDeclaracionContribucionParafiscal = servicio.getColeccionDeclaracionContribucionParafiscal();
			isOk = true;
		}
		catch (DExcepcion ex) {
			mensajeError = ex.getMessage();
			mensajeErrorDetallado = ex.getMensajeDetallado();
			isOk = false;
		}
	}
}
