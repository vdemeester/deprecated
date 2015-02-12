package com.github.vdemeester.idea.docker;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;

/**
 * Created by vincent on 2/12/15.
 */
public class DockerToolWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        DockerToolWindow window = DockerToolWindow.getInstance(project);
        window.initDockerToolWindow(toolWindow);
    }
}
