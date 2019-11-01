/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
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
 * <p>Descripcion: Comando de acci� utilizado para eliminar un objeto SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccElimSolicitudDeclaracionImpl extends DCmdAccElimSolicitudDeclaracion {


  /**
   * Ejecuta el comando de acci�.
   */
  protected void ejecutarComando() {
    try {
      DCmdSrvElimSolicitudDeclaracion servicio = (DCmdSrvElimSolicitudDeclaracion) getServicio("diligenciamientomasivo.DCmdSrvElimSolicitudDeclaracion");
      servicio.inicializar(pkSolicitudDeclaracion);
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
