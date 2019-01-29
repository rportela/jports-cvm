package cvm;

import java.util.Date;

import jports.adapters.BooleanAsSpecificString;
import jports.adapters.DigitsOnlyToInteger;
import jports.text.CsvColumn;
import jports.text.CsvTable;

@CsvTable(
		separator = "\t",
		charset = "windows-1252",
		firstRowHasNames = true)
public class CiaIncentivada {

	@CsvColumn(
			name = "CD_CVM")
	public int codigo_cvm;

	@CsvColumn(
			name = "DENOM_SOCIAL")
	public String razao_social;

	@CsvColumn(
			name = "DENOM_COMERC")
	public String nome;

	@CsvColumn(
			name = "SETOR_ATIV")
	public String setor;

	@CsvColumn(
			name = "PF_PJ",
			adapter = BooleanAsSpecificString.class,
			pattern = "PF")
	public Boolean is_pf;

	@CsvColumn(
			name = "CNPJ")
	public long cnpj;

	@CsvColumn(
			name = "DT_REG",
			pattern = "dd/MM/yyyy")
	public Date registro_data;

	@CsvColumn(
			name = "DT_CONST",
			pattern = "dd/MM/yyyy")
	public Date constituicao_data;

	@CsvColumn(
			name = "DT_CANCEL",
			pattern = "dd/MM/yyyy")
	public Date cancelamento_data;

	@CsvColumn(
			name = "MOTIVO_CANCEL")
	public String cancelamento_motivo;

	@CsvColumn(
			name = "SIT_REG")
	public String situacao;

	@CsvColumn(
			name = "DT_INI_SIT",
			pattern = "dd/MM/yyyy")
	public Date situacao_data;

	@CsvColumn(
			name = "SIT_EMISSOR")
	public String emissor_situacao;

	@CsvColumn(
			name = "DT_INI_SIT_EMISSOR",
			pattern = "dd/MM/yyyy")
	public Date emissor_data;

	@CsvColumn(
			name = "CATEG_REG")
	public String categoria;

	@CsvColumn(
			name = "DT_INI_CATEG",
			pattern = "dd/MM/yyyy")
	public Date categoria_data;

	@CsvColumn(
			name = "AUDITOR")
	public String auditor_nome;

	@CsvColumn(
			name = "CNPJ_AUDITOR")
	public Long auditor_cnpj;

	@CsvColumn(
			name = "TP_ENDER")
	public String endereco_tipo;

	@CsvColumn(
			name = "LOGRADOURO")
	public String endereco_logradouro;

	@CsvColumn(
			name = "COMPL")
	public String endereco_complemento;

	@CsvColumn(
			name = "BAIRRO")
	public String endereco_bairro;

	@CsvColumn(
			name = "CIDADE")
	public String endereco_cidade;

	@CsvColumn(
			name = "ESTADO")
	public String endereco_estado;

	@CsvColumn(
			name = "PAIS")
	public String endereco_pais;

	@CsvColumn(
			name = "CD_POSTAL",
			adapter = DigitsOnlyToInteger.class)
	public Integer endereco_cep;

	@CsvColumn(
			name = "TEL")
	public String telefone;

	@CsvColumn(
			name = "FAX")
	public String fax;

	@CsvColumn(
			name = "EMAIL")
	public String email;

	@CsvColumn(
			name = "TP_RESP")
	public String responsavel_tipo;

	@CsvColumn(
			name = "RESP")
	public String responsavel_nome;

	@CsvColumn(
			name = "DT_INI_RESP",
			pattern = "dd/MM/yyyy")
	public Date responsavel_data;

	@CsvColumn(
			name = "LOGRADOURO_RESP")
	public String responsavel_logradouro;

	@CsvColumn(
			name = "COMPL_RESP")
	public String responsavel_complemento;

	@CsvColumn(
			name = "BAIRRO_RESP")
	public String responsavel_bairro;

	@CsvColumn(
			name = "CIDADE_RESP")
	public String responsavel_cidade;

	@CsvColumn(
			name = "UF_RESP")
	public String responsavel_uf;

	@CsvColumn(
			name = "PAIS_RESP")
	public String responsavel_pais;

	@CsvColumn(
			name = "CEP_RESP")
	public Integer responsavel_cep;

	@CsvColumn(
			name = "TEL_RESP")
	public String responsavel_tel;

	@CsvColumn(
			name = "FAX_RESP")
	public String responsavel_fax;

	@CsvColumn(
			name = "EMAIL_RESP")
	public String responsavel_email;
}
