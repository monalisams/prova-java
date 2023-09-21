package br.com.confidencecambio.javabasico.service;

import br.com.confidencecambio.javabasico.dto.imc.Imc;
import br.com.confidencecambio.javabasico.dto.imc.ImcResult;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
public class ImcServiceTest {

    @Test
    public void testCalculateIMC() {
        // Configuração do Mock
        Imc imcDto = new Imc(70.0, 1.75);
        ImcService imcService = new ImcService();

        // Executa o cálculo do IMC
        ImcResult result = imcService.calculateIMC(imcDto);

        // Verifica se o resultado é o esperado
        DecimalFormat df = new DecimalFormat("#.##");
        String expectedResult;
        double imc = imcDto.getWeight() / (imcDto.getHeight() * imcDto.getHeight());
        if (imc < 18.5) {
            expectedResult = "Abaixo do Peso";
        } else if (imc < 24.9) {
            expectedResult = "Peso Normal";
        } else if (imc < 29.9) {
            expectedResult = "Sobrepeso";
        } else {
            expectedResult = "Obeso";
        }

        assertThat(result.getResult()).isEqualTo(expectedResult);
        assertThat(result.getImc()).isEqualTo(df.format(imc));
    }

    @Test
    public void testValidateValues() {
        // Crie uma instância do serviço
        ImcService imcService = new ImcService();

        // Teste um valor válido
        Imc imcDtoValid = new Imc(70.0, 1.75);
        boolean isValid = imcService.validateValues(imcDtoValid);
        assertThat(isValid).isTrue();

        // Teste um valor inválido (altura <= 0)
        Imc imcDtoInvalidHeight = new Imc(70.0, -1.75);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            imcService.validateValues(imcDtoInvalidHeight);
        }).withMessage("Height must be greater than zero");

        // Teste um valor inválido (peso <= 0)
        Imc imcDtoInvalidWeight = new Imc(-70.0, 1.75);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            imcService.validateValues(imcDtoInvalidWeight);
        }).withMessage("Weight must be greater than zero");
    }

    @Test
    public void testResultImc() {
        ImcService imcService = new ImcService();

        // Teste para imc < 18.5
        ImcResult resultUnderweight = imcService.resultImc(17.0);
        assertThat(resultUnderweight.getResult()).isEqualTo("Abaixo do Peso");
        assertThat(resultUnderweight.getImc()).isEqualTo("17");

        // Teste para imc entre 18.5 e 24.9
        ImcResult resultNormalWeight = imcService.resultImc(22.5);
        assertThat(resultNormalWeight.getResult()).isEqualTo("Peso Normal");
        assertThat(resultNormalWeight.getImc()).isEqualTo("22,5");

        // Teste para imc entre 24.9 e 29.9
        ImcResult resultOverweight = imcService.resultImc(27.0);
        assertThat(resultOverweight.getResult()).isEqualTo("Sobrepeso");
        assertThat(resultOverweight.getImc()).isEqualTo("27");

        // Teste para imc >= 29.9
        ImcResult resultObese = imcService.resultImc(30.0);
        assertThat(resultObese.getResult()).isEqualTo("Obeso");
        assertThat(resultObese.getImc()).isEqualTo("30");
    }
}
