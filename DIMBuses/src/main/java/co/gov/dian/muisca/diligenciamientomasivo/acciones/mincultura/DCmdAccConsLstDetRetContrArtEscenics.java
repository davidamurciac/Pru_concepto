/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.mincultura;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de acción utilizado para consultar objetos DetRetContrArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstDetRetContrArtEscenics extends DComandoAccion {
	private static final long serialVersionUID = -852945100L; 
	
	public static final int CONSULTAR_POR_RETENCONTRIBARTESCENICS = 0;

	protected static final int CONSULTA_GENERICA = 1;

	/** Llave primaria de RetenContribArtEscenics */
	protected DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics;

	protected DDetRetContrArtEscenicsTO toDetRetContrArtEscenics;


	/** Tipo de operación de consulta */
	protected int tipoOperacion = -1;
	/** Colección de objetos DDetRetContrArtEscenicsTO */
	protected Collection<DDetRetContrArtEscenicsTO> objetosDetRetContrArtEscenics;

	/**
	 * Inicializa la consulta por RetenContribArtEscenics.
	 * @param pkRetenContribArtEscenics Llave primaria de RetenContribArtEscenics
	 */
	public void inicializarConsultarPorRetenContribArtEscenics(DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics) {
		isOk = false;
		tipoOperacion = CONSULTAR_POR_RETENCONTRIBARTESCENICS;
		this.pkRetenContribArtEscenics = pkRetenContribArtEscenics;
		objetosDetRetContrArtEscenics = null;
	}


	/**
	 * Inicializa la consulta genérica de DetRetContrArtEscenics.
	 * @param attDetRetContrArtEscenics Atributos de DetRetContrArtEscenics
	 */
	public void inicializarConsultaGenerica(DDetRetContrArtEscenicsTO toDetRetContrArtEscenics) {
		tipoOperacion = CONSULTA_GENERICA;
		this.toDetRetContrArtEscenics = toDetRetContrArtEscenics;

	}

	/**
	 * Devuelve la colección de objetos DetRetContrArtEscenics que se hayan consultado.
	 * @return Un Collection con objetos DDetRetContrArtEscenicsTO
	 */
	public Collection<DDetRetContrArtEscenicsTO> getColeccionDetRetContrArtEscenics() {
		return objetosDetRetContrArtEscenics;
	}

	/**
	 * Ejecuta el comando de acción.
	 */
	protected void ejecutarComando() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdAccConsLstDetRetContrArtEscenics();
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
		return "Permite consultar objetos DetRetContrArtEscenics";
	}

	/**
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		switch (tipoOperacion) {
			case CONSULTAR_POR_RETENCONTRIBARTESCENICS:
				parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics",pkRetenContribArtEscenics);
				parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getIdeDocumento()",pkRetenContribArtEscenics.getIdeDocumento());
				parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getNumRepeticion()",pkRetenContribArtEscenics.getNumRepeticion());
				break;

			case CONSULTA_GENERICA:
				parametros.put(this.getClass().getName()+":validar:toDetRetContrArtEscenics",toDetRetContrArtEscenics);
				break;


			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos DetRetContrArtEscenics"), getMensajeOperInvalida());
		}
		validarParametros("Listar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccConsLstDetRetContrArtEscenics) {
			DCmdAccConsLstDetRetContrArtEscenics copia = (DCmdAccConsLstDetRetContrArtEscenics) comando;
			copia.tipoOperacion = tipoOperacion;
			copia.pkRetenContribArtEscenics = pkRetenContribArtEscenics;
			copia.objetosDetRetContrArtEscenics = objetosDetRetContrArtEscenics;
			copia.toDetRetContrArtEscenics = toDetRetContrArtEscenics;
		}
	}
}
