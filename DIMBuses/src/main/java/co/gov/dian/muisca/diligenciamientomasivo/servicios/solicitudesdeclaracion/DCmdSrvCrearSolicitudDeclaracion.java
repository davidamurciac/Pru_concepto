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
 * <p>Descripcion: Comando de servicio utilizado para crear un objeto SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvCrearSolicitudDeclaracion extends DComandoServicioNegocio {
  /** Objeto de transporte de SolicitudDeclaracion */
  protected DSolicitudDeclaracionTO toSolicitudDeclaracion;
  /** Llave primaria de SolicitudDeclaracion */
  protected DSolicitudDeclaracionPKTO pkSolicitudDeclaracion;
  /** Atributos de SolicitudDeclaracion */
  protected DSolicitudDeclaracionAttTO attSolicitudDeclaracion;

  /**
   * Inicializa la creaciï¿½ de SolicitudDeclaracion.
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
   * la creación de SolicitudDeclaracion
   */
  protected void ejecutarComando() throws DExcepcion {
      throw new UnsupportedOperationException();
  }

  /**
   * Obtiene una copia (clon) del comando.
   * @return Un Object con la copia del comando
   */
  public Object clonar() {
    return new DCmdSrvCrearSolicitudDeclaracion();
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
    return "Permite crear un objeto SolicitudDeclaracion";
  }

  /**
   * Método para validar los parámetros inicializados, invocado
   * previamente a la ejecucióndel comando.
   * @return true si los parámetros son válidos; false de lo contrario
   * @throws DValidarExcepcion Si los parámetros no son válidos
   */
  public boolean validar() throws DValidarExcepcion {
   		Map parametros=new HashMap();
	    parametros.put(this.getClass().getName()+":validar:toSolicitudDeclaracion",toSolicitudDeclaracion);
        parametros.put(this.getClass().getName()+":validar:attSolicitudDeclaracion",attSolicitudDeclaracion);    
    	parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion",pkSolicitudDeclaracion);
		parametros.put(this.getClass().getName()+":validar:attSolicitudDeclaracion.getIdeFormato()",attSolicitudDeclaracion.getIdeFormato());
		validarParametros("Crear",parametros);
    	return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como parï¿½etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdSrvCrearSolicitudDeclaracion) {
      DCmdSrvCrearSolicitudDeclaracion copia = (DCmdSrvCrearSolicitudDeclaracion) comando;
      copia.toSolicitudDeclaracion = toSolicitudDeclaracion;
      copia.pkSolicitudDeclaracion = pkSolicitudDeclaracion;
      copia.attSolicitudDeclaracion = attSolicitudDeclaracion;
    }
  }
}