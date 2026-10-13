package com.vhre.iotguard.modules.reading.mapper;

import com.vhre.base.core.base.mapper.BaseMapper;
import com.vhre.base.core.base.mapper.BaseMapperConfig;
import com.vhre.iotguard.modules.reading.dto.ReadingDTO;
import com.vhre.iotguard.modules.reading.entity.Reading;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface ReadingMapper extends BaseMapper<Reading, ReadingDTO> {
}
