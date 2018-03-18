package com.nextneo.bank.web.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.nextneo.bank.integration.dto.wrapper.UserLoggedDtoWrapper;

public class Authorizer implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent evento) {
		
		boolean isLogged = false;

		FacesContext context = evento.getFacesContext();
		String namePage = context.getViewRoot().getViewId();

		System.out.println(namePage); 

		UserLoggedDtoWrapper userLogged = (UserLoggedDtoWrapper) context.getExternalContext().getSessionMap().get("userLogged");
		if (userLogged != null) {
			isLogged = true;
		} else {
			isLogged = false;
		}

		if (isLogged) {	
			if ("/login.xhtml".equals(namePage) || "/index.xhtml".equals(namePage)) {
				NavigationHandler handler = context.getApplication()
						.getNavigationHandler();
				handler.handleNavigation(context, null, "/home?faces-redirect=true");
				context.renderResponse();
			} else {
				return;
			}
		} else {
			if ("/login.xhtml".equals(namePage)) {
				return;
			} else {
				// redirecionamento para login.xhtml
				NavigationHandler handler = context.getApplication()
						.getNavigationHandler();
				handler.handleNavigation(context, null, "/login?faces-redirect=true");
				context.renderResponse();
			}
		}

	}
	

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
