/**
 *  Republica de Colombia
 * Copyright (c) 2008 Direcciï¿½n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: 
 */

package co.gov.dian.muisca.diligenciamientomasivo.acciones.documentos.impl;

//~--- Paquetes Requeridos -

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.diligenciamiento.acciones.documentos.DCmdAccDiligenciarDocPresentado;
import co.gov.dian.muisca.diligenciamientomasivo.acciones.documentos.DCmdAccPresentar120;
import co.gov.dian.muisca.entradasalida.acciones.exogena.DCmdAccActDocGenExogena;
import co.gov.dian.muisca.entradasalida.acciones.exogena.DCmdAccConsDocGenExogena;
import co.gov.dian.muisca.entradasalida.documento.DDocumentoUtil;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.documento.IDGrupoDoc;
import co.gov.dian.muisca.entradasalida.documento.IDOcurrencia;
import co.gov.dian.muisca.entradasalida.formatos.IDFormato;
import co.gov.dian.muisca.entradasalida.formatos.IDModosDiligenciamiento;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.*;
import co.gov.dian.muisca.diligenciamientomasivo.general.preciostransferencia.DHelperExpedienteGeneracionDeclPreciosTransf;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoPKTO;
import co.gov.dian.muisca.cargamasiva.general.to.procesamiento.DSolicitudIngresoTO;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaAttTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaPKTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DDocGenExogenaTO;
import co.gov.dian.muisca.entradasalida.general.to.exogena.DSolicitudExogenaTO;
import co.gov.dian.muisca.entradasalida.servicios.DCmdSrvConsFormato;
import co.gov.dian.muisca.cargamasiva.servicios.procesamiento.DCmdSrvConsSolicitudIngreso;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvConsIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.servicios.exogena.DCmdSrvConsLstSolicitudExogena;

import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Iterator;

//~--- Clases -

/**
 * Class description
 *
 *
 * @version    Enter version here..., 09/02/04
 * @author     Enter your name here...    
 */
