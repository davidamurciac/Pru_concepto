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
 * <p>Descripcion: Comando de acción utilizado para consultar objetos RetenContribArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstRetenContribArtEscenicsImpl extends DCmdAccConsLstRetenContribArtEscenics {
	private static final long serialVersionUID = 1243236391L; 

	/**
	 * Ejecuta el comando de acción.
	 */
	protected void ejecutarComando() {
		try {
			DCmdSrvConsLstRetenContribArtEscenics servicio = (DCmdSrvConsLstRetenContribArtEscenics) getServicio("diligenciamientomasivo.DCmdSrvConsLstRetenContribArtEscenics");
			servicio.setPaginable(true);
			switch (tipoOperacion) {

			case CONSULTA_GENERICA:
				servicio.inicializarConsultaGenerica(toRetenContribArtEscenics);
				break;


			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos RetenContribArtEscenics"), getMensajeOperInvalida());
			}
			servicio.ejecutar();
			objetosRetenContribArtEscenics = servicio.getColeccionRetenContribArtEscenics();
			isOk = true;
		}
		catch (DExcepcion ex) {
			mensajeError = ex.getMessage();
			mensajeErrorDetallado = ex.getMensajeDetallado();
			isOk = false;
		}
	}
}
