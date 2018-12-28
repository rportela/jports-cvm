package cvm;

import java.util.Date;

import jports.adapters.BooleanAsSpecificString;
import jports.adapters.DateAdapter;
import jports.adapters.DigitsOnlyToLong;
import jports.text.CsvColumn;
import jports.text.CsvTable;

/**
 * Bloco 5: DEPÓSITOS A PRAZO E OUTROS TÍTULOS DE IF (As aplicações dos fundos
 * estão organizadas nos arquivos de dados conforme a estrutura de blocos do
 * padrão XML do Informe CDA).
 * 
 * @author rportela
 *
 */
@CsvTable(
		separator = ";",
		charset = "windows-1252",
		firstRowHasNames = true)
public class FundoCarteiraItemDepositoAPrazo extends FundoCarteiraItem {

	/**
	 * CNPJ do emissor
	 */
	@CsvColumn(
			name = "CNPJ_EMISSOR",
			adapter = DigitsOnlyToLong.class)
	public Long emissor_cnpj;

	/**
	 * Nome do Emissor - varchar(200)
	 */
	@CsvColumn(
			name = "EMISSOR")
	public String emissor_nome;

	/**
	 * Data de vencimento
	 */
	@CsvColumn(
			name = "DT_VENC",
			adapter = DateAdapter.class,
			pattern = "yyyy-MM-dd")
	public Date vencimento;

	/**
	 * Indica se é título pós-fixado
	 */
	@CsvColumn(
			name = "TITULO_POSFX",
			adapter = BooleanAsSpecificString.class,
			pattern = "S")
	public Boolean pos_fixado;

	/**
	 * Código do indexador (somente pós-fixados) - varchar(50)
	 */
	@CsvColumn(
			name = "CD_INDEXADOR_POSFX")
	public String pos_fixado_indexador_codigo;

	/**
	 * Descrição do indexador (somente pós-fixados) - varchar(100)
	 */
	@CsvColumn(
			name = "DS_INDEXADOR_POSFX")
	public String pos_fixado_indexador_nome;

	/**
	 * Percentual do indexador (somente pós-fixados)
	 */
	@CsvColumn(
			name = "PR_INDEXADOR_POSFX")
	public Double pos_fixado_indexador_percentual;

	/**
	 * Percentual do cupom (somente pós-fixados)
	 */
	@CsvColumn(
			name = "PR_CUPOM_POSFX")
	public Double pos_fixado_cupom;

	/**
	 * Percentual da taxa contratada (somente pré-fixados)
	 */
	@CsvColumn(
			name = "PR_TAXA_PREFX")
	public Double pre_fixado_taxa;

	/**
	 * Indica se o emissor do título possui classificação de risco
	 */
	@CsvColumn(
			name = "RISCO_EMISSOR",
			adapter = BooleanAsSpecificString.class,
			pattern = "S")
	public Boolean risco;

	/**
	 * Nome da agência de classificação de risco
	 */
	@CsvColumn(
			name = "AG_RISCO")
	public String risco_agencia;

	/**
	 * Data da classificação de risco
	 */
	@CsvColumn(
			name = "DT_RISCO",
			adapter = DateAdapter.class,
			pattern = "yyyy-MM-dd")
	public Date risco_data;

	/**
	 * Grau de risco atribuído - varchar(6)
	 */
	@CsvColumn(
			name = "GRAU_RISCO")
	public String risco_grau;

	/**
	 * Bloco 5: DEPÓSITOS A PRAZO E OUTROS TÍTULOS DE IF
	 */
	@Override
	public FundoCarteiraItemTipo getTipoItem() {
		return FundoCarteiraItemTipo.DEPOSITOS_A_PRAZO_E_OUTROS_TITULOS_DE_IF;
	}
}
