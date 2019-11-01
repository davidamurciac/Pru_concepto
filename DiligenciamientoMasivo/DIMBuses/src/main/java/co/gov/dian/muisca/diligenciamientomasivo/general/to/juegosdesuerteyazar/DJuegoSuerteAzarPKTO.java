/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;


/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de transporte para la PK de JuegoSuerteAzar.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: DIAN</p>
 *
 * @author dmahechav
 * @version 1.0
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DJuegoSuerteAzarPKTO implements IDTO {
  // Campos de la PK
  private java.lang.Long ideDocumento;
  private java.lang.Integer numRepeticion;

  /**
   * Construye un nuevo DJuegoSuerteAzarPKTO por defecto.
   */
  public DJuegoSuerteAzarPKTO() { }

  /**
   * Construye un nuevo DJuegoSuerteAzarPKTO con los elementos de la llave primaria.
   * @param ideDocumento java.lang.Long
   * @param numRepeticion java.lang.Integer
   */
  public DJuegoSuerteAzarPKTO(java.lang.Long ideDocumento, java.lang.Integer numRepeticion) {
    setIdeDocumento(ideDocumento);
    setNumRepeticion(numRepeticion);
  }

  /**
   * Devuelve el valor de ideDocumento.
   * @return Un objeto java.lang.Long
   */
  public java.lang.Long getIdeDocumento() {
    return ideDocumento;
  }

  /**
   * Establece el valor de ideDocumento.
   * @param ideDocumento El nuevo valor de ideDocumento
   */
  public void setIdeDocumento(java.lang.Long ideDocumento) {
    this.ideDocumento = ideDocumento;
  }

  /**
   * Devuelve el valor de numRepeticion.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getNumRepeticion() {
    return numRepeticion;
  }

  /**
   * Establece el valor de numRepeticion.
   * @param numRepeticion El nuevo valor de numRepeticion
   */
  public void setNumRepeticion(java.lang.Integer numRepeticion) {
    this.numRepeticion = numRepeticion;
  }

}
