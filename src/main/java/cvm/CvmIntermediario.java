package cvm;

import java.util.Date;

import jports.adapters.DateAdapter;
import jports.adapters.DigitsOnlyToLong;
import jports.adapters.IntegerAdapter;
import jports.text.CsvColumn;
import jports.text.CsvTable;

/**
 * Participantes Intermediários: Informação Cadastral
 * 
 * Dados cadastrais de Participantes Intermediários: Bancos Comerciais, Bancos
 * de Investimentos, Bancos Múltiplos, Caixas Econômicas, Cooperativas de
 * Crédito, Corretoras e Distribuidoras.
 * 
 * @author rportela
 *
 */
@CsvTable(separator = ";", charset = "windows-1252")
public class CvmIntermediario {

	/**
	 * Tipo de participante - Alfanumérico - varchar; Precisão: 50; Scale: 0
	 */
	@CsvColumn(name = "TP_PARTIC")
	public String tipo_participante;

	/**
	 * Cadastro Nacional de Pessoas Jurídicas - Alfanumérico - varchar; Precisão:
	 * 20; Scale: 0
	 */
	@CsvColumn(name = "CNPJ", adapter = DigitsOnlyToLong.class)
	public long cnpj;

	/**
	 * Denominação Social - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "DENOM_SOCIAL")
	public String razao_social;

	/**
	 * Denominação Comercial - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "DENOM_COMERC")
	public String nome;

	/**
	 * Data de registro - date; Precisão: 10 Scale: 0
	 */
	@CsvColumn(name = "DT_REG", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date registro_data;

	/**
	 * Data de cancelamento - date; Precisão: 10; Scale: 0
	 */
	@CsvColumn(name = "DT_CANCEL", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date cancelamento_data;

	/**
	 * Motivo de cancelamento - Alfanumérico - varchar; Precisão: 80; Scale: 0
	 */
	@CsvColumn(name = "MOTIVO_CANCEL")
	public String cancelamento_motivo;

	/**
	 * Situação - Alfanumérico - varchar; Precisão: 40;Scale: 0
	 */
	@CsvColumn(name = "SIT")
	public String situacao;

	/**
	 * Data início da situação - date; Precisão: 10; Scale: 0
	 */
	@CsvColumn(name = "DT_INI_SIT", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date situacao_data;

	/**
	 * Código CVM - Numérico - numeric; Precisão: 7; Scale: 0
	 */
	@CsvColumn(name = "CD_CVM", adapter = IntegerAdapter.class)
	public int codigo_cvm;

	/**
	 * Setor de atividade - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "SETOR_ATIV")
	public String setor_atividade;

	/**
	 * Tipo de endereço- Alfanumérico- char; Precisão: 30; Scale: 0
	 */
	@CsvColumn(name = "TP_ENDER")
	public String endereco_tipo;

	/**
	 * Logradouro - Alfanumérico- varchar Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "LOGRADOURO")
	public String endereco_logradouro;

	/**
	 * Complemento de endereço - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "COMPL")
	public String endereco_complemento;

	/**
	 * Bairro - Alfanumérico - varchar; Precisão: 30; Scale: 0
	 */
	@CsvColumn(name = "BAIRRO")
	public String endereco_bairro;

	/**
	 * Nome do município - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "MUN")
	public String endereco_municipio;

	/**
	 * Unidade da Federação - Alfanumérico - char; Precisão: 2; Scale: 0
	 */
	@CsvColumn(name = "UF")
	public String endereco_uf;

	/**
	 * País - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "PAIS")
	public String pais;

	/**
	 * CEP - Numérico - numeric; Precisão: 8; Scale: 0
	 */
	@CsvColumn(name = "CEP")
	public Integer endereco_cep;

	/**
	 * Código de DDD (Telefone) - Numérico - varchar; Precisão: 4; Scale: 0
	 */
	@CsvColumn(name = "DDD_TEL")
	public Integer telefone_ddd;

	/**
	 * Telefone - Numérico - numeric; Precisão: 15; Scale: 0
	 */
	@CsvColumn(name = "TEL")
	public Integer telefone_numero;

	/**
	 * Código de DDD (FAX) - Numérico - varchar; Precisão: 4; Scale: 0
	 */
	@CsvColumn(name = "DDD_FAX")
	public Integer fax_ddd;

	/**
	 * FAX - Numérico - numeric; Precisão: 15; Scale: 0
	 */
	@CsvColumn(name = "FAX")
	public Integer fax_numero;

	/**
	 * Endereço de e-mail - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "EMAIL")
	public String email;

	/**
	 * Tipo de responsável - Alfanumérico - varchar; Precisão: 100; Scale: 0
	 */
	@CsvColumn(name = "TP_RESP")
	public String responsavel_tipo;

	/**
	 * Nome do Responsável - Alfanumérico - varchar; Precisão: 60; Scale: 0
	 */
	@CsvColumn(name = "RESP")
	public String responsavel_nome;

	/**
	 * Data início de atuação do responsável - date; Precisão: 10; Scale: 0
	 */
	@CsvColumn(name = "DT_INI_RESP", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date responsavel_data;

	/**
	 * Endereço de e-mail do responsável - Alfanumérico - varchar; Precisão: 100;
	 * Scale: 0
	 */
	@CsvColumn(name = "EMAIL_RESP")
	public String responsavel_email;
}
