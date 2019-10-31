package co.gov.dian.muisca.diligenciamientomasivo.acciones.tareasprogramadas.mincultura.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.delegados.DBusDelegateFactory;
import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.to.interfaz.DBSArchivo;
import co.gov.dian.muisca.arquitectura.general.to.interfaz.DBSArchivos;
import co.gov.dian.muisca.arquitectura.general.to.interfaz.DBSSolicitud;
import co.gov.dian.muisca.arquitectura.general.to.interfaz.DBSUsuariosKey;
import co.gov.dian.muisca.arquitectura.general.to.interfaz.DUsuarioKey;
import co.gov.dian.muisca.arquitectura.interfaces.IDBusAccionesDelegate;
import co.gov.dian.muisca.arquitectura.interfaces.IDBusDelegateFactory;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicio;
import co.gov.dian.muisca.arquitectura.util.DFechaUtils;
import co.gov.dian.muisca.bandejasalida.acciones.solicitud.DCmdAccBSRegistrarSolicitud;
import co.gov.dian.muisca.diligenciamiento.general.constantes.IDConstantesDiligenciamiento;
import co.gov.dian.muisca.diligenciamiento.general.helper.DHelperPoliticasDil;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.tareasprogramadas.mincultura.DCmdAccGenerarArchivoMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.buses.DBusServiciosDelegateDIMBTxNueva;
import co.gov.dian.muisca.diligenciamientomasivo.constantes.tareasprogramadas.mincultura.IDConstantesMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.general.constantes.IDConstantesDiligenciamientoMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DDetRegDiaMinculturaAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRegDiarioMinCulturaAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRegDiarioMinCulturaTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionMinculturaTO;
import co.gov.dian.muisca.diligenciamientomasivo.recursos.DAdminRecursosDilMasivo;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvActRegDiarioMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvConsLstRegDiarioMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvConsPdfDocumentos;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura.DCmdSrvCrearRegDiarioMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.solicitudesdeclaracion.DCmdSrvConsLstDeclaracionMincultura;
import co.gov.dian.muisca.entradasalida.constantes.dian.IDConstantesDIAN;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsDocumentoMUISCA;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsIdentificadorDoc;
import co.gov.dian.muisca.rut.general.to.DRegistroRutTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsRegistroRut;


/**
 * Implementación de las operaciones que efectúa el comando de acción DCmdAccGenerarArchivoMinCultura
 * 
 * @author jbernalv1, dmurciac
 */
public class DCmdAccGenerarArchivoMinCulturaImpl extends DCmdAccGenerarArchivoMinCultura {

	private static final long	serialVersionUID	= 2161902256927669598L;
	private static final Logger	log					= Logger.getLogger(DCmdAccGenerarArchivoMinCulturaImpl.class);
	private String				ipServidor			= "";

	/**
	 * Método que se ejecuta en ambiente no transaccional
	 */
	protected void ejecutarComandoSinTransaccion() throws DExcepcion {
		isOk = true;
		exito = true;

		try {
			ipServidor = InetAddress.getLocalHost().getHostAddress();
		}
		catch (UnknownHostException ex) {
			ex.printStackTrace();
		}

		try {
			String formatos = consultarParametrosPolitica(IDConstantesDiligenciamiento.POLITICA_FORMATOS_MINCULTURA, IDConstantesDiligenciamiento.NOM_PARAM_MINCULTURA_FORMATOS, "&").get(0);

			List<Integer> diasPendientesProcesar = consultarDiasPendientesProcesar();

			for (Integer dia : diasPendientesProcesar) {
				procesarDocumentosDia(dia, formatos);
			}
		}
		catch (DExcepcion ex) {
			mensajeError = ex.getMessage();
			mensajeErrorDetallado = ex.getMensajeDetallado();

			isOk = false;
			exito = false;
		}
	}

	/**
	 * Método que se ejecuta en ambiente transaccional
	 */
	protected void ejecutarComando() {
		log.info("--->>>> DCmdAccGenerarArchivoMinCultura: Ejecución " + (exito ? "exitosa" : "con errores"));

		isOk = exito;
	}

