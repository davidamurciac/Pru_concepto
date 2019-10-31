package co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;




/**
 * TO que representa los campos de la llave primaria del registro de una solicitud no movida
 * 
 * @author nfontechar
 *
 */
public class DSolicitudIngresoNoMovidaPKTO implements IDTO {
	
	/*
	 * ATRIBUTOS
	 */
	
	
	
	/**
	 * El ide de la solicitud
	 */
	private Long ideSolicitud;
	
	
	
	
	/*
	 * CONSTRUCTORES
	 */
	public DSolicitudIngresoNoMovidaPKTO(){
		this.ideSolicitud=null;
	}
	
	public DSolicitudIngresoNoMovidaPKTO(Long ideSolicitud){
		this.ideSolicitud=ideSolicitud;
	}

		
	
	
	
	/*
	 * GETTERS Y SETTERS
	 */
	
	
	
	
	/**
	 * Devuelve el valor del atributo ideSolicitud
	 * 
	 * @return ideSolicitud
	 */
	public Long getIdeSolicitud() {
		return ideSolicitud;
	}

	
	
	
	/**
	 * Asigna un valor al atributo ideSolicitud 
	 * 
	 * @param ideSolicitud Objeto de tipo Long con el valor a asignar al atributo ideSolicitud
	 */
	public void setIdeSolicitud(Long ideSolicitud) {
		this.ideSolicitud = ideSolicitud;
	}
	

}/*fin de class*/
