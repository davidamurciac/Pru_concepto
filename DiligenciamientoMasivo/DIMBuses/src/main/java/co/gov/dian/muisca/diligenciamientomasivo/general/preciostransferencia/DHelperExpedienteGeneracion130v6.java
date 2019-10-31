/**
 *  Republica de Colombia
 * Copyright (c) 2008 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DHelperExpedienteGeneracion130v6.java, 3, 2/3/09 7:28:16 PM COT, ARMANDO PEREA MORA$
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
 * @version $Revision: 3$
 * <pre>
 * $Log[10]:
 *  3    V1.3       1.1.1.0     2/3/09 7:28:16 PM COT  ARMANDO PEREA MORA
 *       Precios de Transferencia
 *  2    EntradaSalida 1.1         2/7/08 5:39:19 PM COT  ARMANDO PEREA MORA
 *       Cambios para fecha de vencimiento y asignacion de tarea
 *  1    EntradaSalida 1.0         2/7/08 12:10:34 PM COT OSCAR ALBERTO ROMERO
 *       RUIZ  
 * $
 * </pre>
 */

public class DHelperExpedienteGeneracion130v6 extends DHelperExpedienteGeneracion130 {
	//~--- Campos Estaticos -

	private static final Logger logger = Logger.getLogger(DHelperExpedienteGeneracion130v6.class);

	//~--- Constructores -

	/**
	 * Constructor p�blico
	 * @param contexto IDContexto
	 */
	public DHelperExpedienteGeneracion130v6(DComandoAplicacion comando) {
		super(comando);
	}

	//~--- Metodos -

	protected void inicializarVariablesInternas() {

		// Alias para constantes de Eventos de Precios de Transferencia
		IDConstantesFlujoPreciosTransferencia ids = null;

		super.inicializarVariablesInternas();

		setIdeTareaDiligenciarDec(new Long(ids.IDE_TAREA_DILIGENCIAR_DECLARACION_CONSOLIDADA_V6));

		setIdeParTareaIdeDocDeclaracion(new Integer(ids.IDE_PAR_TAREA_DOC_DECLARACION_CONSOLIDADA_V6));
		setIdeParTareaNumRepDocDeclaracion(new Integer(ids.IDE_PAR_TAREA_NUM_REP_DOC_DECLARACION_CONSOLIDADA_V6));
		setIdeParTareaIdeDocSolicitud(new Integer(ids.IDE_PAR_TAREA_DOC_SOLICITUD_CONSOLIDADA_V6));
		setIdeParTareaEsRepeticion(new Integer(ids.IDE_PAR_TAREA_ES_REPETICION_CONSOLIDADA_V6));
	}
}
