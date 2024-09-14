package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleEdge;

/**
 * @author NOUMEN DARRYL
 *
 */

@SuppressWarnings("serial")
public class LineChart extends ApplicationFrame{
	private JFreeChart lineChart;
	private XYSeries tempOut;
	private XYSeries tempIn;
	
	private double compteur;
	public ValueMarker mark;
	
	public LineChart(String applicationTitle , String chartTitle)
	   {
	      super(applicationTitle);
	      lineChart = ChartFactory.createXYLineChart(
	         chartTitle,
	         "Temps(seconds)","Temperature(°C)",
	         createDataset(),
	         PlotOrientation.VERTICAL,
	         true,true,false);
	      
	      lineChart.getTitle().setFont(new Font("Tahoma", Font.PLAIN, 19));
	      
		  getJChart().getLegend().setPosition(RectangleEdge.RIGHT);
	      
	      ChartPanel chartPanel = new ChartPanel(lineChart);
	      chartPanel.setPreferredSize(new java.awt.Dimension(600,500));
	      setContentPane( chartPanel );
	      compteur = 0;
	      
	      XYPlot plot = lineChart.getXYPlot();
		  mark = new ValueMarker(0, Color.ORANGE, new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 12.0f, new float[] {5.0f}, 0.0f));
		  plot.addRangeMarker(mark, Layer.BACKGROUND);
		  
		  plot.setBackgroundPaint(new Color(45,45,45));
		  plot.setRangeGridlinePaint(new Color(10,10,10));
		  plot.setDomainGridlinePaint(new Color(10,10,10));
		  
		  plot.getDomainAxis().setLabelFont(new Font("Segoe UI", Font.BOLD, 14));
		  plot.getRangeAxis().setLabelFont(new Font("Segoe UI", Font.BOLD, 14));
		  
		  XYItemRenderer renderer = plot.getRenderer();
		  
		  // Température extérieure
		  renderer.setSeriesItemLabelPaint(0, new Color(241, 61, 7));
		  renderer.setSeriesPaint(0, new Color(241, 61, 7));
		  renderer.setSeriesStroke(0,  new BasicStroke(2));
		  
		  // Température intérieure
		  renderer.setSeriesItemLabelPaint(1, new Color(0, 174, 189));
		  renderer.setSeriesPaint(1, new Color(0, 174, 189));
		  renderer.setSeriesStroke(1,  new BasicStroke(2));
		  
		  //plot.getDomainAxis().setRange(new Range(0, 15000));
		  plot.getDomainAxis().setTickLabelPaint(new Color(169,169,169));
		  plot.getDomainAxis().setTickLabelFont(new Font("Segoe UI", Font.BOLD, 13));
		  
		  plot.getRangeAxis().setRange(new Range(0, 100)); 
		  plot.getRangeAxis().setTickLabelPaint(new Color(169,169,169));
		  plot.getRangeAxis().setTickLabelFont(new Font("Segoe UI", Font.BOLD, 13));
		  
		  //plot.setBackgroundPaint(new Color(77,77,77));
		  lineChart.getLegend().setBackgroundPaint(new Color(77,77,77));
		  lineChart.getLegend().setItemPaint(new Color(200,200,200));
		  lineChart.getLegend().setBorder(0, 0, 0, 0);
		  lineChart.getLegend().setItemFont(new Font("Segoe UI", Font.BOLD, 13));
	   }

	private XYSeriesCollection createDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		tempOut = new XYSeries("T° Out");
		tempIn = new XYSeries("T° In");

		dataset.addSeries(tempOut);
		dataset.addSeries(tempIn);
		
		this.tempIn.setMaximumItemCount(600);
		this.tempOut.setMaximumItemCount(600);
		
		return dataset;
	}

	public JFreeChart getJChart() {
		return lineChart;
	}
	
	public void addData(float tempIn, float tempOut) {
		compteur++;
		this.tempIn.add(compteur,tempIn);
		this.tempOut.add(compteur,tempOut);
	}
}
