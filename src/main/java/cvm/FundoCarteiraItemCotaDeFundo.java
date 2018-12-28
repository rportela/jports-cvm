package cvm;

import jports.adapters.DigitsOnlyToLong;
import jports.text.CsvColumn;
import jports.text.CsvTable;

/**
 * As aplicações dos fundos estão organizadas nos arquivos de dados conforme a
 * estrutura de blocos do padrão XML do Informe CDA.
 * 
 * Bloco 2: COTAS DE FUNDOS DE INVESTIMENTO
 * 
 * @author rportela
 *
 */
@CsvTable(
		separator = ";",
		charset = "windows-1252",
		firstRowHasNames = true)
public class FundoCarteiraItemCotaDeFundo extends FundoCarteiraItem {

	/**
	 * CNPJ do fundo investido - varchar(20)
	 */
	@CsvColumn(
			name = "CNPJ_FUNDO_COTA",
			adapter = DigitsOnlyToLong.class)
	public Long cnpj_fundo_cota;

	/**
	 * Denominação social do fundo investido - varchar(100)
	 */
	@CsvColumn(
			name = "NM_FUNDO_COTA")
	public String nome_fundo_cota;

	/**
	 * BLOCO 2 - Cotas de fundos de investimento
	 */
	@Override
	public FundoCarteiraItemTipo getTipoItem() {
		return FundoCarteiraItemTipo.COTAS_DE_FUNDOS;
	}

}
