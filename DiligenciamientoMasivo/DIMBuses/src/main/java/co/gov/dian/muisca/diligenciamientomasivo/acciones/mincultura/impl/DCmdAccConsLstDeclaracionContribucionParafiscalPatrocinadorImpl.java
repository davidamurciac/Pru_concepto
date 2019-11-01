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
 * <p>Descripcion: Comando de acción utilizado para consultar objetos DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstDeclaracionContribucionParafiscalPatrocinadorImpl extends DCmdAccConsLstDeclaracionContribucionParafiscalPatrocinador {
	private static final long serialVersionUID = 1983199664L; 

	/**
	 * Ejecuta el comando de acción.
	 */
	protected void ejecutarComando() {
		try {
			DCmdSrvConsLstDeclaracionContribucionParafiscalPatrocinador servicio = (DCmdSrvConsLstDeclaracionContribucionParafiscalPatrocinador) getServicio("diligenciamientomasivo.DCmdSrvConsLstDeclaracionContribucionParafiscalPatrocinador");
			servicio.setPaginable(true);
			switch (tipoOperacion) {
			case CONSULTAR_POR_DECLARACIONCONTRIBUCIONPARAFISCAL:
				servicio.inicializarConsultarPorDeclaracionContribucionParafiscal(pkDeclaracionContribucionParafiscal);
				break;

			case CONSULTA_GENERICA:
				servicio.inicializarConsultaGenerica(toDeclaracionContribucionParafiscalPatrocinador);
				break;


			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos DeclaracionContribucionParafiscalPatrocinador"), getMensajeOperInvalida());
			}
			servicio.ejecutar();
			objetosDeclaracionContribucionParafiscalPatrocinador = servicio.getColeccionDeclaracionContribucionParafiscalPatrocinador();
			isOk = true;
		}
		catch (DExcepcion ex) {
			mensajeError = ex.getMessage();
			mensajeErrorDetallado = ex.getMensajeDetallado();
			isOk = false;
		}
	}
}
