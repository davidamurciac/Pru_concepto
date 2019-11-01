package co.gov.dian.muisca.diligenciamientomasivo.acciones.tareasprogramadas.mincultura;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAccion;


/**
 * Comando de acción que hace las veces de Tarea Programada de generación de zip para los formatos de MINTIC y enviarlos a la Bandeja de Salida
 * 
 * @author jbernalv1, dmurciac
 *
 */
public class DCmdAccGenerarArchivoMinCultura extends DComandoAccion {

	private static final long	serialVersionUID	= 3780716322438047638L;

	protected boolean			exito;

	protected void ejecutarComando() {
		throw new UnsupportedOperationException();
	}

	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccGenerarArchivoMinCultura) {
			DCmdAccGenerarArchivoMinCultura copia = (DCmdAccGenerarArchivoMinCultura) comando;
			copia.exito = exito;
		}
	}

	public Object clonar() {
		return new DCmdAccGenerarArchivoMinCultura();
	}

	public String getDescripcion() {
		return "Comando de acción para la generación de archivos Zip para el MINTIC a la Bandeja de Salida";
	}

	public boolean isAuditable() {
		return false;
	}

	public boolean validar() throws DValidarExcepcion {
		return true;
	}

}
