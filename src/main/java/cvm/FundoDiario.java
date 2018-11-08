package cvm;

import java.util.Date;

import jports.adapters.DateAdapter;
import jports.adapters.DigitsOnlyToLong;
import jports.adapters.DoubleAdapter;
import jports.adapters.IntegerAdapter;
import jports.text.CsvColumn;

/**
 * O INFORME DIÁRIO é um demonstrativo que contém as seguintes informações do
 * fundo, relativas à data de competência:
 * 
 * Valor total da carteira do fundo;
 * 
 * Patrimônio líquido;
 * 
 * Valor da cota;
 * 
 * Captações realizadas no dia;
 * 
 * Resgates pagos no dia;
 * 
 * Número de cotistas
 * 
 * O conjunto de dados disponibiliza os informes diários referentes aos Fundos
 * de Investimento nos últimos doze meses.
 * 
 * Os arquivos referentes ao mês corrente (M) e anterior (M-1) serão atualizados
 * diariamente com as eventuais reapresentações. A atualização ocorre de terça a
 * sábado, às 08:00h, com os dados recebidos pelo CVMWeb até as 23:59h do dia
 * anterior.
 * 
 * Os arquivos referentes aos meses M-2 e M-3 serão atualizados semanalmente com
 * as eventuais reapresentações.
 * 
 * Os arquivos referente aos meses M-4, M-5, ..., até M-11 serão atualizados
 * mensalmente com as eventuais reapresentações.
 * 
 * @author rportela
 *
 */
public class FundoDiario {

	/**
	 * CNPJ do fundo; Domínio: Alfanumérico; Tipo dados: varchar; Precisão: 20;
	 * Scale: 0;
	 */
	@CsvColumn(name = "CNPJ_FUNDO", adapter = DigitsOnlyToLong.class)
	public long cnpj;

	/**
	 * Descrição: Data de competência do documento; Domínio: AAAA-MM-DD; Tipo dados:
	 * date; Precisão: 10; Scale: 0;
	 */
	@CsvColumn(name = "DT_COMPTC", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date competencia;

	/**
	 * Valor total da carteira; Domínio: Numérico; Tipo dados: numeric; Precisão:
	 * 17; Scale: 2;
	 */
	@CsvColumn(name = "VL_TOTAL", adapter = DoubleAdapter.class)
	public double carteira;

	/**
	 * Valor da cota; Domínio: Numérico; Tipo dados: numeric; Precisão: 27; Scale:
	 * 12;
	 */
	@CsvColumn(name = "VL_QUOTA", adapter = DoubleAdapter.class)
	public double quota;

	/**
	 * Valor do patrimônio líquido; Domínio: Numérico; Tipo dados: numeric;
	 * Precisão: 17; Scale: 2;
	 */
	@CsvColumn(name = "VL_PATRIM_LIQ", adapter = DoubleAdapter.class)
	public double pl;

	/**
	 * Captação do dia; Domínio: Numérico; Tipo dados: numeric; Precisão: 17; Scale:
	 * 2;
	 */
	@CsvColumn(name = "CAPTC_DIA", adapter = DoubleAdapter.class)
	public double captacao;

	/**
	 * Resgate no dia; Domínio: Numérico; Tipo dados: numeric; Precisão: 17; Scale:
	 * 2;
	 */
	@CsvColumn(name = "RESG_DIA", adapter = DoubleAdapter.class)
	public double resgate;

	/**
	 * Número de cotistas; Domínio: Numérico; Tipo dados: int; Precisão: 10; Scale:
	 * 0;
	 */
	@CsvColumn(name = "RESG_DIA", adapter = IntegerAdapter.class)
	public int cotistas;
}
