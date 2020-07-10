package com.senla.rent.configs.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.rent.api.dto.tariff.TariffDTO;
import com.senla.rent.api.dto.tariff.TariffEditDTO;
import com.senla.rent.entity.Tariff;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.security.core.parameters.P;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Configuration
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
