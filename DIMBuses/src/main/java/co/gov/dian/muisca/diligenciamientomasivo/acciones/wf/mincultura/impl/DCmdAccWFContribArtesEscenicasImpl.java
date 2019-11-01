package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.mincultura.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.constantes.TipoDocumentos;
import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.to.tablasbasicas.DValorDominioAttTO;
import co.gov.dian.muisca.arquitectura.general.to.tablasbasicas.DValorDominioTO;
import co.gov.dian.muisca.arquitectura.servicios.valoresdominio.DCmdSrvConsValorDominio;
import co.gov.dian.muisca.arquitectura.web.buses.DContextoSeguridad;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DAuditoriaRegistroPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DAuditoriaRegistroTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudArchivoTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoPKTO;
import co.gov.dian.muisca.cargamasiva.helper.DProcesamientoFlujoPosteriorHelper;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvActAuditoriaRegistro;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsAuditoriaRegistro;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsLstSolicitudArchivo;
import co.gov.dian.muisca.diligenciamiento.general.constantes.IDConstantesDiligenciamiento;
import co.gov.dian.muisca.diligenciamiento.general.helper.DDatosArqHelper;
import co.gov.dian.muisca.diligenciamiento.general.helper.DObtenerDocValidoHelper;
import co.gov.dian.muisca.diligenciamiento.util.listas.DIdentificadoresDocComparator;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.mincultura.DCmdAccWFContribArtesEscenicas;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConceptosNegocioAnexoRentaGC;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConceptosNegocioFtosMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConstantesEstadosProcesoFlujoPosterior;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConstantesMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDeclaracionContribucionParafiscalEventoTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDeclaracionContribucionParafiscalPatrocinadorTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDeclaracionContribucionParafiscalTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvCrearDeclaracionContribucionParafiscal;
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
import co.gov.dian.muisca.entradasalida.liquidacion.impl.DCalculosLiquidacion;
import co.gov.dian.muisca.entradasalida.servicios.consintegral.DCmdSrvLlenarDocumentoConsRut;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoMUISCA;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.servicios.formatos.DCmdSrvConsLstMapeoCasillaNegocio;
import co.gov.dian.muisca.gestionexpediente.comando.IDContextoWF;
import co.gov.dian.muisca.rut.constantes.DTipoIdentificacion;
import co.gov.dian.muisca.rut.general.to.DRegistroRutPKTO;
import co.gov.dian.muisca.rut.general.to.DRegistroRutTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsPersonaRut;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsRegistroRut;


public class DCmdAccWFContribArtesEscenicasImpl extends DCmdAccWFContribArtesEscenicas {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String TAG_ERROR = "e";
	private String mensaje;
	
	protected void ejecutarComandoSinTransaccion() {
		LOGGER.info("*********** INICIO circuito generacion documento Contribución Artes Escenicas 480 ***************");

		try {
			
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
			
			Long valorTotalBoleteria = 0L;
			Long totalDerechosCortesia = 0L;
			Long totalDerechosPatrocinio = 0L;
			Long valorSubtotalRetenciones = 0L;
			Long valorSubtotalCrucesMenor3Uvt = 0L;
			Long pagosExceso = 0L;
			String productorOcasinal = "false";
			String productorPermanente = "false";
			String registroProductor = "";
			
			IDDocumento docTemporal = null;
			IDOcurrencia ocurrenciaRetencion = null;
			
			int numVersionFormato = 0;

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
					// documentoRte  ----> 2185
					IDDocumento documentoAnexoRte = srvConsDoc.getDocumento();
					IDOcurrencia ocurrenciaAnexoRte = documentoAnexoRte.getGrupos().getGrupo(1).getOcurrencia(1);
					if(docTemporal == null) {
						//@todo OJO porque no siempre el número de identificación corresponde a un NIT
						//Integer tipoIdentificacion = ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_TIPO_DOCUMENTO).getIntWrapper();
						//String numIdentificacion = ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_NUM_IDENTIFICACION).getValorCadena();
						this.nit = ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_NIT).getValorEntero();
						this.idPersona = consultarIdPersonaRut(nit); 
						//this.nit = consultarNit(idPersona);
						this.anio = ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_ANNIO).getInt();
						this.tipoDecla = ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_TIPO_DECLARACION).getInt();
											
