package gr.athtech.mis.servlet3;

import gr.athtech.mis.config.MySitemeshFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import gr.athtech.mis.config.SpringRootConfig;
import gr.athtech.mis.config.SpringSecurityConfig;
import gr.athtech.mis.config.SpringWebConfig;
import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;

public class MyWebInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    /*
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        addSitemeshFilter(servletContext);
    }
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringRootConfig.class/*, SpringSecurityConfig.class*/};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return new Filter[]{characterEncodingFilter, new MySitemeshFilter()};
    }

}
