/**
 *  Republica de Colombia
 * Copyright (c) 2007 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DHelperExpedienteGeneracion120v8.java, 3, 2/3/09 7:27:30 PM COT, ARMANDO PEREA MORA$
 */

package co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia;

//~--- Paquetes Requeridos -

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAplicacion;
import co.gov.dian.muisca.arquitectura.util.DFechaUtils;

import org.apache.log4j.Logger;

import java.util.Date;

//~--- Clases -

//Paquetes requeridos

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Helper para el manejo del nuevo esquema de Eventos y Tareas
 * para la Declaraci� Informativa de Precios de Transferencia Individual
 * Proceso Indicio</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: DIAN</p>
 *
 * @author Armando Perea Mora
 * @version $Revision: 3$
 * <pre>
 * $Log[10]:
 *  3    V1.3       1.1.1.0     2/3/09 7:27:30 PM COT  ARMANDO PEREA MORA
 *       Precios de Transferencia
 *  2    EntradaSalida 1.1         2/7/08 5:27:06 PM COT  ARMANDO PEREA MORA
 *       Cambios para fecha de vencimiento de tareas y separacion de tarea
 *  1    EntradaSalida 1.0         2/3/08 3:11:10 AM COT  ARMANDO PEREA MORA 
 * $
 * </pre>
 */

public class DHelperExpedienteGeneracion120v8 extends DHelperExpedienteGeneracion120 {
	//~--- Campos Estaticos -

	private static final Logger logger = Logger.getLogger(DHelperExpedienteGeneracion120v8.class);

	//~--- Constructores -

	/**
	 * Constructor p�blico
	 * @param contexto IDContexto
	 */
	public DHelperExpedienteGeneracion120v8(DComandoAplicacion comando) {
		super(comando);
	}

	//~--- Metodos -

	protected void inicializarVariablesInternas() {

		// Alias para constantes de Eventos de Precios de Transferencia
		IDConstantesFlujoPreciosTransferencia ids = null;

		super.inicializarVariablesInternas();

		setIdeTareaDiligenciarDec(new Long(ids.IDE_TAREA_DILIGENCIAR_DECLARACION_INDIVIDUAL_V8));

		setIdeParTareaIdeDocDeclaracion(new Integer(ids.IDE_PAR_TAREA_DOC_DECLARACION_INDIVIDUAL_V8));
		setIdeParTareaNumRepDocDeclaracion(new Integer(ids.IDE_PAR_TAREA_NUM_REP_DOC_DECLARACION_INDIVIDUAL_V8));
		setIdeParTareaIdeDocSolicitud(new Integer(ids.IDE_PAR_TAREA_DOC_SOLICITUD_INDIVIDUAL_V8));
		setIdeParTareaEsRepeticion(new Integer(ids.IDE_PAR_TAREA_ES_REPETICION_INDIVIDUAL_V8));
	}

	//~--- get methods -

	protected int getFechaVencimientoTarea(int anoGravable, long nit, int ideFormato, int numVersionFormato,
		int concepto, Date fechaSolicitud) throws DExcepcion {
		Date result = fechaSolicitud;
		if ((anoGravable == 2007) && (concepto == 1)) {
			Date fechaVencimientoTabla = getFechaVencimientoTabla(anoGravable, nit, ideFormato, numVersionFormato);
			if (fechaSolicitud.compareTo(fechaVencimientoTabla) <= 0) {
				result = fechaVencimientoTabla;
			}
		}
		else {
			logger.info("Fecha de Vencimiento de la Tarea hoy mismo");
		}
		logger.info("Fecha de Vencimiento de la Tarea: " + result + " vigencia " + anoGravable + " concepto " +
			concepto);

		return DFechaUtils.getFechaNegocio(result);
	}
}