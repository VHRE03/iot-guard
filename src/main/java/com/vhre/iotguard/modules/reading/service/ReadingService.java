package com.vhre.iotguard.modules.reading.service;

import com.vhre.base.core.base.service.BaseService;
import com.vhre.iotguard.modules.reading.dto.ReadingDTO;
import com.vhre.iotguard.modules.reading.entity.Reading;

import java.util.UUID;

public interface ReadingService extends BaseService<Reading, ReadingDTO, UUID> {
}