	private void procesarDocumentosDia(Integer dia, String formatos) {
		List<DDetRegDiaMinculturaAttTO> listaAttDetRegDiario = new LinkedList<DDetRegDiaMinculturaAttTO>();
		DRegDiarioMinCulturaTO regErrorPrevio = consultarErrorRegDiarioMinCultura(dia);

		try {
			Map<Long, List<DSolicitudDeclaracionMinculturaTO>> declaraciones = consultarDeclaracionesDia(dia, formatos);

			if (declaraciones == null || declaraciones.isEmpty()) {
				registroDiario(dia, "No se encontraron documentos presentados para el día: " + dia + ".", true, null, regErrorPrevio);
			}
			else {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ZipOutputStream zos = new ZipOutputStream(baos);
				StringBuilder resumen = new StringBuilder();

				for (Entry<Long, List<DSolicitudDeclaracionMinculturaTO>> entrada : declaraciones.entrySet()) {
					Long numNit = entrada.getKey();
					List<DSolicitudDeclaracionMinculturaTO> solicitudesNit = entrada.getValue();
					Set<Long> procesados = new LinkedHashSet<Long>();

					for (DSolicitudDeclaracionMinculturaTO to : solicitudesNit) {
						log.info("Nit --->>>> " + numNit + " Presentado -> " + to.getIdeDocumentoPresentado() + " Cargado -> " + to.getIdeDocumentoCargado());

						String rutaArchivo = "" + dia + "/" + numNit + "/";
						StringBuffer errorSolicitud = new StringBuffer();

						adicionarArchivosDocumento(procesados, zos, rutaArchivo, to.getIdeDocumentoPresentado(), to.getNumRepeticionPresentado(), to.getIdeFormatoPresentado(), errorSolicitud);
						adicionarArchivosDocumento(procesados, zos, rutaArchivo, to.getIdeDocumentoCargado(), to.getNumRepeticionCargado(), to.getIdeFormatoCargado(), errorSolicitud);

						resumen.append(String.format("%20d;%20d;%20d;%20d\r\n", to.getNumNit(), to.getIdeDocumentoPresentado(), to.getValorPresentado(), to.getIdeDocumentoCargado()));

						if (errorSolicitud.length() > 0) {
							DDetRegDiaMinculturaAttTO attDetError = new DDetRegDiaMinculturaAttTO();
							attDetError.setNumNit(numNit.toString());
							attDetError.setNumSolicitud(to.getIdeSolicitud().toString());
							attDetError.setNumDocumentoPresentado(to.getIdeDocumentoPresentado().toString());
							attDetError.setNumDocumentoCarga(to.getIdeDocumentoCargado().toString());
							attDetError.setValDetalleProceso(errorSolicitud.toString());
							listaAttDetRegDiario.add(attDetError);
						}
					}
				}

				if (resumen.length() > 0) {
					resumen.insert(0, "--------------------;--------------------;--------------------;--------------------\r\n");
					resumen.insert(0, "        NIT         ;  DOC. PRESENTADO   ;       VALOR        ;    DOC. CARGADO\r\n");

					adicionarArchivo(zos, ("" + dia + ".txt"), resumen.toString().getBytes(), null);
				}

				try {
					zos.flush();
					zos.close();
					baos.flush();
					baos.close();
				}
				catch (IOException ex) {
					throw new Exception("Error cerrando archivo Zip. Día: " + dia + ". " + ex.getMessage() + ".");
				}

				boolean diaOk = (listaAttDetRegDiario.size() == 0);
				byte[] bytesZip = baos.toByteArray();

				if (diaOk || regErrorPrevio == null) {
					crearTareaBandejaSalida(dia, bytesZip);
				}

				registroDiario(dia, "Ejecución " + (diaOk ? "exitosa" : "con errores") + ". Zip: (" + (int) Math.ceil(bytesZip.length / 1024.0) + " kB).", diaOk, listaAttDetRegDiario, regErrorPrevio);
			}
		}
		catch (Exception ex) {
			registroDiario(dia, "Ejecución con errores. " + ex.getMessage(), false, listaAttDetRegDiario, regErrorPrevio);
		}
	}

