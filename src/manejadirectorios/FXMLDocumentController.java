package manejadirectorios;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.AccionesDirectorio;

public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private Pane panelBase;
    @FXML
    private Pane panelRadio;
    @FXML
    private RadioButton listarContenido;
    @FXML
    private ToggleGroup rbListados;
    @FXML
    private RadioButton listarFiltrado;
    @FXML
    private RadioButton listarLectura;
    @FXML
    private RadioButton listarTamaño;
    @FXML
    private RadioButton crearNuevo;
    @FXML
    private TextField fieldFiltrado;
    @FXML
    private TextField fieldTamaño;
    @FXML
    private TextField fieldNuevoArchivo;
    @FXML
    private Button botonAbrir;
    @FXML
    private Button botonEjecutar;
    @FXML
    private Button botonSalir;
    @FXML
    private TextField fiendRuta;
    @FXML
    private TextArea textZone;

    private AccionesDirectorio acciones = new AccionesDirectorio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estilos();
        accesos();

    }

    @FXML
    private void abrirRuta(ActionEvent event) {
        Stage stage = new Stage();
        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("Buscar Archivo");
        File archivo = fileChooser.showDialog(stage);
        if (archivo != null) {
            File pathArchivo = new File(archivo.getAbsolutePath());
            acciones.setDirectorio(pathArchivo.toPath());
            fiendRuta.setText(String.valueOf(pathArchivo));
        }
        accesos();
    }

    @FXML
    private void ejecutar(ActionEvent event) {
        if (acciones.getDirectorio() != null) {
            if (listarContenido.isSelected()) {
                textZone.setText(acciones.listarDirectorio());
            } else if (listarFiltrado.isSelected()) {
                textZone.setText(acciones.filtrarContenido(fieldFiltrado.getText()));
            } else if (listarLectura.isSelected()) {
                textZone.setText(acciones.archivosLectura());
            } else if (listarTamaño.isSelected()) {
                textZone.setText(acciones.contenidoPorTamaño(Long.parseLong(fieldTamaño.getText())));
            } else if (crearNuevo.isSelected()) {
                acciones.crearArchivo(fieldNuevoArchivo.getText());
            } else {
                textZone.setText("Elige una opción");
            }
        } else {
            textZone.setText("Abre antes un directorio");
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        
        
    }

    private void estilos() {
        panelBase.setStyle("-fx-background-color: lightgrey;");
        fieldFiltrado.setStyle("-fx-background-color : lightgreen;");
        fieldNuevoArchivo.setStyle("-fx-background-color : lightgreen;");
        fieldTamaño.setStyle("-fx-background-color : lightgreen;");
        fiendRuta.setStyle("-fx-background-color : lightgreen;");
        panelRadio.setStyle("-fx-border-color:black; -fx-border-width:1px; -fx-border-radius:3px;");
        fieldFiltrado.setVisible(false);
        fieldTamaño.setVisible(false);
        fieldNuevoArchivo.setVisible(false);
        textZone.setStyle("-fx-control-inner-background:black;");
    }

    private void accesos() {
        if (acciones.getDirectorio() == null) {
            RadioButton[] buttons = new RadioButton[]{listarContenido, listarFiltrado, listarLectura, listarTamaño, crearNuevo};
            for (RadioButton btn : buttons) {
                btn.setDisable(true);
            }
        }
        if (acciones.getDirectorio() != null) {
            RadioButton[] buttons = new RadioButton[]{listarContenido, listarFiltrado, listarLectura, listarTamaño, crearNuevo};
            for (RadioButton btn : buttons) {
                btn.setDisable(false);
            }
        }
    }

    @FXML
    private void select(ActionEvent event) {
        if (listarFiltrado.isSelected()) {
            fieldFiltrado.setVisible(true);
        } else {
            fieldFiltrado.setVisible(false);
        }
        if (listarTamaño.isSelected()) {
            fieldTamaño.setVisible(true);
        } else {
            fieldTamaño.setVisible(false);
        }
        if (crearNuevo.isSelected()) {
            fieldNuevoArchivo.setVisible(true);
        } else {
            fieldNuevoArchivo.setVisible(false);
        }
    }
}
