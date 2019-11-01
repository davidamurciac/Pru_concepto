package co.gov.dian.muisca.diligenciamientomasivo.dao.movimientoperiodico.impl.oracle;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.general.persistencia.dao.DDAO;
import co.gov.dian.muisca.arquitectura.general.persistencia.dao.DSentenciaSQL;
import co.gov.dian.muisca.arquitectura.interfaces.IDDataSet;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.dataset.DDataSet;
import co.gov.dian.muisca.diligenciamientomasivo.dao.movimientoperiodico.IDDAOMovimientoPeriodico;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico.DSolicitudIngresoNoMovidaAttTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico.DSolicitudIngresoNoMovidaPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico.DSolicitudIngresoNoMovidaTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DMarcaDocumentoAttTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DMarcaDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DMarcaDocumentoTO;



/****
 * DAO con la implementación de las operaciones que soportan el movimiento periódico de solicitudes de ingreso que figuran como no movidas en 
 * el sistema.
 * 
 * @author nfontechar
 *
 */
public class DDAOMovimientoPeriodico extends DDAO implements IDDAOMovimientoPeriodico{

	
	/*
	 * CONSTANTES
	 */
	private static final String FORMATOS="1082,1084,1124,1125,1126";
	
	
	
	/*
	 * ATRIBUTOS
	 */
	
	/****
	 * Atributo de tipo boolean que indica la manera en como se ejecuta el DAO
	 */
	private boolean ejecucionLibre=false;
	
	
	
	/****
	 * Lista de objetos DSolicitudIngresoNoMovidaTO que contiene las solicitudes que figuran como no movidas en el sistema
	 */
	private List<DSolicitudIngresoNoMovidaTO> solicitudesNoMovidas=null;
	
	
	
	/****
	 * Lista de objetos DMarcaDocumentoTO que contiene el log de marcas de los documentos que conforman las solicitudes no movidas
	 */
	private List<DMarcaDocumentoTO> marcasDoc=null;
	
	
	
	/**
	 * TO que representa un registro del log de marcas de cada documento de las solicitudes no movidas
	 */
	private DMarcaDocumentoTO marcaTO=null;
	
	
	
	/**
	 * TO que representa la llave primaria del registro del log de marcas de cada documento de las solicitudes no movidas
	 */
	private DMarcaDocumentoPKTO marcaPKTO=null;
	
	
	
	/**
	 * TO que representa los atributos del registro del log de marcas de cada documento de las solicitudes no movidas
	 */
	private DMarcaDocumentoAttTO marcaAttTO=null;
	
	
	
	/**
	 * TO que contiene los datos de una solicitud no movida
	 */
	private DSolicitudIngresoNoMovidaTO to=null;
	
	
	
	/**
	 * TO que contiene los datos de la llave primaria de una solicitud no movida
	 */
	private DSolicitudIngresoNoMovidaPKTO pk=null;
	
	
	
