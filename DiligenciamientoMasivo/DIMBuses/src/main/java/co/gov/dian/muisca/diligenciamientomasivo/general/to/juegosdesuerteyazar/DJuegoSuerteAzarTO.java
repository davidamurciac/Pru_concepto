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
 * <p>Descripcion: Objeto de transporte para JuegoSuerteAzar.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: DIAN</p>
 *
 * @author dmahechav
 * @version 1.0
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DJuegoSuerteAzarTO implements IDTO {
  /**
   * Llave primaria del objeto
   */
  private DJuegoSuerteAzarPKTO pk;
  /**
   * Atributos del objeto
   */
  private DJuegoSuerteAzarAttTO att;

  /**
   * Construye un nuevo DJuegoSuerteAzarTO por defecto.
   */
  public DJuegoSuerteAzarTO() {
    this.pk  = new DJuegoSuerteAzarPKTO();
    this.att = new DJuegoSuerteAzarAttTO();
  }

  /**
   * Construye un nuevo DJuegoSuerteAzarTO con la PK y el Att.
   * @param pk Llave primaria
   * @param att Atributos
   */
  public DJuegoSuerteAzarTO(DJuegoSuerteAzarPKTO pk, DJuegoSuerteAzarAttTO att) {
    this.pk = pk;
    this.att = att;
  }

  /**
   * Devuelve la llave primaria del objeto.
   * @return Un objeto JuegoSuerteAzarPKTO
   */
  public DJuegoSuerteAzarPKTO getPK() {
    return pk;
  }

  /**
   * Devuelve los atributos del objeto.
   * @return Un objeto DJuegoSuerteAzarAttTO
   */
  public DJuegoSuerteAzarAttTO getAtt() {
    return att;
  }

  /**
   * Establece la llave primaria del objeto.
   * @param pk Llave primaria
   */
  public void setPK(DJuegoSuerteAzarPKTO pk) {
    this.pk = pk;
  }

  /**
   * Establece los atributos del objeto.
   * @param att Atributos
   */
  public void setAtt(DJuegoSuerteAzarAttTO att) {
    this.att = att;
  }

}
