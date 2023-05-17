package com.delphi.mongo_rest_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class CallPythonScript {
    private static String callPythonScript(String param1, String param2) {
        try {
            // Build command to execute Python script with parameters
            String pythonPath = "F:\\UCI\\del_menuAI.py";
            List<String> command = Arrays.asList("python", pythonPath, param1, param2);

            // Execute command and capture output
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            InputStream errorStream = process.getErrorStream();

            // Read output from Python script
            StringBuilder output = new StringBuilder();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Read error output from Python script
            StringBuilder errorOutput = new StringBuilder();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                if (errorOutput.length() > 0) {
                    System.out.println("Warning: Error output from Python script:");
                    System.out.println(errorOutput.toString());
                }
                return output.toString();
            } else {
                System.out.println("Error: Python script execution failed with exit code " + exitCode);
                if (errorOutput.length() > 0) {
                    System.out.println("Error output from Python script:");
                    System.out.println(errorOutput.toString());
                }
                return null; // or handle the error case as desired
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String param1 = "1";
        String param2 = "646326d165d1ac48fbed5d36";
        String output = callPythonScript(param1, param2);
        if (output != null) {
            System.out.println(output);
        } else {
            System.out.println("An error occurred while executing the Python script.");
        }
    }
}


