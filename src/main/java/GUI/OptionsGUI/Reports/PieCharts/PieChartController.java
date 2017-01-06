package GUI.OptionsGUI.Reports.PieCharts;

import Controller.OptionController;
import Domain.DTO.AverageSection;
import Domain.DTO.TopSections;
import Utils.Exceptions.MyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 2017-01-06.
 */
public class PieChartController {
    private OptionController controller;
    private Stage mainStage;
    private VBox scene;

    public PieChartController(OptionController controller) {
        this.controller = controller;

        scene = new VBox();

        mainStage = new Stage();
        mainStage.setScene(new Scene(scene, 600, 400));
        mainStage.setTitle("Pie Chart - report");
        mainStage.setResizable(false);
        mainStage.initModality(Modality.APPLICATION_MODAL);
    }

    public void generateReportAverageSections(String top) throws MyException {
        scene.getChildren().clear();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        List<AverageSection> sections = controller.getAverageSection(top);
        for (AverageSection s : sections) {
            data.add(new PieChart.Data(s.getSectionName(), s.getSectionAverage()));
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);
        PieChart chart = new PieChart(pieChartData);
        chart.setLabelLineLength(16);
        chart.setLegendSide(Side.RIGHT);

        chart.setTitle("Average Sections");
        scene.getChildren().add(chart);

        mainStage.show();
    }

    public void generateReportTopSections(String top) throws MyException{
        scene.getChildren().clear();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        List<TopSections> sections = controller.getTopSections(top);
        for (TopSections s : sections) {
            data.add(new PieChart.Data(s.getSectionName(), s.getSectionRegisteredCandidates()));
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);
        PieChart chart = new PieChart(pieChartData);
        chart.setLabelLineLength(16);
        chart.setLegendSide(Side.RIGHT);

        chart.setTitle("Top Sections");
        scene.getChildren().add(chart);

        mainStage.show();
    }

}
