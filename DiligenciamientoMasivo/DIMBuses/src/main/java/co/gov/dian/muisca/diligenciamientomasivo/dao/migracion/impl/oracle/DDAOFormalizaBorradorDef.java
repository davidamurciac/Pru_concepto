/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.dao.migracion.impl.oracle;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.general.persistencia.dao.DDAO;
import co.gov.dian.muisca.arquitectura.general.persistencia.dao.DSentenciaSQL;
import co.gov.dian.muisca.arquitectura.interfaces.IDDataSet;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.dataset.DDataSet;
import co.gov.dian.muisca.arquitectura.mensajes.DManipuladorMensaje;
import co.gov.dian.muisca.arquitectura.mensajes.IDArqMensajes;
import co.gov.dian.muisca.diligenciamientomasivo.dao.migracion.IDDAOFormalizaBorradorDef;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.DFormalizaBorradorDefTO;

/**
 * <p>
 * Titulo: Proyecto MUISCA
 * </p>
 * <p>
 * Descripcion: Objeto de acceso a datos para FormalizaBorradorDef.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: DIAN
 * </p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 *
 *          <pre>
 * $Log[10]:$
 *          </pre>
 */
public class DDAOFormalizaBorradorDef extends DDAO implements IDDAOFormalizaBorradorDef {
	/** colección de objetos DFormalizaBorradorDefTO */
	private Collection<DFormalizaBorradorDefTO> objetosFormalizaBorradorDef;
	/** Objeto de transporte de FormalizaBorradorDef */
	private DFormalizaBorradorDefTO toFormalizaBorradorDef;
	/** Llave primaria de FormalizaBorradorDef */
	private DFormalizaBorradorDefPKTO pkFormalizaBorradorDef;
	/** Atributos de FormalizaBorradorDef */
	private DFormalizaBorradorDefAttTO attFormalizaBorradorDef;

	private Integer indEstado;

	private String error;

	/**
	 * Actualiza los datos de FormalizaBorradorDef.
	 *
	 * @return Un int con el número de registros actualizados
	 * @throws SQLException
	 *             Si ocurre un error de base de datos al actualizar
	 */
	private int actualizar() throws SQLException {
		final DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresObjeto(sentencia);
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		final int resultado = sentencia.getRegistrosAfectados();
		if (resultado <= 0) {
			throw new SQLException("No se ha actualizado ningún registro");
		}
		if (resultado > 1) {
			throw new SQLException("Se intentó actualizar más de un registro");
		}
		return resultado;
	}

	/**
	 * Asigna los valores no nulos de un objeto.
	 *
	 * @param unaSentencia
	 *            Sentencia para asignación
	 * @throws SQLException
	 *             Si ocurre un error al asignar los valores
	 */
	private void asignarValoresGenericos(DSentenciaSQL unaSentencia) throws SQLException {
		if (pkFormalizaBorradorDef != null) {
			if (pkFormalizaBorradorDef.getIdeProceso() != null) {
				unaSentencia.setLong("IDE_PROCESO", pkFormalizaBorradorDef.getIdeProceso());
			}
		}

		if (attFormalizaBorradorDef != null) {
			if (attFormalizaBorradorDef.getIdeDocumento() != null) {
				unaSentencia.setLong("IDE_DOCUMENTO", attFormalizaBorradorDef.getIdeDocumento());
			}
			if (attFormalizaBorradorDef.getNumRepeticion() != null) {
				unaSentencia.setInt("NUM_REPETICION", attFormalizaBorradorDef.getNumRepeticion());
			}
			if (attFormalizaBorradorDef.getIdeFormato() != null) {
				unaSentencia.setInt("IDE_FORMATO", attFormalizaBorradorDef.getIdeFormato());
			}
			if (attFormalizaBorradorDef.getNumVersionFormato() != null) {
				unaSentencia.setShort("NUM_VERSION_FORMATO", attFormalizaBorradorDef.getNumVersionFormato());
			}
			if (attFormalizaBorradorDef.getIdeDocumentoRecibo() != null) {
				unaSentencia.setLong("IDE_DOCUMENTO_RECIBO", attFormalizaBorradorDef.getIdeDocumentoRecibo());
			}
			if (attFormalizaBorradorDef.getNumRepeticionRecibo() != null) {
				unaSentencia.setInt("NUM_REPETICION_RECIBO", attFormalizaBorradorDef.getNumRepeticionRecibo());
			}
			if (attFormalizaBorradorDef.getIdeFormatoRecibo() != null) {
				unaSentencia.setInt("IDE_FORMATO_RECIBO", attFormalizaBorradorDef.getIdeFormatoRecibo());
			}
			if (attFormalizaBorradorDef.getNumVersionFormatoRecibo() != null) {
				unaSentencia.setShort("NUM_VERSION_FORMATO_RECIBO",
						attFormalizaBorradorDef.getNumVersionFormatoRecibo());
			}
			if (attFormalizaBorradorDef.getIdePersonaRut() != null) {
				unaSentencia.setLong("IDE_PERSONA_RUT", attFormalizaBorradorDef.getIdePersonaRut());
			}
			if (attFormalizaBorradorDef.getIndEstado() != null) {
				unaSentencia.setInt("IND_ESTADO", attFormalizaBorradorDef.getIndEstado());
			}
			if (attFormalizaBorradorDef.getTxtObservaciones() != null) {
				unaSentencia.setString("TXT_OBSERVACIONES", attFormalizaBorradorDef.getTxtObservaciones());
			}
			if (attFormalizaBorradorDef.getFecCambio() != null) {
				unaSentencia.setTimestamp("FEC_CAMBIO", attFormalizaBorradorDef.getFecCambio());
			}
			if (attFormalizaBorradorDef.getIdeUsuarioCambio() != null) {
				unaSentencia.setLong("IDE_USUARIO_CAMBIO", attFormalizaBorradorDef.getIdeUsuarioCambio());
			}
		}
	}

