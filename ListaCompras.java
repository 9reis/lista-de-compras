import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ListaCompras extends Application { 
private ArrayList<String> listaDeCompras = new ArrayList<>();
private ListView<String> listaVisualizavel = new ListView<>(); // Exibe os itens da lista 

@Override 
public void start(Stage palco){
	palco.setTitle("Lista de Compras");

	TextField textFieldDescricaoItem = new TextField();
	Button btnAdicionar = new Button("Adicionar");
	Button btnExportar = new Button("Exportar");

	Label labelAdicionar = new Label("Digite o item que deseja adicionar: ");
	Label labelListaDeCompras = new Label("Lista de Compras");

	// Cria o Observable a partir da listaDeCompras; 
	ObservableList<String> observableListaDeCompras = FXCollections.observableArrayList(listaDeCompras);
	listaVisualizavel.setItems(observableListaDeCompras);

	VBox vBox = new VBox();
	vBox.getChildren().addAll(labelAdicionar, textFieldDescricaoItem, btnAdicionar);
	vBox.getChildren().addAll(labelListaDeCompras, listaVisualizavel, btnExportar);
	vBox.setSpacing(10); // Espaçamento vertical entre os componentes; 
	vBox.setPadding(new Insets(10)); // Margens internas (espaçamento) para o VBOX;

	btnAdicionar.setOnAction(e -> {
		String item = textFieldDescricaoItem.getText(); // Pega o texto digitado 
		if(!item.isEmpty()){ // Entra no if se o texto não está vazio (digitou algo)
			listaDeCompras.add(item); // Add o item digitado a listaDeCompras 
			listaVisualizavel.getItems().add(item); // Add o texto à listaVisualizavel
			textFieldDescricaoItem.clear();
		}

	});

	btnExportar.setOnAction(e -> {
		try{
			File arq = new File("listaDeCompras.txt");
			PrintWriter writer = new PrintWriter(arq); // Grava os itens da lista no arq; 

			for(String item : listaDeCompras){ // Percorre todos os itens da compra 
				writer.println(item);
			}
			writer.close();
		}catch(Exception er){
			System.out.println("Erro Ocorrido " + er.getMessage());
		}
	});

	Scene cena = new Scene(vBox, 350, 300);
	palco.setScene(cena);
	palco.show();
}

	public static void main(String[] args){
		launch(args);
	}
}