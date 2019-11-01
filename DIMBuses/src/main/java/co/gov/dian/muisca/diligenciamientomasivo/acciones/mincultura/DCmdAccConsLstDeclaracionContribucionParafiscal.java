/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
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
 * <p>Descripcion: Comando de acci�n utilizado para consultar objetos DeclaracionContribucionParafiscal.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstDeclaracionContribucionParafiscal extends DComandoAccion {
	private static final long serialVersionUID = 220814242L; 
	

	protected static final int CONSULTA_GENERICA = 0;


	protected DDeclaracionContribucionParafiscalTO toDeclaracionContribucionParafiscal;


	/** Tipo de operaci�n de consulta */
	protected int tipoOperacion = -1;
	/** Colecci�n de objetos DDeclaracionContribucionParafiscalTO */
	protected Collection<DDeclaracionContribucionParafiscalTO> objetosDeclaracionContribucionParafiscal;


	/**
	 * Inicializa la consulta gen�rica de DeclaracionContribucionParafiscal.
	 * @param attDeclaracionContribucionParafiscal Atributos de DeclaracionContribucionParafiscal
	 */
	public void inicializarConsultaGenerica(DDeclaracionContribucionParafiscalTO toDeclaracionContribucionParafiscal) {
		tipoOperacion = CONSULTA_GENERICA;
		this.toDeclaracionContribucionParafiscal = toDeclaracionContribucionParafiscal;

	}

	/**
	 * Devuelve la colecci�n de objetos DeclaracionContribucionParafiscal que se hayan consultado.
	 * @return Un Collection con objetos DDeclaracionContribucionParafiscalTO
	 */
	public Collection<DDeclaracionContribucionParafiscalTO> getColeccionDeclaracionContribucionParafiscal() {
		return objetosDeclaracionContribucionParafiscal;
	}

	/**
	 * Ejecuta el comando de acci�n.
	 */
	protected void ejecutarComando() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdAccConsLstDeclaracionContribucionParafiscal();
	}

	/**
	 * Indica si el comando es auditable.
	 * @return true si el comando es auditable; false de lo contrario
	 */
	public boolean isAuditable() {
		return true;
	}

	/**
	 * Obtiene la descripci�n del comando.
	 * @return Un String con la descripci�n del comando
	 */
	public String getDescripcion() {
		return "Permite consultar objetos DeclaracionContribucionParafiscal";
	}

	/**
	 * M�todo para validar los par�metros inicializados, invocado
	 * previamente a la ejecuci�n del comando.
	 * @return true si los par�metros son v�lidos; false de lo contrario
	 * @throws DValidarExcepcion Si los par�metros no son v�lidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		switch (tipoOperacion) {

			case CONSULTA_GENERICA:
				parametros.put(this.getClass().getName()+":validar:toDeclaracionContribucionParafiscal",toDeclaracionContribucionParafiscal);
				break;


			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos DeclaracionContribucionParafiscal"), getMensajeOperInvalida());
		}
		validarParametros("Listar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como par�metro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccConsLstDeclaracionContribucionParafiscal) {
			DCmdAccConsLstDeclaracionContribucionParafiscal copia = (DCmdAccConsLstDeclaracionContribucionParafiscal) comando;
			copia.tipoOperacion = tipoOperacion;
			copia.objetosDeclaracionContribucionParafiscal = objetosDeclaracionContribucionParafiscal;
			copia.toDeclaracionContribucionParafiscal = toDeclaracionContribucionParafiscal;
		}
	}
}
