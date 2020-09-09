package ch.noseryoung.uk.domainModels.auction.unitest;

import ch.noseryoung.uk.domainModels.auction.Auction;
import ch.noseryoung.uk.domainModels.auction.AuctionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuctionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuctionService auctionService;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    //Arrange = Definition Mock
    @Test
    public void findById_requestAuctionById_returnAuction() throws Exception{
        Auction auctionToBeTestedAgainst = new Auction(1, 150);
        given(auctionService.findById(anyInt())).will(invocation ->{
            if("non-existent".equals(invocation.getArgument(0))) throw new Exception();
           return (auctionToBeTestedAgainst);
        });
    //Act = mvc.preform = Anfrage Faken
        mvc.perform(
                MockMvcRequestBuilders.get("/auctions/{id}", auctionToBeTestedAgainst.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //Assert = Entspricht JSon den erwarteten Werten
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(auctionToBeTestedAgainst.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.auctionArticle").value(auctionToBeTestedAgainst.getAuctionArticle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startOfAuction").value(auctionToBeTestedAgainst.getStartOfAuction()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(auctionToBeTestedAgainst.getPrice()));

        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(auctionService, times(1)).findById(integerArgumentCaptor.capture());
        Assertions.assertThat(integerArgumentCaptor.getValue()).isEqualTo(auctionToBeTestedAgainst.getId());
    }


    @Test
    public void getAll_requestAllAuctions_returnsAllAuctions() throws Exception{

        List<Auction> listOfAuctionsToBeTestedAgainst = new ArrayList<>();
        Auction auction1 = new Auction(1,12.0);
        Auction auction2 = new Auction(2,14.0);
        listOfAuctionsToBeTestedAgainst.add(auction1);
        listOfAuctionsToBeTestedAgainst.add(auction2);

        given(auctionService.findAll()).willReturn(listOfAuctionsToBeTestedAgainst);

        mvc.perform(
                MockMvcRequestBuilders.get("/auctions")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value("12.0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value("14.0"));
        verify(auctionService, times(1)).findAll();
    }


    @Test
    public void getAuctionByRange_requestAuctionsInRange_returnsAuctionsInRange() throws Exception{

        List<Auction> listOfAuctionsInRangeToBetestedAgainst = new ArrayList<>();
        Auction auction1 = new Auction(1, 13.0);
        Auction auction2 = new Auction(2, 20.0);
        listOfAuctionsInRangeToBetestedAgainst.add(auction1);
        listOfAuctionsInRangeToBetestedAgainst.add(auction2);

        given(auctionService.getAuctionByRange(anyInt(), anyInt())).willReturn(listOfAuctionsInRangeToBetestedAgainst);

        mvc.perform(
                MockMvcRequestBuilders.get("/auctions/{maxPrice}/{minPrice}", 50, 0)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value("13.0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value("20.0"));
        verify(auctionService, times(1)).getAuctionByRange(anyInt(),anyInt());
    }
}
