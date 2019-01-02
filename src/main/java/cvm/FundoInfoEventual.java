package cvm;

import java.util.Date;

import jports.adapters.DigitsOnlyToLong;
import jports.text.CsvColumn;
import jports.text.CsvTable;

/**
 * Fundos de Investimento: Documentos: Eventuais, DFs e Demonstrativos
 * Trimestrais
 * 
 * O conjunto de dados disponibiliza os Documentos Eventuais, Demonstrações
 * Financeiras (DFs) e Demonstrativos Trimestrais de Fundos de Investimento nos
 * últimos cinco anos.
 * 
 * São Documentos Eventuais:
 * 
 * Ata de Assembléia Geral Ordinária (AGO)
 * 
 * Edital de Convocação para Ass. Geral Ordinária (EDITAL AGO)
 * 
 * Outros
 * 
 * Fato Relevante
 * 
 * Demonstrações Contábeis
 * 
 * Regulamento de Fundos
 * 
 * Prospecto de Fundos
 * 
 * Prospecto de Distribuição
 * 
 * Aviso ao Mercado
 * 
 * Proposta do Administrador
 * 
 * Relatório de Classificação de Risco
 * 
 * Anúncio de Início de Distribuição (AID)
 * 
 * Anúncio de Encerramento de Distribuição (AED)
 * 
 * Os arquivos referentes aos anos corrente e anterior serão atualizados
 * semanalmente com as eventuais reapresentações.
 * 
 * Os arquivos referentes aos anos A-2, ..., A-4 serão atualizados mensalmente
 * com as eventuais reapresentações.
 * 
 * 
 * @author rportela
 *
 */
@CsvTable(separator = ";", charset = "windows-1252", firstRowHasNames = true)
public class FundoInfoEventual {

	/**
	 * Tipo de fundo - Alfanumérico - varchar; Precisão: 15; Scale: 0
	 */
	@CsvColumn(name = "TP_FUNDO")
	public String fundo_tipo;

	/**
	 * Descrição: CNPJ do fundo - Alfanumérico - varchar; Precisão: 20; Scale: 0
	 */
	@CsvColumn(name = "CNPJ_FUNDO", adapter = DigitsOnlyToLong.class)
	public long cnpj;

	/**
	 * Denominação Social - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "DENOM_SOCIAL")
	public String razao_social;

	/**
	 * date; Precisão: 10; Scale: 0
	 */
	@CsvColumn(name = "DT_COMPTC", pattern = "yyyy-MM-dd")
	public Date competencia;

	/**
	 * date; Precisão: 10 Scale: 0
	 */
	@CsvColumn(name = "DT_RECEB", pattern = "yyyy-MM-dd")
	public Date arquivo_data;

	/**
	 * varchar; Precisão: 15 Scale: 0
	 */
	@CsvColumn(name = "TP_DOC")
	public String arquivo_tipo;

	/**
	 * varchar; Precisão: 100 Scale: 0
	 */
	@CsvColumn(name = "NM_ARQ")
	public String arquivo_nome;

	/**
	 * varchar; Precisão: 141 Scale: 0
	 */
	@CsvColumn(name = "LINK_ARQ")
	public String arquivo_link;

}
