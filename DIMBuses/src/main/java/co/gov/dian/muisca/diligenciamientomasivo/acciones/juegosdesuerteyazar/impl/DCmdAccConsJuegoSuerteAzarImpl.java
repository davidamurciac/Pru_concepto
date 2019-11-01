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
 * <p>Descripcion: Comando de acción utilizado para consultar un objeto JuegoSuerteAzar.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: DIAN</p>
 *
 * @author dmahechav
 * @version 1.0
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsJuegoSuerteAzarImpl extends DCmdAccConsJuegoSuerteAzar {

  /**
   * Ejecuta el comando de acciï¿½.
   */
  protected void ejecutarComando() {
    try {
      DCmdSrvConsJuegoSuerteAzar servicio = (DCmdSrvConsJuegoSuerteAzar) getServicio("diligenciamientomasivo.juegosdesuerteyazar.DCmdSrvConsJuegoSuerteAzar");
      switch(tipoOperacion){
      case CONSULTAR_POR_PK:
    	  servicio.inicializar(pkJuegoSuerteAzar);
          servicio.ejecutar();
          toJuegoSuerteAzar = servicio.getJuegoSuerteAzar();
          isOk = true;
          break;
      case CONSULTA_GENERICA:
    	  servicio.inicializarConsultaGenerica(toJuegoSuerteAzar);
          servicio.ejecutar();
          declaracionesJuegos = servicio.getDeclaracionesJuegos();
          isOk = true;
          break;
      }
      
    }
    catch (DExcepcion ex) {
      mensajeError = ex.getMessage();
      mensajeErrorDetallado = ex.getMensajeDetallado();
      isOk = false;
    }
  }

}
