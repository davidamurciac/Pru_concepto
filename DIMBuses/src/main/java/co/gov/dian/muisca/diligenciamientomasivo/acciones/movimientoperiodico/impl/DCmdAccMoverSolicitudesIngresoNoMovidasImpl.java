package co.gov.dian.muisca.diligenciamientomasivo.acciones.movimientoperiodico.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.general.mensajes.IDParametro;
import co.gov.dian.muisca.arquitectura.general.to.IDTO;
import co.gov.dian.muisca.arquitectura.interfaces.IDPolitica;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.politicas.DLocalizadorPolitica;
import co.gov.dian.muisca.cargamasiva.servicios.DCmdSrvMoverDocsAuditoriaRegistros;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.movimientoperiodico.DCmdAccMoverSolicitudesIngresoNoMovidas;
import co.gov.dian.muisca.diligenciamientomasivo.buses.DBusServiciosDelegateDIMBTxNueva;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico.DSolicitudIngresoNoMovidaPKTO;
import co.gov.dian.muisca.diligenciamientomasivo.general.to.movimientoperiodico.DSolicitudIngresoNoMovidaTO;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.movimientoperiodico.DCmdSrvSolicitudesIngresoNoMovidas;
import co.gov.dian.muisca.entradasalida.general.to.documento.DMarcaDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DMarcaDocumentoTO;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvElimMarcasDocumento;

public class DCmdAccMoverSolicitudesIngresoNoMovidasImpl extends DCmdAccMoverSolicitudesIngresoNoMovidas {


	/*
	 * CONSTANTES
	 */
	private static final Logger logger = Logger.getLogger(DCmdAccMoverSolicitudesIngresoNoMovidasImpl.class);
	private static final int MAX_REGISTROS=2000;
	private static final int POLITICA=89;
	private static final String[] PARAMETROS={"NOTIFICAR_INICIO_FIN","NOTIFICAR_DETALLE","DESTINO","MAX_REGS_PROCESAR","MOVER"};
	private StringBuilder sbIniFin=null;
	private StringBuilder sbDetalle=null;
	private DConfigMovimientoTO config=null;
	private Date dInicio=null;
	private Date dFin=null;
	private String mensaje=null;
	private int fallos;




	/*
	 * MÉTODOS PRIVADOS
	 */






	/*public void notificarMe(String mensaje, String asunto) throws DExcepcion {
		try {
			DBusServiciosDelegateARQ txNueva=new DBusServiciosDelegateARQ();
			txNueva.setContextoSeguridad(getContexto().getContextoSeguridad());
			txNueva.setTransaccional(true);
			SMTPMail smptMail = new SMTPMail(
					"Solicitudes de Ingreso No Movidas", "", asunto);
			smptMail.addDestinatario("", "jariasp@dian.gov.co");
			smptMail.setMensajeTexto(mensaje);
			DCmdSrvEnvioMail envioMail = (DCmdSrvEnvioMail) txNueva.getComando("arquitectura.mensajes.DCmdSrvEnvioMail");
			envioMail.inicializar(smptMail);
			envioMail.ejecutar();
		} catch (DExcepcion ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}/* fin de notificarMe */



	private List<DSolicitudIngresoNoMovidaTO> obtenerSolicitudesNoMovidas(DSolicitudIngresoNoMovidaTO aux){
		List<DSolicitudIngresoNoMovidaTO> lista=null;
		try {			
			DCmdSrvSolicitudesIngresoNoMovidas srvDocs=(DCmdSrvSolicitudesIngresoNoMovidas) getServicio("diligenciamientomasivo.movimientoperiodico.DCmdSrvSolicitudesIngresoNoMovidas");
			srvDocs.inicializarConsultarSolicitudesNoMovidas(aux);
			srvDocs.ejecutar();
			lista=srvDocs.getSolicitudesNoMovidas();			
		}/*fin de try*/
		catch (DExcepcion exc) {
			mensaje="\n\n----->  Se produjo un error obteniendo las solicitudes no movidas:\nCausa: "+exc.getCause()+"\nDetalles: "+exc.getLocalizedMessage();
			logger.info(mensaje);
			registrarEnDetalle("Hubo un error obteniendo las solicitudes no movidas:\nCausa: "+exc.getCause()+"\nDetalles: "+exc.getLocalizedMessage());
		}/*fin de catch*/
		return lista;
	}/*fin de obtenerSolicitudesNoMovidas*/

