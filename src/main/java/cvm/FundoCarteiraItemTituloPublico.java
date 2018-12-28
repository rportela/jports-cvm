package cvm;

import java.util.Date;

import jports.adapters.DateAdapter;
import jports.text.CsvColumn;
import jports.text.CsvTable;

/**
 * As aplicações dos fundos estão organizadas nos arquivos de dados conforme a
 * estrutura de blocos do padrão XML do Informe CDA.
 * 
 * Bloco 1: TÍTULOS PÚBLICOS DO SELIC
 * 
 * @author rportela
 *
 */
@CsvTable(
		separator = ";",
		charset = "windows-1252",
		firstRowHasNames = true)
public class FundoCarteiraItemTituloPublico extends FundoCarteiraItem {

	/**
	 * Tipo de título público - varchar(50)
	 */
	@CsvColumn(
			name = "TP_TITPUB")
	public String titulo_publico_tipo;

	/**
	 * Código ISIN (International Securities Identification Number) - varchar(12)
	 */
	@CsvColumn(
			name = "CD_ISIN")
	public String isin;

	/**
	 * Código SELIC - varchar(6)
	 */
	@CsvColumn(
			name = "CD_SELIC")
	public String codigo_selic;

	/**
	 * Data de emissão
	 */
	@CsvColumn(
			name = "DT_EMISSAO",
			adapter = DateAdapter.class,
			pattern = "yyyy-MM-dd")
	public Date emissao;

	/**
	 * Data de vencimento
	 */
	@CsvColumn(
			name = "DT_VENC",
			adapter = DateAdapter.class,
			pattern = "yyyy-MM-dd")
	public Date vencimento;

	@Override
	public final FundoCarteiraItemTipo getTipoItem() {
		return FundoCarteiraItemTipo.TITULOS_PUBLICOS;
	}

}
