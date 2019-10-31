package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.solicitudesdeclaracion.impl;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DAuditoriaRegistroTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudArchivoTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoPKTO;
import co.gov.dian.muisca.cargamasiva.helper.DProcesamientoFlujoPosteriorHelper;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsAuditoriaRegistro;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsLstSolicitudArchivo;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.solicitudesdeclaracion.DCmdAccWFSolicitudesDeclaracion;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConceptosNegocioAnexoRentaGC;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.DCmdSrvCrearSolicitudDeclaracion;
import co.gov.dian.muisca.entradasalida.documento.DDocumentoUtil;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoMUISCA;
import co.gov.dian.muisca.rut.general.to.DRegistroRutTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsRegistroRut;

public class DCmdAccWFSolicitudesDeclaracionImpl extends DCmdAccWFSolicitudesDeclaracion{
	
	
	protected void ejecutarComandoSinTransaccion() {
		LOGGER.info("*********** INICIO registro documentos carga masiva ***************");
		try {
			documentoCargaPK = (DDocumentoPKTO)getDocumentos().iterator().next();
			DProcesamientoFlujoPosteriorHelper.iniciarProcesoFlujoPosterior(documentoCargaPK.getIdeDocumento());
			DSolicitudIngresoPKTO solicitudIngresoPK = new DSolicitudIngresoPKTO(documentoCargaPK.getIdeDocumento());
			DCmdSrvConsLstSolicitudArchivo srvConsLstConsArchivo = (DCmdSrvConsLstSolicitudArchivo) 
					getServicio("cargamasiva.procesamiento.DCmdSrvConsLstSolicitudArchivo");
			srvConsLstConsArchivo.inicializarConsultarPorSolicitudIngreso(solicitudIngresoPK);
			LOGGER.info("Inicializado el servicio DCmdSrvConsLstSolicitudArchivo con solicitud de Ingreso:"
					+ solicitudIngresoPK.getIdeSolicitud());
			
			srvConsLstConsArchivo.ejecutar();
			
			Collection coleccionArchivosSol = srvConsLstConsArchivo.getColeccionSolicitudArchivo();
			
			DCmdSrvConsDocumentoMUISCA srvConsDoc = (DCmdSrvConsDocumentoMUISCA)
					this.getServicio("entradasalida.documentos.DCmdSrvConsDocumentoMUISCA");
			
			
			Iterator iterColeccionArchivosSol = coleccionArchivosSol.iterator();
			LOGGER.info("Se va a iniciar el proceso de carga: cantidad de archivos en coleccion:" + coleccionArchivosSol.size());
			while (iterColeccionArchivosSol.hasNext()) {

				DSolicitudArchivoTO solicitudArchivoTO = (DSolicitudArchivoTO) iterColeccionArchivosSol.next();
				
				DCmdSrvConsAuditoriaRegistro consAuditoriaRegistro = (DCmdSrvConsAuditoriaRegistro)
						getServicio("cargamasiva.procesamiento.DCmdSrvConsAuditoriaRegistro");
				consAuditoriaRegistro.inicializarConsultaPorIdeSolicitud(solicitudArchivoTO.getPK().getIdeSolicitud());
				consAuditoriaRegistro.ejecutar();
				
				Iterator itAudReg = consAuditoriaRegistro.getColeccionAuditoriaRegistro().iterator();
				
				while (itAudReg.hasNext()){
					DAuditoriaRegistroTO toAudReg = (DAuditoriaRegistroTO)itAudReg.next();
					
					srvConsDoc.inicializar(toAudReg.getPK().getIdeDocumento(), 1, toAudReg.getAtt().getIdeFormato());
					srvConsDoc.ejecutar();
					
					IDDocumento documento = srvConsDoc.getDocumento();
					
					
					LOGGER.info("El XML del documento es:");
					
					LOGGER.info(DDocumentoUtil.obtenerXmlDeDocumento(documento));
					
					Long idDocumentoAsociado = documento.getGrupos().getGrupo(1).getOcurrencia(1).getValCasillaNeg(
							IDConceptosNegocioAnexoRentaGC.CAS_NUMERO_DECLAR_RENTA_ASOCIADA).getValorEntero();

					Long nit = documento.getGrupos().getGrupo(1).getOcurrencia(1).getValCasillaNeg(
							IDConceptosNegocioAnexoRentaGC.CASNEG_NIT).getValorEntero();	
					

					Long idPersona = consultarIdPersonaRut(nit);

					DSolicitudDeclaracionAttTO attToSolicitudDeclaracion = new DSolicitudDeclaracionAttTO();
					DSolicitudDeclaracionPKTO pkSolicitudDeclaracion = new DSolicitudDeclaracionPKTO(documentoCargaPK.getIdeDocumento(),
							documentoCargaPK.getNumRepeticion());
					attToSolicitudDeclaracion.setIdeDocumento(idDocumentoAsociado);
					attToSolicitudDeclaracion.setNumRepeticion(1);
					attToSolicitudDeclaracion.setCodEstado(IDDocumento.IDE_ESTADO_PRESENTADO);
					attToSolicitudDeclaracion.setIdeFormato(110);
					attToSolicitudDeclaracion.setNumVersionFormato((byte)2);
					attToSolicitudDeclaracion.setIdePersona(idPersona);
					attToSolicitudDeclaracion.setFecCambio(new Timestamp(System.currentTimeMillis()));
					attToSolicitudDeclaracion.setIdeUsuarioCambio(solicitudArchivoTO.getAtt().getIdeUsuarioCambio());
					attToSolicitudDeclaracion.setIdeFormatoCarga(documento.getIdFormato());
					attToSolicitudDeclaracion.setNumVersionFormatoCarga((byte)documento.getVersionFormato());
					toSolicitudDeclaracion = new DSolicitudDeclaracionTO(pkSolicitudDeclaracion, attToSolicitudDeclaracion);
				}
			}
				
				
		} catch (DExcepcion ex) {
			try {
				DProcesamientoFlujoPosteriorHelper.finalizarErrorInesperadoProcesoFlujoPosterior(documentoCargaPK.getIdeDocumento());
			} catch (DExcepcion e) {
				LOGGER.error("Error actualizando estado en CM, ", ex);
				e.printStackTrace();
			}
			LOGGER.error("Error al ejecutar Con Transacción, ",	ex);
			isFinalizado = true;
			tieneErrorNoTransaccional = true;
			isOk = false;
		}
	}
	