	/**
	 * Asigna todos los valores de un objeto.
	 *
	 * @param unaSentencia
	 *            Sentencia para asignación
	 * @throws SQLException
	 *             Si ocurre un error al asignar los valores
	 */
	private void asignarValoresObjeto(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setInt("IND_ESTADO", attFormalizaBorradorDef.getIndEstado());
		unaSentencia.setString("TXT_OBSERVACIONES", attFormalizaBorradorDef.getTxtObservaciones());
		unaSentencia.setTimestamp("FEC_CAMBIO", attFormalizaBorradorDef.getFecCambio());
		unaSentencia.setLong("IDE_USUARIO_CAMBIO", attFormalizaBorradorDef.getIdeUsuarioCambio());

		if (tipoOperacion == ACTUALIZAR || tipoOperacion == CREAR) {
			unaSentencia.setLong("IDE_DOCUMENTO", attFormalizaBorradorDef.getIdeDocumento());
			unaSentencia.setInt("NUM_REPETICION", attFormalizaBorradorDef.getNumRepeticion());
			unaSentencia.setInt("IDE_FORMATO", attFormalizaBorradorDef.getIdeFormato());
			unaSentencia.setShort("NUM_VERSION_FORMATO", attFormalizaBorradorDef.getNumVersionFormato());
			unaSentencia.setLong("IDE_DOCUMENTO_RECIBO", attFormalizaBorradorDef.getIdeDocumentoRecibo());
			unaSentencia.setInt("NUM_REPETICION_RECIBO", attFormalizaBorradorDef.getNumRepeticionRecibo());
			unaSentencia.setInt("IDE_FORMATO_RECIBO", attFormalizaBorradorDef.getIdeFormatoRecibo());
			unaSentencia.setShort("NUM_VERSION_FORMATO_RECIBO", attFormalizaBorradorDef.getNumVersionFormatoRecibo());
			unaSentencia.setLong("IDE_PERSONA_RUT", attFormalizaBorradorDef.getIdePersonaRut());
		}

	}

	/**
	 * Asigna los valores para pk en una sentencia SQL.
	 *
	 * @param unaSentencia
	 *            Sentencia para asignación
	 * @throws SQLException
	 *             Si ocurre un error al asignar los valores
	 */
	private void asignarValoresPK(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setLong("IDE_PROCESO", pkFormalizaBorradorDef.getIdeProceso());
	}

	/**
	 * Construye un objeto FormalizaBorradorDef con base en el data set.
	 *
	 * @param resultado
	 *            Contenedor de los datos
	 * @throws SQLException
	 *             Si ocurre un error de base de datos al cargar el objeto
	 */
	private void cargarFormalizaBorradorDef(IDDataSet resultado) throws SQLException {
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		toFormalizaBorradorDef = completarFormalizaBorradorDef(resultado);
	}

