package co.gov.dian.muisca.diligenciamientomasivo.constantes.tareasprogramadas.etesa;

import co.gov.dian.muisca.entradasalida.general.to.formato.DFormatoPKTO;



/**
 * Interfaz de constantes que representan datos y operaciones en la carga de prevalidadores iniciales ETESA a la Bandeja de Salida
 * 
 * @author nfontechar
 *
 */
public interface IDConstantesPrevsABS {
	
	
	public final String RUTA_BASE="/home/tmp/repositorioDocumentos/etesa/";
	public final String ARCHIVO_BASE="Nits.txt";
	public final String ARCHIVO_ETESA="etesa.properties";
	public final String FLUJO_GENERICO="3f8181ea2014d45601201545cd5f0003";	
	public final DFormatoPKTO PK=new DFormatoPKTO(new Integer(1549),new Integer(1));
	public final Long TIPO_ARCHIVO=new Long(53528995);
	public final int NUM_ARCHIVOS=1;
	public final int POLITICA=new Integer(60);
	public final String PARAMETRO_NOMBRE="NOMBRE_PREVALIDADOR";
	public final String PARAMETRO_ANIO="ANIO_PREVALIDADOR";
	public final String PARAMETRO_VERSION="VERSION_PREVALIDADOR";
	public final String PARAMETRO_EXTENSION="EXTENSION_PREVALIDADOR";
	
	
	
	
	/*
	 * CONSTANTES DE BD
	 */
	
	public final int CREAR=1;
	public final int ACTUALIZAR=2;
	public final int ELIMINAR=3;
	public final int CONSULTAR_POR_PK=4;
	public final int CONSULTAR_POR_NIT=5;
	public final int CONSULTAR_POR_SOL_EXP=6;
	
	public static final String NOM_TABLA="EYS_DOCUMENTOS_BAN_SALIDA";
	public static final String NOM_CAMPOS_TABLA="(IDE_SOLICITUD,IDE_EXPEDIENTE,NUM_NIT,TIPO_ARCHIVO,IDE_DOCUMENTO,NUM_REPETICION,FEC_GENERACION,IDE_USUARIO_CAMBIO,IDE_RECURSO)";
	public static final String VAL_CAMPOS_TABLA="(:IDE_SOLICITUD,:IDE_EXPEDIENTE,:NUM_NIT,:TIPO_ARCHIVO,:IDE_DOCUMENTO,:NUM_REPETICION,:FEC_GENERACION,:IDE_USUARIO_CAMBIO,:IDE_RECURSO)";
	public static final String ACT_CAMPOS_TABLA="(TIPO_ARCHIVO=:TIPO_ARCHIVO,IDE_DOCUMENTO=:IDE_DOCUMENTO,NUM_REPETICION=:NUM_REPETICION,FEC_GENERACION=:FEC_GENERACION,IDE_USUARIO_CAMBIO=:IDE_USUARIO_CAMBIO,IDE_RECURSO=:IDE_RECURSO)";
	public static final String NOM_CAMPO_NIT="NUM_NIT";
	public static final String VAL_CAMPO_NIT=":NUM_NIT";
	public static final String NOM_CAMPO_EXP="IDE_EXPEDIENTE";
	public static final String VAL_CAMPO_EXP=":IDE_EXPEDIENTE";
	public static final String NOM_CAMPO_SOL="IDE_SOLICITUD";
	public static final String VAL_CAMPO_SOL=":IDE_SOLICITUD";
	public static final String NOM_CAMPO_TIPO="TIPO_ARCHIVO";
	public static final String VAL_CAMPO_TIPO=":TIPO_ARCHIVO";
	
	
	/*
	 * CONSTANTES DE NAVEGACIÓN
	 */
	public static final String FWD_INICIO="/DefInicioPrevs";
	public static final String FWD_DETALLE="/DefListaPrevs";
	public static final String CONTENT_TYPE_XLS = "application/xls";
	

}/*fin de interface*/
