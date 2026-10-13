package com.vhre.iotguard.modules.reading.service;

import com.vhre.base.core.base.service.BaseServiceImpl;
import com.vhre.iotguard.kafka.events.ReadingEvent;
import com.vhre.iotguard.kafka.publisher.ReadingPublisher;
import com.vhre.iotguard.modules.reading.dto.ReadingDTO;
import com.vhre.iotguard.modules.reading.entity.Reading;
import com.vhre.iotguard.modules.reading.mapper.ReadingMapper;
import com.vhre.iotguard.modules.reading.repository.ReadingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReadingServiceImpl extends BaseServiceImpl<Reading, ReadingDTO, UUID> implements ReadingService {

    private final ReadingPublisher publisher;

    public ReadingServiceImpl(
            ReadingRepository repository,
            ReadingMapper mapper,
            ReadingPublisher publisher
    ) {
        super(repository, mapper);
        this.publisher = publisher;
    }

    @Override
    public void afterSave(Reading saved, ReadingDTO dto) {
        publisher.publish(toEvent(saved));
    }

    private ReadingEvent toEvent(Reading reading) {
        return new ReadingEvent(
                reading.getSensorId(),
                reading.getTemperature(),
                reading.getHumidity(),
                reading.getCreatedAt()
        );
    }
}
