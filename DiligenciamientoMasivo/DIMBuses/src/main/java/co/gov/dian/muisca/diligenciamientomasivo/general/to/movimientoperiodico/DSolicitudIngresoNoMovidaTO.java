package co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;




/*****
 * TO que representa un registro de solicitud no movida
 * 
 * @author nfontechar
 *
 */
public class DSolicitudIngresoNoMovidaTO implements IDTO {
	
	/*
	 * ATRIBUTOS
	 */
	
	
	
	/**
	 * Campos de llave primaria
	 */
	DSolicitudIngresoNoMovidaPKTO pk;
	
	
	
	
	/**
	 * Campos de atributos
	 */
	DSolicitudIngresoNoMovidaAttTO att;
		
	
	/**
	 * Cantidad de Registros pendientes
	 */
	Integer registrosPendientes;
	
	
	/**
	 * Inicio del intervalo
	 */
	Integer inicio;
	
	
	/**
	 * Fin del intervalo
	 */
	Integer fin;
	
	
	/**
	 * El conteo de registros a buscar: Marcas (M) o Solicitudes (S)
	 */
	String conteo;
	
	
	
	
	/*
	 * CONSTRUCTORES
	 */
	public DSolicitudIngresoNoMovidaTO(){
		this.pk=null;
		this.att=null;
		this.conteo=null;
		this.inicio=null;
		this.fin=null;
		this.registrosPendientes=null;
	}
	
	public DSolicitudIngresoNoMovidaTO(DSolicitudIngresoNoMovidaPKTO pk,DSolicitudIngresoNoMovidaAttTO att){
		this.pk=pk;
		this.att=att;
	}

	
	
	
	/*
	 * GETTERS Y SETTERS
	 */
	
	
	
	
	/**
	 * Devuelve el valor del atributo pk
	 * 
	 * @return pk
	 */
	public DSolicitudIngresoNoMovidaPKTO getPk() {
		return pk;
	}

	
	
	
	/**
	 * Asigna un valor al atributo pk
	 * 
	 * @param pk Objeto de tipo DSolicitudIngresoNoMovidaPKTO para asignar al atributo pk
	 */
	public void setPk(DSolicitudIngresoNoMovidaPKTO pk) {
		this.pk = pk;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo att
	 * 
	 * @return att
	 */
	public DSolicitudIngresoNoMovidaAttTO getAtt() {
		return att;
	}

	
	
	
	/**
	 * Asigna un valor al atributo att
	 * 
	 * @param pk Objeto de tipo DSolicitudIngresoNoMovidaAttTO para asignar al atributo att
	 */
	public void setAtt(DSolicitudIngresoNoMovidaAttTO att) {
		this.att = att;
	}

	
	/**
	 * Obtiene el valor del atributo registrosPendientes 
	 * 
	 */
	public Integer getRegistrosPendientes() {
		return registrosPendientes;
	}

	
	/**
	 * Asigna un valor al atributo registrosPendientes
	 * 
	 * @param registrosPendientes Objeto de tipo Integer para asignar al atributo registrosPendientes
	 */	
	public void setRegistrosPendientes(Integer registrosPendientes) {
		this.registrosPendientes = registrosPendientes;
	}

	/**
	 * Asigna un valor al atributo inicio
	 * 
	 * @param inicio Objeto de tipo Integer para asignar al atributo inicio
	 */
	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}

	
	/**
	 * Asigna un valor al atributo fin
	 * 
	 * @param fin Objeto de tipo Integer para asignar al atributo fin
	 */
	public void setFin(Integer fin) {
		this.fin = fin;
	}

	
	/**
	 * Asigna un valor al atributo conteo
	 * 
	 * @param conteo Objeto de tipo String para asignar al atributo conteo
	 */
	public void setConteo(String conteo) {
		this.conteo = conteo;
	}
	

	/**
	 * Obtiene el valor del atributo conteo
	 *  
	 * @return conteo
	 */
	public String getConteo() {
		return conteo;
	}

	
	
	/**
	 * Obtiene el valor del atributo inicio
	 *  
	 * @return inicio
	 */
	public Integer getInicio() {
		return inicio;
	}

	
	/**
	 * Obtiene el valor del atributo fin
	 * 
	 * @return fin
	 */
	public Integer getFin() {
		return fin;
	}
	
	
	
	
	

}/*fin de class*/
