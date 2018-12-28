package cvm;

import java.util.Date;

import jports.adapters.DateAdapter;
import jports.text.CsvColumn;
import jports.text.CsvTable;

/**
 * Bloco 4: DEMAIS ATIVOS CODIFICADOS (As aplicações dos fundos estão
 * organizadas nos arquivos de dados conforme a estrutura de blocos do padrão
 * XML do Informe CDA).
 * 
 * @author rportela
 *
 */
@CsvTable(
		separator = ";",
		charset = "windows-1252",
		firstRowHasNames = true)
public class FundoCarteiraItemAtivoCodificado extends FundoCarteiraItem {

	/**
	 * Código do ativo - varchar(100)
	 */
	@CsvColumn(
			name = "CD_ATIVO")
	public String ativo_codigo;

	/**
	 * Descrição do ativo - varchar(100)
	 */
	@CsvColumn(
			name = "DS_ATIVO")
	public String ativo_descricao;

	/**
	 * Código ISIN (International Securities Identification Number) - varchar(12)
	 */
	@CsvColumn(
			name = "CD_ISIN")
	public String isin;

	/**
	 * Data início da vigência
	 */
	@CsvColumn(
			name = "DT_INI_VIGENCIA",
			adapter = DateAdapter.class,
			pattern = "yyyy-MM-dd")
	public Date vigencia_inicio;

	/**
	 * Data fim da vigência
	 */
	@CsvColumn(
			name = "DT_FIM_VIGENCIA",
			adapter = DateAdapter.class,
			pattern = "yyyy-MM-dd")
	public Date vigencia_fim;

	/**
	 * Bloco 4: DEMAIS ATIVOS CODIFICADOS
	 */
	@Override
	public FundoCarteiraItemTipo getTipoItem() {
		return FundoCarteiraItemTipo.ATIVOS_CODIFICADOS;
	}

}
