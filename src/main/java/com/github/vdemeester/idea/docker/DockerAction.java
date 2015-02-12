package com.github.vdemeester.idea.docker;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;

public class DockerAction extends AnAction {

    public DockerAction() {
        super("Docker action 1");
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        System.out.println("Project : "+project);
        System.out.println("Project base path : "+project.getBasePath());
    }
}
