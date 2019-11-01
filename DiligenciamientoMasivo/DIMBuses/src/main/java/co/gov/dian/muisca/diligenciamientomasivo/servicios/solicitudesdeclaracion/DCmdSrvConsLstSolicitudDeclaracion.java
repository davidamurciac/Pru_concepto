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
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para consultar objetos SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvConsLstSolicitudDeclaracion extends DComandoServicioNegocio {

	
	/**
	 * Constante de tipo <code>int</code> que representa la operación de CONSULTA GENÉRICA.
	 */
	protected static final int CONSULTA_GENERICA=1;
	
	/**
	 * Constante de tipo <code>int</code> que representa la operación de CONSULTA GENÉRICA ATRIBUTOS.
	 */
	protected static final int CONSULTA_GENERICA_ATT=2; 
	protected static final int CONSULTA_FORMATO_FEC_CAMBIO=3;
	

  /** Tipo de operaciï¿½ de consulta */
  protected int tipoOperacion = -1;
  /** Colecciï¿½ de objetos DSolicitudDeclaracionTO */
  protected Collection<DSolicitudDeclaracionTO> objetosSolicitudDeclaracion;
  /**
   * Atributo de tipo <code>DSolicitudDeclaracionTO</code> con el 
   */
  protected DSolicitudDeclaracionTO to;

    
  
  /**
   * Inicializa el comando para la operación CONSULTA GENÉRICA.
   * 
   * @param to Dato de tipo <code>DSolicitudDeclaracionTO</code> con los valores para la búsqueda.
   */
  public void inicializarConsultaGenerica(DSolicitudDeclaracionTO to){
	  tipoOperacion=CONSULTA_GENERICA;
	  this.to=to;
  }/*fin de inicializarConsultaGenerica*/
  
  public void inicializarConsultaGenericaAtt(DSolicitudDeclaracionTO to){
	  tipoOperacion=CONSULTA_GENERICA_ATT;
	  this.to=to;
  }/*fin de inicializarConsultaGenerica*/
 
  /**
   * Inicializa el DAO para hacer consultas por ideFormato y FecCambio.
   * 
   * @param to Dato de tipo <code>DSolictudDeclaracionTO</code> con los parámetros de búsqueda.
   */
  public void inicializarConsultarPorFormatoFecCambio(DSolicitudDeclaracionTO to) {
	  tipoOperacion=CONSULTA_FORMATO_FEC_CAMBIO;
	  this.to=to;
  }
  
  
  /**
   * Devuelve la colecciï¿½ de objetos SolicitudDeclaracion que se hayan consultado.
   * @return Un Collection con objetos DSolicitudDeclaracionTO
   */
  public Collection getColeccionSolicitudDeclaracion() {
    return objetosSolicitudDeclaracion;
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
    return new DCmdSrvConsLstSolicitudDeclaracion();
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
   * @return Un String con la descripciï¿½ del comando
   */
  public String getDescripcion() {
    return "Permite consultar objetos SolicitudDeclaracion";
  }

  /**
   * Método para validar los parámetros inicializados, invocado
   * previamente a la ejecución del comando.
   * @return true si los paretros son váidos; false de lo contrario
   * @throws DValidarExcepcion Si los parámetros no son váidos
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
	     case CONSULTA_GENERICA_ATT:
	     case CONSULTA_FORMATO_FEC_CAMBIO:
	     operacion="La consulta";
    	 parametros.put(this.getClass().getName()+":validar:to",to);
    	 parametros.put(this.getClass().getName()+":validar:to.getAtt()",to.getAtt());	    	 
    	 break;
	  }/*fin de switch*/
	  validarParametros(operacion,parametros);
	  return true;
  }

  /**
   * Para copiar el contenido del comando actual al comando enviado como parï¿½etro.
   * @param comando Comando sobre el cual copiar
   */
  public void asignar(IDComando comando) {
    if (comando instanceof DCmdSrvConsLstSolicitudDeclaracion) {
      DCmdSrvConsLstSolicitudDeclaracion copia = (DCmdSrvConsLstSolicitudDeclaracion) comando;
      copia.tipoOperacion = tipoOperacion;
      copia.objetosSolicitudDeclaracion = objetosSolicitudDeclaracion;
      copia.to=to;
    }
  }
}