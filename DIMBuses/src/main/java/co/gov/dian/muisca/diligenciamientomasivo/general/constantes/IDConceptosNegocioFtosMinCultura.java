package co.gov.dian.muisca.diligenciamientomasivo.general.constantes;

import co.gov.dian.muisca.diligenciamiento.general.constantes.IDConceptosNegocioDil;

public interface IDConceptosNegocioFtosMinCultura extends IDConceptosNegocioDil {

	/** HOJA1 */
	public static final int CAS_CODIGO_CORRECCION = 27;
	public static final int CAS_CODIGO_TIPO_DECLARACION = 532;

	// Concepto Documento 470 (casilla 35)
	static final int CONCEPTO_INICIAL = 1;
	static final int CONCEPTO_LEGALIZACION = 2;
	static final int CONCEPTO_ANTICIPADA = 2;
	static final int CONCEPTO_CORRECION = 4;
	static final int CONCEPTO_MODIFICACION = 5;
	
	int TIPO_DECLARACION_INICIAL = 0;
	int TIPO_DECLARACION_CORRECCION = 1;
	
	// Descripcion Conceptos
	String INICIAL = "INICIAL";
	String LEGALIZACION = "LEGALIZACION";
	String ANTICIPADA = "ANTICIPADA";
	String CORRECION = "CORRECION";
	String MODIFICACION = "MODIFICACION";

	// CONCEPTOS 470
	int CONCEPTO_BOLETAS_PRECIO_SUPERIOR_3_UVT = 6918; // CAS 27
	int CONCEPTO_TOTAL_OTROS_DERECHOS_SUPERIOR_3_UVT = 6919; // CAS 28
	int CONCEPTO_TOTAL_BASE_RETENCION_CONTRIB_PARAFISCAL = 6920;// CAS 29
	int CONCEPTO_VALOR_RETENCION_CONTRIB_PARAFISCAL = 6922;// CAS 30
	int CONCEPTO_DEVOLUCIONES_RETENCIONES_EXCESO = 6923;// CAS 31
	int CONCEPTO_VALOR_NETO_RETENCION_COTRIB_PARAFISCAL = 6925;// CAS 33
	int CONCEPTO_VALOR_PAGO_RETENCION_COTRIB_PARAFISCAL = 6926;// CAS 39

	// CONCEPTOS 480
	int CAS_NEG_NUMERO_TOTAL_BOLETAS_VENDIDAS = 3517; // CAS 28
	int CAS_NEG_VALOR_TOTAL_DERECHOS_ASISTENCIA_CORTESIA = 6927; // CAS 29
	int CAS_NEG_VALOR_TOTAL_DERECHOS_ASISTENCIA_PATROCINIOS = 6928; // CAS 30
	int CONCEPTO_MENOS_TOTAL_BOLETAS_VENDIDAS = 6929; // CAS 31
	int CONCEPTO_BASE_GRAVABLE_CONTRIB_PARAFISCAL = 6931; // CAS 32
	int CONCEPTO_TOTAL_CONTRIB_PARAFISCAL = 51; // CAS 33
	int CONCEPTO_MENOS_RETENCION_CONTRIB_PARAFISCAL = 6932; // CAS 34
	int CONCEPTO_TOTAL_PAGAR_CONTRIB_PARAFISCAL = 6933; // CAS 35
	int CONCEPTO_PAGOS_EXCESO_CONTRIB_PARAFISCAL_ANTERIORES = 6935; // CAS 36
	int CONCEPTO_TOTAL_NETO_PAGAR_CONTRIB_PARAFISCAL = 6936; // CAS 38
	int CAS_NEG_CLAS_PROD_PERMANENTE = 6937; // CAS 45
	int CAS_NEG_CLAS_PROD_OCASIONAL = 6938; // CAS 46
	

