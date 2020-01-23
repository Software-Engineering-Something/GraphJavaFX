package JavaFX;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DataChart extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
	}

	private void init(Stage primaryStage) {
		HBox root = new HBox();
		Scene scene = new Scene(root, 450, 330);

		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Date");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Close");

		LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Stock Predicting Graph");

		XYChart.Series<Number, Number> data = new XYChart.Series<>();

		// read csv file
		// change the directory to work
		String csvFile = "C:\\Users\\quanh\\Documents\\aal.us.csv";
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			 br.readLine();
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] country = line.split(cvsSplitBy);
				String dateString = country[0].replace("-", "");
				dateString = dateString.replace("\"", "");
				int dateToInt = Integer.valueOf(dateString);
				double closeToInt = Double.valueOf(country[4]);
				data.getData().add(new XYChart.Data<Number, Number>(dateToInt, closeToInt));
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		lineChart.getData().add(data);
		root.getChildren().add(lineChart);
		primaryStage.setTitle("Line");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
