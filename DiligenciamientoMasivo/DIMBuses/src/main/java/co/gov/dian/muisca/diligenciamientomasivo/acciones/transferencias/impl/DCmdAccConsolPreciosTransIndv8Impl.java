package co.gov.dian.muisca.diligenciamientomasivo.acciones.transferencias.impl;

import java.util.Collection;
import java.util.Iterator;

import co.gov.dian.muisca.arquitectura.acciones.DCmdAccTerminarTarea;
import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.entradasalida.acciones.DCmdAccCrearDocumentoESTemporal;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.transferencias.DCmdAccConsolPreciosTransIndv8;
import co.gov.dian.muisca.entradasalida.documento.DDocumentoUtil;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;
import co.gov.dian.muisca.entradasalida.formatos.IDFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDModosDiligenciamiento;
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
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoES;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentosES;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvActDocGenExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvConsSolicitudExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvCrearSolicitudExogena;
import org.apache.log4j.Logger;

public class DCmdAccConsolPreciosTransIndv8Impl extends DCmdAccConsolPreciosTransIndv8{

    Logger logger = Logger.getLogger(DCmdAccConsolPreciosTransIndv8Impl.class);
    private final int CASILLA_ACCIONISTAS_RELACIONADOS = 28;
    private final int CASILLA_SOLICITUD_1124 = 47;

    private static final int CASILLA_31_1124 = 31;
    private static final int CASILLA_25_1124 = 25;

    private static final int AMPLITUD_INTERVALO = 100;

    private Logger LOGGER = Logger.getLogger(DCmdAccConsolPreciosTransIndv8Impl.class);

    /** */
    private static final int IDE_FORMATO = 120;
    /** */
    private static final int NUM_VERSION = 8;


    protected void ejecutarComando(){

        try {
            IDDocumento docConsolidado = obtenerDocumento();

            if(docSolicitud != null && docSolicitud.getIdeDocumento() != null){

                DSolicitudIngresoTO solicitudTO = consultarSolicitud(docSolicitud.getIdeDocumento());

                long numItem = solicitudTO.getSolicitudAtt().
                               getNumTotalRegistros().longValue();

                validarPorcentaje(solicitudTO);

                IDOcurrencia ocurrencia = docConsolidado.getGrupos().getGrupo(1).
                                          getOcurrencia(1);

                ocurrencia.getValorCasilla(CASILLA_ACCIONISTAS_RELACIONADOS).
                        setValor(new Long(numItem));

                ocurrencia.getValorCasilla(CASILLA_SOLICITUD_1124).
                        setValor(solicitudTO.getSolicitudPK().getIdeSolicitud());

                actualizarDocumento(docConsolidado);

                crearSolicitudExogena();
            }

            /**
             * Cambio estado doc gen exogena
             */
            DCmdSrvActDocGenExogena miComandoExogena = null;
            String nombreSrv = "entradasalida.exogena.DCmdSrvActDocGenExogena";
            miComandoExogena = (DCmdSrvActDocGenExogena)getServicio(nombreSrv);

            DDocGenExogenaTO docGenExogenaTo = new DDocGenExogenaTO();
            DDocGenExogenaPKTO docGenEsogenaPk = new DDocGenExogenaPKTO();
            DDocGenExogenaAttTO docGenEsogenaAtt = new DDocGenExogenaAttTO();

            docGenEsogenaPk.setIdeDocumentoGen(new Long(docConsolidado.
                                                        getId()));
            docGenEsogenaPk.setNumRepeticion(new Integer(docConsolidado.
                                                         getRepeticion()));

            docGenEsogenaAtt.setCodEstado(new Byte((byte) IDDocumento.
                                                   IDE_ESTADO_TEMPORAL));

            docGenExogenaTo.setPk(docGenEsogenaPk);

            docGenExogenaTo.setAtt(docGenEsogenaAtt);

            miComandoExogena.inicializarActEstado(docGenExogenaTo);

            miComandoExogena.ejecutar();

            eliminaTarea();

            isOk = true;

        } catch (DExcepcion ex) {
            logger.error("Error al ejecutar ejecutarComandoSinTransaccion(), ", ex);
            setExcepcion(ex);
            this.mensajeError = ex.getMessage();
            this.mensajeErrorDetallado = ex.getMensajeDetallado();
            isOk = false;
        }
    }

