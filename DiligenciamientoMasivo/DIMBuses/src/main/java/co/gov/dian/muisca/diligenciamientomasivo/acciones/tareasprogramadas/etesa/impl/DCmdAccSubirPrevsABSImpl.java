package co.gov.dian.muisca.diligenciamientomasivo.acciones.tareasprogramadas.etesa.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.mensajes.IDParametro;
import co.gov.dian.muisca.arquitectura.interfaces.IDPolitica;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.politicas.DLocalizadorPolitica;
import co.gov.dian.muisca.arquitectura.web.buses.DAccesoSeguridad;
import co.gov.dian.muisca.arquitectura.web.buses.DContextoSeguridad;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.tareasprogramadas.etesa.DCmdAccSubirPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.buses.DBusServiciosDelegateDIMB;
import co.gov.dian.muisca.diligenciamientomasivo.constantes.tareasprogramadas.etesa.IDConstantesPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.etesa.DPrevalidadorETESATO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.tareasprogramadas.etesa.DCmdSrvSubirPrevsABS;
import co.gov.dian.muisca.diligenciamientomasivo.util.etesa.DOpenerETESA;


/**
 * Implementación de las operaciones que efectúa el comando de acción DCmdAccSubirPrevsABS
 * 
 * @author nfontechar
 *
 */
public class DCmdAccSubirPrevsABSImpl extends DCmdAccSubirPrevsABS {
	
	
	/**
	 * Atributo de tipo Logger para registrar evetualidades durante la ejecución del comando 
	 */
	private static final Logger log=Logger.getLogger(DCmdAccSubirPrevsABSImpl.class);

	
	/**
	 * Método que se ejecuta en ambiente no transaccional
	 * 
	 * @throws DExcepcion Si la ejecución del método falla por algún motivo
	 */
	protected void ejecutarComandoSinTransaccion() throws DExcepcion {
		try{
			subirPrevsABS();
			isOk=true;
			exito=true;
		}/*fin de try*/
		catch(DExcepcion exc){
			mensajeError=exc.getMessage();
			mensajeErrorDetallado=exc.getMensajeDetallado();
			isOk=false;
			exito=false;
		}/*fin de catch*/
	}/*fin de ejecutarComandoSinTransacción*/
	
	
	/**
	 * Método que se ejecuta en ambiente transaccional
	 */
	protected void ejecutarComando() {
		if(exito){
			log.info("--->>>> DCmdAccSubirPrevsABS: Ejecución exitosa");
		}/*fin de if*/
		else{
			log.info("--->>>> DCmdAccSubirPrevsABS: Ejecución con errores");
		}/*fin de else*/
		isOk=exito;
	}/*fin de ejecutarComando*/



	private DPrevalidadorETESATO armarPrev(String nit) throws DExcepcion{
		DLocalizadorPolitica localizador=new DLocalizadorPolitica();
		IDPolitica politica=localizador.getPolitica(IDConstantesPrevsABS.POLITICA);
		IDParametro nom=politica.getParametro(IDConstantesPrevsABS.PARAMETRO_NOMBRE);
		IDParametro anio=politica.getParametro(IDConstantesPrevsABS.PARAMETRO_ANIO);
		IDParametro ver=politica.getParametro(IDConstantesPrevsABS.PARAMETRO_VERSION);
		IDParametro ext=politica.getParametro(IDConstantesPrevsABS.PARAMETRO_EXTENSION);
		StringBuilder sbPrefijo=new StringBuilder((String) nom.getValor());
		sbPrefijo.append((String) anio.getValor());
		sbPrefijo.append("_v");
		sbPrefijo.append((String) ver.getValor());
		sbPrefijo.append("_");		
		DPrevalidadorETESATO prev=new DPrevalidadorETESATO();
		prev.setExtensionArchivo((String) ext.getValor());
		prev.setNumNIT(nit);
		prev.setPk(IDConstantesPrevsABS.PK);
		prev.setPrefijoArchivo(sbPrefijo.toString());
		return prev;
	}/*fin de armarPrev*/
	

