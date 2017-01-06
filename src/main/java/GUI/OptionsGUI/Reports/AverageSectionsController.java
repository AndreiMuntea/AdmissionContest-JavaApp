package GUI.OptionsGUI.Reports;

import Controller.OptionController;
import Domain.DTO.AverageSection;
import Domain.DTO.TopSections;
import Utils.Exceptions.MyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 2017-01-06.
 */
public class AverageSectionsController {
    @FXML
    private HBox centerPane;


    private Stage mainStage;
    private OptionController optionController;
    private List<AverageSection> data;
    private int flag;
    private String top;
    private BufferedImage img;

    public AverageSectionsController() {
    }

    public void initialiseComponents(OptionController optionController, Stage mainStage) {
        this.data = null;
        this.optionController = optionController;
        this.flag = 0;
        this.mainStage = mainStage;

        this.mainStage.setOnCloseRequest(e -> this.flag = 0);
    }

    public void generatePieChart(String top) throws MyException {
        if (flag == 0) {
            this.top = top;
            data = optionController.getAverageSection(top);
        }
        flag = 1;
        centerPane.getChildren().clear();


        ArrayList<PieChart.Data> data = new ArrayList<>();
        List<AverageSection> sections = this.data;
        for (AverageSection s : sections) {
            data.add(new PieChart.Data(s.getSectionName(), s.getSectionAverage()));
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);
        PieChart chart = new PieChart(pieChartData);
        chart.setLabelLineLength(16);
        chart.setLegendSide(Side.BOTTOM);

        chart.setTitle("Average Sections");
        centerPane.getChildren().add(chart);
        img = SwingFXUtils.fromFXImage(centerPane.snapshot(new SnapshotParameters(), null), null);
        mainStage.show();
    }

    public void generateBarChart(String top) throws MyException {
        if (flag == 0) {
            this.top = top;
            data = optionController.getAverageSection(top);
        }
        flag = -1;
        centerPane.getChildren().clear();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);

        bc.setTitle("Average sections");
        xAxis.setLabel("Section");
        yAxis.setLabel("Candidates grade");

        XYChart.Series<String, Number> data = new XYChart.Series<>();
        List<AverageSection> sections  = this.data;
        for( AverageSection s : sections)
        {
            data.getData().add(new XYChart.Data<>(s.getSectionName(), s.getSectionAverage()));
        }
        bc.getData().add(data);

        centerPane.getChildren().add(bc);
        img = SwingFXUtils.fromFXImage(centerPane.snapshot(new SnapshotParameters(), null), null);
        mainStage.show();
    }

    public void switchHandler() {
        try {
            if (flag == 1) generateBarChart(this.top);
            else if (flag == -1) generatePieChart(this.top);
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    public void export() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG image (*.png)", "*.png");
        FileChooser.ExtensionFilter bmpFilter = new FileChooser.ExtensionFilter("BMP image (*.bmp)", "*.bmp");
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG image (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF doc (*.pdf)", "*.pdf");

        fileChooser.getExtensionFilters().addAll(pngFilter, bmpFilter, jpgFilter, pdfFilter);

        try {
            File file = fileChooser.showSaveDialog(mainStage);
            if (file != null) {
                String filePath = file.getAbsolutePath();
                String extension = FilenameUtils.getExtension(filePath);
                optionController.exportImage(this.img, filePath, extension);
            }
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        catch (Exception e){e.printStackTrace();}
    }

}