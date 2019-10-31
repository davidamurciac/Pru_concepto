package co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;



/**
 * Objeto de transporte que representa la llave primaria de un registro de la tabla EYS_DOCUMENTOS_BAN_SALIDA
 * 
 * @author nfontechar
 *
 */
public class DPrevalidadorABSPKTO implements IDTO {
	
	
	/*
	 * ATRIBUTOS
	 */
	private Long ideSolicitud;
	private Long ideExpediente;
	private Long numNIT;
	
	
	
	
	
	/*
	 * CONSTRUCTORES
	 */
	public DPrevalidadorABSPKTO(Long ideSolicitud, Long ideExpediente, Long numNIT) {
		this.ideSolicitud = ideSolicitud;
		this.ideExpediente = ideExpediente;
		this.numNIT=numNIT;
	}
	
	public DPrevalidadorABSPKTO() {
		this.ideSolicitud = null;
		this.ideExpediente = null;
		this.numNIT=null;
	}

	
	
	/*
	 * GETTERS Y SETTERS
	 */
	public Long getIdeSolicitud() {
		return ideSolicitud;
	}

	public void setIdeSolicitud(Long ideSolicitud) {
		this.ideSolicitud = ideSolicitud;
	}

	public Long getIdeExpediente() {
		return ideExpediente;
	}

	public void setIdeExpediente(Long ideExpediente) {
		this.ideExpediente = ideExpediente;
	}

	public Long getNumNIT() {
		return numNIT;
	}

	public void setNumNIT(Long numNIT) {
		this.numNIT = numNIT;
	}
		
	

}/*fin de class*/
