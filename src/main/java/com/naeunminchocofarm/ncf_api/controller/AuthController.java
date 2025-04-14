package com.naeunminchocofarm.ncf_api.controller;

import com.naeunminchocofarm.ncf_api.lib.auth.ExpiredAuthorizationDataException;
import com.naeunminchocofarm.ncf_api.lib.auth.RequestAuth;
import com.naeunminchocofarm.ncf_api.lib.auth.AuthInfo;
import com.naeunminchocofarm.ncf_api.lib.jwt.JwtHandler;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class AuthController {
    private static final Logger log = LogManager.getLogger(AuthController.class);
    private final JwtHandler jwtHandler;

    public AuthController(JwtHandler jwtHandler) {
        this.jwtHandler = jwtHandler;
    }

//    @GetMapping("/auth") 다시받을라는깡동이었는거같은디...
//    public void reAuth(@RequestAuth AuthInfo authInfo, HttpServletResponse response) {
//        if (authInfo == null) {
//            throw new ExpiredAuthorizationDataException();
//        }
//        String newAccessToken = jwtHandler.generateToken();
//        response.setHeader("Authorization", "Bearer " + newAccessToken);
//    }

    @PostMapping("/login")
    public String getToken(@RequestParam("id") Long id, @RequestParam("roleNames") Set<String> roleNames, @RequestParam("roleFlags") Set<Integer> roleFlags) {
        log.info("id = {}, roleNames = {}, roleFlags = {}", id, roleNames.toString(), roleFlags.toString());
        return this.jwtHandler.generateToken(id, roleNames, roleFlags);
    }
}
