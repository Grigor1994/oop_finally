package com.grigor.picsart.service;


import com.grigor.picsart.model.electronic.phone.MobilePhone;
import com.grigor.picsart.util.convert.Converter;
import com.grigor.picsart.util.reader.Reader;
import com.grigor.picsart.util.writer.Writer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.grigor.picsart.util.Constants.COMMA;

public class PhoneService {

    private static final String FILE_PATH = "phone.txt";
    private static final int CURRENT_YEAR = 2020;

    public static void addMobilePhone(MobilePhone mobilePhone) throws IOException {
        writeMobilePhoneToFile(mobilePhone, FILE_PATH, true);
    }

    public static List<MobilePhone> getPhoneList() throws FileNotFoundException {
        Reader reader = new Reader(FILE_PATH);
        List<String> data = reader.readAllData();
        List<MobilePhone> mobilePhones = new ArrayList<>();
        for (String datum : data) {
            mobilePhones.add(Converter.convertToMobilePhone(datum));
        }
        return mobilePhones;
    }

    public static void writeMobilePhoneToFile(MobilePhone mobilePhone, String path, boolean append) throws IOException {
        String outputText =
                mobilePhone.getBrand() + COMMA + mobilePhone.getModel() + COMMA
                        + mobilePhone.getSerialNumber() + COMMA + mobilePhone.getReleaseYear() + COMMA
                        + mobilePhone.getWeight() + COMMA + mobilePhone.getOperatingSystem() + COMMA
                        + mobilePhone.getBatteryCapacity() + COMMA + mobilePhone.getDisplayType() + COMMA
                        + mobilePhone.getNetworkType() + COMMA + mobilePhone.isDualSim() + COMMA
                        + mobilePhone.isTouchScreen() + COMMA + mobilePhone.isHasMemoryCardSlot() + COMMA
                        + mobilePhone.isHasMainCamera() + COMMA + mobilePhone.isSelfieCamera() + COMMA
                        + mobilePhone.isBluetooth() + COMMA + mobilePhone.getMemory() + "\n";
        Writer.writeToFile(path, outputText, append);
    }

    public static void printNewestPhone(List<MobilePhone> mobilePhones) {
        int min = (CURRENT_YEAR - mobilePhones.get(0).getReleaseYear());
        for (MobilePhone mobilePhone : mobilePhones) {
            if (min > (CURRENT_YEAR - mobilePhone.getReleaseYear())) {
                min = (CURRENT_YEAR - mobilePhone.getReleaseYear());
                System.out.print(mobilePhone + " ");
            }
        }
    }
}
