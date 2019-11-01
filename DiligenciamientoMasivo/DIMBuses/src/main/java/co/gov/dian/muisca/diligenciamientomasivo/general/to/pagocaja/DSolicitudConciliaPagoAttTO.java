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
 * <p>Descripcion: Objeto de transporte para los atributos de SolicitudConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DSolicitudConciliaPagoAttTO implements IDTO {
  // Atributos
  private java.lang.Long numNit;
  private java.lang.Long idePersonaSolicitud;
  private java.sql.Timestamp fecSolicitud;
  private java.lang.Long numTotalRegistros;
  private java.lang.Integer numRegistrosPago;
  private java.lang.Double valTotal;
  private java.sql.Timestamp fecProceso;
  private java.lang.Integer codEstadoProceso;

  /**
   * Construye un nuevo DSolicitudConciliaPagoAttTO por defecto.
   */
  public DSolicitudConciliaPagoAttTO() { }

  /**
   * Construye un nuevo DSolicitudConciliaPagoAttTO con los atributos.
   * @param numNit java.lang.Long
   * @param idePersonaSolicitud java.lang.Long
   * @param fecSolicitud java.sql.Timestamp
   * @param numTotalRegistros java.lang.Long
   * @param numRegistrosPago java.lang.Integer
   * @param valTotal java.lang.Double
   * @param fecProceso java.sql.Timestamp
   * @param codEstadoProceso java.lang.Integer
   */
  public DSolicitudConciliaPagoAttTO(java.lang.Long numNit, java.lang.Long idePersonaSolicitud, java.sql.Timestamp fecSolicitud, java.lang.Long numTotalRegistros, java.lang.Integer numRegistrosPago, java.lang.Double valTotal, java.sql.Timestamp fecProceso, java.lang.Integer codEstadoProceso) {
    setNumNit(numNit);
    setIdePersonaSolicitud(idePersonaSolicitud);
    setFecSolicitud(fecSolicitud);
    setNumTotalRegistros(numTotalRegistros);
    setNumRegistrosPago(numRegistrosPago);
    setValTotal(valTotal);
    setFecProceso(fecProceso);
    setCodEstadoProceso(codEstadoProceso);
  }

  /**
   * Devuelve el valor de numNit.
   * @return Un objeto java.lang.Long
   */
  public java.lang.Long getNumNit() {
    return numNit;
  }

  /**
   * Establece el valor de numNit.
   * @param numNit El nuevo valor de numNit
   */
  public void setNumNit(java.lang.Long numNit) {
    this.numNit = numNit;
  }

  /**
   * Devuelve el valor de idePersonaSolicitud.
   * @return Un objeto java.lang.Long
   */
  public java.lang.Long getIdePersonaSolicitud() {
    return idePersonaSolicitud;
  }

  /**
   * Establece el valor de idePersonaSolicitud.
   * @param idePersonaSolicitud El nuevo valor de idePersonaSolicitud
   */
  public void setIdePersonaSolicitud(java.lang.Long idePersonaSolicitud) {
    this.idePersonaSolicitud = idePersonaSolicitud;
  }

  /**
   * Devuelve el valor de fecSolicitud.
   * @return Un objeto java.sql.Timestamp
   */
  public java.sql.Timestamp getFecSolicitud() {
    return fecSolicitud;
  }

  /**
   * Establece el valor de fecSolicitud.
   * @param fecSolicitud El nuevo valor de fecSolicitud
   */
  public void setFecSolicitud(java.sql.Timestamp fecSolicitud) {
    this.fecSolicitud = fecSolicitud;
  }

  /**
   * Devuelve el valor de numTotalRegistros.
   * @return Un objeto java.lang.Long
   */
  public java.lang.Long getNumTotalRegistros() {
    return numTotalRegistros;
  }

  /**
   * Establece el valor de numTotalRegistros.
   * @param numTotalRegistros El nuevo valor de numTotalRegistros
   */
  public void setNumTotalRegistros(java.lang.Long numTotalRegistros) {
    this.numTotalRegistros = numTotalRegistros;
  }

  /**
   * Devuelve el valor de numRegistrosPago.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getNumRegistrosPago() {
    return numRegistrosPago;
  }

  /**
   * Establece el valor de numRegistrosPago.
   * @param numRegistrosPago El nuevo valor de numRegistrosPago
   */
  public void setNumRegistrosPago(java.lang.Integer numRegistrosPago) {
    this.numRegistrosPago = numRegistrosPago;
  }

  /**
   * Devuelve el valor de valTotal.
   * @return Un objeto java.lang.Double
   */
  public java.lang.Double getValTotal() {
    return valTotal;
  }

  /**
   * Establece el valor de valTotal.
   * @param valTotal El nuevo valor de valTotal
   */
  public void setValTotal(java.lang.Double valTotal) {
    this.valTotal = valTotal;
  }

  /**
   * Devuelve el valor de fecProceso.
   * @return Un objeto java.sql.Timestamp
   */
  public java.sql.Timestamp getFecProceso() {
    return fecProceso;
  }

  /**
   * Establece el valor de fecProceso.
   * @param fecProceso El nuevo valor de fecProceso
   */
  public void setFecProceso(java.sql.Timestamp fecProceso) {
    this.fecProceso = fecProceso;
  }

  /**
   * Devuelve el valor de codEstadoProceso.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getCodEstadoProceso() {
    return codEstadoProceso;
  }

  /**
   * Establece el valor de codEstadoProceso.
   * @param codEstadoProceso El nuevo valor de codEstadoProceso
   */
  public void setCodEstadoProceso(java.lang.Integer codEstadoProceso) {
    this.codEstadoProceso = codEstadoProceso;
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
