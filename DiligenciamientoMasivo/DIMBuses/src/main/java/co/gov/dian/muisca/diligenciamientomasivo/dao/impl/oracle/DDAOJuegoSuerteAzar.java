/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.dao.impl.oracle;

import java.sql.*;
import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.general.persistencia.dao.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.dataset.*;
import co.gov.dian.muisca.arquitectura.mensajes.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.juegosdesuerteyazar.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de acceso a datos para JuegoSuerteAzar.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: DIAN</p>
 *
 * @author dmahechav
 * @version 1.0
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDAOJuegoSuerteAzar extends DDAO implements IDDAOJuegoSuerteAzar {
	/** colección de objetos DJuegoSuerteAzarTO */
	private Collection objetosJuegoSuerteAzar;
	/** Objeto de transporte de JuegoSuerteAzar */
	private DJuegoSuerteAzarTO toJuegoSuerteAzar;
	/** Llave primaria de JuegoSuerteAzar */
	private DJuegoSuerteAzarPKTO pkJuegoSuerteAzar;
	/** Atributos de JuegoSuerteAzar */
	private DJuegoSuerteAzarAttTO attJuegoSuerteAzar;
	private Integer codEstado;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkJuegoSuerteAzar Llave primaria de JuegoSuerteAzar
	 */
	public void inicializarConsultarPorPK(DJuegoSuerteAzarPKTO pkJuegoSuerteAzar) {
		setTipoOperacion(CONSULTAR_POR_PK);
		this.pkJuegoSuerteAzar = pkJuegoSuerteAzar;
	}

	/**
	 * Inicializa la creaciï¿½n de JuegoSuerteAzar.
	 * @param toJuegoSuerteAzar Objeto de Transporte de JuegoSuerteAzar
	 */
	public void inicializarCrear(DJuegoSuerteAzarTO toJuegoSuerteAzar) {
		setTipoOperacion(CREAR);
		this.toJuegoSuerteAzar = toJuegoSuerteAzar;
		if (toJuegoSuerteAzar != null) {
			pkJuegoSuerteAzar = this.toJuegoSuerteAzar.getPK();
			attJuegoSuerteAzar = this.toJuegoSuerteAzar.getAtt();
		}
	}

	/**
	 * Inicializa la actualizaciï¿½n de JuegoSuerteAzar.
	 * @param toJuegoSuerteAzar Objeto de Transporte de JuegoSuerteAzar
	 */
	public void inicializarActualizar(DJuegoSuerteAzarTO toJuegoSuerteAzar) {
		setTipoOperacion(ACTUALIZAR);
		this.toJuegoSuerteAzar = toJuegoSuerteAzar;
		if (toJuegoSuerteAzar != null) {
			pkJuegoSuerteAzar = this.toJuegoSuerteAzar.getPK();
			attJuegoSuerteAzar = this.toJuegoSuerteAzar.getAtt();
		}
	}

	/**
	 * Inicializa la eliminaciï¿½n de JuegoSuerteAzar.
	 * @param pkJuegoSuerteAzar Llave primaria de JuegoSuerteAzar
	 */
	public void inicializarEliminar(DJuegoSuerteAzarPKTO pkJuegoSuerteAzar) {
		setTipoOperacion(ELIMINAR);
		this.pkJuegoSuerteAzar = pkJuegoSuerteAzar;
	}

	/**
	 * Inicializa la consulta genérica de JuegoSuerteAzar.
	 * @param attJuegoSuerteAzar Atributos de JuegoSuerteAzar
	 */
	public void inicializarConsultaGenerica(DJuegoSuerteAzarTO toJuegoSuerteAzar) {
		setTipoOperacion(CONSULTA_GENERICA);

		this.toJuegoSuerteAzar = toJuegoSuerteAzar;
		if (toJuegoSuerteAzar != null) {
			pkJuegoSuerteAzar = this.toJuegoSuerteAzar.getPK();
			attJuegoSuerteAzar = this.toJuegoSuerteAzar.getAtt();
		}

	}
	/**
	 * Inicializa la actualizaciï¿½n del estado de la declaración JuegoSuerteAzar.
	 * @param attJuegoSuerteAzar 
	 *    */
	public void inicializarActualizarEstado(DJuegoSuerteAzarPKTO pkJuegoSuerteAzar, Integer codEstado) {
		setTipoOperacion(ACTUALIZAR_ESTADO);
		this.pkJuegoSuerteAzar = pkJuegoSuerteAzar;
		this.codEstado = codEstado;
	}

	/**
	 * Devuelve las sentencias sql a ejecutar, dependiendo del tipo de operación a realizar.
	 * @return Un Map de Strings con la relación de sentencias sql
	 */
	public Map getSentenciasSQL() {
		Map m = new HashMap();
		StringBuffer sql = new StringBuffer();
		switch (getTipoOperacion()) {
		case CREAR:
			sql.append("insert into DIL_JUEGOS_SUERTE_AZAR")
			.append(" (IDE_DOCUMENTO, NUM_REPETICION, IDE_DOCUMENTO_CARGA, NUM_REPETICION_CARGA, COD_ESTADO, IDE_USUARIO_CAMBIO, FEC_CAMBIO, IDE_PERSONA, IDE_TAREA)")
			.append(" VALUES (:IDE_DOCUMENTO, :NUM_REPETICION, :IDE_DOCUMENTO_CARGA, :NUM_REPETICION_CARGA, :COD_ESTADO, :IDE_USUARIO_CAMBIO, :FEC_CAMBIO, :IDE_PERSONA, :IDE_TAREA)");
			m.put("sentencia1", sql.toString());
			break;
		case ACTUALIZAR:
			sql.append("update DIL_JUEGOS_SUERTE_AZAR")
			.append(" set IDE_DOCUMENTO_CARGA=:IDE_DOCUMENTO_CARGA, NUM_REPETICION_CARGA=:NUM_REPETICION_CARGA, COD_ESTADO=:COD_ESTADO, IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO, FEC_CAMBIO=:FEC_CAMBIO, IDE_PERSONA=:IDE_PERSONA, IDE_TAREA=:IDE_TAREA")
			.append(" where IDE_DOCUMENTO=:IDE_DOCUMENTO and NUM_REPETICION=:NUM_REPETICION");
			m.put("sentencia1", sql.toString());
			break;
		case ELIMINAR:
			sql.append("delete from DIL_JUEGOS_SUERTE_AZAR")
			.append(" where IDE_DOCUMENTO=:IDE_DOCUMENTO and NUM_REPETICION=:NUM_REPETICION");
			m.put("sentencia1", sql.toString());
			break;
		case CONSULTAR_POR_PK:
			sql.append("select * from DIL_JUEGOS_SUERTE_AZAR")
			.append(" where IDE_DOCUMENTO=:IDE_DOCUMENTO and NUM_REPETICION=:NUM_REPETICION");
			m.put("sentencia1", sql.toString());
			break;

		case CONSULTA_GENERICA:
			sql.append("select * from DIL_JUEGOS_SUERTE_AZAR where ")
			.append(generarFiltroGenerico());
			m.put("sentencia1", sql.toString());
			break;
		case ACTUALIZAR_ESTADO:
			sql.append("update DIL_JUEGOS_SUERTE_AZAR")
			.append(" set COD_ESTADO=:COD_ESTADO")
			.append(" where IDE_DOCUMENTO=:IDE_DOCUMENTO and NUM_REPETICION=:NUM_REPETICION");
			m.put("sentencia1", sql.toString());
			break;
		}
		return m;
	}


	/**
	 * obtenerConsultaGenerica
	 *
	 * @return StringBuffer
	 */
	private String generarFiltroGenerico() {

		StringBuffer condiciones = new StringBuffer();
		String y = " AND ";
		boolean append = false;




		if (pkJuegoSuerteAzar != null) {

			if (pkJuegoSuerteAzar.getIdeDocumento() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append(
						"IDE_DOCUMENTO=:IDE_DOCUMENTO");
				append = true;

			}
			if (pkJuegoSuerteAzar.getNumRepeticion() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append(
						"NUM_REPETICION=:NUM_REPETICION");
				append = true;

			}


		}


		if (attJuegoSuerteAzar != null) {

			if (attJuegoSuerteAzar.getIdeDocumentoCarga() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append(
						"IDE_DOCUMENTO_CARGA=:IDE_DOCUMENTO_CARGA");
				append = true;

			}
			if (attJuegoSuerteAzar.getNumRepeticionCarga() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append(
						"NUM_REPETICION_CARGA=:NUM_REPETICION_CARGA");
				append = true;

			}
			if (attJuegoSuerteAzar.getCodEstado() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append(
						"COD_ESTADO=:COD_ESTADO");
				append = true;

			}
			if (attJuegoSuerteAzar.getIdeUsuarioCambio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append(
						"IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO");
				append = true;

			}
			if (attJuegoSuerteAzar.getFecCambio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append(
						"FEC_CAMBIO=:FEC_CAMBIO");
				append = true;

			}
			if (attJuegoSuerteAzar.getIdePersona() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append(
						"IDE_PERSONA=:IDE_PERSONA");
				append = true;

			}
			if (attJuegoSuerteAzar.getIdeTarea() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append(
						"IDE_TAREA=:IDE_TAREA");
				append = true;

			}


		}

		return condiciones.toString();
	}



	/**
	 * Asigna los valores no nulos de un objeto.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresGenericos(DSentenciaSQL unaSentencia) throws
	SQLException {

		if (pkJuegoSuerteAzar != null) {

			if (pkJuegoSuerteAzar.getIdeDocumento() != null) {
				unaSentencia.setLong("IDE_DOCUMENTO", pkJuegoSuerteAzar.getIdeDocumento());
			}
			if (pkJuegoSuerteAzar.getNumRepeticion() != null) {
				unaSentencia.setInt("NUM_REPETICION", pkJuegoSuerteAzar.getNumRepeticion());
			}
		}

		if (attJuegoSuerteAzar != null) {

			if (attJuegoSuerteAzar.getIdeDocumentoCarga() != null) {
				unaSentencia.setLong("IDE_DOCUMENTO_CARGA", attJuegoSuerteAzar.getIdeDocumentoCarga());
			}
			if (attJuegoSuerteAzar.getNumRepeticionCarga() != null) {
				unaSentencia.setInt("NUM_REPETICION_CARGA", attJuegoSuerteAzar.getNumRepeticionCarga());
			}
			if (attJuegoSuerteAzar.getCodEstado() != null) {
				unaSentencia.setInt("COD_ESTADO", attJuegoSuerteAzar.getCodEstado());
			}
			if (attJuegoSuerteAzar.getIdeUsuarioCambio() != null) {
				unaSentencia.setLong("IDE_USUARIO_CAMBIO", attJuegoSuerteAzar.getIdeUsuarioCambio());
			}
			if (attJuegoSuerteAzar.getFecCambio() != null) {
				unaSentencia.setTimestamp("FEC_CAMBIO", attJuegoSuerteAzar.getFecCambio());
			}
			if (attJuegoSuerteAzar.getIdePersona() != null) {
				unaSentencia.setLong("IDE_PERSONA", attJuegoSuerteAzar.getIdePersona());
			}
			if (attJuegoSuerteAzar.getIdeTarea() != null) {
				unaSentencia.setLong("IDE_TAREA", attJuegoSuerteAzar.getIdeTarea());
			}

		}


	}

	/**
	 * Guarda los datos de JuegoSuerteAzar.
	 * @return @return Un int con el número de registros guardados
	 * @throws SQLException Si ocurre un error de base de datos al guardar
	 */
	public int guardar() throws SQLException {
		switch (getTipoOperacion()) {
		case CREAR:
			return crear();
		case ACTUALIZAR:
			return actualizar();
		case ACTUALIZAR_ESTADO:
			return actualizarEstado();
		}
		return -1;
	}

	/**
	 * Elimina registros de JuegoSuerteAzar.
	 * @return Un int con el número de registros eliminados
	 * @throws SQLException Si ocurre un error de base de datos al eliminar
	 */
	public int eliminar() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		return sentencia.getRegistrosAfectados();
	}

	/**
	 * Consulta los datos de JuegoSuerteAzar.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	public IDDataSet consultar() throws SQLException {
		//    DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		switch (getTipoOperacion()) {
		case CONSULTAR_POR_PK:
			return consultarPorPK();
		case CONSULTA_GENERICA:
			return consultaGenerica();

		}
		return null;
	}

	/**
	 * Crea un registro de JuegoSuerteAzar.
	 * @return Un int con el número de registros creados
	 * @throws SQLException Si ocurre un error de base de datos al crear
	 */
	private int crear() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresObjeto(sentencia);
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		int resultado = sentencia.getRegistrosAfectados();
		if (resultado <= 0) {
			throw new SQLException("No se ha creado ningún registro");
		}
		if (resultado > 1) {
			throw new SQLException("Se intentó crear más de un registro");
		}
		return resultado;
	}

	/**
	 * Actualiza los datos de JuegoSuerteAzar.
	 * @return Un int con el número de registros actualizados
	 * @throws SQLException Si ocurre un error de base de datos al actualizar
	 */
	private int actualizar() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresObjeto(sentencia);
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		int resultado = sentencia.getRegistrosAfectados();
		if (resultado <= 0) {
			throw new SQLException("No se ha actualizado ningún registro");
		}
		if (resultado > 1) {
			throw new SQLException("Se intentó actualizar más de un registro");
		}
		return resultado;
	}

	/**
	 * Actualiza los datos de JuegoSuerteAzar.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorPK() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarJuegoSuerteAzar(resultado);
		return resultado;
	}

	/**
	 * Consulta generica de los datos de JuegoSuerteAzar.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultaGenerica() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresGenericos(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosJuegoSuerteAzar(resultado);
		return resultado;

	}
	/**
	 * Actualiza el estado de JuegoSuerteAzar.
	 * @return Un int con el número de registros actualizados
	 * @throws SQLException Si ocurre un error de base de datos al actualizar
	 */
	private int actualizarEstado() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		sentencia.setInt("COD_ESTADO", codEstado);
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		int resultado = sentencia.getRegistrosAfectados();
		if (resultado <= 0) {
			throw new SQLException("No se ha actualizado ningún registro");
		}
		if (resultado > 1) {
			throw new SQLException("Se intentó actualizar más de un registro");
		}
		return resultado;
	}

	/**
	 * Asigna los valores para pk en una sentencia SQL.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresPK(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setLong("IDE_DOCUMENTO", pkJuegoSuerteAzar.getIdeDocumento());
		unaSentencia.setInt("NUM_REPETICION", pkJuegoSuerteAzar.getNumRepeticion());
	}

	/**
	 * Asigna todos los valores de un objeto.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresObjeto(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setLong("IDE_DOCUMENTO_CARGA", attJuegoSuerteAzar.getIdeDocumentoCarga());
		unaSentencia.setInt("NUM_REPETICION_CARGA", attJuegoSuerteAzar.getNumRepeticionCarga());
		unaSentencia.setInt("COD_ESTADO", attJuegoSuerteAzar.getCodEstado());
		unaSentencia.setLong("IDE_USUARIO_CAMBIO", attJuegoSuerteAzar.getIdeUsuarioCambio());
		unaSentencia.setTimestamp("FEC_CAMBIO", attJuegoSuerteAzar.getFecCambio());
		unaSentencia.setLong("IDE_PERSONA", attJuegoSuerteAzar.getIdePersona());
		unaSentencia.setLong("IDE_TAREA", attJuegoSuerteAzar.getIdeTarea());
	}

	/**
	 * Construye un objeto JuegoSuerteAzar con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private void cargarJuegoSuerteAzar(IDDataSet resultado) throws SQLException {
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		toJuegoSuerteAzar = completarJuegoSuerteAzar(resultado);
	}

	/**
	 * Construye objetos JuegoSuerteAzar con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar los objetos
	 */
	private void cargarObjetosJuegoSuerteAzar(IDDataSet resultado) throws SQLException {
		objetosJuegoSuerteAzar = new ArrayList();
		toJuegoSuerteAzar = null;
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		do {
			DJuegoSuerteAzarTO objeto = completarJuegoSuerteAzar(resultado);
			objetosJuegoSuerteAzar.add(objeto);
		} while (resultado.siguiente());
		resultado.primero();
	}

	/**
	 * Construye un objeto JuegoSuerteAzar con base en el data set.
	 * El data set debe contener datos en la posición actual.
	 * @param resultado Contenedor de los datos
	 * @return Un JuegoSuerteAzar
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private DJuegoSuerteAzarTO completarJuegoSuerteAzar(IDDataSet resultado) throws SQLException {
		// Conformar el objeto PK
		DJuegoSuerteAzarPKTO pk = new DJuegoSuerteAzarPKTO();
		//java.lang.Long
		pk.setIdeDocumento(resultado.getLongWrapper("IDE_DOCUMENTO"));
		//java.lang.Integer
		pk.setNumRepeticion(resultado.getIntWrapper("NUM_REPETICION"));

		// Conformar el objeto Att
		DJuegoSuerteAzarAttTO att = new DJuegoSuerteAzarAttTO();
		//java.lang.Long
		if (resultado.getValorPorNombre("IDE_DOCUMENTO_CARGA") != null) {
			att.setIdeDocumentoCarga(resultado.getLongWrapper("IDE_DOCUMENTO_CARGA"));
		}
		//java.lang.Integer
		if (resultado.getValorPorNombre("NUM_REPETICION_CARGA") != null) {
			att.setNumRepeticionCarga(resultado.getIntWrapper("NUM_REPETICION_CARGA"));
		}
		//java.lang.Integer
		if (resultado.getValorPorNombre("COD_ESTADO") != null) {
			att.setCodEstado(resultado.getIntWrapper("COD_ESTADO"));
		}
		//java.lang.Long
		if (resultado.getValorPorNombre("IDE_USUARIO_CAMBIO") != null) {
			att.setIdeUsuarioCambio(resultado.getLongWrapper("IDE_USUARIO_CAMBIO"));
		}
		//java.sql.Timestamp
		att.setFecCambio((Timestamp)resultado.getValorPorNombre("FEC_CAMBIO"));
		//java.lang.Long
		if (resultado.getValorPorNombre("IDE_PERSONA") != null) {
			att.setIdePersona(resultado.getLongWrapper("IDE_PERSONA"));
		}
		//java.lang.Long
		if (resultado.getValorPorNombre("IDE_TAREA") != null) {
			att.setIdeTarea(resultado.getLongWrapper("IDE_TAREA"));
		}
		// Conformar el TO
		DJuegoSuerteAzarTO to = new DJuegoSuerteAzarTO();
		to.setPK(pk);
		to.setAtt(att);
		return to;
	}

	/**
	 * Devuelve el objeto JuegoSuerteAzar que se haya consultado.
	 * @return Un objeto DJuegoSuerteAzarTO
	 */
	public DJuegoSuerteAzarTO getJuegoSuerteAzar() {
		return toJuegoSuerteAzar;
	}

	/**
	 * Devuelve la colección de objetos JuegoSuerteAzar que se hayan consultado.
	 * @return Un Collection con objetos DJuegoSuerteAzarTO
	 */
	public Collection getColeccionJuegoSuerteAzar() {
		return objetosJuegoSuerteAzar;
	}

	/**
	 * Indica si el DAO es de ejecución libre.
	 * @return true si es de ejecución libre; false de lo contrario
	 */
	public boolean isEjecucionLibre() {
		return false;
	}

	/**
	 * Mï¿½todo para validar los parámetros inicializados, invocado por el administrador de persistencia.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 * @todo COMPLEMENTAR
	 */
	public boolean validar() throws DValidarExcepcion {
		DManipuladorMensaje manipulador;
		String operacion = null;
		Map parametros=new HashMap();
		switch (getTipoOperacion()) {
		case CREAR: operacion = "la creación"; break;
		case ACTUALIZAR: operacion = "la actualización"; break;
		case ELIMINAR: operacion = "la eliminación"; break;
		case CONSULTAR_POR_PK: operacion = "la consulta"; break;
		case CONSULTA_GENERICA: operacion = "la consulta"; break;
		case ACTUALIZAR_ESTADO: operacion = "la actualización"; break;
		}

		if (operacion == null) {
			manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
			String mensaje = manipulador.getMensaje();
			throw new DValidarExcepcion(mensaje, mensaje);
		}

		if (tipoOperacion == CREAR || tipoOperacion == ACTUALIZAR) {
			parametros.put(this.getClass().getName()+":validar:toJuegoSuerteAzar",toJuegoSuerteAzar);
			parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar",pkJuegoSuerteAzar);
			parametros.put(this.getClass().getName()+":validar:attJuegoSuerteAzar",attJuegoSuerteAzar);

			parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar.getIdeDocumento()",pkJuegoSuerteAzar.getIdeDocumento());
			parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar.getNumRepeticion()",pkJuegoSuerteAzar.getNumRepeticion());
		}

		if (tipoOperacion == CONSULTAR_POR_PK || tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar",pkJuegoSuerteAzar);
			parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar.getIdeDocumento()",pkJuegoSuerteAzar.getIdeDocumento());
			parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar.getNumRepeticion()",pkJuegoSuerteAzar.getNumRepeticion());
		}

		if(tipoOperacion == CONSULTA_GENERICA){
			parametros.put(this.getClass().getName()+":validar:toJuegoSuerteAzar",toJuegoSuerteAzar);

		}
		if(tipoOperacion == ACTUALIZAR_ESTADO){
			parametros.put(this.getClass().getName()+":validar:pkJuegoSuerteAzar",pkJuegoSuerteAzar);
			parametros.put(this.getClass().getName()+":validar:codEstado",codEstado);

		}


		validarParametros(operacion,parametros);
		return true;
	}
}
