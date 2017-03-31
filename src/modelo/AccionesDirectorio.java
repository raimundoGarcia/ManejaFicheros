package modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.stream.Stream;

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

    public String listarDirectorio() {
        //File listado =directorio.toFile();
        String lista = "";
        lista = "1. LISTADO DEL CONTENIDO DE LA CARPETA: " + this.directorio.getFileName() + "\n";

        try (Stream<Path> ficheros = Files.list(directorio)) {
            Iterator<Path> it = ficheros.iterator();

            while (it.hasNext()) {
                Path tipo = it.next();
                File tipos = tipo.toFile();
                if (tipos.isDirectory()) {
                    lista += "CARPETA " + tipos.getName() + " TAMAÑO "+ tipos.length() + " bytes " + "\n";
                } else {
                    lista += "ARCHIVO " + tipos.getName() + " TAMAÑO "+ tipos.length() + " bytes " + "\n";
                }
            }
                }catch (IOException ex) {
            lista = "Error en la lista de ficheros";
    }
                return lista;

            }

        }
     

            
      
 

