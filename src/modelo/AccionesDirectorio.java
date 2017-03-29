
package modelo;

import java.io.File;
import java.nio.file.Path;


public class AccionesDirectorio {
    private Path directorio;

    public AccionesDirectorio() {
        
    }

    public Path getDirectorio() {
        return directorio;
    }

    public void setDirectorio(Path directorio) {
        this.directorio = directorio;
    }
    public String[] listarDirectorio(){
         File listado =directorio.toFile();
         String[] lista =listado.list();
         return lista;
    }
    
}