    public void validarPorcentaje(DSolicitudIngresoTO unaSolicitudTO) throws DExcepcion {

        int registroActual;

        DSolicitudIngresoPKTO solicitudIngresoPK = unaSolicitudTO.getSolicitudPK();
        documentoCargaPK = new DDocumentoPKTO(unaSolicitudTO.getSolicitudPK().getIdeSolicitud(),
                                              new Integer("1"));

        DCmdSrvConsLstSolicitudArchivo srvConsLstConsArchivo = null;
        String nombreSrv = "entradasalida.cargamasiva.DCmdSrvConsLstSolicitudArchivo";
        srvConsLstConsArchivo = (DCmdSrvConsLstSolicitudArchivo) getServicio(nombreSrv);
        srvConsLstConsArchivo.inicializarConsultarPorSolicitudIngreso(solicitudIngresoPK);
        srvConsLstConsArchivo.ejecutar();

        Collection coleccionArchivos = srvConsLstConsArchivo.getColeccionSolicitudArchivo();

        Iterator iteradorColeccionArchivos = coleccionArchivos.iterator();
        LOGGER.info("Se va a iniciar el proceso de carga: ");

        DCmdSrvConsDocumentosES srvConsDoc = null;
        String nombreSrv2 = "entradasalida.documentos.DCmdSrvConsDocumentosES";
        srvConsDoc = (DCmdSrvConsDocumentosES)this.getServicio(nombreSrv2);


        while (iteradorColeccionArchivos.hasNext()) {
            registroActual = 1;
            float porcentaje = 0;
            int extremoSuperiorIntervalo;
            DSolicitudArchivoTO solictidArchivoTO = (DSolicitudArchivoTO)
                                                    iteradorColeccionArchivos.next();
            Long ideRecursoArchivo = solictidArchivoTO.getPK().getIdeRecursoArchivo();
            srvConsDoc.inicializar(documentoCargaPK.getIdeDocumento(),
                                   ideRecursoArchivo, registroActual,
                                   AMPLITUD_INTERVALO);
            srvConsDoc.ejecutar();
            documentosCargados = srvConsDoc.getDocumentos();

            while (documentosCargados != null && documentosCargados.size() > 0) {
                extremoSuperiorIntervalo = registroActual + AMPLITUD_INTERVALO - 1;

                LOGGER.info("Procesando solicitud: " + documentoCargaPK.getIdeDocumento() +
                            ", recurso archivo " + ideRecursoArchivo + ", items: " +
                            registroActual + " - " + extremoSuperiorIntervalo);

                //Se recorren los documentos de la carga masiva (1124)
                for (Iterator iter = documentosCargados.iterator(); iter.hasNext(); ) {

                    IDDocumento itemDocumento = (IDDocumento) iter.next();
                    IDOcurrencia ocurrencia = itemDocumento.getGrupos().getGrupo(1).
                                              getOcurrencia(1);

                    porcentaje += (ocurrencia.getValorCasilla(CASILLA_31_1124) !=
                                   null && ocurrencia.getValorCasilla(CASILLA_31_1124).
                                   getValorDecimal() != null) ? ocurrencia.
                            getValorCasilla(CASILLA_31_1124).getValorDecimal().floatValue() : 0;

                    if(Math.ceil(porcentaje)> 100) {
                        throw new DExcepcion("Error consolidando 120",
                                             "El porcentaje de participacion de los socios sobrepasa el 100%");
                    }

                    registroActual = registroActual + AMPLITUD_INTERVALO;
                }
                srvConsDoc.inicializar(documentoCargaPK.getIdeDocumento(),
                                       ideRecursoArchivo, registroActual,
                                       AMPLITUD_INTERVALO);
                srvConsDoc.ejecutar();
                documentosCargados = srvConsDoc.getDocumentos();
            }
        }
    }

