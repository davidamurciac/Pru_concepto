package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.DParametroTareaAttTO;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.DParametroTareaPKTO;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.DParametroTareaTO;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.DTareaAttTO;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.DTareaPKTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioAttTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioRolPKTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioRolTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioTO;
import co.gov.dian.muisca.arquitectura.general.to.tablasbasicas.DOrganizacionPKTO;
import co.gov.dian.muisca.arquitectura.servicios.DCmdSrvCrearTarea;
import co.gov.dian.muisca.arquitectura.servicios.seguridad.DCmdSrvConsUsuarioRol;
import co.gov.dian.muisca.arquitectura.servicios.seguridad.DCmdSrvConsUsuarios;
import co.gov.dian.muisca.arquitectura.servicios.tablasbasicas.DCmdSrvConsCatalogoOrganizaciones;
import co.gov.dian.muisca.arquitectura.web.buses.DBusServiciosEYSDelegateTxNueva;
import co.gov.dian.muisca.cargamasiva.constantes.IDEstadosCircuitoCargaMasiva;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudArchivoTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoTO;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsLstSolicitudArchivo;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsSolicitudIngreso;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia.DCmdAccWFGenerarDecInformativaConsolidadaIndv5;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConceptosNegocioPreciosTransferencia;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConstantesDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracion120v5;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.DCmdSrvConsLstSolicitudDeclaracion;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.DCmdSrvConsSolicitudDeclaracion;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.DCmdSrvCrearSolicitudDeclaracion;
import co.gov.dian.muisca.entradasalida.acciones.consintegral.DCmdAccLlenarDocumentoNuevoConsRut;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDLogDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;
import co.gov.dian.muisca.entradasalida.excepciones.DEntradaSalidaExcepcion;
import co.gov.dian.muisca.entradasalida.formatos.IDFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDModosDiligenciamiento;
import co.gov.dian.muisca.entradasalida.formatos.IDTiposOrigenDeclaracion;
import co.gov.dian.muisca.entradasalida.formatos.constantes.DTipoCorreccion;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.servicios.cargamasiva.DCmdSrvHomologacionPnuts;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoES;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoMUISCA;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentosES;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvCrearDocumentoES;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsMascaraRut;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsPersonaRut;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsRegistroRut;

