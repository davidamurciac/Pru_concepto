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
 * <p>Descripcion: Comando de servicio utilizado para crear un objeto DeclaracionContribucionParafiscal.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvCrearDeclaracionContribucionParafiscal extends DComandoServicioInterno {
	private static final long serialVersionUID = -1980219527L; 

	/** Objeto de transporte de DeclaracionContribucionParafiscal */
	protected DDeclaracionContribucionParafiscalTO toDeclaracionContribucionParafiscal;
	/** Llave primaria de DeclaracionContribucionParafiscal */
	protected DDeclaracionContribucionParafiscalPKTO pkDeclaracionContribucionParafiscal;
	/** Atributos de DeclaracionContribucionParafiscal */
	protected DDeclaracionContribucionParafiscalAttTO attDeclaracionContribucionParafiscal;

	/**
	 * Inicializa la creación de DeclaracionContribucionParafiscal.
	 * @param toDeclaracionContribucionParafiscal Objeto de Transporte de DeclaracionContribucionParafiscal
	 */
	public void inicializar(DDeclaracionContribucionParafiscalTO toDeclaracionContribucionParafiscal) {
		this.toDeclaracionContribucionParafiscal = toDeclaracionContribucionParafiscal;
		if (toDeclaracionContribucionParafiscal != null) {
			pkDeclaracionContribucionParafiscal = this.toDeclaracionContribucionParafiscal.getPK();
			attDeclaracionContribucionParafiscal = this.toDeclaracionContribucionParafiscal.getAtt();
		}
	}


	/**
	 * Ejecuta el comando de servicio.
	 * @throws DExcepcion Si ocurre algún error al realizar la
	 * la creación de DeclaracionContribucionParafiscal
	 */
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdSrvCrearDeclaracionContribucionParafiscal();
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
		return "Permite crear un objeto DeclaracionContribucionParafiscal";
	}

	/**
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecucióndel comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(this.getClass().getName()+":validar:toDeclaracionContribucionParafiscal",toDeclaracionContribucionParafiscal);
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal",attDeclaracionContribucionParafiscal);
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal",pkDeclaracionContribucionParafiscal);
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscal.getIdeDocumentoContParaf());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscal.getNumRepeticionDocContParf());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getIdePersonaRut()",attDeclaracionContribucionParafiscal.getIdePersonaRut());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getNumAnio()",attDeclaracionContribucionParafiscal.getNumAnio());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getNumPeriodo()",attDeclaracionContribucionParafiscal.getNumPeriodo());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getIdeSolicitud()",attDeclaracionContribucionParafiscal.getIdeSolicitud());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getIdeTipoDocumento()",attDeclaracionContribucionParafiscal.getIdeTipoDocumento());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getNumIdentificacion()",attDeclaracionContribucionParafiscal.getNumIdentificacion());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValTotalBolVendida()",attDeclaracionContribucionParafiscal.getValTotalBolVendida());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValTotalDerCortesia()",attDeclaracionContribucionParafiscal.getValTotalDerCortesia());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValTotalDerPatrocinio()",attDeclaracionContribucionParafiscal.getValTotalDerPatrocinio());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValTotalCruces()",attDeclaracionContribucionParafiscal.getValTotalCruces());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValSubtotalRetenciones()",attDeclaracionContribucionParafiscal.getValSubtotalRetenciones());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValSubtotalDevoluciones()",attDeclaracionContribucionParafiscal.getValSubtotalDevoluciones());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValTipoDeclaracion()",attDeclaracionContribucionParafiscal.getValTipoDeclaracion());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getValFechaPresentacion()",attDeclaracionContribucionParafiscal.getValFechaPresentacion());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getIdeUsuarioCambio()",attDeclaracionContribucionParafiscal.getIdeUsuarioCambio());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscal.getFecCambio()",attDeclaracionContribucionParafiscal.getFecCambio());
		validarParametros("Crear",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvCrearDeclaracionContribucionParafiscal) {
			DCmdSrvCrearDeclaracionContribucionParafiscal copia = (DCmdSrvCrearDeclaracionContribucionParafiscal) comando;
			copia.toDeclaracionContribucionParafiscal = toDeclaracionContribucionParafiscal;
			copia.pkDeclaracionContribucionParafiscal = pkDeclaracionContribucionParafiscal;
			copia.attDeclaracionContribucionParafiscal = attDeclaracionContribucionParafiscal;
		}
	}
}
