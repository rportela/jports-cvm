package cvm;

import java.util.Date;

import jports.adapters.BooleanAsSpecificString;
import jports.adapters.DateAdapter;
import jports.adapters.DigitsOnlyToLong;
import jports.text.CsvColumn;
import jports.text.CsvTable;

/**
 * Bloco 6: TÍTULOS DO AGRONEGÓCIO E DE CRÉDITO PRIVADO (As aplicações dos
 * fundos estão organizadas nos arquivos de dados conforme a estrutura de blocos
 * do padrão XML do Informe CDA).
 * 
 * @author rportela
 *
 */
@CsvTable(
		separator = ";",
		charset = "windows-1252",
		firstRowHasNames = true)
public class FundoCarteiraItemCreditoPrivado extends FundoCarteiraItem {

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
	 * Indica se é título pós-fixado.
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
	 * Indica se é título registrado na CETIP
	 */
	@CsvColumn(
			name = "TITULO_CETIP",
			adapter = BooleanAsSpecificString.class,
			pattern = "S")
	public Boolean cetip;

	/**
	 * Indica se o título possui garantia ou seguro
	 */
	@CsvColumn(
			name = "TITULO_GARANTIA",
			adapter = BooleanAsSpecificString.class,
			pattern = "S")
	public Boolean garantia;

	/**
	 * CNPJ da instituição financeira com coobrigação
	 */
	@CsvColumn(
			name = "CNPJ_INSTITUICAO_FINANC_COOBR",
			adapter = DigitsOnlyToLong.class)
	public Long coobrigacao_cnpj;

	/**
	 * Bloco 6: TÍTULOS DO AGRONEGÓCIO E DE CRÉDITO PRIVADO
	 */
	@Override
	public FundoCarteiraItemTipo getTipoItem() {
		return FundoCarteiraItemTipo.AGRONEGOCIO_CREDITO_PRIVADO;
	}

}
