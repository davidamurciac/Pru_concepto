package co.gov.dian.muisca.diligenciamientomasivo.general.constantes;

/**
 * 
 * 
 * <p>Title: Conceptos de negocio Precios de transferencia</p>
 * <p>Description: Interfaz que contiene la constantes de casillas de negocio usadas
 * por los formularios de Precios de Transferencia.</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: DIAN</p>
 * @author dmahechav
 * @version 1.0
 */


public interface IDConceptosNegocioPreciosTransferencia extends IDConceptosNegocioDim{

		
	//************************************************************************//
    //**     Identificadores de Casilla Negocio Precios Transferencia.	    **//
    //************************************************************************//
	public static final int CAS_NEG_MONTO_TOTAL_OPERACIONES_INGRESO = 649;
	public static final int CAS_NEG_MONTO_TOTAL_OPERACIONES_EGRESO = 650;
	public static final int CAS_NEG_MOVIMIENTO_DEBITO_SALDO_ACTIVO = 651;	
	public static final int CAS_NEG_MOVIMIENTO_CREDITO_SALDO_ACTIVO = 652;
	public static final int CAS_NEG_SALDO_FINAL_SALDO_ACTIVO = 653;
	public static final int CAS_NEG_MOVIMIENTO_DEBITO_SALDO_PASIVO = 654;
	public static final int CAS_NEG_MOVIMIENTO_CREDITO_SALDO_PASIVO = 655;
	public static final int CAS_NEG_SALDO_FINAL_SALDO_PASIVO = 656;
		
	//************************************************************************//
    //**  		Concepto negocio Declaración InFormativa Individual     	**//
	//**      				Precios de Transferencia						**//
    //************************************************************************//
	public static final int CAS_NEG_TIPO_DOC_CONTROLANTE = 3853;
	public static final int CAS_NEG_NUMERO_IDENTIFICACION_FISCAL = 657;
	public static final int CAS_NEG_COD_PAIS_CONTROLANTE = 658;
	public static final int CAS_NEG_PRIMER_APELLIDO_CONTROLANTE = 659;
	public static final int CAS_NEG_SEGUNDO_APELLIDO_CONTROLANTE = 660;
	public static final int CAS_NEG_PRIMER_NOMBRE_CONTROLANTE = 661;
	public static final int CAS_NEG_OTROS_NOMBRE_CONTROLANTE = 662;
	public static final int CAS_NEG_RZ_SOCIAL_CONTROLANTE = 663;
	public static final int CAS_NEG_SOLICITUD_HOJA_2 = 665;
	public static final int CAS_NEG_SOLICITUD_DE_ENVIO = 666;
	public static final int CAS_NEG_TIPO_OPERACION = 1083; 
	public static final int CAS_NEG_MONTO_OPERACION = 3871;
	public static final int CAS_NEG_MONTO_PRINCIPAL = 6615;

