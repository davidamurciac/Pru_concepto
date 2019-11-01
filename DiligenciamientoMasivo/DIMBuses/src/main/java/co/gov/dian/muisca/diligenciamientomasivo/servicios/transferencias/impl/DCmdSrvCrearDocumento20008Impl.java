/**
 *  Republica de Colombia
 * Copyright (c) 2007 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DCmdSrvCrearDocumento20008Impl.java, 2, 2/3/09 7:33:01 PM COT, ARMANDO PEREA MORA$
 */

package co.gov.dian.muisca.diligenciamientomasivo.servicios.transferencias.impl;

//~--- Paquetes Requeridos -

import co.gov.dian.muisca.arquitectura.general.excepcion.DExcepcion;
import co.gov.dian.muisca.arquitectura.util.DFechaUtils;
import co.gov.dian.muisca.entradasalida.documento.DDocumentoUtil;
import co.gov.dian.muisca.entradasalida.documento.DIdentificadorDoc;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;
import co.gov.dian.muisca.entradasalida.formatos.IDFormato;
import co.gov.dian.muisca.entradasalida.formatos.constantes.IDCasillasFormulario120v7;
import co.gov.dian.muisca.entradasalida.formatos.constantes.IDCasillasFormulario130v7;
import co.gov.dian.muisca.entradasalida.formatos.constantes.IDCasillasFormulario20008;
import co.gov.dian.muisca.entradasalida.general.helper.DCasillasHelper;
import co.gov.dian.muisca.entradasalida.servicios.DCmdSrvConsFormato;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvCrearDocumentoES;
import co.gov.dian.muisca.entradasalida.servicios.documentos.DCmdSrvMoverDocumentoES;
import co.gov.dian.muisca.diligenciamientomasivo.servicios.transferencias.DCmdSrvCrearDocumento20008;
import co.gov.dian.muisca.rut.general.to.DPersonaRutTO;
import co.gov.dian.muisca.rut.servicios.DCmdSrvConsPersonaRut;

//Paquetes requeridos
import java.text.SimpleDateFormat;

import java.util.Date;

//~--- Clases -

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de Acci�n para crear un Documento Tarea de
 * Presentaci�n de formulario para el Formato Precios de transferencia
 * Formato 20008</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: DIAN</p>
 *
 * @author Armando perea Mora
 * @version $Revision: 2$
 * <pre>
 * $Log[10]:
 *  2    V1.3       1.0.1.0     2/3/09 7:33:01 PM COT  ARMANDO PEREA MORA
 *       Precios de Transferencia
 *  1    EntradaSalida 1.0         11/7/07 2:40:27 PM COT ARMANDO PEREA MORA 
 * $
 * </pre>
 */
public class DCmdSrvCrearDocumento20008Impl extends DCmdSrvCrearDocumento20008 {
	//~--- Metodos -

	/**
	 * Realiza la creaci�n del documento
	 * @return DIdentificadorDoc
	 * @throws DExcepcion
	 */
	private DIdentificadorDoc crearDocumento() throws DExcepcion {
		DPersonaRutTO personaRemitente = getPersonaRemitente();

		IDDocumento documento = getIDDocumento();

		llenarCasillas(documento, personaRemitente);

		// CREAR EL DOCUMENTO
		DCmdSrvCrearDocumentoES cmdCrear =
			(DCmdSrvCrearDocumentoES) getServicio("entradasalida.documentos.DCmdSrvCrearDocumentoES");
		cmdCrear.inicializarSinId(documento, IDFormato.ENTRADA_DILIGENCIAMIENTO_ADMITIVO);
		cmdCrear.ejecutar();

		// MOVER EL DOCUMENTO A ES
		DIdentificadorDoc identificador = cmdCrear.getIdentificadorDoc();
		DCmdSrvMoverDocumentoES cmdMover =
			(DCmdSrvMoverDocumentoES) getServicio("entradasalida.documentos.DCmdSrvMoverDocumentoES");
		cmdMover.inicializar(identificador.getIdDocumento(), identificador.getNumRepeticion(),
			identificador.getIdFormato(), identificador.getVersion());
		cmdMover.ejecutar();
		return identificador;
	}

	/**
	 * Ejecuta el comando de acci�n
	 */
	protected void ejecutarComando() {
		try {
			DIdentificadorDoc identificador = crearDocumento();

			numeroDocumento = new Long(identificador.getIdDocumento());
			numeroRepeticion = new Integer(identificador.getNumRepeticion());
		}
		catch (Exception ex) {
			numeroDocumento = null;
			numeroRepeticion = null;
		}
	}

