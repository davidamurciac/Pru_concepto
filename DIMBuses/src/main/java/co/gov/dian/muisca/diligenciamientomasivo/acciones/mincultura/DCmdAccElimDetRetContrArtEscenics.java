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
 * <p>Descripcion: Comando de acci�n utilizado para eliminar un objeto DetRetContrArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccElimDetRetContrArtEscenics extends DComandoAccion {
	private static final long serialVersionUID = -1052661429L; 

	/** Llave primaria de DetRetContrArtEscenics */
	protected DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics;

	/**
	 * Inicializa la eliminaci�n de DetRetContrArtEscenics.
	 * @param pkDetRetContrArtEscenics Llave primaria de DetRetContrArtEscenics
	 */
	public void inicializar(DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics) {
		isOk = false;
		this.pkDetRetContrArtEscenics = pkDetRetContrArtEscenics;
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
		return new DCmdAccElimDetRetContrArtEscenics();
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
		return "Permite eliminar un objeto DetRetContrArtEscenics";
	}

	/**
	 * M�odo para validar los par�metros inicializados, invocado
	 * previamente a la ejecuci�n del comando.
	 * @return true si los par�metros son v�lidos; false de lo contrario
	 * @throws DValidarExcepcion Si los par�metros no son v�lidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics",pkDetRetContrArtEscenics);
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getIdeDocumento()",pkDetRetContrArtEscenics.getIdeDocumento());
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumRepeticion()",pkDetRetContrArtEscenics.getNumRepeticion());
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumOcurrencia()",pkDetRetContrArtEscenics.getNumOcurrencia());
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumItem()",pkDetRetContrArtEscenics.getNumItem());
		validarParametros("Eliminar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como par�metro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccElimDetRetContrArtEscenics) {
			DCmdAccElimDetRetContrArtEscenics copia = (DCmdAccElimDetRetContrArtEscenics) comando;
			copia.pkDetRetContrArtEscenics = pkDetRetContrArtEscenics;
		}
	}
}
