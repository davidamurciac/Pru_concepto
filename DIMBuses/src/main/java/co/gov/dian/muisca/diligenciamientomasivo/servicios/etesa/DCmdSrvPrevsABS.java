package co.gov.dian.muisca.diligenciamientomasivo.servicios.etesa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicioInterno;
import co.gov.dian.muisca.arquitectura.mensajes.DManipuladorMensaje;
import co.gov.dian.muisca.arquitectura.mensajes.IDArqMensajes;
import co.gov.dian.muisca.diligenciamientomasivo.constantes.tareasprogramadas.etesa.IDConstantesPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DPrevalidadorABSAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DPrevalidadorABSPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DPrevalidadorABSTO;



/**
 * Comando de servicio que permite operar sobre la tabla EYS_DOCUMENTOS_BAN_SALIDA, para registro de prevalidadores iniciales ETESA
 * 
 * @author nfontechar
 *
 */
public class DCmdSrvPrevsABS extends DComandoServicioInterno {

	
	/*
	 * ATRIBUTOS
	 */
	
	protected DPrevalidadorABSTO elTO=null;
	protected DPrevalidadorABSPKTO laPK=null;
	protected DPrevalidadorABSAttTO elAtt=null;
	protected List<DPrevalidadorABSTO> losTOs=null;
	
	private int tipoOperacion=-1;
	
	
	
	/*
	 * MÉTODOS PRIVADOS
	 */
	private void inicializarServicio(DPrevalidadorABSTO to){
		switch(getTipoOperacion()){
		   case IDConstantesPrevsABS.CREAR:
		   case IDConstantesPrevsABS.ACTUALIZAR:
			   this.elTO=to;
			   if(elTO!=null){
				   this.laPK=elTO.getPk();
				   this.elAtt=elTO.getAtt();
			   }/*fin de if*/
			   break;
		   case IDConstantesPrevsABS.ELIMINAR:
		   case IDConstantesPrevsABS.CONSULTAR_POR_NIT:
		   case IDConstantesPrevsABS.CONSULTAR_POR_PK:
		   case IDConstantesPrevsABS.CONSULTAR_POR_SOL_EXP:
			   this.elTO=to;
			   if(elTO!=null){
				   this.laPK=elTO.getPk();
			   }/*fin de if*/
			   break;
		}/*fin de switch*/
	}/*fin de inicializarServicio*/
	
	
	
	
	/*
	 * MÉTODOS INICIALIZAR*
	 */
	 
	 public void inicializarCrear(DPrevalidadorABSTO to){
		 setTipoOperacion(IDConstantesPrevsABS.CREAR);
		 inicializarServicio(to);
	 }/*fin de inicializarCrear*/
	 
	 public void inicializarActualizar(DPrevalidadorABSTO to){
		 setTipoOperacion(IDConstantesPrevsABS.ACTUALIZAR);
		 inicializarServicio(to);
	 }/*fin de inicializarActualizar*/
	 
	 public void inicializarEliminar(DPrevalidadorABSPKTO pk){
		 setTipoOperacion(IDConstantesPrevsABS.ELIMINAR);
		 inicializarServicio(new DPrevalidadorABSTO(pk,null));		 
	 }/*fin de inicializarEliminar*/
	 
	 public void inicializarConsultarPorPK(DPrevalidadorABSPKTO pk){
		 setTipoOperacion(IDConstantesPrevsABS.CONSULTAR_POR_PK);
		 inicializarServicio(new DPrevalidadorABSTO(pk,null));
	 }/*fin de inicializarConsultarPorPK*/
	 
	 public void inicializarConsultarPorNIT(DPrevalidadorABSPKTO pk){
		 setTipoOperacion(IDConstantesPrevsABS.CONSULTAR_POR_NIT);
		 inicializarServicio(new DPrevalidadorABSTO(pk,null));
	 }/*fin de inicializarConsultarPorNIT*/
	 
	 public void inicializarConsultarPorSolicitudYExpediente(DPrevalidadorABSPKTO pk){
		 setTipoOperacion(IDConstantesPrevsABS.CONSULTAR_POR_SOL_EXP);
		 inicializarServicio(new DPrevalidadorABSTO(pk,null));
	 }/*fin de inicializarConsultarPorSolicitudYExpediente*/
	 
	 
	 
	 
	
	/*
	 * GETETRS y SETTERS
	 */
	
	 public int getTipoOperacion() {
		 return tipoOperacion;
	 }

	 public void setTipoOperacion(int tipoOperacion) {
		 this.tipoOperacion = tipoOperacion;
	 }

	 public List<DPrevalidadorABSTO> getLosTOs() {
		 return losTOs;
	 }

	 
	 
	 
	 
	 
	 
