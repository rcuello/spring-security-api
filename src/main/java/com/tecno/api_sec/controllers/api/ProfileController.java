package com.tecno.api_sec.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecno.api_sec.configuration.security.filters.AuditDetails;
import com.tecno.api_sec.persistence.entity.UserEntity;
import com.tecno.api_sec.services.impl.AuditExtractorService;
import com.tecno.api_sec.services.impl.AuthenticationServiceImpl;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private AuditExtractorService auditExtractorService;

    @GetMapping("/my-profile")
    public ResponseEntity<UserEntity> findMyProfile() {
        UserEntity user = authenticationService.findCurrentUser();

        AuditDetails audit = auditExtractorService.extractClientAudit();
        System.out.println(audit.getRemoteAddress());
        System.out.println(audit.getUserAgent());

        return ResponseEntity.ok(user);
    }
}
