package com.vertex;

import java.util.Arrays;
import java.util.Enumeration;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vertex")
public class VertexController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, World!");
    }

    @PostMapping("/export")
    public ResponseEntity<?> exportDataToVertexEnv(HttpServletRequest request, @RequestBody String inputData) {
        try {

            JsonObject jsonInput = JsonParser.parseString(inputData).getAsJsonObject();

            JsonObject dataObj = jsonInput.getAsJsonObject("Data");
            JsonObject rootObj = dataObj.getAsJsonObject("Root");
            JsonArray totalIds = dataObj.getAsJsonArray("TotalIds");
            JsonArray optionalIds = dataObj.getAsJsonArray("OptionalIds");

            String rootId = rootObj.get("id").getAsString();
            System.out.println("Processing Export for Root ID: " + rootId);

            for (int i = 0; i < totalIds.size(); i++) {
                String currentId = totalIds.get(i).getAsString();
                System.out.println("current processing ID ---------------->" + currentId);
            }

            for (int i = 0; i < optionalIds.size(); i++) {
                String currentoptId = optionalIds.get(i).getAsString();
                System.out.println("------------------------------------------------------------------");
                System.out.println("current OptionalId processing ID ---------------->" + currentoptId);
                System.out.println("------------------------------------------------------------------");
            }

            JsonObject response = new JsonObject();
            response.addProperty("status", "success");
            response.addProperty("message", "Exported " + totalIds.size() + " items");
            
            printRequestDetails(request);

            return ResponseEntity.ok(response.toString());

        } catch (Exception e) {

            JsonObject error = new JsonObject();
            error.addProperty("error", e.getMessage());

            return ResponseEntity.internalServerError().body(error.toString());
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