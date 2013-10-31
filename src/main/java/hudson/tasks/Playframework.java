package hudson.tasks;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;

import java.io.IOException;

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

  public PlayframeworkInstallation getPlayFramework() {
    for( PlayframeworkInstallation i : getDescriptor().getInstallations() ){
      if(mPlayName != null && mPlayName.equals(i.getName())) {
        return i;
      }
    }
    return null;
  }

  @Override
  public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
    listener.getLogger().println( "Play Name is " + mPlayName + " targes is " + mTargets + " Project dir is " + mProjectDir);
    final PlayframeworkInstallation installation = getPlayFramework();
    return installation != null;
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

    public void setInstallations(PlayframeworkInstallation[] installations) {
      mInstallations = installations;
      save();
    }
  }



}
