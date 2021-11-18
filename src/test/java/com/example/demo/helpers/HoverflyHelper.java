package com.example.demo.helpers;

import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyMode;
import io.specto.hoverfly.junit.core.SimulationSource;

import java.io.Closeable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HoverflyHelper implements Closeable {
    private final Hoverfly hoverfly;
    private final HoverflyMode mode;
    private final Path simulation;

    private boolean fitToExport = true;

    protected HoverflyHelper(Hoverfly hoverfly, HoverflyMode mode, Path simulation) {
        this.hoverfly = hoverfly;
        this.mode = mode;
        this.simulation = simulation;
    }

    public static Path getSimulationPath(String name) {
        String path = "src/test/resources/stubs";
        return Paths.get(path).resolve(name + ".json");
    }

    public static HoverflyHelper simulateOrCapture(Hoverfly hoverfly, String simulation) {
        Path simulationPath = getSimulationPath(simulation);
        HoverflyMode mode = HoverflyMode.CAPTURE;
        if (Files.isReadable(simulationPath)) {
            mode = HoverflyMode.SIMULATE;
        }
        hoverfly.setMode(mode);
        if (mode == HoverflyMode.SIMULATE) {
            hoverfly.simulate(SimulationSource.file(simulationPath));
        }

        return new HoverflyHelper(hoverfly, mode, simulationPath);
    }

    @Override
    public void close() {
        if (mode == HoverflyMode.CAPTURE && fitToExport) {
            hoverfly.exportSimulation(simulation);
        }
        hoverfly.reset();
    }

    /**
     * Prevents the simulation from being exported.
     * Only works in capture mode.
     */
    public void preventExport() {
        this.fitToExport = false;
    }
}