	/**
	 * Construye objetos FormalizaBorradorDef con base en el data set.
	 *
	 * @param resultado
	 *            Contenedor de los datos
	 * @throws SQLException
	 *             Si ocurre un error de base de datos al cargar los objetos
	 */
	private void cargarObjetosFormalizaBorradorDef(IDDataSet resultado) throws SQLException {
		objetosFormalizaBorradorDef = new ArrayList<DFormalizaBorradorDefTO>();
		toFormalizaBorradorDef = null;
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		do {
			final DFormalizaBorradorDefTO objeto = completarFormalizaBorradorDef(resultado);
			objetosFormalizaBorradorDef.add(objeto);
		} while (resultado.siguiente());
		resultado.primero();
	}

	/**
	 * Construye un objeto FormalizaBorradorDef con base en el data set. El data
	 * set debe contener datos en la posición actual.
	 *
	 * @param resultado
	 *            Contenedor de los datos
	 * @return Un FormalizaBorradorDef
	 * @throws SQLException
	 *             Si ocurre un error de base de datos al cargar el objeto
	 */
	private DFormalizaBorradorDefTO completarFormalizaBorradorDef(IDDataSet resultado) throws SQLException {
		// Conformar el objeto PK
		final DFormalizaBorradorDefPKTO pk = new DFormalizaBorradorDefPKTO();
		// java.lang.Long
		pk.setIdeProceso(resultado.getLongWrapper("IDE_PROCESO"));

		// Conformar el objeto Att
		final DFormalizaBorradorDefAttTO att = new DFormalizaBorradorDefAttTO();
		// java.lang.Long
		att.setIdeDocumento(resultado.getLongWrapper("IDE_DOCUMENTO"));
		// java.lang.Integer
		att.setNumRepeticion(resultado.getIntWrapper("NUM_REPETICION"));
		// java.lang.Integer
		att.setIdeFormato(resultado.getIntWrapper("IDE_FORMATO"));
		// java.lang.Byte
		att.setNumVersionFormato(resultado.getByteWrapper("NUM_VERSION_FORMATO"));
		// java.lang.Long
		att.setIdeDocumentoRecibo(resultado.getLongWrapper("IDE_DOCUMENTO_RECIBO"));
		// java.lang.Integer
		att.setNumRepeticionRecibo(resultado.getIntWrapper("NUM_REPETICION_RECIBO"));
		// java.lang.Integer
		att.setIdeFormatoRecibo(resultado.getIntWrapper("IDE_FORMATO_RECIBO"));
		// java.lang.Byte
		att.setNumVersionFormatoRecibo(resultado.getByteWrapper("NUM_VERSION_FORMATO_RECIBO"));
		// java.lang.Long
		att.setIdePersonaRut(resultado.getLongWrapper("IDE_PERSONA_RUT"));
		// java.lang.String
		att.setIndEstado(resultado.getInt("IND_ESTADO"));
		// java.lang.String
		att.setTxtObservaciones(resultado.getString("TXT_OBSERVACIONES"));
		// java.sql.Timestamp
		att.setFecCambio((Timestamp) resultado.getValorPorNombre("FEC_CAMBIO"));
		// java.lang.Long
		if (resultado.getValorPorNombre("IDE_USUARIO_CAMBIO") != null) {
			att.setIdeUsuarioCambio(resultado.getLongWrapper("IDE_USUARIO_CAMBIO"));
		}

		// Conformar el TO
		final DFormalizaBorradorDefTO to = new DFormalizaBorradorDefTO();
		to.setPK(pk);
		to.setAtt(att);
		return to;
	}

	/**
	 * Consulta genérica de los datos de FormalizaBorradorDef.
	 *
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException
	 *             Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultaGenerica() throws SQLException {
		final DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresGenericos(sentencia);
		sentencia.ejecutar();
		final DDataSet resultado = sentencia.getDataSet();
		cargarObjetosFormalizaBorradorDef(resultado);
		return resultado;
	}

	/**
	 * Consulta los datos de FormalizaBorradorDef.
	 *
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException
	 *             Si ocurre un error de base de datos al consultar
	 */
	@Override
	public IDDataSet consultar() throws SQLException {
		switch (getTipoOperacion()) {
		case CONSULTAR_POR_PK:
			return consultarPorPK();
		case CONSULTA_GENERICA:
			return consultaGenerica();
		case CONSULTA_TODOS:
			return consultaTodos();
		}
		return null;
	}

