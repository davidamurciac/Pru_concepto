package co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;
import co.gov.dian.muisca.entradasalida.general.to.formato.DFormatoPKTO;



/**
 * Objeto de transporte que representa un archivo prevalidador inicial que se cargará a la Bandeja de Salida
 * 
 * @author nfontechar
 *
 */
public class DPrevalidadorETESATO implements IDTO {
	
	/*
	 * ATRIBUTOS
	 */
	private String prefijoArchivo;
	private String extensionArchivo;
	private String numNIT;
	private DFormatoPKTO pk;
	private byte[] contenidoArchivo;
	
	
	/*
	 * CONSTRUCTORES
	 */
	public DPrevalidadorETESATO(String prefijoArchivo, String extensionArchivo, String numNIT, DFormatoPKTO pk, byte[] contenidoArchivo) {
		this.prefijoArchivo=prefijoArchivo;
		this.extensionArchivo = extensionArchivo;
		this.numNIT = numNIT;
		this.pk = pk;
		this.contenidoArchivo = contenidoArchivo;
	}
	
	public DPrevalidadorETESATO() {
		this.prefijoArchivo = null;
		this.extensionArchivo = null;
		this.numNIT = null;
		this.pk = null;
		this.contenidoArchivo = null;
	}

	
	
	/*
	 * GETTERS y SETTERS
	 */
	public String getNombreArchivo() {
		String nombreArchivo=null;
		StringBuilder sb=new StringBuilder();
		if(getPrefijoArchivo()!=null && !getPrefijoArchivo().equals("")){
			sb.append(getPrefijoArchivo());
		}/*fin de if*/	
		else{
			sb.append("NO_PREFIJO_");
		}/*fin de else*/
		if(getNumNIT()!=null && !getNumNIT().equals("")){
			sb.append(getNumNIT());
		}/*fin de if*/
		else{
			sb.append("NO_NIT");
		}/*fin de else*/
		nombreArchivo=sb.toString();
		return nombreArchivo;
	}	

	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	public String getNumNIT() {
		return numNIT;
	}

	public void setNumNIT(String numNIT) {
		this.numNIT = numNIT;
	}

	public DFormatoPKTO getPk() {
		return pk;
	}

	public void setPk(DFormatoPKTO pk) {
		this.pk = pk;
	}

	public byte[] getContenidoArchivo() {
		return contenidoArchivo;
	}

	public void setContenidoArchivo(byte[] contenidoArchivo) {
		this.contenidoArchivo = contenidoArchivo;
	}

	public String getPrefijoArchivo() {
		return prefijoArchivo;
	}

	public void setPrefijoArchivo(String prefijoArchivo) {
		this.prefijoArchivo = prefijoArchivo;
	}
	
	
	
	

}/*fin de class*/
