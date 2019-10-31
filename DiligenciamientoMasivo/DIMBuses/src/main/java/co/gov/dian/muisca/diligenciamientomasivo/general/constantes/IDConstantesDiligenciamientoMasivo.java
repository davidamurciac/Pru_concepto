package co.gov.dian.muisca.diligenciamientomasivo.general.constantes;

import co.gov.dian.muisca.diligenciamiento.general.constantes.IDConstantesDiligenciamiento;


/**
 * Interfaz que contiene los valores constantes usados por los formularios
 * en Diligenciamiento Masivo
 */

public interface IDConstantesDiligenciamientoMasivo {
	
	
	/* _____________________________________
	  |										|\
	  | FORMULARIOS DILIGENCIAMIENTO MASIVO	| |
	  |_____________________________________| |
	  \______________________________________\|
	*/
	  
	 public static final int FORMULARIO_PRECIOS_TRANSFERENCIA_INDIVIDUAL = 120;
	 public static final int FORMULARIO_PRECIOS_TRANSFERENCIA_CONSOLID = 130;
	 public static final int FORMULARIO_PRECIOS_TRANSFERENCIA_CONSOLID_HOJA2 = 1126;
	 public static final int FORMULARIO_PRECIOS_TRANSFERENCIA_INDIVID_HOJA2 = 1125;
	 public static final int FORMULARIO_ANEXO_RENTA = 1732;
	 public static final int VERSION_FORMULARIO_ANEXO_RENTA_2012 = 5;
	 public static final int ANIO_FORMULARIO_ANEXO_RENTA_VERSION_5 = 2012;
	 	 
	 
	 
	/* _____________________________________
	  |										|\
	  |     CODIGOS VALORES DOMINIOS		| |
	  |_____________________________________| |
	  \______________________________________\|
	*/
	 
	 //Valor dominio declarantes
	 public static final int COD_VAL_DOMINIO_DECLARANTES = 138;
	 //Código dominio de la sanción mínima
	 public static final int DATO_SANCION_MINIMA = 104;
	 //Valor dominio primer día período GMF
	 public static final int COD_VALOR_INICIO_PER_GMF = 118;


	/* _____________________________________
	  |										|\
	  |   IDENTIFICADORES DE PERIODICIDAD	| |
	  |_____________________________________| |
	  \______________________________________\|
	*/
	 
	 public static final int APERIODICO = 19110711;
	 public static final int PERIODICIDAD_ANUAL = 19110708;
	 public static final int PERIODICIDAD_CUATRIMESTRAL = 19110712;
	 public static final int PERIODICIDAD_BIMESTRAL = 19110705;


	/* _____________________________________
	  |										|\
	  |     		FORWARDS				| |
	  |_____________________________________| |
	  \______________________________________\|
	*/
	 
	 //Forward a la pantalla de proceso de firma
	 public static final String FORWARD_PROCESO_FIRMA_PT = "DefProcesoFirmaPreciosTrans";
	 
	  

	 /* _____________________________________
	  |										|\
	  |     	GRUPOS FORMATO				| |
	  |_____________________________________| |
	  \______________________________________\|
	*/
	 
	public static final int IDE_GRUPO_DOC_HOJA_1 = 1;
	public static final int IDE_OCURRENCIA_DEFAULT = 1;
	public static final int NUM_REPETICION_DEFAULT = 1;
	


    /* _____________________________________
	  |										|\
	  |     CONSTANTES LIQUIDACION			| |
	  |_____________________________________| |
	  \______________________________________\|
	*/
	
	public static final int CUOTA_DEFAULT = 1;
	public static final int TOTAL_CUOTAS_DEFAULT = 1;
	public static final int POSICION_DEF_LIQ_TIPO_DECLARANTE = 0;	
	


    /* _____________________________________
	  |										|\
	  |      CONCEPTOS LIQUIDACION			| |
	  |_____________________________________| |
	  \______________________________________\|
	*/
	
	
	
	//Concepto pago PT
	public static final int CONCEPTO_PAGO_PRECIOS_TRANSF = 55;

    /* _____________________________________
	  |										|\
	  |    ESTADOS LIQUIDACION DOCUMENTOS	| |
	  |_____________________________________| |
	  \______________________________________\|
	*/
	
	
    public static final int IDE_ESTADO_NO_PRESENTADO = -1;
	public static final int IDE_ESTADO_NO_LIQUIDACION = -2;
	
	 /* _____________________________________
	  |										|\
	  |    ESTADOS LIQUIDACION DOCUMENTOS	| |
	  |_____________________________________| |
	  \______________________________________\|
	*/
	
	
    public static final String ESTADO_EXITOSO_POSTERIOR = "24";
    
    
    /* _____________________________________
	  |										|\
	  |    ESTADOS LIQUIDACION DOCUMENTOS	| |
	  |_____________________________________| |
	  \______________________________________\|
	*/
    
    public static final int IDE_FUNCION = 499;
    
    
    /* _____________________________________
	  |										|\
	  |    EXTENSIÓN ARCHIVOS 				| |
	  |_____________________________________| |
	  \______________________________________\|
	*/
    
    public static final String NOM_EXTENSION_PDF = ".pdf";
    
    public static final String NOM_EXTENSION_ZIP = ".zip";
    
    public static final String NOM_EXTENSION_TXT = ".txt";
    
    
    /* _____________________________________
	  |										|\
	  |    PARAMETROS PARA LA TAREA DE 		| |
	  |		PRECIOS DE TRANSFERENCIA 		| |
	  |_____________________________________| |
	  \______________________________________\|
	*/
  
    public static final String URL = "";
    public static int PARAM_IDE_TAREA = 27;
    
    
}/*fin de interface*/