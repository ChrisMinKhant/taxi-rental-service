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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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

    @Override
    public String updateDueDate(DueDateRequest dueDateRequest) {

        if (!this.isLicenceNumberExist(dueDateRequest.getLicenceNumber())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, response.LNF);
        }

        Licence temporaryLicence = licenceRepository.findById(dueDateRequest.getLicenceNumber()).get();

        temporaryLicence.setDueDate(LocalDate.parse(dueDateRequest.getUpdatedDueDate()));

        licenceRepository.save(temporaryLicence);

        return response.ACT;
    }

    @Override
    public String removeLicence(String licenceNumber) {
        if (!this.isLicenceNumberExist(licenceNumber)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, response.LNF);
        }

        if (licenceRepository.findById(licenceNumber).get().getType() == LicenceType.CAR) {
            String cngId = carRepository.findById(licenceNumber).get().getCngId();

            cngRepository.deleteById(cngId);
            carRepository.deleteById(licenceNumber);

            Driver temporaryDriver = driverRepository.findByDrivingCarLicenceNumber(licenceNumber).isEmpty() ? null :
                    driverRepository.findByDrivingCarLicenceNumber(licenceNumber).get();

            if (temporaryDriver != null) {
                temporaryDriver.setDrivingCarLicence(null);

                driverRepository.save(temporaryDriver);
            }

            licenceRepository.deleteById(licenceNumber);

            return response.ACT;
        }

        driverRepository.deleteById(licenceNumber);

        licenceRepository.deleteById(licenceNumber);

        return response.ACT;
    }

    private boolean isLicenceNumberExist(String licenceNumber) {
        return !licenceRepository.findById(licenceNumber).isEmpty() ? true : false;
    }
}
