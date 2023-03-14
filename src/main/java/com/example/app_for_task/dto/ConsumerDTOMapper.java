package com.example.app_for_task.dto;

import com.example.app_for_task.model.consumer.Consumer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsumerDTOMapper {
    ConsumerDTO inDtoMap(Consumer consumer);
    Consumer fromDtoMap(ConsumerDTO consumerDTO);
}
