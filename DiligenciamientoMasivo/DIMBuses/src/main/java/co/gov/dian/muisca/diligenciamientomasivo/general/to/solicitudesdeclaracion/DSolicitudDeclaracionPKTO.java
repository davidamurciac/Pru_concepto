/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;


/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de transporte para la PK de SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DSolicitudDeclaracionPKTO implements IDTO {
  // Campos de la PK
  private java.lang.Long ideDocumentoCarga;
  private java.lang.Integer numRepeticionCarga;

  /**
   * Construye un nuevo DSolicitudDeclaracionPKTO por defecto.
   */
  public DSolicitudDeclaracionPKTO() { }

  /**
   * Construye un nuevo DSolicitudDeclaracionPKTO con los elementos de la llave primaria.
   * @param ideDocumento java.lang.Long
   * @param numRepeticion java.lang.Integer
   */
  public DSolicitudDeclaracionPKTO(Long ideDocumentoCarga, Integer numRepeticionCarga) 
  {
    setIdeDocumentoCarga(ideDocumentoCarga);
    setNumRepeticionCarga(numRepeticionCarga);
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
   * Compara el objeto actual con el objeto especificado.
   * @param objeto Objeto con el cual se compara
   * @return true si los objetos son iguales; false de lo contrario
   */
  public boolean equals(Object objeto) {
    if (this == objeto) {
      return true;
    }
    if (!(objeto instanceof DSolicitudDeclaracionPKTO)) {
      return false;
    }
    DSolicitudDeclaracionPKTO otro = (DSolicitudDeclaracionPKTO) objeto;
    EqualsBuilder builder = new EqualsBuilder();
    builder.append(getIdeDocumentoCarga(),  otro.getIdeDocumentoCarga());
    builder.append(getNumRepeticionCarga(),  otro.getNumRepeticionCarga());
    return builder.isEquals();
  }

  /**
   * Devuelve una representación en String del objeto.
   * @return String
   */
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this);
    builder.append("ideDocumento", getIdeDocumentoCarga());
    builder.append("numRepeticion", getNumRepeticionCarga());
    return builder.toString();
  }

}