	private void subirPrevsABS() throws DExcepcion{
		StringBuilder sb=new StringBuilder();
		sb.append(IDConstantesPrevsABS.RUTA_BASE).append(IDConstantesPrevsABS.ARCHIVO_BASE);
		DContextoSeguridad tmp=contextualizar();
		try{
			/*
			 * Cargando archivo base de NITs
			 */
			File archivoBase=new File(sb.toString());
			BufferedReader lectorBase=new BufferedReader(new FileReader(archivoBase));
			
			/*
			 * Leyendo archivo base de NITs, línea por línea
			 */
			while(lectorBase.ready()){
				String nit=lectorBase.readLine();
				sb=new StringBuilder();
				boolean existePrev=true;
				
				/*
				 * Cargando archivo prevalidador para el NIT seleccionado
				 */
				DPrevalidadorETESATO preETESA=armarPrev(nit);
				sb.append(IDConstantesPrevsABS.RUTA_BASE).append(preETESA.getNombreArchivo()).append(".").append(preETESA.getExtensionArchivo());
				File prev=new File(sb.toString());
				int longPrev=(int) prev.length();
				byte[] contenido=new byte[longPrev];
				FileInputStream fis=null;
				try{
					fis=new FileInputStream(prev);
					fis.read(contenido);
				}/*fin de try*/
				catch(FileNotFoundException exc){
					existePrev=false;
					log.error("--->>>> No se encontró archivo para el NIT "+nit+". Se continuará con el siguiente en la lista...");
				}/*fin de catch*/
				
				if(existePrev){ // Se verifica que el archivo existe en la ubicación física. Si no, continúa con el próximo en la lista
					/*
					 * Cargando el contenido al TO que contiene el prevalidador que se subirá a la Bandeja de Salida
					 */
					preETESA.setContenidoArchivo(contenido);
					/*
					 * Preparando servicio para subir el prevalidador. Una transacción por registro.
					 */
					DBusServiciosDelegateDIMB delegado=new DBusServiciosDelegateDIMB();
					delegado.setTransaccional(true);				
					delegado.setContextoSeguridad(tmp);
					DCmdSrvSubirPrevsABS subidor=null;				
					try {
						subidor=(DCmdSrvSubirPrevsABS) delegado.getComando("diligenciamientomasivo.tareasprogramadas.etesa.DCmdSrvSubirPrevsABS");
						subidor.inicializarSubirPrevalidador(preETESA);
						subidor.ejecutar();

						/*
						 * El prevalidador fue cargado exitosamente. Borrando el archivo del directorio base
						 */
						prev.delete();
						log.info("--->>>> "+preETESA.getNombreArchivo()+": Hecho!!");
					} catch (Exception e) {
						String detalle=e.getLocalizedMessage();
						log.error("--->>>> DCmdAccSubirPrevsABS: Hubo un error con el prevalidador "+preETESA.getNombreArchivo()+": "+detalle+". Revisar traza:");
						e.printStackTrace();
					}
				}/*fin de if*/
			}/*fin de while*/
		}/*fin de try*/
		catch(Exception exc){
			if(exc instanceof FileNotFoundException){
				log.error("--->>>> No se encontró el archivo "+IDConstantesPrevsABS.ARCHIVO_BASE+".\nDetalles:\n"+exc.getLocalizedMessage());
			}/*fin de if*/
			else{
				String msj=exc.getMessage();
				String det=exc.getLocalizedMessage();
				throw new DExcepcion(msj,det);
			}/*fin de else*/			
		}/*fin de catch*/		
	}/*fin de subirPrevsABS*/
		
	
	private DContextoSeguridad contextualizar() throws DExcepcion{
		DOpenerETESA helper=new DOpenerETESA();
		helper.reanudar();
		DAccesoSeguridad acceso=new DAccesoSeguridad();
		try {
			return acceso.autenticarUsuario(helper.getDato1(), helper.getDato2().getBytes("UTF8"),Integer.parseInt(helper.getDato3()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}/*fin de contextualizar*/
	
	

}/*fin de class*/