	/**
	 * Llena todas las casillas del documento
	 * @param documento IDDocumento
	 * @param personaRemitente DPersonaRutTO
	 */
	private void llenarCasillas(IDDocumento documento, DPersonaRutTO personaRemitente) {
		IDCasillasFormulario20008 ids = null;       // Alias constantes Formato 20008
		IDCasillasFormulario120v7 ids120 = null;    // Alias constantes Formato 120
		IDCasillasFormulario130v7 ids130 = null;    // Alias constantes Formato 130

		DCasillasHelper casillas = new DCasillasHelper(documento);

		// Cabecera
		casillas.setValorCasilla(ids.ANO, (new SimpleDateFormat("yyyy")).format(new Date()));

		// interesado
		casillas.setValorCasilla(ids.TIPO_DOC_INTERESADO, interesado.getUsuarioAtt().getIdeTipoIdent());
		casillas.setValorCasilla(ids.NUMERO_IDENT_INTERESADO, interesado.getUsuarioAtt().getValorIdent());
		casillas.setValorCasilla(ids.PRIMER_APELLIDO_INTERESADO, interesado.getUsuarioAtt().getPrimerAppellido());
		casillas.setValorCasilla(ids.SEGUNDO_APELLIDO_INTERESADO,
			interesado.getUsuarioAtt().getSegundoApellido());
		casillas.setValorCasilla(ids.PRIMER_NOMBRE_INTERESADO, interesado.getUsuarioAtt().getPrimerNombre());
		casillas.setValorCasilla(ids.OTROS_NOMBRES_INTERESADO, interesado.getUsuarioAtt().getOtrosNombres());

		// remitente
		if (personaRemitente != null) {
			casillas.setValorCasilla(ids.TIPO_DOC_REMITENTE, personaRemitente.getAtt().getIdeTipoDocumento());
			casillas.setValorCasilla(ids.NUMERO_IDENT_REMITENTE, personaRemitente.getAtt().getNumIdentVigente());
			casillas.setValorCasilla(ids.PRIMER_NOMBRE_REMITENTE, personaRemitente.getAtt().getNomPrimerNombre());
			casillas.setValorCasilla(ids.PRIMER_APELLIDO_REMITENTE,
				personaRemitente.getAtt().getNomPrimerApellido());
			casillas.setValorCasilla(ids.SEGUNDO_APELLIDO_REMITENTE,
				personaRemitente.getAtt().getNomSegundoApellido());
			casillas.setValorCasilla(ids.OTROS_NOMBRES_REMITENTE, personaRemitente.getAtt().getNomOtrosNombres());
			casillas.setValorCasilla(ids.RAZON_SOCIAL_REMITENTE, personaRemitente.getAtt().getNomRazonSocial());
		}
		else {
			casillas.setValorCasilla(ids.TIPO_DOC_REMITENTE, interesado.getUsuarioAtt().getIdeTipoIdent());
			casillas.setValorCasilla(ids.NUMERO_IDENT_REMITENTE, interesado.getUsuarioAtt().getValorIdent());
			casillas.setValorCasilla(ids.PRIMER_NOMBRE_REMITENTE, interesado.getUsuarioAtt().getPrimerNombre());
			casillas.setValorCasilla(ids.PRIMER_APELLIDO_REMITENTE,
				interesado.getUsuarioAtt().getSegundoApellido());
			casillas.setValorCasilla(ids.SEGUNDO_APELLIDO_REMITENTE, interesado.getUsuarioAtt().getPrimerNombre());
			casillas.setValorCasilla(ids.OTROS_NOMBRES_REMITENTE, interesado.getUsuarioAtt().getOtrosNombres());
			casillas.setValorCasilla(ids.RAZON_SOCIAL_REMITENTE,
				remitente.getUsuarioRolAtt().getNombreRazonSocialUsuario());
		}

		// Datos de la solicitud
		casillas.setValorCasilla(ids.NUMERO_SOLICITUD, solicitud.getSolicitudPK().getIdeSolicitud());
		casillas.setValorCasilla(ids.ESTADO_SOLICITUD, solicitud.getSolicitudAtt().getCodEstado());
		casillas.setValorCasilla(ids.FORMATO_SOLICITUD, solicitud.getSolicitudAtt().getIdeFormato());
		casillas.setValorCasilla(ids.VERSION_SOLICITUD, solicitud.getSolicitudAtt().getNumVersionFormato());
		casillas.setValorCasilla(ids.CONCEPTO_SOLICITUD, solicitud.getSolicitudAtt().getCodConcepto());
		casillas.setValorCasilla(
			ids.FECHA_SOLICITUD,
			new Long(
				DFechaUtils.getFechaHoraNegocio(new Date(solicitud.getSolicitudAtt().getFecSolicitud().getTime()))));
		casillas.setValorCasilla(ids.NUMERO_ARCHIVOS, solicitud.getSolicitudAtt().getNumTotalArchivos());
		casillas.setValorCasilla(ids.NUMERO_REGISTROS, solicitud.getSolicitudAtt().getNumTotalRegistros());

		// Datos del formulario a presentar
		DCasillasHelper casillasDocPresentar = new DCasillasHelper(documentoAPresentar);
		casillas.setValorCasilla(ids.NUMERO_PRESENTACION, new Long(documentoAPresentar.getId()));
		casillas.setValorCasilla(ids.FORMATO_PRESENTACION, new Integer(documentoAPresentar.getIdFormato()));
		casillas.setValorCasilla(ids.VERSION_PRESENTACION, new Integer(documentoAPresentar.getVersionFormato()));
		if (documentoAPresentar.getIdFormato() == 120) {
			casillas.setValorCasilla(ids.MONTO_OPERACIONES_INGRESO,
				casillasDocPresentar.obtenerValorCasilla(ids120.MONTO_INGRESO));
			casillas.setValorCasilla(ids.MONTO_OPERACIONES_EGRESO,
				casillasDocPresentar.obtenerValorCasilla(ids120.MONTO_EGRESO));
			casillas.setValorCasilla(ids.SALDO_FINAL_ACTIVO,
				casillasDocPresentar.obtenerValorCasilla(ids120.SALDO_FINAL_ACTIVO));
			casillas.setValorCasilla(ids.SALDO_FINAL_PASIVO,
				casillasDocPresentar.obtenerValorCasilla(ids120.SALDO_FINAL_PASIVO));
		}
		else if (documentoAPresentar.getIdFormato() == 130) {
			casillas.setValorCasilla(ids.MONTO_OPERACIONES_INGRESO,
				casillasDocPresentar.obtenerValorCasilla(ids130.MONTO_TOTAL_OPERACIONES_INGRESO));
			casillas.setValorCasilla(ids.MONTO_OPERACIONES_EGRESO,
				casillasDocPresentar.obtenerValorCasilla(ids130.MONTO_TOTAL_OPERACIONES_EGRESO));
			casillas.setValorCasilla(ids.SALDO_FINAL_ACTIVO,
				casillasDocPresentar.obtenerValorCasilla(ids130.SALDO_FINAL_ACTIVO));
			casillas.setValorCasilla(ids.SALDO_FINAL_PASIVO,
				casillasDocPresentar.obtenerValorCasilla(ids130.SALDO_FINAL_PASIVO));
		}

		// pie
		casillas.setValorCasilla(ids.FECHA_EXPEDICION, new Long(DFechaUtils.getFechaHoraNegocio(new Date())));
	}

