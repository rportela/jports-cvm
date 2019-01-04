package cvm;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Composição e Diversificação das Aplicações</h1>
 * 
 * <p>
 * O documento COMPOSIÇÃO E DIVERSIFICAÇÃO DAS APLICAÇÕES (CDA) descreve os
 * ativos que compõem as carteiras de fundos de investimento.
 * </p>
 * 
 * <p>
 * O conjunto de dados disponibiliza as carteiras dos seguintes tipos de fundos:
 * </p>
 * 
 * <ul>
 * <li>FACFIF: FUNDOS DE APLICAÇÃO EM COTAS DE FUNDOS DE INVESTIMENTO</li>
 * <li>FAPI: FUNDOS DE APOSENTADORIA PROGRAMADA INDIVIDUAL</li>
 * <li>FI: FUNDOS DE INVESTIMENTO ICVM 555</li>
 * <li>FI-FGTS: FUNDOS DE INVESTIMENTO FGTS</li>
 * <li>FIC-FITVM: FUNDOS DE INVESTIMENTO EM COTAS DE FITVM</li>
 * <li>FIEX: FUNDOS DE INVESTIMENTO NO EXTERIOR</li>
 * <li>FIF: FUNDOS DE INVESTIMENTO FINANCEIRO</li>
 * <li>FIFDIV: FUNDOS DE INVESTIMENTO FINANCEIRO -DIVIDA ESTAD/MUN</li>
 * <li>FIIM: FUNDOS DE INVESTIMENTO EM ÍNDICE DE MERCADO</li>
 * <li>FIP: FUNDOS DE INVESTIMENTO EM PARTICIPAÇÕES</li>
 * <li>FITVM: FUNDOS DE INVESTIMENTO EM TÍTULOS E VALORES MOBILIÁRIOS</li>
 * <li>FMAI: FUNDOS MÚTUOS EM AÇÕES INCENTIVADAS</li>
 * <li>FMIEE: FUNDOS MÚTUOS DE INVESTIMENTOS EM EMPR. EMERGENTES</li>
 * <li>FMP-FGTS: FUNDOS MÚTUOS DE PRIVATIZAÇÃO – FGTS</li>
 * <li>FMP-FGTS CL: FUNDOS MÚTUOS DE PRIVATIZAÇÃO - FGTS CARTEIRA LIVRE</li>
 * <li>FUNCINE: FUNDOS FINANC. DA INDÚSTRIA CINEMATOGRÁFICA NACIONAL</li>
 * </ul>
 * 
 * <p>
 * As aplicações dos fundos estão organizadas nos arquivos de dados conforme a
 * estrutura de blocos do padrão XML do Informe CDA.
 * </p>
 * 
 * <ul>
 * <li>Bloco 1: TÍTULOS PÚBLICOS DO SELIC</li>
 * <li>Bloco 2: COTAS DE FUNDOS DE INVESTIMENTO</li>
 * <li>Bloco 3: SWAP</li>
 * <li>Bloco 4: DEMAIS ATIVOS CODIFICADOS</li>
 * <li>Bloco 5: DEPÓSITOS A PRAZO E OUTROS TÍTULOS DE IF</li>
 * <li>Bloco 6: TÍTULOS DO AGRONEGÓCIO E DE CRÉDITO PRIVADO</li>
 * <li>Bloco 7: INVESTIMENTO NO EXTERIOR</li>
 * <li>Bloco 8: DEMAIS ATIVOS NÃO CODIFICADOS</li>
 * </ul>
 * <p>
 * Importante: Os detalhes sobre as aplicações somente estarão disponíveis após
 * expirado o prazo de confidencialidade solicitado pelos administradores dos
 * fundos no envio das informações à CVM.
 * </p>
 * 
 * @author rportela
 *
 */
public class FundoCarteira {

	/**
	 * Bloco 1: TÍTULOS PÚBLICOS DO SELIC
	 */
	public final List<FundoCarteiraItemTituloPublico> titulos_publicos = new ArrayList<>(40000);

	/**
	 * Bloco 2: COTAS DE FUNDOS DE INVESTIMENTO
	 */
	public final List<FundoCarteiraItemCotaDeFundo> cotas_de_fundos = new ArrayList<>(90000);

	/**
	 * Bloco 3: SWAP
	 */
	public final List<FundoCarteiraItemSwap> swaps = new ArrayList<>(200);

	/**
	 * Bloco 4: DEMAIS ATIVOS CODIFICADOS
	 */
	public final List<FundoCarteiraItemAtivoCodificado> ativos_codificados = new ArrayList<>(60000);

	/**
	 * Bloco 5: DEPÓSITOS A PRAZO E OUTROS TÍTULOS DE IF
	 */
	public final List<FundoCarteiraItemDepositoAPrazo> depositos_a_prazo = new ArrayList<>(50000);

	/*
	 * Bloco 6: TÍTULOS DO AGRONEGÓCIO E DE CRÉDITO PRIVADO
	 */
	public final List<FundoCarteiraItemCreditoPrivado> credito_privado = new ArrayList<>(50000);

	/**
	 * Bloco 7: INVESTIMENTO NO EXTERIOR
	 */
	public final List<FundoCarteiraItemInvestimentoExterior> investimentos_no_exterior = new ArrayList<>(50000);

	/**
	 * Bloco 8: DEMAIS ATIVOS NÃO CODIFICADOS
	 */
	public final List<FundoCarteiraItemNaoCodificado> demais_nao_codificados = new ArrayList<>(50000);

}
