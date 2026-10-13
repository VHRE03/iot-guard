package com.vhre.iotguard.modules.auditreading.service;

import com.vhre.base.core.base.service.BaseServiceImpl;
import com.vhre.iotguard.modules.auditreading.dto.AuditReadingDTO;
import com.vhre.iotguard.modules.auditreading.entity.AuditReading;
import com.vhre.iotguard.modules.auditreading.mapper.AuditReadingMapper;
import com.vhre.iotguard.modules.auditreading.repository.AuditReadingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuditReadingServiceImpl extends BaseServiceImpl<AuditReading, AuditReadingDTO, UUID> implements AuditReadingService {

    public AuditReadingServiceImpl(AuditReadingRepository repository, AuditReadingMapper mapper) {
        super(repository, mapper);
    }
}
