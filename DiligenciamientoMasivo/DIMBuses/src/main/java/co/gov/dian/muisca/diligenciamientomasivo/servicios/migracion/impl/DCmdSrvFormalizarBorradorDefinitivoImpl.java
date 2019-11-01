package co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.util.DFechaUtils;
import co.gov.dian.muisca.arquitectura.web.buses.DBusServiciosARQDelegateTxNueva;
import co.gov.dian.muisca.diligenciamiento.general.constantes.IDConceptosNegocioDil;
import co.gov.dian.muisca.diligenciamiento.general.constantes.IDConstantesDiligenciamiento;
import co.gov.dian.muisca.diligenciamientomasivo.general.enums.DEnumEstadosPrMigracion;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion.DCmdSrvActFormalizaBorradorDef;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.migracion.DCmdSrvFormalizarBorradorDefinitivo;
import co.gov.dian.muisca.entradasalida.delegados.DDelegadoSrvConsFormato;
import co.gov.dian.muisca.entradasalida.documento.DDocumentoUtil;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDCasilla;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDEstadosProcesamiento;
import co.gov.dian.muisca.entradasalida.documento.IDLogDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;
import co.gov.dian.muisca.entradasalida.excepciones.DEntradaSalidaExcepcion;
import co.gov.dian.muisca.entradasalida.formatos.IDConstantesFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDConstantesFormatoEntrada;
import co.gov.dian.muisca.entradasalida.formatos.IDFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDModosDiligenciamiento;
import co.gov.dian.muisca.entradasalida.general.delegados.DDelegadoOperacionesMasivas;
import co.gov.dian.muisca.entradasalida.general.to.cargamasiva.DIdentificadorDocumentoTO;
import co.gov.dian.muisca.entradasalida.general.to.formato.DFormatoEntradaTO;
import co.gov.dian.muisca.entradasalida.servicios.control.DCmdSrvRegistrarTablaPivote;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvActFechaAcuseDocES;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvCambiarEstadoDocumento;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoES;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoMUISCA;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvCrearDocumentoESModoDil;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvMoverDocumentoES;
import co.gov.dian.muisca.entradasalida.servicios.formatos.DCmdSrvGenerarAutoadhesivoFormato;
import co.gov.dian.muisca.entradasalida.servicios.migracion.DCmdSrvCrearLogEnvioSiat;
import co.gov.dian.muisca.rut.constantes.IDTipoContribuyentesRUT;
import co.gov.dian.muisca.rut.general.to.DRegistroRutPKTO;
import co.gov.dian.muisca.rut.general.to.DRegistroRutTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsRegistroRut;

public class DCmdSrvFormalizarBorradorDefinitivoImpl extends DCmdSrvFormalizarBorradorDefinitivo {

	/**
	 *
	 */
	private static final long serialVersionUID = -104231930452067437L;
	private static final Logger log = Logger.getLogger(DCmdSrvFormalizarBorradorDefinitivoImpl.class);
	private static Integer[] FORMATOS_PERMITIDOS = { 110, 210, 230, 240, 300, 350, 420 };

	/**
	 *
	 * @param idFormato
	 * @param numVersionFormato
	 * @param idDocumento
	 * @param numRepeticion
	 * @throws DExcepcion
	 */
	private void actualizarEstadoDocumento(Integer idFormato, Integer numVersionFormato, Long idDocumento,
			Integer numRepeticion) throws DExcepcion {
		log.info("Se cambia estado al documento");
		final DCmdSrvCambiarEstadoDocumento comandoEstado = (DCmdSrvCambiarEstadoDocumento) getServicio(
				"entradasalida.documentos.DCmdSrvCambiarEstadoDocumento");
		comandoEstado.inicializar(idFormato, numVersionFormato, idDocumento, numRepeticion,
				IDFormato.ENTRADA_RECAUDO_CAJA, IDDocumento.IDE_ESTADO_PRESENTADO);
		comandoEstado.ejecutar();
	}

	/**
	 *
	 * @param ideRegistroProceso
	 * @param estado
	 * @param error
	 * @throws DExcepcion
	 */
	private void actualizarEstadoRegistro(Long ideRegistroProceso, Integer estado, String error) throws DExcepcion {

		log.info("Actualizando el estado del proceso " + ideRegistroProceso + " a " + estado
				+ " el error existente es: " + error);
		final DFormalizaBorradorDefTO toFormalizaBorradorDef = new DFormalizaBorradorDefTO();
		toFormalizaBorradorDef.getPK().setIdeProceso(ideRegistroProceso);
		toFormalizaBorradorDef.getAtt().setIndEstado(estado);
		toFormalizaBorradorDef.getAtt().setTxtObservaciones(error);

		final DCmdSrvActFormalizaBorradorDef actualizarEstado = (DCmdSrvActFormalizaBorradorDef) getServicio(
				"diligenciamientomasivo.migracion.DCmdSrvActFormalizaBorradorDef");
		actualizarEstado.inicializarActualizarEstado(toFormalizaBorradorDef);
		actualizarEstado.ejecutar();
	}

