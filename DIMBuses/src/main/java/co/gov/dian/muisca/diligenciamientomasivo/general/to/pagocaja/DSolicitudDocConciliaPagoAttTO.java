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
 * <p>Descripcion: Objeto de transporte para los atributos de SolicitudDocConciliaPago.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DSolicitudDocConciliaPagoAttTO implements IDTO {
  // Atributos
  private java.lang.Long nitDeclaracion;
  private java.lang.String cadHas;
  private java.lang.Integer valFormaPago;
  private java.lang.Integer valConcepto;
  private java.lang.Double valorRecaudado;
  private java.lang.Integer fecLimitePago;
  private java.sql.Timestamp fecTransaccion;
  private java.lang.Integer codEntRecaudadora;
  private java.lang.Integer codSucursal;
  private java.lang.Integer codCajero;
  private java.lang.String numTransaccion;
  private java.lang.Integer codHorario;
  private java.sql.Timestamp fecProceso;
  private java.lang.Integer codEstadoProceso;

  /**
   * Construye un nuevo DSolicitudDocConciliaPagoAttTO por defecto.
   */
  public DSolicitudDocConciliaPagoAttTO() { }

  /**
   * Construye un nuevo DSolicitudDocConciliaPagoAttTO con los atributos.
   * @param nitDeclaracion java.lang.Long
   * @param cadHas java.lang.String
   * @param valFormaPago java.lang.Integer
   * @param valConcepto java.lang.Integer
   * @param valorRecaudado java.lang.Double
   * @param fecLimitePago java.lang.Integer
   * @param fecTransaccion java.sql.Timestamp
   * @param codEntRecaudadora java.lang.Integer
   * @param codSucursal java.lang.Integer
   * @param codCajero java.lang.Integer
   * @param numTransaccion java.lang.String
   * @param codHorario java.lang.Integer
   * @param fecProceso java.sql.Timestamp
   * @param codEstadoProceso java.lang.Integer
   */
  public DSolicitudDocConciliaPagoAttTO(java.lang.Long nitDeclaracion, java.lang.String cadHas, java.lang.Integer valFormaPago, java.lang.Integer valConcepto, java.lang.Double valorRecaudado, java.lang.Integer fecLimitePago, java.sql.Timestamp fecTransaccion, java.lang.Integer codEntRecaudadora, java.lang.Integer codSucursal, java.lang.Integer codCajero, java.lang.String numTransaccion, java.lang.Integer codHorario, java.sql.Timestamp fecProceso, java.lang.Integer codEstadoProceso) {
    setNitDeclaracion(nitDeclaracion);
    setCadHas(cadHas);
    setValFormaPago(valFormaPago);
    setValConcepto(valConcepto);
    setValorRecaudado(valorRecaudado);
    setFecLimitePago(fecLimitePago);
    setFecTransaccion(fecTransaccion);
    setCodEntRecaudadora(codEntRecaudadora);
    setCodSucursal(codSucursal);
    setCodCajero(codCajero);
    setNumTransaccion(numTransaccion);
    setCodHorario(codHorario);
    setFecProceso(fecProceso);
    setCodEstadoProceso(codEstadoProceso);
  }

  /**
   * Devuelve el valor de nitDeclaracion.
   * @return Un objeto java.lang.Long
   */
  public java.lang.Long getNitDeclaracion() {
    return nitDeclaracion;
  }

  /**
   * Establece el valor de nitDeclaracion.
   * @param nitDeclaracion El nuevo valor de nitDeclaracion
   */
  public void setNitDeclaracion(java.lang.Long nitDeclaracion) {
    this.nitDeclaracion = nitDeclaracion;
  }

  /**
   * Devuelve el valor de cadHas.
   * @return Un objeto java.lang.String
   */
  public java.lang.String getCadHas() {
    return cadHas;
  }

  /**
   * Establece el valor de cadHas.
   * @param cadHas El nuevo valor de cadHas
   */
  public void setCadHas(java.lang.String cadHas) {
    this.cadHas = cadHas;
  }

  /**
   * Devuelve el valor de valFormaPago.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getValFormaPago() {
    return valFormaPago;
  }

  /**
   * Establece el valor de valFormaPago.
   * @param valFormaPago El nuevo valor de valFormaPago
   */
  public void setValFormaPago(java.lang.Integer valFormaPago) {
    this.valFormaPago = valFormaPago;
  }

  /**
   * Devuelve el valor de valConcepto.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getValConcepto() {
    return valConcepto;
  }

  /**
   * Establece el valor de valConcepto.
   * @param valConcepto El nuevo valor de valConcepto
   */
  public void setValConcepto(java.lang.Integer valConcepto) {
    this.valConcepto = valConcepto;
  }

  /**
   * Devuelve el valor de valorRecaudado.
   * @return Un objeto java.lang.Double
   */
  public java.lang.Double getValorRecaudado() {
    return valorRecaudado;
  }

  /**
   * Establece el valor de valorRecaudado.
   * @param valorRecaudado El nuevo valor de valorRecaudado
   */
  public void setValorRecaudado(java.lang.Double valorRecaudado) {
    this.valorRecaudado = valorRecaudado;
  }

  /**
   * Devuelve el valor de fecLimitePago.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getFecLimitePago() {
    return fecLimitePago;
  }

  /**
   * Establece el valor de fecLimitePago.
   * @param fecLimitePago El nuevo valor de fecLimitePago
   */
  public void setFecLimitePago(java.lang.Integer fecLimitePago) {
    this.fecLimitePago = fecLimitePago;
  }

  /**
   * Devuelve el valor de fecTransaccion.
   * @return Un objeto java.sql.Timestamp
   */
  public java.sql.Timestamp getFecTransaccion() {
    return fecTransaccion;
  }

  /**
   * Establece el valor de fecTransaccion.
   * @param fecTransaccion El nuevo valor de fecTransaccion
   */
  public void setFecTransaccion(java.sql.Timestamp fecTransaccion) {
    this.fecTransaccion = fecTransaccion;
  }

  /**
   * Devuelve el valor de codEntRecaudadora.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getCodEntRecaudadora() {
    return codEntRecaudadora;
  }

  /**
   * Establece el valor de codEntRecaudadora.
   * @param codEntRecaudadora El nuevo valor de codEntRecaudadora
   */
  public void setCodEntRecaudadora(java.lang.Integer codEntRecaudadora) {
    this.codEntRecaudadora = codEntRecaudadora;
  }

  /**
   * Devuelve el valor de codSucursal.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getCodSucursal() {
    return codSucursal;
  }

  /**
   * Establece el valor de codSucursal.
   * @param codSucursal El nuevo valor de codSucursal
   */
  public void setCodSucursal(java.lang.Integer codSucursal) {
    this.codSucursal = codSucursal;
  }

  /**
   * Devuelve el valor de codCajero.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getCodCajero() {
    return codCajero;
  }

  /**
   * Establece el valor de codCajero.
   * @param codCajero El nuevo valor de codCajero
   */
  public void setCodCajero(java.lang.Integer codCajero) {
    this.codCajero = codCajero;
  }

  /**
   * Devuelve el valor de numTransaccion.
   * @return Un objeto java.lang.String
   */
  public java.lang.String getNumTransaccion() {
    return numTransaccion;
  }

  /**
   * Establece el valor de numTransaccion.
   * @param numTransaccion El nuevo valor de numTransaccion
   */
  public void setNumTransaccion(java.lang.String numTransaccion) {
    this.numTransaccion = numTransaccion;
  }

  /**
   * Devuelve el valor de codHorario.
   * @return Un objeto java.lang.Integer
   */
  public java.lang.Integer getCodHorario() {
    return codHorario;
  }

  /**
   * Establece el valor de codHorario.
   * @param codHorario El nuevo valor de codHorario
   */
  public void setCodHorario(java.lang.Integer codHorario) {
    this.codHorario = codHorario;
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