	//~--- get methods -

	/**
	 * Obtiene el IDDocumento que es un documento en blanco generado por
	 * DDcocumentoUtil con el formato especifico
	 *
	 * @return IDDocumento
	 * @throws DExcepcion
	 */
	private IDDocumento getIDDocumento() throws DExcepcion {
		DCmdSrvConsFormato srvConsFormato = (DCmdSrvConsFormato) getServicio("entradasalida.DCmdSrvConsFormato");
		srvConsFormato.inicializar(FORMATO, VERSION);
		srvConsFormato.ejecutar();

		IDFormato formato = srvConsFormato.getFormatoPorDefecto();
		if (formato == null) {
			throw new DExcepcion("Formato no existe", "No fue posbile hallar" + "el formato con Id " + FORMATO);
		}

		IDDocumento documento = DDocumentoUtil.getDocumentoEnBlanco(formato);
		return documento;
	}

	/**
	 * Obtiene la persona RUT que remite el documento
	 *
	 * @return DPersonaRutTO
	 */
	private DPersonaRutTO getPersonaRemitente() throws DExcepcion {
		DPersonaRutTO persona = null;
		if ((remitente != null) && (remitente.getUsuarioRolAtt() != null) &&
			(remitente.getUsuarioRolAtt().getNumNitOrg() != null)) {
			DCmdSrvConsPersonaRut servConsPersona =
				(DCmdSrvConsPersonaRut) getServicio("rut.DCmdSrvConsPersonaRut");
			servConsPersona.inicializarConsultarPorNIT(remitente.getUsuarioRolAtt().getNumNitOrg().longValue());
			servConsPersona.ejecutar();
			persona = servConsPersona.getPersonaRut();
		}
		return persona;
	}
}