	/**
	 * Actualiza los datos de FormalizaBorradorDef.
	 *
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException
	 *             Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorPK() throws SQLException {
		final DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		final DDataSet resultado = sentencia.getDataSet();
		cargarFormalizaBorradorDef(resultado);
		return resultado;
	}

	/**
	 * Consulta Todos.
	 *
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException
	 *             Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultaTodos() throws SQLException {
		final DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		sentencia.ejecutar();
		final DDataSet resultado = sentencia.getDataSet();
		cargarObjetosFormalizaBorradorDef(resultado);
		return resultado;
	}

	/**
	 * Crea un registro de FormalizaBorradorDef.
	 *
	 * @return Un int con el número de registros creados
	 * @throws SQLException
	 *             Si ocurre un error de base de datos al crear
	 */
	private int crear() throws SQLException {
		final DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		pkFormalizaBorradorDef.setIdeProceso(sentencia.getSiguienteNumero(SEQ_FORMALIZA_BORRADOR).longValue());
		asignarValoresObjeto(sentencia);
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		final int resultado = sentencia.getRegistrosAfectados();
		if (resultado <= 0) {
			throw new SQLException("No se ha creado ningún registro");
		}
		if (resultado > 1) {
			throw new SQLException("Se intentó crear más de un registro");
		}
		return resultado;
	}

	/**
	 * Elimina registros de FormalizaBorradorDef.
	 *
	 * @return Un int con el número de registros eliminados
	 * @throws SQLException
	 *             Si ocurre un error de base de datos al eliminar
	 */
	@Override
	public int eliminar() throws SQLException {
		final DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		return sentencia.getRegistrosAfectados();
	}

	/**
	 * obtenerConsultaGenerica
	 *
	 * @return StringBuffer
	 */
	private String generarFiltroGenerico() {
		final StringBuffer condiciones = new StringBuffer();
		final String y = " AND ";
		boolean append = false;

		if (pkFormalizaBorradorDef != null) {

			if (pkFormalizaBorradorDef.getIdeProceso() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_PROCESO=:IDE_PROCESO");
				append = true;

			}
		}

		if (attFormalizaBorradorDef != null) {

			if (attFormalizaBorradorDef.getIdeDocumento() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_DOCUMENTO=:IDE_DOCUMENTO");
				append = true;

			}
			if (attFormalizaBorradorDef.getNumRepeticion() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_REPETICION=:NUM_REPETICION");
				append = true;

			}
			if (attFormalizaBorradorDef.getIdeFormato() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_FORMATO=:IDE_FORMATO");
				append = true;

			}
			if (attFormalizaBorradorDef.getNumVersionFormato() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_VERSION_FORMATO=:NUM_VERSION_FORMATO");
				append = true;

			}
			if (attFormalizaBorradorDef.getIdeDocumentoRecibo() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_DOCUMENTO_RECIBO=:IDE_DOCUMENTO_RECIBO");
				append = true;

			}
			if (attFormalizaBorradorDef.getNumRepeticionRecibo() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_REPETICION_RECIBO=:NUM_REPETICION_RECIBO");
				append = true;

			}
			if (attFormalizaBorradorDef.getIdeFormatoRecibo() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_FORMATO_RECIBO=:IDE_FORMATO_RECIBO");
				append = true;

			}
			if (attFormalizaBorradorDef.getNumVersionFormatoRecibo() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_VERSION_FORMATO_RECIBO=:NUM_VERSION_FORMATO_RECIBO");
				append = true;

			}
			if (attFormalizaBorradorDef.getIdePersonaRut() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_PERSONA_RUT=:IDE_PERSONA_RUT");
				append = true;

			}
			if (attFormalizaBorradorDef.getIndEstado() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IND_ESTADO=:IND_ESTADO");
				append = true;

			}
			if (attFormalizaBorradorDef.getTxtObservaciones() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("TXT_OBSERVACIONES=:TXT_OBSERVACIONES");
				append = true;

			}
			if (attFormalizaBorradorDef.getFecCambio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("FEC_CAMBIO=:FEC_CAMBIO");
				append = true;

			}
			if (attFormalizaBorradorDef.getIdeUsuarioCambio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO");
				append = true;

			}
		}

		return condiciones.toString();
	}

