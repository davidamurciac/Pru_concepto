/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.solicitudesdeclaracion.impl;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.*;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.solicitudesdeclaracion.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de acciï¿½ utilizado para consultar un objeto SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsSolicitudDeclaracionImpl extends DCmdAccConsSolicitudDeclaracion {

  /**
   * Ejecuta el comando de acciï¿½.
   */
  protected void ejecutarComando() {
    try {
      DCmdSrvConsSolicitudDeclaracion servicio = (DCmdSrvConsSolicitudDeclaracion) getServicio("diligenciamientomasivo.DCmdSrvConsSolicitudDeclaracion");
      servicio.inicializar(pkSolicitudDeclaracion);
      servicio.ejecutar();
      toSolicitudDeclaracion = servicio.getSolicitudDeclaracion();
      isOk = true;
    }
    catch (DExcepcion ex) {
      mensajeError = ex.getMessage();
      mensajeErrorDetallado = ex.getMensajeDetallado();
      isOk = false;
    }
  }

}
