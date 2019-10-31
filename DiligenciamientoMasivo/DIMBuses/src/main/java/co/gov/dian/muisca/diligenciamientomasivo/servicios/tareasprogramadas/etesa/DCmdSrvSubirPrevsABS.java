package co.gov.dian.muisca.diligenciamientomasivo.servicios.tareasprogramadas.etesa;

import java.util.HashMap;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicioInterno;
import co.gov.dian.muisca.arquitectura.mensajes.DManipuladorMensaje;
import co.gov.dian.muisca.arquitectura.mensajes.IDArqMensajes;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DPrevalidadorETESATO;



/**
 * Comando de servicio que ejecuta la carga de prevalidadores iniciales ETESA a la Bandeja de Salida
 * 
 * @author nfontechar
 *
 */
public class DCmdSrvSubirPrevsABS extends DComandoServicioInterno {

	
	
	/*
	 * CONSTANTES
	 */
	protected static final int SUBIR_PREV=1;
	
	
	/*
	 * ATRIBUTOS
	 */
	protected DPrevalidadorETESATO elPrev;
	protected int tipoOperacion;
	
	
	
	
	/*
	 * MÉTODOS INICIALIZAR
	 */
	public void inicializarSubirPrevalidador(DPrevalidadorETESATO to){
		this.elPrev=to;
		setTipoOperacion(SUBIR_PREV);
	}/*fin de inicializarSubirPrevalidador*/

	
	
	
	
	
	/*
	 * GETTERS y SETTERS
	 */
	protected int getTipoOperacion() {
		return tipoOperacion;
	}

	protected void setTipoOperacion(int tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}


	





	/*
	 * MÉTODOS HEREDADOS
	 */
	protected void ejecutarComando() {
		throw new UnsupportedOperationException();
	}

	public void asignar(IDComando comando) {
		if(comando instanceof DCmdSrvSubirPrevsABS){
			DCmdSrvSubirPrevsABS copia=(DCmdSrvSubirPrevsABS) comando;
			copia.elPrev=elPrev;
			copia.tipoOperacion=tipoOperacion;
		}/*fin de if*/
	}

	public Object clonar() {
		return new DCmdSrvSubirPrevsABS();
	}

	public String getDescripcion() {
		return "Tarea programada para subir prevalidadores a Bandeja de Salida";
	}

	public boolean isAuditable() {
		return false;
	}

	public boolean validar() throws DValidarExcepcion {
		DManipuladorMensaje manipulador;
		String operacion=null;
		Map parametros=null;
		switch(getTipoOperacion()){
		   case SUBIR_PREV:
			   operacion="carga de prevalidador";
			   parametros=new HashMap();
			   parametros.put(this.getClass().getName()+":validar:elPrev", elPrev);
			   parametros.put(this.getClass().getName()+":validar:elPrev.getContenidoArchivo()", elPrev.getContenidoArchivo());
			   parametros.put(this.getClass().getName()+":validar:elPrev.getExtensionArchivo()", elPrev.getExtensionArchivo());
			   parametros.put(this.getClass().getName()+":validar:elPrev.getPrefijoArchivo()", elPrev.getPrefijoArchivo());
			   parametros.put(this.getClass().getName()+":validar:elPrev.getNumNIT()", elPrev.getNumNIT());
			   parametros.put(this.getClass().getName()+":validar:elPrev.getPk()", elPrev.getPk());
			   break;
		}/*fin de switch*/
		if (operacion == null) {
			manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
			String mensaje = manipulador.getMensaje();
			throw new DValidarExcepcion(mensaje, mensaje);
		}/*fin de if*/
        validarParametros(operacion, parametros);
		return true;
	}

}/*fin de class*/
