import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Chart {
    public static void draw(ArrayList<Point> in) {
        XYSeriesCollection lineDataset = new XYSeriesCollection ();

        double a = in.get(0).getX() - 1.0, b = in.get(in.size() - 1).getX() + 1.0;
        /*XYSeries border = new XYSeries("y=0");
        border.add(a,0);
        border.add(b,0);
        lineDataset.addSeries(border);*/

        XYSeries series0 = new XYSeries("Начальные точки");
        for (Point point : in) {
            series0.add(point.getX(), point.getY());
        }

        lineDataset.addSeries(series0);

        XYSeries series1 = new XYSeries("Нютон");
        for (double j = a; j < b; j += (b - a) / 100.0) {
            series1.add(j, Methods.newtonPolynomial(in, j));
        }

        lineDataset.addSeries(series1);

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "f(x)", "x",
                "y",
                lineDataset, PlotOrientation.VERTICAL,
                true, true, false);

        try {
            ChartUtils.saveChartAsJPEG(new File("src/main/resources/charts.jpeg"), lineChart, 1920, 1080);
        } catch (IOException e) {
            System.out.println("Не удалось сохранить график");
        } catch (IllegalArgumentException e) {
            System.out.println("Не правильный формат числа");
        }
    }
}
