package modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
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

        String lista = "";
        lista = "1. LISTADO DEL CONTENIDO DE LA CARPETA: " + this.directorio.getFileName() + "\n";

        try (Stream<Path> ficheros = Files.list(directorio)) {
            Iterator<Path> it = ficheros.iterator();

            while (it.hasNext()) {
                Path tipo = it.next();
                File tipos = tipo.toFile();
                if (tipos.isDirectory()) {
                    lista += "CARPETA " + tipos.getName() + ", TAMAÑO " + conversorTamaño(tipos.length())
                            + ", Modificado " + conversorFecha(tipos.lastModified()) + "\n";
                } else {
                    lista += "ARCHIVO " + tipos.getName() + " TAMAÑO " + conversorTamaño(tipos.length())
                            + ", Modificado " + conversorFecha(tipos.lastModified()) + "\n";
                }
            }
        } catch (IOException ex) {
            lista = "Error en la lista de ficheros";
        }
        return lista;

    }

    private String conversorTamaño(Long tamaño) {
        String unidad;
        if (tamaño / 1000000000 > 1) {
            unidad = tamaño / 1000000000 + " GB";
        } else if (tamaño / 1000000 > 1) {
            unidad = tamaño / 1000000 + " MB";
        } else if (tamaño / 1000 > 1) {
            unidad = tamaño / 1000 + " KB";
        } else {
            unidad = tamaño + " Bytes";
        }
        return unidad;
    }

    private String conversorFecha(long milisegundos) {
        String fecha;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy ; HH:mm:ss");
        fecha = formato.format(milisegundos);
        return fecha;
    }

    public String filtrarContenido(String cadena) {
        String contenido = "";

        try (Stream<Path> ficheros = Files.list(directorio)) {
            Iterator<Path> it = ficheros.iterator();

            while (it.hasNext()) {
                Path tipo = it.next();
                if (tipo.getFileName().toString().contains(cadena)) {
                    File tipos = tipo.toFile();
                    if (tipos.isDirectory()) {
                        contenido += "CARPETA " + tipos.getName() + ", TAMAÑO " + conversorTamaño(tipos.length())
                                + ", Modificado " + conversorFecha(tipos.lastModified()) + "\n";
                    } else {
                        contenido += "ARCHIVO " + tipos.getName() + " TAMAÑO " + conversorTamaño(tipos.length())
                                + ", Modificado " + conversorFecha(tipos.lastModified()) + "\n";
                    }
                }
            }
        } catch (IOException ex) {
            contenido = "Error en la lista de ficheros";
        }
        return contenido;
    }

    public String archivosLectura() {
        String lista = "";
        try (Stream<Path> ficheros = Files.list(directorio)) {
            Iterator<Path> it = ficheros.iterator();

            while (it.hasNext()) {
                Path tipo = it.next();
                File tipos = tipo.toFile();

                if (tipos.isFile()) {
                    lista += tipos.getName() + " TAMAÑO " + conversorTamaño(tipos.length())
                            + ", Modificado " + conversorFecha(tipos.lastModified()) + "\n";
                }
            }
        } catch (IOException ex) {
            lista = "Error en la lista de ficheros";
        }
        return lista;

    }

    public String contenidoPorTamaño(long bytes) {
        String lista = "";
        try (Stream<Path> ficheros = Files.list(directorio)) {
            Iterator<Path> it = ficheros.iterator();

            while (it.hasNext()) {
                Path tipo = it.next();
                File tipos = tipo.toFile();
                if (tipos.length() == bytes) {
                    if (tipos.isDirectory()) {
                        lista += "CARPETA " + tipos.getName() + ", TAMAÑO " + conversorTamaño(tipos.length())
                                + ", Modificado " + conversorFecha(tipos.lastModified()) + "\n";
                    } else {
                        lista += "ARCHIVO " + tipos.getName() + " TAMAÑO " + conversorTamaño(tipos.length())
                                + ", Modificado " + conversorFecha(tipos.lastModified()) + "\n";
                    }
                }
            }
        } catch (IOException ex) {
            lista = "Error en la lista de ficheros";
        }
        return lista;
    }

    public void crearArchivo(String nuevo) {
        try {
            File nuevos = new File(directorio +"\\"+ nuevo);
            if (!nuevos.exists()) {
                nuevos.createNewFile();
            }
            
        } catch (IOException ex) {

        }
    }
}
