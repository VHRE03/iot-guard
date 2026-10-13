package com.vhre.iotguard.modules.reading.controller;

import com.vhre.base.core.base.controller.BaseController;
import com.vhre.iotguard.modules.reading.dto.ReadingDTO;
import com.vhre.iotguard.modules.reading.entity.Reading;
import com.vhre.iotguard.modules.reading.service.ReadingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/readings")
public class ReadingController extends BaseController<Reading, ReadingDTO, UUID> {

    public ReadingController(ReadingService service) {
        super(service);
    }
}
