package cvm;

import jports.adapters.BooleanAsSpecificString;
import jports.adapters.DigitsOnlyToLong;
import jports.text.CsvColumn;
import jports.text.CsvTable;

/**
 * Bloco 8: DEMAIS ATIVOS NÃO CODIFICADOS (As aplicações dos fundos estão
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
public class FundoCarteiraItemNaoCodificado extends FundoCarteiraItem {

	/**
	 * Descrição do ativo - varchar(1000)
	 */
	@CsvColumn(
			name = "DS_ATIVO")
	public String ativo_nome;

	/**
	 * Indica se o emissor é pessoa física ou jurídica
	 */
	@CsvColumn(
			name = "PF_PJ_EMISSOR",
			adapter = BooleanAsSpecificString.class,
			pattern = "PF")
	public Boolean emissor_pf;

	/**
	 * Informa o código de identificação do emissor, pessoa física ou jurídica
	 */
	@CsvColumn(
			name = "CPF_CNPJ_EMISSOR",
			adapter = DigitsOnlyToLong.class)
	public Long emissor_cpf_cnpj;

	/**
	 * Nome do Emissor - varchar(150)
	 */
	@CsvColumn(
			name = "EMISSOR")
	public String emissor_nome;

	/**
	 * Bloco 8: DEMAIS ATIVOS NÃO CODIFICADOS
	 */
	@Override
	public FundoCarteiraItemTipo getTipoItem() {
		return FundoCarteiraItemTipo.ATIVOS_NAO_CODIFICADOS;
	}

}
