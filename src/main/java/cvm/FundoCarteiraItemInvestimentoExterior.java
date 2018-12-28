package cvm;

import java.util.Date;

import jports.adapters.BooleanAsSpecificString;
import jports.adapters.DateAdapter;
import jports.text.CsvColumn;
import jports.text.CsvTable;

/**
 * Bloco 7: INVESTIMENTO NO EXTERIOR (As aplicações dos fundos estão organizadas
 * nos arquivos de dados conforme a estrutura de blocos do padrão XML do Informe
 * CDA).
 * 
 * @author rportela
 *
 */
@CsvTable(
		separator = ";",
		charset = "windows-1252",
		firstRowHasNames = true)
public class FundoCarteiraItemInvestimentoExterior extends FundoCarteiraItem {

	/**
	 * Indica se é veículo de investimento coletivo.
	 */
	@CsvColumn(
			name = "INVEST_COLETIVO",
			adapter = BooleanAsSpecificString.class,
			pattern = "S")
	public Boolean investimento_coletivo;

	/**
	 * Indica se a gestão da carteira do veículo de investimento coletivo conta com
	 * influência, direta ou indireta, do gestor.
	 */
	@CsvColumn(
			name = "INVEST_COLETIVO_GESTOR",
			adapter = BooleanAsSpecificString.class,
			pattern = "S")
	public Boolean investimento_coletivo_gestor;

	/**
	 * Nome do Emissor - varchar(200)
	 */
	@CsvColumn(
			name = "EMISSOR")
	public String emissor;

	/**
	 * Data de vencimento.
	 */
	@CsvColumn(
			name = "DT_VENC",
			adapter = DateAdapter.class,
			pattern = "yyyy-MM-dd")
	public Date vencimento;

	/**
	 * Código do país - varchar(3)
	 */
	@CsvColumn(
			name = "CD_PAIS")
	public String pais;

	/**
	 * Código da Bolsa ou Mercado de balcão - varchar(6)
	 */
	@CsvColumn(
			name = "CD_BV_MERC")
	public String mercado_codigo;

	/**
	 * Bolsa ou Mercado de balcão - varchar(12)
	 */
	@CsvColumn(
			name = "BV_MERC")
	public String mercado_nome;

	/**
	 * Código do ativo na Bolsa ou Mercado de balcão onde foi adquirido -
	 * varchar(12)
	 */
	@CsvColumn(
			name = "CD_ATIVO_BV_MERC")
	public String ativo_codigo;

	/**
	 * Indica se o emissor do título possui classificação de risco
	 */
	@CsvColumn(
			name = "RISCO_EMISSOR",
			adapter = BooleanAsSpecificString.class,
			pattern = "S")
	public Boolean risco;

	/**
	 * Nome da agência de classificação de risco - varchar(200)
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
	 * Descrição do ativo no exterior - varchar(50)
	 */
	@CsvColumn(
			name = "DS_ATIVO_EXTERIOR")
	public String ativo_nome;

	/**
	 * Quantidade de ativos no exterior
	 */
	@CsvColumn(
			name = "QT_ATIVO_EXTERIOR")
	public Double ativo_quantidade;

	/**
	 * Valor do ativo no exterior
	 */
	@CsvColumn(
			name = "VL_ATIVO_EXTERIOR")
	public Double ativo_valor;

	/**
	 * Bloco 7: INVESTIMENTO NO EXTERIOR
	 */
	@Override
	public FundoCarteiraItemTipo getTipoItem() {
		return FundoCarteiraItemTipo.INVESTIMENTOS_NO_EXTERIOR;
	}

}
