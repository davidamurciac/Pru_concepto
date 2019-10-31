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
 * <p>Descripcion: Comando de acción utilizado para consultar un objeto DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsDeclaracionContribucionParafiscalPatrocinador extends DComandoAccion {
	private static final long serialVersionUID = -1478047917L; 

	/** Objeto de transporte de DeclaracionContribucionParafiscalPatrocinador */
	protected DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador;
	/** Llave primaria de DeclaracionContribucionParafiscalPatrocinador */
	protected DDeclaracionContribucionParafiscalPatrocinadorPKTO pkDeclaracionContribucionParafiscalPatrocinador;
	/** Atributos de DeclaracionContribucionParafiscalPatrocinador */
	protected DDeclaracionContribucionParafiscalPatrocinadorAttTO attDeclaracionContribucionParafiscalPatrocinador;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkDeclaracionContribucionParafiscalPatrocinador Llave primaria de DeclaracionContribucionParafiscalPatrocinador
	 */
	public void inicializar(DDeclaracionContribucionParafiscalPatrocinadorPKTO pkDeclaracionContribucionParafiscalPatrocinador) {
		isOk = false;
		toDeclaracionContribucionParafiscalPatrocinador = null;
		attDeclaracionContribucionParafiscalPatrocinador = null;
		this.pkDeclaracionContribucionParafiscalPatrocinador = pkDeclaracionContribucionParafiscalPatrocinador;
	}

	/**
	 * Devuelve el objeto DeclaracionContribucionParafiscalPatrocinador que se haya consultado.
	 * @return Un objeto DDeclaracionContribucionParafiscalPatrocinadorTO
	 */
	public DDeclaracionContribucionParafiscalPatrocinadorTO getDeclaracionContribucionParafiscalPatrocinador() {
		return toDeclaracionContribucionParafiscalPatrocinador;
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
		return new DCmdAccConsDeclaracionContribucionParafiscalPatrocinador();
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
		return "Permite consultar un objeto DeclaracionContribucionParafiscalPatrocinador";
	}

	/**
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador",pkDeclaracionContribucionParafiscalPatrocinador);
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem());
		validarParametros("Consulta",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccConsDeclaracionContribucionParafiscalPatrocinador) {
			DCmdAccConsDeclaracionContribucionParafiscalPatrocinador copia = (DCmdAccConsDeclaracionContribucionParafiscalPatrocinador) comando;
			copia.toDeclaracionContribucionParafiscalPatrocinador = toDeclaracionContribucionParafiscalPatrocinador;
			copia.pkDeclaracionContribucionParafiscalPatrocinador = pkDeclaracionContribucionParafiscalPatrocinador;
			copia.attDeclaracionContribucionParafiscalPatrocinador = attDeclaracionContribucionParafiscalPatrocinador;
		}
	}
}