	private void agregarInfoError(StringBuffer infoError, String error) {
		if (infoError.length() > 0) {
			infoError.append(" ");
		}

		infoError.append(error);
	}

	private void adicionarArchivosDocumento(Set<Long> procesados, ZipOutputStream zos, String ruta, Long ideDocumento, Integer numRepeticion, Integer ideFormato, StringBuffer infoError) {
		if (procesados.contains(ideDocumento)) {
			return;
		}

		procesados.add(ideDocumento);

		try {
			DCmdSrvConsIdentificadorDoc consDocumentos = (DCmdSrvConsIdentificadorDoc) getServicio("entradasalida.documentos.DCmdSrvConsIdentificadorDoc", true);
			consDocumentos.inicializarConsPorId(ideDocumento, numRepeticion, true, ideFormato);
			consDocumentos.ejecutar();
			DIdentificadorDoc identificadorDoc = consDocumentos.getIdentificadorDoc();

			DCmdSrvConsDocumentoMUISCA consDocumentoMUISCA = (DCmdSrvConsDocumentoMUISCA) getServicio("entradasalida.documentos.DCmdSrvConsDocumentoMUISCA", true);
			consDocumentoMUISCA.inicializar(ideDocumento, numRepeticion, ideFormato);
			consDocumentoMUISCA.ejecutar();
			IDDocumento documento = consDocumentoMUISCA.getDocumento();

			adicionarArchivo(zos, ruta + ideDocumento + ".xml", consDocumentoMUISCA.getDocumentoXml().getBytes(), infoError);
			adicionarArchivo(zos, ruta + ideDocumento + ".pdf", consultarPdfDocumento(documento, identificadorDoc), infoError);
		}
		catch (Exception ex) {
			agregarInfoError(infoError, "Error en la consulta de información del documento: " + ideDocumento + "-" + numRepeticion + " (" + ideFormato + "). " + ex.getMessage());
		}
	}

	private void adicionarArchivo(ZipOutputStream zos, String archivo, byte[] bytes, StringBuffer infoError) {
		try {
			zos.putNextEntry(new ZipEntry(archivo));
			zos.write(bytes);
			zos.flush();
			zos.closeEntry();
		}
		catch (IOException ex) {
			ex.printStackTrace();

			if (infoError != null) {
				agregarInfoError(infoError, "Error en la adición de entrada al Zip, archivo: " + archivo + ". " + ex.getMessage() + ".");
			}
		}
	}

	private List<Integer> consultarDiasPendientesProcesar() throws DExcepcion {
		final Integer diaInicial = IDConstantesMinCultura.FECHA_INICIO_PROCESO;
		final Integer diaFinal = DFechaUtils.getFechaNegocio(new Date(System.currentTimeMillis() - (24 * 60 * 60 * 1000))); // Día anterior
		List<Integer> listaDiasPendientes = new LinkedList<Integer>();
		List<Integer> listaDiasProcesados = new LinkedList<Integer>();
		Integer dia = diaInicial;

		DCmdSrvConsLstRegDiarioMinCultura srvConsRegsDiarios = (DCmdSrvConsLstRegDiarioMinCultura) getServicio("diligenciamientomasivo.mincultura.DCmdSrvConsLstRegDiarioMinCultura", true);
		DRegDiarioMinCulturaTO toRegDiarioMinCultura = new DRegDiarioMinCulturaTO();
		DRegDiarioMinCulturaAttTO attRegDiarioMinCultura = new DRegDiarioMinCulturaAttTO();
		attRegDiarioMinCultura.setCodEstado(IDConstantesMinCultura.ESTADO_CORRECTO);
		toRegDiarioMinCultura.setAtt(attRegDiarioMinCultura);
		srvConsRegsDiarios.inicializarConsultaGenerica(toRegDiarioMinCultura);
		srvConsRegsDiarios.ejecutar();

		for (DRegDiarioMinCulturaTO regDiario : srvConsRegsDiarios.getColeccionRegDiarioMinCultura()) {
			listaDiasProcesados.add(DFechaUtils.getFechaNegocio(regDiario.getAtt().getFecRegistro()));
		}

		for (int i = 1; dia.compareTo(diaFinal) <= 0; i++) {
			// Excluye los dias procesados
			if (!listaDiasProcesados.contains(dia)) {
				listaDiasPendientes.add(dia);
			}

			dia = DFechaUtils.sumarDiasCalendario(diaInicial, i);
		}

		return listaDiasPendientes;
	}