	/**
	 *
	 * @param idDocumento
	 * @param numRepeticion
	 * @param ideFormato
	 * @param fechaAcuse
	 * @throws DExcepcion
	 */
	private void actualizarFecAcuseRecibo(Long idDocumento, Integer numRepeticion, Integer ideFormato, Long fechaAcuse)
			throws DExcepcion {
		log.info("Actualizando la fecha de acuse de recibo");
		final DCmdSrvActFechaAcuseDocES actFecAcuse = (DCmdSrvActFechaAcuseDocES) getServicio(
				"entradasalida.documentos.DCmdSrvActFechaAcuseDocES");
		actFecAcuse.inicializar(idDocumento, numRepeticion, ideFormato, fechaAcuse, false);
		actFecAcuse.ejecutar();
	}

	/**
	 * Busca si existe una declaración con las caracteristicas de la que se
	 * pretende presentar
	 *
	 * @param anioGravable
	 * @param periodo
	 * @param nit
	 * @param ideFormato
	 * @param numVersionFormato
	 * @return
	 * @throws DExcepcion
	 */
	private boolean consultarDeclaracionesPresentadas(Integer anioGravable, Integer periodo, Long nit,
			Integer ideFormato, Integer numVersionFormato) throws DExcepcion {

		log.info("Se consultan declaraciones presentadas");
		String cadenaDocumentos = null;
		final Map casillasCabPie = new HashMap();
		final DIdentificadorDocumentoTO toIdentificadorDoc = new DIdentificadorDocumentoTO();
		casillasCabPie.put(new Integer(IDConstantesFormato.IDE_CASILLA_ANNIO), anioGravable);
		if (periodo != null) {
			casillasCabPie.put(new Integer(IDConstantesFormato.IDE_CASILLA_PERIODO), periodo);
		}
		casillasCabPie.put(new Integer(IDConstantesFormato.IDE_CASILLA_NIT), nit);
		toIdentificadorDoc.setIdFormato(ideFormato);
		toIdentificadorDoc.setCasillasCabPie(casillasCabPie);

		final DCmdSrvConsIdentificadorDoc consIdentificador = consultarIdentificadores(toIdentificadorDoc, true);

		final Collection<DIdentificadorDoc> colDeclaracionesPresentadas = consIdentificador.getIdentificadoresDoc();

		if (colDeclaracionesPresentadas != null && !colDeclaracionesPresentadas.isEmpty()) {
			for (final DIdentificadorDoc identificador : colDeclaracionesPresentadas) {
				if (identificador.getIdModoNegocio() != IDModosDiligenciamiento.MODO_ANULADO) {
					if (cadenaDocumentos != null) {
						cadenaDocumentos = cadenaDocumentos + "," + identificador.getIdDocumento();
					} else {
						cadenaDocumentos = String.valueOf(identificador.getIdDocumento());
					}
				}
			}
		}

		if (cadenaDocumentos != null) {
			error = "Ya existen documentos para el año, período, nit y formato :" + cadenaDocumentos;
		}

		return cadenaDocumentos != null;
	}

	/**
	 *
	 * @param idDocumento
	 * @param numrepeticion
	 * @param idFormato
	 * @return
	 * @throws DExcepcion
	 */
	private IDDocumento consultarDocumentoBorrador(Long idDocumento, Integer numRepeticion, Integer idFormato)
			throws DExcepcion {
		log.info("Se consulta documento en borrador");
		final DCmdSrvConsDocumentoES srvDocEs = (DCmdSrvConsDocumentoES) getServicio(
				"entradasalida.documentos.DCmdSrvConsDocumentoES");
		srvDocEs.inicializarConsPorId(idDocumento, numRepeticion, idFormato);
		srvDocEs.ejecutar();
		return srvDocEs.getDocumento();
	}

	/**
	 *
	 * @param idDocumento
	 * @param numRepeticion
	 * @param ideFormato
	 * @return
	 * @throws DExcepcion
	 */
	private DCmdSrvConsDocumentoMUISCA consultarDocumentoMuisca(Long idDocumento, Integer numRepeticion,
			Integer ideFormato) throws DExcepcion {
		log.info("Se Consulta el documento Definitivo");
		final DCmdSrvConsDocumentoMUISCA srvMUISCA = (DCmdSrvConsDocumentoMUISCA) getServicio(
				"entradasalida.documentos.DCmdSrvConsDocumentoMUISCA");
		srvMUISCA.inicializar(idDocumento, numRepeticion, ideFormato);
		srvMUISCA.ejecutar();

		return srvMUISCA;

	}

