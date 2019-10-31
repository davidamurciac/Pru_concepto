package co.gov.dian.muisca.diligenciamientomasivo.acciones.tareasprogramadas.etesa;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAccion;



/**
 * Comando de acción que hace las veces de Tarea Programada de carga de prevalidadores iniciales ETESA a la Bandeja de Salida
 * 
 * @author nfontechar
 *
 */
public class DCmdAccSubirPrevsABS extends DComandoAccion {
	
	
		
	
	/*
	 * ATRIBUTOS
	 */
	protected boolean exito;
	
	
	
	/*
	 * MÉTODOS HEREDADOS
	 */

	protected void ejecutarComando() {
		throw new UnsupportedOperationException();
	}

	public void asignar(IDComando comando) {
		if(comando instanceof DCmdAccSubirPrevsABS){
			DCmdAccSubirPrevsABS copia=(DCmdAccSubirPrevsABS) comando;
			copia.exito=exito;
		}/*fin de if*/
	}

	public Object clonar() {
		return new DCmdAccSubirPrevsABS();
	}

	public String getDescripcion() {
		return "Comando de acción para la carga de prevalidadores iniciales de ETESA a la Bandeja de Salida";
	}

	public boolean isAuditable() {
		return false;
	}

	public boolean validar() throws DValidarExcepcion {
		return true;
	}

}/*fin de class*/
