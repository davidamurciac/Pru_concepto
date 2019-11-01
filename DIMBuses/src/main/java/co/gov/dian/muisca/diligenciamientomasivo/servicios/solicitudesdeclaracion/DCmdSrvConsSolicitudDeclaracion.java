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
 * <p>Descripcion: Comando de servicio utilizado para consultar un objeto SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvConsSolicitudDeclaracion extends DComandoServicioNegocio {
  /** Objeto de transporte de SolicitudDeclaracion */
  protected DSolicitudDeclaracionTO toSolicitudDeclaracion;
  /** Llave primaria de SolicitudDeclaracion */
  protected DSolicitudDeclaracionPKTO pkSolicitudDeclaracion;
  /** Atributos de SolicitudDeclaracion */
  protected DSolicitudDeclaracionAttTO attSolicitudDeclaracion;

  /**
   * Inicializa la consulta por llave primaria.
   * @param pkSolicitudDeclaracion Llave primaria de SolicitudDeclaracion
   */
  public void inicializar(DSolicitudDeclaracionPKTO pkSolicitudDeclaracion) {
    toSolicitudDeclaracion = null;
    attSolicitudDeclaracion = null;
    this.pkSolicitudDeclaracion = pkSolicitudDeclaracion;
  }

  /**
   * Devuelve el objeto SolicitudDeclaracion que se haya consultado.
   * @return Un objeto DSolicitudDeclaracionTO
   */
  public DSolicitudDeclaracionTO getSolicitudDeclaracion() {
    return toSolicitudDeclaracion;
  }

  /**
   * Ejecuta el comando de servicio.
   * @throws DExcepcion Si ocurre algún error al realizar la
   * la consulta de SolicitudDeclaracion
   */
  protected void ejecutarComando() throws DExcepcion {
      throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    return new DCmdSrvConsSolicitudDeclaracion();
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
    return "Permite consultar un objeto SolicitudDeclaracion";
  }

  /**
   * Mï¿½odo para validar los parámetros inicializados, invocado
   * previamente a la ejecución del comando.
   * @return true si los parámetros son válidos; false de lo contrario
   * @throws DValidarExcepcion Si los parámetros no son válidos
   */
  public boolean validar() throws DValidarExcepcion {
    Map parametros=new HashMap();
    parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion",pkSolicitudDeclaracion);
	validarParametros("Consulta",parametros);
    return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como parï¿½etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdSrvConsSolicitudDeclaracion) {
      DCmdSrvConsSolicitudDeclaracion copia = (DCmdSrvConsSolicitudDeclaracion) comando;
      copia.toSolicitudDeclaracion = toSolicitudDeclaracion;
      copia.pkSolicitudDeclaracion = pkSolicitudDeclaracion;
      copia.attSolicitudDeclaracion = attSolicitudDeclaracion;
    }
  }
}