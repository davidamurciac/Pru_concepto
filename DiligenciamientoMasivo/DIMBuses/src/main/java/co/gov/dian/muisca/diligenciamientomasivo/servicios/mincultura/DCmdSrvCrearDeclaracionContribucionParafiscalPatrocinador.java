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
 * <p>Descripcion: Comando de servicio utilizado para crear un objeto DeclaracionContribucionParafiscalPatrocinador.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvCrearDeclaracionContribucionParafiscalPatrocinador extends DComandoServicioInterno {
	private static final long serialVersionUID = 327145991L; 

	/** Objeto de transporte de DeclaracionContribucionParafiscalPatrocinador */
	protected DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador;
	/** Llave primaria de DeclaracionContribucionParafiscalPatrocinador */
	protected DDeclaracionContribucionParafiscalPatrocinadorPKTO pkDeclaracionContribucionParafiscalPatrocinador;
	/** Atributos de DeclaracionContribucionParafiscalPatrocinador */
	protected DDeclaracionContribucionParafiscalPatrocinadorAttTO attDeclaracionContribucionParafiscalPatrocinador;
	
	/** colección de objetos DDeclaracionContribucionParafiscalEventoTO */
	protected Collection<DDeclaracionContribucionParafiscalPatrocinadorTO> objetosDeclaracionContribucionParafiscalPatrocinador;
	
	protected final int CREAR = 1;
	protected final int CREAR_CON_COLECCION = 2;
	
	protected int tipoOperacion;

	/**
	 * Inicializa la creación de DeclaracionContribucionParafiscalPatrocinador.
	 * @param toDeclaracionContribucionParafiscalPatrocinador Objeto de Transporte de DeclaracionContribucionParafiscalPatrocinador
	 */
	public void inicializar(DDeclaracionContribucionParafiscalPatrocinadorTO toDeclaracionContribucionParafiscalPatrocinador) {
		this.toDeclaracionContribucionParafiscalPatrocinador = toDeclaracionContribucionParafiscalPatrocinador;
		if (toDeclaracionContribucionParafiscalPatrocinador != null) {
			pkDeclaracionContribucionParafiscalPatrocinador = this.toDeclaracionContribucionParafiscalPatrocinador.getPK();
			attDeclaracionContribucionParafiscalPatrocinador = this.toDeclaracionContribucionParafiscalPatrocinador.getAtt();
		}
	}
	
	/**
	 * Inicializa la creación de DDeclaracionContribucionParafiscalPatrocinadorTO con colección.
	 * @param tosDeclaracionContribucionParafiscalPatrocinadorTO Objeto de Transporte de DDeclaracionContribucionParafiscalPatrocinadorTO
	 */
	public void inicializar(Collection<DDeclaracionContribucionParafiscalPatrocinadorTO> tosDeclaracionContribucionParafiscalPatrocinador) {
		tipoOperacion = CREAR_CON_COLECCION;
		this.objetosDeclaracionContribucionParafiscalPatrocinador = tosDeclaracionContribucionParafiscalPatrocinador;		
	}

	/**
	 * Ejecuta el comando de servicio.
	 * @throws DExcepcion Si ocurre algún error al realizar la
	 * la creación de DeclaracionContribucionParafiscalPatrocinador
	 */
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdSrvCrearDeclaracionContribucionParafiscalPatrocinador();
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
		return "Permite crear un objeto DeclaracionContribucionParafiscalPatrocinador";
	}

	/**
	 * Método para validar los parámetros inicializados, invocado
	 * previamente a la ejecucióndel comando.
	 * @return true si los parámetros son válidos; false de lo contrario
	 * @throws DValidarExcepcion Si los parámetros no son válidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros = new HashMap<String, Object>();
		if(tipoOperacion == CREAR) {
		parametros.put(this.getClass().getName()+":validar:toDeclaracionContribucionParafiscalPatrocinador",toDeclaracionContribucionParafiscalPatrocinador);
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalPatrocinador",attDeclaracionContribucionParafiscalPatrocinador);
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalPatrocinador",pkDeclaracionContribucionParafiscalPatrocinador);
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
		} else if(tipoOperacion == CREAR_CON_COLECCION) {
			parametros.put(this.getClass().getName()+":validar:objetosDeclaracionContribucionParafiscalPatrocinador",objetosDeclaracionContribucionParafiscalPatrocinador);
		}
		validarParametros("Crear",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como parámetro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvCrearDeclaracionContribucionParafiscalPatrocinador) {
			DCmdSrvCrearDeclaracionContribucionParafiscalPatrocinador copia = (DCmdSrvCrearDeclaracionContribucionParafiscalPatrocinador) comando;
			copia.toDeclaracionContribucionParafiscalPatrocinador = toDeclaracionContribucionParafiscalPatrocinador;
			copia.pkDeclaracionContribucionParafiscalPatrocinador = pkDeclaracionContribucionParafiscalPatrocinador;
			copia.attDeclaracionContribucionParafiscalPatrocinador = attDeclaracionContribucionParafiscalPatrocinador;
			copia.tipoOperacion = tipoOperacion;
			copia.objetosDeclaracionContribucionParafiscalPatrocinador = objetosDeclaracionContribucionParafiscalPatrocinador;		
		}
	}
}
