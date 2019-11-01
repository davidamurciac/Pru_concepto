package co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.impl.oracle;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.general.persistencia.dao.DDAO;
import co.gov.dian.muisca.arquitectura.general.persistencia.dao.DSentenciaSQL;
import co.gov.dian.muisca.arquitectura.interfaces.IDDataSet;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.dataset.DDataSet;
import co.gov.dian.muisca.arquitectura.mensajes.DManipuladorMensaje;
import co.gov.dian.muisca.arquitectura.mensajes.IDArqMensajes;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAOSolicitudDeclaracionMincultura;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.DSolicitudDeclaracionMinculturaTO;


public class DDAOSolicitudDeclaracionMincultura extends DDAO implements IDDAOSolicitudDeclaracionMincultura {

	private Integer												dia;
	private String												formatos;
	private Map<Long, List<DSolicitudDeclaracionMinculturaTO>>	declaracionesPorNit;

	public void inicializarConsultaFormatoFechaAcuse(Integer dia, String formatos) {
		setTipoOperacion(CONSULTA_FORMATO_FECHA_ACUSE);

		this.dia = dia;
		this.formatos = formatos;
	}

	public Map<Long, List<DSolicitudDeclaracionMinculturaTO>> getDeclaracionesPorNit() {
		return declaracionesPorNit;
	}

	public boolean isEjecucionLibre() {
		return true;
	}

	public boolean validar() throws DValidarExcepcion {
		DManipuladorMensaje manipulador;
		Map<String, Object> parametros = new HashMap<String, Object>();
		String operacion = null;

		switch (getTipoOperacion()) {
		case CONSULTA_FORMATO_FECHA_ACUSE:
			operacion = "La consulta";

			parametros.put(this.getClass().getName() + ":validar:dia", dia);
			parametros.put(this.getClass().getName() + ":validar:formatos", formatos);

			break;
		default:

			break;
		}

		if (operacion == null) {
			manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
			String mensaje = manipulador.getMensaje();
			throw new DValidarExcepcion(mensaje, mensaje);
		}

		validarParametros(operacion, parametros);

		return true;
	}

	@Override
	public Map getSentenciasSQL() {
		Map<String, Object> sentencias = new HashMap<String, Object>();

		switch (getTipoOperacion()) {
		case CONSULTA_FORMATO_FECHA_ACUSE:
			//@formatter:off
			String sql = "SELECT DISTINCT " +
					" A.VAL_CASILLA_5 AS NUM_NIT, " +
					" A.IDE_DOCUMENTO AS IDE_DOCUMENTO_PRESENTADO, " +
					" A.NUM_REPETICION AS NUM_REPETICION_PRESENTADO, " +
					" A.IDE_FORMATO AS IDE_FORMATO_PRESENTADO, " +
					" A.NUM_VERSION_FORMATO AS NUM_VERSION_FORMATO_PRESENTADO, " +
					" A.VAL_CASILLA_980 AS VALOR_PRESENTADO, " +
					" C.IDE_SOLICITUD AS IDE_SOLICITUD, " +
					" D.IDE_DOCUMENTO AS IDE_DOCUMENTO_CARGADO, " +
					" D.NUM_REPETICION AS NUM_REPETICION_CARGADO, " +
					" D.IDE_FORMATO AS IDE_FORMATO_CARGADO, " +
					" D.NUM_VERSION_FORMATO AS NUM_VERSION_FORMATO_CARGADO " +
					"FROM EYS_DOCUMENTOS_DOCS A, " +
					" DIL_SOLICITUDES_DECLARACION B, " +
					" EYS_AUDITORIA_REGISTROS C, " +
					" EYS_DOCUMENTOS_DOCS D " +
					"WHERE A.IDE_FORMATO IN (" + formatos + ") " +
					" AND SUBSTR(TO_CHAR(A.FEC_ACUSE_RECIBO), 1, 8) = :DIA " +
					" AND A.IDE_DOCUMENTO = B.IDE_DOCUMENTO " +
					" AND A.NUM_REPETICION = B.NUM_REPETICION " +
					" AND A.IDE_FORMATO = B.IDE_FORMATO " +
					" AND A.NUM_VERSION_FORMATO = B.NUM_VERSION_FORMATO " +
					" AND B.IDE_DOCUMENTO_CARGA = C.IDE_SOLICITUD " +
					" AND C.IDE_DOCUMENTO = D.IDE_DOCUMENTO " +
					" AND C.NUM_REPETICION_DOCUMENTO = D.NUM_REPETICION " +
					" AND C.IDE_FORMATO = D.IDE_FORMATO " +
					" AND C.NUM_VERSION_FORMATO = D.NUM_VERSION_FORMATO " +
					"ORDER BY A.VAL_CASILLA_5 ASC, " +
					" A.IDE_FORMATO ASC, " +
					" A.IDE_DOCUMENTO ASC, " +
					" A.NUM_REPETICION DESC, " +
					" D.IDE_DOCUMENTO ASC, " +
					" D.NUM_REPETICION DESC";
			//@formatter:on

			sentencias.put("sentencia1", sql);

			break;
		}

		return sentencias;
	}