	//************************************************************************//
    //**  		Concepto negocio Declaración InFormativa Consolidada     	**//
	//**      				Precios de Transferencia						**//
    //************************************************************************//
	public static final int CAS_NEG_INDICADOR_PRESENTO_DEC_INDIVIDUAL = 3862;
	public static final int CAS_NEG_NUM_DECLARACION_INDIVIDUAL = 669;
	public static final int CAS_NEG_MONTO_TOTAL_CONS_OPERACIONES_INGRESO = 3854;	
	public static final int CAS_NEG_MONTO_TOTAL_CONS_OPERACIONES_EGRESO = 3855;
	public static final int CAS_NEG_MONTO_TOTAL_CONS_MOV_DEBITO_ACTIVO = 3856;
	public static final int CAS_NEG_MONTO_TOTAL_CONS_MOV_CREDITO_ACTIVO = 3857;
	public static final int CAS_NEG_MONTO_TOTAL_CONS_SALDO_FINAL_ACTIVO = 3860;
	public static final int CAS_NEG_MONTO_TOTAL_CONS_MOV_DEBITO_PASIVO = 3859;
	public static final int CAS_NEG_MONTO_TOTAL_CONS_MOV_CREDITO_PASIVO = 3858;
	public static final int CAS_NEG_MONTO_TOTAL_CONS_SALDO_FINAL_PASIVO = 3861;
	public static final int CAS_NEG_RANGO_POSITIVO_DEL_MARGEN_O_PRECIO = 3882;
	public static final int CAS_NEG_RANGO_NEGATIVO_DEL_MARGEN = 3887;
	public static final int CAS_NEG_APORTE_SOC_Y_ENTIDADES_EXTRANJERAS = 6610;
	public static final int CAS_NEG_INFORMACION_ADICIONAL = 6611;
	public static final int CAS_NEG_MONTO_TOTAL_DEL_PRINCIPAL_RECIBIDO = 6612;
	public static final int CAS_NEG_MONTO_TOTAL_DEL_PRINCIPAL_PRESTADO = 6613;
	public static final int CAS_NEG_NUM_DIAS_LIQUIDA_SANCION = 6614;
	public static final int CAS_NEG_SANCION_EXTEMPORANEIDAD = 6451;
	
	
	
	
    //************************************************************************//
    //**   Códigos Conceptos de Casilla Negocio para Precios Transferencia. **//
    //************************************************************************//
	public static final String COD_CAS_NEG_MONTO_TOTAL_OPERACIONES_INGRESO = "12";
	public static final String COD_CAS_NEG_MONTO_TOTAL_OPERACIONES_EGRESO = "13";
	public static final String COD_CAS_NEG_MOVIMIENTO_DEBITO_SALDO_ACTIVO = "14";	
	public static final String COD_CAS_NEG_MOVIMIENTO_CREDITO_SALDO_ACTIVO = "5W";
	public static final String COD_CAS_NEG_SALDO_FINAL_SALDO_ACTIVO = "5X";
	public static final String COD_CAS_NEG_MOVIMIENTO_DEBITO_SALDO_PASIVO = "15";
	public static final String COD_CAS_NEG_MOVIMIENTO_CREDITO_SALDO_PASIVO = "5Y";
	public static final String COD_CAS_NEG_SALDO_FINAL_SALDO_PASIVO = "5Z";
	
	
	//************************************************************************//
    //**  	Código Concepto negocio Declaración InFormativa Individual     	**//
	//**      				Precios de Transferencia						**//
    //************************************************************************//
	public static final String COD_CAS_NEG_TIPO_DOC_CONTROLANTE = "TDDC";
	public static final String COD_CAS_NEG_NUMERO_IDENTIFICACION_FISCAL = "6B";
	public static final String COD_CAS_NEG_COD_PAIS_CONTROLANTE = "6C";
	public static final String COD_CAS_NEG_PRIMER_APELLIDO_CONTROLANTE = "8J";
	public static final String COD_CAS_NEG_SEGUNDO_APELLIDO_CONTROLANTE = "8K";
	public static final String COD_CAS_NEG_PRIMER_NOMBRE_CONTROLANTE = "8L";
	public static final String COD_CAS_NEG_OTROS_NOMBRE_CONTROLANTE = "8M";
	public static final String COD_CAS_NEG_RZ_SOCIAL_CONTROLANTE = "6A";
	public static final String COD_CAS_NEG_SOLICITUD_HOJA_2= "8N";
	public static final String COD_CAS_NEG_SOLICITUD_DE_ENVIO = "8O";
	
	//************************************************************************//
    //**  		Concepto negocio Declaración InFormativa consolidada     	**//
	//**      				Precios de Transferencia						**//
    //************************************************************************//
	public static final String COD_CAS_NEG_INDICADOR_PRESENTO_DEC_INDIVIDUAL = "IPDI";
	public static final String COD_CAS_NEG_NUM_DECLARACION_INDIVIDUAL = "NFDI";
	public static final String COD_CAS_NEG_MONTO_TOTAL_CONS_OPERACIONES_INGRESO = "TCOI";
	public static final String COD_CAS_NEG_MONTO_TOTAL_CONS_OPERACIONES_EGRESO = "TCOE";
	public static final String COD_CAS_NEG_MONTO_TOTAL_CONS_MOV_DEBITO_ACTIVO = "TCMD";
	public static final String COD_CAS_NEG_MONTO_TOTAL_CONS_MOV_CREDITO_ACTIVO = "TCMC";
	public static final String COD_CAS_NEG_MONTO_TOTAL_CONS_SALDO_FINAL_ACTIVO = "CSFA";
	public static final String COD_CAS_NEG_MONTO_TOTAL_CONS_MOV_DEBITO_PASIVO = "CMDP";
	public static final String COD_CAS_NEG_MONTO_TOTAL_CONS_MOV_CREDITO_PASIVO = "CMCP";
	public static final String COD_CAS_NEG_MONTO_TOTAL_CONS_SALDO_FINAL_PASIVO = "CSFP";
	public static final String COD_CAS_NEG_APORTE_SOC_Y_ENTIDADES_EXTRANJERAS = "ASEE";
	public static final String COD_CAS_NEG_INFORMACION_ADICIONAL = "INAD";
	public static final String COD_CAS_NEG_MONTO_TOTAL_DEL_PRINCIPAL_RECIBIDO = "MTPR";
	public static final String COD_CAS_NEG_MONTO_TOTAL_DEL_PRINCIPAL_PRESTADO = "MTPP";
	public static final String COD_CAS_NEG_RANGO_POSITIVO_DEL_MARGEN_O_PRECIO = "RPMP";
	public static final String COD_CAS_NEG_RANGO_NEGATIVO_DEL_MARGEN = "RNMA";
	public static final String COD_CAS_NEG_NUM_DIAS_LIQUIDA_SANCION = "NDIA";
	public static final String COD_CAS_NEG_SANCION_EXTEMPORANEIDAD = "SAEX";
	
	
	
	
}