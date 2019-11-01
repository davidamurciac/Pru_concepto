/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.to.pagocaja;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;


/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de transporte para la PK de SolicitudDocConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DSolicitudDocConciliaPagoPKTO implements IDTO {
  // Campos de la PK
  private java.lang.Long ideSolicitud;
  private java.lang.Long ideDocumentoConcilia;
  private java.lang.Long ideDeclaracion;

  /**
   * Construye un nuevo DSolicitudDocConciliaPagoPKTO por defecto.
   */
  public DSolicitudDocConciliaPagoPKTO() { }

  /**
   * Construye un nuevo DSolicitudDocConciliaPagoPKTO con los elementos de la llave primaria.
   * @param ideSolicitud java.lang.Long
   * @param ideDocumentoConcilia java.lang.Long
   * @param ideDeclaracion java.lang.Long
   */
  public DSolicitudDocConciliaPagoPKTO(java.lang.Long ideSolicitud, java.lang.Long ideDocumentoConcilia, java.lang.Long ideDeclaracion) {
    setIdeSolicitud(ideSolicitud);
    setIdeDocumentoConcilia(ideDocumentoConcilia);
    setIdeDeclaracion(ideDeclaracion);
  }

  /**
   * Devuelve el valor de ideSolicitud.
   * @return Un objeto java.lang.Long
   */
  public java.lang.Long getIdeSolicitud() {
    return ideSolicitud;
  }

  /**
   * Establece el valor de ideSolicitud.
   * @param ideSolicitud El nuevo valor de ideSolicitud
   */
  public void setIdeSolicitud(java.lang.Long ideSolicitud) {
    this.ideSolicitud = ideSolicitud;
  }

  /**
   * Devuelve el valor de ideDocumentoConcilia.
   * @return Un objeto java.lang.Long
   */
  public java.lang.Long getIdeDocumentoConcilia() {
    return ideDocumentoConcilia;
  }

  /**
   * Establece el valor de ideDocumentoConcilia.
   * @param ideDocumentoConcilia El nuevo valor de ideDocumentoConcilia
   */
  public void setIdeDocumentoConcilia(java.lang.Long ideDocumentoConcilia) {
    this.ideDocumentoConcilia = ideDocumentoConcilia;
  }

  /**
   * Devuelve el valor de ideDeclaracion.
   * @return Un objeto java.lang.Long
   */
  public java.lang.Long getIdeDeclaracion() {
    return ideDeclaracion;
  }

  /**
   * Establece el valor de ideDeclaracion.
   * @param ideDeclaracion El nuevo valor de ideDeclaracion
   */
  public void setIdeDeclaracion(java.lang.Long ideDeclaracion) {
    this.ideDeclaracion = ideDeclaracion;
  }
  
  @Override
 	public String toString() {
 		StringBuffer sb = new StringBuffer();
 		
 		Method [] arrMetodos = this.getClass().getDeclaredMethods();
 		for( Method metodo : arrMetodos ){
 			int indiceSubString = 0;
 			if( metodo.getName().startsWith("get") ){
 				indiceSubString = 3;
 			}else if( metodo.getName().startsWith("is")){
 				indiceSubString = 2;
 			}
 			if( indiceSubString>0 ){
 				try {
 					sb.append( metodo.getName().substring(indiceSubString) ).append(":");
 					sb.append( metodo.invoke(this, null) ).append("\n");						
 				} catch (IllegalArgumentException e) {
 					e.printStackTrace();
 				} catch (IllegalAccessException e) {
 					e.printStackTrace();
 				} catch (InvocationTargetException e) {
 					e.printStackTrace();
 				}
 			}				
 		}
 		return sb.toString(); 
 	}

}
