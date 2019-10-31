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
 * <p>Descripcion: Comando de acción utilizado para consultar un objeto RegDiarioMinCultura.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsRegDiarioMinCultura extends DComandoAccion {
	private static final long serialVersionUID = 547272355L; 

	/** Objeto de transporte de RegDiarioMinCultura */
	protected DRegDiarioMinCulturaTO toRegDiarioMinCultura;
	/** Llave primaria de RegDiarioMinCultura */
	protected DRegDiarioMinCulturaPKTO pkRegDiarioMinCultura;
	/** Atributos de RegDiarioMinCultura */
	protected DRegDiarioMinCulturaAttTO attRegDiarioMinCultura;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkRegDiarioMinCultura Llave primaria de RegDiarioMinCultura
	 */
	public void inicializar(DRegDiarioMinCulturaPKTO pkRegDiarioMinCultura) {
		isOk = false;
		toRegDiarioMinCultura = null;
		attRegDiarioMinCultura = null;
		this.pkRegDiarioMinCultura = pkRegDiarioMinCultura;
	}

	/**
	 * Devuelve el objeto RegDiarioMinCultura que se haya consultado.
	 * @return Un objeto DRegDiarioMinCulturaTO
	 */
	public DRegDiarioMinCulturaTO getRegDiarioMinCultura() {
		return toRegDiarioMinCultura;
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
		return new DCmdAccConsRegDiarioMinCultura();
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
		return "Permite consultar un objeto RegDiarioMinCultura";
	}

	/**
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:pkRegDiarioMinCultura",pkRegDiarioMinCultura);
		parametros.put(this.getClass().getName()+":validar:pkRegDiarioMinCultura.getIdeRegistroDiario()",pkRegDiarioMinCultura.getIdeRegistroDiario());
		validarParametros("Consulta",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccConsRegDiarioMinCultura) {
			DCmdAccConsRegDiarioMinCultura copia = (DCmdAccConsRegDiarioMinCultura) comando;
			copia.toRegDiarioMinCultura = toRegDiarioMinCultura;
			copia.pkRegDiarioMinCultura = pkRegDiarioMinCultura;
			copia.attRegDiarioMinCultura = attRegDiarioMinCultura;
		}
	}
}
