package co.gov.dian.muisca.diligenciamientomasivo.acciones.transferencias.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.transferencias.
  DCmdAccConsLstSolicitudesValidas;
import co.gov.dian.muisca.entradasalida.constantes.IDEstadosCircuitoCargaMasiva;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.
  DSolicitudIngresoAttTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.
  DSolicitudIngresoPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.
  DSolicitudIngresoTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.
  DSolicitudExogenaPKTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DSolicitudExogenaTO;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.
  DCmdSrvConsLstSolicitudIngresoNoPaginado;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.
  DCmdSrvConsSolicitudIngreso;
import co.gov.dian.muisca.entradasalida.servicios.exogena.
  DCmdSrvConsLstSolicitudExogena;
import co.gov.dian.muisca.entradasalida.servicios.exogena.
  DCmdSrvConsSolicitudExogena;
import org.apache.log4j.Logger;
import java.util.Collection;


public class DCmdAccConsLstSolicitudesValidasImpl extends
  DCmdAccConsLstSolicitudesValidas {

  Logger logger = Logger.getLogger(DCmdAccConsLstSolicitudesValidasImpl.class);

  //Constantes
  private final Integer IDE_FORMATO_1124 = new Integer(1124);
  private static int IDE_FORMATO_1125 = 1125;
  private final String IDE_ESTADO_EXITOSO = "24";
  private final int ORG_A_NOMBRE_PROPIO = 2;

  public DCmdAccConsLstSolicitudesValidasImpl() {
  }


  private DSolicitudIngresoTO obtenerSolicitudIngresoTO(
    Long ideSolicitudExogena) throws DExcepcion {
    DSolicitudIngresoTO result = null;
    if (ideSolicitudExogena != null) {
      DCmdSrvConsSolicitudIngreso srvConsSolIngreso =
        (DCmdSrvConsSolicitudIngreso) getServicio(
        "cargamasiva.procesamiento.DCmdSrvConsSolicitudIngreso");
      srvConsSolIngreso.inicializar(new DSolicitudIngresoPKTO(
        ideSolicitudExogena));
      srvConsSolIngreso.ejecutar();
      result = srvConsSolIngreso.getSolicitudIngreso();
    }
    return result;
  }

  private Collection obtenerSolicitudesExogenaTO(Long ideDocumento,
    Integer numRepeticion) throws DExcepcion {
    Collection result = null;

    if ((ideDocumento != null) && (numRepeticion != null)) {
      DCmdSrvConsLstSolicitudExogena srvCons = (DCmdSrvConsLstSolicitudExogena)
        getServicio("entradasalida.exogena.DCmdSrvConsLstSolicitudExogena");
      srvCons.inicializar(new DDocumentoPKTO(ideDocumento, numRepeticion));
      srvCons.ejecutar();
      result = srvCons.getColeccionSolicitudExogena();
    }
    return result;
  }

  private DSolicitudExogenaTO obtenerSolicitudExogenaTO(
    DSolicitudExogenaPKTO solicitudExogenaPKTo) throws DExcepcion {

    DCmdSrvConsSolicitudExogena conSolExogena = (DCmdSrvConsSolicitudExogena)
      getServicio("entradasalida.exogena.DCmdSrvConsSolicitudExogena");
    conSolExogena.inicializarPorIdeSolicitud(solicitudExogenaPKTo);
    conSolExogena.ejecutar();
    return conSolExogena.getSolicitudExogena();
  }

  private List obtenerSolicitudesIngresoNoPaginado(
    DSolicitudIngresoAttTO solicitudIngresoAttTo) throws DExcepcion {
    List result = null;
    if (solicitudIngresoAttTo != null) {
      DCmdSrvConsLstSolicitudIngresoNoPaginado srvCons =
        (DCmdSrvConsLstSolicitudIngresoNoPaginado) getServicio(
        "entradasalida.cargamasiva.DCmdSrvConsLstSolicitudIngresoNoPaginado");
      srvCons.inicializarConsultaPorParametros(solicitudIngresoAttTo);
      srvCons.ejecutar();
      Collection coleccion = srvCons.getColeccionSolicitudIngreso();
      if (coleccion != null) {
        result = new ArrayList(coleccion);
      }
    }
    return result;
  }

  private List obtenerSolicitudesFormatoConcepto(Integer formato,
    Integer numVersionFormato, Integer codConcepto,
    Integer anoVigencia) throws DExcepcion {

    long idePersonaOrg = ORG_A_NOMBRE_PROPIO;
    if (getContexto().getContextoSeguridad().getIdePersonaOrganizacion() !=
      null) {
      idePersonaOrg = getContexto().getContextoSeguridad().
        getIdePersonaOrganizacion().longValue();
    }

    DSolicitudIngresoAttTO solIngresoAtt = new DSolicitudIngresoAttTO();
    solIngresoAtt.setCodEstado(IDEstadosCircuitoCargaMasiva.OK.toString());
    solIngresoAtt.setIdeFormato(formato);
    solIngresoAtt.setNumVersionFormato(numVersionFormato);
    solIngresoAtt.setCodConcepto(codConcepto);
    solIngresoAtt.setAnioVigencia(anoVigencia);

    if (idePersonaOrg == ORG_A_NOMBRE_PROPIO) {
      solIngresoAtt.setIdePersona(getContexto().getContextoSeguridad().
        getIdePersona());
    }
    else {
      solIngresoAtt.setIdeOrganizacion(new Integer(getContexto().
        getContextoSeguridad().getOrganizacion()));
    }

    return obtenerSolicitudesIngresoNoPaginado(solIngresoAtt);
  }

  protected void ejecutarComando() {
    try {
      Collection solicitudesDocumento = obtenerSolicitudesExogenaTO(
        docGenerado.getIdeDocumento(), docGenerado.getNumRepeticion());
      if ((solicitudesDocumento != null) && !solicitudesDocumento.isEmpty()) {
        DSolicitudIngresoTO solIngreso1125 = null;
        //Buscar el formato 1125 porque es posible que ya se haya asociado el
        //1124. La solicitud asociada con el formato 1124 no esta relacionada
        //en el expediente
        Iterator it = solicitudesDocumento.iterator();
        while (it.hasNext()) {
          DSolicitudExogenaTO solicitudExogena = (DSolicitudExogenaTO)
            it.next();

          DSolicitudIngresoTO solicitudIngreso = obtenerSolicitudIngresoTO(
            solicitudExogena.getPK().getIdeSolicitudExogena());
          if (solicitudIngreso != null) {
            if (solicitudIngreso.getSolicitudAtt().getIdeFormato().
              intValue() == IDE_FORMATO_1125) {
              solIngreso1125 = solicitudIngreso;
              break;
            }
          }
        }

        if (solIngreso1125 == null) {
          throw new DExcepcion("Error", "No fue posible encontrar la " +
            "Solicitud de Ingreso del formato 1125 para el documento " +
            docGenerado.getIdeDocumento() + " Rep: " +
            docGenerado.getNumRepeticion());
        }

        Integer numVersionFormato1125 = solIngreso1125.getSolicitudAtt().
          getNumVersionFormato();
        Integer conceptoDoc1125 = solIngreso1125.getSolicitudAtt().
          getCodConcepto();
        Integer anoDoc1125 = solIngreso1125.getSolicitudAtt().
          getAnioVigencia();

        List solicitudes1124Contribuyente = obtenerSolicitudesFormatoConcepto(
          IDE_FORMATO_1124, numVersionFormato1125, conceptoDoc1125, anoDoc1125);

        if ((solicitudes1124Contribuyente != null) &&
          !solicitudes1124Contribuyente.isEmpty()) {
          Iterator iter = solicitudes1124Contribuyente.iterator();

          while (iter.hasNext()) {
            DSolicitudIngresoTO item = (DSolicitudIngresoTO) iter.next();

            DSolicitudExogenaPKTO solicituExogenaPK = new DSolicitudExogenaPKTO(
              null, item.getSolicitudPK().getIdeSolicitud());

            DSolicitudExogenaTO solicitud1124To =
              obtenerSolicitudExogenaTO(solicituExogenaPK);

            if (solicitud1124To == null) {
              if (correccion) {
                if (item.getSolicitudAtt().getCodConcepto().intValue() ==
                  IDEstadosCircuitoCargaMasiva.CONCEPTO_REEMPLAZO_TOTAL) {
                  if (item.getSolicitudAtt().getCodEstado().equals(
                    IDE_ESTADO_EXITOSO)) {
                    solicitudesValidas.add(item);
                  }
                }
                else if (item.getSolicitudAtt().getCodConcepto().intValue() ==
                  IDEstadosCircuitoCargaMasiva.CONCEPTO_INICIAL) {
                  solicitudesValidas.add(item);
                  break;
                }
              }
              else {
                solicitudesValidas.add(item);
              }
            }
          }

          //Si la lista resulta nula y es una correccion traiga solamente
          //la primera de concepto inicial
          if (solicitudesValidas.isEmpty() && correccion) {
            iter = solicitudes1124Contribuyente.iterator();
            while (iter.hasNext()) {
              DSolicitudIngresoTO item = (DSolicitudIngresoTO) iter.next();

              DSolicitudExogenaPKTO solicituExogenaPK =
                new DSolicitudExogenaPKTO(null,
                item.getSolicitudPK().getIdeSolicitud());

              DSolicitudExogenaTO solicitud1124To =
                obtenerSolicitudExogenaTO(solicituExogenaPK);

              if (solicitud1124To == null) {
                if (item.getSolicitudAtt().getCodConcepto().intValue() ==
                  IDEstadosCircuitoCargaMasiva.CONCEPTO_INICIAL) {
                  solicitudesValidas.add(item);
                  break;
                }
              }
            }
          }
        }
      }

      isOk = true;

    } catch (DExcepcion ex) {
      logger.error("Error al ejecutar ejecutarComando()", ex);
      isOk = false;
    }
  }

}