	// CONCEPTOS 2184
	int CASNEG_CODIGO_O_NUM_EVENTO = 5397;
	int CASNEG_NOMBRE_ESPECTACULO = 7040;
	int CASNEG_MUNICIPIO_DISTRITO_REALIZACION_ESPECTACULO = 7042;
	int CASNEG_DEPARTAMENTO_REALIZACION_ESPECTACULO = 7045;
	int CASNEG_FECHA_REALIZACION_ESPECTACULO = 7049;
	int CASNEG_NIT_PRODUCTOR = 7050;
	int CASNEG_DV_PRODUCTOR = 7054;
	int CASNEG_PRIMER_APELLIDO_PRODUCTOR = 7061;
	int CASNEG_SEGUNDO_APELLIDO_PRODUCTOR = 7062;
	int CASNEG_PRIMER_NOMBRE_PRODUCTOR = 7063;
	int CASNEG_OTROS_NOMBRES_PRODUCTOR = 7064;
	int CASNEG_RAZON_SOCIAL_PRODUCTOR = 7065;
	int CASNEG_DIRECCION_PRODUCTOR = 7066;
	int CASNEG_CIUDAD_MUNICIPIO_PRODUCTOR = 7067;
	int CASNEG_DEPARTAMENTO_PRODUCTOR = 7068;
	int CASNEG_TELEFONO_PRODUCTOR = 7069;
	int CASNEG_LUGAR_ESCENARIO_EVENTO = 7070;
	int CASNEG_VALOR_TOTAL_BOLETAS_VENDIDAS = 7071;
	int CASNEG_VALOR_SERVICIO_DISTRIBUCION_COMERC_BOLETERIA = 7072;
	int CASNEG_NUM_BOLETAS_PRECIO_IGUAL_SUPERIOR_3_UVT = 7079;
	int CASNEG_VALOR_BOLETAS_PRECIO_IGUAL_SUPERIOR_3UVT = 7080;
	int CASNEG_NUM_TOT_OTROS_DEREC_ASIST_CORTESIAS_PREC_IGUAL_SUP_3UVT = 7081;
	int CASNEG_VAL_TOT_OTROS_DEREC_ASIST_CORTESIAS_PREC_IGUAL_SUP_3_UVT = 7083;
	int CASNEG_NUM_BOLET_RETEN_EXCESO_CONTRIB_PARAFISCAL_PER_ANTERIORES = 7086;
	int CASNEG_VAL_BOLET_RETEN_EXCESO_CONTRIB_PARAFISCAL_PER_ANTERIORES = 7087;
	int CASNEG_SUBTOTAL_BOLETAS_PRECIO_IGUAL_SUPERIOR_3_UVT = 7074;
	int CASNEG_SUBT_OTROS_DERECHOS_ASIST_CORTESIAS_VALOR_IGUAL_SUP_UVT = 7077;
	int CASNEG_SUBT_RETEN_EXCESO_CONTRIB_PARAFISCAL_PERIODOS_ANTERIORES = 7078;
	
	
	//Casillas negocio 2185
	int CAS_NEG_NUMERO_SUBTOTAL_BOLETAS_VENDIDAS = 7088; 
	int CAS_NEG_VALOR_SUBTOTAL_DERECHOS_ASISTENCIA_CORTESIA = 7089; 
	int CAS_NEG_VALOR_SUBTOTAL_DERECHOS_ASISTENCIA_PATROCINIOS = 7090; 
    int CAS_NEG_VAL_TOTAL_CRUCES = 7091;
    int CAS_NEG_VAL_SUBTOTAL_RETENCIONES = 7093;
    int CAS_NEG_VAL_SUBTOTAL_DEVOLUCIONES = 7094;        
    int CAS_NEG_VAL_CLASE_PRODUCTOR = 7097;
    int CAS_NEG_VAL_NUM_REGISTRO_PRODUCTOR = 6939;
            
     
    int CAS_NEG_NUM_BOL_VENDIDAS_INF_3UVT = 7098;
    int CAS_NEG_VAL_BOL_VENDIDAS_INF_3UVT = 7099;
    int CAS_NEG_NUM_CORTESIAS_ENTREGADAS = 7100;
    int CAS_NEG_VAL_CORTESIAS_ENTREGADAS = 7101;
    int CAS_NEG_NUM_CORT_ENTREG_INF_3UVT = 7102;
    int CAS_NEG_VAL_CORT_ENTREG_INF_3UVT = 7103;
    int CAS_NEG_NUM_BOLETAS_PATROCINIO = 7104;
    int CAS_NEG_VAL_BOLETAS_PATROCINIOT = 7105;
    int CAS_NEG_NUM_BOL_PATROC_INF_3UVT = 7107;
    int CAS_NEG_VAL_BOL_PATROC_INF_3UVT = 7108;
    int CAS_NEG_NUM_BOL_PATROC_SUP_3UVT = 7110;
    int CAS_NEG_VAL_BOL_PATROC_SUP_3UVT = 7111;    
    int CAS_NEG_NUM_BOLETAS_PAGO_EXCESO = 7112;
    int CAS_NEG_VAL_BOLETAS_PAGO_EXCESO = 7113;
    int CAS_NEG_VAL_PRIMER_APELLIDO_OP = 7116;
    int CAS_NEG_VAL_SEGUNDO_APELLIDO_OP = 7117;
    int CAS_NEG_VAL_PRIMER_NOMBRE_OP = 7118;
    int CAS_NEG_VAL_OTROS_NOMBRES_OP = 7119;
    int CAS_NEG_VAL_RAZON_SOCIAL_OP = 7120;
    
    int CAS_NEG_NUM_NIT = 7121;
    int CAS_NUM_DV = 7123;
    int CAS_NEG_VAL_RAZON_SOCIAL = 7124;
    int CAS_NEG_NUM_BOLETAS_PATROCINIO_P = 7126;
    int CAS_NEG_VAL_UBICACION_LOCALIDAD = 7127;
    int CAS_NEG_VAL_INDIVIDUAL_BOLETA = 7128;

}