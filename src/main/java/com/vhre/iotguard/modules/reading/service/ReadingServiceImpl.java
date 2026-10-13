package com.vhre.iotguard.modules.reading.service;

import com.vhre.base.core.base.service.BaseServiceImpl;
import com.vhre.iotguard.modules.reading.dto.ReadingDTO;
import com.vhre.iotguard.modules.reading.entity.Reading;
import com.vhre.iotguard.modules.reading.mapper.ReadingMapper;
import com.vhre.iotguard.modules.reading.repository.ReadingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReadingServiceImpl extends BaseServiceImpl<Reading, ReadingDTO, UUID> implements ReadingService {

    public ReadingServiceImpl(ReadingRepository repository, ReadingMapper mapper) {
        super(repository, mapper);
    }
}