	/**
	 * Devuelve la colección de objetos FormalizaBorradorDef que se hayan
	 * consultado.
	 *
	 * @return Un Collection con objetos DFormalizaBorradorDefTO
	 */
	public Collection<DFormalizaBorradorDefTO> getColeccionFormalizaBorradorDef() {
		return objetosFormalizaBorradorDef;
	}

	/**
	 * Devuelve el objeto FormalizaBorradorDef que se haya consultado.
	 *
	 * @return Un objeto DFormalizaBorradorDefTO
	 */
	public DFormalizaBorradorDefTO getFormalizaBorradorDef() {
		return toFormalizaBorradorDef;
	}

	/**
	 *
	 * @return
	 */
	public DFormalizaBorradorDefPKTO getPkFormalizaBorradorDef() {
		return pkFormalizaBorradorDef;
	}

	/**
	 * Devuelve las sentencias sql a ejecutar, dependiendo del tipo de operación
	 * a realizar.
	 *
	 * @return Un Map de Strings con la relación de sentencias sql
	 */
	@Override
	public Map<String, String> getSentenciasSQL() {
		final Map<String, String> m = new HashMap<String, String>();
		final StringBuffer sql = new StringBuffer();
		switch (getTipoOperacion()) {
		case CREAR:
			sql.append("insert into DIM_FORMALIZA_BORRADORES_DEF")
					.append(" (IDE_PROCESO, IDE_DOCUMENTO, NUM_REPETICION, IDE_FORMATO, NUM_VERSION_FORMATO, IDE_DOCUMENTO_RECIBO, NUM_REPETICION_RECIBO, IDE_FORMATO_RECIBO, NUM_VERSION_FORMATO_RECIBO, IDE_PERSONA_RUT, IND_ESTADO, TXT_OBSERVACIONES, FEC_CAMBIO, IDE_USUARIO_CAMBIO)")
					.append(" VALUES (:IDE_PROCESO, :IDE_DOCUMENTO, :NUM_REPETICION, :IDE_FORMATO, :NUM_VERSION_FORMATO, :IDE_DOCUMENTO_RECIBO, :NUM_REPETICION_RECIBO, :IDE_FORMATO_RECIBO, :NUM_VERSION_FORMATO_RECIBO, :IDE_PERSONA_RUT, :IND_ESTADO, :TXT_OBSERVACIONES, :FEC_CAMBIO, :IDE_USUARIO_CAMBIO)");
			m.put("sentencia1", sql.toString());
			break;
		case ACTUALIZAR:
			sql.append("update DIM_FORMALIZA_BORRADORES_DEF")
					.append(" set IDE_DOCUMENTO=:IDE_DOCUMENTO, NUM_REPETICION=:NUM_REPETICION, IDE_FORMATO=:IDE_FORMATO, NUM_VERSION_FORMATO=:NUM_VERSION_FORMATO, IDE_DOCUMENTO_RECIBO=:IDE_DOCUMENTO_RECIBO, NUM_REPETICION_RECIBO=:NUM_REPETICION_RECIBO, IDE_FORMATO_RECIBO=:IDE_FORMATO_RECIBO, NUM_VERSION_FORMATO_RECIBO=:NUM_VERSION_FORMATO_RECIBO, IDE_PERSONA_RUT=:IDE_PERSONA_RUT, IND_ESTADO=:IND_ESTADO, TXT_OBSERVACIONES=:TXT_OBSERVACIONES, FEC_CAMBIO=:FEC_CAMBIO, IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO")
					.append(" where IDE_PROCESO=:IDE_PROCESO");
			m.put("sentencia1", sql.toString());
			break;
		case ELIMINAR:
			sql.append("delete from DIM_FORMALIZA_BORRADORES_DEF").append(" where IDE_PROCESO=:IDE_PROCESO");
			m.put("sentencia1", sql.toString());
			break;
		case CONSULTAR_POR_PK:
			sql.append("select * from DIM_FORMALIZA_BORRADORES_DEF").append(" where IDE_PROCESO=:IDE_PROCESO");
			m.put("sentencia1", sql.toString());
			break;
		case CONSULTA_GENERICA:
			sql.append("select * from DIM_FORMALIZA_BORRADORES_DEF where ").append(generarFiltroGenerico());
			m.put("sentencia1", sql.toString());
			break;
		case CONSULTA_TODOS:
			sql.append("select * from DIM_FORMALIZA_BORRADORES_DEF  ").append(" where IND_ESTADO IN(1,2)");
			m.put("sentencia1", sql.toString());
			break;
		case ACTUALIZAR_ESTADO:
			sql.append("update DIM_FORMALIZA_BORRADORES_DEF")
					.append(" set IND_ESTADO=:IND_ESTADO, TXT_OBSERVACIONES=:TXT_OBSERVACIONES, FEC_CAMBIO=:FEC_CAMBIO, IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO")
					.append(" where IDE_PROCESO=:IDE_PROCESO");
			m.put("sentencia1", sql.toString());
			break;
		}
		return m;
	}

