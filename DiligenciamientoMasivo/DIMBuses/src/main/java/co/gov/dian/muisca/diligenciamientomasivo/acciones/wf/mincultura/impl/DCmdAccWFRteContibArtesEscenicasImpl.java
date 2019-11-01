package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.mincultura.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.delegados.DBusDelegateFactory;
import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDBusDelegateFactory;
import co.gov.dian.muisca.arquitectura.interfaces.IDBusServiciosDelegate;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicio;
import co.gov.dian.muisca.arquitectura.web.buses.DContextoSeguridad;
import co.gov.dian.muisca.cargamasiva.constantes.IDEstadosCircuitoCargaMasiva;
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
import co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.mincultura.DCmdAccWFRteContibArtesEscenicas;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConceptosNegocioAnexoRentaGC;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConceptosNegocioFtosMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConstantesEstadosProcesoFlujoPosterior;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConstantesMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDetRetContrArtEscenicsAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDetRetContrArtEscenicsPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDetRetContrArtEscenicsTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRetenContribArtEscenicsAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRetenContribArtEscenicsPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRetenContribArtEscenicsTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvCrearRetenContribArtEscenics;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.DCmdSrvCrearSolicitudDeclaracion;
import co.gov.dian.muisca.entradasalida.acciones.DCmdAccCrearDocumentoESTemporal;
import co.gov.dian.muisca.entradasalida.documento.DDocumentoUtil;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDCasilla;
import co.gov.dian.muisca.entradasalida.documento.IDCasillaColumnaDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDEstadosProcesamiento;
import co.gov.dian.muisca.entradasalida.documento.IDGrupoDoc;
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
import co.gov.dian.muisca.rut.general.to.DRegistroRutTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsRegistroRut;


public class DCmdAccWFRteContibArtesEscenicasImpl extends DCmdAccWFRteContibArtesEscenicas {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4981059726715274885L;
	/**
	 * 
	 */
	final String TAG_ERROR = "e";
	private String mensaje;
	
	protected void ejecutarComandoSinTransaccion() {
		LOGGER.info("*********** INICIO circuito generacion documento Retencion Contribución Artes Escenicas 470 ***************");

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
			
			Long boletasPrecioSuperio3Uvt = 0L;
			Long totalOtrosDerechos = 0L;
			Long devolucionRetenciones = 0L;
			
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
					// documentoRte  ----> 2184
					IDDocumento documentoAnexoRte = srvConsDoc.getDocumento();
					IDOcurrencia ocurrenciaAnexoRte = documentoAnexoRte.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT);
					IDGrupoDoc grupo2AnexoRte =  documentoAnexoRte.getGrupos().getGrupo(2);
					