	private int obtenerConteoPendientes(DSolicitudIngresoNoMovidaTO aux){
		int pendientes=-1;
		DSolicitudIngresoNoMovidaTO miTO=new DSolicitudIngresoNoMovidaTO();
		try{
			DCmdSrvSolicitudesIngresoNoMovidas srvCuenta=(DCmdSrvSolicitudesIngresoNoMovidas) getServicio("diligenciamientomasivo.movimientoperiodico.DCmdSrvSolicitudesIngresoNoMovidas");
			srvCuenta.inicializarConsultarRegistrosPendientes(aux);
			srvCuenta.ejecutar();
			miTO=srvCuenta.getTo();
			if(miTO!=null && miTO.getRegistrosPendientes()!=null){
				pendientes=miTO.getRegistrosPendientes().intValue();
			}/*fin de if*/
		}/*fin de try*/
		catch (DExcepcion exc) {
			mensaje="\n\n----->  Se produjo un error contando elementos solicitados:\nCausa: "+exc.getCause()+"\nDetalles: "+exc.getLocalizedMessage();
			logger.info(mensaje);
			registrarEnDetalle("Hubo un error contando elementos solicitados:\nCausa: "+exc.getCause()+"\nDetalles: "+exc.getLocalizedMessage());			
		}/*fin de catch*/
		return pendientes;		
	}/*fin de obtenerConteoPendientes*/

	private List<DMarcaDocumentoTO> obtenerLogMarcasDoc(DSolicitudIngresoNoMovidaTO aux){
		List<DMarcaDocumentoTO> lista=null;
		try {
			DCmdSrvSolicitudesIngresoNoMovidas srvMarcas=(DCmdSrvSolicitudesIngresoNoMovidas) getServicio("diligenciamientomasivo.movimientoperiodico.DCmdSrvSolicitudesIngresoNoMovidas");
			srvMarcas.inicializarConsultarLogMarcasDocSolictud(aux);
			srvMarcas.ejecutar();
			lista=srvMarcas.getMarcasLogDoc();
		}/*fin de try*/ 
		catch (DExcepcion exc) {
			mensaje="\n\n----->  Se produjo un error obteniendo log de marcas:\nCausa: "+exc.getCause()+"\nDetalles: "+exc.getLocalizedMessage();
			logger.info(mensaje);
			registrarEnDetalle("Hubo un error obteniendo log de marcas:\nCausa: "+exc.getCause()+"\nDetalles: "+exc.getLocalizedMessage());			
		}/*fin de catch*/
		return lista; 
	}/*fin de obtenerLogMarcasDoc*/

	private void eliminarMarcaDoc(DMarcaDocumentoPKTO marcaPK){
		try{
			DBusServiciosDelegateDIMBTxNueva delTxNueva=new DBusServiciosDelegateDIMBTxNueva();
			delTxNueva.setContextoSeguridad(getContexto().getContextoSeguridad());
			delTxNueva.setTransaccional(true);
			DCmdSrvElimMarcasDocumento eliminador=(DCmdSrvElimMarcasDocumento) delTxNueva.getComando("entradasalida.documentos.DCmdSrvElimMarcasDocumento");
			eliminador.inicializar(marcaPK);
			eliminador.ejecutar();
		}/*fin de try*/
		catch (Exception exc) {
			mensaje="\n\n----->  Se produjo un error procesando la marca "+marcaPK.getIdeMarcaFormato()+" en el documento "+marcaPK.getIdeDocumento()+":\nCausa: "+exc.getCause()+"\nDetalles: "+exc.getLocalizedMessage()+"\n----- SIGUIENTE -----";
			fallos++;
			logger.info(mensaje);
			registrarEnDetalle("Hubo un error procesando la marca "+marcaPK.getIdeMarcaFormato()+" en el documento "+marcaPK.getIdeDocumento()+":\nCausa: "+exc.getCause()+"\nDetalles: "+exc.getLocalizedMessage()+"\n----- SIGUIENTE -----");
		}/*fin de catch*/		
	}/*fin de eliminarMarcaDoc*/

