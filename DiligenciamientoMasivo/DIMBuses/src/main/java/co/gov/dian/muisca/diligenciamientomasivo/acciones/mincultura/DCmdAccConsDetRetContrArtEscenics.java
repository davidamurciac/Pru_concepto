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
 * <p>Descripcion: Comando de acción utilizado para consultar un objeto DetRetContrArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccConsDetRetContrArtEscenics extends DComandoAccion {
	private static final long serialVersionUID = -212775951L; 

	/** Objeto de transporte de DetRetContrArtEscenics */
	protected DDetRetContrArtEscenicsTO toDetRetContrArtEscenics;
	/** Llave primaria de DetRetContrArtEscenics */
	protected DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics;
	/** Atributos de DetRetContrArtEscenics */
	protected DDetRetContrArtEscenicsAttTO attDetRetContrArtEscenics;

	/**
	 * Inicializa la consulta por llave primaria.
	 * @param pkDetRetContrArtEscenics Llave primaria de DetRetContrArtEscenics
	 */
	public void inicializar(DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics) {
		isOk = false;
		toDetRetContrArtEscenics = null;
		attDetRetContrArtEscenics = null;
		this.pkDetRetContrArtEscenics = pkDetRetContrArtEscenics;
	}

	/**
	 * Devuelve el objeto DetRetContrArtEscenics que se haya consultado.
	 * @return Un objeto DDetRetContrArtEscenicsTO
	 */
	public DDetRetContrArtEscenicsTO getDetRetContrArtEscenics() {
		return toDetRetContrArtEscenics;
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
		return new DCmdAccConsDetRetContrArtEscenics();
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
		return "Permite consultar un objeto DetRetContrArtEscenics";
	}

	/**
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics",pkDetRetContrArtEscenics);
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getIdeDocumento()",pkDetRetContrArtEscenics.getIdeDocumento());
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumRepeticion()",pkDetRetContrArtEscenics.getNumRepeticion());
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumOcurrencia()",pkDetRetContrArtEscenics.getNumOcurrencia());
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumItem()",pkDetRetContrArtEscenics.getNumItem());
		validarParametros("Consulta",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccConsDetRetContrArtEscenics) {
			DCmdAccConsDetRetContrArtEscenics copia = (DCmdAccConsDetRetContrArtEscenics) comando;
			copia.toDetRetContrArtEscenics = toDetRetContrArtEscenics;
			copia.pkDetRetContrArtEscenics = pkDetRetContrArtEscenics;
			copia.attDetRetContrArtEscenics = attDetRetContrArtEscenics;
		}
	}
}
