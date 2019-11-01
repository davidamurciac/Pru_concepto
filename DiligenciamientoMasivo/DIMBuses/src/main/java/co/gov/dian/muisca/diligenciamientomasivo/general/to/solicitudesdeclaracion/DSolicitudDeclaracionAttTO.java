/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion;

import org.apache.commons.lang.builder.ToStringBuilder;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;


/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de transporte para los atributos de SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DSolicitudDeclaracionAttTO implements IDTO {
  // Atributos
  private java.lang.Long ideDocumento;
  private java.lang.Integer numRepeticion;
  private java.lang.Integer ideFormato;
  private java.lang.Byte numVersionFormato;
  private java.lang.Integer ideFormatoCarga;
  private java.lang.Byte numVersionFormatoCarga;
  private java.lang.Integer codEstado;
  private java.lang.Long ideUsuarioCambio;
  private java.sql.Timestamp fecCambio;
  private java.lang.Long idePersona;
  private java.lang.Long ideTarea;

  /**
   * Construye un nuevo DSolicitudDeclaracionAttTO por defecto.
   */
  public DSolicitudDeclaracionAttTO() { }

  /**
   * Construye un nuevo DSolicitudDeclaracionAttTO con los atributos.
   * @param ideFormato java.lang.Integer
   * @param numVersionFormato java.lang.Byte
   * @param ideDocumentoCarga java.lang.Long
   * @param numRepeticionCarga java.lang.Integer
   * @param ideFormatoCarga java.lang.Integer
   * @param numVersionFormatoCarga java.lang.Byte
   * @param codEstado java.lang.Integer
   * @param ideUsuarioCambio java.lang.Long
   * @param fecCambio java.sql.Timestamp
   * @param idePersona java.lang.Long
   * @param ideTarea java.lang.Long
   */
  public DSolicitudDeclaracionAttTO(Long ideDocumento, Integer numRepeticion,java.lang.Integer ideFormato, java.lang.Byte numVersionFormato, java.lang.Integer ideFormatoCarga, java.lang.Byte numVersionFormatoCarga, java.lang.Integer codEstado, java.lang.Long ideUsuarioCambio, java.sql.Timestamp fecCambio, java.lang.Long idePersona, java.lang.Long ideTarea) {
	setIdeDocumento(ideDocumento);
	setNumRepeticion(numRepeticion);
	setIdeFormato(ideFormato);
    setNumVersionFormato(numVersionFormato);
    setIdeFormatoCarga(ideFormatoCarga);
    setNumVersionFormatoCarga(numVersionFormatoCarga);
    setCodEstado(codEstado);
    setIdeUsuarioCambio(ideUsuarioCambio);
    setFecCambio(fecCambio);
    setIdePersona(idePersona);
    setIdeTarea(ideTarea);
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


  /**
   * Devuelve el valor de ideFormato.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getIdeFormato() {
    return ideFormato;
  }

  /**
   * Establece el valor de ideFormato.
   * @param ideFormato El nuevo valor de ideFormato
   */
  public void setIdeFormato(java.lang.Integer ideFormato) {
    this.ideFormato = ideFormato;
  }

  /**
   * Devuelve el valor de numVersionFormato.
   * @return Un objeto java.lang.Byte
   */
  public java.lang.Byte getNumVersionFormato() {
    return numVersionFormato;
  }

  /**
   * Establece el valor de numVersionFormato.
   * @param numVersionFormato El nuevo valor de numVersionFormato
   */
  public void setNumVersionFormato(java.lang.Byte numVersionFormato) {
    this.numVersionFormato = numVersionFormato;
  }


  /**
   * Devuelve el valor de ideFormatoCarga.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getIdeFormatoCarga() {
    return ideFormatoCarga;
  }

  /**
   * Establece el valor de ideFormatoCarga.
   * @param ideFormatoCarga El nuevo valor de ideFormatoCarga
   */
  public void setIdeFormatoCarga(java.lang.Integer ideFormatoCarga) {
    this.ideFormatoCarga = ideFormatoCarga;
  }

  /**
   * Devuelve el valor de numVersionFormatoCarga.
   * @return Un objeto java.lang.Byte
   */
  public java.lang.Byte getNumVersionFormatoCarga() {
    return numVersionFormatoCarga;
  }

  /**
   * Establece el valor de numVersionFormatoCarga.
   * @param numVersionFormatoCarga El nuevo valor de numVersionFormatoCarga
   */
  public void setNumVersionFormatoCarga(java.lang.Byte numVersionFormatoCarga) {
    this.numVersionFormatoCarga = numVersionFormatoCarga;
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

  /**
   * Devuelve una representación en String del objeto.
   * @return String
   */
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this);
    builder.append("ideDocumento", getIdeDocumento());
    builder.append("numRepeticion", getNumRepeticion());
    builder.append("ideFormato", getIdeFormato());
    builder.append("numVersionFormato", getNumVersionFormato());
    builder.append("ideFormatoCarga", getIdeFormatoCarga());
    builder.append("numVersionFormatoCarga", getNumVersionFormatoCarga());
    builder.append("codEstado", getCodEstado());
    builder.append("ideUsuarioCambio", getIdeUsuarioCambio());
    builder.append("fecCambio", getFecCambio());
    builder.append("idePersona", getIdePersona());
    builder.append("ideTarea", getIdeTarea());
    return builder.toString();
  }

}
