/**
 *  Republica de Colombia
 * Copyright (c) 2007 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DHelperExpedienteGeneracion130.java, 7, 2/3/09 7:28:12 PM COT, ARMANDO PEREA MORA$
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
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: DIAN</p>
 *
 * @author Armando Perea Mora
 * @version $Revision: 7$
 * <pre>
 * $Log[10]:
 *  7    V1.3       1.5.1.0     2/3/09 7:28:12 PM COT  ARMANDO PEREA MORA
 *       Precios de Transferencia
 *  6    EntradaSalida 1.5         2/7/08 5:23:57 PM COT  ARMANDO PEREA MORA
 *       Cambios para fecha de vencimiento de tareas y separacion de tarea
 *  5    EntradaSalida 1.4         2/7/08 12:12:06 PM COT OSCAR ALBERTO ROMERO
 *       RUIZ  Cambio Tarea 120 v 6
 *  4    EntradaSalida 1.3         1/21/08 8:55:49 AM COT ARMANDO PEREA MORA
 *       Cambio de nombre por uno más descriptivo de su contenido
 *  3    EntradaSalida 1.2         1/21/08 8:53:33 AM COT ARMANDO PEREA MORA 
 *  2    EntradaSalida 1.1         11/8/07 5:14:25 PM COT ARMANDO PEREA MORA 
 *  1    EntradaSalida 1.0         11/8/07 5:05:41 PM COT ARMANDO PEREA MORA 
 * $
 * </pre>
 */

public abstract class DHelperExpedienteGeneracion130 extends DHelperExpedienteGeneracionDeclPreciosTransf {
	//~--- Campos Estaticos -

	private static final Logger logger = Logger.getLogger(DHelperExpedienteGeneracion130.class);

	//~--- Constructores -

	/**
	 * Constructor p�blico
	 * @param contexto IDContexto
	 */
	public DHelperExpedienteGeneracion130(DComandoAplicacion comando) {
		super(comando);
	}

	//~--- Metodos -

	protected void inicializarVariablesInternas() {

		// Alias para constantes de Eventos de Precios de Transferencia
		IDConstantesFlujoPreciosTransferencia ids = null;

		setTipoPresentacion("Consolidada");

		setIdeActSolDiligenciamiento(ids.IDE_ACTIVIDAD_SOLICITUD_DILIGENCIAMIENTO_CONSOLIDADA);
		setIdeDoc(new Long(ids.IDE_DOC_CONSOLIDADA));
		setIdeParIdeDocumento(ids.IDE_PAR_IDE_DOC_CONSOLIDADA);
		setNomParIdeDocumento(ids.NOM_PAR_IDE_DOC_CONSOLIDADA);
		setIdeParFormatoDoc(ids.IDE_PAR_FORMATO_DOC_CONSOLIDADA);
		setNomParFormatoDoc(ids.NOM_PAR_FORMATO_DOC_CONSOLIDADA);
		setIdeParNomFormatoDoc(ids.IDE_PAR_NOM_FORMATO_DOC_CONSOLIDADA);
		setNomParNomFormatoDoc(ids.NOM_PAR_NOM_FORMATO_DOC_CONSOLIDADA);
		setIdeParVerFormatoDoc(ids.IDE_PAR_VERSION_DOC_CONSOLIDADA);
		setNomParVerFormatoDoc(ids.NOM_PAR_VERSION_DOC_CONSOLIDADA);
	}
}