	/**
	 * TO que contiene los atributos de una solicitud no movida
	 */
	private DSolicitudIngresoNoMovidaAttTO att=null;
	
	
	
	
	
	
	/*
	 * MÉTODOS PRIVADOS
	 */
	
	
	/**
	 * Asigna a la sentencia SQL los valores de inicio y fin para definir los intervalos en las consultas de conteo 
	 * 
	 * @param sentencia Objeto de tipo DSentenciaSQL que se completará con valores
	 * @throws SQLException Si la asignación de valores falla por algún motivo 
	 */
	private void asignarValoresIntervalo(DSentenciaSQL sentencia) throws SQLException{
		sentencia.setInt("INICIO", to.getInicio());
		sentencia.setInt("FIN", to.getFin());
	}/*fin de asignarValoresIntervalo*/
	
	
	/******
	 * Asigna a la sentencia SQL los valores contenidos en el atributo pk para completarla
	 * 
	 * @param sentencia Objeto de tipo DSentenciaSQL que se completará con valores
	 * @throws SQLException Si la asignación de valores falla por algún motivo
	 */
	private void asignarValoresDePKASentencia(DSentenciaSQL sentencia) throws SQLException{
		if(pk!=null){
			if(pk.getIdeSolicitud()!=null){
				sentencia.setLong("IDE_SOLICITUD",pk.getIdeSolicitud().longValue());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDePKASentencia(): No se pudo extraer el ID de Solicitud, para asignación!!");
			}/*fin de else*/
		}/*fin de if*/
		else{
			throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDePKASentencia(): No se pudo extraer la información correspondiente a la llave primaria de la tabla, para asignación!!");
		}/*fin de else*/
	}/*fin de asignarValoresDePKASentencia*/
	
	
	
	
	/****
	 * Asigna a ls sentencia SQL los valores contenidos en el atributo att para completarla
	 * 
	 * @param sentencia Objeto de tipo DSentenciaSQL que se completará con valores
	 * @throws SQLException Si la asignación de valores falla por algún motivo
	 */
	private void asignarValoresDeAttASentencia(DSentenciaSQL sentencia) throws SQLException{
		if(att!=null){
			if(att.getCodEstado()!=null){
				sentencia.setInt("COD_ESTADO",att.getCodEstado().intValue());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer el Código de Estado, para asignación!!");
			}/*fin de else*/
			if(att.getCodConcepto()!=null){
				sentencia.setInt("COD_CONCEPTO",att.getCodConcepto().intValue());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer el Código de Concepto, para asignación!!");
			}/*fin de else*/
			if(att.getNumPeriodoVigencia()!=null){
				sentencia.setInt("NUM_PERIODO_VIGENCIA",att.getNumPeriodoVigencia().intValue());
			}/*fin de if*/
			else{
				sentencia.setNull("NUM_PERIODO_VIGENCIA",Types.INTEGER);
			}/*fin de else*/
			if(att.getAnioVigencia()!=null){
				sentencia.setInt("ANIO_VIGENCIA",att.getAnioVigencia().intValue());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer el Año de Vigencia, para asignación!!");
			}/*fin de else*/
			if(att.getIdePersona()!=null){
				sentencia.setLong("IDE_PERSONA",att.getIdePersona().longValue());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer el ID de Persona, para asignación!!");
			}/*fin de else*/
			if(att.getIdeOrganizacion()!=null){
				sentencia.setInt("IDE_ORGANIZACION",att.getIdeOrganizacion().intValue());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer el ID de Organización, para asignación!!");
			}/*fin de else*/
			if(att.getIdeFormato()!=null){
				sentencia.setInt("IDE_FORMATO",att.getIdeFormato().intValue());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer el ID de Formato, para asignación!!");
			}/*fin de else*/
			if(att.getNumVersionFormato()!=null){
				sentencia.setInt("NUM_VERSION_FORMATO",att.getNumVersionFormato().intValue());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer el Número de Versión de Formato, para asignación!!");
			}/*fin de else*/
			if(att.getNumTotalArchivos()!=null){
				sentencia.setInt("NUM_TOTAL_ARCHIVOS",att.getNumTotalArchivos().intValue());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer el Número Total de Archivos, para asignación!!");
			}/*fin de else*/
			if(att.getNumTotalRegistros()!=null){
				sentencia.setInt("NUM_TOTAL_REGISTROS",att.getNumTotalRegistros().intValue());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer el Número Total de Registros, para asignación!!");
			}/*fin de else*/
			if(att.getIdeUsuarioSolicitud()!=null){
				sentencia.setInt("IDE_USUARIO_SOLICITUD",att.getIdeUsuarioSolicitud().intValue());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer el ID de Usuario Solicitud, para asignación!!");
			}/*fin de else*/
			if(att.getFecSolicitud()!=null){
				sentencia.setDate("FEC_SOLICITUD",att.getFecSolicitud());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer la Fecha de Solicitud, para asignación!!");
			}/*fin de else*/
			if(att.getIdeUsuarioCambio()!=null){
				sentencia.setInt("IDE_USUARIO_CAMBIO",att.getIdeUsuarioCambio().intValue());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer el ID de Usuario Cambio, para asignación!!");
			}/*fin de else*/
			if(att.getFecCambio()!=null){
				sentencia.setDate("FEC_CAMBIO",att.getFecCambio());
			}/*fin de if*/
			else{
				throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer la Fecha de Cambio, para asignación!!");
			}/*fin de else*/
			if(att.getIdeSolicitudCorregida()!=null){
				sentencia.setLong("IDE_SOLICITUD_CORREGIDA",att.getIdeSolicitudCorregida().longValue());
			}/*fin de if*/
			else{
				sentencia.setNull("IDE_SOLICITUD_CORREGIDA",Types.INTEGER);
			}/*fin de else*/
			if(att.getNumRegistrosOk()!=null){
				sentencia.setInt("NUM_REGISTROS_OK",att.getNumRegistrosOk().intValue());
			}/*fin de if*/
			else{
				sentencia.setNull("NUM_REGISTROS_OK",Types.INTEGER);
			}/*fin de else*/
			if(att.getNumRegistrosConError()!=null){
				sentencia.setInt("NUM_REGISTROS_CON_ERROR",att.getNumRegistrosConError().intValue());
			}/*fin de if*/
			else{
				sentencia.setNull("NUM_REGISTROS_CON_ERROR",Types.INTEGER);
			}/*fin de else*/
			if(att.getCodConceptoCambio()!=null){
				sentencia.setInt("COD_CONCEPTO_CAMBIO",att.getCodConceptoCambio().intValue());
			}/*fin de if*/
			else{
				sentencia.setNull("COD_CONCEPTO_CAMBIO",Types.INTEGER);
			}/*fin de else*/
			if(att.getIdeConceptoCancelacion()!=null){
				sentencia.setInt("IDE_CONCEPTO_CANCELACION",att.getIdeConceptoCancelacion().intValue());
			}/*fin de if*/
			else{
				sentencia.setNull("IDE_CONCEPTO_CANCELACION",Types.INTEGER);
			}/*fin de else*/
			if(att.getIndMovida()!=null){
				sentencia.setString("IND_MOVIDA",att.getIndMovida());
			}/*fin de if*/
			else{
				sentencia.setNull("IND_MOVIDA",Types.VARCHAR);
			}/*fin de else*/
			if(att.getNumRegistrosMovidos()!=null){
				sentencia.setInt("NUM_REGISTROS_MOVIDOS",att.getNumRegistrosMovidos().intValue());
			}/*fin de if*/
			else{
				sentencia.setNull("NUM_REGISTROS_MOVIDOS",Types.INTEGER);
			}/*fin de else*/
			if(att.getIdeTanda()!=null){
				sentencia.setInt("IDE_TANDA",att.getIdeTanda().intValue());
			}/*fin de if*/
			else{
				sentencia.setNull("IDE_TANDA",Types.INTEGER);
			}/*fin de else*/		
		}/*fin de if*/
		else{
			throw new SQLException("DDAOMovimientoPeriodico.asignarValoresDeAttASentencia(): No se pudo extraer la información correspondiente a los atributos de la tabla, para asignación!!");
		}/*fin de else*/
	}/*fin de asignarValoresDeAttASentencia*/
	
	
	
	
	/***
	 * Arma un objeto de tipo DSolicitudIngresoNoMovidaPKTO con los datos obtenidos de la consulta a Base de Datos
	 * 
	 * @param resultado Objeto de tipo IDDataSet con los datos obtenidos de la Base de Datos
	 * @return sinmPKTO Objeto de tipo DSolicitudIngresoNoMovidaPKTO resultante
	 * @throws SQLException Si el armado del objeto falla por alguna razón
	 */
	private DSolicitudIngresoNoMovidaPKTO armarSolicitudIngresoNoMovidaPK(IDDataSet resultado) throws SQLException{
		DSolicitudIngresoNoMovidaPKTO sinmPKTO=new DSolicitudIngresoNoMovidaPKTO();
		BigDecimal bdIdeSolicitud=(BigDecimal) resultado.getValorPorNombre("IDE_SOLICITUD");
		sinmPKTO.setIdeSolicitud(new Long(bdIdeSolicitud.longValue()));
		return sinmPKTO;
	}/*fin de armarSolicitudIngresoNoMovidaPK*/
	
	
	
	
	/***
	 * Arma un objeto de tipo DSolicitudIngresoNoMovidaAttTO con los datos obtenidos de la consulta a Base de Datos
	 * 
	 * @param resultado Objeto de tipo IDDataSet con los datos obtenidos de la Base de Datos
	 * @return sinmAttTO Objeto de tipo DSolicitudIngresoNoMovidaAttTO resultante
	 * @throws SQLException Si el armado del objeto falla por alguna razón
	 */
	private DSolicitudIngresoNoMovidaAttTO armarSolicitudIngresoNoMovidaAtt(IDDataSet resultado) throws SQLException{
		DSolicitudIngresoNoMovidaAttTO sinmAttTO=new DSolicitudIngresoNoMovidaAttTO();				
		String codEstado=(String) resultado.getValorPorNombre("COD_ESTADO");
		BigDecimal bdCodConcepto=(BigDecimal) resultado.getValorPorNombre("COD_CONCEPTO");
		BigDecimal bdNumPeriodoVigencia=(BigDecimal) resultado.getValorPorNombre("NUM_PERIODO_VIGENCIA");
		BigDecimal bdAnioVigencia=(BigDecimal) resultado.getValorPorNombre("ANIO_VIGENCIA");
		BigDecimal bdIdePersona=(BigDecimal) resultado.getValorPorNombre("IDE_PERSONA");
		BigDecimal bdIdeOrganizacion=(BigDecimal) resultado.getValorPorNombre("IDE_ORGANIZACION");
		BigDecimal bdIdeFormato=(BigDecimal) resultado.getValorPorNombre("IDE_FORMATO");
		BigDecimal bdNumVersionFormato=(BigDecimal) resultado.getValorPorNombre("NUM_VERSION_FORMATO");
		BigDecimal bdNumTotalArchivos=(BigDecimal) resultado.getValorPorNombre("NUM_TOTAL_ARCHIVOS");
		BigDecimal bdNumTotalRegistros=(BigDecimal) resultado.getValorPorNombre("NUM_TOTAL_REGISTROS");
		BigDecimal bdIdeUsuarioSolicitud=(BigDecimal) resultado.getValorPorNombre("IDE_USUARIO_SOLICITUD");
		Timestamp tsFecSolicitud=(Timestamp) resultado.getValorPorNombre("FEC_SOLICITUD");
		Date fecSolicitud=new Date(tsFecSolicitud.getTime()); 
		BigDecimal bdIdeUsuarioCambio=(BigDecimal) resultado.getValorPorNombre("IDE_USUARIO_CAMBIO");
		Timestamp tsFecCambio=(Timestamp) resultado.getValorPorNombre("FEC_CAMBIO"); 
		Date fecCambio=new Date(tsFecCambio.getTime()); 
		BigDecimal bdIdeSolicitudCorregida=(BigDecimal) resultado.getValorPorNombre("IDE_SOLICITUD_CORREGIDA");
		BigDecimal bdNumRegistrosOk=(BigDecimal) resultado.getValorPorNombre("NUM_REGISTROS_OK");
		BigDecimal bdNumRegistrosConError=(BigDecimal) resultado.getValorPorNombre("NUM_REGISTROS_CON_ERROR");
		BigDecimal bdCodConceptoCambio=(BigDecimal) resultado.getValorPorNombre("COD_CONCEPTO_CAMBIO");
		BigDecimal bdIdeConceptoCancelacion=(BigDecimal) resultado.getValorPorNombre("IDE_CONCEPTO_CANCELACION");
		String indMovida=(String) resultado.getValorPorNombre("IND_MOVIDA");
		BigDecimal bdNumRegistrosMovidos=(BigDecimal) resultado.getValorPorNombre("NUM_REGISTROS_MOVIDOS");
		BigDecimal bdIdeTanda=(BigDecimal) resultado.getValorPorNombre("IDE_TANDA");
		sinmAttTO.setCodEstado(new Integer(codEstado));
		sinmAttTO.setCodConcepto(new Integer(bdCodConcepto!=null ? bdCodConcepto.intValue() : -1));
		sinmAttTO.setNumPeriodoVigencia(new Integer(bdNumPeriodoVigencia!=null ? bdNumPeriodoVigencia.intValue() : -1));
		sinmAttTO.setAnioVigencia(new Integer(bdAnioVigencia!=null ? bdAnioVigencia.intValue() : -1));
		sinmAttTO.setIdePersona(new Long(bdIdePersona!=null ? bdIdePersona.longValue(): -1));
		sinmAttTO.setIdeOrganizacion(new Integer(bdIdeOrganizacion!=null ? bdIdeOrganizacion.intValue(): -1));
		sinmAttTO.setIdeFormato(new Integer(bdIdeFormato!=null ? bdIdeFormato.intValue() : null));
		sinmAttTO.setNumVersionFormato(new Integer(bdNumVersionFormato!=null ? bdNumVersionFormato.intValue() : -1));
		sinmAttTO.setNumTotalArchivos(new Integer(bdNumTotalArchivos!=null ? bdNumTotalArchivos.intValue() : -1));
		sinmAttTO.setNumTotalRegistros(new Integer(bdNumTotalRegistros!=null ? bdNumTotalRegistros.intValue() : -1));
		sinmAttTO.setIdeUsuarioSolicitud(new Integer(bdIdeUsuarioSolicitud!=null ? bdIdeUsuarioSolicitud.intValue() : -1));
		sinmAttTO.setIdeUsuarioCambio(new Integer(bdIdeUsuarioCambio!=null ? bdIdeUsuarioCambio.intValue() : -1));
		sinmAttTO.setIdeSolicitudCorregida(new Long(bdIdeSolicitudCorregida!=null ? bdIdeSolicitudCorregida.longValue() : -1));
		sinmAttTO.setNumRegistrosOk(new Integer(bdNumRegistrosOk!=null ? bdNumRegistrosOk.intValue() : -1));
		sinmAttTO.setNumRegistrosConError(new Integer(bdNumRegistrosConError!=null ? bdNumRegistrosConError.intValue() : -1));
		sinmAttTO.setCodConceptoCambio(new Integer(bdCodConceptoCambio!=null ? bdCodConceptoCambio.intValue() : -1));
		sinmAttTO.setIdeConceptoCancelacion(new Integer(bdIdeConceptoCancelacion!=null ? bdIdeConceptoCancelacion.intValue() : -1));
		sinmAttTO.setNumRegistrosMovidos(new Integer(bdNumRegistrosMovidos!=null ? bdNumRegistrosMovidos.intValue() : -1));
		sinmAttTO.setIdeTanda(new Integer(bdIdeTanda!=null ? bdIdeTanda.intValue() : -1));
		sinmAttTO.setFecSolicitud(fecSolicitud);
		sinmAttTO.setFecCambio(fecCambio);
		sinmAttTO.setIndMovida(indMovida);		
		return sinmAttTO;
	}/*fin de armarSolicitudIngresoNoMovidaAtt*/
	
	
	
	
	/***
	 * Arma un objeto de tipo DSolicitudIngresoNoMovidaTO con los datos obtenidos de la consulta a Base de Datos
	 * 
	 * @param resultado Objeto de tipo IDDataSet con los datos obtenidos de la Base de Datos
	 * @return sinmTO Objeto de tipo DSolicitudIngresoNoMovidaTO resultante
	 * @throws SQLException Si el armado del objeto falla por alguna razón
	 */
	private DSolicitudIngresoNoMovidaTO armarSolicitudIngresoNoMovidaTO(IDDataSet resultado) throws SQLException{
		DSolicitudIngresoNoMovidaTO sinmTO=new DSolicitudIngresoNoMovidaTO();
		if(getTipoOperacion()==CONSULTAR_MARCAS_PENDIENTES || getTipoOperacion()==CONSULTAR_REGISTROS_PENDIENTES){			
			BigDecimal bd=(BigDecimal) resultado.getValorPorNombre("NRO_REGISTROS");
			sinmTO.setRegistrosPendientes(new Integer(bd.intValue()));
		}/*fin de if*/
		else{
			sinmTO.setPk(armarSolicitudIngresoNoMovidaPK(resultado));
			sinmTO.setAtt(armarSolicitudIngresoNoMovidaAtt(resultado));
		}/*fin de else*/		
		return sinmTO;
	}/*fin de armarSolicitudIngresoNoMovidaTO*/
	
	
	
	
	/***
	 * Arma un objeto de tipo DMarcaDocumentoPKTO con los datos obtenidos de la consulta a Base de Datos
	 * 
	 * @param resultado Objeto de tipo IDDataSet con los datos obtenidos de la Base de Datos
	 * @return marcaPk Objeto de tipo DMarcaDocumentoPKTO resultante
	 * @throws SQLException Si el armado del objeto falla por alguna razón
	 */
	private DMarcaDocumentoPKTO armarMarcaDocumentoPK(IDDataSet resultado) throws SQLException{
		DMarcaDocumentoPKTO marcaPk=new DMarcaDocumentoPKTO();
		BigDecimal bdIdeDocumento=(BigDecimal) resultado.getValorPorNombre("IDE_DOCUMENTO");
		BigDecimal bdNumRepeticion=(BigDecimal) resultado.getValorPorNombre("NUM_REPETICION");
		BigDecimal bdIdeMarcaDocumento=(BigDecimal) resultado.getValorPorNombre("IDE_MARCA_DOCUMENTO");
		BigDecimal bdIdeFormato=(BigDecimal) resultado.getValorPorNombre("IDE_FORMATO");
		BigDecimal bdNumVersionFormato=(BigDecimal) resultado.getValorPorNombre("NUM_VERSION_FORMATO");
		BigDecimal bdIdeMarcaFormato=(BigDecimal) resultado.getValorPorNombre("IDE_MARCA_FORMATO");
		marcaPk.setIdeDocumento(bdIdeDocumento!=null ? new Long(bdIdeDocumento.longValue()) : new Long(-1));
		marcaPk.setNumRepeticion(bdNumRepeticion!=null ? new Integer(bdNumRepeticion.intValue()) : new Integer(-1));
		marcaPk.setIdeMarcaDocumento(bdIdeMarcaDocumento!=null ? new Integer(bdIdeMarcaDocumento.intValue()) : new Integer(-1));
		marcaPk.setIdeFormato(bdIdeFormato!=null ? new Integer(bdIdeFormato.intValue()) : new Integer(-1));
		marcaPk.setNumVersionFormato(bdNumVersionFormato!=null ? new Integer(bdNumVersionFormato.intValue()) : new Integer(-1));
		marcaPk.setIdeMarcaFormato(bdIdeMarcaFormato!=null ? new Integer(bdIdeMarcaFormato.intValue()) : new Integer(-1));
		return marcaPk;
	}/*fin de armarMarcaDocumentoPK*/
	
	
	
	
	/***
	 * Arma un objeto de tipo DMarcaDocumentoAttTO con los datos obtenidos de la consulta a Base de Datos
	 * 
	 * @param resultado Objeto de tipo IDDataSet con los datos obtenidos de la Base de Datos
	 * @return marcaAtt Objeto de tipo DMarcaDocumentoAttTO resultante
	 * @throws SQLException Si el armado del objeto falla por alguna razón
	 */
	private DMarcaDocumentoAttTO armarMarcaDocumentoAtt(IDDataSet resultado) throws SQLException{
		DMarcaDocumentoAttTO marcaAtt=new DMarcaDocumentoAttTO();
		BigDecimal bdIdeGrupo=(BigDecimal) resultado.getValorPorNombre("IDE_GRUPO");
		BigDecimal bdIdeCasilla=(BigDecimal) resultado.getValorPorNombre("IDE_CASILLA");
		BigDecimal bdNumItem=(BigDecimal) resultado.getValorPorNombre("NUM_ITEM");
		BigDecimal bdNumOcurrencia=(BigDecimal) resultado.getValorPorNombre("NUM_OCURRENCIA");
		String indEsActivo=(String) resultado.getValorPorNombre("IND_ES_ACTIVO");
		String valMsjDetallado=(String) resultado.getValorPorNombre("VAL_MENSAJE_DETALLADO");
		marcaAtt.setIdeGrupo(bdIdeGrupo!=null ? new Integer(bdIdeGrupo.intValue()) : new Integer(-1));
		marcaAtt.setIdeCasilla(bdIdeCasilla!=null ? new Integer(bdIdeCasilla.intValue()) : new Integer(-1));
		marcaAtt.setNumItem(bdNumItem!=null ? new Integer(bdNumItem.intValue()) : new Integer(-1));
		marcaAtt.setNumOcurrencia(bdNumOcurrencia!=null ? new Integer(bdNumOcurrencia.intValue()) : new Integer(-1));
		marcaAtt.setIndEsActivo(indEsActivo.equals("N") ? false : true);
		marcaAtt.setMensajeDetallado(valMsjDetallado);
		return marcaAtt;
	}/*fin de armarMarcaDocumentoAtt*/
	
	
	
	
	/***
	 * Arma un objeto de tipo DMarcaDocumentoTO con los datos obtenidos de la consulta a Base de Datos
	 * 
	 * @param resultado Objeto de tipo IDDataSet con los datos obtenidos de la Base de Datos
	 * @return mdocTO Objeto de tipo DMarcaDocumentoTO resultante
	 * @throws SQLException Si el armado del objeto falla por alguna razón
	 */
	private DMarcaDocumentoTO armarMarcaDocumentoTO(IDDataSet resultado) throws SQLException{
		DMarcaDocumentoTO mdocTO=new DMarcaDocumentoTO();
		mdocTO.setPK(armarMarcaDocumentoPK(resultado));
		mdocTO.setAtt(armarMarcaDocumentoAtt(resultado));
		return mdocTO;
	}/*fin de armarMarcaDocumentoTO*/
	
	
	
	
	/******
	 * Arma las colecciones de TOs correspondientes a la operación solicitada
	 * 
	 * @param resultado Objeto de tipo IDDataSet con los datos obtenidos de la Base de Datos 
	 * @throws SQLException Si el armado de las colecciones falla por algún motivo
	 */
	private void armarColeccionDeTOS(IDDataSet resultado) throws SQLException{
		solicitudesNoMovidas = new ArrayList<DSolicitudIngresoNoMovidaTO>();
		to=null;
		marcaTO=null;
		marcasDoc=new ArrayList<DMarcaDocumentoTO>();
        if (resultado.getNumeroFilas() == 0) {
            return;
        }
        resultado.primero();
        do {
        	switch(getTipoOperacion()){
        	   case CONSULTAR_SOLICITUDES_NO_MOVIDAS:
        		  solicitudesNoMovidas.add(armarSolicitudIngresoNoMovidaTO(resultado));
                  break;
        	   case CONSULTAR_LOG_MARCAS_DOCS_SOLICITUD:
        		  marcasDoc.add(armarMarcaDocumentoTO(resultado));
                  break;
        	}/*fin de switch*/            
        } while (resultado.siguiente());
        resultado.primero();
	}/*fin de armarColeccionDeTOS*/
	
	
	/******
	 * Arma un objeto DSolicitudIngresoNoMovidaTO
	 * 
	 * @param resultado Objeto de tipo IDDataSet con los datos obtenidos de la Base de Datos 
	 * @throws SQLException Si el armado de las colecciones falla por algún motivo
	 */
	private void armarUnTO(IDDataSet resultado) throws SQLException{
		solicitudesNoMovidas = null;
		to=null;
		marcaTO=null;
		marcasDoc=null;
        if (resultado.getNumeroFilas() == 0) {
            return;
        }
        resultado.primero();
        to=armarSolicitudIngresoNoMovidaTO(resultado);        
 	}/*fin de armarColeccionDeTOS*/
		
	
	
	
	
	
	/*****
	 * Ejecuta una sentencia SQL de consulta a la Base de Datos
	 * 
	 * @return resultado Objeto de tipo IDDataSet con los datos obtenidos de la Base de Datos 
	 * @throws SQLException Si la ejecución de la consulta falla por algún motivo
	 */
	private IDDataSet consultarRegistros() throws SQLException{
		DSentenciaSQL sentencia = getSentenciaSQLPreparada("sentencia1");
		if(getTipoOperacion()!=CONSULTAR_MARCAS_PENDIENTES && getTipoOperacion()!=CONSULTAR_REGISTROS_PENDIENTES){
			asignarValoresIntervalo(sentencia);
		}/*fin de if*/
		sentencia.ejecutar();
        DDataSet resultado = sentencia.getDataSet();
        if(getTipoOperacion()==CONSULTAR_MARCAS_PENDIENTES || getTipoOperacion()==CONSULTAR_REGISTROS_PENDIENTES){
        	armarUnTO(resultado);
        }/*fin de if*/
        else{
        	armarColeccionDeTOS(resultado);
        }/*fin de else*/        
        return resultado;
	}/*fin de consultarRegistros*/
	
	
	
	
	
	
	/*
	 * MÉTODOS INICIALIZAR
	 */
	
	
	/****
	 * Inicializa el DAO para consulta del número de registros pendientes por mover
	 */
	public void inicializarConsultarRegistrosPorMover(){
		setTipoOperacion(CONSULTAR_REGISTROS_PENDIENTES);
	}/*fin de inicializarConsultarRegistrosPorMover*/
	
	
	/****
	 * Inicializa el DAO para consulta del número de marcas pendientes por borrar
	 */
	public void inicializarConsultarMarcasPorQuitar(){
		setTipoOperacion(CONSULTAR_MARCAS_PENDIENTES);
	}/*fin de inicializarConsultarMarcasPorQuitar*/ 
	
	
	
