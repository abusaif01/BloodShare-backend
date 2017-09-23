package com.bloodshare.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.bloodshare.entity.Donor;
import com.bloodshare.service.DonorService;

@Component
public class TokenFilter implements Filter
{
	private static final String HEADER_NAME="authorization";
	private static final String TOKEN_PREFIX="Bearer ";
	private static final Logger logger=LoggerFactory.getLogger(TokenFilter.class);
	
	private DonorService donorService;
	
	@Autowired
	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("\nINSIDE THE FILER ");
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response =(HttpServletResponse) res;
		logger.debug("URI "+request.getRequestURI());
		if(request.getRequestURI().contains( "/authenticate") || request.getRequestURI().equals("/") 
				|| request.getRequestURI().contains("docs/api.html") )
		{
			logger.debug("skipping /authenticate");
			chain.doFilter(req, res);
			return;
		}
		
		String authHeader=request.getHeader(HEADER_NAME);
		logger.debug("Header " + authHeader);
		
		if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().append("{\"message\":\"authentication header incorect\"}");
			return;
		}
		String token = authHeader.substring(TOKEN_PREFIX.length());
		
		logger.debug("Donor service "+donorService);
		Donor donor=donorService.getDonorWithCookie(token);
		logger.debug("Donor "+donor);
		if(donor==null)
		{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().append("{\"message\":\"No data found for provided token\"}");
			return;
		}
		
		request.setAttribute("session_donor", donor);
		chain.doFilter(req, res);
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
	            filterConfig.getServletContext());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
