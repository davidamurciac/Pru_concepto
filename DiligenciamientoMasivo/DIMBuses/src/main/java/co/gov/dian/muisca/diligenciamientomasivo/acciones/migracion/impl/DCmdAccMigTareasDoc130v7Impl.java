/**
 *  Republica de Colombia
 * Copyright (c) 2007 Direcciï¿½n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DCmdAccMigTareasDoc130v7Impl.java, 9, 2/3/09 8:54:50 PM COT, ARMANDO PEREA MORA$
 */

package co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion.impl;

//Paquetes requeridos

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.DTareaPKTO;
import co.gov.dian.muisca.arquitectura.servicios.bandejatareas.DCmdSrvCancelarTarea;
import co.gov.dian.muisca.cargamasiva.acciones.DCmdAccProcesarExpedienteCMPorDemanda;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoTO;
import co.gov.dian.muisca.cargamasiva.servicios.DCmdSrvConsIdeFlujoCargaMasiva;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsSolicitudIngreso;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion.DCmdAccMigTareasDoc130v7;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracion130v7;
import co.gov.dian.muisca.entradasalida.acciones.DCmdAccConsIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.constantes.IDEstadosCircuitoCargaMasiva;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaPKTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DSolicitudExogenaTO;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoES;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvConsDocGenExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvConsLstSolicitudExogena;
import co.gov.dian.muisca.gestionexpediente.general.helper.DCrearEventosHelper;
import co.gov.dian.muisca.gestionexpediente.general.to.expediente.DConsultaExpedienteTO;
import co.gov.dian.muisca.gestionexpediente.general.to.expediente.DExpedienteTO;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Interfaz para el procesamiento de la migracion al nuevo esquema de Eventos 
 * y Tareas de documentos 130 version 7</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: DIAN</p>
 *
 * @author Armando Perea Mora
 * @version $Revision: 9$
 * <pre>
 * $Log[10]:
 *  9    V1.3       1.6.1.1     2/3/09 8:54:50 PM COT  ARMANDO PEREA MORA
 *       Precios de Transferencia
 *  8    V1.3       1.6.1.0     2/3/09 7:22:37 PM COT  ARMANDO PEREA MORA
 *       Precios de Transferencia
 *  7    EntradaSalida 1.6         2/12/08 3:55:08 PM COT JUAN SEBASTIAN
 *       ZULUAGA MOLINA Se cambia el llamado a la acciÃ³n que crea el
 *       expediente de carga masiva por demanda
 *  6    EntradaSalida 1.5         2/7/08 6:14:58 PM COT  ARMANDO PEREA MORA
 *       Mejoras seleccion helper expedientes
 *  5    EntradaSalida 1.4         2/7/08 5:27:06 PM COT  ARMANDO PEREA MORA
 *       Cambios para fecha de vencimiento de tareas y separacion de tarea
 *  4    EntradaSalida 1.3         1/31/08 3:14:53 PM COT ARMANDO PEREA MORA
 *       Correciones estabilizacion para ejecuciÃ³n
 *  3    EntradaSalida 1.2         1/28/08 1:45:29 PM COT ARMANDO PEREA MORA
 *       Migracion solo 130
 *  2    EntradaSalida 1.1         1/28/08 1:45:00 PM COT ARMANDO PEREA MORA 
 *  1    EntradaSalida 1.0         1/25/08 8:14:49 PM COT ARMANDO PEREA MORA 
 * $
 * </pre>
 */

public class DCmdAccMigTareasDoc130v7Impl extends DCmdAccMigTareasDoc130v7 {

  //Constantes
  private static final int IDE_FORMATO_CARGA = 1126;
  private static final int IDE_FORMATO = 130;
  private static final int IDE_FORMATO_SOLICITUD = 10006;

	private String ideFlujo;
	private Map parametrosAsuntos;
	private Map parametrosInicial;
	private Map parametrosTotal;
	private Map parametrosParcial;
	private Map parametrosCancelacion;
	private Map documentosEventos;
	private Map idesActividades;
	
	private void definirIdentificadores(int formato, int versionFormato) throws DExcepcion {
		DCmdSrvConsIdeFlujoCargaMasiva cmdConsulta = (DCmdSrvConsIdeFlujoCargaMasiva) getServicio("cargamasiva.DCmdSrvConsIdeFlujoCargaMasiva");
		cmdConsulta.inicializar(new Integer(formato), new Integer(versionFormato));
		cmdConsulta.ejecutar();
		ideFlujo = cmdConsulta.getIdeFlujo();
		if (ideFlujo == null) {
			throw new DExcepcion("No se encontró una configuración valida para el formato y version dados", "La configuración de carga masiva  del formato: " + formato + " versión: " + versionFormato
					+ " no especifica que flujo debe usarse");
		}
		parametrosAsuntos = cmdConsulta.getParametrosAsuntos();
		parametrosInicial = cmdConsulta.getParametrosInicial();
		parametrosTotal = cmdConsulta.getParametrosTotal();
		parametrosParcial = cmdConsulta.getParametrosParcial();
		parametrosCancelacion = cmdConsulta.getParametrosCancelacion();
		documentosEventos = cmdConsulta.getDocumentosEventos();
		idesActividades = cmdConsulta.getIdesActividades();
	}
  
