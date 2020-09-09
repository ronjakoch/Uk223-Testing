package ch.noseryoung.uk.domainModels.auction.dto;

import ch.noseryoung.uk.domainModels.auction.Auction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

// This annotation defines this class as a mapper
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuctionMapper {

    // This method signature generates a method which maps a DTO to it's representing business object
    Auction fromDTO(AuctionDTO dto);

    // This method signature generates a method which maps a list of DTOs to a list of it's representing business objects
    List<Auction> fromDTOs(List<AuctionDTO> dtos);

    // This method signature generates a method which maps a set of DTOs to a set of it's representing business objects
    Set<Auction> fromDTOs(Set<AuctionDTO> dtos);

    // This method signature generates a method which maps a business object to it's representative DTO
    AuctionDTO toDTO(Auction dm);

    // This method signature generates a method which maps a list of business objects to a list of it's representative DTOs
    List<AuctionDTO> toDTOs(List<Auction> dms);

    // This method signature generates a method which maps a set of business objects to a set of it's representative DTOs
    Set<AuctionDTO> toDTOs(Set<Auction> dms);

}


