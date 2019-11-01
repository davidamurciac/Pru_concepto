package co.gov.dian.muisca.diligenciamientomasivo.acciones.transferencias;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.entradasalida.general.to.documento.DDocumentoPKTO;
import java.util.Collection;
import co.gov.dian.muisca.arquitectura.general.to.bandejatareas.DTareaPKTO;

//entradasalida.transferencias.DCmdAccConsolPreciosTransIndv8

public class DCmdAccConsolPreciosTransIndv8 extends DComandoAccion {

    protected DDocumentoPKTO docGenerado = null;
    protected DDocumentoPKTO docSolicitud = null;
    protected transient DDocumentoPKTO documentoCargaPK;
    protected transient Collection documentosCargados;
    protected DTareaPKTO tareaPK = null;

    public DCmdAccConsolPreciosTransIndv8() {
    }

    public void inicializarConsolidar(DDocumentoPKTO docGenerado,
                                      DDocumentoPKTO docSolicitud,
                                      DTareaPKTO tareaPK) {

        this.docGenerado = docGenerado;
        this.docSolicitud = docSolicitud;
        this.tareaPK = tareaPK;
    }

    /**
     * M�todo llamado por el Bus, para ejecutar la copia de los valores
     * retornados por la ejecuci�n del comando en un entorno distribuido
     *
     * @param comando IDComando
     * @todo Implement this
     *   co.gov.dian.muisca.arquitectura.interfaces.IDComando method
     */
    public void asignar(IDComando comando) {
        if (comando instanceof DCmdAccConsolPreciosTransIndv8) {
            DCmdAccConsolPreciosTransIndv8 copia;
            copia = (DCmdAccConsolPreciosTransIndv8) comando;
            copia.docGenerado = docGenerado;
            copia.docSolicitud = docSolicitud;
            copia.documentoCargaPK = documentoCargaPK;
            copia.documentosCargados = documentosCargados;
            copia.tareaPK = tareaPK;
        }
    }

    protected void ejecutarComando() {
        throw new UnsupportedOperationException();
    }


    /**
     *
     * @return Object
     * @todo Implement this
     *   co.gov.dian.muisca.arquitectura.interfaces.IDClonable method
     */
    public Object clonar() {
        return new DCmdAccConsolPreciosTransIndv8();
    }



    /**
     * Retorna la descripci�n del comando
     *
     * @return String
     * @todo Implement this
     *   co.gov.dian.muisca.arquitectura.interfaces.IDComando method
     */
    public String getDescripcion() {
        return "comando de accion que toma un documento 120 y " +
                "llena algunas casillas con el doc 1124";
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
     * Previa a la ejecucion de un comando se deber�a ejecutar una validacion
     * de los par�metros inicializados
     *
     * @throws DValidarExcepcion
     * @return boolean
     * @todo Implement this
     *   co.gov.dian.muisca.arquitectura.interfaces.IDComando method
     */
    public boolean validar() throws DValidarExcepcion {

        if (docGenerado != null && (docGenerado.getIdeDocumento()
                                    != null && docGenerado.getNumRepeticion() != null)) {
            return true;
        }

        if(tareaPK == null) {
            return false;
        }

        return false;
    }
}