						IDFormato formato = consultarFormato(IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_CONTRIB_MINCULTURA);
						numVersionFormato = formato.getVersion();
						docTemporal = DDocumentoUtil.getDocumentoEnBlanco(formato);
						this.documentoRetencion = actualizarCabeceraPie(docTemporal, this.idPersona);
						ocurrenciaRetencion = documentoRetencion.getGrupos().getGrupo(1).getOcurrencia(1);
					}

					/* TODO
					 * Se hace la comparación asi, mientras se sabe cual es el valor real de la inicial y corrección
					 */
					if ( tipoDecla == IDConceptosNegocioFtosMinCultura.TIPO_DECLARACION_CORRECCION){
						correccion = true;
						docCorreccion480 = ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CAS_CODIGO_CORRECCION).getValorEntero();
						ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_CODIGO_CORRECCION).setValor(IDConstantesMinCultura.VAL_DOMINIO_CORRECCION_PRIVADA);
						ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CAS_CODIGO_CORRECCION).setValor(docCorreccion480);
					}

					
					DFormatoPKTO formatoPKRenta = new DFormatoPKTO(documentoRetencion.getIdFormato(),documentoRetencion.getVersionFormato());
					DFormatoPKTO formatoPKAnexo = new DFormatoPKTO(documentoAnexoRte.getIdFormato(),documentoAnexoRte.getVersionFormato());

					DCmdSrvConsLstMapeoCasillaNegocio srvConsLstMapeoNeg = (DCmdSrvConsLstMapeoCasillaNegocio) getServicio("entradasalida.formatos.DCmdSrvConsLstMapeoCasillaNegocio");
					srvConsLstMapeoNeg.inicializarConsLstMapeosFtoVer(formatoPKRenta);
					srvConsLstMapeoNeg.ejecutar();
					colMapeoCasillaNegocioRetencion = srvConsLstMapeoNeg.getColeccionMapeoCasillaNegocio();
					
					srvConsLstMapeoNeg.inicializarConsLstMapeosFtoVer(formatoPKAnexo);
					srvConsLstMapeoNeg.ejecutar();
					colMapeoCasillaNegocioAnexoRte = srvConsLstMapeoNeg.getColeccionMapeoCasillaNegocio();					

					Iterator iterCasNegRetencion = colMapeoCasillaNegocioRetencion.iterator();
					// Consulta los mapeos de casillas de negocio iguales para el 480 y 2185
					while (iterCasNegRetencion.hasNext()){
						DMapeoCasillaNegocioTO casilla = (DMapeoCasillaNegocioTO) iterCasNegRetencion.next();
						LOGGER.info("Casilla: "+casilla.getPK().getIdeCasilla()+" del 480. Concepto: "+casilla.getPK().getIdeConcepto());
												
						if (casilla.getPK().getIdeCasilla() == IDConstantesFormato.IDE_CASILLA_PERIODO || (casilla.getPK().getIdeCasilla() > IDConstantesFormato.FINAL_NUM_CABECERA && casilla.getPK().getIdeCasilla() < IDConstantesFormato.INICIO_NUM_PIE)){
							Iterator iterCasNegAnexo = colMapeoCasillaNegocioAnexoRte.iterator();
							while (iterCasNegAnexo.hasNext()){ 

								DMapeoCasillaNegocioTO casillaAnexo = (DMapeoCasillaNegocioTO) iterCasNegAnexo.next();
								Long valorCasilla;
								if (casillaAnexo.getPK().getIdeGrupo().intValue() == 1 &&  casilla.getPK().getIdeConcepto().equals(casillaAnexo.getPK().getIdeConcepto())){
									valorCasilla = documentoAnexoRte.getGrupos().getGrupo(casillaAnexo.getPK().getIdeGrupo()).getOcurrencia(1).getValCasillaNeg(casillaAnexo.getPK().getIdeConcepto()).getLong();																	
									if(casillaAnexo.getPK().getIdeCasilla() == IDConstantesFormato.IDE_CASILLA_PERIODO) {
										periodo = valorCasilla.intValue();
										break;
									}									
									
								} else if (casillaAnexo.getPK().getIdeGrupo().intValue() == 1 ){																		
									if(casilla.getPK().getIdeConcepto().equals(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUMERO_TOTAL_BOLETAS_VENDIDAS)){
										Long tmpBol =  documentoAnexoRte.getGrupos().getGrupo(1).getOcurrencia(1).getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUMERO_SUBTOTAL_BOLETAS_VENDIDAS).getLong();
										if(tmpBol == null) {
											tmpBol = 0L;
										}
										valorTotalBoleteria += tmpBol;
										break;
									} else if(casilla.getPK().getIdeConcepto().equals(IDConceptosNegocioFtosMinCultura.CAS_NEG_VALOR_TOTAL_DERECHOS_ASISTENCIA_CORTESIA)){
										Long tmpTotalOtr = documentoAnexoRte.getGrupos().getGrupo(1).getOcurrencia(1).getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VALOR_SUBTOTAL_DERECHOS_ASISTENCIA_CORTESIA).getLong();
										if(tmpTotalOtr == null) {
											tmpTotalOtr = 0L;
										}
										totalDerechosCortesia += tmpTotalOtr;
										break;
									} else if(casilla.getPK().getIdeConcepto().equals(IDConceptosNegocioFtosMinCultura.CAS_NEG_VALOR_TOTAL_DERECHOS_ASISTENCIA_PATROCINIOS)){
										Long tmpTotalBase = documentoAnexoRte.getGrupos().getGrupo(1).getOcurrencia(1).getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VALOR_SUBTOTAL_DERECHOS_ASISTENCIA_PATROCINIOS).getLong();
										if(tmpTotalBase == null) {
											tmpTotalBase = 0L;
										}
										totalDerechosPatrocinio += tmpTotalBase;
										break;
									} else if(casilla.getPK().getIdeConcepto().equals(IDConceptosNegocioFtosMinCultura.CONCEPTO_MENOS_RETENCION_CONTRIB_PARAFISCAL)){
										Long tmpPagoExceso = documentoAnexoRte.getGrupos().getGrupo(1).getOcurrencia(1).getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_SUBTOTAL_RETENCIONES).getLong();
										if(tmpPagoExceso == null ){
											tmpPagoExceso = 0L;
										}
										valorSubtotalRetenciones += tmpPagoExceso;
										break;
									} else if(casilla.getPK().getIdeConcepto().equals(IDConceptosNegocioFtosMinCultura.CONCEPTO_MENOS_TOTAL_BOLETAS_VENDIDAS)){
										Long tmpMenos3Uvt = documentoAnexoRte.getGrupos().getGrupo(1).getOcurrencia(1).getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_TOTAL_CRUCES).getLong();
										if(tmpMenos3Uvt == null ){
											tmpMenos3Uvt = 0L;
										}
										valorSubtotalCrucesMenor3Uvt += tmpMenos3Uvt;
										break;
									} else if(casilla.getPK().getIdeConcepto().equals(IDConceptosNegocioFtosMinCultura.CONCEPTO_PAGOS_EXCESO_CONTRIB_PARAFISCAL_ANTERIORES)){
										Long tmpPagoExceso = documentoAnexoRte.getGrupos().getGrupo(1).getOcurrencia(1).getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_SUBTOTAL_DEVOLUCIONES).getLong();
										if(tmpPagoExceso == null ){
											tmpPagoExceso = 0L;
										}
										pagosExceso += tmpPagoExceso;
										break;
									} else if(casilla.getPK().getIdeConcepto().equals(IDConceptosNegocioFtosMinCultura.CAS_NEG_CLAS_PROD_PERMANENTE)){
										Integer clasProd = documentoAnexoRte.getGrupos().getGrupo(1).getOcurrencia(1).getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_CLASE_PRODUCTOR).getIntWrapper();
										if(clasProd != null && clasProd.intValue() == 1){
											productorPermanente = "true";
										} else if(clasProd != null && clasProd.intValue() == 2){
											productorOcasinal = "true";
										}
										break;
									} else if(casilla.getPK().getIdeConcepto().equals(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_NUM_REGISTRO_PRODUCTOR)){
										registroProductor = documentoAnexoRte.getGrupos().getGrupo(1).getOcurrencia(1).getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_NUM_REGISTRO_PRODUCTOR).getValorCadena();
										break;
									}
																										
								}
							}

						}						
												
					}
					
					//Se poblan los tos para persistir los datos del documento 2185
					
					
					generarPersistencia1185(documentoAnexoRte);
										
					
					// Genera TO Solicitud Declaracion
					DSolicitudDeclaracionAttTO attToSolicitudDeclaracion = new DSolicitudDeclaracionAttTO();
					DSolicitudDeclaracionPKTO pkSolicitudDeclaracion = new DSolicitudDeclaracionPKTO(documentoCargaPK.getIdeDocumento(),documentoCargaPK.getNumRepeticion());
					attToSolicitudDeclaracion.setCodEstado(IDDocumento.IDE_ESTADO_PRESENTADO);
					attToSolicitudDeclaracion.setIdeFormato(IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_CONTRIB_MINCULTURA);
					attToSolicitudDeclaracion.setNumVersionFormato((byte)numVersionFormato);
					attToSolicitudDeclaracion.setIdePersona(idPersona);
					attToSolicitudDeclaracion.setFecCambio(new Timestamp(System.currentTimeMillis()));
					attToSolicitudDeclaracion.setIdeUsuarioCambio(solicitudArchivoTO.getAtt().getIdeUsuarioCambio());
					attToSolicitudDeclaracion.setIdeFormatoCarga(documentoAnexoRte.getIdFormato());
					attToSolicitudDeclaracion.setNumVersionFormatoCarga((byte)documentoAnexoRte.getVersionFormato());
					toSolicitudDeclaracion = new DSolicitudDeclaracionTO(pkSolicitudDeclaracion, attToSolicitudDeclaracion);
				}
			}
			
			valorTotalBoleteria = DCalculosLiquidacion.redondearAMiles(valorTotalBoleteria);
			totalDerechosCortesia = DCalculosLiquidacion.redondearAMiles(totalDerechosCortesia);
			totalDerechosPatrocinio = DCalculosLiquidacion.redondearAMiles(totalDerechosPatrocinio);
			valorSubtotalRetenciones = DCalculosLiquidacion.redondearAMiles(valorSubtotalRetenciones);
			valorSubtotalCrucesMenor3Uvt = DCalculosLiquidacion.redondearAMiles(valorSubtotalCrucesMenor3Uvt);
			Long baseGravable = DCalculosLiquidacion.redondearAMiles(valorTotalBoleteria + totalDerechosCortesia + totalDerechosPatrocinio -  valorSubtotalCrucesMenor3Uvt);
			if(baseGravable < 0L) {
				baseGravable = 0L;
			}
			Long totalContribParaf = DCalculosLiquidacion.redondearAMiles(baseGravable * 0.1);
			pagosExceso = DCalculosLiquidacion.redondearAMiles(pagosExceso);
			Long totalPagar  = DCalculosLiquidacion.redondearAMiles(totalContribParaf - valorSubtotalRetenciones);
			if(totalPagar < 0L) {
				totalPagar =0L;
			}
			Long netoPagar  = DCalculosLiquidacion.redondearAMiles(totalPagar - pagosExceso);
			if(netoPagar < 0L) {
				netoPagar = 0L;
			}
			//28
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUMERO_TOTAL_BOLETAS_VENDIDAS).setValor(valorTotalBoleteria);
			//29
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VALOR_TOTAL_DERECHOS_ASISTENCIA_CORTESIA).setValor(totalDerechosCortesia);
			//30
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VALOR_TOTAL_DERECHOS_ASISTENCIA_PATROCINIOS).setValor(totalDerechosPatrocinio);
			//31
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_MENOS_TOTAL_BOLETAS_VENDIDAS).setValor(valorSubtotalCrucesMenor3Uvt);
			//32
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_BASE_GRAVABLE_CONTRIB_PARAFISCAL).setValor(baseGravable);
			//33
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_TOTAL_CONTRIB_PARAFISCAL).setValor(totalContribParaf);
			//34
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_MENOS_RETENCION_CONTRIB_PARAFISCAL).setValor(valorSubtotalRetenciones);
			//35
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_TOTAL_PAGAR_CONTRIB_PARAFISCAL).setValor(totalPagar);
			//36
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_PAGOS_EXCESO_CONTRIB_PARAFISCAL_ANTERIORES).setValor(pagosExceso);
			//38
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_TOTAL_NETO_PAGAR_CONTRIB_PARAFISCAL).setValor(netoPagar);
			//40
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_TOTAL_SALDO_A_PAGAR).setValor(netoPagar);
			//42
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_VALOR_PAGO_SANCIONES).setValor(0L);
			//44
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_TOTAL_IMPUESTO_A_CARGO).setValor(netoPagar);
			
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_CLAS_PROD_PERMANENTE).setValor(productorPermanente);
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_CLAS_PROD_OCASIONAL).setValor(productorOcasinal);
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_NUM_REGISTRO_PRODUCTOR).setValor(registroProductor);
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_PAGO_TOTAL).setValor(netoPagar);
								
			
			if(productorOcasinal.equals("true")) {
				periodo += 10;
			}
			
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_PERIODO).setValor(periodo);
			
			LOGGER.info("El XML del documento es:");			
			LOGGER.info(DDocumentoUtil.obtenerXmlDeDocumento(documentoRetencion));	
			
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

		// Realiza todas las validaciones correspondientes al guardado del documento por estado, periodo y año
		if (tieneErrorNoTransaccional){
			isOk=false;
			return;
		}	
		try {
			DIdentificadorDoc identificadorDoc = null;
			DIdentificadorDoc identDocumento = null;
			if (!correccion){ //solo iniciales
				DIdentificadorDocumentoTO toIdentificadorDoc;				

				toIdentificadorDoc = construirIdentificadorDoc(documentoRetencion.getIdFormato(),documentoRetencion.getVersionFormato(),null, IDModosDiligenciamiento.MODO_INICIAL,anio,nit, periodo);

				identDocumento = consultarIdentificadorDoc(toIdentificadorDoc,true);
				// Busca documentos definitivos
				if (identDocumento != null){
					mensaje = "Se encontró una declaración inicial presentada, debe presentar una carga de corrección"; 
					finalizarProcesoFlujoPosterior(auditoriaRegistro, IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_EXISTE_INICIAL,mensaje);
					LOGGER.info(mensaje);
					isFinalizado = false;
					isOk = true;
					return;
				}else{
					// Si no existen, busca borradores para verificar el estado de estos
					identDocumento = consultarIdentificadorDoc(toIdentificadorDoc,false);
					if (identDocumento != null){
						if(identDocumento.getIdEstado()==IDEstadosProcesamiento.IDE_ESTADO_TEMPORAL){ // Si está en estado borrador lo sobrescribe
							documentoRetencion.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_NUM_FORMULARIO).setValor(identDocumento.getIdDocumento());
							identificadorDoc = actualizarDocumento(documentoRetencion, IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_CONTRIB_MINCULTURA, IDModosDiligenciamiento.MODO_INICIAL);
						}else if(identDocumento.getIdEstado()==IDEstadosProcesamiento.IDE_ESTADO_PROCESO_FIRMA){ // Si está en proceso de firma genera error
							mensaje = "Se encontró una declaración en proceso de firma";
							finalizarProcesoFlujoPosterior(auditoriaRegistro, IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_EN_FIRMAS,mensaje);
							LOGGER.info(mensaje);
							isFinalizado = false;
							isOk = true;
							return;								
						}
					}else{
						// Si no hay borradores guarda el documento
						identificadorDoc = salvarDocumento(documentoRetencion,IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_CONTRIB_MINCULTURA,IDModosDiligenciamiento.MODO_INICIAL,false);
					}
				}

			}else{

				boolean continuar = false;
				DIdentificadorDocumentoTO toIdentificadorDocIni = construirIdentificadorDoc(documentoRetencion.getIdFormato(),documentoRetencion.getVersionFormato(),null,IDModosDiligenciamiento.MODO_INICIAL,anio,nit, periodo);
				DIdentificadorDoc identDocumentoInic = consultarIdentificadorDoc(toIdentificadorDocIni,true);
				// Si tiene inicial continua, sino marca error
				if (identDocumentoInic != null){
					DIdentificadorDocumentoTO toIdentificadorDoc = construirIdentificadorDoc(documentoRetencion.getIdFormato(),documentoRetencion.getVersionFormato(),null,IDModosDiligenciamiento.MODO_CORRECCION,anio,nit, periodo);
					identDocumento = consultarIdentificadorDoc(toIdentificadorDoc,true);
					
					if (identDocumento == null){// No tiene documentos de corrección presentados
						if (identDocumentoInic.getIdDocumento() == docCorreccion480) {// Compara número doc inicial con casilla documento anterior
							LOGGER.info("Numero declaración inicial para corregir: "+identDocumentoInic.getIdDocumento());
							continuar = true;
						}
					}else{
						if (identDocumento.getIdDocumento() == docCorreccion480) {// Compara número doc inicial con casilla documento anterior
							LOGGER.info("Numero declaración corrección para corregir: "+identDocumento.getIdDocumento());
							continuar = true;
						}
					}
					if (continuar){
						//Consulta si tiene borradores de corrección
						DIdentificadorDocumentoTO toIdentificadorDocBorr = construirIdentificadorDoc(documentoRetencion.getIdFormato(),documentoRetencion.getVersionFormato(),null,IDModosDiligenciamiento.MODO_CORRECCION,anio,nit, periodo);
						DIdentificadorDoc identDocumentoBorr = consultarIdentificadorDoc(toIdentificadorDocBorr,false);
						if (identDocumentoBorr != null){
							if(identDocumentoBorr.getIdEstado()==IDEstadosProcesamiento.IDE_ESTADO_TEMPORAL){
								documentoRetencion.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_NUM_FORMULARIO).setValor(identDocumentoBorr.getIdDocumento());
								identificadorDoc = actualizarDocumento(documentoRetencion, IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_CONTRIB_MINCULTURA, IDModosDiligenciamiento.MODO_CORRECCION);
							}else if(identDocumentoBorr.getIdEstado()==IDEstadosProcesamiento.IDE_ESTADO_PROCESO_FIRMA){
								mensaje = "Se encontró una declaración en proceso de firma";
								finalizarProcesoFlujoPosterior(auditoriaRegistro, IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_EN_FIRMAS,mensaje);
								LOGGER.info(mensaje);
								isFinalizado = false;
								isOk = true;
								return;
							}
						}else{
							identificadorDoc = salvarDocumento(documentoRetencion,IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_CONTRIB_MINCULTURA,IDModosDiligenciamiento.MODO_CORRECCION,false);
						}
					}else{
						mensaje = "El número de documento registrado en la casilla de documento anterior no coincide con la última declaración válida para corregir.";
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
				LOGGER.info("***************Se creó el documento 470: "+identificadorDoc.getIdDocumento()+" generado para la solicitud: "+ documentoCargaPK.getIdeDocumento()+"***************");
			}
			
			LOGGER.info("*********** Se registra documento carga masiva ***************");
			DCmdSrvCrearSolicitudDeclaracion srvCrearSolicitudDeclaracion = (DCmdSrvCrearSolicitudDeclaracion) getServicio("diligenciamientomasivo.solicitudesdeclaracion.DCmdSrvCrearSolicitudDeclaracion");

			toSolicitudDeclaracion.getAtt().setIdeDocumento(identificadorDoc.getIdDocumento());
			toSolicitudDeclaracion.getAtt().setNumRepeticion(identificadorDoc.getNumRepeticion());

			srvCrearSolicitudDeclaracion.inicializar(toSolicitudDeclaracion);
			srvCrearSolicitudDeclaracion.ejecutar();
			
			declaracionContribParafTO.getAtt().setIdeDocumento480(identificadorDoc.getIdDocumento());
			declaracionContribParafTO.getAtt().setNumRepeticionDoc480(identificadorDoc.getNumRepeticion());
			declaracionContribParafTO.getAtt().setIdeSolicitud(documentoCargaPK.getIdeDocumento());
			declaracionContribParafTO.getAtt().setIdePersonaRut(idPersona);
			declaracionContribParafTO.getAtt().setFecCambio(new Timestamp(System.currentTimeMillis()));
			declaracionContribParafTO.getAtt().setIdeUsuarioCambio(this.getContexto().getContextoSeguridad().getIdeUsuario());
			
			guardarTOsDeclaracionAnexo(declaracionContribParafTO);

			LOGGER.info("*********** FIN Documento Retencion Contribución Artes Escénicas OK ***************");
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
	
	/**
	 * Construye objeto DIdentificadorDocumentoTO
	 * @param ideFormato
	 * @param numVersionFormato
	 * @param ideDocumento
	 * @param modoNegocio
	 * @param anno
	 * @param nit
	 * @throws DEntradaSalidaExcepcion
	 */
	private DIdentificadorDocumentoTO construirIdentificadorDoc(int ideFormato, int numVersionFormato, Long ideDocumento, Integer modoNegocio, Integer anno, Long nit, Integer periodo) throws DEntradaSalidaExcepcion {

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
		if (periodo != null & periodo > 0) {
			casillasCabPie.put(new Integer(IDConstantesFormato.IDE_CASILLA_PERIODO), new Long(periodo));
		}
		toIdentificadorDoc.setCasillasCabPie(casillasCabPie);
		return toIdentificadorDoc;
	}
	
	
	
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
	 * @throws Exception 
	 */
	/*private Long consultarNit(Long idePersonaRut) throws Exception {
		DCmdSrvConsRegistroRut srvConsRut = (DCmdSrvConsRegistroRut) this.getServicio("rut.DCmdSrvConsRegistroRut");
		srvConsRut.inicializarConsultarPorPK( new DRegistroRutPKTO(idePersonaRut));
		srvConsRut.ejecutar();		
		return srvConsRut.getRegistroRut().getAtt().getNumNit();
	}*/

	/**
	 * Consulta ide Persona Rut
	 * @param nit
	 * @return
	 * @throws Exception 
	 */
	/*private Long consultarIdPersonaRut(Integer tipoDocumento, String valIdentificacion) throws Exception {
		DCmdSrvConsPersonaRut srvConsRut = (DCmdSrvConsPersonaRut) this.getServicio("rut.DCmdSrvConsPersonaRut");
		
		if(tipoDocumento < 1000) {
			//Hay que dehomologar el valor
			DValorDominioTO tipDocumento = consultarValorDominio(tipoDocumento.toString(), TipoDocumentos.COD_DOMINIO_TIPOS_DOC);
			tipoDocumento = tipDocumento.getValorDominioPK().getCodigo();
		}
		
		srvConsRut.inicializarConsultaConfiable(tipoDocumento, valIdentificacion);
		srvConsRut.ejecutar();
		return srvConsRut.getMascaraRut().getPK().getIdePersonaRut();
		
	}*/

	/**
	 * Consulta ide Persona Rut
	 * @param nit
	 * @return
	 * @throws Exception 
	 */
	private Long consultarIdPersonaRut(Long nit) throws Exception {
		DCmdSrvConsRegistroRut srvConsRut = (DCmdSrvConsRegistroRut) this.getServicio("rut.DCmdSrvConsRegistroRut");
		srvConsRut.inicializarConsultarPorNIT(nit);
		DRegistroRutTO personaRut = new DRegistroRutTO();
		srvConsRut.ejecutar();
		personaRut = srvConsRut.getRegistroRut();
		return personaRut.getPK().getIdePersonaRut();
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
		//documento.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_PAGO_TOTAL).setValor(0);
		documento.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_ORIGEN_DECLARACION).setValor(IDTiposOrigenDeclaracion.PRIVADA);
		DCmdSrvLlenarDocumentoConsRut srvLlenarDocumento = (DCmdSrvLlenarDocumentoConsRut) getServicio("entradasalida.consintegral.DCmdSrvLlenarDocumentoConsRut");
		// El documento es modificado por referencia
		srvLlenarDocumento.inicializarLlenarCabeceraPiePorIdePersonaRut(idePersona.longValue(),documento);
		srvLlenarDocumento.ejecutar();
		IDDocumento documentoAct = srvLlenarDocumento.getDocumento();
		return documentoAct;
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
	
	private void consultaObligado() throws DExcepcion{		
		DDatosArqHelper helperARQ=new DDatosArqHelper(getContexto().getContextoSeguridad());
		esObligado=helperARQ.isObligado();
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
			dCmdSrvCrearDocumentoES.inicializarGuardarConId(strDocXML, IDFormato.ENTRADA_DILIGENCIAMIENTO_WEB, modo, ideFormato);
		else
			dCmdSrvCrearDocumentoES.inicializarGuardarSinId(strDocXML, IDFormato.ENTRADA_DILIGENCIAMIENTO_WEB, modo, ideFormato);
		
		dCmdSrvCrearDocumentoES.ejecutar();
		identificadorDoc = dCmdSrvCrearDocumentoES.getIdentificadorDoc();

		return identificadorDoc;
	}
	
	private void generarPersistencia1185(IDDocumento documentoAnexoRte) {
	
		IDOcurrencia ocurrenciaHoja1 = documentoAnexoRte.getGrupos().getGrupo(1).getOcurrencia(1);
		
		declaracionContribParafTO = generarToHoja1(ocurrenciaHoja1, documentoAnexoRte.getId(), documentoAnexoRte.getRepeticion());
		
		int[] ocurrenciasH2 = documentoAnexoRte.getGrupos().getGrupo(2).getOcurrencias();
		for(int i = 0; i < ocurrenciasH2.length; i++) {
			IDOcurrencia ocurrencia = documentoAnexoRte.getGrupos().getGrupo(2).getOcurrencia(ocurrenciasH2[i]);
			adicionarDatosHoja2(declaracionContribParafTO, ocurrencia);
		}
		
		int[] ocurrenciasH3 = documentoAnexoRte.getGrupos().getGrupo(3).getOcurrencias();
		for(int j = 0; j < ocurrenciasH3.length; j++) {
			IDOcurrencia ocurrencia = documentoAnexoRte.getGrupos().getGrupo(3).getOcurrencia(ocurrenciasH3[j]);
			adicionarDatosHoja3(declaracionContribParafTO, ocurrencia);
		}
				
	}
	
	private DDeclaracionContribucionParafiscalTO generarToHoja1(IDOcurrencia ocurrencia, Long ideDocumento, Integer numRepeticion) {
		DDeclaracionContribucionParafiscalTO declaracion = new DDeclaracionContribucionParafiscalTO();
		declaracion.getPK().setIdeDocumentoContParaf(ideDocumento);
		declaracion.getPK().setNumRepeticionDocContParf(numRepeticion);
		declaracion.getAtt().setFecCambio(new Timestamp(System.currentTimeMillis()));
		IDCasilla casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_ANNIO);
		if(casilla != null) {
			declaracion.getAtt().setNumAnio(casilla.getIntWrapper());
		}
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_PERIODO);
		if(casilla != null) {
			declaracion.getAtt().setNumPeriodo(casilla.getIntWrapper());
		}
		declaracion.getAtt().setIdeTipoDocumento(DTipoIdentificacion.IDE_NIT);		
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_NIT);
		if(casilla != null) {
			declaracion.getAtt().setNumIdentificacion(casilla.getValorCadena());
		}
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_PRIMER_APELLIDO);
		if(casilla != null) {
			declaracion.getAtt().setValPrimerApellido(casilla.getValorCadena());
		}
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_SEGUNDO_APELLIDO);
		if(casilla != null) {
			declaracion.getAtt().setValSegundoApellido(casilla.getValorCadena());
		}
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_PRIMER_NOMBRE);
		if(casilla != null) {
			declaracion.getAtt().setValPrimerNombre(casilla.getValorCadena());
		}
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_OTROS_NOMBRES);
		if(casilla != null) {
			declaracion.getAtt().setValOtrosNombres(casilla.getValorCadena());
		}
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_RAZON_SOCIAL);
		if(casilla != null) {
			declaracion.getAtt().setValRazonSocial(casilla.getValorCadena());
		}
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUMERO_SUBTOTAL_BOLETAS_VENDIDAS);
		if(casilla != null) {
			declaracion.getAtt().setValTotalBolVendida(casilla.getLongWrapper());
		}
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VALOR_SUBTOTAL_DERECHOS_ASISTENCIA_CORTESIA);
		if(casilla != null) {
			declaracion.getAtt().setValTotalDerCortesia(casilla.getLongWrapper());
		}
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VALOR_SUBTOTAL_DERECHOS_ASISTENCIA_PATROCINIOS);
		if(casilla != null) {
			declaracion.getAtt().setValTotalDerPatrocinio(casilla.getLongWrapper());
		}
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_TOTAL_CRUCES);
		if(casilla != null) {
			declaracion.getAtt().setValTotalCruces(casilla.getLongWrapper());
		}
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_SUBTOTAL_RETENCIONES);
		if(casilla != null) {
			declaracion.getAtt().setValSubtotalRetenciones(casilla.getLongWrapper());
		}	
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_SUBTOTAL_DEVOLUCIONES);
		if(casilla != null) {
			declaracion.getAtt().setValSubtotalDevoluciones(casilla.getLongWrapper());
		}	
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_TIPO_DECLARACION);
		if(casilla != null) {
			declaracion.getAtt().setValTipoDeclaracion(casilla.getIntWrapper());
		}
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUM_FORMULARIO_ANTERIOR);
		if(casilla != null) {
			declaracion.getAtt().setNumFormularioAnterior(casilla.getLongWrapper());
		}	
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_CLASE_PRODUCTOR);
		if(casilla != null) {
			declaracion.getAtt().setValClaseProductor(casilla.getValorCadena());
		}	
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_NUM_REGISTRO_PRODUCTOR);
		if(casilla != null) {
			declaracion.getAtt().setValNumRegistroProductor(casilla.getValorCadena());
		}
		casilla = ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_FECHA_TRANSACCION);
		if(casilla != null) {
			declaracion.getAtt().setValFechaPresentacion(casilla.getLongWrapper());
		}	
	        	
		return declaracion;        
	}

	private void adicionarDatosHoja2(DDeclaracionContribucionParafiscalTO declaracion, IDOcurrencia ocurrencia) {

		if(declaracion.getColEventos() == null) {
			declaracion.setColEventos(new ArrayList<DDeclaracionContribucionParafiscalEventoTO>());
		}
		IDCasillaColumnaDoc casNumEvento = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_CODIGO_O_NUM_EVENTO);
		if (casNumEvento != null) {
            int numFilas = casNumEvento.getNumFilas();
            for (int k = 1; k <= numFilas; k++) {
                IDCasillaColumnaDoc casMun = (IDCasillaColumnaDoc) ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_MUNICIPIO_DISTRITO_REALIZACION_ESPECTACULO);
                IDCasillaColumnaDoc casDepto = (IDCasillaColumnaDoc) ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_DEPARTAMENTO_REALIZACION_ESPECTACULO);
                IDCasillaColumnaDoc casNomLugar = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_LUGAR_ESCENARIO_EVENTO);
                IDCasillaColumnaDoc casFecEvento = (IDCasillaColumnaDoc) ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_FECHA_REALIZACION_ESPECTACULO);
                IDCasillaColumnaDoc casNumBoletasVend = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUMERO_TOTAL_BOLETAS_VENDIDAS);
                IDCasillaColumnaDoc casValBoletasVend = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_VALOR_TOTAL_BOLETAS_VENDIDAS);
                IDCasillaColumnaDoc casNumBoletasVendInf3 = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUM_BOL_VENDIDAS_INF_3UVT);
                IDCasillaColumnaDoc casValBoletasVendInf3 = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_BOL_VENDIDAS_INF_3UVT);
                IDCasillaColumnaDoc casNumBoletasVendSup3 = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_NUM_BOLETAS_PRECIO_IGUAL_SUPERIOR_3_UVT);
                IDCasillaColumnaDoc casValBoletasVendSup3 = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_VALOR_BOLETAS_PRECIO_IGUAL_SUPERIOR_3UVT);
                IDCasillaColumnaDoc casNumCortesias = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUM_CORTESIAS_ENTREGADAS);
                IDCasillaColumnaDoc casValCortesias = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_CORTESIAS_ENTREGADAS);
                IDCasillaColumnaDoc casNumCortesiasInf3 = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUM_CORT_ENTREG_INF_3UVT);
                IDCasillaColumnaDoc casValCortesiasInf3 = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_CORT_ENTREG_INF_3UVT);
                IDCasillaColumnaDoc casNumCortesiasSup3 = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_NUM_TOT_OTROS_DEREC_ASIST_CORTESIAS_PREC_IGUAL_SUP_3UVT);
                IDCasillaColumnaDoc casValCortesiasSup3 = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_VAL_TOT_OTROS_DEREC_ASIST_CORTESIAS_PREC_IGUAL_SUP_3_UVT);
                IDCasillaColumnaDoc casNumBoletasPatroc = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUM_BOLETAS_PATROCINIO);
                IDCasillaColumnaDoc casValBoletasPatroc = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_BOLETAS_PATROCINIOT);
                IDCasillaColumnaDoc casNumBoletasPatrocInf3 = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUM_BOL_PATROC_INF_3UVT);
                IDCasillaColumnaDoc casValBoletasPatrocInf3 = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_BOL_PATROC_INF_3UVT);
                IDCasillaColumnaDoc casNumBoletasPatrocSup3 = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUM_BOL_PATROC_SUP_3UVT);
                IDCasillaColumnaDoc casValBoletasPatrocSup3 = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_BOL_PATROC_SUP_3UVT);
                IDCasillaColumnaDoc casValBaseContrib = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_BASE_GRAVABLE_CONTRIB_PARAFISCAL);
                IDCasillaColumnaDoc casValContribParaf = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_TOTAL_PAGAR_CONTRIB_PARAFISCAL);
                IDCasillaColumnaDoc casValRetencionContrib = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_VALOR_RETENCION_CONTRIB_PARAFISCAL);
                IDCasillaColumnaDoc casNumBoletasPagoExceso = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUM_BOLETAS_PAGO_EXCESO);
                IDCasillaColumnaDoc casValBoletasPagoExceso = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_BOLETAS_PAGO_EXCESO);
                IDCasillaColumnaDoc casValPrimAp = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_PRIMER_APELLIDO_OP);
                IDCasillaColumnaDoc casValSegundoAp = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_SEGUNDO_APELLIDO_OP);
                IDCasillaColumnaDoc casValPrimerNomb = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_PRIMER_NOMBRE_OP);
                IDCasillaColumnaDoc casValOtrosNombs = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_OTROS_NOMBRES_OP);
                IDCasillaColumnaDoc casValRazonSocial = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_RAZON_SOCIAL_OP);
                
                
                DDeclaracionContribucionParafiscalEventoTO evento = new DDeclaracionContribucionParafiscalEventoTO();
                
                evento.getPK().setIdeDocumentoContParaf(declaracion.getPK().getIdeDocumentoContParaf());
                evento.getPK().setNumRepeticionDocContParf(declaracion.getPK().getNumRepeticionDocContParf());
                evento.getPK().setIdeItem(k);
                evento.getPK().setIdeOcurrencia(ocurrencia.getId());
                evento.getAtt().setIdeUsuarioCambio(this.getContexto().getContextoSeguridad().getIdeUsuario());
                evento.getAtt().setFecCambio(new Timestamp(System.currentTimeMillis()));
                
                if (casNumEvento != null && casNumEvento.irA(k)) {
                    evento.getAtt().setValNumEvento(casNumEvento.getValorCadena());                    
                }
                if (casMun != null && casMun.irA(k)) {
                    evento.getAtt().setIdeMunicipioEvento(casMun.getIntWrapper());
                }
                if (casDepto != null && casDepto.irA(k)) {
                    evento.getAtt().setIdeDepartamentoEvento(casDepto.getIntWrapper());                    
                }
                if (casNomLugar != null && casNomLugar.irA(k)) {
                    evento.getAtt().setValNombLugarEvento(casNomLugar.getValorCadena());
                }
                if (casFecEvento != null && casFecEvento.irA(k)) {
                    evento.getAtt().setFecRealizacionEvento(casFecEvento.getIntWrapper());
                }
                if (casNumBoletasVend != null && casNumBoletasVend.irA(k)) {
                    evento.getAtt().setNumBoletasVendidas(casNumBoletasVend.getIntWrapper());
                }
                if (casValBoletasVend != null && casValBoletasVend.irA(k)) {
                    evento.getAtt().setValBoletasVendidas(casValBoletasVend.getLongWrapper());
                }
                if (casNumBoletasVendInf3 != null && casNumBoletasVendInf3.irA(k)) {
                    evento.getAtt().setNumBolVendidasInf3uvt(casNumBoletasVendInf3.getIntWrapper());
                }
                if (casValBoletasVendInf3 != null && casValBoletasVendInf3.irA(k)) {
                    evento.getAtt().setValBolVendidasInf3uvt(casValBoletasVendInf3.getLongWrapper());
                }
                if (casNumBoletasVendSup3 != null && casNumBoletasVendSup3.irA(k)) {
                    evento.getAtt().setNumBolVendidasSup3uvt(casNumBoletasVendSup3.getIntWrapper());
                }
                if (casValBoletasVendSup3 != null && casValBoletasVendSup3.irA(k)) {
                    evento.getAtt().setValBolVendidasSup3uvt(casValBoletasVendSup3.getLongWrapper());
                }
                if (casNumCortesias != null && casNumCortesias.irA(k)) {
                    evento.getAtt().setNumCortesiasEntregadas(casNumCortesias.getIntWrapper());
                }
                if (casValCortesias != null && casValCortesias.irA(k)) {
                    evento.getAtt().setValCortesiasEntregadas(casValCortesias.getLongWrapper());
                }
                if (casNumCortesiasInf3 != null && casNumCortesiasInf3.irA(k)) {
                    evento.getAtt().setNumCortEntregInf3uvt(casNumCortesiasInf3.getIntWrapper());
                }
                if (casValCortesiasInf3 != null && casValCortesiasInf3.irA(k)) {
                    evento.getAtt().setValCortEntregInf3uvt(casValCortesiasInf3.getLongWrapper());
                }
                if (casNumCortesiasSup3 != null && casNumCortesiasSup3.irA(k)) {
                    evento.getAtt().setNumCortEntregSup3uvt(casNumCortesiasSup3.getIntWrapper());                    
                }
                if (casValCortesiasSup3 != null && casValCortesiasSup3.irA(k)) {
                    evento.getAtt().setValCortEntregSup3uvt(casValCortesiasSup3.getLongWrapper());
                }
                if (casNumBoletasPatroc != null && casNumBoletasPatroc.irA(k)) {
                    evento.getAtt().setNumBoletasPatrocinio(casNumBoletasPatroc.getIntWrapper());
                }
                if (casValBoletasPatroc != null && casValBoletasPatroc.irA(k)) {
                    evento.getAtt().setValBoletasPatrocinio(casValBoletasPatroc.getLongWrapper());
                }
                if (casNumBoletasPatrocInf3 != null && casNumBoletasPatrocInf3.irA(k)) {
                    evento.getAtt().setNumBolPatrocInf3uvt(casNumBoletasPatrocInf3.getIntWrapper());
                }
                if (casValBoletasPatrocInf3 != null && casValBoletasPatrocInf3.irA(k)) {
                    evento.getAtt().setValBolPatrocInf3uvt(casValBoletasPatrocInf3.getLongWrapper());
                }
                if (casNumBoletasPatrocSup3 != null && casNumBoletasPatrocSup3.irA(k)) {
                    evento.getAtt().setNumBolPatrocSup3uvt(casNumBoletasPatrocSup3.getIntWrapper());
                }
                if (casValBoletasPatrocSup3 != null && casValBoletasPatrocSup3.irA(k)) {
                    evento.getAtt().setValBolPatrocSup3uvt(casValBoletasPatrocSup3.getLongWrapper());
                }
                if (casValBaseContrib != null && casValBaseContrib.irA(k)) {
                    evento.getAtt().setValBaseGravableCp(casValBaseContrib.getLongWrapper());
                }
                if (casValContribParaf != null && casValContribParaf.irA(k)) {
                    evento.getAtt().setValContribParafiscal(casValContribParaf.getLongWrapper());
                }
                if (casValRetencionContrib != null && casValRetencionContrib.irA(k)) {
                    evento.getAtt().setValRetencionCp(casValRetencionContrib.getLongWrapper());
                }
                if (casNumBoletasPagoExceso != null && casNumBoletasPagoExceso.irA(k)) {
                    evento.getAtt().setNumBoletasPagoExceso(casNumBoletasPagoExceso.getIntWrapper());
                }
                if (casValBoletasPagoExceso != null && casValBoletasPagoExceso.irA(k)) {
                    evento.getAtt().setValBoletasPagoExceso(casValBoletasPagoExceso.getLongWrapper());                    
                }
                if (casValPrimAp != null && casValPrimAp.irA(k)) {
                    evento.getAtt().setValPrimerApellidoOp(casValPrimAp.getValorCadena());
                }
                if (casValSegundoAp != null && casValSegundoAp.irA(k)) {
                    evento.getAtt().setValSegundoApellidoOp(casValSegundoAp.getValorCadena());
                }
                if (casValPrimerNomb != null && casValPrimerNomb.irA(k)) {
                    evento.getAtt().setValPrimerNombreOp(casValPrimerNomb.getValorCadena());                    
                }
                if (casValOtrosNombs != null && casValOtrosNombs.irA(k)) {
                    evento.getAtt().setValOtrosNombresOp(casValOtrosNombs.getValorCadena());
                }
                if (casValRazonSocial != null && casValRazonSocial.irA(k)) {
                    evento.getAtt().setValRazonSocialOp(casValRazonSocial.getValorCadena());                    
                }
                declaracion.getColEventos().add(evento);
            }
		}				    
	}

	private void adicionarDatosHoja3(DDeclaracionContribucionParafiscalTO declaracion, IDOcurrencia ocurrencia) {

		if(declaracion.getColPatrocinadores() == null) {
			declaracion.setColPatrocinadores(new ArrayList<DDeclaracionContribucionParafiscalPatrocinadorTO>());
		}
		IDCasillaColumnaDoc casNumEvento = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_CODIGO_O_NUM_EVENTO);
		if (casNumEvento != null) {
            int numFilas = casNumEvento.getNumFilas();
            for (int k = 1; k <= numFilas; k++) {
                IDCasillaColumnaDoc casNumNit = (IDCasillaColumnaDoc) ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUM_NIT);
                IDCasillaColumnaDoc casDv = (IDCasillaColumnaDoc) ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NUM_DV);
                IDCasillaColumnaDoc casValRazonSocial = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_RAZON_SOCIAL);
                IDCasillaColumnaDoc casNumBoletasPatrocinio = (IDCasillaColumnaDoc) ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUM_BOLETAS_PATROCINIO_P);
                IDCasillaColumnaDoc casValUbicacionLocalidad = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_UBICACION_LOCALIDAD);
                IDCasillaColumnaDoc casValIndividualBoleta = (IDCasillaColumnaDoc)ocurrencia.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_VAL_INDIVIDUAL_BOLETA);
                                
                DDeclaracionContribucionParafiscalPatrocinadorTO patrocinio = new DDeclaracionContribucionParafiscalPatrocinadorTO();
                
                patrocinio.getPK().setIdeDocumentoContParaf(declaracion.getPK().getIdeDocumentoContParaf());
                patrocinio.getPK().setNumRepeticionDocContParf(declaracion.getPK().getNumRepeticionDocContParf());
                patrocinio.getPK().setIdeItem(k);
                patrocinio.getPK().setIdeOcurrencia(ocurrencia.getId());
                patrocinio.getAtt().setIdeUsuarioCambio(this.getContexto().getContextoSeguridad().getIdeUsuario());
                patrocinio.getAtt().setFecCambio(new Timestamp(System.currentTimeMillis()));
                
                if (casNumEvento != null && casNumEvento.irA(k)) {
                    patrocinio.getAtt().setNumEvento(casNumEvento.getValorCadena());                    
                }
                if (casNumNit != null && casNumNit.irA(k)) {
                    patrocinio.getAtt().setNumNit(casNumNit.getLongWrapper());                    
                }
                if (casDv != null && casDv.irA(k)) {
                    patrocinio.getAtt().setNumDv(casDv.getIntWrapper());                    
                }
                if (casValRazonSocial != null && casValRazonSocial.irA(k)) {
                    patrocinio.getAtt().setValRazonSocial(casValRazonSocial.getValorCadena());
                }
                if (casNumBoletasPatrocinio != null && casNumBoletasPatrocinio.irA(k)) {
                    patrocinio.getAtt().setNumBoletasPatrocinio(casNumBoletasPatrocinio.getIntWrapper());
                }
                if (casValUbicacionLocalidad != null && casValUbicacionLocalidad.irA(k)) {
                    patrocinio.getAtt().setValUbicacionLocalidad(casValUbicacionLocalidad.getValorCadena());
                }
                if (casValIndividualBoleta != null && casValIndividualBoleta.irA(k)) {
                    patrocinio.getAtt().setValIndividualBoleta(casValIndividualBoleta.getLongWrapper());
                }                                
                
                declaracion.getColPatrocinadores().add(patrocinio);
            }
		}				    
	}
	
	
	private void guardarTOsDeclaracionAnexo(DDeclaracionContribucionParafiscalTO toDeclaracionContribucionParafiscal) throws DExcepcion {
		DCmdSrvCrearDeclaracionContribucionParafiscal srvCrear = (DCmdSrvCrearDeclaracionContribucionParafiscal)getServicio("diligenciamientomasivo.mincultura.DCmdSrvCrearDeclaracionContribucionParafiscal");
		srvCrear.inicializar(toDeclaracionContribucionParafiscal);
		srvCrear.ejecutar();
		
	}
	
	
	 protected DValorDominioTO consultarValorDominio(String valDominio,
	          Integer tipoDominio) throws DExcepcion {
	      DCmdSrvConsValorDominio accVD = (DCmdSrvConsValorDominio) getServicio(
	              "arquitectura.valoresdominio.DCmdSrvConsValorDominio");
	      DValorDominioAttTO att = new DValorDominioAttTO();
	      att.setCodDominio(tipoDominio);
	      att.setValor(valDominio);
	      accVD.inicializarConsultaPorATT(att);
	      accVD.ejecutar();
	      if (accVD.getValoresDominio() != null &&
	          accVD.getValoresDominio().size() > 0) {
	          return (DValorDominioTO) accVD.getValoresDominio().iterator().next();
	      }
	      return null;
	  }
}