	private void moverDocsSolicitud(Long ideSolicitud){
		try{
			DBusServiciosDelegateDIMBTxNueva delTxNew=new DBusServiciosDelegateDIMBTxNueva();
			delTxNew.setContextoSeguridad(getContexto().getContextoSeguridad());
			delTxNew.setTransaccional(true);
			DCmdSrvMoverDocsAuditoriaRegistros movedor=(DCmdSrvMoverDocsAuditoriaRegistros) delTxNew.getComando("cargamasiva.DCmdSrvMoverDocsAuditoriaRegistros");
			movedor.inicializar(ideSolicitud);
			movedor.ejecutar();
			mensaje="\n\n----->  Solicitud "+ideSolicitud+" movida satisfactoriamente.\n----- SIGUIENTE -----";
			logger.info(mensaje);
			registrarEnDetalle("Solicitud "+ideSolicitud+" movida satisfactoriamente.\n----- SIGUIENTE -----");
		}/*fin de try*/
		catch(Exception exc){
			mensaje="\n\n----->  Se produjo un error procesando los documentos de la solicitud "+ideSolicitud+":\nCausa: "+exc.getCause()+"\nDetalles: "+exc.getLocalizedMessage()+"\n----- SIGUIENTE -----";
			fallos++;
			logger.info(mensaje);
			registrarEnDetalle("Hubo un error procesando los documentos de la solicitud "+ideSolicitud+":\nCausa: "+exc.getCause()+"\nDetalles: "+exc.getLocalizedMessage()+"\n----- SIGUIENTE -----");
		}/*fin de catch*/
	}/*fin de moverDocsSolicitud*/


	private String obtenerDuracionProceso(Date dInicial,Date dFinal){
		long inicio=dInicial.getTime();
		long fin=dFinal.getTime();
		long resta=fin-inicio;
		long enSegs=resta/1000;
		long[] tiempo=new long[4];
		for(int i=0;i<4;i++){
			tiempo[i]=0;
		}/*fin de for*/
		if(enSegs>0){
			tiempo[0]=enSegs/86400;
			tiempo[1]=(enSegs % 86400)/3600;
			tiempo[2]=((enSegs % 86400) % 3600)/60;
			tiempo[3]=(((enSegs % 86400) % 3600) % 60);
		}/*fin de if*/
		String duracion="";
		if(tiempo[0]>0){
			duracion+=tiempo[0]==1 ? (tiempo[0]+" dia ") : (tiempo[0]+" dias, "); 
		}/*fin de if*/
		if(tiempo[1]>0){
			duracion+=tiempo[1]==1 ? (tiempo[1]+" hora ") : (tiempo[1]+" horas, "); 
		}/*fin de if*/
		if(tiempo[2]>0){
			duracion+=tiempo[2]==1 ? (tiempo[2]+" minuto ") : (tiempo[2]+" minutos, "); 
		}/*fin de if*/
		if(tiempo[3]>0){
			duracion+=tiempo[3]==1 ? (tiempo[3]+" segundo ") : (tiempo[3]+" segundos. "); 
		}/*fin de if*/
		return duracion;
	}/*fin de obtenerDuracionProceso*/


	private DConfigMovimientoTO obtenerConfigMovimiento(){
		DLocalizadorPolitica localizador=new DLocalizadorPolitica();
		IDPolitica politica=null;
		try {
			politica=localizador.getPolitica(POLITICA);
			if(politica!=null){
				Boolean inicioFin=new Boolean(false);
				Boolean detalle=new Boolean(false);
				String destino=null;
				Integer maxRegs=new Integer(0);
				Boolean mover=new Boolean(false);
				IDParametro parametro1=politica.getParametro(PARAMETROS[0]);
				IDParametro parametro2=politica.getParametro(PARAMETROS[1]);
				IDParametro parametro3=politica.getParametro(PARAMETROS[2]);
				IDParametro parametro4=politica.getParametro(PARAMETROS[3]);
				IDParametro parametro5=politica.getParametro(PARAMETROS[4]);
				if(parametro1!=null && parametro1.getValor()!=null && ((String) parametro1.getValor()).trim().toUpperCase().equals("S")){
					inicioFin=new Boolean(true);
				}/*fin de if*/
				if(parametro2!=null && parametro2.getValor()!=null && ((String) parametro2.getValor()).trim().toUpperCase().equals("S")){
					detalle=new Boolean(true);
				}/*fin de if*/
				if(parametro5!=null && parametro5.getValor()!=null && ((String) parametro5.getValor()).trim().toUpperCase().equals("S")){
					mover=new Boolean(true);
				}/*fin de if*/
				if(parametro3!=null && parametro3.getValor()!=null){
					destino=((String) parametro3.getValor()).trim();
				}/*fin de if*/
				if(parametro4!=null && parametro3.getValor()!=null){
					maxRegs=new Integer(((String) parametro4.getValor()).trim());
				}/*fin de if*/
				config=new DConfigMovimientoTO(mover,inicioFin,detalle,destino,maxRegs);
			}/*fin de if*/
			else{
				config=new DConfigMovimientoTO();
			}/*fin de else*/
		} catch (DExcepcion e) {
			mensaje="\n\n-----> Hubo un error obteniendo la configuración del proceso:\nCausa: "+e.getCause()+"\nDetalle: "+e.getLocalizedMessage();
			logger.info(mensaje);
		}
		return config;
	}/*fin de obtenerConfigMovimiento*/

