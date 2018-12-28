package cvm;

import jports.text.CsvColumn;
import jports.text.CsvTable;

/**
 * Bloco 3: SWAP (As aplicações dos fundos estão organizadas nos arquivos de
 * dados conforme a estrutura de blocos do padrão XML do Informe CDA.)
 * 
 * @author rportela
 *
 */
@CsvTable(
		separator = ";",
		charset = "windows-1252",
		firstRowHasNames = true)
public class FundoCarteiraItemSwap extends FundoCarteiraItem {

	/**
	 * Código SWAP - varchar(50)
	 */
	@CsvColumn(
			name = "CD_SWAP")
	public String swap_codigo;

	/**
	 * Descrição do tipo de ativo SWAP - varchar(100)
	 */
	@CsvColumn(
			name = "DS_SWAP")
	public String swap_nome;

	@Override
	public FundoCarteiraItemTipo getTipoItem() {
		return FundoCarteiraItemTipo.SWAP;
	}

}
