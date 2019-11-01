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
 * <p>Descripcion: Comando de acción utilizado para actualizar un objeto DetRetContrArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdAccActDetRetContrArtEscenics extends DComandoAccion {
	private static final long serialVersionUID = 625207219L; 

	/** Objeto de transporte de DetRetContrArtEscenics */
	protected DDetRetContrArtEscenicsTO toDetRetContrArtEscenics;
	/** Llave primaria de DetRetContrArtEscenics */
	protected DDetRetContrArtEscenicsPKTO pkDetRetContrArtEscenics;
	/** Atributos de DetRetContrArtEscenics */
	protected DDetRetContrArtEscenicsAttTO attDetRetContrArtEscenics;

	/**
	 * Inicializa la actualización de DetRetContrArtEscenics.
	 * @param toDetRetContrArtEscenics Objeto de Transporte de DetRetContrArtEscenics
	 */
	public void inicializar(DDetRetContrArtEscenicsTO toDetRetContrArtEscenics) {
		isOk = false;
		this.toDetRetContrArtEscenics = toDetRetContrArtEscenics;
		if (toDetRetContrArtEscenics != null) {
			pkDetRetContrArtEscenics = this.toDetRetContrArtEscenics.getPK();
			attDetRetContrArtEscenics = this.toDetRetContrArtEscenics.getAtt();
		}
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
		return new DCmdAccActDetRetContrArtEscenics();
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
		return "Permite actualizar un objeto DetRetContrArtEscenics";
	}

	/**
	 * Méodo para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:toDetRetContrArtEscenics",toDetRetContrArtEscenics);
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics",pkDetRetContrArtEscenics);
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics",attDetRetContrArtEscenics);
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getIdeDocumento()",pkDetRetContrArtEscenics.getIdeDocumento());
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumRepeticion()",pkDetRetContrArtEscenics.getNumRepeticion());
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumOcurrencia()",pkDetRetContrArtEscenics.getNumOcurrencia());
		parametros.put(this.getClass().getName()+":validar:pkDetRetContrArtEscenics.getNumItem()",pkDetRetContrArtEscenics.getNumItem());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getIdeFormato()",attDetRetContrArtEscenics.getIdeFormato());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getNumVersionFormato()",attDetRetContrArtEscenics.getNumVersionFormato());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getNumEvento()",attDetRetContrArtEscenics.getNumEvento());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getNomEspectaculo()",attDetRetContrArtEscenics.getNomEspectaculo());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getCodMunicipioEspectaculo()",attDetRetContrArtEscenics.getCodMunicipioEspectaculo());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getCodDepartamentoEspectaculo()",attDetRetContrArtEscenics.getCodDepartamentoEspectaculo());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getFecRealizacion()",attDetRetContrArtEscenics.getFecRealizacion());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getIdePersonaRutProductor()",attDetRetContrArtEscenics.getIdePersonaRutProductor());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getValDireccionProductor()",attDetRetContrArtEscenics.getValDireccionProductor());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getCodMunicipioProductor()",attDetRetContrArtEscenics.getCodMunicipioProductor());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getCodDepartamentoProductor()",attDetRetContrArtEscenics.getCodDepartamentoProductor());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getValLugarEvento()",attDetRetContrArtEscenics.getValLugarEvento());		
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getNumTotBoletasVendidas()",attDetRetContrArtEscenics.getNumTotBoletasVendidas());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getValTotBoletasVendidas()",attDetRetContrArtEscenics.getValTotBoletasVendidas());
		parametros.put(this.getClass().getName()+":validar:attDetRetContrArtEscenics.getValServDistComerBoleteria()",attDetRetContrArtEscenics.getValServDistComerBoleteria());
		validarParametros("Actualizar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccActDetRetContrArtEscenics) {
			DCmdAccActDetRetContrArtEscenics copia = (DCmdAccActDetRetContrArtEscenics) comando;
			copia.toDetRetContrArtEscenics = toDetRetContrArtEscenics;
			copia.pkDetRetContrArtEscenics = pkDetRetContrArtEscenics;
			copia.attDetRetContrArtEscenics = attDetRetContrArtEscenics;
		}
	}
}
