package www.hyb.filter;

import www.hyb.untils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(req, resp);
            JdbcUtils.commitClose();
        } catch (IOException | ServletException e) {
            JdbcUtils.rollbackClose();
            e.printStackTrace();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
