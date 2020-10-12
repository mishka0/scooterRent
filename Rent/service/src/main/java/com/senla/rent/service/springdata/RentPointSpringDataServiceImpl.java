package com.senla.rent.service.springdata;

import com.senla.rent.api.dao.springdata.RentPointSpringDataRepository;
import com.senla.rent.api.dto.projections.RentPointProj;
import com.senla.rent.api.dto.rentalpoint.RentPointWithTownDTO;
import com.senla.rent.api.service.springdata.RentPointSpringDataService;
import com.senla.rent.entity.RentPoint;
import com.senla.rent.service.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class RentPointSpringDataServiceImpl implements RentPointSpringDataService {
    @Autowired
    private RentPointSpringDataRepository rentPointSpringDataRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public List<RentPointWithTownDTO> getAllPoints(Pageable page) {
        try {
            return rentPointSpringDataRepository.findAll(page)
                    .stream()
                    .map(rentalPoint -> modelMapper.map(rentalPoint, RentPointWithTownDTO.class))
                    .collect(Collectors.toList());

        } catch (RuntimeException exception) {
            log.error("Can't get rent points! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get rent points!");
        }
    }

    @Override
    public RentPointWithTownDTO findByAddress(String address) {
        return modelMapper.map(rentPointSpringDataRepository.findByAddress(address), RentPointWithTownDTO.class);
    }

    @Override
    public RentPointWithTownDTO findByIdFetchTown(Integer id) {
        return modelMapper.map(rentPointSpringDataRepository.findByIdFetchTown(id), RentPointWithTownDTO.class);
    }

    @Override
    public RentPointWithTownDTO findByIdNative(Integer id) {
        return modelMapper.map(rentPointSpringDataRepository.findByIdNative(id), RentPointWithTownDTO.class);    }

    @Override
    public RentPointWithTownDTO findWithSpec() {
        RentPoint rentPoint = rentPointSpringDataRepository.findAll(RentPointSpringDataRepository.spec()).get(0);
        RentPointWithTownDTO rentPointWithTownDTO = modelMapper.map(rentPoint, RentPointWithTownDTO.class);
        return rentPointWithTownDTO;
    }

    @Override
    public RentPointProj getProj(Integer id) {
        return rentPointSpringDataRepository.getRentProj(id);
    }
}
