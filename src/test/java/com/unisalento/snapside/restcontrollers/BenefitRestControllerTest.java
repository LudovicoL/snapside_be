package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.BenefitAdapter;
import com.unisalento.snapside.iservices.IAdService;
import com.unisalento.snapside.iservices.IBenefitService;
import com.unisalento.snapside.iservices.IUserService;
import com.unisalento.snapside.models.BenefitDTO;
import org.junit.jupiter.api.Test;

import com.unisalento.snapside.generated.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class BenefitRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    IBenefitService benefitServiceMock;
    @Mock
    IUserService userServiceMock;
    @Mock
    IAdService adServiceMock;

    private BenefitEntity benefit = new BenefitEntity();
    private BenefitAdapter benefitAdapter = new BenefitAdapter();
    private ObjectMapper mapper = new ObjectMapper();
    private BenefitDTO benefitDTO = new BenefitDTO();
    private List<BenefitEntity> entities = new ArrayList<>();

    private AdEntity ad = new AdEntity();
    private UserEntity user = new UserEntity();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new BenefitRestController(benefitServiceMock, userServiceMock, adServiceMock))
                .build();
        benefit.setIdBenefit(1);
        benefit.setCheckinDate(new Timestamp(System.currentTimeMillis()));
        benefit.setCheckoutDate(new Timestamp(System.currentTimeMillis()));
        benefit.setInterested(1);
        benefit.setInterestedSellerSide(1);
        benefit.setPaymentType("Payment type");
        benefit.setPaidAmount(1.1);
        benefit.setPaid(true);
        benefit.setUserByUserIdUser(new UserEntity());
        benefit.setAdByAdIdAd(new AdEntity());

        benefitDTO.setIdBenefit(1);
        benefitDTO.setCheckinDate(new Timestamp(System.currentTimeMillis()));
        benefitDTO.setCheckoutDate(new Timestamp(System.currentTimeMillis()));
        benefitDTO.setInterested(1);
        benefitDTO.setPaymentType("Payment type");
        benefitDTO.setPaidAmount(1.1);
        benefitDTO.setPaid(true);
        benefitDTO.setUser_id_user(1);
        benefitDTO.setAd_id_ad(1);
        benefitDTO.setInterested_seller_side(1);

        for(int i = 0; i < 5; i++) entities.add(benefit);
    }

    @Test
    void getAll() throws Exception {
        when(benefitServiceMock.getAll()).thenReturn(entities);
        mockMvc.perform(get("/benefit/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(benefitServiceMock.getById(1)).thenReturn(benefit);
        mockMvc.perform(get("/benefit/getById/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void getAllBenefitsAtAd() throws Exception {
        when(benefitServiceMock.getAllBenefitsAtAd(1)).thenReturn(entities);
        mockMvc.perform(get("/benefit/getAllBenefitsAtAd/{id_ad}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void getAllBenefitsByCustomer() throws Exception {
        when(benefitServiceMock.getAllBenefitsByCustomer(1)).thenReturn(entities);
        mockMvc.perform(get("/benefit/getAllBenefitsByCustomer/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void getAllBenefitsBySeller() throws Exception {
        when(benefitServiceMock.getAllBenefitsBySeller(1)).thenReturn(entities);
        mockMvc.perform(get("/benefit/getAllBenefitsBySeller/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void saveBenefit() throws Exception {
        when(adServiceMock.getById(benefitDTO.getAd_id_ad())).thenReturn(ad);
        when(userServiceMock.getById(benefitDTO.getUser_id_user())).thenReturn(user);
        mockMvc.perform(post("/benefit/save")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(benefitDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}