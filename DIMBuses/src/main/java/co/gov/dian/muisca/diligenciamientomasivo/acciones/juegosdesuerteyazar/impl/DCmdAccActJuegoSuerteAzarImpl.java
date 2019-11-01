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
 * <p>Descripcion: Comando de acción utilizado para actualizar un objeto JuegoSuerteAzar.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: DIAN</p>
 *
 * @author dmahechav
 * @version 1.0
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccActJuegoSuerteAzarImpl extends DCmdAccActJuegoSuerteAzar {


  /**
   * Ejecuta el comando de acción.
   */
  protected void ejecutarComando() {
    try {
    	DCmdSrvActJuegoSuerteAzar servicio = (DCmdSrvActJuegoSuerteAzar) getServicio("diligenciamientomasivo.juegosdesuerteyazar.DCmdSrvActJuegoSuerteAzar");
    	switch(tipoOperacion){
        case ACTUALIZAR:
        	servicio.inicializar(toJuegoSuerteAzar);
        	servicio.ejecutar();
        	isOk = true;
        	break;
        case ACTUALIZAR_ESTADO:
        	servicio.inicializarActEstado(pkJuegoSuerteAzar, codEstado);
        	servicio.ejecutar();
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
