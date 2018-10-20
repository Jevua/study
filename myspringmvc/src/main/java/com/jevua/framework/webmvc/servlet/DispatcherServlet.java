package com.jevua.framework.webmvc.servlet;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jevua.framework.annotation.MyAutowired;
import com.jevua.framework.annotation.MyController;
import com.jevua.framework.annotation.MyRequestMapping;
import com.jevua.framework.annotation.MyService;

/**
 * @author jevua
 */
public class DispatcherServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private List<String> classNames = new ArrayList();

    private Map<String, Object> ioc = new HashMap<>();

    private Map<String, Object> handlerMapping = new HashMap<>();


    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //分发请求，根据handlermapping获取方法
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception");
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        if (!handlerMapping.containsKey(url)) {
            resp.getWriter().write("404 NOT FOUND");
            return;
        }

        Method method = (Method) handlerMapping.get(url);
        System.out.println(method);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        //加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //扫描定义的路径
        doScanner(contextConfig.getProperty("scanPackage"));

        //初始化实例
        try {
            doInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        //注入Autowired
        doAutowired();

        //初始化HandlerMapping
        initHandlerMapping();

        System.out.println("My Spring init end..");

    }

    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(MyController.class)) {
                continue;
            }
            String baseUrl = "";
            if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                baseUrl = requestMapping.value();
            }
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                    continue;
                }
                MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                String methodUrl = ("/" + baseUrl + requestMapping.value()).replaceAll("/+", "/");

                handlerMapping.put(methodUrl, method);

                System.out.println("Mapping : " + methodUrl + " method :" + method);
            }
        }
    }

    private void doAutowired() {
        if (ioc.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(MyAutowired.class)) {
                    continue;
                }
                MyAutowired autowired = field.getAnnotation(MyAutowired.class);
                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }

                field.setAccessible(true);

                try {
                    field.set(entry.getValue(), ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }

    }

    private void doInstance() throws IllegalAccessException, InstantiationException {
        if (classNames.isEmpty()) {
            return;
        }
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);

                if (clazz.isAnnotationPresent(MyController.class)) {
                    String beanName = lowerFirstWord(clazz.getName());
                    ioc.put(beanName, clazz.newInstance());

                }
                else
                    if (clazz.isAnnotationPresent(MyService.class)) {
                        MyService service = clazz.getAnnotation(MyService.class);
                        String beanName = service.value();
                        if ("".equals(beanName)) {
                            beanName = lowerFirstWord(clazz.getName());
                        }
                        Object instance = clazz.newInstance();
                        ioc.put(beanName, instance);

                        for (Class<?> i : clazz.getInterfaces()) {
                            beanName = i.getName();
                            if (ioc.containsKey(beanName)) {
                                throw new RuntimeException("beanName is exists");
                            }
                            ioc.put(beanName, instance);
                        }
                    }
                    else {
                        continue;
                    }

            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    private void doScanner(String scanPackage) {
        String resource = "/" + scanPackage.replaceAll("\\.", "/");
        URL url = this.getClass().getClassLoader().getResource(resource);

        File files = new File(url.getFile());

        for (File file : files.listFiles()) {
            String fileName = scanPackage + "." + file.getName();
            if (file.isDirectory()) {
                doScanner(fileName);
            }
            else {
                String className = fileName.replaceAll(".class", "");
                classNames.add(className);
            }

        }

    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    private String lowerFirstWord(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
