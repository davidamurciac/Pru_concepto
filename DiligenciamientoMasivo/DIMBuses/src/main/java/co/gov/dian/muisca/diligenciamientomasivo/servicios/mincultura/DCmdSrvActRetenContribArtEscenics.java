/**
 * Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header:$
 */
package co.gov.dian.muisca.diligenciamientomasivo.servicios.mincultura;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.mincultura.*;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de servicio utilizado para actualizar un objeto RetenContribArtEscenics.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvActRetenContribArtEscenics extends DComandoServicioInterno {
	private static final long serialVersionUID = -1791671085L; 

	/** Objeto de transporte de RetenContribArtEscenics */
	protected DRetenContribArtEscenicsTO toRetenContribArtEscenics;
	/** Llave primaria de RetenContribArtEscenics */
	protected DRetenContribArtEscenicsPKTO pkRetenContribArtEscenics;
	/** Atributos de RetenContribArtEscenics */
	protected DRetenContribArtEscenicsAttTO attRetenContribArtEscenics;

	/**
	 * Inicializa la actualización de RetenContribArtEscenics.
	 * @param toRetenContribArtEscenics Objeto de Transporte de RetenContribArtEscenics
	 */
	public void inicializar(DRetenContribArtEscenicsTO toRetenContribArtEscenics) {
		this.toRetenContribArtEscenics = toRetenContribArtEscenics;
		if (toRetenContribArtEscenics != null) {
			pkRetenContribArtEscenics = this.toRetenContribArtEscenics.getPK();
			attRetenContribArtEscenics = this.toRetenContribArtEscenics.getAtt();
		}
	}

	/**
	 * Ejecuta el comando de servicio.
	 * @throws DExcepcion Si ocurre algún error al realizar la
	 * la actualización de RetenContribArtEscenics
	 */
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdSrvActRetenContribArtEscenics();
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
		return "Permite actualizar un objeto RetenContribArtEscenics";
	}

	/**
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecución del comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros=new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:toRetenContribArtEscenics",toRetenContribArtEscenics);
		parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics",pkRetenContribArtEscenics);
		parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics",attRetenContribArtEscenics);
		parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getIdeDocumento()",pkRetenContribArtEscenics.getIdeDocumento());
		parametros.put(this.getClass().getName()+":validar:pkRetenContribArtEscenics.getNumRepeticion()",pkRetenContribArtEscenics.getNumRepeticion());
		parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdeFormato()",attRetenContribArtEscenics.getIdeFormato());
		parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumVersionFormato()",attRetenContribArtEscenics.getNumVersionFormato());
		parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdeDocumentoCarga()",attRetenContribArtEscenics.getIdeDocumentoCarga());
		parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumRepeticionCarga()",attRetenContribArtEscenics.getNumRepeticionCarga());
		parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdeFormatoCarga()",attRetenContribArtEscenics.getIdeFormatoCarga());
		parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumVersionFormatoCarga()",attRetenContribArtEscenics.getNumVersionFormatoCarga());
		parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getIdePersonaRutAgente()",attRetenContribArtEscenics.getIdePersonaRutAgente());
		parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getNumAnio()",attRetenContribArtEscenics.getNumAnio());
		parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getValSubtBolsPrecIgSup3uvt()",attRetenContribArtEscenics.getValSubtBolsPrecIgSup3uvt());
		parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getValSubtDerAsistIgSup3uvt()",attRetenContribArtEscenics.getValSubtDerAsistIgSup3uvt());
		parametros.put(this.getClass().getName()+":validar:attRetenContribArtEscenics.getValSubtDevsRetensEvenAnt()",attRetenContribArtEscenics.getValSubtDevsRetensEvenAnt());
		validarParametros("Actualizar",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvActRetenContribArtEscenics) {
			DCmdSrvActRetenContribArtEscenics copia = (DCmdSrvActRetenContribArtEscenics) comando;
			copia.toRetenContribArtEscenics = toRetenContribArtEscenics;
			copia.pkRetenContribArtEscenics = pkRetenContribArtEscenics;
			copia.attRetenContribArtEscenics = attRetenContribArtEscenics;
		}
	}
}
