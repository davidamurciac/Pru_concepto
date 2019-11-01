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
 * <p>Descripcion: Comando de acci� utilizado para consultar objetos SolicitudConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstSolicitudConciliaPagoImpl extends DCmdAccConsLstSolicitudConciliaPago {

  /**
   * Ejecuta el comando de acci�.
   */
  protected void ejecutarComando() {
    try {
      DCmdSrvConsLstSolicitudConciliaPago servicio = (DCmdSrvConsLstSolicitudConciliaPago) getServicio("diligenciamientomasivo.pagocaja.DCmdSrvConsLstSolicitudConciliaPago");

      switch (tipoOperacion) {
        default:
          //throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos SolicitudConciliaPago"), getMensajeOperInvalida());
      }
      servicio.ejecutar();
      objetosSolicitudConciliaPago = servicio.getColeccionSolicitudConciliaPago();
      isOk = true;
    }
    catch (DExcepcion ex) {
      mensajeError = ex.getMessage();
      mensajeErrorDetallado = ex.getMensajeDetallado();
      isOk = false;
    }
  }

}
