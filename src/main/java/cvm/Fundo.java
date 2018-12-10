package cvm;

import java.util.Date;

import jports.adapters.BooleanAsSpecificString;
import jports.adapters.DateAdapter;
import jports.adapters.DigitsOnlyToLong;
import jports.adapters.DoubleAdapter;
import jports.text.CsvColumn;
import jports.text.CsvTable;

@CsvTable(separator = ";", firstRowHasNames = true, charset = "windows-1252")
public class Fundo {

	/**
	 * Tipo de fundo - Alfanumérico - varchar; Precisão: 15; Scale: 0
	 */
	@CsvColumn(name = "TP_FUNDO")
	public String tipo_fundo;

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
	 * Data de registro - date; Precisão: 10; Scale: 0
	 */
	@CsvColumn(name = "DT_REG", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date registro_data;

	/**
	 * Data de constituição - date; Precisão: 10; Scale: 0
	 */
	@CsvColumn(name = "DT_CONST", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date constituicao_data;

	/**
	 * Data de cancelamento - date; Precisão: 10; Scale: 0
	 */
	@CsvColumn(name = "DT_CANCEL", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date cancelamento_data;

	/**
	 * Situação - Alfanumérico - varchar; Precisão: 40; Scale: 0
	 */
	@CsvColumn(name = "SIT")
	public String situacao;

	/**
	 * Data início da situação - date; Precisão: 10; Scale: 0
	 */
	@CsvColumn(name = "DT_INI_SIT", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date situacao_data;

	/**
	 * Data início do exercício social - date; Precisão: 10; Scale: 0
	 */
	@CsvColumn(name = "DT_INI_EXERC", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date exercicio_inicio;

	/**
	 * Data fim do exercício social - date; Precisão: 10; Scale: 0
	 */
	@CsvColumn(name = "DT_FIM_EXERC", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date exercicio_fim;

	/**
	 * Classe Domínio - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "CLASSE")
	public String classe;

	/**
	 * Data de início na classe - date; Precisão: 10 Scale: 0
	 */
	@CsvColumn(name = "DT_INI_CLASSE", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date classe_data;

	/**
	 * Forma de rentabilidade do fundo (indicador de desempenho) - Alfanumérico -
	 * varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "RENTAB_FUNDO")
	public String rentabilidade;

	/**
	 * Forma de condomínio - Aberto/Fechado - char; Precisão: 15; Scale: 0
	 */
	@CsvColumn(name = "CONDOM", adapter = BooleanAsSpecificString.class, pattern = "Aberto")
	public Boolean fundo_aberto;

	/**
	 * Indica se é fundo de cotas - S/N - varchar; Precisão: 1; Scale: 0
	 */
	@CsvColumn(name = "FUNDO_COTAS", adapter = BooleanAsSpecificString.class, pattern = "S")
	public Boolean fundo_cotas;

	/**
	 * Indica se é fundo exclusivo - S/N - char; Precisão: 1; Scale: 0
	 */
	@CsvColumn(name = "FUNDO_EXCLUSIVO", adapter = BooleanAsSpecificString.class, pattern = "S")
	public Boolean fundo_exclusivo;

	/**
	 * Indica se possui tributação de longo prazo - S/N - char; Precisão: 1;Scale: 0
	 */
	@CsvColumn(name = "TRIB_LPRAZO", adapter = BooleanAsSpecificString.class, pattern = "S")
	public Boolean tributacao_longo_prazo;

	/**
	 * Indica se é destinado a investidores qualificados Domínio: S/N Tipo dados:
	 * char Precisão: 1 Scale: 0
	 */
	@CsvColumn(name = "INVEST_QUALIF", adapter = BooleanAsSpecificString.class, pattern = "S")
	public Boolean investidores_qualificados;

	/**
	 * Taxa de performance - Alfanumérico - varchar; Precisão: 431; Scale: 0
	 */
	@CsvColumn(name = "TAXA_PERFM")
	public String performance_descricao;

	/**
	 * Valor do patrimônio líquido Domínio - Numérico - numeric; Precisão: 23;
	 * Scale: 2
	 */
	@CsvColumn(name = "VL_PATRIM_LIQ", adapter = DoubleAdapter.class)
	public Double pl;

	/**
	 * Data do patrimônio líquido - date; Precisão: 10; Scale: 0
	 */
	@CsvColumn(name = "DT_PATRIM_LIQ", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date pl_data;

	/**
	 * Nome do Diretor Responsável - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "DIRETOR")
	public String diretor;

	/**
	 * CNPJ do Administrador - Alfanumérico - varchar; Precisão: 20; Scale: 0
	 */
	@CsvColumn(name = "CNPJ_ADMIN", adapter = DigitsOnlyToLong.class)
	public Long administrador_cnpj;

	/**
	 * Nome do Administrador - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "ADMIN")
	public String administrdor_nome;

	/**
	 * Indica se o gestor é pessoa física ou jurídica - PF/PJ - varchar; Precisão:
	 * 2; Scale: 0
	 */
	@CsvColumn(name = "PF_PJ_GESTOR", adapter = BooleanAsSpecificString.class, pattern = "PF")
	public boolean gestor_pf;

	/**
	 * Informa o código de identificação do gestor pessoa física ou jurídica -
	 * Alfanumérico - varchar; Precisão: 20; Scale: 0
	 */
	@CsvColumn(name = "CPF_CNPJ_GESTOR", adapter = DigitsOnlyToLong.class)
	public Long gestor_cnpj;

	/**
	 * Nome do Gestor - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "GESTOR")
	public String gestor_nome;

	/**
	 * CNPJ do Auditor - Alfanumérico - varchar; Precisão: 20; Scale: 0
	 */
	@CsvColumn(name = "CNPJ_AUDITOR", adapter = DigitsOnlyToLong.class)
	public Long auditor_cnpj;

	/**
	 * Nome do Auditor - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "AUDITOR")
	public String auditor_nome;

}
