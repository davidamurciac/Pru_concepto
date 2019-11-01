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
 * <p>Descripcion: Objeto de transporte para los atributos de JuegoSuerteAzar.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: DIAN</p>
 *
 * @author dmahechav
 * @version 1.0
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DJuegoSuerteAzarAttTO implements IDTO {
  // Atributos
  private java.lang.Long ideDocumentoCarga;
  private java.lang.Integer numRepeticionCarga;
  private java.lang.Integer codEstado;
  private java.lang.Long ideUsuarioCambio;
  private java.sql.Timestamp fecCambio;
  private java.lang.Long idePersona;
  private java.lang.Long ideTarea;

  /**
   * Construye un nuevo DJuegoSuerteAzarAttTO por defecto.
   */
  public DJuegoSuerteAzarAttTO() { }

  /**
   * Construye un nuevo DJuegoSuerteAzarAttTO con los atributos.
   * @param ideDocumentoCarga java.lang.Long
   * @param numRepeticionCarga java.lang.Integer
   * @param codEstado java.lang.Integer
   * @param ideUsuarioCambio java.lang.Long
   * @param fecCambio java.sql.Timestamp
   * @param idePersona java.lang.Long
   * @param ideTarea java.lang.Long
   */
  public DJuegoSuerteAzarAttTO(java.lang.Long ideDocumentoCarga, java.lang.Integer numRepeticionCarga, java.lang.Integer codEstado, java.lang.Long ideUsuarioCambio, java.sql.Timestamp fecCambio, java.lang.Long idePersona, java.lang.Long ideTarea) {
    setIdeDocumentoCarga(ideDocumentoCarga);
    setNumRepeticionCarga(numRepeticionCarga);
    setCodEstado(codEstado);
    setIdeUsuarioCambio(ideUsuarioCambio);
    setFecCambio(fecCambio);
    setIdePersona(idePersona);
    setIdeTarea(ideTarea);
  }

  /**
   * Devuelve el valor de ideDocumentoCarga.
   * @return Un objeto java.lang.Long
   */
  public java.lang.Long getIdeDocumentoCarga() {
    return ideDocumentoCarga;
  }

  /**
   * Establece el valor de ideDocumentoCarga.
   * @param ideDocumentoCarga El nuevo valor de ideDocumentoCarga
   */
  public void setIdeDocumentoCarga(java.lang.Long ideDocumentoCarga) {
    this.ideDocumentoCarga = ideDocumentoCarga;
  }

  /**
   * Devuelve el valor de numRepeticionCarga.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getNumRepeticionCarga() {
    return numRepeticionCarga;
  }

  /**
   * Establece el valor de numRepeticionCarga.
   * @param numRepeticionCarga El nuevo valor de numRepeticionCarga
   */
  public void setNumRepeticionCarga(java.lang.Integer numRepeticionCarga) {
    this.numRepeticionCarga = numRepeticionCarga;
  }

  /**
   * Devuelve el valor de codEstado.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getCodEstado() {
    return codEstado;
  }

  /**
   * Establece el valor de codEstado.
   * @param codEstado El nuevo valor de codEstado
   */
  public void setCodEstado(java.lang.Integer codEstado) {
    this.codEstado = codEstado;
  }

  /**
   * Devuelve el valor de ideUsuarioCambio.
   * @return Un objeto java.lang.Long
   */
  public java.lang.Long getIdeUsuarioCambio() {
    return ideUsuarioCambio;
  }

  /**
   * Establece el valor de ideUsuarioCambio.
   * @param ideUsuarioCambio El nuevo valor de ideUsuarioCambio
   */
  public void setIdeUsuarioCambio(java.lang.Long ideUsuarioCambio) {
    this.ideUsuarioCambio = ideUsuarioCambio;
  }

  /**
   * Devuelve el valor de fecCambio.
   * @return Un objeto java.sql.Timestamp
   */
  public java.sql.Timestamp getFecCambio() {
    return fecCambio;
  }

  /**
   * Establece el valor de fecCambio.
   * @param fecCambio El nuevo valor de fecCambio
   */
  public void setFecCambio(java.sql.Timestamp fecCambio) {
    this.fecCambio = fecCambio;
  }

  /**
   * Devuelve el valor de idePersona.
   * @return Un objeto java.lang.Long
   */
  public java.lang.Long getIdePersona() {
    return idePersona;
  }

  /**
   * Establece el valor de idePersona.
   * @param idePersona El nuevo valor de idePersona
   */
  public void setIdePersona(java.lang.Long idePersona) {
    this.idePersona = idePersona;
  }

  /**
   * Devuelve el valor de ideTarea.
   * @return Un objeto java.lang.Long
   */
  public java.lang.Long getIdeTarea() {
    return ideTarea;
  }

  /**
   * Establece el valor de ideTarea.
   * @param ideTarea El nuevo valor de ideTarea
   */
  public void setIdeTarea(java.lang.Long ideTarea) {
    this.ideTarea = ideTarea;
  }

}