					if(docTemporal == null) {					
						this.nit = ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_NIT).getValorEntero();
						this.anio = ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_ANNIO).getInt();
						this.tipoDecla = ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_TIPO_DECLARACION).getIntWrapper();
						this.idPersona = consultarIdPersonaRut(nit);					
						IDFormato formato = consultarFormato(IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RTE_CONTRIB_MINCULTURA);
						numVersionFormato = formato.getVersion();
						docTemporal = DDocumentoUtil.getDocumentoEnBlanco(formato);
						this.documentoRetencion = actualizarCabeceraPie(docTemporal, this.idPersona);
						ocurrenciaRetencion = documentoRetencion.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT);
					}
					
					/* TODO
					 * Se hace la comparación asi, mientras se sabe cual es el valor real de la inicial y corrección
					 */
					if (tipoDecla == IDConceptosNegocioFtosMinCultura.TIPO_DECLARACION_CORRECCION){
						correccion = true;
						docCorreccion470 = ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CAS_CODIGO_CORRECCION).getValorEntero();
						ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_CODIGO_CORRECCION).setValor(IDConstantesMinCultura.VAL_DOMINIO_CORRECCION_PRIVADA);
						ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CAS_CODIGO_CORRECCION).setValor(docCorreccion470);
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
					
										
					// Consulta los mapeos de casillas de negocio iguales para el 470 y 2184
					while (iterCasNegRetencion.hasNext()){
						DMapeoCasillaNegocioTO casilla = (DMapeoCasillaNegocioTO) iterCasNegRetencion.next();
						LOGGER.info("Casilla: "+casilla.getPK().getIdeCasilla()+" del 470. Concepto: "+casilla.getPK().getIdeConcepto());
						if (casilla.getPK().getIdeCasilla() == IDConstantesFormato.IDE_CASILLA_PERIODO || (casilla.getPK().getIdeCasilla() > IDConstantesFormato.FINAL_NUM_CABECERA && casilla.getPK().getIdeCasilla() < IDConstantesFormato.INICIO_NUM_PIE)){
							Iterator iterCasNegAnexo = colMapeoCasillaNegocioAnexoRte.iterator();
							while (iterCasNegAnexo.hasNext()){ 
								DMapeoCasillaNegocioTO casillaAnexo = (DMapeoCasillaNegocioTO) iterCasNegAnexo.next();
								Long valorCasilla;
								if (casillaAnexo.getPK().getIdeGrupo().intValue() == 1 &&  casilla.getPK().getIdeConcepto().equals(casillaAnexo.getPK().getIdeConcepto())){
									valorCasilla = documentoAnexoRte.getGrupos().getGrupo(casillaAnexo.getPK().getIdeGrupo()).getOcurrencia(1).getValCasillaNeg(casillaAnexo.getPK().getIdeConcepto()).getLong();								
									ocurrenciaRetencion.getValCasillaNeg(casilla.getPK().getIdeConcepto()).setValor(valorCasilla);
									LOGGER.debug("Se copia valor de concepto negocio "+casilla.getPK().getIdeConcepto()+" Casilla:"+casillaAnexo.getPK().getIdeCasilla()+
										" del 2184 A casilla "+casilla.getPK().getIdeCasilla()+" de 470");
									if(casillaAnexo.getPK().getIdeCasilla() == IDConstantesFormato.IDE_CASILLA_PERIODO) {
										periodo = valorCasilla.intValue();
										break;
									}
								
									
								} else if (casillaAnexo.getPK().getIdeGrupo().intValue() == 1 ){
									if(casilla.getPK().getIdeConcepto().equals(IDConceptosNegocioFtosMinCultura.CONCEPTO_BOLETAS_PRECIO_SUPERIOR_3_UVT)){
										Long tmpBol =  documentoAnexoRte.getGrupos().getGrupo(casillaAnexo.getPK().getIdeGrupo()).getOcurrencia(1).getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_SUBTOTAL_BOLETAS_PRECIO_IGUAL_SUPERIOR_3_UVT).getLong();
										if(tmpBol == null) {
											tmpBol = 0L;
										}
										boletasPrecioSuperio3Uvt += tmpBol;
										break;
									} else if(casilla.getPK().getIdeConcepto().equals(IDConceptosNegocioFtosMinCultura.CONCEPTO_TOTAL_OTROS_DERECHOS_SUPERIOR_3_UVT)){
										Long tmpTotalOtr = documentoAnexoRte.getGrupos().getGrupo(1).getOcurrencia(1).getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_SUBT_OTROS_DERECHOS_ASIST_CORTESIAS_VALOR_IGUAL_SUP_UVT).getLong();
										if(tmpTotalOtr == null) {
											tmpTotalOtr = 0L;
										}
										totalOtrosDerechos += tmpTotalOtr;
										break;
									}else if(casilla.getPK().getIdeConcepto().equals(IDConceptosNegocioFtosMinCultura.CONCEPTO_DEVOLUCIONES_RETENCIONES_EXCESO)){
										Long tmpDevolucionRetenciones = documentoAnexoRte.getGrupos().getGrupo(1).getOcurrencia(1).getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_SUBT_RETEN_EXCESO_CONTRIB_PARAFISCAL_PERIODOS_ANTERIORES).getLong();
										if(tmpDevolucionRetenciones == null) {
											tmpDevolucionRetenciones = 0L;
										}
										devolucionRetenciones += tmpDevolucionRetenciones;
										break;
									}									
								}
							}							

						}						

					}			
					
					
					// Genera TO Solicitud Declaracion
					DSolicitudDeclaracionAttTO attToSolicitudDeclaracion = new DSolicitudDeclaracionAttTO();
					DSolicitudDeclaracionPKTO pkSolicitudDeclaracion = new DSolicitudDeclaracionPKTO(documentoCargaPK.getIdeDocumento(),documentoCargaPK.getNumRepeticion());
					attToSolicitudDeclaracion.setCodEstado(IDDocumento.IDE_ESTADO_PRESENTADO);
					attToSolicitudDeclaracion.setIdeFormato(IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RTE_CONTRIB_MINCULTURA);
					attToSolicitudDeclaracion.setNumVersionFormato((byte)numVersionFormato);
					attToSolicitudDeclaracion.setIdePersona(idPersona);
					attToSolicitudDeclaracion.setFecCambio(new Timestamp(System.currentTimeMillis()));
					attToSolicitudDeclaracion.setIdeUsuarioCambio(solicitudArchivoTO.getAtt().getIdeUsuarioCambio());
					attToSolicitudDeclaracion.setIdeFormatoCarga(documentoAnexoRte.getIdFormato());
					attToSolicitudDeclaracion.setNumVersionFormatoCarga((byte)documentoAnexoRte.getVersionFormato());
					toSolicitudDeclaracion = new DSolicitudDeclaracionTO(pkSolicitudDeclaracion, attToSolicitudDeclaracion);

					
					DRetenContribArtEscenicsAttTO attRetenContribArtEscenics = new DRetenContribArtEscenicsAttTO();
					DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics = new DRetenContribArtEscenicsPKTO(toAudReg.getPK().getIdeDocumento(),IDConstantesDiligenciamiento.NUM_REPETICION_DEFAULT);
					attRetenContribArtEscenics.setIdeFormato(IDE_FORMATO_RETEN_CONTRIB_ESPECTAC_PUBLICOS_ARTES_ESCENICAS);
					attRetenContribArtEscenics.setNumVersionFormato(NUM_VERSION_FORMATO_RETEN_CONTRIB_ESPECTAC_PUBLICOS_ARTES_ESCENICAS.byteValue());
					attRetenContribArtEscenics.setIdeDocumentoCarga(documentoCargaPK.getIdeDocumento());
					attRetenContribArtEscenics.setNumRepeticionCarga(documentoCargaPK.getNumRepeticion());
					attRetenContribArtEscenics.setIdeFormatoCarga(IDEstadosCircuitoCargaMasiva.FORMATO_SOLICITUD);
					attRetenContribArtEscenics.setNumVersionFormatoCarga((byte)IDEstadosCircuitoCargaMasiva.VERSION_FORMATO_SOLICITUD);
					attRetenContribArtEscenics.setIdePersonaRutAgente(idPersona);
					attRetenContribArtEscenics.setFecCambio(new Timestamp(System.currentTimeMillis()));
					attRetenContribArtEscenics.setIdeUsuarioCambio(solicitudArchivoTO.getAtt().getIdeUsuarioCambio());
					attRetenContribArtEscenics.setNumAnio(ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_ANNIO).getShortWrapper());
					attRetenContribArtEscenics.setNumPeriodo(ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_PERIODO).getByteWrapper());
					attRetenContribArtEscenics.setValSubtBolsPrecIgSup3uvt(ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_SUBTOTAL_BOLETAS_PRECIO_IGUAL_SUPERIOR_3_UVT).getBigDecimal());
					attRetenContribArtEscenics.setValSubtDerAsistIgSup3uvt(ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_SUBT_OTROS_DERECHOS_ASIST_CORTESIAS_VALOR_IGUAL_SUP_UVT).getBigDecimal());
					attRetenContribArtEscenics.setValSubtDevsRetensEvenAnt(ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_SUBT_RETEN_EXCESO_CONTRIB_PARAFISCAL_PERIODOS_ANTERIORES).getBigDecimal());
					attRetenContribArtEscenics.setNumTipoDeclaracion(ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_TIPO_DECLARACION).getIntWrapper());
					attRetenContribArtEscenics.setNumFormularioAnterior(ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUM_FORMULARIO_ANTERIOR).getLongWrapper());
					attRetenContribArtEscenics.setFecTransaccion(ocurrenciaAnexoRte.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_FECHA_TRANSACCION).getLongWrapper());
					attRetenContribArtEscenics.setCodEstado(IDDocumento.IDE_ESTADO_PRESENTADO);
					
					lstDetRetContrArtEscenics = new ArrayList<DDetRetContrArtEscenicsTO>();
					
					int numOcurrencias = grupo2AnexoRte.getOcurrencias().length;
					for(int i=1;i<=numOcurrencias;i++){

						IDOcurrencia ocurrenciaGrupo2 = grupo2AnexoRte.getOcurrencia(1);
						
						
						int numFilas = ((IDCasillaColumnaDoc)(ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_CODIGO_O_NUM_EVENTO))).getNumFilas();
						for(int j = 1;j<=numFilas;j++){
							DDetRetContrArtEscenicsAttTO attDetRetContrArtEscenics = new DDetRetContrArtEscenicsAttTO();
							DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics = new DDetRetContrArtEscenicsPKTO(toAudReg.getPK().getIdeDocumento(),IDConstantesDiligenciamiento.NUM_REPETICION_DEFAULT,(short)i,j);

							attDetRetContrArtEscenics.setFecCambio(new Timestamp(System.currentTimeMillis()));
							attDetRetContrArtEscenics.setIdeUsuarioCambio(solicitudArchivoTO.getAtt().getIdeUsuarioCambio());
							attDetRetContrArtEscenics.setIdeFormato(IDE_FORMATO_RETEN_CONTRIB_ESPECTAC_PUBLICOS_ARTES_ESCENICAS);
							attDetRetContrArtEscenics.setNumVersionFormato(NUM_VERSION_FORMATO_RETEN_CONTRIB_ESPECTAC_PUBLICOS_ARTES_ESCENICAS.byteValue());
							
							IDCasillaColumnaDoc casCodNumEvento = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_CODIGO_O_NUM_EVENTO);
							casCodNumEvento.irA(j);
							String codNumEvento =  casCodNumEvento.getValorCadena();
							attDetRetContrArtEscenics.setNumEvento(codNumEvento);
							
							IDCasillaColumnaDoc casNomEspectaculo = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_NOMBRE_ESPECTACULO);
							casNomEspectaculo.irA(j);
							String nomEspectaculo = casNomEspectaculo.getString();
							attDetRetContrArtEscenics.setNomEspectaculo(nomEspectaculo);
							
							IDCasillaColumnaDoc casMunDistRealizEspecta = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_MUNICIPIO_DISTRITO_REALIZACION_ESPECTACULO);
							casMunDistRealizEspecta.irA(j);
							Integer munDistRealizEspecta = casMunDistRealizEspecta.getIntWrapper();
							attDetRetContrArtEscenics.setCodMunicipioEspectaculo(munDistRealizEspecta);
							
							IDCasillaColumnaDoc casDptoRealizEspecta = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_MUNICIPIO_DISTRITO_REALIZACION_ESPECTACULO);
							casDptoRealizEspecta.irA(j);
							Integer dptoRealizEspecta = casDptoRealizEspecta.getIntWrapper();
							attDetRetContrArtEscenics.setCodDepartamentoEspectaculo(dptoRealizEspecta);
							
							IDCasillaColumnaDoc casFecRealizacion =  (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_FECHA_REALIZACION_ESPECTACULO);
							casFecRealizacion.irA(j);
							Integer fecRealizacion = casFecRealizacion.getIntWrapper();
							attDetRetContrArtEscenics.setFecRealizacion(fecRealizacion);
							
							IDCasillaColumnaDoc casNumNitProduc = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_NIT_PRODUCTOR);
							casNumNitProduc.irA(j);
							Long numNitProduc = casNumNitProduc.getLongWrapper();
							Long idePersonaRutProductor = consultarIdPersonaRut(numNitProduc);
							attDetRetContrArtEscenics.setIdePersonaRutProductor(idePersonaRutProductor);
														
							IDCasillaColumnaDoc casValDirProduc = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_DIRECCION_PRODUCTOR);
							casValDirProduc.irA(j);
							String valDirProduc = casValDirProduc.getString();
							attDetRetContrArtEscenics.setValDireccionProductor(valDirProduc);
														
							IDCasillaColumnaDoc casCiudMunProduc = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_CIUDAD_MUNICIPIO_PRODUCTOR);
							casCiudMunProduc.irA(j);
							Integer ciudMunProduc = casCiudMunProduc.getIntWrapper();
							attDetRetContrArtEscenics.setCodMunicipioProductor(ciudMunProduc);
							
							IDCasillaColumnaDoc casDptoProduc = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_DEPARTAMENTO_PRODUCTOR);
							casDptoProduc.irA(j);
							Integer dptoProduc = casDptoProduc.getIntWrapper();
							attDetRetContrArtEscenics.setCodDepartamentoProductor(dptoProduc);
							
							IDCasillaColumnaDoc casTelProduc = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_TELEFONO_PRODUCTOR);
							casTelProduc.irA(j);
							Long telProduc = casTelProduc.getLongWrapper();
							attDetRetContrArtEscenics.setNumTelefonoProductor(telProduc);
							
							IDCasillaColumnaDoc casValLugarEvento = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_LUGAR_ESCENARIO_EVENTO);
							casValLugarEvento.irA(j);
							String valLugarEvento = casValLugarEvento.getString();
							attDetRetContrArtEscenics.setValLugarEvento(valLugarEvento);
							
							IDCasillaColumnaDoc casNumTotBoletasVendidas = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_NUMERO_TOTAL_BOLETAS_VENDIDAS);
							casNumTotBoletasVendidas.irA(j);
							Integer numTotBoletasVendidas = casNumTotBoletasVendidas.getIntWrapper();
							attDetRetContrArtEscenics.setNumTotBoletasVendidas(numTotBoletasVendidas);
							
							IDCasillaColumnaDoc casValTotBoletasVendidas = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_VALOR_TOTAL_BOLETAS_VENDIDAS);
							casValTotBoletasVendidas.irA(j);
							BigDecimal valTotBoletasVendidas = casValTotBoletasVendidas.getBigDecimal();
							attDetRetContrArtEscenics.setValTotBoletasVendidas(valTotBoletasVendidas);
							
							IDCasillaColumnaDoc casValServDistComerBoleteria = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_VALOR_SERVICIO_DISTRIBUCION_COMERC_BOLETERIA);
							casValServDistComerBoleteria.irA(j);
							BigDecimal valServDistComerBoleteria = casValServDistComerBoleteria.getBigDecimal();
							attDetRetContrArtEscenics.setValServDistComerBoleteria(valServDistComerBoleteria);
							
							IDCasillaColumnaDoc casNumTotBolPrecIgSup3uvt = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_NUM_BOLETAS_PRECIO_IGUAL_SUPERIOR_3_UVT);
							casNumTotBolPrecIgSup3uvt.irA(j);
							Integer numTotBolPrecIgSup3uvt = casNumTotBolPrecIgSup3uvt.getIntWrapper();
							attDetRetContrArtEscenics.setNumTotBolPrecIgSup3uvt(numTotBolPrecIgSup3uvt);
							
							IDCasillaColumnaDoc casValTotBolPrecIgSup3uvt = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_VALOR_BOLETAS_PRECIO_IGUAL_SUPERIOR_3UVT);
							casValTotBolPrecIgSup3uvt.irA(j);
							BigDecimal valTotBolPrecIgSup3uvt = casValTotBolPrecIgSup3uvt.getBigDecimal();
							attDetRetContrArtEscenics.setValTotBolPrecIgSup3uvt(valTotBolPrecIgSup3uvt);
							
							IDCasillaColumnaDoc casNumTotDerAsPreIgSup3uvt = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_NUM_TOT_OTROS_DEREC_ASIST_CORTESIAS_PREC_IGUAL_SUP_3UVT);
							casNumTotDerAsPreIgSup3uvt.irA(j);
							Integer numTotDerAsPreIgSup3uvt = casNumTotDerAsPreIgSup3uvt.getIntWrapper();
							attDetRetContrArtEscenics.setNumTotDerAsPreIgSup3uvt(numTotDerAsPreIgSup3uvt);
							
							IDCasillaColumnaDoc casValTotDerAsPreIgSup3uvt = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_VAL_TOT_OTROS_DEREC_ASIST_CORTESIAS_PREC_IGUAL_SUP_3_UVT);
							casValTotDerAsPreIgSup3uvt.irA(j);
							BigDecimal valTotDerAsPreIgSup3uvt = casValTotDerAsPreIgSup3uvt.getBigDecimal();
							attDetRetContrArtEscenics.setValTotDerAsPreIgSup3uvt(valTotDerAsPreIgSup3uvt);
							
							IDCasillaColumnaDoc casNumBoletasRetencionExceso = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_NUM_BOLET_RETEN_EXCESO_CONTRIB_PARAFISCAL_PER_ANTERIORES);
							casNumBoletasRetencionExceso.irA(j);
							Integer numBoletasRetencionExceso = casNumBoletasRetencionExceso.getIntWrapper();
							attDetRetContrArtEscenics.setNumBoletasRetencionExceso(numBoletasRetencionExceso);
							
							IDCasillaColumnaDoc casValBoletasRetencionExceso = (IDCasillaColumnaDoc)ocurrenciaGrupo2.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_VAL_BOLET_RETEN_EXCESO_CONTRIB_PARAFISCAL_PER_ANTERIORES);
							casValBoletasRetencionExceso.irA(j);
							BigDecimal valBoletasRetencionExceso = casValBoletasRetencionExceso.getBigDecimal();
							attDetRetContrArtEscenics.setValBoletasRetencionExceso(valBoletasRetencionExceso);
		
							lstDetRetContrArtEscenics.add(new DDetRetContrArtEscenicsTO(pkDetRetContrArtEscenics, attDetRetContrArtEscenics));
						}
						
					}
					
					toRetenContribArtEscenics = new DRetenContribArtEscenicsTO(pkRetenContribArtEscenics, attRetenContribArtEscenics);
				}
			}
			
			boletasPrecioSuperio3Uvt = DCalculosLiquidacion.redondearAMiles(boletasPrecioSuperio3Uvt);
			totalOtrosDerechos = DCalculosLiquidacion.redondearAMiles(totalOtrosDerechos);
			Long valorRetencionContribucion = DCalculosLiquidacion.redondearAMiles((boletasPrecioSuperio3Uvt + totalOtrosDerechos)*0.1);
			devolucionRetenciones = DCalculosLiquidacion.redondearAMiles(devolucionRetenciones);
			Long valorNetoRetencion  = valorRetencionContribucion - devolucionRetenciones ;
			if(valorNetoRetencion < 0L) {
				valorNetoRetencion = 0L;
			}
			Long valorPagoSanciones = 0L; 
			Long totalSaldoPagar  = 0L;			
			//27
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_BOLETAS_PRECIO_SUPERIOR_3_UVT).setValor(boletasPrecioSuperio3Uvt);
			//28
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_TOTAL_OTROS_DERECHOS_SUPERIOR_3_UVT).setValor(totalOtrosDerechos);
			//29
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_TOTAL_BASE_RETENCION_CONTRIB_PARAFISCAL).setValor(boletasPrecioSuperio3Uvt + totalOtrosDerechos);
			//30
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_VALOR_RETENCION_CONTRIB_PARAFISCAL).setValor(valorRetencionContribucion);
			//31
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_DEVOLUCIONES_RETENCIONES_EXCESO).setValor(devolucionRetenciones);
			//33
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_VALOR_NETO_RETENCION_COTRIB_PARAFISCAL).setValor(valorNetoRetencion);
			//35
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CAS_NEG_TOTAL_SALDO_A_PAGAR).setValor(totalSaldoPagar);
			//37
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_VALOR_PAGO_SANCIONES).setValor(valorPagoSanciones);
			//39
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CONCEPTO_VALOR_PAGO_RETENCION_COTRIB_PARAFISCAL).setValor(valorNetoRetencion);
			//980
			ocurrenciaRetencion.getValCasillaNeg(IDConceptosNegocioFtosMinCultura.CASNEG_PAGO_TOTAL).setValor(valorNetoRetencion);
			
			LOGGER.info("El XML del documento es:");			
			LOGGER.info(DDocumentoUtil.obtenerXmlDeDocumento(documentoRetencion));	
			
			isOk = true;
		} catch (Exception ex) {
			try {
				DProcesamientoFlujoPosteriorHelper.finalizarErrorValidacionProcesoFlujoPosterior(documentoCargaPK.getIdeDocumento());
				ex.printStackTrace();
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

				toIdentificadorDoc = construirIdentificadorDoc(documentoRetencion.getIdFormato(),documentoRetencion.getVersionFormato(),null, IDModosDiligenciamiento.MODO_INICIAL,anio,nit,periodo);

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
							identificadorDoc = actualizarDocumento(documentoRetencion, IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RTE_CONTRIB_MINCULTURA, IDModosDiligenciamiento.MODO_INICIAL);
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
						identificadorDoc = salvarDocumento(documentoRetencion,IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RTE_CONTRIB_MINCULTURA,IDModosDiligenciamiento.MODO_INICIAL,false);
					}
				}

			}else{

				boolean continuar = false;
				DIdentificadorDocumentoTO toIdentificadorDocIni = construirIdentificadorDoc(documentoRetencion.getIdFormato(),documentoRetencion.getVersionFormato(),null,IDModosDiligenciamiento.MODO_INICIAL,anio,nit,periodo);
				DIdentificadorDoc identDocumentoInic = consultarIdentificadorDoc(toIdentificadorDocIni,true);
				// Si tiene inicial continua, sino marca error
				if (identDocumentoInic != null){
					DIdentificadorDocumentoTO toIdentificadorDoc = construirIdentificadorDoc(documentoRetencion.getIdFormato(),documentoRetencion.getVersionFormato(),null,IDModosDiligenciamiento.MODO_CORRECCION,anio,nit,periodo);
					identDocumento = consultarIdentificadorDoc(toIdentificadorDoc,true);
					
					if (identDocumento == null){// No tiene documentos de corrección presentados
						if (identDocumentoInic.getIdDocumento() == docCorreccion470) {// Compara número doc inicial con casilla documento anterior
							LOGGER.info("Numero declaración inicial para corregir: "+identDocumentoInic.getIdDocumento());
							continuar = true;
						}
					}else{
						if (identDocumento.getIdDocumento() == docCorreccion470) {// Compara número doc inicial con casilla documento anterior
							LOGGER.info("Numero declaración corrección para corregir: "+identDocumento.getIdDocumento());
							continuar = true;
						}
					}
					if (continuar){
						//Consulta si tiene borradores de corrección
						DIdentificadorDocumentoTO toIdentificadorDocBorr = construirIdentificadorDoc(documentoRetencion.getIdFormato(),documentoRetencion.getVersionFormato(),null,IDModosDiligenciamiento.MODO_CORRECCION,anio,nit,periodo);
						DIdentificadorDoc identDocumentoBorr = consultarIdentificadorDoc(toIdentificadorDocBorr,false);
						if (identDocumentoBorr != null){
							if(identDocumentoBorr.getIdEstado()==IDEstadosProcesamiento.IDE_ESTADO_TEMPORAL){
								documentoRetencion.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioAnexoRentaGC.CASNEG_NUM_FORMULARIO).setValor(identDocumentoBorr.getIdDocumento());
								identificadorDoc = actualizarDocumento(documentoRetencion, IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RTE_CONTRIB_MINCULTURA, IDModosDiligenciamiento.MODO_CORRECCION);
							}else if(identDocumentoBorr.getIdEstado()==IDEstadosProcesamiento.IDE_ESTADO_PROCESO_FIRMA){
								mensaje = "Se encontró una declaración en proceso de firma";
								finalizarProcesoFlujoPosterior(auditoriaRegistro, IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_EN_FIRMAS,mensaje);
								LOGGER.info(mensaje);
								isFinalizado = false;
								isOk = true;
								return;
							}
						}else{
							identificadorDoc = salvarDocumento(documentoRetencion,IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RTE_CONTRIB_MINCULTURA,IDModosDiligenciamiento.MODO_CORRECCION,false);
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
			
			
			LOGGER.info("*********** Se registra documento en modelo de negocio ***************");

			toRetenContribArtEscenics.getAtt().setIdeDocumentoDeclaracion(identificadorDoc.getIdDocumento());
			toRetenContribArtEscenics.getAtt().setNumRepeticionDeclaracion(identificadorDoc.getNumRepeticion());
			toRetenContribArtEscenics.getAtt().setIdeFormatoDeclaracion(identificadorDoc.getIdFormato());
			toRetenContribArtEscenics.getAtt().setNumVersionFormatoDecla((byte)identificadorDoc.getVersion());
			
			DCmdSrvCrearRetenContribArtEscenics srvCrearRetenContribArtEscenics = (DCmdSrvCrearRetenContribArtEscenics)
				getServicio("diligenciamientomasivo.mincultura.DCmdSrvCrearRetenContribArtEscenics");
			srvCrearRetenContribArtEscenics.inicializar(toRetenContribArtEscenics, lstDetRetContrArtEscenics);
			srvCrearRetenContribArtEscenics.ejecutar();


			LOGGER.info("*********** Se registra documento en solicitudes declaración ***************");
			
			DCmdSrvCrearSolicitudDeclaracion srvCrearSolicitudDeclaracion = (DCmdSrvCrearSolicitudDeclaracion) 
			getServicio("diligenciamientomasivo.solicitudesdeclaracion.DCmdSrvCrearSolicitudDeclaracion");
			
			toSolicitudDeclaracion.getAtt().setIdeDocumento(identificadorDoc.getIdDocumento());
			toSolicitudDeclaracion.getAtt().setNumRepeticion(identificadorDoc.getNumRepeticion());
		
			srvCrearSolicitudDeclaracion.inicializar(toSolicitudDeclaracion);
			srvCrearSolicitudDeclaracion.ejecutar();

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
	
	/*private Long retornarValorCasilla(Collection<DMapeoCasillaNegocioTO> casillasNegocio, Integer concepto, IDDocumento documentoAnexoRte){
		for(DMapeoCasillaNegocioTO mapeoCasilla: casillasNegocio){
			if(mapeoCasilla.getPK().getIdeConcepto().equals(concepto)) {
				
			}
		}
	}*/
	
	/**
	 * Comando de servicio para manejarlo de forma Transaccional
	 * @param nomSrv :: nombre del servicio
	 * @return DComandoServicio
	 * @throws DExcepcion
	 */
	private DComandoServicio getServicioTx(String nomSrv) throws DExcepcion {
		IDComando miComando;
		IDBusDelegateFactory fac = DBusDelegateFactory.getInstance();
		try{
			IDBusServiciosDelegate delegadoSrv = fac.getDelegadoServicios(nomSrv);
			delegadoSrv.setTransaccional(true);
			miComando = (IDComando) delegadoSrv.getComando(nomSrv);
			miComando.getContexto().setContextoSeguridad(getContexto().getContextoSeguridad());
		}catch (DExcepcion ex){
			ex.printStackTrace();
			throw ex;
		}catch (Exception ex){
			ex.printStackTrace();
			throw new DExcepcion(ex);
		}
		return (DComandoServicio) miComando;
	}
	
	
}
