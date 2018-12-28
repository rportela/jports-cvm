package cvm;

/**
 * As aplicações dos fundos estão organizadas nos arquivos de dados conforme a
 * estrutura de blocos do padrão XML do Informe CDA:
 * 
 * @author rportela
 *
 */
public enum FundoCarteiraItemTipo {

	/**
	 * Bloco 1: TÍTULOS PÚBLICOS DO SELIC
	 */
	TITULOS_PUBLICOS,
	/**
	 * Bloco 2: COTAS DE FUNDOS DE INVESTIMENTO
	 */
	COTAS_DE_FUNDOS,
	/**
	 * Bloco 3: SWAP
	 */
	SWAP,
	/**
	 * Bloco 4: DEMAIS ATIVOS CODIFICADOS
	 */
	ATIVOS_CODIFICADOS,
	/**
	 * Bloco 5: DEPÓSITOS A PRAZO E OUTROS TÍTULOS DE IF
	 */
	DEPOSITOS_A_PRAZO_E_OUTROS_TITULOS_DE_IF,
	/**
	 * Bloco 6: TÍTULOS DO AGRONEGÓCIO E DE CRÉDITO PRIVADO
	 */
	AGRONEGOCIO_CREDITO_PRIVADO,
	/**
	 * Bloco 7: INVESTIMENTO NO EXTERIOR
	 */
	INVESTIMENTOS_NO_EXTERIOR,
	/**
	 * Bloco 8: DEMAIS ATIVOS NÃO CODIFICADOS
	 */
	ATIVOS_NAO_CODIFICADOS

}
