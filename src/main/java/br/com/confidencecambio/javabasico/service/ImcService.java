package br.com.confidencecambio.javabasico.service;

import br.com.confidencecambio.javabasico.dto.imc.Imc;
import br.com.confidencecambio.javabasico.dto.imc.ImcResult;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ImcService {
    public ImcResult calculateIMC(Imc imcDto) {
        System.out.println("Calculating imc");
        validateValues(imcDto);

        double imc = imcDto.getWeight() / (imcDto.getHeight() * imcDto.getHeight());

        System.out.printf("Calculated imc: %.2f", imc);
        return resultImc(imc);
    }

    public boolean validateValues(Imc imcDto) {

        if (imcDto.getHeight() <= 0) {
            throw new IllegalArgumentException("Height must be greater than zero");
        }

        if (imcDto.getWeight() <= 0){
            throw new IllegalArgumentException("Weight must be greater than zero");
        }

        System.out.println("Value passed in the request parameter is valid");

        return true;
    }

    public ImcResult resultImc(double imc) {
        DecimalFormat df = new DecimalFormat("#.##");
        String result;
        if (imc < 18.5) {
            result = "Abaixo do Peso";
        } else if (imc < 24.9) {
            result = "Peso Normal";
        } else if (imc < 29.9) {
            result = "Sobrepeso";
        } else {
            result = "Obeso";
        }

        return new ImcResult(result,df.format(imc));
    }
}