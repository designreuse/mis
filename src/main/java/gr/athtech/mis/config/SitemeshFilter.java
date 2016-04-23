package gr.athtech.mis.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SitemeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/WEB-INF/decorators/default.jsp")
                .addDecoratorPath("/login", "/WEB-INF/decorators/login.jsp");

        /*
        // Map default decorator. This shall be applied to all paths if no other paths match.
             builder.addDecoratorPath("/*", "/default-decorator.html") 
           // Map decorators to path patterns. 
           .addDecoratorPath("/admin/*", "/another-decorator.html")
           .addDecoratorPath("/*.special.jsp", "/special-decorator.html")
           // Map multiple decorators to the a single path.
           .addDecoratorPaths("/articles/*", "/decorators/article.html",
                                             "/decoratos/two-page-layout.html", 
                                             "/decorators/common.html")
           // Exclude path from decoration.
           .addExcludedPath("/javadoc/*")
           .addExcludedPath("/brochures/*");
         */
        builder.setMimeTypes("text/html", "application/xhtml+xml");

    }
}
