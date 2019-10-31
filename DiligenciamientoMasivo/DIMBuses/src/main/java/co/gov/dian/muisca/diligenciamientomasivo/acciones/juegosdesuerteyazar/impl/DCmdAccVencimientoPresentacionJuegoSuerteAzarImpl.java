package co.gov.dian.muisca.diligenciamientomasivo.acciones.juegosdesuerteyazar.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DParamTareaNegTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaNegPKTO;
import co.gov.dian.muisca.arquitectura.helper.DTareasNegHelper;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.juegosdesuerteyazar.DCmdAccVencimientoPresentacionJuegoSuerteAzar;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConstantesCircuitoJuegosSuerteyAzar;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.DJuegoSuerteAzarPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.DJuegoSuerteAzarTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.juegosdesuerteyazar.DCmdSrvActJuegoSuerteAzar;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.juegosdesuerteyazar.DCmdSrvConsJuegoSuerteAzar;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.formatos.IDFormato;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvCambiarEstadoDocumento;
import co.gov.dian.muisca.gestionexpediente.general.helper.DCrearEventosHelper;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DDocEventoAttTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DDocEventoPKTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DDocEventoTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DParametroEventoAttTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DParametroEventoPKTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DParametroEventoTO;
import co.gov.dian.muisca.gestionexpediente.general.to.expediente.DConsultaExpedienteTO;
import co.gov.dian.muisca.gestionexpediente.general.to.expediente.DExpedienteTO;
import co.gov.dian.muisca.gestionexpediente.servicios.DCmdSrvCerrarExpediente;

