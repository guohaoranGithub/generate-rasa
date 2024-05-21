package com.miplus.generaterasa.utils;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DockerClientBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BotManager {
    /**
     * 训练rasa
     *
     * @param projectPath
     */
    public static void trainRasaModel(String projectPath) {
        ProcessBuilder processBuilder = new ProcessBuilder("rasa", "train");
        processBuilder.directory(new File(projectPath));
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Training failed with exit code: " + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动rasa
     *
     * @param composeFilePath
     */
    public static void runDockerCompose(String composeFilePath) {
        //docker compose -f /bot/j7dinrasd-5dx/docker-compose.yml up -d
        ProcessBuilder processBuilder = new ProcessBuilder("docker compose", "-f", composeFilePath, "up", "-d");
        processBuilder.directory(new File(composeFilePath).getParentFile());
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        // todo 这里可以解析输出的日志来判断状态，例如"Starting", "Running"等
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Docker Compose failed with exit code: " + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止rasa
     *
     * @param composeFilePath
     * @throws IOException
     * @throws InterruptedException
     */
    public static void stopDockerCompose(String composeFilePath) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("docker compose", "-f", composeFilePath, "down");
        processBuilder.directory(new File(composeFilePath).getParentFile());
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Docker Compose failed with exit code: " + exitCode);
        }
    }

    /**
     * 检查服务状态
     *
     * @param containerName
     */
    public static void checkContainerStatus(String containerName) {
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();
        List<Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec();

        for (Container container : containers) {
            if (container.getNames()[0].contains(containerName)) {
                InspectContainerResponse containerResponse = dockerClient.inspectContainerCmd(container.getId()).exec();
                System.out.println("Container " + containerName + " status: " + containerResponse.getState().getStatus());
            }
        }
    }
}
