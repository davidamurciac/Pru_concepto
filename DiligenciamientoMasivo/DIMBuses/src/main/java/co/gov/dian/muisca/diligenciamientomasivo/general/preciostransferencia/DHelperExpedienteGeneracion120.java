/**
 *  Republica de Colombia
 * Copyright (c) 2007 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DHelperExpedienteGeneracion120.java, 8, 2/3/09 7:26:30 PM COT, ARMANDO PEREA MORA$
 */

package co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia;

//~--- Paquetes Requeridos -

import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAplicacion;

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
 * @version $Revision: 8$
 * <pre>
 * $Log[10]:
 *  8    V1.3       1.6.1.0     2/3/09 7:26:30 PM COT  ARMANDO PEREA MORA
 *       Precios de Transferencia
 *  7    EntradaSalida 1.6         2/7/08 5:23:57 PM COT  ARMANDO PEREA MORA
 *       Cambios para fecha de vencimiento de tareas y separacion de tarea
 *  6    EntradaSalida 1.5         2/7/08 12:12:05 PM COT OSCAR ALBERTO ROMERO
 *       RUIZ  Cambio Tarea 120 v 6
 *  5    EntradaSalida 1.4         2/3/08 1:03:11 PM COT  ARMANDO PEREA MORA
 *       Cambios para seleccionar la version correcta de manejador de
 *       expedientes e tareas y eventos de Presentacion formato 120
 *  4    EntradaSalida 1.3         1/21/08 8:55:49 AM COT ARMANDO PEREA MORA
 *       Cambio de nombre por uno más descriptivo de su contenido
 *  3    EntradaSalida 1.2         1/21/08 8:53:33 AM COT ARMANDO PEREA MORA 
 *  2    EntradaSalida 1.1         11/8/07 5:14:10 PM COT ARMANDO PEREA MORA 
 *  1    EntradaSalida 1.0         11/8/07 5:05:41 PM COT ARMANDO PEREA MORA 
 * $
 * </pre>
 */

public abstract class DHelperExpedienteGeneracion120 extends DHelperExpedienteGeneracionDeclPreciosTransf {
	//~--- Constructores -

	/**
	 * Constructor p�blico
	 * @param contexto IDContexto
	 */
	public DHelperExpedienteGeneracion120(DComandoAplicacion comando) {
		super(comando);
	}

	//~--- Metodos -

	protected void inicializarVariablesInternas() {

		// Alias para constantes de Eventos de Precios de Transferencia
		IDConstantesFlujoPreciosTransferencia ids = null;

		setTipoPresentacion("Individual");

		setIdeActSolDiligenciamiento(ids.IDE_ACTIVIDAD_SOLICITUD_DILIGENCIAMIENTO_INDIVIDUALES);
		setIdeDoc(new Long(ids.IDE_DOC_INDIVIDUAL));
		setIdeParIdeDocumento(ids.IDE_PAR_IDE_DOC_INDIVIDUAL);
		setNomParIdeDocumento(ids.NOM_PAR_IDE_DOC_INDIVIDUAL);
		setIdeParFormatoDoc(ids.IDE_PAR_FORMATO_DOC_INDIVIDUAL);
		setNomParFormatoDoc(ids.NOM_PAR_FORMATO_DOC_INDIVIDUAL);
		setIdeParNomFormatoDoc(ids.IDE_PAR_NOM_FORMATO_DOC_INDIVIDUAL);
		setNomParNomFormatoDoc(ids.NOM_PAR_NOM_FORMATO_DOC_INDIVIDUAL);
		setIdeParVerFormatoDoc(ids.IDE_PAR_VERSION_DOC_INDIVIDUAL);
		setNomParVerFormatoDoc(ids.NOM_PAR_VERSION_DOC_INDIVIDUAL);
	}
}