	/**
	 *
	 * @param idDocumento
	 * @param numRepeticion
	 * @param esDefinitivo
	 * @param idFormato
	 * @return
	 * @throws DExcepcion
	 */
	private DCmdSrvConsIdentificadorDoc consultarIdentificador(Long idDocumento, Integer numRepeticion,
			boolean esDefinitivo, Integer idFormato) throws DExcepcion {

		log.info("Se Consulta el identificador del documento " + idDocumento + " con repetición " + numRepeticion
				+ " en definitivo " + esDefinitivo);
		final DCmdSrvConsIdentificadorDoc consIdentificador = (DCmdSrvConsIdentificadorDoc) getServicio(
				"entradasalida.documentos.DCmdSrvConsIdentificadorDoc");
		consIdentificador.inicializarConsPorId(idDocumento, numRepeticion, esDefinitivo, idFormato);
		consIdentificador.ejecutar();
		return consIdentificador;
	}

	/**
	 *
	 * @param toIdentificadorDoc
	 * @param esDefinitivo
	 * @return
	 * @throws DExcepcion
	 */
	private DCmdSrvConsIdentificadorDoc consultarIdentificadores(DIdentificadorDocumentoTO toIdentificadorDoc,
			boolean esDefinitivo) throws DExcepcion {

		final DCmdSrvConsIdentificadorDoc consIdentificador = (DCmdSrvConsIdentificadorDoc) getServicio(
				"entradasalida.documentos.DCmdSrvConsIdentificadorDoc");
		consIdentificador.inicializarConsGenerica(toIdentificadorDoc, esDefinitivo);
		consIdentificador.ejecutar();
		return consIdentificador;
	}

	/**
	 * Ejecuta el comando de servicio.
	 *
	 * @throws DExcepcion
	 *             Si ocurre algn error al realizar la consulta de
	 *             FormalizaBorradorDef o si el objeto no existe
	 */
	@Override
	protected void ejecutarComando() throws DExcepcion {

		log.info("Se inicia la formalización del documento " + docFormalizar.getAtt().getIdeDocumento());

		actualizarEstadoRegistro(docFormalizar.getPK().getIdeProceso(),
				DEnumEstadosPrMigracion.PROCESANDO.getCodTipoEstado(), "");

		final DCmdSrvConsIdentificadorDoc consIdentificador = consultarIdentificador(
				docFormalizar.getAtt().getIdeDocumento(), docFormalizar.getAtt().getNumRepeticion(), false,
				docFormalizar.getAtt().getIdeFormato());

		if (consIdentificador.getIdentificadorDoc() != null) {

			final Integer anioGravable = new Integer(consIdentificador.getIdentificadorDocTO().getCasillasCabPie()
					.get(IDConstantesFormato.IDE_CASILLA_ANNIO).toString());

			final Object casPeriodo = consIdentificador.getIdentificadorDocTO().getCasillasCabPie()
					.get(IDConstantesFormato.IDE_CASILLA_PERIODO);

			Integer periodo = null;

			if (casPeriodo != null) {
				periodo = new Integer(consIdentificador.getIdentificadorDocTO().getCasillasCabPie()
						.get(IDConstantesFormato.IDE_CASILLA_PERIODO).toString());
			}

			final Long nit = new Long(consIdentificador.getIdentificadorDocTO().getCasillasCabPie()
					.get(IDConstantesFormato.IDE_CASILLA_NIT).toString());

			final Integer idModoNegocio = consIdentificador.getIdentificadorDoc().getIdModoNegocio();

			final Integer estado = consIdentificador.getIdentificadorDoc().getIdEstado();

			final boolean personasIdenticas = validarPersona(docFormalizar.getAtt().getIdePersonaRut(), nit);

			if (personasIdenticas) {
				// Se valida el borrador antes de moverlo, sino cumple no será
				// movido
				final boolean hayErroresBorrador = validarBorradorDefinitivo(anioGravable,
						docFormalizar.getAtt().getIdeFormato(), idModoNegocio, estado,
						docFormalizar.getAtt().getIdePersonaRut());

				if (!hayErroresBorrador) {
					final Long saldoAFavor = extraerSaldoAFavor(docFormalizar.getAtt().getIdeDocumento(),
							docFormalizar.getAtt().getNumRepeticion(), docFormalizar.getAtt().getIdeFormato());

					final boolean haySaldoAFavor = saldoAFavor != null && saldoAFavor > 0;

					if (!haySaldoAFavor) {

						final boolean hayDeclaracionesPresentadas = consultarDeclaracionesPresentadas(anioGravable,
								periodo, nit, docFormalizar.getAtt().getIdeFormato(),
								docFormalizar.getAtt().getNumVersionFormato().intValue());

						if (!hayDeclaracionesPresentadas) {

							final boolean hayErroresReciboPago = validarReciboPago(
									docFormalizar.getAtt().getIdeDocumentoRecibo(),
									docFormalizar.getAtt().getNumRepeticionRecibo(),
									docFormalizar.getAtt().getIdeFormatoRecibo(),
									docFormalizar.getAtt().getIdeDocumento(), docFormalizar.getAtt().getIdeFormato(),
									docFormalizar.getAtt().getIdePersonaRut());

							if (!hayErroresReciboPago) {
								formalizarDeclaracion(consIdentificador.getIdentificadorDoc());
								actualizarEstadoRegistro(docFormalizar.getPK().getIdeProceso(),
										DEnumEstadosPrMigracion.TERMINADO.getCodTipoEstado(), "");
							} else {
								actualizarEstadoRegistro(docFormalizar.getPK().getIdeProceso(),
										DEnumEstadosPrMigracion.ERROR_PROCESANDO.getCodTipoEstado(), error);
							}

						} else {
							actualizarEstadoRegistro(docFormalizar.getPK().getIdeProceso(),
									DEnumEstadosPrMigracion.ERROR_PROCESANDO.getCodTipoEstado(), error);
						}
					} else {
						error = "El borrador " + docFormalizar.getAtt().getIdeDocumento()
								+ " no puede ser presentado porque hay un saldo a favor de: " + saldoAFavor;
						actualizarEstadoRegistro(docFormalizar.getPK().getIdeProceso(),
								DEnumEstadosPrMigracion.ERROR_PROCESANDO.getCodTipoEstado(), error);
					}

				} else {
					actualizarEstadoRegistro(docFormalizar.getPK().getIdeProceso(),
							DEnumEstadosPrMigracion.ERROR_PROCESANDO.getCodTipoEstado(), error);
				}
			} else {
				error = "El identificador de la persona rut de la tabla:" + docFormalizar.getAtt().getIdePersonaRut()
						+ " es diferente al identificador de la persona rut de la declaración correspondiente al nit: "
						+ nit;
				actualizarEstadoRegistro(docFormalizar.getPK().getIdeProceso(),
						DEnumEstadosPrMigracion.ERROR_PROCESANDO.getCodTipoEstado(), error);
			}
		} else {
			error = "El documento no existe en los borradores:" + docFormalizar.getAtt().getIdeDocumento();
			actualizarEstadoRegistro(docFormalizar.getPK().getIdeProceso(),
					DEnumEstadosPrMigracion.ERROR_PROCESANDO.getCodTipoEstado(), error);
		}

	}

