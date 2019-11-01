package co.gov.dian.muisca.diligenciamientomasivo.servicios.movimientoperiodico;

import java.util.List;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicioInterno;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico.DSolicitudIngresoNoMovidaTO;




/****
 * Servicio que permite mover a los repositorios definitivos, solicitudes de ingreso que presentaron errores de homologación,
 * pero que fueron tenidas en cuenta como válidas. Tarea Programada.
 * 
 * @author nfontechar
 *
 */
public class DCmdSrvMoverSolicitudesIngresoNoMovidas extends DComandoServicioInterno {

	
		
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
		if(comando instanceof DCmdSrvMoverSolicitudesIngresoNoMovidas){
			DCmdSrvMoverSolicitudesIngresoNoMovidas copia=(DCmdSrvMoverSolicitudesIngresoNoMovidas) comando;
			copia.solicitudesMovidas=solicitudesMovidas;
			copia.solicitudesNoMovidas=solicitudesNoMovidas;
		}/*fin de if*/
	}

	public Object clonar() {
		return new DCmdSrvMoverSolicitudesIngresoNoMovidas();
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
