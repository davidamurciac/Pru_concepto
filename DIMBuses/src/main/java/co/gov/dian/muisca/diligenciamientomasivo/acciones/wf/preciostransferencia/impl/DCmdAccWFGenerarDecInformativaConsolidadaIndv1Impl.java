package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.
  DParametroTareaAttTO;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.
  DParametroTareaPKTO;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.
  DParametroTareaTO;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.DTareaAttTO;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.DTareaPKTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioAttTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioRolPKTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioRolTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioTO;
import co.gov.dian.muisca.arquitectura.general.to.tablasbasicas.
  DOrganizacionPKTO;
import co.gov.dian.muisca.arquitectura.general.to.tablasbasicas.DOrganizacionTO;
import co.gov.dian.muisca.arquitectura.servicios.DCmdSrvCrearTarea;
import co.gov.dian.muisca.arquitectura.servicios.seguridad.
  DCmdSrvConsUsuarioRol;
import co.gov.dian.muisca.arquitectura.servicios.seguridad.DCmdSrvConsUsuarios;
import co.gov.dian.muisca.arquitectura.servicios.tablasbasicas.
  DCmdSrvConsCatalogoOrganizaciones;
import co.gov.dian.muisca.arquitectura.web.buses.DBusServiciosEYSDelegateTxNueva;
import co.gov.dian.muisca.entradasalida.acciones.consintegral.
  DCmdAccLlenarDocumentoNuevoConsRut;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia.
  DCmdAccWFGenerarDecInformativaConsolidadaIndv1;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDLogDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;
import co.gov.dian.muisca.entradasalida.excepciones.DEntradaSalidaExcepcion;
import co.gov.dian.muisca.entradasalida.formatos.IDFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDModosDiligenciamiento;
import co.gov.dian.muisca.cargamasiva.constantes.IDEstadosCircuitoCargaMasiva;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.
  DSolicitudArchivoTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.
  DSolicitudIngresoPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.
  DSolicitudIngresoTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaAttTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaPKTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.
  DSolicitudExogenaAttTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.
  DSolicitudExogenaPKTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DSolicitudExogenaTO;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.
  DCmdSrvConsLstSolicitudArchivo;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.
  DCmdSrvConsSolicitudIngreso;
import co.gov.dian.muisca.entradasalida.servicios.cargamasiva.
  DCmdSrvHomologacionPnuts;
import co.gov.dian.muisca.entradasalida.servicios.documentos.
  DCmdSrvConsDocumentosES;
import co.gov.dian.muisca.entradasalida.servicios.exogena.
  DCmdSrvConsSolicitudExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.
  DCmdSrvCrearDocGenExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.
  DCmdSrvCrearSolicitudExogena;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsMascaraRut;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsPersonaRut;
import co.gov.dian.muisca.entradasalida.servicios.exogena.
  DCmdSrvConsLstDocGenExogena;
import java.util.*;
import co.gov.dian.muisca.entradasalida.servicios.exogena.
  DCmdSrvConsLstSolicitudExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.
  DCmdSrvConsDocGenExogena;
import java.math.BigDecimal;
import co.gov.dian.muisca.entradasalida.formatos.IDConstantesFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDTiposOrigenDeclaracion;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.
  DComandoAccion;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaNegTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaNegPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.
  DHelperExpedienteGeneracion120v1;
import co.gov.dian.muisca.entradasalida.servicios.documentos.
  DCmdSrvCrearDocumentoES;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.
  DHelperExpedienteGeneracionDeclPreciosTransf;
import org.apache.log4j.Logger;
import co.gov.dian.muisca.entradasalida.servicios.documentos.
  DCmdSrvConsDocumentoMUISCA;
import co.gov.dian.muisca.entradasalida.servicios.documentos.
  DCmdSrvConsDocumentoES;

