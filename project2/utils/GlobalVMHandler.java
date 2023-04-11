package project2.utils;

import project2.vmHandler.VMHandler;

public class GlobalVMHandler {
    private static VMHandler vmh;

    public static VMHandler createVMHandler(){
        return vmh = new VMHandler();
    }

    public static VMHandler getVmh() {
        return vmh;
    }
}
