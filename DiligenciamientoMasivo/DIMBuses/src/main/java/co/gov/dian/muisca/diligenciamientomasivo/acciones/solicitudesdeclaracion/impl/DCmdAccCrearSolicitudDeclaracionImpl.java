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
 * <p>Descripcion: Comando de acciï¿½ utilizado para crear un objeto SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccCrearSolicitudDeclaracionImpl extends DCmdAccCrearSolicitudDeclaracion {


  /**
   * Ejecuta el comando de acciï¿½.
   */
  protected void ejecutarComando() {
    try {
      DCmdSrvCrearSolicitudDeclaracion servicio = (DCmdSrvCrearSolicitudDeclaracion) getServicio("diligenciamientomasivo.DCmdSrvCrearSolicitudDeclaracion");
      servicio.inicializar(toSolicitudDeclaracion);
      servicio.ejecutar();
      isOk = true;
    }
    catch (DExcepcion ex) {
      mensajeError = ex.getMessage();
      mensajeErrorDetallado = ex.getMensajeDetallado();
      isOk = false;
    }
  }

}