public class DCmdAccWFGenerarDecInformativaConsolidadaIndv1Impl extends 
  DCmdAccWFGenerarDecInformativaConsolidadaIndv1 {

  private static final Logger logger = Logger.getLogger(
    DCmdAccWFGenerarDecInformativaConsolidadaIndv1Impl.class);

  //MENSAJES DE ERROR
  private final String ERROR_NO_DOC_120_ANTERIOR =
    "No se genero un documento 120 " +
    "para la solicitud anterior que se esta corrigiendo";

    private final String ERROR_DOC_INICIAL_EXISTE = "Ya existe una declaracion INICIAL para el a�o gravable. " +
    "No es posible presentar la declaraci�n ";

  private final String ERROR_DOC_120_ANTERIOR_NO_PRESENTADO =
    "se genero un documento 120 " +
    "para la solicitud anterior que se esta corrigiendo pero este no " +
    "ha sido presentado";

  private final int ORG_A_NOMBRE_PROPIO = 2;
  private final int CONCEPTO_CORRECCION = 2;
  private final int IDE_FORMATO_CARGA = 1125;

  //CASILLAS DE CABECERA
  private static final int CASILLA_ANNO = 1;
  private static final int CASILLA_NUMERO_IDENTIFICACION_TRIBUTARIA = 5;
  private static final int CASILLA_DV = 6;
  private static final int CASILLA_PRIMER_APELLIDO = 7;
  private static final int CASILLA_SEGUNDO_APELLIDO = 8;
  private static final int CASILLA_PRIMER_NOMBRE = 9;
  private static final int CASILLA_OTROS_NOMBRES = 10;
  private static final int CASILLA_RAZON_SOCIAL = 11;
  private static final int CODIGO_CORRECCION = 24;
  private static final int CASILLA_CORRECCION = 25;
  private static final int CASILLA_FRACCION_ANIO = 26;

  //CASILLAS DE PIE
  private static final int CASILLA_980 = 980;

  //CASILLAS DE NEGOCIO
  private static final int CASILLA_TOTAL_ENTIDADES_CONSOLIDADAS = 27;
  private static final int CASILLA_MONTO_TOTAL_OPERACIONES_INGRESO = 28;
  private static final int CASILLA_MONTO_TOTAL_OPERACIONES_EGRESO = 29;
  private static final int CASILLA_MONTO_MOVIMIENTO_ACTIVO_DEBITO = 30;
  private static final int CASILLA_MONTO_MOVIMIENTO_ACTIVO_CREDITO = 31;
  private static final int CASILLA_MONTO_SALDO_ACTIVO_FINAL = 32;
  private static final int CASILLA_MONTO_MOVIMIENTO_PASIVO_DEBITO = 33;
  private static final int CASILLA_MONTO_MOVIMIENTO_PASIVO_CREDITO = 34;
  private static final int CASILLA_MONTO_SALDO_PASIVO_FINAL = 35;
  private static final int CASILLA_SOLICITUD_ENVIO_1125 = 46;
  private static final int CASILLA_BASE_LIQUIDACION = 44;
  private static final int CASILLA_SANCION = 45;

  private static final int AMPLITUD_INTERVALO = 100;

  protected static final int GRUPO = 1;
  protected static final int OCURRENCIA = 1;
  protected static final int CASILLA_CONCEPTO = 2;

  private static final int IDE_FORMATO = 120;
  private static final int NUM_VERSION_FORMATO = 1;

  //------------------------CASILLAS FORMATO 1125 ----------------------//
  private static final int CASILLA_1_1125 = 1;
  private static final int CASILLA_31_1125 = 31;
  private static final int CASILLA_33_1125 = 33;
  private static final int CASILLA_49_1125 = 49;
  private static final int CASILLA_50_1125 = 50;
  private static final int CASILLA_51_1125 = 51;

  //--------------------PARAMETROS PARA LA TAREA----------------------//
  /**
   * @todo cambiar por la url correcta
   */
  static final String URL =
    "/WebDiligenciamientomasivo/DefPreciosTransIndivPopUpv1.faces";

  private final String NOM_PARAM_ID_DOCUMENTO_DECLARACION =
    "idDocumentoDeclaracion";
  private final String NOM_PARAM_NUM_REPETICION_DECLARACION =
    "numRepeticionDeclaracion";
  private final String NOM_PARAM_NUM_ID_DOCUMENTO_SOLICITUD =
    "idDocumentoSolicitud";
  private final String NOM_PARAM_ES_CORRECCION = "esCorreccion";
  private int PARAM_IDE_TAREA = 27;
  private int PARAM_TAREA_IDE_DOCUMENTO = 0;
  private int PARAM_TAREA_NUM_REP_DOCUMENTO = 1;
  private int PARAM_TAREA_IDE_DOCUMENTO_SOLICITUD = 2;
  private int PARAM_TAREA_ES_CORRECCION = 3;
  private int ORGANIZACION_NOMBRE_PROPIO = 2;

  //Helpers
  private DHelperExpedienteGeneracion120v1 manejadorExpedientes = null;

  public DCmdAccWFGenerarDecInformativaConsolidadaIndv1Impl() {
  }

  /**
   * Este metodo valida que no se procesen dos cargas del mismo a�o.
   * @return boolean
   */
  private boolean validarProcesamiento(DSolicitudIngresoTO miSolicitud) throws
    DExcepcion {

    boolean puedeProcesar = true;

    int annoSolActual = miSolicitud.getSolicitudAtt().getAnioVigencia().
      intValue();
    int conceptoActual = miSolicitud.getSolicitudAtt().getCodConcepto().
      intValue();

    DCmdSrvConsLstDocGenExogena consLstGenExogena = null;
    String nombreAcc = "entradasalida.exogena.DCmdSrvConsLstDocGenExogena";
    consLstGenExogena = (DCmdSrvConsLstDocGenExogena) getServicio(nombreAcc);

    DDocGenExogenaAttTO docGenExogenaAtt = new DDocGenExogenaAttTO();
    docGenExogenaAtt.setIdeFormato(new Integer(IDE_FORMATO));
    docGenExogenaAtt.setVersion(new Integer(NUM_VERSION_FORMATO));
    docGenExogenaAtt.setCodEstado(new Byte((byte) IDDocumento.
      IDE_ESTADO_PRESENTADO));

    Long idePersonaOrg = obtenerIdePersonaOrg(miSolicitud.getSolicitudAtt().
      getIdeOrganizacion());

    if (idePersonaOrg == null) {

      idePersonaOrg = miSolicitud.getSolicitudAtt().getIdeUsuarioSolicitud();
    }

    docGenExogenaAtt.setIdePersonaOrg(idePersonaOrg);
    consLstGenExogena.incializar(docGenExogenaAtt);

    consLstGenExogena.ejecutar();

    Collection docsGenerados = consLstGenExogena.getColeccionDocGenExogena();

    if (docsGenerados != null) {

      for (Iterator iter = docsGenerados.iterator(); iter.hasNext(); ) {
        DDocGenExogenaTO item = (DDocGenExogenaTO) iter.next();

        DDocumentoPKTO documentoPK = new DDocumentoPKTO(item.getPk().
          getIdeDocumentoGen(), new Integer("1"));

        Collection docsSolicitudes = obtenerSolDocGen(documentoPK);

        if (docsSolicitudes != null) {
          for (Iterator iterSol = docsSolicitudes.iterator(); iterSol.hasNext(); ) {
            DSolicitudExogenaTO itemSol = (DSolicitudExogenaTO) iterSol.next();

            DSolicitudIngresoTO solIngresoTO = consultarSolicitud(itemSol.
              getPK().getIdeSolicitudExogena());

            if (solIngresoTO.getSolicitudAtt().getIdeFormato().intValue() ==
              IDE_FORMATO_CARGA &&
              solIngresoTO.getSolicitudAtt().getAnioVigencia().intValue() ==
              annoSolActual &&
              conceptoActual != CONCEPTO_CORRECCION) {

              puedeProcesar = false;
              tipoError = ERROR_DOC_INICIAL_EXISTE;
              break;
            }
          }
        }
      }
    }
    return puedeProcesar;
  }

  private Collection obtenerSolDocGen(DDocumentoPKTO documentoPK) throws
    DExcepcion {

    DCmdSrvConsLstSolicitudExogena consLstSolExogena = null;
    String nombreSrv = "entradasalida.exogena.DCmdSrvConsLstSolicitudExogena";
    consLstSolExogena = (DCmdSrvConsLstSolicitudExogena) getServicio(nombreSrv);
    consLstSolExogena.inicializar(documentoPK);
    consLstSolExogena.ejecutar();

    return consLstSolExogena.getColeccionSolicitudExogena();
  }

  protected void ejecutarComandoSinTransaccion() {
    logger.info("Inicio WF Decl. Inf. Precios de Transferencia Individual v1");
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

        DSolicitudIngresoPKTO solicitudIngresoPK = new
          DSolicitudIngresoPKTO(
          documentoCargaPK.getIdeDocumento());

        idSolilcitudIngreso1125 = solicitudIngresoPK.getIdeSolicitud().
          longValue();

        DCmdSrvConsLstSolicitudArchivo srvConsLstConsArchivo = null;
        String nombreSrv =
          "cargamasiva.procesamiento.DCmdSrvConsLstSolicitudArchivo";
        srvConsLstConsArchivo = (DCmdSrvConsLstSolicitudArchivo) getServicio(
          nombreSrv);
        srvConsLstConsArchivo.inicializarConsultarPorSolicitudIngreso(
          solicitudIngresoPK);
        srvConsLstConsArchivo.ejecutar();

        DCmdSrvConsDocumentosES srvConsDoc = null;
        String nombreSrv2 = "entradasalida.documentos.DCmdSrvConsDocumentosES";
        srvConsDoc = (DCmdSrvConsDocumentosES)this.getServicio(nombreSrv2);

        Collection coleccionArchivos = srvConsLstConsArchivo.
          getColeccionSolicitudArchivo();

        Iterator iteradorColeccionArchivos = coleccionArchivos.iterator();

        long milisInicio = System.currentTimeMillis();

        while (iteradorColeccionArchivos.hasNext()) {

          registroActual = 1;
          int extremoSuperiorIntervalo;
          DSolicitudArchivoTO solictidArchivoTO = (DSolicitudArchivoTO)
            iteradorColeccionArchivos.next();
          Long ideRecursoArchivo = solictidArchivoTO.getPK().
            getIdeRecursoArchivo();
          srvConsDoc.inicializar(documentoCargaPK.getIdeDocumento(),
            ideRecursoArchivo, registroActual,
            AMPLITUD_INTERVALO);
          srvConsDoc.ejecutar();
          documentosCargados = srvConsDoc.getDocumentos();

          while (documentosCargados != null && documentosCargados.size() > 0) {
            extremoSuperiorIntervalo = registroActual + AMPLITUD_INTERVALO - 1;

            /*logger.info("Procesando solicitud: " +
                        documentoCargaPK.getIdeDocumento() +
             ", recurso archivo " + ideRecursoArchivo + ", items: " +
                        registroActual + " - " + extremoSuperiorIntervalo);*/

            //Se recorren los documentos de la carga masiva (1125)
            for (Iterator iter = documentosCargados.iterator(); iter.hasNext(); ) {

              IDDocumento itemDocumento = (IDDocumento) iter.next();
              itemDocumento = dehomologar(itemDocumento);
              IDOcurrencia ocurrencia = itemDocumento.getGrupos().getGrupo(1).
                getOcurrencia(1);

              //Aplicacion de las reglas de negocio.

              if (ocurrencia.getValorCasilla(CASILLA_31_1125) != null &&
                ocurrencia.getValorCasilla(CASILLA_31_1125).
                getValorEntero() != null) {

                Long valCas31 = ocurrencia.getValorCasilla(CASILLA_31_1125).
                  getValorEntero();
                //logger.debug("DESPUES valCas31->" + valCas31);

                //casilla 28 del 120 ver 1
                if (valCas31.longValue() >= 1 && valCas31.longValue() <= 19) {

                  montoTotalOperacionIngreso +=
                    (ocurrencia.getValorCasilla(CASILLA_33_1125) != null && //31??33
                    ocurrencia.getValorCasilla(CASILLA_33_1125). //31??33
                    getValorEntero() != null) ? ocurrencia.
                    getValorCasilla(CASILLA_33_1125).
                    getValorEntero().longValue() : 0;
                }

                //casilla 29 del 120 ver 8
                if (valCas31.longValue() >= 20 &&
                  valCas31.longValue() <= 43) {

                  montoTotalOperacionEgreso +=
                    (ocurrencia.getValorCasilla(CASILLA_33_1125) !=
                    null &&
                    ocurrencia.getValorCasilla(CASILLA_33_1125).
                    getValorEntero() != null) ?
                    ocurrencia.getValorCasilla(CASILLA_33_1125).
                    getValorEntero().longValue() : 0;

                }

                //casilla 30 casilla 31, casilla 32 del 120 ver 8
                if (valCas31.longValue() >= 44 &&
                  valCas31.longValue() <= 52) {

                  montoMovimientoActivoDebito +=
                    (ocurrencia.getValorCasilla(CASILLA_49_1125) !=
                    null &&
                    ocurrencia.getValorCasilla(CASILLA_49_1125).
                    getValorEntero() != null) ?
                    ocurrencia.getValorCasilla(CASILLA_49_1125).
                    getValorEntero().longValue() : 0;

                  montoMovimientoActivoCredito +=
                    (ocurrencia.getValorCasilla(CASILLA_50_1125) !=
                    null &&
                    ocurrencia.getValorCasilla(CASILLA_50_1125).
                    getValorEntero() != null) ?
                    ocurrencia.getValorCasilla(CASILLA_50_1125).
                    getValorEntero().longValue() : 0;

                  montoSaldoActivoFinal +=
                    (ocurrencia.getValorCasilla(CASILLA_51_1125) !=
                    null &&
                    ocurrencia.getValorCasilla(CASILLA_51_1125).
                    getValorEntero() != null) ?
                    ocurrencia.getValorCasilla(CASILLA_51_1125).
                    getValorEntero().longValue() : 0;
                }

                //casilla 34, casilla 35, casilla 36 del 120 ver 8
                if (valCas31.longValue() >= 53 &&
                  valCas31.longValue() <= 57) {

                  montoMovimientoPasivoDebito +=
                    (ocurrencia.getValorCasilla(CASILLA_49_1125) !=
                    null &&
                    ocurrencia.getValorCasilla(CASILLA_49_1125).
                    getValorEntero() != null) ?
                    ocurrencia.getValorCasilla(CASILLA_49_1125).
                    getValorEntero().longValue() : 0;

                  montoMovimientoPasivoCredito +=
                    (ocurrencia.getValorCasilla(CASILLA_50_1125) !=
                    null &&
                    ocurrencia.getValorCasilla(CASILLA_50_1125).
                    getValorEntero() != null) ?
                    ocurrencia.getValorCasilla(CASILLA_50_1125).
                    getValorEntero().longValue() : 0;

                  montoSaldoPasivoFinal +=
                    (ocurrencia.getValorCasilla(CASILLA_51_1125) !=
                    null &&
                    ocurrencia.getValorCasilla(CASILLA_51_1125).
                    getValorEntero() != null) ?
                    ocurrencia.getValorCasilla(CASILLA_51_1125).
                    getValorEntero().longValue() : 0;

                }
                
                //casilla 44 sancion por extemporaneidad
                if (valCas31.longValue() >= 1  &&
                        valCas31.longValue() <= 57) {
			      montoOperacion +=
			    	(ocurrencia.getValorCasilla(CASILLA_33_1125) !=
		            null &&
		            ocurrencia.getValorCasilla(CASILLA_33_1125).
		            getValorEntero() != null) ?
		            ocurrencia.getValorCasilla(CASILLA_33_1125).
		            getValorEntero().longValue() : 0;  
				}

                ////////////////////////////////////////////////////////////

                nit = new Long(ocurrencia.getValorCasilla(
                  CASILLA_NUMERO_IDENTIFICACION_TRIBUTARIA).
                  getValorEntero().
                  longValue());

                dv = new Long(ocurrencia.getValorCasilla(CASILLA_DV).
                  getValorEntero().
                  longValue());
              }
              numRegistrosProcesados++;
            }
            registroActual = registroActual + AMPLITUD_INTERVALO;
            /**
             * @todo Notificar estado al MBEAN
             */
            srvConsDoc.inicializar(documentoCargaPK.getIdeDocumento(),
              ideRecursoArchivo, registroActual,
              AMPLITUD_INTERVALO);
            srvConsDoc.ejecutar();
            documentosCargados = srvConsDoc.getDocumentos();
          }
          ideArchivosProcesados = ideArchivosProcesados + " " +
            ideRecursoArchivo;

          //asignarValoresDocumentoControl(docEstado, IDS_ARCHIVOS_PROCESADOS, ideArchivosProcesados, 11);
          //guardarDocEstado();
        }

        if (numRegistrosSol != numRegistrosProcesados) {

          String mensajeError = "Los registros para la solicitud No. " +
            ideSol + " no coinciden con los procesados";
          logger.error(mensajeError);

          procesarValido = false;

          tipoError = "registros duplicados";

          throw new DExcepcion("Error generando 120", mensajeError);
        }

        long milisFin = System.currentTimeMillis();
        long tiempo = milisFin - milisInicio;
        /*logger.info("Termino de consolidar la solicitud: " +
         documentoCargaPK.getIdeDocumento() + ", tiempo de ejecucion: " +
                    tiempo);*/

        num_item = solicitud.getSolicitudAtt().getNumTotalRegistros().longValue();
        //logger.debug("nit a consultar: " + nit);
        idPersona = consultarIdPersonaRut(nit.longValue());
        //logger.debug("idPersona: " + idPersona);

        annoDeclaracion = solicitud.getSolicitudAtt().getAnioVigencia().
          intValue();

        procesarValido = validarDocCorreccion(solicitud);
      }
      isOk = true;

    } catch (DExcepcion ex) {
      //logger.error("Error al ejecutar ejecutarComandoSinTransaccion(), ",ex);
      isFinalizado = true;
      isOk = false;
    }
  }

  /*
        public final IDDocumento obtenerDocumentoMuisca(long idDocumento,
                                                        int numRepeticion,
   int idFormato) throws DExcepcion {
            DCmdAccConsDocumentoMUISCA accConsDocumentoMuisca = ejecutarAccConsDocMuisca(idDocumento,
   numRepeticion,
   idFormato);
            IDDocumento documento = accConsDocumentoMuisca.getDocumento();
            return documento;

        }

   */

  private IDDocumento obtenerDocumentoMUISCA(long ideDocumento,
    int numRepeticion, int ideFormato) throws DExcepcion {
	DBusServiciosEYSDelegateTxNueva delegado = new DBusServiciosEYSDelegateTxNueva();  
	delegado.setTransaccional(true);
	DCmdSrvConsDocumentoMUISCA srvConsDoc = (DCmdSrvConsDocumentoMUISCA) delegado.getComando
    ("entradasalida.documentos.DCmdSrvConsDocumentoMUISCA");
    srvConsDoc.inicializar(ideDocumento, numRepeticion, ideFormato);
    srvConsDoc.ejecutar();
    return srvConsDoc.getDocumento();
  }

  private IDDocumento obtenerDocumentoBuzon(long ideDocumento,
    int numRepeticion, int ideFormato) throws DExcepcion {
    DCmdSrvConsDocumentoES srvConsDoc = (DCmdSrvConsDocumentoES)
      getServicio("entradasalida.documentos.DCmdSrvConsDocumentoES");
    srvConsDoc.inicializarConsPorId(ideDocumento, numRepeticion, ideFormato);
    srvConsDoc.ejecutar();
    return srvConsDoc.getDocumento();
  }

  private DSolicitudExogenaTO consultarSolicitudExogena(Long ideSolicitud) throws
  DExcepcion {
      DCmdSrvConsSolicitudExogena consSolExogena = (DCmdSrvConsSolicitudExogena)
      getServicio("entradasalida.exogena.DCmdSrvConsSolicitudExogena");
      consSolExogena.inicializarPorIdeSolicitud(new DSolicitudExogenaPKTO(null,
	      ideSolicitud));
      consSolExogena.ejecutar();
      return consSolExogena.getSolicitudExogena();
  }

  private DDocGenExogenaTO consultarDocGenExogena(Long ideDocumento,
	  Integer numRepDocumento) throws DExcepcion {
      DCmdSrvConsDocGenExogena servicio = (DCmdSrvConsDocGenExogena)
      	getServicio("entradasalida.exogena.DCmdSrvConsDocGenExogena");
      DDocGenExogenaPKTO docGenPK = new DDocGenExogenaPKTO(ideDocumento, numRepDocumento);
      servicio.inicializar(docGenPK);
      servicio.ejecutar();
      return servicio.getDocGenExogena();
  }




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
		
		if (unaSolicitud.getSolicitudAtt().getNumeroDocumentoAnterior() != null) {
		Long ideSolicitudAnterior = unaSolicitud.getSolicitudAtt()
				.getNumeroDocumentoAnterior();
		do {
			
			srvSolIng.inicializar(new DSolicitudIngresoPKTO(new Long(
					ideSolicitudAnterior)));
			srvSolIng.ejecutar();
			Long idFormCargaCorreccion = srvSolIng.getSolicitudIngreso()
					.getSolicitudAtt().getNumeroDocumentoAnterior();
			estadoSolicitud = srvSolIng.getSolicitudIngreso().getSolicitudAtt()
					.getCodEstado();

			if (Integer.parseInt(estadoSolicitud) == IDEstadosCircuitoCargaMasiva.OK
					.intValue()) {
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

		

			// esCorreccion = true;

			DSolicitudExogenaTO solExogena = null;
			// Long ideSolicitudAnterior =
			// unaSolicitud.getSolicitudAtt().getNumeroDocumentoAnterior();

			while ((solExogena == null) && (ideSolicitudAnterior != null)
					&& (ideSolicitudAnterior.longValue() > 0)) {
				solExogena = consultarSolicitudExogena(ideSolicitudAnterior);

				idDocumentoCorreccion = solExogena.getPK().getIdeDocumentoGen()
						.longValue();
				numRepDocumentoCorreccion = solExogena.getPK().getRepeticionDocGen().intValue();


				// Si se encuentra la solicitud verificar que ya este presentada
				if (solExogena != null) {
					DDocGenExogenaTO documentoGenerado = consultarDocGenExogena(new Long(idDocumentoCorreccion), new Integer(
						numRepDocumentoCorreccion));

					if ((documentoGenerado == null)
						|| (documentoGenerado.getAtt().getCodEstado().intValue() != IDDocumento.IDE_ESTADO_PRESENTADO)) {
						solExogena = null;
					}
				}
				// Si la solicitud anterior no ha sido exitosa no existe un
				// registro en Solicitud Exogena, por lo cual hay que
				// consultar las solicitudes ingreso para ver cual era la
				// solicitud que se corregia y buscar el registro de
				// solicitud exogena. Si no se encuentra continuar
				// hasta encontrarlo
				if (solExogena == null) {
					DSolicitudIngresoTO solicitudIngreso = consultarSolicitud(ideSolicitudAnterior);
					ideSolicitudAnterior = solicitudIngreso.getSolicitudAtt().getNumeroDocumentoAnterior();
				}
			}

			if (solExogena == null) {
				throw new DExcepcion("Solicitud Anterior no Existe", "La solicitud "
					+ unaSolicitud.getSolicitudPK().getIdeSolicitud()
					+ " es una correccion pero no se encontro la carga anterior "
					+ unaSolicitud.getSolicitudAtt().getNumeroDocumentoAnterior()
					+ " con Declaracion Presentada ni ninguna carga anterior con Declaracion Presentada."
					+	"Para presentar una correccion es necesario que exista al menos una "
					+	"declaracion presentada");
			}

			idDocumentoCorreccion = solExogena.getPK().getIdeDocumentoGen().longValue();
			numRepDocumentoCorreccion = solExogena.getPK().getRepeticionDocGen().intValue();

			correccionValida = true;
			IDDocumento docACorregir = obtenerDocumentoMUISCA(idDocumentoCorreccion, numRepDocumentoCorreccion, IDE_FORMATO);
			if (docACorregir == null) {
				throw new DExcepcion("Error", "No se encontro como " + "documento definitivo el documento "
					+ idDocumentoCorreccion + '-' + numRepDocumentoCorreccion + " que corrige la solicitud");
			}
			IDOcurrencia ocurrencia = docACorregir.getGrupos().getGrupo(1).getOcurrencia(1);

			if (ocurrencia.getValorCasilla(CASILLA_SANCION) != null
				&& ocurrencia.getValorCasilla(CASILLA_SANCION).getValorEntero() != null) {
				valorSancionAnterior = ocurrencia.getValorCasilla(CASILLA_SANCION).getValorEntero().longValue();
			} else {
				valorSancionAnterior = 0;
			}

		}
			return (esCorreccion == true ? correccionValida : true);
    }

    /**
  	 * Ejecuta la l�gica de negocio.
  	 *
  	 * @todo Implement this
  	 *       co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAccion
  	 *       method
  	 */
  protected void ejecutarComandoWF() {
	  logger.info("Ejecutando comando WF....");
    try {
      if (procesarValido) {
        documento = crearDocumento(idPersona.longValue());

        IDOcurrencia ocurrencia = documento.getGrupos().getGrupo(1).
          getOcurrencia(1);

        //se adiciona las casillas de totales

        ocurrencia.getValorCasilla(CASILLA_TOTAL_ENTIDADES_CONSOLIDADAS).
          setValor(new Long(num_item));

        ocurrencia.getValorCasilla(
          CASILLA_MONTO_TOTAL_OPERACIONES_INGRESO).
          setValor(new Long(montoTotalOperacionIngreso));

        ocurrencia.getValorCasilla(
          CASILLA_MONTO_TOTAL_OPERACIONES_EGRESO).
          setValor(new Long(montoTotalOperacionEgreso));

        ocurrencia.getValorCasilla(
          CASILLA_MONTO_MOVIMIENTO_ACTIVO_DEBITO).
          setValor(new Long(montoMovimientoActivoDebito));

        ocurrencia.getValorCasilla(
          CASILLA_MONTO_MOVIMIENTO_ACTIVO_CREDITO).
          setValor(new Long(montoMovimientoActivoCredito));

        ocurrencia.getValorCasilla(CASILLA_MONTO_SALDO_ACTIVO_FINAL).
          setValor(new Long(montoSaldoActivoFinal));

        ocurrencia.getValorCasilla(
          CASILLA_MONTO_MOVIMIENTO_PASIVO_DEBITO).
          setValor(new Long(montoMovimientoPasivoDebito));

        ocurrencia.getValorCasilla(
          CASILLA_MONTO_MOVIMIENTO_PASIVO_CREDITO).
          setValor(new Long(montoMovimientoPasivoCredito));

        ocurrencia.getValorCasilla(CASILLA_MONTO_SALDO_PASIVO_FINAL).
          setValor(new Long(montoSaldoPasivoFinal));

        ocurrencia.getValorCasilla(CASILLA_SOLICITUD_ENVIO_1125).
          setValor(new Long(idSolilcitudIngreso1125));

        ocurrencia.getValorCasilla(IDConstantesFormato.
          IDE_CASILLA_ORIGEN_DECLARACION).
          setValor(new Integer(IDTiposOrigenDeclaracion.INTERNO_PRIVADA));

        ocurrencia.getValorCasilla(CASILLA_BASE_LIQUIDACION).
          setValor(new Long(montoOperacion));
        ocurrencia.getValorCasilla(CASILLA_980).
          setValor("0");

        if (idDocumentoCorreccion != 0) {
          ocurrencia.getValorCasilla(CODIGO_CORRECCION).
            setValor("29700001");
          ocurrencia.getValorCasilla(CASILLA_CORRECCION).
            setValor(new Long(idDocumentoCorreccion));
          ocurrencia.getValorCasilla(CASILLA_SANCION).setValor(
            new Long(valorSancionAnterior));
        }
        else {
          ocurrencia.getValorCasilla(CASILLA_SANCION).setValor("0");
        }
        ocurrencia.getValorCasilla(
          CASILLA_NUMERO_IDENTIFICACION_TRIBUTARIA).
          setValor(nit);
        ocurrencia.getValorCasilla(CASILLA_DV).setValor(dv);

        // A�o
        int annioActual = Calendar.getInstance().get(Calendar.YEAR);
        if (annoDeclaracion == annioActual) {
          ocurrencia.getValorCasilla(CASILLA_ANNO).setValor(new Integer(
            annoDeclaracion - 1));
          ocurrencia.getValorCasilla(CASILLA_FRACCION_ANIO).
            setValorCadena("true");
        }
        else {
          ocurrencia.getValorCasilla(CASILLA_ANNO).setValor(new Integer(
            annoDeclaracion));
          ocurrencia.getValorCasilla(CASILLA_FRACCION_ANIO).
            setValorCadena("false");
        }

        if (personaRut.getAtt().getNomPrimerApellido() != null) {
          ocurrencia.getValorCasilla(CASILLA_PRIMER_APELLIDO).
            setValor(personaRut.getAtt().getNomPrimerApellido());
        }

        if (personaRut.getAtt().getNomSegundoApellido() != null) {
          ocurrencia.getValorCasilla(CASILLA_SEGUNDO_APELLIDO).
            setValor(
            personaRut.getAtt().getNomSegundoApellido());
        }

        if (personaRut.getAtt().getNomPrimerNombre() != null) {
          ocurrencia.getValorCasilla(CASILLA_PRIMER_NOMBRE).setValor(
            personaRut.getAtt().getNomPrimerNombre());
        }

        if (personaRut.getAtt().getNomPrimerNombre() != null) {
          ocurrencia.getValorCasilla(CASILLA_OTROS_NOMBRES).setValor(
            personaRut.getAtt().getNomOtrosNombres());
        }

        if (personaRut.getAtt().getNomRazonSocial() != null) {
          ocurrencia.getValorCasilla(CASILLA_RAZON_SOCIAL).setValor(
            personaRut.getAtt().getNomRazonSocial());
        }

        logger.info("Se va a guardar el documento 120 generado para la " +
          "solicitud: " + documentoCargaPK.getIdeDocumento());

        identificadorDoc = salvarDocumento(documento);
        anexarDocumento(identificadorDoc.getIdDocumento(),
          identificadorDoc.getNumRepeticion(),
          identificadorDoc.getIdFormato(),
          identificadorDoc.getVersion(),
          identificadorDoc.getIdEstado());

        DTareaNegTO tareaNegTo =
          getManejadorExpedientes().procesarPresentacionPorEnvioArchivos(
          documentoCargaPK.getIdeDocumento(), documento, new Date());
        if (tareaNegTo != null) {
          crearSolicitudExogena(null, tareaNegTo.getPK().getIdeDocumentoTarea(),
            tareaNegTo.getPK().getNumRepeticionDocTarea());
        }

        //Long ideTarea = notificarResponsable(solicitud, DILIGENCIAR_DOCUMENTO);
        //crearSolicitudExogena(ideTarea);

      }
      else {
    	  logger.info("Notificando responsable......");
        getManejadorExpedientes().procesarInconsistencia(tipoError,
          documentoCargaPK.getIdeDocumento());
        /*Long ideTarea = */notificarResponsable(solicitud, tipoError);
      }

      isFinalizado = true;
      isOk = true;

    } catch (DExcepcion ex) {
      //logger.error("Error al ejecutar ejecutarComandoWF(), ", ex);
      isFinalizado = true;
      isOk = true;
    }
    logger.info("Finalizada ejecucion del WF Decl. Inf. Precios de " +
      "Transferencia Individual v1");
  }

  // m�todos privados
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
    srvConsMascara.inicializarConsultarPorIdeMascaraRut(personaRut.getPK().
      getIdePersonaRut());
    srvConsMascara.ejecutar();
    return srvConsMascara.getMascaraRut().getPK().getIdePersonaRut();
  }

  private DSolicitudIngresoTO consultarSolicitud(Long idSolicitud) throws
    DExcepcion {
    DCmdSrvConsSolicitudIngreso srvConsSol = null;
    String nombreAcc = "cargamasiva.procesamiento.DCmdSrvConsSolicitudIngreso";
    srvConsSol = (DCmdSrvConsSolicitudIngreso) getServicio(nombreAcc);

    srvConsSol.inicializar(new DSolicitudIngresoPKTO(idSolicitud));

    srvConsSol.ejecutar();
    return srvConsSol.getSolicitudIngreso();
  }

  private IDDocumento crearDocumento(long idPersonaRut) throws DExcepcion {
    DCmdAccLlenarDocumentoNuevoConsRut accCrearDocumento = null;
    String nombreAcc =
      "entradasalida.consintegral.DCmdAccLlenarDocumentoNuevoConsRut";
    accCrearDocumento = (DCmdAccLlenarDocumentoNuevoConsRut)this.getAccion(
      nombreAcc);

    accCrearDocumento.inicializarPorIdePersonaRut(IDE_FORMATO,
      NUM_VERSION_FORMATO,
      idPersonaRut,
      IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO);
    accCrearDocumento.ejecutar();
    IDDocumento documento = accCrearDocumento.getDocumento();
    return documento;
  }


  private DHelperExpedienteGeneracion120v1 getManejadorExpedientes() {
    if (manejadorExpedientes == null) {
      manejadorExpedientes =
        new DHelperExpedienteGeneracion120v1(this);
    }
    return manejadorExpedientes;
  }

  private Collection construirParametros() {
	  
	  logger.info("Inicio Construcci�n de Parametros para la tarea");

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
    DParametroTareaAttTO paramAtt = new DParametroTareaAttTO(
      NOM_PARAM_ID_DOCUMENTO_DECLARACION,
      new Character('N'),
      new BigDecimal(idDocGen.longValue()), null, null);
    paramAtt.setIdeParametroTareaDef(new Short((short)
      PARAM_TAREA_IDE_DOCUMENTO));
    paramAtt.setIdeTareaDef(new Integer(PARAM_IDE_TAREA));
    DParametroTareaTO param = new DParametroTareaTO(paramPK, paramAtt);
    parametros.add(param);
    logger.info("Par�metro 1:\n\t"+param.getParametroTareaAtt().getNomParametro()+": "+idDocGen.longValue()+"\nAdicionado a Par�metros");

    paramPK = new DParametroTareaPKTO(null, null);
    paramAtt = new DParametroTareaAttTO(
      NOM_PARAM_NUM_REPETICION_DECLARACION,
      new Character('N'),
      new BigDecimal(repDocGen.intValue()), null, null);
    // Cambiar por el tipo de tarea que corresponda
    paramAtt.setIdeParametroTareaDef(new Short((short)
      PARAM_TAREA_NUM_REP_DOCUMENTO));
    paramAtt.setIdeTareaDef(new Integer(PARAM_IDE_TAREA));
    // FIN Cambiar por el tipo de tarea que corresponda
    param = new DParametroTareaTO(paramPK, paramAtt);
    parametros.add(param);
    logger.info("Par�metro 2:\n\t"+param.getParametroTareaAtt().getNomParametro()+": "+repDocGen.intValue()+"\nAdicionado a Par�metros");
    

    paramPK = new DParametroTareaPKTO(null, null);
    paramAtt = new DParametroTareaAttTO(
      NOM_PARAM_NUM_ID_DOCUMENTO_SOLICITUD,
      new Character('N'),
      new BigDecimal(idDocCarga.longValue()), null, null);
    // Cambiar por el tipo de tarea que corresponda
    paramAtt.setIdeParametroTareaDef(new Short((short)
      PARAM_TAREA_IDE_DOCUMENTO_SOLICITUD));
    paramAtt.setIdeTareaDef(new Integer(PARAM_IDE_TAREA));
    // FIN Cambiar por el tipo de tarea que corresponda
    param = new DParametroTareaTO(paramPK, paramAtt);
    parametros.add(param);
    logger.info("Par�metro 3:\n\t"+param.getParametroTareaAtt().getNomParametro()+": "+idDocCarga.longValue()+"\nAdicionado a Par�metros");

    paramPK = new DParametroTareaPKTO(null, null);
    paramAtt = new DParametroTareaAttTO(
      NOM_PARAM_ES_CORRECCION,
      new Character('C'),
      null, (idDocumentoCorreccion != 0 ? "si" : "no"), null);
    // Cambiar por el tipo de tarea que corresponda
    paramAtt.setIdeParametroTareaDef(new Short((short)
      PARAM_TAREA_ES_CORRECCION));
    paramAtt.setIdeTareaDef(new Integer(PARAM_IDE_TAREA));
    // FIN Cambiar por el tipo de tarea que corresponda
    param = new DParametroTareaTO(paramPK, paramAtt);
    parametros.add(param);
    logger.info("Par�metro 4:\n\t"+param.getParametroTareaAtt().getNomParametro()+": "+(idDocumentoCorreccion != 0 ? "si" : "no")+"\nAdicionado a Par�metros");
    
    logger.info("Retornando par�metros!");

    return parametros;
  }

  private Long notificarResponsable(DSolicitudIngresoTO miSolicitud,
    String msjNotificacion) throws DExcepcion {
	  
	  logger.info("Notificando responsable de la tarea");

    String urlTarea = "";

    logger.info("procesarValido: "+procesarValido);
    if (procesarValido) {

      // Anexar el numero de solicitud al mensaje
      msjNotificacion += Long.toString(identificadorDoc.getIdDocumento());
      //urlTarea = "/WebDiligenciamientomasivo/DefPreciosTransIndivPopUpv1.faces";
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
    DCmdSrvConsUsuarios srvConsUsuarios = (DCmdSrvConsUsuarios) getServicio(
      "arquitectura.seguridad.DCmdSrvConsUsuarios");
    srvConsUsuarios.iniciar(usr);
    srvConsUsuarios.ejecutar();
    Long idUsuario = srvConsUsuarios.getUsuarioTO().getUsuarioPK().getId();
    logger.info("Consulta usuario con el idePersonaRemitente: "+att.getIdeOrganizacion()+" -> Ideusuario: "+idUsuario);

    // Consultar el usuario y rol para enviar la tarea
    Integer idOrg = miSolicitud.getSolicitudAtt().getIdeOrganizacion();
    DCmdSrvConsUsuarioRol srvUsuario = (DCmdSrvConsUsuarioRol) getServicio(
      "arquitectura.seguridad.DCmdSrvConsUsuarioRol");
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
      DCmdSrvCrearTarea srvTarea = (DCmdSrvCrearTarea) getServicio(
        "arquitectura.DCmdSrvCrearTarea");
      DTareaAttTO tarea = new DTareaAttTO(idUsuario,
        idOrg, pkUsuario.getIdeLugar(),
        pkUsuario.getIdeTipoOrganizacion(),
        pkUsuario.getIdeUnidadAdministrativa(),
        pkUsuario.getIdeRol(),
        new Integer(PARAM_IDE_TAREA), // cambiar por el tipo de tarea creado para 540
        new Character('A'),
        msjNotificacion,
        new Date(System.currentTimeMillis()),
        new Date(System.currentTimeMillis()),
        new Date(System.currentTimeMillis()),
        urlTarea);

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
      logger.info("Ejecutada la creaci�n de la tarea!!");
      DTareaPKTO tareaPK = srvTarea.getTareaPK();
      idTarea = tareaPK.getIdeTarea();
      logger.info("Se obtuvo el ideTarea: "+idTarea);

    }

    return (msjNotificacion.equals("registros duplicados") ? new Long( -1) :
      idTarea);

  }

  private void crearSolicitudExogena(Long ideTarea, Long ideDocumentoTarea,
    Integer numRepedicionDocTarea) throws DExcepcion {

    int concepto = 1;

    Long idePersonaOrg = null;

    DCmdSrvCrearDocGenExogena srvCrearDocGenExogena = null;
    String nombreSrv = "entradasalida.exogena.DCmdSrvCrearDocGenExogena";
    srvCrearDocGenExogena = (DCmdSrvCrearDocGenExogena) getServicio(nombreSrv);

    DCmdSrvCrearSolicitudExogena srvCrearSolicitudExogena = null;
    String nombreSrv2 = "entradasalida.exogena.DCmdSrvCrearSolicitudExogena";
    srvCrearSolicitudExogena = (DCmdSrvCrearSolicitudExogena) getServicio(
      nombreSrv2);

    // REGISTRAR EL DOCUMENTO EN LAS TABLAS
    DDocGenExogenaPKTO docGenExogenaPK = new DDocGenExogenaPKTO(
      new Long(identificadorDoc.getIdDocumento()),
      new Integer(identificadorDoc.getNumRepeticion()));

    DDocGenExogenaAttTO docGenExogenaAtt = new DDocGenExogenaAttTO();
    docGenExogenaAtt.setFecCambio(new Timestamp(System.currentTimeMillis()));
    docGenExogenaAtt.setIdeTarea(ideTarea);
    docGenExogenaAtt.setIdeDocumentoTarea(ideDocumentoTarea);
    docGenExogenaAtt.setNumRepeticionDocTarea(numRepedicionDocTarea);

    // Consultar la organizacion y el ide_persona_rut de la organizacion si es diferente a 2
    Integer idOrganizacion = solicitud.getSolicitudAtt().getIdeOrganizacion();

    idePersonaOrg = (idOrganizacion.intValue() != ORGANIZACION_NOMBRE_PROPIO) ?
      obtenerIdePersonaOrg(solicitud.getSolicitudAtt().getIdeOrganizacion()) :
      solicitud.getSolicitudAtt().getIdePersona();

    docGenExogenaAtt.setIdePersonaOrg(idePersonaOrg);
    docGenExogenaAtt.setIdePersonaRut(solicitud.getSolicitudAtt().getIdePersona());

    docGenExogenaAtt.setIdeUsuarioCambio(solicitud.getSolicitudAtt().
      getIdePersona());
    docGenExogenaAtt.setCodEstado(new Byte(((byte) IDDocumento.
      IDE_ESTADO_GENERADO_DIAN)));

    docGenExogenaAtt.setIdeFormato(new Integer(IDE_FORMATO));
    docGenExogenaAtt.setVersion(new Integer(NUM_VERSION_FORMATO));

    if (idDocumentoCorreccion != 0) {
      concepto = 2;
      docGenExogenaAtt.setIdeDeclaracionCorregida(new Long(
        idDocumentoCorreccion));
      docGenExogenaAtt.setRepeticionDeclaracionCorregida(new Integer(
        numRepDocumentoCorreccion));
    }

    docGenExogenaAtt.setCodConcpto(new Integer(concepto));

    DDocGenExogenaTO dogGenExogenaTO = new DDocGenExogenaTO(docGenExogenaPK,
      docGenExogenaAtt);

    srvCrearDocGenExogena.inicializar(dogGenExogenaTO);
    srvCrearDocGenExogena.ejecutar();

    DSolicitudExogenaPKTO solExogenaPK = new DSolicitudExogenaPKTO(new Long(
      identificadorDoc.getIdDocumento()),
      solicitud.getSolicitudPK().getIdeSolicitud());
    solExogenaPK.setRepeticionDocGen(new Integer(identificadorDoc.
      getNumRepeticion()));

    DSolicitudExogenaTO solExogenaTO = new DSolicitudExogenaTO(solExogenaPK,
      new DSolicitudExogenaAttTO());

    srvCrearSolicitudExogena.inicializar(solExogenaTO);
    srvCrearSolicitudExogena.ejecutar();
  }

  private Long obtenerIdePersonaOrg(Integer ideOrganizacion) throws
    DExcepcion {

    String nomSrv =
      "arquitectura.tablasbasicas.DCmdSrvConsCatalogoOrganizaciones";

    DCmdSrvConsCatalogoOrganizaciones catalogoOrg = null;

    catalogoOrg = (DCmdSrvConsCatalogoOrganizaciones)
      getServicio(nomSrv);

    catalogoOrg.inicializarConsultaPorCodigo(new DOrganizacionPKTO(
      ideOrganizacion));

    catalogoOrg.ejecutar();
    return catalogoOrg.getOrganizacion().getOrganizacionAtt().getIdePersona();

  }

  private DIdentificadorDoc salvarDocumento(IDDocumento doc) throws
    DExcepcion {

    DIdentificadorDoc identificadorDoc = new DIdentificadorDoc();

    DCmdSrvCrearDocumentoES dCmdSrvCrearDocumentoES = (DCmdSrvCrearDocumentoES)
      getServicio("entradasalida.documentos.DCmdSrvCrearDocumentoES");

    IDOcurrencia ocurrencia = doc.getGrupos().getGrupo(1).getOcurrencia(1);

    // modo negocio
    int modoDil = ocurrencia.getValorCasilla(25) != null &&
      ocurrencia.getValorCasilla(25).getValorEntero() != null ?
      IDModosDiligenciamiento.MODO_CORRECCION :
      IDModosDiligenciamiento.MODO_INICIAL;

    dCmdSrvCrearDocumentoES.inicializarSinId(doc,
      IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO);
    dCmdSrvCrearDocumentoES.setEstadoExterno(
      new Integer(IDDocumento.IDE_ESTADO_TEMPORAL));
    dCmdSrvCrearDocumentoES.getIdentificadorDoc().setIdModoNegocio(modoDil);
    dCmdSrvCrearDocumentoES.ejecutar();
    identificadorDoc = dCmdSrvCrearDocumentoES.getIdentificadorDoc();
    /*
         String nitDoc = doc.getGrupos().getGrupo(1).getOcurrencia(1).
           getValorCasilla(5).getValorEntero().toString();
            DCmdSrvActEstadoDocumentoES srvActEstado = (DCmdSrvActEstadoDocumentoES)getServicio(
     "entradasalida.documentos.DCmdSrvActEstadoDocumentoES");
            srvActEstado.inicializar(identificadorDoc.getIdDocumento(), identificadorDoc.getNumRepeticion(), IDDocumento.IDE_ESTADO_TEMPORAL);
            srvActEstado.ejecutar();
            identificadorDoc.setIdEstado(IDDocumento.IDE_ESTADO_TEMPORAL);
     */
    return identificadorDoc;
  }

  private IDDocumento dehomologar(IDDocumento documento) throws DExcepcion {
    DCmdSrvHomologacionPnuts srvHomologarPnuts = (DCmdSrvHomologacionPnuts)
      this.getServicio("entradasalida.cargamasiva.DCmdSrvHomologacionPnuts");

    srvHomologarPnuts.inicializarDehomologar(documento);

    srvHomologarPnuts.ejecutar();
    IDLogDocumento logDocumento = srvHomologarPnuts.getLogErroresDoc();

    if (logDocumento.getConteo() > 0) {
      String mensaje = "El Documento " + documento.getId() + " con repetici�n " +
        documento.getRepeticion() + " no se ha podido ";
      mensaje += " dehomologar correctamente.";
      throw new DEntradaSalidaExcepcion(mensaje, logDocumento.getXml());
    }

    IDDocumento docHomologado = srvHomologarPnuts.getDocumentoHomologado();

    return docHomologado;
  }
}
