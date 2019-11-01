package co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.juegosdesuerteyazar.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import co.gov.dian.muisca.arquitectura.general.constantes.IDTareasNeg;
import co.gov.dian.muisca.arquitectura.general.constantes.TipoDocumentos;
import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.formateador.implgenerica.DFechaHora;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioAttTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioRolAttTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioRolPKTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioRolTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DParamTareaNegAttTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DParamTareaNegPKTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DParamTareaNegTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaNegAttTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaNegPKTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaNegTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaPersonaNegAttTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaPersonaNegPKTO;
import co.gov.dian.muisca.arquitectura.general.to.tareas.DTareaPersonaNegTO;
import co.gov.dian.muisca.arquitectura.helper.DTareasNegHelper;
import co.gov.dian.muisca.arquitectura.servicios.seguridad.DCmdSrvConsUsuarioRol;
import co.gov.dian.muisca.arquitectura.servicios.seguridad.DCmdSrvConsUsuarios;
import co.gov.dian.muisca.arquitectura.util.DFechaUtils;
import co.gov.dian.muisca.arquitectura.util.DUsuariosSistema;
import co.gov.dian.muisca.arquitectura.web.buses.DContextoSeguridad;
import co.gov.dian.muisca.cargamasiva.general.to.DConfiguracionCargaMasivaPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.DConfiguracionCargaMasivaTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudArchivoTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoTO;
import co.gov.dian.muisca.cargamasiva.helper.DProcesamientoFlujoPosteriorHelper;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsLstSolicitudArchivo;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsSolicitudIngreso;
import co.gov.dian.muisca.cargamasiva.acciones.DCmdAccConsConfiguracionCargaMasiva;
import co.gov.dian.muisca.diligenciamiento.general.constantes.IDConceptosNegocioDil;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.wf.juegosdesuerteyazar.DCmdAccWFDeclaracionJuegosSuerteAzar;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConceptosNegocioJuegosSuerteYAzar;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConstantesCircuitoJuegosSuerteyAzar;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConstantesDocTareaJuegosSuerteYAzar;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.DJuegoSuerteAzarAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.DJuegoSuerteAzarPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.DJuegoSuerteAzarTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.juegosdesuerteyazar.DCmdSrvCrearJuegoSuerteAzar;
import co.gov.dian.muisca.entradasalida.acciones.DCmdAccCrearDocumentoESTemporal;
import co.gov.dian.muisca.entradasalida.acciones.consintegral.DCmdAccLlenarDocumentoNuevoConsRut;
import co.gov.dian.muisca.entradasalida.documento.DDocumentoUtil;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;
import co.gov.dian.muisca.entradasalida.formatos.IDFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDModosDiligenciamiento;
import co.gov.dian.muisca.entradasalida.formatos.IDTiposOrigenDeclaracion;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.liquidacion.impl.DCalculosLiquidacion;
import co.gov.dian.muisca.entradasalida.servicios.DCmdSrvConsFormato;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoMUISCA;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentosES;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvCrearDocumentoES;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvMoverDocumentoES;
import co.gov.dian.muisca.gestionexpediente.general.constantes.IDConstantesEventos;
import co.gov.dian.muisca.gestionexpediente.general.helper.DCrearEventosHelper;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DDocEventoAttTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DDocEventoPKTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DDocEventoTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DEventoPKTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DParametroEventoAttTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DParametroEventoPKTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DParametroEventoTO;
import co.gov.dian.muisca.gestionexpediente.general.to.evento.DPersonaEventoTO;
import co.gov.dian.muisca.gestionexpediente.general.to.expediente.DConsultaExpedienteTO;
import co.gov.dian.muisca.gestionexpediente.general.to.expediente.DExpedienteTO;
import co.gov.dian.muisca.rut.acciones.liquidacion.DCmdAccConsPlazosPresentaDecla;
import co.gov.dian.muisca.rut.buses.DBusServiciosDelegateRUT;
import co.gov.dian.muisca.rut.constantes.IDTipoDeclaranteRUT;
import co.gov.dian.muisca.rut.general.to.DRegistroRutTO;
import co.gov.dian.muisca.rut.general.to.inteligenciacorporativa.DPlazosPresentaDeclaPKTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsRegistroRut;

