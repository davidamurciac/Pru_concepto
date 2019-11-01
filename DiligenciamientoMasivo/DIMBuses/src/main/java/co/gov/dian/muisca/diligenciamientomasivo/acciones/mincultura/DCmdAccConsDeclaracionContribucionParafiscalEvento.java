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
 * <p>Descripcion: Comando de acci�n utilizado para consultar un objeto DeclaracionContribucionParafiscalEvento.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsDeclaracionContribucionParafiscalEvento extends DComandoAccion {
	private static final long serialVersionUID = 1209115706L; 

	/** Objeto de transporte de DeclaracionContribucionParafiscalEvento */
	protected DDeclaracionContribucionParafiscalEventoTO toDeclaracionContribucionParafiscalEvento;
	/** Llave primaria de DeclaracionContribucionParafiscalEvento */
	protected DDeclaracionContribucionParafiscalEventoPKTO pkDeclaracionContribucionParafiscalEvento;
	/** Atributos de DeclaracionContribucionParafiscalEvento */
	protected DDeclaracionContribucionParafiscalEventoAttTO attDeclaracionContribucionParafiscalEvento;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkDeclaracionContribucionParafiscalEvento Llave primaria de DeclaracionContribucionParafiscalEvento
	 */
	public void inicializar(DDeclaracionContribucionParafiscalEventoPKTO pkDeclaracionContribucionParafiscalEvento) {
		isOk = false;
		toDeclaracionContribucionParafiscalEvento = null;
		attDeclaracionContribucionParafiscalEvento = null;
		this.pkDeclaracionContribucionParafiscalEvento = pkDeclaracionContribucionParafiscalEvento;
	}

	/**
	 * Devuelve el objeto DeclaracionContribucionParafiscalEvento que se haya consultado.
	 * @return Un objeto DDeclaracionContribucionParafiscalEventoTO
	 */
	public DDeclaracionContribucionParafiscalEventoTO getDeclaracionContribucionParafiscalEvento() {
		return toDeclaracionContribucionParafiscalEvento;
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
		return new DCmdAccConsDeclaracionContribucionParafiscalEvento();
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
		return "Permite consultar un objeto DeclaracionContribucionParafiscalEvento";
	}

	/**
	 * M�todo para validar los par�metros inicializados, invocado
	 * previamente a la ejecuci�n del comando.
	 * @return true si los par�metros son v�lidos; false de lo contrario
	 * @throws DValidarExcepcion Si los par�metros no son v�lidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento",pkDeclaracionContribucionParafiscalEvento);
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia()",pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeItem()",pkDeclaracionContribucionParafiscalEvento.getIdeItem());
		validarParametros("Consulta",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como par�metro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccConsDeclaracionContribucionParafiscalEvento) {
			DCmdAccConsDeclaracionContribucionParafiscalEvento copia = (DCmdAccConsDeclaracionContribucionParafiscalEvento) comando;
			copia.toDeclaracionContribucionParafiscalEvento = toDeclaracionContribucionParafiscalEvento;
			copia.pkDeclaracionContribucionParafiscalEvento = pkDeclaracionContribucionParafiscalEvento;
			copia.attDeclaracionContribucionParafiscalEvento = attDeclaracionContribucionParafiscalEvento;
		}
	}
}
