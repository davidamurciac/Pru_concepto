package co.gov.dian.muisca.diligenciamientomasivo.acciones.juegosdesuerteyazar;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaNegTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaPersonaNegTO;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.IDVencimientoTarea;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAccion;

public class DCmdAccVencimientoPresentacionJuegoSuerteAzar extends 
	DComandoAccion implements IDVencimientoTarea {
	
	protected DTareaPersonaNegTO toTareaPersona;
	protected DTareaNegTO toTareaNeg;
	protected static Logger LOGGER = Logger.getLogger(DCmdAccVencimientoPresentacionJuegoSuerteAzar.class);

	protected void ejecutarComando() {
		throw new UnsupportedOperationException();		
	}

	public void inicializarAdministrarVencimiento(DTareaPersonaNegTO pToTareaPersona){
		toTareaPersona = pToTareaPersona;
	}

	public void inicializarAdministrarVencimiento(DTareaNegTO tareaNegTo) {
		toTareaNeg = tareaNegTo;		
	}
	
	public void asignar(IDComando comando) {
	    if (comando instanceof DCmdAccVencimientoPresentacionJuegoSuerteAzar) {
	    	DCmdAccVencimientoPresentacionJuegoSuerteAzar copia = (DCmdAccVencimientoPresentacionJuegoSuerteAzar) comando;
		      copia.toTareaPersona = toTareaPersona;
		      copia.toTareaNeg = toTareaNeg;
		 }
	}

	public Object clonar() {
		return new DCmdAccVencimientoPresentacionJuegoSuerteAzar();
	}

	public String getDescripcion() {
		return "Verifica vencimiento tareas de Juegos de suerte y azar";
	}

	public boolean isAuditable() {
		return false;
	}

	public boolean validar() throws DValidarExcepcion {
		if(toTareaPersona == null || toTareaPersona.getPk()==null
				||toTareaPersona.getAtt()==null||toTareaPersona.getPk().getIdeDocumentoReparto()==null
				||toTareaPersona.getPk().getIdeDocumentoTarea()==null||toTareaPersona.getPk().getNumRepeticionDocReparto()==null
				||toTareaPersona.getPk().getNumRepeticionDocTarea()==null||toTareaPersona.getAtt().getIdeExpediente()==null){
					throw new DValidarExcepcion("El parametro es nulo","toTareaPersona es nulo");
		}
		return true;
	}
}
