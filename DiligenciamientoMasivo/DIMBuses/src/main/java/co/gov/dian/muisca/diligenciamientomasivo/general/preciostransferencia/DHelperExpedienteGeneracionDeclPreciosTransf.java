/**
 *  Republica de Colombia
 * Copyright (c) 2007 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DHelperExpedienteGeneracionDeclPreciosTransf.java, 25, 2/5/09 12:25:50 PM COT, EDGARDO GARCIA OCAMP$
 */

package co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia;

//~--- Paquetes Requeridos -

import co.gov.dian.muisca.arquitectura.general.constantes.IDTareasNeg;
import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioAttTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioRolPKTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioRolTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DParamTareaNegAttTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DParamTareaNegPKTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DParamTareaNegTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaNegAttTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaNegPKTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaNegTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaPersonaNegAttTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaPersonaNegPKTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaPersonaNegTO;
import co.gov.dian.muisca.arquitectura.helper.DTareasNegHelper;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAplicacion;
import co.gov.dian.muisca.arquitectura.servicios.seguridad.DCmdSrvConsUsuarioRol;
import co.gov.dian.muisca.arquitectura.servicios.seguridad.DCmdSrvConsUsuarios;
import co.gov.dian.muisca.arquitectura.servicios.tareas.DCmdSrvConsLstTareaNeg;
import co.gov.dian.muisca.arquitectura.util.DFechaUtils;
import co.gov.dian.muisca.cargamasiva.constantes.IDEstadosCircuitoCargaMasiva;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.formatos.IDFormato;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.servicios.DCmdSrvConsFormato;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsSolicitudIngreso;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsIdentificadorDoc;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.transferencias.DCmdSrvCrearDocumento20008;
import co.gov.dian.muisca.gestionexpediente.general.constantes.IDConstantesEventos;
import co.gov.dian.muisca.gestionexpediente.general.constantes.IDConstantesExpedientes;
import co.gov.dian.muisca.gestionexpediente.general.helper.DCrearEventosHelper;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DDocEventoAttTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DDocEventoPKTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DDocEventoTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DEventoPKTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DEventoTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DParametroEventoAttTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DParametroEventoPKTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DParametroEventoTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DPersonaEventoTO;
import co.gov.dian.muisca.gestionexpediente.general.to.expediente.DExpedientePKTO;
import co.gov.dian.muisca.gestionexpediente.general.to.expediente.DExpedienteTO;
import co.gov.dian.muisca.gestionexpediente.general.to.expediente.DParamExpedienteAttTO;
import co.gov.dian.muisca.gestionexpediente.general.to.expediente.DParamExpedientePKTO;
import co.gov.dian.muisca.gestionexpediente.general.to.expediente.DParamExpedienteTO;
import co.gov.dian.muisca.gestionexpediente.servicios.DCmdSrvConsExpediente;
import co.gov.dian.muisca.rut.general.to.DMascaraRutTO;
import co.gov.dian.muisca.rut.general.to.DPersonaRutTO;
import co.gov.dian.muisca.rut.general.to.DRegistroRutPKTO;
import co.gov.dian.muisca.rut.general.to.DRegistroRutTO;
import co.gov.dian.muisca.rut.general.to.inteligenciacorporativa.DPlazosPresentaDeclaPKTO;
import co.gov.dian.muisca.rut.general.to.inteligenciacorporativa.DPlazosPresentaDeclaTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsMascaraRut;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsPersonaRut;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsRegistroRut;
import co.gov.dian.muisca.rut.servicios.liquidacion.DCmdSrvConsPlazosPresentaDecla;

import org.apache.log4j.Logger;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

//~--- Clases -

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto que reune la funcionalidad similar para el manejo
 * del nuevo esquema de Eventos y Tareas para la Declaraci�n Informativa
 * de Precios de Transferencia</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: DIAN</p>
 *
 * @author Armando Perea Mora
 * @version $Revision: 25$
 * <pre>
 * $Log[10]:
 *  25   V1.3       1.18.1.3.1.12/5/09 12:25:50 PM COT EDGARDO GARCIA OCAMP se
 *       agrego la solicitud a la creacion del expendiente
 *  24   V1.3       1.18.1.3.1.02/3/09 7:28:26 PM COT  ARMANDO PEREA MORA
 *       Precios de Transferencia
 *  23   V1.3       1.18.1.3    2/2/09 10:01:09 PM COT EDGARDO GARCIA OCAMP Se
 *       agrega el contexto
 *  22   V1.3       1.18.1.2    2/2/09 6:54:09 PM COT  EDGARDO GARCIA OCAMP
 *       logger
 *  21   V1.3       1.18.1.1    2/2/09 6:36:38 PM COT  EDGARDO GARCIA OCAMP
 *       logger
 *  20   V1.3       1.18.1.0    2/1/09 12:04:10 PM COT DIEGO HERNANDO MAHECHA
 *       Se envía todo el contexto en vez del contexto de seguridad
 *  19   EntradaSalida 1.18        2/19/08 6:29:35 PM COT ARMANDO PEREA MORA
 *       Cambios Menores
 *  18   EntradaSalida 1.17        2/14/08 6:13:42 PM COT ARMANDO PEREA MORA
 *       Correccion de mensajes de error
 *  17   EntradaSalida 1.16        2/11/08 10:36:10 AM COTARMANDO PEREA MORA El
 *        evento de inconsistencia no es unico
 *  16   EntradaSalida 1.15        2/7/08 5:27:06 PM COT  ARMANDO PEREA MORA
 *       Cambios para fecha de vencimiento de tareas y separacion de tarea
 * $
 * </pre>
 */
public class DHelperExpedienteGeneracionDeclPreciosTransf {
	//~--- Campos Estaticos -

	private static final Logger logger = Logger.getLogger(DHelperExpedienteGeneracionDeclPreciosTransf.class);

	// Constantes
	protected static final int IDE_FORMATO_TAREA = 20008;
	protected static final int IDE_FORMATO_SOLICITUD = 10006;

	//~--- Campos -

	private DComandoAplicacion comando;

	// Helpers
	private DCrearEventosHelper creadorEventos = null;
	private DTareasNegHelper creadorTareas = null;

	// Tipo de Presentacion
	private String tipoPresentacion;

	// Tipo de Presentacion
	private String nombreDocumento;

	// Identificador de la Actividad de Solicitud de Diligenciamiento
	private String ideActSolDiligenciamiento;

	// Identificador del Documento
	private Long ideDoc;

	// Identificador Parametro Identificador del Documento
	private String ideParIdeDocumento;

	// nombre Parametro Identificador del Documento
	private String nomParIdeDocumento;

	// Identificador Parametro Formato del Documento
	private String ideParFormatoDoc;

	// Nombre Parametro Formato del Documento
	private String nomParFormatoDoc;

	// Identificador Parametro Nombre Formato del Documento
	private String ideParNomFormatoDoc;

	// Nombre Parametro Nombre Formato del Documento
	private String nomParNomFormatoDoc;

	// Identificador Parametro Version del Formato del Documento
	private String ideParVerFormatoDoc;

	// Nombre Parametro Version Parametro del Documento
	private String nomParVerFormatoDoc;

	// Identificador de la Tarea para el diligenciamiento del Documento
	private Long ideTareaDiligenciarDec;

	// Identificador del Parametro de Tarea Ide Documento Declaracion
	private Integer ideParTareaIdeDocDeclaracion;

	// Identificador del Parametro de Tarea N�mero de Repetici�n Documento
	// Declaracion
	private Integer ideParTareaNumRepDocDeclaracion;

	// Identificador del Parametro de Tarea Ide Documento Solicitud
	private Integer ideParTareaIdeDocSolicitud;

	// Identificador del Parametro de Tarea Ide Documento Solicitud
	private Integer ideParTareaEsRepeticion;

	// Tarea existe
	private boolean tareaDiligenciarDocumentoExiste = false;

	//~--- Constructores -

	/**
	 * Constructor p�blico
	 * @param contexto IDContexto
	 */
	public DHelperExpedienteGeneracionDeclPreciosTransf(DComandoAplicacion comando) {
		this.comando = comando;
		inicializarVariablesInternas();
	}

	//~--- Metodos -

	/**
	 * Activa el expediente
	 *
	 * @param expedienteTo DExpedienteTO
	 * @throws DExcepcion
	 * @throws Exception
	 */
	public void activarExpediente(DExpedienteTO expedienteTo) throws DExcepcion, Exception {
		verificarExpedienteAsignado(expedienteTo, "para ser activado");

		expedienteTo.getAtt().setCodEstado(IDConstantesExpedientes.ESTADO_ACTIVO);
		getCreadorEventos().actualizarExpediente(expedienteTo);
		logger.info("Se activo el Asunto " + expedienteTo.getPk().getIdeExpediente());
	}

	/**
	 * Cierra el expediente cerrando todas sus tareas
	 *
	 * @param expedienteTo DExpedienteTO
	 * @throws DExcepcion
	 * @throws Exception
	 */
	public void cerrarExpediente(DExpedienteTO expedienteTo) throws DExcepcion, Exception {
		verificarExpedienteAsignado(expedienteTo, "para ser cerrado");
		cerrarTodasTareasExpediente(expedienteTo);
		cerrarTodosEventosExpediente(expedienteTo);

		expedienteTo.getAtt().setCodEstado(IDConstantesExpedientes.ESTADO_CERRADO);
		getCreadorEventos().actualizarExpediente(expedienteTo);
		logger.info("Se cerro el Asunto " + expedienteTo.getPk().getIdeExpediente());
	}

	/**
	 * Cierra el expediente cerrando todas sus tareas a partir de un objeto
	 * de tranferencia Tarea Persona
	 * a DExcepciones
	 * @throws DExcepcion
	 */
	public void cerrarExpediente(DTareaPersonaNegTO tareaPersonaTO) throws DExcepcion, Exception {

		DExpedienteTO expedienteToPreciosTrans = getExpedienteTo(tareaPersonaTO.getAtt().getIdeExpediente());
		verificarExpedienteAsignado(expedienteToPreciosTrans, "para cerrar Asunto");
		cerrarExpediente(expedienteToPreciosTrans);
	}

	/**
	 * Cierra el expediente a partir del expediente de la solicitud o el documento
	 *
	 * @throws DExcepcion
	 */
	public void cerrarExpediente(DDocumentoPKTO documentoPkTo) throws DExcepcion {

		try {
			DExpedienteTO expedienteTo = getExpendienteToPreciosTransfDocumento(documentoPkTo);
			cerrarExpediente(expedienteTo);
		}
		catch (Exception e) {
			logger.info(e.getMessage());
			throw new DExcepcion(e.getMessage(), e.getMessage());
		}

	}

