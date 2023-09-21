package br.com.confidencecambio.javabasico.dto.imc;

public class ImcResult {
    private String result;
    private String imc;

    public ImcResult(String text, String imc) {
        this.result = text;
        this.imc = imc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String text) {
        this.result = text;
    }

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }
}
