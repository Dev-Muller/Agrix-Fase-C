package com.betrybe.agrix.advice;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * GeneralControllerAdvice class.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  @ExceptionHandler(FarmNotFoundException.class)
  @ResponseBody
  public ResponseEntity<String> handleFarmNotFoundException(FarmNotFoundException exception) {
    return new ResponseEntity<>("Fazenda não encontrada!", HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(CropNotFoundException.class)
  @ResponseBody
  public ResponseEntity<String> handleCropNotFoundException(CropNotFoundException exception) {
    return new ResponseEntity<>("Plantação não encontrada!", HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<String> handleException(Exception exception) {
    return new ResponseEntity<>("Erro interno!", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(FertilizerNotFoundException.class)
  @ResponseBody
  public ResponseEntity<String> handleFertilizerNotFoundException(
      FertilizerNotFoundException exception) {
    return new ResponseEntity<>("Fertilizante não encontrado!", HttpStatus.NOT_FOUND);
  }

}
