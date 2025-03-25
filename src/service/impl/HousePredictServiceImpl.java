package service.impl;

import dto.HousePredict;
import service.HousePredictService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HousePredictServiceImpl implements HousePredictService {

    @Override
    public String predict(HousePredict housePredict) {
        try {
            // Chuẩn bị danh sách tham số (gửi sang Python)
            List<String> command = new ArrayList<>();
            command.add("python");
            command.add("predict.py");
            command.add(String.valueOf(housePredict.getArea()));
            command.add(String.valueOf(housePredict.getFloors()));
            command.add(String.valueOf(housePredict.getBedrooms()));
            command.add(String.valueOf(housePredict.getBathrooms()));
            command.add(String.valueOf(housePredict.getFrontage()));
            command.add(String.valueOf(housePredict.getRoadWidth()));
            command.add(housePredict.getAddress());

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                return line;
            }

            int exitCode = process.waitFor();
            System.out.println("Python script exited with code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
