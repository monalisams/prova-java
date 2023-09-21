package br.com.confidencecambio.javabasico.controller;

import br.com.confidencecambio.javabasico.dto.imc.ErrorResponse;
import br.com.confidencecambio.javabasico.dto.imc.Imc;
import br.com.confidencecambio.javabasico.dto.imc.ImcResult;
import br.com.confidencecambio.javabasico.service.ImcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imc")
public class IMCController {
    private final ImcService imcService;

    @Autowired
    public IMCController(ImcService imcService) {
        this.imcService = imcService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImcResult calculateIMC(@RequestParam(value = "weight", required = true) Double weight, @RequestParam(value = "height", required = true) Double height) {

        return imcService.calculateIMC(new Imc(weight, height));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingParams(MissingServletRequestParameterException e) {
        String paramName = e.getParameterName();
        String errorMessage = paramName + " is require ";
        return new ErrorResponse(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY.value());
    }
}