	/*
	 * MÉTODOS HEREDADOS
	 */
	public void asignar(IDComando comando) {
		if(comando instanceof DCmdSrvPrevsABS){
			DCmdSrvPrevsABS copia=(DCmdSrvPrevsABS) comando;
			copia.elTO=elTO;
			copia.laPK=laPK;
			copia.elAtt=elAtt;
			copia.losTOs=losTOs;
			copia.tipoOperacion=tipoOperacion;
		}/*fi de if*/
	}

	public Object clonar() {
		return new DCmdSrvPrevsABS();
	}

	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	public String getDescripcion() {
		return "Servicio para operaciones con los datos de los prevalidadores puestos en Bandeja de Salida";
	}

	public boolean isAuditable() {
		return false;
	}

	public boolean validar() throws DValidarExcepcion {
		DManipuladorMensaje manipulador;
        String operacion = null;
        String campo = null;
        Map pars=null;
        switch(getTipoOperacion()){
           case IDConstantesPrevsABS.CREAR:
        	   operacion="la creación";
        	   pars=new HashMap();
        	   pars.put(this.getClass().getName()+":validar:elTO", elTO);
        	   pars.put(this.getClass().getName()+":validar:laPK", laPK);
        	   pars.put(this.getClass().getName()+":validar:elAtt", elAtt);
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeExpediente()", laPK.getIdeExpediente());
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeSolicitud()", laPK.getIdeSolicitud());
        	   pars.put(this.getClass().getName()+":validar:laPK.getNumNIT()", laPK.getNumNIT());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getFecGeneracion()", elAtt.getFecGeneracion());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getIdeRecurso()", elAtt.getIdeRecurso());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getIdeUsuarioCambio()", elAtt.getIdeUsuarioCambio());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getTipoArchivo()", elAtt.getTipoArchivo());        	   
        	   break;
           case IDConstantesPrevsABS.ACTUALIZAR:
        	   operacion="la actualización";
        	   pars=new HashMap();
        	   pars.put(this.getClass().getName()+":validar:elTO", elTO);
        	   pars.put(this.getClass().getName()+":validar:laPK", laPK);
        	   pars.put(this.getClass().getName()+":validar:elAtt", elAtt);
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeExpediente()", laPK.getIdeExpediente());
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeSolicitud()", laPK.getIdeSolicitud());
        	   pars.put(this.getClass().getName()+":validar:laPK.getNumNIT()", laPK.getNumNIT());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getFecGeneracion()", elAtt.getFecGeneracion());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getIdeRecurso()", elAtt.getIdeRecurso());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getIdeUsuarioCambio()", elAtt.getIdeUsuarioCambio());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getTipoArchivo()", elAtt.getTipoArchivo());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getIdeDocumento()", elAtt.getIdeDocumento());
        	   pars.put(this.getClass().getName()+":validar:elAtt.getNumRepeticion()", elAtt.getNumRepeticion());
        	   break;
           case IDConstantesPrevsABS.ELIMINAR:
        	   operacion="la eliminación";
        	   pars=new HashMap();
        	   pars.put(this.getClass().getName()+":validar:laPK", laPK);
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeExpediente()", laPK.getIdeExpediente());
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeSolicitud()", laPK.getIdeSolicitud());
        	   pars.put(this.getClass().getName()+":validar:laPK.getNumNIT()", laPK.getNumNIT());
        	   break;
           case IDConstantesPrevsABS.CONSULTAR_POR_PK:
        	   operacion="la consulta por llave primaria";
        	   pars=new HashMap();
        	   pars.put(this.getClass().getName()+":validar:laPK", laPK);
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeExpediente()", laPK.getIdeExpediente());
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeSolicitud()", laPK.getIdeSolicitud());
        	   pars.put(this.getClass().getName()+":validar:laPK.getNumNIT()", laPK.getNumNIT());
        	   break;
           case IDConstantesPrevsABS.CONSULTAR_POR_NIT:
        	   operacion="la consulta por NIT";
        	   pars=new HashMap();
        	   pars.put(this.getClass().getName()+":validar:laPK", laPK);
        	   pars.put(this.getClass().getName()+":validar:laPK.getNumNIT()", laPK.getNumNIT());
        	   break;
           case IDConstantesPrevsABS.CONSULTAR_POR_SOL_EXP:
        	   operacion="la consulta por solicitud y expediente";
        	   pars=new HashMap();
        	   pars.put(this.getClass().getName()+":validar:laPK", laPK);
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeExpediente()", laPK.getIdeExpediente());
        	   pars.put(this.getClass().getName()+":validar:laPK.getIdeSolicitud()", laPK.getIdeSolicitud());
        	   break;
        }/*fin de switch*/
        if (operacion == null) {
			manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
			String mensaje = manipulador.getMensaje();
			throw new DValidarExcepcion(mensaje, mensaje);
		}/*fin de if*/
        validarParametros(operacion, pars);
		return true;
	}

}/*fin de class*/