	@Override
	public IDDataSet consultar() throws SQLException {
		switch (getTipoOperacion()) {

		case CONSULTA_FORMATO_FECHA_ACUSE:
			return consultaFormatoFechaAcuse();
		}

		return null;
	}

	private IDDataSet consultaFormatoFechaAcuse() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		sentencia.setString("DIA", dia.toString());
		sentencia.ejecutar();

		DDataSet resultado = sentencia.getDataSet();

		cargarSolicitudDeclaracion(resultado);

		return resultado;
	}

	private void cargarSolicitudDeclaracion(IDDataSet resultado) throws SQLException {
		declaracionesPorNit = new LinkedHashMap<Long, List<DSolicitudDeclaracionMinculturaTO>>();

		if (resultado.getNumeroFilas() == 0) {
			return;
		}

		resultado.primero();

		do {
			DSolicitudDeclaracionMinculturaTO to = cargarDeclaracionMincultura(resultado);

			Long numNit = to.getNumNit();
			List<DSolicitudDeclaracionMinculturaTO> declaracionesNit = declaracionesPorNit.get(numNit);

			if (declaracionesNit == null) {
				declaracionesNit = new LinkedList<DSolicitudDeclaracionMinculturaTO>();
				declaracionesPorNit.put(numNit, declaracionesNit);
			}

			declaracionesNit.add(to);
		}
		while (resultado.siguiente());

		resultado.primero();
	}

	private DSolicitudDeclaracionMinculturaTO cargarDeclaracionMincultura(IDDataSet resultado) throws SQLException {
		DSolicitudDeclaracionMinculturaTO to = new DSolicitudDeclaracionMinculturaTO();

		if (resultado.getValorPorNombre("NUM_NIT") != null) {
			to.setNumNit(resultado.getLongWrapper("NUM_NIT"));
		}

		if (resultado.getValorPorNombre("IDE_DOCUMENTO_PRESENTADO") != null) {
			to.setIdeDocumentoPresentado(resultado.getLongWrapper("IDE_DOCUMENTO_PRESENTADO"));
		}

		if (resultado.getValorPorNombre("NUM_REPETICION_PRESENTADO") != null) {
			to.setNumRepeticionPresentado(resultado.getIntWrapper("NUM_REPETICION_PRESENTADO"));
		}

		if (resultado.getValorPorNombre("IDE_FORMATO_PRESENTADO") != null) {
			to.setIdeFormatoPresentado(resultado.getIntWrapper("IDE_FORMATO_PRESENTADO"));
		}

		if (resultado.getValorPorNombre("NUM_VERSION_FORMATO_PRESENTADO") != null) {
			to.setNumVersionFormatoPresentado(resultado.getIntWrapper("NUM_VERSION_FORMATO_PRESENTADO"));
		}

		if (resultado.getValorPorNombre("VALOR_PRESENTADO") != null) {
			to.setValorPresentado(resultado.getLongWrapper("VALOR_PRESENTADO"));
		}

		if (resultado.getValorPorNombre("IDE_SOLICITUD") != null) {
			to.setIdeSolicitud(resultado.getLongWrapper("IDE_SOLICITUD"));
		}

		if (resultado.getValorPorNombre("IDE_DOCUMENTO_CARGADO") != null) {
			to.setIdeDocumentoCargado(resultado.getLongWrapper("IDE_DOCUMENTO_CARGADO"));
		}

		if (resultado.getValorPorNombre("NUM_REPETICION_CARGADO") != null) {
			to.setNumRepeticionCargado(resultado.getIntWrapper("NUM_REPETICION_CARGADO"));
		}

		if (resultado.getValorPorNombre("IDE_FORMATO_CARGADO") != null) {
			to.setIdeFormatoCargado(resultado.getIntWrapper("IDE_FORMATO_CARGADO"));
		}

		if (resultado.getValorPorNombre("NUM_VERSION_FORMATO_CARGADO") != null) {
			to.setNumVersionFormatoCargado(resultado.getIntWrapper("NUM_VERSION_FORMATO_CARGADO"));
		}

		return to;
	}

	@Override
	public int eliminar() throws SQLException {
		throw new UnsupportedOperationException("Metodo no implementado");
	}

	@Override
	public int guardar() throws SQLException {
		throw new UnsupportedOperationException("Metodo no implementado");
	}

}
