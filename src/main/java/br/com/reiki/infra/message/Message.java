package br.com.reiki.infra.message;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.com.reiki.infra.util.Util;

@Component("message")
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(Message.class);

	private ResourceBundle getBundle(FacesContext fc) {
		return ResourceBundle.getBundle("messages", fc.getViewRoot().getLocale());
	}

	public String getMessage(String key) {
		FacesContext fc = FacesContext.getCurrentInstance();
		return getBundle(fc).getString(key);
	}

	public String getMessage(String key, Object... params) {
		FacesContext fc = FacesContext.getCurrentInstance();
		MessageFormat form = new MessageFormat(getBundle(fc).getString(key));
		return form.format(params);
	}

	public String getMessageWithParameters(String message, Object... parameters) {
		if (parameters != null && parameters.length > 0 && message.indexOf('{') != -1) {
			message = MessageFormat.format(message, parameters);
		}
		return message;
	}

	public void addInfo(String key, Object... parameters) {
		addMessage(key, FacesMessage.SEVERITY_INFO, parameters);
	}

	public void addWarn(String key, Object... parameters) {
		addMessage(key, FacesMessage.SEVERITY_WARN, parameters);
	}

	public void addError(String key, Object... parameters) {
		addMessage(key, FacesMessage.SEVERITY_ERROR, parameters);
	}

	public void addFatal(String key, Object... parameters) {
		addMessage(key, FacesMessage.SEVERITY_FATAL, parameters);
	}

	public void addErrorMessage(String message) {
		addMessage(message, FacesMessage.SEVERITY_ERROR);
	}

	public void addErrorMessageFor(String key, String nomeDoCampo) {
		addMessageFor(key, FacesMessage.SEVERITY_ERROR, nomeDoCampo);
	}

	public void addMessage(String message, Severity severity) {
		FacesMessage fm = new FacesMessage(severity, message, message);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

	public void addMessageFor(String key, Severity severity, String nomeDoCampo) {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (Util.isStringEmpty(key)) {
			LOGGER.error("Erro ao configurar mensagens: a chave e nula ou vazia.");
		}
		FacesMessage fm = new FacesMessage(severity, "*", "*");
		fc.addMessage("formUsuario:" + nomeDoCampo, fm);
	}

	public void addMessage(String key, Severity severity, Object... parameters) {
		FacesContext fc = FacesContext.getCurrentInstance();
		String message = "";
		if (Util.isStringEmpty(key)) {
			LOGGER.error("Erro ao configurar mensagens: a chave e nula ou vazia.");
			message = "O sistema apresentou um problema interno.";
		} else {
			try {
				message = getMessageWithParameters(getBundle(fc).getString(key), parameters);
			} catch (RuntimeException e) {
				LOGGER.error("Erro ao configurar mensagens. Key: '" + key + "', Message: '" + message + "', Parameters: '" + parameters + "'");
				message = "O sistema apresentou um problema interno, tente novamente.";
			}
		}
		FacesMessage fm = new FacesMessage(severity, message, message);
		fc.addMessage(null, fm);
	}

}
