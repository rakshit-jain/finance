package com.hackathon.mario.exception;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import com.hackathon.mario.util.LoggerUtil;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private final ExceptionHandler wrapped;

	public CustomExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;

	}

	public void handle() throws FacesException {
		final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();

		while (i.hasNext()) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext exceptionContext = (ExceptionQueuedEventContext) event.getSource();
			// get the exception from context
			Throwable t = exceptionContext.getException();
			// here you do what ever you want with exception
			try {
				// log error ?
				LoggerUtil.error(t.getMessage(), t);
				String viewId = "/error.xhtml";
				FacesContext context = FacesContext.getCurrentInstance();
				ViewHandler viewHandler = context.getApplication().getViewHandler();
				context.setViewRoot(viewHandler.createView(context, viewId));
				context.getPartialViewContext().setRenderAll(true);
				context.renderResponse();
			} finally {
				// remove it from queue
				i.remove();
			}
		}
		getWrapped().handle();
	}

}
