package project2.loaders;

import project2.utils.Globals;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProgramLoader {

    public final static void loadPrograms(){
        File configFile = new File("./cfg/programs.config");

        if (configFile.exists()) {
            Globals.hasProgramFile = true;
        }
        else {
            System.out.println("The programs.config file does not exist");
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("./cfg/programs.config"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int cores = 0;
                int ram = 0;
                int ssd = 0;
                int bandwidth = 0;
                int gpu = 0;
                int time = 0;

                for (String part : parts) {
                    String[] keyValue = part.split(":");
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    if (key.equals("cores")) {
                        cores = Integer.parseInt(value);
                    } else if (key.equals("ram")) {
                        ram = Integer.parseInt(value);
                    } else if (key.equals("ssd")) {
                        ssd = Integer.parseInt(value);
                    } else if (key.equals("bandwidth")) {
                        bandwidth = Integer.parseInt(value);
                    } else if (key.equals("gpu")) {
                        gpu = Integer.parseInt(value);
                    } else if (key.equals("time")) {
                        time = Integer.parseInt(value);
                    }
                }

                Globals.globalProgramHandler.createProgram(cores, ram, ssd, gpu, bandwidth, time);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
