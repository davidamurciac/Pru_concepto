package co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa;

import java.security.PrivateKey;
import java.security.PublicKey;

import co.gov.dian.muisca.arquitectura.general.to.IDTO;



/**
 * Objeto de transporte que contiene los datos de seguridad con el que se cargarán los prevalidadores iniciales ETESA
 * en la Bandeja de Salida 
 * 
 * @author nfontechar
 *
 */
public class DEncargadoTO implements IDTO {
	
	PrivateKey desmayada=null;
	PublicKey cualquiera=null;
	byte[] dato1=null;
	byte[] dato2=null;
	byte[] dato3=null;
	
		
	
	
	public PrivateKey getDesmayada() {
		return desmayada;
	}
	public void setDesmayada(PrivateKey desmayada) {
		this.desmayada = desmayada;
	}
	public PublicKey getCualquiera() {
		return cualquiera;
	}
	public void setCualquiera(PublicKey cualquiera) {
		this.cualquiera = cualquiera;
	}
	public byte[] getDato3() {
		return dato3;
	}
	public void setDato3(byte[] dato3) {
		this.dato3 = dato3;
	}
	public byte[] getDato1() {
		return dato1;
	}
	public void setDato1(byte[] dato1) {
		this.dato1 = dato1;
	}
	public byte[] getDato2() {
		return dato2;
	}
	public void setDato2(byte[] dato2) {
		this.dato2 = dato2;
	}
	

}/*fin de class*/
