package hr.foi.diplomski.rad

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import groovy.util.logging.Slf4j
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@Component
@Order(2)
public class RequestResponseLoggingFilter implements Filter {

    @Value('${mySettings.mockServices.enabled}')
    private boolean enabledMock

    @Value('${mySettings.mockServices.port}')
    private int mockPort

    @Override
    void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        def token = req.getHeader("x-call-tracking-token")
//        if (token) {
//            res.setHeader("x-call-tracking-token", token)
//        }

        /*    if (enabledMock && MockMapping.isExistMapping(req.getRequestURL())) {
                String uri = req.getScheme() + "://" +
                        req.getServerName() +
                        ":" + mockPort +
                        req.getRequestURI() +
                        (req.getQueryString() != null ? "?" + req.getQueryString() : "");
                *//*        res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200")
                        res.setHeader("Access-Control-Allow-kizo", "*")*//*


            }*/


        /*     log.info(
                     "Logging Request  {} : {}", req.getMethod(),
                     req.getRequestURI())
     */
        chain.doFilter(request, response);
/*        log.info(
                "Logging Response :{}",
                res.getContentType());*/
    }

    @Override
    void destroy() {

    }
// other methods
}