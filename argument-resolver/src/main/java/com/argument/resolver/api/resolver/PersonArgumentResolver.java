package com.argument.resolver.api.resolver;

import com.argument.resolver.api.Person;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PersonArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Person.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        var name = webRequest.getParameter("name"); // if nullable field
        var age = getAge(webRequest);
        return new Person(name, age);
    }

    private int getAge(NativeWebRequest webRequest) {
        var age = webRequest.getParameter("age");
        if (age == null || isInteger(age)) {
            throw new IllegalArgumentException("parameter age(%s) is invalid".formatted(age));
        }
        return Integer.parseInt(age);
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
