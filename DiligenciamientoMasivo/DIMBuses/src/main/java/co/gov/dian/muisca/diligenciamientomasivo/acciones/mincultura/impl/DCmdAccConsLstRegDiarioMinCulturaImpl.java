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
 * <p>Descripcion: Comando de acción utilizado para consultar objetos RegDiarioMinCultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstRegDiarioMinCulturaImpl extends DCmdAccConsLstRegDiarioMinCultura {
	private static final long serialVersionUID = 1356129088L; 

	/**
	 * Ejecuta el comando de acción.
	 */
	protected void ejecutarComando() {
		try {
			DCmdSrvConsLstRegDiarioMinCultura servicio = (DCmdSrvConsLstRegDiarioMinCultura) getServicio("diligenciamientomasivo.DCmdSrvConsLstRegDiarioMinCultura");
			servicio.setPaginable(true);
			switch (tipoOperacion) {

			case CONSULTA_GENERICA:
				servicio.inicializarConsultaGenerica(toRegDiarioMinCultura);
				break;


			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos RegDiarioMinCultura"), getMensajeOperInvalida());
			}
			servicio.ejecutar();
			objetosRegDiarioMinCultura = servicio.getColeccionRegDiarioMinCultura();
			isOk = true;
		}
		catch (DExcepcion ex) {
			mensajeError = ex.getMessage();
			mensajeErrorDetallado = ex.getMensajeDetallado();
			isOk = false;
		}
	}
}
