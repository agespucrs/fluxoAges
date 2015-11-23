package br.ages.crud.validator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.ages.crud.exception.ValidationException;
import br.ages.crud.util.MensagemContantes;

public class SenhaValidator implements Validator {

	public boolean validar(Map<String, Object> valores) throws ValidationException {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]{9}");
		String msgErro = "";

		for (String key : valores.keySet()) {
			String senha = (String) valores.get(key);
			if (!"".equals(senha) || senha != null) {
				if (senha.length() < 3) {
					msgErro += MensagemContantes.MSG_ERR_SENHA_INVALIDA.concat("<br/>");
				}
				if (senha.length() > 9) {
					msgErro += MensagemContantes.MSG_ERR_SENHA_INVALIDA.concat("<br/>");
				}

				Matcher matcher = pattern.matcher(senha);

				if (matcher.find()) {
					msgErro += MensagemContantes.MSG_ERR_SENHA_INVALIDA.concat("<br/>");
				}

			} else {
				msgErro += (MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", key).concat("<br/>"));
			}
		}
		if (!"".equals(msgErro)) {
			throw new ValidationException(msgErro);
		}

		return true;
	}

}
