package co.gov.dian.muisca.diligenciamientomasivo.acciones.juegosdesuerteyazar;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAccion;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.operacionaduanera.acciones.traficopostal.DCmdAccPresentar540;

public class DCmdAccPresentar320 extends DComandoAccion {

	protected Long nit;
	protected IDDocumento documento;
	protected Boolean tieneFirmas;
	protected Boolean presentacionExtemporanea;
	protected Boolean presentado;
	protected Boolean pasaValidaciones;
	protected long idDocumento = -1;
	protected int numRepeticion = -1;
	protected int formato = -1;
	protected int version = -1;
	protected int anno = -1;
	protected int periodo = -1;
	protected int tipoOperacion; 
	protected int codEstado;
	protected final static int INICIALIZAR_PANTALLA = 1;
	protected final static int INICIALIZAR_VISOR = 2;
	@Override
	protected void ejecutarComando() {
		
	}
	
	public void inicializar(Long nit, IDDocumento documento){
		this.documento = documento;
		this.nit = nit;
		this.tipoOperacion = INICIALIZAR_PANTALLA;
	}
	
	public void inicializar(long idDocumento,int numRepeticion, int formato,
			int version, int anno, int periodo,long nit,int codEstado){
		this.idDocumento = idDocumento;
		this.numRepeticion = numRepeticion;
		this.formato = formato;
		this.version = version;
		this.anno = anno;
		this.periodo = periodo;
		this.nit = nit;
		this.codEstado = codEstado;
		this.tipoOperacion = INICIALIZAR_VISOR;
	}

	@Override
	public void asignar(IDComando comando) {
		if (comando instanceof DCmdAccPresentar320) {
			DCmdAccPresentar320 copia = (DCmdAccPresentar320) comando;
			copia.nit = nit;
			copia.documento = documento;
			copia.idDocumento = idDocumento;
			copia.numRepeticion = numRepeticion;
			copia.formato = formato;
			copia.version = version;
			copia.anno = anno;
			copia.periodo = periodo;
			copia.tieneFirmas = tieneFirmas;
			copia.presentacionExtemporanea = presentacionExtemporanea;
			copia.presentado = presentado;
			copia.pasaValidaciones = pasaValidaciones;
			copia.codEstado = codEstado;
		}
	}

	@Override
	public Object clonar() {
		return new DCmdAccPresentar320();
	}

	@Override
	public String getDescripcion() {
		return ("Accion para presentar documento 320");
	}

	@Override
	public boolean isAuditable() {
		
		return false;
	}

	public boolean validar() throws DValidarExcepcion {
		if (tipoOperacion == INICIALIZAR_PANTALLA){
		if (nit == null){
			return false;
		}else if (documento == null){
			return false;
		}
		}else if (tipoOperacion == INICIALIZAR_VISOR){
			if (nit==null)
				return false;
			else if (idDocumento == -1)
				return false;
			if (numRepeticion == -1)
				return false;
			if (formato == -1)
				return false;
			if (version == -1)
				return false;
			if (anno == -1)
				return false;
			if (periodo == -1)
				return false;
		}
		return true;
	}

	public Boolean getTieneFirmas() {
		return tieneFirmas;
	}

	public void setTieneFirmas(Boolean tieneFirmas) {
		this.tieneFirmas = tieneFirmas;
	}

	public Boolean getPresentacionExtemporanea() {
		return presentacionExtemporanea;
	}

	public void setPresentacionExtemporanea(Boolean presentacionExtemporanea) {
		this.presentacionExtemporanea = presentacionExtemporanea;
	}

	public Boolean getPresentado() {
		return presentado;
	}

	public void setPresentado(Boolean presentado) {
		this.presentado = presentado;
	}

	public Boolean getPasaValidaciones() {
		return pasaValidaciones;
	}

	public void setPasaValidaciones(Boolean pasaValidaciones) {
		this.pasaValidaciones = pasaValidaciones;
	}
	
	public int getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(int codEstado) {
		this.codEstado = codEstado;
	}

}