public class DCmdAccWFDeclaracionJuegosSuerteAzarImpl extends
DCmdAccWFDeclaracionJuegosSuerteAzar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8285112182381157610L;

	private static final int AMPLITUD_INTERVALO = 100;
	private static final int NUM_FORMATO = 320;
	private static final int VERSION_FORMATO = 1;
	

	protected void ejecutarComandoSinTransaccion() {
		int registroActual = 1;
		try {
			LOGGER.info("*********** INICIO Circuito Documento ETESA ***************");
			documentoCargaPK = (DDocumentoPKTO)getDocumentos().iterator().next();
			DProcesamientoFlujoPosteriorHelper.iniciarProcesoFlujoPosterior(documentoCargaPK.getIdeDocumento());
			DSolicitudIngresoPKTO solicitudIngresoPK = new DSolicitudIngresoPKTO(documentoCargaPK.getIdeDocumento());			
			DCmdSrvConsLstSolicitudArchivo srvConsLstConsArchivo = (DCmdSrvConsLstSolicitudArchivo) getServicio("cargamasiva.procesamiento.DCmdSrvConsLstSolicitudArchivo");
			srvConsLstConsArchivo.inicializarConsultarPorSolicitudIngreso(solicitudIngresoPK);
			LOGGER.info("Inicializado el servicio DCmdSrvConsLstSolicitudArchivo con solicitud de Ingreso:"
					+ solicitudIngresoPK.getIdeSolicitud());
			srvConsLstConsArchivo.ejecutar();

			Collection coleccionArchivosSol = srvConsLstConsArchivo.getColeccionSolicitudArchivo();

			DCmdSrvConsDocumentosES srvConsDoc = (DCmdSrvConsDocumentosES) 
				this.getServicio("entradasalida.documentos.DCmdSrvConsDocumentosES");

			Iterator iterColeccionArchivosSol = coleccionArchivosSol.iterator();
			LOGGER.info("Se va a iniciar el proceso de carga: cantidad de archivos en coleccion:" + coleccionArchivosSol.size());
			while (iterColeccionArchivosSol.hasNext()) {
				registroActual = 1;
				Double porcentaje = new Double(1);
				int extremoSuperiorIntervalo;
				DSolicitudArchivoTO solictidArchivoTO = (DSolicitudArchivoTO) iterColeccionArchivosSol.next();
				Long ideRecursoArchivo = solictidArchivoTO.getPK().getIdeRecursoArchivo();

				srvConsDoc.inicializar(documentoCargaPK.getIdeDocumento(), ideRecursoArchivo, registroActual, AMPLITUD_INTERVALO);
				srvConsDoc.ejecutar();
				documentosCargados = srvConsDoc.getDocumentos();

				if (documentosCargados == null) {
					LOGGER.info("documentosCargados es nulo");
				} else {
					LOGGER.info("Num documentos cargados:" + documentosCargados.size());
				}

				while (documentosCargados != null
						&& documentosCargados.size() > 0) {
					extremoSuperiorIntervalo = registroActual
					+ AMPLITUD_INTERVALO - 1;
					LOGGER.info("Procesando solicitud: " + documentoCargaPK.getIdeDocumento() + ", recurso archivo " + ideRecursoArchivo
							+ ", items: " + registroActual + " - "+ extremoSuperiorIntervalo);

					for (Iterator iter = documentosCargados.iterator(); iter.hasNext();) {

						IDDocumento itemDocumento = (IDDocumento) iter.next();
						IDOcurrencia ocurrencia = itemDocumento.getGrupos().getGrupo(1).getOcurrencia(1);
						/**
						 * Se obtiene la modalidad del documento y con base a la
						 * modalidad se van totalizando las casillas
						 */
						int modalidad = ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_CODIGO_MODALIDAD).getInt();
						
						Long baseLiquidacion = ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_BASE_LIQUIDACION).getValorEntero().longValue();
						Long derExplotacionPorItem = ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_TOTAL_DERECHOS_EXPLOTACION_ITEM).getValorEntero()
						.longValue();
						Object valorCasIva = ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_VALOR_IVA).getValor();
						Long valorIva = (valorCasIva!=null)?(Long)valorCasIva:0;

						Long calculoModalidad3 = baseLiquidacion;
						
						switch (modalidad) {
						case IDConstantesCircuitoJuegosSuerteyAzar.LOCALIZADOS:
						
							if (baseLiquidacion != null) {
								totalBaseLiquidacionMod1 = totalBaseLiquidacionMod1
								+ derExplotacionPorItem;
							}

							if (baseLiquidacion != null) {
								totalDerExplotacionPorItem1 = totalDerExplotacionPorItem1
								+ derExplotacionPorItem;
							}
							break;
						case IDConstantesCircuitoJuegosSuerteyAzar.NOVEDOSOS:
							if (baseLiquidacion != null) {
								totalBaseLiquidacionMod2 = totalBaseLiquidacionMod2
								+ baseLiquidacion;
							}
							if (baseLiquidacion != null) {
								totalDerExplotacionPorItem2 = totalDerExplotacionPorItem2
								+ derExplotacionPorItem;
							}
							break;
						case IDConstantesCircuitoJuegosSuerteyAzar.PROMOCIONALES:
							if (calculoModalidad3 != null) {
								totalBaseLiquidacionMod3 = totalBaseLiquidacionMod3
								+ calculoModalidad3;
							}
							if (baseLiquidacion != null) {
								totalDerExplotacionPorItem3 = totalDerExplotacionPorItem3
								+ derExplotacionPorItem;
							}
							break;
						case IDConstantesCircuitoJuegosSuerteyAzar.RIFAS:
							if (baseLiquidacion != null) {
								totalBaseLiquidacionMod4 = totalBaseLiquidacionMod4
								+ baseLiquidacion;
							}
							if (baseLiquidacion != null) {
								totalDerExplotacionPorItem4 = totalDerExplotacionPorItem4
								+ derExplotacionPorItem;
							}
							break;
						case IDConstantesCircuitoJuegosSuerteyAzar.APUESTAS:
							if (baseLiquidacion != null) {
								totalBaseLiquidacionMod5 = totalBaseLiquidacionMod5
								+ baseLiquidacion;
							}
							if (baseLiquidacion != null) {
								totalDerExplotacionPorItem5 = totalDerExplotacionPorItem5
								+ derExplotacionPorItem;
							}
							break;
						}
						this.anio = ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CASNEG_ANNIO).getValorEntero().intValue();
						this.periodo = ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CASNEG_PERIODO).getValorEntero().intValue();
						nit = new Long(ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CASNEG_NUM_IDENTIFICACION).getValorEntero().longValue());
						dv = new Long(ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CASNEG_DV_NIT).getValorEntero().longValue());
						calculoModalidad3 = 0L;
					}

					registroActual = registroActual + AMPLITUD_INTERVALO;

					srvConsDoc.inicializar(documentoCargaPK.getIdeDocumento(),ideRecursoArchivo, registroActual, AMPLITUD_INTERVALO);
					srvConsDoc.ejecutar();
					documentosCargados = srvConsDoc.getDocumentos();

					totalBaseLiquidacionMod1 = DCalculosLiquidacion.redondearAMiles(totalBaseLiquidacionMod1);
					totalBaseLiquidacionMod2 = DCalculosLiquidacion.redondearAMiles(totalBaseLiquidacionMod2);
					totalBaseLiquidacionMod3 = DCalculosLiquidacion.redondearAMiles(totalBaseLiquidacionMod3);
					totalBaseLiquidacionMod4 = DCalculosLiquidacion.redondearAMiles(totalBaseLiquidacionMod4);
					totalBaseLiquidacionMod5 = DCalculosLiquidacion.redondearAMiles(totalBaseLiquidacionMod5);
					
					totalBaseLiqJuegos = totalBaseLiquidacionMod1+totalBaseLiquidacionMod2
					+totalBaseLiquidacionMod3+totalBaseLiquidacionMod4+totalBaseLiquidacionMod5;
					
					totalBaseLiqJuegos = DCalculosLiquidacion.redondearAMiles(totalBaseLiqJuegos);

					totalDerExplotacionPorItem1 = DCalculosLiquidacion.redondearAMiles(totalDerExplotacionPorItem1);
					totalDerExplotacionPorItem2 = DCalculosLiquidacion.redondearAMiles(totalDerExplotacionPorItem2);
					totalDerExplotacionPorItem3 = DCalculosLiquidacion.redondearAMiles(totalDerExplotacionPorItem3);
					totalDerExplotacionPorItem4 = DCalculosLiquidacion.redondearAMiles(totalDerExplotacionPorItem4);
					totalDerExplotacionPorItem5 = DCalculosLiquidacion.redondearAMiles(totalDerExplotacionPorItem5);
					
					totalDerExplotacion = totalDerExplotacionPorItem1+totalDerExplotacionPorItem2
					+totalDerExplotacionPorItem3+totalDerExplotacionPorItem4+totalDerExplotacionPorItem5;
					
					totalDerExplotacion = DCalculosLiquidacion.redondearAMiles(totalDerExplotacion);
					
					gastosAdmon =  (new Double (totalDerExplotacion) * porcentaje)/100;

					gastosAdmon = DCalculosLiquidacion.redondearAMiles(gastosAdmon);

					totalPagarPeriodo = gastosAdmon + totalDerExplotacion;
					
					totalPagarPeriodo = DCalculosLiquidacion.redondearAMiles(totalPagarPeriodo);
					
				}
			}
			LOGGER.info("Termino de consolidar la solicitud: "
					+ documentoCargaPK.getIdeDocumento());

			solicitud = consultarSolicitud(documentoCargaPK.getIdeDocumento());
			num_item = solicitud.getSolicitudAtt().getNumTotalRegistros().longValue();
			LOGGER.debug("nit a consultar: " + nit);
			idPersona = consultarIdPersonaRut(nit.longValue());
			LOGGER.debug("idPersona: " + idPersona);

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
			isOk = false;
		}
		isOk = true;
	}

	protected void ejecutarComandoWF() {
		try{
			if (tieneErrorNoTransaccional){
				isOk=false;
				return;
			}
			documento = crearDocumento(idPersona.longValue());
			IDOcurrencia ocurrencia = documento.getGrupos().getGrupo(1).getOcurrencia(1);

			//Casillas de cabecera
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CASNEG_ANNIO).setValor(anio);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CASNEG_PERIODO).setValor(periodo);
			
			//se adicionan las casillas de totales
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_BASE_LIQ_JUEGOS_LOCALIZADOS).setValor(totalBaseLiquidacionMod1);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_BASE_LIQ_JUEGOS_NOVEDOSOS).setValor(totalBaseLiquidacionMod2);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_BASE_LIQ_JUEGOS_PROMOCIONALES).setValor(totalBaseLiquidacionMod3);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_BASE_RIFAS).setValor(totalBaseLiquidacionMod4);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_BASE_EVENTOS_GALLISTICOS_CANINOS).setValor(totalBaseLiquidacionMod5);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_BASE_TOTAL_JUEGOS_SUERTE_AZAR).setValor(totalBaseLiqJuegos);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_DER_EXPLOTACION_JUEGOS_LOCALIZADOS).setValor(totalDerExplotacionPorItem1);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_DER_EXPLOTACION_JUEGOS_NOVEDOSOS).setValor(totalDerExplotacionPorItem2);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_DER_EXPLOTACION_JUEGOS_PROMOCIONALES).setValor(totalDerExplotacionPorItem3);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_DER_EXPLOTACION_RIFAS).setValor(totalDerExplotacionPorItem4);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_DER_EXPLOTACION_GALLISTICOS_CANINOS).setValor(totalDerExplotacionPorItem5);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_TOTAL_DER_EXPLOTACION_PERIODO).setValor(totalDerExplotacion);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_GASTOS_ADMINISTRACION).setValorDecimal(gastosAdmon);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_TOTAL_PAGAR_PERIODO).setValorDecimal(totalPagarPeriodo);
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_TOTAL_PAGAR).setValorDecimal(totalPagarPeriodo);
			//Aún no se ha definido si el formato tiene sancion, por esta razón se envía en 0
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_SANCIONES).setValor(new Integer (0));
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_VALOR_PAGO_SANCIONES).setValor(new Integer (0));
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_VAL_PAGO_DERECHOS_EXPLOTACION).setValor(new Integer (0));
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_VAL_PAGO_GASTOS_ADMINISTRACION).setValor(new Integer (0));
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CAS_NEG_VALOR_PAGO_INTERESES).setValor(new Integer (0));
			
			//Casilla origen declaración
			ocurrencia.getValCasillaNeg(IDConceptosNegocioJuegosSuerteYAzar.CASNEG_ORIGEN_DECLARACION).setValor(new Integer(IDTiposOrigenDeclaracion.INTERNO_PRIVADA));
			//Casilla solicitud
			ocurrencia.getValCasillaNeg(IDConceptosNegocioDil.CAS_NEG_NUM_RADICADO).setValor(new Long(documentoCargaPK.getIdeDocumento()));

			LOGGER.info("Va a guardar el borrador del documento 320 v1 generado para la solicitud: "+ documentoCargaPK.getIdeDocumento());
			identificadorDoc = salvarDocumento(documento,NUM_FORMATO);
			// NO HACE NADA, EN TEORIA PERO IGUAL LO DEJO
			anexarDocumento (identificadorDoc.getIdDocumento(), identificadorDoc.getNumRepeticion(),
					identificadorDoc.getIdFormato(), identificadorDoc.getVersion(), identificadorDoc.getIdEstado());
			
			//Crea tarea presentación
			Long ideTarea = crearTareaPresentarJuegosSuerteyAzar();

			juegosPK = new DJuegoSuerteAzarPKTO(new Long(identificadorDoc.getIdDocumento()), new Integer(identificadorDoc.getNumRepeticion()));
            juegosAtt = new DJuegoSuerteAzarAttTO();
            juegosAtt.setIdeDocumentoCarga(documentoCargaPK.getIdeDocumento());
            juegosAtt.setNumRepeticionCarga(documentoCargaPK.getNumRepeticion());
            juegosAtt.setFecCambio(new Timestamp(System.currentTimeMillis()));
            juegosAtt.setIdePersona(consultarIdPersonaRut(nit.longValue()));
            juegosAtt.setIdeUsuarioCambio(solicitud.getSolicitudAtt().getIdeUsuarioSolicitud());
            juegosAtt.setCodEstado(new Integer(IDDocumento.IDE_ESTADO_TEMPORAL));
            juegosAtt.setIdeTarea(ideTarea);
            DJuegoSuerteAzarTO juegosTO = new DJuegoSuerteAzarTO(juegosPK, juegosAtt);
            DCmdSrvCrearJuegoSuerteAzar srvCrearJuegoSA = (DCmdSrvCrearJuegoSuerteAzar) 
            			getServicio("diligenciamientomasivo.juegosdesuerteyazar.DCmdSrvCrearJuegoSuerteAzar");
            srvCrearJuegoSA.inicializar(juegosTO);
            srvCrearJuegoSA.ejecutar();
            
			LOGGER.info("*********** FIN Documento ETESA OK ***************");
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

	/**
	 * Genera la tarea al responsable de Presentar el documento.
	 * @return
	 * @throws DExcepcion 
	 */

	private Long crearTareaPresentarJuegosSuerteyAzar() throws DExcepcion{

		//Consultar el usuario con el id de la persona remitente
		Long idPersona = solicitud.getSolicitudAtt().getIdePersona();        
		DUsuarioTO usuarioTO = new DUsuarioTO();
		DUsuarioAttTO usuarioAttTO = new DUsuarioAttTO();
		usuarioAttTO.setIdePersonaRut(idPersona);
		usuarioTO.setUsuarioAtt(usuarioAttTO);        
		DCmdSrvConsUsuarios srvConsUsuarios = (DCmdSrvConsUsuarios) getServicio("arquitectura.seguridad.DCmdSrvConsUsuarios");
		srvConsUsuarios.iniciar(usuarioTO);
		srvConsUsuarios.ejecutar();
		usuarioTO = srvConsUsuarios.getUsuarioTO();
		Long idUsuario = usuarioTO.getUsuarioPK().getId();

		//Consultar el usuario y rol para enviar la tarea
		Integer idOrg = solicitud.getSolicitudAtt().getIdeOrganizacion();
		DCmdSrvConsUsuarioRol srvUsuario = (DCmdSrvConsUsuarioRol) getServicio("arquitectura.seguridad.DCmdSrvConsUsuarioRol");
		DUsuarioRolTO usuarioRolTO = new DUsuarioRolTO(new DUsuarioRolPKTO(),new DUsuarioRolAttTO());
		usuarioRolTO.getUsuarioRolPK().setIdeUsuario(idUsuario);
		usuarioRolTO.getUsuarioRolPK().setIdeOrganizacion(idOrg);
		srvUsuario.inicializarConsultaGenericaPorPK(usuarioRolTO.getUsuarioRolPK());
		srvUsuario.ejecutar();
		Collection rolesUsuarios_ = srvUsuario.getRolesUsuario();
		usuarioRolTO = (DUsuarioRolTO)rolesUsuarios_.iterator().next();

		//Enviar mensaje de notificaciï¿½n a la bandeja de tareas        
		DCrearEventosHelper creadorEventos = new DCrearEventosHelper(getContexto());
		DTareasNegHelper creadorTareas = new DTareasNegHelper(getContexto().getContextoSeguridad());

		DCmdSrvConsDocumentoMUISCA conslDoc10006 = consultarDocumentoMuisca(solicitud.getSolicitudPK().getIdeSolicitud().
				longValue(),1,IDConstantesCircuitoJuegosSuerteyAzar.DOCUMENTO_SOLICITUD);
		try {	
			crearExpediente( creadorEventos );

			//*******************************************************************
			//ADICIONA EVENTO PRESENTACIï¿½N DE INFORMACION POR CARGA DE ARCHIVOS
			//Crea la coleccion de documentos para el evento

			Collection coleccionDocEventoPresentCarga = new ArrayList();
			
			DDocEventoTO docEventoPresentCarga  = new DDocEventoTO(new DDocEventoPKTO(),new DDocEventoAttTO());
			
			docEventoPresentCarga.getPk().setIdeEvento( null );
			docEventoPresentCarga.getPk().setIdeDocumento( solicitud.getSolicitudPK().getIdeSolicitud() );			
			docEventoPresentCarga.getPk().setIdeExpediente( new Long(expediente.getPk().getIdeExpediente()) );
			docEventoPresentCarga.getPk().setNumRepeticion( new Integer(1) );
			docEventoPresentCarga.getAtt().setIdeFormato( solicitud.getSolicitudAtt().getIdeFormato().intValue() );
			docEventoPresentCarga.getAtt().setNumVersionFormato( solicitud.getSolicitudAtt().getNumVersionFormato().intValue() );
			docEventoPresentCarga.getAtt().setIdeTipoDocActividadUsuario( IDConstantesCircuitoJuegosSuerteyAzar.TIPO_DOC_ACTIVIDAD_PRESENTACION_INFORMACION );
			
			coleccionDocEventoPresentCarga.add( docEventoPresentCarga );
			
			//Crea la coleccion de parametros para el evento	        
	        Collection coleccionParamEventoPresentCarga = new ArrayList();
	        
	        DParametroEventoTO paramEventoPresentCarga1 = new DParametroEventoTO( new DParametroEventoPKTO(),new DParametroEventoAttTO() );
	        
	        paramEventoPresentCarga1.getPK().setIdeExpediente( new Long( expediente.getPk().getIdeExpediente() ) );
	        paramEventoPresentCarga1.getPK().setIdeParamDef( IDConstantesCircuitoJuegosSuerteyAzar.IDE_PAR_ACT_CM_IDSOLICITUD );
	        paramEventoPresentCarga1.getAtt().setNomParametro( IDConstantesCircuitoJuegosSuerteyAzar.NOM_PAR_ACT_CM_IDSOLICITUD );
	        paramEventoPresentCarga1.getAtt().setValParametro( solicitud.getSolicitudPK().getIdeSolicitud()+"" );
	        	        
	        coleccionParamEventoPresentCarga.add( paramEventoPresentCarga1 );
	        	        
	        DParametroEventoTO paramEventoPresentCarga2 = new DParametroEventoTO( new DParametroEventoPKTO(),new DParametroEventoAttTO() );
	        
	        paramEventoPresentCarga2.getPK().setIdeExpediente( new Long( expediente.getPk().getIdeExpediente() ) );
	        paramEventoPresentCarga2.getPK().setIdeParamDef( IDConstantesCircuitoJuegosSuerteyAzar.IDE_PAR_ACT_CM_IDDOCUMENTO );
	        paramEventoPresentCarga2.getAtt().setNomParametro( IDConstantesCircuitoJuegosSuerteyAzar.NOM_PAR_ACT_CM_IDDOCUMENTO );
	        paramEventoPresentCarga2.getAtt().setValParametro( documento.getId()+"" );
	        	        
	        coleccionParamEventoPresentCarga.add( paramEventoPresentCarga2 );

			//Crea la coleccion de personas para el evento
	        Collection coleccionPersonasEventoPresentCarga = new ArrayList();	        	        
	        
	        DPersonaEventoTO personasEventoPresentCarga = creadorEventos.getPersonaEventoPorIdDocumento(conslDoc10006.getIdentificadorDoc(),1);

	        coleccionPersonasEventoPresentCarga.add(personasEventoPresentCarga);
	        
	        //Adiciona el evento
			DEventoPKTO eventoPresentCarga = creadorEventos.adicionarEvento(
													expediente, 
													IDConstantesCircuitoJuegosSuerteyAzar.IDE_ACTIVIDAD_CARGA_MASIVA, 
													"Presentación de información por carga de archivos.", 
													coleccionDocEventoPresentCarga, 
													coleccionPersonasEventoPresentCarga, 
													coleccionParamEventoPresentCarga, idOrg.intValue());

			LOGGER.info("Adicionado el evento de presentacion 10006. Evento:"+ eventoPresentCarga.getIdeEvento());
			
			//*******************************************************************			
			//CREACION DE LA TAREA		
			//Creacion del documento de la tarea
			DCmdSrvConsFormato srvConsFormato = (DCmdSrvConsFormato) getServicio(
					"entradasalida.DCmdSrvConsFormato");

			srvConsFormato.inicializar(	IDConstantesCircuitoJuegosSuerteyAzar.FORMATO_DOCUMENTO_TAREA_PRESENTACION,
					IDConstantesCircuitoJuegosSuerteyAzar.VERSION_FORMATO_DOCUMENTO_TAREA_PRESENTACION,
					IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO);
			srvConsFormato.ejecutar();

			IDDocumento documentoTarea = DDocumentoUtil.getDocumentoEnBlanco(srvConsFormato.getFormatoPorDefecto());			
			//Construye documento tarea...
			armarDocTarea(conslDoc10006,documentoTarea, usuarioRolTO);
			// Crea el documento
			DCmdSrvCrearDocumentoES accCrearDoc = (DCmdSrvCrearDocumentoES) getServicio("entradasalida.documentos.DCmdSrvCrearDocumentoES");
			accCrearDoc.inicializarSinId(documentoTarea,IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO);
			accCrearDoc.ejecutar();
			// Mueve el documento a ES
			DIdentificadorDoc identificador = accCrearDoc.getIdentificadorDoc();
			DCmdSrvMoverDocumentoES cmdMover = (DCmdSrvMoverDocumentoES) getServicio("entradasalida.documentos.DCmdSrvMoverDocumentoES");
			cmdMover.inicializar(identificador.getIdDocumento(), identificador.getNumRepeticion(), 
					identificador.getIdFormato(),identificador.getVersion());
			cmdMover.ejecutar();
			
			long idDocGuardado = accCrearDoc.getDocumento().getId();
			int numeroRepeticiones = accCrearDoc.getDocumento().getRepeticion();
			LOGGER.info("Se ha creado el documento TAREA: "+new Long(idDocGuardado));
			
			int anioGravable = conslDoc10006.getDocumento().getGrupos().getGrupo(1).getOcurrencia(1).getValorCasilla(43).getInt();
			int periodo = conslDoc10006.getDocumento().getGrupos().getGrupo(1).getOcurrencia(1).getValorCasilla(44).getInt();
			
			//Definicion de la tarea
			DTareaNegTO tareaNegTO = new DTareaNegTO(new DTareaNegPKTO(),new DTareaNegAttTO());
			tareaNegTO.getPk().setIdeDocumentoTarea(new Long(idDocGuardado));
			tareaNegTO.getPk().setNumRepeticionDocTarea( new Integer(numeroRepeticiones) );
			tareaNegTO.getAtt().setIdePersonaCreacion(usuarioRolTO.getUsuarioRolAtt().getIdePersona());
			tareaNegTO.getAtt().setIdeOrganizacion(usuarioRolTO.getUsuarioRolPK().getIdeOrganizacion());
			tareaNegTO.getAtt().setIdeLugar(usuarioRolTO.getUsuarioRolPK().getIdeLugar());
			tareaNegTO.getAtt().setIdeEstablecimiento(usuarioRolTO.getUsuarioRolPK().getIdeEstablecimiento());
			tareaNegTO.getAtt().setIdeTipoOrganizacion(usuarioRolTO.getUsuarioRolPK().getIdeTipoOrganizacion());
			tareaNegTO.getAtt().setIdeUnidadAdm(usuarioRolTO.getUsuarioRolPK().getIdeUnidadAdministrativa());
			tareaNegTO.getAtt().setIdeRol(usuarioRolTO.getUsuarioRolPK().getIdeRol() );
			tareaNegTO.getAtt().setIdeTareaDefNeg(new Long(IDConstantesCircuitoJuegosSuerteyAzar.TAREA_PRESENTAR_JUEGOS_SUERTE_AZAR));
			tareaNegTO.getAtt().setFecFinalPrevista(new Long(getFechaFinalPrevista(anioGravable,periodo,getContexto().getContextoSeguridad())));
			tareaNegTO.getAtt().setCodEstado(IDTareasNeg.COD_ESTADO_TAREA_ACTIVA);
			tareaNegTO.getAtt().setFecCreacion(new Long(DFechaUtils.getFechaHoraNegocio(new Date())));
			tareaNegTO.getAtt().setIdeExpediente( new Long( expediente.getPk().getIdeExpediente() ) );
			tareaNegTO.getAtt().setIdeEvento( eventoPresentCarga.getIdeEvento());

			List parametros = new ArrayList();
			DParamTareaNegTO par1 = new DParamTareaNegTO(new DParamTareaNegPKTO(),new DParamTareaNegAttTO());
			par1.getPk().setIdeDocumentoTarea(idDocGuardado);
			par1.getPk().setNumRepDocTarea(numeroRepeticiones);
			par1.getPk().setIdeParamTareaNeg(null);	        
			par1.getAtt().setIdeTareaDefNeg(new Long(IDConstantesCircuitoJuegosSuerteyAzar.TAREA_PRESENTAR_JUEGOS_SUERTE_AZAR));
			par1.getAtt().setIdeParamTareaDefNeg( new Integer(IDConstantesCircuitoJuegosSuerteyAzar.PARAM_T1_PRESEN_JUA_SOLICITUD_ID) );
			par1.getAtt().setNomParametro(IDConstantesCircuitoJuegosSuerteyAzar.PARAM_T1_PRESEN_JUA_SOLICITUD_NOMBRE);
			par1.getAtt().setCodTipoDato(IDTareasNeg.TIPO_DATO_NUMERICO);	        
			par1.getAtt().setValNumericoLong(new Long( solicitud.getSolicitudPK().getIdeSolicitud()));

			parametros.add(par1);

			DParamTareaNegTO par2 = new DParamTareaNegTO(new DParamTareaNegPKTO(),new DParamTareaNegAttTO());
			par2.getPk().setIdeDocumentoTarea(idDocGuardado);
			par2.getPk().setNumRepDocTarea(numeroRepeticiones);
			par2.getPk().setIdeParamTareaNeg(null);	        
			par2.getAtt().setIdeTareaDefNeg(new Long(IDConstantesCircuitoJuegosSuerteyAzar.TAREA_PRESENTAR_JUEGOS_SUERTE_AZAR));
			par2.getAtt().setIdeParamTareaDefNeg( new Integer(IDConstantesCircuitoJuegosSuerteyAzar.PARAM_T1_PRESEN_JUA_ID_DOCUMENTO_ID) );
			par2.getAtt().setNomParametro(IDConstantesCircuitoJuegosSuerteyAzar.PARAM_T1_PRESEN_JUA_ID_DOCUMENTO_NOMBRE);
			par2.getAtt().setCodTipoDato(IDTareasNeg.TIPO_DATO_NUMERICO);	        
			par2.getAtt().setValNumericoLong(new Long(identificadorDoc.getIdDocumento()));

			parametros.add(par2);

			creadorTareas.crearTarea( tareaNegTO , parametros);

			//Definicion de la tarea de reparto
			DTareaPersonaNegTO tareaPersonaNegTO = new DTareaPersonaNegTO(new DTareaPersonaNegPKTO(),new DTareaPersonaNegAttTO());

			tareaPersonaNegTO.getPk().setIdeDocumentoTarea( tareaNegTO.getPk().getIdeDocumentoTarea() );
			tareaPersonaNegTO.getPk().setNumRepeticionDocTarea( tareaNegTO.getPk().getNumRepeticionDocTarea() );

			tareaPersonaNegTO.getAtt().setCodEstado( IDTareasNeg.COD_ESTADO_TAREA_ACTIVA );
			tareaPersonaNegTO.getAtt().setFecAsignacion( tareaNegTO.getAtt().getFecCreacion() );
			tareaPersonaNegTO.getAtt().setFecInicio( tareaNegTO.getAtt().getFecCreacion() );					
			tareaPersonaNegTO.getAtt().setIdeEvento( tareaNegTO.getAtt().getIdeEvento() );
			tareaPersonaNegTO.getAtt().setIdeExpediente( tareaNegTO.getAtt().getIdeExpediente() );
			tareaPersonaNegTO.getAtt().setIdePersonaReparto( new Long(DUsuariosSistema.getIdePersonaSistema()) );
			tareaPersonaNegTO.getAtt().setIdePersonaRut( idPersona );
			tareaPersonaNegTO.getAtt().setIdeTareaDefNeg( tareaNegTO.getAtt().getIdeTareaDefNeg() );			
			tareaPersonaNegTO.getAtt().setIdeLugar( tareaNegTO.getAtt().getIdeLugar() );
			tareaPersonaNegTO.getAtt().setIdeEstablecimiento( tareaNegTO.getAtt().getIdeEstablecimiento() );
			tareaPersonaNegTO.getAtt().setIdeTipoOrganizacion( tareaNegTO.getAtt().getIdeTipoOrganizacion() );
			tareaPersonaNegTO.getAtt().setIdeUnidadAdm( tareaNegTO.getAtt().getIdeUnidadAdm() );			
			tareaPersonaNegTO.getAtt().setIdeOrganizacion( tareaNegTO.getAtt().getIdeOrganizacion() );
			tareaPersonaNegTO.getAtt().setIdeRol( tareaNegTO.getAtt().getIdeRol() );			
			tareaPersonaNegTO.getAtt().setFecFinalPrevista( tareaNegTO.getAtt().getFecFinalPrevista() );						

			DIdentificadorDoc identificadorTareaReparto = creadorTareas.crearDocumentoReparto(tareaPersonaNegTO, "");
			tareaPersonaNegTO.getPk().setIdeDocumentoReparto( new Long(identificadorTareaReparto.getIdDocumento()) );
			tareaPersonaNegTO.getPk().setNumRepeticionDocReparto( new Integer(identificadorTareaReparto.getNumRepeticion()) );

			creadorTareas.crearRepartoTarea(tareaPersonaNegTO);

			return new Long(documentoTarea.getId());
		}catch (Exception e) {
			LOGGER.error("Error al crear el expediente Documento Juego Suerte y Azar", e);
			throw new DExcepcion(e);
		}
	}

	/**
	 * Crea el expediente.
	 * 
	 * @param pSolicitud
	 * @param creadorEventos
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	private void crearExpediente(DCrearEventosHelper creadorEventos)throws DExcepcion{
		DExpedienteTO expedientePadre = null;
		try {
			//Si el expediente ya ha sido creado sale del metodo de creacion.
			if(expediente!=null) return;

			//Consulta su correspondiente flujo CM y Busca el expediente padre 
			DDocumentoPKTO documentoSolicitud_ = new DDocumentoPKTO();
			documentoSolicitud_.setIdeDocumento(solicitud.getSolicitudPK().getIdeSolicitud());

			DConfiguracionCargaMasivaPKTO confCMPk = new DConfiguracionCargaMasivaPKTO();

			confCMPk.setIdeFormato(IDConstantesCircuitoJuegosSuerteyAzar.DOCUMENTO_DETALLE_DECLARACION);
			confCMPk.setNumVersionFormato(IDConstantesCircuitoJuegosSuerteyAzar.DOCUMENTO_DETALLE_DECLARACION_VERSION);

			DCmdAccConsConfiguracionCargaMasiva accConsConfCM = (DCmdAccConsConfiguracionCargaMasiva) this.
			getAccion("cargamasiva.DCmdAccConsConfiguracionCargaMasiva");
			accConsConfCM.inicializar(confCMPk);
			accConsConfCM.ejecutar();
			DConfiguracionCargaMasivaTO configCMTO = accConsConfCM.getConfiguracionCargaMasiva();

			DConsultaExpedienteTO consultaExpedienteTO = new DConsultaExpedienteTO();
			consultaExpedienteTO.setNumDocumento(documentoSolicitud_.getIdeDocumento());
			consultaExpedienteTO.setNumRepeticion(new Integer(1));
			consultaExpedienteTO.setIdeFlujo(configCMTO.getAtt().getIdeFlujo().toString());

			Collection expedientesColeccion = creadorEventos.consultarExpedientes(consultaExpedienteTO);	

			if (expedientesColeccion != null && !expedientesColeccion.isEmpty()){
				expedientePadre = (DExpedienteTO) expedientesColeccion.iterator().next();
			}

			//Crea la coleccion de parametros del asunto
			Collection parametrosAsunto = new ArrayList();	
			DDocumentoPKTO documentoExpediente = new DDocumentoPKTO();
		    documentoExpediente.setIdeDocumento( new Long (identificadorDoc.getIdDocumento()) );
		    documentoExpediente.setNumRepeticion( new Integer( 1 ) );
		    //Crea el expediente -- Se utiliza como total a pagar la suma totalPagarPeriodo. 
		    //sin la Sancion porque se supone no hay tal.
		    expediente = creadorEventos.crearExpediente(documentoExpediente, 
														IDConstantesCircuitoJuegosSuerteyAzar.IDE_FLUJO,
														new Integer(getContexto().getContextoSeguridad().getIdeUnidadAdm()),
														parametrosAsunto,new Double(totalPagarPeriodo).longValue(),NUM_FORMATO);
		    LOGGER.info("Creado el expediente "+expediente);
		    
		    //Asigna la jerarquia al expedianrte creado
			creadorEventos.crearJerarquiaExpediente(expedientePadre.getPk().getIdeExpediente(), 
													expediente.getPk().getIdeExpediente(), 
													IDConstantesEventos.TIP_JERARQUIA_DERIVA);

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	private void armarDocTarea(DCmdSrvConsDocumentoMUISCA conslDoc10006,IDDocumento documentoTarea,DUsuarioRolTO usuarioRolTO){

		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.ANIO, anio , documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.TIPO_DOCUMENTO_REMITENTE,TipoDocumentos.INTERNO_NIT,documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.NUMERO_IDENTIFICACION_REMITENTE,usuarioRolTO.getUsuarioRolAtt().getNumNitOrg(), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.PRIMER_APELLIDO_REMITENTE,usuarioRolTO.getUsuarioRolAtt().getPrimerAppellido(), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.SEGUNDO_APELLIDO_REMITENTE,usuarioRolTO.getUsuarioRolAtt().getSegundoApellido(), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.PRIMER_NOMBRE_INTERESADO,usuarioRolTO.getUsuarioRolAtt().getPrimerNombre(), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.PRIMER_NOMBRE_REMITENTE,usuarioRolTO.getUsuarioRolAtt().getPrimerNombre(), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.RAZON_SOCIAL_REMITENTE,usuarioRolTO.getUsuarioRolAtt().getNombreRazonSocialUsuario(), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.NUMERO_SOLICITUD,solicitud.getSolicitudPK().getIdeSolicitud(), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.ESTADO_SOLICITUD,solicitud.getSolicitudAtt().getCodEstado(), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.FORMATO_SOLICITUD,solicitud.getSolicitudAtt().getIdeFormato(), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.VERSION_SOLICITUD,solicitud.getSolicitudAtt().getNumVersionFormato(), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.CONCEPTO_SOLICITUD,solicitud.getSolicitudAtt().getCodConcepto(), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.ANIO_VIGENCIA_SOLICITUD,solicitud.getSolicitudAtt().getAnioVigencia(), documentoTarea);
		Timestamp fec = solicitud.getSolicitudAtt().getFecSolicitud();
		Long fechaSolicitud = null;
		if(fec!=null){
			fechaSolicitud = new Long (DFechaUtils.getFechaNegocio(fec));
		}
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.FECHA_SOLICITUD,fechaSolicitud, documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.NUMERO_ARCHIVO_SOLICITUD,solicitud.getSolicitudAtt().getNumTotalArchivos(), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.NUMERO_REGISTROS_SOLICITUD,solicitud.getSolicitudAtt().getNumTotalRegistros(), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.NUMERO_FORMULARIO_PRESENTAR,DDocumentoUtil.getValorCasillaSimp(4, 1, 1, documento), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.FORMATO_PRESENTAR,NUM_FORMATO, documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.VERSION_PRESENTAR,VERSION_FORMATO, documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.TOTAL_BASE_LIQUIDACION,new Double (totalBaseLiqJuegos), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.TOTAL_DERECHOS_EXPLOTACION,new Double (totalDerExplotacion), documentoTarea);
		DDocumentoUtil.setValorCasillaSimp(IDConstantesDocTareaJuegosSuerteYAzar.TOTAL_PAGAR_PERIODO,new Double (totalPagarPeriodo), documentoTarea);
	}


	private IDDocumento crearDocumento(long idPersonaRut) throws DExcepcion {
		DCmdAccLlenarDocumentoNuevoConsRut accCrearDocumento = (DCmdAccLlenarDocumentoNuevoConsRut) this
		.getAccion("entradasalida.consintegral.DCmdAccLlenarDocumentoNuevoConsRut");
		accCrearDocumento.inicializarPorIdePersonaRut(NUM_FORMATO,
				VERSION_FORMATO, idPersonaRut,
				IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO);
		accCrearDocumento.ejecutar();
		IDDocumento documento = accCrearDocumento.getDocumento();
		return documento;
	}

	private DSolicitudIngresoTO consultarSolicitud(Long idSolicitud)
	throws DExcepcion {
		DCmdSrvConsSolicitudIngreso srvConsSol = (DCmdSrvConsSolicitudIngreso) getServicio("cargamasiva.procesamiento.DCmdSrvConsSolicitudIngreso");

		srvConsSol.inicializar(new DSolicitudIngresoPKTO(idSolicitud));

		srvConsSol.ejecutar();
		return srvConsSol.getSolicitudIngreso();
	}

	private Long consultarIdPersonaRut(long nit) throws DExcepcion {
		DCmdSrvConsRegistroRut srvConsRut = (DCmdSrvConsRegistroRut) this
		.getServicio("rut.DCmdSrvConsRegistroRut");
		srvConsRut.inicializarConsultarPorNIT(nit);
		personaRut = new DRegistroRutTO();
		srvConsRut.ejecutar();
		personaRut = srvConsRut.getRegistroRut();
		return personaRut.getPK().getIdePersonaRut();
	}

	private DIdentificadorDoc salvarDocumento(IDDocumento doc, int ideFormato)
	throws DExcepcion {

		DIdentificadorDoc identificadorDoc = new DIdentificadorDoc();
		DCmdAccCrearDocumentoESTemporal dCmdSrvCrearDocumentoES = (DCmdAccCrearDocumentoESTemporal) getAccion("entradasalida.DCmdAccCrearDocumentoESTemporal");

		String strDocXML = DDocumentoUtil.obtenerXmlDeDocumento(doc);
		dCmdSrvCrearDocumentoES.inicializarGuardarSinId(strDocXML,
				IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO,
				IDModosDiligenciamiento.MODO_INICIAL, ideFormato);

		dCmdSrvCrearDocumentoES.ejecutar();
		identificadorDoc = dCmdSrvCrearDocumentoES.getIdentificadorDoc();

		return identificadorDoc;
	}
	/**
	 * Ejecuta el servicio de consulta de documentos muisca a travs de de l id del documento y la repeticiï¿½n
	 * @param pIdDocumento
	 * @param pNumRepeticion
	 * @return
	 * @throws DExcepcion
	 */
	private DCmdSrvConsDocumentoMUISCA consultarDocumentoMuisca(long pIdDocumento, int pNumRepeticion,int pIdeFormato)throws DExcepcion{
		DCmdSrvConsDocumentoMUISCA  srvConsDocMuisca = (
				DCmdSrvConsDocumentoMUISCA) getServicio(
				"entradasalida.documentos.DCmdSrvConsDocumentoMUISCA");
		srvConsDocMuisca.inicializar(pIdDocumento,pNumRepeticion,pIdeFormato);
		srvConsDocMuisca.ejecutar();
		return srvConsDocMuisca;
	}

	/**
	 * Se obtiene la fecha límite de la presentación de la declaración.
	 * @param anioGravable
	 * @param Periodo
	 * @return
	 * @throws DExcepcion 
	 */
	private int getFechaFinalPrevista(int anioGravable,  int Periodo, DContextoSeguridad contextoSeg) throws DExcepcion{
        DCmdAccConsPlazosPresentaDecla consPlazos = (DCmdAccConsPlazosPresentaDecla)
        getAccion("rut.liquidacion.DCmdAccConsPlazosPresentaDecla");
        int ideTipoDeclarante = obtenerTipoDeclarante(nit, contextoSeg);
        DPlazosPresentaDeclaPKTO to = new DPlazosPresentaDeclaPKTO((short)anioGravable,NUM_FORMATO,(byte)VERSION_FORMATO,
        		new Integer(Periodo).byteValue(),ideTipoDeclarante,nit);
        consPlazos.inicializarConsPorNit(to);
        consPlazos.ejecutar();
		return consPlazos.getFechaLimite();
	}
	
    /**
    * Obtiene el tipo de declarante.
    * @param numNit Long
    * @param contextoSeg DContextoSeguridad
    * @return int
    */
   private static int obtenerTipoDeclarante(Long numNit, DContextoSeguridad contextoSeg) {
       int idTipoDeclarante = 0;
       int fecha = DFechaHora.convertirDateToInteger(new Date()).intValue();
       DBusServiciosDelegateRUT delegadoSrvs = new DBusServiciosDelegateRUT();

       try {
           DCmdSrvConsRegistroRut srvConsRegistroRut = (DCmdSrvConsRegistroRut)
                   delegadoSrvs.getComando("rut.DCmdSrvConsRegistroRut");
           srvConsRegistroRut.getContexto().setContextoSeguridad(contextoSeg);
           srvConsRegistroRut.inicializarConsultarPorNIT(numNit.longValue(), fecha);
           srvConsRegistroRut.ejecutar();
           DRegistroRutTO registroRut = srvConsRegistroRut.getRegistroRut();
           boolean isGranContribuyente = srvConsRegistroRut.isGranContribuyente();
           Integer codTipoContribuyente = registroRut.getAtt().getCodTipoContribuyente();
           if (codTipoContribuyente != null) {
               idTipoDeclarante = codTipoContribuyente.intValue();
               // Esta es el truco ese para ponerle el 3 si es juridico y gran contribuyente
               if (isGranContribuyente) {
                   idTipoDeclarante = IDTipoDeclaranteRUT.GRAN_CONTRIBUYENTE;
               }
           }
       } catch(DExcepcion ex) {
    	   LOGGER.error("Hubo un error obteniendo el tipo de declarante a partir del nit " + numNit, ex);
       } catch (Exception ex) {
    	   LOGGER.error("Hubo un error obteniendo el tipo de declarante a partir del nit " + numNit, ex);
       }

       return idTipoDeclarante;
   }
}
