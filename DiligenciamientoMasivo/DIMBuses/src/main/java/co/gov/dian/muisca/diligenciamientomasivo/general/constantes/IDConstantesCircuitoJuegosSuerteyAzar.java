package co.gov.dian.muisca.diligenciamientomasivo.general.constantes;

public interface IDConstantesCircuitoJuegosSuerteyAzar {

		public static final int IDE_FORMATO_DECLARACION_JUEGOS_SUERTE_AZAR = 320;
		public static final int NUM_VERSION_FORMATO_DECLARACION_JUEGOS_SUERTE_AZAR = 1;
		public static final int DOCUMENTO_DETALLE_DECLARACION = 1549;
	    public static final int DOCUMENTO_DETALLE_DECLARACION_VERSION = 1;
	    public static final int DOCUMENTO_RECIBO_ID = 495;
	    public static final int DOCUMENTO_RECIBO_VERSION = 1;
	    public static final int DOCUMENTO_SOLICITUD = 10006;
	    
	    public static final int FORMATO_DOCUMENTO_TAREA_PRESENTACION = 20014;
	    public static final int VERSION_FORMATO_DOCUMENTO_TAREA_PRESENTACION = 1;
	    
	    //Asunto Circuito 320  (Flujo, tramites, actividades, parametros, tareas)
	    public static final String IDE_FLUJO = "8a7f8f8d2cd1c45b012ce08235460003";
	    public static final long TAREA_PRESENTAR_JUEGOS_SUERTE_AZAR = 566;
	    public static final int PARAM_T1_PRESEN_JUA_SOLICITUD_ID = 1;
	    public static final String PARAM_T1_PRESEN_JUA_SOLICITUD_NOMBRE = "idSolicitud";
	    public static final int PARAM_T1_PRESEN_JUA_ID_DOCUMENTO_ID = 2;
	    public static final String PARAM_T1_PRESEN_JUA_ID_DOCUMENTO_NOMBRE = "idDocumento"; 
	    
	    public static final String IDE_TRAMITE_CARGA_MASIVA = "f023d3e30aff0f0d00cff777eb860b65";
	    public static final String IDE_TRAMITE_PRESENTACION = "f6acdcbfbf01016a40e3e58cf4704842";
		public static final String IDE_TRAMITE_VENCIMIENTO_PLAZO = "f6acddb5bf01016a580a950ca6239767";
		public static final String IDE_ACTIVIDAD_CARGA_MASIVA = "f6cc00450aff0f0d00e9f5a43e75206c";
		public static final String IDE_ACTIVIDAD_PRESENTACION = "099a44b8bf01016a2ec137eb5309fe11";
		public static final String IDE_ACTIVIDAD_VENCIMIENTO_PLAZO = "f6d458190aff0f0d01b717f59bf4fda9";

		public static final String IDE_PAR_ACT_PRE_IDDOCUMENTO = "09a79904bf01016a19082bcc47540bc4";
		public static final String NOM_PAR_ACT_PRE_IDDOCUMENTO = "idDocumento";
		
		public static final String IDE_PAR_ACT_CM_IDDOCUMENTO = "f6d029d70aff0f0d0044e24acb272bc4";
		public static final String NOM_PAR_ACT_CM_IDDOCUMENTO = "idDocumento";
		public static final String IDE_PAR_ACT_CM_IDSOLICITUD = "043442dfbf01016a7e5787ea24b34bcf";
		public static final String NOM_PAR_ACT_CM_IDSOLICITUD = "idSolicitud"; 
		
		public static final String IDE_PAR_ACT_VENC_IDDOCUMENTO = "f6d6e0e00aff0f0d009a1147a4e14384";
		public static final String NOM_PAR_ACT_VENC_IDDOCUMENTO = "idDocumento";
		public static final String IDE_PAR_ACT_VENC_IDSOLICITUD = "f6d6e1010aff0f0d00e8e3d3f4b98bab";
		public static final String NOM_PAR_ACT_VENC_IDSOLICITUD = "idSolicitud"; 
		
		static final long TIPO_DOC_ACTIVIDAD_PRESENTACION_INFORMACION = 908;
		static final long TIPO_DOC_ACTIVIDAD_PRESENTAR_JUEGOS_SUERTE_AZAR= 909;
		static final long TIPO_DOC_ACTIVIDAD_VENCIMIENTO_TAREA = 910;
		static final long TIPO_DOC_ACTIVIDAD_VENCIMIENTO_TAREA_REPARTO = 911;
	    
	    //* Modalidades de declaración 1549
	    
	    static final int LOCALIZADOS = 1; 
	    static final int NOVEDOSOS = 2; 
	    static final int PROMOCIONALES = 3; 
	    static final int RIFAS = 4; 
	    static final int APUESTAS = 5; 
}
