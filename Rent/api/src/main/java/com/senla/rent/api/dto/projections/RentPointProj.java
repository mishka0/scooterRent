package com.senla.rent.api.dto.projections;


public interface RentPointProj {
     Integer getId();

     String getAddress();

     TownProj getTown();
}
