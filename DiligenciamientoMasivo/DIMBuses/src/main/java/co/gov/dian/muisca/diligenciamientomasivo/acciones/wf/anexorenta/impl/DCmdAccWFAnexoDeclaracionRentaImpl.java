package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.anexorenta.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.web.buses.DContextoSeguridad;
import co.gov.dian.muisca.cargamasiva.acciones.procesamiento.DCmdAccConsAuditoriaRegistro;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DAuditoriaRegistroPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DAuditoriaRegistroTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudArchivoTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoPKTO;
import co.gov.dian.muisca.cargamasiva.helper.DProcesamientoFlujoPosteriorHelper;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvActAuditoriaRegistro;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsAuditoriaRegistro;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsLstSolicitudArchivo;
import co.gov.dian.muisca.diligenciamiento.buses.DBusServiciosDelegateDILB;
import co.gov.dian.muisca.diligenciamiento.general.constantes.IDConceptosNegocioRentaObligados;
import co.gov.dian.muisca.diligenciamiento.general.constantes.IDConstantesDiligenciamiento;
import co.gov.dian.muisca.diligenciamiento.general.helper.DDatosArqHelper;
import co.gov.dian.muisca.diligenciamiento.general.helper.DObtenerDocValidoHelper;
import co.gov.dian.muisca.diligenciamiento.general.to.primeracuota.DPagoPrimeraCuotaPKTO;
import co.gov.dian.muisca.diligenciamiento.general.to.primeracuota.DPagoPrimeraCuotaTO;
import co.gov.dian.muisca.diligenciamiento.servicios.primeracuota.DCmdSrvConsPagoPrimeraCuota;
import co.gov.dian.muisca.diligenciamiento.util.listas.DIdentificadoresDocComparator;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.anexorenta.DCmdAccWFAnexoDeclaracionRenta;
import co.gov.dian.muisca.diligenciamientomasivo.buses.DBusServiciosDelegateDIMB;
import co.gov.dian.muisca.diligenciamientomasivo.buses.ejb.DDIMBusAccionesBean;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConceptosNegocioAnexoRentaGC;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConstantesDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConstantesEstadosProcesoFlujoPosterior;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.DCmdSrvCrearSolicitudDeclaracion;
import co.gov.dian.muisca.entradasalida.acciones.DCmdAccCrearDocumentoESTemporal;
import co.gov.dian.muisca.entradasalida.documento.DDocumentoUtil;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDCasilla;
import co.gov.dian.muisca.entradasalida.documento.IDCasillaColumnaDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDEstadosProcesamiento;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;
import co.gov.dian.muisca.entradasalida.excepciones.DEntradaSalidaExcepcion;
import co.gov.dian.muisca.entradasalida.formatos.IDConstantesFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDModosDiligenciamiento;
import co.gov.dian.muisca.entradasalida.formatos.IDTiposOrigenDeclaracion;
import co.gov.dian.muisca.entradasalida.formatos.constantes.DTipoCorreccion;
import co.gov.dian.muisca.entradasalida.general.to.cargamasiva.DIdentificadorDocumentoTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.general.to.formato.DFormatoPKTO;
import co.gov.dian.muisca.entradasalida.general.to.formato.DMapeoCasillaNegocioTO;
import co.gov.dian.muisca.entradasalida.servicios.consintegral.DCmdSrvLlenarDocumentoConsRut;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvCambiarEstadoDocumento;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoMUISCA;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.servicios.formatos.DCmdSrvConsLstMapeoCasillaNegocio;
import co.gov.dian.muisca.gestionexpediente.comando.IDContextoWF;
import co.gov.dian.muisca.rut.general.to.DRegistroRutTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsRegistroRut;

public class DCmdAccWFAnexoDeclaracionRentaImpl extends DCmdAccWFAnexoDeclaracionRenta {
	
	final String TAG_ERROR = "e";
	String mensaje;

