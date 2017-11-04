package havan.blog.demo.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorViewResolver;

public abstract class ErrorView implements ErrorViewResolver {
	public String resolveErrorView(HttpServletRequest req, HttpServletResponse res, Map<String, Object> model){
		return "/error/500";
	}
}
