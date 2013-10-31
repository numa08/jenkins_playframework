package hudson.tasks;

import hudson.EnvVars;
import hudson.Extension;
import hudson.model.EnvironmentSpecific;
import hudson.model.TaskListener;
import hudson.model.Node;
import hudson.slaves.NodeSpecific;
import hudson.tools.ToolDescriptor;
import hudson.tools.ToolInstaller;
import hudson.tools.ToolProperty;
import hudson.tools.ToolInstallation;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import jenkins.model.Jenkins;

import org.kohsuke.stapler.DataBoundConstructor;

public final class PlayframeworkInstallation extends ToolInstallation implements EnvironmentSpecific<PlayframeworkInstallation>, NodeSpecific<PlayframeworkInstallation> {

    @DataBoundConstructor
    public PlayframeworkInstallation(String name, String home, List<? extends ToolProperty<?>> properties) {
      super(name, home, properties);
    }

    public PlayframeworkInstallation(String name, String home) {
      this(name, home, Collections.<ToolProperty<?>>emptyList());
    }

    public PlayframeworkInstallation forNode(Node node, TaskListener log) throws IOException, InterruptedException {
      return new PlayframeworkInstallation(getName(), translateFor(node, log), getProperties().toList());
    }

    public PlayframeworkInstallation forEnvironment(EnvVars environment) {
      return new PlayframeworkInstallation(getName(), environment.expand(getHome()), getProperties().toList());
    }

    @Extension
    public static class DescriptorImpl extends ToolDescriptor<PlayframeworkInstallation> {

      @Override
      public String getDisplayName() {
        return "Playframeowk";
      }

      @Override
      public PlayframeworkInstallation[] getInstallations() {
        return Jenkins.getInstance().getDescriptorByType(Playframework.DescriptorImpl.class).getInstallations();
      }

      @Override
      public void setInstallations(PlayframeworkInstallation... installations) {
        Jenkins.getInstance().getDescriptorByType(Playframework.DescriptorImpl.class).setInstallations(installations);
      }

      @Override
      public List<? extends ToolInstaller> getDefaultInstallers() {
        return Collections.singletonList(new PlayframeworkInstaller(null));
      }
    }
  }
