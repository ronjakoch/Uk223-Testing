package ch.noseryoung.uk.domainModels.auction.unitest;

import ch.noseryoung.uk.domainModels.auction.Auction;
import ch.noseryoung.uk.domainModels.auction.AuctionRepository;
import ch.noseryoung.uk.domainModels.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:resource/application-test.properties")
class AuctionRepositoryTest {

    Auction auctionToBeTestedAgainst1;
    Auction auctionToBeTestedAgainst2;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;


    @Resource
    private AuctionRepository auctionRepository;

    @Test
    public void givenAuction_whenSave_thenGetOk(){
        auctionToBeTestedAgainst1 = new Auction(1, 13);
        auctionToBeTestedAgainst2 = new Auction(2, 14);
        List<Auction> listOfAuctionsToBeTestedAgainst = new ArrayList<>();
        listOfAuctionsToBeTestedAgainst.add(auctionToBeTestedAgainst1);
        listOfAuctionsToBeTestedAgainst.add(auctionToBeTestedAgainst2);

        int auctionIdFromDB1 = testEntityManager.persistAndGetId(auctionToBeTestedAgainst1, Integer.class);
        int auctionIdFromDB2 = testEntityManager.persistAndGetId(auctionToBeTestedAgainst1, Integer.class);

        List<Integer> listOfIdsFromDB = new ArrayList<>();
        listOfIdsFromDB.add(auctionIdFromDB1);
        listOfIdsFromDB.add(auctionIdFromDB2);

        List<Auction> auctionsFromDB = auctionRepository.findAll();

        assertThat(auctionToBeTestedAgainst1.getId()).isEqualTo(auctionsFromDB.get(0).getId());
        assertThat(auctionToBeTestedAgainst1.getPrice()).isEqualTo(auctionsFromDB.get(0).getPrice());
        assertThat(auctionToBeTestedAgainst2.getId()).isEqualTo(auctionsFromDB.get(1).getId());
        assertThat(auctionToBeTestedAgainst2.getPrice()).isEqualTo(auctionsFromDB.get(1).getPrice());
    }
}
