/**
 *  Republica de Colombia
 * Copyright (c) 2007 Direcciï¿½n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DCmdAccTareaDilig120VencidaImpl.java, 12, 2/16/09 10:04:04 AM COT, EDGARDO GARCIA OCAMP$
 */

package co.gov.dian.muisca.diligenciamientomasivo.acciones.transferencias.impl;

//Paquetes referenciados
import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaAttTO;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvConsLstDocGenExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvActDocGenExogena;
import java.util.Collection;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaTO;
import co.gov.dian.muisca.entradasalida.formatos.IDModosDiligenciamiento;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaPersonaNegTO;
import java.util.Iterator;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvCambiarModoNegocioDoc;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.transferencias.DCmdAccTareaDilig120Vencida;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracion120v7;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracion120v8;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracion120v9;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracion120;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracion120v6;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Comando de Acciï¿½n para el Manejo del Vencimiento de Tarea de
 * Diligenciamiento de Declaracion de Precios de Transferencia Individual
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: DIAN
 * </p>
 * 
 * @author Armando Perea Mora
 * @version $Revision: 12$
 * 
 *          <pre>
 * $Log[10]:
 *  12   V1.3       1.8.1.2     2/16/09 10:04:04 AM COTEDGARDO GARCIA OCAMP
 *       Cambios para formato 120  version 9
 *       
 *  11   V1.3       1.8.1.1     2/3/09 8:48:53 PM COT  ARMANDO PEREA MORA
 *       Precios de Transferencia
 *  10   V1.3       1.8.1.0     2/3/09 7:23:24 PM COT  ARMANDO PEREA MORA
 *       Precios de Transferencia
 *  9    EntradaSalida 1.8         2/7/08 5:59:21 PM COT  ARMANDO PEREA MORA
 *       Cambios en la seleccion del helper de expedientes
 *  8    EntradaSalida 1.7         2/3/08 1:03:11 PM COT  ARMANDO PEREA MORA
 *       Cambios para seleccionar la version correcta de manejador de
 *       expedientes e tareas y eventos de Presentacion formato 120
 *  7    EntradaSalida 1.6         1/21/08 9:25:25 AM COT ARMANDO PEREA MORA
 *       Cambio de nombre por uno mas descriptivo con respecto a sus funciones
 *  6    EntradaSalida 1.5         1/21/08 9:25:25 AM COT ARMANDO PEREA MORA 
 *  5    EntradaSalida 1.4         1/17/08 2:50:31 PM COT ARMANDO PEREA MORA Se
 *        elimina constante inoperante VERSION_FORMATO
 *  4    EntradaSalida 1.3         12/13/07 12:03:15 PM COTRAUL BELTRÁN BELTRÁN
 *         Modificaciones para SegmentaciÃ³n.
 *  3    EntradaSalida 1.2         11/8/07 5:25:50 PM COT ARMANDO PEREA MORA Se
 *        cambio la clase DCmdSrvActEstadosIndicadoresDoc por
 *       DCmdSrvCambiarModoNegocioDoc para la anulación del documento
 * $
 * </pre>
 */
