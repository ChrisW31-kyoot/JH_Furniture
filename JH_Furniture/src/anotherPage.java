import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class anotherPage extends Application {

	BorderPane bp = new BorderPane();
	
	Stage stage = new Stage();
	Scene scene = new Scene(bp, 600, 300);
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		stage.setScene(scene);
		stage.setTitle("yu bs yu");
		stage.show();
		
		
	}

}
