package com.naeunminchocofarm.ncf_api.controller;

import com.naeunminchocofarm.ncf_api.lib.auth.RequestAuth;
import com.naeunminchocofarm.ncf_api.lib.auth.AuthInfo;
import com.naeunminchocofarm.ncf_api.lib.jwt.JwtHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class AuthExampleController {
    private static final Logger log = LogManager.getLogger(AuthExampleController.class);
    private final JwtHandler jwtHandler;

    public AuthExampleController(JwtHandler jwtHandler) {
        this.jwtHandler = jwtHandler;
    }

    @GetMapping("/examples/auth")
    public void authExample(@RequestAuth() AuthInfo authInfo) {
        System.out.println(authInfo);
    }

    @PostMapping("/examples/login")
    public String getToken(@RequestParam("id") Long id, @RequestParam("roleNames") Set<String> roleNames, @RequestParam("roleFlags") Set<Integer> roleFlags) {
        log.info("id = {}, roleNames = {}, roleFlags = {}", id, roleNames.toString(), roleFlags.toString());
        return this.jwtHandler.generateToken(id, roleNames, roleFlags);
    }
}