	private DRegDiarioMinCulturaTO consultarErrorRegDiarioMinCultura(Integer dia) {
		try {
			DCmdSrvConsLstRegDiarioMinCultura srvConsRegsDiarios = (DCmdSrvConsLstRegDiarioMinCultura) getServicio("diligenciamientomasivo.mincultura.DCmdSrvConsLstRegDiarioMinCultura", true);
			DRegDiarioMinCulturaTO toRegDiarioMinCultura = new DRegDiarioMinCulturaTO();
			DRegDiarioMinCulturaAttTO attRegDiarioMinCultura = new DRegDiarioMinCulturaAttTO();
			attRegDiarioMinCultura.setFecRegistro(new Timestamp(DFechaUtils.getFecha(dia).getTime()));
			attRegDiarioMinCultura.setCodEstado(IDConstantesMinCultura.ESTADO_CON_ERROR);
			toRegDiarioMinCultura.setAtt(attRegDiarioMinCultura);
			srvConsRegsDiarios.inicializarConsultaGenerica(toRegDiarioMinCultura);
			srvConsRegsDiarios.ejecutar();

			Collection<DRegDiarioMinCulturaTO> registros = srvConsRegsDiarios.getColeccionRegDiarioMinCultura();

			if (!registros.isEmpty()) {
				return registros.iterator().next();
			}
		}
		catch (DExcepcion ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * Se consultan las tablas dil_solicitudes_declaracion y de documentos para traer los formularios de declaración de MinCultura, agrupados por NIT
	 */
	private Map<Long, List<DSolicitudDeclaracionMinculturaTO>> consultarDeclaracionesDia(Integer dia, String formatos) throws DExcepcion {
		try {
			DCmdSrvConsLstDeclaracionMincultura srv = (DCmdSrvConsLstDeclaracionMincultura) getServicio("diligenciamientomasivo.solicitudesdeclaracion.DCmdSrvConsLstDeclaracionMincultura", true);
			srv.inicializarConsultaFormatoFechaAcuse(dia, formatos);
			srv.ejecutar();

			Map<Long, List<DSolicitudDeclaracionMinculturaTO>> declaracionesPorNit = srv.getDeclaracionesPorNit();
			log.info("Dia -> " + dia + ". Documentos de formatos -> (" + formatos + ") consultados : " + declaracionesPorNit.size());

			return declaracionesPorNit;
		}
		catch (Exception ex) {
			ex.printStackTrace();

			throw new DExcepcion("Error en la consulta de declaraciones. Día: " + dia + ". " + ex.getMessage() + ".", null);
		}
	}

	private List<String> consultarParametrosPolitica(int idPolitica, String nombreParametro, String separador) throws DExcepcion {
		DHelperPoliticasDil politicasDil = new DHelperPoliticasDil(idPolitica);
		StringTokenizer listaTkParametros = politicasDil.obtenerParametroPolitica(nombreParametro, (separador != null ? separador : IDConstantesDiligenciamiento.TOKEN_DEFAULT_DIL));
		List<String> valoresParametros = new LinkedList<String>();

		while (listaTkParametros.hasMoreTokens()) {
			valoresParametros.add(listaTkParametros.nextToken());
		}

		return valoresParametros;
	}

	private DComandoServicio getServicio(String nombreServicio, boolean isTransaccional) throws DExcepcion {
		DBusServiciosDelegateDIMBTxNueva delegado = new DBusServiciosDelegateDIMBTxNueva();
		delegado.setContextoSeguridad(this.getContexto().getContextoSeguridad());
		delegado.setTransaccional(isTransaccional);

		try {
			return (DComandoServicio) delegado.getComando(nombreServicio);
		}
		catch (Exception ex) {
			ex.printStackTrace();

			throw new DExcepcion("Error en la obtención del servicio: " + nombreServicio + ". " + ex.getMessage(), null);
		}
	}

	private void registroDiario(Integer dia, String mensaje, boolean ok, List<DDetRegDiaMinculturaAttTO> listaAttDetRegDiario, DRegDiarioMinCulturaTO toRegDiario) {
		try {
			if (toRegDiario == null) {
				toRegDiario = new DRegDiarioMinCulturaTO();
				DRegDiarioMinCulturaAttTO attRegDiario = new DRegDiarioMinCulturaAttTO();
				attRegDiario.setCodEstado(ok ? IDConstantesMinCultura.ESTADO_CORRECTO : IDConstantesMinCultura.ESTADO_CON_ERROR);
				attRegDiario.setDirServidorProceso(ipServidor);
				attRegDiario.setFecCambio(new Timestamp(new Date().getTime()));
				attRegDiario.setFecRegistro(new Timestamp(DFechaUtils.getFecha(dia).getTime()));
				attRegDiario.setValDescripcionProceso(mensaje);
				toRegDiario.setAtt(attRegDiario);

				DCmdSrvCrearRegDiarioMinCultura srvCrearRegistro = (DCmdSrvCrearRegDiarioMinCultura) getServicio("diligenciamientomasivo.mincultura.DCmdSrvCrearRegDiarioMinCultura", true);

				if (listaAttDetRegDiario == null || listaAttDetRegDiario.size() == 0) {
					srvCrearRegistro.inicializar(toRegDiario);
				}
				else {
					srvCrearRegistro.inicializar(toRegDiario, listaAttDetRegDiario);
				}

				srvCrearRegistro.ejecutar();
			}
			else {
				DRegDiarioMinCulturaAttTO attRegDiario = toRegDiario.getAtt();
				attRegDiario.setCodEstado(ok ? IDConstantesMinCultura.ESTADO_CORRECTO : IDConstantesMinCultura.ESTADO_CON_ERROR);
				attRegDiario.setDirServidorProceso(ipServidor);
				attRegDiario.setFecCambio(new Timestamp(new Date().getTime()));
				attRegDiario.setValDescripcionProceso(mensaje + "   * (Día reprocesado)");

				DCmdSrvActRegDiarioMinCultura srvActRegistro = (DCmdSrvActRegDiarioMinCultura) getServicio("diligenciamientomasivo.mincultura.DCmdSrvActRegDiarioMinCultura", true);
				srvActRegistro.inicializar(toRegDiario);
				srvActRegistro.ejecutar();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void crearTareaBandejaSalida(Integer dia, byte[] bytesArchivo) {
		try {
			// ****** Servicio de bandeja de salida *****
			DBSSolicitud solicitud = new DBSSolicitud();
			solicitud.setFuncion(IDConstantesDiligenciamientoMasivo.IDE_FUNCION);

			// TODO se debe definir la organización que debe ir de remitente
			DUsuarioKey remitente = new DUsuarioKey();
			remitente.setIdePersona(71l);
			remitente.setIdeOrganizacion(IDConstantesDIAN.IDE_ORGANIZACION);
			solicitud.setRemitente(remitente);
			solicitud.setCarpeta("ArchivoMC-" + DFechaUtils.getFechaFormatoVigente(dia));

			// Autorizado
			// TODO hacer consulta de los funcionarios con el rol de consulta de bandeja de salida y colocarle el archivo .zip a cada uno
			Integer ideOrganizacion = consultarOrganizacion();
			List<Long> idesPersona = consultarIdePersonas();
			DBSUsuariosKey listAutorizados = new DBSUsuariosKey();

			for (Long idePersona : idesPersona) {
				DUsuarioKey autorizado = new DUsuarioKey();
				autorizado.setIdeOrganizacion(ideOrganizacion);
				// autorizado.setIdePersona(new Long(26673125));
				autorizado.setIdePersona(idePersona);
				listAutorizados.adicionarUsuario(autorizado);
			}

			solicitud.setAutorizados(listAutorizados);

			DBSArchivo archivo = new DBSArchivo();
			archivo.setNombre(dia + IDConstantesDiligenciamientoMasivo.NOM_EXTENSION_ZIP);
			archivo.setArchivo(bytesArchivo);
			archivo.setDescripcion(DAdminRecursosDilMasivo.obtenerRecurso("mensaje_archivo_bandeja") + DFechaUtils.getFechaFormatoVigente(dia));
			DBSArchivos listArchivos = new DBSArchivos();
			listArchivos.adicionarArchivo(archivo);
			solicitud.setArchivos(listArchivos);

			IDBusDelegateFactory factory = DBusDelegateFactory.getInstance();
			IDBusAccionesDelegate delegado = factory.getDelegadoAcciones("bandejasalida.solicitud.DCmdAccBSRegistrarSolicitud");
			DCmdAccBSRegistrarSolicitud cmdRegistro = (DCmdAccBSRegistrarSolicitud) delegado.getComando("bandejasalida.solicitud.DCmdAccBSRegistrarSolicitud");
			cmdRegistro.getContexto().setContextoSeguridad(getContexto().getContextoSeguridad());
			cmdRegistro.inicializar(solicitud);
			cmdRegistro.ejecutar();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private Integer consultarOrganizacion() throws DExcepcion {
		List<String> parametros = consultarParametrosPolitica(IDConstantesDiligenciamiento.POLITICA_FORMATOS_MINCULTURA, IDConstantesDiligenciamiento.NOM_PARAM_MINCULTURA_ORGANIZACION, null);

		if (!parametros.isEmpty()) {
			return new Integer(parametros.iterator().next());
		}

		return null;
	}

	private List<Long> consultarIdePersonas() throws DExcepcion {
		List<String> parametros = consultarParametrosPolitica(IDConstantesDiligenciamiento.POLITICA_FORMATOS_MINCULTURA, IDConstantesDiligenciamiento.NOM_PARAM_MINCULTURA_NITS_PERSONAS, null);
		List<Long> idePersonas = new LinkedList<Long>();

		for (String param : parametros) {
			DRegistroRutTO regRut = consultarRegistroRutPorNit(Long.valueOf(param));

			if (regRut != null) {
				idePersonas.add(regRut.getPK().getIdePersonaRut());
			}
		}

		return idePersonas;
	}

	private DRegistroRutTO consultarRegistroRutPorNit(Long nit) throws DExcepcion {
		DCmdSrvConsRegistroRut srvConsRegistroRut = (DCmdSrvConsRegistroRut) getServicio("rut.DCmdSrvConsRegistroRut", true);
		srvConsRegistroRut.inicializarConsultarPorNIT(nit);
		srvConsRegistroRut.ejecutar();

		return srvConsRegistroRut.getRegistroRut();
	}

	private byte[] consultarPdfDocumento(IDDocumento documento, DIdentificadorDoc identificadorDocumento) throws DExcepcion {
		try {
			DCmdSrvConsPdfDocumentos consDocumento = (DCmdSrvConsPdfDocumentos) getServicio("diligenciamientomasivo.mincultura.DCmdSrvConsPdfDocumentos", false);
			consDocumento.inicializar(documento, identificadorDocumento);
			consDocumento.ejecutar();

			byte[] bytesPdf = consDocumento.getPdfBytes();

			if (bytesPdf == null) {
				throw new Exception(consDocumento.getTrazaError());
			}

			return bytesPdf;
		}
		catch (Exception ex) {
			ex.printStackTrace();

			throw new DExcepcion("Error al consultar el pdf del documento: " + documento.getId() + ". " + ex.getMessage(), null);
		}
	}

}
