/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.impl.oracle;

import java.math.BigDecimal;
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
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.IDDAORegDiarioMinCultura;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRegDiarioMinCulturaAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRegDiarioMinCulturaPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.DRegDiarioMinCulturaTO;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de acceso a datos para RegDiarioMinCultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDAORegDiarioMinCultura extends DDAO implements IDDAORegDiarioMinCultura {
	/** colección de objetos DRegDiarioMinCulturaTO */
	private Collection<DRegDiarioMinCulturaTO> objetosRegDiarioMinCultura;
	/** Objeto de transporte de RegDiarioMinCultura */
	private DRegDiarioMinCulturaTO toRegDiarioMinCultura;
	/** Llave primaria de RegDiarioMinCultura */
	private DRegDiarioMinCulturaPKTO pkRegDiarioMinCultura;
	/** Atributos de RegDiarioMinCultura */
	private DRegDiarioMinCulturaAttTO attRegDiarioMinCultura;
	
	private BigDecimal numSecuencia;

	

	public BigDecimal getNumSecuencia() {
		return numSecuencia;
	}

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkRegDiarioMinCultura Llave primaria de RegDiarioMinCultura
	 */
	public void inicializarConsultarPorPK(DRegDiarioMinCulturaPKTO pkRegDiarioMinCultura) {
		setTipoOperacion(CONSULTAR_POR_PK);
		this.pkRegDiarioMinCultura = pkRegDiarioMinCultura;
	}

	/**
	 * Inicializa la creaciónn de RegDiarioMinCultura.
	 * @param toRegDiarioMinCultura Objeto de Transporte de RegDiarioMinCultura
	 */
	public void inicializarCrear(DRegDiarioMinCulturaTO toRegDiarioMinCultura) {
		setTipoOperacion(CREAR);
		this.toRegDiarioMinCultura = toRegDiarioMinCultura;
		if (toRegDiarioMinCultura != null) {
			pkRegDiarioMinCultura = this.toRegDiarioMinCultura.getPK();
			attRegDiarioMinCultura = this.toRegDiarioMinCultura.getAtt();
		}
	}

	/**
	 * Inicializa la actualización de RegDiarioMinCultura.
	 * @param toRegDiarioMinCultura Objeto de Transporte de RegDiarioMinCultura
	 */
	public void inicializarActualizar(DRegDiarioMinCulturaTO toRegDiarioMinCultura) {
		setTipoOperacion(ACTUALIZAR);
		this.toRegDiarioMinCultura = toRegDiarioMinCultura;
		if (toRegDiarioMinCultura != null) {
			pkRegDiarioMinCultura = this.toRegDiarioMinCultura.getPK();
			attRegDiarioMinCultura = this.toRegDiarioMinCultura.getAtt();
		}
	}

	/**
	 * Inicializa la eliminación de RegDiarioMinCultura.
	 * @param pkRegDiarioMinCultura Llave primaria de RegDiarioMinCultura
	 */
	public void inicializarEliminar(DRegDiarioMinCulturaPKTO pkRegDiarioMinCultura) {
		setTipoOperacion(ELIMINAR);
		this.pkRegDiarioMinCultura = pkRegDiarioMinCultura;
	}

	/**
	 * Inicializa la consulta genérica de RegDiarioMinCultura.
	 * @param attRegDiarioMinCultura Atributos de RegDiarioMinCultura
	 */
	public void inicializarConsultaGenerica(DRegDiarioMinCulturaTO toRegDiarioMinCultura) {
		setTipoOperacion(CONSULTA_GENERICA);

		this.toRegDiarioMinCultura = toRegDiarioMinCultura;
		if (toRegDiarioMinCultura != null) {
			pkRegDiarioMinCultura = this.toRegDiarioMinCultura.getPK();
			attRegDiarioMinCultura = this.toRegDiarioMinCultura.getAtt();
		}
	}

	/**
	 * Devuelve las sentencias sql a ejecutar, dependiendo del tipo de operación a realizar.
	 * @return Un Map de Strings con la relación de sentencias sql
	 */
	public Map<String, String> getSentenciasSQL() {
		Map<String, String> m = new HashMap<String, String>();
		StringBuffer sql = new StringBuffer();
		switch (getTipoOperacion()) {
			case CREAR:
				sql.append("insert into DIL_REGISTRO_DIARIO_MINCULTURA")
					.append(" (IDE_REGISTRO_DIARIO, FEC_REGISTRO, COD_ESTADO, DIR_SERVIDOR_PROCESO, VAL_DESCRIPCION_PROCESO, FEC_CAMBIO)")
					.append(" VALUES (:IDE_REGISTRO_DIARIO, :FEC_REGISTRO, :COD_ESTADO, :DIR_SERVIDOR_PROCESO, :VAL_DESCRIPCION_PROCESO, :FEC_CAMBIO)");
				m.put("sentencia1", sql.toString());
				break;
			case ACTUALIZAR:
				sql.append("update DIL_REGISTRO_DIARIO_MINCULTURA")
					.append(" set FEC_REGISTRO=:FEC_REGISTRO, COD_ESTADO=:COD_ESTADO, DIR_SERVIDOR_PROCESO=:DIR_SERVIDOR_PROCESO, VAL_DESCRIPCION_PROCESO=:VAL_DESCRIPCION_PROCESO, FEC_CAMBIO=:FEC_CAMBIO")
					.append(" where IDE_REGISTRO_DIARIO=:IDE_REGISTRO_DIARIO");
				m.put("sentencia1", sql.toString());
				break;
			case ELIMINAR:
				sql.append("delete from DIL_REGISTRO_DIARIO_MINCULTURA")
					.append(" where IDE_REGISTRO_DIARIO=:IDE_REGISTRO_DIARIO");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTAR_POR_PK:
				sql.append("select * from DIL_REGISTRO_DIARIO_MINCULTURA")
					.append(" where IDE_REGISTRO_DIARIO=:IDE_REGISTRO_DIARIO");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTA_GENERICA:
				sql.append("select * from DIL_REGISTRO_DIARIO_MINCULTURA where ")
					.append(generarFiltroGenerico());
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

		if (pkRegDiarioMinCultura != null) {

			if (pkRegDiarioMinCultura.getIdeRegistroDiario() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_REGISTRO_DIARIO=:IDE_REGISTRO_DIARIO");
				append = true;

			}
		}

		if (attRegDiarioMinCultura != null) {

			if (attRegDiarioMinCultura.getFecRegistro() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("FEC_REGISTRO=:FEC_REGISTRO");
				append = true;

			}
			if (attRegDiarioMinCultura.getCodEstado() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("COD_ESTADO=:COD_ESTADO");
				append = true;

			}
			if (attRegDiarioMinCultura.getDirServidorProceso() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("DIR_SERVIDOR_PROCESO=:DIR_SERVIDOR_PROCESO");
				append = true;

			}
			if (attRegDiarioMinCultura.getValDescripcionProceso() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_DESCRIPCION_PROCESO=:VAL_DESCRIPCION_PROCESO");
				append = true;

			}
			if (attRegDiarioMinCultura.getFecCambio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("FEC_CAMBIO=:FEC_CAMBIO");
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
	private void asignarValoresGenericos(DSentenciaSQL unaSentencia) throws SQLException {
		if (pkRegDiarioMinCultura != null) {
			if (pkRegDiarioMinCultura.getIdeRegistroDiario() != null) {
				unaSentencia.setString("IDE_REGISTRO_DIARIO", pkRegDiarioMinCultura.getIdeRegistroDiario());
			}
		}

		if (attRegDiarioMinCultura != null) {
			if (attRegDiarioMinCultura.getFecRegistro() != null) {
				unaSentencia.setTimestamp("FEC_REGISTRO", attRegDiarioMinCultura.getFecRegistro());
			}
			if (attRegDiarioMinCultura.getCodEstado() != null) {
				unaSentencia.setString("COD_ESTADO", attRegDiarioMinCultura.getCodEstado());
			}
			if (attRegDiarioMinCultura.getDirServidorProceso() != null) {
				unaSentencia.setString("DIR_SERVIDOR_PROCESO", attRegDiarioMinCultura.getDirServidorProceso());
			}
			if (attRegDiarioMinCultura.getValDescripcionProceso() != null) {
				unaSentencia.setString("VAL_DESCRIPCION_PROCESO", attRegDiarioMinCultura.getValDescripcionProceso());
			}
			if (attRegDiarioMinCultura.getFecCambio() != null) {
				unaSentencia.setTimestamp("FEC_CAMBIO", attRegDiarioMinCultura.getFecCambio());
			}
		}
	}

	/**
	 * Guarda los datos de RegDiarioMinCultura.
	 * @return @return Un int con el número de registros guardados
	 * @throws SQLException Si ocurre un error de base de datos al guardar
	 */
	public int guardar() throws SQLException {
		switch (getTipoOperacion()) {
			case CREAR:
				return crear();
			case ACTUALIZAR:
				return actualizar();
		}
		return -1;
	}

	/**
	 * Elimina registros de RegDiarioMinCultura.
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
	 * Consulta los datos de RegDiarioMinCultura.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	public IDDataSet consultar() throws SQLException {
		switch (getTipoOperacion()) {
			case CONSULTAR_POR_PK:
				return consultarPorPK();
			case CONSULTA_GENERICA:
				return consultaGenerica();
		}
		return null;
	}

	/**
	 * Crea un registro de RegDiarioMinCultura.
	 * @return Un int con el número de registros creados
	 * @throws SQLException Si ocurre un error de base de datos al crear
	 */
	private int crear() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		pkRegDiarioMinCultura = new DRegDiarioMinCulturaPKTO();		
		numSecuencia =  (BigDecimal) sentencia.getSiguienteNumero(SEQ_REGISTROS_MINCULTURA);
		pkRegDiarioMinCultura.setIdeRegistroDiario(numSecuencia.toString());		
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
	 * Actualiza los datos de RegDiarioMinCultura.
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
	 * Actualiza los datos de RegDiarioMinCultura.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorPK() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarRegDiarioMinCultura(resultado);
		return resultado;
	}

	/**
	 * Consulta genérica de los datos de RegDiarioMinCultura.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultaGenerica() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresGenericos(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosRegDiarioMinCultura(resultado);
		return resultado;
	}

	/**
	 * Asigna los valores para pk en una sentencia SQL.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresPK(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setString("IDE_REGISTRO_DIARIO", pkRegDiarioMinCultura.getIdeRegistroDiario());
	}

	/**
	 * Asigna todos los valores de un objeto.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresObjeto(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setTimestamp("FEC_REGISTRO", attRegDiarioMinCultura.getFecRegistro());
		unaSentencia.setString("COD_ESTADO", attRegDiarioMinCultura.getCodEstado());
		unaSentencia.setString("DIR_SERVIDOR_PROCESO", attRegDiarioMinCultura.getDirServidorProceso());
		unaSentencia.setString("VAL_DESCRIPCION_PROCESO", attRegDiarioMinCultura.getValDescripcionProceso());
		unaSentencia.setTimestamp("FEC_CAMBIO", attRegDiarioMinCultura.getFecCambio());
	}

	/**
	 * Construye un objeto RegDiarioMinCultura con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private void cargarRegDiarioMinCultura(IDDataSet resultado) throws SQLException {
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		toRegDiarioMinCultura = completarRegDiarioMinCultura(resultado);
	}

	/**
	 * Construye objetos RegDiarioMinCultura con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar los objetos
	 */
	private void cargarObjetosRegDiarioMinCultura(IDDataSet resultado) throws SQLException {
		objetosRegDiarioMinCultura = new ArrayList<DRegDiarioMinCulturaTO>();
		toRegDiarioMinCultura = null;
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		do {
			DRegDiarioMinCulturaTO objeto = completarRegDiarioMinCultura(resultado);
			objetosRegDiarioMinCultura.add(objeto);
		} while (resultado.siguiente());
		resultado.primero();
	}

	/**
	 * Construye un objeto RegDiarioMinCultura con base en el data set.
	 * El data set debe contener datos en la posición actual.
	 * @param resultado Contenedor de los datos
	 * @return Un RegDiarioMinCultura
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private DRegDiarioMinCulturaTO completarRegDiarioMinCultura(IDDataSet resultado) throws SQLException {
		// Conformar el objeto PK
		DRegDiarioMinCulturaPKTO pk = new DRegDiarioMinCulturaPKTO();
		//java.lang.String
		pk.setIdeRegistroDiario(resultado.getString("IDE_REGISTRO_DIARIO"));

		// Conformar el objeto Att
		DRegDiarioMinCulturaAttTO att = new DRegDiarioMinCulturaAttTO();
		//java.sql.Timestamp
		att.setFecRegistro((Timestamp)resultado.getValorPorNombre("FEC_REGISTRO"));
		//java.lang.String
		att.setCodEstado(resultado.getString("COD_ESTADO"));
		//java.lang.String
		att.setDirServidorProceso(resultado.getString("DIR_SERVIDOR_PROCESO"));
		//java.lang.String
		att.setValDescripcionProceso(resultado.getString("VAL_DESCRIPCION_PROCESO"));
		//java.sql.Timestamp
		att.setFecCambio((Timestamp)resultado.getValorPorNombre("FEC_CAMBIO"));

		// Conformar el TO
		DRegDiarioMinCulturaTO to = new DRegDiarioMinCulturaTO();
		to.setPK(pk);
		to.setAtt(att);
		return to;
	}

	/**
	 * Devuelve el objeto RegDiarioMinCultura que se haya consultado.
	 * @return Un objeto DRegDiarioMinCulturaTO
	 */
	public DRegDiarioMinCulturaTO getRegDiarioMinCultura() {
		return toRegDiarioMinCultura;
	}

	/**
	 * Devuelve la colección de objetos RegDiarioMinCultura que se hayan consultado.
	 * @return Un Collection con objetos DRegDiarioMinCulturaTO
	 */
	public Collection<DRegDiarioMinCulturaTO> getColeccionRegDiarioMinCultura() {
		return objetosRegDiarioMinCultura;
	}

	/**
	 * Indica si el DAO es de ejecución libre.
	 * @return true si es de ejecución libre; false de lo contrario
	 */
	public boolean isEjecucionLibre() {
		return true;
	}

	/**
	 * Método para validar los parámetros inicializados, invocado por el administrador de persistencia.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 * @todo COMPLEMENTAR
	 */
	public boolean validar() throws DValidarExcepcion {
		DManipuladorMensaje manipulador;
		String operacion = null;
		Map<String, Object> parametros=new HashMap<String, Object>();
		switch (getTipoOperacion()) {
			case CREAR: operacion = "la creación"; break;
			case ACTUALIZAR: operacion = "la actualización"; break;
			case ELIMINAR: operacion = "la eliminación"; break;
			case CONSULTAR_POR_PK: operacion = "la consulta"; break;
			case CONSULTA_GENERICA: operacion = "la consulta"; break;
		}

		if (operacion == null) {
			manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
			String mensaje = manipulador.getMensaje();
			throw new DValidarExcepcion(mensaje, mensaje);
		}

		if (tipoOperacion == CREAR || tipoOperacion == ACTUALIZAR) {
			parametros.put(this.getClass().getName()+":validar:toRegDiarioMinCultura",toRegDiarioMinCultura);
			//parametros.put(this.getClass().getName()+":validar:pkRegDiarioMinCultura",pkRegDiarioMinCultura);
			parametros.put(this.getClass().getName()+":validar:attRegDiarioMinCultura",attRegDiarioMinCultura);

			//parametros.put(this.getClass().getName()+":validar:pkRegDiarioMinCultura.getIdeRegistroDiario()",pkRegDiarioMinCultura.getIdeRegistroDiario());
			parametros.put(this.getClass().getName()+":validar:attRegDiarioMinCultura.getFecRegistro()",attRegDiarioMinCultura.getFecRegistro());
			parametros.put(this.getClass().getName()+":validar:attRegDiarioMinCultura.getCodEstado()",attRegDiarioMinCultura.getCodEstado());
			parametros.put(this.getClass().getName()+":validar:attRegDiarioMinCultura.getDirServidorProceso()",attRegDiarioMinCultura.getDirServidorProceso());
			parametros.put(this.getClass().getName()+":validar:attRegDiarioMinCultura.getValDescripcionProceso()",attRegDiarioMinCultura.getValDescripcionProceso());
			parametros.put(this.getClass().getName()+":validar:attRegDiarioMinCultura.getFecCambio()",attRegDiarioMinCultura.getFecCambio());
		}

		if (tipoOperacion == CONSULTAR_POR_PK || tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName()+":validar:pkRegDiarioMinCultura",pkRegDiarioMinCultura);
			parametros.put(this.getClass().getName()+":validar:pkRegDiarioMinCultura.getIdeRegistroDiario()",pkRegDiarioMinCultura.getIdeRegistroDiario());
		}

		if (tipoOperacion == CONSULTA_GENERICA) {
			parametros.put(this.getClass().getName()+":validar:toRegDiarioMinCultura",toRegDiarioMinCultura);
		}


		validarParametros(operacion,parametros);
		return true;
	}
	
}
