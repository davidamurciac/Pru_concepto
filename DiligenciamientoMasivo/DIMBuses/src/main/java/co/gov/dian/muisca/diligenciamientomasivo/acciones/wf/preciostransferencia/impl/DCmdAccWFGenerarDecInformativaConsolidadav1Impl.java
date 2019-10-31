package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

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
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaNegTO;
import co.gov.dian.muisca.arquitectura.servicios.DCmdSrvCrearTarea;
import co.gov.dian.muisca.arquitectura.servicios.seguridad.DCmdSrvConsUsuarioRol;
import co.gov.dian.muisca.arquitectura.servicios.seguridad.DCmdSrvConsUsuarios;
import co.gov.dian.muisca.arquitectura.servicios.tablasbasicas.DCmdSrvConsCatalogoOrganizaciones;
import co.gov.dian.muisca.arquitectura.web.buses.DBusServiciosEYSDelegateTxNueva;
import co.gov.dian.muisca.entradasalida.acciones.DCmdAccConsDocumentoMUISCA;
import co.gov.dian.muisca.entradasalida.acciones.DCmdAccConsIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.acciones.consintegral.DCmdAccLlenarDocumentoNuevoConsRut;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia.DCmdAccWFGenerarDecInformativaConsolidadav1;
import co.gov.dian.muisca.entradasalida.documento.DDocumentoUtil;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDLogDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;
import co.gov.dian.muisca.entradasalida.excepciones.DEntradaSalidaExcepcion;
import co.gov.dian.muisca.entradasalida.formatos.IDConstantesFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDModosDiligenciamiento;
import co.gov.dian.muisca.entradasalida.formatos.IDTiposOrigenDeclaracion;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConceptosNegocioPreciosTransferencia;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracion130v1;
import co.gov.dian.muisca.entradasalida.general.to.cargamasiva.DIdentificadorDocumentoTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudArchivoTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaAttTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaPKTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DSolicitudExogenaAttTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DSolicitudExogenaPKTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DSolicitudExogenaTO;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsLstSolicitudArchivo;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsSolicitudIngreso;
import co.gov.dian.muisca.entradasalida.servicios.cargamasiva.DCmdSrvHomologacionPnuts;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoMUISCA;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentosES;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvConsDocGenExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvConsLstDocGenExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvConsLstSolicitudExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvConsSolicitudExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvCrearDocGenExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvCrearSolicitudExogena;
import co.gov.dian.muisca.rut.general.to.DRegistroRutTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsMascaraRut;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsPersonaRut;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvCrearDocumentoES;
import org.apache.log4j.Logger;
import java.util.Map;
import java.util.HashMap;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class DCmdAccWFGenerarDecInformativaConsolidadav1Impl extends
  DCmdAccWFGenerarDecInformativaConsolidadav1 {

  private static final Logger logger = Logger.getLogger(
        DCmdAccWFGenerarDecInformativaConsolidadav1Impl.class);

  //MENSAJES DE ERROR
  private final String ERROR_NO_DOC_130_ANTERIOR =
    "No se genero un documento 130 " +
    "para la solicitud anterior que se esta corrigiendo";

    private final String ERROR_DOC_INICIAL_EXISTE = "Ya existe una declaracion INICIAL para el a�o gravable. " +
    "No es posible presentar la declaraci�n ";

  private final String ERROR_DOC_130_ANTERIOR_NO_PRESNETADO =
    "Se genero un documento 130 " +
    "para la solicitud anterior que se esta corrigiendo pero este no ha sido presentado";

  private final String ERROR_NIT_DUPLICADO = "No se genero un documento 130 " +
    "porque se encontraron NIT duplicados enla solicitud No";

  private final int ORG_A_NOMBRE_PROPIO = 2;
  private final int NUM_FORMATO_CARGA = 1126;

  private static final int CASILLA_ANIO = 1;
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


  private static final int CASILLA_TOTAL_ENTIDADES_CONSOLIDADAS = 27;
  private static final int CASILLA_TOTAL_OPERACIONES_INGRESO = 28;
  private static final int CASILLA_TOTAL_OPERACIONES_EGRESO = 29;
  private static final int CASILLA_ACTIVO_MOVIMIENTO_DEBITO = 30;
  private static final int CASILLA_ACTIVO_MOVIMIENTO_CREDITO = 31;
  private static final int CASILLA_ACTIVO_SALDO = 32;
  private static final int CASILLA_PASIVO_MOVIMIENTO_DEBITO = 33;
  private static final int CASILLA_PASIVO_MOVIMIENTO_CREDITO = 34;
  private static final int CASILLA_PASIVO_SALDO = 35;

  //casillas del obligado a declarar

  private static final int CASILLA_PRESENTO_DEC_INDIV = 36;
  private static final int CASILLA_DEC_INDIVIDUAL = 37;

  private static final int CASILLA_BASE_LIQUIDACION = 46;
  private static final int CASILLA_SANCION = 47;
  private static final int CASILLA_DOCUMENTO_RADICACION = 48;
  private static final int CASILLA_980 = 980;
  private static final int CASILLA_986 = 986;


  private static final int CASILLA_24_1126 = 24;
  private static final int CASILLA_28_1126 = 28;
  private static final int CASILLA_30_1126 = 30;
  private static final int CASILLA_31_1126 = 31;
  private static final int CASILLA_32_1126 = 32;
  private static final int CASILLA_33_1126 = 33;
  private static final int CASILLA_34_1126 = 34;
  private static final int CASILLA_35_1126 = 35;
  private static final int CASILLA_36_1126 = 36;
  private static final int CASILLA_37_1126 = 37;

//Casillas del 120 para matriz
  private static final int CASILLA_4_120 = 4;
  private static final int CASILLA_28_120 = 28;
  private static final int CASILLA_29_120 = 29;
  private static final int CASILLA_30_120 = 30;
  private static final int CASILLA_31_120 = 31;
  private static final int CASILLA_32_120 = 32;
  private static final int CASILLA_33_120 = 33;
  private static final int CASILLA_34_120 = 34;
  private static final int CASILLA_35_120 = 35;
  private static final int CASILLA_36_120 = 36;

  private static final int CONCEPTO_CORRECCION = 2;
  private static final int AMPLITUD_INTERVALO = 100;

  protected static final int GRUPO = 1;
  protected static final int OCURRENCIA = 1;
  protected static final int CASILLA_CONCEPTO = 2;

  private static final int NUM_FORMATO = 130;
  private static final int NUM_FORMATO_INDIVIDUAL = 120;
  private static final int VERSION_FORMATO = 1;
  private static final int ORGANIZACION_NOMBRE_PROPIO = 2;
  
  


  String NOM_PARAM_ID_DOCUMENTO_DECLARACION = "idDocumentoDeclaracion";
  String NOM_PARAM_NUM_REPETICION_DECLARACION = "numRepeticionDeclaracion";
  int PARAM_IDE_TAREA = 27;
  int PARAM_TAREA_IDE_DOCUMENTO = 0;
  int PARAM_TAREA_NUM_REP_DOCUMENTO = 1;

  //Helpers
  DHelperExpedienteGeneracion130v1 manejadorExpedientes = null;

  public DCmdAccWFGenerarDecInformativaConsolidadav1Impl() {
  }

    /**
     * Este metodo valida que no se procesen dos cargas del mismo a�o.
     *
     * @return boolean
     * @param miSolicitud DSolicitudIngresoTO
     * @throws DExcepcion
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
    docGenExogenaAtt.setIdeFormato(new Integer(NUM_FORMATO));

    docGenExogenaAtt.setVersion(new Integer(VERSION_FORMATO));
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
              NUM_FORMATO_CARGA &&
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
    String nombreSrv =
      "entradasalida.exogena.DCmdSrvConsLstSolicitudExogena";
    consLstSolExogena = (DCmdSrvConsLstSolicitudExogena) getServicio(
      nombreSrv);
    consLstSolExogena.inicializar(documentoPK);
    consLstSolExogena.ejecutar();

    return consLstSolExogena.getColeccionSolicitudExogena();
  }

  private DHelperExpedienteGeneracion130v1 getManejadorExpedientes() {
    if (manejadorExpedientes == null) {
      manejadorExpedientes = new DHelperExpedienteGeneracion130v1(this);
    }
    return manejadorExpedientes;
  }

  protected void ejecutarComandoSinTransaccion() {
    logger.info("Inicio WF Decl. Inf. Precios de Transferencia Consolidada v1");
    Set nits = new TreeSet();

    int registroActual = 1;
    String ideArchivosProcesados = "";
    try {
      documentoCargaPK = (DDocumentoPKTO) getDocumentos().iterator().next();

      solicitud = consultarSolicitud(documentoCargaPK.getIdeDocumento());

      procesarValido = validarProcesamiento(solicitud);

      if (procesarValido) {
        logger.info("Proceso valido. Se va a iniciar la carga");
        DSolicitudIngresoPKTO solicitudIngresoPK = new
          DSolicitudIngresoPKTO(
          documentoCargaPK.getIdeDocumento());

        DCmdSrvConsLstSolicitudArchivo srvConsLstConsArchivo = (
          DCmdSrvConsLstSolicitudArchivo) getServicio(
          "cargamasiva.procesamiento.DCmdSrvConsLstSolicitudArchivo");
        srvConsLstConsArchivo.inicializarConsultarPorSolicitudIngreso(
          solicitudIngresoPK);
        srvConsLstConsArchivo.ejecutar();

        DCmdSrvConsSolicitudIngreso srvConsSolIngreso = (
          DCmdSrvConsSolicitudIngreso) getServicio(
          "cargamasiva.procesamiento.DCmdSrvConsSolicitudIngreso");
        srvConsSolIngreso.inicializar(solicitudIngresoPK);
        srvConsSolIngreso.ejecutar();
        solicitudIngresoTO = srvConsSolIngreso.getSolicitudIngreso();

        DCmdSrvConsDocumentosES srvConsDoc = (DCmdSrvConsDocumentosES)this.
          getServicio(
          "entradasalida.documentos.DCmdSrvConsDocumentosES");

        Collection coleccionArchivos = srvConsLstConsArchivo.
          getColeccionSolicitudArchivo();

        Iterator iteradorColeccionArchivos = coleccionArchivos.iterator();
        //logger.info("Se va a iniciar el proceso de carga: ");
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

            for (Iterator iter = documentosCargados.iterator(); iter.hasNext(); ) {

              IDDocumento itemDocumento = dehomologar((IDDocumento) iter.next());

              IDOcurrencia ocurrencia = itemDocumento.getGrupos().getGrupo(1).
                getOcurrencia(1);
              long operacionesIngreso = 0;
              long operacionesEgreso = 0;
              long activoMovimientoDebito = 0;
              long activoMovimientoCredito = 0;
              long activoSaldo = 0;
              long pasivoMovimientoDebito = 0;
              long pasivoMovimientoCredito = 0;
              long pasivoSaldo = 0;

              if (ocurrencia.getValorCasilla(CASILLA_30_1126) != null &&
                ocurrencia.getValorCasilla(CASILLA_30_1126).
                getValorEntero() != null) {
                operacionesIngreso = ocurrencia.getValorCasilla(
                  CASILLA_30_1126).getValorEntero().
                  longValue();
              }

              if (ocurrencia.getValorCasilla(CASILLA_31_1126) != null &&
                ocurrencia.getValorCasilla(CASILLA_31_1126).
                getValorEntero() != null) {
                operacionesEgreso = ocurrencia.getValorCasilla(
                  CASILLA_31_1126).getValorEntero().
                  longValue();
              }

              if (ocurrencia.getValorCasilla(CASILLA_32_1126) != null &&
                ocurrencia.getValorCasilla(CASILLA_32_1126).
                getValorEntero() != null) {
                activoMovimientoDebito = ocurrencia.getValorCasilla(
                  CASILLA_32_1126).getValorEntero().
                  longValue();

              }

              if (ocurrencia.getValorCasilla(CASILLA_33_1126) != null &&
                ocurrencia.getValorCasilla(CASILLA_33_1126).
                getValorEntero() != null) {
                activoMovimientoCredito = ocurrencia.getValorCasilla(
                  CASILLA_33_1126).getValorEntero().
                  longValue();

              }

              if (ocurrencia.getValorCasilla(CASILLA_34_1126) != null &&
                ocurrencia.getValorCasilla(CASILLA_34_1126).
                getValorEntero() != null) {
                activoSaldo = ocurrencia.getValorCasilla(
                  CASILLA_34_1126).getValorEntero().
                  longValue();

              }

              if (ocurrencia.getValorCasilla(CASILLA_35_1126) != null &&
                ocurrencia.getValorCasilla(CASILLA_35_1126).
                getValorEntero() != null) {
                pasivoMovimientoDebito = ocurrencia.getValorCasilla(
                  CASILLA_35_1126).getValorEntero().
                  longValue();

              }

              if (ocurrencia.getValorCasilla(CASILLA_36_1126) != null &&
                ocurrencia.getValorCasilla(CASILLA_36_1126).
                getValorEntero() != null) {
                pasivoMovimientoCredito = ocurrencia.getValorCasilla(
                  CASILLA_36_1126).getValorEntero().
                  longValue();

              }

              if (ocurrencia.getValorCasilla(CASILLA_37_1126) != null &&
                ocurrencia.getValorCasilla(CASILLA_37_1126).
                getValorEntero() != null) {
                pasivoSaldo = ocurrencia.getValorCasilla(
                  CASILLA_37_1126).getValorEntero().
                  longValue();

              }

              totalOperacionesIngreso += operacionesIngreso;
              totalOperacionesEgreso += operacionesEgreso;
              totalActivoMovimientoDebito += activoMovimientoDebito;
              totalActivoMovimientoCredito += activoMovimientoCredito;
              totalActivoSaldo += activoSaldo;
              totalPasivoMovimientoDebito += pasivoMovimientoDebito;
              totalPasivoMovimientoCredito += pasivoMovimientoCredito;
              totalPasivoSaldo += pasivoSaldo;

              nit = new Long(ocurrencia.getValorCasilla(
                CASILLA_NUMERO_IDENTIFICACION_TRIBUTARIA).
                getValorEntero().
                longValue());
              dv = new Long(ocurrencia.getValorCasilla(CASILLA_DV).
                getValorEntero().
                longValue());

              String nit = (ocurrencia.getValorCasilla(CASILLA_24_1126) !=
                null && ocurrencia.getValorCasilla(CASILLA_24_1126).
                getValorEntero() != null) ? ocurrencia.
                getValorCasilla(CASILLA_24_1126).getValorEntero().toString() :
                "";

              nits.add(new String(nit));

            }
            registroActual = registroActual + AMPLITUD_INTERVALO;
            srvConsDoc.inicializar(documentoCargaPK.getIdeDocumento(),
              ideRecursoArchivo, registroActual,
              AMPLITUD_INTERVALO);
            srvConsDoc.ejecutar();
            documentosCargados = srvConsDoc.getDocumentos();
          }
          ideArchivosProcesados = ideArchivosProcesados + " " +
            ideRecursoArchivo;
        }

        int numItem = solicitud.getSolicitudAtt().getNumTotalRegistros().
          intValue();

        if (nits.size() != numItem) {
          procesarValido = false;
          tipoError = ERROR_NIT_DUPLICADO + solicitud.
            getSolicitudPK().getIdeSolicitud();
        }

        long milisFin = System.currentTimeMillis();
        long tiempo = milisFin - milisInicio;

        num_item = solicitud.getSolicitudAtt().getNumTotalRegistros().longValue();

        idPersona = consultarIdPersonaRut(nit.longValue());

        annoSolicitud = solicitud.getSolicitudAtt().getAnioVigencia().
          intValue();

        if (procesarValido) {
          procesarValido = validarDocCorreccion(solicitud);
        }
      }
      isOk = true;

    } catch (DExcepcion ex) {
      isFinalizado = true;
      isOk = false;
    }
  }

  public boolean validarDocCorreccion(DSolicitudIngresoTO unaSolicitud) throws
    DExcepcion {

    //Verifica si el documento es de correccion.
    //En caso de serlo le agrega el id del docuemnto 120 anterior. 120 o 130 anterior??BMR

    boolean correccionValida = true;
    boolean buscarDocAnterior = true;
    boolean esCorreccion = false;

    DCmdSrvConsSolicitudExogena consSolExogena = null;
    String nombreSrv = "entradasalida.exogena.DCmdSrvConsSolicitudExogena";

    DCmdSrvConsDocGenExogena consDocGenExogena = null;
    String nombreSrv2 = "entradasalida.exogena.DCmdSrvConsDocGenExogena";

    DCmdSrvConsSolicitudIngreso consSolIngreso = null;
    String srvSolIngreso =
      "cargamasiva.procesamiento.DCmdSrvConsSolicitudIngreso";

    if (unaSolicitud.getSolicitudAtt().getNumeroDocumentoAnterior() != null) {

      esCorreccion = true;

      consSolExogena = (DCmdSrvConsSolicitudExogena) getServicio(nombreSrv);
      consSolExogena.inicializarPorIdeSolicitud(new DSolicitudExogenaPKTO(null,
        unaSolicitud.getSolicitudAtt().getNumeroDocumentoAnterior()));
      consSolExogena.ejecutar();
      DSolicitudExogenaTO solExogena = consSolExogena.getSolicitudExogena();

      if (solExogena != null) {
        idDocumentoCorreccion = solExogena.getPK().getIdeDocumentoGen().
          longValue();
        consDocGenExogena = (DCmdSrvConsDocGenExogena) getServicio(
          nombreSrv2);
        DDocGenExogenaPKTO docGenPK = new DDocGenExogenaPKTO(new Long(
          idDocumentoCorreccion), new Integer(1));

        consDocGenExogena.inicializar(docGenPK);
        consDocGenExogena.ejecutar();

        if (consDocGenExogena.getDocGenExogena().
          getAtt().getCodEstado().intValue() ==
          IDDocumento.IDE_ESTADO_PRESENTADO) {

          buscarDocAnterior = false;
          correccionValida = true;

        }
        else {
          tipoError = ERROR_DOC_130_ANTERIOR_NO_PRESNETADO;
          correccionValida = false;
        }
      }

      if (buscarDocAnterior) {

        Long solIngreso = unaSolicitud.getSolicitudAtt().
          getNumeroDocumentoAnterior();
        consSolIngreso = (DCmdSrvConsSolicitudIngreso) getServicio(
          srvSolIngreso);
        consSolIngreso.inicializar(new DSolicitudIngresoPKTO(solIngreso));
        consSolIngreso.ejecutar();

        //Llamado recursivo hasta encontrar una solicitud valida.
        correccionValida = validarDocCorreccion(consSolIngreso.
          getSolicitudIngreso());
      }
      
      idDocumentoCorreccion = solExogena.getPK().getIdeDocumentoGen().longValue();
      repeticionDocCorreccion= solExogena.getPK().getRepeticionDocGen().intValue();

		correccionValida = true;
      
       IDDocumento docACorregir = obtenerDocumentoMUISCA(idDocumentoCorreccion, repeticionDocCorreccion , NUM_FORMATO);
		if (docACorregir == null) {
			throw new DExcepcion("Error", "No se encontro como " + "documento definitivo el documento "
				+ idDocumentoCorreccion + '-' + repeticionDocCorreccion  + " que corrige la solicitud");
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
   */
  protected void ejecutarComandoWF() {
    try {
      if (procesarValido) {
        Long idDeclaracionCorregida = null;
        documento130 = crearDocumento(idPersona.longValue());

        IDOcurrencia ocurrencia = documento130.getGrupos().getGrupo(1).
          getOcurrencia(1);
        //se adiciona las casillas de totales
        ocurrencia.getValorCasilla(CASILLA_TOTAL_ENTIDADES_CONSOLIDADAS).
          setValor(new Long(num_item));
        ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_CONS_OPERACIONES_INGRESO).
          setValor(new
          Long(totalOperacionesIngreso));
        ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_CONS_OPERACIONES_EGRESO ).
          setValor(new
          Long(totalOperacionesEgreso));
        ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_CONS_MOV_DEBITO_ACTIVO ).
          setValor(new
          Long(totalActivoMovimientoDebito));
        ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_CONS_MOV_CREDITO_ACTIVO).
          setValor(new
          Long(totalActivoMovimientoCredito));
        ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_CONS_SALDO_FINAL_ACTIVO).setValor(new
          Long(totalActivoSaldo));

        ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_CONS_MOV_DEBITO_PASIVO).
          setValor(new
          Long(totalPasivoMovimientoDebito));
        ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_CONS_MOV_CREDITO_PASIVO).
          setValor(new
          Long(totalPasivoMovimientoCredito));
        ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_CONS_SALDO_FINAL_PASIVO).setValor(new
          Long(totalPasivoSaldo));

        // Manejo de individual para matriz
        DCmdAccConsIdentificadorDoc consIden = (DCmdAccConsIdentificadorDoc)
          getAccion("entradasalida.DCmdAccConsIdentificadorDoc");
        DIdentificadorDocumentoTO idenDocTo = new DIdentificadorDocumentoTO();

        DRegistroRutTO to = null;

        idenDocTo.setIdFormato(new Integer(NUM_FORMATO_INDIVIDUAL));

        logger.info("Se imprime parcialmente el XML del 130: \n" + DDocumentoUtil.obtenerXmlDeDocumento(documento130));

        boolean isPeriodo = false;

        Integer CAS_ANNO = new Integer(1);
        Integer ANNO_GRAV = 2011;

        Map casCab = new HashMap();
        casCab.put(CAS_ANNO, ANNO_GRAV);
        casCab.put(IDConstantesFormato.IDE_CASILLA_NIT, nit);

        int version120 = VERSION_FORMATO;

        if(annoSolicitud >= 2011)
        	version120 = 3;
        if(annoSolicitud == 2012)
            isPeriodo = true;
        
        logger.info("El a�o de solicitud es :" + annoSolicitud);
        logger.info("La versi�n del formato del 120 es :" + version120);
        logger.info("El a�o gravable del 120 es :" + ANNO_GRAV);
        
        idenDocTo.setNumVersion(new Integer(version120));

        idenDocTo.setCasillasCabPie(casCab);

        consIden.inicializarConsGenerica(idenDocTo, true);
        consIden.ejecutar();
        
        Collection identificadores = consIden.getIdentificadoresDoc();
        
        logger.info("Existe el siguiente n�mero de identificadores " + identificadores.size());
        
        
        DIdentificadorDoc idenDoc = null;
        
        //compare
        if (identificadores != null && !identificadores.isEmpty()) {
        	
        	logger.info("Existe el siguiente n�mero de identificadores " + identificadores.size());
        	
        	Iterator itTemp = identificadores.iterator();
        	
        	while(itTemp.hasNext()){
        		DIdentificadorDoc ident = (DIdentificadorDoc)itTemp.next();
        		logger.info("Declaraci�n 120 encontrada: " + ident.getIdDocumento());
        	}
        	
            // El �ltimo por fecha
            for(Iterator it = identificadores.iterator(); it.hasNext();){
              DIdentificadorDoc idenTemp = (DIdentificadorDoc)it.next();
              String cadClavIden = idenTemp.getClaveNegocio();

              if(isPeriodo && (cadClavIden.indexOf("26=1") != -1
                 || cadClavIden.toLowerCase().indexOf("26=true") != -1)){
                  idenDoc = idenTemp;
                  break;
              }else if(!isPeriodo && cadClavIden.indexOf("26=1") == -1
                 && cadClavIden.toLowerCase().indexOf("26=true") == -1){
                  idenDoc = idenTemp;
                  break;
              }
            }
        }
        
        
        
        if (idenDoc != null) {
          logger.info("El Identificador del documento no es nulo");
          
          DCmdAccConsDocumentoMUISCA consDoc = (DCmdAccConsDocumentoMUISCA)
            getAccion(
            "entradasalida.DCmdAccConsDocumentoMUISCA");
          consDoc.inicializar(idenDoc.getIdDocumento(), 1, NUM_FORMATO_INDIVIDUAL);
          consDoc.ejecutar();
          documento120 = consDoc.getHomologacion();

          IDOcurrencia ocurrenciaInd = documento120.getGrupos().getGrupo(1).
            getOcurrencia(1);

          ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_INDICADOR_PRESENTO_DEC_INDIVIDUAL).
            setValor("1");
          ocurrencia.getValorCasilla(CASILLA_DEC_INDIVIDUAL).
            setValor(ocurrenciaInd.getValorCasilla(CASILLA_4_120).
            getValorEntero());
          if(version120 == 3)
          {	
        	  		ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_OPERACIONES_INGRESO).
        	  		  setValor(ocurrenciaInd.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_OPERACIONES_INGRESO).
                      getValorEntero());
                    ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_OPERACIONES_EGRESO).
                      setValor(ocurrenciaInd.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_OPERACIONES_EGRESO).
                      getValorEntero());
                    ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MOVIMIENTO_DEBITO_SALDO_ACTIVO).
                      setValor(ocurrenciaInd.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MOVIMIENTO_DEBITO_SALDO_ACTIVO ).
                      getValorEntero());
                    ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MOVIMIENTO_CREDITO_SALDO_ACTIVO).
                      setValor(ocurrenciaInd.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MOVIMIENTO_CREDITO_SALDO_ACTIVO).
                      getValorEntero());
                    ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SALDO_FINAL_SALDO_ACTIVO).
                      setValor(ocurrenciaInd.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SALDO_FINAL_SALDO_ACTIVO).
                      getValorEntero());
                    ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MOVIMIENTO_DEBITO_SALDO_PASIVO).
                      setValor(ocurrenciaInd.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MOVIMIENTO_DEBITO_SALDO_PASIVO).
                      getValorEntero());
                    ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MOVIMIENTO_CREDITO_SALDO_PASIVO).
                      setValor(ocurrenciaInd.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MOVIMIENTO_CREDITO_SALDO_PASIVO ).
                      getValorEntero());
                    ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SALDO_FINAL_SALDO_PASIVO).
                      setValor(ocurrenciaInd.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SALDO_FINAL_SALDO_PASIVO).
                      getValorEntero());
          }

        }
        else {
          ocurrencia.getValorCasilla(CASILLA_PRESENTO_DEC_INDIV).
            setValor("2");

          ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_OPERACIONES_INGRESO).
            setValor("0");
          ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_OPERACIONES_EGRESO).
            setValor("0");
          ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MOVIMIENTO_DEBITO_SALDO_ACTIVO).
            setValor("0");
          ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MOVIMIENTO_CREDITO_SALDO_ACTIVO).
            setValor("0");
          ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SALDO_FINAL_SALDO_ACTIVO).
            setValor("0");
          ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MOVIMIENTO_DEBITO_SALDO_PASIVO).
            setValor("0");
          ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MOVIMIENTO_CREDITO_SALDO_PASIVO).
            setValor("0");
          ocurrencia.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SALDO_FINAL_SALDO_PASIVO).
            setValor("0");

        }

        ocurrencia.getValorCasilla(
          CASILLA_NUMERO_IDENTIFICACION_TRIBUTARIA).
          setValor(nit);
        ocurrencia.getValorCasilla(CASILLA_DV).setValor(dv);



        int annioActual = Calendar.getInstance().get(Calendar.YEAR);
        if (annoSolicitud == annioActual) {
          ocurrencia.getValorCasilla(CASILLA_ANIO).setValor(new Integer(
            annoSolicitud - 1));
          ocurrencia.getValorCasilla(CASILLA_FRACCION_ANIO).
            setValorCadena("true");
        }
        else  {
          ocurrencia.getValorCasilla(CASILLA_ANIO).setValor(new Integer(
            annoSolicitud));
          ocurrencia.getValorCasilla(CASILLA_FRACCION_ANIO).
            setValorCadena("false");
        }

        //Casilla 995
        ocurrencia.getValorCasilla(IDConstantesFormato.
          IDE_CASILLA_ORIGEN_DECLARACION).
          setValor(new Integer(IDTiposOrigenDeclaracion.INTERNO_PRIVADA));

        if (personaRut.getAtt().getNomPrimerApellido() != null) {
          ocurrencia.getValorCasilla(CASILLA_PRIMER_APELLIDO).
            setValor(
            personaRut.getAtt().getNomPrimerApellido());

        }

        if (idDocumentoCorreccion != 0) {

          ocurrencia.getValorCasilla(CODIGO_CORRECCION).
            setValor("29700001");
          ocurrencia.getValorCasilla(CASILLA_CORRECCION).
            setValor(new Long(idDocumentoCorreccion));
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
        //cambie por getNomOtrosNombres(), por que estaba getNomOtrosNombres() en v6
        if (personaRut.getAtt().getNomOtrosNombres() != null) {
          ocurrencia.getValorCasilla(CASILLA_OTROS_NOMBRES).setValor(
            personaRut.getAtt().getNomOtrosNombres());

        }

        if (personaRut.getAtt().getNomRazonSocial() != null) {
          ocurrencia.getValorCasilla(CASILLA_RAZON_SOCIAL).setValor(
            personaRut.getAtt().getNomRazonSocial());
        }

        ocurrencia.getValorCasilla(CASILLA_DOCUMENTO_RADICACION).
          setValor(
          documentoCargaPK.getIdeDocumento());
        // Se setean estos valores por que son obligatorios y tipo entrada 5 esta estricto S
        ocurrencia.getValorCasilla(CASILLA_BASE_LIQUIDACION).
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
        ocurrencia.getValorCasilla(CASILLA_980).
          setValor("0");
        ocurrencia.getValorCasilla(CASILLA_986).
          setValor("1");

        logger.info(
          "Va a guardar el documento 130 generado para la solicitud: " +
          documentoCargaPK.getIdeDocumento());

        identificadorDoc = salvarDocumento(documento130);
        anexarDocumento(identificadorDoc.getIdDocumento(),
          identificadorDoc.getNumRepeticion(),
          identificadorDoc.getIdFormato(),
          identificadorDoc.getVersion(),
          identificadorDoc.getIdEstado());

        DTareaNegTO tareaNegTo =
          getManejadorExpedientes().procesarPresentacionPorEnvioArchivos(
          documentoCargaPK.getIdeDocumento(), documento130, new Date());
        if (tareaNegTo != null) {
          crearSolicitudExogena(null, tareaNegTo.getPK().getIdeDocumentoTarea(),
            tareaNegTo.getPK().getNumRepeticionDocTarea());
        }

      }
      else {
        getManejadorExpedientes().procesarInconsistencia(tipoError,
          documentoCargaPK.getIdeDocumento());
        Long ideTarea = notificarResponsable(solicitud, tipoError);
      }

      isFinalizado = true;
      isOk = true;
      //logger.info("Finalizada ejecucion del WF de Precios de transferencia " +
      //  "Consolidada terminado con exito");
    } catch (DExcepcion ex) {
      //logger.error("Error al ejecutar ejecutarComandoWF(), ", ex);
      isFinalizado = true;
      isOk = false;
    }
    logger.info("Finalizada ejecucion del WF Decl. Inf. Precios de " +
      "Transferencia Consolidada v1");

  }


  // m�todos privados

  private Long consultarIdPersonaRut(long nit) throws DExcepcion {
    DCmdSrvConsPersonaRut srvConsRut = (DCmdSrvConsPersonaRut)this.
      getServicio(
      "rut.DCmdSrvConsPersonaRut");
    srvConsRut.inicializarConsultarPorNIT(nit);
    srvConsRut.ejecutar();
    personaRut = srvConsRut.getPersonaRut();
    DCmdSrvConsMascaraRut srvConsMascara = (DCmdSrvConsMascaraRut)this.
      getServicio(
      "rut.DCmdSrvConsMascaraRut");
    srvConsMascara.inicializarConsultarPorIdeMascaraRut(personaRut.getPK().
      getIdePersonaRut());
    srvConsMascara.ejecutar();
    return srvConsMascara.getMascaraRut().getPK().getIdePersonaRut();
  }

  private DSolicitudIngresoTO consultarSolicitud(Long idSolicitud) throws
    DExcepcion {
    DCmdSrvConsSolicitudIngreso srvConsSol = (
      DCmdSrvConsSolicitudIngreso) getServicio(
      "cargamasiva.procesamiento.DCmdSrvConsSolicitudIngreso");

    srvConsSol.inicializar(new DSolicitudIngresoPKTO(idSolicitud));

    srvConsSol.ejecutar();
    return srvConsSol.getSolicitudIngreso();
  }

  private IDDocumento crearDocumento(long idPersonaRut) throws DExcepcion {
    DCmdAccLlenarDocumentoNuevoConsRut accCrearDocumento = (
      DCmdAccLlenarDocumentoNuevoConsRut)this.getAccion(
      "entradasalida.consintegral.DCmdAccLlenarDocumentoNuevoConsRut");
    accCrearDocumento.inicializarPorIdePersonaRut(
      NUM_FORMATO,
      VERSION_FORMATO,
      idPersonaRut,
      IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO);
    accCrearDocumento.ejecutar();
    IDDocumento documento = accCrearDocumento.getDocumento();
    return documento;
  }

  private DIdentificadorDoc salvarDocumento(IDDocumento doc) throws
    DExcepcion {

    DIdentificadorDoc identificadorDoc = new DIdentificadorDoc();
    DCmdSrvCrearDocumentoES dCmdSrvCrearDocumentoES = (DCmdSrvCrearDocumentoES)
      getServicio("entradasalida.documentos.DCmdSrvCrearDocumentoES");

    // modo negocio
    int modoDil =
      solicitudIngresoTO.getSolicitudAtt().getCodConcepto().intValue() ==
      CONCEPTO_CORRECCION ?
      IDModosDiligenciamiento.MODO_CORRECCION :
      IDModosDiligenciamiento.MODO_INICIAL;

    dCmdSrvCrearDocumentoES.inicializarSinId(doc,
      IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO);
    dCmdSrvCrearDocumentoES.setEstadoExterno(
      new Integer(IDDocumento.IDE_ESTADO_TEMPORAL));
    dCmdSrvCrearDocumentoES.getIdentificadorDoc().setIdModoNegocio(modoDil);
    dCmdSrvCrearDocumentoES.ejecutar();
    identificadorDoc = dCmdSrvCrearDocumentoES.getIdentificadorDoc();

    return identificadorDoc;
  }

  private Long notificarResponsable(DSolicitudIngresoTO miSolicitud,
                                    String msjNotificacion) throws DExcepcion {

      String urlTarea = "";

      if (procesarValido) {

          // Anexar el numero de solicitud al mensaje
          msjNotificacion += Long.toString(identificadorDoc.getIdDocumento());
          urlTarea = "/WebDiligenciamientomasivo/DefPreciosTransConsolidadav1.faces";
      }

      msjNotificacion += " generada por la solicitud " +
                  documentoCargaPK.getIdeDocumento();

      // Consultar el usuario con el id de la persona remitente
      Long idPersona = solicitud.getSolicitudAtt().getIdePersona();
      Long idTarea = null;
      DUsuarioTO usr = new DUsuarioTO();
      DUsuarioAttTO att = new DUsuarioAttTO();
      att.setIdePersonaRut(idPersona);

      usr.setUsuarioAtt(att);
      DCmdSrvConsUsuarios srvConsUsuarios = (DCmdSrvConsUsuarios) getServicio(
              "arquitectura.seguridad.DCmdSrvConsUsuarios");
      srvConsUsuarios.iniciar(usr);
      srvConsUsuarios.ejecutar();
      Long idUsuario = srvConsUsuarios.getUsuarioTO().getUsuarioPK().getId();

      // Consultar el usuario y rol para enviar la tarea
      Integer idOrg = solicitud.getSolicitudAtt().getIdeOrganizacion();
      DCmdSrvConsUsuarioRol srvUsuario = (DCmdSrvConsUsuarioRol) getServicio(
              "arquitectura.seguridad.DCmdSrvConsUsuarioRol");
      DUsuarioRolPKTO pkUsuario = new DUsuarioRolPKTO();
      pkUsuario.setIdeUsuario(idUsuario);
      pkUsuario.setIdeOrganizacion(idOrg);
      srvUsuario.inicializarConsultaGenericaPorPK(pkUsuario);
      srvUsuario.ejecutar();
      Collection usuarios = srvUsuario.getRolesUsuario();
      if (usuarios != null && !usuarios.isEmpty()) {
          DUsuarioRolTO usuario = (DUsuarioRolTO) usuarios.iterator().next();
          pkUsuario = usuario.getUsuarioRolPK();
          // Enviar mensaje de notificacion a la bandeja de tareas
          DCmdSrvCrearTarea srvTarea = (DCmdSrvCrearTarea) getServicio(
                  "arquitectura.DCmdSrvCrearTarea");
          DTareaAttTO tarea = new DTareaAttTO(idUsuario,
                                              idOrg, pkUsuario.getIdeLugar(),
                                              pkUsuario.getIdeTipoOrganizacion(),
                                              pkUsuario.getIdeUnidadAdministrativa(),
                                              pkUsuario.getIdeRol(),
                                              new Integer(PARAM_IDE_TAREA),
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
          srvTarea.ejecutar();
          DTareaPKTO tareaPK = srvTarea.getTareaPK();
          idTarea = tareaPK.getIdeTarea();

      }
      return idTarea;
  }

  private Collection construirParametros() {

    Double idDocGen = new Double( -1);
    Double repDocGen = new Double( -1);

    if (procesarValido) {
      idDocGen = new Double(identificadorDoc.getIdDocumento());
      repDocGen = new Double(identificadorDoc.getNumRepeticion());
    }

    Collection parametros = new ArrayList();

    DParametroTareaPKTO paramPK = new DParametroTareaPKTO(null, null);
    DParametroTareaAttTO paramAtt = new DParametroTareaAttTO(
      NOM_PARAM_ID_DOCUMENTO_DECLARACION,
      new Character('N'),
      new BigDecimal(idDocGen.longValue()),
      null, null);
    paramAtt.setIdeParametroTareaDef(new Short((short)
      PARAM_TAREA_IDE_DOCUMENTO));
    paramAtt.setIdeTareaDef(new Integer(PARAM_IDE_TAREA));
    DParametroTareaTO param = new DParametroTareaTO(paramPK, paramAtt);
    parametros.add(param);

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

    return parametros;
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
      IDE_ESTADO_TEMPORAL)));

    docGenExogenaAtt.setIdeFormato(new Integer(NUM_FORMATO));
    docGenExogenaAtt.setVersion(new Integer(VERSION_FORMATO));

    if (idDocumentoCorreccion != 0) {
      concepto = 2;
      docGenExogenaAtt.setIdeDeclaracionCorregida(new Long(
        idDocumentoCorreccion));
      docGenExogenaAtt.setRepeticionDeclaracionCorregida(new Integer(1));
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

  private IDDocumento dehomologar(IDDocumento documento) throws DExcepcion {
    DCmdSrvHomologacionPnuts srvHomologarPnuts = (DCmdSrvHomologacionPnuts)this.
      getServicio("entradasalida.cargamasiva.DCmdSrvHomologacionPnuts");

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
  private IDDocumento obtenerDocumentoMUISCA(long ideDocumento,
		    int numRepeticion, int ideFormato) throws DExcepcion {
		DBusServiciosEYSDelegateTxNueva delegado = new DBusServiciosEYSDelegateTxNueva();
		delegado.setTransaccional(true);
		DCmdSrvConsDocumentoMUISCA srvConsDoc = (DCmdSrvConsDocumentoMUISCA) delegado
				.getComando("entradasalida.documentos.DCmdSrvConsDocumentoMUISCA");
		srvConsDoc.inicializar(ideDocumento, numRepeticion, ideFormato);
		srvConsDoc.ejecutar();
		return srvConsDoc.getDocumento();
	}
}