  //Campos
  DHelperExpedienteGeneracion130v7 manejadorExpedientes = null;

  /**
   * Constructor
   */
  public DCmdAccMigTareasDoc130v7Impl() {
  }

  /**
   * Elimina una tarea como mensaje
   *
   * @param ideDocumento Long
   * @param numRepeticion Integer
   * @return Collection
   * @throws DExcepcion
   */
  private void cancelarTareaMensaje(Long ideTarea) throws DExcepcion {
    DCmdSrvCancelarTarea cmdElimTarea = (DCmdSrvCancelarTarea)
      getServicio("arquitectura.bandejatareas.DCmdSrvCancelarTarea");
    DTareaPKTO tareaPK = new DTareaPKTO(ideTarea);
    cmdElimTarea.inicializar(tareaPK);
    cmdElimTarea.ejecutar();
  }

  /**
   * Obtiene la informaciï¿½n de un 130 Presentado como solicitud exogena
   *
   * @param ideDocumento Long
   * @param numRepeticion Integer
   * @return Collection
   * @throws DExcepcion
   */
  private DDocGenExogenaTO obtenerDocGenExogena(Long ideDocumento,
    Integer numRepeticion) throws DExcepcion {
    DCmdSrvConsDocGenExogena consDocGenExogena = (DCmdSrvConsDocGenExogena)
      getServicio("entradasalida.exogena.DCmdSrvConsDocGenExogena");
    DDocGenExogenaPKTO docGenPK = new DDocGenExogenaPKTO(ideDocumento,
      numRepeticion);

    consDocGenExogena.inicializar(docGenPK);
    consDocGenExogena.ejecutar();

    return consDocGenExogena.getDocGenExogena();
  }

  /**
   * Obtiene una lista de Solicitudes de un documento 130
   *
   * @param ideDocumento Long
   * @param numRepeticion Integer
   * @return Collection
   * @throws DExcepcion
   */
  private Collection obtenerSolicitudesExogena(Long ideDocumento,
    Integer numRepeticion) throws DExcepcion {
    DDocumentoPKTO documentoPK = new DDocumentoPKTO(ideDocumento,
      numRepeticion);
    DCmdSrvConsLstSolicitudExogena consLstSolExogena =
      (DCmdSrvConsLstSolicitudExogena) getServicio(
      "entradasalida.exogena.DCmdSrvConsLstSolicitudExogena");
    consLstSolExogena.inicializar(documentoPK);
    consLstSolExogena.ejecutar();

    return consLstSolExogena.getColeccionSolicitudExogena();
  }

  private DSolicitudIngresoTO obtenerSolicitudIngresoTO(Long idSolicitud) throws
    DExcepcion {
    DCmdSrvConsSolicitudIngreso srvConsSol = (DCmdSrvConsSolicitudIngreso)
      getServicio("cargamasiva.procesamiento.DCmdSrvConsSolicitudIngreso");

    srvConsSol.inicializar(new DSolicitudIngresoPKTO(idSolicitud));

    srvConsSol.ejecutar();
    return srvConsSol.getSolicitudIngreso();
  }

  private DSolicitudIngresoTO obtenerSolicitudIngreso(Long ideDocumento,
    Integer numRepeticion) throws DExcepcion {
    DSolicitudIngresoTO result = null;

    Collection solicitudes = obtenerSolicitudesExogena(ideDocumento,
      numRepeticion);
    if (solicitudes != null) {
      Iterator it = solicitudes.iterator();
      while (it.hasNext()) {
        DSolicitudExogenaTO solicitudExogena = (DSolicitudExogenaTO) it.next();

        DSolicitudIngresoTO solicitudIngresoTo = obtenerSolicitudIngresoTO(
          solicitudExogena.getPK().getIdeSolicitudExogena());

        if ((solicitudIngresoTo != null) &&
          (solicitudIngresoTo.getSolicitudAtt().getIdeFormato().intValue() ==
          IDE_FORMATO_CARGA)) {
          result = solicitudIngresoTo;
          break;
        }
      }
    }
    return result;
  }