public class DCmdAccWFGenerarDecInformativaConsolidadaIndv5Impl extends DCmdAccWFGenerarDecInformativaConsolidadaIndv5 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3899696911841580497L;

	/**
	 * 
	 */

	private static final Logger logger = Logger.getLogger(
			DCmdAccWFGenerarDecInformativaConsolidadaIndv5Impl.class);

	//MENSAJES DE ERROR
	private final String ERROR_DOC_INICIAL_EXISTE = "Ya existe una declaracion INICIAL para el año gravable. " + "No es posible presentar la declaración ";
	private final int CONCEPTO_CORRECCION = 2;
	private final int IDE_FORMATO_CARGA = 1125;
	private static final int CASILLA_NUMERO_IDENTIFICACION_TRIBUTARIA = 5;
	private static final int CASILLA_DV = 6;



	//CASILLAS DE NEGOCIO
	private static final int AMPLITUD_INTERVALO = 100;
	protected static final int GRUPO = 1;
	protected static final int OCURRENCIA = 1;
	protected static final int CASILLA_CONCEPTO = 2;

	private static final int IDE_FORMATO = 120;
	private static final int NUM_VERSION_FORMATO = 5;

	//------------------------CASILLAS FORMATO 1125 ----------------------//
	private static final int CASILLA_60_1125 = 60;
	private static final int CASILLA_62_1125 = 62;
	private static final int CASILLA_63_1125 = 63;



	//--------------------PARAMETROS PARA LA TAREA----------------------//
	/**
	 * @todo cambiar por la url correcta
	 */
	static final String URL = "/WebDiligenciamientomasivo/DefPreciosTransIndivPopUpv5.faces";

	private final String NOM_PARAM_ID_DOCUMENTO_DECLARACION = "idDocumentoDeclaracion";
	private final String NOM_PARAM_NUM_REPETICION_DECLARACION = "numRepeticionDeclaracion";
	private final String NOM_PARAM_NUM_ID_DOCUMENTO_SOLICITUD = "idDocumentoSolicitud";
	private final String NOM_PARAM_ES_CORRECCION = "esCorreccion";
	private int PARAM_IDE_TAREA = 27;
	private int PARAM_TAREA_IDE_DOCUMENTO = 0;
	private int PARAM_TAREA_NUM_REP_DOCUMENTO = 1;
	private int PARAM_TAREA_IDE_DOCUMENTO_SOLICITUD = 2;
	private int PARAM_TAREA_ES_CORRECCION = 3;
	private int ORGANIZACION_NOMBRE_PROPIO = 3;

	//Helpers
	private DHelperExpedienteGeneracion120v5 manejadorExpedientes = null;

	public DCmdAccWFGenerarDecInformativaConsolidadaIndv5Impl() {
	}

	/**
	 * Este metodo valida que no se procesen dos cargas del mismo anio.
	 * @return boolean
	 */
	private boolean validarProcesamiento(DSolicitudIngresoTO miSolicitud) throws DExcepcion {

		boolean puedeProcesar = true;

		int annoSolActual = miSolicitud.getSolicitudAtt().getAnioVigencia().intValue();
		int conceptoActual = miSolicitud.getSolicitudAtt().getCodConcepto().intValue();

		DCmdSrvConsLstSolicitudDeclaracion consLstSolDecla = null;
		String nombreAcc = "diligenciamientomasivo.solicitudesdeclaracion.DCmdSrvConsLstSolicitudDeclaracion";
		consLstSolDecla = (DCmdSrvConsLstSolicitudDeclaracion) getServicio(nombreAcc);

		DSolicitudDeclaracionAttTO solDeclaAtt = new DSolicitudDeclaracionAttTO();
		solDeclaAtt.setIdeFormato(new Integer(IDE_FORMATO));
		solDeclaAtt.setNumVersionFormato((byte)NUM_VERSION_FORMATO);
		solDeclaAtt.setCodEstado(IDDocumento.IDE_ESTADO_PRESENTADO);

		Long idePersonaOrg = obtenerIdePersonaOrg(miSolicitud.getSolicitudAtt().getIdeOrganizacion());

		if (idePersonaOrg == null) {
			idePersonaOrg = miSolicitud.getSolicitudAtt().getIdeUsuarioSolicitud();
		}

		solDeclaAtt.setIdePersona(idePersonaOrg);
		consLstSolDecla.inicializarConsultaGenerica(new DSolicitudDeclaracionTO(new DSolicitudDeclaracionPKTO(),solDeclaAtt));

		consLstSolDecla.ejecutar();

		Collection docsGenerados = consLstSolDecla.getColeccionSolicitudDeclaracion();

		if (docsGenerados != null) {
			for (Iterator iter = docsGenerados.iterator(); iter.hasNext(); ) {
				DSolicitudDeclaracionTO item = (DSolicitudDeclaracionTO) iter.next();
				DSolicitudIngresoTO solIngresoTO = consultarSolicitud(item.getPK().getIdeDocumentoCarga());
				if (solIngresoTO.getSolicitudAtt().getIdeFormato().intValue() == IDE_FORMATO_CARGA &&
						solIngresoTO.getSolicitudAtt().getAnioVigencia().intValue() == annoSolActual && conceptoActual != CONCEPTO_CORRECCION) {
					puedeProcesar = false;
					tipoError = ERROR_DOC_INICIAL_EXISTE;
					break;
				}
			}
		}
		return puedeProcesar;
	}



	/**
	 * 
	 */
	protected void ejecutarComandoSinTransaccion() {
		logger.info("Inicio WF Decl. Inf. Precios de Transferencia Individual v5");
		int registroActual = 1;
		String ideArchivosProcesados = "";

		try {
			documentoCargaPK = (DDocumentoPKTO) getDocumentos().iterator().next();
			solicitud = consultarSolicitud(documentoCargaPK.getIdeDocumento());
			long ideSol = solicitud.getSolicitudPK().getIdeSolicitud().longValue();
			long numRegistrosSol = solicitud.getSolicitudAtt().
			getNumTotalRegistros().longValue();
			long numRegistrosProcesados = 0;

			procesarValido = validarProcesamiento(solicitud);

			if (procesarValido) {
				logger.info("Proceso valido. Se va a iniciar la carga");

				DSolicitudIngresoPKTO solicitudIngresoPK = new DSolicitudIngresoPKTO(documentoCargaPK.getIdeDocumento());
				idSolilcitudIngreso1125 = solicitudIngresoPK.getIdeSolicitud().longValue();

				DCmdSrvConsLstSolicitudArchivo srvConsLstConsArchivo = null;
				String nombreSrv = "cargamasiva.procesamiento.DCmdSrvConsLstSolicitudArchivo";
				srvConsLstConsArchivo = (DCmdSrvConsLstSolicitudArchivo) getServicio(nombreSrv);
				srvConsLstConsArchivo.inicializarConsultarPorSolicitudIngreso(solicitudIngresoPK);
				srvConsLstConsArchivo.ejecutar();

				DCmdSrvConsDocumentosES srvConsDoc = null;
				String nombreSrv2 = "entradasalida.documentos.DCmdSrvConsDocumentosES";
				srvConsDoc = (DCmdSrvConsDocumentosES)this.getServicio(nombreSrv2);

				Collection coleccionArchivos = srvConsLstConsArchivo.getColeccionSolicitudArchivo();
				Iterator iteradorColeccionArchivos = coleccionArchivos.iterator();
				long milisInicio = System.currentTimeMillis();

				while (iteradorColeccionArchivos.hasNext()) {

					registroActual = 1;
					int extremoSuperiorIntervalo;
					DSolicitudArchivoTO solictidArchivoTO = (DSolicitudArchivoTO)
					iteradorColeccionArchivos.next();
					Long ideRecursoArchivo = solictidArchivoTO.getPK().
					getIdeRecursoArchivo();
					srvConsDoc.inicializar(documentoCargaPK.getIdeDocumento(),ideRecursoArchivo, registroActual,AMPLITUD_INTERVALO);
					srvConsDoc.ejecutar();
					documentosCargados = srvConsDoc.getDocumentos();

					while (documentosCargados != null && documentosCargados.size() > 0) {
						extremoSuperiorIntervalo = registroActual + AMPLITUD_INTERVALO - 1;

						//Se recorren los documentos de la carga masiva (1125)
						for (Iterator iter = documentosCargados.iterator(); iter.hasNext(); ) {

							IDDocumento itemDocumento = (IDDocumento) iter.next();
							itemDocumento = dehomologar(itemDocumento);
							IDOcurrencia ocurrencia = itemDocumento.getGrupos().getGrupo(1).getOcurrencia(1);

							//Aplicacion de las reglas de negocio.

							if (ocurrencia.getValorCasilla(CASILLA_60_1125) != null && ocurrencia.getValorCasilla(CASILLA_60_1125).getValorEntero() != null) {

								Long valCas60 = ocurrencia.getValorCasilla(CASILLA_60_1125).getValorEntero();

								//casilla 28 del 120 ver 5
								if (valCas60.longValue() >= 1 && valCas60.longValue() <= 29) {

									montoTotalOperacionIngreso += (ocurrencia.getValorCasilla(CASILLA_62_1125) != null && //31??33
											ocurrencia.getValorCasilla(CASILLA_62_1125).getValorEntero() != null) ? ocurrencia.
													getValorCasilla(CASILLA_62_1125).getValorEntero().longValue() : 0;
								}

								//casilla 29 del 120 ver 5
								if (valCas60.longValue() >= 30 && valCas60.longValue() <= 58) {

									montoTotalOperacionEgreso += (ocurrencia.getValorCasilla(CASILLA_62_1125) !=
										null &&	ocurrencia.getValorCasilla(CASILLA_62_1125).getValorEntero() != null) ?
												ocurrencia.getValorCasilla(CASILLA_62_1125).getValorEntero().longValue() : 0;

								}
								//casilla 30 del 120 ver 5
								if (valCas60.longValue() >= 59 && valCas60.longValue() <= 60) {

									aporteSociedadesExtranjeras += (ocurrencia.getValorCasilla(CASILLA_62_1125) !=
										null &&	ocurrencia.getValorCasilla(CASILLA_62_1125).getValorEntero() != null) ?
												ocurrencia.getValorCasilla(CASILLA_62_1125).getValorEntero().longValue() : 0;

								}
								//casilla 31 del 120 ver 5
								if (valCas60.longValue() >= 61 && valCas60.longValue() <= 63) {

									informacionAdicional += (ocurrencia.getValorCasilla(CASILLA_62_1125) !=
										null &&	ocurrencia.getValorCasilla(CASILLA_62_1125).getValorEntero() != null) ?
												ocurrencia.getValorCasilla(CASILLA_62_1125).getValorEntero().longValue() : 0;

								}


								// casilla 32 del 120 ver 5
								if (valCas60.longValue() == 42 ) {

									montoTotalPrincipalRecibido += (ocurrencia.getValorCasilla(CASILLA_63_1125) !=
										null && ocurrencia.getValorCasilla(CASILLA_63_1125).getValorEntero() != null) ?
												ocurrencia.getValorCasilla(CASILLA_63_1125).getValorEntero().longValue() : 0;
								}

								//casilla 33 del 120 ver 5
								if (valCas60.longValue() == 13 ) {

									montoTotalPrincipalPrestado += (ocurrencia.getValorCasilla(CASILLA_63_1125) !=
										null && ocurrencia.getValorCasilla(CASILLA_63_1125).getValorEntero() != null) ?
												ocurrencia.getValorCasilla(CASILLA_63_1125).getValorEntero().longValue() : 0;
								}							

								////////////////////////////////////////////////////////////
								nit = new Long(ocurrencia.getValorCasilla(
										CASILLA_NUMERO_IDENTIFICACION_TRIBUTARIA).
										getValorEntero().
										longValue());

								dv = new Long(ocurrencia.getValorCasilla(CASILLA_DV).
										getValorEntero().
										longValue());
								admin = consultarAdministracion(nit);         
							}
							numRegistrosProcesados++;
						}
						registroActual = registroActual + AMPLITUD_INTERVALO;
						/**
						 * @todo Notificar estado al MBEAN
						 */
						srvConsDoc.inicializar(documentoCargaPK.getIdeDocumento(),ideRecursoArchivo, registroActual,AMPLITUD_INTERVALO);
						srvConsDoc.ejecutar();
						documentosCargados = srvConsDoc.getDocumentos();
					}
					ideArchivosProcesados = ideArchivosProcesados + " " +
					ideRecursoArchivo;

				}

				if (numRegistrosSol != numRegistrosProcesados) {

					String mensajeError = "Los registros para la solicitud No. " + ideSol + " no coinciden con los procesados";
					logger.error(mensajeError);
					procesarValido = false;
					tipoError = "registros duplicados";
					throw new DExcepcion("Error generando 120", mensajeError);
				}

				num_item = solicitud.getSolicitudAtt().getNumTotalRegistros().longValue();
				idPersona = consultarIdPersonaRut(nit.longValue());
				annoDeclaracion = solicitud.getSolicitudAtt().getAnioVigencia().
				intValue();

				procesarValido = validarDocCorreccion(solicitud);
			}
			isOk = true;

		} catch (DExcepcion ex) {
			isFinalizado = true;
			isOk = false;
		}
	}


	/**
	 * 
	 * @param ideDocumento
	 * @param numRepeticion
	 * @param ideFormato
	 * @return
	 * @throws DExcepcion
	 */
	private IDDocumento obtenerDocumentoMUISCA(long ideDocumento,int numRepeticion, int ideFormato) throws DExcepcion {
		DBusServiciosEYSDelegateTxNueva delegado = new DBusServiciosEYSDelegateTxNueva();  
		delegado.setTransaccional(true);
		DCmdSrvConsDocumentoMUISCA srvConsDoc = (DCmdSrvConsDocumentoMUISCA) 
			delegado.getComando("entradasalida.documentos.DCmdSrvConsDocumentoMUISCA");
		srvConsDoc.inicializar(ideDocumento, numRepeticion, ideFormato);
		srvConsDoc.ejecutar();
		return srvConsDoc.getDocumento();
	}

	/**
	 * 
	 * @param ideDocumento
	 * @param numRepeticion
	 * @param ideFormato
	 * @return
	 * @throws DExcepcion
	 */
	private IDDocumento obtenerDocumentoBuzon(long ideDocumento,int numRepeticion, int ideFormato) throws DExcepcion {
		DCmdSrvConsDocumentoES srvConsDoc = (DCmdSrvConsDocumentoES)
			getServicio("entradasalida.documentos.DCmdSrvConsDocumentoES");
		srvConsDoc.inicializarConsPorId(ideDocumento, numRepeticion, ideFormato);
		srvConsDoc.ejecutar();
		return srvConsDoc.getDocumento();
	}

	/**
	 * 
	 * @param ideDocumento
	 * @param numRepDocumento
	 * @return
	 * @throws DExcepcion
	 */
	private DSolicitudDeclaracionTO consultarSolicitudDeclaracion(Long ideDocumentoCarga,Integer numRepDocumentoCarga) throws DExcepcion {
		DCmdSrvConsSolicitudDeclaracion servicio = (DCmdSrvConsSolicitudDeclaracion)
			getServicio("diligenciamientomasivo.solicitudesdeclaracion.DCmdSrvConsSolicitudDeclaracion");
		DSolicitudDeclaracionPKTO solDeclapk = new DSolicitudDeclaracionPKTO(ideDocumentoCarga, numRepDocumentoCarga);
		servicio.inicializar(solDeclapk);
		servicio.ejecutar();
		return servicio.getSolicitudDeclaracion();
	}


	/**
	 * 
	 * @param unaSolicitud
	 * @return
	 * @throws DExcepcion
	 */
	public boolean validarDocCorreccion(DSolicitudIngresoTO unaSolicitud) throws
	DExcepcion {

		// Verifica si el documento es de correccion.
		// En caso de serlo le agrega el id del docuemnto 120 anterior.

		boolean correccionValida = false;
		boolean esCorreccion = false;
		String estadoSolicitud = "";
		DCmdSrvConsSolicitudIngreso srvSolIng = (DCmdSrvConsSolicitudIngreso) 
			getServicio("cargamasiva.procesamiento.DCmdSrvConsSolicitudIngreso");
		long idFormCargaCorr = 0;
		DSolicitudDeclaracionTO declaracionTO = null;

		if (unaSolicitud.getSolicitudAtt().getNumeroDocumentoAnterior() != null) {
			Long ideSolicitudAnterior = unaSolicitud.getSolicitudAtt().getNumeroDocumentoAnterior();
			do {

				srvSolIng.inicializar(new DSolicitudIngresoPKTO(new Long(ideSolicitudAnterior)));
				srvSolIng.ejecutar();
				Long idFormCargaCorreccion = srvSolIng.getSolicitudIngreso().getSolicitudAtt().getNumeroDocumentoAnterior();
				estadoSolicitud = srvSolIng.getSolicitudIngreso().getSolicitudAtt().getCodEstado();

				if (Integer.parseInt(estadoSolicitud) == IDEstadosCircuitoCargaMasiva.OK.intValue()) {
					esCorreccion = true;
					break;
				} else {

					if (idFormCargaCorreccion != null) {
						idFormCargaCorr = idFormCargaCorreccion.longValue();
					} else {
						idFormCargaCorr = 0;
					}

					if (idFormCargaCorr > 0) {
						ideSolicitudAnterior = idFormCargaCorr;
					}

				}
			} while (idFormCargaCorr > 0);

			declaracionTO = consultarSolicitudDeclaracion(ideSolicitudAnterior, 1);
			boolean esPresentado = false;

			if(declaracionTO != null){
				if(declaracionTO.getAtt().getCodEstado()==(IDDocumento.IDE_ESTADO_PRESENTADO)){
					esPresentado = true;
				}
			}

			if(esPresentado == false){
				correccionValida = false;
			}

			if (esPresentado == false) {
				throw new DExcepcion("Solicitud Anterior no Existe", "La solicitud "
						+ unaSolicitud.getSolicitudPK().getIdeSolicitud()
						+ " es una correccion pero no se encontro la carga anterior " + unaSolicitud.getSolicitudAtt().getNumeroDocumentoAnterior()
						+ " con Declaracion Presentada ni ninguna carga anterior con Declaracion Presentada."
						+	"Para presentar una correccion es necesario que exista al menos una "
						+	"declaracion presentada");
			}



			if(declaracionTO != null){
				idDocumentoCorreccion = declaracionTO.getAtt().getIdeDocumento();
				numRepDocumentoCorreccion = declaracionTO.getAtt().getNumRepeticion();

				correccionValida = true;
				IDDocumento docACorregir = obtenerDocumentoMUISCA(idDocumentoCorreccion, numRepDocumentoCorreccion, IDE_FORMATO);
				if (docACorregir == null) {
					throw new DExcepcion("Error", "No se encontro como " + "documento definitivo el documento "
							+ idDocumentoCorreccion + '-' + numRepDocumentoCorreccion + " que corrige la solicitud");
				}
				IDOcurrencia ocurrencia = docACorregir.getGrupos().getGrupo(1).getOcurrencia(1);
				valorMargenNegativo = ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_RANGO_NEGATIVO_DEL_MARGEN).getValorDecimal().doubleValue();
				valorMargenPositivo = ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_RANGO_POSITIVO_DEL_MARGEN_O_PRECIO).getValorDecimal().doubleValue();

				if (ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SANCIONES) != null	&& ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SANCIONES).getValorEntero() != null) {
					valorSancionAnterior = ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SANCIONES).getValorEntero().longValue();
				} else {
					valorSancionAnterior = 0;
				}
			}
		}	


		return (esCorreccion == true ? correccionValida : true);
	}


	/**
	 * Se crea documento 120 en blanco, 
	 */
	protected void ejecutarComandoWF() {
		logger.info("Ejecutando comando WF....");
		try {
			if (procesarValido) {
				documento = crearDocumento(idPersona.longValue());

				IDOcurrencia ocurrencia = documento.getGrupos().getGrupo(1).getOcurrencia(1);

				//se adiciona las casillas de totales
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_NUM_ITEMS_DECLARADOS).setValor(new Long(num_item));
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_OPERACIONES_INGRESO).setValor(new Long(montoTotalOperacionIngreso));
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_OPERACIONES_EGRESO).setValor(new Long(montoTotalOperacionEgreso));
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_APORTE_SOC_Y_ENTIDADES_EXTRANJERAS).setValor(new Long(aporteSociedadesExtranjeras)); 
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_INFORMACION_ADICIONAL).setValor(new Long(informacionAdicional)); 
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_DEL_PRINCIPAL_RECIBIDO).setValor(new Long(montoTotalPrincipalRecibido));
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_DEL_PRINCIPAL_PRESTADO).setValor(new Long(montoTotalPrincipalPrestado));
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_ORIGEN_DECLARACION).setValor(new Integer(IDTiposOrigenDeclaracion.INTERNO_PRIVADA));
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_BASE_LIQUIDACION).setValor("0");
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_PAGO_TOTAL).setValor("0");
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SOLICITUD_HOJA_2).setValor(documentoCargaPK.getIdeDocumento());

				if (idDocumentoCorreccion != 0) {
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_CODIGO_CORRECCION).setValor(DTipoCorreccion.DECLARACION_PRIVADA);
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_NUM_FORMULARIO_ANTERIOR).setValor(new Long(idDocumentoCorreccion));
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SANCIONES).setValor(new Long(valorSancionAnterior));
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_RANGO_NEGATIVO_DEL_MARGEN).setValor(new Double(valorMargenNegativo));
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_RANGO_POSITIVO_DEL_MARGEN_O_PRECIO).setValor(new Double(valorMargenPositivo));
				}
				else {
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SANCIONES).setValor("0");
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_CODIGO_CORRECCION).setValor("");
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_NUM_FORMULARIO_ANTERIOR).setValor("");
				}
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_NIT).setValor(nit);
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_DV_NIT).setValor(dv);
				ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_COD_ADMINISTRACION).setValor(admin);

				int annioActual = Calendar.getInstance().get(Calendar.YEAR);
				if (annoDeclaracion == annioActual) {
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_ANNIO).setValor(new Integer(annoDeclaracion - 1));
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_FRACCION_ANIO_GRAVABLE).setValorCadena("true");
				}
				else {
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_ANNIO).setValor(new Integer(annoDeclaracion));
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_FRACCION_ANIO_GRAVABLE).setValorCadena("false");
				}

				if (personaRut.getAtt().getNomPrimerApellido() != null) {
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_PRIMER_APELLIDO).setValor(personaRut.getAtt().getNomPrimerApellido());
				}

				if (personaRut.getAtt().getNomSegundoApellido() != null) {
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_SEGUNDO_APELLIDO).setValor(personaRut.getAtt().getNomSegundoApellido());
				}

				if (personaRut.getAtt().getNomPrimerNombre() != null) {
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_PRIMER_NOMBRE).setValor(personaRut.getAtt().getNomPrimerNombre());
				}

				if (personaRut.getAtt().getNomPrimerNombre() != null) {
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_OTROS_NOMBRES).setValor(personaRut.getAtt().getNomOtrosNombres());
				}

				if (personaRut.getAtt().getNomRazonSocial() != null) {
					ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_RAZON_SOCIAL).setValor(personaRut.getAtt().getNomRazonSocial());
				}

				logger.info("Se va a guardar el documento 120 generado para la " +	"solicitud: " + documentoCargaPK.getIdeDocumento());

				identificadorDoc = salvarDocumento(documento);
				anexarDocumento(identificadorDoc.getIdDocumento(),identificadorDoc.getNumRepeticion(),identificadorDoc.getIdFormato(),
						identificadorDoc.getVersion(),identificadorDoc.getIdEstado());

				crearSolicitudDeclaracion(documento.getId(), documento.getRepeticion());

			}
			else {
				logger.info("Notificando responsable......");
				getManejadorExpedientes().procesarInconsistencia(tipoError,documentoCargaPK.getIdeDocumento());
				notificarResponsable(solicitud, tipoError);
			}


			isFinalizado = true;
			isOk = true;

		} catch (DExcepcion ex) {
			isFinalizado = true;
			isOk = true;
		}
		logger.info("Finalizada ejecucion del WF Decl. Inf. Precios de " + "Transferencia Individual v5");
	}


	/**
	 * Consulta el ide_persona_rut a partir del nit
	 * @param nit
	 * @return
	 * @throws DExcepcion
	 */
	private Long consultarIdPersonaRut(long nit) throws DExcepcion {
		DCmdSrvConsPersonaRut srvConsRut = null;
		String nombreSrv = "rut.DCmdSrvConsPersonaRut";
		srvConsRut = (DCmdSrvConsPersonaRut)this.getServicio(nombreSrv);
		srvConsRut.inicializarConsultarPorNIT(nit);
		srvConsRut.ejecutar();
		personaRut = srvConsRut.getPersonaRut();

		DCmdSrvConsMascaraRut srvConsMascara = null;
		String nombreSrv2 = "rut.DCmdSrvConsMascaraRut";
		srvConsMascara = (DCmdSrvConsMascaraRut)this.getServicio(nombreSrv2);
		srvConsMascara.inicializarConsultarPorIdeMascaraRut(personaRut.getPK().getIdePersonaRut());
		srvConsMascara.ejecutar();
		return srvConsMascara.getMascaraRut().getPK().getIdePersonaRut();
	}

	private Long consultarAdministracion(long nit) throws DExcepcion {
		DCmdSrvConsRegistroRut consRegistroRut = (DCmdSrvConsRegistroRut)
		getServicio("rut.DCmdSrvConsRegistroRut");
		consRegistroRut.inicializarConsultarPorNIT(nit);
		consRegistroRut.ejecutar();				   
		Long admin = new Long(consRegistroRut.getRegistroRut().getAtt().getIdeAdministracion());
		return admin;
	}


	/**
	 * 
	 * @param idSolicitud
	 * @return
	 * @throws DExcepcion
	 */
	private DSolicitudIngresoTO consultarSolicitud(Long idSolicitud) throws	DExcepcion {
		DCmdSrvConsSolicitudIngreso srvConsSol = null;
		String nombreAcc = "cargamasiva.procesamiento.DCmdSrvConsSolicitudIngreso";
		srvConsSol = (DCmdSrvConsSolicitudIngreso) getServicio(nombreAcc);
		srvConsSol.inicializar(new DSolicitudIngresoPKTO(idSolicitud));
		srvConsSol.ejecutar();
		return srvConsSol.getSolicitudIngreso();
	}

	/**
	 * 
	 * @param idPersonaRut
	 * @return
	 * @throws DExcepcion
	 */
	private IDDocumento crearDocumento(long idPersonaRut) throws DExcepcion {
		DCmdAccLlenarDocumentoNuevoConsRut accCrearDocumento = null;
		String nombreAcc = "entradasalida.consintegral.DCmdAccLlenarDocumentoNuevoConsRut";
		accCrearDocumento = (DCmdAccLlenarDocumentoNuevoConsRut)this.getAccion(	nombreAcc);
		accCrearDocumento.inicializarPorIdePersonaRut(IDE_FORMATO,NUM_VERSION_FORMATO,idPersonaRut,	IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO);
		accCrearDocumento.ejecutar();
		IDDocumento documento = accCrearDocumento.getDocumento();
		return documento;
	}

	/**
	 * 
	 * @return
	 */
	private DHelperExpedienteGeneracion120v5 getManejadorExpedientes() {
		if (manejadorExpedientes == null) {
			manejadorExpedientes = new DHelperExpedienteGeneracion120v5(this);
		}
		return manejadorExpedientes;
	}

	/**
	 * 
	 * @return
	 */
	private Collection construirParametros() {

		logger.info("Inicio Construcción de Parametros para la tarea");

		Double idDocGen = new Double( -1);
		Double repDocGen = new Double( -1);
		Double idDocCarga = new Double( -1);

		if (procesarValido) {
			idDocGen = new Double(identificadorDoc.getIdDocumento());
			repDocGen = new Double(identificadorDoc.getNumRepeticion());
			idDocCarga = new Double(documentoCargaPK.getIdeDocumento().longValue());
		}

		Collection parametros = new ArrayList();

		DParametroTareaPKTO paramPK = new DParametroTareaPKTO(null, null);
		DParametroTareaAttTO paramAtt = new DParametroTareaAttTO(NOM_PARAM_ID_DOCUMENTO_DECLARACION, new Character('N'),	new BigDecimal(idDocGen.longValue()), null, null);
		paramAtt.setIdeParametroTareaDef(new Short((short)PARAM_TAREA_IDE_DOCUMENTO));
		paramAtt.setIdeTareaDef(new Integer(PARAM_IDE_TAREA));
		DParametroTareaTO param = new DParametroTareaTO(paramPK, paramAtt);
		parametros.add(param);
		logger.info("Parametro 1:\n\t"+param.getParametroTareaAtt().getNomParametro()+": "+idDocGen.longValue()+"\nAdicionado a Parametros");

		paramPK = new DParametroTareaPKTO(null, null);
		paramAtt = new DParametroTareaAttTO(NOM_PARAM_NUM_REPETICION_DECLARACION,new Character('N'),new BigDecimal(repDocGen.intValue()), null, null);
		// Cambiar por el tipo de tarea que corresponda
		paramAtt.setIdeParametroTareaDef(new Short((short)PARAM_TAREA_NUM_REP_DOCUMENTO));
		paramAtt.setIdeTareaDef(new Integer(PARAM_IDE_TAREA));
		// FIN Cambiar por el tipo de tarea que corresponda
		param = new DParametroTareaTO(paramPK, paramAtt);
		parametros.add(param);
		logger.info("Parametro 2:\n\t"+param.getParametroTareaAtt().getNomParametro()+": "+repDocGen.intValue()+"\nAdicionado a Parametros");


		paramPK = new DParametroTareaPKTO(null, null);
		paramAtt = new DParametroTareaAttTO(NOM_PARAM_NUM_ID_DOCUMENTO_SOLICITUD,new Character('N'),
				new BigDecimal(idDocCarga.longValue()), null, null);
		// Cambiar por el tipo de tarea que corresponda
		paramAtt.setIdeParametroTareaDef(new Short((short)PARAM_TAREA_IDE_DOCUMENTO_SOLICITUD));
		paramAtt.setIdeTareaDef(new Integer(PARAM_IDE_TAREA));
		// FIN Cambiar por el tipo de tarea que corresponda
		param = new DParametroTareaTO(paramPK, paramAtt);
		parametros.add(param);
		logger.info("Parametro 3:\n\t"+param.getParametroTareaAtt().getNomParametro()+": "+idDocCarga.longValue()+"\nAdicionado a Parametros");
		paramPK = new DParametroTareaPKTO(null, null);
		paramAtt = new DParametroTareaAttTO(NOM_PARAM_ES_CORRECCION,new Character('C'),null, (idDocumentoCorreccion != 0 ? "si" : "no"), null);
		// Cambiar por el tipo de tarea que corresponda
		paramAtt.setIdeParametroTareaDef(new Short((short)PARAM_TAREA_ES_CORRECCION));
		paramAtt.setIdeTareaDef(new Integer(PARAM_IDE_TAREA));
		// FIN Cambiar por el tipo de tarea que corresponda
		param = new DParametroTareaTO(paramPK, paramAtt);
		parametros.add(param);
		logger.info("Parametro 4:\n\t"+param.getParametroTareaAtt().getNomParametro()+": "+(idDocumentoCorreccion != 0 ? "si" : "no")+"\nAdicionado a Parametros");

		logger.info("Retornando parametros!");

		return parametros;
	}

	/**
	 * 
	 * @param miSolicitud
	 * @param msjNotificacion
	 * @return
	 * @throws DExcepcion
	 */
	private Long notificarResponsable(DSolicitudIngresoTO miSolicitud,
			String msjNotificacion) throws DExcepcion {

		logger.info("Notificando responsable de la tarea");

		String urlTarea = "";

		logger.info("procesarValido: "+procesarValido);
		if (procesarValido) {
			// Anexar el numero de solicitud al mensaje
			msjNotificacion += Long.toString(identificadorDoc.getIdDocumento());
		}
		msjNotificacion += " generada por la Solicitud de Carga " +
		documentoCargaPK.getIdeDocumento();

		logger.info("msjNotificacion: "+msjNotificacion);

		// Consultar el usuario con el id de la persona remitente
		Long idPersona = miSolicitud.getSolicitudAtt().getIdePersona();
		Long idTarea = null;
		DUsuarioTO usr = new DUsuarioTO();
		DUsuarioAttTO att = new DUsuarioAttTO();
		att.setIdePersonaRut(idPersona);

		usr.setUsuarioAtt(att);
		logger.info("Consulta usuario con el idePersonaRemitente: "+att.getIdeOrganizacion());
		DCmdSrvConsUsuarios srvConsUsuarios = (DCmdSrvConsUsuarios) 
			getServicio("arquitectura.seguridad.DCmdSrvConsUsuarios");
		srvConsUsuarios.iniciar(usr);
		srvConsUsuarios.ejecutar();
		Long idUsuario = srvConsUsuarios.getUsuarioTO().getUsuarioPK().getId();
		logger.info("Consulta usuario con el idePersonaRemitente: "+att.getIdeOrganizacion()+" -> Ideusuario: "+idUsuario);

		// Consultar el usuario y rol para enviar la tarea
		Integer idOrg = miSolicitud.getSolicitudAtt().getIdeOrganizacion();
		DCmdSrvConsUsuarioRol srvUsuario = (DCmdSrvConsUsuarioRol) getServicio("arquitectura.seguridad.DCmdSrvConsUsuarioRol");
		DUsuarioRolPKTO pkUsuario = new DUsuarioRolPKTO();
		pkUsuario.setIdeUsuario(idUsuario);
		pkUsuario.setIdeOrganizacion(idOrg);
		srvUsuario.inicializarConsultaGenericaPorPK(pkUsuario);
		srvUsuario.ejecutar();
		logger.info("Consulta rol.....");
		Collection usuarios = srvUsuario.getRolesUsuario();
		logger.info("Roles para el usuario "+idUsuario+": "+usuarios.size());
		if (usuarios != null && !usuarios.isEmpty()) {
			DUsuarioRolTO usuario = (DUsuarioRolTO) usuarios.iterator().next();
			pkUsuario = usuario.getUsuarioRolPK();
			logger.info("Rol: "+usuario.getUsuarioRolPK().getIdeRol());
			// Enviar mensaje de notificacion a la bandeja de tareas
			DCmdSrvCrearTarea srvTarea = (DCmdSrvCrearTarea) 
				getServicio("arquitectura.DCmdSrvCrearTarea");
			DTareaAttTO tarea = new DTareaAttTO(idUsuario,idOrg, pkUsuario.getIdeLugar(),
					pkUsuario.getIdeTipoOrganizacion(),	pkUsuario.getIdeUnidadAdministrativa(),
					pkUsuario.getIdeRol(),new Integer(PARAM_IDE_TAREA), // cambiar por el tipo de tarea creado para 540
					new Character('A'),	msjNotificacion,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),
					new Date(System.currentTimeMillis()),urlTarea);

			tarea.setIdeFlujo(getContextoWF().getIdFlujo());
			tarea.setIdeActividad(getContextoWF().getIdActividad());

			Collection parametros = construirParametros();
			srvTarea.inicializarTareaParametrosCompletos(tarea, parametros);
			logger.info("Crear la tarea: ");
			logger.info("DTareaAttTO:\n");
			logger.info("idUsuario: "+idUsuario);
			logger.info("idOrg: "+idOrg);
			logger.info("idLugar: "+pkUsuario.getIdeLugar());
			logger.info("idTipoOrg: "+pkUsuario.getIdeTipoOrganizacion());
			logger.info("idUndAdm: "+pkUsuario.getIdeUnidadAdministrativa());
			logger.info("idRol: "+pkUsuario.getIdeRol());
			logger.info("PARAM_IDE_TAREA: "+PARAM_IDE_TAREA);
			logger.info("A");
			logger.info("msjNotificacion: "+msjNotificacion);
			logger.info("urlTarea: "+urlTarea);
			srvTarea.ejecutar();
			logger.info("Ejecutada la creacion de la tarea!!");
			DTareaPKTO tareaPK = srvTarea.getTareaPK();
			idTarea = tareaPK.getIdeTarea();
			logger.info("Se obtuvo el ideTarea: "+idTarea);

		}

		return (msjNotificacion.equals("registros duplicados") ? new Long( -1) :
			idTarea);

	}
	/**
	 * 
	 * @param ideDoc
	 * @throws DExcepcion
	 */
	private void crearSolicitudDeclaracion(Long ideDoc, Integer numRepeticion) throws DExcepcion {
		DCmdSrvCrearSolicitudDeclaracion  srvCrearSolicitudDeclaracion = null;
		String nombreSrv = "diligenciamientomasivo.solicitudesdeclaracion.DCmdSrvCrearSolicitudDeclaracion";
		srvCrearSolicitudDeclaracion = (DCmdSrvCrearSolicitudDeclaracion) getServicio(nombreSrv);

		DSolicitudDeclaracionPKTO pkSolicitudDeclaracion = new DSolicitudDeclaracionPKTO(documentoCargaPK.getIdeDocumento(), documentoCargaPK.getNumRepeticion());
		DSolicitudDeclaracionAttTO docSolicitudDeclaracionAtt = new DSolicitudDeclaracionAttTO();
		docSolicitudDeclaracionAtt.setIdeDocumento(ideDoc);
		docSolicitudDeclaracionAtt.setNumRepeticion(numRepeticion);
		docSolicitudDeclaracionAtt.setCodEstado(IDDocumento.IDE_ESTADO_GENERADO_DIAN);
		docSolicitudDeclaracionAtt.setIdeFormato(IDConstantesDiligenciamientoMasivo.FORMULARIO_PRECIOS_TRANSFERENCIA_INDIVIDUAL);
		docSolicitudDeclaracionAtt.setNumVersionFormato((byte)documento.getVersionFormato());
		docSolicitudDeclaracionAtt.setIdePersona(idPersona);
		docSolicitudDeclaracionAtt.setFecCambio(solicitud.getSolicitudAtt().getFecSolicitud());
		docSolicitudDeclaracionAtt.setIdeUsuarioCambio(solicitud.getSolicitudAtt().getIdeUsuarioCambio());
		docSolicitudDeclaracionAtt.setIdeFormatoCarga(solicitud.getSolicitudAtt().getIdeFormato());
		docSolicitudDeclaracionAtt.setNumVersionFormatoCarga((byte)solicitud.getSolicitudAtt().getNumVersionFormato().byteValue());

		DSolicitudDeclaracionTO solDeclaraTO = new DSolicitudDeclaracionTO(pkSolicitudDeclaracion, docSolicitudDeclaracionAtt);
		srvCrearSolicitudDeclaracion.inicializar(solDeclaraTO);
		srvCrearSolicitudDeclaracion.ejecutar();

	}

	/**
	 * 
	 * @param ideOrganizacion
	 * @return
	 * @throws DExcepcion
	 */
	private Long obtenerIdePersonaOrg(Integer ideOrganizacion) throws DExcepcion {
		String nomSrv = "arquitectura.tablasbasicas.DCmdSrvConsCatalogoOrganizaciones";
		DCmdSrvConsCatalogoOrganizaciones catalogoOrg = null;
		catalogoOrg = (DCmdSrvConsCatalogoOrganizaciones) getServicio(nomSrv);
		catalogoOrg.inicializarConsultaPorCodigo(new DOrganizacionPKTO(ideOrganizacion));
		catalogoOrg.ejecutar();
		return catalogoOrg.getOrganizacion().getOrganizacionAtt().getIdePersona();

	}

	/**
	 * 
	 * @param doc
	 * @return
	 * @throws DExcepcion
	 */
	private DIdentificadorDoc salvarDocumento(IDDocumento doc) throws DExcepcion {

		DIdentificadorDoc identificadorDoc = new DIdentificadorDoc();
		DCmdSrvCrearDocumentoES dCmdSrvCrearDocumentoES = (DCmdSrvCrearDocumentoES)
			getServicio("entradasalida.documentos.DCmdSrvCrearDocumentoES");
		IDOcurrencia ocurrencia = doc.getGrupos().getGrupo(1).getOcurrencia(1);

		// modo negocio
		int modoDil = ocurrencia.getValorCasilla(25) != null &&
		ocurrencia.getValorCasilla(25).getValorEntero() != null ? IDModosDiligenciamiento.MODO_CORRECCION :
			IDModosDiligenciamiento.MODO_INICIAL;

		dCmdSrvCrearDocumentoES.inicializarSinId(doc,IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO);
		dCmdSrvCrearDocumentoES.setEstadoExterno(new Integer(IDDocumento.IDE_ESTADO_TEMPORAL));
		dCmdSrvCrearDocumentoES.getIdentificadorDoc().setIdModoNegocio(modoDil);
		dCmdSrvCrearDocumentoES.ejecutar();
		identificadorDoc = dCmdSrvCrearDocumentoES.getIdentificadorDoc();
		return identificadorDoc;
	}

	/**
	 * 
	 * @param documento
	 * @return
	 * @throws DExcepcion
	 */
	private IDDocumento dehomologar(IDDocumento documento) throws DExcepcion {
		DCmdSrvHomologacionPnuts srvHomologarPnuts = (DCmdSrvHomologacionPnuts)
			this.getServicio("entradasalida.cargamasiva.DCmdSrvHomologacionPnuts");

		srvHomologarPnuts.inicializarDehomologar(documento);
		srvHomologarPnuts.ejecutar();
		IDLogDocumento logDocumento = srvHomologarPnuts.getLogErroresDoc();

		if (logDocumento.getConteo() > 0) {
			String mensaje = "El Documento " + documento.getId() + " con repetición " + documento.getRepeticion() + " no se ha podido ";
			mensaje += " dehomologar correctamente.";
			throw new DEntradaSalidaExcepcion(mensaje, logDocumento.getXml());
		}

		IDDocumento docHomologado = srvHomologarPnuts.getDocumentoHomologado();
		return docHomologado;
	}
}
