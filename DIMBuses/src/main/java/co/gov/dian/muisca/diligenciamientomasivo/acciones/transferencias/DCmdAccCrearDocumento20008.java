/**
 *  Republica de Colombia
 * Copyright (c) 2007 Direcci�n de Impuestos y Aduanas Nacionales.
 * (DIAN - www.dian.gov.co).  Todos los Derechos reservados.
 *
 * $Header: DCmdAccCrearDocumento20008.java, 1, 11/7/07 2:40:27 PM COT, ARMANDO PEREA MORA$
 */

package co.gov.dian.muisca.diligenciamientomasivo.acciones.transferencias;

//Paquetes requeridos
import co.gov.dian.muisca.arquitectura.general.excepcion.DValidarExcepcion;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioRolTO;
import co.gov.dian.muisca.arquitectura.general.to.seguridad.DUsuarioTO;
import co.gov.dian.muisca.arquitectura.interfaces.IDComando;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.
        DComandoAccion;
import co.gov.dian.muisca.entradasalida.general.to.cargamasiva.
        DSolicitudIngresoTO;
import co.gov.dian.muisca.entradasalida.documento.IDDocumento;

/**
 * <p>Titulo: Proyecto MUISCA</p>
 * <p>Descripcion: Comando de Acci�n para crear un Documento Tarea de
 * Presentaci�n de formulario para el Formato Precios de transferencia
 * Formato 20008</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: DIAN</p>
 *
 * @author Armando perea Mora
 * @version $Revision: 1$
 * <pre>
 * $Log[10]:
 *  1    EntradaSalida 1.0         11/7/07 2:40:27 PM COT ARMANDO PEREA MORA 
 * $
 * </pre>
 */
public class DCmdAccCrearDocumento20008 extends DComandoAccion {

    private static final long serialVersionUID = 1L;

    protected static final int FORMATO = 20008;

    protected static final int VERSION = 7;

    //Solicitud de Carga Masiva en la cual se debe basar el documento
    protected DSolicitudIngresoTO solicitud;

    //Persona interesada en realizar la tarea
    protected DUsuarioTO interesado;

    //Persona que cuyos actos origina la tarea
    protected DUsuarioRolTO remitente;

    //Documento de Precios de transferencia apresentar
    protected IDDocumento documentoAPresentar;

    protected Long numeroDocumento;

    protected Integer numeroRepeticion;

    public void inicializar(DSolicitudIngresoTO solicitud,
            DUsuarioTO interesado, DUsuarioRolTO remitente,
            IDDocumento documentoAPresentar) {
        this.solicitud = solicitud;
        this.interesado = interesado;
        this.remitente = remitente;
        this.documentoAPresentar = documentoAPresentar;
    }

    protected void ejecutarComando() {
        throw new UnsupportedOperationException();
    }

    public void asignar(IDComando comando) {
        if (comando instanceof DCmdAccCrearDocumento20008) {
            DCmdAccCrearDocumento20008 copia =
                    (DCmdAccCrearDocumento20008) comando;
            copia.solicitud = this.solicitud;
            copia.remitente = this.remitente;
            copia.interesado = this.interesado;
            copia.documentoAPresentar = this.documentoAPresentar;
            copia.numeroDocumento = this.numeroDocumento;
            copia.numeroRepeticion = this.numeroRepeticion;
        }
    }

    public Object clonar() {
        DCmdAccCrearDocumento20008 clon = new DCmdAccCrearDocumento20008();
        asignar(clon);
        return clon;
    }

    public String getDescripcion() {
        return "Acci�n que se encarga de crear el documento que define " +
                "la tarea de de Presentaci�n de formulario para el " +
                "Formato de Precios de Transferencia";
    }

    public boolean isAuditable() {
        return false;
    }

    public boolean validar() throws DValidarExcepcion {
        return true;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getNumeroRepeticion() {
        return numeroRepeticion;
    }

    public void setNumeroRepeticion(Integer numeroRepeticion) {
        this.numeroRepeticion = numeroRepeticion;
    }

}