  private DExpedienteTO obtenerExpedienteTOCargaMasiva(Long ideDocumento,
    Integer numRepeticion) throws DExcepcion {
    DExpedienteTO result = null;
    DCrearEventosHelper creadorEventos = new DCrearEventosHelper(getContexto());

    DConsultaExpedienteTO criterio = new DConsultaExpedienteTO();
    criterio.setNumDocumento(ideDocumento); /*Documento 10006*/
    criterio.setNumRepeticion(numRepeticion);
    criterio.setIdeFlujo(IDEstadosCircuitoCargaMasiva.
      ID_FLUJO_CARGA_MASIVA_PRECIOS_TRANSFERENCIA);

    Collection expedientes = creadorEventos.consultarExpedientes(criterio);

    if ((expedientes != null) && !expedientes.isEmpty()) {
      result = (DExpedienteTO) expedientes.iterator().next();
    }
    return result;
  }

  /**
   * Obtiene el Identificador de Documento de un Documento a partir de un
   * numero de documento
   * @param numeroDocumento Long
   * @return DIdentificadorDoc
   * @throws DExcepcion
   */
  private DIdentificadorDoc obtenerIdentificadorDocMuisca(Long numeroDocumento,
    Integer ideFormato) throws
    DExcepcion {
    DCmdAccConsIdentificadorDoc cmdAccConsIdDoc = (DCmdAccConsIdentificadorDoc)
      getAccion("entradasalida.DCmdAccConsIdentificadorDoc");
    //Documentos MUISCA solamente
    cmdAccConsIdDoc.inicializarConsPorId(numeroDocumento.longValue(), 1, true,
      ideFormato.intValue());
    cmdAccConsIdDoc.ejecutar();

    return cmdAccConsIdDoc.getIdentificadorDoc();
  }

  /**
   * el Identificador de Documento de un Documento a partir de un
   * numero de documento verificando que es un documento de Gestiï¿½n Masiva
   * @param numeroDocumento Long
   * @return DIdentificadorDoc
   * @throws DExcepcion
   */
  private DIdentificadorDoc obtenerIdentificadorDocCarga(Long
    numeroDocumento) throws DExcepcion {
    if (numeroDocumento == null) {
      throw new DExcepcion("El nï¿½mero de documento es nulo",
        "Error al Obtener Documento Gestion Masiva. " +
        "El nï¿½mero de documento es nulo");
    }

    //Buscar Identificador Documento Gestion Masiva que para el caso ya debe
    //estar como documento Muisca
    DIdentificadorDoc idDoc = obtenerIdentificadorDocMuisca(numeroDocumento,
      new Integer(IDE_FORMATO_SOLICITUD));
    return idDoc;
  }

  private void crearExpedienteCargaMasiva(DSolicitudIngresoTO solicitud) throws
    DExcepcion {
    if (solicitud == null) {
      throw new DExcepcion("Error", "El objeto de transferencia de la " +
        "Solicitud de Ingreso es nulo");
    }

    DIdentificadorDoc idenDocCargaMasiva = obtenerIdentificadorDocCarga(
      solicitud.getSolicitudPK().getIdeSolicitud());
    if (idenDocCargaMasiva == null) {
      throw new DExcepcion("Error", "No fue posible hallar el " +
        "Identificador de Documento del Documento nï¿½mero" +
        solicitud.getSolicitudPK().getIdeSolicitud());
    }

    DExpedienteTO expediente = obtenerExpedienteTOCargaMasiva(
      new Long(idenDocCargaMasiva.getIdDocumento()),
      new Integer(idenDocCargaMasiva.getNumRepeticion()));
    if (expediente == null) {
      DCmdAccProcesarExpedienteCMPorDemanda accProcesoDemanda =
        (DCmdAccProcesarExpedienteCMPorDemanda)
        getAccion("cargamasiva.DCmdAccProcesarExpedienteCMPorDemanda");
      accProcesoDemanda.inicializar(	ideFlujo, 
				solicitud, 
				idenDocCargaMasiva, 
				parametrosAsuntos, 
				parametrosInicial, 
				parametrosTotal, 
				parametrosParcial,
				parametrosCancelacion, 
				documentosEventos, 
				idesActividades, 
				true);	
      accProcesoDemanda.ejecutar();
      //expediente = accProcesoDemanda.getExpediente();
    }
  }

  private DHelperExpedienteGeneracion130v7 getManejadorExpedientes() {
    if (manejadorExpedientes == null) {
      manejadorExpedientes =
        new DHelperExpedienteGeneracion130v7(this);
    }
    return manejadorExpedientes;
  }

