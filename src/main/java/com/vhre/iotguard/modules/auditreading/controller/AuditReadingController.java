package com.vhre.iotguard.modules.auditreading.controller;

import com.vhre.base.core.base.controller.BaseController;
import com.vhre.iotguard.modules.auditreading.dto.AuditReadingDTO;
import com.vhre.iotguard.modules.auditreading.entity.AuditReading;
import com.vhre.iotguard.modules.auditreading.service.AuditReadingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Endpoints restringidos igual que fin-pay restringe su TransactionController: los
 * registros de auditoria son inmutables una vez creados, asi que solo se exponen
 * creacion y lecturas; {@code PUT} y {@code DELETE} responden 405.
 */
@RestController
@RequestMapping("/api/v1/audit-readings")
public class AuditReadingController extends BaseController<AuditReading, AuditReadingDTO, UUID> {

    public AuditReadingController(AuditReadingService service) {
        super(service, ApiMethod.CREATE, ApiMethod.READ_ALL, ApiMethod.READ_ONE);
    }
}
