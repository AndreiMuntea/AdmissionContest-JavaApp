package GUI.OptionsGUI.Reports.Bar;

import Controller.OptionController;
import Domain.DTO.AverageSection;
import Domain.DTO.TopSections;
import Utils.Exceptions.MyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 2017-01-06.
 */
public class BarController {
    private OptionController controller;
    private Stage mainStage;
    private VBox scene;

    public BarController(OptionController controller) {
        this.controller = controller;

        scene = new VBox();

        mainStage = new Stage();
        mainStage.setScene(new Scene(scene,600,400));
        mainStage.setTitle("Top Sections Bar Chart - report");
        mainStage.setResizable(false);
        mainStage.initModality(Modality.APPLICATION_MODAL);
    }
    public void generateReportTopSections(String top) throws MyException {
        scene.getChildren().clear();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);

        bc.setTitle("Top sections");
        xAxis.setLabel("Section");
        yAxis.setLabel("Registered candidates");

        XYChart.Series<String, Number> data = new XYChart.Series<>();
        List<TopSections> sections  = controller.getTopSections(top);
        for( TopSections s : sections)
        {
            data.getData().add(new XYChart.Data<>(s.getSectionName(), s.getSectionRegisteredCandidates()));
        }
        bc.getData().add(data);

        scene.getChildren().add(bc);

        mainStage.show();
    }

    public void generateReportAverageSections(String top) throws MyException {
        scene.getChildren().clear();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);

        bc.setTitle("Average sections");
        xAxis.setLabel("Section");
        yAxis.setLabel("Candidates grade");

        XYChart.Series<String, Number> data = new XYChart.Series<>();
        List<AverageSection> sections  = controller.getAverageSection(top);
        for( AverageSection s : sections)
        {
            data.getData().add(new XYChart.Data<>(s.getSectionName(), s.getSectionAverage()));
        }
        bc.getData().add(data);

        scene.getChildren().add(bc);

        mainStage.show();
    }
}
