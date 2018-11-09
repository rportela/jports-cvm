package cvm;

import java.util.Date;

import jports.adapters.DateAdapter;
import jports.adapters.DigitsOnlyToLong;
import jports.adapters.DoubleAdapter;
import jports.adapters.IntegerAdapter;
import jports.text.CsvColumn;
import jports.text.CsvTable;

/**
 * O INFORME DI�RIO � um demonstrativo que cont�m as seguintes informa��es do
 * fundo, relativas � data de compet�ncia:
 * 
 * Valor total da carteira do fundo;
 * 
 * Patrim�nio l�quido;
 * 
 * Valor da cota;
 * 
 * Capta��es realizadas no dia;
 * 
 * Resgates pagos no dia;
 * 
 * N�mero de cotistas
 * 
 * O conjunto de dados disponibiliza os informes di�rios referentes aos Fundos
 * de Investimento nos �ltimos doze meses.
 * 
 * Os arquivos referentes ao m�s corrente (M) e anterior (M-1) ser�o atualizados
 * diariamente com as eventuais reapresenta��es. A atualiza��o ocorre de ter�a a
 * s�bado, �s 08:00h, com os dados recebidos pelo CVMWeb at� as 23:59h do dia
 * anterior.
 * 
 * Os arquivos referentes aos meses M-2 e M-3 ser�o atualizados semanalmente com
 * as eventuais reapresenta��es.
 * 
 * Os arquivos referente aos meses M-4, M-5, ..., at� M-11 ser�o atualizados
 * mensalmente com as eventuais reapresenta��es.
 * 
 * @author rportela
 *
 */
@CsvTable(separator = ";", charset = "windows-1252")
public class CvmFundoDiario {

	/**
	 * CNPJ do fundo; Dom�nio: Alfanum�rico; Tipo dados: varchar; Precis�o: 20;
	 * Scale: 0;
	 */
	@CsvColumn(name = "CNPJ_FUNDO", adapter = DigitsOnlyToLong.class)
	public long cnpj;

	/**
	 * Descri��o: Data de compet�ncia do documento; Dom�nio: AAAA-MM-DD; Tipo dados:
	 * date; Precis�o: 10; Scale: 0;
	 */
	@CsvColumn(name = "DT_COMPTC", adapter = DateAdapter.class, pattern = "yyyy-MM-dd")
	public Date competencia;

	/**
	 * Valor total da carteira; Dom�nio: Num�rico; Tipo dados: numeric; Precis�o:
	 * 17; Scale: 2;
	 */
	@CsvColumn(name = "VL_TOTAL", adapter = DoubleAdapter.class)
	public double carteira;

	/**
	 * Valor da cota; Dom�nio: Num�rico; Tipo dados: numeric; Precis�o: 27; Scale:
	 * 12;
	 */
	@CsvColumn(name = "VL_QUOTA", adapter = DoubleAdapter.class)
	public double quota;

	/**
	 * Valor do patrim�nio l�quido; Dom�nio: Num�rico; Tipo dados: numeric;
	 * Precis�o: 17; Scale: 2;
	 */
	@CsvColumn(name = "VL_PATRIM_LIQ", adapter = DoubleAdapter.class)
	public double pl;

	/**
	 * Capta��o do dia; Dom�nio: Num�rico; Tipo dados: numeric; Precis�o: 17; Scale:
	 * 2;
	 */
	@CsvColumn(name = "CAPTC_DIA", adapter = DoubleAdapter.class)
	public double captacao;

	/**
	 * Resgate no dia; Dom�nio: Num�rico; Tipo dados: numeric; Precis�o: 17; Scale:
	 * 2;
	 */
	@CsvColumn(name = "RESG_DIA", adapter = DoubleAdapter.class)
	public double resgate;

	/**
	 * N�mero de cotistas; Dom�nio: Num�rico; Tipo dados: int; Precis�o: 10; Scale:
	 * 0;
	 */
	@CsvColumn(name = "RESG_DIA", adapter = IntegerAdapter.class)
	public int cotistas;
}
