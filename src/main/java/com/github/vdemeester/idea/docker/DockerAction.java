package com.github.vdemeester.idea.docker;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerCertificateException;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.ContainerInfo;

public class DockerAction extends AnAction {

    public DockerAction() {
        super("Docker action 1");
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        System.out.println("Project : "+project);
        System.out.println("Project base path : "+project.getBasePath());

        try {
            DockerClient docker = DefaultDockerClient.fromEnv().build();

            ContainerConfig config = ContainerConfig.builder()
                    .image("busybox")
                    .cmd("sh", "-c", "while :; do sleep 1; done")
                    .build();
            ContainerCreation creation = docker.createContainer(config);
            String id = creation.id();

            ContainerInfo info = docker.inspectContainer(id);
            System.out.println(info);

            docker.startContainer(id);

            docker.killContainer(id);
        } catch (DockerCertificateException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (DockerException e) {
            e.printStackTrace();
        }
    }
}
