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
 * <p>Descripcion: Objeto de transporte para SolicitudConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DSolicitudConciliaPagoTO implements IDTO {
  /**
   * Llave primaria del objeto
   */
  private DSolicitudConciliaPagoPKTO pk;
  /**
   * Atributos del objeto
   */
  private DSolicitudConciliaPagoAttTO att;

  /**
   * Construye un nuevo DSolicitudConciliaPagoTO por defecto.
   */
  public DSolicitudConciliaPagoTO() {
    this.pk  = new DSolicitudConciliaPagoPKTO();
    this.att = new DSolicitudConciliaPagoAttTO();
  }

  /**
   * Construye un nuevo DSolicitudConciliaPagoTO con la PK y el Att.
   * @param pk Llave primaria
   * @param att Atributos
   */
  public DSolicitudConciliaPagoTO(DSolicitudConciliaPagoPKTO pk, DSolicitudConciliaPagoAttTO att) {
    this.pk = pk;
    this.att = att;
  }

  /**
   * Devuelve la llave primaria del objeto.
   * @return Un objeto SolicitudConciliaPagoPKTO
   */
  public DSolicitudConciliaPagoPKTO getPK() {
    return pk;
  }

  /**
   * Devuelve los atributos del objeto.
   * @return Un objeto DSolicitudConciliaPagoAttTO
   */
  public DSolicitudConciliaPagoAttTO getAtt() {
    return att;
  }

  /**
   * Establece la llave primaria del objeto.
   * @param pk Llave primaria
   */
  public void setPK(DSolicitudConciliaPagoPKTO pk) {
    this.pk = pk;
  }

  /**
   * Establece los atributos del objeto.
   * @param att Atributos
   */
  public void setAtt(DSolicitudConciliaPagoAttTO att) {
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