  private IDDocumento obtenerIDDocumento(Long ideDocumento,
    Integer numRepeticion) throws DExcepcion {
    DCmdSrvConsDocumentoES servicio = (DCmdSrvConsDocumentoES)
      getServicio("entradasalida.documentos.DCmdSrvConsDocumentoES");
    servicio.inicializarConsPorId(ideDocumento.longValue(),
      numRepeticion.intValue(), IDE_FORMATO);
    servicio.ejecutar();
    return servicio.getDocumento();
  }

  protected void ejecutarComando() {
    try {
      //Obtener el documento
      IDDocumento documento = obtenerIDDocumento(getIdeDocumento(),
        getNumRepeticion());
      if (documento == null) {
        throw new DExcepcion("Error", "No fue posible encontrar el " +
          "Documento numero " + getIdeDocumento() +
          " Rep: " + getNumRepeticion());
      }
      //Buscar Solicitud
      DSolicitudIngresoTO solicitudIngresoTo = obtenerSolicitudIngreso(
        getIdeDocumento(), getNumRepeticion());
      if (solicitudIngresoTo == null) {
        throw new DExcepcion("Error", "No fue posible encontrar en " +
          "Solicitudes Exogena la Solicitud de Ingreso para el documento " +
          getIdeDocumento() + " Rep: " + getNumRepeticion());
      }
      //Eliminar la tarea en el modelo de mensajes
      DDocGenExogenaTO docGenExogena = obtenerDocGenExogena(getIdeDocumento(),
        getNumRepeticion());
      if ((docGenExogena != null) &&
        (docGenExogena.getAtt().getIdeTarea() != null)) {
        cancelarTareaMensaje(docGenExogena.getAtt().getIdeTarea());
      }
      //Crear Expediente Carga Masiva
      crearExpedienteCargaMasiva(solicitudIngresoTo);
      //Generar tarea de Diligenciamiento
      /*
      DTareaNegTO tareaNegTo =
        getManejadorExpedientes().procesarPresentacionPorEnvioArchivos(
        solicitudIngresoTo.getSolicitudPK().getIdeSolicitud(), documento);
      */
      isOk = true;
    } catch (DExcepcion ex) {
      setExcepcion(ex);
      isOk = false;
    }

    /*
         // Conformar el contexto de Workflow
                    DContextoWF miCtx = new DContextoWF();
                    miCtx.setIdFlujo(idFlujo);
                    miCtx.setIdActividad(miIdActiv);
                    miCtx.setIdUsuario(miIdUsuario);
                    miCtx.setIdRol(miIdRol);
                    miCtx.setIdLugar(miIdLugar);
                    miCtx.setIdOrganizacion(miIdOrg);
                    miCtx.setIdTipoOrganizacion(miIdTipoOrg);
                    miCtx.setIdUnidadAdm(miIdUndAdm);


          DComandoWF miComandoWF = (DComandoWF) miDelegado.getComando(miNomComando, false);
                    /*if (logger.isDebugEnabled()) {
                            logger.debug("Obtuvo comando de WF");
                        }*/
     /*                miComandoWF.setContextoWF(miCtx);
                     miComandoWF.setActividad(miActiv);
                     miComandoWF.setTramite(miTram);
                     miComandoWF.setFlujo(miFlujo);
      */
     /**@todo REVISAR LAS CUESTIONES DE TX CUANDO FALLA ALGO EN LA EJECUCIï¿½N DE UN COMANDO DE WF*/
     /*                try {
      miComandoWF.setParametrosWF(consultarParametrosWF(miCtx));
                         miComandoWF.ejecutar();
                     } catch (DExcepcion ex) {
      logger.error("Error al ejecutar el comando de WF", ex);
                         throw ex;
                     }

      */
     /**
      * Consulta los parï¿½metros de entrada y salida de la actividad y
      * los asigna al comando de WF.
      *
      * @throws DExcepcion Si ocurre algï¿½n error al consultar los
      * parï¿½metros
      */
     /*        public Collection consultarParametrosWF(IDContextoWF contextoWF) throws DExcepcion
             {
                 // Cargar los parï¿½metros de la actividad
                 DCmdSrvConsLstParametrosActTramEje miCmd = (DCmdSrvConsLstParametrosActTramEje) getServicio("gestionexpediente.DCmdSrvConsLstParametrosActTramEje");
                 miCmd.inicializar(contextoWF.getIdActividad(), null);
      */
     /*if (logger.isDebugEnabled()) {
       logger.debug("consultarParametrosWF...ID de la actividad->" + getContextoWF().getIdActividad());
                  }*/
     /*            miCmd.ejecutar();
                 return miCmd.getListaParametros();
        }
      */
  }

}
