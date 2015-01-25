package com.sportaholic.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sportaholic.helper.Helper;
import com.sportaholic.model.Uri;
import com.sportaholic.service.UriService;

@WebFilter("/*")
public class UriFilter implements Filter {
	
	@Autowired
	private UriService uriService;
	
	@Override
	public void destroy() { }

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		Uri uri = null;
		try {
			uri = this.uriService.getByFriendlyUri(request.getPathInfo());
			req.setAttribute("uriService", this.uriService);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (uri != null) {
			req.setAttribute("activeUri", uri);
			RequestDispatcher dispatcher = request.getRequestDispatcher(uri.getUri());
			req.setAttribute("uris", Helper.getBreadcrumbs(uri));
			dispatcher.forward(req, res);
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext servletContext = filterConfig.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
		AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
		
		autowireCapableBeanFactory.configureBean(this, "uriService");

	}

}
