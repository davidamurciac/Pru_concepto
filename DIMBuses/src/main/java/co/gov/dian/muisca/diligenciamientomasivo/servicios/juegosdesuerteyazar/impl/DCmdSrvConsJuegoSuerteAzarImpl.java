/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.juegosdesuerteyazar.impl;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.*;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.juegosdesuerteyazar.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para consultar un objeto JuegoSuerteAzar.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: DIAN</p>
 *
 * @author dmahechav
 * @version 1.0
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvConsJuegoSuerteAzarImpl extends DCmdSrvConsJuegoSuerteAzar {

  /**
   * Ejecuta el comando de servicio.
   *
   * @throws DExcepcion Si ocurre algn error al realizar la
   * consulta de JuegoSuerteAzar
   */
  protected void ejecutarComando() throws DExcepcion {
    IDAdminPersistencia admin = getAdministradorPersistencia();
    try {
      // Iniciar los DAO's
      IDDAOFactoryDiligenciamientoMasivo fabrica = new DDAOFactoryDiligenciamientoMasivo();
      IDDAOJuegoSuerteAzar dao = fabrica.getDaoJuegoSuerteAzar();
      switch(tipoOperacion){
      case CONSULTAR_POR_PK:
    	  //Consultar
    	  dao.inicializarConsultarPorPK(pkJuegoSuerteAzar);
    	  admin.buscar(dao);
    	  toJuegoSuerteAzar = dao.getJuegoSuerteAzar();
      case CONSULTA_GENERICA:
    	  dao.inicializarConsultaGenerica(toJuegoSuerteAzar);
    	  admin.buscar(dao);
    	  declaracionesJuegos = dao.getColeccionJuegoSuerteAzar();
      }
    }
    finally {
      admin.cerrarSesion();
    }
  }

}