	private String obtenerTiempo(Date fechaHora){
		String tiempo="N/D";
		try {
			DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
			String tFechaHora=df.format(fechaHora);
			fechaHora=df.parse(tFechaHora);
			df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			tiempo=df.format(fechaHora);		
		} catch (ParseException e) {
			mensaje="\n\n-----> Hubo un error obteniendo el momento de un evento:\nCausa: "+e.getCause()+"\nDetalle: "+e.getLocalizedMessage();
			logger.info(mensaje);
		}
		return tiempo;
	}/*fin de obtenerTiempo*/

	private void registrarEnDetalle(String detalle){
		if(config!=null){
			if(config.getNotificarDetalle().booleanValue()){
				if(sbDetalle==null){
					sbDetalle=new StringBuilder("*** RESUMEN PROCESO ***\n\n");
				}/*fin de if*/
				sbDetalle.append(obtenerTiempo(new Date(System.currentTimeMillis()))).append(": ").append(detalle).append("\n");
			}/*fin de if*/
		}/*fin de if*/
	}/*fin de registrarEnDetalle*/



	private void notificarInicioFin(String inicioFin, boolean inicio) throws DExcepcion{
		if(config!=null){
			if(config.getNotificarInicioFin().booleanValue()){
				if(config.getDestino()!=null && !config.getDestino().equals("")){
					sbIniFin=new StringBuilder("");
					String etapa="";			
					sbIniFin.append(inicioFin).append("\n");
					String asunto=inicio ? "Inicio de Proceso" : "Finalización de Proceso";				
				}/*fin de if*/
				else{
					mensaje="\n\n-----> Se configuró \"NOTIFICAR INICIO Y FIN DEL PROCESO\" pero no se especificó una dirección de correo para tal fin.";
					logger.info(mensaje);
				}/*fin de else*/
			}/*fin de if*/
		}/*fin de if*/
	}/*fin de registrarInicioFin*/






	/*
	 * MÉTODO PRINCIPAL
	 */

