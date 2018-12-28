package cvm;

import java.util.Date;

import jports.adapters.BooleanAsSpecificString;
import jports.adapters.DateAdapter;
import jports.adapters.DigitsOnlyToLong;
import jports.text.CsvColumn;

/**
 * Itens comuns a todos os layouts de informações de itens de carteira de fundos
 * de investimento disponibilizados no portal de dados abertos da CVM.
 * 
 * @author rportela
 *
 */
public abstract class FundoCarteiraItem {

	/**
	 * Tipo de fundo - Alfanumérico - varchar(15)
	 */
	@CsvColumn(
			name = "TP_FUNDO")
	public String fundo_tipo;

	/**
	 * CNPJ do fundo - Alfanumérico - varchar(20)
	 */
	@CsvColumn(
			name = "CNPJ_FUNDO",
			adapter = DigitsOnlyToLong.class)
	public Long cnpj;

	/**
	 * Denominação Social - Alfanumérico - varchar(100)
	 */
	@CsvColumn(
			name = "DENOM_SOCIAL")
	public String razao_social;

	/**
	 * Data de competência do documento
	 */
	@CsvColumn(
			name = "DT_COMPTC",
			adapter = DateAdapter.class,
			pattern = "yyyy-MM-dd")
	public Date competencia;

	/**
	 * Tipo de aplicação - Alfanumérico - varchar(150)
	 */
	@CsvColumn(
			name = "TP_APLIC")
	public String aplicacao_tipo;

	/**
	 * Tipo de ativo - Alfanumérico - varchar(150)
	 */
	@CsvColumn(
			name = "TP_ATIVO")
	public String ativo_tipo;

	/**
	 * Indica se o emissor da aplicação é ligado ao gestor ou administrador do fundo
	 * de investimento
	 */
	@CsvColumn(
			name = "EMISSOR_LIGADO",
			adapter = BooleanAsSpecificString.class,
			pattern = "S")
	public Boolean emissor_ligado;

	/**
	 * Tipo de negociação - Para negociação/Mantido até o vencimento - varchar(24)
	 */
	@CsvColumn(
			name = "TP_NEGOC")
	public String negociacao_tipo;

	/**
	 * Quantidade de vendas dos negócios realizados no mês - Numeric(21, 6)
	 */
	@CsvColumn(
			name = "QT_VENDA_NEGOC")
	public Double venda_quantidade;

	/**
	 * Valor das vendas dos negócios realizados no mês - Numeric(17, 2)
	 */
	@CsvColumn(
			name = "QT_VENDA_NEGOC")
	public Double venda_valor;

	/**
	 * Quantidade de aquisições dos negócios realizados no mês - Numeric(21, 6)
	 */
	@CsvColumn(
			name = "QT_AQUIS_NEGOC")
	public Double compra_quantidade;

	/**
	 * Valor das aquisições dos negócios realizados no mês - Numeric(17, 2)
	 */
	@CsvColumn(
			name = "VL_AQUIS_NEGOC")
	public Double compra_valor;

	/**
	 * Quantidade da posição final - Numeric(21, 6)
	 */
	@CsvColumn(
			name = "QT_POS_FINAL")
	public Double posicao_final_quantidade;

	/**
	 * Valor de mercado da posição final - Numeric(17, 2)
	 */
	@CsvColumn(
			name = "VL_MERC_POS_FINAL")
	public Double posicao_final_valor;

	/**
	 * Valor de custo da posição final - Numeric(17,2)
	 */
	@CsvColumn(
			name = "VL_CUSTO_POS_FINAL")
	public Double posicao_final_custo;

	/**
	 * Prazo de confidencialidade da aplicação
	 */
	@CsvColumn(
			name = "DT_CONFID_APLIC",
			adapter = DateAdapter.class,
			pattern = "yyyy-MM-dd")
	public Date prazo_confidencialidade;

	/**
	 * Retorna o tipo de item da carteira do fundo de investimento;
	 * 
	 * @return
	 */
	public abstract FundoCarteiraItemTipo getTipoItem();
}
