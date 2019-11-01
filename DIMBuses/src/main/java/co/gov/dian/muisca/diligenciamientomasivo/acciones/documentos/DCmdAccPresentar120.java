/**
 *  Republica de Colombia
 * Copyright (c) 2004 Dirección de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DCmdAccPresentar120.java, 1, 3/08/06 04:11:03 PM COT, MVEGAV,$
 */
package co.gov.dian.muisca.diligenciamientomasivo.acciones.documentos;

import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComandoAccion;


public class DCmdAccPresentar120 extends DComandoAccion {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6908902992385340724L;


	public DCmdAccPresentar120() {
    }

    protected String documentoFirmaDIAN;
    protected long idDocumento;
    protected int numRepeticion;
    protected int formato;
    protected int version;
    protected int anno;
    protected int periodo;
    protected long nitContribuyente;
    protected long idPersonaRut;
    protected int estadoDoc;
    protected Boolean esPresentado;
    protected long ideDocCarga;
    protected int numRepeticionDocCarga;
    protected int codEstado;
    protected Boolean tieneFirmasCompletas = new Boolean(false);
    protected Boolean pasaValidaciones = new Boolean(true);


    public void inicializarDiligenciamiento(long idDocumento,
                                            int numRepeticion, int formato,
                                            int version,
                                            int anno, int periodo,
                                            long nitContribuyente,
                                            long ideDocCarga,
                                            int numRepeticionDocCarga,
                                            int codEstado) {
        this.idDocumento = idDocumento;
        this.numRepeticion = numRepeticion;
        this.formato = formato;
        this.version = version;
        this.anno = anno;
        this.periodo = periodo;
        this.nitContribuyente = nitContribuyente;
        idPersonaRut = getContexto().getContextoSeguridad().getIdePersona().
                       longValue();
        this.esPresentado = Boolean.FALSE;
        this.ideDocCarga=ideDocCarga;
        this.numRepeticionDocCarga=numRepeticionDocCarga;
        this.codEstado=codEstado;
    }

    /**
     *
     * @param comando IDComando
     * @todo Implement this
     *   co.gov.dian.muisca.arquitectura.interfaces.IDComando method
     */
    public void asignar(IDComando comando) {
        if (comando instanceof DCmdAccPresentar120) {
            DCmdAccPresentar120 copia = (
                    DCmdAccPresentar120) comando;

            copia.idDocumento = idDocumento;
            copia.numRepeticion = numRepeticion;
            copia.formato = formato;
            copia.version = version;
            copia.anno = anno;
            copia.periodo = periodo;
            copia.nitContribuyente = nitContribuyente;
            copia.documentoFirmaDIAN = documentoFirmaDIAN;
            copia.idPersonaRut = idPersonaRut;
            copia.estadoDoc = estadoDoc;
            copia.esPresentado = esPresentado;
            copia.ideDocCarga=ideDocCarga;
            copia.numRepeticionDocCarga=numRepeticionDocCarga;
            copia.codEstado=codEstado;
            copia.tieneFirmasCompletas=tieneFirmasCompletas;



        }
    }

    /**
     *
     * @return Object
     * @todo Implement this
     *   co.gov.dian.muisca.arquitectura.interfaces.IDClonable method
     */
    public Object clonar() {
        return new DCmdAccPresentar120();
    }

    /**
     *
     * @throws DExcepcion
     * @todo Implement this
     *   co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.DComando
     *   method
     */
    protected void ejecutarComando() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return String
     * @todo Implement this
     *   co.gov.dian.muisca.arquitectura.interfaces.IDComando method
     */
    public String getDescripcion() {
        return
                "Comando de Acción de diligenciamiento de Documento presentado DIAN";
    }

    /**
     *
     * @return boolean
     * @todo Implement this
     *   co.gov.dian.muisca.arquitectura.interfaces.IDAuditable method
     */
    public boolean isAuditable() {
        return false;
    }

    /**
     *
     * @throws DValidarExcepcion
     * @return boolean
     * @todo Implement this
     *   co.gov.dian.muisca.arquitectura.interfaces.IDComando method
     */
    public boolean validar() throws DValidarExcepcion {
        return (idDocumento > 0 && numRepeticion > 0 &&
                formato > 0 && version > 0 && anno > 0 &&
                nitContribuyente > 0);
    }

    public String getDocumentoFirmaDIAN() {

        return documentoFirmaDIAN;
    }

    public int getEstadoDoc() {
        return estadoDoc;
    }

    public void setEsPresentado(Boolean esPresentado) {
        this.esPresentado = esPresentado;
    }

    public Boolean getEsPresentado() {
        return esPresentado;
    }

    public Boolean getTieneFirmasCompletas() {
        return tieneFirmasCompletas;
    }

    public Boolean getPasaValidaciones() {
        return pasaValidaciones;
    }
}
