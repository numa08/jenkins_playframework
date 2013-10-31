package hudson.tasks;

import hudson.Extension;
import hudson.FilePath;
import hudson.model.TaskListener;
import hudson.model.Node;
import hudson.tools.DownloadFromUrlInstaller;
import hudson.tools.ToolInstallation;

import java.io.IOException;

import org.kohsuke.stapler.DataBoundConstructor;

public class PlayframeworkInstaller extends DownloadFromUrlInstaller {

	@DataBoundConstructor
	public PlayframeworkInstaller(String id) {
		super(id);
	}

	@Override
	public FilePath performInstallation(ToolInstallation tool, Node node,
			TaskListener log) throws IOException, InterruptedException {
		System.out.println("perform installation");
		return null;
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