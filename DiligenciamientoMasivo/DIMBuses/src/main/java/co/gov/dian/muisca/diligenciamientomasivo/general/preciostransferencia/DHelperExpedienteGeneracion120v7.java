/**
 *  Republica de Colombia
 * Copyright (c) 2008 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DHelperExpedienteGeneracion120v7.java, 5, 2/3/09 7:26:55 PM COT, ARMANDO PEREA MORA$
 */

package co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia;

//~--- Paquetes Requeridos -

import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAplicacion;

import org.apache.log4j.Logger;

//~--- Clases -

//Paquetes requeridos

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Helper para el manejo del nuevo esquema de Eventos y Tareas
 * para la Declaraci� Informativa de Precios de Transferencia Individual
 * Proceso Indicio</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: DIAN</p>
 *
 * @author Armando Perea Mora
 * @version $Revision: 5$
 * <pre>
 * $Log[10]:
 *  5    V1.3       1.3.1.0     2/3/09 7:26:55 PM COT  ARMANDO PEREA MORA
 *       Precios de Transferencia
 *  4    EntradaSalida 1.3         2/7/08 5:39:19 PM COT  ARMANDO PEREA MORA
 *       Cambios para fecha de vencimiento y asignacion de tarea
 *  3    EntradaSalida 1.2         2/7/08 5:23:57 PM COT  ARMANDO PEREA MORA
 *       Cambios para fecha de vencimiento de tareas y separacion de tarea
 *  2    EntradaSalida 1.1         2/7/08 12:12:09 PM COT OSCAR ALBERTO ROMERO
 *       RUIZ  Cambio Tarea 120 v 6
 *  1    EntradaSalida 1.0         2/3/08 1:01:50 PM COT  ARMANDO PEREA MORA 
 * $
 * </pre>
 */

public class DHelperExpedienteGeneracion120v7 extends DHelperExpedienteGeneracion120 {
	//~--- Campos Estaticos -

	private static final Logger logger = Logger.getLogger(DHelperExpedienteGeneracion120v7.class);

	//~--- Constructores -

	/**
	 * Constructor p�blico
	 * @param contexto IDContexto
	 */
	public DHelperExpedienteGeneracion120v7(DComandoAplicacion comando) {
		super(comando);
	}

	//~--- Metodos -

	protected void inicializarVariablesInternas() {

		// Alias para constantes de Eventos de Precios de Transferencia
		IDConstantesFlujoPreciosTransferencia ids = null;

		super.inicializarVariablesInternas();

		setIdeTareaDiligenciarDec(new Long(ids.IDE_TAREA_DILIGENCIAR_DECLARACION_INDIVIDUAL_V7));

		setIdeParTareaIdeDocDeclaracion(new Integer(ids.IDE_PAR_TAREA_DOC_DECLARACION_INDIVIDUAL_V7));
		setIdeParTareaNumRepDocDeclaracion(new Integer(ids.IDE_PAR_TAREA_NUM_REP_DOC_DECLARACION_INDIVIDUAL_V7));
		setIdeParTareaIdeDocSolicitud(new Integer(ids.IDE_PAR_TAREA_DOC_SOLICITUD_INDIVIDUAL_V7));
		setIdeParTareaEsRepeticion(new Integer(ids.IDE_PAR_TAREA_ES_REPETICION_INDIVIDUAL_V7));
	}
}
