/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.solicitudesdeclaracion;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de acci�n utilizado para crear un objeto SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccCrearSolicitudDeclaracion extends DComandoAccion {
  /** Objeto de transporte de SolicitudDeclaracion */
  protected DSolicitudDeclaracionTO toSolicitudDeclaracion;
  /** Llave primaria de SolicitudDeclaracion */
  protected DSolicitudDeclaracionPKTO pkSolicitudDeclaracion;
  /** Atributos de SolicitudDeclaracion */
  protected DSolicitudDeclaracionAttTO attSolicitudDeclaracion;

  /**
   * Inicializa la creaci� de SolicitudDeclaracion.
   * @param toSolicitudDeclaracion Objeto de Transporte de SolicitudDeclaracion
   */
  public void inicializar(DSolicitudDeclaracionTO toSolicitudDeclaracion) {
    isOk = false;
    this.toSolicitudDeclaracion = toSolicitudDeclaracion;
    if (toSolicitudDeclaracion != null) {
      pkSolicitudDeclaracion = this.toSolicitudDeclaracion.getPK();
      attSolicitudDeclaracion = this.toSolicitudDeclaracion.getAtt();
    }
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
    return new DCmdAccCrearSolicitudDeclaracion();
  }

  /**
   * Indica si el comando es auditable.
   * @return true si el comando es auditable; false de lo contrario
   */
  public boolean isAuditable() {
    return true;
  }

  /**
   * Obtiene la descripci� del comando.
   * @return Un String con la descripci� del comando
   */
  public String getDescripcion() {
    return "Permite crear un objeto SolicitudDeclaracion";
  }

  /**
   * M�todo para validar los par�metros inicializados, invocado
   * previamente a la ejecuci�n del comando.
   * @return true si los par�metros son v�lidos; false de lo contrario
   * @throws DValidarExcepcion Si los parametros no son v�lidos
   */
  public boolean validar() throws DValidarExcepcion {
   		Map parametros=new HashMap();
	    parametros.put(this.getClass().getName()+":validar:toSolicitudDeclaracion",toSolicitudDeclaracion);
        parametros.put(this.getClass().getName()+":validar:attSolicitudDeclaracion",attSolicitudDeclaracion);    
    	parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion",pkSolicitudDeclaracion);
		parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion.getIdeDocumento()",attSolicitudDeclaracion.getIdeDocumento());
		parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion.getNumRepeticion()",attSolicitudDeclaracion.getNumRepeticion());
		parametros.put(this.getClass().getName()+":validar:attSolicitudDeclaracion.getIdeFormato()",attSolicitudDeclaracion.getIdeFormato());
		validarParametros("Crear",parametros);
    	return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como par�etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdAccCrearSolicitudDeclaracion) {
      DCmdAccCrearSolicitudDeclaracion copia = (DCmdAccCrearSolicitudDeclaracion) comando;
      copia.toSolicitudDeclaracion = toSolicitudDeclaracion;
      copia.pkSolicitudDeclaracion = pkSolicitudDeclaracion;
      copia.attSolicitudDeclaracion = attSolicitudDeclaracion;
    }
  }
}