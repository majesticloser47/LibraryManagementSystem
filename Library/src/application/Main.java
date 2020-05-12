package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private double xOffSet=0;
	private double yOffSet=0;
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			scene.setFill(Color.TRANSPARENT);
			root.setOnMousePressed(new EventHandler<MouseEvent>(){
				public void handle(MouseEvent e)
				{
					xOffSet=e.getSceneX();
					yOffSet=e.getSceneY();
				}
			});
			root.setOnMouseDragged(new EventHandler<MouseEvent>(){
				public void handle(MouseEvent e)
				{
					primaryStage.setX(e.getScreenX()-xOffSet);
					primaryStage.setY(e.getScreenY()-yOffSet);
				}
			});
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
