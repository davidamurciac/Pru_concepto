/**
 * Republica de Colombia
 * Copyright (c) 2004 Direcci�n de Impuestos y Aduanas Nacionales.
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
 * <p>Descripcion: Comando de servicio utilizado para crear un objeto DeclaracionContribucionParafiscalEvento.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: DIAN</p>
 *
 * @author Plugin Middlegen-MUISCA
 * @version $Revision:$
 * <pre>
 * $Log[10]:$
 * </pre>
 */
public class DCmdSrvCrearDeclaracionContribucionParafiscalEvento extends DComandoServicioInterno {
	private static final long serialVersionUID = 1439419886L; 

	/** Objeto de transporte de DeclaracionContribucionParafiscalEvento */
	protected DDeclaracionContribucionParafiscalEventoTO toDeclaracionContribucionParafiscalEvento;
	/** Llave primaria de DeclaracionContribucionParafiscalEvento */
	protected DDeclaracionContribucionParafiscalEventoPKTO pkDeclaracionContribucionParafiscalEvento;
	/** Atributos de DeclaracionContribucionParafiscalEvento */
	protected DDeclaracionContribucionParafiscalEventoAttTO attDeclaracionContribucionParafiscalEvento;
	
	/** colecci�n de objetos DDeclaracionContribucionParafiscalEventoTO */
	protected Collection<DDeclaracionContribucionParafiscalEventoTO> objetosDeclaracionContribucionParafiscalEvento;
	
	protected final int CREAR = 1;
	protected final int CREAR_CON_COLECCION = 2;
	
	protected int tipoOperacion;
	
	/**
	 * Inicializa la creaci�n de DeclaracionContribucionParafiscalEvento.
	 * @param toDeclaracionContribucionParafiscalEvento Objeto de Transporte de DeclaracionContribucionParafiscalEvento
	 */
	public void inicializar(DDeclaracionContribucionParafiscalEventoTO toDeclaracionContribucionParafiscalEvento) {
		tipoOperacion = CREAR;
		this.toDeclaracionContribucionParafiscalEvento = toDeclaracionContribucionParafiscalEvento;
		if (toDeclaracionContribucionParafiscalEvento != null) {
			pkDeclaracionContribucionParafiscalEvento = this.toDeclaracionContribucionParafiscalEvento.getPK();
			attDeclaracionContribucionParafiscalEvento = this.toDeclaracionContribucionParafiscalEvento.getAtt();
		}
	}
	
	/**
	 * Inicializa la creaci�n de DeclaracionContribucionParafiscalEvento con colecci�n.
	 * @param toDeclaracionContribucionParafiscalEvento Objeto de Transporte de DeclaracionContribucionParafiscalEvento
	 */
	public void inicializar(Collection<DDeclaracionContribucionParafiscalEventoTO> tosDeclaracionContribucionParafiscalEvento) {
		tipoOperacion = CREAR_CON_COLECCION;
		this.objetosDeclaracionContribucionParafiscalEvento = tosDeclaracionContribucionParafiscalEvento;		
	}


