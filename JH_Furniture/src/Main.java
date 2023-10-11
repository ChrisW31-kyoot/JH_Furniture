import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {
	int idGenerate = 1;
	int price; 
	
	
	Stage stage, next;
	Scene scene;
	BorderPane bp;
	GridPane gp, gpTable;
	ScrollPane sp;
	TextField idTF, nameTF, priceTF;
	Label idLbl, nameLbl, priceLbl, typeLbl;
	Button submitBtn, updateBtn, deleteBtn;
	HBox buttons;
	MenuBar mb;
	Menu menu;
	MenuItem pindah, stay;
	ComboBox<String> cb;
	TableView<Furniture> table;
	
	Alert errorAlert = new Alert(AlertType.ERROR);
	Alert infoAlert = new Alert(AlertType.INFORMATION);
	
	public void initial() {
		stage = new Stage();
		gp = new GridPane();
		gpTable = new GridPane();
		bp = new BorderPane();
		sp = new ScrollPane();
		
		scene = new Scene(bp, 600, 650);
		
		
		idTF = new TextField("FR" + String.format("%03d", idGenerate));
		nameTF = new TextField();
		priceTF = new TextField();
		
		idTF.setEditable(false);
		
		idLbl = new Label("Furniture's ID");
		typeLbl = new Label("Furniture's type");
		nameLbl = new Label("Furniture's name");
		priceLbl = new Label("Furniture's price");
		
		submitBtn = new Button("Submit");
		deleteBtn = new Button("Delete");
		updateBtn = new Button("Update");
		
		
		cb = new ComboBox<>();
		
		cb.getItems().add("Chair");
		cb.getItems().add("Bed");
		cb.getItems().add("Table");
		
		buttons = new HBox();
		
		buttons.getChildren().add(submitBtn);
		buttons.getChildren().add(deleteBtn);
		buttons.getChildren().add(updateBtn);
		
		cb.getSelectionModel().select("Chair");
		
		mb = new MenuBar();
		pindah = new MenuItem("yuk pindah yuk");
		stay = new MenuItem("stay aja bah");
		
		menu = new Menu("Option");
		
		menu.getItems().add(pindah);
		menu.getItems().add(stay);
		
		mb.getMenus().add(menu);
		
		
	
		
	}
	
	
	public void setPosition() {
		
		gpTable.add(mb, 0, 0);
		gpTable.add(sp, 0, 1);
		
		gp.add(idLbl, 0, 0);
		gp.add(idTF, 1, 0);
		
		gp.add(nameLbl, 0, 1);
		gp.add(nameTF, 1, 1);
		
		gp.add(typeLbl, 0, 2);
		gp.add(cb, 1, 2);
		
		gp.add(priceLbl, 0, 3);
		gp.add(priceTF, 1, 3);
		
		gp.add(buttons, 0, 5);
		
		
		bp.setCenter(gp);
		bp.setTop(gpTable);
		bp.setBottom(buttons);
		
		sp.setContent(table);
		
		gp.setAlignment(Pos.TOP_CENTER);
		buttons.setAlignment(Pos.BASELINE_CENTER);
		
		gp.setHgap(15);
		gp.setVgap(10);
		
		gp.setPadding(new Insets(10));
		
		gpTable.setHgap(15);
		gpTable.setVgap(10);
		
		gpTable.setPadding(new Insets(10));
		
	
		
		
		
		
	}
	
public static void main(String[] args) {
		
		launch(args);
	}

public void addTable() {
	//Inisialisasi
	table = new TableView<Furniture>();
	
	//Set kolom dari tabel
	TableColumn<Furniture, String> idCol = new TableColumn<Furniture, String>("FurnitureID");
	idCol.setCellValueFactory(new PropertyValueFactory<Furniture, String>("id"));
	
	TableColumn<Furniture, String> nameCol = new TableColumn<Furniture, String>("FurnitureName");
	nameCol.setCellValueFactory(new PropertyValueFactory<Furniture, String>("name"));
	
	TableColumn<Furniture, String> typeCol = new TableColumn<Furniture, String>("FurnitureType");
	typeCol.setCellValueFactory(new PropertyValueFactory<Furniture, String>("type"));
	
	TableColumn<Furniture, Integer> priceCol = new TableColumn<Furniture, Integer>("FurniturePrice");
	priceCol.setCellValueFactory(new PropertyValueFactory<Furniture, Integer>("price"));
	
	table.getColumns().addAll(idCol, nameCol, typeCol, priceCol);
	
	table.setMinHeight(200);
	table.setMinWidth(580);
	
	idCol.setMinWidth(145);
	nameCol.setMinWidth(145);
	typeCol.setMinWidth(145);
	priceCol.setMinWidth(145);
	
	
}

	@Override
	public void start(Stage arg0) throws Exception {
		
		initial();
		addTable();
		setPosition();
		
		submitBtn.setOnAction(this);
		deleteBtn.setOnAction(this);
		updateBtn.setOnAction(this);
		pindah.setOnAction(this);
		stay.setOnAction(this);
		
		
		stage.setTitle("JH_Furniture");
		stage.setScene(scene);
		stage.show();
		stage.setResizable(false);
		
	}
	

	@Override
	public void handle(ActionEvent e) {
		
		if(e.getSource() == submitBtn) {
			if(nameTF.getText().isEmpty() || nameTF.getText().length() < 5){
				;
				errorAlert.setHeaderText("Name not valid!");
				errorAlert.setContentText("Name field must be filled! / > 5 characters");
				errorAlert.showAndWait();
				}
			
				
				else if(priceTF.getText().isEmpty() || Integer.parseInt(priceTF.getText().toString()) < 15000) {
					errorAlert.setHeaderText("Price not valid!");
					errorAlert.setContentText("Price field must be filled! / > 15.000");
					errorAlert.showAndWait();
				}
				else {
					
					System.out.println("Submit");
					table.getItems().add(new Furniture(idTF.getText(), nameTF.getText(), cb.getValue(), price = Integer.parseInt(priceTF.getText().toString())));
					idGenerate++;
					idTF.setText("FR" + String.format("%03d", idGenerate)); // This line updates the idTF field
			}
			
			
			
		}
		else if(e.getSource() == deleteBtn) {
			table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
			System.out.println("Delete");
		}
		
		else if(e.getSource() == updateBtn) {
			table.getSelectionModel().getSelectedItem().setName(nameTF.getText());
			table.getSelectionModel().getSelectedItem().setPrice(Integer.parseInt(priceTF.getText()));
			
			table.refresh();
			
			System.out.println("Update");
		}
	
		else if(e.getSource() == pindah) {
			stage.close();
			
			Stage next = new Stage();
			
			try {
				new anotherPage().start(next);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(e.getSource() == stay) {
			infoAlert.setHeaderText("You decided to stay");
			infoAlert.setContentText("tidak seperti dia :(");
			infoAlert.showAndWait();
			
		}else {
	        // Handle the case where no row is selected
	        // You may want to show an alert or take some other action
	        System.out.println("No row selected for update");
	    
		}
	}

}















