package co.gov.dian.muisca.diligenciamientomasivo.util.etesa;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import co.gov.dian.muisca.diligenciamientomasivo.constantes.tareasprogramadas.etesa.IDConstantesPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DEncargadoTO;



/**
 * Helper de obtención de datos de seguridad para la carga de prevalidadores iniciales ETESA a la Bandeja de Salida
 * 
 * @author nfontechar
 *
 */
public class DOpenerETESA {
	
	
	private String dato1;
	private String dato2;
	private String dato3;

	
	private String abrir(byte[] s,PrivateKey peca) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
	   Cipher c=Cipher.getInstance("RSA");
	   c.init(Cipher.DECRYPT_MODE, peca); 
	   byte[] descifrado=c.doFinal(s);
	   return new String(descifrado,"UTF8");	   
	}/*fin de abrir*/
	   
	  
	   
	public void reanudar(){
	   try {
		  ObjectInputStream ois=new ObjectInputStream(new FileInputStream((IDConstantesPrevsABS.RUTA_BASE+IDConstantesPrevsABS.ARCHIVO_ETESA)));
		  DEncargadoTO to=new DEncargadoTO();
		  to=(DEncargadoTO) ois.readObject();
			if(to!=null){
				try {
					dato1=abrir(to.getDato1(),to.getDesmayada());
					dato2=abrir(to.getDato2(),to.getDesmayada());
					dato3=abrir(to.getDato3(),to.getDesmayada());
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					e.printStackTrace();
				} catch (BadPaddingException e) {
					e.printStackTrace();
				}
			}/*fin de if*/
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	   }/*fin de recuperar*/



	public String getDato1() {
		return dato1;
	}

	public String getDato2() {
		return dato2;
	}

	public String getDato3() {
		return dato3;
	}
	
	
	
	
}/*fin de class*/