public class DCmdAccVencimientoPresentacionJuegoSuerteAzarImpl 
	extends DCmdAccVencimientoPresentacionJuegoSuerteAzar{
	private DExpedienteTO expediente;
	
	public void ejecutarComando(){
		DTareasNegHelper creadorTareas_ = new DTareasNegHelper(getContexto().getContextoSeguridad());
		DCrearEventosHelper creadorEventos_ = new DCrearEventosHelper(getContexto());
		
		try {
			Collection parametros = creadorTareas_.consultarParametrosTarea(new DTareaNegPKTO(toTareaPersona.getPk().getIdeDocumentoTarea(),toTareaPersona.getPk().getNumRepeticionDocTarea()));
			long ideDocumentoDecJuegosSuerteAzar=0;
			int numRepeticionDecJuegosSuerteAzar=0;
			for(Iterator it = parametros.iterator();it.hasNext();){
				DParamTareaNegTO param = (DParamTareaNegTO)it.next();
				int parametro = param.getAtt().getIdeParamTareaDefNeg().intValue();
				switch(parametro){
					case 1:{
						ideDocumentoDecJuegosSuerteAzar=param.getAtt().getValNumericoLong().longValue();
						break;
					}
					case 2:{
						numRepeticionDecJuegosSuerteAzar=param.getAtt().getValNumericoLong().intValue();
						break;
					}
				}
			}
			
			cerrarJuegoSuerteAzar(new Long(ideDocumentoDecJuegosSuerteAzar),new Integer(numRepeticionDecJuegosSuerteAzar));
			cerrarDocumento(ideDocumentoDecJuegosSuerteAzar,numRepeticionDecJuegosSuerteAzar);
			
			DConsultaExpedienteTO criterio = new DConsultaExpedienteTO();
			criterio.setNumExpediente(toTareaPersona.getAtt().getIdeExpediente());
			Collection expedientes = creadorEventos_.consultarExpedientes(criterio);
			Iterator itExpedientes = expedientes.iterator();
	
			expediente = (DExpedienteTO)itExpedientes.next(); 
		
			creadorTareas_.cancelarReparto(toTareaPersona.getPk());
			creadorTareas_.cancelarTarea(new DTareaNegPKTO(toTareaPersona.getPk().getIdeDocumentoTarea(),toTareaPersona.getPk().getNumRepeticionDocTarea()));
			
			Collection docEventoPresCollection_ = new ArrayList();
			DDocEventoTO docEvento_ = new DDocEventoTO(new DDocEventoPKTO(),new DDocEventoAttTO());
			docEvento_.getPk().setIdeEvento(null);
			docEvento_.getPk().setIdeDocumento(toTareaPersona.getPk().getIdeDocumentoTarea());
			docEvento_.getPk().setIdeExpediente(toTareaPersona.getAtt().getIdeExpediente());
			docEvento_.getPk().setNumRepeticion(toTareaPersona.getPk().getNumRepeticionDocTarea());

			docEvento_.getAtt().setIdeFormato(IDConstantesCircuitoJuegosSuerteyAzar.FORMATO_DOCUMENTO_TAREA_PRESENTACION);
			docEvento_.getAtt().setNumVersionFormato(IDConstantesCircuitoJuegosSuerteyAzar.VERSION_FORMATO_DOCUMENTO_TAREA_PRESENTACION);
			docEvento_.getAtt().setIdeTipoDocActividadUsuario(IDConstantesCircuitoJuegosSuerteyAzar.TIPO_DOC_ACTIVIDAD_VENCIMIENTO_TAREA);
			
			DDocEventoTO docEventoReparto_ = new DDocEventoTO(new DDocEventoPKTO(),new DDocEventoAttTO());
			docEventoReparto_.getPk().setIdeEvento(null);
			docEventoReparto_.getPk().setIdeDocumento(toTareaPersona.getPk().getIdeDocumentoReparto());
			docEventoReparto_.getPk().setIdeExpediente(toTareaPersona.getAtt().getIdeExpediente());
			docEventoReparto_.getPk().setNumRepeticion(toTareaPersona.getPk().getNumRepeticionDocReparto());

			docEventoReparto_.getAtt().setIdeFormato(IDConstantesCircuitoJuegosSuerteyAzar.FORMATO_DOCUMENTO_TAREA_PRESENTACION);
			docEventoReparto_.getAtt().setNumVersionFormato(IDConstantesCircuitoJuegosSuerteyAzar.VERSION_FORMATO_DOCUMENTO_TAREA_PRESENTACION);
			docEventoReparto_.getAtt().setIdeTipoDocActividadUsuario(IDConstantesCircuitoJuegosSuerteyAzar.TIPO_DOC_ACTIVIDAD_VENCIMIENTO_TAREA_REPARTO);

			docEventoPresCollection_.add(docEventoReparto_);        

	        
			//Crea la coleccion de parametros para el evento	        
	        Collection parametrosEvento_ = new ArrayList();
	        DParametroEventoTO  paramEventoTO_ = new DParametroEventoTO( new DParametroEventoPKTO(),new DParametroEventoAttTO() );
	        paramEventoTO_.getPK().setIdeExpediente(toTareaPersona.getAtt().getIdeExpediente());
	        paramEventoTO_.getPK().setIdeParamDef(IDConstantesCircuitoJuegosSuerteyAzar.IDE_PAR_ACT_VENC_IDDOCUMENTO);
	        paramEventoTO_.getAtt().setNomParametro(IDConstantesCircuitoJuegosSuerteyAzar.PARAM_T1_PRESEN_JUA_SOLICITUD_NOMBRE);
	        paramEventoTO_.getAtt().setValParametro(toTareaPersona.getPk().getIdeDocumentoTarea().toString());
	        parametrosEvento_.add( paramEventoTO_ );
			//Crea la coleccion de personas para el evento
	        Collection personasEventoCollection = new ArrayList();

	        //Adiciona el evento
			creadorEventos_.adicionarEvento(
											expediente, 
											IDConstantesCircuitoJuegosSuerteyAzar.IDE_ACTIVIDAD_VENCIMIENTO_PLAZO, 
											"Se inicia la presentación de la información para poder compilarla en un solo documento", 
											docEventoPresCollection_, 
											personasEventoCollection, 
											parametrosEvento_, 
											1
											);
			DCmdSrvCerrarExpediente cerrarExpediente_ = (DCmdSrvCerrarExpediente)getServicio("gestionexpediente.DCmdSrvCerrarExpediente");
            cerrarExpediente_.inicializar(expediente);
            cerrarExpediente_.ejecutar();
            isOk = true;
		} catch (DExcepcion e) {
			e.printStackTrace();
			LOGGER.error("Error al finalizar la tarea.", e);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error al finalizar la tarea.", e);
		}
	}
	private void cerrarJuegoSuerteAzar(Long ideDocumento, Integer numRepeticion) throws DExcepcion{
		if(ideDocumento.equals(Long.valueOf("0"))||numRepeticion.equals(Integer.valueOf("0")))throw new DExcepcion("Documento no puede tener identificador nulo","No se encontró el idedocumento 540 y su numRepeticion dentro de los parametros de la tarea");
		DCmdSrvConsJuegoSuerteAzar srvConsJuegoSuerteAzar = (DCmdSrvConsJuegoSuerteAzar)
        	getServicio("diligenciamientomasivo.juegosdesuerteyazar.DCmdSrvConsJuegoSuerteAzar");
		DJuegoSuerteAzarPKTO juegosSuerteAzarPK = new DJuegoSuerteAzarPKTO();
		juegosSuerteAzarPK.setIdeDocumento(ideDocumento);
		juegosSuerteAzarPK.setNumRepeticion(numRepeticion);
		
		srvConsJuegoSuerteAzar.inicializar(juegosSuerteAzarPK);
		srvConsJuegoSuerteAzar.ejecutar();
		
		DJuegoSuerteAzarTO juegoSuerteAzarTO = srvConsJuegoSuerteAzar.getJuegoSuerteAzar();
		juegoSuerteAzarTO.getAtt().setCodEstado(new Integer(IDDocumento.IDE_ESTADO_FUERA_TERMINO));
		
		DCmdSrvActJuegoSuerteAzar srvActJuegoSuerteAzar = (DCmdSrvActJuegoSuerteAzar)getServicio("diligenciamientomasivo.juegosdesuerteyazar.DCmdSrvActJuegoSuerteAzar");
		srvActJuegoSuerteAzar.inicializar(juegoSuerteAzarTO);
		srvActJuegoSuerteAzar.ejecutar();
	}
	
	private void cerrarDocumento(long ideDocumento, int numRepeticion) throws DExcepcion{
        DCmdSrvCambiarEstadoDocumento comandoEstado = (DCmdSrvCambiarEstadoDocumento)
        getServicio("entradasalida.documentos.DCmdSrvCambiarEstadoDocumento");

		comandoEstado.inicializar(IDConstantesCircuitoJuegosSuerteyAzar.IDE_FORMATO_DECLARACION_JUEGOS_SUERTE_AZAR, 
				IDConstantesCircuitoJuegosSuerteyAzar.NUM_VERSION_FORMATO_DECLARACION_JUEGOS_SUERTE_AZAR,
				ideDocumento,
				numRepeticion,
				IDFormato.ENTRADA_DILIGENCIAMIENTO_WEB,
				IDDocumento.IDE_ESTADO_FUERA_TERMINO);
		
		comandoEstado.ejecutar();
	}
}