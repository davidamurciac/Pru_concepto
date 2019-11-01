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
 * <p>Descripcion: Comando de acci�n utilizado para actualizar un objeto DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccActDeclaracionContribucionParafiscalPatrocinador extends DComandoAccion {
	private static final long serialVersionUID = 1348735313L; 

	/** Objeto de transporte de DeclaracionContribucionParafiscalPatrocinador */
	protected DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador;
	/** Llave primaria de DeclaracionContribucionParafiscalPatrocinador */
	protected DDeclaracionContribucionParafiscalPatrocinadorPKTO pkDeclaracionContribucionParafiscalPatrocinador;
	/** Atributos de DeclaracionContribucionParafiscalPatrocinador */
	protected DDeclaracionContribucionParafiscalPatrocinadorAttTO attDeclaracionContribucionParafiscalPatrocinador;

	/**
	 * Inicializa la actualizaci�n de DeclaracionContribucionParafiscalPatrocinador.
	 * @param toDeclaracionContribucionParafiscalPatrocinador Objeto de Transporte de DeclaracionContribucionParafiscalPatrocinador
	 */
	public void inicializar(DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador) {
		isOk = false;
		this.toDeclaracionContribucionParafiscalPatrocinador = toDeclaracionContribucionParafiscalPatrocinador;
		if (toDeclaracionContribucionParafiscalPatrocinador != null) {
			pkDeclaracionContribucionParafiscalPatrocinador = this.toDeclaracionContribucionParafiscalPatrocinador.getPK();
			attDeclaracionContribucionParafiscalPatrocinador = this.toDeclaracionContribucionParafiscalPatrocinador.getAtt();
		}
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
		return new DCmdAccActDeclaracionContribucionParafiscalPatrocinador();
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
		return "Permite actualizar un objeto DeclaracionContribucionParafiscalPatrocinador";
	}

	/**
	 * M�odo para validar los par�metros inicializados, invocado
	 * previamente a la ejecuci�n del comando.
	 * @return true si los par�metros son v�lidos; false de lo contrario
	 * @throws DValidarExcepcion Si los par�metros no son v�lidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:toDeclaracionContribucionParafiscalPatrocinador",toDeclaracionContribucionParafiscalPatrocinador);
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador",pkDeclaracionContribucionParafiscalPatrocinador);
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador",attDeclaracionContribucionParafiscalPatrocinador);
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeDocumentoContParaf());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscalPatrocinador.getNumRepeticionDocContParf());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeOcurrencia());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem()",pkDeclaracionContribucionParafiscalPatrocinador.getIdeItem());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getNumEvento()",attDeclaracionContribucionParafiscalPatrocinador.getNumEvento());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getNumNit()",attDeclaracionContribucionParafiscalPatrocinador.getNumNit());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getNumDv()",attDeclaracionContribucionParafiscalPatrocinador.getNumDv());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getValRazonSocial()",attDeclaracionContribucionParafiscalPatrocinador.getValRazonSocial());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getNumBoletasPatrocinio()",attDeclaracionContribucionParafiscalPatrocinador.getNumBoletasPatrocinio());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getValIndividualBoleta()",attDeclaracionContribucionParafiscalPatrocinador.getValIndividualBoleta());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getIdeUsuarioCambio()",attDeclaracionContribucionParafiscalPatrocinador.getIdeUsuarioCambio());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador.getFecCambio()",attDeclaracionContribucionParafiscalPatrocinador.getFecCambio());
		validarParametros("Actualizar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como par�metro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccActDeclaracionContribucionParafiscalPatrocinador) {
			DCmdAccActDeclaracionContribucionParafiscalPatrocinador copia = (DCmdAccActDeclaracionContribucionParafiscalPatrocinador) comando;
			copia.toDeclaracionContribucionParafiscalPatrocinador = toDeclaracionContribucionParafiscalPatrocinador;
			copia.pkDeclaracionContribucionParafiscalPatrocinador = pkDeclaracionContribucionParafiscalPatrocinador;
			copia.attDeclaracionContribucionParafiscalPatrocinador = attDeclaracionContribucionParafiscalPatrocinador;
		}
	}
}