	protected void ejecutarComandoWF() {
		try{
			if (tieneErrorNoTransaccional){
				isOk=false;
				return;
			}
			DCmdSrvCrearSolicitudDeclaracion srvCrearSolicitudDeclaracion = (DCmdSrvCrearSolicitudDeclaracion)
			getServicio("diligenciamientomasivo.solicitudesdeclaracion.DCmdSrvCrearSolicitudDeclaracion");
			srvCrearSolicitudDeclaracion.inicializar(toSolicitudDeclaracion);
			srvCrearSolicitudDeclaracion.ejecutar();

			LOGGER.info("*********** FIN registro documentos carga masiva ***************");
			DProcesamientoFlujoPosteriorHelper.finalizarExitosoProcesoFlujoPosterior(documentoCargaPK.getIdeDocumento());
			isFinalizado = true;
			isOk = true;
			
		}catch (Exception ex) {
			try {
				DProcesamientoFlujoPosteriorHelper.finalizarErrorInesperadoProcesoFlujoPosterior(documentoCargaPK.getIdeDocumento());
			} catch (DExcepcion e) {
				LOGGER.error("Error actualizando estado en CM, ", ex);
				e.printStackTrace();
			}
			LOGGER.error("Error al ejecutar Con Transacción, ",
					ex);
			isFinalizado = true;
			isOk = false;
		}
	}

	private Long consultarIdPersonaRut(Long nit) throws DExcepcion {
		DCmdSrvConsRegistroRut srvConsRut = (DCmdSrvConsRegistroRut) this.getServicio("rut.DCmdSrvConsRegistroRut");
		srvConsRut.inicializarConsultarPorNIT(nit);
		DRegistroRutTO personaRut = new DRegistroRutTO();
		srvConsRut.ejecutar();
		personaRut = srvConsRut.getRegistroRut();
		return personaRut.getPK().getIdePersonaRut();
	}
}
