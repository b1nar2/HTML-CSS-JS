package com.javateam.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControlURI extends HttpServlet {
    
	private static final long serialVersionUID = -7319756570026146592L;
	
	// <커맨드, 핸들러인스턴스> 매핑 정보 저장
    private Map<String, Object> commandHandlerMap = new HashMap<String, Object>();
    
    public void init(ServletConfig config) throws ServletException {
    	
        String configFile = config.getInitParameter("configFile");
        Properties prop = new Properties();
        FileInputStream fis = null;
        
        try {
		            fis = new FileInputStream(configFile);
		            prop.load(fis);
        } catch (IOException e) {
            throw new ServletException(e);
        } finally {
            if (fis != null) try { fis.close(); } catch(IOException ex) {}
        }
        
        Iterator<Object> keyIter = prop.keySet().iterator();
        
        while( keyIter.hasNext() ) {
        	
	            String command = (String)keyIter.next();
	            String handlerClassName = prop.getProperty(command);
            
            try {            	
		                Class<?> handlerClass = Class.forName(handlerClassName);
		                Object handlerInstance = handlerClass.newInstance();
		                commandHandlerMap.put(command, handlerInstance);
            
            } catch (ClassNotFoundException e) {
            				throw new ServletException(e);
            } catch (InstantiationException e) {
                			throw new ServletException(e);
            } catch (IllegalAccessException e) {
                			throw new ServletException(e);
            }
        } // while
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)  
    		throws ServletException, IOException {
    	
        requestPro(request, response);        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
    		throws ServletException, IOException {
    	
        requestPro(request, response);        
    }
    
    private void requestPro(HttpServletRequest request, HttpServletResponse response)  
    		throws ServletException, IOException {
  
    	String command = request.getRequestURI();
   
    	if (command.indexOf(request.getContextPath()) == 0) {
            command = command.substring(request.getContextPath().length());
        }
        
        CommandAction handler = 
            (CommandAction)commandHandlerMap.get(command);

        String viewPage = null;
        
        try {
        		viewPage = handler.requestPro(request, response);
        } catch(Throwable e) {
            throw new ServletException(e);
        }
        
        RequestDispatcher dispatcher 
        	= request.getRequestDispatcher(viewPage);
        dispatcher.forward(request, response);
    }
    
} // class