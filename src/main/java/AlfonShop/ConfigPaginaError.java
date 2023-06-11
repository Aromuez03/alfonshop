package AlfonShop;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Configuration
public class ConfigPaginaError implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        // Página de error para excepciones NullPointerException
        ErrorPage errorPageNullPointerException = new ErrorPage(NullPointerException.class, "/error.html");
        registry.addErrorPages(errorPageNullPointerException);

        // Página de error para excepciones IOException
        ErrorPage errorPageIOException = new ErrorPage(IOException.class, "/error.html");
        registry.addErrorPages(errorPageIOException);

        // Página de error para excepciones IllegalArgumentException
        ErrorPage errorPageIllegalArgumentException = new ErrorPage(IllegalArgumentException.class, "/error.html");
        registry.addErrorPages(errorPageIllegalArgumentException);

        // Página de error para excepciones RuntimeException
        ErrorPage errorPageRuntimeException = new ErrorPage(RuntimeException.class, "/error.html");
        registry.addErrorPages(errorPageRuntimeException);

        // Página de error para el código de estado 404 (NOT_FOUND)
        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error.html"));
    }
}
