package zttc.itat.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.bind.annotation.RequestParam;

import zttc.itat.model.SystemContext;

public class SystemContextFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        int offset = 0;

        try {
            offset = Integer.parseInt(req.getParameter("pager.offset"));
        } catch (NumberFormatException e) {
        }
        try {
            SystemContext.setOffset(offset);
            SystemContext.setSize(15);
            //此处代码必须要加,需要看一下filter的原理
            chain.doFilter(req, resp);
        } finally {
            SystemContext.removeOffset();
            SystemContext.removeSize();
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
