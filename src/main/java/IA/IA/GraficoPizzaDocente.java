package IA.IA;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * @author adelinofernandes
 */
public class GraficoPizzaDocente extends JFrame{
 
	private static final long serialVersionUID = 1L;
 
	public GraficoPizzaDocente(String applicationTitle, String chartTitle, int denominacao[]) {
		super(applicationTitle);
		PieDataset dataset = criarDados(denominacao);
		JFreeChart chart = criarGrafico(dataset, chartTitle);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 800));
		setContentPane(chartPanel);
	}
 
	/**
	 * Cria um conjunto de amostra dos dados abertos da UFRN
	 */
 
	private PieDataset criarDados(int denominacao[]) {
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Ajunto A", denominacao[0]);
		result.setValue("Assistente A", denominacao[1]);
		result.setValue("Auxiliar A", denominacao[2]);
		result.setValue("Assistente", denominacao[3]);
		result.setValue("Adjunto", denominacao[4]);
		result.setValue("Associado", denominacao[5]);
		result.setValue("Titular", denominacao[6]);
		return result;
 
	}
 
	/**
	 * Cria um gráfico 
	 */
 
	private JFreeChart criarGrafico(PieDataset dataset, String title) {
		JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setStartAngle(180);
		return chart;
	}
 
}