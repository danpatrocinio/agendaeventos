package br.com.agenda.controller.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public abstract class BaseReportMB<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> params = new HashMap<String, Object>();

	void addMessage(String componentId, String errorMessage) {
		FacesMessage message = new FacesMessage(errorMessage);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(componentId, message);

	}

	void addParam(String key, Object value) {
		this.params.put(key, value);
	}

	void executeReport(JRDataSource jrRS) {

		ServletOutputStream servletOutputStream = null;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext()
		        .getResponse();

		try {
			servletOutputStream = response.getOutputStream();

			JasperRunManager.runReportToPdfStream(new FileInputStream(new File(getFilePath())),
			        response.getOutputStream(), params, jrRS);

			response.setContentType("application/pdf");

			context.renderResponse();
			context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				servletOutputStream.flush();
				servletOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	void gerarRelatorio(List<T> list) {
		executeReport(new JRBeanCollectionDataSource(list));
	}

	void gerarRelatorio(ResultSet rs) {
		executeReport(new JRResultSetDataSource(rs));
	}

	abstract String getJasperNameFile();

	private String getFilePath() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext context = (ServletContext) externalContext.getContext();

		return context.getRealPath("WEB-INF/reports/" + getJasperNameFile() + ".jasper");
	}

}
