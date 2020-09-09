package ch.noseryoung.uk.domainModels.auction.unitest;

import ch.noseryoung.uk.domainModels.auction.Auction;
import ch.noseryoung.uk.domainModels.auction.AuctionRepository;
import ch.noseryoung.uk.domainModels.auction.AuctionService;
import org.junit.jupiter.api.AfterEach;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuctionServicelmplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private AuctionService auctionService;

    @MockBean
    private AuctionRepository auctionRepository;

    @Test
    public void getAuctionByRange_requestAuctionsInRange_returnsAuctionsInRange() throws Exception{

        List<Auction> listOfAuctionsInRangeToBetestedAgainst = new ArrayList<>();
        Auction auction1 = new Auction(1, -1.0);
        Auction auction2 = new Auction(2, 0.0);
        Auction auction3 = new Auction(3, 13.0);
        Auction auction4 = new Auction(4, 50.0);
        Auction auction5 = new Auction(5, 56.0);
        listOfAuctionsInRangeToBetestedAgainst.add(auction1);
        listOfAuctionsInRangeToBetestedAgainst.add(auction2);
        listOfAuctionsInRangeToBetestedAgainst.add(auction3);
        listOfAuctionsInRangeToBetestedAgainst.add(auction4);
        listOfAuctionsInRangeToBetestedAgainst.add(auction5);

        given(auctionRepository.findAll()).willReturn(listOfAuctionsInRangeToBetestedAgainst);

        List<Auction> listOfAuctionsReturned = auctionService.getAuctionByRange(50,0);
        Assertions.assertThat(listOfAuctionsReturned.size()).isEqualTo(1);
        Assertions.assertThat(listOfAuctionsReturned.get(0).getId()).isEqualTo(auction3.getId());
        Assertions.assertThat(listOfAuctionsReturned.get(0).getPrice()).isEqualTo(auction3.getPrice());

        verify(auctionRepository, times(1)).findAll();
    }
}