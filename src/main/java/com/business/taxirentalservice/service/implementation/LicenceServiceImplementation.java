package com.business.taxirentalservice.service.implementation;

import com.business.taxirentalservice.constant.GeneralResponse;
import com.business.taxirentalservice.constant.LicenceType;
import com.business.taxirentalservice.dto.DueDateRequest;
import com.business.taxirentalservice.model.Driver;
import com.business.taxirentalservice.model.Licence;
import com.business.taxirentalservice.repository.CarRepository;
import com.business.taxirentalservice.repository.CngRepository;
import com.business.taxirentalservice.repository.DriverRepository;
import com.business.taxirentalservice.repository.LicenceRepository;
import com.business.taxirentalservice.service.LicenceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LicenceServiceImplementation implements LicenceService {
    @Autowired
    private LicenceRepository licenceRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CngRepository cngRepository;
    private GeneralResponse response = new GeneralResponse();

    private final Logger logger = LogManager.getLogger(LicenceServiceImplementation.class);

    @Override
    public String updateDueDate(DueDateRequest dueDateRequest) {

        logger.info("updating Due Date...");
        if (!this.isLicenceNumberExist(dueDateRequest.getLicenceNumber())) {
            logger.info("provided licence number not found...");
            return response.LNF;
        }

        Licence temporaryLicence = licenceRepository.findById(dueDateRequest.getLicenceNumber()).get();

        temporaryLicence.setDueDate(LocalDate.parse(dueDateRequest.getUpdatedDueDate()));

        licenceRepository.save(temporaryLicence);

        return response.ACT;
    }

    @Override
    public String removeLicence(String licenceNumber) {
        logger.info("removing licence...");
        if (!this.isLicenceNumberExist(licenceNumber)) {
            logger.info("provided licence number not found.");
            return response.LNF;
        }

        if (licenceRepository.findById(licenceNumber).get().getType() == LicenceType.CAR) {
            logger.info("removing car...");
            String cngId = carRepository.findById(licenceNumber).get().getCngId();

            cngRepository.deleteById(cngId);
            carRepository.deleteById(licenceNumber);

            Driver temporaryDriver = driverRepository.findByDrivingCarLicenceNumber(licenceNumber).isEmpty() ? null :
                    driverRepository.findByDrivingCarLicenceNumber(licenceNumber).get();

            if (temporaryDriver != null) {
                logger.info("removing car from driver...");
                temporaryDriver.setDrivingCarLicence(null);

                driverRepository.save(temporaryDriver);
            }

            licenceRepository.deleteById(licenceNumber);

            return response.ACT;
        }

        logger.info("removing driver...");
        driverRepository.deleteById(licenceNumber);

        licenceRepository.deleteById(licenceNumber);

        return response.ACT;
    }

    private boolean isLicenceNumberExist(String licenceNumber) {
        return !licenceRepository.findById(licenceNumber).isEmpty() ? true : false;
    }
}
