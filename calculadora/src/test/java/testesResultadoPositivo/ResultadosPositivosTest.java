package testesResultadoPositivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import softwarealvo.Calculadora;

@TestMethodOrder(OrderAnnotation.class)
class ResultadosPositivosTest {

	static Calculadora c;

	// Pré-condição
	@BeforeAll
	static void preCondicaoGeral() {
		c = new Calculadora();
		System.out.println("00 - Pre condicao geral");
	}

	@BeforeEach
	void preCondicaoTeste() {
		System.out.println("0 - Pre condicao TESTE");
	}

	@Test
	@DisplayName("Soma de positivo e negativo ==> positivo")
	@Order(1)
	//@Disabled("Aguarda correcao do defeito #4575")
	void testSomaPositivoENegativo_ResultadoPositivo() {
		System.out.println("1 - Soma positivo negativo => positivo");
		// assertEquals(4, c.soma(5, -2));
		assertThat(c.soma(5, -2), is(3));
	}
	
	@Test
	void testMultiplicacao() {
		assertThat(c.multiplicacao(8,2), is(16));
	}

	@Test
	@DisplayName("Soma positivos => positivo")
	@Order(2)
	void testSomaPositivos_ResultadoPositivo() {
		System.out.println("2 - Soma positivos => positivo");
		assertThat("Soma com erro", c.soma(2, 3), is(5));
	}

	int v1;
	int v2;
	int r;

	@ParameterizedTest
	@CsvFileSource(resources = "/dados.csv", numLinesToSkip = 1, delimiter = ';')
	void testSomaNegativoEPositivo_ResultadoPositivo(String valor1, String valor2, String resultado) {
		v1 = Integer.parseInt(valor1);
		v2 = Integer.parseInt(valor2);
		r  = Integer.parseInt(resultado);
		System.out.println("3 - CSV " + v1 + " " + v2 + " " + r );
		assertThat(c.soma(v1, v2), is(r));
	}

	@AfterEach
	void posCondicaoTeste() {
		System.out.println("9 - Pos condicao teste");
	}

	@AfterAll
	static void posCondicaoGeral() {
		System.out.println("99 - Pos condicao GERAL");
	}

}
