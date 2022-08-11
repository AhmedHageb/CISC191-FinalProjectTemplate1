package edu.sdccd.cisc191.Server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application implements Runnable{

    Thread thread;
    TableView tableView;
    private static GradesList gradesList = GradesList.subjectsList;

    public void start()
    {
        if (thread == null)
        {
            thread = new Thread(this,"Loading GUI" );
            thread.start();
        }
    }
    @Override
    public void run()
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("GradeBook");
        MenuBar menuBar = new MenuBar();

        Menu menu1 = new Menu("Page");
        MenuItem menuItem1 = new MenuItem("Redraw");
        menuItem1.setOnAction(e -> {
            this.drawGradeBook();
        });
        MenuItem menuItem2 = new MenuItem("Reset");
        menuItem2.setOnAction(e -> {
            this.reset();
        });
        menu1.getItems().add(menuItem1);
        menu1.getItems().add(menuItem2);
        menuBar.getMenus().add(menu1);

        tableView = new TableView<>();
        VBox vbox = new VBox();
        vbox.getChildren().add(menuBar);
        vbox.getChildren().add(tableView);

        drawGradeBook();
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();

    }

    //creates the grade book and enters the subjects and the grades entered by the user
    public void drawGradeBook()
    {
        tableView.getColumns().clear();
        tableView.getItems().clear();
        TableColumn<Subject, String> classColumn= new TableColumn<>("Subject");
        TableColumn<Subject, Double> gradeColumn = new TableColumn<>("Grade");
        classColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("Grade"));
        tableView.getColumns().add(classColumn);
        tableView.getColumns().add(gradeColumn);
        for (int i = 0; i < gradesList.getClassGradeArrayList().size(); i++) {
            tableView.getItems().add(gradesList.getClassGradeArrayList().get(i));
        }
    }
    //clears the grade book gui and resets the subject arraylist to remove all the grades entered by the user and start a new grade book
    public void reset()
    {
        tableView.getColumns().clear();
        tableView.getItems().clear();
        gradesList.deleteAll();
    }

}
