/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
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
 * <p>Descripcion: Comando de acci�n utilizado para consultar objetos JuegoSuerteAzar.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: DIAN</p>
 *
 * @author dmahechav
 * @version 1.0
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstJuegoSuerteAzar extends DComandoAccion {

  protected static final int CONSULTA_GENERICA = 0;


  protected DJuegoSuerteAzarTO toJuegoSuerteAzar;


  /** Tipo de operaci�n de consulta */
  protected int tipoOperacion = -1;
  /** Colecci�n de objetos DJuegoSuerteAzarTO */
  protected Collection objetosJuegoSuerteAzar;


  /**
   * Inicializa la consulta gen�rica de JuegoSuerteAzar.
   * @param attJuegoSuerteAzar Atributos de JuegoSuerteAzar
   */
  public void inicializarConsultaGenerica(DJuegoSuerteAzarTO toJuegoSuerteAzar) {
    tipoOperacion = CONSULTA_GENERICA;
    this.toJuegoSuerteAzar = toJuegoSuerteAzar;

  }

  /**
   * Devuelve la colecci�n de objetos JuegoSuerteAzar que se hayan consultado.
   * @return Un Collection con objetos DJuegoSuerteAzarTO
   */
  public Collection getColeccionJuegoSuerteAzar() {
    return objetosJuegoSuerteAzar;
  }

  /**
   * Ejecuta el comando de acci�n.
   */
  protected void ejecutarComando() {
      throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    return new DCmdAccConsLstJuegoSuerteAzar();
  }

  /**
   * Indica si el comando es auditable.
   * @return true si el comando es auditable; false de lo contrario
   */
  public boolean isAuditable() {
    return true;
  }

  /**
   * Obtiene la descripci�n del comando.
   * @return Un String con la descripci� del comando
   */
  public String getDescripcion() {
    return "Permite consultar objetos JuegoSuerteAzar";
  }

  /**
   * M�todo para validar los par�metros inicializados, invocado
   * previamente a la ejecuci�n del comando.
   * @return true si los par�metros son v�lidos; false de lo contrario
   * @throws DValidarExcepcion Si los par�metros no son v�lidos
   */
  public boolean validar() throws DValidarExcepcion {
    Map parametros=new HashMap();
    switch (tipoOperacion) {

      case CONSULTA_GENERICA:
     	parametros.put(this.getClass().getName()+":validar:toJuegoSuerteAzar",toJuegoSuerteAzar);
        break;


      default:
         throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos JuegoSuerteAzar"), getMensajeOperInvalida());
    }
    validarParametros("Listar",parametros);
    return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como par�etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdAccConsLstJuegoSuerteAzar) {
      DCmdAccConsLstJuegoSuerteAzar copia = (DCmdAccConsLstJuegoSuerteAzar) comando;
      copia.tipoOperacion = tipoOperacion;
      copia.objetosJuegoSuerteAzar = objetosJuegoSuerteAzar;
      copia.toJuegoSuerteAzar = toJuegoSuerteAzar;

    }
  }
}