	protected void ejecutarComandoSinTransaccion() {
		
		LOGGER.info("*********** INICIO circuito generacion renta ***************");
		
		DContextoSeguridad contexto=getContexto().getContextoSeguridad();
		LOGGER.info("\n\n\n*************************************************************************************\n\nContexto->Tipo Organización: "+contexto.getIdeTipoOrganizacion());
		LOGGER.info("Contexto->Organización: "+contexto.getOrganizacion());
		LOGGER.info("Contexto->Id Usuario: "+contexto.getIdeUsuario());
		LOGGER.info("Contexto->Nombre Organización: "+contexto.getNombreOrganizacion());
		LOGGER.info("Contexto->Nombre Razón Social Organización: "+contexto.getNombreRazonSocialOrganizacion());
		LOGGER.info("Contexto->Nombre Razón Social Usuario: "+contexto.getNombreRazonSocialUsuario());
		LOGGER.info("Contexto->Num Ident Vigente: "+contexto.getNumIdentVigente());
		LOGGER.info("Contexto->Id Persona: "+contexto.getIdePersona());
		LOGGER.info("Contexto->Id Persona Organización: "+contexto.getIdePersonaOrganizacion());
		
		IDContextoWF contextoWF=getContextoWF();
		LOGGER.info("ContextoWF->Id Actividad: "+contextoWF.getIdActividad());
		LOGGER.info("ContextoWF->Id Flujo: "+contextoWF.getIdFlujo());
		LOGGER.info("ContextoWF->Id Lugar: "+contextoWF.getIdLugar());
		LOGGER.info("ContextoWF->Id Organización: "+contextoWF.getIdOrganizacion());
		LOGGER.info("ContextoWF->Id Usuario: "+contextoWF.getIdUsuario()+"\n\n************************************************************************\n\n\n");
		
		try {
			documentoCargaPK = (DDocumentoPKTO)getDocumentos().iterator().next();
			DProcesamientoFlujoPosteriorHelper.iniciarProcesoFlujoPosterior(documentoCargaPK.getIdeDocumento());
			DSolicitudIngresoPKTO solicitudIngresoPK = new DSolicitudIngresoPKTO(documentoCargaPK.getIdeDocumento());
			DCmdSrvConsLstSolicitudArchivo srvConsLstConsArchivo = (DCmdSrvConsLstSolicitudArchivo) getServicio("cargamasiva.procesamiento.DCmdSrvConsLstSolicitudArchivo");
			srvConsLstConsArchivo.inicializarConsultarPorSolicitudIngreso(solicitudIngresoPK);
			LOGGER.info("Inicializado el servicio DCmdSrvConsLstSolicitudArchivo con solicitud de Ingreso:"	+ solicitudIngresoPK.getIdeSolicitud());
			srvConsLstConsArchivo.ejecutar();
			Collection coleccionArchivosSol = srvConsLstConsArchivo.getColeccionSolicitudArchivo();
			DCmdSrvConsDocumentoMUISCA srvConsDoc = (DCmdSrvConsDocumentoMUISCA) getServicio("entradasalida.documentos.DCmdSrvConsDocumentoMUISCA");
			Iterator iterColeccionArchivosSol = coleccionArchivosSol.iterator();
			LOGGER.info("Se va a iniciar el proceso de carga: cantidad de archivos en coleccion:" + coleccionArchivosSol.size());
			while (iterColeccionArchivosSol.hasNext()) {
				DSolicitudArchivoTO solicitudArchivoTO = (DSolicitudArchivoTO) iterColeccionArchivosSol.next();
				DCmdSrvConsAuditoriaRegistro consAuditoriaRegistro = (DCmdSrvConsAuditoriaRegistro)	getServicio("cargamasiva.procesamiento.DCmdSrvConsAuditoriaRegistro");
				consAuditoriaRegistro.inicializarConsultaPorIdeSolicitud(solicitudArchivoTO.getPK().getIdeSolicitud());				
				consAuditoriaRegistro.ejecutar();
				Iterator itAudReg = consAuditoriaRegistro.getColeccionAuditoriaRegistro().iterator();
				while (itAudReg.hasNext()){
					DAuditoriaRegistroTO toAudReg = (DAuditoriaRegistroTO)itAudReg.next();
					srvConsDoc.inicializar(toAudReg.getPK().getIdeDocumento(), 1, toAudReg.getAtt().getIdeFormato());
					srvConsDoc.ejecutar();
					this.auditoriaRegistro =  toAudReg.getPK();
					//documentoAnexo -- > 1732
					IDDocumento documentoAnexo = srvConsDoc.getDocumento();
					IDOcurrencia ocurrenciaAnexo = documentoAnexo.getGrupos().getGrupo(1).getOcurrencia(1);
					this.nit = ocurrenciaAnexo.getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_NIT).getValorEntero();
					this.anio = ocurrenciaAnexo.getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_ANNIO).getInt();
					this.idPersona = consultarIdPersonaRut(nit);
					// se verifica que exista casilla fracción de año
					IDCasilla casAnexo = ocurrenciaAnexo.getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CAS_NEG_FRACCION_ANIO_GRAVABLE);
					if (casAnexo != null && casAnexo.getBoolean() == true){
						this.esFraccionAnio = true;
						esFraccionAnioValida(this.anio);
					}/*fin de if*/					
					//Se asignan los modos negocio con los que se va a trabajar en la declaración según sea el caso
					asignarModosNegocio(documentoAnexo.getVersionFormato());
					// Determina si se genera o no 110 
					String sin110 = ocurrenciaAnexo.getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CAS_NUMERO_FTO_1732_CORRIGE).getString();// 26
					this.docCorreccion110 = ocurrenciaAnexo.getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CAS_CODIGO_CORRECCION).getValorEntero();//28
					if (docCorreccion110 != null){
						if (docCorreccion110 == 0L)
							docCorreccion110 = null;
					}
					this.declaRentaAsoc = ocurrenciaAnexo.getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CAS_NUMERO_DECLAR_RENTA_ASOCIADA).getValorEntero();//27
					if (declaRentaAsoc != null){
						if (declaRentaAsoc == 0L)
							declaRentaAsoc = null;
					}
					if (sin110!=null || declaRentaAsoc != null)
						this.generar110 = false;
					if (docCorreccion110 != null)
						this.correccion110 = true;
					
					IDFormato formato = consultarFormato(IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS);
					if (this.generar110){
						
						IDDocumento docTemporal = DDocumentoUtil.getDocumentoEnBlanco(formato);
						this.documentoRenta = actualizarCabeceraPie(docTemporal, this.idPersona);
						IDOcurrencia ocurrenciaRenta = documentoRenta.getGrupos().getGrupo(1).getOcurrencia(1);

						DFormatoPKTO formatoPKRenta = new DFormatoPKTO(documentoRenta.getIdFormato(),documentoRenta.getVersionFormato());
						DFormatoPKTO formatoPKAnexo = new DFormatoPKTO(documentoAnexo.getIdFormato(),documentoAnexo.getVersionFormato());
						
						DCmdSrvConsLstMapeoCasillaNegocio srvConsLstMapeoNeg = (DCmdSrvConsLstMapeoCasillaNegocio) getServicio("entradasalida.formatos.DCmdSrvConsLstMapeoCasillaNegocio");
						srvConsLstMapeoNeg.inicializarConsConceptosIguales(formatoPKRenta, formatoPKAnexo, false);
						srvConsLstMapeoNeg.ejecutar();
						colMapeoCasillaNegocioRenta = srvConsLstMapeoNeg.getColMapeoCasillasNegocioFto1();
						colMapeoCasillaNegocioAnexo = srvConsLstMapeoNeg.getColMapeoCasillasNegocioFto2();

						Iterator iterCasNegRenta = colMapeoCasillaNegocioRenta.iterator();
						
						
						while (iterCasNegRenta.hasNext()){
							DMapeoCasillaNegocioTO casilla = (DMapeoCasillaNegocioTO) iterCasNegRenta.next();
							LOGGER.info("Casilla: "+casilla.getPK().getIdeCasilla()+" del 110. Concepto: "+casilla.getPK().getIdeConcepto());
							if (casilla.getPK().getIdeCasilla() > IDConstantesFormato.FINAL_NUM_CABECERA && casilla.getPK().getIdeCasilla() < IDConstantesFormato.INICIO_NUM_PIE){
								Iterator iterCasNegAnexo = colMapeoCasillaNegocioAnexo.iterator();
								while (iterCasNegAnexo.hasNext()){ 
									DMapeoCasillaNegocioTO casillaAnexo = (DMapeoCasillaNegocioTO) iterCasNegAnexo.next();
									Long valorCasilla;
									if (casilla.getPK().getIdeConcepto().equals(casillaAnexo.getPK().getIdeConcepto())){
										if (casillaAnexo.getPK().getIdeItem() == -1){
											if (casillaAnexo.getPK().getIdeConcepto() == IDConceptosNegocioAnexoRentaGC.CAS_NEG_DESC_IMPUESTOS_PAGADO){
												valorCasilla = documentoAnexo.getGrupos().getGrupo(casillaAnexo.getPK().getIdeGrupo()).getOcurrencia(1).getValCasillaNeg(casillaAnexo.getPK().getIdeConcepto()).getLong();
											}else{
												valorCasilla = documentoAnexo.getGrupos().getGrupo(casillaAnexo.getPK().getIdeGrupo()).getOcurrencia(1).getValCasillaNeg(casillaAnexo.getPK().getIdeConcepto()).getValorEntero();
											}
										}
										else{
											IDCasilla casillaAxo = documentoAnexo.getGrupos().getGrupo(casillaAnexo.getPK().getIdeGrupo()).getOcurrencia(1).getValCasillaNeg(casillaAnexo.getPK().getIdeConcepto());
											IDCasillaColumnaDoc casillaCol = (IDCasillaColumnaDoc)casillaAxo;
											casillaCol.irA(1);//Se traen valores solo del item 1
											if (casilla.getPK().getIdeConcepto().equals(IDConceptosNegocioRentaObligados.CASNEG_INGRESOS_NO_CONSTITUTIVOS_DE_RENTA_NI_GANACIA_OCASIONAL)){
												casillaCol.irA(4);
											}
											valorCasilla = casillaCol.getLong();
										}
										ocurrenciaRenta.getValCasillaNeg(casilla.getPK().getIdeConcepto()).setValor(valorCasilla);
										LOGGER.debug("Se copia valor de concepto negocio "+casilla.getPK().getIdeConcepto()+" Casilla:"+casillaAnexo.getPK().getIdeCasilla()+
												" del 1732 A casilla "+casilla.getPK().getIdeCasilla()+" de 110");
										break;
									}
								}
								if (correccion110){
									ocurrenciaRenta.getValCasillaNeg(IDConceptosNegocioRentaObligados.CAS_NEG_CODIGO_CORRECCION).setValor(DTipoCorreccion.DECLARACION_PRIVADA);
								}
							}
							
						}
						try{
							ocurrenciaRenta.getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CAS_NEG_CAMBIO_TITULAR_INV_EXTRANJERA).setValor(false);
						}
						catch (NullPointerException e) {
								LOGGER.info( "Concepto no contemplado para esta versión");							
						}
						if (esFraccionAnio){
							ocurrenciaRenta.getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CAS_NEG_FRACCION_ANIO_GRAVABLE).setValor(true);
						}else{
							ocurrenciaRenta.getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CAS_NEG_FRACCION_ANIO_GRAVABLE).setValor(false);
						}
						
						LOGGER.info("El XML del documento es:");			
						LOGGER.info(DDocumentoUtil.obtenerXmlDeDocumento(documentoRenta));	
					}
					// Genera TO Solicitud Declaracion
					DSolicitudDeclaracionAttTO attToSolicitudDeclaracion = new DSolicitudDeclaracionAttTO();
					DSolicitudDeclaracionPKTO pkSolicitudDeclaracion = new DSolicitudDeclaracionPKTO(documentoCargaPK.getIdeDocumento(),documentoCargaPK.getNumRepeticion());
					attToSolicitudDeclaracion.setCodEstado(IDDocumento.IDE_ESTADO_PRESENTADO);
					attToSolicitudDeclaracion.setIdeFormato(IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS);
					attToSolicitudDeclaracion.setNumVersionFormato((byte)formato.getVersion());
					attToSolicitudDeclaracion.setIdePersona(idPersona);
					attToSolicitudDeclaracion.setFecCambio(new Timestamp(System.currentTimeMillis()));
					attToSolicitudDeclaracion.setIdeUsuarioCambio(solicitudArchivoTO.getAtt().getIdeUsuarioCambio());
					attToSolicitudDeclaracion.setIdeFormatoCarga(documentoAnexo.getIdFormato());
					attToSolicitudDeclaracion.setNumVersionFormatoCarga((byte)documentoAnexo.getVersionFormato());
					toSolicitudDeclaracion = new DSolicitudDeclaracionTO(pkSolicitudDeclaracion, attToSolicitudDeclaracion);
				}/*fin de while*/
			}
			isOk = true;
		} catch (DExcepcion ex) {
			try {
				DProcesamientoFlujoPosteriorHelper.finalizarErrorValidacionProcesoFlujoPosterior(documentoCargaPK.getIdeDocumento());
			} catch (DExcepcion e) {
				LOGGER.error("Error actualizando estado en CM, ", ex);
				e.printStackTrace();
			}
			LOGGER.error("Error al ejecutar Sin Transacción, ", ex);
			isFinalizado = true;
			tieneErrorNoTransaccional = true;
			isOk = true;
		} catch (Exception ex) {
			try {
				DProcesamientoFlujoPosteriorHelper.finalizarErrorValidacionProcesoFlujoPosterior(documentoCargaPK.getIdeDocumento());
			} catch (DExcepcion e) {
				LOGGER.error("Error actualizando estado en CM, ", ex);
				e.printStackTrace();
			}
			LOGGER.error("Error al ejecutar Sin Transacción, ", ex);
			isFinalizado = true;
			tieneErrorNoTransaccional = true;
			isOk = true;
		}
	}
	
	protected void ejecutarComandoWF() {

		try{
			/*if(documentoRenta==null){
				isOk=true;
				//return;
			}/*fin de if*/
			
			if (tieneErrorNoTransaccional){
				isOk=false;
				return;
			}	
			if (esFraccionAnio){
				if(!esFraccionAnioValida){
					mensaje = "Se trató de generar una declaración de fracción de año para un año que no aplica.";
					LOGGER.info(mensaje);
					finalizarProcesoFlujoPosterior(auditoriaRegistro, IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_FRACCION_ANIO, mensaje);
					isFinalizado = false;
					tieneErrorNoTransaccional = true;
					isOk = true;
					return;							
				}/*fin de if*/
			}
			DIdentificadorDoc identificadorDoc = null;
			if (generar110){
				DIdentificadorDoc identDocumento = null;
				if (!correccion110){ //solo iniciales
					DPagoPrimeraCuotaTO primeraCuotaTO = null;
					if (this.esGranContribuyente && !this.esFraccionAnio){
						// Consulta documento 110 Primera Cuota 
						DPagoPrimeraCuotaPKTO primeraCuotaPK = new DPagoPrimeraCuotaPKTO(null,idPersona,documentoRenta.getIdFormato(),documentoRenta.getVersionFormato());
						primeraCuotaTO = consultarPrimeraCuota(primeraCuotaPK);
						if (primeraCuotaTO != null){
							DIdentificadorDocumentoTO toIdentificadorDoc = construirIdentificadorDoc(documentoRenta.getIdFormato(),documentoRenta.getVersionFormato(),primeraCuotaTO.getPk().getIdeDocumentoDeclaracion(),IDModosDiligenciamiento.MODO_INICIAL,anio,nit);
							// Verifica que se encuentre en borrador
							identDocumento = consultarIdentificadorDoc(toIdentificadorDoc,false);
							if (identDocumento != null){
								documentoRenta.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_NUM_FORMULARIO).setValor(identDocumento.getIdDocumento());
								identificadorDoc = actualizarDocumento(documentoRenta, IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS, IDModosDiligenciamiento.MODO_INICIAL);
							}else{
								identDocumento = consultarIdentificadorDoc(toIdentificadorDoc,true);
								if (identDocumento != null){
									mensaje = "Se encontró una declaración inicial presentada, debe presentar una carga de corrección";
									finalizarProcesoFlujoPosterior(auditoriaRegistro, IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_EXISTE_INICIAL,mensaje);
									LOGGER.info(mensaje);
									isFinalizado = false;
									isOk = true;
									return;									
								}else{
									documentoRenta.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_NUM_FORMULARIO).setValor(primeraCuotaTO.getPk().getIdeDocumentoDeclaracion());
									identificadorDoc = salvarDocumento(documentoRenta,IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS,IDModosDiligenciamiento.MODO_INICIAL,true);
								}
							}
						}
					}if (primeraCuotaTO == null){//No es GC, o no presentó primera cuota
						DIdentificadorDocumentoTO toIdentificadorDoc = construirIdentificadorDoc(documentoRenta.getIdFormato(),documentoRenta.getVersionFormato(),null,modoNegInicial,anio,nit);
						identDocumento = consultarIdentificadorDoc(toIdentificadorDoc,true);
						if (identDocumento != null){
							mensaje = "Se encontró una declaración inicial presentada, debe presentar una carga de corrección"; 
							finalizarProcesoFlujoPosterior(auditoriaRegistro, IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_EXISTE_INICIAL,mensaje);
							LOGGER.info(mensaje);
							isFinalizado = false;
							isOk = true;
							return;
						}else{
							identDocumento = consultarIdentificadorDoc(toIdentificadorDoc,false);
							if (identDocumento != null){
								if(identDocumento.getIdEstado()==IDEstadosProcesamiento.IDE_ESTADO_TEMPORAL){
									documentoRenta.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_NUM_FORMULARIO).setValor(identDocumento.getIdDocumento());
									identificadorDoc = actualizarDocumento(documentoRenta, IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS, modoNegInicial);
								}else if(identDocumento.getIdEstado()==IDEstadosProcesamiento.IDE_ESTADO_PROCESO_FIRMA){
									mensaje = "Se encontró una declaración en proceso de firma";
									finalizarProcesoFlujoPosterior(auditoriaRegistro, IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_EN_FIRMAS,mensaje);
									LOGGER.info(mensaje);
									isFinalizado = false;
									isOk = true;
									return;								
								}else if(identDocumento.getIdEstado()==IDEstadosProcesamiento.IDE_ESTADO_VALIDADO){
									//Verifica que sea obligado a presentar virtualmente, si es asi, pone el documento en borrador
									consultaObligado();
								    if (esObligado){
								    	actualizarEstadoDocumentoBuzon(identDocumento.getIdFormato(),identDocumento.getVersion(),identDocumento.getIdDocumento(),identDocumento.getNumRepeticion(),identDocumento.getIdTipoEntrada(),IDEstadosProcesamiento.IDE_ESTADO_TEMPORAL);
								    	identificadorDoc = actualizarDocumento(documentoRenta, IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS, modoNegInicial);
								    }else{									
								    	identificadorDoc = salvarDocumento(documentoRenta,IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS,modoNegInicial,false);
								    }
								}
							}else{
								identificadorDoc = salvarDocumento(documentoRenta,IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS,modoNegInicial,false);
							}
						}
					}
				}else{ // CORRECCIÓN
					boolean continuar = false;
					DIdentificadorDocumentoTO toIdentificadorDocIni = construirIdentificadorDoc(documentoRenta.getIdFormato(),documentoRenta.getVersionFormato(),null,modoNegInicial,anio,nit);
					DIdentificadorDoc identDocumentoInic = consultarIdentificadorDoc(toIdentificadorDocIni,true);
					// Si tiene inicial continua, sino marca error
					if (identDocumentoInic != null){
						DIdentificadorDocumentoTO toIdentificadorDoc = construirIdentificadorDoc(documentoRenta.getIdFormato(),documentoRenta.getVersionFormato(),null,modoNegCorreccion,anio,nit);
						identDocumento = consultarIdentificadorDoc(toIdentificadorDoc,true);
						if (identDocumento == null){// No tiene documentos de corrección presentados
							if (identDocumentoInic.getIdDocumento() == docCorreccion110)// Compara número doc inicial con casilla 28
								LOGGER.info("Numero declaración inicial para corregir: "+identDocumentoInic.getIdDocumento());
								continuar = true;
						}else{
							if (identDocumento.getIdDocumento() == docCorreccion110)// Compara número doc corrección con casilla 28
								LOGGER.info("Numero declaración corrección para corregir: "+identDocumento.getIdDocumento());
								continuar = true;
						}
						if (continuar){
							//Consulta si tiene borradores de corrección
							DIdentificadorDocumentoTO toIdentificadorDocBorr = construirIdentificadorDoc(documentoRenta.getIdFormato(),documentoRenta.getVersionFormato(),null,modoNegCorreccion,anio,nit);
							DIdentificadorDoc identDocumentoBorr = consultarIdentificadorDoc(toIdentificadorDocBorr,false);
							if (identDocumentoBorr != null){
								if(identDocumentoBorr.getIdEstado()==IDEstadosProcesamiento.IDE_ESTADO_TEMPORAL){
									documentoRenta.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_NUM_FORMULARIO).setValor(identDocumentoBorr.getIdDocumento());
									identificadorDoc = actualizarDocumento(documentoRenta, IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS, modoNegCorreccion);
								}else if(identDocumentoBorr.getIdEstado()==IDEstadosProcesamiento.IDE_ESTADO_PROCESO_FIRMA){
									mensaje = "Se encontró una declaración en proceso de firma";
									finalizarProcesoFlujoPosterior(auditoriaRegistro, IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_EN_FIRMAS,mensaje);
									LOGGER.info(mensaje);
									isFinalizado = false;
									isOk = true;
									return;
								}else if(identDocumentoBorr.getIdEstado()==IDEstadosProcesamiento.IDE_ESTADO_VALIDADO){
									//Verifica que sea obligado a presentar virtualmente, si es asi, pone el documento en borrador
									consultaObligado();
								    if (esObligado){
								    	actualizarEstadoDocumentoBuzon(identDocumentoBorr.getIdFormato(),identDocumentoBorr.getVersion(),identDocumentoBorr.getIdDocumento(),identDocumentoBorr.getNumRepeticion(),identDocumentoBorr.getIdTipoEntrada(),IDEstadosProcesamiento.IDE_ESTADO_TEMPORAL);
								    	documentoRenta.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_NUM_FORMULARIO).setValor(identDocumentoBorr.getIdDocumento());
								    	identificadorDoc = actualizarDocumento(documentoRenta, IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS, modoNegCorreccion);
								    }else{									
								    	identificadorDoc = salvarDocumento(documentoRenta,IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS,modoNegCorreccion,false);
								    }
								}
							}else{
								identificadorDoc = salvarDocumento(documentoRenta,IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS,modoNegCorreccion,false);
							}
						}else{
							mensaje = "El número de documento registrado en la casilla 28 no coincide con la última declaración válida para corregir.";
							finalizarProcesoFlujoPosterior(auditoriaRegistro, IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_NUM_DEC_RENTA_NO_ES_ULTIMA_VALIDA,mensaje);
							LOGGER.info(mensaje);
							isFinalizado = false;
							isOk = true;
							return;							
						}
					}else{
						mensaje = "No se encontró una declaración inicial presentada para corregir.";
						finalizarProcesoFlujoPosterior(auditoriaRegistro, IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_NO_EXISTE_INICIAL_PARA_CORREGIR,mensaje);
						LOGGER.info(mensaje);
						isFinalizado = false;
						isOk = true;
						return;						
					}
				}
				LOGGER.info("Se creó el documento 110: "+identificadorDoc.getIdDocumento()+" generado para la solicitud: "+ documentoCargaPK.getIdeDocumento());
			}
			
			LOGGER.info("*********** Se registra documento carga masiva ***************");
			DCmdSrvCrearSolicitudDeclaracion srvCrearSolicitudDeclaracion = (DCmdSrvCrearSolicitudDeclaracion) getServicio("diligenciamientomasivo.solicitudesdeclaracion.DCmdSrvCrearSolicitudDeclaracion");

			if (identificadorDoc != null){
				toSolicitudDeclaracion.getAtt().setIdeDocumento(identificadorDoc.getIdDocumento());
				toSolicitudDeclaracion.getAtt().setNumRepeticion(identificadorDoc.getNumRepeticion());
			}else if (declaRentaAsoc != null){// Si tiene valor en la casilla 27, lo asocia
				toSolicitudDeclaracion.getAtt().setIdeDocumento(declaRentaAsoc);
				toSolicitudDeclaracion.getAtt().setNumRepeticion(1);
			}else{ // caso en el que la carga NO genera 110
				toSolicitudDeclaracion.getAtt().setIdeDocumento(null);
				toSolicitudDeclaracion.getAtt().setNumRepeticion(null);
			}
			srvCrearSolicitudDeclaracion.inicializar(toSolicitudDeclaracion);
			srvCrearSolicitudDeclaracion.ejecutar();

			LOGGER.info("*********** FIN Documento Renta OK ***************");
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
			LOGGER.error("Error al ejecutar Con Transacción, ",	ex);
			isFinalizado = true;
			isOk = true;
		}
	}
	
	private void consultaObligado() throws DExcepcion{		
		DDatosArqHelper helperARQ=new DDatosArqHelper(getContexto().getContextoSeguridad());
		esObligado=helperARQ.isObligado();
	}
	
	 /**
     * Actualiza el estado del documento borrador.
     */
    private void actualizarEstadoDocumentoBuzon(int formato, int version, long idDocumento,int numRepeticion,int tipoEntrada, int ideEstado) throws DExcepcion{
    	
    	DCmdSrvCambiarEstadoDocumento  srvcambiarEstadoDoc = (DCmdSrvCambiarEstadoDocumento) getServicio("entradasalida.documentos.DCmdSrvCambiarEstadoDocumento");
    	srvcambiarEstadoDoc.inicializar(formato, version, idDocumento, numRepeticion, tipoEntrada, ideEstado)  ;
    	srvcambiarEstadoDoc.ejecutar();

    	DIdentificadorDoc identificador = null ;
        identificador = srvcambiarEstadoDoc.getIdentificadorDoc() ; 
        
         if (identificador.getIdEstado() ==  ideEstado  )
        	 LOGGER.info("Se cambió el estado del documento: "+idDocumento+" a borrador");
    }
   
	/**
	 * Asigna método negocio según casilla año de 1732
	 */
	private void asignarModosNegocio(Integer versionAnexo){
		//El 1732v5 no tiene casilla fracción de año por lo tanto hay que hacer comparación por año gravable, 2012
		if (versionAnexo == IDConstantesDiligenciamientoMasivo.VERSION_FORMULARIO_ANEXO_RENTA_2012){
			if (this.anio == IDConstantesDiligenciamientoMasivo.ANIO_FORMULARIO_ANEXO_RENTA_VERSION_5+1){
				this.esFraccionAnio = true;
				LOGGER.info("Es fracción de año: " + this.esFraccionAnio);
				this.modoNegInicial = IDModosDiligenciamiento.MODO_FRACCION_INICIAL;
				this.modoNegCorreccion = IDModosDiligenciamiento.MODO_FRACCION_CORRECCION;
				this.anio = this.anio - 1;
			}else{
				this.modoNegInicial = IDModosDiligenciamiento.MODO_INICIAL;
				this.modoNegCorreccion = IDModosDiligenciamiento.MODO_CORRECCION;
			}
		}else{
			if (this.esFraccionAnio){
				LOGGER.info("Es fracción de año: " + this.esFraccionAnio);
				this.modoNegInicial = IDModosDiligenciamiento.MODO_FRACCION_INICIAL;
				this.modoNegCorreccion = IDModosDiligenciamiento.MODO_FRACCION_CORRECCION;
				this.anio = this.anio - 1;
			}else{
				this.modoNegInicial = IDModosDiligenciamiento.MODO_INICIAL;
				this.modoNegCorreccion = IDModosDiligenciamiento.MODO_CORRECCION;
			}
		}
	}
	
	/**
	 * Consulta TO primera cuota
	 * @param primeraCuotaPK
	 * @return
	 * @throws DExcepcion
	 */
	private DPagoPrimeraCuotaTO consultarPrimeraCuota (DPagoPrimeraCuotaPKTO primeraCuotaPK)throws DExcepcion {
		DCmdSrvConsPagoPrimeraCuota srvConsDocPrimeraCuota = (DCmdSrvConsPagoPrimeraCuota) getServicio("diligenciamiento.primeracuota.DCmdSrvConsPagoPrimeraCuota");
		srvConsDocPrimeraCuota.inicializarConsultarPorPersonaFormato(primeraCuotaPK);
		srvConsDocPrimeraCuota.ejecutar();
		DPagoPrimeraCuotaTO primeraCuotaTO = srvConsDocPrimeraCuota.getTo();
		return primeraCuotaTO;
	}
	
	/**
	 * Consulta identificadores Doc
	 * @param toIdentificadorDoc
	 * @param definitiva
	 * @return
	 * @throws Exception
	 */
	private DIdentificadorDoc consultarIdentificadorDoc (DIdentificadorDocumentoTO toIdentificadorDoc,Boolean definitiva)throws Exception {
		DCmdSrvConsIdentificadorDoc srvConsIdentificadorDoc = (DCmdSrvConsIdentificadorDoc)	getServicio("entradasalida.documentos.DCmdSrvConsIdentificadorDoc");
		srvConsIdentificadorDoc.inicializarConsGenerica(toIdentificadorDoc, definitiva);
		srvConsIdentificadorDoc.ejecutar();
		Collection identificadoresDoc = srvConsIdentificadorDoc.getIdentificadoresDoc();
		DIdentificadorDoc identificadorDoc = null;
		DObtenerDocValidoHelper helper=new DObtenerDocValidoHelper(this.getContexto(),toIdentificadorDoc.getIdModoNegocio(),0L);
		if (identificadoresDoc != null && definitiva) {
			identificadorDoc = helper.obtenerIdentificadorValido(identificadoresDoc);
		}else if (identificadoresDoc != null) {
			if (identificadoresDoc.size()>1){
				Collections.sort((ArrayList<DIdentificadorDoc>)identificadoresDoc, new DIdentificadoresDocComparator(DIdentificadoresDocComparator.ORDENAMIENTO_ESTADO));
			}
				Iterator<DIdentificadorDoc> itr = identificadoresDoc.iterator();
				while (itr.hasNext()) {
					identificadorDoc = (DIdentificadorDoc) itr.next();
					if (identificadorDoc != null && identificadorDoc.getIdModoNegocio() == toIdentificadorDoc.getIdModoNegocio()) {
						return identificadorDoc;
					}
				}
			}
		return identificadorDoc;
	}

	/**
	 * Consulta ide Persona Rut
	 * @param nit
	 * @return
	 * @throws DExcepcion
	 */
	private Long consultarIdPersonaRut(Long nit) throws DExcepcion {
		DCmdSrvConsRegistroRut srvConsRut = (DCmdSrvConsRegistroRut) this.getServicio("rut.DCmdSrvConsRegistroRut");
		srvConsRut.inicializarConsultarPorNIT(nit);
		DRegistroRutTO personaRut = new DRegistroRutTO();
		srvConsRut.ejecutar();
		personaRut = srvConsRut.getRegistroRut();
		this.esGranContribuyente = srvConsRut.isGranContribuyente();
		return personaRut.getPK().getIdePersonaRut();
	}

	/**
	 * Consulta la version valida del formato para el año gravable
	 * @param idFormato
	 * @return
	 * @throws Exception
	 */
	private IDFormato consultarFormato(Integer idFormato) throws Exception {
		LOGGER.info("Consultando versión valida del formato:"+idFormato+" año: "+anio);
		Collection<IDFormato> lstFormatos = DDocumentoUtil.getListaToFormatos(idFormato);
    	Iterator<IDFormato> it = lstFormatos.iterator();
    	while(it.hasNext()){
    		IDFormato formato = (IDFormato)it.next();
    		Integer anioDesde = formato.getNumAnnoGravable();
    		Integer anioHasta = formato.getNumAnnoGravableHasta();
    		if(anio>= anioDesde && anio<= anioHasta){
    			LOGGER.info("Versión valida del formato:"+formato.getId()+" versión: "+formato.getVersion());
    			return formato;
    		}
    	}
    	LOGGER.info("No se encontró version valida del formato");
		return null;
	}

	/**
	 * 
	 * @param documento
	 * @param idePersona
	 * @return
	 * @throws Exception
	 */
	private IDDocumento actualizarCabeceraPie(IDDocumento documento, Long idePersona) throws Exception {
		
		documento.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_ANNIO).setValor(this.anio);
		documento.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_PAGO_TOTAL).setValor(0);
		documento.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_ORIGEN_DECLARACION).setValor(IDTiposOrigenDeclaracion.INTERNO_PRIVADA);
		DCmdSrvLlenarDocumentoConsRut srvLlenarDocumento = (DCmdSrvLlenarDocumentoConsRut) getServicio("entradasalida.consintegral.DCmdSrvLlenarDocumentoConsRut");
		// El documento es modificado por referencia
		srvLlenarDocumento.inicializarLlenarCabeceraPiePorIdePersonaRut(idePersona.longValue(),documento);
		srvLlenarDocumento.ejecutar();
		IDDocumento documentoAct = srvLlenarDocumento.getDocumento();
		return documentoAct;
	}
	
	/**
	 * Guarda documento borrador
	 * @param doc
	 * @param ideFormato
	 * @param modo
	 * @param conId
	 * @return
	 * @throws DExcepcion
	 */
	private DIdentificadorDoc salvarDocumento(IDDocumento doc, int ideFormato, Integer modo, Boolean conId) throws DExcepcion {

		if (modo== null)
			modo = IDModosDiligenciamiento.MODO_INICIAL;
		
		DIdentificadorDoc identificadorDoc = new DIdentificadorDoc();
		DCmdAccCrearDocumentoESTemporal dCmdSrvCrearDocumentoES = (DCmdAccCrearDocumentoESTemporal) getAccion("entradasalida.DCmdAccCrearDocumentoESTemporal");
		String strDocXML = DDocumentoUtil.obtenerXmlDeDocumento(doc);
		if (conId)
			dCmdSrvCrearDocumentoES.inicializarGuardarConId(strDocXML, IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO, modo, ideFormato);
		else
			dCmdSrvCrearDocumentoES.inicializarGuardarSinId(strDocXML, IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO, modo, ideFormato);
		
		dCmdSrvCrearDocumentoES.ejecutar();
		identificadorDoc = dCmdSrvCrearDocumentoES.getIdentificadorDoc();

		return identificadorDoc;
	}
	
	/**
	 * Actualiza documento borrador
	 * @param doc
	 * @param ideFormato
	 * @param modo
	 * @return
	 * @throws DExcepcion
	 */
	
	private DIdentificadorDoc actualizarDocumento(IDDocumento doc, int ideFormato, Integer modo) throws DExcepcion {

		if (modo== null)
			modo = IDModosDiligenciamiento.MODO_INICIAL;
		
		DIdentificadorDoc identificadorDoc = new DIdentificadorDoc();
		DCmdAccCrearDocumentoESTemporal dCmdSrvCrearDocumentoES = (DCmdAccCrearDocumentoESTemporal) getAccion("entradasalida.DCmdAccCrearDocumentoESTemporal");
		String strDocXML = DDocumentoUtil.obtenerXmlDeDocumento(doc);
		
		dCmdSrvCrearDocumentoES.inicializarActualizar(doc.getId(), doc.getRepeticion(), strDocXML, IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO, modo, doc.getIdFormato());
		dCmdSrvCrearDocumentoES.ejecutar();
		identificadorDoc = dCmdSrvCrearDocumentoES.getIdentificadorDoc();

		return identificadorDoc;
	}
	
	/**
	 * 
	 * @return
	 * @throws DExcepcion
	 */
	/*private Boolean isGranContribuyente()throws DExcepcion {
		Boolean esGranContribuyente = false;
		DDatosRutHelper rutHelper = new DDatosRutHelper();
		esGranContribuyente = rutHelper.isGranContribuyente();
		return esGranContribuyente;
	}*/
	
	/**
	 * Construye objeto DIdentificadorDocumentoTO
	 * @param ideFormato
	 * @param numVersionFormato
	 * @param ideDocumento
	 * @param modoNegocio
	 * @param anno
	 * @param nit
	 * @return
	 * @throws DEntradaSalidaExcepcion
	 */
	private DIdentificadorDocumentoTO construirIdentificadorDoc(int ideFormato, int numVersionFormato, Long ideDocumento, Integer modoNegocio, Integer anno, Long nit) throws DEntradaSalidaExcepcion {

		DIdentificadorDocumentoTO toIdentificadorDoc = new DIdentificadorDocumentoTO();
		Map casillasCabPie = new HashMap();
		toIdentificadorDoc.setIdFormato(new Integer(ideFormato));
		toIdentificadorDoc.setNumVersion(numVersionFormato);
		toIdentificadorDoc.setIdModoNegocio(modoNegocio);
		if (ideDocumento != null)
			toIdentificadorDoc.setIdDocumento(ideDocumento);
		if (anno > 0) {
			casillasCabPie.put(new Integer(IDConstantesFormato.IDE_CASILLA_ANNIO), new Long(anno));
		}
		if (nit > 0) {
			casillasCabPie.put(new Integer(IDConstantesFormato.IDE_CASILLA_NIT), new Long(nit));
		}
		toIdentificadorDoc.setCasillasCabPie(casillasCabPie);
		return toIdentificadorDoc;
	}
	
	/**
	 * Verifica si los datos proporcionados para generar una declaración de fracción de año sean correctos y consistentes.
	 * 
	 * @param anio Dato de tipo <code>int</code> que representa el año gravable informado. 
	 * @return Dato de tipo <code>boolean</code> que indica si el proceso es válido (<code>true</code>) o no (<code>false</code>);
	 */
	private boolean esFraccionAnioValida(int anio){
		Calendar cal=Calendar.getInstance();
		int anioActual=cal.get(Calendar.YEAR);
		if(anio<anioActual){
			this.esFraccionAnioValida=false;
		}/*fin de if*/
		return this.esFraccionAnioValida;
	}/*fin de esFraccionAnioValida*/
	
	/**
	 * Finaliza el procesamiento de una solicitud asignando un estado determinado.
	 * 
	 * @param ideSolicitud Dato de tipo <code>Long</code> con el número de la solicitud en proceso.
	 * @param estadoProceso Dato de tipo <code>Integer</code> con el estado a asignar.
	 * @throws Exception 
	 */
	public void finalizarProcesoFlujoPosterior(DAuditoriaRegistroPKTO auditoriaRegistroPk, Integer marcaProceso, String mensaje) throws Exception{
		
		DProcesamientoFlujoPosteriorHelper.finalizarErrorValidacionProcesoFlujoPosterior(documentoCargaPK.getIdeDocumento());
		
		DCmdSrvConsAuditoriaRegistro consAuditoriaReg = (DCmdSrvConsAuditoriaRegistro) getServicio("cargamasiva.procesamiento.DCmdSrvConsAuditoriaRegistro");
		consAuditoriaReg.inicializar(auditoriaRegistroPk);
		consAuditoriaReg.ejecutar();
		DAuditoriaRegistroTO auditoriaTO= consAuditoriaReg.getAuditoriaRegistro();

		String desError =  auditoriaTO.getAtt().getDesError();
		StringBuffer sbuffer = new StringBuffer(desError);
		
		if (!sbuffer.toString().endsWith("</led>")){
			
			sbuffer = new StringBuffer(sbuffer.toString().replace("/>",">"));
			sbuffer = new StringBuffer(sbuffer.toString().replace("\n",""));
			sbuffer.append( "<" ).append( TAG_ERROR ).append( " " );
			sbuffer.append( "id_ef=\"").append(marcaProceso).append("\" id_g=\"1\" id_c=\"4\" n_o=\"1\" act=\"n\" mensaje=\"").append(mensaje).append("\" />" ).append("\n");
			sbuffer.append( "</led>" );
		}else{
			int index=sbuffer.toString().indexOf("</led>");
			StringBuffer aux= new StringBuffer(desError.substring(0, index));
			aux.append( "<" ).append( TAG_ERROR ).append( " " );
			aux.append( "id_ef=\"").append(marcaProceso).append("\" id_g=\"1\" id_c=\"4\" n_o=\"1\" act=\"n\" mensaje=\"").append(mensaje).append("\" />" ).append("\n");
			aux.append( "</led>" );
			sbuffer = aux;
			
		}
			
		desError = sbuffer.toString();
		
		auditoriaTO.getAtt().setDesError(desError);
		auditoriaTO.getAtt().setFecCambio(new Timestamp(System.currentTimeMillis()));
		auditoriaTO.getAtt().setCodMarca(IDConstantesEstadosProcesoFlujoPosterior.COD_ERROR_CON_MARCA);
		
		DCmdSrvActAuditoriaRegistro srvActAuditoria = (DCmdSrvActAuditoriaRegistro)	getServicio("cargamasiva.procesamiento.DCmdSrvActAuditoriaRegistro");
		srvActAuditoria.inicializar(auditoriaTO);
		srvActAuditoria.ejecutar();
		LOGGER.info("Se actualiza des_error");

	}/* fin de finalizarProcesoFlujoPosterior*/ 
	
	 
	
}/*fin de class*/
