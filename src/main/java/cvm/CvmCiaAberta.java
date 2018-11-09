package cvm;

import java.util.Date;

import jports.adapters.DateAdapter;
import jports.adapters.DigitsOnlyToLong;
import jports.text.CsvColumn;
import jports.text.CsvTable;

/**
 * Dados cadastrais de companhias abertas, como CNPJ, data de registro e
 * situação do registro.
 * 
 * @author rportela
 *
 */
@CsvTable(separator = ";", charset = "windows-1252")
public class CvmCiaAberta {

	/**
	 * CNPJ da companhia; Domínio: Alfanumérico; Tipo dados: varchar; Precisão: 20;
	 * Scale: 0;
	 */
	@CsvColumn(name = "CNPJ_CIA", adapter = DigitsOnlyToLong.class)
	public long cnpj;

	/**
	 * Denominação Social; Domínio: Alfanumérico; Tipo dados: varchar; Precisão:
	 * 100; Scale: 0;
	 * 
	 */
	@CsvColumn(name = "DENOM_SOCIAL")
	public String razao_social;

	/**
	 * Denominação Comercial; Domínio: Alfanumérico; Tipo dados: varchar; Precisão:
	 * 100; Scale: 0;
	 */
	@CsvColumn(name = "DENOM_COMERC")
	public String nome;

