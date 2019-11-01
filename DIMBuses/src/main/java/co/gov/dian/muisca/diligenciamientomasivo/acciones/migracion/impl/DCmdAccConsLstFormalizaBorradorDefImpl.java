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
 * <p>Descripcion: Comando de acción utilizado para consultar objetos FormalizaBorradorDef.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstFormalizaBorradorDefImpl extends DCmdAccConsLstFormalizaBorradorDef {
	private static final long serialVersionUID = -19156510L; 

	/**
	 * Ejecuta el comando de acción.
	 */
	protected void ejecutarComando() {
		try {
			DCmdSrvConsLstFormalizaBorradorDef servicio = (DCmdSrvConsLstFormalizaBorradorDef) getServicio("diligenciamientomasivo.DCmdSrvConsLstFormalizaBorradorDef");
			servicio.setPaginable(true);
			switch (tipoOperacion) {

			case CONSULTA_GENERICA:
				servicio.inicializarConsultaGenerica(toFormalizaBorradorDef);
				break;


			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos FormalizaBorradorDef"), getMensajeOperInvalida());
			}
			servicio.ejecutar();
			objetosFormalizaBorradorDef = servicio.getColeccionFormalizaBorradorDef();
			isOk = true;
		}
		catch (DExcepcion ex) {
			mensajeError = ex.getMessage();
			mensajeErrorDetallado = ex.getMensajeDetallado();
			isOk = false;
		}
	}
}
