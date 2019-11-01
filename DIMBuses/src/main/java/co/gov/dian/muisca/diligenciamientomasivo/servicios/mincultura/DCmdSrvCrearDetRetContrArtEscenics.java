/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para crear un objeto DetRetContrArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: DIAN</p>
 *
 * @author dmahechav
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvCrearDetRetContrArtEscenics extends DComandoServicioInterno {
	private static final long serialVersionUID = -1092471875L; 

	/** Objeto de transporte de DetRetContrArtEscenics */
	protected DDetRetContrArtEscenicsTO toDetRetContrArtEscenics;
	/** Llave primaria de DetRetContrArtEscenics */
	protected DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics;
	/** Atributos de DetRetContrArtEscenics */
	protected DDetRetContrArtEscenicsAttTO attDetRetContrArtEscenics;
	/** colección de objetos DDetRetContrArtEscenicsTO */
	protected Collection<DDetRetContrArtEscenicsTO> objetosDetRetContrArtEscenics;
	/** Tipo de operación de consulta */
	protected int tipoOperacion = -1;
	
	public static final int CREAR = 0;
	public static final int CREAR_EN_BATCH = 1;	

	/**
	 * Inicializa la creación de DetRetContrArtEscenics.
	 * @param toDetRetContrArtEscenics Objeto de Transporte de DetRetContrArtEscenics
	 */
	public void inicializar(DDetRetContrArtEscenicsTO toDetRetContrArtEscenics) {
		tipoOperacion = CREAR;
		this.toDetRetContrArtEscenics = toDetRetContrArtEscenics;
		if (toDetRetContrArtEscenics != null) {
			pkDetRetContrArtEscenics = this.toDetRetContrArtEscenics.getPK();
			attDetRetContrArtEscenics = this.toDetRetContrArtEscenics.getAtt();
		}
	}


	/**
	 * Inicializa la creación en batch de DetRetContrArtEscenics.
	 * @param Collection<DDetRetContrArtEscenicsTO> lista de Objetos de Transporte de DetRetContrArtEscenics
	 */
	public void inicializarCrearEnBatch(Collection<DDetRetContrArtEscenicsTO> objetosDetRetContrArtEscenics){
		tipoOperacion = CREAR_EN_BATCH;
		this.objetosDetRetContrArtEscenics = objetosDetRetContrArtEscenics;
	}
	
	/**
	 * Ejecuta el comando de servicio.
	 * @throws DExcepcion Si ocurre algún error al realizar la
	 * la creación de DetRetContrArtEscenics
	 */
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdSrvCrearDetRetContrArtEscenics();
	}

	/**
	 * Indica si el comando es auditable.
	 * @return true si el comando es auditable; false de lo contrario
	 */
	public boolean isAuditable() {
		return true;
	}

	/**
	 * Obtiene la descripción del comando.
	 * @return Un String con la descripción del comando
	 */
	public String getDescripcion() {
		return "Permite crear un objeto DetRetContrArtEscenics";
	}

	/**
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecucióndel comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros = new HashMap<String, Object>();
		switch (tipoOperacion) {
		case CREAR:
			parametros.put(this.getClass().getName()+":validar:toDetRetContrArtEscenics",toDetRetContrArtEscenics);
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics",attDetRetContrArtEscenics);
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics",pkDetRetContrArtEscenics);
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getIdeDocumento()",pkDetRetContrArtEscenics.getIdeDocumento());
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumRepeticion()",pkDetRetContrArtEscenics.getNumRepeticion());
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumOcurrencia()",pkDetRetContrArtEscenics.getNumOcurrencia());
			parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumItem()",pkDetRetContrArtEscenics.getNumItem());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getIdeFormato()",attDetRetContrArtEscenics.getIdeFormato());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getNumVersionFormato()",attDetRetContrArtEscenics.getNumVersionFormato());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getNumEvento()",attDetRetContrArtEscenics.getNumEvento());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getNomEspectaculo()",attDetRetContrArtEscenics.getNomEspectaculo());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getCodMunicipioEspectaculo()",attDetRetContrArtEscenics.getCodMunicipioEspectaculo());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getCodDepartamentoEspectaculo()",attDetRetContrArtEscenics.getCodDepartamentoEspectaculo());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getFecRealizacion()",attDetRetContrArtEscenics.getFecRealizacion());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getIdePersonaRutProductor()",attDetRetContrArtEscenics.getIdePersonaRutProductor());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getValDireccionProductor()",attDetRetContrArtEscenics.getValDireccionProductor());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getCodMunicipioProductor()",attDetRetContrArtEscenics.getCodMunicipioProductor());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getCodDepartamentoProductor()",attDetRetContrArtEscenics.getCodDepartamentoProductor());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getValLugarEvento()",attDetRetContrArtEscenics.getValLugarEvento());		
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getNumTotBoletasVendidas()",attDetRetContrArtEscenics.getNumTotBoletasVendidas());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getValTotBoletasVendidas()",attDetRetContrArtEscenics.getValTotBoletasVendidas());
			parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getValServDistComerBoleteria()",attDetRetContrArtEscenics.getValServDistComerBoleteria());
			break;
		case CREAR_EN_BATCH:
			if (objetosDetRetContrArtEscenics == null
					|| objetosDetRetContrArtEscenics.size() <= 0) {
				throw new DValidarExcepcion(
						"La lista de detalle a crear no puede ser nula o estar vacía.",
						null);
			}
			break;
		default:
			throw new DValidarExcepcion(getMensajeGeneral("la creación", "de objetos RetenContribArtEscenics"), getMensajeOperInvalida());
		}
		validarParametros("Crear",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvCrearDetRetContrArtEscenics) {
			DCmdSrvCrearDetRetContrArtEscenics copia = (DCmdSrvCrearDetRetContrArtEscenics) comando;
			copia.toDetRetContrArtEscenics = toDetRetContrArtEscenics;
			copia.pkDetRetContrArtEscenics = pkDetRetContrArtEscenics;
			copia.attDetRetContrArtEscenics = attDetRetContrArtEscenics;
			copia.tipoOperacion = tipoOperacion;
			copia.objetosDetRetContrArtEscenics = objetosDetRetContrArtEscenics;
		}
	}
}