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
		  
		  // Conta quantos projetos de pesquisa um professor x tem.
		  for (int i = 1; i < docentes_csv.size(); i++) {
			quantidade_de_projetos_por_docente[i] = 0;
			for (int j = 1; j < bolsistas_csv.size(); j++) {
				if (bolsistas_csv.get(j)[0] != bolsistas_csv.get(j-1)[0]) {
					if (docentes_csv.get(i)[0].equals(bolsistas_csv.get(j)[7])) {
						quantidade_de_projetos_por_docente[i]++;
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
					denominacao[0] += quantidade_de_projetos_por_docente[i];
					break;
				case "ClasseA-AssistenteA":
					denominacao[1] += quantidade_de_projetos_por_docente[i];
					break;
				case "ClasseA-Auxiliar":
					denominacao[2] += quantidade_de_projetos_por_docente[i];
					break;
				case "ClasseB-Assistente":
					denominacao[3] += quantidade_de_projetos_por_docente[i];
					break;	
				case "ClasseC-Adjunto":
					denominacao[4] += quantidade_de_projetos_por_docente[i];
					break;
				case "ClasseD-Associado":
					denominacao[5] += quantidade_de_projetos_por_docente[i];
					break;
				case "ClasseE-Titular":
					denominacao[6] += quantidade_de_projetos_por_docente[i];
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
	}
}
