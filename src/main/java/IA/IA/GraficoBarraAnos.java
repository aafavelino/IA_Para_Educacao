package IA.IA;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author adelinofernandes
 */
public class GraficoBarraAnos extends JFrame{
	 
	  public GraficoBarraAnos( String applicationTitle , String chartTitle, int []anos ) {
	      super( applicationTitle );        
	      JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Anos", "Qt. Projetos", criarGrafico(anos), PlotOrientation.VERTICAL,true, true, false);
	      ChartPanel chartPanel = new ChartPanel( barChart );        
	      chartPanel.setPreferredSize(new java.awt.Dimension( 10000 , 567 ) );     
	      
	      setContentPane( chartPanel ); 
	   }
	   
	   private CategoryDataset criarGrafico(int []anos_p) {
		  String anos[] = new String[18]; 
		  for (int i = 0; i < 18; i++) 
			anos[i] =  Integer.toString(2000+i+1);
	  
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset();  

	      for (int i = 0; i < 18; i++) {
			dataset.addValue(anos_p[i], anos[i], anos[i]);
	      }
	                 
	      return dataset; 
	   }
	 
}
