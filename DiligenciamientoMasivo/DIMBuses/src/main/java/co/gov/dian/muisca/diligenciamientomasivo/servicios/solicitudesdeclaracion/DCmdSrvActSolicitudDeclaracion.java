/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para actualizar un objeto SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvActSolicitudDeclaracion extends DComandoServicioNegocio {
  /** Objeto de transporte de SolicitudDeclaracion */
  protected DSolicitudDeclaracionTO toSolicitudDeclaracion;
  /** Llave primaria de SolicitudDeclaracion */
  protected DSolicitudDeclaracionPKTO pkSolicitudDeclaracion;
  /** Atributos de SolicitudDeclaracion */
  protected DSolicitudDeclaracionAttTO attSolicitudDeclaracion;

  /**
   * Inicializa la actualizaciï¿½ de SolicitudDeclaracion.
   * @param toSolicitudDeclaracion Objeto de Transporte de SolicitudDeclaracion
   */
  public void inicializar(DSolicitudDeclaracionTO toSolicitudDeclaracion) {
    this.toSolicitudDeclaracion = toSolicitudDeclaracion;
    if (toSolicitudDeclaracion != null) {
      pkSolicitudDeclaracion = this.toSolicitudDeclaracion.getPK();
      attSolicitudDeclaracion = this.toSolicitudDeclaracion.getAtt();
    }
  }

  /**
   * Ejecuta el comando de servicio.
   * @throws DExcepcion Si ocurre algún error al realizar la
   * la actualización de SolicitudDeclaracion
   */
  protected void ejecutarComando() throws DExcepcion {
      throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    return new DCmdSrvActSolicitudDeclaracion();
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
    return "Permite actualizar un objeto SolicitudDeclaracion";
  }

  /**
   * Mï¿½odo para validar los parï¿½etros inicializados, invocado
   * previamente a la ejecuciï¿½ del comando.
   * @return true si los parï¿½etros son vï¿½idos; false de lo contrario
   * @throws DValidarExcepcion Si los parï¿½etros no son vï¿½idos
   */
  public boolean validar() throws DValidarExcepcion {
    Map parametros=new HashMap();
    parametros.put(this.getClass().getName()+":validar:toSolicitudDeclaracion",toSolicitudDeclaracion);
    parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion",pkSolicitudDeclaracion);
    parametros.put(this.getClass().getName()+":validar:attSolicitudDeclaracion",attSolicitudDeclaracion);    
	parametros.put(this.getClass().getName()+":validar:attSolicitudDeclaracion.getIdeFormato()",attSolicitudDeclaracion.getIdeFormato());
	validarParametros("Actualizar",parametros);
    return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como parï¿½etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdSrvActSolicitudDeclaracion) {
      DCmdSrvActSolicitudDeclaracion copia = (DCmdSrvActSolicitudDeclaracion) comando;
      copia.toSolicitudDeclaracion = toSolicitudDeclaracion;
      copia.pkSolicitudDeclaracion = pkSolicitudDeclaracion;
      copia.attSolicitudDeclaracion = attSolicitudDeclaracion;
    }
  }
}