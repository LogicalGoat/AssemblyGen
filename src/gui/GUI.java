package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLGUI.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setMinWidth(500);
            stage.setMinHeight(600);
            stage.setTitle("Assembly Generator");
			stage.getIcons().add(new Image("/GUI/icon.png"));
		}catch(IOException e) {
			System.err.println("Error al inicializar FXML");
		}
	}

}