public class DCmdAccTareaDilig120VencidaImpl extends
		DCmdAccTareaDilig120Vencida {

	private DHelperExpedienteGeneracion120 manejadorExpedientes = null;
	private Integer versionFormato120 = null;

	/**
	 * Ejecuta el comando de Acciï¿½n.
	 * 
	 * @throws DExcepcion
	 *             Si ocurre algï¿½n error al realizar la creaciï¿½n de Relaciï¿½n
	 *             Materia Proceso Indicio
	 */
	protected void ejecutarComando() {
		try {
			versionFormato120 = null;
			anularDocumentoDeclaracion(getTareaPersonaTo());

			if (versionFormato120 == null) {
				throw new DExcepcion(
						"Error",
						"No fue posible encontrar la "
								+ "version del formato 120 para determinar el manejador de "
								+ "expedientes de tareas");
			}
			DHelperExpedienteGeneracion120 manejador = getManejadorExpedientes(versionFormato120
					.intValue());
			if (manejador == null) {
				throw new DExcepcion(
						"Error",
						"No fue posible encontrar el "
								+ "manejador de Expediente para el formato 120 version "
								+ versionFormato120);
			}
			manejador.procesarCancelacionVencimientoTarea(getTareaPersonaTo());
			isOk = true;
		} catch (DExcepcion ex) {
			mensajeError = ex.getMessage();
			mensajeErrorDetallado = ex.getMensajeDetallado();
			isOk = false;
		}
	}

	private DHelperExpedienteGeneracion120 getManejadorExpedientes(int version) {
		if (version == 6) {
			manejadorExpedientes = new DHelperExpedienteGeneracion120v6(this);
		} else if (version == 7) {
			manejadorExpedientes = new DHelperExpedienteGeneracion120v7(this);
		} else if (version == 8) {
			manejadorExpedientes = new DHelperExpedienteGeneracion120v8(this);
		} else if (version == 9) {
			manejadorExpedientes = new DHelperExpedienteGeneracion120v9(this);
		}
		return manejadorExpedientes;
	}

	/**
	 * Anula el documento de Declaracion
	 * 
	 * @param tareaPersonaNegTo
	 *            DTareaPersonaNegTO
	 * @throws DExcepcion
	 */
	private void anularDocumentoDeclaracion(DTareaPersonaNegTO tareaPersonaNegTo)
			throws DExcepcion {

		DDocGenExogenaAttTO docGenExogenaAtt = new DDocGenExogenaAttTO();
		docGenExogenaAtt.setIdeDocumentoTarea(tareaPersonaNegTo.getPk()
				.getIdeDocumentoTarea());
		docGenExogenaAtt.setNumRepeticionDocTarea(tareaPersonaNegTo.getPk()
				.getNumRepeticionDocTarea());

		DCmdSrvConsLstDocGenExogena consLstGenExogena = (DCmdSrvConsLstDocGenExogena) getServicio("entradasalida.exogena.DCmdSrvConsLstDocGenExogena");
		consLstGenExogena.inicializarPorTarea(docGenExogenaAtt);
		consLstGenExogena.ejecutar();

		Collection docsGenerados = consLstGenExogena
				.getColeccionDocGenExogena();

		if (docsGenerados != null) {
			Iterator iter = docsGenerados.iterator();
			while (iter.hasNext()) {
				DDocGenExogenaTO docGenExogenaTo = (DDocGenExogenaTO) iter
						.next();

				// Determinar el numero de la version del formato
				versionFormato120 = docGenExogenaTo.getAtt().getVersion();
				// Actualizar el estado del documento
				docGenExogenaTo.getAtt().setCodEstado(
						new Byte((byte) IDDocumento.IDE_ESTADO_FUERA_TERMINO));

				DCmdSrvActDocGenExogena actGenExogena = (DCmdSrvActDocGenExogena) getServicio("entradasalida.exogena.DCmdSrvActDocGenExogena");
				actGenExogena.inicializar(docGenExogenaTo);
				actGenExogena.ejecutar();

				// Actualizar Modo de Negocio
				DDocumentoPKTO documentoPK = new DDocumentoPKTO(docGenExogenaTo
						.getPk().getIdeDocumentoGen(), docGenExogenaTo.getPk()
						.getNumRepeticion());
				DCmdSrvCambiarModoNegocioDoc cmdCambiarModoNegocioDoc = (DCmdSrvCambiarModoNegocioDoc) getServicio("entradasalida.documentos.DCmdSrvCambiarModoNegocioDoc");
				// Documentos Temporal solamente
				cmdCambiarModoNegocioDoc.inicializarActModoNegocioDocEs(
						documentoPK, new Integer(
								IDModosDiligenciamiento.MODO_ANULADO),
						docGenExogenaTo.getAtt().getIdeFormato().intValue());
				cmdCambiarModoNegocioDoc.ejecutar();
			}
		}
	}
}
