package co.gov.dian.muisca.diligenciamientomasivo.acciones.movimientoperiodico;

import java.util.List;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAccion;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico.DSolicitudIngresoNoMovidaTO;

public class DCmdAccMoverSolicitudesIngresoNoMovidas extends DComandoAccion {

	
	
	/*
	 * ATRIBUTOS
	 */
	
	
	
	
	/***
	 * Lista de objetos DSolicitudIngresoNoMovidaTO que contiene las solicitudes que se encuentran en el sistema como no movidas.
	 */
	protected List<DSolicitudIngresoNoMovidaTO> solicitudesNoMovidas=null;
	
	
	/****
	 * Lista de objetos DSolicitudIngresoNoMovidaTO que contiene las solicitudes que pudieron ser movidas con éxito.
	 */
	protected List<DSolicitudIngresoNoMovidaTO> solicitudesMovidas=null;
	
	
	
	
	
	
	
	
	
	
	/*
	 * GETTERS Y SETTERS
	 */
	
	
	
	
	/**
	 * Devuelve el valor del atributo solicitudesNoMovidas
	 * 
	 * @return solicitudesNoMovidas
	 */
	public List<DSolicitudIngresoNoMovidaTO> getSolicitudesNoMovidas() {
		return solicitudesNoMovidas;
	}

	
	
	
	/**
	 * Devuelve el valor del atributo solicitudesMovidas
	 * 
	 * @return solicitudesMovidas
	 */
	public List<DSolicitudIngresoNoMovidaTO> getSolicitudesMovidas() {
		return solicitudesMovidas;
	}

	
	


	/*
	 * MÉTODOS HEREDADOS
	 */
	protected void ejecutarComando() {
		throw new UnsupportedOperationException();
	}

	public void asignar(IDComando comando) {
		if(comando instanceof DCmdAccMoverSolicitudesIngresoNoMovidas){
			DCmdAccMoverSolicitudesIngresoNoMovidas copia=(DCmdAccMoverSolicitudesIngresoNoMovidas) comando;
			copia.solicitudesMovidas=solicitudesMovidas;
			copia.solicitudesNoMovidas=solicitudesNoMovidas;
		}/*fin de if*/
	}

	public Object clonar() {
		return new DCmdAccMoverSolicitudesIngresoNoMovidas();
	}

	public String getDescripcion() {
		return "Acción para operaciones con solicitudes de ingreso que aún no han sido movidas";
	}

	public boolean isAuditable() {
		return false;
	}

	public boolean validar() throws DValidarExcepcion {
		return true;
	}

}/*fin de class*/
