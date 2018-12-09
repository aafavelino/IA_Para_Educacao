package IA.IA;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.jfree.ui.RefineryUtilities;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * @author adelinofernandes
 */
public class App 
{

	   
	public static void main(String[] args) throws IOException {
		
		  // Leitura dos arquivos dos dados abertos da UFRN 
		  Reader docentes = Files.newBufferedReader(Paths.get("docentes.csv"));
		  Reader bolsistas =  Files.newBufferedReader(Paths.get("bolsistas-de-iniciacao-cientifica.csv"));
		  Reader projetos =  Files.newBufferedReader(Paths.get("projetos-de-pesquisa.csv"));
		  Reader progressoes =  Files.newBufferedReader(Paths.get("progressoes.csv"));

		  CSVParser parser = new CSVParserBuilder()
				    .withSeparator(';')
				    .withIgnoreQuotations(true)
				    .build();
		  
		  CSVReader csvReaderBolsistas = new CSVReaderBuilder(bolsistas)
				    .withSkipLines(0)
				    .withCSVParser(parser)
				    .build();
		  CSVReader csvReaderDocentes = new CSVReaderBuilder(docentes)
				    .withSkipLines(0)
				    .withCSVParser(parser)
				    .build();
		  CSVReader csvReaderProgressoes = new CSVReaderBuilder(progressoes)
				    .withSkipLines(0)
				    .withCSVParser(parser)
				    .build();
		  CSVReader csvReaderProjetos = new CSVReaderBuilder(projetos)
				    .withSkipLines(0)
				    .withCSVParser(parser)
				    .build();	  
	      
		  
		  
		  
		  // Alocando os arquivos para trabalhar em cima de listas
		  List<String[]> docentes_csv = csvReaderDocentes.readAll();
		  List<String[]> bolsistas_csv = csvReaderBolsistas.readAll();
		  List<String[]> progressoes_csv = csvReaderProgressoes.readAll();
		  List<String[]> projetos_csv = csvReaderProjetos.readAll();
		  
		  int quantidade_de_projetos_por_docente[] = new int [docentes_csv.size()]; 
		  int denominacao[] = new int [7]; 
		  int crescimento_pesquisa_ano [] = new int [18];
		  int quantidade_de_projetos_por_docente2013[] = new int [docentes_csv.size()]; 
		  int quantidade_de_projetos_por_docente2014[] = new int [docentes_csv.size()]; 
		  int quantidade_de_projetos_por_docente2015[] = new int [docentes_csv.size()]; 
		  int quantidade_de_projetos_por_docente2016[] = new int [docentes_csv.size()]; 
		  int quantidade_de_projetos_por_docente2017[] = new int [docentes_csv.size()]; 
		  int denominacao2013[] = new int [7]; 
		  int denominacao2014[] = new int [7]; 
		  int denominacao2015[] = new int [7]; 
		  int denominacao2016[] = new int [7]; 
		  int denominacao2017[] = new int [7]; 
		  int qtd_denominacao[] = new int [7]; 
		  
		  // Conta quantos projetos de pesquisa um professor x tem.
		  for (int i = 1; i < docentes_csv.size(); i++) {
			quantidade_de_projetos_por_docente[i] = 0;
			quantidade_de_projetos_por_docente2013[i] = 0;
			for (int j = 1; j < bolsistas_csv.size(); j++) {
				if (bolsistas_csv.get(j)[0] != bolsistas_csv.get(j-1)[0]) {
					if (docentes_csv.get(i)[0].equals(bolsistas_csv.get(j)[7])) {
						quantidade_de_projetos_por_docente[i]++;
						if(Integer.parseInt(bolsistas_csv.get(j)[6]) == 2013) 
							quantidade_de_projetos_por_docente2013[i]++;
						if(Integer.parseInt(bolsistas_csv.get(j)[6]) == 2014) 
							quantidade_de_projetos_por_docente2014[i]++;		
						if(Integer.parseInt(bolsistas_csv.get(j)[6]) == 2015) 
							quantidade_de_projetos_por_docente2015[i]++;
						if(Integer.parseInt(bolsistas_csv.get(j)[6]) == 2016) 
							quantidade_de_projetos_por_docente2016[i]++;
						if(Integer.parseInt(bolsistas_csv.get(j)[6]) == 2017) 
							quantidade_de_projetos_por_docente2017[i]++;
					}

				}

			}
		  }
		  
		  try {
			  for (int j = 1; j < bolsistas_csv.size(); j++) {

					  if (Integer.parseInt(bolsistas_csv.get(j)[6])<2001 || Integer.parseInt(bolsistas_csv.get(j)[6]) >2018) {
						  continue;
					} else {
						if (bolsistas_csv.get(j)[0] != bolsistas_csv.get(j-1)[0]) 
							crescimento_pesquisa_ano[(Integer.parseInt(bolsistas_csv.get(j)[6])-2001)]++;
					}
				  }
		} catch (Exception e) {
			System.err.println("Estouro de Buffer");
		}

		  
		  
		  // Por meio da denomicação fazemos a análise de qual classe é mais produtiva na pesquisa
		  for (int i = 0; i < docentes_csv.size(); i++) {
			  switch (docentes_csv.get(i)[7]) {
			  	
				case "ClasseA-AdjuntoA":
					qtd_denominacao[0]++;
					denominacao[0] += quantidade_de_projetos_por_docente[i];
					
					denominacao2013[0] += quantidade_de_projetos_por_docente2013[i];
					denominacao2014[0] += quantidade_de_projetos_por_docente2014[i];
					denominacao2015[0] += quantidade_de_projetos_por_docente2015[i];
					denominacao2016[0] += quantidade_de_projetos_por_docente2016[i];
					denominacao2017[0] += quantidade_de_projetos_por_docente2017[i];
					
					break;
				case "ClasseA-AssistenteA":
					qtd_denominacao[1]++;
					denominacao[1] += quantidade_de_projetos_por_docente[i];
					
					denominacao2013[1] += quantidade_de_projetos_por_docente2013[i];
					denominacao2014[1] += quantidade_de_projetos_por_docente2014[i];
					denominacao2015[1] += quantidade_de_projetos_por_docente2015[i];
					denominacao2016[1] += quantidade_de_projetos_por_docente2016[i];
					denominacao2017[1] += quantidade_de_projetos_por_docente2017[i];
					
					break;
				case "ClasseA-Auxiliar":
					qtd_denominacao[2]++;
					denominacao[2] += quantidade_de_projetos_por_docente[i];
					
					denominacao2013[2] += quantidade_de_projetos_por_docente2013[i];
					denominacao2014[2] += quantidade_de_projetos_por_docente2014[i];
					denominacao2015[2] += quantidade_de_projetos_por_docente2015[i];
					denominacao2016[2] += quantidade_de_projetos_por_docente2016[i];
					denominacao2017[2] += quantidade_de_projetos_por_docente2017[i];
					
					break;
				case "ClasseB-Assistente":
					qtd_denominacao[3]++;
					denominacao[3] += quantidade_de_projetos_por_docente[i];
					
					denominacao2013[3] += quantidade_de_projetos_por_docente2013[i];
					denominacao2014[3] += quantidade_de_projetos_por_docente2014[i];
					denominacao2015[3] += quantidade_de_projetos_por_docente2015[i];
					denominacao2016[3] += quantidade_de_projetos_por_docente2016[i];
					denominacao2017[3] += quantidade_de_projetos_por_docente2017[i];
					
					break;	
				case "ClasseC-Adjunto":
					qtd_denominacao[4]++;
					denominacao[4] += quantidade_de_projetos_por_docente[i];
					
					denominacao2013[4] += quantidade_de_projetos_por_docente2013[i];
					denominacao2014[4] += quantidade_de_projetos_por_docente2014[i];
					denominacao2015[4] += quantidade_de_projetos_por_docente2015[i];
					denominacao2016[4] += quantidade_de_projetos_por_docente2016[i];
					denominacao2017[4] += quantidade_de_projetos_por_docente2017[i];
					
					break;
				case "ClasseD-Associado":
					qtd_denominacao[5]++;
					denominacao[5] += quantidade_de_projetos_por_docente[i];
					
					denominacao2013[5] += quantidade_de_projetos_por_docente2013[i];
					denominacao2014[5] += quantidade_de_projetos_por_docente2014[i];
					denominacao2015[5] += quantidade_de_projetos_por_docente2015[i];
					denominacao2016[5] += quantidade_de_projetos_por_docente2016[i];
					denominacao2017[5] += quantidade_de_projetos_por_docente2017[i];
					
					break;
				case "ClasseE-Titular":
					qtd_denominacao[6]++;
					denominacao[6] += quantidade_de_projetos_por_docente[i];
					
					denominacao2013[6] += quantidade_de_projetos_por_docente2013[i];
					denominacao2014[6] += quantidade_de_projetos_por_docente2014[i];
					denominacao2015[6] += quantidade_de_projetos_por_docente2015[i];
					denominacao2016[6] += quantidade_de_projetos_por_docente2016[i];
					denominacao2017[6] += quantidade_de_projetos_por_docente2017[i];
					
					break;
		
				default:
					break;
			  }
		  }
		  
	    GraficoPizzaDocente pizza = new GraficoPizzaDocente("Produtividade pela progressão de Docentes", "Relação entre Pesquisa e Progressões de Docentes", denominacao);
	    pizza.pack();
	    pizza.setVisible(true); 
	    

		GraficoBarraAnos barra = new GraficoBarraAnos("Pesquisas", "Projetos de Pesquisa Por Ano", crescimento_pesquisa_ano);
	    	barra.pack( );        
	    	RefineryUtilities.centerFrameOnScreen(barra);        
	    	barra.setVisible( true ); 
	    	
		GraficoPizzaDocente pizza2 = new GraficoPizzaDocente("Produtividade pela progressão de Docentes ano 2013", "Relação entre Pesquisa e Progressões de Docentes ano 2013", denominacao2013);
		pizza2.pack();
		pizza2.setVisible(true); 
		
		GraficoPizzaDocente pizza3 = new GraficoPizzaDocente("Produtividade pela progressão de Docentes ano 2014", "Relação entre Pesquisa e Progressões de Docentes ano 2014", denominacao2014);
		pizza3.pack();
		pizza3.setVisible(true); 
		
		
		GraficoPizzaDocente pizza4 = new GraficoPizzaDocente("Produtividade pela progressão de Docentes ano 2015", "Relação entre Pesquisa e Progressões de Docentes ano 2015", denominacao2015);
		pizza4.pack();
		pizza4.setVisible(true); 	
		
		GraficoPizzaDocente pizza5 = new GraficoPizzaDocente("Produtividade pela progressão de Docentes ano 2016", "Relação entre Pesquisa e Progressões de Docentes ano 2016", denominacao2016);
		pizza5.pack();
		pizza5.setVisible(true); 	
		
		
		GraficoPizzaDocente pizza6 = new GraficoPizzaDocente("Produtividade pela progressão de Docentes ano 2017", "Relação entre Pesquisa e Progressões de Docentes ano 2017", denominacao2017);
		pizza6.pack();
		pizza6.setVisible(true); 			
	    	
//	    	for (int i = 0; i < qtd_denominacao.length; i++) {
//	    			double g = denominacao2013[i]/qtd_denominacao[i];
//				System.out.println(denominacao2013[i]+" " + qtd_denominacao[i]+"  "+g);
//			}
	}
}
