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
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de acci�n utilizado para consultar objetos SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstSolicitudDeclaracion extends DComandoAccion {


	
	/**
	 * Constante de tipo <code>int</code> que representa la operaci�n de CONSULTA GEN�RICA.
	 */
	protected static final int CONSULTA_GENERICA=1;
	
	
  /** Tipo de operaci�n de consulta */
  protected int tipoOperacion = -1;
  /** Colecci�n de objetos DSolicitudDeclaracionTO */
  protected Collection objetosSolicitudDeclaracion;
  /**
   * Atributo de tipo <code>DSolicitudDeclaracionTO</code> con el 
   */
  protected DSolicitudDeclaracionTO to;

  
  
  
  
  
  /**
   * Inicializa el comando para la operaci�n CONSULTA GEN�RICA.
   * 
   * @param to Dato de tipo <code>DSolicitudDeclaracionTO</code> con los valores para la b�squeda.
   */
  public void inicializarConsultaGenerica(DSolicitudDeclaracionTO to){
	  tipoOperacion=CONSULTA_GENERICA;
	  this.to=to;
  }/*fin de inicializarConsultaGenerica*/
  
  
  
  /**
   * Devuelve la colecci�n de objetos SolicitudDeclaracion que se hayan consultado.
   * @return Un Collection con objetos DSolicitudDeclaracionTO
   */
  public Collection getColeccionSolicitudDeclaracion() {
    return objetosSolicitudDeclaracion;
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
    return new DCmdAccConsLstSolicitudDeclaracion();
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
    return "Permite consultar objetos SolicitudDeclaracion";
  }

  /**
   * M�todo para validar los par�metros inicializados, invocado
   * previamente a la ejecuci�n del comando.
   * @return true si los par�metros son v�lidos; false de lo contrario
   * @throws DValidarExcepcion Si los par�metros no son v�lidos
   */
  public boolean validar() throws DValidarExcepcion {
	  Map parametros=new HashMap();
	  String operacion="Listar";
	  switch(tipoOperacion){
	     case CONSULTA_GENERICA:
	    	 operacion="La consulta";
	    	 parametros.put(this.getClass().getName()+":validar:to",to);
	    	 parametros.put(this.getClass().getName()+":validar:to.getPK()",to.getPK());
	    	 parametros.put(this.getClass().getName()+":validar:to.getAtt()",to.getAtt());	    	 
	    	 break;
	  }/*fin de switch*/
	  validarParametros(operacion,parametros);
	  return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como par�etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdAccConsLstSolicitudDeclaracion) {
      DCmdAccConsLstSolicitudDeclaracion copia = (DCmdAccConsLstSolicitudDeclaracion) comando;
      copia.tipoOperacion = tipoOperacion;
      copia.objetosSolicitudDeclaracion = objetosSolicitudDeclaracion;
      copia.to=to;
    }
  }
}