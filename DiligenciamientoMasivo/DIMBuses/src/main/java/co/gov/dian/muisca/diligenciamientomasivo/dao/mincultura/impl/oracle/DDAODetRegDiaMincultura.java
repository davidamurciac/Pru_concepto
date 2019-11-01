/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.impl.oracle;

import java.sql.*;
import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.general.persistencia.dao.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.dataset.*;
import co.gov.dian.muisca.arquitectura.mensajes.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.mincultura.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de acceso a datos para DetRegDiaMincultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDAODetRegDiaMincultura extends DDAO implements IDDAODetRegDiaMincultura {
	/** colección de objetos DDetRegDiaMinculturaTO */
	private Collection<DDetRegDiaMinculturaTO> objetosDetRegDiaMincultura;
	/** Objeto de transporte de DetRegDiaMincultura */
	private DDetRegDiaMinculturaTO toDetRegDiaMincultura;
	/** Llave primaria de DetRegDiaMincultura */
	private DDetRegDiaMinculturaPKTO pkDetRegDiaMincultura;
	/** Atributos de DetRegDiaMincultura */
	private DDetRegDiaMinculturaAttTO attDetRegDiaMincultura;
	/** Llave primaria de RegDiarioMinCultura */
	private DRegDiarioMinCulturaPKTO pkRegDiarioMinCultura;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkDetRegDiaMincultura Llave primaria de DetRegDiaMincultura
	 */
	public void inicializarConsultarPorPK(DDetRegDiaMinculturaPKTO pkDetRegDiaMincultura) {
		setTipoOperacion(CONSULTAR_POR_PK);
		this.pkDetRegDiaMincultura = pkDetRegDiaMincultura;
	}

	/**
	 * Inicializa la consulta por RegDiarioMinCultura.
	 * @param pkRegDiarioMinCultura Llave primaria de RegDiarioMinCultura
	 */
	public void inicializarConsultarPorRegDiarioMinCultura(DRegDiarioMinCulturaPKTO pkRegDiarioMinCultura) {
		setTipoOperacion(CONSULTAR_POR_REGDIARIOMINCULTURA);
		this.pkRegDiarioMinCultura = pkRegDiarioMinCultura;
	}

	/**
	 * Inicializa la creaciónn de DetRegDiaMincultura.
	 * @param toDetRegDiaMincultura Objeto de Transporte de DetRegDiaMincultura
	 */
	public void inicializarCrear(DDetRegDiaMinculturaTO toDetRegDiaMincultura) {
		setTipoOperacion(CREAR);
		this.toDetRegDiaMincultura = toDetRegDiaMincultura;
		if (toDetRegDiaMincultura != null) {
			pkDetRegDiaMincultura = this.toDetRegDiaMincultura.getPK();
			attDetRegDiaMincultura = this.toDetRegDiaMincultura.getAtt();
		}
	}

	/**
	 * Inicializa la actualización de DetRegDiaMincultura.
	 * @param toDetRegDiaMincultura Objeto de Transporte de DetRegDiaMincultura
	 */
	public void inicializarActualizar(DDetRegDiaMinculturaTO toDetRegDiaMincultura) {
		setTipoOperacion(ACTUALIZAR);
		this.toDetRegDiaMincultura = toDetRegDiaMincultura;
		if (toDetRegDiaMincultura != null) {
			pkDetRegDiaMincultura = this.toDetRegDiaMincultura.getPK();
			attDetRegDiaMincultura = this.toDetRegDiaMincultura.getAtt();
		}
	}

	/**
	 * Inicializa la eliminación de DetRegDiaMincultura.
	 * @param pkDetRegDiaMincultura Llave primaria de DetRegDiaMincultura
	 */
	public void inicializarEliminar(DDetRegDiaMinculturaPKTO pkDetRegDiaMincultura) {
		setTipoOperacion(ELIMINAR);
		this.pkDetRegDiaMincultura = pkDetRegDiaMincultura;
	}

	/**
	 * Inicializa la consulta genérica de DetRegDiaMincultura.
	 * @param attDetRegDiaMincultura Atributos de DetRegDiaMincultura
	 */
	public void inicializarConsultaGenerica(DDetRegDiaMinculturaTO toDetRegDiaMincultura) {
		setTipoOperacion(CONSULTA_GENERICA);

		this.toDetRegDiaMincultura = toDetRegDiaMincultura;
		if (toDetRegDiaMincultura != null) {
			pkDetRegDiaMincultura = this.toDetRegDiaMincultura.getPK();
			attDetRegDiaMincultura = this.toDetRegDiaMincultura.getAtt();
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
				sql.append("insert into DIL_DETALLE_REG_DIA_MINCULTURA")
					.append(" (IDE_DETALLE_DIARIO, IDE_REGISTRO_DIARIO, FEC_REGISTRO, NUM_NIT, NUM_DOCUMENTO_PRESENTADO, NUM_DOCUMENTO_CARGA, NUM_SOLICITUD, FEC_CAMBIO, VAL_DETALLE_PROCESO)")
					.append(" VALUES (:IDE_DETALLE_DIARIO, :IDE_REGISTRO_DIARIO, :FEC_REGISTRO, :NUM_NIT, :NUM_DOCUMENTO_PRESENTADO, :NUM_DOCUMENTO_CARGA, :NUM_SOLICITUD, :FEC_CAMBIO, :VAL_DETALLE_PROCESO)");
				m.put("sentencia1", sql.toString());
				break;
			case ACTUALIZAR:
				sql.append("update DIL_DETALLE_REG_DIA_MINCULTURA")
					.append(" set IDE_REGISTRO_DIARIO=:IDE_REGISTRO_DIARIO, FEC_REGISTRO=:FEC_REGISTRO, NUM_NIT=:NUM_NIT, NUM_DOCUMENTO_PRESENTADO=:NUM_DOCUMENTO_PRESENTADO, NUM_DOCUMENTO_CARGA=:NUM_DOCUMENTO_CARGA, NUM_SOLICITUD=:NUM_SOLICITUD, FEC_CAMBIO=:FEC_CAMBIO, VAL_DETALLE_PROCESO=:VAL_DETALLE_PROCESO")
					.append(" where IDE_DETALLE_DIARIO=:IDE_DETALLE_DIARIO");
				m.put("sentencia1", sql.toString());
				break;
			case ELIMINAR:
				sql.append("delete from DIL_DETALLE_REG_DIA_MINCULTURA")
					.append(" where IDE_DETALLE_DIARIO=:IDE_DETALLE_DIARIO");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTAR_POR_PK:
				sql.append("select * from DIL_DETALLE_REG_DIA_MINCULTURA")
					.append(" where IDE_DETALLE_DIARIO=:IDE_DETALLE_DIARIO");
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTA_GENERICA:
				sql.append("select * from DIL_DETALLE_REG_DIA_MINCULTURA where ")
					.append(generarFiltroGenerico());
				m.put("sentencia1", sql.toString());
				break;
			case CONSULTAR_POR_REGDIARIOMINCULTURA:
				sql.append("select * from DIL_DETALLE_REG_DIA_MINCULTURA")
					.append(" where IDE_REGISTRO_DIARIO=:IDE_REGISTRO_DIARIO");
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

		if (pkDetRegDiaMincultura != null) {

			if (pkDetRegDiaMincultura.getIdeDetalleDiario() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_DETALLE_DIARIO=:IDE_DETALLE_DIARIO");
				append = true;

			}
		}

		if (attDetRegDiaMincultura != null) {

			if (attDetRegDiaMincultura.getIdeRegistroDiario() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("IDE_REGISTRO_DIARIO=:IDE_REGISTRO_DIARIO");
				append = true;

			}
			if (attDetRegDiaMincultura.getFecRegistro() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("FEC_REGISTRO=:FEC_REGISTRO");
				append = true;

			}
			if (attDetRegDiaMincultura.getNumNit() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_NIT=:NUM_NIT");
				append = true;

			}
			if (attDetRegDiaMincultura.getNumDocumentoPresentado() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_DOCUMENTO_PRESENTADO=:NUM_DOCUMENTO_PRESENTADO");
				append = true;

			}
			if (attDetRegDiaMincultura.getNumDocumentoCarga() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_DOCUMENTO_CARGA=:NUM_DOCUMENTO_CARGA");
				append = true;

			}
			if (attDetRegDiaMincultura.getNumSolicitud() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("NUM_SOLICITUD=:NUM_SOLICITUD");
				append = true;

			}
			if (attDetRegDiaMincultura.getFecCambio() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("FEC_CAMBIO=:FEC_CAMBIO");
				append = true;

			}
			if (attDetRegDiaMincultura.getValDetalleProceso() != null) {
				if (append) {
					condiciones.append(y);
				}
				condiciones.append("VAL_DETALLE_PROCESO=:VAL_DETALLE_PROCESO");
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
		if (pkDetRegDiaMincultura != null) {
			if (pkDetRegDiaMincultura.getIdeDetalleDiario() != null) {
				unaSentencia.setString("IDE_DETALLE_DIARIO", pkDetRegDiaMincultura.getIdeDetalleDiario());
			}
		}

		if (attDetRegDiaMincultura != null) {
			if (attDetRegDiaMincultura.getIdeRegistroDiario() != null) {
				unaSentencia.setString("IDE_REGISTRO_DIARIO", attDetRegDiaMincultura.getIdeRegistroDiario());
			}
			if (attDetRegDiaMincultura.getFecRegistro() != null) {
				unaSentencia.setTimestamp("FEC_REGISTRO", attDetRegDiaMincultura.getFecRegistro());
			}
			if (attDetRegDiaMincultura.getNumNit() != null) {
				unaSentencia.setString("NUM_NIT", attDetRegDiaMincultura.getNumNit());
			}
			if (attDetRegDiaMincultura.getNumDocumentoPresentado() != null) {
				unaSentencia.setString("NUM_DOCUMENTO_PRESENTADO", attDetRegDiaMincultura.getNumDocumentoPresentado());
			}
			if (attDetRegDiaMincultura.getNumDocumentoCarga() != null) {
				unaSentencia.setString("NUM_DOCUMENTO_CARGA", attDetRegDiaMincultura.getNumDocumentoCarga());
			}
			if (attDetRegDiaMincultura.getNumSolicitud() != null) {
				unaSentencia.setString("NUM_SOLICITUD", attDetRegDiaMincultura.getNumSolicitud());
			}
			if (attDetRegDiaMincultura.getFecCambio() != null) {
				unaSentencia.setTimestamp("FEC_CAMBIO", attDetRegDiaMincultura.getFecCambio());
			}
			if (attDetRegDiaMincultura.getValDetalleProceso() != null) {
				unaSentencia.setString("VAL_DETALLE_PROCESO", attDetRegDiaMincultura.getValDetalleProceso());
			}
		}
	}

	/**
	 * Guarda los datos de DetRegDiaMincultura.
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
	 * Elimina registros de DetRegDiaMincultura.
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
	 * Consulta los datos de DetRegDiaMincultura.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	public IDDataSet consultar() throws SQLException {
		switch (getTipoOperacion()) {
			case CONSULTAR_POR_PK:
				return consultarPorPK();
			case CONSULTAR_POR_REGDIARIOMINCULTURA:
				return consultarPorRegDiarioMinCultura();
			case CONSULTA_GENERICA:
				return consultaGenerica();
		}
		return null;
	}

	/**
	 * Crea un registro de DetRegDiaMincultura.
	 * @return Un int con el número de registros creados
	 * @throws SQLException Si ocurre un error de base de datos al crear
	 */
	private int crear() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		pkDetRegDiaMincultura = new DDetRegDiaMinculturaPKTO();
		pkDetRegDiaMincultura.setIdeDetalleDiario(sentencia.getSiguienteNumero(SEQ_DETALLERS_REGISTROS_MINCULTURA).toString());
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
	 * Actualiza los datos de DetRegDiaMincultura.
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
	 * Actualiza los datos de DetRegDiaMincultura.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorPK() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarDetRegDiaMincultura(resultado);
		return resultado;
	}

	/**
	 * Consulta genérica de los datos de DetRegDiaMincultura.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultaGenerica() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresGenericos(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosDetRegDiaMincultura(resultado);
		return resultado;
	}

	/**
	 * Consulta por RegDiarioMinCultura.
	 * @return Un IDDataSet con la colección de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorRegDiarioMinCultura() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		sentencia.setString("IDE_REGISTRO_DIARIO", pkRegDiarioMinCultura.getIdeRegistroDiario());
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosDetRegDiaMincultura(resultado);
		return resultado;
	}

	/**
	 * Asigna los valores para pk en una sentencia SQL.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresPK(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setString("IDE_DETALLE_DIARIO", pkDetRegDiaMincultura.getIdeDetalleDiario());
	}

	/**
	 * Asigna todos los valores de un objeto.
	 * @param unaSentencia Sentencia para asignación
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresObjeto(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setString("IDE_REGISTRO_DIARIO", attDetRegDiaMincultura.getIdeRegistroDiario());
		unaSentencia.setTimestamp("FEC_REGISTRO", attDetRegDiaMincultura.getFecRegistro());
		unaSentencia.setString("NUM_NIT", attDetRegDiaMincultura.getNumNit());
		unaSentencia.setString("NUM_DOCUMENTO_PRESENTADO", attDetRegDiaMincultura.getNumDocumentoPresentado());
		unaSentencia.setString("NUM_DOCUMENTO_CARGA", attDetRegDiaMincultura.getNumDocumentoCarga());
		unaSentencia.setString("NUM_SOLICITUD", attDetRegDiaMincultura.getNumSolicitud());
		unaSentencia.setTimestamp("FEC_CAMBIO", attDetRegDiaMincultura.getFecCambio());
		unaSentencia.setString("VAL_DETALLE_PROCESO", attDetRegDiaMincultura.getValDetalleProceso());
	}

	/**
	 * Construye un objeto DetRegDiaMincultura con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private void cargarDetRegDiaMincultura(IDDataSet resultado) throws SQLException {
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		toDetRegDiaMincultura = completarDetRegDiaMincultura(resultado);
	}

	/**
	 * Construye objetos DetRegDiaMincultura con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar los objetos
	 */
	private void cargarObjetosDetRegDiaMincultura(IDDataSet resultado) throws SQLException {
		objetosDetRegDiaMincultura = new ArrayList<DDetRegDiaMinculturaTO>();
		toDetRegDiaMincultura = null;
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		do {
			DDetRegDiaMinculturaTO objeto = completarDetRegDiaMincultura(resultado);
			objetosDetRegDiaMincultura.add(objeto);
		} while (resultado.siguiente());
		resultado.primero();
	}

	/**
	 * Construye un objeto DetRegDiaMincultura con base en el data set.
	 * El data set debe contener datos en la posición actual.
	 * @param resultado Contenedor de los datos
	 * @return Un DetRegDiaMincultura
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private DDetRegDiaMinculturaTO completarDetRegDiaMincultura(IDDataSet resultado) throws SQLException {
		// Conformar el objeto PK
		DDetRegDiaMinculturaPKTO pk = new DDetRegDiaMinculturaPKTO();
		//java.lang.String
		pk.setIdeDetalleDiario(resultado.getString("IDE_DETALLE_DIARIO"));

		// Conformar el objeto Att
		DDetRegDiaMinculturaAttTO att = new DDetRegDiaMinculturaAttTO();
		//java.lang.String
		att.setIdeRegistroDiario(resultado.getString("IDE_REGISTRO_DIARIO"));
		//java.sql.Timestamp
		att.setFecRegistro((Timestamp)resultado.getValorPorNombre("FEC_REGISTRO"));
		//java.lang.String
		att.setNumNit(resultado.getString("NUM_NIT"));
		//java.lang.String
		att.setNumDocumentoPresentado(resultado.getString("NUM_DOCUMENTO_PRESENTADO"));
		//java.lang.String
		att.setNumDocumentoCarga(resultado.getString("NUM_DOCUMENTO_CARGA"));
		//java.lang.String
		att.setNumSolicitud(resultado.getString("NUM_SOLICITUD"));
		//java.sql.Timestamp
		att.setFecCambio((Timestamp)resultado.getValorPorNombre("FEC_CAMBIO"));
		//java.lang.String
		att.setValDetalleProceso(resultado.getString("VAL_DETALLE_PROCESO"));

		// Conformar el TO
		DDetRegDiaMinculturaTO to = new DDetRegDiaMinculturaTO();
		to.setPK(pk);
		to.setAtt(att);
		return to;
	}

	/**
	 * Devuelve el objeto DetRegDiaMincultura que se haya consultado.
	 * @return Un objeto DDetRegDiaMinculturaTO
	 */
	public DDetRegDiaMinculturaTO getDetRegDiaMincultura() {
		return toDetRegDiaMincultura;
	}

	/**
	 * Devuelve la colección de objetos DetRegDiaMincultura que se hayan consultado.
	 * @return Un Collection con objetos DDetRegDiaMinculturaTO
	 */
	public Collection<DDetRegDiaMinculturaTO> getColeccionDetRegDiaMincultura() {
		return objetosDetRegDiaMincultura;
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
			case CONSULTAR_POR_REGDIARIOMINCULTURA: operacion = "la consulta"; break;
		}

		if (operacion == null) {
			manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
			String mensaje = manipulador.getMensaje();
			throw new DValidarExcepcion(mensaje, mensaje);
		}

		if (tipoOperacion == CREAR || tipoOperacion == ACTUALIZAR) {
			parametros.put(this.getClass().getName()+":validar:toDetRegDiaMincultura",toDetRegDiaMincultura);
			//parametros.put(this.getClass().getName()+":validar:pkDetRegDiaMincultura",pkDetRegDiaMincultura);
			parametros.put(this.getClass().getName()+":validar:attDetRegDiaMincultura",attDetRegDiaMincultura);

			//parametros.put(this.getClass().getName()+":validar:pkDetRegDiaMincultura.getIdeDetalleDiario()",pkDetRegDiaMincultura.getIdeDetalleDiario());
			parametros.put(this.getClass().getName()+":validar:attDetRegDiaMincultura.getIdeRegistroDiario()",attDetRegDiaMincultura.getIdeRegistroDiario());
			parametros.put(this.getClass().getName()+":validar:attDetRegDiaMincultura.getFecRegistro()",attDetRegDiaMincultura.getFecRegistro());
			parametros.put(this.getClass().getName()+":validar:attDetRegDiaMincultura.getFecCambio()",attDetRegDiaMincultura.getFecCambio());
		}

		if (tipoOperacion == CONSULTAR_POR_PK || tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName()+":validar:pkDetRegDiaMincultura",pkDetRegDiaMincultura);
			parametros.put(this.getClass().getName()+":validar:pkDetRegDiaMincultura.getIdeDetalleDiario()",pkDetRegDiaMincultura.getIdeDetalleDiario());
		}

		if (tipoOperacion == CONSULTA_GENERICA) {
			parametros.put(this.getClass().getName()+":validar:toDetRegDiaMincultura",toDetRegDiaMincultura);
		}

		if (tipoOperacion == CONSULTAR_POR_REGDIARIOMINCULTURA) {
			parametros.put(this.getClass().getName()+":validar:pkRegDiarioMinCultura.getIdeRegistroDiario()",pkRegDiarioMinCultura.getIdeRegistroDiario());
		}

		validarParametros(operacion,parametros);
		return true;
	}
}