    private void crearSolicitudExogena() throws DExcepcion {

        DCmdSrvConsSolicitudExogena consSolExogena = null;
        String nombreSrv1 = "entradasalida.exogena.DCmdSrvConsSolicitudExogena";

        consSolExogena = (DCmdSrvConsSolicitudExogena) getServicio(nombreSrv1);

        DSolicitudExogenaPKTO solExogenaPK = new DSolicitudExogenaPKTO();
        solExogenaPK.setIdeDocumentoGen(docGenerado.getIdeDocumento());
        solExogenaPK.setRepeticionDocGen(docGenerado.getNumRepeticion());
        solExogenaPK.setIdeSolicitudExogena(docSolicitud.getIdeDocumento());

        consSolExogena.inicializarPorIdeSolicitud(solExogenaPK);
        consSolExogena.ejecutar();
        DSolicitudExogenaTO solExogena = consSolExogena.getSolicitudExogena();

        if(solExogena == null) {

            DCmdSrvCrearSolicitudExogena srvCrearSolicitudExogena = null;
            String nombreSrv2 = "entradasalida.exogena.DCmdSrvCrearSolicitudExogena";
            srvCrearSolicitudExogena = (DCmdSrvCrearSolicitudExogena) getServicio(nombreSrv2);

            DSolicitudExogenaTO solExogenaTO = new DSolicitudExogenaTO(solExogenaPK,
                    new DSolicitudExogenaAttTO());

            srvCrearSolicitudExogena.inicializar(solExogenaTO);
            srvCrearSolicitudExogena.ejecutar();

        } else {
            throw new DExcepcion("Error formato 1124",
                                             "El formato 1124 seleccionado ya fu� asociado");
        }
    }

    private void actualizarDocumento(IDDocumento docConsolidado) throws DExcepcion {

        DCmdAccCrearDocumentoESTemporal guardarTmp = null;
        String nombreAcc = "entradasalida.DCmdAccCrearDocumentoESTemporal";
        guardarTmp = (DCmdAccCrearDocumentoESTemporal) getAccion(nombreAcc);

        String documentoXml = DDocumentoUtil.obtenerXmlDeDocumento(docConsolidado);
        long idDocumento = docConsolidado.getId();
        int repeticion = docConsolidado.getRepeticion();

        guardarTmp.inicializarActualizar(idDocumento, repeticion, documentoXml,
                                         IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO,
                                         IDModosDiligenciamiento.MODO_INICIAL, IDE_FORMATO);
        guardarTmp.ejecutar();
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

    private IDDocumento obtenerDocumento()throws DExcepcion {

        DCmdSrvConsDocumentoES srvConsDoc = null;
        String nombreSrv2 = "entradasalida.documentos.DCmdSrvConsDocumentoES";
        srvConsDoc = (DCmdSrvConsDocumentoES)this.getServicio(nombreSrv2);

        long ideDocumento = docGenerado.getIdeDocumento().longValue();
        int repeticion = docGenerado.getNumRepeticion().intValue();

        srvConsDoc.inicializarConsPorId(ideDocumento, repeticion, IDE_FORMATO);
        srvConsDoc.ejecutar();

        return srvConsDoc.getDocumento();
    }

    private void eliminaTarea() throws DExcepcion {
        if (tareaPK != null) {
            // Termina la tarea actual.
            DCmdAccTerminarTarea terminarTarea = (DCmdAccTerminarTarea)
                                                 getAccion(
                    "arquitectura.DCmdAccTerminarTarea");
            terminarTarea.inicializar(tareaPK);
            terminarTarea.ejecutar();
        }
    }

}
