package com.vhre.iotguard.modules.auditreading.service;

import com.vhre.base.core.base.service.BaseService;
import com.vhre.iotguard.modules.auditreading.dto.AuditReadingDTO;
import com.vhre.iotguard.modules.auditreading.entity.AuditReading;

import java.util.UUID;

public interface AuditReadingService extends BaseService<AuditReading, AuditReadingDTO, UUID> {
}
