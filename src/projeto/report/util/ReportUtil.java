package projeto.report.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import com.lowagie.text.pdf.codec.Base64.InputStream;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class ReportUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private static final String UNDERLINE = "_";
	private static final String PONTO = ".";
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private static final String EXTESION_ODS = "ods";
	private static final String EXTESION_XLS = "xls";
	private static final String EXTESION_HTML = "html";
	private static final String EXTESION_PDF = "pdf";
	private String SEPARATOR = File.separator;
	private static final int RELATORIO_PDF = 1;
	private static final int RELATORIO_EXCEL = 2;
	private static final int RELATORIO_HTML = 3;
	private static final int RELATORIO_PLANILHA_OPEN_OFFICE = 4;
	private StreamedContent arquivoRetorno = null;
	private String caminhoArquivoRelatorio = null;
	private JRExporter tipoArquivoExportado = null;
	private String extensaoArquivoExportado = "";
	private String caminhoSubreport_dir = "";
	private File arquivoGerado = null;
	
	
	public StreamedContent geraRelatorio(List<?> listDataBeanCollectionReport, HashMap parametrosRelatorio, String nomeRelatorioJasper, 
			String nomeRelatorioSaida, int tipoRelatorio) throws Exception {
		
		//Cria a lista de collectionsDataSource de beans que carregam os dados para o relat�rio
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listDataBeanCollectionReport);
		
		
		/*
		 * Fornece o caminho fisico at� a pasta que cont�m os relatorios compilados .jasper
		 */
		FacesContext context = FacesContext.getCurrentInstance();
		context.responseComplete();
		ServletContext scontext = (ServletContext) context.getExternalContext().getContext();
		
		String caminhoRelatorio = scontext.getRealPath(FOLDER_RELATORIOS);
		//ex: c:/aplicacao/relatorios/rel_bairro.jasper
		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + PONTO + "jasper");
		
		//fazer uma verifica��o caso gere um war e o projeto rodar em outro local, verificar se existe o caminho.
		if(caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) && !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}
		
		//Caminho para imagens no relatorio
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		//caminho completo at� o relatorio compilado indicado
		String caminhoArquivoJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + PONTO + "jasper";
		
		//Faz o carregamento do relatorio indicado
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);
		
		//Seta parametro SUBREPORT_DIR como caminho fisico para sub-reports
		caminhoSubreport_dir = caminhoRelatorio + SEPARATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubreport_dir);
		
		//Carrega o arquivo compilado para a memoria
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
		
		//Verificar o formato que ser� impresso o relatorio
		switch (tipoRelatorio) {
		case RELATORIO_PDF:
			tipoArquivoExportado = new JRPdfExporter();
			extensaoArquivoExportado = EXTESION_PDF;
			break;
		case RELATORIO_HTML:
			tipoArquivoExportado = new JRHtmlExporter();
			extensaoArquivoExportado = EXTESION_HTML;
			break;
		case RELATORIO_EXCEL:
			tipoArquivoExportado = new JRXlsExporter();
			extensaoArquivoExportado = EXTESION_XLS;
			break;
		case RELATORIO_PLANILHA_OPEN_OFFICE:
			tipoArquivoExportado = new JROdtExporter();
			extensaoArquivoExportado = EXTESION_ODS;
			break;

		default:		
			tipoArquivoExportado = new JRPdfExporter();
			extensaoArquivoExportado = EXTESION_PDF;
			break;
		}
		
		
		nomeRelatorioSaida += UNDERLINE + DateUtils.getDateAtualReportName();
		
		//Caminho do relatorio exportado
		caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + PONTO + extensaoArquivoExportado;
		
		//Cria novo file exportado
		arquivoGerado = new File(caminhoArquivoRelatorio);
		
		//Prepara a impressao
		tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		
		//Nome do arquivo fisico a ser impresso/exportado
		tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		//Executa a exporta��o
		tipoArquivoExportado.exportReport();
		
		//Remove o arquivo do servidor ap�s ser feito o download pelo usu�rio para n��o consumir memoria do servidor
		arquivoGerado.deleteOnExit();
		
		//Cria o inputstream para ser usado pelo PrimeFaces
		java.io.InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
		
		//Faz o retorno para aplica��o
		arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio,"application/"+extensaoArquivoExportado, nomeRelatorioSaida + PONTO + extensaoArquivoExportado);
		
		return arquivoRetorno;
				
		
	}
	

}
