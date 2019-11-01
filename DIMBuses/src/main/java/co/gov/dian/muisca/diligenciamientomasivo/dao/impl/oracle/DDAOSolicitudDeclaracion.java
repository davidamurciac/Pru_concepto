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
import co.gov.dian.muisca.diligenciamientomasivo.general.to.solicitudesdeclaracion.*;
import co.gov.dian.muisca.diligenciamientomasivo.dao.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Objeto de acceso a datos para SolicitudDeclaracion.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author dmahechav
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DDAOSolicitudDeclaracion extends DDAO implements IDDAOSolicitudDeclaracion {
	/** Colecciï¿½n de objetos DSolicitudDeclaracionTO */
	private Collection objetosSolicitudDeclaracion;
	/** Objeto de transporte de SolicitudDeclaracion */
	private DSolicitudDeclaracionTO toSolicitudDeclaracion;
	/** Llave primaria de SolicitudDeclaracion */
	private DSolicitudDeclaracionPKTO pkSolicitudDeclaracion;
	/** Atributos de SolicitudDeclaracion */
	private DSolicitudDeclaracionAttTO attSolicitudDeclaracion;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkSolicitudDeclaracion Llave primaria de SolicitudDeclaracion
	 */
	public void inicializarConsultarPorPK(DSolicitudDeclaracionPKTO pkSolicitudDeclaracion) {
		setTipoOperacion(CONSULTAR_POR_PK);
		this.pkSolicitudDeclaracion = pkSolicitudDeclaracion;
	}

	/**
	 * Inicializa la creación de SolicitudDeclaracion.
	 * @param toSolicitudDeclaracion Objeto de Transporte de SolicitudDeclaracion
	 */
	public void inicializarCrear(DSolicitudDeclaracionTO toSolicitudDeclaracion) {
		setTipoOperacion(CREAR);
		this.toSolicitudDeclaracion = toSolicitudDeclaracion;
		if (toSolicitudDeclaracion != null) {
			pkSolicitudDeclaracion = this.toSolicitudDeclaracion.getPK();
			attSolicitudDeclaracion = this.toSolicitudDeclaracion.getAtt();
		}
	}

	/**
	 * Inicializa la actualizaciï¿½n de SolicitudDeclaracion.
	 * @param toSolicitudDeclaracion Objeto de Transporte de SolicitudDeclaracion
	 */
	public void inicializarActualizar(DSolicitudDeclaracionTO toSolicitudDeclaracion) {
		setTipoOperacion(ACTUALIZAR);
		this.toSolicitudDeclaracion = toSolicitudDeclaracion;
		if (toSolicitudDeclaracion != null) {
			pkSolicitudDeclaracion = this.toSolicitudDeclaracion.getPK();
			attSolicitudDeclaracion = this.toSolicitudDeclaracion.getAtt();
		}
	}

	/**
	 * Inicializa la eliminaciï¿½n de SolicitudDeclaracion.
	 * @param pkSolicitudDeclaracion Llave primaria de SolicitudDeclaracion
	 */
	public void inicializarEliminar(DSolicitudDeclaracionPKTO pkSolicitudDeclaracion) {
		setTipoOperacion(ELIMINAR);
		this.pkSolicitudDeclaracion = pkSolicitudDeclaracion;
	}

	/**
	 * Inicializa el DAO para hacer consultas genéricas.
	 * 
	 * @param to Dato de tipo <code>DSolictudDeclaracionTO</code> con los parámetros de búsqueda.
	 */
	public void inicializarConsultarGenerica(DSolicitudDeclaracionTO to){
		setTipoOperacion(CONSULTA_GENERICA);
		this.toSolicitudDeclaracion=to;
		if(this.toSolicitudDeclaracion!=null){
			this.pkSolicitudDeclaracion=toSolicitudDeclaracion.getPK();
			this.attSolicitudDeclaracion=toSolicitudDeclaracion.getAtt();
		}/*fin de if*/
	}/*fin de inicializarConsultarGenerica*/
	
	 /**
	   * Inicializa el DAO para hacer consultas por ideFormato y FecCambio.
	   * 
	   * @param to Dato de tipo <code>DSolictudDeclaracionTO</code> con los parámetros de búsqueda.
	   */
	  public void inicializarConsultarPorFormatoFecCambio(DSolicitudDeclaracionTO to) {
		  setTipoOperacion(CONSULTA_FORMATO_FEC_CAMBIO);
			this.toSolicitudDeclaracion=to;
			if(this.toSolicitudDeclaracion!=null){
				this.pkSolicitudDeclaracion=toSolicitudDeclaracion.getPK();
				this.attSolicitudDeclaracion=toSolicitudDeclaracion.getAtt();
			}
	  }
	
	public void inicializarConsultarGenericaAtt(DSolicitudDeclaracionTO to){
		setTipoOperacion(CONSULTA_GENERICA_ATT);
		this.toSolicitudDeclaracion=to;
		if(this.toSolicitudDeclaracion!=null){	
			this.attSolicitudDeclaracion=toSolicitudDeclaracion.getAtt();
		}/*fin de if*/
	}/*fin de inicializarConsultarGenericaAtt*/


	/**
	 * Devuelve las sentencias sql a ejecutar, dependiendo del tipo de operación a realizar.
	 * @return Un Map de Strings con la relación de sentencias sql
	 */
	public Map getSentenciasSQL() {
		Map m = new HashMap();
		StringBuffer sql = new StringBuffer();
		switch (getTipoOperacion()) {
		case CREAR:
			sql.append("insert into DIL_SOLICITUDES_DECLARACION")
			.append(" (IDE_DOCUMENTO, NUM_REPETICION, IDE_FORMATO, NUM_VERSION_FORMATO, IDE_DOCUMENTO_CARGA, NUM_REPETICION_CARGA, IDE_FORMATO_CARGA, NUM_VERSION_FORMATO_CARGA, COD_ESTADO, IDE_USUARIO_CAMBIO, FEC_CAMBIO, IDE_PERSONA, IDE_TAREA)")
			.append(" VALUES (:IDE_DOCUMENTO, :NUM_REPETICION, :IDE_FORMATO, :NUM_VERSION_FORMATO, :IDE_DOCUMENTO_CARGA, :NUM_REPETICION_CARGA, :IDE_FORMATO_CARGA, :NUM_VERSION_FORMATO_CARGA, :COD_ESTADO, :IDE_USUARIO_CAMBIO, :FEC_CAMBIO, :IDE_PERSONA, :IDE_TAREA)");
			m.put("sentencia1", sql.toString());
			break;
		case ACTUALIZAR:
			sql.append("update DIL_SOLICITUDES_DECLARACION")
			.append(" set IDE_FORMATO=:IDE_FORMATO, NUM_VERSION_FORMATO=:NUM_VERSION_FORMATO, IDE_DOCUMENTO_CARGA=:IDE_DOCUMENTO_CARGA, NUM_REPETICION_CARGA=:NUM_REPETICION_CARGA, IDE_FORMATO_CARGA=:IDE_FORMATO_CARGA, NUM_VERSION_FORMATO_CARGA=:NUM_VERSION_FORMATO_CARGA, COD_ESTADO=:COD_ESTADO, IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO, FEC_CAMBIO=:FEC_CAMBIO, IDE_PERSONA=:IDE_PERSONA, IDE_TAREA=:IDE_TAREA")
			.append(" where IDE_DOCUMENTO=:IDE_DOCUMENTO and NUM_REPETICION=:NUM_REPETICION");
			m.put("sentencia1", sql.toString());
			break;
		case ELIMINAR:
			sql.append("delete from DIL_SOLICITUDES_DECLARACION")
			.append(" where IDE_DOCUMENTO=:IDE_DOCUMENTO and NUM_REPETICION=:NUM_REPETICION");
			m.put("sentencia1", sql.toString());
			break;
		case CONSULTAR_POR_PK:
			sql.append("select * from DIL_SOLICITUDES_DECLARACION")
			.append(" where IDE_DOCUMENTO_CARGA=:IDE_DOCUMENTO_CARGA and NUM_REPETICION_CARGA=:NUM_REPETICION_CARGA");
			m.put("sentencia1", sql.toString());
			break;
		case CONSULTA_GENERICA:
			sql.append("select * from ")
			   .append("DIL_SOLICITUDES_DECLARACION ")
			   .append("where ")
			   .append(armarFiltroGenerico())
			   .append(" and IDE_DOCUMENTO is not null order by FEC_CAMBIO desc");
			m.put("sentencia1", sql.toString());
			break;
		case CONSULTA_GENERICA_ATT:
			sql.append("select * from ")
			   .append("DIL_SOLICITUDES_DECLARACION ")
			   .append("where ")
			   .append(armarFiltroGenericoAtt())
			   .append(" order by FEC_CAMBIO desc");
			m.put("sentencia1", sql.toString());
			break;	
		case CONSULTA_FORMATO_FEC_CAMBIO:
			sql.append("select * from DIL_SOLICITUDES_DECLARACION")
			.append(" where IDE_FORMATO=:IDE_FORMATO and FEC_CAMBIO>=:FECHA1 AND FEC_CAMBIO<=:FECHA2");
			m.put("sentencia1", sql.toString());
			break;
		}
		return m;
	}

	/**
	 * Guarda los datos de SolicitudDeclaracion.
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
	 * Elimina registros de SolicitudDeclaracion.
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
	 * Consulta los datos de SolicitudDeclaracion.
	 * @return Un IDDataSet con la coleccion de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	public IDDataSet consultar() throws SQLException {
		switch (getTipoOperacion()) {
		case CONSULTAR_POR_PK:
			return consultarPorPK();
		case CONSULTA_GENERICA:
			return consultarGenerico();
		case CONSULTA_GENERICA_ATT:
			return consultarGenericoAtt();
		case CONSULTA_FORMATO_FEC_CAMBIO:
			return consultarPorFormatoFecCambio();
		}
		return null;
	}

	/**
	 * Crea un registro de SolicitudDeclaracion.
	 * @return Un int con el numero de registros creados
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
	 * Actualiza los datos de SolicitudDeclaracion.
	 * @return Un int con el numero de registros actualizados
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
	 * Actualiza los datos de SolicitudDeclaracion.
	 * @return Un IDDataSet con la coleccion de registros encontrados
	 * @throws SQLException Si ocurre un error de base de datos al consultar
	 */
	private IDDataSet consultarPorPK() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresPK(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarSolicitudDeclaracion(resultado);
		return resultado;
	}
	
	/**
	 * Ejecuta la consulta genérica de registros.
	 * 
	 * @return Dato de tipo <code>IDDataSet</code> con los resultados obtenidos de la base de datos.
	 * @throws SQLException Si la ejecución falla por algún motivo.
	 */
	private IDDataSet consultarGenerico() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresGenerico(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosSolicitudDeclaracion(resultado);
		return resultado;
	}
	/**
	 * Ejecuta la consulta genérica Att de registros.
	 * 
	 * @return Dato de tipo <code>IDDataSet</code> con los resultados obtenidos de la base de datos.
	 * @throws SQLException Si la ejecución falla por algún motivo.
	 */
	private IDDataSet consultarGenericoAtt() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		asignarValoresGenericoAtt(sentencia);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosSolicitudDeclaracion(resultado);
		return resultado;
	}
	
	
	/**
	 * Ejecuta la consulta por ideFormato y fecCambio.
	 * 
	 * @return Dato de tipo <code>IDDataSet</code> con los resultados obtenidos de la base de datos.
	 * @throws SQLException Si la ejecución falla por algún motivo.
	 */
	private IDDataSet consultarPorFormatoFecCambio() throws SQLException {
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		sentencia.setInt("IDE_FORMATO", attSolicitudDeclaracion.getIdeFormato());
		//FECHA1 viene con la fecha y cero horas, minutos y segundos
		//FECHA2 se le agregan a la fecha que se recibe 23 horas 59 minutos y 59 segundos 
		sentencia.setTimestamp("FECHA1", attSolicitudDeclaracion.getFecCambio());
		Timestamp fec2 = new Timestamp(attSolicitudDeclaracion.getFecCambio().getTime() + ((24*60*60 - 1)*1000));
		sentencia.setTimestamp("FECHA2", fec2);
		sentencia.ejecutar();
		DDataSet resultado = sentencia.getDataSet();
		cargarObjetosSolicitudDeclaracion(resultado);
		return resultado;
	}
	
	/**
	 * Asigna los valores para pk en una sentencia SQL.
	 * @param unaSentencia Sentencia para asignacion
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresPK(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setLong("IDE_DOCUMENTO_CARGA", pkSolicitudDeclaracion.getIdeDocumentoCarga());
		unaSentencia.setInt("NUM_REPETICION_CARGA", pkSolicitudDeclaracion.getNumRepeticionCarga());
	}

	/**
	 * Asigna todos los valores de un objeto.
	 * @param unaSentencia Sentencia para asignacion
	 * @throws SQLException Si ocurre un error al asignar los valores
	 */
	private void asignarValoresObjeto(DSentenciaSQL unaSentencia) throws SQLException {
		unaSentencia.setLong("IDE_DOCUMENTO", attSolicitudDeclaracion.getIdeDocumento());
		unaSentencia.setInt("NUM_REPETICION", attSolicitudDeclaracion.getNumRepeticion());
		unaSentencia.setInt("IDE_FORMATO", attSolicitudDeclaracion.getIdeFormato());
		unaSentencia.setShort("NUM_VERSION_FORMATO", attSolicitudDeclaracion.getNumVersionFormato());
		unaSentencia.setInt("IDE_FORMATO_CARGA", attSolicitudDeclaracion.getIdeFormatoCarga());
		unaSentencia.setShort("NUM_VERSION_FORMATO_CARGA", attSolicitudDeclaracion.getNumVersionFormatoCarga());
		unaSentencia.setInt("COD_ESTADO", attSolicitudDeclaracion.getCodEstado());
		unaSentencia.setLong("IDE_USUARIO_CAMBIO", attSolicitudDeclaracion.getIdeUsuarioCambio());
		unaSentencia.setTimestamp("FEC_CAMBIO", attSolicitudDeclaracion.getFecCambio());
		unaSentencia.setLong("IDE_PERSONA", attSolicitudDeclaracion.getIdePersona());
		unaSentencia.setLong("IDE_TAREA", attSolicitudDeclaracion.getIdeTarea());
	}
	
	/**
	 * Asigna los valores a la consulta, de acuerdo con los datos proporcionados.
	 * 
	 * @param sql Dato de tipo <code>DSentenciaSQL</code> a donde se asignarán valores.
	 */
	private void asignarValoresGenerico(DSentenciaSQL unaSentencia) throws SQLException{
		if(pkSolicitudDeclaracion.getIdeDocumentoCarga()!=null){
			unaSentencia.setLong("IDE_DOCUMENTO_CARGA", pkSolicitudDeclaracion.getIdeDocumentoCarga());
		}/*fin de if*/
		if(pkSolicitudDeclaracion.getNumRepeticionCarga()!=null){
			unaSentencia.setInt("NUM_REPETICION_CARGA", pkSolicitudDeclaracion.getNumRepeticionCarga());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeDocumento()!=null){
			unaSentencia.setLong("IDE_DOCUMENTO", attSolicitudDeclaracion.getIdeDocumento());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getNumRepeticion()!=null){
			unaSentencia.setInt("NUM_REPETICION", attSolicitudDeclaracion.getNumRepeticion());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeFormato()!=null){
			unaSentencia.setInt("IDE_FORMATO", attSolicitudDeclaracion.getIdeFormato());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getNumVersionFormato()!=null){
			unaSentencia.setShort("NUM_VERSION_FORMATO", attSolicitudDeclaracion.getNumVersionFormato());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeFormatoCarga()!=null){
			unaSentencia.setInt("IDE_FORMATO_CARGA", attSolicitudDeclaracion.getIdeFormatoCarga());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getNumVersionFormatoCarga()!=null){
			unaSentencia.setShort("NUM_VERSION_FORMATO_CARGA", attSolicitudDeclaracion.getNumVersionFormatoCarga());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getCodEstado()!=null){
			unaSentencia.setInt("COD_ESTADO", attSolicitudDeclaracion.getCodEstado());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeUsuarioCambio()!=null){
			unaSentencia.setLong("IDE_USUARIO_CAMBIO", attSolicitudDeclaracion.getIdeUsuarioCambio());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getFecCambio()!=null){
			unaSentencia.setTimestamp("FEC_CAMBIO", attSolicitudDeclaracion.getFecCambio());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdePersona()!=null){
			unaSentencia.setLong("IDE_PERSONA", attSolicitudDeclaracion.getIdePersona());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeTarea()!=null){
			unaSentencia.setLong("IDE_TAREA", attSolicitudDeclaracion.getIdeTarea());
		}/*fin de if*/
	}/*fin de asignarValoresGenerico*/
	
	/**
	 * Asigna los valores a la consulta, de acuerdo con los datos proporcionados.
	 * 
	 * @param sql Dato de tipo <code>DSentenciaSQL</code> a donde se asignarán valores.
	 */
	private void asignarValoresGenericoAtt(DSentenciaSQL unaSentencia) throws SQLException{
	   if(attSolicitudDeclaracion.getIdeDocumento()!=null){
			unaSentencia.setLong("IDE_DOCUMENTO", attSolicitudDeclaracion.getIdeDocumento());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getNumRepeticion()!=null){
			unaSentencia.setInt("NUM_REPETICION", attSolicitudDeclaracion.getNumRepeticion());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeFormato()!=null){
			unaSentencia.setInt("IDE_FORMATO", attSolicitudDeclaracion.getIdeFormato());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getNumVersionFormato()!=null){
			unaSentencia.setShort("NUM_VERSION_FORMATO", attSolicitudDeclaracion.getNumVersionFormato());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeFormatoCarga()!=null){
			unaSentencia.setInt("IDE_FORMATO_CARGA", attSolicitudDeclaracion.getIdeFormatoCarga());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getNumVersionFormatoCarga()!=null){
			unaSentencia.setShort("NUM_VERSION_FORMATO_CARGA", attSolicitudDeclaracion.getNumVersionFormatoCarga());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getCodEstado()!=null){
			unaSentencia.setInt("COD_ESTADO", attSolicitudDeclaracion.getCodEstado());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeUsuarioCambio()!=null){
			unaSentencia.setLong("IDE_USUARIO_CAMBIO", attSolicitudDeclaracion.getIdeUsuarioCambio());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getFecCambio()!=null){
			unaSentencia.setTimestamp("FEC_CAMBIO", attSolicitudDeclaracion.getFecCambio());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdePersona()!=null){
			unaSentencia.setLong("IDE_PERSONA", attSolicitudDeclaracion.getIdePersona());
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeTarea()!=null){
			unaSentencia.setLong("IDE_TAREA", attSolicitudDeclaracion.getIdeTarea());
		}/*fin de if*/
	}/*fin de asignarValoresGenerico*/
	

	/**
	 * Construye un objeto SolicitudDeclaracion con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private void cargarSolicitudDeclaracion(IDDataSet resultado) throws SQLException {
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		toSolicitudDeclaracion = completarSolicitudDeclaracion(resultado);
	}

	/**
	 * Construye objetos SolicitudDeclaracion con base en el data set.
	 * @param resultado Contenedor de los datos
	 * @throws SQLException Si ocurre un error de base de datos al cargar los objetos
	 */
	private void cargarObjetosSolicitudDeclaracion(IDDataSet resultado) throws SQLException {
		objetosSolicitudDeclaracion = new ArrayList();
		toSolicitudDeclaracion = null;
		if (resultado.getNumeroFilas() == 0) {
			return;
		}
		resultado.primero();
		do {
			DSolicitudDeclaracionTO objeto = completarSolicitudDeclaracion(resultado);
			objetosSolicitudDeclaracion.add(objeto);
		} while (resultado.siguiente());
		resultado.primero();
	}

	/**
	 * Construye un objeto SolicitudDeclaracion con base en el data set.
	 * El data set debe contener datos en la posiciï¿½n actual.
	 * @param resultado Contenedor de los datos
	 * @return Un SolicitudDeclaracion
	 * @throws SQLException Si ocurre un error de base de datos al cargar el objeto
	 */
	private DSolicitudDeclaracionTO completarSolicitudDeclaracion(IDDataSet resultado) throws SQLException {
		// Conformar el objeto PK
		DSolicitudDeclaracionPKTO pk = new DSolicitudDeclaracionPKTO();
		//java.lang.Long
		pk.setIdeDocumentoCarga(resultado.getLongWrapper("IDE_DOCUMENTO_CARGA"));
		//java.lang.Integer
		pk.setNumRepeticionCarga(resultado.getIntWrapper("NUM_REPETICION_CARGA"));

		// Conformar el objeto Att
		DSolicitudDeclaracionAttTO att = new DSolicitudDeclaracionAttTO();
		//java.lang.Long
		att.setIdeDocumento(resultado.getLongWrapper("IDE_DOCUMENTO"));
		//java.lang.Integer
		att.setNumRepeticion(resultado.getIntWrapper("NUM_REPETICION"));
		//java.lang.Integer
		att.setIdeFormato(resultado.getIntWrapper("IDE_FORMATO"));
		//java.lang.Byte
		if (resultado.getValorPorNombre("NUM_VERSION_FORMATO") != null) {
			att.setNumVersionFormato(resultado.getByteWrapper("NUM_VERSION_FORMATO"));
		}

		//java.lang.Integer
		if (resultado.getValorPorNombre("IDE_FORMATO_CARGA") != null) {
			att.setIdeFormatoCarga(resultado.getIntWrapper("IDE_FORMATO_CARGA"));
		}
		//java.lang.Byte
		if (resultado.getValorPorNombre("NUM_VERSION_FORMATO_CARGA") != null) {
			att.setNumVersionFormatoCarga(resultado.getByteWrapper("NUM_VERSION_FORMATO_CARGA"));
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
		DSolicitudDeclaracionTO to = new DSolicitudDeclaracionTO();
		to.setPK(pk);
		to.setAtt(att);
		return to;
	}
	
	/**
	 * Arma el filtro de acuerdo con los parámetros proporcionados para la consulta.
	 * 
	 * @return Dato de tipo <code>String</code> con el filtro armado.
	 */
	private String armarFiltroGenerico(){
		StringBuffer sql=new StringBuffer("");
		boolean primer=true;
		String y=" AND ";
		if(attSolicitudDeclaracion.getIdeDocumento()!=null){
			if(!primer){				
				sql.append(y);
			}/*fin de if*/
			sql.append("IDE_DOCUMENTO=:IDE_DOCUMENTO");
			primer=false;
		}/*fin de if*/
		if(pkSolicitudDeclaracion.getIdeDocumentoCarga()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("IDE_DOCUMENTO_CARGA=:IDE_DOCUMENTO_CARGA");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getNumRepeticion()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("NUM_REPETICION=:NUM_REPETICION");
			primer=false;
		}/*fin de if*/
		if(pkSolicitudDeclaracion.getNumRepeticionCarga()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("NUM_REPETICION_CARGA=:NUM_REPETICION_CARGA");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getCodEstado()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("COD_ESTADO=:COD_ESTADO");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getFecCambio()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("FEC_CAMBIO=:FEC_CAMBIO");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeFormato()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("IDE_FORMATO=:IDE_FORMATO");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeFormatoCarga()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("IDE_FORMATO_CARGA=:IDE_FORMATO_CARGA");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdePersona()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("IDE_PERSONA=:IDE_PERSONA");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeTarea()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("IDE_TAREA=:IDE_TAREA");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeUsuarioCambio()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getNumVersionFormato()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("NUM_VERSION_FORMATO=:NUM_VERSION_FORMATO");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getNumVersionFormatoCarga()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("NUM_VERSION_FORMATO_CARGA=:NUM_VERSION_FORMATO_CARGA");
			primer=false;
		}/*fin de if*/
		return sql.toString();
	}/*fin de armarFiltroGenerico*/
	
	private String armarFiltroGenericoAtt(){
		StringBuffer sql=new StringBuffer("");
		boolean primer=true;
		String y=" AND ";
		if(attSolicitudDeclaracion.getIdeDocumento()!=null){
			if(!primer){				
				sql.append(y);
			}/*fin de if*/
			sql.append("IDE_DOCUMENTO=:IDE_DOCUMENTO");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getNumRepeticion()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("NUM_REPETICION=:NUM_REPETICION");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getCodEstado()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("COD_ESTADO=:COD_ESTADO");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getFecCambio()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("FEC_CAMBIO=:FEC_CAMBIO");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeFormato()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("IDE_FORMATO=:IDE_FORMATO");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeFormatoCarga()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("IDE_FORMATO_CARGA=:IDE_FORMATO_CARGA");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdePersona()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("IDE_PERSONA=:IDE_PERSONA");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeTarea()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("IDE_TAREA=:IDE_TAREA");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getIdeUsuarioCambio()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getNumVersionFormato()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("NUM_VERSION_FORMATO=:NUM_VERSION_FORMATO");
			primer=false;
		}/*fin de if*/
		if(attSolicitudDeclaracion.getNumVersionFormatoCarga()!=null){
			if(!primer){
				sql.append(y);
			}/*fin de if*/
			sql.append("NUM_VERSION_FORMATO_CARGA=:NUM_VERSION_FORMATO_CARGA");
			primer=false;
		}/*fin de if*/
		return sql.toString();
	}/*fin de armarFiltroGenericoatt*/

	/**
	 * Devuelve el objeto SolicitudDeclaracion que se haya consultado.
	 * @return Un objeto DSolicitudDeclaracionTO
	 */
	public DSolicitudDeclaracionTO getSolicitudDeclaracion() {
		return toSolicitudDeclaracion;
	}

	/**
	 * Devuelve la colecciï¿½n de objetos SolicitudDeclaracion que se hayan consultado.
	 * @return Un Collection con objetos DSolicitudDeclaracionTO
	 */
	public Collection getColeccionSolicitudDeclaracion() {
		return objetosSolicitudDeclaracion;
	}

	/**
	 * Indica si el DAO es de ejecuciï¿½n libre.
	 * @return true si es de ejecuciï¿½n libre; false de lo contrario
	 */
	public boolean isEjecucionLibre() {
		return false;
	}

	/**
	 * Mï¿½todo para validar los parï¿½metros inicializados, invocado por el administrador de persistencia.
	 * @return true si los parï¿½metros son vï¿½lidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parï¿½metros no son vï¿½lidos
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
		case CONSULTAR_POR_PK:
		case CONSULTA_GENERICA:
		case CONSULTA_GENERICA_ATT:
		case CONSULTA_FORMATO_FEC_CAMBIO:
			operacion = "la consulta"; break;
		}

		if (operacion == null) {
			manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_INVALIDA);
			String mensaje = manipulador.getMensaje();
			throw new DValidarExcepcion(mensaje, mensaje);
		}

		if (tipoOperacion == CREAR || tipoOperacion == ACTUALIZAR) {
			parametros.put(this.getClass().getName()+":validar:toSolicitudDeclaracion",toSolicitudDeclaracion);
			parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion",pkSolicitudDeclaracion);
			parametros.put(this.getClass().getName()+":validar:attSolicitudDeclaracion",attSolicitudDeclaracion);

			parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion.getIdeDocumentoCarga()",pkSolicitudDeclaracion.getIdeDocumentoCarga());
			parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion.getNumRepeticionCarga()",pkSolicitudDeclaracion.getNumRepeticionCarga());
			parametros.put(this.getClass().getName()+":validar:attSolicitudDeclaracion.getIdeFormato()",attSolicitudDeclaracion.getIdeFormato());
		} else 	if (tipoOperacion == CONSULTAR_POR_PK || tipoOperacion == ELIMINAR) {
			parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion",pkSolicitudDeclaracion);
			parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion.getIdeDocumentoCarga()",pkSolicitudDeclaracion.getIdeDocumentoCarga());
			parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion.getNumRepeticionCarga()",pkSolicitudDeclaracion.getNumRepeticionCarga());
		}	else if(tipoOperacion==CONSULTA_GENERICA){
			parametros.put(this.getClass().getName()+":validar:toSolicitudDeclaracion",toSolicitudDeclaracion);
			parametros.put(this.getClass().getName()+":validar:pkSolicitudDeclaracion",pkSolicitudDeclaracion);
			parametros.put(this.getClass().getName()+":validar:attSolicitudDeclaracion",attSolicitudDeclaracion);
		} else if(tipoOperacion==CONSULTA_GENERICA_ATT){
			parametros.put(this.getClass().getName()+":validar:toSolicitudDeclaracion",toSolicitudDeclaracion);			
			parametros.put(this.getClass().getName()+":validar:attSolicitudDeclaracion",attSolicitudDeclaracion);
		} else if(tipoOperacion == CONSULTA_FORMATO_FEC_CAMBIO) {
			parametros.put(this.getClass().getName()+":validar:toSolicitudDeclaracion",toSolicitudDeclaracion);			
			parametros.put(this.getClass().getName()+":validar:attSolicitudDeclaracion",attSolicitudDeclaracion);
			parametros.put(this.getClass().getName()+":validar:attSolicitudDeclaracion.getIdeFormato()",attSolicitudDeclaracion.getIdeFormato());
			parametros.put(this.getClass().getName()+":validar:attSolicitudDeclaracion.getFecCambio()",attSolicitudDeclaracion.getFecCambio());
		}


		validarParametros(operacion,parametros);
		return true;
	}
}