public class DCmdAccPresentar120Impl extends DCmdAccPresentar120 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8257238100900702078L;
	//~--- Campos Estaticos -

	/* Atributos */
	protected static Logger LOGGER = Logger.getLogger(DCmdAccPresentar120Impl.class);
	private static int IDE_FORMATO_1125 = 1125;
	private static int IDE_FORMATO_120 = 120;
	private static int IDE_FORMATO_130 = 130;

	//~--- Campos -

	private DHelperExpedienteGeneracionDeclPreciosTransf manejadorExpedientes = null;

	//~--- Metodos -

	private void actualizarDocGenExogenaTO(DDocGenExogenaTO docGenExogenaTo) throws DExcepcion {
		DCmdAccActDocGenExogena dCmdAccActDocGenExogena =
			(DCmdAccActDocGenExogena) getAccion("entradasalida.exogena.DCmdAccActDocGenExogena");
		dCmdAccActDocGenExogena.inicializar(docGenExogenaTo);
		dCmdAccActDocGenExogena.ejecutar();
	}

	public void ejecutarComando() {
		try {

			DIdentificadorDoc identPresentado = validarDocumento();
			if (identPresentado != null) {
				String mensajeFr = ((identPresentado.getIdModoNegocio() ==
					IDModosDiligenciamiento.MODO_FRACCION_INICIAL) || (identPresentado.getIdModoNegocio() ==
						IDModosDiligenciamiento.MODO_FRACCION_CORRECCION)) ? " inicial fracción de año " : " inicial ";
				String mensaje = "Ya existe una declaración " + mensajeFr +
					" presentada, por favor consulte la declaración con número " + identPresentado.getIdDocumento() +
					". La presente declaración se debe diligenciar como corrección";
				throw new DExcepcion("ERROR Presentando el Documento. ", mensaje);
			}

			DCmdAccDiligenciarDocPresentado accDiligenciarDocPresentado =
				obtenerAccDiligenciarDocPresentado(idDocumento, numRepeticion, formato, version, anno, periodo,
					nitContribuyente);
			documentoFirmaDIAN = accDiligenciarDocPresentado.getDocumentoFirmaDIAN();
			estadoDoc = accDiligenciarDocPresentado.getEstadoDoc();
			tieneFirmasCompletas = accDiligenciarDocPresentado.getTieneFirmasCompletas();
			esPresentado = accDiligenciarDocPresentado.getEsPresentado();
			pasaValidaciones = accDiligenciarDocPresentado.getPasaValidaciones();

			if (esPresentado.booleanValue()) {
				DDocGenExogenaPKTO pk = new DDocGenExogenaPKTO(new Long(idDocumento), new Integer(numRepeticion));
				DDocGenExogenaTO docGenExogenaTo = obtenerDocGenExogenaTO(pk);
				if (docGenExogenaTo != null) {
					DDocGenExogenaAttTO docGenExogenaAtt = docGenExogenaTo.getAtt();
					docGenExogenaAtt.setCodEstado(new Byte("" + IDDocumento.IDE_ESTADO_PRESENTADO));
					docGenExogenaTo.setAtt(docGenExogenaAtt);
					actualizarDocGenExogenaTO(docGenExogenaTo);
				}

				if (ideDocCarga == 0) {
					Collection solicitudesExogena = obtenerSolicitudesExogenaTO(new Long(idDocumento),
						new Integer(numRepeticion));

					if ((solicitudesExogena != null) &&!solicitudesExogena.isEmpty()) {
						Iterator it = solicitudesExogena.iterator();
						if (formato == IDE_FORMATO_120) {

							// Buscar el formato 1125 porque es posible que ya se haya
							// asociado el 1124. La solicitud asociada con el formato 1124
							// no esta relacionada en el expediente
							while (it.hasNext()) {
								DSolicitudExogenaTO solicitudExogena = (DSolicitudExogenaTO) it.next();

								DSolicitudIngresoTO solicitudIngreso =
									obtenerSolicitudIngresoTO(solicitudExogena.getPK().getIdeSolicitudExogena());
								if (solicitudIngreso != null) {
									if (solicitudIngreso.getSolicitudAtt().getIdeFormato().intValue() == IDE_FORMATO_1125) {
										ideDocCarga = solicitudIngreso.getSolicitudPK().getIdeSolicitud().longValue();
										break;
									}
								}
							}
						}
						else if (formato == IDE_FORMATO_130) {
							DSolicitudExogenaTO solicitudExogena = (DSolicitudExogenaTO) it.next();
							ideDocCarga = solicitudExogena.getPK().getIdeSolicitudExogena().longValue();
						}
					}
				}

				if (ideDocCarga == 0) {
					throw new DExcepcion("Error",
						"No fue posible encontrar la " + "Solicitud de Ingreso del formato 1125 para el documento " +
						idDocumento + " Rep: " + numRepeticion);
				}

				DHelperExpedienteGeneracionDeclPreciosTransf manejador = getManejadorExpedientes(formato, version);
				if (manejador == null) {
					throw new DExcepcion("Error",
						"No fue posible encontrar el " + "manejador de Expediente para el formato " + formato +
						" y version " + version);
				}
				manejador.procesarPresentacionDocumento(new Long(ideDocCarga), new Long(idDocumento),
					new Integer(formato));
			}
			isOk = true;

		}
		catch (DExcepcion ex) {
			isOk = false;
			esPresentado = Boolean.FALSE;
			this.mensajeError = ex.getMessage();
			this.mensajeErrorDetallado = ex.getMensajeDetallado();
		}
	}

	/* MODELO */
	private DCmdAccDiligenciarDocPresentado obtenerAccDiligenciarDocPresentado(long idDocumento,
		int numRepeticion, int formato, int version, int anno, int periodo,
		long nitContribuyente) throws DExcepcion {
		DCmdAccDiligenciarDocPresentado result = (DCmdAccDiligenciarDocPresentado) getAccion(
			"diligenciamiento.documentos.DCmdAccDiligenciarDocPresentado");
		result.inicializarDiligenciamiento(idDocumento, numRepeticion, formato, version, anno, periodo,
			nitContribuyente);
		result.ejecutar();
		return result;
	}

	private DDocGenExogenaTO obtenerDocGenExogenaTO(DDocGenExogenaPKTO pkDocGenExogena) throws DExcepcion {
		DCmdAccConsDocGenExogena accCons =
			(DCmdAccConsDocGenExogena) getAccion("entradasalida.exogena.DCmdAccConsDocGenExogena");
		accCons.inicializar(pkDocGenExogena);
		accCons.ejecutar();
		return accCons.getDocGenExogena();
	}

	private IDFormato obtenerIDFormato(int idFormato, int version) throws DExcepcion {
		DCmdSrvConsFormato srvCons = (DCmdSrvConsFormato) getServicio("entradasalida.DCmdSrvConsFormato");
		srvCons.inicializar(idFormato, version);
		srvCons.ejecutar();
		return srvCons.getFormatoPorDefecto();
	}

	private DIdentificadorDoc obtenerIdentificadorDoc(long idDocumento, int numRepeticion,
		boolean isDefinitivo, int ideFormato) throws DExcepcion {
		DCmdSrvConsIdentificadorDoc srvCons =
			(DCmdSrvConsIdentificadorDoc) getServicio("entradasalida.documentos.DCmdSrvConsIdentificadorDoc");
		srvCons.inicializarConsPorId(idDocumento, numRepeticion, isDefinitivo, ideFormato);
		srvCons.ejecutar();
		return srvCons.getIdentificadorDoc();
	}

	private DIdentificadorDoc obtenerIdentificadorDoc(IDDocumento docConsulta, boolean isDefinitivo,
		DIdentificadorDoc identEYS) throws DExcepcion {
		DCmdSrvConsIdentificadorDoc srvCons =
			(DCmdSrvConsIdentificadorDoc) getServicio("entradasalida.documentos.DCmdSrvConsIdentificadorDoc");
		srvCons.inicializarConsPorObj(docConsulta, isDefinitivo);
		srvCons.ejecutar();
		return srvCons.getIdentificadorPorModo(identEYS.getIdModoNegocio());
	}

	private DSolicitudIngresoTO obtenerSolicitudIngresoTO(Long ideSolicitud) throws DExcepcion {
		DSolicitudIngresoTO result = null;
		if (ideSolicitud != null) {
			DCmdSrvConsSolicitudIngreso srvConsSolIngreso =
				(DCmdSrvConsSolicitudIngreso) getServicio("cargamasiva.procesamiento.DCmdSrvConsSolicitudIngreso");
			srvConsSolIngreso.inicializar(new DSolicitudIngresoPKTO(ideSolicitud));
			srvConsSolIngreso.ejecutar();
			result = srvConsSolIngreso.getSolicitudIngreso();
		}
		return result;
	}

	private Collection obtenerSolicitudesExogenaTO(Long ideDocumento, Integer numRepeticion) throws DExcepcion {
		Collection result = null;

		if ((ideDocumento != null) && (numRepeticion != null)) {
			DCmdSrvConsLstSolicitudExogena srvCons =
				(DCmdSrvConsLstSolicitudExogena) getServicio("entradasalida.exogena.DCmdSrvConsLstSolicitudExogena");
			srvCons.inicializar(new DDocumentoPKTO(ideDocumento, numRepeticion));
			srvCons.ejecutar();
			result = srvCons.getColeccionSolicitudExogena();
		}
		return result;
	}

	/* Metodos Privados */

	public DIdentificadorDoc validarDocumento() throws DExcepcion {
		DIdentificadorDoc identMUIS = null;
		DIdentificadorDoc identEYS = obtenerIdentificadorDoc(idDocumento, numRepeticion, false, formato);
		if ((identEYS != null) && (identEYS.getIdModoNegocio() != IDModosDiligenciamiento.MODO_CORRECCION) &&
			(identEYS.getIdModoNegocio() != IDModosDiligenciamiento.MODO_FRACCION_CORRECCION)) {
			IDFormato formatoBl = obtenerIDFormato(formato, version);
			IDDocumento _docEjemplo = DDocumentoUtil.getDocumentoEnBlanco(formatoBl);
			IDGrupoDoc grupo = _docEjemplo.getGrupos().getGrupo(1);
			IDOcurrencia ocurrencia = grupo.getOcurrencia(1);

			if (anno > 0) {
				ocurrencia.getValorCasilla(1).setValor(new Integer(anno));
			}

			if (nitContribuyente > 0) {
				ocurrencia.getValorCasilla(5).setValor(new Long(nitContribuyente));
			}

			identMUIS = obtenerIdentificadorDoc(_docEjemplo, true, identEYS);
		}
		return identMUIS;
	}

	//~--- get methods -

	private DHelperExpedienteGeneracionDeclPreciosTransf getManejadorExpedientes(int formato, int version) {
		if (manejadorExpedientes == null) {
			if (formato == 130) {
				if (version == 6) {
					manejadorExpedientes = new DHelperExpedienteGeneracion130v6(this);
				}
				else if (version == 7) {
					manejadorExpedientes = new DHelperExpedienteGeneracion130v7(this);
				}
				else if (version == 8) {
					manejadorExpedientes = new DHelperExpedienteGeneracion130v8(this);
				}
				else if (version == 9) {
					manejadorExpedientes = new DHelperExpedienteGeneracion130v9(this);
				}
				else if (version == 1) {
					manejadorExpedientes = new DHelperExpedienteGeneracion130v1(this);
				}
				else if (version == 2) {
					manejadorExpedientes = new DHelperExpedienteGeneracion130v2(this);
				}
			}
			else if (formato == 120) {
				if (version == 6) {
					manejadorExpedientes = new DHelperExpedienteGeneracion120v6(this);
				}
				else if (version == 7) {
					manejadorExpedientes = new DHelperExpedienteGeneracion120v7(this);
				}
				else if (version == 8) {
					manejadorExpedientes = new DHelperExpedienteGeneracion120v8(this);
				}
				else if (version == 9) {
					manejadorExpedientes = new DHelperExpedienteGeneracion120v9(this);
				}
				else if (version == 1) {
					manejadorExpedientes = new DHelperExpedienteGeneracion120v1(this);
				}
				else if (version == 2) {
					manejadorExpedientes = new DHelperExpedienteGeneracion120v2(this);
				}
				else if (version == 3) {
					manejadorExpedientes = new DHelperExpedienteGeneracion120v3(this);
				}
				else if (version == 4) {
					manejadorExpedientes = new DHelperExpedienteGeneracion120v4(this);
				}
			}
		}
		return manejadorExpedientes;
	}
}
