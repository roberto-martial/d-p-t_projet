package pmf.view;

public class PMFView {

	private PMFFrame frame;

	public PMFView() {
		this.frame = new PMFFrame();
	}
	
	public void start() {
		this.getFrame().setVisible(true);
	}

	public PMFFrame getFrame() {
		return frame;
	}

	public void setFrame(PMFFrame frame) {
		this.frame = frame;
	}

}