	/**
	 * Cierra todas las tareas de un expediente. Requiere que se halla asignado
	 * un expediente
	 */
	private void cerrarTodasTareasExpediente(DExpedienteTO expedienteTo) throws DExcepcion {
		verificarExpedienteAsignado(expedienteTo, "para cerrar sus tareas");

		Collection tareas = getTareasExpediente(new Long(expedienteTo.getPk().getIdeExpediente()));

		if (tareas != null) {
			for (Iterator iterTareas = tareas.iterator(); iterTareas.hasNext(); ) {
				DTareaNegTO tarea = (DTareaNegTO) iterTareas.next();

				// Finalizar la tarea y todos sus repartos

				getCreadorTareas().finalizarTareaYRepartos(tarea.getPk());
			}
		}
	}

	/**
	 * Cierra todas los eventos de un expediente. Requiere que se halla asignado
	 * un expediente
	 */
	private void cerrarTodosEventosExpediente(DExpedienteTO expedienteTo) throws DExcepcion, Exception {
		getCreadorEventos().actualizarEstadoEventoPorExpediente(expedienteTo,
			IDConstantesExpedientes.ESTADO_CERRADO);
	}

	/**
	 * Obtiene un objeto de transferencia Solicitud de Ingreso a partir de un
	 * documento
	 *
	 * @param idSolicitud Long
	 * @return DSolicitudIngresoTO
	 * @throws DExcepcion
	 */
	private DSolicitudIngresoTO consultarSolicitud(Long idSolicitud) throws DExcepcion {
		DCmdSrvConsSolicitudIngreso srvConsSol = (DCmdSrvConsSolicitudIngreso) comando.getServicio(
			"cargamasiva.procesamiento.DCmdSrvConsSolicitudIngreso");
		srvConsSol.inicializar(new DSolicitudIngresoPKTO(idSolicitud));
		srvConsSol.ejecutar();
		return srvConsSol.getSolicitudIngreso();
	}

