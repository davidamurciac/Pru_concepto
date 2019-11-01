/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.pagocaja.impl;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.pagocaja.*;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.pagocaja.*;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.pagocaja.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de acci� utilizado para actualizar un objeto SolicitudDocConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccActSolicitudDocConciliaPagoImpl extends DCmdAccActSolicitudDocConciliaPago {


  /**
   * Ejecuta el comando de acci�.
   */
  protected void ejecutarComando() {
    try {
      DCmdSrvActSolicitudDocConciliaPago servicio = (DCmdSrvActSolicitudDocConciliaPago) getServicio("diligenciamientomasivo.pagocaja.DCmdSrvActSolicitudDocConciliaPago");
      servicio.inicializar(toSolicitudDocConciliaPago);
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
