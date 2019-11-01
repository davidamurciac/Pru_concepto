/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.juegosdesuerteyazar;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.*;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.juegosdesuerteyazar.*;

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
public class DCmdAccActJuegoSuerteAzar extends DComandoAccion {
  /** Objeto de transporte de JuegoSuerteAzar */
  protected DJuegoSuerteAzarTO toJuegoSuerteAzar;
  /** Llave primaria de JuegoSuerteAzar */
  protected DJuegoSuerteAzarPKTO pkJuegoSuerteAzar;
  /** Atributos de JuegoSuerteAzar */
  protected DJuegoSuerteAzarAttTO attJuegoSuerteAzar;
  protected Integer codEstado;
  protected int tipoOperacion = -1;
  protected static final int ACTUALIZAR = 1;
  protected static final int ACTUALIZAR_ESTADO = 2;

  /**
   * Inicializa la actualización de JuegoSuerteAzar.
   * @param toJuegoSuerteAzar Objeto de Transporte de JuegoSuerteAzar
   */
  public void inicializar(DJuegoSuerteAzarTO toJuegoSuerteAzar) {
    isOk = false;
    this.toJuegoSuerteAzar = toJuegoSuerteAzar;
    if (toJuegoSuerteAzar != null) {
      pkJuegoSuerteAzar = this.toJuegoSuerteAzar.getPK();
      attJuegoSuerteAzar = this.toJuegoSuerteAzar.getAtt();
      this.tipoOperacion = ACTUALIZAR;
    }
  }
  
  /**
   * Inicializa la actualizaciï¿½ de estado JuegoSuerteAzar.
   * @param toJuegoSuerteAzar Objeto de Transporte de JuegoSuerteAzar
   */
  public void inicializarActEstado(DJuegoSuerteAzarPKTO pkJuegoSuerteAzar, Integer codEstado) {
    this.pkJuegoSuerteAzar = pkJuegoSuerteAzar;
    this.codEstado = codEstado;
    this.tipoOperacion = ACTUALIZAR_ESTADO;
  }


  /**
   * Ejecuta el comando de acción.
   */
  protected void ejecutarComando() {
      throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    return new DCmdAccActJuegoSuerteAzar();
  }

  /**
   * Indica si el comando es auditable.
   * @return true si el comando es auditable; false de lo contrario
   */
  public boolean isAuditable() {
    return true;
  }

  /**
   * Obtiene la descripción del comando.
   * @return Un String con la descripción del comando
   */
  public String getDescripcion() {
    return "Permite actualizar un objeto JuegoSuerteAzar";
  }

  /**
   * Méodo para validar los parámetros inicializados, invocado
   * previamente a la ejecución del comando.
   * @return true si los parámetros son válidos; false de lo contrario
   * @throws DValidarExcepcion Si los parámetros no son válidos
   */
  public boolean validar() throws DValidarExcepcion {
      Map parametros=new HashMap();
      switch(tipoOperacion){
      case ACTUALIZAR:
    	  parametros.put(this.getClass().getName()+":validar:toJuegoSuerteAzar",toJuegoSuerteAzar);
    	  parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar",pkJuegoSuerteAzar);
    	  parametros.put(this.getClass().getName()+":validar:attJuegoSuerteAzar",attJuegoSuerteAzar);
    	  parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar.getIdeDocumento()",pkJuegoSuerteAzar.getIdeDocumento());
    	  parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar.getNumRepeticion()",pkJuegoSuerteAzar.getNumRepeticion());
    	  break;
      case ACTUALIZAR_ESTADO:
      validarParametros("Actualizar",parametros);
      	  parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar",pkJuegoSuerteAzar);
      	  parametros.put(this.getClass().getName()+":validar:codEstado",codEstado);
      	  break;
      }
      return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como parï¿½etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdAccActJuegoSuerteAzar) {
      DCmdAccActJuegoSuerteAzar copia = (DCmdAccActJuegoSuerteAzar) comando;
      copia.toJuegoSuerteAzar = toJuegoSuerteAzar;
      copia.pkJuegoSuerteAzar = pkJuegoSuerteAzar;
      copia.attJuegoSuerteAzar = attJuegoSuerteAzar;
      copia.codEstado = codEstado;
      copia.tipoOperacion =tipoOperacion;
    }
  }
}
