package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.DTareaAttTO;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.DTareaPKTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioAttTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioRolPKTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioRolTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioTO;
import co.gov.dian.muisca.arquitectura.general.to.tablasbasicas.DOrganizacionPKTO;
import co.gov.dian.muisca.arquitectura.servicios.DCmdSrvCrearTarea;
import co.gov.dian.muisca.arquitectura.servicios.seguridad.DCmdSrvConsUsuarioRol;
import co.gov.dian.muisca.arquitectura.servicios.seguridad.DCmdSrvConsUsuarios;
import co.gov.dian.muisca.arquitectura.servicios.tablasbasicas.DCmdSrvConsCatalogoOrganizaciones;
import co.gov.dian.muisca.cargamasiva.constantes.IDEstadosCircuitoCargaMasiva;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DAuditoriaRegistroTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudArchivoTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoTO;
import co.gov.dian.muisca.cargamasiva.helper.DProcesamientoFlujoPosteriorHelper;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvActAuditoriaRegistro;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsAuditoriaRegistro;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsLstSolicitudArchivo;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsSolicitudIngreso;
import co.gov.dian.muisca.diligenciamiento.general.constantes.IDConstantesDiligenciamiento;
import co.gov.dian.muisca.diligenciamiento.general.helper.DObtenerDocValidoHelper;
import co.gov.dian.muisca.diligenciamiento.util.listas.DIdentificadoresDocComparator;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.preciostransferencia.DCmdAccWFGenerarDecInformativaConsolidadaIndividual;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConceptosNegocioAnexoRentaGC;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConceptosNegocioPreciosTransferencia;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConstantesDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConstantesEstadosProcesoFlujoPosterior;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracion120v10;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.DCmdSrvConsSolicitudDeclaracion;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.DCmdSrvCrearSolicitudDeclaracion;
import co.gov.dian.muisca.entradasalida.acciones.DCmdAccCrearDocumentoESTemporal;
import co.gov.dian.muisca.entradasalida.documento.DDocumentoUtil;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDCasilla;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDLogDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;
import co.gov.dian.muisca.entradasalida.documento.impl.pordefecto.DCasillaSimpleDoc;
import co.gov.dian.muisca.entradasalida.excepciones.DEntradaSalidaExcepcion;
import co.gov.dian.muisca.entradasalida.formatos.IDConstantesFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDModosDiligenciamiento;
import co.gov.dian.muisca.entradasalida.formatos.IDTiposOrigenDeclaracion;
import co.gov.dian.muisca.entradasalida.formatos.constantes.DTipoCorreccion;
import co.gov.dian.muisca.entradasalida.general.to.cargamasiva.DIdentificadorDocumentoTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.servicios.cargamasiva.DCmdSrvHomologacionPnuts;
import co.gov.dian.muisca.entradasalida.servicios.consintegral.DCmdSrvLlenarDocumentoConsRut;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoES;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoMUISCA;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsIdentificadorDoc;
import co.gov.dian.muisca.rut.general.to.DRegistroRutPKTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsRegistroRut;

public class DCmdAccWFGenerarDecInformativaConsolidadaIndividualImpl extends DCmdAccWFGenerarDecInformativaConsolidadaIndividual {

	final String TAG_ERROR = "e";

