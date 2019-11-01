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
 * <p>Descripcion: Comando de servicio utilizado para crear un objeto RetenContribArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvCrearRetenContribArtEscenics extends DComandoServicioInterno {
	private static final long serialVersionUID = 1455539134L; 

	/** Objeto de transporte de RetenContribArtEscenics */
	protected DRetenContribArtEscenicsTO toRetenContribArtEscenics;
	/** Llave primaria de RetenContribArtEscenics */
	protected DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics;
	/** Atributos de RetenContribArtEscenics */
	protected DRetenContribArtEscenicsAttTO attRetenContribArtEscenics;
	/** Colección de objetos de transporte de RetenContribArtEscenics */
	protected List<DDetRetContrArtEscenicsTO> lstDetRetContrArtEscenics;
	/** Tipo de operación de consulta */
	protected int tipoOperacion = -1;
	
	public static final int CREAR = 0;
	public static final int CREAR_CON_DETALLE = 1;

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	@Override
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvCrearRetenContribArtEscenics) {
			final DCmdSrvCrearRetenContribArtEscenics copia = (DCmdSrvCrearRetenContribArtEscenics) comando;
			copia.toRetenContribArtEscenics = toRetenContribArtEscenics;
			copia.pkRetenContribArtEscenics = pkRetenContribArtEscenics;
			copia.attRetenContribArtEscenics = attRetenContribArtEscenics;
			copia.lstDetRetContrArtEscenics = lstDetRetContrArtEscenics;
			copia.tipoOperacion = tipoOperacion;
		}
	}
	
	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	@Override
	public Object clonar() {
		return new DCmdSrvCrearRetenContribArtEscenics();
	}


	/**
	 * Ejecuta el comando de servicio.
	 * @throws DExcepcion Si ocurre algún error al realizar la
	 * la creación de RetenContribArtEscenics
	 */
	@Override
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene la descripción del comando.
	 * @return Un String con la descripción del comando
	 */
	@Override
	public String getDescripcion() {
		return "Permite crear un objeto RetenContribArtEscenics";
	}

	/**
	 * Devuelve la llave primaria para el objeto creado.
	 * @return Un objeto DRetenContribArtEscenicsPKTO
	 */
	public DRetenContribArtEscenicsPKTO getRetenContribArtEscenicsPK() {
		return pkRetenContribArtEscenics;
	}

	/**
	 * Inicializa la creación de RetenContribArtEscenics.
	 * @param attRetenContribArtEscenics Atributos de RetenContribArtEscenics
	 */
	public void inicializar(DRetenContribArtEscenicsAttTO attRetenContribArtEscenics) {
		tipoOperacion = CREAR;
		this.attRetenContribArtEscenics = attRetenContribArtEscenics;
		pkRetenContribArtEscenics = null;
		toRetenContribArtEscenics = null;
	}

	/**
	 * Inicializa la creación de RetenContribArtEscenics.
	 * @param attRetenContribArtEscenics Atributos de RetenContribArtEscenics
	 * @param List<DDetRetContrArtEscenicsTO> lstDetRetContrArtEscenics lista de detalle de RetenContribArtEscenics
	 */
	public void inicializar(DRetenContribArtEscenicsTO toRetenContribArtEscenics, List<DDetRetContrArtEscenicsTO> lstDetRetContrArtEscenics) {
		tipoOperacion = CREAR_CON_DETALLE;
		this.toRetenContribArtEscenics = toRetenContribArtEscenics;
		this.lstDetRetContrArtEscenics = lstDetRetContrArtEscenics;
	}

	/**
	 * Indica si el comando es auditable.
	 * @return true si el comando es auditable; false de lo contrario
	 */
	@Override
	public boolean isAuditable() {
		return true;
	}

	/**
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecucióndel comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		final Map<String, Object> parametros = new HashMap<String, Object>();
		switch (tipoOperacion) {
		case CREAR:
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics",attRetenContribArtEscenics);
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdeFormato()",attRetenContribArtEscenics.getIdeFormato());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumVersionFormato()",attRetenContribArtEscenics.getNumVersionFormato());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdeDocumentoCarga()",attRetenContribArtEscenics.getIdeDocumentoCarga());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumRepeticionCarga()",attRetenContribArtEscenics.getNumRepeticionCarga());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdeFormatoCarga()",attRetenContribArtEscenics.getIdeFormatoCarga());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumVersionFormatoCarga()",attRetenContribArtEscenics.getNumVersionFormatoCarga());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdePersonaRutAgente()",attRetenContribArtEscenics.getIdePersonaRutAgente());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumAnio()",attRetenContribArtEscenics.getNumAnio());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getValSubtBolsPrecIgSup3uvt()",attRetenContribArtEscenics.getValSubtBolsPrecIgSup3uvt());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getValSubtDerAsistIgSup3uvt()",attRetenContribArtEscenics.getValSubtDerAsistIgSup3uvt());
			parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getValSubtDevsRetensEvenAnt()",attRetenContribArtEscenics.getValSubtDevsRetensEvenAnt());
			break;
		case CREAR_CON_DETALLE:
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics",toRetenContribArtEscenics);
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics",toRetenContribArtEscenics.getPK());
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics.getAtt()",toRetenContribArtEscenics.getAtt());
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics.getAtt().getIdeFormato()",toRetenContribArtEscenics.getAtt().getIdeFormato());
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics.getAtt().getNumVersionFormato()",toRetenContribArtEscenics.getAtt().getNumVersionFormato());
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics.getAtt().getIdeDocumentoCarga()",toRetenContribArtEscenics.getAtt().getIdeDocumentoCarga());
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics.getAtt().getNumRepeticionCarga()",toRetenContribArtEscenics.getAtt().getNumRepeticionCarga());
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics.getAtt().getIdeFormatoCarga()",toRetenContribArtEscenics.getAtt().getIdeFormatoCarga());
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics.getAtt().getNumVersionFormatoCarga()",toRetenContribArtEscenics.getAtt().getNumVersionFormatoCarga());
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics.getAtt().getIdePersonaRutAgente()",toRetenContribArtEscenics.getAtt().getIdePersonaRutAgente());
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics.getAtt().getNumAnio()",toRetenContribArtEscenics.getAtt().getNumAnio());
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics.getAtt().getValSubtBolsPrecIgSup3uvt()",toRetenContribArtEscenics.getAtt().getValSubtBolsPrecIgSup3uvt());
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics.getAtt().getValSubtDerAsistIgSup3uvt()",toRetenContribArtEscenics.getAtt().getValSubtDerAsistIgSup3uvt());
			parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics.getAtt().getValSubtDevsRetensEvenAnt()",toRetenContribArtEscenics.getAtt().getValSubtDevsRetensEvenAnt());
			
			if (lstDetRetContrArtEscenics == null
					|| lstDetRetContrArtEscenics.size() <= 0) {
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
}