	protected void ejecutarComandoSinTransaccion() throws DExcepcion{
		config=obtenerConfigMovimiento();
		if(config!=null){
			if(config.getMover().booleanValue()){
				if(config.getMaxRegsProcesar().intValue()<=MAX_REGISTROS){
					try{
						dInicio=new Date(System.currentTimeMillis());
						mensaje="";
						mensaje="\n\n\n\n********** INICIO MOVIMIENTO DE SOLICITUDES NO MOVIDAS FORMATOS 1082,1084,1124,1125,1126 **********\n\n-----> Proceso iniciado en "+obtenerTiempo(dInicio);
						logger.info(mensaje);
						notificarInicioFin("Se inició el proceso de movimiento de solicitudes en "+obtenerTiempo(dInicio), true);
						DSolicitudIngresoNoMovidaTO aux=new DSolicitudIngresoNoMovidaTO();
						aux.setConteo("M");
						int marcasPend=obtenerConteoPendientes(aux);
						aux.setConteo("S");
						int docsPend=obtenerConteoPendientes(aux);
						mensaje="\n\n-----> Se hallaron "+(marcasPend>=0 ? marcasPend : "N/D")+" marcas correspondientes a "+(docsPend>=0 ? docsPend : "N/D")+" documentos en las solicitudes por mover.\n\n-----> Procesando grupo de "+config.getMaxRegsProcesar()+" marcas...";
						logger.info(mensaje);
						registrarEnDetalle("Hay "+(marcasPend>=0 ? marcasPend : "N/D")+" marcas en "+(docsPend>=0 ? docsPend : "N/D")+" documentos.");
						registrarEnDetalle("Procesando "+config.getMaxRegsProcesar()+" marcas...");
						fallos=0;
						aux.setInicio(new Integer(1));
						aux.setFin(config.getMaxRegsProcesar());
						List<DMarcaDocumentoTO> marcasLog=obtenerLogMarcasDoc(aux);
						if(marcasLog!=null){
							for(DMarcaDocumentoTO marca:marcasLog){
								eliminarMarcaDoc(marca.getPK());
							}/*fin de for*/
							mensaje="\n\n-----> Proceso de marcas terminado. Ocurrieron "+fallos+" fallos.\n\n-----> Procesando grupo de "+config.getMaxRegsProcesar()+" documentos...";
							logger.info(mensaje);
							registrarEnDetalle("Proceso de marcas terminado, con "+fallos+" fallos.");
							registrarEnDetalle("Procesando "+config.getMaxRegsProcesar()+" documentos...");
						}/*fin de if*/
						else{
							mensaje="\n\n-----> No se encontraron marcas para procesar.\n\n-----> Procesando grupo de "+config.getMaxRegsProcesar()+" documentos...";
							logger.info(mensaje);
							registrarEnDetalle("No se encontraron marcas para procesar.");
							registrarEnDetalle("Procesando "+config.getMaxRegsProcesar()+" documentos...");
						}/*fin de else*/
						fallos=0;
						solicitudesNoMovidas=obtenerSolicitudesNoMovidas(aux);
						if(solicitudesNoMovidas!=null){
							for(DSolicitudIngresoNoMovidaTO solicitud:solicitudesNoMovidas){
								if(solicitud!=null){
									DSolicitudIngresoNoMovidaPKTO pk=solicitud.getPk();
									if(pk!=null){
										Long ideSolicitud=pk.getIdeSolicitud();
										if(ideSolicitud!=null){
											moverDocsSolicitud(ideSolicitud);
										}/*fin de if*/
										else{
											mensaje="\n\n-----> Se encontró una solicitud sin IDE en la lista.";
											fallos++;
											logger.info(mensaje);
											registrarEnDetalle("Solicitud sin IDE en la lista.");
										}/*fin de else*/
									}/*fin de if*/
									else{
										mensaje="\n\n-----> Se encontró una solicitud sin PK en la lista.";
										fallos++;
										logger.info(mensaje);
										registrarEnDetalle("Solicitud sin PK en la lista.");
									}/*fin de else*/
								}/*fin de if*/
								else{
									mensaje="\n\n-----> Se encontró una solicitud NULA en la lista.";
									fallos++;
									logger.info(mensaje);
									registrarEnDetalle("Solicitud NULA en la lista.");
								}/*fin de else*/
							}/*fin de for*/
							mensaje="\n\n-----> Proceso de documentos terminado. Ocurrieron "+fallos+" fallos.";
							logger.info(mensaje);
							registrarEnDetalle("Proceso de documentos terminado, con "+fallos+" fallos.");						
						}/*fin de if*/
						else{
							mensaje="\n\n-----> No se encontraron documentos para procesar.";
							logger.info(mensaje);
							registrarEnDetalle("No se encontraron documentos para procesar.");
						}/*fin de else*/
						long fin=System.currentTimeMillis();
						dFin=new Date(fin);
						String duracion=obtenerDuracionProceso(dInicio, dFin);
						mensaje="\n\n----->  Proceso completado satisfactoriamente en "+obtenerTiempo(dFin)+", tras "+duracion+"\n\n********** FIN MOVIMIENTO DE SOLICITUDES NO MOVIDAS FORMATOS 1082,1084,1124,1125,1126 **********\n\n\n\n";
						logger.info(mensaje);
						notificarInicioFin("Se completó el proceso de movimiento de solicitudes en "+obtenerTiempo(dFin)+"\nResultado: SIN ERRORES.\nDURACIÓN: "+duracion, false);
						isOk=true;
					}/*fin de try*/
					catch(Exception exc){
						long fin=System.currentTimeMillis();
						dFin=new Date(fin);
						String duracion=obtenerDuracionProceso(dInicio, dFin);
						mensaje="\n\n----->  Proceso completado con errores en "+obtenerTiempo(dFin)+", tras "+duracion+"\n\n********** FIN MOVIMIENTO DE SOLICITUDES NO MOVIDAS FORMATOS 1082,1084,1124,1125,1126 **********\n\n\n\n";
						logger.info(mensaje);
						notificarInicioFin("Se completó el proceso de movimiento de solicitudes en "+obtenerTiempo(dFin)+"\nResultado: CON ERRORES.\nDURACIÓN: "+duracion, false);
						mensajeError = exc.getMessage();
						mensajeErrorDetallado = exc.getLocalizedMessage();
						isOk = false;
					}/*fin de catch*/
				}/*fin de if*/
				else{
					mensaje="\n\n\n\n********** MOVIMIENTO DE SOLICITUDES NO MOVIDAS FORMATOS 1082,1084,1124,1125,1126: El Proceso no se ejecuta por configuración: El máximo de registros a procesar ("+config.getMaxRegsProcesar()+") excede el máximo de registros permitidos para proceso (2000).";
					logger.info(mensaje);
					if(config.getNotificarDetalle().booleanValue() || config.getNotificarInicioFin().booleanValue()){
						DConfigMovimientoTO copia=new DConfigMovimientoTO(config.getMover(), new Boolean(true), config.getNotificarDetalle(), config.getDestino(), config.getMaxRegsProcesar());
						config=copia;
						notificarInicioFin("El Proceso no se ejecuta por configuración: El máximo de registros a procesar ("+config.getMaxRegsProcesar()+") excede el máximo de registros permitidos para proceso (2000).", false);
					}/*fin de if*/
				}/*fin de else*/
			}/*fin de if*/
			else{
				mensaje="\n\n\n\n********** MOVIMIENTO DE SOLICITUDES NO MOVIDAS FORMATOS 1082,1084,1124,1125,1126: El Proceso no se ejecuta por configuración: Está indicado \"NO MOVER\".";
				logger.info(mensaje);
				if(config.getNotificarDetalle().booleanValue() || config.getNotificarInicioFin().booleanValue()){
					DConfigMovimientoTO copia=new DConfigMovimientoTO(config.getMover(), new Boolean(true), config.getNotificarDetalle(), config.getDestino(), config.getMaxRegsProcesar());
					config=copia;
					notificarInicioFin("El Proceso no se ejecuta por configuración: Está indicado \"NO MOVER\".", false);
				}/*fin de if*/
			}/*fin de else*/
		}/*fin de if*/
		else{
			mensaje="\n\n\n\n********** MOVIMIENTO DE SOLICITUDES NO MOVIDAS FORMATOS 1082,1084,1124,1125,1126: El Proceso no se ejecuta por configuración: No Se Encontró Configuración.";
			logger.info(mensaje);
		}/*fin de else*/
	}/*fin de ejecutarComandoSinTransaccion*/


