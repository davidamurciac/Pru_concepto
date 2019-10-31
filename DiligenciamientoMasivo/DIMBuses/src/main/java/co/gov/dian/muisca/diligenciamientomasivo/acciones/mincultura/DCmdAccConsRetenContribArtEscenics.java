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
 * <p>Descripcion: Comando de acci�n utilizado para consultar un objeto RetenContribArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsRetenContribArtEscenics extends DComandoAccion {
	private static final long serialVersionUID = -1338658294L; 

	/** Objeto de transporte de RetenContribArtEscenics */
	protected DRetenContribArtEscenicsTO toRetenContribArtEscenics;
	/** Llave primaria de RetenContribArtEscenics */
	protected DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics;
	/** Atributos de RetenContribArtEscenics */
	protected DRetenContribArtEscenicsAttTO attRetenContribArtEscenics;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkRetenContribArtEscenics Llave primaria de RetenContribArtEscenics
	 */
	public void inicializar(DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics) {
		isOk = false;
		toRetenContribArtEscenics = null;
		attRetenContribArtEscenics = null;
		this.pkRetenContribArtEscenics = pkRetenContribArtEscenics;
	}

	/**
	 * Devuelve el objeto RetenContribArtEscenics que se haya consultado.
	 * @return Un objeto DRetenContribArtEscenicsTO
	 */
	public DRetenContribArtEscenicsTO getRetenContribArtEscenics() {
		return toRetenContribArtEscenics;
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
		return new DCmdAccConsRetenContribArtEscenics();
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
		return "Permite consultar un objeto RetenContribArtEscenics";
	}

	/**
	 * M�todo para validar los par�metros inicializados, invocado
	 * previamente a la ejecuci�n del comando.
	 * @return true si los par�metros son v�lidos; false de lo contrario
	 * @throws DValidarExcepcion Si los par�metros no son v�lidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics",pkRetenContribArtEscenics);
		parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getIdeDocumento()",pkRetenContribArtEscenics.getIdeDocumento());
		parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getNumRepeticion()",pkRetenContribArtEscenics.getNumRepeticion());
		validarParametros("Consulta",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como par�metro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccConsRetenContribArtEscenics) {
			DCmdAccConsRetenContribArtEscenics copia = (DCmdAccConsRetenContribArtEscenics) comando;
			copia.toRetenContribArtEscenics = toRetenContribArtEscenics;
			copia.pkRetenContribArtEscenics = pkRetenContribArtEscenics;
			copia.attRetenContribArtEscenics = attRetenContribArtEscenics;
		}
	}
}