	/**
	 *
	 * @param idDocumento
	 * @param numRepeticion
	 * @param idFormato
	 * @return
	 * @throws DExcepcion
	 */
	private Long extraerSaldoAFavor(Long idDocumento, Integer numRepeticion, Integer idFormato) throws DExcepcion {
		Long saldoAFavor = 0L;
		final IDDocumento documento = consultarDocumentoBorrador(idDocumento, numRepeticion, idFormato);
		final IDCasilla casSaldoAFavor = documento.getGrupos()
				.getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1)
				.getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT)
				.getValCasillaNeg(IDConceptosNegocioDil.CAS_NEG_TOTAL_SALDO_FAVOR);

		if (casSaldoAFavor != null) {
			saldoAFavor = casSaldoAFavor.getLongWrapper();
		}

		return saldoAFavor;
	}

	/**
	 *
	 * @param identificador
	 * @throws DExcepcion
	 */
	private void formalizarDeclaracion(DIdentificadorDoc identificador) throws DExcepcion {

		log.info("Se inicia la formalización de la declaración");

		actualizarEstadoDocumento(identificador.getIdFormato(), identificador.getVersion(),
				identificador.getIdDocumento(), identificador.getNumRepeticion());

		actualizarFecAcuseRecibo(identificador.getIdDocumento(), identificador.getNumRepeticion(),
				identificador.getIdFormato(), fechaTransaccion);

		// Se modifica el documento temporal.
		final IDDocumento documento = modificarDocumento(identificador.getIdDocumento(),
				identificador.getNumRepeticion(), identificador.getIdFormato());

		guardarDocumento(documento);

		moverDocumento(identificador.getIdDocumento(), identificador.getNumRepeticion(), identificador.getIdFormato(),
				identificador.getVersion());

		final DCmdSrvConsDocumentoMUISCA srvMUISCA = consultarDocumentoMuisca(identificador.getIdDocumento(),
				identificador.getNumRepeticion(), identificador.getIdFormato());

		final IDLogDocumento logDocumento = srvMUISCA.getMarcasDocObj(identificador.getIdFormato(),
				identificador.getVersion());

		// Se consultan nuevamente los identificadores una vez movida la
		// declaración.
		final DCmdSrvConsIdentificadorDoc consIdentificador = consultarIdentificador(identificador.getIdDocumento(),
				identificador.getNumRepeticion(), true, identificador.getIdFormato());

		registrarDocumentoGestionMasiva(consIdentificador.getIdentificadorDoc(), logDocumento);

		registrarDocEnTablaPivote(srvMUISCA.getDocumento());

		registrarDocEnSiat(identificador.getIdDocumento(), identificador.getNumRepeticion(),
				identificador.getIdFormato(), identificador.getVersion());
	}

	/**
	 * Método para generar el Autoadhesivo
	 *
	 * @return java.lang.String
	 * @throws DExcepcion
	 */
	private String generarAutoadhesivo() throws DExcepcion {

		log.info("Generando autoadhesivo");
		/**
		 * Se requiere el código externo del canal para la generación del
		 * autoadhesivo
		 */

		/** Se genera el autoadhesivo. */
		final DCmdSrvGenerarAutoadhesivoFormato srvAutoadhesivo = (DCmdSrvGenerarAutoadhesivoFormato) getServicio(
				"entradasalida.formatos.DCmdSrvGenerarAutoadhesivoFormato");
		srvAutoadhesivo.inicializar();
		srvAutoadhesivo.ejecutar();
		return srvAutoadhesivo.getNumAutoadhesivo();
	}

	/**
	 *
	 * @param servicio
	 * @return
	 */
	private IDComando getServicioNuevaTx(String servicio) {
		final DBusServiciosARQDelegateTxNueva delegado = new DBusServiciosARQDelegateTxNueva();
		try {
			return delegado.getComando(servicio);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param documento
	 * @throws DExcepcion
	 */
	private void guardarDocumento(IDDocumento documento) throws DExcepcion {

		log.info("Se guarda el documento on los cambios de autoadhesivo, fecha, valor total y verificación firma");
		final DCmdSrvCrearDocumentoESModoDil srvCrearDocTemporal = (DCmdSrvCrearDocumentoESModoDil) getServicio(
				"entradasalida.documentos.DCmdSrvCrearDocumentoESModoDil");
		String xmlDocumento;
		try {
			xmlDocumento = DDocumentoUtil.obtenerXmlDeDocumento(documento);
			srvCrearDocTemporal.inicializarActualizar(documento.getId(), documento.getRepeticion(), xmlDocumento,
					IDFormato.ENTRADA_RECAUDO_CAJA, IDModosDiligenciamiento.MODO_INICIAL);
			srvCrearDocTemporal.ejecutar();
		} catch (final DEntradaSalidaExcepcion e) {
			error = "error al actualizar el documento " + e.getMessage() + " " + e.getMensajeDetallado();
		}

	}

	/**
	 *
	 * @param documento
	 * @return
	 */
	public IDDocumento inicializarCasillasPago(IDDocumento documento) {

		log.info("Inicialización de casillas de pago");
		final IDOcurrencia ocurrencia = documento.getGrupos()
				.getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1)
				.getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT);

		for (final Integer casNeg : IDConceptosNegocioDil.CAS_NEG_SECCION_PAGOS) {
			final IDCasilla casPago = ocurrencia.getValCasillaNeg(casNeg);
			if (casPago != null)
				casPago.setValorEntero(0L);
		}
		return documento;
	}

	/**
	 *
	 * @param idDocumento
	 * @param numrepeticion
	 * @param idFormato
	 * @return
	 * @throws DExcepcion
	 */
	private IDDocumento modificarDocumento(Long idDocumento, Integer numRepeticion, Integer idFormato)
			throws DExcepcion {

		log.info("Se modifica documento en borrador");

		IDDocumento documento = consultarDocumentoBorrador(idDocumento, numRepeticion, idFormato);
		documento.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1)
				.getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT)
				.getValCasillaNeg(IDConceptosNegocioDil.CASNEG_VERIF_FIRMA_DECLARANTE).setValor(1);

		documento.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1)
				.getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT)
				.getValCasillaNeg(IDConceptosNegocioDil.CASNEG_AUTOADHESIVO).setValor(generarAutoadhesivo());

		documento.getGrupos().getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1)
				.getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT)
				.getValCasillaNeg(IDConceptosNegocioDil.CASNEG_FECHA_TRANSACCION).setValor(fechaTransaccion);

		documento = inicializarCasillasPago(documento);

		return documento;
	}

	/**
	 *
	 * @param idDocumento
	 * @param numRepeticion
	 * @param idFormato
	 * @param numVersionFormato
	 * @return
	 * @throws DExcepcion
	 */
	private void moverDocumento(Long idDocumento, Integer numRepeticion, Integer idFormato, Integer numVersionFormato)
			throws DExcepcion {
		log.info("Se mueve documento a definitivos");
		// Mover el documento a la DIAN
		final DCmdSrvMoverDocumentoES miComandoMover = (DCmdSrvMoverDocumentoES) getServicio(
				"entradasalida.documentos.DCmdSrvMoverDocumentoES");
		miComandoMover.inicializarGestionMasivaMigracion(idDocumento, numRepeticion, idFormato, numVersionFormato);
		miComandoMover.ejecutar();
	}

	/**
	 *
	 * @param nit
	 * @return
	 * @throws DExcepcion
	 */
	private Long obtenerPersonaNit(Long nit) throws DExcepcion {
		log.info("Obteniendo persona rut");
		final DCmdSrvConsRegistroRut consSrvRegRut = (DCmdSrvConsRegistroRut) getServicio("rut.DCmdSrvConsRegistroRut");
		consSrvRegRut.inicializarConsultarPorNIT(nit, DFechaUtils.getFechaNegocio(new Date()));
		consSrvRegRut.ejecutar();
		DRegistroRutTO toRegistroRut = consSrvRegRut.getRegistroRut();
		Long idePersonaRut = null;
		if(toRegistroRut != null){
			idePersonaRut = toRegistroRut.getPK().getIdePersonaRut();
			log.info("El tipo de contribuyente obtenido es: " + idePersonaRut);
		}
		else{
			log.error("La persona rut es nula para nit :" + nit);
		}
		return idePersonaRut;
	}

	/**
	 *
	 * @param idePersonaRut
	 * @throws DExcepcion
	 */
	private Integer obtenerTipoContribuyente(Long idePersonaRut) throws DExcepcion {
		log.info("Obteniendo tipo de contribuyente");
		final DCmdSrvConsRegistroRut consSrvRegRut = (DCmdSrvConsRegistroRut) getServicio("rut.DCmdSrvConsRegistroRut");
		consSrvRegRut.inicializarConsultarPorPK(new DRegistroRutPKTO(idePersonaRut));
		consSrvRegRut.ejecutar();
		DRegistroRutTO toRegistroRut = consSrvRegRut.getRegistroRut();
		Integer codTipoContribuyente = null;
		
		if(toRegistroRut != null){
			codTipoContribuyente = toRegistroRut.getAtt().getCodTipoContribuyente();
			log.info("El tipo de contribuyente obtenido es: "
					+ codTipoContribuyente);
		}
		else{
			log.error("La persona rut es nula para el identificador:" + idePersonaRut);
		}
		return codTipoContribuyente;
	}

	/**
	 *
	 * @param idDocumento
	 * @param numRepeticion
	 * @param idFormato
	 * @param numVersionFormato
	 * @throws DExcepcion
	 */
	private void registrarDocEnSiat(Long idDocumento, Integer numRepeticion, Integer idFormato,
			Integer numVersionFormato) throws DExcepcion {
		log.info("Registrando el documento en SIAT");
		// Realiza el registro del documento para que sea enviado al SIAT
		final DCmdSrvCrearLogEnvioSiat crearLogEnvio = (DCmdSrvCrearLogEnvioSiat) getServicio(
				"entradasalida.migracion.DCmdSrvCrearLogEnvioSiat");
		crearLogEnvio.inicializarRegistrarEntrada(idDocumento, numRepeticion, idFormato, numVersionFormato);
		crearLogEnvio.ejecutar();
	}

	/**
	 *
	 * @param docPivote
	 * @throws DExcepcion
	 */
	private void registrarDocEnTablaPivote(IDDocumento docPivote) throws DExcepcion {
		log.info("Registrando el documento en tabla Pivote");
		// Servicio que registra en la tabla pivote
		final DCmdSrvRegistrarTablaPivote pivote = (DCmdSrvRegistrarTablaPivote) getServicio(
				"entradasalida.control.DCmdSrvRegistrarTablaPivote");

		pivote.inicializar(docPivote);
		pivote.ejecutar();
	}

	/**
	 *
	 * @param identificador
	 * @param logDocumento
	 * @throws DExcepcion
	 */
	private void registrarDocumentoGestionMasiva(DIdentificadorDoc identificador, IDLogDocumento logDocumento)
			throws DExcepcion {

		log.info("Inicio de registro del documento en Gestión Masiva: " + identificador.getIdDocumento());
		final DDelegadoSrvConsFormato delegadoSrvFormato = new DDelegadoSrvConsFormato();
		IDFormato formato;
		try {
			formato = delegadoSrvFormato.consultarFormato(identificador.getIdFormato(), identificador.getVersion(),
					IDFormato.ENTRADA_RECAUDO_CAJA);

			final DFormatoEntradaTO toFormatoEntrada = formato.getFormatoEntradaTO();
			if (toFormatoEntrada.getAtt().getIndRegistrarDocGestion() == null || toFormatoEntrada.getAtt()
					.getIndRegistrarDocGestion().equals(IDConstantesFormatoEntrada.NO_REG_DOC_GESTION)) {
				final Integer ideRepositorio = DDocumentoUtil.getIdeRepositorio(identificador.getIdFormato());
				final DDelegadoOperacionesMasivas delegOperMasivas = new DDelegadoOperacionesMasivas(this.getContexto(),
						this.getEmisoresComando(), ideRepositorio);
				toFormatoEntrada.getAtt()
				.setIndRegistrarDocGestion(IDConstantesFormatoEntrada.REG_DOC_GESTION_SIN_VERIFICAR_HOMOL);
				final boolean registrado = delegOperMasivas.registrarDocGestion(identificador, toFormatoEntrada,
						logDocumento);

				if (!registrado) {
					error = "No se pudo registrar el documento en Gestión Masiva identificador: " + identificador.getIdDocumento();
					actualizarEstadoRegistro(docFormalizar.getPK().getIdeProceso(),
							DEnumEstadosPrMigracion.ERROR_PROCESANDO.getCodTipoEstado(), error);
				}

			}
			else{
				log.error("No Ingresa a registrar el documento a gestión masiva: " + identificador.getIdDocumento());
			}
		} catch (final DEntradaSalidaExcepcion e) {
			error = "error al registrar el documento en gestión masiva " + e.getMessage() + " "
					+ e.getMensajeDetallado();
		}
	}

	/**
	 *
	 * @param anioGravable
	 * @param nit
	 * @param ideFormato
	 * @param idModoNegocio
	 * @param estado
	 * @param idePersonaRut
	 * @return
	 * @throws DExcepcion
	 */
	private boolean validarBorradorDefinitivo(Integer anioGravable, Integer ideFormato, Integer idModoNegocio,
			Integer estado, Long idePersonaRut) throws DExcepcion {

		log.info("Validación del documento borrador");
		String errores = null;

		final List<Integer> listaFormatos = Arrays.asList(FORMATOS_PERMITIDOS);

		if (!idModoNegocio.equals(IDModosDiligenciamiento.MODO_INICIAL)) {
			errores = errores + "Modo Negocio: El modo negocio no corresponde a inicial - " + idModoNegocio;
		}

		if (!estado.equals(IDEstadosProcesamiento.IDE_ESTADO_VALIDADO)) {
			if (errores != null) {
				errores = errores + "\n";
			}

			errores = "Estado: El estado no corresponde a borrador definitivo - " + estado;
		}

		if (!listaFormatos.contains(ideFormato)) {
			if (errores != null) {
				errores = errores + "\n";
			}

			errores = "Formato: El formato no es permitido - " + ideFormato;
		} else {
			final Integer tipoContribuyente = obtenerTipoContribuyente(idePersonaRut);
			if(tipoContribuyente != null){
				if (ideFormato.equals(IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_IMAS_CTA_PROPIA)
						|| ideFormato.equals(IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_IMAS_EMPLEADO)
						|| ideFormato.equals(IDE_FORMATO_RENTA)) {

					if (!(anioGravable >= ANIO_GRAVABLE_DESDE && anioGravable <= ANIO_GRAVABLE_HASTA_RENTA)) {
						errores = "Año Gravable: La declaración de renta no corresponde a un año gravable autorizado - "
								+ anioGravable;
					}
				} else if (ideFormato.equals(IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS)) {
					if (tipoContribuyente.equals(new Integer(IDTipoContribuyentesRUT.NATURAL))) {
						if (!(anioGravable >= ANIO_GRAVABLE_DESDE && anioGravable <= ANIO_GRAVABLE_HASTA_RENTA)) {
							errores = "Año Gravable: La declaración de renta no corresponde a un año gravable autorizado - "
									+ anioGravable;
						}
					} else {
						if (!(anioGravable >= ANIO_GRAVABLE_DESDE && anioGravable <= ANIO_GRAVABLE_HASTA)) {
							errores = "Año Gravable: La declaración no corresponde a un año gravable autorizado - "
									+ anioGravable;
						}
					}

				} else {
					if (!(anioGravable >= ANIO_GRAVABLE_DESDE && anioGravable <= ANIO_GRAVABLE_HASTA)) {
						errores = "Año Gravable: La declaración no corresponde a un año gravable autorizado - "
								+ anioGravable;
					}
				}

			}
			else{
				errores = "Error al consultar el tipo de contribuyente en el RUT para el usuario con persona rut  - "
						+ idePersonaRut;
			}
		}
		error = errores;
		return errores != null;

	}

	/**
	 *
	 * @param idePersonaRut
	 * @param nitDocumento
	 *            nit que se encuentra en la declaración o recibo de pago.
	 * @return
	 * @throws DExcepcion
	 */
	private boolean validarPersona(Long idePersonaRut, Long nitDocumento) throws DExcepcion {
		final Long idePersonaRutDocumento = obtenerPersonaNit(nitDocumento);
		log.error("Las personas a comparar son idePersonaRut: " + idePersonaRut + " idePersonaRutDocumento " + idePersonaRutDocumento);
		return idePersonaRut.equals(idePersonaRutDocumento);
	}

	/**
	 *
	 * @param ideDocumentoRecibo
	 * @param numRepeticionRecibo
	 * @param ideFormatoRecibo
	 * @param ideDocumento
	 * @param ideFormato
	 * @return
	 * @throws DExcepcion
	 */
	private boolean validarReciboPago(Long ideDocumentoRecibo, Integer numRepeticionRecibo, Integer ideFormatoRecibo,
			Long ideDocumento, Integer ideFormato, Long idePersonaRut) throws DExcepcion {

		log.info("Se inicial la Validación del recibo de pago");

		String errores = null;

		final DCmdSrvConsIdentificadorDoc consIdentificador = consultarIdentificador(ideDocumentoRecibo,
				numRepeticionRecibo, true, ideFormato);

		final DIdentificadorDoc identificador = consIdentificador.getIdentificadorDoc();

		if (identificador != null) {
			final DCmdSrvConsDocumentoMUISCA consDocumentoMUISCA = consultarDocumentoMuisca(ideDocumentoRecibo,
					numRepeticionRecibo, ideFormatoRecibo);

			final IDDocumento documentoReciboPago = consDocumentoMUISCA.getDocumento();
			final Long idDocumentoDeclaracion = documentoReciboPago.getGrupos()
					.getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1)
					.getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT)
					.getValCasillaNeg(IDConceptosNegocioDil.CAS_NUM_FORMULARIO_DECLARACION).getLongWrapper();

			final Long nitReciboPago = documentoReciboPago.getGrupos()
					.getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1)
					.getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT)
					.getValorCasilla(IDConstantesFormato.IDE_CASILLA_NIT).getLongWrapper();

			final boolean personasIdenticas = validarPersona(idePersonaRut, nitReciboPago);

			if (personasIdenticas) {
				if (!idDocumentoDeclaracion.equals(ideDocumento)) {
					errores = "Recibo de Pago: El recibo de pago no esta relacionado con la declaración - "
							+ ideDocumentoRecibo + " - " + ideDocumento;
				} else {
					fechaTransaccion = null;

					if (identificador.getFecAcuseRecibo() == -1 || identificador.getFecAcuseRecibo() == 0) {
						fechaTransaccion = documentoReciboPago.getGrupos()
								.getGrupo(IDConstantesDiligenciamiento.IDE_GRUPO_DOC_HOJA_1)
								.getOcurrencia(IDConstantesDiligenciamiento.IDE_OCURRENCIA_DEFAULT)
								.getValorCasilla(IDConstantesFormato.IDE_CASILLA_FECHA_TRANSACCION).getLongWrapper();

					} else {
						fechaTransaccion = identificador.getFecAcuseRecibo();
					}

					if (fechaTransaccion != null && !fechaTransaccion.equals(0) && !fechaTransaccion.equals(-1)) {

						final Long fecha = new Long(fechaTransaccion.toString().substring(0, 8));
						final Integer tipoContribuyente = obtenerTipoContribuyente(idePersonaRut);

						log.info("La fecha de transacción del documento es: " + fecha + " el formato " + ideFormato
								+ " y el tipo de contribuyente es: " + tipoContribuyente);

						if (ideFormato.equals(IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_IMAS_CTA_PROPIA)
								|| ideFormato
										.equals(IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_IMAS_EMPLEADO)
								|| ideFormato.equals(IDE_FORMATO_RENTA)) {
							log.info("Validación Renta naturales pata el documento " + ideDocumento);
							if (fecha > FECHA_HASTA_RENTA) {
								errores = "Recibo de Pago: El recibo de pago de renta debe ser menor o igual a 29 de Diciembre de 2016 - "
										+ fechaTransaccion;
							}
						} else if (ideFormato
								.equals(IDConstantesDiligenciamiento.FORMULARIO_DECLARACION_RENTA_JURIDICOS)) {
							if (tipoContribuyente.equals(new Integer(IDTipoContribuyentesRUT.NATURAL))) {
								log.info("Validación Renta juridicos, contribuyente natural, para el documento "
										+ ideDocumento);
								if (fecha > FECHA_HASTA_RENTA) {
									errores = "Recibo de Pago: El recibo de pago de renta debe ser menor o igual a 29 de Diciembre de 2016 - "
											+ fechaTransaccion;
								}
							} else {
								log.info("Validación Renta juridicos, contribuyente juridico, para el documento "
										+ ideDocumento);
								if (fecha > FECHA_HASTA) {
									errores = "Recibo de Pago: El recibo de pago  debe ser menor o igual a 10 de enero de 2012 - "
											+ fechaTransaccion;
								}
							}

						} else {
							if (fecha > FECHA_HASTA) {
								errores = "Recibo de Pago: El recibo de pago  debe ser menor o igual a 10 de enero de 2012 - "
										+ fechaTransaccion;
							}
						}
					} else {
						errores = "Recibo de Pago: No se pudo obtener la fecha de transacción del recibo de pago - "
								+ ideDocumentoRecibo;
					}
				}
			} else {
				errores = "El identificador de la persona rut de la tabla:" + idePersonaRut
						+ " es diferente al identificador de la persona rut correspondiente al nit del recibo de pago: "
						+ nitReciboPago;
			}
		} else {
			errores = "Recibo de Pago: El recibo de pago no existe o no esta presentado - " + ideDocumentoRecibo;
		}

		error = errores;
		return errores != null;
	}

}