	/**
	 * Crea el evento de Cancelacion por Vencimiento de Tarea
	 * @throws DExcepcion
	 * @throws Exception
	 */
	private DEventoPKTO crearEventoCancelacionPorVencimientoTarea(DTareaPersonaNegTO tareaPersonaTO,
		DExpedienteTO expedienteToPreciosTrans) throws DExcepcion, Exception {
		DEventoPKTO eventoPkTo = null;

		// VERIFICACION INTERNA
		// Verificar que objetos necesarios esten listos
		verificarTareaPersona(tareaPersonaTO, "para cancelaci�n");

		// Obtener identificador del Documento de Tarea
		DIdentificadorDoc idenDocTarea = getIdentificadorDocMuisca(tareaPersonaTO.getPk().getIdeDocumentoTarea(),
			new Integer(IDE_FORMATO_TAREA));
		String ideActividadUsuario =
			IDConstantesFlujoPreciosTransferencia.IDE_ACTIVIDAD_CANCELAR_POR_VENCIMIENTO_TAREA;

		// BUSCAR EXPEDIENTE
		// Como no es el primer evento del circuito buscar el expediente
		if (expedienteToPreciosTrans == null) {
			expedienteToPreciosTrans = getExpedienteTo(tareaPersonaTO.getAtt().getIdeExpediente());
		}

		// Este evento puede existir cuantas veces se quiera. Pero nada m�s
		// deber�a ser una vez ya que la tarea se crea nada m�s una vez

		verificarExpedienteAsignado(expedienteToPreciosTrans,
			"para el evento de Cancelaci�n por Vencimiento de Tarea");

		// CONFIGURAR EVENTO
		// Crear colleccion de documentos para el evento
		Collection documentosEvento = new ArrayList();

		DDocEventoPKTO ePK = new DDocEventoPKTO();
		ePK.setIdeExpediente(tareaPersonaTO.getAtt().getIdeExpediente());
		ePK.setIdeDocumento(tareaPersonaTO.getPk().getIdeDocumentoTarea());
		ePK.setNumRepeticion(tareaPersonaTO.getPk().getNumRepeticionDocTarea());

		DDocEventoAttTO eAtt = new DDocEventoAttTO();
		eAtt.setIdeFormato(idenDocTarea.getIdFormato());
		eAtt.setNumVersionFormato(idenDocTarea.getVersion());
		eAtt.setIdeTipoDocActividadUsuario(IDConstantesFlujoPreciosTransferencia.IDE_DOC_TAREA);

		DDocEventoTO documentoEvento = new DDocEventoTO(ePK, eAtt);

		documentosEvento.add(documentoEvento);

		// Crear coleccion de personas por evento
		Collection personasEvento = new ArrayList();

		DPersonaEventoTO personaTo = getCreadorEventos().getPersonaEventoPorIdDocumento(idenDocTarea, 1);
		personaTo.getAtt().setIndImplicado(IDConstantesExpedientes.IND_RESPONSABLE);
		personasEvento.add(personaTo);

		Collection parametrosEvento = new ArrayList();

		// Definicion de los parametros
		DParametroEventoPKTO pPk = null;
		DParametroEventoAttTO pAtt = null;

		// Parametro Ide Expediente Cancelaci�n
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_EXPEDIENTE_CANCELACION);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_EXPEDIENTE_CANCELACION);
		pAtt.setValParametro("" + expedienteToPreciosTrans.getPk().getIdeExpediente());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro Tipo de Declaracion
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_IDE_DOC_TAREA_VENCIDA);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_IDE_DOC_TAREA_VENCIDA);
		pAtt.setValParametro("" + tareaPersonaTO.getPk().getIdeDocumentoTarea());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro Ide Documento Gesti�n Masiva
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_NUM_REPET_DOC_TAREA_VENCIDA);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_NUM_REPET_DOC_TAREA_VENCIDA);
		pAtt.setValParametro("" + tareaPersonaTO.getPk().getNumRepeticionDocTarea());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Asignar otras variables necesarias
		boolean esUnico = true;

		String descripcion = "Se cancelo la tarea, Ide Doc Tarea " +
			tareaPersonaTO.getPk().getIdeDocumentoTarea() + " Num. Rep. " +
			tareaPersonaTO.getPk().getNumRepeticionDocTarea() + " Asunto " +
			expedienteToPreciosTrans.getPk().getIdeExpediente();

		// CREAR EL EVENTO
		// Antes de crear el evento nuevo ir al expediente y cerrar todos los
		// eventos del expediente para que el nuevo quede como el �nico activo
		cerrarTodosEventosExpediente(expedienteToPreciosTrans);

		eventoPkTo = getCreadorEventos().adicionarEvento(expedienteToPreciosTrans, ideActividadUsuario,
			descripcion, documentosEvento, personasEvento, parametrosEvento,
			tareaPersonaTO.getAtt().getIdeOrganizacion().intValue(),
			tareaPersonaTO.getAtt().getIdeLugar().intValue(), esUnico);

		logger.info("Se creo un nuevo evento de Cancelacion Por Vencimiento de Tarea " + getNombreDocumento() +
			". Asunto: " + eventoPkTo.getIdeExpediente() + " Ide del evento: " + eventoPkTo.getIdeEvento());

		return eventoPkTo;
	}

	/**
	 * Crea el evento de Informar Inconsistencia
	 * @throws DExcepcion
	 * @throws Exception
	 */
	private DEventoPKTO crearEventoInformarInconsistencia(String tipoError,
		Long ideDocumentoCarga) throws DExcepcion, Exception {
		DEventoPKTO eventoPkTo = null;

		// VERIFICACION INTERNA
		DSolicitudIngresoTO solicitud = consultarSolicitud(ideDocumentoCarga);

		// Verificar que objetos necesarios esten listos
		verificarSolicitud(solicitud);

		// OBTENCION DE VARIABLES NECESARIAS
		// Identificador de la Empresa que realiza la solicitud
		Integer ideOrganizacion = solicitud.getSolicitudAtt().getIdeOrganizacion();

		// Obtener la unidad Administrativa y el lugar a partir de la
		// persona que ha enviado la solicitud
		Long idePersonaCreacion = solicitud.getSolicitudAtt().getIdePersona();

		// Objeto de transferencia de usuario de la Persona Interesada
		DUsuarioTO toUsuarioInteresado = getUsuarioTo(idePersonaCreacion);

		// Obtener algun rol de la Persona Interesada para enviar la tarea
		// al remitente
		DUsuarioRolTO usuarioRol = getPrimerRolUsuario(toUsuarioInteresado.getUsuarioPK().getId(),
			ideOrganizacion);

		Integer ideUnidadAdministrativa = usuarioRol.getUsuarioRolPK().getIdeUnidadAdministrativa();
		Integer ideLugar = usuarioRol.getUsuarioRolPK().getIdeLugar();

		// Obtener identificador del Documento de Solicitud de carga
		DIdentificadorDoc idenDocCarga = getIdentificadorDocCarga(ideDocumentoCarga);

		DDocumentoPKTO documentoCargaPkTo = new DDocumentoPKTO();
		documentoCargaPkTo.setIdeDocumento(new Long(idenDocCarga.getIdDocumento()));
		documentoCargaPkTo.setNumRepeticion(new Integer(idenDocCarga.getNumRepeticion()));

		IDFormato formatoDocCarga = getFormato(idenDocCarga.getIdFormato(), idenDocCarga.getVersion());

		String ideActividadUsuario = IDConstantesFlujoPreciosTransferencia.IDE_ACTIVIDAD_INFORMAR_INCONSISTENCIA;

		// BUSCAR EXPEDIENTE
		// Buscar expediente. Si no lo encuentra crearlo
		DExpedienteTO expedienteToPreciosTrans = getExpendienteToPreciosTransfDocumento(documentoCargaPkTo);

		// Este evento puede existir cuantas veces se quiera ya que se pueden
		// presentar inconsistencias en cualquier momento
		if (expedienteToPreciosTrans != null) {
			logger.info("Se encontro ya creado el Asunto de " + getNombreDocumento() + ". Ide: " +
				expedienteToPreciosTrans.getPk().getIdeExpediente());
		}
		else {
			expedienteToPreciosTrans = crearExpedientePreciosTransferencia(ideUnidadAdministrativa,
				documentoCargaPkTo);
		}

		verificarExpedienteAsignado(expedienteToPreciosTrans, "para el evento Informar Inconsistencia");

		// CONFIGURAR EVENTO
		// Crear colleccion de documentos para el evento
		Collection documentosEvento = new ArrayList();

		DDocEventoPKTO ePK = new DDocEventoPKTO();
		ePK.setIdeExpediente(new Long(expedienteToPreciosTrans.getPk().getIdeExpediente()));
		ePK.setIdeDocumento(documentoCargaPkTo.getIdeDocumento());
		ePK.setNumRepeticion(documentoCargaPkTo.getNumRepeticion());

		DDocEventoAttTO eAtt = new DDocEventoAttTO();

		eAtt.setIdeFormato(idenDocCarga.getIdFormato());
		eAtt.setNumVersionFormato(idenDocCarga.getVersion());
		eAtt.setIdeTipoDocActividadUsuario(IDConstantesFlujoPreciosTransferencia.IDE_DOC_CARGA_MASIVA);

		DDocEventoTO documentoEvento = new DDocEventoTO(ePK, eAtt);

		documentosEvento.add(documentoEvento);

		// Crear coleccion de personas por evento
		Collection personasEvento = new ArrayList();

		DPersonaEventoTO personaTo = getCreadorEventos().getPersonaEventoPorIdDocumento(idenDocCarga, 1);
		personaTo.getAtt().setIndImplicado(IDConstantesExpedientes.IND_RESPONSABLE);
		personasEvento.add(personaTo);

		Collection parametrosEvento = new ArrayList();

		// Definicion de los parametros
		DParametroEventoPKTO pPk = null;
		DParametroEventoAttTO pAtt = null;

		// Parametro Tipo de Error
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_IDE_TIPO_ERROR_INCONSISTENCIA);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_IDE_TIPO_ERROR_INCONSISTENCIA);
		pAtt.setValParametro(tipoError);
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro Tipo de Declaracion
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_IDE_TIPO_DECLARACION_INCONSISTENCIA);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_IDE_TIPO_DECLARACION_INCONSISTENCIA);
		pAtt.setValParametro(getTipoPresentacion());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro Ide Documento Gesti�n Masiva
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_IDE_DOC_SOLICITUD_INCONSISTENCIA);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_IDE_DOC_SOLICITUD_INCONSISTENCIA);
		pAtt.setValParametro("" + documentoCargaPkTo.getIdeDocumento());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro Ide Formato Documento Gesti�n Masiva
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_FORMATO_DOC_SOLICITUD_INCONSISTENCIA);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_FORMATO_DOC_SOLICITUD_INCONSISTENCIA);
		pAtt.setValParametro("" + solicitud.getSolicitudAtt().getIdeFormato());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro Nombre Formato Documento Gesti�n Masiva
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(
			IDConstantesFlujoPreciosTransferencia.IDE_PAR_NOM_FORMATO_DOC_SOLICITUD_INCONSISTENCIA);
		pAtt.setNomParametro(
			IDConstantesFlujoPreciosTransferencia.NOM_PAR_NOM_FORMATO_DOC_SOLICITUD_INCONSISTENCIA);
		pAtt.setValParametro("" + formatoDocCarga.getNombre());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro Versi�n Formato Documento Gesti�n Masiva
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_VERSION_DOC_SOLICITUD_INCONSISTENCIA);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_VERSION_DOC_SOLICITUD_INCONSISTENCIA);
		pAtt.setValParametro("" + solicitud.getSolicitudAtt().getNumVersionFormato());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro A�o Vigencia Documento Gesti�n Masiva
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_VIGENCIA_DOC_SOLICITUD_INCONSISTENCIA);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_VIGENCIA_DOC_SOLICITUD_INCONSISTENCIA);
		pAtt.setValParametro("" + solicitud.getSolicitudAtt().getAnioVigencia());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Asignar otras variables necesarias
		boolean esUnico = false;

		String descripcion = "Se presento una inconsistencia , documento " +
			documentoCargaPkTo.getIdeDocumento() + " Asunto " + expedienteToPreciosTrans.getPk().getIdeExpediente();

		// CREAR EL EVENTO
		// Antes de crear el evento nuevo ir al expediente y cerrar todos los
		// eventos del expediente para que el nuevo quede como el �nico activo
		cerrarTodosEventosExpediente(expedienteToPreciosTrans);

		eventoPkTo = getCreadorEventos().adicionarEvento(expedienteToPreciosTrans, ideActividadUsuario,
			descripcion, documentosEvento, personasEvento, parametrosEvento, ideOrganizacion.intValue(),
			ideLugar.intValue(), esUnico);

		logger.info("Se creo un nuevo evento de Informaci�n de Inconsistencia " + getNombreDocumento() +
			". Asunto: " + eventoPkTo.getIdeExpediente() + " Ide del evento: " + eventoPkTo.getIdeEvento());

		return eventoPkTo;
	}

	/**
	 * Crea el evento de Solicitud de Diligenciamiento
	 * @throws DExcepcion
	 * @throws Exception
	 */
	private DEventoPKTO crearEventoPresentarDocumento(Long ideDocumentoCarga, Long ideDocumentoPresentado,
		Integer ideFormatoDocumentoPresentado) throws DExcepcion, Exception {
		DEventoPKTO eventoPkTo = null;

		// VERIFICACION INTERNA
		DSolicitudIngresoTO solicitud = consultarSolicitud(ideDocumentoCarga);

		// Ya debe estar como documento muisca
		DIdentificadorDoc identificadorDoc = getIdentificadorDocMuisca(ideDocumentoPresentado,
			ideFormatoDocumentoPresentado);

		// Verificar que objetos necesarios esten listos
		verificarIdentificadorDocumento(identificadorDoc);
		verificarSolicitud(solicitud);

		// OBTENCION DE VARIABLES NECESARIAS
		// Identificador de la Empresa que realiza la solicitud
		Integer ideOrganizacion = solicitud.getSolicitudAtt().getIdeOrganizacion();

		// Obtener la unidad Administrativa y el lugar a partir de la
		// persona que ha enviado la solicitud
		Long idePersonaCreacion = solicitud.getSolicitudAtt().getIdePersona();

		// Objeto de transferencia de usuario de la Persona Interesada
		DUsuarioTO toUsuarioInteresado = getUsuarioTo(idePersonaCreacion);

		// Obtener algun rol de la Persona Interesada para enviar la tarea
		// al remitente
		DUsuarioRolTO usuarioRol = getPrimerRolUsuario(toUsuarioInteresado.getUsuarioPK().getId(),
			ideOrganizacion);

		// Obtener identificador del Documento de Solicitud de carga
		DIdentificadorDoc idenDocCarga = getIdentificadorDocCarga(ideDocumentoCarga);

		DDocumentoPKTO documentoCargaPkTo = new DDocumentoPKTO();
		documentoCargaPkTo.setIdeDocumento(new Long(idenDocCarga.getIdDocumento()));
		documentoCargaPkTo.setNumRepeticion(new Integer(idenDocCarga.getNumRepeticion()));

		Integer ideLugar = usuarioRol.getUsuarioRolPK().getIdeLugar();

		String ideActividadUsuario = getIdeActSolDiligenciamiento();

		IDFormato formatoDocumento = getFormato(identificadorDoc.getIdFormato(), identificadorDoc.getVersion());

		// BUSCAR EXPEDIENTE
		// Como no es el primer evento del circuito buscar el expediente
		DExpedienteTO expedienteToPreciosTrans = getExpendienteToPreciosTransfDocumento(documentoCargaPkTo);

		if (expedienteToPreciosTrans == null) {
			throw new DExcepcion("Expediente No Encontrado",
				"No fue posible encontrar el expediente de precios de Transferencia para el documento " +
				documentoCargaPkTo.getIdeDocumento());
		}

		// Si no es nulo verifique que este evento no exista ya que es �nico
		if (expedienteToPreciosTrans != null) {
			DEventoTO eventoToExistente = getPrimerTipoActividadEnExpediente(ideActividadUsuario,
				expedienteToPreciosTrans);
			if ((eventoToExistente != null) && (eventoToExistente.getPk() != null)) {
				eventoPkTo = eventoToExistente.getPk();
				logger.info("Ya se habia creado el evento de la actividad Solicitud de " + "Diligenciamiento Ide: " +
					eventoPkTo.getIdeEvento() + ". Asunto: " + eventoPkTo.getIdeExpediente());
				return eventoPkTo;
			}
		}

		verificarExpedienteAsignado(expedienteToPreciosTrans, "para el evento Presentar Documento Declaracion");

		// CONFIGURAR EVENTO
		// Crear colleccion de documentos para el evento
		Collection documentosEvento = new ArrayList();

		DDocEventoPKTO ePK = new DDocEventoPKTO();
		ePK.setIdeExpediente(new Long(expedienteToPreciosTrans.getPk().getIdeExpediente()));
		ePK.setIdeDocumento(new Long(identificadorDoc.getIdDocumento()));
		ePK.setNumRepeticion(new Integer(identificadorDoc.getNumRepeticion()));

		DDocEventoAttTO eAtt = new DDocEventoAttTO();

		eAtt.setIdeFormato(identificadorDoc.getIdFormato());
		eAtt.setNumVersionFormato(identificadorDoc.getVersion());
		eAtt.setIdeTipoDocActividadUsuario(getIdeDoc().longValue());

		DDocEventoTO documentoEvento = new DDocEventoTO(ePK, eAtt);

		documentosEvento.add(documentoEvento);

		// Crear coleccion de personas por evento
		Collection personasEvento = new ArrayList();

		DPersonaEventoTO personaTo = getCreadorEventos().getPersonaEventoPorIdDocumento(identificadorDoc, 1);
		personaTo.getAtt().setIndImplicado(IDConstantesExpedientes.IND_RESPONSABLE);
		personasEvento.add(personaTo);

		Collection parametrosEvento = new ArrayList();

		// Definicion de los parametros
		DParametroEventoPKTO pPk = null;
		DParametroEventoAttTO pAtt = null;

		// Parametro Ide Documento
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(getIdeParIdeDocumento());
		pAtt.setNomParametro(getNomParIdeDocumento());
		pAtt.setValParametro("" + identificadorDoc.getIdDocumento());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro Nombre Formato Documento Gesti�n Masiva
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(getIdeParNomFormatoDoc());
		pAtt.setNomParametro(getNomParNomFormatoDoc());
		pAtt.setValParametro("" + formatoDocumento.getNombre());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro Versi�n Formato Documento
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(getIdeParFormatoDoc());
		pAtt.setNomParametro(getNomParFormatoDoc());
		pAtt.setValParametro("" + identificadorDoc.getIdFormato());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro Ide Formato Documento
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(getIdeParVerFormatoDoc());
		pAtt.setNomParametro(getNomParVerFormatoDoc());
		pAtt.setValParametro("" + identificadorDoc.getVersion());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Asignar otras variables necesarias
		boolean esUnico = true;

		String descripcion = "Se solicito el diligenciamiento del formato " + getNombreDocumento() + ", " +
			"documento " + identificadorDoc.getIdDocumento() + " Asunto " +
			expedienteToPreciosTrans.getPk().getIdeExpediente();

		// CREAR EL EVENTO
		// Antes de crear el evento nuevo ir al expediente y cerrar todos los
		// eventos del expediente para que el nuevo quede como el �nico activo
		cerrarTodosEventosExpediente(expedienteToPreciosTrans);

		eventoPkTo = getCreadorEventos().adicionarEvento(expedienteToPreciosTrans, ideActividadUsuario,
			descripcion, documentosEvento, personasEvento, parametrosEvento, ideOrganizacion.intValue(),
			ideLugar.intValue(), esUnico);

		logger.info("Se creo un nuevo evento de Solicitud de Diligenciamiento de " + getNombreDocumento() +
			". Asunto: " + eventoPkTo.getIdeExpediente() + " Ide del evento: " + eventoPkTo.getIdeEvento());

		return eventoPkTo;
	}

	/**
	 * Crea el evento de Presentar env�o de Archivos
	 * @throws DExcepcion
	 * @throws Exception
	 */
	private DEventoPKTO crearEventoPresentarEnvioArchivos(Long ideDocumentoCarga) throws DExcepcion, Exception {
		DEventoPKTO eventoPkTo = null;

		// VERIFICACION INTERNA
		DSolicitudIngresoTO solicitud = consultarSolicitud(ideDocumentoCarga);

		// Verificar que objetos necesarios esten listos
		verificarSolicitud(solicitud);

		// OBTENCION DE VARIABLES NECESARIAS

		// Identificador de la Empresa que realiza la solicitud
		Integer ideOrganizacion = solicitud.getSolicitudAtt().getIdeOrganizacion();

		// Obtener la unidad Administrativa y el lugar a partir de la
		// persona que ha enviado la solicitud
		Long idePersonaCreacion = solicitud.getSolicitudAtt().getIdePersona();

		// Objeto de transferencia de usuario de la Persona Interesada
		DUsuarioTO toUsuarioInteresado = getUsuarioTo(idePersonaCreacion);

		// Obtener algun rol de la Persona Interesada para enviar la tarea
		// al remitente
		DUsuarioRolTO usuarioRol = getPrimerRolUsuario(toUsuarioInteresado.getUsuarioPK().getId(),
			ideOrganizacion);

		Integer ideUnidadAdministrativa = usuarioRol.getUsuarioRolPK().getIdeUnidadAdministrativa();
		Integer ideLugar = usuarioRol.getUsuarioRolPK().getIdeLugar();

		// Obtener identificador del Documento de Solicitud de carga
		DIdentificadorDoc idenDocCarga = getIdentificadorDocCarga(solicitud.getSolicitudPK().getIdeSolicitud());

		DDocumentoPKTO documentoCargaPkTo = new DDocumentoPKTO();
		documentoCargaPkTo.setIdeDocumento(new Long(idenDocCarga.getIdDocumento()));
		documentoCargaPkTo.setNumRepeticion(new Integer(idenDocCarga.getNumRepeticion()));

		IDFormato formatoDocCarga = getFormato(idenDocCarga.getIdFormato(), idenDocCarga.getVersion());

		String ideActividadUsuario = IDConstantesFlujoPreciosTransferencia.IDE_ACTIVIDAD_PRESENTACION_INFORMACION;

		// BUSCAR EXPEDIENTE
		// Como es el primer evento del circuito crear el expediente
		DExpedienteTO expedienteToPreciosTrans = getExpendienteToPreciosTransfDocumento(documentoCargaPkTo);

		// Si no es nulo verifique que este evento no exista ya que es �nico
		if (expedienteToPreciosTrans != null) {
			logger.info("Se encontro ya creado el Asunto de " + getNombreDocumento() + ". Ide: " +
				expedienteToPreciosTrans.getPk().getIdeExpediente());

			DEventoTO eventoToExistente = getPrimerTipoActividadEnExpediente(ideActividadUsuario,
				expedienteToPreciosTrans);
			if ((eventoToExistente != null) && (eventoToExistente.getPk() != null)) {
				eventoPkTo = eventoToExistente.getPk();
				logger.info("Ya se habia creado el evento de la actividad Presentacion " + "Informacion Ide: " +
					eventoPkTo.getIdeEvento() + ". Asunto: " + eventoPkTo.getIdeExpediente());
				return eventoPkTo;
			}
		}
		else {
			expedienteToPreciosTrans = crearExpedientePreciosTransferencia(ideUnidadAdministrativa,
				documentoCargaPkTo);
		}

		verificarExpedienteAsignado(expedienteToPreciosTrans,
			"para el evento de Presentaci�n de Envio de Archivos");

		// CONFIGURAR EVENTO
		// Crear colleccion de documentos para el evento
		Collection documentosEvento = new ArrayList();

		DDocEventoPKTO ePK = new DDocEventoPKTO();
		ePK.setIdeExpediente(new Long(expedienteToPreciosTrans.getPk().getIdeExpediente()));
		ePK.setIdeDocumento(new Long(idenDocCarga.getIdDocumento()));
		ePK.setNumRepeticion(new Integer(idenDocCarga.getNumRepeticion()));

		DDocEventoAttTO eAtt = new DDocEventoAttTO();

		eAtt.setIdeFormato(idenDocCarga.getIdFormato());
		eAtt.setNumVersionFormato(idenDocCarga.getVersion());
		eAtt.setIdeTipoDocActividadUsuario(IDConstantesFlujoPreciosTransferencia.IDE_DOC_SOLICITUD);

		DDocEventoTO documentoEvento = new DDocEventoTO(ePK, eAtt);

		documentosEvento.add(documentoEvento);

		// Crear coleccion de personas por evento
		Collection personasEvento = new ArrayList();

		DPersonaEventoTO personaTo = getCreadorEventos().getPersonaEventoPorIdDocumento(idenDocCarga, 1);
		personaTo.getAtt().setIndImplicado(IDConstantesExpedientes.IND_RESPONSABLE);
		personasEvento.add(personaTo);

		Collection parametrosEvento = new ArrayList();

		// Definicion de los parametros
		DParametroEventoPKTO pPk = null;
		DParametroEventoAttTO pAtt = null;

		// Parametro Ide Documento Gesti�n Masiva
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_IDE_DOC_SOLICITUD);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_IDE_DOC_SOLICITUD);
		pAtt.setValParametro("" + documentoCargaPkTo.getIdeDocumento());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro Ide Formato Documento Gesti�n Masiva
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_FORMATO_DOC_SOLICITUD);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_FORMATO_DOC_SOLICITUD);
		pAtt.setValParametro("" + solicitud.getSolicitudAtt().getIdeFormato());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro Nombre Formato Documento Gesti�n Masiva
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_NOM_FORMATO_DOC_SOLICITUD);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_NOM_FORMATO_DOC_SOLICITUD);
		pAtt.setValParametro("" + formatoDocCarga.getNombre());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro Versi�n Formato Documento Gesti�n Masiva
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_VERSION_DOC_SOLICITUD);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_VERSION_DOC_SOLICITUD);
		pAtt.setValParametro("" + solicitud.getSolicitudAtt().getNumVersionFormato());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Parametro A�o Vigencia Documento Gesti�n Masiva
		pPk = new DParametroEventoPKTO();
		pAtt = new DParametroEventoAttTO();
		pPk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PAR_VIGENCIA_DOC_SOLICITUD);
		pAtt.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PAR_VIGENCIA_DOC_SOLICITUD);
		pAtt.setValParametro("" + solicitud.getSolicitudAtt().getAnioVigencia());
		parametrosEvento.add(new DParametroEventoTO(pPk, pAtt));

		// Asignar otras variables necesarias
		boolean esUnico = true;

		String descripcion = "Se presenta la informaci�n inicial, documento " +
			documentoCargaPkTo.getIdeDocumento() + " Asunto " + expedienteToPreciosTrans.getPk().getIdeExpediente();

		// CREAR EL EVENTO
		// Antes de crear el evento nuevo ir al expediente y cerrar todos los
		// eventos del expediente para que el nuevo quede como el �nico activo
		cerrarTodosEventosExpediente(expedienteToPreciosTrans);

		eventoPkTo = getCreadorEventos().adicionarEvento(expedienteToPreciosTrans, ideActividadUsuario,
			descripcion, documentosEvento, personasEvento, parametrosEvento, ideOrganizacion.intValue(),
			ideLugar.intValue(), esUnico);

		logger.info("Se creo un nuevo evento de Recibo de Informaci�n " + getNombreDocumento() + ". Asunto: " +
			eventoPkTo.getIdeExpediente() + " Ide del evento: " + eventoPkTo.getIdeEvento());

		return eventoPkTo;
	}

	/**
	 * Crea un expediente del circuito de Precios de transferencia
	 * @param ideUnidadAdministrativa Integer
	 * @return DExpedienteTO
	 * @throws DExcepcion
	 */
	private DExpedienteTO crearExpedientePreciosTransferencia(Integer ideUnidadAdministrativa,
		DDocumentoPKTO documentoCargaPK) throws DExcepcion {
		DExpedienteTO expedienteTo = null;

		// Parametros Expediente
		Collection parametrosExpediente = new ArrayList();

		DParamExpedienteTO to;
		DParamExpedientePKTO pk;
		DParamExpedienteAttTO att;

		// Parametro Tipo de Transferencia
		pk = new DParamExpedientePKTO();
		att = new DParamExpedienteAttTO();
		to = new DParamExpedienteTO(pk, att);
		pk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PARAM_TIPO_TRANSFERENCIA);
		att.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PARAM_TIPO_TRANSFERENCIA);
		att.setValParametro("" + getTipoPresentacion());
		parametrosExpediente.add(to);

		// Parametro N[umero de documento
		pk = new DParamExpedientePKTO();
		att = new DParamExpedienteAttTO();
		to = new DParamExpedienteTO(pk, att);
		pk.setIdeParamDef(IDConstantesFlujoPreciosTransferencia.IDE_PARAM_NUM_DOCU);
		att.setNomParametro(IDConstantesFlujoPreciosTransferencia.NOM_PARAM_NUM_DOCU);
		att.setValParametro("" + documentoCargaPK.getIdeDocumento());
		parametrosExpediente.add(to);

		try {
			expedienteTo = getCreadorEventos().crearExpediente(documentoCargaPK,
				IDConstantesFlujoPreciosTransferencia.IDE_FLUJO_PRECIOS_TRANFERENCIA, ideUnidadAdministrativa,
				parametrosExpediente, 0l,IDE_FORMATO_SOLICITUD);

			// Crear la jerarquia de expediente

			DExpedienteTO expedientePadre = getExpendienteToDocumento(documentoCargaPK,
				IDEstadosCircuitoCargaMasiva.ID_FLUJO_CARGA_MASIVA_PRECIOS_TRANSFERENCIA);
			if (expedientePadre != null) {
				getCreadorEventos().crearJerarquiaExpediente(expedientePadre.getPk().getIdeExpediente(),
					expedienteTo.getPk().getIdeExpediente(), IDConstantesEventos.TIP_JERARQUIA_DERIVA);
			}
		}
		catch (Exception e) {
			throw new DExcepcion(e);
		}
		logger.info("Se creo el Asunto " + expedienteTo.getPk().getIdeExpediente() + " de Precios " +
			"de Transferencia " + getTipoPresentacion());

		return expedienteTo;
	}

	/**
	 * Crea la tarea Diligenciar Documento
	 * @throws DExcepcion
	 * @throws Exception
	 */
	private DTareaNegTO crearTareaDiligenciarDocumento(DEventoPKTO eventoPkTo, Long ideDocumentoCarga,
		IDDocumento documento, Date fechaSolicitud) throws DExcepcion, Exception {
		DTareaNegTO tareaNegTo = null;

		if (tareaNegTo == null) {
			logger.info("Se entro a crear Tarea Diligenciar Documento");
		}

		// VERIFICACION INTERNA
		DSolicitudIngresoTO solicitud = consultarSolicitud(ideDocumentoCarga);

		// Verificar que objetos necesarios esten listos
		verificarDocumento(documento);

		// El documento es borrador y debe estar como documento Buzon

		DIdentificadorDoc identificadorDoc = getIdentificadorDocTemporal(new Long(documento.getId()),
			new Integer(documento.getIdFormato()));
		verificarIdentificadorDocumento(identificadorDoc);
		verificarSolicitud(solicitud);

		// OBTENCION DE VARIABLES NECESARIAS
		// Fecha de Vencimiento de la Tarea
		int fechaVencimientoTarea = getFechaVencimientoTarea(fechaSolicitud);

		// Identificador de la Empresa que realiza la solicitud
		Integer ideOrganizacion = solicitud.getSolicitudAtt().getIdeOrganizacion();

		// Obtener la unidad Administrativa y el lugar a partir de la
		// persona que ha enviado la solicitud
		Long idePersonaCreacion = solicitud.getSolicitudAtt().getIdePersona();

		// Objeto de transferencia de usuario de la Persona Interesada
		DUsuarioTO toUsuarioInteresado = getUsuarioTo(idePersonaCreacion);

		// Obtener algun rol de la Persona Interesada para enviar la tarea
		// al remitente
		DUsuarioRolTO usuarioRol = getPrimerRolUsuario(toUsuarioInteresado.getUsuarioPK().getId(),
			ideOrganizacion);

		// Obtener identificador del Documento de Solicitud de carga
		DIdentificadorDoc idenDocCarga = getIdentificadorDocCarga(ideDocumentoCarga);

		DDocumentoPKTO documentoCargaPkTo = new DDocumentoPKTO();
		documentoCargaPkTo.setIdeDocumento(new Long(idenDocCarga.getIdDocumento()));
		documentoCargaPkTo.setNumRepeticion(new Integer(idenDocCarga.getNumRepeticion()));

		// BUSCAR EXPEDIENTE
		DExpedienteTO expedienteToPreciosTrans = getExpedienteTo(eventoPkTo.getIdeExpediente());

		if (expedienteToPreciosTrans == null) {
			throw new DExcepcion("Expediente No Encontrado",
				"No fue posible encontrar el expediente de precios de Transferencia para el documento " +
				documentoCargaPkTo.getIdeDocumento());
		}

		// Si no es nulo el expediente verifique que esta tarea no exista ya
		// que es �nica
		if (expedienteToPreciosTrans != null) {
			tareaDiligenciarDocumentoExiste = false;

			DTareaNegTO tareaToExistente = getPrimerTipoTareaEnExpediente(getIdeTareaDiligenciarDec(),
				new Long(expedienteToPreciosTrans.getPk().getIdeExpediente()));
			if (tareaToExistente != null) {
				tareaDiligenciarDocumentoExiste = true;
				logger.info("Ya se habia creado la tarea con " + " Ide de Tarea: " +
					tareaToExistente.getPk().getIdeDocumentoTarea() + " de Diligenciamiento de " +
					getNombreDocumento() + ". Asunto: " + tareaToExistente.getAtt().getIdeExpediente() +
					" Ide de Evento: " + tareaToExistente.getAtt().getIdeEvento());
				return tareaToExistente;
			}
		}

		verificarExpedienteAsignado(expedienteToPreciosTrans,
			"para la tarea Diligenciar Documento de Declaraci�n");

		// CREAR EL DOCUMENTO DE LA TAREA
		DCmdSrvCrearDocumento20008 cmdCrearDocTar = (DCmdSrvCrearDocumento20008) comando.getServicio(
			"diligenciamientomasivo.transferencias.DCmdSrvCrearDocumento20008");
		cmdCrearDocTar.inicializar(solicitud, toUsuarioInteresado, usuarioRol, documento);
		cmdCrearDocTar.ejecutar();

		Long numDocTarea = cmdCrearDocTar.getNumeroDocumento();
		Integer numRepeticionDocTarea = cmdCrearDocTar.getNumeroRepeticion();

		// CONFIGURAR TAREA
		DUsuarioRolPKTO pkUsuarioRol = usuarioRol.getUsuarioRolPK();

		DTareaNegPKTO tPk = new DTareaNegPKTO();
		tPk.setIdeDocumentoTarea(numDocTarea);
		tPk.setNumRepeticionDocTarea(numRepeticionDocTarea);

		DTareaNegAttTO tAtt = new DTareaNegAttTO();
		tAtt.setCodEstado(IDTareasNeg.COD_ESTADO_TAREA_ACTIVA);
		tAtt.setFecCreacion(new Long(DFechaUtils.getFechaHoraNegocio(new Date())));
		tAtt.setFecFinalPrevista(new Long(99991231L));
		tAtt.setIdePersonaCreacion(idePersonaCreacion);
		tAtt.setIdeRol(pkUsuarioRol.getIdeRol());
		tAtt.setIdeOrganizacion(ideOrganizacion);
		tAtt.setIdeLugar(pkUsuarioRol.getIdeLugar());
		tAtt.setIdeEstablecimiento(pkUsuarioRol.getIdeEstablecimiento());
		tAtt.setIdeTipoOrganizacion(pkUsuarioRol.getIdeTipoOrganizacion());
		tAtt.setIdeUnidadAdm(pkUsuarioRol.getIdeUnidadAdministrativa());
		tAtt.setIdeTareaDefNeg(getIdeTareaDiligenciarDec());
		tAtt.setIdeExpediente(new Long(expedienteToPreciosTrans.getPk().getIdeExpediente()));
		tAtt.setIdeEvento(eventoPkTo.getIdeEvento());

		DTareaNegTO toTarea = new DTareaNegTO(tPk, tAtt);

		// Definicion de los parametros
		List parametrosTarea = new ArrayList();

		DParamTareaNegPKTO pPk = null;
		DParamTareaNegAttTO pAtt = null;

		// Parametro Ide Documento Declaracion
		pPk = new DParamTareaNegPKTO();
		pPk.setIdeDocumentoTarea(toTarea.getPk().getIdeDocumentoTarea());
		pPk.setNumRepDocTarea(toTarea.getPk().getNumRepeticionDocTarea());
		pPk.setIdeParamTareaNeg(getIdeParTareaIdeDocDeclaracion());

		pAtt = new DParamTareaNegAttTO();
		pAtt.setIdeTareaDefNeg(toTarea.getAtt().getIdeTareaDefNeg());
		pAtt.setIdeParamTareaDefNeg(getIdeParTareaIdeDocDeclaracion());
		pAtt.setCodTipoDato(IDTareasNeg.TIPO_DATO_NUMERICO);
		pAtt.setValNumerico(new BigDecimal(identificadorDoc.getIdDocumento()));
		parametrosTarea.add(new DParamTareaNegTO(pPk, pAtt));

		// Parametro N�mero de Repeticion Documento Declaracion
		pPk = new DParamTareaNegPKTO();
		pPk.setIdeDocumentoTarea(toTarea.getPk().getIdeDocumentoTarea());
		pPk.setNumRepDocTarea(toTarea.getPk().getNumRepeticionDocTarea());
		pPk.setIdeParamTareaNeg(getIdeParTareaNumRepDocDeclaracion());

		pAtt = new DParamTareaNegAttTO();
		pAtt.setIdeTareaDefNeg(toTarea.getAtt().getIdeTareaDefNeg());
		pAtt.setIdeParamTareaDefNeg(getIdeParTareaNumRepDocDeclaracion());
		pAtt.setCodTipoDato(IDTareasNeg.TIPO_DATO_NUMERICO);
		pAtt.setValNumerico(new BigDecimal(identificadorDoc.getNumRepeticion()));
		parametrosTarea.add(new DParamTareaNegTO(pPk, pAtt));

		// Parametro Ide Documento Solicitud
		pPk = new DParamTareaNegPKTO();
		pPk.setIdeDocumentoTarea(toTarea.getPk().getIdeDocumentoTarea());
		pPk.setNumRepDocTarea(toTarea.getPk().getNumRepeticionDocTarea());
		pPk.setIdeParamTareaNeg(getIdeParTareaIdeDocSolicitud());

		pAtt = new DParamTareaNegAttTO();
		pAtt.setIdeTareaDefNeg(toTarea.getAtt().getIdeTareaDefNeg());
		pAtt.setIdeParamTareaDefNeg(getIdeParTareaIdeDocSolicitud());
		pAtt.setCodTipoDato(IDTareasNeg.TIPO_DATO_NUMERICO);
		pAtt.setValNumerico(new BigDecimal(solicitud.getSolicitudPK().getIdeSolicitud().doubleValue()));
		parametrosTarea.add(new DParamTareaNegTO(pPk, pAtt));

		// Parametro Es Correccion
		pPk = new DParamTareaNegPKTO();
		pPk.setIdeDocumentoTarea(toTarea.getPk().getIdeDocumentoTarea());
		pPk.setNumRepDocTarea(toTarea.getPk().getNumRepeticionDocTarea());
		pPk.setIdeParamTareaNeg(getIdeParTareaEsRepeticion());

		pAtt = new DParamTareaNegAttTO();
		pAtt.setIdeTareaDefNeg(toTarea.getAtt().getIdeTareaDefNeg());
		pAtt.setIdeParamTareaDefNeg(getIdeParTareaEsRepeticion());
		pAtt.setCodTipoDato(IDTareasNeg.TIPO_DATO_CARACTER);
		pAtt.setValCaracter("" +
			((solicitud.getSolicitudAtt().getNumeroDocumentoAnterior() != null) ? "si" : "no"));
		parametrosTarea.add(new DParamTareaNegTO(pPk, pAtt));

		// Crear coleccion de personas por evento
		Collection personasTarea = new ArrayList();

		/*
		 *    // Consultar el usuario con el id de la persona remitente
		 *    Long idPersona = miSolicitud.getSolicitudAtt().getIdePersona();
		 *    Long idTarea = null;
		 *    DUsuarioTO usr = new DUsuarioTO();
		 *    DUsuarioAttTO att = new DUsuarioAttTO();
		 *    att.setIdePersonaRut(idPersona);
		 *
		 *    usr.setUsuarioAtt(att);
		 * DCmdSrvConsUsuarios srvConsUsuarios = (DCmdSrvConsUsuarios) getServicio(
		 *      "arquitectura.seguridad.DCmdSrvConsUsuarios");
		 *    srvConsUsuarios.iniciar(usr);
		 *    srvConsUsuarios.ejecutar();
		 * Long idUsuario = srvConsUsuarios.getUsuarioTO().getUsuarioPK().getId();
		 *
		 *    // Consultar el usuario y rol para enviar la tarea
		 *    Integer idOrg = miSolicitud.getSolicitudAtt().getIdeOrganizacion();
		 * DCmdSrvConsUsuarioRol srvUsuario = (DCmdSrvConsUsuarioRol) getServicio(
		 *      "arquitectura.seguridad.DCmdSrvConsUsuarioRol");
		 *    DUsuarioRolPKTO pkUsuario = new DUsuarioRolPKTO();
		 *    pkUsuario.setIdeUsuario(idUsuario);
		 *    pkUsuario.setIdeOrganizacion(idOrg);
		 *    srvUsuario.inicializarConsultaGenericaPorPK(pkUsuario);
		 *    srvUsuario.ejecutar();
		 *    Collection usuarios = srvUsuario.getRolesUsuario();
		 *    if (usuarios != null && !usuarios.isEmpty()) {
		 *      DUsuarioRolTO usuario = (DUsuarioRolTO) usuarios.iterator().next();
		 *      pkUsuario = usuario.getUsuarioRolPK();
		 */

		DTareaPersonaNegPKTO perPk = new DTareaPersonaNegPKTO();
		perPk.setIdeDocumentoTarea(toTarea.getPk().getIdeDocumentoTarea());
		perPk.setNumRepeticionDocTarea(toTarea.getPk().getNumRepeticionDocTarea());

		DTareaPersonaNegAttTO perAtt = new DTareaPersonaNegAttTO();
		perAtt.setCodEstado(IDTareasNeg.COD_ESTADO_TAREA_ACTIVA);

		// Determinar a quien se asigna la tarea
		if (idenDocCarga.getIdOrganizacion() == 2) {
			perAtt.setIdePersonaRut(idePersonaCreacion);
		}
		else {
			if (idenDocCarga.getIdPerOrganizacion() > 0) {
				perAtt.setIdePersonaRut(new Long(idenDocCarga.getIdPerOrganizacion()));
			}
			else {
				throw new DExcepcion("Error",
					"Para la asignacion de Tarea se encontro Ide Organizacion igual a " +
					idenDocCarga.getIdOrganizacion() + " pero Ide Persona " + "Organizacion igual a " +
					idenDocCarga.getIdPerOrganizacion());
			}
		}

		perAtt.setIdeOrganizacion(toTarea.getAtt().getIdeOrganizacion());
		perAtt.setIdeTipoOrganizacion(toTarea.getAtt().getIdeTipoOrganizacion());
		perAtt.setIdeLugar(toTarea.getAtt().getIdeLugar());
		perAtt.setIdeEstablecimiento(toTarea.getAtt().getIdeEstablecimiento());
		perAtt.setIdeUnidadAdm(toTarea.getAtt().getIdeUnidadAdm());
		perAtt.setIdeRol(toTarea.getAtt().getIdeRol());
		perAtt.setIdeTareaDefNeg(toTarea.getAtt().getIdeTareaDefNeg());
		perAtt.setFecFinalPrevista(new Long(fechaVencimientoTarea));

		DTareaPersonaNegTO persona = new DTareaPersonaNegTO(perPk, perAtt);
		personasTarea.add(persona);

		/*
		 * DTareaAttTO tarea = new DTareaAttTO(idUsuario,
		 *   idOrg, pkUsuario.getIdeLugar(),
		 *   pkUsuario.getIdeTipoOrganizacion(),
		 *   pkUsuario.getIdeUnidadAdministrativa(),
		 *   pkUsuario.getIdeRol(),
		 * new Integer(PARAM_IDE_TAREA), // cambiar por el tipo de tarea creado para 540
		 *   new Character('A'),
		 *   msjNotificacion,
		 *   new Date(System.currentTimeMillis()),
		 *   new Date(System.currentTimeMillis()),
		 *   new Date(System.currentTimeMillis()),
		 *   urlTarea);
		 */

		// Crear el Documento de reparto para la persona interesada
		DIdentificadorDoc idenDocumentoReparto = getCreadorTareas().crearDocumentoReparto(persona,
			"Solicitud de Diligenciamiento de Declaraci�n Informativa " + tipoPresentacion +
			" de Precios de Transferencia");

		// Asignar documento de reparto a la persona en la tarea
		perPk.setIdeDocumentoReparto(new Long(idenDocumentoReparto.getIdDocumento()));
		perPk.setNumRepeticionDocReparto(new Integer(idenDocumentoReparto.getNumRepeticion()));

		getCreadorTareas().crearTareaYReparto(toTarea, parametrosTarea, personasTarea);

		logger.info("Se creo una nueva tarea con " + " Ide de Tarea: " + toTarea.getPk().getIdeDocumentoTarea() +
			" de Diligenciamiento de " + getNombreDocumento() + ". Asunto: " +
			toTarea.getAtt().getIdeExpediente() + " Ide de Evento: " + toTarea.getAtt().getIdeEvento());

		tareaNegTo = toTarea;

		return tareaNegTo;
	}

	protected void inicializarVariablesInternas() {

		// Se implementa en clases que heredan. Se prefiere en vez de hacer la clase abstracta
	}

	/**
	 * Expone el evento de Informar Inconsistencias y convierte las excepciones
	 * a DExcepciones
	 * @throws DExcepcion
	 */
	public void procesarCancelacionVencimientoTarea(DTareaPersonaNegTO tareaPersonaTO) throws DExcepcion {
		final String MSG_ERROR = "Error al procesar Cancelacion por Vencimiento " + "de Tarea del Documento " +
			getNombreDocumento();

		try {

			// anularDocumentoBorrador(tareaPersonaTO);

			// Obtener el Expediente
			DExpedienteTO expedienteToPreciosTrans = getExpedienteTo(tareaPersonaTO.getAtt().getIdeExpediente());
			verificarExpedienteAsignado(expedienteToPreciosTrans,
				"para procesar Cancelaci�n por Vencimiento de Tarea");
			crearEventoCancelacionPorVencimientoTarea(tareaPersonaTO, expedienteToPreciosTrans);
			cerrarExpediente(expedienteToPreciosTrans);

		}
		catch (DExcepcion de) {
			logger.error(de.getMensajeDetallado());
			throw new DExcepcion(de.getMessage(), MSG_ERROR + ". " + de.getMensajeDetallado());

		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new DExcepcion(e.getMessage(), MSG_ERROR + ". " + e.getMessage());
		}

	}

	/**
	 * Expone el evento de Informar Inconsistencias y convierte las excepciones
	 * a DExcepciones
	 * @throws DExcepcion
	 */
	public DEventoPKTO procesarInconsistencia(String tipoError, Long ideDocumentoCarga) throws DExcepcion {
		final String MSG_ERROR = "Error al procesar " + "Inconsistencia del Documento " + getNombreDocumento();
		DEventoPKTO eventoPkTo = null;

		try {
			eventoPkTo = crearEventoInformarInconsistencia(tipoError, ideDocumentoCarga);
			cerrarExpediente(getExpedienteTo(eventoPkTo.getIdeExpediente()));
		}
		catch (DExcepcion de) {
			logger.error(de.getMensajeDetallado());
			throw new DExcepcion(de.getMessage(), MSG_ERROR + ". " + de.getMensajeDetallado());

		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new DExcepcion(e.getMessage(), MSG_ERROR + ". " + e.getMessage());
		}

		return eventoPkTo;
	}

	/**
	 * Expone el evento de Solicitud de Diligenciamiento y convierte las
	 * excepciones a DExcepciones
	 * @throws DExcepcion
	 */
	public DEventoPKTO procesarPresentacionDocumento(Long ideDocumentoCarga, Long ideDocumentoPresentado,
		Integer ideFormatoDocumentoPresentado) throws DExcepcion {
		final String MSG_ERROR = "Error al procesar Presentaci�n de Documento " + getNombreDocumento();
		DEventoPKTO eventoPkTo = null;

		try {
			eventoPkTo = crearEventoPresentarDocumento(ideDocumentoCarga, ideDocumentoPresentado,
				ideFormatoDocumentoPresentado);
			cerrarExpediente(getExpedienteTo(eventoPkTo.getIdeExpediente()));
		}
		catch (DExcepcion de) {
			logger.error(de.getMensajeDetallado());
			throw new DExcepcion(de.getMessage(), MSG_ERROR + ". " + de.getMensajeDetallado());

		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new DExcepcion(e.getMessage(), MSG_ERROR + ". " + e.getMessage());
		}

		return eventoPkTo;
	}

	/**
	 * Expone el evento de Presentar Envio de Archivos y convierte las excepciones
	 * a DExcepciones
	 * @throws DExcepcion
	 */
	public DTareaNegTO procesarPresentacionPorEnvioArchivos(Long ideDocumentoCarga, IDDocumento documento,
		Date fechaSolicitud) throws DExcepcion {
		final String MSG_ERROR = "Error al procesar Presentaci�n por " +
			"Envio de Archivos para el Documento " + getNombreDocumento();
		DTareaNegTO tareaNegTo = null;

		try {
			DEventoPKTO eventoPkTo = crearEventoPresentarEnvioArchivos(ideDocumentoCarga);
			if (eventoPkTo != null) {
				tareaDiligenciarDocumentoExiste = false;
				tareaNegTo = crearTareaDiligenciarDocumento(eventoPkTo, ideDocumentoCarga, documento, fechaSolicitud);
				if (!tareaDiligenciarDocumentoExiste) {
					activarExpediente(getExpedienteTo(eventoPkTo.getIdeExpediente()));
				}
			}
		}
		catch (DExcepcion de) {
			logger.info(de.getMensajeDetallado());
			throw new DExcepcion(de.getMessage(), MSG_ERROR + ". " + de.getMensajeDetallado());

		}
		catch (Exception e) {
			logger.info(e.getMessage());
			throw new DExcepcion(e.getMessage(), MSG_ERROR + ". " + e.getMessage());
		}

		return tareaNegTo;

	}

	public void verificarDocumento(IDDocumento documento) throws DExcepcion {
		if (documento == null) {
			throw new DExcepcion("Documento de Formato 120 nulo",
				"El documento de " + getNombreDocumento() + " es nulo y es requerido");
		}
	}

	private void verificarExpedienteAsignado(DExpedienteTO expedienteTo,
		String mensajeNoExiste) throws DExcepcion {
		if (expedienteTo == null) {
			throw new DExcepcion("No existe el Asunto", "No existe el Asunto " + mensajeNoExiste);
		}
	}

	/**
	 * Verifica que un identificador de documento corresponda con un tipo
	 * de documento, formato y versi�n
	 *
	 * @param idDoc DIdentificadorDoc
	 * @param tipoDocumento String
	 * @param numeroFormato int
	 * @param version int
	 * @throws DExcepcion
	 */
	private void verificarIdentificadorDoc(DIdentificadorDoc idDoc, String tipoDocumento,
		int numeroFormato) throws DExcepcion {

		// Si es nulo y no se puede hallar como documento Muisca
		if (idDoc == null) {
			throw new DExcepcion("Identificador de documento nulo",
				"El identificador de documento de " + tipoDocumento + " es nulo");
		}
		else {
			if (idDoc.getIdFormato() != numeroFormato) {
				throw new DExcepcion("Identificador de documento con numero " + "de formato erroneo",
					"Identificador de documento de " + tipoDocumento + " no es del formato " + numeroFormato);

			}
		}
	}

	public void verificarIdentificadorDocumento(DIdentificadorDoc identificadorDoc) throws DExcepcion {
		if (identificadorDoc == null) {
			throw new DExcepcion("Identificador Documento de Formato 120 nulo",
				"El Identificador del documento de " + getNombreDocumento() + " es nulo y es requerido");
		}
	}

	public void verificarSolicitud(DSolicitudIngresoTO solicitud) throws DExcepcion {
		if (solicitud == null) {
			throw new DExcepcion("Solicitud nula",
				"La solicitud es nula y es necesaria para procesar " + "el Asunto");
		}
	}

	private void verificarTareaPersona(DTareaPersonaNegTO tareaPersonaTo,
		String mensajeNoExiste) throws DExcepcion {
		if (tareaPersonaTo == null) {
			throw new DExcepcion("No existe la tarea Persona", "No existe la tarea Persona " + mensajeNoExiste);
		}
	}

	//~--- get methods -

	/**
	 * Obtiene un objeto DCrearEventosHelper y lo crea si no existe
	 */
	public DCrearEventosHelper getCreadorEventos() throws DExcepcion {
		if (creadorEventos == null) {
			creadorEventos = new DCrearEventosHelper(comando.getContexto());
		}
		return creadorEventos;
	}

	/**
	 * Obtiene un objeto DTareasNegHelper y lo crea si no existe
	 */
	public DTareasNegHelper getCreadorTareas() throws DExcepcion {
		if (creadorTareas == null) {
			creadorTareas = new DTareasNegHelper(comando.getContexto().getContextoSeguridad());
		}
		return creadorTareas;
	}

	/**
	 * Obtiene un objeto de transferencia de un Expediente
	 *
	 * @param numeroDocumento Long
	 * @throws Exception
	 * @return DExpedienteTO
	 */
	public DExpedienteTO getExpedienteTo(Long ideExpediente) throws Exception {
		DExpedientePKTO expedientePk = new DExpedientePKTO();
		expedientePk.setIdeExpediente(ideExpediente.longValue());

		DCmdSrvConsExpediente srvConsExpedientes =
			(DCmdSrvConsExpediente) comando.getServicio("gestionexpediente.DCmdSrvConsExpediente");
		srvConsExpedientes.inicializar(expedientePk);
		srvConsExpedientes.ejecutar();

		return srvConsExpedientes.getExpediente();
	}

	/**
	 * Obtiene el objeto de transferencia de un Expediente a partir de un
	 * n�mero de documento
	 * @param numeroDocumento Long
	 * @param ideFlujo String
	 * @return DExpedienteTO
	 * @throws Exception
	 */
	private DExpedienteTO getExpendienteToDocumento(DDocumentoPKTO documentoPkTo,
		String ideFlujo) throws Exception {
		return getCreadorEventos().consultarExpedienteDocumento(documentoPkTo, ideFlujo);
	}

	/**
	 * Obtiene el objeto de transferencia de un Expediente del flujo de
	 * precios de transferencia a partir de un n�mero de documento
	 * @param numeroDocumento Long
	 * @param ideFlujo String
	 * @return DExpedienteTO
	 * @throws Exception
	 */
	public DExpedienteTO getExpendienteToPreciosTransfDocumento(DDocumentoPKTO documentoPkTo) throws Exception {
		if (documentoPkTo != null) {
			logger.info("Buscando Expediente de Precios de Transferencia " + documentoPkTo.getIdeDocumento() +
				" Rep: " + documentoPkTo.getNumRepeticion());
		}
		return getExpendienteToDocumento(documentoPkTo,
			IDConstantesFlujoPreciosTransferencia.IDE_FLUJO_PRECIOS_TRANFERENCIA);
	}

	private int getFechaLimite(short ano, long nit, int ideFormato, byte versionFormato, byte numPeriodo,
		int tipoDeclarante) throws DExcepcion {
		DPlazosPresentaDeclaPKTO pk = new DPlazosPresentaDeclaPKTO(ano, ideFormato, versionFormato, numPeriodo,
			tipoDeclarante, nit);

		DCmdSrvConsPlazosPresentaDecla servicio =
			(DCmdSrvConsPlazosPresentaDecla) comando.getServicio("rut.liquidacion.DCmdSrvConsPlazosPresentaDecla");
		servicio.inicializarConsPorNit(pk);
		servicio.ejecutar();

		DPlazosPresentaDeclaTO plazosPresentaDecla = servicio.getPlazosPresentaDecla();
		if (plazosPresentaDecla != null) {
			return plazosPresentaDecla.getAtt().getFecLimite();
		}
		else {
			return 0;
		}
	}

	/**
	 * Obtiene la fecha de vencimiento de un formato para una Persona Rut
	 *
	 * @param personaRUT Long
	 * @return DUsuarioRolTO
	 * @throws DExcepcion
	 */
	protected Date getFechaVencimientoTabla(int anoGravable, long nit, int ideFormato,
		int numVersionFormato) throws DExcepcion {
		String operacion = "Obteniendo fecha de Vencimiento de pago desde Tabla";
		byte numPeriodo = 1;
		byte versionFormato = new Byte("" + numVersionFormato).byteValue();
		short ano = new Short("" + anoGravable).shortValue();
		Date result = new Date();
		logger.info(operacion);

		DPersonaRutTO personaRUT = getPersonaRut(nit);
		if (personaRUT == null) {
			logger.info("Persona Rut nulo para nit " + nit + ". " + operacion);
		}
		else {
			DRegistroRutTO registroRut = getRegistroRut(personaRUT);
			if (registroRut == null) {
				logger.info("Registro Rut nulo para nit " + nit + ". " + operacion);
			}
			else {
				Integer idTipoDeclarante = registroRut.getAtt().getCodTipoContribuyente();
				int fechaLimite = getFechaLimite(ano, nit, ideFormato, versionFormato, numPeriodo,
					idTipoDeclarante.intValue());

				logger.info("Se encontro la fecha limite " + fechaLimite + " para a�o gravable " + anoGravable +
					",  nit " + nit + " para el formato " + ideFormato + " version " + numVersionFormato);

				result = DFechaUtils.getFecha(fechaLimite);
			}
		}

		return result;
	}

	protected int getFechaVencimientoTarea(Date fechaSolicitud) throws DExcepcion {
		Date result = new Date();
		if (fechaSolicitud != null) {
			result = fechaSolicitud;
		}
		return DFechaUtils.getFechaNegocio(result);
	}

	/**
	 * Obtiene la Interfaz del Formato de un Formato a partir de su n�mero
	 * y version
	 * @param numeroDocumento Long
	 * @return DIdentificadorDoc
	 * @throws DExcepcion
	 */
	private IDFormato getFormato(int numFormato, int numVersion) throws DExcepcion {
		DCmdSrvConsFormato miComandoFormato =
			(DCmdSrvConsFormato) comando.getServicio("entradasalida.DCmdSrvConsFormato");
		miComandoFormato.inicializar(numFormato, numVersion);
		miComandoFormato.ejecutar();
		return miComandoFormato.getFormatoPorDefecto();
	}

	protected String getIdeActSolDiligenciamiento() {
		return ideActSolDiligenciamiento;
	}

	protected Long getIdeDoc() {
		return ideDoc;
	}

	public String getIdeParFormatoDoc() {
		return ideParFormatoDoc;
	}

	public String getIdeParIdeDocumento() {
		return ideParIdeDocumento;
	}

	public String getIdeParNomFormatoDoc() {
		return ideParNomFormatoDoc;
	}

	public Integer getIdeParTareaEsRepeticion() {
		return ideParTareaEsRepeticion;
	}

	public Integer getIdeParTareaIdeDocDeclaracion() {
		return ideParTareaIdeDocDeclaracion;
	}

	public Integer getIdeParTareaIdeDocSolicitud() {
		return ideParTareaIdeDocSolicitud;
	}

	public Integer getIdeParTareaNumRepDocDeclaracion() {
		return ideParTareaNumRepDocDeclaracion;
	}

	public String getIdeParVerFormatoDoc() {
		return ideParVerFormatoDoc;
	}

	public Long getIdeTareaDiligenciarDec() {
		return ideTareaDiligenciarDec;
	}

	/**
	 * el Identificador de Documento de un Documento a partir de un
	 * numero de documento verificando que es un documento de Gesti�n Masiva
	 * @param numeroDocumento Long
	 * @return DIdentificadorDoc
	 * @throws DExcepcion
	 */
	private DIdentificadorDoc getIdentificadorDocCarga(Long numeroDocumento) throws DExcepcion {
		if (numeroDocumento == null) {
			throw new DExcepcion("El n�mero de documento es nulo",
				"Error al Obtener Documento Gestion Masiva. " + "El n�mero de documento es nulo");
		}

		// Buscar Identificador Documento Gestion Masiva que para el caso ya debe
		// estar como documento Muisca
		DIdentificadorDoc idDoc = getIdentificadorDocMuisca(numeroDocumento, new Integer(IDE_FORMATO_SOLICITUD));
		verificarIdentificadorDoc(idDoc, "Gesti�n Masiva", IDE_FORMATO_SOLICITUD);
		return idDoc;
	}

	/**
	 * Obtiene el Identificador de Documento de un Documento a partir de un
	 * numero de documento
	 * @param numeroDocumento Long
	 * @return DIdentificadorDoc
	 * @throws DExcepcion
	 */
	private DIdentificadorDoc getIdentificadorDocMuisca(Long numeroDocumento,
		Integer ideFormato) throws DExcepcion {
		DCmdSrvConsIdentificadorDoc servicio = (DCmdSrvConsIdentificadorDoc) comando.getServicio(
			"entradasalida.documentos.DCmdSrvConsIdentificadorDoc");

		// Documentos MUISCA solamente
		servicio.inicializarConsPorId(numeroDocumento.longValue(), 1, true, ideFormato.intValue());
		servicio.ejecutar();

		return servicio.getIdentificadorDoc();
	}

	/**
	 * Obtiene el Identificador de Documento de un Documento a partir de un
	 * numero de documento
	 * @param numeroDocumento Long
	 * @return DIdentificadorDoc
	 * @throws DExcepcion
	 */
	private DIdentificadorDoc getIdentificadorDocTemporal(Long numeroDocumento,
		Integer ideFormato) throws DExcepcion {
		DCmdSrvConsIdentificadorDoc servicio = (DCmdSrvConsIdentificadorDoc) comando.getServicio(
			"entradasalida.documentos.DCmdSrvConsIdentificadorDoc");

		// Documentos MUISCA solamente
		servicio.inicializarConsPorId(numeroDocumento.longValue(), 1, false, ideFormato.intValue());
		servicio.ejecutar();

		return servicio.getIdentificadorDoc();
	}

	/**
	 * Obtiene la mascara Rut de una Persona Rut
	 *
	 * @param personaRUT Long
	 * @return DUsuarioRolTO
	 * @throws DExcepcion
	 */
	private DMascaraRutTO getMascaraRut(DPersonaRutTO personaRUT) throws DExcepcion {
		DCmdSrvConsMascaraRut srvConsMascara =
			(DCmdSrvConsMascaraRut) comando.getServicio("rut.DCmdSrvConsMascaraRut");
		srvConsMascara.inicializarConsultarPorIdeMascaraRut(personaRUT.getPK().getIdePersonaRut());
		srvConsMascara.ejecutar();
		return srvConsMascara.getMascaraRut();
	}

	public String getNomParFormatoDoc() {
		return nomParFormatoDoc;
	}

	public String getNomParIdeDocumento() {
		return nomParIdeDocumento;
	}

	public String getNomParNomFormatoDoc() {
		return nomParNomFormatoDoc;
	}

	public String getNomParVerFormatoDoc() {
		return nomParVerFormatoDoc;
	}

	protected String getNombreDocumento() {
		return nombreDocumento;
	}

	/**
	 * Obtiene la persona rut de un nit
	 *
	 * @param nit Long
	 * @return DUsuarioRolTO
	 * @throws DExcepcion
	 */
	private DPersonaRutTO getPersonaRut(long nit) throws DExcepcion {
		DCmdSrvConsPersonaRut srvConsRut =
			(DCmdSrvConsPersonaRut) comando.getServicio("rut.DCmdSrvConsPersonaRut");
		srvConsRut.inicializarConsultarPorNIT(nit);
		srvConsRut.ejecutar();
		return srvConsRut.getPersonaRut();
	}

	/**
	 * Obtiene el primer rol de un usuario en una Organizaci�n
	 * @param ideUsuario Long
	 * @param ideOrganizacion Integer
	 * @return DUsuarioRolTO
	 * @throws DExcepcion
	 */
	private DUsuarioRolTO getPrimerRolUsuario(Long ideUsuario, Integer ideOrganizacion) throws DExcepcion {
		Collection rolesUsuario = getRolesUsuario(ideUsuario, ideOrganizacion);
		if ((rolesUsuario == null) || rolesUsuario.isEmpty()) {
			throw new DExcepcion("No hay roles para el usuario especificado",
				"No se encontraron roles para el usuario " + ideUsuario + " en la organizacion " + ideOrganizacion);
		}
		return (DUsuarioRolTO) rolesUsuario.iterator().next();
	}

	/**
	 * Obtiene el primer eventode un tipo de actividad en un expediente
	 * @param ideTipoTarea Long
	 * @return boolean
	 * @throws DExcepcion
	 */
	private DEventoTO getPrimerTipoActividadEnExpediente(String ideTipoActividad,
		DExpedienteTO expedienteTo) throws DExcepcion, Exception {

		DEventoTO enventoTo = null;

		Collection eventos = getCreadorEventos().consultarEvento(expedienteTo, ideTipoActividad);
		if ((eventos != null) && (eventos.iterator().hasNext())) {
			enventoTo = (DEventoTO) eventos.iterator().next();
		}

		return enventoTo;
	}

	/**
	 * Obtiene la primera tarea de un tipo en un expediente
	 * @param ideTipoTarea Long
	 * @return boolean
	 * @throws DExcepcion
	 */
	private DTareaNegTO getPrimerTipoTareaEnExpediente(Long ideTipoTarea,
		Long ideExpediente) throws DExcepcion {

		DTareaNegTO resultado = null;

		if ((ideTipoTarea != null) && (ideExpediente != null)) {
			Collection tareas = getTareasExpediente(ideExpediente);

			if (tareas != null) {
				Iterator it = tareas.iterator();
				while (it.hasNext()) {
					DTareaNegTO tarea = (DTareaNegTO) it.next();
					if (tarea.getAtt().getIdeTareaDefNeg().equals(ideTipoTarea)) {
						resultado = tarea;
						break;
					}
				}
			}
		}

		return resultado;
	}

	/**
	 * Obtiene el registro Rut de una Persona Rut
	 *
	 * @param personaRUT Long
	 * @return DUsuarioRolTO
	 * @throws DExcepcion
	 */
	private DRegistroRutTO getRegistroRut(DPersonaRutTO personaRUT) throws DExcepcion {
		DRegistroRutTO result = null;

		DMascaraRutTO mascaraRut = getMascaraRut(personaRUT);
		if (mascaraRut != null) {
			Long idePerRut = mascaraRut.getPK().getIdePersonaRut();
			DCmdSrvConsRegistroRut servicio =
				(DCmdSrvConsRegistroRut) comando.getServicio("rut.DCmdSrvConsRegistroRut");
			servicio.inicializarConsultarPorPK(new DRegistroRutPKTO(idePerRut));
			servicio.ejecutar();

			result = servicio.getRegistroRut();
		}
		return result;
	}

	/**
	 * Obtiene la lista de roles de un usuario en una Organizaci�n
	 * @param ideUsuario Long
	 * @param ideOrganizacion Integer
	 * @return Collection
	 * @throws DExcepcion
	 */
	private Collection getRolesUsuario(Long ideUsuario, Integer ideOrganizacion) throws DExcepcion {
		DUsuarioRolPKTO pkUsuarioRol = new DUsuarioRolPKTO();
		pkUsuarioRol.setIdeUsuario(ideUsuario);
		pkUsuarioRol.setIdeOrganizacion(ideOrganizacion);

		DCmdSrvConsUsuarioRol srvUsuario =
			(DCmdSrvConsUsuarioRol) comando.getServicio("arquitectura.seguridad.DCmdSrvConsUsuarioRol");
		srvUsuario.inicializarConsultaGenericaPorPK(pkUsuarioRol);
		srvUsuario.ejecutar();
		return srvUsuario.getRolesUsuario();
	}

	/**
	 * Obtiene las tareas de un expediente
	 * @param ideTipoTarea Long
	 * @return boolean
	 * @throws DExcepcion
	 */
	private Collection getTareasExpediente(Long ideExpediente) throws DExcepcion {

		DTareaNegAttTO tAtt = new DTareaNegAttTO();
		tAtt.setIdeExpediente(ideExpediente);

		DCmdSrvConsLstTareaNeg srvConsTareas =
			(DCmdSrvConsLstTareaNeg) comando.getServicio("arquitectura.tareas.DCmdSrvConsLstTareaNeg");
		srvConsTareas.inicializarConsultaGenerica(new DTareaNegTO(new DTareaNegPKTO(), tAtt));
		srvConsTareas.ejecutar();

		return srvConsTareas.getColeccionTareaNeg();
	}

	protected String getTipoPresentacion() {
		return tipoPresentacion;
	}

	/**
	 * Obtiene un objeto de transferencia Usuario a partir del identificador
	 * de una persona
	 * @param idePersona Long
	 * @return DUsuarioTO
	 * @throws DExcepcion
	 */
	private DUsuarioTO getUsuarioTo(Long idePersona) throws DExcepcion {
		DUsuarioTO toUsuario = new DUsuarioTO();
		DUsuarioAttTO attUsuario = new DUsuarioAttTO();
		attUsuario.setIdePersonaRut(idePersona);
		toUsuario.setUsuarioAtt(attUsuario);

		DCmdSrvConsUsuarios srvConsUsuarios =
			(DCmdSrvConsUsuarios) comando.getServicio("arquitectura.seguridad.DCmdSrvConsUsuarios");
		srvConsUsuarios.iniciar(toUsuario);
		srvConsUsuarios.ejecutar();

		return srvConsUsuarios.getUsuarioTO();
	}

	//~--- set methods

	protected void setIdeActSolDiligenciamiento(String ideActSolDiligenciamiento) {
		this.ideActSolDiligenciamiento = ideActSolDiligenciamiento;
	}

	protected void setIdeDoc(Long ideDoc) {
		this.ideDoc = ideDoc;
	}

	protected void setIdeParFormatoDoc(String ideParFormatoDoc) {
		this.ideParFormatoDoc = ideParFormatoDoc;
	}

	protected void setIdeParIdeDocumento(String ideParIdeDocumento) {
		this.ideParIdeDocumento = ideParIdeDocumento;
	}

	public void setIdeParNomFormatoDoc(String ideParNomFormatoDoc) {
		this.ideParNomFormatoDoc = ideParNomFormatoDoc;
	}

	public void setIdeParTareaEsRepeticion(Integer ideParTareaEsRepeticion) {
		this.ideParTareaEsRepeticion = ideParTareaEsRepeticion;
	}

	public void setIdeParTareaIdeDocDeclaracion(Integer ideParTareaIdeDocDeclaracion) {
		this.ideParTareaIdeDocDeclaracion = ideParTareaIdeDocDeclaracion;
	}

	public void setIdeParTareaIdeDocSolicitud(Integer ideParTareaIdeDocSolicitud) {
		this.ideParTareaIdeDocSolicitud = ideParTareaIdeDocSolicitud;
	}

	public void setIdeParTareaNumRepDocDeclaracion(Integer ideParTareaNumRepDocDeclaracion) {
		this.ideParTareaNumRepDocDeclaracion = ideParTareaNumRepDocDeclaracion;
	}

	protected void setIdeParVerFormatoDoc(String ideParVerFormatoDoc) {
		this.ideParVerFormatoDoc = ideParVerFormatoDoc;
	}

	public void setIdeTareaDiligenciarDec(Long ideTareaDiligenciarDec) {
		this.ideTareaDiligenciarDec = ideTareaDiligenciarDec;
	}

	protected void setNomParFormatoDoc(String nomParFormatoDoc) {
		this.nomParFormatoDoc = nomParFormatoDoc;
	}

	protected void setNomParIdeDocumento(String nomParIdeDocumento) {
		this.nomParIdeDocumento = nomParIdeDocumento;
	}

	public void setNomParNomFormatoDoc(String nomParNomFormatoDoc) {
		this.nomParNomFormatoDoc = nomParNomFormatoDoc;
	}

	protected void setNomParVerFormatoDoc(String nomParVerFormatoDoc) {
		this.nomParVerFormatoDoc = nomParVerFormatoDoc;
	}

	private void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	protected void setTipoPresentacion(String tipoPresentacion) {
		this.tipoPresentacion = tipoPresentacion;
		setNombreDocumento("Declaraci�n Informativa " + tipoPresentacion + " de Precios de Transferencia");
	}
}
