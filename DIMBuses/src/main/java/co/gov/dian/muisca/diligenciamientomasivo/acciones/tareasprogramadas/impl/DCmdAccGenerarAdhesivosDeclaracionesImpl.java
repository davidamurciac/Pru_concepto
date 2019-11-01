package co.gov.dian.muisca.diligenciamientomasivo.acciones.tareasprogramadas.impl;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.diligenciamiento.general.helper.DHelperPoliticasDil;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.tareasprogramadas.DCmdAccGenerarAdhesivosDeclaraciones;
import co.gov.dian.muisca.entradasalida.servicios.formatos.DCmdSrvGenerarAutoadhesivoFormato;

/**
 * <p>Title: Proyecto MUISCA </p>
 * <p>Description: Implementación de la clase para generar un listado de autoadhesivos </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: DIAN </p>
 * @author dmahechav
 * @version 1.0
 */
public class DCmdAccGenerarAdhesivosDeclaracionesImpl extends DCmdAccGenerarAdhesivosDeclaraciones {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5819949307316910366L;
	private static final int POLITICA_AUTOADHESIVOS = 130;
	private static final Logger logger =Logger.getLogger(DCmdAccGenerarAdhesivosDeclaracionesImpl.class);
	
	/**
	 * Método que se ejecuta en ambiente no transaccional
	 * @throws DExcepcion Si la ejecución del método falla por algún motivo
	 */
	protected void ejecutarComandoSinTransaccion() throws DExcepcion {
		try{
			generarAutoAdhesivo();
			isOk = true;
			exito = true;
		}
		catch(DExcepcion e){
			mensajeError = e.getMessage();
			mensajeErrorDetallado = e.getMensajeDetallado();
			isOk = false;
			exito = false;
		}
	}
	
	/**
	 * Método que se ejecuta en ambiente transaccional
	 */
	protected void ejecutarComando() {
		if(exito)
			logger.info("DCmdAccGenerarAdhesivosDeclaraciones: Ejecución exitosa");
		else
			logger.info("DCmdAccGenerarAdhesivosDeclaraciones: Ejecución con errores");
		isOk=exito;
	}
	
	private void generarAutoAdhesivo() throws DExcepcion{
		DHelperPoliticasDil helperPoliticaDil = new DHelperPoliticasDil(POLITICA_AUTOADHESIVOS);
		StringTokenizer paramNumAutoadhesivos = helperPoliticaDil.obtenerParametroPolitica("NUMERO_AUTOADHESIVOS", ",");
		Integer numAutoadhesivos = Integer.parseInt(paramNumAutoadhesivos.nextToken());
		
		for(int i=0;i<numAutoadhesivos;i++){
			DCmdSrvGenerarAutoadhesivoFormato srvGenerarAutoAdhesivo = (DCmdSrvGenerarAutoadhesivoFormato)
			getServicio("entradasalida.formatos.DCmdSrvGenerarAutoadhesivoFormato");
			srvGenerarAutoAdhesivo.inicializar();
			srvGenerarAutoAdhesivo.ejecutar();
			String autoAdhesivo = srvGenerarAutoAdhesivo.getNumAutoadhesivo();
			logger.info(autoAdhesivo);
		}
	}
}
