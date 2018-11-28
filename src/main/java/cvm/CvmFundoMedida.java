package cvm;

import java.util.Date;

import jports.adapters.DigitsOnlyToLong;
import jports.text.CsvColumn;
import jports.text.CsvTable;

/**
 * 
 * Fundos Estruturados: Medidas
 * 
 * Medidas de fundos estruturados (FIP, FIDC, FII, etc.): Patrimônio Líquido e
 * Número de Cotistas.
 * 
 * O conjunto de dados disponibiliza as medidas referentes aos últimos doze
 * meses.
 * 
 * Os arquivos referentes ao último mês completo (M-1) e anterior (M-2) serão
 * atualizados semanalmente com as eventuais reapresentações.
 * 
 * Os arquivos referente aos meses M-3, M-4, ..., até M-12 serão atualizados
 * mensalmente com as eventuais reapresentações.
 * 
 * @author rportela
 *
 */
@CsvTable(separator = ";", charset = "windows-1252", firstRowHasNames = true)
public class CvmFundoMedida {

	/**
	 * Tipo de fundo - Alfanumérico - varchar; Precisão: 15; Scale: 0
	 */
	@CsvColumn(name = "TP_FUNDO")
	public String fundo_tipo;

	/**
	 * CNPJ do fundo - Alfanumérico - varchar; Precisão: 20; Scale: 0
	 */
	@CsvColumn(name = "CNPJ_FUNDO", adapter = DigitsOnlyToLong.class)
	public long cnpj;

	/**
	 * Denominação Social - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "DENOM_SOCIAL")
	public String razao_social;

	/**
	 * Data de competência do documento - date; Precisão: 10; Scale: 0
	 */
	@CsvColumn(name = "DT_COMPTC", pattern = "yyyy-MM-dd")
	public Date competencia;

	/**
	 * Valor do patrimônio líquido - Numérico - numeric; Precisão: 23; Scale: 2
	 */
	@CsvColumn(name = "VL_PATRIM_LIQ")
	public Double pl;

	/**
	 * Número de cotistas - Numérico - numeric; Precisão: 33; Scale: 0
	 */
	@CsvColumn(name = "NR_COTST")
	public Integer cotistas;

}