	/**
	 * Guarda los datos de FormalizaBorradorDef.
	 *
	 * @return @return Un int con el número de registros guardados
	 * @throws SQLException
	 *             Si ocurre un error de base de datos al guardar
	 */
	@Override
	public int guardar() throws SQLException {
		switch (getTipoOperacion()) {
		case CREAR:
			return crear();
		case ACTUALIZAR_ESTADO:
		case ACTUALIZAR:
			return actualizar();
		}
		return -1;
	}

	/**
	 * Inicializa la actualización de FormalizaBorradorDef.
	 *
	 * @param toFormalizaBorradorDef
	 *            Objeto de Transporte de FormalizaBorradorDef
	 */
	public void inicializarActualizar(DFormalizaBorradorDefTO toFormalizaBorradorDef) {
		setTipoOperacion(ACTUALIZAR);
		this.toFormalizaBorradorDef = toFormalizaBorradorDef;
		if (toFormalizaBorradorDef != null) {
			pkFormalizaBorradorDef = this.toFormalizaBorradorDef.getPK();
			attFormalizaBorradorDef = this.toFormalizaBorradorDef.getAtt();
		}
	}

	/**
	 * Inicializa la actualización de FormalizaBorradorDef.
	 *
	 * @param toFormalizaBorradorDef
	 *            Objeto de Transporte de FormalizaBorradorDef
	 */
	public void inicializarActualizarEstado(DFormalizaBorradorDefTO toFormalizaBorradorDef) {
		setTipoOperacion(ACTUALIZAR_ESTADO);
		this.toFormalizaBorradorDef = toFormalizaBorradorDef;
		if (toFormalizaBorradorDef != null) {
			pkFormalizaBorradorDef = this.toFormalizaBorradorDef.getPK();
			attFormalizaBorradorDef = this.toFormalizaBorradorDef.getAtt();
		}
	}

	/**
	 * Inicializa la consulta genérica de FormalizaBorradorDef.
	 *
	 * @param attFormalizaBorradorDef
	 *            Atributos de FormalizaBorradorDef
	 */
	public void inicializarConsultaGenerica(DFormalizaBorradorDefTO toFormalizaBorradorDef) {
		setTipoOperacion(CONSULTA_GENERICA);

		this.toFormalizaBorradorDef = toFormalizaBorradorDef;
		if (toFormalizaBorradorDef != null) {
			pkFormalizaBorradorDef = this.toFormalizaBorradorDef.getPK();
			attFormalizaBorradorDef = this.toFormalizaBorradorDef.getAtt();
		}
	}

	/**
	 * Inicializa la consulta por llave primaria.
	 *
	 * @param pkFormalizaBorradorDef
	 *            Llave primaria de FormalizaBorradorDef
	 */
	public void inicializarConsultarPorPK(DFormalizaBorradorDefPKTO pkFormalizaBorradorDef) {
		setTipoOperacion(CONSULTAR_POR_PK);
		this.pkFormalizaBorradorDef = pkFormalizaBorradorDef;
	}

	/**
	 * Inicializa la consulta de todos los borradores definitivos a formalizar.
	 *
	 * @param attFormalizaBorradorDef
	 *            Atributos de FormalizaBorradorDef
	 */
	public void inicializarConsultaTodos() {
		setTipoOperacion(CONSULTA_TODOS);
	}

	/**
	 * Inicializa la creaciónn de FormalizaBorradorDef.
	 *
	 * @param toFormalizaBorradorDef
	 *            Objeto de Transporte de FormalizaBorradorDef
	 */
	public void inicializarCrear(DFormalizaBorradorDefTO toFormalizaBorradorDef) {
		setTipoOperacion(CREAR);
		this.toFormalizaBorradorDef = toFormalizaBorradorDef;
		if (toFormalizaBorradorDef != null) {
			pkFormalizaBorradorDef = this.toFormalizaBorradorDef.getPK();
			attFormalizaBorradorDef = this.toFormalizaBorradorDef.getAtt();
		}
	}

