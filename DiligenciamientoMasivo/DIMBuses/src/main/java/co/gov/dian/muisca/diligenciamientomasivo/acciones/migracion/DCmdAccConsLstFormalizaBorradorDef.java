/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.migracion;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.migracion.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de acci�n utilizado para consultar objetos FormalizaBorradorDef.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsLstFormalizaBorradorDef extends DComandoAccion {
	private static final long serialVersionUID = 1009236784L; 
	

	protected static final int CONSULTA_GENERICA = 0;


	protected DFormalizaBorradorDefTO toFormalizaBorradorDef;


	/** Tipo de operaci�n de consulta */
	protected int tipoOperacion = -1;
	/** Colecci�n de objetos DFormalizaBorradorDefTO */
	protected Collection<DFormalizaBorradorDefTO> objetosFormalizaBorradorDef;


	/**
	 * Inicializa la consulta gen�rica de FormalizaBorradorDef.
	 * @param attFormalizaBorradorDef Atributos de FormalizaBorradorDef
	 */
	public void inicializarConsultaGenerica(DFormalizaBorradorDefTO toFormalizaBorradorDef) {
		tipoOperacion = CONSULTA_GENERICA;
		this.toFormalizaBorradorDef = toFormalizaBorradorDef;

	}

	/**
	 * Devuelve la colecci�n de objetos FormalizaBorradorDef que se hayan consultado.
	 * @return Un Collection con objetos DFormalizaBorradorDefTO
	 */
	public Collection<DFormalizaBorradorDefTO> getColeccionFormalizaBorradorDef() {
		return objetosFormalizaBorradorDef;
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
		return new DCmdAccConsLstFormalizaBorradorDef();
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
		return "Permite consultar objetos FormalizaBorradorDef";
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
				parametros.put(this.getClass().getName()+":validar:toFormalizaBorradorDef",toFormalizaBorradorDef);
				break;


			default:
				throw new DValidarExcepcion(getMensajeGeneral("la consulta", "de objetos FormalizaBorradorDef"), getMensajeOperInvalida());
		}
		validarParametros("Listar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como par�metro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccConsLstFormalizaBorradorDef) {
			DCmdAccConsLstFormalizaBorradorDef copia = (DCmdAccConsLstFormalizaBorradorDef) comando;
			copia.tipoOperacion = tipoOperacion;
			copia.objetosFormalizaBorradorDef = objetosFormalizaBorradorDef;
			copia.toFormalizaBorradorDef = toFormalizaBorradorDef;
		}
	}
}
