package co.gov.dian.muisca.diligenciamientomasivo.acciones.tareasprogramadas;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAccion;

/**
 * <p>Title: Proyecto MUISCA </p>
 * <p>Description: Clase que genera un listado de autoadhesivos </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: DIAN </p>
 * @author dmahechav
 * @version 1.0
 */
public class DCmdAccGenerarAdhesivosDeclaraciones extends DComandoAccion {

	
	protected boolean exito;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7559141495059020670L;

	@Override
	protected void ejecutarComando() {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void asignar(IDComando comando) {
		if(comando instanceof DCmdAccGenerarAdhesivosDeclaraciones){
			DCmdAccGenerarAdhesivosDeclaraciones copia = (DCmdAccGenerarAdhesivosDeclaraciones) comando;
			copia.exito=exito;
		}
	}

	@Override
	public Object clonar() {
		return new DCmdAccGenerarAdhesivosDeclaraciones();
	}

	@Override
	public String getDescripcion() {
		return "Comando de acción para la generación de un número determinado de autoadhesivos";
	}

	@Override
	public boolean isAuditable() {
		return false;
	}

	public boolean validar() throws DValidarExcepcion {
		return true;
	}

}
