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
 * <p>Descripcion: Objeto de transporte para DetRetContrArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDetRetContrArtEscenicsTO implements IDTO {

	private static final long serialVersionUID = -1826971377L; 

	/**
	 * Llave primaria del objeto
	 */
	private DDetRetContrArtEscenicsPKTO pk;
	/**
	 * Atributos del objeto
	 */
	private DDetRetContrArtEscenicsAttTO att;

	/**
	 * Construye un nuevo DDetRetContrArtEscenicsTO por defecto.
	 */
	public DDetRetContrArtEscenicsTO() {
		this.pk  = new DDetRetContrArtEscenicsPKTO();
		this.att = new DDetRetContrArtEscenicsAttTO();
	}

	/**
	 * Construye un nuevo DDetRetContrArtEscenicsTO con la PK y el Att.
	 * @param pk Llave primaria
	 * @param att Atributos
	 */
	public DDetRetContrArtEscenicsTO(DDetRetContrArtEscenicsPKTO pk, DDetRetContrArtEscenicsAttTO att) {
		this.pk = pk;
		this.att = att;
	}

	/**
	 * Devuelve la llave primaria del objeto.
	 * @return Un objeto DetRetContrArtEscenicsPKTO
	 */
	public DDetRetContrArtEscenicsPKTO getPK() {
		return pk;
	}

	/**
	 * Devuelve los atributos del objeto.
	 * @return Un objeto DDetRetContrArtEscenicsAttTO
	 */
	public DDetRetContrArtEscenicsAttTO getAtt() {
		return att;
	}

	/**
	 * Establece la llave primaria del objeto.
	 * @param pk Llave primaria
	 */
	public void setPK(DDetRetContrArtEscenicsPKTO pk) {
		this.pk = pk;
	}

	/**
	 * Establece los atributos del objeto.
	 * @param att Atributos
	 */
	public void setAtt(DDetRetContrArtEscenicsAttTO att) {
		this.att = att;
	}
}
