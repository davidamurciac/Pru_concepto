package co.gov.dian.muisca.diligenciamientomasivo.acciones.transferencias;

import java.util.*;

import co.gov.dian.muisca.arquitectura.general.excepcion.*;
import co.gov.dian.muisca.arquitectura.interfaces.*;
import co.gov.dian.muisca.arquitectura.interfaces.implgenerica.comandos.*;
import co.gov.dian.muisca.entradasalida.general.to.documento.*;

public class DCmdAccConsLstSolicitudesValidas extends DComandoAccion {

    protected DDocumentoPKTO docGenerado;
    protected DDocumentoPKTO docAnterior;
    protected boolean correccion;
    protected List solicitudesValidas = new ArrayList();

    public DCmdAccConsLstSolicitudesValidas() {
    }

    public void inicializar(DDocumentoPKTO docGenerado) {

        this.docGenerado = docGenerado;
        this.correccion = false;
    }

    public void inicializarCorreccion(DDocumentoPKTO docGenerado,
                                      DDocumentoPKTO docAnterior) {

        this.docGenerado = docGenerado;
        this.correccion = true;
        this.docAnterior = docAnterior;
    }

    public void asignar(IDComando comando) {
        if (comando instanceof DCmdAccConsLstSolicitudesValidas) {

            DCmdAccConsLstSolicitudesValidas copia;
            copia = (DCmdAccConsLstSolicitudesValidas) comando;
            copia.docGenerado = docGenerado;
            copia.solicitudesValidas = solicitudesValidas;
            copia.correccion = correccion;
            copia.docAnterior = docAnterior;
        }
    }

    public Object clonar() {
        return new DCmdAccConsLstSolicitudesValidas();
    }

    public boolean isAuditable() {
        return false;
    }

    public String getDescripcion() {
        return
                "Comando de accion que retorna una lista de las solicitudes validas " +
                "de formatos 1124 para consolidar un formato 120";
    }

    protected void ejecutarComando() {
        throw new UnsupportedOperationException();
    }

    public boolean validar() throws DValidarExcepcion {
        if (!correccion) {
            return (docGenerado != null && docGenerado.getIdeDocumento() != null
                    && docGenerado.getNumRepeticion() != null);
        } else {
            return ((docGenerado != null && docGenerado.getIdeDocumento() != null
                     && docGenerado.getNumRepeticion() != null) &&
                    (docAnterior != null && docAnterior.getIdeDocumento() != null));
        }
    }

    public List getSolicitudesValidas() {
        return solicitudesValidas;
    }
}
