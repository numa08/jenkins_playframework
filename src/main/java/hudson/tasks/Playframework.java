package hudson.tasks;

import hudson.EnvVars;
import hudson.Extension;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.EnvironmentSpecific;
import hudson.model.TaskListener;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
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

  private final String mPlayName;
  private final String mTargets;
  private final String mProjectDir;

  @DataBoundConstructor
  public Playframework(String playName, String targets, String projectDir) {
    mPlayName = playName;
    mTargets = targets;
    mProjectDir = projectDir;
  }

  public String getPlayname() {
    return mPlayName;
  }

  public String getTargets() {
    return mTargets;
  }

  public String getProjectDir() {
    return mProjectDir;
  }

  @Override
  public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
    return true;
  }

  @Override
  public DescriptorImpl getDescriptor() {
    return (DescriptorImpl)super.getDescriptor();
  }

  @Extension
  public static final class DescriptorImpl extends BuildStepDescriptor<Builder>{

    
    private volatile PlayframeworkInstallation[] mInstallations = new PlayframeworkInstallation[0];

    @Override
    public boolean isApplicable(Class<? extends AbstractProject> jobType) {
      return true;
    }

    @Override
    public String getDisplayName() {
      return "Playframeworkの呼び出し";
    }

    public PlayframeworkInstallation[] getInstallations() {
      return mInstallations;
    }
  }

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


