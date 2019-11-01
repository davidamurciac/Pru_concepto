package co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;



/**
 * Objeto de transporte que representa un prevalidador inicial ETESA visualizado en la pantalla de Bandeja de Salida
 * 
 * @author nfontechar
 *
 */
public class DPrevalidadorBSTO implements IDTO {
	
	
	private String nombre;
	private String fecGen;
	private String ideSolicitud;
	private String ideRecurso;
	
	
	
	public DPrevalidadorBSTO(String nombre, String fecGen) {
		this.nombre = nombre;
		this.fecGen = fecGen;
		this.ideSolicitud=null;
		this.ideSolicitud=null;
	}
	
	public DPrevalidadorBSTO(String nombre, String fecGen, String ideSolicitud) {
		this.nombre = nombre;
		this.fecGen = fecGen;
		this.ideSolicitud=ideSolicitud;
		this.ideSolicitud=null;
	}
	
		
	public DPrevalidadorBSTO(String nombre, String fecGen, String ideSolicitud,	String ideRecurso) {
		this.nombre = nombre;
		this.fecGen = fecGen;
		this.ideSolicitud = ideSolicitud;
		this.ideRecurso = ideRecurso;
	}

	public DPrevalidadorBSTO() {
		this.nombre = null;
		this.fecGen = null;
		this.ideSolicitud=null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecGen() {
		return fecGen;
	}

	public void setFecGen(String fecGen) {
		this.fecGen = fecGen;
	}

	public String getIdeSolicitud() {
		return ideSolicitud;
	}

	public void setIdeSolicitud(String ideSolicitud) {
		this.ideSolicitud = ideSolicitud;
	}

	public String getIdeRecurso() {
		return ideRecurso;
	}

	public void setIdeRecurso(String ideRecurso) {
		this.ideRecurso = ideRecurso;
	}
	
	
	
	

}/*fin de class*/