	/**
	 * Ejecuta el comando de servicio.
	 * @throws DExcepcion Si ocurre alg�n error al realizar la
	 * la creaci�n de DeclaracionContribucionParafiscalEvento
	 */
	protected void ejecutarComando() throws DExcepcion {
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene una copia (clon) del comando.
	 * @return Un Object con la copia del comando
	 */
	public Object clonar() {
		return new DCmdSrvCrearDeclaracionContribucionParafiscalEvento();
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
		return "Permite crear un objeto DeclaracionContribucionParafiscalEvento";
	}

	/**
	 * M�todo para validar los par�metros inicializados, invocado
	 * previamente a la ejecuci�ndel comando.
	 * @return true si los par�metros son v�lidos; false de lo contrario
	 * @throws DValidarExcepcion Si los par�metros no son v�lidos
	 */
	public boolean validar() throws DValidarExcepcion {
		Map<String, Object> parametros = new HashMap<String, Object>();
		if(tipoOperacion == CREAR) {
		parametros.put(this.getClass().getName()+":validar:toDeclaracionContribucionParafiscalEvento",toDeclaracionContribucionParafiscalEvento);
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento",attDeclaracionContribucionParafiscalEvento);
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento",pkDeclaracionContribucionParafiscalEvento);
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf()",pkDeclaracionContribucionParafiscalEvento.getIdeDocumentoContParaf());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf()",pkDeclaracionContribucionParafiscalEvento.getNumRepeticionDocContParf());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia()",pkDeclaracionContribucionParafiscalEvento.getIdeOcurrencia());
		parametros.put(this.getClass().getName()+":validar:pkDeclaracionContribucionParafiscalEvento.getIdeItem()",pkDeclaracionContribucionParafiscalEvento.getIdeItem());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValNumEvento()",attDeclaracionContribucionParafiscalEvento.getValNumEvento());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getIdeMunicipioEvento()",attDeclaracionContribucionParafiscalEvento.getIdeMunicipioEvento());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getIdeDepartamentoEvento()",attDeclaracionContribucionParafiscalEvento.getIdeDepartamentoEvento());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValNombLugarEvento()",attDeclaracionContribucionParafiscalEvento.getValNombLugarEvento());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getFecRealizacionEvento()",attDeclaracionContribucionParafiscalEvento.getFecRealizacionEvento());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumBoletasVendidas()",attDeclaracionContribucionParafiscalEvento.getNumBoletasVendidas());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBoletasVendidas()",attDeclaracionContribucionParafiscalEvento.getValBoletasVendidas());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumBolVendidasInf3uvt()",attDeclaracionContribucionParafiscalEvento.getNumBolVendidasInf3uvt());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBolVendidasInf3uvt()",attDeclaracionContribucionParafiscalEvento.getValBolVendidasInf3uvt());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumBolVendidasSup3uvt()",attDeclaracionContribucionParafiscalEvento.getNumBolVendidasSup3uvt());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBolVendidasSup3uvt()",attDeclaracionContribucionParafiscalEvento.getValBolVendidasSup3uvt());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumCortesiasEntregadas()",attDeclaracionContribucionParafiscalEvento.getNumCortesiasEntregadas());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValCortesiasEntregadas()",attDeclaracionContribucionParafiscalEvento.getValCortesiasEntregadas());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumCortEntregInf3uvt()",attDeclaracionContribucionParafiscalEvento.getNumCortEntregInf3uvt());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValCortEntregInf3uvt()",attDeclaracionContribucionParafiscalEvento.getValCortEntregInf3uvt());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumCortEntregSup3uvt()",attDeclaracionContribucionParafiscalEvento.getNumCortEntregSup3uvt());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValCortEntregSup3uvt()",attDeclaracionContribucionParafiscalEvento.getValCortEntregSup3uvt());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumBoletasPatrocinio()",attDeclaracionContribucionParafiscalEvento.getNumBoletasPatrocinio());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBoletasPatrocinio()",attDeclaracionContribucionParafiscalEvento.getValBoletasPatrocinio());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumBolPatrocInf3uvt()",attDeclaracionContribucionParafiscalEvento.getNumBolPatrocInf3uvt());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBolPatrocInf3uvt()",attDeclaracionContribucionParafiscalEvento.getValBolPatrocInf3uvt());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getNumBolPatrocSup3uvt()",attDeclaracionContribucionParafiscalEvento.getNumBolPatrocSup3uvt());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBolPatrocSup3uvt()",attDeclaracionContribucionParafiscalEvento.getValBolPatrocSup3uvt());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValBaseGravableCp()",attDeclaracionContribucionParafiscalEvento.getValBaseGravableCp());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValContribParafiscal()",attDeclaracionContribucionParafiscalEvento.getValContribParafiscal());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getValRetencionCp()",attDeclaracionContribucionParafiscalEvento.getValRetencionCp());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getIdeUsuarioCambio()",attDeclaracionContribucionParafiscalEvento.getIdeUsuarioCambio());
		parametros.put(this.getClass().getName()+":validar:attDeclaracionContribucionParafiscalEvento.getFecCambio()",attDeclaracionContribucionParafiscalEvento.getFecCambio());
		} else if(tipoOperacion == CREAR_CON_COLECCION) {
			parametros.put(this.getClass().getName()+":validar:objetosDeclaracionContribucionParafiscalEvento",objetosDeclaracionContribucionParafiscalEvento);
		}
		validarParametros("Crear",parametros);
		return true;
	}

	/**
	 * Para copiar el contenido del comando actual al comando enviado como par�metro.
	 * @param comando Comando sobre el cual copiar
	 */
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdSrvCrearDeclaracionContribucionParafiscalEvento) {
			DCmdSrvCrearDeclaracionContribucionParafiscalEvento copia = (DCmdSrvCrearDeclaracionContribucionParafiscalEvento) comando;
			copia.toDeclaracionContribucionParafiscalEvento = toDeclaracionContribucionParafiscalEvento;
			copia.pkDeclaracionContribucionParafiscalEvento = pkDeclaracionContribucionParafiscalEvento;
			copia.attDeclaracionContribucionParafiscalEvento = attDeclaracionContribucionParafiscalEvento;
			copia.objetosDeclaracionContribucionParafiscalEvento = objetosDeclaracionContribucionParafiscalEvento;
			copia.tipoOperacion = tipoOperacion;
		}
	}
}
