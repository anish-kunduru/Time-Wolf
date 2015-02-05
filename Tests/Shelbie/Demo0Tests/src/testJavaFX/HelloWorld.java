package testJavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class HelloWorld extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
    	primaryStage.setTitle("Test Form");
    	
    	GridPane grid = new GridPane();
    	grid.setAlignment(Pos.CENTER);
    	
    	Text title = new Text("Hello!");
    	title.setFont(Font.font("Comic Sans", FontWeight.BOLD, 15));
    	grid.add(title, 0, 0, 2, 1);
    	
    	Label firstName = new Label("First Name:");
    	grid.add(firstName, 0, 1);
    	
    	TextField fname = new TextField();
    	grid.add(fname, 1, 1);
    	
    	Label lastName = new Label("Last Name:");
    	grid.add(lastName, 0, 2);
    	
    	TextField lname = new TextField();
    	grid.add(lname, 1, 2);
    	
    	Button btn = new Button("Confirm");
    	HBox hbBtn = new HBox(10);
    	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	hbBtn.getChildren().add(btn);
    	grid.add(hbBtn, 1, 4);
    	
    	
    	final Text actiontarget = new Text();
    		grid.add(actiontarget, 1, 6);
    	
    	btn.setOnAction(new EventHandler<ActionEvent>(){
        		
        		@Override
        		public void handle(ActionEvent e){
        			actiontarget.setFill(Color.FIREBRICK);
        			actiontarget.setText("Your name is " + fname.getText() + " " + lname.getText());
        		}
        	});
    		
    	Scene scene = new Scene(grid, 300, 275);
    	primaryStage.setScene(scene);
    	
    	primaryStage.show();
    }
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Hello World!");
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
// 
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        Label label1 = new Label("Number:");
//        TextField textField = new TextField ();
//        HBox hb = new HBox();
//        hb.getChildren().addAll(label1, textField);
//        hb.setSpacing(10);
//
//        Button btn2 = new Button();
//        btn2.setText("Find the factorial!");
//        btn2.setOnAction(new EventHandler<ActionEvent>(){
//        	@Override
//        	public void handle(ActionEvent event){
//        		int num = Integer.parseInt(textField.getText());
//        		int sum = num;
//        		num--;
//        		while(num != 0)
//        		{
//        			sum = sum * num;
//        			num--;
//        		}
//        		System.out.println(sum);
//        	}
//        
//        });
//        
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        root.getChildren().add(hb);
//        root.getChildren().add(btn2);
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();
//    }
}
