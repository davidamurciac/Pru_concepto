package co.gov.dian.muisca.diligenciamientomasivo.servicios.movimientoperiodico;

import java.util.List;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoServicioInterno;
import co.gov.dian.muisca.arquitectura.mensajes.DManipuladorMensaje;
import co.gov.dian.muisca.arquitectura.mensajes.IDArqMensajes;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico.DSolicitudIngresoNoMovidaTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DMarcaDocumentoTO;




/****
 * Servicio que permite mover a los repositorios definitivos, solicitudes de ingreso que presentaron errores de homologación, pero que fueron 
 * tenidas en cuenta como válidas 
 * 
 * @author nfontechar
 *
 */
public class DCmdSrvSolicitudesIngresoNoMovidas extends DComandoServicioInterno {

	
	
	
	/*
	 * CONSTANTES
	 */
	protected static final int CONSULTAR_SOLICITUDES_NO_MOVIDAS=1;
	protected static final int CONSULTAR_LOG_MARCAS_DOCS_SOLICITUD=2;
	protected static final int CONSULTAR_REGISTROS_PENDIENTES=3;
	
	
	
	/*
	 * ATRIBUTOS
	 */
	
	/***
	 * Almacena la operación a realizar de acuerdo con el inicializar invocado.
	 */
	protected int tipoOperacion=-1;
	
	
	
	/***
	 * Lista de objetos DSolicitudIngresoNoMovidaTO que contiene las solicitudes que se encuentran en el sistema como no movidas.
	 */
	protected List<DSolicitudIngresoNoMovidaTO> solicitudesNoMovidas=null;
	
	
	
	/****
	 * Lista de objetos DSolicitudIngresoNoMovidaTO que contiene las solicitudes que pudieron ser movidas con éxito.
	 */
	protected List<DSolicitudIngresoNoMovidaTO> solicitudesMovidas=null;
	
	
	
	/****
	 * Lista de objetos DMarcaDocumentoTO que contiene el log de marcas de los documentos que conforman las solicitudes no movidas.
	 */
	protected List<DMarcaDocumentoTO> marcasLogDoc=null;
	
	
	
	/**
	 * TO que contiene los datos de una solicitud no movida
	 */
	protected DSolicitudIngresoNoMovidaTO to=null;
	
	
	
	/*
	 * MÉTODOS INICIALIZAR
	 */
	
	
	
	/***
	 * Inicializa el servicio para consultar las solicitudes de ingreso que no han sido movidas
	 */
	public void inicializarConsultarSolicitudesNoMovidas(DSolicitudIngresoNoMovidaTO to){
		this.to=to;
		setTipoOperacion(CONSULTAR_SOLICITUDES_NO_MOVIDAS);
	}/*fin de inicializarConsultarSolicitudesNoMovidas*/
	
	
    
    
    /****
     * Inicializa el servicio para consultar el log de marcas de los documentos que conforman las solicitudes no movidas.
     */
	public void inicializarConsultarLogMarcasDocSolictud(DSolicitudIngresoNoMovidaTO to){
		this.to=to;
		setTipoOperacion(CONSULTAR_LOG_MARCAS_DOCS_SOLICITUD);
	}/*fin de inicializarConsultarLogMarcasDocSolictud*/
	
	
	
	/**
	 * Inicializa el servicio para consultar el número de registros pendientes, tanto de marcas como de solicitude sin mover
	 */
	public void inicializarConsultarRegistrosPendientes(DSolicitudIngresoNoMovidaTO to){
		this.to=to;
		setTipoOperacion(CONSULTAR_REGISTROS_PENDIENTES);
	}/*fin de inicializarConsultarRegistrosPendientes*/ 
	
	/*
	 * MÉTODOS HEREDADOS
	 */
	public void asignar(IDComando comando) {
		if(comando instanceof DCmdSrvSolicitudesIngresoNoMovidas){
			DCmdSrvSolicitudesIngresoNoMovidas copia=(DCmdSrvSolicitudesIngresoNoMovidas) comando;
			copia.solicitudesMovidas=solicitudesMovidas;
			copia.solicitudesNoMovidas=solicitudesNoMovidas;
			copia.marcasLogDoc=marcasLogDoc;
			copia.tipoOperacion=tipoOperacion;
			copia.to=to;
		}/*fin de if*/
	}

	public Object clonar() {
		return new DCmdSrvSolicitudesIngresoNoMovidas();
	}

	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	public String getDescripcion() {
		return "Servicio para operaciones con solicitudes de ingreso que aún no han sido movidas";
	}

	public boolean isAuditable() {
		return true;
	}

	public boolean validar() throws DValidarExcepcion {		
		return true;
	}
	
	
	
	
	
	
	

	/*
	 * GETTERS Y SETTERS
	 */
	
	
	
	/***
	 * Devuelve el valor del atributo solicitudesNoMovidas
	 * 
	 * @return solicitudesNoMovidas
	 */
	public List<DSolicitudIngresoNoMovidaTO> getSolicitudesNoMovidas() {
		return solicitudesNoMovidas;
	}
	
	
	
	/***
	 * Devuelve el valor del atributo solicitudesMovidas
	 * 
	 * @return solicitudesMovidas
	 */
	public List<DSolicitudIngresoNoMovidaTO> getSolicitudesMovidas() {
		return solicitudesMovidas;
	}
	
	
	
	/***
	 * Devuelve el valor del atributo marcasLogDoc
	 * 
	 * @return marcasLogDoc
	 */
	public List<DMarcaDocumentoTO> getMarcasLogDoc() {
		return marcasLogDoc;
	}

	
	
	/**
	 * Devuelve el valor del atributo to
	 * 	
	 * @return to
	 */
	public DSolicitudIngresoNoMovidaTO getTo() {
		return to;
	}
	
	
	
	/***
	 * Asigna un valor de tipo int al atributo tipoOperacion
	 * 
	 * @param tipoOperacion Dato tipo int que se asignará al atributo tipoOperacion
	 */
	public void setTipoOperacion(int tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	
	
	/***
	 * Devuelve el valor del atributo tipoOperacion
	 * 
	 * @return tipoOperacion
	 */
	public int getTipoOperacion() {
		return tipoOperacion;
	}
	
	
	
	
	
	
	
	/*
	 * MÉTODOS PROTEGIDOS
	 */	
	protected String generarMsjGral() {
        DManipuladorMensaje manipulador = new DManipuladorMensaje(IDArqMensajes.ME_PARAMETROS_INV_OPERACION);
        manipulador.setParametro("operacion", "la consulta");
        manipulador.setParametro("objeto", "informacion del tercero");
        return manipulador.getMensaje();
    }

    /**
     * Genera el mensaje de nulidad.
     * @param parametro parometro validado
     * @return String
     */
    protected String generarMsjNulidad(String parametro) {
        DManipuladorMensaje manipulador = new DManipuladorMensaje(IDArqMensajes.ME_PARAMETROS_NULOS);
        manipulador.setParametro("parametros", parametro);
        return manipulador.getMensaje();
    }


    /**
     * Genera el mensaje de operacio de consulta invoida.
     * @return String
     */
    protected String generarMsjOperInvalida() {
        DManipuladorMensaje manipulador = new DManipuladorMensaje(IDArqMensajes.ME_OPER_CONS_INVALIDA);
        return manipulador.getMensaje();
    }
	

}/*fin de class*/
