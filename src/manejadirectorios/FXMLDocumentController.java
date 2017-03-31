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
        panelBase.setStyle("-fx-background-color: lightgrey;");
        fieldFiltrado.setStyle("-fx-background-color : lightgreen;");
        fieldNuevoArchivo.setStyle("-fx-background-color : lightgreen;");
        fieldTamaño.setStyle("-fx-background-color : lightgreen;");
        fiendRuta.setStyle("-fx-background-color : lightgreen;");
        panelRadio.setStyle("-fx-border-color:black; -fx-border-width:1px; -fx-border-radius:3px;");
        

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
    }
    @FXML
    private void ejecutar(ActionEvent event) {
        if (listarContenido.isSelected()){
            textZone.setText(String.valueOf(acciones.listarDirectorio()));
        }
    }

    @FXML
    private void salir(ActionEvent event) {
    }

}
