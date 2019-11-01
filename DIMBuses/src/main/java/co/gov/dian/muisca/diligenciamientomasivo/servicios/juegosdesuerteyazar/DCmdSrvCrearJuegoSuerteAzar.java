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
 * <p>Descripcion: Comando de servicio utilizado para crear un objeto JuegoSuerteAzar.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: DIAN</p>
 *
 * @author dmahechav
 * @version 1.0
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvCrearJuegoSuerteAzar extends DComandoServicioInterno {
  /** Objeto de transporte de JuegoSuerteAzar */
  protected DJuegoSuerteAzarTO toJuegoSuerteAzar;
  /** Llave primaria de JuegoSuerteAzar */
  protected DJuegoSuerteAzarPKTO pkJuegoSuerteAzar;
  /** Atributos de JuegoSuerteAzar */
  protected DJuegoSuerteAzarAttTO attJuegoSuerteAzar;

  /**
   * Inicializa la creaciï¿½ de JuegoSuerteAzar.
   * @param toJuegoSuerteAzar Objeto de Transporte de JuegoSuerteAzar
   */
  public void inicializar(DJuegoSuerteAzarTO toJuegoSuerteAzar) {
    this.toJuegoSuerteAzar = toJuegoSuerteAzar;
    if (toJuegoSuerteAzar != null) {
      pkJuegoSuerteAzar = this.toJuegoSuerteAzar.getPK();
      attJuegoSuerteAzar = this.toJuegoSuerteAzar.getAtt();
    }
  }


  /**
   * Ejecuta el comando de servicio.
   * @throws DExcepcion Si ocurre algún error al realizar la
   * la creación de JuegoSuerteAzar
   */
  protected void ejecutarComando() throws DExcepcion {
      throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    return new DCmdSrvCrearJuegoSuerteAzar();
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
    return "Permite crear un objeto JuegoSuerteAzar";
  }

  /**
   * Método para validar los parámetros inicializados, invocado
   * previamente a la ejecucióndel comando.
   * @return true si los parámetros son válidos; false de lo contrario
   * @throws DValidarExcepcion Si los parámetros no son válidos
   */
  public boolean validar() throws DValidarExcepcion {
      Map parametros=new HashMap();
      parametros.put(this.getClass().getName()+":validar:toJuegoSuerteAzar",toJuegoSuerteAzar);
      parametros.put(this.getClass().getName()+":validar:attJuegoSuerteAzar",attJuegoSuerteAzar);
      parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar",pkJuegoSuerteAzar);
      parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar.getIdeDocumento()",pkJuegoSuerteAzar.getIdeDocumento());
      parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar.getNumRepeticion()",pkJuegoSuerteAzar.getNumRepeticion());
      validarParametros("Crear",parametros);
      return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como parï¿½etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdSrvCrearJuegoSuerteAzar) {
      DCmdSrvCrearJuegoSuerteAzar copia = (DCmdSrvCrearJuegoSuerteAzar) comando;
      copia.toJuegoSuerteAzar = toJuegoSuerteAzar;
      copia.pkJuegoSuerteAzar = pkJuegoSuerteAzar;
      copia.attJuegoSuerteAzar = attJuegoSuerteAzar;
    }
  }
}