	protected void ejecutarComandoSinTransaccion() {

		LOGGER.info(" **************   Inicio WF Decl. Inf. Precios de Transferencia Individual  ********************* ");
		Integer numRegistrosProcesados = 0;
		Integer numRegistrosSol = 0; 

		try {
			documentoCargaPK = (DDocumentoPKTO)getDocumentos().iterator().next();
			DProcesamientoFlujoPosteriorHelper.iniciarProcesoFlujoPosterior(documentoCargaPK.getIdeDocumento());
			DSolicitudIngresoPKTO solicitudIngresoPK = new DSolicitudIngresoPKTO(documentoCargaPK.getIdeDocumento());
			DCmdSrvConsLstSolicitudArchivo srvConsLstConsArchivo = (DCmdSrvConsLstSolicitudArchivo) getServicio("cargamasiva.procesamiento.DCmdSrvConsLstSolicitudArchivo");
			srvConsLstConsArchivo.inicializarConsultarPorSolicitudIngreso(solicitudIngresoPK);
			LOGGER.info("Inicializado el servicio DCmdSrvConsLstSolicitudArchivo con solicitud de Ingreso:"	+ solicitudIngresoPK.getIdeSolicitud());
			srvConsLstConsArchivo.ejecutar();

			solicitud = consultarSolicitud(documentoCargaPK.getIdeDocumento());
			//this.idPersona = solicitud.getSolicitudAtt().getIdePersona();
			//this.idPersona = this.getContexto().getContextoSeguridad().getIdePersonaOrganizacion();
			this.anio = solicitud.getSolicitudAtt().getAnioVigencia();
			
			idPersona = obtenerIdePersonaOrg(solicitud.getSolicitudAtt().getIdeOrganizacion());

			if (idPersona == null) {
				idPersona = solicitud.getSolicitudAtt().getIdeUsuarioSolicitud();
			}
			//Se asignan los modos negocio con los que se va a trabajar en la declaración según sea el caso
			asignarModosNegocio();
			
			formato = consultarFormato(IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_PRECIOS_TRANSFERENCIA);
			
			//Se valida si la carga se realizo correctamente
			cargaValida = validarCarga(documentoCargaPK);
			
			if (cargaValida){
				
				// Crea documento 120 en blanco
				
				IDDocumento docTemporal = DDocumentoUtil.getDocumentoEnBlanco(formato);
				this.documentoPT = actualizarCabeceraPie(docTemporal, this.idPersona);

				Collection coleccionArchivosSol = srvConsLstConsArchivo.getColeccionSolicitudArchivo();
				DCmdSrvConsDocumentoES srvConsDocEs = (DCmdSrvConsDocumentoES) getServicio("entradasalida.documentos.DCmdSrvConsDocumentoES");
				Iterator iterColeccionArchivosSol = coleccionArchivosSol.iterator();
				LOGGER.info("Se va a iniciar el proceso de carga: cantidad de archivos en coleccion:" + coleccionArchivosSol.size());
				numRegistrosSol = coleccionArchivosSol.size();
				DAuditoriaRegistroTO toAudReg=new DAuditoriaRegistroTO();
				
				while (iterColeccionArchivosSol.hasNext()) {
					DSolicitudArchivoTO solicitudArchivoTO = (DSolicitudArchivoTO) iterColeccionArchivosSol.next();
					DCmdSrvConsAuditoriaRegistro consAuditoriaRegistro = (DCmdSrvConsAuditoriaRegistro)	getServicio("cargamasiva.procesamiento.DCmdSrvConsAuditoriaRegistro");
					consAuditoriaRegistro.inicializarConsultaPorIdeSolicitud(solicitudArchivoTO.getPK().getIdeSolicitud());				
					consAuditoriaRegistro.ejecutar();

					Iterator itAudReg = consAuditoriaRegistro.getColeccionAuditoriaRegistro().iterator();

					// Se itera cada 1125
					while (itAudReg.hasNext()){
						toAudReg = (DAuditoriaRegistroTO)itAudReg.next();
						srvConsDocEs.inicializarConsPorId(toAudReg.getPK().getIdeDocumento(), 1, 1125);
						srvConsDocEs.ejecutar();
						this.documentoCargadoSolic = srvConsDocEs.getDocumento();

						/**
						 *  Se aplican reglas de negocio
						 *  cas 28= Si C60 de F1125 = 01 a 29; sume el valor de las C62 del F1125
						 *  cas 29= Si C60 de F1125 = 30 a 58; sume el valor de las C62 del F1125
						 *  cas 30= Si C60 de F1125 = 59 o 60; sume el valor de la C62 del F1125
						 *  cas 31= Si C60 de F1125 = 61 a 63; sume el valor de la C62 del F1125
						 */
						if (documentoCargadoSolic != null){
							// Se dehomologa documento para obtener el valor de la cas 60.
							IDDocumento documento = dehomologar(documentoCargadoSolic);
							IDOcurrencia ocurrenciadocInformativo = documento.getGrupos().getGrupo(1).getOcurrencia(1);
							if (ocurrenciadocInformativo.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_TIPO_OPERACION) != null 
									&& ocurrenciadocInformativo.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_TIPO_OPERACION).getValorEntero() != null) {

								Integer valCas60 = Integer.valueOf(ocurrenciadocInformativo.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_TIPO_OPERACION).getValorEntero().intValue());
								Long valCas62 = 0L;
								Long valCas63 = 0L;

								if (ocurrenciadocInformativo.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_OPERACION) != null && 
										ocurrenciadocInformativo.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_OPERACION).getValorEntero() != null) {
									valCas62 = ocurrenciadocInformativo.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_OPERACION).getValorEntero(); 
								}
								if (ocurrenciadocInformativo.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_PRINCIPAL) != null && 
										ocurrenciadocInformativo.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_PRINCIPAL).getValorEntero() != null) {
									valCas63 = ocurrenciadocInformativo.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_PRINCIPAL).getValorEntero(); 
								}
								/**
								 * Poner la lógica en una política ??
								 */
								//casilla 28
								if (valCas60 >= 1 && valCas60 <= 29) {
									montoTotalOperacionIngreso += valCas62;
								}//casilla 29
								else if  (valCas60 >= 30 && valCas60 <= 58){ 
									montoTotalOperacionEgreso += valCas62;
								}//casilla 30
								else if  (valCas60 >= 59 && valCas60 <= 60){ 
									aporteSociedadesExtranjeras += valCas62;
								}//casilla 31
								else if  (valCas60 >= 61 && valCas60 <= 63){ 
									informacionAdicional += valCas62;
								}

								//casilla 32
								if (valCas60 == 42) {
									montoTotalPrincipalRec += valCas63;
								}//casilla 32
								else if (valCas60 == 13) {
									montoTotalPrincipalPres += valCas63;
								}

							}
						}else{
							// No encontró borrador declaración
						}
						auditoriaRegistro = toAudReg.getPK();
					}

					numRegistrosProcesados++;
					IDOcurrencia ocurrenciaPT = documentoPT.getGrupos().getGrupo(1).getOcurrencia(1);
					// Se asignan valores a las casillas del fto 120
					if (esFraccionAnio){
						ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_FRACCION_ANIO_GRAVABLE).setValor(true);
					}else{
						ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_FRACCION_ANIO_GRAVABLE).setValor(false);
					}
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_NUM_ITEMS_DECLARADOS).setValor(solicitud.getSolicitudAtt().getNumTotalRegistros());
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_OPERACIONES_INGRESO).setValor(montoTotalOperacionIngreso);//cas28
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_OPERACIONES_EGRESO).setValor(montoTotalOperacionEgreso);//cas29
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_APORTE_SOC_Y_ENTIDADES_EXTRANJERAS).setValor(aporteSociedadesExtranjeras);//cas30
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_INFORMACION_ADICIONAL).setValor(informacionAdicional);//cas31
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_DEL_PRINCIPAL_RECIBIDO).setValor(montoTotalPrincipalRec);//cas32
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_MONTO_TOTAL_DEL_PRINCIPAL_PRESTADO).setValor(montoTotalPrincipalPres);//cas33
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_BASE_LIQUIDACION).setValor("0");
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SANCIONES).setValor("0"); //Si es este número de solicitud ??
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SANCION_EXTEMPORANEIDAD).setValor("0"); 
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_PAGO_TOTAL).setValor("0");
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SOLICITUD_HOJA_2).setValor(documentoCargaPK.getIdeDocumento()); //Si es este número de solicitud ??

					LOGGER.info("El XML del documento es:");			
					LOGGER.info(DDocumentoUtil.obtenerXmlDeDocumento(documentoPT));	

					/**
					 * Genera TO Solicitud Declaracion
					 */
					DSolicitudDeclaracionAttTO attToSolicitudDeclaracion = new DSolicitudDeclaracionAttTO();
					DSolicitudDeclaracionPKTO pkSolicitudDeclaracion = new DSolicitudDeclaracionPKTO(documentoCargaPK.getIdeDocumento(),documentoCargaPK.getNumRepeticion());
					attToSolicitudDeclaracion.setCodEstado(IDDocumento.IDE_ESTADO_TEMPORAL);
					attToSolicitudDeclaracion.setIdeFormato(IDConstantesDiligenciamientoMasivo.FORMULARIO_PRECIOS_TRANSFERENCIA_INDIVIDUAL);
					attToSolicitudDeclaracion.setNumVersionFormato((byte)formato.getVersion());
					attToSolicitudDeclaracion.setIdePersona(idPersona);
					attToSolicitudDeclaracion.setFecCambio(new Timestamp(System.currentTimeMillis()));
					attToSolicitudDeclaracion.setIdeUsuarioCambio(solicitudArchivoTO.getAtt().getIdeUsuarioCambio());
					attToSolicitudDeclaracion.setIdeFormatoCarga(toAudReg.getAtt().getIdeFormato());
					attToSolicitudDeclaracion.setNumVersionFormatoCarga((byte)toAudReg.getAtt().getNumVersionFormato().byteValue());
					toSolicitudDeclaracion = new DSolicitudDeclaracionTO(pkSolicitudDeclaracion, attToSolicitudDeclaracion);


				}

				if (numRegistrosSol != numRegistrosProcesados) {
					String mensajeError = "Los registros para la solicitud No. " + solicitudIngresoPK.getIdeSolicitud() + " no coinciden con los procesados";
					try {
						DProcesamientoFlujoPosteriorHelper.finalizarErrorValidacionProcesoFlujoPosterior(documentoCargaPK.getIdeDocumento());
					} catch (DExcepcion e) {
						LOGGER.error(mensajeError, e);
						e.printStackTrace();
					}

					LOGGER.error(mensajeError);
					tieneErrorNoTransaccional = true;
					isOk=false;
					throw new DExcepcion("Error generando 120", mensajeError);
				}

				isOk=true;
			}
		}catch (DExcepcion ex) {
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

			if (tieneErrorNoTransaccional){
				getManejadorExpedientes().procesarInconsistencia(mensaje,documentoCargaPK.getIdeDocumento());
				notificarResponsable(solicitud, mensaje);
				isOk=false;
				LOGGER.info("*********** FIN CON ERROR "+ mensaje +" de Documento Precios de Transferencia  ***************");
				return;
			}
			
			if(!cargaValida){
				finalizarProcesoFlujoPosterior(marca);
				isFinalizado = true;
				isOk = true;
				LOGGER.info("*********** FIN CON ERROR  "+ mensaje +" de Documento Precios de Transferencia  ***************");
				return;
			}
			DIdentificadorDoc identDocCreado;
			if (esCorreccion){
				/**
				 * Se agrega al documento creado las casillas que se traen de la declaración anterior
				 */
				IDOcurrencia ocurrenciaPT = documentoPT.getGrupos().getGrupo(1).getOcurrencia(1);
				IDOcurrencia ocurrenciaDocAnt = identDocumentoAnterior.getGrupos().getGrupo(1).getOcurrencia(1);

				if (ocurrenciaDocAnt.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_RANGO_NEGATIVO_DEL_MARGEN).getValor() !=null){
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_RANGO_NEGATIVO_DEL_MARGEN)
					.setValorDecimal(ocurrenciaDocAnt.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_RANGO_NEGATIVO_DEL_MARGEN).getValorDecimal().doubleValue());
				}else{
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_RANGO_NEGATIVO_DEL_MARGEN)
					.setValorDecimal((double) 0L);
				}
				if (ocurrenciaDocAnt.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_RANGO_POSITIVO_DEL_MARGEN_O_PRECIO).getValor() != null){
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_RANGO_POSITIVO_DEL_MARGEN_O_PRECIO)
					.setValorDecimal(ocurrenciaDocAnt.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_RANGO_POSITIVO_DEL_MARGEN_O_PRECIO).getValorDecimal().doubleValue());
				}else{
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_RANGO_POSITIVO_DEL_MARGEN_O_PRECIO)
					.setValorDecimal((double) 0L);
				}
								
				ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_CODIGO_CORRECCION).setValor(DTipoCorreccion.DECLARACION_PRIVADA);
				ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_NUM_FORMULARIO_ANTERIOR)
				.setValorEntero(ocurrenciaDocAnt.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_NUM_FORMULARIO).getValorEntero());

				if (ocurrenciaDocAnt.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SANCIONES) != null	&& 
						ocurrenciaDocAnt.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SANCIONES).getValorEntero() != null) {
					ocurrenciaPT.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SANCIONES)
					.setValorEntero(ocurrenciaDocAnt.getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_SANCIONES).getValorEntero().longValue());
				} 
				LOGGER.info("Se va a guardar el documento 120 generado para la " +	"solicitud: " + documentoCargaPK.getIdeDocumento());
				identDocCreado = salvarDocumento(documentoPT, documentoPT.getIdFormato(), modoNegCorreccion);
			}else{
				identDocCreado = salvarDocumento(documentoPT, documentoPT.getIdFormato(), modoNegInicial);
			}
			LOGGER.info("Se creó el documento 120: "+identDocCreado.getIdDocumento()+" generado para la solicitud: "+ documentoCargaPK.getIdeDocumento());

			anexarDocumento(identDocCreado.getIdDocumento(),identDocCreado.getNumRepeticion(),identDocCreado.getIdFormato(),
					identDocCreado.getVersion(),identDocCreado.getIdEstado());

			LOGGER.info("*********** Se registra documento carga masiva ***************");

			crearSolicitudDeclaracion(identDocCreado.getIdDocumento(), identDocCreado.getNumRepeticion());

			LOGGER.info("*********** FIN Documento Precios de Transferencia  OK ***************");
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
	 * Consulta el concepto de la solicitud, si es inicial valida que no existe auna declaración inicial presentada de 120,sino genera error 
	 * si es corrección consulta que la solicitud que corrige fué exitosa y generó un documento 120 que fué presentado, sino consulta las anteriores cargas, 
	 * si no encuentra ninguna presentada con 120, genera error
	 * 
	 * @param documentoCargaPK
	 * @throws Exception
	 */
	private Boolean validarCarga(DDocumentoPKTO documentoCargaPK) throws Exception{

		/**
		 * Se consulta en tabla de solicitudes y se busca por una declaración presentada y que sea una carga inicial
		 */

		LOGGER.info("*********** Ingresa a validar carga ***************");
		boolean valida = true;
		
		DCmdSrvConsSolicitudDeclaracion consSolDecla = (DCmdSrvConsSolicitudDeclaracion) getServicio("diligenciamientomasivo.solicitudesdeclaracion.DCmdSrvConsSolicitudDeclaracion");
		DCmdSrvConsSolicitudIngreso srvSolIng = (DCmdSrvConsSolicitudIngreso) getServicio("cargamasiva.procesamiento.DCmdSrvConsSolicitudIngreso");

		// Se consulta solicitud
		srvSolIng.inicializar(new DSolicitudIngresoPKTO(documentoCargaPK.getIdeDocumento()));
		srvSolIng.ejecutar();
		DSolicitudIngresoTO solIngreso = srvSolIng.getSolicitudIngreso();	
		
		LOGGER.info(" solicitud ingreso :" +solIngreso.getSolicitudPK().getIdeSolicitud());
		int conceptoActual = solIngreso.getSolicitudAtt().getCodConcepto().intValue();
		
		if (conceptoActual == IDEstadosCircuitoCargaMasiva.CONCEPTO_INICIAL) {

			// Consulta si la solicitud que se está procesando ya generó documentos
			consSolDecla.inicializar(new DSolicitudDeclaracionPKTO(documentoCargaPK.getIdeDocumento(),documentoCargaPK.getNumRepeticion()));
			consSolDecla.ejecutar();

			DSolicitudDeclaracionTO docGeneradoCargaActual = consSolDecla.getSolicitudDeclaracion();

			if (docGeneradoCargaActual != null){// Debería quedar en 24?
				mensaje = "La solicitud actual, ya se procesó generando el documento consolidado de precios de transferencia";
				marca = IDEstadosCircuitoCargaMasiva.OK; // Canmbiar marca
				valida =false;
				return valida;
			}
			//Consulta NIT del idePersonaOrg
			
			consultarDatosRut(idPersona);	
			nit = registroRutTO.getAtt().getNumNit();
			// Consulta si existen declaraciones iniciales presentadas
			DIdentificadorDocumentoTO toIdentificadorDoc = construirIdentificadorDoc(formato.getId(),formato.getVersion(),null,modoNegInicial,anio,nit);
			DIdentificadorDoc identDocumento = consultarIdentificadorDoc(toIdentificadorDoc,true);
			if (identDocumento != null){
				mensaje = "Se encontró una declaración inicial presentada, debe presentar una carga de corrección"; 
				marca = IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_EXISTE_INICIAL; // Canmbiar marca
				valida =false;	
				return valida;
			}
				
		}else if (conceptoActual == IDEstadosCircuitoCargaMasiva.CONCEPTO_REEMPLAZO_TOTAL){

            LOGGER.info(" Concepto :" +conceptoActual);
            DSolicitudIngresoTO solIngresoAnt = null;

            while (solIngreso != null){
                            esCorreccion = true;

                            // Verificar concepto solicitud

                            int conceptoSolicitud = solIngreso.getSolicitudAtt().getCodConcepto().intValue();

                            if (conceptoSolicitud == IDEstadosCircuitoCargaMasiva.CONCEPTO_INICIAL){
                                            // Se consulta si la solicitud anterior tiene una declaración 120 generada y presentada
                                            consSolDecla.inicializar(new DSolicitudDeclaracionPKTO(solIngreso.getSolicitudPK().getIdeSolicitud(),1));
                                            consSolDecla.ejecutar();

                                            DSolicitudDeclaracionTO  declaracionTO = consSolDecla.getSolicitudDeclaracion();

                                            if (declaracionTO.getAtt().getCodEstado() == IDDocumento.IDE_ESTADO_PRESENTADO){
                                                           LOGGER.info(" Tiene una declaración 120 presentada, se consulta declaración");
                                                           //Consultar IDDocumento de documento anterior
                                                           identDocumentoAnterior = consultarDocMuisca(declaracionTO.getAtt().getIdeDocumento(), declaracionTO.getAtt().getNumRepeticion(), declaracionTO.getAtt().getIdeFormato());
                                                           valida = true;
                                                            return valida;
                                            }else{
                                                           LOGGER.info(" No se encontró una declaración inicial presentada, debe presentar una solicitud inicial");
                                                           marca = IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_NO_EXISTE_INICIAL_PARA_CORREGIR;
                                                           valida = false;
                                            }
                                            
                            }else{
                                            Long ideSolicitudAnterior = solIngreso.getSolicitudAtt().getNumeroDocumentoAnterior();

                                            LOGGER.info(" solicitud anterior :" +ideSolicitudAnterior);
                                            // Se consulta solicitud anterior
                                            if (ideSolicitudAnterior != null){
                                                           srvSolIng.inicializar(new DSolicitudIngresoPKTO(new Long(ideSolicitudAnterior)));
                                                           srvSolIng.ejecutar();
                                                           solIngresoAnt = srvSolIng.getSolicitudIngreso(); 

                                                           LOGGER.info(" Consulta solicitud anterior, estado solicitud anterior :" +solIngresoAnt.getSolicitudAtt().getCodEstado());

                                                           if ( solIngresoAnt.getSolicitudAtt().getCodEstado().equals(IDEstadosCircuitoCargaMasiva.OK.toString())) {
                                                                           LOGGER.info(" Estado exitoso, consulta si la solicitud tiene un 120 generada");
                                                                           // Se consulta si la solicitud anterior tiene una declaración 120 generada y presentada
                                                                           consSolDecla.inicializar(new DSolicitudDeclaracionPKTO(solIngresoAnt.getSolicitudPK().getIdeSolicitud(),1));
                                                                           consSolDecla.ejecutar();

                                                                           DSolicitudDeclaracionTO  declaracionTO = consSolDecla.getSolicitudDeclaracion();

                                                                           if (declaracionTO.getAtt().getCodEstado() == IDDocumento.IDE_ESTADO_PRESENTADO){
                                                                                           LOGGER.info(" Tiene una declaración 120 presentada, se consulta declaración");
                                                                                           //Consultar IDDocumento de documento anterior
                                                                                           identDocumentoAnterior = consultarDocMuisca(declaracionTO.getAtt().getIdeDocumento(), declaracionTO.getAtt().getNumRepeticion(), declaracionTO.getAtt().getIdeFormato());
                                                                                           valida = true;
                                                                                           return valida;
                                                                           }else{
                                                                                           // El documento generado con la solicitud no fué presentado 
                                                                                           LOGGER.info(" Tiene una declaración 120 pero esta no fué presentada, continúa con solicitud anterior");
                                                                                           //mensaje = "La declaración 120 "+ declaracionTO.getAtt().getIdeDocumento() +"generada con la solicitud:" + solIngresoAnt.getSolicitudPK().getIdeSolicitud() +"no fué presentada, consulta siguiente";
                                                                                           if (solIngresoAnt.getSolicitudAtt().getNumeroDocumentoAnterior() != null){
                                                                                                           srvSolIng.inicializar(new DSolicitudIngresoPKTO(new Long(solIngresoAnt.getSolicitudAtt().getNumeroDocumentoAnterior())));
                                                                                                           srvSolIng.ejecutar();
                                                                                                           solIngreso = srvSolIng.getSolicitudIngreso(); 
                                                                                           }else{
                                                                                                           solIngreso = null;
                                                                                           }
                                                                           }
                                                           }else if (solIngresoAnt.getSolicitudAtt().getNumeroDocumentoAnterior() != null){
                                                                           // Si no se presentó el documento 120 generado en esta solicitud, consulta la declaración que corrigió con esta carga 

                                                                           srvSolIng.inicializar(new DSolicitudIngresoPKTO(new Long(solIngresoAnt.getSolicitudAtt().getNumeroDocumentoAnterior())));
                                                                           srvSolIng.ejecutar();
                                                                           solIngreso = srvSolIng.getSolicitudIngreso(); 

                                                           }

                                            }else{
                                                           mensaje = "Se está reemplazando una solicitud no válida, por favor verificar";
                                                           marca = IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_NUM_DEC_RENTA_NO_ES_ULTIMA_VALIDA;
                                                           valida = false;
                                                           return valida;
                                            }
                            }
            }
            
            if ( solIngreso == null){

                            mensaje = "No Se encontró una declaración inicial presentada, debe presentar una solicitud inicial";
                            marca = IDConstantesEstadosProcesoFlujoPosterior.MARCA_ERROR_NO_EXISTE_INICIAL_PARA_CORREGIR;
                            valida = false;
            }


		}
		return valida;
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
	private DIdentificadorDoc salvarDocumento(IDDocumento doc, int ideFormato, Integer modo) throws DExcepcion {

		if (modo== null)
			modo = IDModosDiligenciamiento.MODO_INICIAL;

		DIdentificadorDoc identificadorDoc = new DIdentificadorDoc();
		DCmdAccCrearDocumentoESTemporal dCmdSrvCrearDocumentoES = (DCmdAccCrearDocumentoESTemporal) getAccion("entradasalida.DCmdAccCrearDocumentoESTemporal");
		String strDocXML = DDocumentoUtil.obtenerXmlDeDocumento(doc);

		dCmdSrvCrearDocumentoES.inicializarGuardarSinId(strDocXML, IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO, modo, ideFormato);

		dCmdSrvCrearDocumentoES.ejecutar();
		identificadorDoc = dCmdSrvCrearDocumentoES.getIdentificadorDoc();

		return identificadorDoc;
	}

	/**
	 * Consulta documento en el repositorio definitivo
	 * @param ideDocumento
	 * @param numRepeticion
	 * @param ideFormato
	 * @return
	 * @throws DExcepcion
	 */
	private IDDocumento consultarDocMuisca(Long ideDocumento, Integer numRepeticion, Integer ideFormato) throws DExcepcion{
		IDDocumento documento;
		DCmdSrvConsDocumentoMUISCA srvConsDoc = (DCmdSrvConsDocumentoMUISCA) getServicio("entradasalida.documentos.DCmdSrvConsDocumentoMUISCA");
		srvConsDoc.inicializar(ideDocumento, numRepeticion, ideFormato);
		srvConsDoc.ejecutar();
		return documento = srvConsDoc.getDocumento();

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
	 * Asigna modo negocio según casilla año de 1125
	 * @param 
	 */
	private void asignarModosNegocio(){
		//El 1125 no tiene casilla fracción de año por lo tanto hay que hacer comparación por año reportado vs año actual
		int annioActual = Calendar.getInstance().get(Calendar.YEAR);
		this.modoNegInicial = IDModosDiligenciamiento.MODO_INICIAL;
		this.modoNegCorreccion = IDModosDiligenciamiento.MODO_CORRECCION;
		if (anio == annioActual) {
			this.esFraccionAnio = true;
			LOGGER.info("Es fracción de año: " + this.esFraccionAnio);
			this.anio = this.anio - 1;
			this.modoNegInicial = IDModosDiligenciamiento.MODO_FRACCION_INICIAL;
			this.modoNegCorreccion = IDModosDiligenciamiento.MODO_FRACCION_CORRECCION;
		}
	}


	/**
	 * Actualiza datos de cabecera y pie, con los datos del RUT
	 * @param documento
	 * @param idePersona
	 * @return
	 * @throws Exception
	 */
	private IDDocumento actualizarCabeceraPie(IDDocumento documento, Long idePersona) throws Exception {

		int annioActual = Calendar.getInstance().get(Calendar.YEAR);
		if (anio == annioActual) {
			documento.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_ANNIO).setValor(new Integer(anio));
			documento.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_FRACCION_ANIO_GRAVABLE).setValorCadena("true");
		}
		else {
			documento.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CASNEG_ANNIO).setValor(new Integer(anio));
			documento.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1).getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT).getValCasillaNeg(IDConceptosNegocioPreciosTransferencia.CAS_NEG_FRACCION_ANIO_GRAVABLE).setValorCadena("false");
		}
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
	 * Consulta solicitud Ingreso
	 * @param idSolicitud
	 * @return
	 * @throws DExcepcion
	 */
	private DSolicitudIngresoTO consultarSolicitud(Long idSolicitud) throws	DExcepcion {
		DCmdSrvConsSolicitudIngreso srvConsSol = null;
		String nombreAcc = "cargamasiva.procesamiento.DCmdSrvConsSolicitudIngreso";
		srvConsSol = (DCmdSrvConsSolicitudIngreso) getServicio(nombreAcc);
		srvConsSol.inicializar(new DSolicitudIngresoPKTO(idSolicitud));
		srvConsSol.ejecutar();
		return srvConsSol.getSolicitudIngreso();
	}	

	/**
	 * Crea registro en dil_solicitudes_declaracion
	 * @param ideDoc
	 * @throws DExcepcion
	 */
	private void crearSolicitudDeclaracion(Long ideDocumento, Integer numRepeticion) throws DExcepcion {
		DCmdSrvCrearSolicitudDeclaracion  srvCrearSolicitudDeclaracion = (DCmdSrvCrearSolicitudDeclaracion) getServicio("diligenciamientomasivo.solicitudesdeclaracion.DCmdSrvCrearSolicitudDeclaracion");

		toSolicitudDeclaracion.getAtt().setIdeDocumento(ideDocumento);
		toSolicitudDeclaracion.getAtt().setNumRepeticion(numRepeticion);

		srvCrearSolicitudDeclaracion.inicializar(toSolicitudDeclaracion);
		srvCrearSolicitudDeclaracion.ejecutar();

	}

	/**
	 * Finaliza el procesamiento de una solicitud asignando un estado determinado.
	 * 
	 * @param ideSolicitud Dato de tipo <code>Long</code> con el número de la solicitud en proceso.
	 * @param estadoProceso Dato de tipo <code>Integer</code> con el estado a asignar.
	 * @throws Exception 
	 */
	public void finalizarProcesoFlujoPosterior(Integer marcaProceso) throws Exception{

		DAuditoriaRegistroTO toAudReg=new DAuditoriaRegistroTO();
		
		DProcesamientoFlujoPosteriorHelper.finalizarErrorValidacionProcesoFlujoPosterior(documentoCargaPK.getIdeDocumento());
		
		DCmdSrvConsAuditoriaRegistro consAuditoriaRegistro = (DCmdSrvConsAuditoriaRegistro)	getServicio("cargamasiva.procesamiento.DCmdSrvConsAuditoriaRegistro");
		DCmdSrvConsAuditoriaRegistro consAuditoriaReg = (DCmdSrvConsAuditoriaRegistro) getServicio("cargamasiva.procesamiento.DCmdSrvConsAuditoriaRegistro");
		consAuditoriaRegistro.inicializarConsultaPorIdeSolicitud(documentoCargaPK.getIdeDocumento());				
		consAuditoriaRegistro.ejecutar();

		Iterator itAudReg = consAuditoriaRegistro.getColeccionAuditoriaRegistro().iterator();
		
		
		// Se itera cada 1125
		while (itAudReg.hasNext()){
			toAudReg = (DAuditoriaRegistroTO)itAudReg.next();
			
			consAuditoriaReg.inicializar(toAudReg.getPK());
			consAuditoriaReg.ejecutar();
			DAuditoriaRegistroTO auditoriaTO= consAuditoriaReg.getAuditoriaRegistro();

			String desError =  auditoriaTO.getAtt().getDesError();
			StringBuffer sbuffer = new StringBuffer(desError);
			StringBuffer Encabezado = new StringBuffer("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\n" +
					"<led xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"log_estados_documento.xsd\"");
			Encabezado.append(" id_d=\"").append(auditoriaTO.getPK().getIdeDocumento()).append("\"n_r=\"1\" cf=\"1125\" vf=\"").append(auditoriaTO.getAtt().getNumVersionFormato()).append("\">\n");
			//" id_d=\"17323000000413\" n_r=\"1\" cf=\"1125\" vf=\"3\">");

			if (desError == null){

				sbuffer = Encabezado;
				sbuffer.append( "<" ).append( TAG_ERROR ).append( " " );
				sbuffer.append( "id_ef=\"").append(marcaProceso).append("\" id_g=\"1\" id_c=\"4\" n_o=\"1\" act=\"n\" mensaje=\"").append(mensaje).append("\" />" ).append("\n");
				sbuffer.append( "</led>" );

			}else if (!sbuffer.toString().endsWith("</led>")){

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

		}
		
	}

	/** SECCION DE GEX, EVALUAR SI SE SACA EN UN HELPER
	 * 
	 * @return
	 */
	private DHelperExpedienteGeneracion120v10 getManejadorExpedientes() {
		//Helpers
		DHelperExpedienteGeneracion120v10 manejadorExpedientes = null;

		if (manejadorExpedientes == null) {
			manejadorExpedientes = new DHelperExpedienteGeneracion120v10(this);
		}
		return manejadorExpedientes;
	}

	/**
	 * 
	 * @param miSolicitud
	 * @param msjNotificacion
	 * @return
	 * @throws DExcepcion
	 */
	private void notificarResponsable(DSolicitudIngresoTO miSolicitud,
			String msjNotificacion) throws DExcepcion {

		LOGGER.info("Notificando responsable de la tarea");

		String urlTarea = "";

		if (esCorreccion) {
			// Anexar el numero de solicitud al mensaje
			msjNotificacion += Long.toString(documentoPT.getId());
		}
		msjNotificacion += " generada por la Solicitud de Carga " +
				documentoCargaPK.getIdeDocumento();

		LOGGER.info("msjNotificacion: "+msjNotificacion);

		// Consultar el usuario con el id de la persona remitente
		Long idPersona = miSolicitud.getSolicitudAtt().getIdePersona();
		Long idTarea = null;
		DUsuarioTO usr = new DUsuarioTO();
		DUsuarioAttTO att = new DUsuarioAttTO();
		att.setIdePersonaRut(idPersona);

		usr.setUsuarioAtt(att);
		LOGGER.info("Consulta usuario con el idePersonaRemitente: "+att.getIdeOrganizacion());
		DCmdSrvConsUsuarios srvConsUsuarios = (DCmdSrvConsUsuarios) 
				getServicio("arquitectura.seguridad.DCmdSrvConsUsuarios");
		srvConsUsuarios.iniciar(usr);
		srvConsUsuarios.ejecutar();
		Long idUsuario = srvConsUsuarios.getUsuarioTO().getUsuarioPK().getId();
		LOGGER.info("Consulta usuario con el idePersonaRemitente: "+att.getIdeOrganizacion()+" -> Ideusuario: "+idUsuario);

		// Consultar el usuario y rol para enviar la tarea
		Integer idOrg = miSolicitud.getSolicitudAtt().getIdeOrganizacion();
		DCmdSrvConsUsuarioRol srvUsuario = (DCmdSrvConsUsuarioRol) getServicio("arquitectura.seguridad.DCmdSrvConsUsuarioRol");
		DUsuarioRolPKTO pkUsuario = new DUsuarioRolPKTO();
		pkUsuario.setIdeUsuario(idUsuario);
		pkUsuario.setIdeOrganizacion(idOrg);
		srvUsuario.inicializarConsultaGenericaPorPK(pkUsuario);
		srvUsuario.ejecutar();
		LOGGER.info("Consulta rol.....");
		Collection usuarios = srvUsuario.getRolesUsuario();
		LOGGER.info("Roles para el usuario "+idUsuario+": "+usuarios.size());
		if (usuarios != null && !usuarios.isEmpty()) {
			DUsuarioRolTO usuario = (DUsuarioRolTO) usuarios.iterator().next();
			pkUsuario = usuario.getUsuarioRolPK();
			LOGGER.info("Rol: "+usuario.getUsuarioRolPK().getIdeRol());
			// Enviar mensaje de notificacion a la bandeja de tareas
			DCmdSrvCrearTarea srvTarea = (DCmdSrvCrearTarea) 
					getServicio("arquitectura.DCmdSrvCrearTarea");
			DTareaAttTO tarea = new DTareaAttTO(idUsuario,idOrg, pkUsuario.getIdeLugar(),
					pkUsuario.getIdeTipoOrganizacion(),	pkUsuario.getIdeUnidadAdministrativa(),
					pkUsuario.getIdeRol(),new Integer(IDConstantesDiligenciamientoMasivo.PARAM_IDE_TAREA),
					new Character('A'),	msjNotificacion,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),
					new Date(System.currentTimeMillis()),urlTarea);
			tarea.setIdeFlujo(getContextoWF().getIdFlujo());
			tarea.setIdeActividad(getContextoWF().getIdActividad());
			//Collection parametros = construirParametros();
			srvTarea.inicializar(tarea);
			LOGGER.info("Crear la tarea: ");
			LOGGER.info("idUsuario: "+idUsuario);
			LOGGER.info("idOrg: "+idOrg);
			LOGGER.info("PARAM_IDE_TAREA: "+IDConstantesDiligenciamientoMasivo.PARAM_IDE_TAREA);
			LOGGER.info("msjNotificacion: "+msjNotificacion);
			srvTarea.ejecutar();
			DTareaPKTO tareaPK = srvTarea.getTareaPK();
			idTarea = tareaPK.getIdeTarea();
			LOGGER.info("Se obtuvo el ideTarea: "+idTarea);
		}
	}

	/**
	 * 
	 * @param documento
	 * @return
	 * @throws DExcepcion
	 */
	private IDDocumento dehomologar(IDDocumento documento) throws DExcepcion {
		DCmdSrvHomologacionPnuts srvHomologarPnuts = (DCmdSrvHomologacionPnuts)
				this.getServicio("entradasalida.cargamasiva.DCmdSrvHomologacionPnuts");

		srvHomologarPnuts.inicializarDehomologar(documento);
		srvHomologarPnuts.ejecutar();
		IDLogDocumento logDocumento = srvHomologarPnuts.getLogErroresDoc();

		if (logDocumento.getConteo() > 0) {
			String mensaje = "El Documento " + documento.getId() + " con repetición " + documento.getRepeticion() + " no se ha podido ";
			mensaje += " dehomologar correctamente.";
			throw new DEntradaSalidaExcepcion(mensaje, logDocumento.getXml());
		}

		IDDocumento docHomologado = srvHomologarPnuts.getDocumentoHomologado();
		return docHomologado;
	}
	
	/**
	 * 
	 * @param ideOrganizacion
	 * @return
	 * @throws DExcepcion
	 */
	private Long obtenerIdePersonaOrg(Integer ideOrganizacion) throws DExcepcion {
		String nomSrv = "arquitectura.tablasbasicas.DCmdSrvConsCatalogoOrganizaciones";
		DCmdSrvConsCatalogoOrganizaciones catalogoOrg = null;
		catalogoOrg = (DCmdSrvConsCatalogoOrganizaciones) getServicio(nomSrv);
		catalogoOrg.inicializarConsultaPorCodigo(new DOrganizacionPKTO(ideOrganizacion));
		catalogoOrg.ejecutar();
		return catalogoOrg.getOrganizacion().getOrganizacionAtt().getIdePersona();

	}


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
	 * Consulta de rut por idePersona
	 * @param idePersona
	 * @return
	 * @throws Exception
	 * @throws DExcepcion
	 */
    private void consultarDatosRut(Long idePersona) throws Exception, DExcepcion{
    	DCmdSrvConsRegistroRut consAccRegRut = (DCmdSrvConsRegistroRut) getServicio("rut.DCmdSrvConsRegistroRut");
    	consAccRegRut.inicializarConsultarPorPK(new DRegistroRutPKTO(this.idPersona));
    	consAccRegRut.ejecutar();
        registroRutTO = consAccRegRut.getRegistroRut();
    }
      
	/*
	private Collection construirParametros() {

		LOGGER.info("Inicio Construcción de Parametros para la tarea");

		Double idDocGen = new Double( -1);
		Double repDocGen = new Double( -1);
		Double idDocCarga = new Double( -1);

		Collection parametros = new ArrayList();

		DParametroTareaPKTO paramPK = new DParametroTareaPKTO(null, null);
		DParametroTareaAttTO paramAtt = new DParametroTareaAttTO(NOM_PARAM_ID_DOCUMENTO_DECLARACION, new Character('N'),	new BigDecimal(idDocGen.longValue()), null, null);
		paramAtt.setIdeParametroTareaDef(new Short((short)PARAM_TAREA_IDE_DOCUMENTO));
		paramAtt.setIdeTareaDef(new Integer(PARAM_IDE_TAREA));
		DParametroTareaTO param = new DParametroTareaTO(paramPK, paramAtt);
		parametros.add(param);
		logger.info("Parametro 1:\n\t"+param.getParametroTareaAtt().getNomParametro()+": "+idDocGen.longValue()+"\nAdicionado a Parametros");

		paramPK = new DParametroTareaPKTO(null, null);
		paramAtt = new DParametroTareaAttTO(NOM_PARAM_NUM_REPETICION_DECLARACION,new Character('N'),new BigDecimal(repDocGen.intValue()), null, null);
		// Cambiar por el tipo de tarea que corresponda
		paramAtt.setIdeParametroTareaDef(new Short((short)PARAM_TAREA_NUM_REP_DOCUMENTO));
		paramAtt.setIdeTareaDef(new Integer(PARAM_IDE_TAREA));
		// FIN Cambiar por el tipo de tarea que corresponda
		param = new DParametroTareaTO(paramPK, paramAtt);
		parametros.add(param);
		logger.info("Parametro 2:\n\t"+param.getParametroTareaAtt().getNomParametro()+": "+repDocGen.intValue()+"\nAdicionado a Parametros");


		paramPK = new DParametroTareaPKTO(null, null);
		paramAtt = new DParametroTareaAttTO(NOM_PARAM_NUM_ID_DOCUMENTO_SOLICITUD,new Character('N'),
				new BigDecimal(idDocCarga.longValue()), null, null);
		// Cambiar por el tipo de tarea que corresponda
		paramAtt.setIdeParametroTareaDef(new Short((short)PARAM_TAREA_IDE_DOCUMENTO_SOLICITUD));
		paramAtt.setIdeTareaDef(new Integer(PARAM_IDE_TAREA));
		// FIN Cambiar por el tipo de tarea que corresponda
		param = new DParametroTareaTO(paramPK, paramAtt);
		parametros.add(param);
		logger.info("Parametro 3:\n\t"+param.getParametroTareaAtt().getNomParametro()+": "+idDocCarga.longValue()+"\nAdicionado a Parametros");
		paramPK = new DParametroTareaPKTO(null, null);
		paramAtt = new DParametroTareaAttTO(NOM_PARAM_ES_CORRECCION,new Character('C'),null, (idDocumentoCorreccion != 0 ? "si" : "no"), null);
		// Cambiar por el tipo de tarea que corresponda
		paramAtt.setIdeParametroTareaDef(new Short((short)PARAM_TAREA_ES_CORRECCION));
		paramAtt.setIdeTareaDef(new Integer(PARAM_IDE_TAREA));
		// FIN Cambiar por el tipo de tarea que corresponda
		param = new DParametroTareaTO(paramPK, paramAtt);
		parametros.add(param);
		logger.info("Parametro 4:\n\t"+param.getParametroTareaAtt().getNomParametro()+": "+(idDocumentoCorreccion != 0 ? "si" : "no")+"\nAdicionado a Parametros");

		logger.info("Retornando parametros!");

		return parametros;
	}*/

}
