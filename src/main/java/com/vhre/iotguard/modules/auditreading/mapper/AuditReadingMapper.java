package com.vhre.iotguard.modules.auditreading.mapper;

import com.vhre.base.core.base.mapper.BaseMapper;
import com.vhre.base.core.base.mapper.BaseMapperConfig;
import com.vhre.iotguard.modules.auditreading.dto.AuditReadingDTO;
import com.vhre.iotguard.modules.auditreading.entity.AuditReading;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface AuditReadingMapper extends BaseMapper<AuditReading, AuditReadingDTO> {

    /**
     * processedAt no se mapea desde el DTO: lo fija la entidad (por defecto ahora)
     * o el consumidor que registre la auditoria.
     */
    @Override
    @Mapping(target = "processedAt", ignore = true)
    AuditReading toEntity(AuditReadingDTO dto);

    /**
     * Como el metodo se re-declara, se repiten los ignores de los campos de auditoria
     * que el BaseMapper protege, mas el de processedAt (metadato de auditoria que
     * no se sobreescribe en actualizaciones).
     */
    @Override
    @Mapping(target = "processedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void updateEntityFromDto(AuditReadingDTO dto, @MappingTarget AuditReading entity);
}
