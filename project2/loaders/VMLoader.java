package project2.loaders;

import project2.utils.GlobalVMHandler;
import project2.utils.Globals;
import project2.virtualMachines.vmExtras.OsType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class VMLoader {
    public static void loadVMS(){
        File configFile = new File("./cfg/vms.config");

        if (configFile.exists()) {
            Globals.hasVMFile = true;
        }
        else {
            System.out.println("The vms.config file does not exist");
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("./cfg/vms.config"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String os = "";
                OsType OS = OsType.FEDORA;
                int cores = 0;
                int ram = 0;
                int ssd = 0;
                int bandwidth = 0;
                int gpu = 0;

                for (String part : parts) {
                    String[] keyValue = part.split(":");
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    if (key.equals("os")) {
                        os = value;
                        if(os.equalsIgnoreCase("Windows"))
                            OS = OsType.WINDOWS;
                        else if(os.equalsIgnoreCase("Ubuntu"))
                            OS = OsType.UBUNTU;
                        else
                            OS = OsType.FEDORA;
                    } else if (key.equals("cores")) {
                        cores = Integer.parseInt(value);
                    } else if (key.equals("ram")) {
                        ram = Integer.parseInt(value);
                    } else if (key.equals("ssd")) {
                        ssd = Integer.parseInt(value);
                    } else if (key.equals("bandwidth")) {
                        bandwidth = Integer.parseInt(value);
                    } else if (key.equals("gpu")) {
                        gpu = Integer.parseInt(value);
                    }
                }

                GlobalVMHandler.getVmh().createVM(cores, ram, OS, ssd, bandwidth, gpu);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