	/****
	 * Inicializa el DAO para consulta de las solicitudes que figuran en el sistema como no movidas
	 */
	public void inicializarConsultarSolicitudesNoMovidas(DSolicitudIngresoNoMovidaTO to){
		this.to=to;
		setTipoOperacion(CONSULTAR_SOLICITUDES_NO_MOVIDAS);
	}/*fin de inicializarConsultarSolicitudesNoMovidas*/
		
	
	
	
	/*****
	 * Inicializa el DAO para consulta del log de marcas de los documentos contenidos en las solicitudes no movidas
	 */
	public void inicializarConsultarLogMarcasDocSolictud(DSolicitudIngresoNoMovidaTO to){
		this.to=to;
		setTipoOperacion(CONSULTAR_LOG_MARCAS_DOCS_SOLICITUD);
	}/*fin de inicializarConsultarLogMarcasDocSolictud*/
	
	
	
	
	
	
	/*
	 * MÉTODOS HEREDADOS
	 */
	public IDDataSet consultar() throws SQLException {
		return consultarRegistros();
	}

	public int eliminar() throws SQLException {
		return 0;
	}

	public Map getSentenciasSQL() {
		Map m = new HashMap();
        StringBuffer sql = new StringBuffer();
        switch (getTipoOperacion()) {
           case CONSULTAR_REGISTROS_PENDIENTES:
        	  setEjecucionLibre(true);
        	  sql.append("SELECT COUNT(1) as NRO_REGISTROS FROM eys_solicitudes_ingreso s WHERE ")
                 .append("(s.cod_concepto_cambio IS NULL OR s.cod_concepto_cambio = 3) ")
                 .append("AND s.cod_estado = 24 AND ")
                 .append("s.ide_formato IN("+FORMATOS+") ") 
                 .append("AND (s.num_registros_ok IS NOT NULL AND s.num_registros_ok > 0) ")
                 .append("AND (s.ind_movida IS NULL OR s.ind_movida = 'N') ")
                 .append("AND ")
                 .append("(")
                 .append("exists (")
                 .append("select null from eys_documentos d, EYS_SOLICITUDES_EXOGENA a where ")
                 .append("d.ide_documento = a.ide_documento_gen and ")
                 .append("s.ide_solicitud = a.ide_solicitud_exogena) ")
                 .append("OR exists (")
                 .append("select null from eys_documentos d, DIL_SOLICITUDES_DECLARACION a where ")
                 .append("d.ide_documento=a.ide_documento and ")
                 .append("s.ide_solicitud= a.ide_documento_carga")
                 .append(") ")
                 .append("OR exists (")
                 .append("select null from eys_documentos d, ADU_IMP_EXP a where ")
                 .append("d.ide_documento=a.ide_documento and ")
                 .append("s.ide_solicitud= a.ide_documento_carga")
                 .append(") ")
                 .append("OR exists (")
                 .append("select null from eys_documentos d, ADU_TRAFICO_POSTAL a where ")
                 .append("d.ide_documento=a.ide_documento and ")
                 .append("s.ide_solicitud= a.ide_documento_carga")
                 .append(")")
                .append(")");
     	      m.put("sentencia1", sql.toString());
        	  break;
           case CONSULTAR_SOLICITUDES_NO_MOVIDAS:
              setEjecucionLibre(true);
              sql.append("select * from (select a.*, rownum regs from (")
                 .append("SELECT * FROM eys_solicitudes_ingreso s WHERE ")
                 .append("(s.cod_concepto_cambio IS NULL OR s.cod_concepto_cambio = 3) ")
                 .append("AND s.cod_estado = 24 AND ")
                 .append("s.ide_formato IN("+FORMATOS+") ") 
                 .append("AND (s.num_registros_ok IS NOT NULL AND s.num_registros_ok > 0) ")
                 .append("AND (s.ind_movida IS NULL OR s.ind_movida = 'N') ")
                 .append("AND ")
                 .append("(")
                 .append("exists (")
                 .append("select null from eys_documentos d, EYS_SOLICITUDES_EXOGENA a where ")
                 .append("d.ide_documento = a.ide_documento_gen and ")
                 .append("s.ide_solicitud = a.ide_solicitud_exogena) ")
                 .append("OR exists (")
                 .append("select null from eys_documentos d, DIL_SOLICITUDES_DECLARACION a where ")
                 .append("d.ide_documento=a.ide_documento and ")
                 .append("s.ide_solicitud= a.ide_documento_carga")
                 .append(") ")
                 .append("OR exists (")
                 .append("select null from eys_documentos d, ADU_IMP_EXP a where ")
                 .append("d.ide_documento=a.ide_documento and ")
                 .append("s.ide_solicitud= a.ide_documento_carga")
                 .append(") ")
                 .append("OR exists (")
                 .append("select null from eys_documentos d, ADU_TRAFICO_POSTAL a where ")
                 .append("d.ide_documento=a.ide_documento and ")
                 .append("s.ide_solicitud= a.ide_documento_carga")
                 .append(")")
                 .append(")")
                 .append("order by ide_solicitud) a where rownum <=:FIN) where regs>=:INICIO");
        	  m.put("sentencia1", sql.toString());
              break;
           case CONSULTAR_LOG_MARCAS_DOCS_SOLICITUD:
        	  setEjecucionLibre(true);
        	  sql.append("select * from (select a.*, rownum regs from (")
        	     .append("select * from eys_marcas_doc_es where ")
        	     .append("ide_documento in(")
        	     .append("select ide_documento from eys_auditoria_registros where ")
        	     .append("ide_solicitud in(")
        	     .append("SELECT ide_solicitud FROM eys_solicitudes_ingreso s WHERE ")
                 .append("(s.cod_concepto_cambio IS NULL OR s.cod_concepto_cambio = 3) ")
                 .append("AND s.cod_estado = 24 AND ")
                 .append("s.ide_formato IN("+FORMATOS+") ") 
                 .append("AND (s.num_registros_ok IS NOT NULL AND s.num_registros_ok > 0) ")
                 .append("AND (s.ind_movida IS NULL OR s.ind_movida = 'N') ")
                 .append("AND ")
                 .append("(")
                 .append("exists (")
                 .append("select null from eys_documentos d, EYS_SOLICITUDES_EXOGENA a where ")
                 .append("d.ide_documento = a.ide_documento_gen and ")
                 .append("s.ide_solicitud = a.ide_solicitud_exogena) ")
                 .append("OR exists (")
                 .append("select null from eys_documentos d, DIL_SOLICITUDES_DECLARACION a where ")
                 .append("d.ide_documento=a.ide_documento and ")
                 .append("s.ide_solicitud= a.ide_documento_carga")
                 .append(") ")
                 .append("OR exists (")
                 .append("select null from eys_documentos d, ADU_IMP_EXP a where ")
                 .append("d.ide_documento=a.ide_documento and ")
                 .append("s.ide_solicitud= a.ide_documento_carga")
                 .append(") ")
                 .append("OR exists (")
                 .append("select null from eys_documentos d, ADU_TRAFICO_POSTAL a where ")
                 .append("d.ide_documento=a.ide_documento and ")
                 .append("s.ide_solicitud= a.ide_documento_carga")
                 .append(")")
                 .append(")")
        	     .append(")")
        	     .append(")")
        	     .append("order by ide_documento) a where rownum <=:FIN) where regs>=:INICIO");        	  
         	  m.put("sentencia1", sql.toString());
        	  break; 
           case CONSULTAR_MARCAS_PENDIENTES:
        	  setEjecucionLibre(true);
         	  sql.append("select count(1) as NRO_REGISTROS from eys_marcas_doc_es where ")
         	     .append("ide_documento in(")
         	     .append("select ide_documento from eys_auditoria_registros where ")
         	     .append("ide_solicitud in(")
         	     .append("SELECT ide_solicitud FROM eys_solicitudes_ingreso s WHERE ")
                 .append("(s.cod_concepto_cambio IS NULL OR s.cod_concepto_cambio = 3) ")
                 .append("AND s.cod_estado = 24 AND ")
                 .append("s.ide_formato IN("+FORMATOS+") ") 
                 .append("AND (s.num_registros_ok IS NOT NULL AND s.num_registros_ok > 0) ")
                 .append("AND (s.ind_movida IS NULL OR s.ind_movida = 'N') ")
                 .append("AND ")
                 .append("(")
                 .append("exists (")
                 .append("select null from eys_documentos d, EYS_SOLICITUDES_EXOGENA a where ")
                 .append("d.ide_documento = a.ide_documento_gen and ")
                 .append("s.ide_solicitud = a.ide_solicitud_exogena) ")
                 .append("OR exists (")
                 .append("select null from eys_documentos d, DIL_SOLICITUDES_DECLARACION a where ")
                 .append("d.ide_documento=a.ide_documento and ")
                 .append("s.ide_solicitud= a.ide_documento_carga")
                 .append(") ")
                 .append("OR exists (")
                 .append("select null from eys_documentos d, ADU_IMP_EXP a where ")
                 .append("d.ide_documento=a.ide_documento and ")
                 .append("s.ide_solicitud= a.ide_documento_carga")
                 .append(") ")
                 .append("OR exists (")
                 .append("select null from eys_documentos d, ADU_TRAFICO_POSTAL a where ")
                 .append("d.ide_documento=a.ide_documento and ")
                 .append("s.ide_solicitud= a.ide_documento_carga")
                 .append(")")
                 .append(")")
         	     .append(")")
         	     .append(")");
          	  m.put("sentencia1", sql.toString());
         	  break; 
        }
		return m;
	}

