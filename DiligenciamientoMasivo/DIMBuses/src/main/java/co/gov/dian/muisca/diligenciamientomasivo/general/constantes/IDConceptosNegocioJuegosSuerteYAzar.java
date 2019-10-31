package co.gov.dian.muisca.diligenciamientomasivo.general.constantes;

/**
 * 
 * 
 * <p>Title: Conceptos de negocio Diligenciamiento</p>
 * <p>Description: Interfaz que contiene la constantes de casillas de negocio usadas
 * por los formularios de Explotación y gastos de Administración Juegos Suerte y Azar.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: DIAN</p>
 * @author dmahechav
 * @version 1.0
 */

public interface IDConceptosNegocioJuegosSuerteYAzar extends IDConceptosNegocioDim{
	
	//************************************************************************//
    //**  Concepto negocio Declaración Derechos de Explotación y Gastos     **//
	//**        de Administración de juegos de suerte y Azar				**//
    //************************************************************************//
	
	public static final int CAS_NEG_BASE_LIQ_JUEGOS_LOCALIZADOS = 3467;	
	public static final int CAS_NEG_BASE_LIQ_JUEGOS_NOVEDOSOS = 3468;	
	public static final int CAS_NEG_BASE_LIQ_JUEGOS_PROMOCIONALES = 3469;	
	public static final int CAS_NEG_BASE_RIFAS = 3470;	
	public static final int CAS_NEG_BASE_EVENTOS_GALLISTICOS_CANINOS = 3471;	
	public static final int CAS_NEG_BASE_TOTAL_JUEGOS_SUERTE_AZAR = 3472;		
	public static final int CAS_NEG_DER_EXPLOTACION_JUEGOS_LOCALIZADOS = 3473;	
	public static final int CAS_NEG_DER_EXPLOTACION_JUEGOS_NOVEDOSOS = 3474;	
	public static final int CAS_NEG_DER_EXPLOTACION_JUEGOS_PROMOCIONALES = 3475;	
	public static final int CAS_NEG_DER_EXPLOTACION_RIFAS = 3476;		
	public static final int CAS_NEG_DER_EXPLOTACION_GALLISTICOS_CANINOS = 3477;	
	public static final int CAS_NEG_TOTAL_DER_EXPLOTACION_PERIODO = 3478;
	
	/**CASILLAS NEGOCIO FORMATO CARGA*/
	public static final int CAS_NEG_CODIGO_MODALIDAD = 3503;
	public static final int CAS_NEG_VALOR_IVA = 3447;
	public static final int CAS_NEG_TOTAL_DERECHOS_EXPLOTACION_ITEM = 3509;
	public static final int CAS_NEG_NUMERO_FORMULARIO_LITOGRAFICO = 3540;
	public static final int CAS_NEG_TARIFA = 2320;
	public static final int CAS_NEG_FORMATO_TENENCIA_LEGAL_ORIGEN = 3508;
	public static final int CAS_NEG_NUM_DECLARACION_IMPORTACION = 3521;
	public static final int CAS_NEG_DEPARTAMENTO_ESTABLECIMIENTO = 2299;
	public static final int CAS_NEG_TIPO_DOC_PROVEEDOR = 2380;
	public static final int CAS_NEG_NUM_IDENTIFICACION_PROVEEDOR = 2381;
	public static final int CAS_NEG_NUM_MUNICIPIO_ESTABLECIMIENTO = 2300;
	public static final int CAS_NEG_ELEMENTO_EQUIPO_JUEGO = 3504;
	
	
	//************************************************************************//
    //**  Código Concepto negocio Declaración Derechos de Explotación       **//
	//**    y Gastos de Administración de juegos de suerte y Azar	  	    **//
    //************************************************************************//
	
	public static final String COD_CAS_NEG_BASE_LIQ_JUEGOS_LOCALIZADOS = "BLJL";	
	public static final String COD_CAS_NEG_BASE_LIQ_JUEGOS_NOVEDOSOS = "BLJN";	
	public static final String COD_CAS_NEG_BASE_LIQ_JUEGOS_PROMOCIONALES = "BLJP";	
	public static final String COD_CAS_BASE_NEG_RIFAS = "BLRI";	
	public static final String COD_CAS_BASE_NEG_EVENTOS_GALLISTICOS_CANINOS = "BLGC";	
	public static final String COD_CAS_NEG_BASE_TOTAL_JUEGOS_SUERTE_AZAR = "TJSA";	
	public static final String COD_CAS_NEG_DER_EXPLOTACION_JUEGOS_LOCALIZADOS = "DEJL";	
	public static final String COD_CAS_NEG_DER_EXPLOTACION_JUEGOS_NOVEDOSOS = "DEJN";	
	public static final String COD_CAS_NEG_DER_EXPLOTACION_JUEGOS_PROMOCIONALES = "DEJP";	
	public static final String COD_CAS_NEG_DER_EXPLOTACION_RIFAS = "DERI";	
	public static final String COD_CAS_NEG_DER_EXPLOTACION_GALLISTICOS_CANINOS = "DEGC";	
	public static final String COD_CAS_NEG_TOTAL_DER_EXPLOTACION_PERIODO = "TDEX";
	
	/**CASILLAS NEGOCIO FORMATO CARGA*/
	public static final String COD_CAS_NEG_CODIGO_MODALIDAD = "COMD";
	public static final String COD_CAS_NEG_TOTAL_DERECHOS_EXPLOTACION_ITEM = "TDEI";
	public static final String COD_CAS_NEG_NUMERO_FORMULARIO_LITOGRAFICO = "NFLI";
	public static final String COD_CAS_NEG_TARIFA = "TARI";
	public static final String COD_CAS_NEG_FORMATO_TENENCIA_LEGAL_ORIGEN = "FTLO";
	public static final String COD_CAS_NEG_NUM_DECLARACION_IMPORTACION = "NDIM";
	public static final String COD_CAS_NEG_DEPARTAMENTO_ESTABLECIMIENTO = "DEST";
	public static final String COD_CAS_NEG_TIPO_DOC_PROVEEDOR = "TDPO";
	public static final String COD_CAS_NEG_NUM_IDENTIFICACION_PROVEEDOR = "NIPO";
	public static final String COD_CAS_NEG_NUM_MUNICIPIO_ESTABLECIMIENTO = "MEST";
	public static final String COD_CAS_NEG_ELEMENTO_EQUIPO_JUEGO = "EEQJ";	
}