	/**
	 * Data de registro; Domínio: AAAA-MM-DD; Tipo dados: date; Precisão: 10; Scale:
	 * 0;
	 */
	@CsvColumn(name = "DT_REG", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date registro_data;

	/**
	 * Data de constituição; Domínio:AAAA-MM-DD; Tipo dados: date; Precisão:10;
	 * Scale:0;
	 */
	@CsvColumn(name = "DT_CONST", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date constituicao_data;

	/**
	 * Data de cancelamento; Domínio:AAAA-MM-DD; Tipo dados: date; Precisão:10;
	 * Scale:0;
	 */
	@CsvColumn(name = "DT_CANCEL", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date cancelamento_data;

	/**
	 * Motivo de cancelamento; Domínio: Alfanumérico; Tipo dados: varchar;
	 * Precisão:80; Scale:0;
	 */
	@CsvColumn(name = "MOTIVO_CANCEL")
	public String cancelamento_motivo;

	/**
	 * Situação; Domínio: Alfanumérico; Tipo dados: varchar; Precisão:40; Scale:0;
	 */
	@CsvColumn(name = "SIT")
	public String situacao;

	/**
	 * Data início da situação; Domínio:AAAA-MM-DD; Tipo dados: date; Precisão:10;
	 * Scale:0;
	 */
	@CsvColumn(name = "INI_SIT", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date situacao_data;

	/**
	 * Código CVM; Domínio: Numérico; Tipo dados: numeric; Precisão:7; Scale:0;
	 */
	@CsvColumn(name = "CD_CVM")
	public int codigo_cvm;

	/**
	 * Setor de atividade; Domínio: Alfanumérico; Tipo dados: varchar; Precisão:100;
	 * Scale:0;
	 */
	@CsvColumn(name = "SETOR_ATIV")
	public String setor;

	/**
	 * Categoria do registro; Domínio: Alfanumérico; Tipo dados: varchar;
	 * Precisão:20; Scale:0;
	 */
	@CsvColumn(name = "CATEG_REG")
	public String categoria;

	/**
	 * Data início da categoria do registro; Domínio:AAAA-MM-DD; Tipo dados: date;
	 * Precisão:10; Scale:0;
	 */
	@CsvColumn(name = "DT_INI_CATEG", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date categoria_data;

	/**
	 * Descrição da situação do emissor; Domínio: Alfanumérico; Tipo dados: char;
	 * Precisão:80; Scale:0;
	 */
	@CsvColumn(name = "SIT_EMISSOR")
	public String emissor_situacao;

	/**
	 * Data início da situação do emissor; Domínio:AAAA-MM-DD; Tipo dados: date;
	 * Precisão:10; Scale:0;
	 */
	@CsvColumn(name = "DT_INI_SIT_EMISSOR", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date emissor_data;

	/**
	 * Tipo de endereço; Domínio: Alfanumérico; Tipo dados: char; Precisão:30;
	 * Scale:0;
	 */
	@CsvColumn(name = "TP_ENDER")
	public String endereco_tipo;

	/**
	 * Logradouro; Domínio: Alfanumérico; Tipo dados: varchar; Precisão:100;
	 * Scale:0;
	 */
	@CsvColumn(name = "LOGRADOURO")
	public String endereco_logradouro;

	/**
	 * Complemento de endereço; Domínio: Alfanumérico; Tipo dados: varchar;
	 * Precisão:100; Scale:0;
	 */
	@CsvColumn(name = "COMPL")
	public String endereco_complemento;

	/**
	 * Bairro; Domínio: Alfanumérico; Tipo dados: varchar; Precisão:30; Scale:0;
	 */
	@CsvColumn(name = "BAIRRO")
	public String endereco_bairro;

	/**
	 * Nome do município; Domínio: Alfanumérico; Tipo dados: varchar; Precisão:100;
	 * Scale:0;
	 */
	@CsvColumn(name = "MUN")
	public String endereco_municipio;

	/**
	 * Unidade da Federação; Domínio: Alfanumérico; Tipo dados: char; Precisão:2;
	 * Scale:0;
	 */
	@CsvColumn(name = "UF")
	public String endereco_uf;

	/**
	 * País; Domínio: Alfanumérico; Tipo dados: varchar; Precisão:100; Scale:0;
	 */
	@CsvColumn(name = "PAIS")
	public String pais;

	/**
	 * CEP; Domínio: Numérico; Tipo dados: numeric; Precisão:8; Scale:0;
	 */
	@CsvColumn(name = "CEP")
	public Integer endereco_cep;

	/**
	 * Código de
	 * 
	 * DDD (Telefone); Domínio: Numérico; Tipo dados: varchar; Precisão: 4; Scale:
	 * 0;
	 */
	@CsvColumn(name = "DDD_TEL")
	public String telefone_ddd;

	/**
	 * Telefone; Domínio: Numérico; Tipo dados: numeric; Precisão: 15; Scale: 0;
	 */
	@CsvColumn(name = "TEL")
	public String telefone;

	/**
	 * Código de DDD (FAX); Domínio: Numérico; Tipo dados: varchar; Precisão: 4;
	 * Scale: 0;
	 */
	@CsvColumn(name = "DDD_FAX")
	public String fax_ddd;

	/**
	 * FAX; Domínio: Numérico; Tipo dados: numeric; Precisão: 15; Scale: 0;
	 */
	@CsvColumn(name = "FAX")
	public String fax;

	/**
	 * Endereço de e-mail; Domínio: Alfanumérico; Tipo dados: varchar; Precisão:
	 * 100; Scale: 0;
	 */
	@CsvColumn(name = "EMAIL")
	public String email;

	/**
	 * Tipo de responsável; Domínio: Alfanumérico; Tipo dados: varchar; Precisão:
	 * 100; Scale: 0;
	 */
	@CsvColumn(name = "TP_RESP")
	public String responsavel_tipo;

	/**
	 * Nome do Responsável; Domínio: Alfanumérico; Tipo dados: varchar; Precisão:
	 * 60; Scale: 0;
	 */
	@CsvColumn(name = "RESP")
	public String responsavel_nome;

	/**
	 * Data início de atuação do responsável; Domínio: AAAA-MM-DD; Tipo dados: date;
	 * Precisão: 10; Scale: 0;
	 */
	@CsvColumn(name = "DT_INI_RESP", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date responsavel_data;

	/**
	 * Logradouro do responsável;Domínio: Alfanumérico; Tipo dados: varchar;
	 * Precisão:30; Scale:0;
	 */
	@CsvColumn(name = "LOGRADOURO_RESP")
	public String responsavel_endereco;

	/**
	 * Complemento de endereço do responsável; Domínio: Alfanumérico; Tipo dados:
	 * varchar; Precisão:18; Scale:0;
	 */
	@CsvColumn(name = "COMPL_RESP")
	public String responsavel_complemento;

	/**
	 * Bairro do responsável; Domínio: Alfanumérico; Tipo dados: varchar;
	 * Precisão:30; Scale:0;
	 */
	@CsvColumn(name = "BAIRRO_RESP")
	public String responsavel_bairro;

	/**
	 * Nome do município do responsável; Domínio: Alfanumérico; Tipo dados: varchar;
	 * Precisão:100; Scale:0;
	 */
	@CsvColumn(name = "MUN_RESP")
	public String responsavel_municipio;

	/**
	 * Unidade da Federação do responsável; Domínio: Alfanumérico; Tipo dados: char;
	 * Precisão:2; Scale:0;
	 */
	@CsvColumn(name = "UF_RESP")
	public String resposavel_uf;

	/**
	 * País do responsável; Domínio: Alfanumérico; Tipo dados: varchar;
	 * Precisão:100; Scale:0;
	 */
	@CsvColumn(name = "PAIS_RESP")
	public String responsavel_pais;

	/**
	 * CEP do responsável; Domínio: Numérico; Tipo dados: numeric; Precisão:8;
	 * Scale:0;
	 */
	@CsvColumn(name = "CEP_RESP")
	public Integer responsavel_cep;

	/**
	 * Código de
	 * 
	 * DDD (Telefone) do responsável; Domínio: Numérico; Tipo dados: varchar;
	 * Precisão: 4; Scale: 0;
	 */
	@CsvColumn(name = "DDD_TEL_RESP")
	public String responsavel_telefone_ddd;

	/**
	 * Telefone do responsável; Domínio: Numérico; Tipo dados: varchar; Precisão:
	 * 30; Scale: 0;
	 */
	@CsvColumn(name = "TEL_RESP")
	public String responsavel_telefone;

	/**
	 * Código de DDD (FAX) do responsável; Domínio: Numérico; Tipo dados: varchar;
	 * Precisão: 4; Scale: 0;
	 */
	@CsvColumn(name = "DDD_FAX_RESP")
	public String responsavel_fax_dd;

	/**
	 * 
	 * FAX do responsável; Domínio: Numérico; Tipo dados: varchar; Precisão: 30;
	 * Scale: 0;
	 */
	@CsvColumn(name = "FAX_RESP")
	public String responsavel_fax;

	/**
	 * Endereço de e-mail do responsável; Domínio: Alfanumérico; Tipo dados:
	 * varchar; Precisão: 100; Scale: 0;
	 */
	@CsvColumn(name = "EMAIL_RESP")
	public String responsavel_email;

	/**
	 * CNPJ do Auditor; Domínio: Alfanumérico; Tipo dados: varchar; Precisão: 20;
	 * Scale: 0;
	 */
	@CsvColumn(name = "CNPJ_AUDITOR", adapter = DigitsOnlyToLong.class)
	public long auditor_cnpj;

	/**
	 * Nome do Auditor; Domínio: Alfanumérico; Tipo dados: varchar; Precisão: 100;
	 * Scale: 0;
	 */
	@CsvColumn(name = "AUDITOR")
	public String auditor_nome;

}