	public int guardar() throws SQLException {
		return 0;
	}

	public boolean validar() throws DValidarExcepcion {
		return true;
	}

	
	
	
	
	
	
	/*
	 * GETTERS Y SETTERS
	 */
	
	
	
	/***
	 * Devuelve el valor del atributo solicitudesNoMovidas.
	 * 
	 * @return solicitudesNoMovidas
	 */
	public List<DSolicitudIngresoNoMovidaTO> getSolicitudesNoMovidas() {
		return solicitudesNoMovidas;
	}
	
	
	
	
	/****
	 * Devuelve el valor del atributo marcasDoc.
	 * 
	 * @return marcasDoc
	 */
	public List<DMarcaDocumentoTO> getMarcasDoc() {
		return marcasDoc;
	}

	
	/**
	 * Devuelve el valor del atributo to
	 * 	
	 * @return to
	 */
	public DSolicitudIngresoNoMovidaTO getTo() {
		return to;
	}




	/****
	 * Asigna un valor al atributo ejecucionLibre.
	 * 
	 * @param ejecucionLibre Dato de tipo boolean para asignar al atributo ejecucionLibre
	 */
	public void setEjecucionLibre(boolean ejecucionLibre) {
		this.ejecucionLibre = ejecucionLibre;
	}
	
	
	
	
	/*****
	 * Obtiene el valor del atributo ejecucionLibre
	 * 
	 * @return ejecucionLibre
	 */
	public boolean isEjecucionLibre() {
		return ejecucionLibre;
	}


	
	
	
	
	

}/*fin de class*/
