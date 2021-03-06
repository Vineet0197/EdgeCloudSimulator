package org.vin.edgecloudsim.applications.smart_building;

import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.core.CloudSim;
import org.vin.edgecloudsim.core.ScenarioFactory;
import org.vin.edgecloudsim.core.SimManager;
import org.vin.edgecloudsim.core.SimSettings;
import org.vin.edgecloudsim.utility.SimLogger;
import org.vin.edgecloudsim.utility.SimUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SmartBuildingApplication {

    /**
     * main() to run this Smart Building Application
     */
    public static void main(String[] args) {
        //disable console output of cloudsim library
        Log.disable();

        //enable console output and file output of this application
        SimLogger.enablePrintLog();

        int iterationNumber = 1;
        String configFile;
        String outputFolder;
        String edgeDevicesFile;
        String applicationsFile;
        if (args.length == 5){
            configFile = args[0];
            edgeDevicesFile = args[1];
            applicationsFile = args[2];
            outputFolder = args[3];
            iterationNumber = Integer.parseInt(args[4]);
        } else{
            SimLogger.printLine("Simulation setting file, output folder and iteration number are not provided! Using default ones...");
            configFile = "scripts/smart_building_app/config/default_config.properties";
            applicationsFile = "scripts/smart_building_app/config/applications.xml";
            edgeDevicesFile = "scripts/smart_building_app/config/edge_devices.xml";
            outputFolder = "sim_results/ite" + iterationNumber;
        }

        // Create Output directory if not exists
        File directory = new File(outputFolder);
        if (!directory.exists()) {
            SimLogger.printLine("Output folder doesn't exists. Creating...");
            if (directory.mkdirs()) {
                SimLogger.printLine("Output folder Created successfully");
            } else {
                SimLogger.printLine("Not able to create output folder directory");
            }
        }
        //load settings from configuration file
        SimSettings SS = SimSettings.getInstance();
        if(!SS.initialize(configFile, edgeDevicesFile, applicationsFile)){
            SimLogger.printLine("cannot initialize simulation settings!");
            System.exit(0);
        }

        if(SS.getFileLoggingEnabled()){
            SimLogger.enableFileLog();
            SimUtils.cleanOutputFolder(outputFolder);
        }

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date SimulationStartDate = Calendar.getInstance().getTime();
        String now = df.format(SimulationStartDate);
        SimLogger.printLine("Simulation started at " + now);
        SimLogger.printLine("----------------------------------------------------------------------");

        for(int j=SS.getMinNumOfMobileDev(); j<=SS.getMaxNumOfMobileDev(); j+=SS.getMobileDevCounterSize()) {
            for(int k=0; k<SS.getSimulationScenarios().length; k++)
            {
                for(int i=0; i<SS.getOrchestratorPolicies().length; i++)
                {
                    String simScenario = SS.getSimulationScenarios()[k];
                    String orchestratorPolicy = SS.getOrchestratorPolicies()[i];
                    Date ScenarioStartDate = Calendar.getInstance().getTime();
                    now = df.format(ScenarioStartDate);

                    SimLogger.printLine("Scenario started at " + now);
                    SimLogger.printLine("Scenario: " + simScenario + " - Policy: " + orchestratorPolicy + " - #iteration: " + iterationNumber);
                    SimLogger.printLine("Duration: " + SS.getSimulationTime()/60 + " min (warm up period: "+ SS.getWarmUpPeriod()/60 +" min) - #devices: " + j);
                    SimLogger.getInstance().simStarted(outputFolder,"SIMRESULT_" + simScenario + "_"  + orchestratorPolicy + "_" + j + "DEVICES");

                    try {
                        // First step: Initialize the CloudSim package. It should be called
                        // before creating any entities.
                        int num_user = 2;   // number of grid users
                        Calendar calendar = Calendar.getInstance();

                        // Initialize the CloudSim library
                        CloudSim.init(num_user, calendar, false, 0.01);

                        // Generate EdgeCloudsim Scenario Factory
                        ScenarioFactory sampleFactory = new SmartBuildingAppScenarioFactory(j,SS.getSimulationTime(), orchestratorPolicy, simScenario);

                        // Generate EdgeCloudSim Simulation Manager
                        SimManager manager = new SimManager(sampleFactory, j, simScenario, orchestratorPolicy);

                        // Start simulation
                        manager.startSimulation();
                    } catch (Exception e) {
                        SimLogger.printLine("The simulation has been terminated due to an unexpected error");
                        e.printStackTrace();
                        System.exit(0);
                    }

                    Date ScenarioEndDate = Calendar.getInstance().getTime();
                    now = df.format(ScenarioEndDate);
                    SimLogger.printLine("Scenario finished at " + now +  ". It took " + SimUtils.getTimeDifference(ScenarioStartDate,ScenarioEndDate));
                    SimLogger.printLine("----------------------------------------------------------------------");
                }//End of orchestrators loop
            }//End of scenarios loop
        }//End of mobile devices loop

        Date SimulationEndDate = Calendar.getInstance().getTime();
        now = df.format(SimulationEndDate);
        SimLogger.printLine("Simulation finished at " + now +  ". It took " + SimUtils.getTimeDifference(SimulationStartDate,SimulationEndDate));

    }
}
