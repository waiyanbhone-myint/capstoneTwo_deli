package com.ps.main;

import com.ps.ui.UserInterface;
import com.ps.util.InventoryTracker;

public class Main {
    public static void main(String[] args) {
        InventoryTracker.load();
        UserInterface ui = new UserInterface();
        ui.launchApp();
    }
}