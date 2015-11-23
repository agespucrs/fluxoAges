package br.ages.crud.validator;


import java.util.Map;

import br.ages.crud.exception.ValidationException;

/**
 * Responsável por validar todo os tipos de valores do sistema
 * 
 * @author Devmedia
 * 
 */
public interface Validator {
	
	/**
	 * Método efetivo de validação que recebe o mapa com os valores e os nomes
	 * 
	 * @param valores
	 * @return
	 * @throws ValidationException
	 */
	public boolean validar(Map<String, Object> valores) throws ValidationException;

}
