package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.BenefitNotFoundException;
import com.unisalento.snapside.repositories.BenefitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import com.unisalento.snapside.generated.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class BenefitServiceTest {

    @Mock
    private BenefitRepository benefitRepositoryMock;
    @InjectMocks
    private BenefitService benefitService;

    private BenefitEntity benefit = new BenefitEntity();
    private BenefitEntity benefit1 = new BenefitEntity();
    private BenefitEntity benefit2 = new BenefitEntity();

    @BeforeEach
    void setUp() {
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

        benefit1.setIdBenefit(2);
        benefit1.setCheckinDate(new Timestamp(System.currentTimeMillis()));
        benefit1.setCheckoutDate(new Timestamp(System.currentTimeMillis()));
        benefit1.setInterested(2);
        benefit1.setInterestedSellerSide(1);
        benefit1.setPaymentType("Payment type1");
        benefit1.setPaidAmount(1.2);
        benefit1.setPaid(true);
        benefit1.setUserByUserIdUser(new UserEntity());
        benefit1.setAdByAdIdAd(new AdEntity());

        benefit2.setIdBenefit(3);
        benefit2.setCheckinDate(new Timestamp(System.currentTimeMillis()));
        benefit2.setCheckoutDate(new Timestamp(System.currentTimeMillis()));
        benefit2.setInterested(3);
        benefit2.setInterestedSellerSide(1);
        benefit2.setPaymentType("Payment type2");
        benefit2.setPaidAmount(1.3);
        benefit2.setPaid(true);
        benefit2.setUserByUserIdUser(new UserEntity());
        benefit2.setAdByAdIdAd(new AdEntity());
    }

    @Test
    void getAll() throws BenefitNotFoundException {
        when(benefitRepositoryMock.findAll()).thenReturn(Arrays.asList(benefit1, benefit2));
        List<BenefitEntity> benefit0 = benefitService.getAll();

        assertEquals(benefit1.getIdBenefit(), benefit0.get(0).getIdBenefit());
        assertEquals(benefit1.getPaymentType(), benefit0.get(0).getPaymentType());
        assertEquals(benefit2.getIdBenefit(), benefit0.get(1).getIdBenefit());
        assertEquals(benefit2.getPaymentType(), benefit0.get(1).getPaymentType());
    }

    @Test
    void getById() throws BenefitNotFoundException {
        when(benefitRepositoryMock.getOne(1)).thenReturn(benefit);
        BenefitEntity benefit0 = benefitService.getById(benefit.getIdBenefit());
        assertEquals(benefit.getIdBenefit(), benefit0.getIdBenefit());
        assertEquals(benefit.getPaymentType(), benefit0.getPaymentType());
    }

    @Test
    void getAllBenefitsAtAd() throws BenefitNotFoundException {
        AdEntity ad = new AdEntity();
        ad.setIdAd(1);
        when(benefitRepositoryMock.getAllBenefitsAtAd(1)).thenReturn(Arrays.asList(benefit1, benefit2));
        List<BenefitEntity> benefit0 = benefitService.getAllBenefitsAtAd(ad.getIdAd());

        assertEquals(benefit1.getIdBenefit(), benefit0.get(0).getIdBenefit());
        assertEquals(benefit1.getPaymentType(), benefit0.get(0).getPaymentType());
        assertEquals(benefit2.getIdBenefit(), benefit0.get(1).getIdBenefit());
        assertEquals(benefit2.getPaymentType(), benefit0.get(1).getPaymentType());

    }

    @Test
    void getAllBenefitsByCustomer() {
    }

    @Test
    void getAllBenefitsBySeller() {
    }

    @Test
    void save() {
    }
}