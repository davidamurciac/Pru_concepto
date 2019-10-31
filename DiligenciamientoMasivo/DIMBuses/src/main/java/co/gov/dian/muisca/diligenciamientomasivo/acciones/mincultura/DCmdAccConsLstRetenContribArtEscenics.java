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
 * <p>Descripcion: Comando de acción utilizado para consultar objetos RetenContribArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstRetenContribArtEscenics extends DComandoAccion {
	private static final long serialVersionUID = 290934567L; 
	

	protected static final int CONSULTA_GENERICA = 0;


	protected DRetenContribArtEscenicsTO toRetenContribArtEscenics;


	/** Tipo de operación de consulta */
	protected int tipoOperacion = -1;
	/** Colección de objetos DRetenContribArtEscenicsTO */
	protected Collection<DRetenContribArtEscenicsTO> objetosRetenContribArtEscenics;


	/**
	 * Inicializa la consulta genérica de RetenContribArtEscenics.
	 * @param attRetenContribArtEscenics Atributos de RetenContribArtEscenics
	 */
	public void inicializarConsultaGenerica(DRetenContribArtEscenicsTO toRetenContribArtEscenics) {
		tipoOperacion = CONSULTA_GENERICA;
		this.toRetenContribArtEscenics = toRetenContribArtEscenics;

	}

	/**
	 * Devuelve la colección de objetos RetenContribArtEscenics que se hayan consultado.
	 * @return Un Collection con objetos DRetenContribArtEscenicsTO
	 */
	public Collection<DRetenContribArtEscenicsTO> getColeccionRetenContribArtEscenics() {
		return objetosRetenContribArtEscenics;
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
		return new DCmdAccConsLstRetenContribArtEscenics();
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
		return "Permite consultar objetos RetenContribArtEscenics";
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

			case CONSULTA_GENERICA:
				parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics",toRetenContribArtEscenics);
				break;


			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos RetenContribArtEscenics"), getMensajeOperInvalida());
		}
		validarParametros("Listar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccConsLstRetenContribArtEscenics) {
			DCmdAccConsLstRetenContribArtEscenics copia = (DCmdAccConsLstRetenContribArtEscenics) comando;
			copia.tipoOperacion = tipoOperacion;
			copia.objetosRetenContribArtEscenics = objetosRetenContribArtEscenics;
			copia.toRetenContribArtEscenics = toRetenContribArtEscenics;
		}
	}
}