	/**
	 * Inicializa la eliminación de FormalizaBorradorDef.
	 *
	 * @param pkFormalizaBorradorDef
	 *            Llave primaria de FormalizaBorradorDef
	 */
	public void inicializarEliminar(DFormalizaBorradorDefPKTO pkFormalizaBorradorDef) {
		setTipoOperacion(ELIMINAR);
		this.pkFormalizaBorradorDef = pkFormalizaBorradorDef;
	}

	/**
	 * Indica si el DAO es de ejecución libre.
	 *
	 * @return true si es de ejecución libre; false de lo contrario
	 */
	public boolean isEjecucionLibre() {
		return false;
	}

	/**
	 * Método para validar los parámetros inicializados, invocado por el
	 * administrador de persistencia.
	 *
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion
	 *             Si los parámetros no son válidos
	 * @todo COMPLEMENTAR
	 */
	public boolean validar() throws DValidarExcepcion {
		DManipuladorMensaje manipulador;
		String operacion = null;
		final Map<String, Object> parametros = new HashMap<String, Object>();
		switch (getTipoOperacion()) {
		case CREAR:
			operacion = "la creación";
			break;
		case ACTUALIZAR:
			operacion = "la actualización";
			break;
		case ELIMINAR:
			operacion = "la eliminación";
			break;
		case CONSULTAR_POR_PK:
			operacion = "la consulta";
			break;
		case CONSULTA_GENERICA:
			operacion = "la consulta";
			break;
		case CONSULTA_TODOS:
			operacion = "la consulta";
			break;
		case ACTUALIZAR_ESTADO:
			operacion = "la actualización";
			break;
		}

		if (operacion == null) {
			manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
			final String mensaje = manipulador.getMensaje();
			throw new DValidarExcepcion(mensaje, mensaje);
		}

		if (tipoOperacion == CREAR || tipoOperacion == ACTUALIZAR) {
			parametros.put(this.getClass().getName() + ":validar:toFormalizaBorradorDef", toFormalizaBorradorDef);
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef", attFormalizaBorradorDef);

			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIdeDocumento()",
					attFormalizaBorradorDef.getIdeDocumento());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getNumRepeticion()",
					attFormalizaBorradorDef.getNumRepeticion());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIdeFormato()",
					attFormalizaBorradorDef.getIdeFormato());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getNumVersionFormato()",
					attFormalizaBorradorDef.getNumVersionFormato());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIdeDocumentoRecibo()",
					attFormalizaBorradorDef.getIdeDocumentoRecibo());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getNumRepeticionRecibo()",
					attFormalizaBorradorDef.getNumRepeticionRecibo());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIdeFormatoRecibo()",
					attFormalizaBorradorDef.getIdeFormatoRecibo());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getNumVersionFormatoRecibo()",
					attFormalizaBorradorDef.getNumVersionFormatoRecibo());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIdePersonaRut()",
					attFormalizaBorradorDef.getIdePersonaRut());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIndEstado()",
					attFormalizaBorradorDef.getIndEstado());
		}

		if (tipoOperacion == CONSULTAR_POR_PK || tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName() + ":validar:pkFormalizaBorradorDef", pkFormalizaBorradorDef);
			parametros.put(this.getClass().getName() + ":validar:pkFormalizaBorradorDef.getIdeProceso()",
					pkFormalizaBorradorDef.getIdeProceso());
		}

		if (tipoOperacion == CONSULTA_GENERICA) {
			parametros.put(this.getClass().getName() + ":validar:toFormalizaBorradorDef", toFormalizaBorradorDef);
		}

		if (tipoOperacion == ACTUALIZAR_ESTADO) {
			parametros.put(this.getClass().getName() + ":validar:toFormalizaBorradorDef", toFormalizaBorradorDef);
			parametros.put(this.getClass().getName() + ":validar:pkFormalizaBorradorDef", pkFormalizaBorradorDef);
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef", attFormalizaBorradorDef);

			parametros.put(this.getClass().getName() + ":validar:pkFormalizaBorradorDef.getIdeProceso()",
					pkFormalizaBorradorDef.getIdeProceso());
			parametros.put(this.getClass().getName() + ":validar:attFormalizaBorradorDef.getIndEstado()",
					attFormalizaBorradorDef.getIndEstado());

		}

		validarParametros(operacion, parametros);
		return true;
	}
}
