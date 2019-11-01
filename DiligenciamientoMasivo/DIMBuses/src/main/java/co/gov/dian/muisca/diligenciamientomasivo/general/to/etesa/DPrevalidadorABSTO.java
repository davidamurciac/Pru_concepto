package co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;



/**
 * Objeto de transporte que representa un registro de la tabla EYS_DOCUMENTOS_BAN_SALIDA
 * 
 * @author nfontechar
 *
 */
public class DPrevalidadorABSTO implements IDTO {
	
	
	/*
	 * ATRIBUTOS
	 */
	private DPrevalidadorABSPKTO pk;
	private DPrevalidadorABSAttTO att;
	private String nombreArchivo;
	
	
	
	/*
	 * CONSTRUCTORES
	 */
	public DPrevalidadorABSTO(DPrevalidadorABSPKTO pk, DPrevalidadorABSAttTO att) {
		this.pk = pk;
		this.att = att;		
	}
	
	public DPrevalidadorABSTO() {
		this.pk = null;
		this.att = null;
		this.nombreArchivo=null;
	}

	
	
	/*
	 * GETTERS Y SETTERS
	 */
	public DPrevalidadorABSPKTO getPk() {
		return pk;
	}

	public void setPk(DPrevalidadorABSPKTO pk) {
		this.pk = pk;
	}

	public DPrevalidadorABSAttTO getAtt() {
		return att;
	}

	public void setAtt(DPrevalidadorABSAttTO att) {
		this.att = att;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	
	
}/*fin de class*/