	protected void ejecutarComando() {
		logger.info("\n\n-----> DCmdAccMoverSolicitudesIngresoNoMovidas: Se ejecutó el comando en "+obtenerTiempo(new Date(System.currentTimeMillis()))+"\n\n\n\n");
		isOk=true;
	}/*fin de ejecutarComando*/


}/*fin de class*/



class DConfigMovimientoTO implements IDTO{

	/*
	 * ATRIBUTOS
	 */
	private Boolean mover;
	private Boolean notificarInicioFin;
	private Boolean notificarDetalle;
	private String destino;
	private Integer maxRegsProcesar;


	/*
	 * CONSTRUCTOR
	 */

	public DConfigMovimientoTO() {
		this.mover = new Boolean(false);
		this.notificarInicioFin = new Boolean(false);
		this.notificarDetalle = new Boolean(false);
		this.destino = null;
		this.maxRegsProcesar = new Integer(0);
	}

	public DConfigMovimientoTO(Boolean mover, Boolean notificarInicioFin, Boolean notificarDetalle, String destino, Integer maxRegsProcesar) {
		this.mover = mover;
		this.notificarInicioFin = notificarInicioFin;
		this.notificarDetalle = notificarDetalle;
		this.destino = destino;
		this.maxRegsProcesar = maxRegsProcesar;
	}


	/*
	 * GETTERS
	 */
	public Boolean getMover() {
		return mover;
	}
	public Boolean getNotificarInicioFin() {
		return notificarInicioFin;
	}
	public Boolean getNotificarDetalle() {
		return notificarDetalle;
	}
	public String getDestino() {
		return destino;
	}
	public Integer getMaxRegsProcesar() {
		return maxRegsProcesar;
	}


}/*fin de inner class*/