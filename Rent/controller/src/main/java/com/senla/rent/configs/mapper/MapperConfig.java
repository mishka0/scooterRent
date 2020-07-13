package com.senla.rent.configs.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.modelmapper.config.Configuration.AccessLevel;
import java.time.Duration;

public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setSkipNullEnabled(true)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);

        Converter<String, Duration> convertStringToDuration = new AbstractConverter<String, Duration>() {
            @Override
            protected Duration convert(String duration) {
                return duration == null ? null : Duration.parse(duration);
            }
        };
        modelMapper.addConverter(convertStringToDuration);
        return modelMapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
