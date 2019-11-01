/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.to.pagocaja;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;


/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de transporte para SolicitudDocConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DSolicitudDocConciliaPagoTO implements IDTO {
  /**
   * Llave primaria del objeto
   */
  private DSolicitudDocConciliaPagoPKTO pk;
  /**
   * Atributos del objeto
   */
  private DSolicitudDocConciliaPagoAttTO att;

  /**
   * Construye un nuevo DSolicitudDocConciliaPagoTO por defecto.
   */
  public DSolicitudDocConciliaPagoTO() {
    this.pk  = new DSolicitudDocConciliaPagoPKTO();
    this.att = new DSolicitudDocConciliaPagoAttTO();
  }

  /**
   * Construye un nuevo DSolicitudDocConciliaPagoTO con la PK y el Att.
   * @param pk Llave primaria
   * @param att Atributos
   */
  public DSolicitudDocConciliaPagoTO(DSolicitudDocConciliaPagoPKTO pk, DSolicitudDocConciliaPagoAttTO att) {
    this.pk = pk;
    this.att = att;
  }

  /**
   * Devuelve la llave primaria del objeto.
   * @return Un objeto SolicitudDocConciliaPagoPKTO
   */
  public DSolicitudDocConciliaPagoPKTO getPK() {
    return pk;
  }

  /**
   * Devuelve los atributos del objeto.
   * @return Un objeto DSolicitudDocConciliaPagoAttTO
   */
  public DSolicitudDocConciliaPagoAttTO getAtt() {
    return att;
  }

  /**
   * Establece la llave primaria del objeto.
   * @param pk Llave primaria
   */
  public void setPK(DSolicitudDocConciliaPagoPKTO pk) {
    this.pk = pk;
  }

  /**
   * Establece los atributos del objeto.
   * @param att Atributos
   */
  public void setAtt(DSolicitudDocConciliaPagoAttTO att) {
    this.att = att;
  }
  
  @Override
 	public String toString() {
 		StringBuffer sb = new StringBuffer();
 		if( pk!=null ){
 			sb.append( pk.toString() );	
 		}
 		if( att!=null ){
 			sb.append( att.toString() );
 		}
 		return sb.toString(); 
 	}

}
