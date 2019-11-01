/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.juegosdesuerteyazar;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.impl.*;

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
public class DCmdSrvConsJuegoSuerteAzar extends DComandoServicioInterno {
  /** Objeto de transporte de JuegoSuerteAzar */
  protected DJuegoSuerteAzarTO toJuegoSuerteAzar;
  /** Llave primaria de JuegoSuerteAzar */
  protected DJuegoSuerteAzarPKTO pkJuegoSuerteAzar;
  /** Atributos de JuegoSuerteAzar */
  protected DJuegoSuerteAzarAttTO attJuegoSuerteAzar;
  protected Collection declaracionesJuegos;
  protected int tipoOperacion = -1;
  
  protected static final int CONSULTAR_POR_PK = 1;
  protected static final int CONSULTA_GENERICA = 2;
  /**
   * Inicializa la consulta por llave primaria.
   * @param pkJuegoSuerteAzar Llave primaria de JuegoSuerteAzar
   */
  public void inicializar(DJuegoSuerteAzarPKTO pkJuegoSuerteAzar) {
    toJuegoSuerteAzar = null;
    attJuegoSuerteAzar = null;
    this.pkJuegoSuerteAzar = pkJuegoSuerteAzar;
    this.tipoOperacion = CONSULTAR_POR_PK;
  }
  /**
   * Inicializa la consulta generica.
   * @param TOJuegoSuerteAzar 
   */
  public void inicializarConsultaGenerica(DJuegoSuerteAzarTO toJuegoSuerteAzar) {
    pkJuegoSuerteAzar = null;
    attJuegoSuerteAzar = null;
    this.toJuegoSuerteAzar = toJuegoSuerteAzar;
    this.tipoOperacion = CONSULTA_GENERICA;
  }


  /**
   * Devuelve el objeto JuegoSuerteAzar que se haya consultado.
   * @return Un objeto DJuegoSuerteAzarTO
   */
  public DJuegoSuerteAzarTO getJuegoSuerteAzar() {
    return toJuegoSuerteAzar;
  }

  /**
   * Ejecuta el comando de servicio.
   * @throws DExcepcion Si ocurre algún error al realizar la
   * la consulta de JuegoSuerteAzar
   */
  protected void ejecutarComando() throws DExcepcion {
      throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    return new DCmdSrvConsJuegoSuerteAzar();
  }

  /**
   * Indica si el comando es auditable.
   * @return true si el comando es auditable; false de lo contrario
   */
  public boolean isAuditable() {
    return true;
  }

  /**
   * Obtiene la descripciï¿½ del comando.
   * @return Un String con la descripciï¿½ del comando
   */
  public String getDescripcion() {
    return "Permite consultar un objeto JuegoSuerteAzar";
  }

  /**
   * Mï¿½odo para validar los parámetros inicializados, invocado
   * previamente a la ejecución del comando.
   * @return true si los parámetros son válidos; false de lo contrario
   * @throws DValidarExcepcion Si los parámetros no son válidos
   */
  public boolean validar() throws DValidarExcepcion {
      Map parametros=new HashMap();
      switch(tipoOperacion){
      case CONSULTAR_POR_PK:
    	  parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar",pkJuegoSuerteAzar);
    	  parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar.getIdeDocumento()",pkJuegoSuerteAzar.getIdeDocumento());
    	  parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar.getNumRepeticion()",pkJuegoSuerteAzar.getNumRepeticion());
    	  validarParametros("Consulta",parametros);
    	  break;
      case CONSULTA_GENERICA:
    	  if(toJuegoSuerteAzar == null){
    		  parametros.put(this.getClass().getName()+":validar:attJuegoSuerteAzar",attJuegoSuerteAzar);
    	  }
    	  if (toJuegoSuerteAzar.getAtt() == null &&
    			  toJuegoSuerteAzar.getPK() == null){
    		  parametros.put(this.getClass().getName()+":validar:attJuegoSuerteAzar",attJuegoSuerteAzar);
    	  }
    	  break;
      }
      return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como parï¿½etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdSrvConsJuegoSuerteAzar) {
      DCmdSrvConsJuegoSuerteAzar copia = (DCmdSrvConsJuegoSuerteAzar) comando;
      copia.toJuegoSuerteAzar = toJuegoSuerteAzar;
      copia.pkJuegoSuerteAzar = pkJuegoSuerteAzar;
      copia.attJuegoSuerteAzar = attJuegoSuerteAzar;
      copia.tipoOperacion = tipoOperacion;
      copia.declaracionesJuegos = declaracionesJuegos;
    }
  }
public Collection getDeclaracionesJuegos() {
	return declaracionesJuegos;
}
}
