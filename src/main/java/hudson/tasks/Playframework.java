package hudson.tasks;

import hudson.EnvVars;
import hudson.Extension;
import hudson.model.EnvironmentSpecific;
import hudson.model.TaskListener;
import hudson.model.Node;
import hudson.slaves.NodeSpecific;
import hudson.tools.ToolDescriptor;
import hudson.tools.ToolProperty;
import hudson.tools.DownloadFromUrlInstaller;
import hudson.tools.ToolInstallation;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;

public class Playframework extends Builder {

  public static final class PlayframeworkInstallation extends ToolInstallation implements EnvironmentSpecific<PlayframeworkInstallation>, NodeSpecific<PlayframeworkInstallation> {

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
    }
  }

  public static class PlayframeworkInstaller extends DownloadFromUrlInstaller {

    @DataBoundConstructor
    public PlayframeworkInstaller(String id) {
      super(id);
    }

    @Extension
    public static final class DescriptorImpl extends DownloadFromUrlInstaller.DescriptorImpl<PlayframeworkInstaller> {

      @Override
      public String getDisplayName() {
        return "Download playframework";
      }

      @Override
      public boolean isApplicable(Class<? extends ToolInstallation> toolType) {
        return true;
      }
    }
  }
}


