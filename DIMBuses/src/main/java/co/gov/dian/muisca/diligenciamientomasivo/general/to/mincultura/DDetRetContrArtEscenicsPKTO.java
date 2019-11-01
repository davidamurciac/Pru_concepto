/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;


/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de transporte para la PK de DetRetContrArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDetRetContrArtEscenicsPKTO implements IDTO {

	private static final long serialVersionUID = 742712333L; 

  // Campos de la PK
	private java.lang.Long ideDocumento;
	private java.lang.Integer numRepeticion;
	private java.lang.Short numOcurrencia;
	private java.lang.Integer numItem;

	/**
	 * Construye un nuevo DDetRetContrArtEscenicsPKTO por defecto.
	 */
	public DDetRetContrArtEscenicsPKTO() { }

	/**
	 * Construye un nuevo DDetRetContrArtEscenicsPKTO con los elementos de la llave primaria.
	 * @param ideDocumento java.lang.Long
	 * @param numRepeticion java.lang.Integer
	 * @param numOcurrencia java.lang.Short
	 * @param numItem java.lang.Integer
	 */
	public DDetRetContrArtEscenicsPKTO(java.lang.Long ideDocumento, java.lang.Integer numRepeticion, java.lang.Short numOcurrencia, java.lang.Integer numItem) {
		setIdeDocumento(ideDocumento);
		setNumRepeticion(numRepeticion);
		setNumOcurrencia(numOcurrencia);
		setNumItem(numItem);
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
	 * Devuelve el valor de numOcurrencia.
	 * @return Un objeto java.lang.Short
	 */
	public java.lang.Short getNumOcurrencia() {
		return numOcurrencia;
	}

	/**
	 * Establece el valor de numOcurrencia.
	 * @param numOcurrencia El nuevo valor de numOcurrencia
	 */
	public void setNumOcurrencia(java.lang.Short numOcurrencia) {
		this.numOcurrencia = numOcurrencia;
	}

	/**
	 * Devuelve el valor de numItem.
	 * @return Un objeto java.lang.Integer
	 */
	public java.lang.Integer getNumItem() {
		return numItem;
	}

	/**
	 * Establece el valor de numItem.
	 * @param numItem El nuevo valor de numItem
	 */
	public void setNumItem(java.lang.Integer numItem) {
		this.numItem = numItem;
	}
}
