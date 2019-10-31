/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.juegosdesuerteyazar.impl;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.*;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.juegosdesuerteyazar.*;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.juegosdesuerteyazar.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de acción utilizado para consultar objetos JuegoSuerteAzar.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: DIAN</p>
 *
 * @author dmahechav
 * @version 1.0
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstJuegoSuerteAzarImpl extends DCmdAccConsLstJuegoSuerteAzar {

  /**
   * Ejecuta el comando de acciï¿½.
   */
  protected void ejecutarComando() {
    try {
      DCmdSrvConsLstJuegoSuerteAzar servicio = (DCmdSrvConsLstJuegoSuerteAzar) getServicio("diligenciamientomasivo.DCmdSrvConsLstJuegoSuerteAzar");
      servicio.setPaginable(true);
      switch (tipoOperacion) {

        case CONSULTA_GENERICA:
          servicio.inicializarConsultaGenerica(toJuegoSuerteAzar);
          break;


        default:
          throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos JuegoSuerteAzar"), getMensajeOperInvalida());
      }
      servicio.ejecutar();
      objetosJuegoSuerteAzar = servicio.getColeccionJuegoSuerteAzar();
      isOk = true;
    }
    catch (DExcepcion ex) {
      mensajeError = ex.getMessage();
      mensajeErrorDetallado = ex.getMensajeDetallado();
      isOk = false;
    }
  }

}
