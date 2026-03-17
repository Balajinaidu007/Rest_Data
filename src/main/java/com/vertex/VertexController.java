package com.vertex;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/vertex")
public class VertexController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, World!");
    }

    @PostMapping("/export")
    public ResponseEntity<?> exportDataToVertexEnv(@RequestBody ExportRequest inputData) {
        try {

            String rootId = inputData.getData().getRoot().getId();
            System.out.println("Processing Export for Root ID: " + rootId);

            for (String currentId : inputData.getData().getTotalIds()) {
                System.out.println("current processing ID ---------------->" + currentId);
            }

            for (String currentoptId : inputData.getData().getOptionalIds()) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("current OptionalId processing ID ---------------->" + currentoptId);
                System.out.println("------------------------------------------------------------------");
            }

            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Exported " + inputData.getData().getTotalIds().size() + " items");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public static void printRequestDetails(HttpServletRequest request) {

        System.out.println("========== REQUEST BASIC INFO ==========");
        System.out.println("Auth Type: " + request.getAuthType());
        System.out.println("Context Path: " + request.getContextPath());
        System.out.println("Method: " + request.getMethod());
        System.out.println("Path Info: " + request.getPathInfo());
        System.out.println("Path Translated: " + request.getPathTranslated());
        System.out.println("Query String: " + request.getQueryString());
        System.out.println("Remote User: " + request.getRemoteUser());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Servlet Path: " + request.getServletPath());
        System.out.println("Protocol: " + request.getProtocol());
        System.out.println("Scheme: " + request.getScheme());
        System.out.println("Server Name: " + request.getServerName());
        System.out.println("Server Port: " + request.getServerPort());

        System.out.println("\n========== CLIENT INFO ==========");
        System.out.println("Remote Addr: " + request.getRemoteAddr());
        System.out.println("Remote Host: " + request.getRemoteHost());
        System.out.println("Remote Port: " + request.getRemotePort());

        System.out.println("\n========== HEADERS ==========");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            System.out.println(header + " : " + request.getHeader(header));
        }

        System.out.println("\n========== PARAMETERS ==========");
        request.getParameterMap().forEach((key, value) -> {
            System.out.println(key + " = " + Arrays.toString(value));
        });

        System.out.println("\n========== ATTRIBUTES ==========");
        Enumeration<String> attrNames = request.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String attr = attrNames.nextElement();
            System.out.println(attr + " : " + request.getAttribute(attr));
        }

        System.out.println("\n========== COOKIES ==========");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + " = " + cookie.getValue());
            }
        }

        System.out.println("\n========== SESSION ==========");
        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println("Session ID: " + session.getId());
            Enumeration<String> sessionAttrs = session.getAttributeNames();
            while (sessionAttrs.hasMoreElements()) {
                String s = sessionAttrs.nextElement();
                System.out.println(s + " : " + session.getAttribute(s));
            }
        }

        System.out.println("\n========== END REQUEST ==========");
    }
}