package filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter("/*")
public class EncodingFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(EncodingFilter.class);


	public void destroy() {
		LOG.debug("Filter destruction starts");
		// no op
		LOG.debug("Filter destruction finished");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
		
		LOG.debug("Filter starts");
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		LOG.trace("Request uri --> " + httpRequest.getRequestURI());
		
		String requestEncoding = request.getCharacterEncoding();
		if (requestEncoding == null) {
			LOG.trace("Request encoding = null, set encoding --> " + "UTF-8");
			request.setCharacterEncoding("UTF-8");
		}
		
		LOG.debug("Filter finished");		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig)  {
		LOG.debug("Filter initialization starts");

		LOG.debug("Filter initialization finished");
	}

}