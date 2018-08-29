package com.dy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.dy.filter
 *
 * @author dengy
 * @date 2018-08-2018/8/29
 * @since jdk1.6
 */
@Slf4j
public class AccessFilter extends ZuulFilter {
    /**
     * 过滤器类型，决定了过滤器在请求的哪个生命周期中执行
     * <p>
     * pre：可以在请求被路由之前调用
     * route：在路由请求时候被调用
     * post：在route和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 通过int值来定义过滤器的执行顺序，数值越小优先级越高
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否会被执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();

        HttpServletRequest request = ctx.getRequest();

        Object accessToken = request.getParameter("token");

        if (accessToken==null){
            //不进行路由
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(402);
            ctx.setResponseBody("{\"result\":\"token is not empty!\",\"resultCode\":\"1001\",\"resultMsg\":\"不得了啊,这家伙可以任意访问该资源!还好Zuul Filter拦截住了\"}");
            //透传到下一个filter
            ctx.set("key","自定义");
        }
        HttpServletResponse response = ctx.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setLocale(new java.util.Locale("zh","CN"));
        return null;
    }
}
